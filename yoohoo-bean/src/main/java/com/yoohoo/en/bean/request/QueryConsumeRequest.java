package com.yoohoo.en.bean.request;

import java.io.Serializable;

public class QueryConsumeRequest implements Serializable {

	private static final long serialVersionUID = 4327385209038635337L;
	
	private String dateStr;
	private int start = 0;
	private int size = 10;
	private Integer lessonId;
	private Integer classId;
	private Integer classItemId;
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Integer getLessonId() {
		return lessonId;
	}
	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public Integer getClassItemId() {
		return classItemId;
	}
	public void setClassItemId(Integer classItemId) {
		this.classItemId = classItemId;
	}
}
