//package com.yoohoo.en.web.student.controller;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import java.text.ParseException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.time.DateUtils;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.yoohoo.en.bean.response.CreateRoomResp;
//import com.yoohoo.en.bean.response.RecodListResp;
//import com.yoohoo.en.bean.response.Record;
//import com.yoohoo.en.cache.util.ChangeTools;
//import com.yoohoo.en.dao.model.TClass;
//import com.yoohoo.en.dao.model.TClassSchedule;
//import com.yoohoo.en.dao.model.TClassStudentR;
//import com.yoohoo.en.dao.model.TLibrary;
//import com.yoohoo.en.dao.model.TStudentLessonSchedule;
//import com.yoohoo.en.dao.model.ext.TClassExt;
//import com.yoohoo.en.dao.model.ext.TClassScheduleExt;
//import com.yoohoo.en.dao.model.ext.TStudentLessonScheduleExt;
//import com.yoohoo.en.lesson.service.TLibraryChapterService;
//import com.yoohoo.en.library.service.TLibraryService;
//import com.yoohoo.en.mcore.utils.PageUtils;
//import com.yoohoo.en.mcore.utils.R;
//import com.yoohoo.en.mcore.utils.ShiroUtils;
//import com.yoohoo.en.msys.service.SysUserRoleService;
//import com.yoohoo.en.service.IClassScheduleService;
//import com.yoohoo.en.service.IClassService;
//import com.yoohoo.en.service.IStudentLessonScheduleService;
//import com.yoohoo.en.service.ITalkCloudService;
//import com.yoohoo.en.utils.StringUtil;
//
//
///**
// * 班级表
// *
// * @author YuanzongInfo-HYL
// * @email admin@yuanzonginfo.com
// * @date 2018-02-03 14:22:14
// */
//@Controller
//@RequestMapping("tclass")
//public class TClassController {
//	@Autowired
//	private IClassService tClassService;
//
//	@Autowired
//	private IClassScheduleService tClassScheduleService;
//
//	@Autowired
//	private IStudentLessonScheduleService tStudentLessonScheduleService;
//
//	@Autowired
//	private ITalkCloudService  talkCloudService;
//
//
//
//	@RequestMapping("/tclass.html")
//	public String page(){
//		return "class/tclass.html";
//	}
//
//
//	@RequestMapping(value = "/videoReplay",method = RequestMethod.GET)
//	public String VideoReplayPage(Integer id, Model model){
//		model.addAttribute("scheduleId",id);
//		return "class/video-replay.jsp";
//	}
//
//	/**
//	 * 列表
//
//	@ResponseBody
//	@RequestMapping("/list")
//	@RequiresPermissions("tclass:list")
//	public R list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//		Query query = new Query(params);
//		List<TClassExt> tClassList = tClassService.queryListExt(query);
//		int total = tClassService.queryTotal(query);
//		PageUtils pageUtil = new PageUtils(tClassList, total, query.getLimit(), query.getPage());
//		return R.ok().put("page", pageUtil);
//	}
// */
//
//	/**
//	 * 信息
//	 */
//	@ResponseBody
//	@RequestMapping("/info/{classId}")
//	public R info(@PathVariable("classId") Integer classId){
//		TClass tClass = tClassService.queryObjectExt(Long.valueOf(classId));
//		return R.ok().put("tClass", tClass);
//	}
//
//	/**
//	 * 保存
//	 */
//	@ResponseBody
//	@RequestMapping("/save")
//	public R save(@RequestBody TClassExt tClass){
//		String msg=tClassService.checkClassInfo(tClass);
//		if(StringUtils.isNotEmpty(msg))
//		{
//			return R.error(msg);
//		}
//
//		this.parseDate(tClass);
//		tClass.setAddBaseInfo(ShiroUtils.getUserId());
//		Long classId = tClassService.save(tClass);
//		R r = R.ok();
//		if (classId != null)
//		{
//			r.put("classId", classId);
//		}
//		return r;
//	}
//
//
//	/**
//	 * 修改
//	 */
//	@ResponseBody
//	@RequestMapping("/update")
//	public R update(@RequestBody TClassExt tClass){
//		String msg=tClassService.checkClassInfo(tClass);
//		if(StringUtils.isNotEmpty(msg))
//		{
//			return R.error(msg);
//		}
//		this.parseDate(tClass);
//		tClass.setModifyBaseInfo(ShiroUtils.getUserId());
//		tClassService.update(tClass);
//		tClassService.updateShcedule(tClass);
//		return R.ok();
//	}
//
//
//	/**
//	 * 处理日期
//	 * @param tClass
//	 */
//	private void parseDate(TClassExt tClass)
//	{
//		try {
//			if (StringUtils.isNotEmpty(tClass.getBeginDateStr()))
//			{
//				tClass.setBeginDate(DateUtils.parseDate(tClass.getBeginDateStr(), new String[]{"yyyy-MM-dd HH:mm"}));
//			}
//			if (StringUtils.isNotEmpty(tClass.getEndDateStr()))
//			{
//				tClass.setEndDate(DateUtils.parseDate(tClass.getEndDateStr(), new String[]{"yyyy-MM-dd HH:mm"}));
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	}
//
//
//	/**
//	 * 删除
//	 */
//	@ResponseBody
//	@RequestMapping("/delete")
//	public R delete(@RequestBody Integer classId){
//		tClassService.delete(Long.valueOf(classId));
//		return R.ok();
//	}
//
//
//	@ResponseBody
//	@RequestMapping("/saveBatchStudent")
// 	public R saveBatchStudent(@RequestBody List<TClassStudentR> list){
//		tClassService.saveBatchStudent(list);
//
//		return R.ok();
//	}
//
//	@ResponseBody
//	@RequestMapping("/saveSchedule")
//	public CreateRoomResp saveSchedule(@RequestBody TClassScheduleExt classScheduleExt) throws ParseException {
//		/**
//		 * 判断是否该时间段已经被选择
//		 */
//		CreateRoomResp roomRese=new CreateRoomResp();
//	 	boolean flag=	tClassScheduleService.checkTimeSchedule(classScheduleExt.getLessonTimeStr(),classScheduleExt.getEndTimeStr(),classScheduleExt.getTeacherId());
//		if(!flag){
//			roomRese.setCode("203");
//			roomRese.setMessage("当前老师已经被排课！！！");
//			return  roomRese;
//		}
//		classScheduleExt.setAddBaseInfo(ShiroUtils.getUserId());
//		tClassScheduleService.saveSchedule(classScheduleExt);
//		TClassSchedule s=tClassScheduleService.queryObject(classScheduleExt.getScheduleId());
//		if(null != s)
//		{
//			CreateRoomResp orUpdateRoom = talkCloudService.createOrUpdateRoom(s.getScheduleId() == null ?null:s.getScheduleId().intValue(), 
//															          		  s.getLessonId() == null ?null:s.getLessonId().intValue(),
//															          		  s.getChapterId() == null ?null:s.getChapterId().intValue(), 
//															          		  s.getClassId() == null ?null:s.getClassId().intValue(), 
//															          		  s.getTeacherId() == null ?null:s.getTeacherId().intValue());
//			return orUpdateRoom;
//		}
//		return null;
//	}
//
//	/**
//	 *  取消课节
//	 * @param classScheduleExt
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/updateTime")
//	public CreateRoomResp delSchedule(@RequestBody TClassScheduleExt classScheduleExt){
//		CreateRoomResp createRoomResp=new CreateRoomResp();
//		//判断上课开始是否过时
//		if(!classScheduleExt.getLessonTime().after(new Date(System.currentTimeMillis())))
//		{
//			//过时不能取消
//			System.out.println("当前课程已经开始，取消失败");
//			createRoomResp.setCode("400");
//			return createRoomResp;
//		}
//		//否则继续
//		classScheduleExt.setLessonTime(null);
//		classScheduleExt.setEndTime(null);
//		classScheduleExt.setLessonTimeStr(null);
//		classScheduleExt.setEndTimeStr(null);
//		Integer integer = tClassScheduleService.updateTime(classScheduleExt);
//		if(integer==null)
//		{
//			createRoomResp.setCode("500");
//			return createRoomResp;
//		}
//		TClassSchedule s=tClassScheduleService.queryObject(classScheduleExt.getScheduleId());
//		if(null != s)
//		{
//			CreateRoomResp orUpdateRoom = talkCloudService.createOrUpdateRoom(s.getScheduleId() == null ?null:s.getScheduleId().intValue(), 
//														            		  s.getLessonId() == null ?null:s.getLessonId().intValue(),
//														            		  s.getChapterId() == null ?null:s.getChapterId().intValue(), 
//														            		  s.getClassId() == null ?null:s.getClassId().intValue(), 
//														            		  s.getTeacherId() == null ?null:s.getTeacherId().intValue());
//			return orUpdateRoom;
//		}
//		createRoomResp.setCode("500");
//		return createRoomResp;
//	}
//
//
//	/**
//	 * 列表
//	 */
//	@ResponseBody
//	@RequestMapping("/getStuScheduleList")
//	public R getStuScheduleList(@RequestParam Map<String, Object> params){
//        if(!sysUserRoleService.isAdmin(ShiroUtils.getUserId()))
//        	params.put("addUserId", ShiroUtils.getUserId());
//		List<TStudentLessonScheduleExt> stuScheduleList = tStudentLessonScheduleService.getListExt(params);
//		return R.ok().put("stuScheduleList", stuScheduleList);
//	}
//
//
//	/**
//	 *
//	 * 课节关联加入学生
//	 * @param list
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/saveBatchStuSchedule")
//	public R saveBatchStuSchedule(@RequestBody List<TStudentLessonSchedule> list){
//		if(null!=list && list.size()>0){
//			for(TStudentLessonSchedule info:list){
//				info.setAddBaseInfo(ShiroUtils.getUserId());
//			}
//			//判断是否是正常加入的学生，
//			if(list.get(0).getType()==1){
//				//需要批量保存
//				tStudentLessonScheduleService.saveBatchNomalStuSchedule(list);
//			}else{
//				//否则不进行批量保存
//				tStudentLessonScheduleService.saveBatchStuSchedule(list);
//			}
//		}
//		return R.ok();
//	}
//
//
//	/**
//	 * 删除插班生信息
//	 * @param recordId
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/delStuSchedule")
//	public R delStuSchedule( Integer recordId,Integer type,Long classId,Long studentId){
//
//		if(type!=1){
//			//不进行批量删除删除
//			tStudentLessonScheduleService.delStuSchedule(recordId);
//		}else{
//			tStudentLessonScheduleService.delBatchStuScheduleByStuId(classId,studentId);
//		}
//		return R.ok();
//	}
//
//
//	/**
//	 *
//	 * 当发布课程后直接获取到助教的url，同时根据t_class表中查询assistant_id
//	 */
//	@ResponseBody
//	@RequestMapping("/getAssistTeacherUrl")
//	public String getAssistTeacherUrl(String roomId){
//		if( roomId!=null){
//			String assitUrl= talkCloudService.getAssistTeacherUrl(roomId);
//			return  assitUrl;
//		}
//		return "0";
//	}
//
//	/**
//	 *
//	 * 获取当前课节录像
//	 */
//
//	@RequestMapping("/getScheduleVideoReplay")
//	@ResponseBody
//	public R getScheduleVideoReplay(Long scheduleId){
//		//1.根据scheduleId从t_class_schedule表中获取用户的roomId;
//		TClassSchedule tClassSchedule = tClassScheduleService.queryObject(scheduleId);
//		if(null==tClassSchedule){
//
//
//			return R.error(10000,"用户未登陆");
//		}
//		//2.使用http://IP  地址: : 端口号/ / WebAPI/getrecordlis     参数:key,serial(房间号) 获取录像地址
//		RecodListResp recodListResp=talkCloudService.getRecordList(tClassSchedule.getRoomId());
//		if(!recodListResp.getResult().equals("0"))
//		{
//			if(recodListResp.getResult().equals("-1")){
//				return R.error(-1,"当前课程没有课堂录像");
//			}
//			//如果不为0说明出错
//			return R.error(4007,"当前房间号不存在");
//		}
//		List<Record> recordlist = recodListResp.getRecordlist();
//		for (int i=0;i<recordlist.size();i++){
//			String duration = recordlist.get(i).getDuration();
//			recordlist.get(i).setDuration( ChangeTools.formatSeconds(Integer.parseInt(duration)));
//			long video_size = Long.parseLong(recordlist.get(i).getSize());
//			recordlist.get(i).setSize(ChangeTools.getPrintSize(video_size));
//		}
//		return  R.ok().put("recordlist",recordlist);
//	}
//
//
//	/**
//	 * 列表搜索重写
//	 */
//	@ResponseBody
//	@RequestMapping("/list")
//	public R QueryList(Integer limit,Integer page, Integer lessonId,String className,Integer classType,Integer consumeType,String teacherName,String beginDate,String endDate) throws ParseException {
//
//		TClass tClass=new TClass();
//		/**
//		 * 设置分页
//		 */
//
//		tClass.setStar((page-1)*limit);
//		tClass.setPage(page);
//		tClass.setLimit(limit);
//		/**
//		 * 班级名称
//		 */
//		if(StringUtil.isNotEmpty(className)){
//			tClass.setClassName("%"+className+"%");
//		}
//
//		/**
//		 *
//		 * 老师姓名
//		 */
//		if(StringUtil.isNotEmpty(teacherName)){
//				tClass.setTeacherName("%"+teacherName+"%");
//		}
//
//		/**
//		 * 课本类型
//		 */
//
//		if( null !=lessonId && lessonId!=0){
//			tClass.setLessonId(Long.valueOf(lessonId));
//		}
//		/**
//		 * 班级类型
//		 */
//		if( null!=classType && classType!=0){
//			tClass.setClassType(classType);
//		}
//		/**
//		 *
//		 * 设置消费类型
//		 */
//		if( null!=consumeType && consumeType !=0){
//			tClass.setClassItemId(consumeType);
//		}
//
//		/**
//		 * 开始日期
//		 */
//		if(StringUtil.isNotEmpty(beginDate)){
//			Date begin = DateUtils.parseDate(beginDate, new String[]{"yyyy-MM-dd"});
//			tClass.setBeginDate(begin);
//		}
//		/**
//		 *
//		 * 结束日期
//		 */
//		if(StringUtil.isNotEmpty(endDate)){
//			Date end = DateUtils.parseDate(endDate, new String[]{"yyyy-MM-dd"});
//			tClass.setEndDate(end);
//		}
//        if(!sysUserRoleService.isAdmin(ShiroUtils.getUserId()))
//        	tClass.setAddUserId(ShiroUtils.getUserId());
//		List<TClassExt> tClassList = tClassService.queryList(tClass);
//		Integer total =tClassService.queryTotal(tClass);
//		PageUtils pageUtil = new PageUtils(tClassList, total, limit, page);
//		return R.ok().put("page", pageUtil);
//	}
//}
