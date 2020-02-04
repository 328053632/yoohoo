package com.yoohoo.en.classes.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 班级定义表
 * 
 * @author dev-group
 * @email admin@yuanzonginfo.com
 * @date 2018-02-01 19:29:30
 */
public class TClassDefineEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer classItemId;
	//
	private String classItemName;
	//
	private Integer studentNum;
	//
	private Date addTime;
	//课时价格
	private Integer lessonPrice;
	//缺课价格
	private Integer absenteeismPrice;
	//请假价格
	private Integer leavePrice;
	//试听价格
	private Integer tryPrice;
	//旁听价格
	private Integer attendPrice;

	/**
	 * 设置：
	 */
	public void setClassItemId(Integer classItemId) {
		this.classItemId = classItemId;
	}
	/**
	 * 获取：
	 */
	public Integer getClassItemId() {
		return classItemId;
	}
	/**
	 * 设置：
	 */
	public void setClassItemName(String classItemName) {
		this.classItemName = classItemName;
	}
	/**
	 * 获取：
	 */
	public String getClassItemName() {
		return classItemName;
	}
	/**
	 * 设置：
	 */
	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
	}
	/**
	 * 获取：
	 */
	public Integer getStudentNum() {
		return studentNum;
	}
	/**
	 * 设置：
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：
	 */
	public Date getAddTime() {
		return addTime;
	}
	/**
	 * 设置：课时价格
	 */
	public void setLessonPrice(Integer lessonPrice) {
		this.lessonPrice = lessonPrice;
	}
	/**
	 * 获取：课时价格
	 */
	public Integer getLessonPrice() {
		return lessonPrice;
	}
	/**
	 * 设置：缺课价格
	 */
	public void setAbsenteeismPrice(Integer absenteeismPrice) {
		this.absenteeismPrice = absenteeismPrice;
	}
	/**
	 * 获取：缺课价格
	 */
	public Integer getAbsenteeismPrice() {
		return absenteeismPrice;
	}
	/**
	 * 设置：请假价格
	 */
	public void setLeavePrice(Integer leavePrice) {
		this.leavePrice = leavePrice;
	}
	/**
	 * 获取：请假价格
	 */
	public Integer getLeavePrice() {
		return leavePrice;
	}
	/**
	 * 设置：试听价格
	 */
	public void setTryPrice(Integer tryPrice) {
		this.tryPrice = tryPrice;
	}
	/**
	 * 获取：试听价格
	 */
	public Integer getTryPrice() {
		return tryPrice;
	}
	/**
	 * 设置：旁听价格
	 */
	public void setAttendPrice(Integer attendPrice) {
		this.attendPrice = attendPrice;
	}
	/**
	 * 获取：旁听价格
	 */
	public Integer getAttendPrice() {
		return attendPrice;
	}
}
