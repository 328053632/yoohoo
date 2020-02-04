package com.yoohoo.en.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoohoo.en.bean.response.BaseResp;
import com.yoohoo.en.constant.ApplicationConstant;
import com.yoohoo.en.dao.mapper.LessonDao;
import com.yoohoo.en.dao.mapper.ScheduleDAO;
import com.yoohoo.en.dao.mapper.TClassScheduleMapper;
import com.yoohoo.en.dao.mapper.TTeacherInfoMapper;
import com.yoohoo.en.dao.mapper.TVerifycodeMapper;
import com.yoohoo.en.dao.model.TTeacherInfo;
import com.yoohoo.en.dao.model.TTeacherInfoExample;
import com.yoohoo.en.dao.model.TVerifycode;
import com.yoohoo.en.dao.model.ext.TeacherLessonPeriodInfo;
import com.yoohoo.en.dao.model.ext.TeacherScheduleInfo;
import com.yoohoo.en.dao.model.ext.TeacherScheduleTimesOfDay;
import com.yoohoo.en.service.ISystemConfigService;
import com.yoohoo.en.service.ITalkCloudService;
import com.yoohoo.en.service.ITeacherInfoService;
import com.yoohoo.en.utils.DateUtil;
import com.yoohoo.en.utils.SendSmsUtil;

@Service
public class TeacherInfoServiceImpl implements ITeacherInfoService {
	@Autowired
	private TTeacherInfoMapper tTeacherInfoMapper;
	@Autowired
	private LessonDao lessonDao;
	@Autowired 
	private ITalkCloudService talkCloudService;
	@Autowired
	private ScheduleDAO scheduleDAO;
	@Autowired
	private TClassScheduleMapper tClassScheduleMapper;
    @Autowired
    private TVerifycodeMapper verifycodeMapper;
    @Autowired
    private ISystemConfigService configService;
    
	@Override
	public TTeacherInfo query(String account)
	{
		TTeacherInfoExample example = new TTeacherInfoExample();
		example.createCriteria().andAccountEqualTo(account).andStatusEqualTo(ApplicationConstant.USER_STATUS_NORMAL);
		List<TTeacherInfo> tTeacherInfos = tTeacherInfoMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(tTeacherInfos)){
			example = new TTeacherInfoExample();
			example.createCriteria().andEmailEqualTo(account).andStatusEqualTo(ApplicationConstant.USER_STATUS_NORMAL);
			tTeacherInfos = tTeacherInfoMapper.selectByExample(example);
		}
		if (CollectionUtils.isEmpty(tTeacherInfos))
		{
			return null;
		}
		return tTeacherInfos.get(0);
	}

	@Override
	public TTeacherInfo query(int userId) {
		TTeacherInfoExample example = new TTeacherInfoExample();
		example.createCriteria().andTeacherIdEqualTo(userId);
		List<TTeacherInfo> tTeacherInfos = tTeacherInfoMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(tTeacherInfos)) {
			return null;
		}
		return tTeacherInfos.get(0);
	}
    
	@Override
	public List<TTeacherInfo> query(List<Long> teacherIds)
	{
    	TTeacherInfoExample tTeacherInfoExample = new TTeacherInfoExample();
    	TTeacherInfoExample.Criteria criteria = tTeacherInfoExample.createCriteria();
    	criteria.andTeacherIdIn(teacherIds);
		return tTeacherInfoMapper.selectByExample(tTeacherInfoExample);
	}

	@Override
	public BaseResp queryLessonPeriodByDate(String date, Integer teacherId,Integer teacherType) {

		BaseResp resp = new BaseResp();
		List<TeacherLessonPeriodInfo> todayLessons;
		if(teacherType==1){
			//老师
			todayLessons=lessonDao.queryTeacherLessonSchedule(teacherId, date);
		}else  if(teacherType==2){
			//课长
			todayLessons = lessonDao.querymasterTeacherLessonSchedule(teacherId, date);
		}else{
			//电教
			todayLessons = lessonDao.queryeTeacherLessonSchedule(teacherId, date);
		}

		if (CollectionUtils.isNotEmpty(todayLessons)) {
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
			for(TeacherLessonPeriodInfo p:todayLessons)
			{
			//  0等待上课   1 开始上课  当前时间在开始时间前5分钟到结束时间5分钟以内的 且未结束的，
				//  2 已上课（可点击链接） 开始上课 当前时间在开始时间前5分钟到结束时间5分钟以内的 且已结束的
				//  3 已上课
				if(null != p.getEndTime() && p.getEndTime().before(new Date(System.currentTimeMillis()-ApplicationConstant.LESSON_OPEN_AFTER_TEACHER)))
				{
					p.setTeachStatus(2);
				}else if(null != p.getLessonTime() && p.getLessonTime().before(new Date(System.currentTimeMillis()+ApplicationConstant.LESSON_OPEN_BEFORE_TEACHER))){
					if(Integer.compare(p.getStatus(), 2) ==0)
					{
						p.setTeachStatus(2);
					}else{
						p.setTeachStatus(1);
					}
					if(teacherType==1){
						p.setRoomTeacherUrl(talkCloudService.teacherEntryRoom(p.getRoomId(), p.getRoomTeacherPasswd(), p.getTeacherName(), p.getTeacherId()));
					}else if(teacherType==2){
						p.setRoomTeacherUrl(talkCloudService.parentEntryRoom(p.getRoomId(),p.getRoomTeacherPasswd()+"0",p.getTeacherName()+"助教",p.getTeacherId()+1));
					}else{
						p.setRoomTeacherUrl(talkCloudService.parentEntryRoom(p.getRoomId(),p.getRoomTeacherPasswd()+"0",p.getTeacherName()+"巡课",p.getTeacherId()+2));
					}
				}else{
					p.setTeachStatus(0);
				}
				if(null != p.getLessonTime())
				{
					p.setLessonPeriod(timeFormat.format( p.getLessonTime()));
				}else{
					p.setLessonPeriod("未知");
				}
				if(null != p.getEndTime())
				{
					p.setLessonPeriod(p.getLessonPeriod()+"-"+timeFormat.format( p.getEndTime()));
				}else{
					p.setLessonPeriod(p.getLessonPeriod()+"-未知");
				}
			}
			resp.getData().put("lessons", todayLessons);
			resp.getData().put("TeacherType",teacherType);
		}
		return resp;
	}

	@Override
	public List<TeacherScheduleTimesOfDay> queryScheduleTimesOfDay(String TeacherType,int teacherId, String startTime, String endTime)
	{
		List<TeacherScheduleInfo> scheduleList = scheduleDAO.queryScheduleList(TeacherType,DateUtil.parse(startTime, "yyyy-MM-dd"),
			DateUtil.parse(endTime, "yyyy-MM-dd"),
			teacherId);
		return TeacherScheduleTimesOfDay.buildList(scheduleList);
	}

	@Override
	public TTeacherInfo selectById(Integer teacherId) {


		return tTeacherInfoMapper.selectByPrimaryKey(teacherId);
	}

	@Override
	public TTeacherInfo insertOrUpdate(TTeacherInfo teacherInfo) {
		tTeacherInfoMapper.insertSelective(teacherInfo);
		return teacherInfo;
	}

	@Override
	public boolean checkUserIsExists(String msisdn) {
		TTeacherInfoExample example=new TTeacherInfoExample();
		example.createCriteria().andStatusEqualTo(ApplicationConstant.UserStatus.NORMAL.getValue())
                .andRegStatusEqualTo("1").andMsisdnEqualTo(msisdn);
		return tTeacherInfoMapper.countByExample(example) > 0;
	}

    @Override
    public boolean sendVerificationCode(String phone, Integer serviceType)
    {
        String verifyCodeStr = ((int)((Math.random() * 9 + 1) * 100000)) + "";

        if (SendSmsUtil.sendVerifyCodeSms(phone, verifyCodeStr, configService.getSmsConfig()))
        {
            Date time = new Date();
            TVerifycode verifycode = new TVerifycode();
            verifycode.setCreateTime(time);
            verifycode.setMsisdn(phone);
            verifycode.setVerifyCode(verifyCodeStr);
            verifycode.setServiceType(serviceType);
            verifycode.setOverTime(DateUtils.addMinutes(time, 10));
            verifycodeMapper.insert(verifycode);
            return true;
        }
        return false;
    }
}
