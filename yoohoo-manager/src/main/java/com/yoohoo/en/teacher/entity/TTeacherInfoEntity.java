package com.yoohoo.en.teacher.entity;

import java.io.Serializable;
import java.util.Date;

import java.util.List;
import com.yoohoo.en.dao.model.TTeacherRate;
import com.yoohoo.en.dao.model.common.BaseInfoModel;



/**
 * 
 * 
 * @author dev-group
 * @email admin@yuanzonginfo.com
 * @date 2018-02-01 21:24:37
 */
public class TTeacherInfoEntity extends BaseInfoModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//老师id自增
	private Integer teacherId;
	//合伙人
	private String subAdminName;
	//合伙人
	private String subAdminRoleName;
	//登录账号
	private String account;
	//用户密码
	private String password;
	//老师中文名
	private String name;
	//老师英文名
	private String enName;
	//手机号码
	private String phone;
	//老师工号
	private String jobNumber;
	//邮箱地址
	private String email;
	//国家名称
	private String country;
	//职位
	private String position;
	//地址
	private String address;
	//账号添加时间
	private Date createTime;
	//账号最后修改时间
	private Date updateTime;
	//账号状态, 0:已删除 1:正常
	private Integer status;
	//员工图片
	private String teacherImage;

	//员工性别
	private Integer sex;
	//员工类型
	private Integer positionType;
	//教龄
	private String teacherAge;
	//学历
	private String educational;

	//机构评价
	private String evaluation;

	//员工基本介绍
	private String introduce;

	//老师视频
	private String teacherVideo;
	/**
	 * 上课价格/5分钟
	 */
	private Integer price;
	/**
	 * 社交账户
	 * @return
	 */
	private String communicateAccount;
	
	private String headImage;
	
	private List<TTeacherRate> rateList;

	public String getTeacherVideo() {
		return teacherVideo;
	}

	public void setTeacherVideo(String teacherVideo) {
		this.teacherVideo = teacherVideo;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getPositionType() {
		return positionType;
	}

	public void setPositionType(Integer positionType) {
		this.positionType = positionType;
	}

	public String getTeacherAge() {
		return teacherAge;
	}

	public void setTeacherAge(String teacherAge) {
		this.teacherAge = teacherAge;
	}

	public String getEducational() {
		return educational;
	}

	public void setEducational(String educational) {
		this.educational = educational;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}



	/**
	 * 设置：老师id自增
	 */
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	/**
	 * 获取：老师id自增
	 */
	public Integer getTeacherId() {
		return teacherId;
	}
	/**
	 * 设置：登录账号
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 获取：登录账号
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * 设置：用户密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：用户密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：老师中文名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：老师中文名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：老师英文名
	 */
	public void setEnName(String enName) {
		this.enName = enName;
	}
	/**
	 * 获取：老师英文名
	 */
	public String getEnName() {
		return enName;
	}
	/**
	 * 设置：手机号码
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号码
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：老师工号
	 */
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	/**
	 * 获取：老师工号
	 */
	public String getJobNumber() {
		return jobNumber;
	}
	/**
	 * 设置：邮箱地址
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱地址
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：国家名称
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * 获取：国家名称
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * 设置：职位
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * 获取：职位
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：账号添加时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：账号添加时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：账号最后修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：账号最后修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：账号状态, 0:已删除 1:正常
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：账号状态, 0:已删除 1:正常
	 */
	public Integer getStatus() {
		return status;
	}

	public String getTeacherImage() {
		return teacherImage;
	}

	public void setTeacherImage(String teacherImage) {
		this.teacherImage = teacherImage;
	}

	public String getSubAdminName() {
		return subAdminName;
	}

	public void setSubAdminName(String subAdminName) {
		this.subAdminName = subAdminName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getCommunicateAccount() {
		return communicateAccount;
	}

	public void setCommunicateAccount(String communicateAccount) {
		this.communicateAccount = communicateAccount;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public List<TTeacherRate> getRateList() {
		return rateList;
	}

	public void setRateList(List<TTeacherRate> rateList) {
		this.rateList = rateList;
	}

	public String getSubAdminRoleName() {
		return subAdminRoleName;
	}

	public void setSubAdminRoleName(String subAdminRoleName) {
		this.subAdminRoleName = subAdminRoleName;
	}
	
}
