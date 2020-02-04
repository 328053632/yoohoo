package com.yoohoo.en.bean.message;

import java.io.Serializable;

public class ApplyLessonMessage extends BaseMessage implements Serializable {

	private static final long serialVersionUID = -4372241751296328365L;

	private String title;
	private Integer chapterId;
	private String chapterName;
	private String lessonDate;
	private String lessonTime;
	private String recommendPhone;
	private String lessonName;
	private int lessonId;
	private String className;
	private String teacherName;
	private String suggest;

	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getChapterId() {
		return chapterId;
	}

	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getLessonDate() {
		return lessonDate;
	}

	public void setLessonDate(String lessonDate) {
		this.lessonDate = lessonDate;
	}

	public String getLessonTime() {
		return lessonTime;
	}

	public void setLessonTime(String lessonTime) {
		this.lessonTime = lessonTime;
	}

	public String getRecommendPhone() {
		return recommendPhone;
	}

	public void setRecommendPhone(String recommendPhone) {
		this.recommendPhone = recommendPhone;
	}

	public String getLessonName()
	{
		return lessonName;
	}

	public void setLessonName(String lessonName)
	{
		this.lessonName = lessonName;
	}

	public int getLessonId()
	{
		return lessonId;
	}

	public void setLessonId(int lessonId)
	{
		this.lessonId = lessonId;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}
}
