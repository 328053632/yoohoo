package com.yoohoo.en.dao.model.ext;

import com.yoohoo.en.dao.model.TClassSchedule;

public class TeacherLessonPeriodInfo  extends TClassSchedule{

	private static final long serialVersionUID = -8938614021143789289L;
	
	private String lessonName;
	private String chapterName;
	private String className;
	private String teacherName;
	private String lessonPeriod;
	//  0等待上课   1 开始上课  当前时间在开始时间前5分钟到结束时间5分钟以内的 且未结束的，
	//  2 已上课（可点击链接） 开始上课 当前时间在开始时间前5分钟到结束时间5分钟以内的 且已结束的
	//  3 已上课
	private int   teachStatus=0;
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getTeachStatus() {
		return teachStatus;
	}
	public void setTeachStatus(int teachStatus) {
		this.teachStatus = teachStatus;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getLessonPeriod() {
		return lessonPeriod;
	}
	public void setLessonPeriod(String lessonPeriod) {
		this.lessonPeriod = lessonPeriod;
	}
}
