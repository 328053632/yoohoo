package com.yoohoo.en.service;

import com.yoohoo.en.bean.response.BaseResp;
import com.yoohoo.en.dao.model.TTeacherInfo;
import com.yoohoo.en.dao.model.ext.TeacherScheduleTimesOfDay;

import java.util.List;

public interface ITeacherInfoService
{
    TTeacherInfo query(String account);

    TTeacherInfo query(int userId);
    
    BaseResp queryLessonPeriodByDate(String date,Integer teacherId,Integer teacherType);

    /**
     * 查询老师每天课程数
     * @param teacherId
     * @param startTime
     * @param endTime
     * @return
     */
    List<TeacherScheduleTimesOfDay> queryScheduleTimesOfDay(String TeacherType,int teacherId, String startTime, String endTime);

    TTeacherInfo selectById(Integer teacherId);

	TTeacherInfo insertOrUpdate(TTeacherInfo teacherInfo);

	boolean checkUserIsExists(String phone);

	boolean sendVerificationCode(String phone, Integer type);

	List<TTeacherInfo> query(List<Long> teacherIds);
}
