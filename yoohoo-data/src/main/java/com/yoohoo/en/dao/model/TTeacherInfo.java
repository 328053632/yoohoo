package com.yoohoo.en.dao.model;

import java.util.Date;

public class TTeacherInfo {
    private Integer teacherId;

    private String account;

    private String password;

    private String name;

    private String enName;

    private String phone;

    private String jobNumber;

    private String email;

    private String country;

    private String position;

    private String address;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private String teacherImage;

    private String teacherVideo;

    private Integer sex;

    private Integer positionType;

    private String teacherage;

    private String educational;

    private String introduce;

    private String evaluation;
	/**
	 * 上课价格/5分钟
	 */
	private Integer price;
	/**
	 * 社交账户
	 * @return
	 */
	private String communicateAccount;

    //当前老师的登陆的类型
    private Integer TeacherType;

    private String type;

    public Integer getTeacherType() {
        return TeacherType;
    }

    public void setTeacherType(Integer teacherType) {
        TeacherType = teacherType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber == null ? null : jobNumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTeacherImage() {
        return teacherImage;
    }

    public void setTeacherImage(String teacherImage) {
        this.teacherImage = teacherImage == null ? null : teacherImage.trim();
    }

    public String getTeacherVideo() {
        return teacherVideo;
    }

    public void setTeacherVideo(String teacherVideo) {
        this.teacherVideo = teacherVideo == null ? null : teacherVideo.trim();
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

    public String getTeacherage() {
        return teacherage;
    }

    public void setTeacherage(String teacherage) {
        this.teacherage = teacherage == null ? null : teacherage.trim();
    }

    public String getEducational() {
        return educational;
    }

    public void setEducational(String educational) {
        this.educational = educational == null ? null : educational.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation == null ? null : evaluation.trim();
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
}