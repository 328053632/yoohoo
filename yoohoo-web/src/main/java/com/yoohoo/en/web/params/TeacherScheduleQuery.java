package com.yoohoo.en.web.params;

import java.io.Serializable;
import java.util.Date;

public class TeacherScheduleQuery implements Serializable{
	private static final long serialVersionUID = -4823790190870260748L;

    private Long teacherId;//老师id

    private Integer month; // 查询月份 （202001）

    private Integer date; // 查询日期 （20200101）

    private Date beginTime;// 查询开始时间

    private Date endTime;// 查询结束时间

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

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

}