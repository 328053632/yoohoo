package com.yoohoo.en.web.student.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yoohoo.en.bean.ScheduleInfo;
import com.yoohoo.en.bean.ServiceParam;
import com.yoohoo.en.bean.message.ApplyLessonMessage;
import com.yoohoo.en.bean.message.AuditionMessage;
import com.yoohoo.en.bean.message.RemediationMessage;
import com.yoohoo.en.bean.request.LoginRequest;
import com.yoohoo.en.bean.request.RegisterRequest;
import com.yoohoo.en.bean.response.BaseResp;
import com.yoohoo.en.bean.response.LoginResponse;
import com.yoohoo.en.bean.response.RecodListResp;
import com.yoohoo.en.bean.response.Record;
import com.yoohoo.en.bean.response.ScheduleListResponse;
import com.yoohoo.en.cache.util.ChangeTools;
import com.yoohoo.en.constant.ApplicationConstant;
import com.yoohoo.en.constant.ResponseCode;
import com.yoohoo.en.dao.mapper.TStudentDynamicMapper;
import com.yoohoo.en.dao.model.TClassSchedule;
import com.yoohoo.en.dao.model.TLessonChapter;
import com.yoohoo.en.dao.model.TLibrary;
import com.yoohoo.en.dao.model.TMessage;
import com.yoohoo.en.dao.model.TStudentDynamic;
import com.yoohoo.en.dao.model.TStudentInfo;
import com.yoohoo.en.dao.model.TTeacherInfo;
import com.yoohoo.en.dao.model.ext.ConsumeExample;
import com.yoohoo.en.dao.model.ext.ConsumeLog;
import com.yoohoo.en.dao.model.ext.LessonInfoExt;
import com.yoohoo.en.model.PageModel;
import com.yoohoo.en.service.ICategoryService;
import com.yoohoo.en.service.IClassDefineService;
import com.yoohoo.en.service.IClassScheduleService;
import com.yoohoo.en.service.ILessonInfoService;
import com.yoohoo.en.service.IMessageService;
import com.yoohoo.en.service.IStudentInfoService;
import com.yoohoo.en.service.IStudentService;
import com.yoohoo.en.service.ISystemConfigService;
import com.yoohoo.en.service.ITalkCloudService;
import com.yoohoo.en.service.IVerifycodeService;
import com.yoohoo.en.utils.DateUtil;
import com.yoohoo.en.utils.SessionUtil;
import com.yoohoo.en.utils.StringUtil;
import com.yoohoo.en.web.resp.ClassTypeListResp;
import com.yoohoo.en.web.spring.ConfigProper;
import com.yoohoo.en.web.student.service.StudentLoginService;
import com.yoohoo.en.web.student.service.TLessonChapterSevice;
import com.yoohoo.en.web.student.service.TLibraryChapterService;
import com.yoohoo.en.web.teacher.service.TTeacherScheduleService;

@RestController
@RequestMapping("/stu/user")
public class StudentController {
    @Autowired
    private StudentLoginService studentLoginService;//学生登录

    @Autowired
    private IStudentInfoService studentInfoService;//学生信息

    @Autowired
    private IVerifycodeService verifycodeService;//验证码

    @Autowired
    private IClassDefineService classDefineService;

    @Autowired
    private ISystemConfigService sysConfigService;

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITalkCloudService talkCloudService;

    @Autowired
    private IClassScheduleService classScheduleService;

    @Autowired
    private ILessonInfoService lessonInfoService;

    @Autowired
    private ICategoryService categoryService;


    @Autowired
    private TTeacherScheduleService tTeacherScheduleService;

    @Autowired
    private TLessonChapterSevice tLessonChapterSevice;

    @Resource(name="libraryChapterService")
    private TLibraryChapterService libraryChapterService;

    @Autowired
    private ConfigProper properties;
    
    @Autowired
    private TStudentDynamicMapper tStudentDynamicMapper;

    private static Logger logger = Logger.getLogger(StudentController.class);


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginResponse login(LoginRequest request, HttpSession session) {
        return studentLoginService.login(request.getAccount(), request.getPassword(), session);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public BaseResp register(RegisterRequest request, HttpSession session) {
        TStudentInfo studentInfo = studentInfoService.queryRegistered(request.getMsisdn());
        BaseResp response = new BaseResp();
        if(studentInfo != null){
            response.setResultCodeInfo(ResponseCode.SEND_VERIFICATION_CODE_ERROR_MSISDN_IS_REGISTER);
            return response;
        }
        
        studentInfo = new TStudentInfo();
        studentInfo.setMsisdn(request.getMsisdn());
        studentInfo.setPasswd(request.getPassword());
        studentInfo.setStatus(ApplicationConstant.USER_STATUS_NORMAL);
        studentInfo.setBalance(0);
        studentInfo.setEarbBalance(0);
        studentInfo.setPresentBalance(0);
        studentInfo.setRegStatus("1");


        boolean valid = verifycodeService.checkCode(request.getMsisdn(),
                request.getCode(),
                ApplicationConstant.VERIFY_CODE_TYPE_REGISTER);

        if (!valid) {
            response.setResultCodeInfo(ResponseCode.VERIFY_CODE_LESS_EFFICACY);
            return response;
        }

        studentInfo = studentInfoService.insertOrUpdate(studentInfo);

        if (studentInfo.getUserId() == 0) {
            response.setResultCodeInfo(ResponseCode.REGISTER_FAILED);
            return response;
        }
        SessionUtil.INSTANCE.putSessionStudent(session, studentInfo);
        return response;
    }

    /**
     * 发送短信验证码
     *
     * @return
     */
    @RequestMapping(value = "/sendVerification", method = RequestMethod.GET)
    @ResponseBody
    public BaseResp sendVerificationCode(String phone, Integer type) {
        BaseResp resp = new BaseResp();
        boolean userIsExists = studentInfoService.checkUserIsExists(phone);
        //1注册 2重置密码
        if (Integer.compare(type, 1) == 0 && userIsExists) {
            //boolean userIsExists = studentInfoService.checkUserIsExists(msisdn);
            resp.setResultCodeInfo(ResponseCode.SEND_VERIFICATION_CODE_ERROR_MSISDN_IS_REGISTER);
            return resp;
        }
        if (Integer.compare(type, 2) == 0 && !userIsExists) {
            resp.setResultCodeInfo(ResponseCode.SEND_VERIFICATION_CODE_ERROR_MSISDN_IS_NOT_REGISTER);
            return resp;
        }
        boolean success = studentInfoService.sendVerificationCode(phone, type);
        if (!success) {
            resp.setResultCodeInfo(ResponseCode.SEND_VERIFICATION_CODE_ERROR);
        }
        return resp;
    }
	
	/**
	 * 保存动态信息
	 */
	@ResponseBody
	@RequestMapping("/dynamic/save")
	public BaseResp dynamic(TStudentDynamic dynamic){
		BaseResp resp = new BaseResp();
		tStudentDynamicMapper.insert(dynamic);
		return resp;
	}
	
	/**
	 * 查看动态信息
	 */
	@ResponseBody
	@RequestMapping("/dynamic/info/{studentId}")
	public BaseResp dynamicInfo(Long studentId){
		BaseResp resp = new BaseResp();
		TStudentDynamic tStudentDynamic = new TStudentDynamic();
		tStudentDynamic.setStudentId(studentId);
		resp.put("data", tStudentDynamicMapper.selectOne(tStudentDynamic));
		return resp;
	}

    /**
     * 获取班级类型列表
     *
     * @return
     */
    @RequestMapping(value = "/classTypeList", method = RequestMethod.GET)
    @ResponseBody
    public ClassTypeListResp classTypeList() {
        logger.debug("获取班级类型列表......");
        ClassTypeListResp resp = new ClassTypeListResp();
        resp.setClassDefines(classDefineService.list());
        resp.setLessonTimes(sysConfigService.lessonTimeList());
        return resp;
    }

    /**
     * 申请试听
     *
     * @param applyMessage
     * @return
     */
    @RequestMapping(value = "/applyAudition", method = RequestMethod.POST)
    @ResponseBody
    public BaseResp applyAudition(AuditionMessage applyMessage) {
        BaseResp resp = new BaseResp();
        TMessage message = new TMessage();
        message.setStatus(ApplicationConstant.MESSAGE_STATUS_NOT_READ);
        message.setAddTime(new Date());
        message.setCotent(JSON.toJSONString(applyMessage));
        message.setmType(applyMessage.getMessageType());
        message.setuType(ApplicationConstant.USER_TYPE_STUDENT);
        messageService.insert(message);
        if (message.getmId() == 0) {
            resp.setResultCodeInfo(ResponseCode.ERROR);
        }
        return resp;
    }

    /**
     * 获取学生课程表
     *
     * @return
     */
    @RequestMapping(value = "/scheduleList", method = RequestMethod.GET)
    @ResponseBody
    public ScheduleListResponse scheduleList(Long monday, Long sunday, Integer type, HttpSession session) {
        ScheduleListResponse response = new ScheduleListResponse();
        TStudentInfo studentInfo = SessionUtil.INSTANCE.getLoginStudent(session);
        if (null == studentInfo) {
            response.setResultCodeInfo(ResponseCode.USER_NOT_LOGIN);
            return response;
        }
        Map<String, Date> dateMap = null;
        if (monday == null && sunday == null) {
            dateMap = DateUtil.dateMapper();
        } else {
            dateMap = DateUtil.dateMapper(new Date(monday), new Date(sunday), type);
        }

        Map<String, List<ScheduleInfo>> scheduleMapp = studentService.scheduleList(studentInfo.getUserId().intValue(), dateMap);
        response.setScheduleMapper(scheduleMapp);
        response.setDateMapper(dateMap);
        return response;
    }

    /**
     * 获取直播链接
     *
     * @param scheduleId
     * @return
     */
    @RequestMapping(value = "/joinClassRoomUrl", method = RequestMethod.GET)
    @ResponseBody
    public BaseResp getJoinClassRoomUrl(Integer scheduleId, HttpSession session) {
        BaseResp response = new BaseResp();
        TStudentInfo studentInfo = SessionUtil.INSTANCE.getLoginStudent(session);
        if (null == studentInfo) {
            response.setResultCodeInfo(ResponseCode.USER_NOT_LOGIN);
            return response;
        }
        //判断太阳花的数量
        if(studentInfo.getBalance() <0){
            response.setMessage("1");
        }
        if(studentInfo.getBalance() >=0 && studentInfo.getBalance()<1000){
            response.setMessage("2");
        }
        TClassSchedule tClassSchedule = classScheduleService.queryObject(scheduleId.longValue());
        if (tClassSchedule == null || StringUtil.isEmpty(tClassSchedule.getRoomId())) {
            return response;
        }

        String studentJoinRoomUrl = talkCloudService.getStudentJoinRoomUrl(tClassSchedule.getRoomId(),
                studentInfo.getUserId().intValue());
        response.put("classUrl", studentJoinRoomUrl);
        return response;
    }


    /**
     *
     * 获取家长巡课链接
     */
    @RequestMapping("/joinParentsClassRoomUrl")
    @ResponseBody
    public BaseResp getJoinParentsClassRoomUrl(Integer scheduleId, HttpSession session){
        BaseResp baseResp=new BaseResp();
        TStudentInfo studentInfo = SessionUtil.INSTANCE.getLoginStudent(session);
        //判断太阳花的数量
        if(studentInfo.getBalance() <0){
            baseResp.setMessage("1");
        }
        if(studentInfo.getBalance() >=0 && studentInfo.getBalance()<1000){
            baseResp.setMessage("2");
        }
        if (null == studentInfo) {
            baseResp.setResultCodeInfo(ResponseCode.USER_NOT_LOGIN);
            return baseResp;
        }
        TClassSchedule tClassSchedule = classScheduleService.queryObject(scheduleId.longValue());
        if (tClassSchedule == null || StringUtil.isEmpty(tClassSchedule.getRoomId())) {
            return baseResp;
        }
        String parentsUrl=  talkCloudService.getParentsJoinRoomUrl(tClassSchedule.getRoomId(),studentInfo.getUserId().intValue());
        baseResp.put("classUrl",parentsUrl);
        return baseResp;
    }

    /**
     * 申请请假
     *
     * @param scheduleId
     * @return
     */
    @RequestMapping(value = "/applyLeave", method = RequestMethod.POST)
    @ResponseBody
    public BaseResp applyLeave(Integer scheduleId, HttpSession session) {
        BaseResp response = new BaseResp();
        TStudentInfo studentInfo = SessionUtil.INSTANCE.getLoginStudent(session);
        if (null == studentInfo) {
            response.setResultCodeInfo(ResponseCode.USER_NOT_LOGIN);
            return response;
        }

        boolean success = studentService.applyLeave(scheduleId.longValue(), studentInfo);
        if (!success) {
            response.setResultCodeInfo(ResponseCode.ERROR);
        }
        return response;
    }


    /**
     * 申请补课或者申请请假
     *
     * @return
     */
    @RequestMapping(value = "/applyRemediation", method = RequestMethod.POST)
    @ResponseBody
    public BaseResp applyRemediation(RemediationMessage message,
                                     HttpSession session) {
        BaseResp response = new BaseResp();
        TStudentInfo studentInfo = SessionUtil.INSTANCE.getLoginStudent(session);
        if (null == studentInfo) {
            response.setResultCodeInfo(ResponseCode.USER_NOT_LOGIN);
            return response;
        }
        message.setName(studentInfo.getName());
        message.setApplyPhone(studentInfo.getMsisdn());
        message.setStudentId(studentInfo.getUserId());
      //  message.setMessageType(ApplicationConstant.MESSAGE_TYPE_REMEDIATION);
        boolean success = studentService.applyRemediation(message);
        if (!success) {
            response.setResultCodeInfo(ResponseCode.ERROR);
        }
        return response;
    }





    /**
     * 查询学生在学课程列表
     *
     * @param pageNo
     * @param limit
     * @param session
     * @return
     */
    @RequestMapping(value = "/myStudyLessonList", method = RequestMethod.GET)
    @ResponseBody
    public BaseResp myStudyLessonList(int pageNo, int limit, int categoryId, String name, HttpSession session,Integer lessonId) {
        BaseResp response = new BaseResp();
        TStudentInfo studentInfo = SessionUtil.INSTANCE.getLoginStudent(session);
        if (null == studentInfo) {
            response.setResultCodeInfo(ResponseCode.USER_NOT_LOGIN);
            return response;
        }
        ServiceParam param = new ServiceParam();
        param.setPageNo(pageNo);
        param.setLimit(limit);
        param.setUserId(studentInfo.getUserId());
        param.setName(name);
        param.setCategoryId(categoryId);
        if(lessonId!=null){
                param.setLessonId(lessonId.longValue());
        }
        PageModel<LessonInfoExt> page = studentService.myStudyLessonList(param);
        response.put("page", page);
        return response;
    }

    @RequestMapping(value = "/myStudyChapterList/{lessonId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResp myStudyChapterList(@PathVariable(value = "lessonId") Integer lessonId, HttpSession session) {
        TStudentInfo studentInfo = SessionUtil.INSTANCE.getLoginStudent(session);
        ServiceParam param = new ServiceParam();
        param.setLessonId(lessonId.longValue());
        //param.setPageNo(pageNo);
        //param.setLimit(limit);
        param.setUserId(studentInfo.getUserId());
        return studentService.myStudyChapterList(param);
    }

    /**
     * 课程库
     *
     * @return
     */
    @RequestMapping(value = "/queryLessonList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResp queryLessonList(String lessonName, Integer categoryId, int pageNo, int limit, HttpSession session) throws InvocationTargetException, IllegalAccessException {
        return lessonInfoService.queryLessons(lessonName, categoryId, pageNo, limit,0);
    }


    /**
     * 课程库查询课节（课程路径）
     */
    @RequestMapping(value = "/queryChapterList/{lessonId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResp queryChapterList(@PathVariable(value = "lessonId") Integer lessonId) {

        return lessonInfoService.queryChapters(lessonId.longValue());
    }

    /**
     * 查询学生申请情况
     *
     * @return
     */
    @RequestMapping(value = "/applyStatus", method = RequestMethod.GET)
    @ResponseBody
    public BaseResp applyStatus(int lessonId, int chapterId, HttpSession session) {
        TStudentInfo studentInfo = SessionUtil.INSTANCE.getLoginStudent(session);
        if(Objects.isNull(studentInfo)){
        	return new BaseResp();
        }
        return lessonInfoService.applyStatus(studentInfo.getUserId(), lessonId, chapterId);
    }

    /**
     * 绑定孩子信息
     *
     * @param name
     * @param enName
     * @param birthday
     * @param session
     * @return
     */
    @RequestMapping(value = "/addChild", method = RequestMethod.POST)
    @ResponseBody
    public BaseResp addChild(String name, String enName, String birthday, HttpSession session) {
        BaseResp response = new BaseResp();
        TStudentInfo studentInfo = SessionUtil.INSTANCE.getLoginStudent(session);
        if (null == studentInfo) {
            response.setResultCodeInfo(ResponseCode.USER_NOT_LOGIN);
            return response;
        }

        studentInfo.setName(StringUtils.isNotEmpty(name) ? name : studentInfo.getMsisdn());

        if (StringUtils.isNotEmpty(enName)) {
            studentInfo.setEnName(enName);
        }

        if (StringUtils.isNotEmpty(birthday)) {
            studentInfo.setBirthday(birthday);
        }
        boolean success = studentInfoService.update(studentInfo);

        if (!success) {
            response.setResultCodeInfo(ResponseCode.ERROR);
        }

        SessionUtil.INSTANCE.putSessionStudent(session, studentInfo);
        return response;
    }

    @RequestMapping(value = "/applyLesson", method = RequestMethod.POST)
    @ResponseBody
    public BaseResp applyLesson(ApplyLessonMessage applyLessonMsg, HttpSession session) {
        BaseResp resp = new BaseResp();
        TStudentInfo studentInfo = SessionUtil.INSTANCE.getLoginStudent(session);
        if (null == studentInfo) {
            resp.setResultCodeInfo(ResponseCode.USER_NOT_LOGIN);
            return resp;
        }
        applyLessonMsg.setName(studentInfo.getName());
        TMessage message = new TMessage();
        message.setStatus(ApplicationConstant.MESSAGE_STATUS_NOT_READ);
        message.setAddTime(new Date());
        message.setCotent(JSON.toJSONString(applyLessonMsg));
        message.setmType(applyLessonMsg.getMessageType());
        message.setuType(ApplicationConstant.USER_TYPE_STUDENT);
        message.setuId(studentInfo.getUserId());
        messageService.insert(message);
        if (message.getmId() == 0) {
            resp.setResultCodeInfo(ResponseCode.ERROR);
        }
        return resp;
    }

    /**
     * 查询用户消费记录
     *
     * @return
     */
    @RequestMapping(value = "/consumerLog/{type}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResp consumerLog(@PathVariable("type") Integer type, String stime, String etime,
                                Integer start, Integer limit, HttpSession session) {
        BaseResp response = new BaseResp();
        TStudentInfo studentInfo = SessionUtil.INSTANCE.getLoginStudent(session);
        if (null == studentInfo) {
            response.setResultCodeInfo(ResponseCode.USER_NOT_LOGIN);
            return response;
        }

        ConsumeExample example = new ConsumeExample();
        example.setType(2);
        example.setStudentId(studentInfo.getUserId().intValue());
        if (0 != type) {
            example.setLessonType(type);
        }
        example.setStime(DateUtil.parse(stime, "yyyy-MM-dd"));
        example.setEtime(DateUtil.parse(etime, "yyyy-MM-dd"));
        example.setStart(start);
        example.setLimit(limit);
        List<ConsumeLog> consumeLogs = studentService.comsumeLog(example);
        int totalCount = studentService.countComsumeLog(example);
        response.put("logList", consumeLogs);
        response.put("total", totalCount);
        return response;
    }

    /**
     * 查询号码对应的孩子信息
     * @param session
     * @return
     */
    @RequestMapping(value = "/loadChildInfo", method = RequestMethod.GET)
    @ResponseBody
    public BaseResp loadChildInfo(HttpSession session) {
        BaseResp response = new BaseResp();
        TStudentInfo loginStudent = SessionUtil.INSTANCE.getLoginStudent(session);
        if (loginStudent == null) {
            response.setResultCodeInfo(ResponseCode.ERROR);
            return response;
        }
        response.put("childInfo", loginStudent);
        return response;
    }



    /**
     * 获取数据库最新用户信息, 并更新session中的用户信息
     */
    @RequestMapping(value = "/loginStudentInfo", method = RequestMethod.GET)
    @ResponseBody
    public BaseResp loginStudentInfo(HttpSession session) {
        BaseResp resp = new BaseResp();
        TStudentInfo studentInfo = SessionUtil.INSTANCE.getLoginStudent(session);
        if (studentInfo == null) {
            resp.setResultCodeInfo(ResponseCode.USER_NOT_LOGIN);
            return resp;
        }

        TStudentInfo info = studentInfoService.query(studentInfo.getMsisdn());
        SessionUtil.INSTANCE.putSessionStudent(session, info);
        resp.put("studentInfo", info);
        return resp;
    }


    /**
     *  获取学生扣费成功后的上课录像
     */
    @RequestMapping(value="/getStuVideoReplay",method = RequestMethod.GET)
    @ResponseBody
    public BaseResp getStuVideoReplay(Integer scheduleId){
        BaseResp resp=new BaseResp();
        //1.根据scheduleId从t_class_schedule表中获取用户的roomId;
        TClassSchedule tClassSchedule = classScheduleService.queryObject(scheduleId.longValue());
        if(null==tClassSchedule){
            resp.setResultCodeInfo(ResponseCode.USER_NOT_LOGIN);
            return resp;
        }
        //2.使用http://IP  地址: : 端口号/ / WebAPI/getrecordlis     参数:key,serial(房间号) 获取录像地址
        RecodListResp recodListResp=talkCloudService.getRecordList(tClassSchedule.getRoomId());
        if(!recodListResp.getResult().equals("0"))
        {
            if(recodListResp.getResult().equals("-1")){

                resp.setResultCodeInfo(ResponseCode.NO_VIEO);
                return resp;
            }
            //如果不为0说明出错
            resp.setResultCodeInfo(ResponseCode.GET_RECORD_VIDE_ERROR);
            return resp;
        }
        List<Record> recordlist = recodListResp.getRecordlist();
        for (int i=0;i<recordlist.size();i++){
            String duration = recordlist.get(i).getDuration();
            recordlist.get(i).setDuration( ChangeTools.formatSeconds(Integer.parseInt(duration)));
            long video_size = Long.parseLong(recordlist.get(i).getSize());
            recordlist.get(i).setSize(ChangeTools.getPrintSize(video_size));
        }
        resp.put("recodListResp",recodListResp);
        return resp;
    }

    /**
     * 获取菜单列表
     * @return
     */
    @RequestMapping("menuData")
    @ResponseBody
    public BaseResp menuData() {
        BaseResp resp = new BaseResp();
        resp.put("menus", categoryService.menuData());
        return resp;
    }


    /**
     *
     * 获取当前课节关联文件
     */
    @RequestMapping("/getChapterFile")
    @ResponseBody
    public BaseResp getChapterFile(Integer chapterId,Integer type){
        BaseResp resp=new BaseResp();
     List<TLibrary>  libraryList=   libraryChapterService.getFiles(chapterId,type);
     if(null!=libraryList && libraryList.size()>0 ){
         resp.put("fileList",libraryList);
     }else{
         resp.setCode("500");
     }
        return  resp;
    }



    /**
     * 根据时间和课时选择当前可以上课的老师
     * @param dateTime 当天时间 格式:2018-11-16
     * @param timeSchedule 时刻 格式:07:00-07:25
     */
    @RequestMapping("/getTeacherListByTime")
    @ResponseBody
    public BaseResp getTeacherListByTime(String dateTime,String timeSchedule) throws ParseException {
        //根据当前时间和时刻返回可选择的老师集合
        BaseResp baseResp=new BaseResp();
        List<TTeacherInfo>  teacherList = null;
        baseResp.put("teacherList",teacherList);
        return baseResp;
    }


    /**
     *
     * 根据LessonId 获取课节Id
     */

    @RequestMapping("/getChapterListByLessonId")
    @ResponseBody
    public BaseResp getChapterListByLessonId(String lessonId){
       BaseResp baseResp=new BaseResp();
       List<TLessonChapter>  lessonChapterList= tLessonChapterSevice.getChapterListByLessonId(lessonId);
       if(lessonChapterList!=null){
           baseResp.put("lessonChapterList",lessonChapterList);
       }
        return  baseResp;
    }
}
