package com.yoohoo.en.web.teacher.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yoohoo.en.bean.message.ApplyLessonMessage;
import com.yoohoo.en.bean.request.LoginRequest;
import com.yoohoo.en.bean.request.TeacherRegisterRequest;
import com.yoohoo.en.bean.response.BaseResp;
import com.yoohoo.en.bean.response.LoginResponse;
import com.yoohoo.en.constant.ApplicationConstant;
import com.yoohoo.en.constant.ResponseCode;
import com.yoohoo.en.dao.model.TLessonInfo;
import com.yoohoo.en.dao.model.TMessage;
import com.yoohoo.en.dao.model.TTeacherInfo;
import com.yoohoo.en.dao.model.TTeacherScheduleNv;
import com.yoohoo.en.dao.model.ext.TeacherScheduleTimesOfDay;
import com.yoohoo.en.service.ILessonChapterService;
import com.yoohoo.en.service.ILessonInfoService;
import com.yoohoo.en.service.IMessageService;
import com.yoohoo.en.service.ITeacherInfoService;
import com.yoohoo.en.service.IVerifycodeService;
import com.yoohoo.en.utils.DateUtil;
import com.yoohoo.en.utils.SessionUtil;
import com.yoohoo.en.web.params.TeacherScheduleQuery;
import com.yoohoo.en.web.params.TeacherScheduleSave;
import com.yoohoo.en.web.spring.ConfigProper;
import com.yoohoo.en.web.teacher.service.TClassScheduleService;
import com.yoohoo.en.web.teacher.service.TTeacherScheduleService;
import com.yoohoo.en.web.teacher.service.TeacherLoginService;

@RestController
@RequestMapping("/teach/user")
public class TeacherController
{
    @Autowired
    private TeacherLoginService loginService;

    @Autowired
    private ITeacherInfoService teacherInfoService;

    @Autowired
    private ILessonInfoService lessonInfoService;

    @Autowired
    private ILessonChapterService lessonChapterService;
    @Autowired
    private ConfigProper configProper;

    @Autowired
    private TTeacherScheduleService tTeacherScheduleService;

    @Autowired
    private IMessageService messageService;

    @Autowired
    private TClassScheduleService tClassScheduleService;
    @Autowired
    private IVerifycodeService verifycodeService;//验证码
    @RequestMapping("/login")
    @ResponseBody
    public LoginResponse login(LoginRequest request, HttpSession session)
    {
        return loginService.login(request.getAccount(), request.getPassword(),request.getTeacherType(),session);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public BaseResp register(TeacherRegisterRequest request, HttpSession session) {
        TTeacherInfo teacherInfo = teacherInfoService.query(request.getAccount());
        BaseResp response = new BaseResp();
        if(teacherInfo != null){
            response.setResultCodeInfo(ResponseCode.SEND_VERIFICATION_CODE_ERROR_MSISDN_IS_REGISTER);
            return response;
        }
        teacherInfo = new TTeacherInfo();
        BeanUtils.copyProperties(request, teacherInfo);
        teacherInfo.setCreateTime(new Date());
        teacherInfo.setUpdateTime(new Date());
        teacherInfo.setPositionType(null);

        teacherInfo = teacherInfoService.insertOrUpdate(teacherInfo);

        if (teacherInfo.getTeacherId() == 0) {
            response.setResultCodeInfo(ResponseCode.REGISTER_FAILED);
            return response;
        }
        SessionUtil.INSTANCE.putSessionStudent(session, teacherInfo);
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
        boolean userIsExists = teacherInfoService.checkUserIsExists(phone);
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
        boolean success = teacherInfoService.sendVerificationCode(phone, type);
        if (!success) {
            resp.setResultCodeInfo(ResponseCode.SEND_VERIFICATION_CODE_ERROR);
        }
        return resp;
    }

    @RequestMapping("/getTeacherLessonPeriod")
    @ResponseBody
    public BaseResp login(String date, HttpSession session)
    {
        BaseResp resp = new BaseResp();
        TTeacherInfo teacherInfo = SessionUtil.INSTANCE.getLoginTeacher(session);
        if (null != teacherInfo)
        {
            return teacherInfoService.queryLessonPeriodByDate(date, teacherInfo.getTeacherId(),teacherInfo.getTeacherType());
        }
        return resp;
    }

    /**
     * 查询老师每天的课程数
     *
     * @param startTime
     * @param endTime
     * @param session
     * @return
     */
    @RequestMapping("/lessonCountOfDay")
    @ResponseBody
    public BaseResp lessonCountOfDay(String startTime, String endTime, HttpSession session)
    {
        BaseResp resp = new BaseResp();
        TTeacherInfo loginTeacher = SessionUtil.INSTANCE.getLoginTeacher(session);
        if (loginTeacher == null)
        {
            resp.setResultCodeInfo(ResponseCode.USER_NOT_LOGIN);
            return resp;
        }

        String TeacherType="";
        //区分老师员工类型
        if(loginTeacher.getTeacherType()==1){
            TeacherType="1";
        }else if (loginTeacher.getTeacherType()==2){
            TeacherType="2";
        }else{
            TeacherType="3";
        }
        //获取老师某段时间之内的课程
        List<TeacherScheduleTimesOfDay> teacherScheduleInfos = teacherInfoService.queryScheduleTimesOfDay(TeacherType,loginTeacher.getTeacherId(),
            startTime,
            endTime);
        resp.put("data", teacherScheduleInfos);
        return resp;
    }


    /**
     *
     * 老师身份查询课程库
     */

    @RequestMapping(value = "/queryLessonList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResp queryLessonList(String lessonName, Integer categoryId, int pageNo, int limit, HttpSession session) throws InvocationTargetException, IllegalAccessException {
        TTeacherInfo loginTeacher = SessionUtil.INSTANCE.getLoginTeacher(session);
        Integer type=0;
        if(loginTeacher!=null){
          type=1;
        }
        return lessonInfoService.queryLessons(lessonName, categoryId, pageNo, limit,type);
    }


    /**
     * 老师身份获取当前课程详细信息
     */
    @RequestMapping(value = "/detail/{lessonId}",method = RequestMethod.GET)
    @ResponseBody
    public BaseResp LessonDetail(@PathVariable("lessonId") Integer lessonId){
        BaseResp resp=new BaseResp();
        TLessonInfo tLessonInfo = lessonInfoService.queryById(lessonId.longValue());
        int count = lessonChapterService.countChapter(lessonId.longValue());
        resp.put("lessonInfo", tLessonInfo);
        resp.put("prefix", configProper.getCoverUrlPrefix());
        resp.put("count", count);
        return  resp;
    }


    /**
     *
     * 老师申请上课或者开发课程
     */

    @RequestMapping(value="/applyLesson",method = RequestMethod.POST)
    @ResponseBody
    public BaseResp ApplyLesson(ApplyLessonMessage applyLessonMessage,HttpSession session){
        BaseResp baseResp=new BaseResp();
        TTeacherInfo loginTeacher = SessionUtil.INSTANCE.getLoginTeacher(session);
        if(loginTeacher!=null){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            String date= format.format(new Date());
            applyLessonMessage.setLessonDate(String.valueOf(date));
            TMessage tMessage=new TMessage();
            tMessage.setStatus(ApplicationConstant.MESSAGE_STATUS_NOT_READ);
            tMessage.setAddTime(new Date());
            tMessage.setCotent(JSON.toJSONString(applyLessonMessage));
            tMessage.setmType(applyLessonMessage.getMessageType());
            tMessage.setuType(ApplicationConstant.USER_TYPE_TEACHER);
            tMessage.setuId(loginTeacher.getTeacherId().longValue());
            int insert = messageService.insert(tMessage);
            if(insert>0){

                return baseResp;
            }
        }
        baseResp.setCode("1");
        return baseResp;
    }

    /**
     * 根据老师Id获取老师所有详情信息
     */
    @RequestMapping("/getTeacherInfo/{teacherId}")
    @ResponseBody
    public BaseResp getTeacherInfo(@PathVariable("teacherId") Integer teacherId){
        BaseResp baseResp=new BaseResp();
        TTeacherInfo teacherInfo=   teacherInfoService.selectById(teacherId);
        baseResp.put("teacherInfo",teacherInfo);
        return  baseResp;
    }
    
    /**
     * 存储老师时刻表
     */
    @RequestMapping("/saveTeacherSchedule")
    @ResponseBody
    public BaseResp saveTeacherSchedule(TeacherScheduleSave data,HttpSession session) throws ParseException {
        TTeacherInfo teacherInfo = SessionUtil.INSTANCE.getLoginTeacher(session);
        data.setTeacherId(teacherInfo.getTeacherId().longValue());
        BaseResp baseResp=new BaseResp();
        if(data.getIfWeekBatch() !=null && data.getIfWeekBatch() == 1){
        	tTeacherScheduleService.deleteTeacherScheduleAfterToday(teacherInfo.getTeacherId().longValue());
        }
        tTeacherScheduleService.saveTeacherSchedule(data.buildScheduleList());
        return  baseResp;
    }

    /**
     * 获取老师时刻表
     */
    @RequestMapping("/getSchedule")
    @ResponseBody
    public BaseResp getSchedule(TeacherScheduleQuery query, HttpSession session) throws ParseException {
        TTeacherInfo teacherInfo = SessionUtil.INSTANCE.getLoginTeacher(session);
        BaseResp baseResp=new BaseResp();
        query.setTeacherId(teacherInfo.getTeacherId().longValue());
        query.setDate(DateUtil.getIntValueFromLocalDate(LocalDate.now()));
        baseResp.put("scheduleList", tTeacherScheduleService.queryTeacherSchedule(query));
        return  baseResp;
    }

    /**
     * 获取老师日程表
     */
    @RequestMapping("/getTeacherDateScheduleList")
    @ResponseBody
    public BaseResp getTeacherDateScheduleList(TeacherScheduleQuery query, HttpSession session) throws ParseException {
        TTeacherInfo teacherInfo = SessionUtil.INSTANCE.getLoginTeacher(session);
        BaseResp baseResp=new BaseResp();
        query.setTeacherId(teacherInfo.getTeacherId().longValue());
        baseResp.put("scheduleList", tTeacherScheduleService.queryTeacherSchedule(query));
        return  baseResp;
    }


    /**
     * 修改老师时刻表
     */

    @RequestMapping("/editTeacherSchedule")
    @ResponseBody
    public BaseResp editTeacherSchedule(List<TTeacherScheduleNv> modelList,HttpSession session){
    	TTeacherInfo teacherInfo = SessionUtil.INSTANCE.getLoginTeacher(session);
        BaseResp baseResp=new BaseResp();
        for(TTeacherScheduleNv e:modelList){
        	e.setAddBaseInfo(teacherInfo.getTeacherId());
        }
        tTeacherScheduleService.updateTeacherSchedule(modelList);
        return baseResp;
    }

}

