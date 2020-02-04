package com.yoohoo.en.dao.model.ext;

import java.util.List;

public class ConsumePackInfo extends TClassScheduleExt {

	private static final long serialVersionUID = 7953049891398867850L;
	private String lessonName;
	private String className;
	private Integer classItemId;
	private String classItemName;
	private String teacherName;
	//电教姓名
	private  String eteacherName;
	//课长姓名
	private String masterteacherName;
	//班级类型
	private Integer classType;


	private Integer teachStatus;
	private List<StudentConsumeInfo> studentConsumeList;


	public Integer getClassType() {
		return classType;
	}

	public void setClassType(Integer classType) {
		this.classType = classType;
	}

	public String getEteacherName() {
		return eteacherName;
	}

	public void setEteacherName(String eteacherName) {
		this.eteacherName = eteacherName;
	}

	public String getMasterteacherName() {
		return masterteacherName;
	}

	public void setMasterteacherName(String masterteacherName) {
		this.masterteacherName = masterteacherName;
	}

	public Integer getClassItemId() {
		return classItemId;
	}
	public void setClassItemId(Integer classItemId) {
		this.classItemId = classItemId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassItemName() {
		return classItemName;
	}
	public void setClassItemName(String classItemName) {
		this.classItemName = classItemName;
	}
	public List<StudentConsumeInfo> getStudentConsumeList() {
		return studentConsumeList;
	}
	public void setStudentConsumeList(List<StudentConsumeInfo> studentConsumeList) {
		this.studentConsumeList = studentConsumeList;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Integer getTeachStatus() {
		return teachStatus;
	}
	public void setTeachStatus(Integer teachStatus) {
		this.teachStatus = teachStatus;
	}
}
