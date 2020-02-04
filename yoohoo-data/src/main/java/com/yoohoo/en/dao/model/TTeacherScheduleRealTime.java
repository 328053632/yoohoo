package com.yoohoo.en.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.yoohoo.en.dao.model.common.BaseInfoModel;

public class TTeacherScheduleRealTime extends BaseInfoModel {
	private static final long serialVersionUID = -4823790190870260748L;

	private Long id;

    private Long teacherId;//老师id

    private Integer date; // 上课日期 （20200101）

    private Date beginTime;// 开始时间

    private Date endTime;// 结束时间

	private Long teacherScheduleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getTeacherScheduleId() {
		return teacherScheduleId;
	}

	public void setTeacherScheduleId(Long teacherScheduleId) {
		this.teacherScheduleId = teacherScheduleId;
	}
	
	public static List<TTeacherScheduleRealTime> buildList(List<TTeacherScheduleNv> scheduleList){
		if(CollectionUtils.isEmpty(scheduleList)){
			return null;
		}
		List<TTeacherScheduleRealTime> list = new  ArrayList<TTeacherScheduleRealTime>();
		TTeacherScheduleRealTime e = null;
		for(TTeacherScheduleNv schedule:scheduleList){
			e = new TTeacherScheduleRealTime();
			BeanUtils.copyProperties(schedule, e);
			e.setTeacherScheduleId(schedule.getId());
			list.add(e);
		}
		return list;
	}

}