package com.yoohoo.en.service;

import java.util.Date;
import java.util.List;

import com.yoohoo.en.dao.model.TTeacherScheduleNv;


public interface TeacherScheduleNvService
{
	void save(List<TTeacherScheduleNv> modelList);
	List<TTeacherScheduleNv> getListByTeacherId(Long teacherId);
    List<TTeacherScheduleNv> getListByTeacherIdList(List<Object> teacherIdList);
	List<TTeacherScheduleNv> getListByDateRange(Long teacherId, Date beginDate,Date endDate);
	List<TTeacherScheduleNv> getListByDate(Long teacherId, Integer date);
	void update(List<TTeacherScheduleNv> list);
	List<Long> queryTeacherIdsByTime(Date beginTime, Date endTime);
}
