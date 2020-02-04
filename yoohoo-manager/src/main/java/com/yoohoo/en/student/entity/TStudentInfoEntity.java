package com.yoohoo.en.student.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import com.yoohoo.en.dao.model.common.BaseInfoModel;



/**
 * 学生信息表
 * 
 * @author dev-group
 * @email admin@yuanzonginfo.com
 * @date 2018-02-01 21:25:01
 */
public class TStudentInfoEntity extends BaseInfoModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//用户ID
	private Long userId;
	//合伙人姓名
	private String subAdminName;
	//姓名
	private String name;
	//英文名字
	private String enName;
	//账户余额
	private Integer balance;
	private Integer earbBalance;
	private Integer presentBalance;
	//生日
	private String birthday;
	//手机号码
	private String msisdn;
	//密码
	private String passwd;
	//状态：0 删除  1 正常
	private Integer status;
	//0 注册试用  1学习缴费
	private String regStatus;
	//注册时间
	private Date regTime;
	//管理平台增加用户时的ID
	private Integer fromAdmin;
	//
	private Date lastUpdateTime;
	//
	private Integer lastUpdateAdmin;

	@Column
    private Long oldAddUserId;//原合伙人id
	//省份
	private String province;
	//城市
	private String city;
	//区域
	private String area;
	//微信
	private String wechat;

	/**
	 * 生源
	 */
    private Integer type;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 头像
     */
    private String userImage;
    
    /**
     * 性别（1：女，2：男）
     */
    private Integer sex;
    
    /**
     * 单位
     */
    private String unit;
    
    /**
     * 邮寄地址
     */
    private String address;

	/**
	 * 设置：用户ID
	 */
	public void setUserId(Long userId) {
		
		this.userId = userId;
	}
	/**
	 * 获取：用户ID
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：英文名字
	 */
	public void setEnName(String enName) {
		this.enName = enName;
	}
	/**
	 * 获取：英文名字
	 */
	public String getEnName() {
		return enName;
	}
	/**
	 * 设置：账户余额
	 */
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	/**
	 * 获取：账户余额
	 */
	public Integer getBalance() {
		return balance;
	}
	/**
	 * 设置：生日
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：生日
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * 设置：手机号码
	 */
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	/**
	 * 获取：手机号码
	 */
	public String getMsisdn() {
		return msisdn;
	}
	/**
	 * 设置：密码
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	/**
	 * 获取：密码
	 */
	public String getPasswd() {
		return passwd;
	}
	/**
	 * 设置：状态：0 删除  1 正常
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态：0 删除  1 正常
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：0 注册试用  1学习缴费
	 */
	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}
	/**
	 * 获取：0 注册试用  1学习缴费
	 */
	public String getRegStatus() {
		return regStatus;
	}
	/**
	 * 设置：注册时间
	 */
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	/**
	 * 获取：注册时间
	 */
	public Date getRegTime() {
		return regTime;
	}
	/**
	 * 设置：管理平台增加用户时的ID
	 */
	public void setFromAdmin(Integer fromAdmin) {
		this.fromAdmin = fromAdmin;
	}
	/**
	 * 获取：管理平台增加用户时的ID
	 */
	public Integer getFromAdmin() {
		return fromAdmin;
	}
	/**
	 * 设置：
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	/**
	 * 获取：
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	/**
	 * 设置：
	 */
	public void setLastUpdateAdmin(Integer lastUpdateAdmin) {
		this.lastUpdateAdmin = lastUpdateAdmin;
	}
	/**
	 * 获取：
	 */
	public Integer getLastUpdateAdmin() {
		return lastUpdateAdmin;
	}
	public Integer getEarbBalance() {
		return earbBalance;
	}
	public void setEarbBalance(Integer earbBalance) {
		this.earbBalance = earbBalance;
	}
	public Integer getPresentBalance() {
		return presentBalance;
	}
	public void setPresentBalance(Integer presentBalance) {
		this.presentBalance = presentBalance;
	}
	public String getSubAdminName() {
		return subAdminName;
	}
	public void setSubAdminName(String subAdminName) {
		this.subAdminName = subAdminName;
	}
	public Long getOldAddUserId() {
		return oldAddUserId;
	}
	public void setOldAddUserId(Long oldAddUserId) {
		this.oldAddUserId = oldAddUserId;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
}
