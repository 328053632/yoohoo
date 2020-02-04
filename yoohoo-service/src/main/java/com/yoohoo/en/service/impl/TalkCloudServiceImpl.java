package com.yoohoo.en.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.yoohoo.en.bean.response.RecodListResp;
import com.yoohoo.en.bean.talk.*;
import com.yoohoo.en.dao.mapper.*;
import com.yoohoo.en.dao.model.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yoohoo.en.bean.response.CreateRoomResp;
import com.yoohoo.en.constant.ApplicationConstant;
import com.yoohoo.en.service.ITalkCloudService;
import com.yoohoo.en.utils.HttpUtils;
import com.yoohoo.en.utils.StringUtil;
import com.yoohoo.en.utils.TalkMD5Util;

@Service
public class TalkCloudServiceImpl implements ITalkCloudService{
	private static final Logger logger= LogManager.getLogger(ITalkCloudService.class);
	private static int LESSON_CALLBACK_EVENT_TYPE_START=1;
	private static int LESSON_CALLBACK_EVENT_TYPE_END=2;
	@Autowired
	private TTalkcloudEventMapper tTalkcloudEventMapper;
	@Autowired
	private TTalkcloudUserEventMapper tTalkcloudUserEventMapper;
	@Autowired
	private SysConfigMapper sysConfigMapper;
	@Autowired
	private TClassScheduleMapper tClassScheduleMapper;
	@Autowired
	private TLessonChapterMapper tLessonChapterMapper;
	@Autowired
	private TTeacherInfoMapper tTeacherInfoMapper;
	@Autowired
	private TStudentInfoMapper tStudentInfoMapper;
	@Autowired
	private TLibraryChapterMapper tLibraryChapterMapper;

	@Autowired
	private  TClassMapper tClassMapper;


	@Override
	public void startLessonCallBack(String roomId) {
		if (StringUtil.isEmpty(roomId)) {
			return;
		}
		addEventLog(roomId, LESSON_CALLBACK_EVENT_TYPE_START);
	}

	private void addEventLog(String roomId, Integer eventType) {
		TClassScheduleExample lessonExample = new TClassScheduleExample();
		lessonExample.createCriteria().andRoomIdEqualTo(roomId);
		List<TClassSchedule> lessons = tClassScheduleMapper.selectByExample(lessonExample);
		TTalkcloudEvent event = new TTalkcloudEvent();
		event.setRoomId(roomId);
		if (null != lessons && !lessons.isEmpty()) {
			event.setLessonId(lessons.get(0).getLessonId());
			event.setChapterId(lessons.get(0).getChapterId());
			event.setClassId(lessons.get(0).getClassId());
			//如果是开始上课前xx分钟以后收到了开始课通知，就把此课程标记为已上过课
			if(null != lessons.get(0).getLessonTime()  && lessons.get(0).getLessonTime().after(new Date(System.currentTimeMillis()-ApplicationConstant.LESSON_OPEN_BEFORE_TEACHER)))
			{
				TClassSchedule record = new TClassSchedule();
				//课程已结束
				record.setStatus(2);
				record.setScheduleId(lessons.get(0).getScheduleId());
				tClassScheduleMapper.updateByPrimaryKeySelective(record);
			}
		}
		event.setEventType(eventType);
		event.setAddTime(new Date());
		tTalkcloudEventMapper.insert(event);
	}

	@Override
	public void finishLessonCallBack(String roomId) {
		if (StringUtil.isEmpty(roomId)) {
			return;
		}
		addEventLog(roomId, LESSON_CALLBACK_EVENT_TYPE_END);
	}

	@Override
	public void loginLogoutCallBack(String roomId, String userId, Integer roleType, Integer status) {
		if (StringUtil.isEmpty(roomId)) {
			return;
		}

		TClassScheduleExample lessonExample = new TClassScheduleExample();
		lessonExample.createCriteria().andRoomIdEqualTo(roomId);
		List<TClassSchedule> lessons = tClassScheduleMapper.selectByExample(lessonExample);
		TTalkcloudUserEvent event = new TTalkcloudUserEvent();
		event.setRoomId(roomId);
		if (null != lessons && !lessons.isEmpty()) {
			event.setLessonId(lessons.get(0).getLessonId());
			event.setChapterId(lessons.get(0).getChapterId());
			event.setClassId(lessons.get(0).getClassId());
		}
		event.setUserId(userId);
		event.setUserType(roleType);
		event.setAction(status);
		event.setAddTime(new Date());
		tTalkcloudUserEventMapper.insert(event);
	}

	@Override
	public CreateRoomResp createOrUpdateRoom(Integer scheduleId, Integer lessonId, Integer chapterId, Integer classId,
			Integer teacherId) {
		CreateRoomResp resp = new CreateRoomResp();
		resp.setResultCodeInfo(com.yoohoo.en.constant.ResponseCode.CREATE_ROOM_ERROR);
		TClassSchedule classLessonSchedule = null;
		if (null != scheduleId) {
			classLessonSchedule = tClassScheduleMapper.selectByPrimaryKey(Long.valueOf(scheduleId));
		} else {
			TClassScheduleExample example = new TClassScheduleExample();
			example.createCriteria().andLessonIdEqualTo(lessonId).andChapterIdEqualTo(chapterId)
					.andClassIdEqualTo(Long.valueOf(classId)).andTeacherIdEqualTo(teacherId);
			List<TClassSchedule> lessons = tClassScheduleMapper.selectByExample(example);
			if (null != lessons && !lessons.isEmpty()) {
				classLessonSchedule = lessons.get(0);
			}
		}
		if (null == classLessonSchedule) {
			resp.setResultCodeInfo(com.yoohoo.en.constant.ResponseCode.CREATE_ROOM_ERROR_LESSON_NOT_EXISTS);
			return resp;
		}

		TLessonChapter chapterInfo = tLessonChapterMapper.selectByPrimaryKey(classLessonSchedule.getChapterId());
		String chapterName = "";
		if (null != chapterInfo) {
			chapterName = chapterInfo.getTitle();
		} else {
			chapterName = String.valueOf(classLessonSchedule.getChapterId());
		}
		chapterName = chapterName.length() > 20 ? chapterName.substring(0, 20) : chapterName;
		Date now = new Date();
		int start = getSecond(now);
		if (null != classLessonSchedule.getLessonTime() && !classLessonSchedule.getLessonTime().before(now)) {
			start = getSecond(classLessonSchedule.getLessonTime());
		}

		int end = getSecond(now) + (50 * 60);
		classLessonSchedule.getEndTime();
		if (null != classLessonSchedule.getEndTime() && !classLessonSchedule.getEndTime().before(now)) {
			end = getSecond(classLessonSchedule.getEndTime())+(600);
		}
		String teacherPwd = RandomStringUtils.randomAlphanumeric(8);
		String studentPwd = RandomStringUtils.randomAlphanumeric(8);
		CreateRoomResult createRoomResult = null;
		TClass tClass = tClassMapper.selectByPrimaryKey(classLessonSchedule.getClassId());
		if (StringUtils.isEmpty(classLessonSchedule.getRoomId())) {
			if(tClass.getClassItemId()==1 || tClass.getClassItemId()==7 ){
				createRoomResult = this.createOrUpdateRoom(null, chapterName, start, end, teacherPwd, studentPwd,0);
			}else{
				createRoomResult = this.createOrUpdateRoom(null, chapterName, start, end, teacherPwd, studentPwd,3);
			}
		} else {
			if(tClass.getClassItemId()==1){
				createRoomResult = this.createOrUpdateRoom(classLessonSchedule.getRoomId(), chapterName, start, end,
						teacherPwd, studentPwd,0);
			}else{
				createRoomResult = this.createOrUpdateRoom(classLessonSchedule.getRoomId(), chapterName, start, end,
						teacherPwd, studentPwd,3);
			}
		}
		if (null != createRoomResult&& ApplicationConstant.TALK_API_RESULT_SUCCESS.equals(createRoomResult.getResult())) {
			TClassSchedule record = new TClassSchedule();
			record.setScheduleId(classLessonSchedule.getScheduleId());
			record.setRoomId(createRoomResult.getSerial());
			record.setRoomStuPasswd(studentPwd);
			record.setRoomTeacherPasswd(teacherPwd);
			tClassScheduleMapper.updateByPrimaryKeySelective(record);
			resp.setRoomId(createRoomResult.getSerial());
			resp.setScheduleId(classLessonSchedule.getScheduleId());
			resp.setResultCodeInfo(com.yoohoo.en.constant.ResponseCode.SUCCESS);
			return resp;
		} else {
			resp.setResultCodeInfo(com.yoohoo.en.constant.ResponseCode.CREATE_ROOM_ERROR_TALK_CREATE_ERROR);
			return resp;
		}
	}

	private int getSecond(Date date) {
		return (int) (date.getTime() / 1000);
	}
	/**
	 *
	 * 获取老师进入系统的url
	 * @param roomId
	 * @param teacherId
	 * @return
	 */
	@Override
	public String getTeacherJoinRoomUrl(String roomId, Integer teacherId) {

		TClassScheduleExample example = new TClassScheduleExample();
		example.createCriteria().andRoomIdEqualTo(roomId).andTeacherIdEqualTo(teacherId);
		List<TClassSchedule> classScheduleList = tClassScheduleMapper.selectByExample(example);
		TTeacherInfo teacherInfo = tTeacherInfoMapper.selectByPrimaryKey(teacherId);
		String teacherName = (null != teacherInfo && StringUtils.isNotEmpty(teacherInfo.getName()))
				? teacherInfo.getName() : String.valueOf(teacherId);
		if (CollectionUtils.isNotEmpty(classScheduleList)) {
			return this.teacherEntryRoom(roomId, classScheduleList.get(0).getRoomTeacherPasswd(), teacherName,
					teacherId);
		}
		return null;
	}

	/**
	 *
	 * 获取学生进入系统的url
	 * @param roomId
	 * @param studentId
	 * @return
	 */
	@Override
	public String getStudentJoinRoomUrl(String roomId, Integer studentId) {
		TClassScheduleExample example = new TClassScheduleExample();
		example.createCriteria().andRoomIdEqualTo(roomId);
		List<TClassSchedule> classScheduleList = tClassScheduleMapper.selectByExample(example);
		TStudentInfo studentInfo = tStudentInfoMapper.selectByPrimaryKey(Long.valueOf(studentId));
		String teacherName = (null != studentInfo && StringUtils.isNotEmpty(studentInfo.getName()))
				? studentInfo.getName() : String.valueOf(studentId);
		if (CollectionUtils.isNotEmpty(classScheduleList)) {
			return this.studentEntryRoom(roomId, "", teacherName, studentId);
		}
		return null;
	}

	/**
	 *
	 * 	获取助教进入系统的url
	 * @param roomId
	 * @return
	 */
	@Override
	public String getAssistTeacherUrl(String roomId) {

		TClassScheduleExample example = new TClassScheduleExample();
		example.createCriteria().andRoomIdEqualTo(roomId);
		List<TClassSchedule> tClassSchedules = tClassScheduleMapper.selectByExample(example);
			if(CollectionUtils.isNotEmpty(tClassSchedules)){
				return  this.assistEntryRoom(roomId,"D322wEEnI","助教",0);
			}
		return null;
	}

	/**
	 *
	 * 获取父母进入系统的url 巡课身份进入教室
	 * @param roomId
	 * @param stuedntId
	 * @return
	 */
	@Override
	public String getParentsJoinRoomUrl(String roomId, Integer stuedntId) {
		TClassScheduleExample example = new TClassScheduleExample();
		example.createCriteria().andRoomIdEqualTo(roomId);
		TStudentInfo studentInfo = tStudentInfoMapper.selectByPrimaryKey(Long.valueOf(stuedntId));
		List<TClassSchedule> tClassSchedules = tClassScheduleMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(tClassSchedules)){
			return  this.parentEntryRoom(roomId,tClassSchedules.get(0).getRoomTeacherPasswd()+"0",studentInfo.getName()+"的家长",Integer.valueOf(studentInfo.getUserId().toString())+2);
		}
		return null;
	}

	@Override
	public RecodListResp getRecordList(String roomId) {
		List<ParamsBean> list=new ArrayList<>();
		list.add(new ParamsBean("key",loadConfig(ApplicationConstant.TALK_CONF_KEY_AUTHKEY, "NNIuw6grpMtzPir6")));
		String url="getrecordlist/";
		list.add(new ParamsBean("serial", roomId));
		String jsonUrl=loadConfig(ApplicationConstant.TALK_CONF_KEY_API_URL, "http://global.talk-cloud.net/WebAPI/") + "getrecordlist/"
				+ HttpUtils.getValue(list).replaceAll("=", "/").replaceAll("&", "/");
		System.out.println(jsonUrl);
		RecodListResp s= (RecodListResp) HttpUtils.httpSend(
					loadConfig(ApplicationConstant.TALK_CONF_KEY_API_URL, "http://global.talk-cloud.net/WebAPI/") + url,
					list, new RequestCallback<RecodListResp>() {
						public RecodListResp callBack(String res) {
							if (StringUtils.isNotEmpty(res)) {
								RecodListResp r= JSONObject.parseObject(res, RecodListResp.class);
								if(null == r){
									logger.error(res);
								}
								return r;
							}
							return null;
						}
					});
			System.out.println(s);
		return  s;
	}

	private String loadConfig(String key, String defaultValue) {
		SysConfigExample example = new SysConfigExample();
		example.createCriteria().andKeyEqualTo(key);
		List<SysConfig> confs = sysConfigMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(confs) && StringUtils.isNotEmpty(confs.get(0).getValue())) {
			return confs.get(0).getValue();
		}
		return defaultValue;
	}
	/**
	 * 创建和修教室
	 * @param roomId
	 * @param roomName
	 * @param start
	 * @param end
	 * @param teacherPwd
	 * @param studentPwd
	 * @return
	 */
	private CreateRoomResult createOrUpdateRoom(String roomId, String roomName, int start, int end, String teacherPwd,
			String studentPwd,Integer roomType) {

		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = "roomcreate/";
		list.add(new ParamsBean("key", loadConfig(ApplicationConstant.TALK_CONF_KEY_AUTHKEY, "NNIuw6grpMtzPir6"))); // 必填// 企业id
		//list.add(new ParamsBean("key",  "NNIuw6grpMtzPir6")); // 必填// 企业id
		//authkey
		if (StringUtils.isNotEmpty(roomId)) {
			url = "roommodify/";
			list.add(new ParamsBean("serial", roomId));
		}
		list.add(new ParamsBean("roomname", TalkMD5Util.encode(roomName))); // 必填// 房间名称必填；如果有中文请使用UTF8编码，特殊字符需使用urlencode转义
		list.add(new ParamsBean("roomtype", roomType));// 0:1对1 3：1对多
		list.add(new ParamsBean("starttime", start)); // 必填 房间开始时间戳(精确到秒)
		list.add(new ParamsBean("endtime", end)); // 必填 房间结束时间(精确到秒)
		list.add(new ParamsBean("chairmanpwd", teacherPwd)); // 必填 老师密码// 必填，4=<长度<=16
		String assistantPwd = loadConfig(ApplicationConstant.TALK_CONF_KEY_ASSISTANT_PAWD, "D322wEEnI");
		list.add(new ParamsBean("assistantpwd", assistantPwd));// 必填，助教密码4=<长度<=16
		list.add(new ParamsBean("patrolpwd", teacherPwd+"0"));// 必填，巡课密码4=<长度<=16
		list.add(new ParamsBean("passwordrequired", "0")); // 选填 学生进入房间是否需要密码
		// 0:否、1:是
		list.add(new ParamsBean("confuserpwd", studentPwd)); // 学生密码
		// passwordrequired
		// = 1
		// 时必填(4=<长度<=16)或者allowsidelineuser
		// = 1 时必填
		list.add(new ParamsBean("videotype", "1")); // 选填 视频分辨率 0：176x144
		// 1：320x240 2：640x480
		// 3：1280x720 4：1920x1080
		list.add(new ParamsBean("videoframerate", 20)); // 帧率10,15,20,25,30
		list.add(new ParamsBean("autoopenav", 1));//自动开启音视频 0: 不自动开启 1：自动开启

		return (CreateRoomResult) HttpUtils.httpSend(
				loadConfig(ApplicationConstant.TALK_CONF_KEY_API_URL, "http://global.talk-cloud.net/WebAPI/") + url,
				list, new RequestCallback<CreateRoomResult>() {
					public CreateRoomResult callBack(String res) {
						if (StringUtils.isNotEmpty(res)) {
							CreateRoomResult r= JSONObject.parseObject(res, CreateRoomResult.class);
							if(null == r){
								logger.error(res);
							}
								return r;
						}
						return null;
					}
				});
		}




	public String teacherEntryRoom(String roomId, String userPwd, String name, int userId) {
		return this.entry(roomId, userPwd, name, "0", userId);
	}

	public String assistEntryRoom(String roomId, String userPwd, String name, int userId) {
		return this.entry(roomId, userPwd, name, "1", userId);
	}

	public String parentEntryRoom(String roomId, String userPwd, String name, int userId){
		return this.entry(roomId, userPwd, name, "4", userId);
	}


	private String studentEntryRoom(String roomId, String userPwd, String name, int userId) {
		return this.entry(roomId, userPwd, name, "2", userId);
	}

	private String entry(String roomId, String userPwd, String name, String usertypestr, int userId) {
		int starts = this.getSecond(new Date()); // 当前时间戳
		String keystr = loadConfig(ApplicationConstant.TALK_CONF_KEY_AUTHKEY, "NNIuw6grpMtzPir6"); // 必填
																									// 企业id
																									// authkey
		String tsstr = starts + ""; // 当前时间戳，不需要手动填
		String serialstr = roomId; // 必填 房间号 非0开始的数字串， 请保证房间号唯一
		String authstr = keystr + tsstr + serialstr + usertypestr;
		String authmd5 = TalkMD5Util.MD5(authstr);
		String pwd1 = userPwd;// 密码
		String userpassword = null;
		try {
			userpassword = TalkMD5Util.encrypts(pwd1, keystr);
			System.out.println("userpassword=" + userpassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String userName = TalkMD5Util.encode(name);// #%&特殊字符不允许输入
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		list.add(new ParamsBean("domain", loadConfig(ApplicationConstant.TALK_CONF_KEY_DOMAIN, "yyyy"))); // 公司域名
		list.add(new ParamsBean("serial", serialstr)); // 必填 房间号
		list.add(new ParamsBean("username", userName)); // 必填 用户名
														// 用户在房间中显示的名称；使用UTF8编码，特殊字符需使用urlencode转义
		list.add(new ParamsBean("usertype", usertypestr));// 必填， 0：主讲(老师 ) 1：助教
															   // 2: 学员 3：直播用户
															   // 4:巡检员 默认值为2；
		list.add(new ParamsBean("pid", userId));      // 选填, 第三方系统的用户id；默认值为0
		list.add(new ParamsBean("ts", tsstr));        // 必填，当前GMT时间戳，int格式
		list.add(new ParamsBean("auth", authmd5)); // 必填，auth值为MD5(key + ts +
													    // serial +
													    // usertype)其中key为双方协商的接口密钥：默认值为：yil97lLwpd6uELjB
		list.add(new ParamsBean("userpassword", userpassword)); // 必填 用户密码
																// 密码格式为：128位AES加密串加密密钥默认为5NIWjlgmvqwbt494注：参见附件5.1
																// AES加密
		// list.add(new ParamsBean("extradata", ""));//扩展数据 用户扩展数据，建议使用urlencode
		list.add(new ParamsBean("servername", "cnb"));// cn/as/us
		list.add(new ParamsBean("jumpurl", loadConfig(ApplicationConstant.TALK_LESSON_FINLISHED_BACKURL, "")));// 选填：课程结束课程后，自动跳转到通过参数传入的指定URL（进入时指定、不同角色不同）请在参数的最后面传递这个参数！
		return loadConfig(ApplicationConstant.TALK_CONF_KEY_API_URL, "http://global.talk-cloud.net/WebAPI/") + "entry/"
				+ HttpUtils.getValue(list).replaceAll("=", "/").replaceAll("&", "/");
		// return
		// (String)HttpUtils.httpSend(loadConfig(ApplicationConstant.TALK_CONF_KEY_API_URL,"http://global.talk-cloud.net/WebAPI/")
		// + "entry/", list, new RequestCallback<String>()
		// {)
		// public String callBack(String res)
		// {
		// return res;
		// }
		// });
	}


	/**
	 * 文件上传 关联房间号
	 * @param roomId
	 * @param map
	 */
	@Override
	public Integer uploadFileByRoomId(String roomId, Map<String,InputStream> map,Integer shceduleId) {
		//记录是否正确存入
		List<Long> recodeList=new ArrayList<>();
		Integer code = null;
		//遍历map发送文件
		//创建HttpClient对象
		CloseableHttpClient client = HttpClients.createDefault();
		String uri="uploadfile";
		String str = loadConfig(ApplicationConstant.TALK_CONF_KEY_API_URL, "http://global.talk-cloud.net/WebAPI/")+uri;
		HttpPost post=new HttpPost(str);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.setCharset(Charset.forName(HTTP.UTF_8));
		//String keystr = "NNIuw6grpMtzPir6"; // 必填
		String keystr = loadConfig(ApplicationConstant.TALK_CONF_KEY_AUTHKEY, "NNIuw6grpMtzPir6"); // 必填
		builder.addPart("key", new StringBody(keystr, ContentType.create("text/plain", Consts.UTF_8)));
		builder.addPart("serial", new StringBody(roomId, ContentType.create("text/plain", Consts.UTF_8)));
		builder.addPart("conversion", new StringBody("1", ContentType.create("text/plain", Consts.UTF_8)));
		for(String key:map.keySet()){
			try {
				builder.addBinaryBody("filedata", map.get(key), ContentType.DEFAULT_BINARY,key);
				builder.addPart("isopen", new StringBody("0", ContentType.create("text/plain", Consts.UTF_8)));
				builder.addPart("dynamicppt", new StringBody("0", ContentType.create("text/plain", Consts.UTF_8)));
				builder.addPart("isdefault", new StringBody("0", ContentType.create("text/plain", Consts.UTF_8)));
				HttpEntity reqEntity =  builder.build();
				post.setEntity(reqEntity);
				CloseableHttpResponse response = client.execute(post);
				//获取响应对象
				HttpEntity resEntity = response.getEntity();
				if(resEntity != null){
					//打印响应内容
					String data=EntityUtils.toString(resEntity,Charset.forName("UTF-8"));
					UploadFileResult uploadFileResult = JSONObject.toJavaObject((com.alibaba.fastjson.JSON) JSONObject.parse(data), UploadFileResult.class);
					if(uploadFileResult.getResult()==0){
						//存储每一个文件上传后的状态
						recodeList.add(uploadFileResult.getFileid());
					}else{
						code=uploadFileResult.getResult();
						//如果出错那么就直接回滚进行删除之前上传文件
						if(recodeList.size()>0){
							//说明有数据，删除上传成功的文件重新进行上传
							List<String> deleteFileIds=new ArrayList<>();
							for(int i=0;i<recodeList.size();i++){
								//进行数组赋
								deleteFileIds.add(recodeList.get(i)+"");
							}
							//删除已经上传的文件id
							this.deleteFileById(deleteFileIds);
							break;
						}
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//判断是否全部存入
		if (recodeList.size()==map.size()){
			//全部存入，文件上传成功
			TClassScheduleExample example=new TClassScheduleExample();
			example.createCriteria().andScheduleIdEqualTo(Long.valueOf(shceduleId));
			TClassSchedule bean=new TClassSchedule();
			bean.setIsUpload(1);
			//更新状态
			tClassScheduleMapper.updateByExampleSelective(bean,example);


			code=0;
			return code;
		}
		return code;
	}

	/**
	 * 删除课件
	 */

	public Integer deleteFileById(List<String> fileidarr){

		Integer result=null;
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		for(int i=0;i<fileidarr.size();i++){
			list.add(new ParamsBean("fileidarr[]",fileidarr.get(i)));
		}
		String  getUrl= loadConfig(ApplicationConstant.TALK_CONF_KEY_API_URL, "http://global.talk-cloud.net/WebAPI/") + "deletefile/key/NNIuw6grpMtzPir6/?"
				+ HttpUtils.getValue(list);
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpGet get=new HttpGet(getUrl);
			CloseableHttpResponse execute = client.execute(get);
			HttpEntity entity = execute.getEntity();
			String s = EntityUtils.toString(entity);
			UploadFileResult uploadFileResult = JSONObject.toJavaObject((JSON) JSONObject.parse(s), UploadFileResult.class);
			result= uploadFileResult.getResult();
		} catch (IOException e) {
			e.printStackTrace();
		}
			return result;
	}


	/**
	 *
	 * 获得房间关联文件ID
	 * @param roomId
	 * @return
	 */
	@Override
	public List<String> getRoomFile(String roomId) {
		List<String> list=new ArrayList<>();
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			String uri="getroomfile";
			String str = loadConfig(ApplicationConstant.TALK_CONF_KEY_API_URL, "http://global.talk-cloud.net/WebAPI/")+uri;
			HttpPost post=new HttpPost(str);
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setCharset(Charset.forName(HTTP.UTF_8));
			String keystr = loadConfig(ApplicationConstant.TALK_CONF_KEY_AUTHKEY, "NNIuw6grpMtzPir6"); // 必填
			builder.addPart("key", new StringBody(keystr, ContentType.create("text/plain", Consts.UTF_8)));
			builder.addPart("serial", new StringBody(roomId, ContentType.create("text/plain", Consts.UTF_8)));
			HttpEntity reqEntity =  builder.build();
			post.setEntity(reqEntity);
			CloseableHttpResponse response = client.execute(post);
			//获取响应对象
			HttpEntity resEntity = response.getEntity();
			if(resEntity != null){
				//打印响应内容
				String data=EntityUtils.toString(resEntity,Charset.forName("UTF-8"));
				RoomFileBean RoomFileResult = JSONObject.toJavaObject((JSON) JSONObject.parse(data), RoomFileBean.class);
				if(RoomFileResult.getResult()==0){

					List<Roomfile> roomfile = RoomFileResult.getRoomfile();
					for(Roomfile r:roomfile){
						list.add(r.getFileid());
					}
					return list;
				}else{
					//没有课件，或者没有该房间
					return null;
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
