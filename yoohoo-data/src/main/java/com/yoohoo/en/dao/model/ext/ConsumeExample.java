package com.yoohoo.en.dao.model.ext;

import java.io.Serializable;
import java.util.Date;

import com.yoohoo.en.dao.model.common.BaseInfoModel;

public class ConsumeExample extends BaseInfoModel implements Serializable {
	private static final long serialVersionUID = 8441495317664377976L;
	private Integer type;
	private Integer teacherId;
	private Integer classId;
	private Integer classItemId;
	private Integer lessonId;
	private Integer studentId;
	private Date stime;
	private Date etime;
	private Integer lessonType;
	private Integer start=0;
	private Integer limit=10;
	private String keyWord;
	public Integer getLessonId() {
		return lessonId;
	}
	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
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
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Date getStime() {
		return stime;
	}
	public void setStime(Date stime) {
		this.stime = stime;
	}
	public Date getEtime() {
		return etime;
	}
	public void setEtime(Date etime) {
		this.etime = etime;
	}
	public Integer getLessonType() {
		return lessonType;
	}
	public void setLessonType(Integer lessonType) {
		this.lessonType = lessonType;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
}
