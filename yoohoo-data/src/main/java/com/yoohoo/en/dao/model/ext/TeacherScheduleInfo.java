package com.yoohoo.en.dao.model.ext;

import java.io.Serializable;
import java.util.Date;

public class TeacherScheduleInfo implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String dateLabel;

    private Date beginTime;

    private Date endTime;

    private Integer status;

	public String getDateLabel() {
		return dateLabel;
	}

	public void setDateLabel(String dateLabel) {
		this.dateLabel = dateLabel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
}
