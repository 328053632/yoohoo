package com.yoohoo.en.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * 学生信息基类
 * t_student_info
 */
public class TStudentInfo implements Serializable {
	@Id
    private Long userId;
	@Column
    private String name;
	@Column
    private String enName;
	@Column
    private Integer balance;
	@Column
    private Integer earbBalance;
	@Column
    private Integer presentBalance;
	@Column
    private String birthday;
	@Column
    private String msisdn;
	@Column
    private String passwd;
	@Column
    private Integer status;
	@Column
    private String regStatus;
	@Column
    private Date regTime;
	@Column
    private Integer fromAdmin;
	@Column
    private Date lastUpdateTime;
	@Column
    private Integer lastUpdateAdmin;
	@Column
    private Integer type;
    
	//管理平台增加用户时的ID

    @Transient
    private Long oldAddUserId;//原合伙人id
	//省份
	private String province;
	//城市
	private String city;
	//区域
	private String area;
	//微信
    @Column
	private String wechat;
    
    /**
     * 邮箱
     */
    @Column
    private String email;
    
    /**
     * 头像
     */
    @Column
    private String userImage;
    
    /**
     * 性别（1：女，2：男）
     */
    @Column
    private Integer sex;
    
    /**
     * 单位
     */
    @Column
    private String unit;
    
    /**
     * 邮寄地址
     */
    @Column
    private String address;

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn == null ? null : msisdn.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus == null ? null : regStatus.trim();
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Integer getFromAdmin() {
        return fromAdmin;
    }

    public void setFromAdmin(Integer fromAdmin) {
        this.fromAdmin = fromAdmin;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getLastUpdateAdmin() {
        return lastUpdateAdmin;
    }

    public void setLastUpdateAdmin(Integer lastUpdateAdmin) {
        this.lastUpdateAdmin = lastUpdateAdmin;
    }

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	private TStudentInfo(Builder b) {
        userId = b.userId;
        name = b.name;
        enName = b.enName;
        balance = b.balance;
        earbBalance = b.earbBalance;
        presentBalance = b.presentBalance;
        birthday = b.birthday;
        msisdn = b.msisdn;
        passwd = b.passwd;
        status = b.status;
        regStatus = b.regStatus;
        regTime = b.regTime;
        fromAdmin = b.fromAdmin;
        lastUpdateTime = b.lastUpdateTime;
        lastUpdateAdmin = b.lastUpdateAdmin;
    }

    public TStudentInfo() {
        super();
    }

    public static class Builder {
        private Long userId;

        private String name;

        private String enName;

        private Integer balance;

        private Integer earbBalance;

        private Integer presentBalance;

        private String birthday;

        private String msisdn;

        private String passwd;

        private Integer status;

        private String regStatus;

        private Date regTime;

        private Integer fromAdmin;

        private Date lastUpdateTime;

        private Integer lastUpdateAdmin;

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder enName(String enName) {
            this.enName = enName;
            return this;
        }

        public Builder balance(Integer balance) {
            this.balance = balance;
            return this;
        }

        public Builder earbBalance(Integer earbBalance) {
            this.earbBalance = earbBalance;
            return this;
        }

        public Builder presentBalance(Integer presentBalance) {
            this.presentBalance = presentBalance;
            return this;
        }

        public Builder birthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder msisdn(String msisdn) {
            this.msisdn = msisdn;
            return this;
        }

        public Builder passwd(String passwd) {
            this.passwd = passwd;
            return this;
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder regStatus(String regStatus) {
            this.regStatus = regStatus;
            return this;
        }

        public Builder regTime(Date regTime) {
            this.regTime = regTime;
            return this;
        }

        public Builder fromAdmin(Integer fromAdmin) {
            this.fromAdmin = fromAdmin;
            return this;
        }

        public Builder lastUpdateTime(Date lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
            return this;
        }

        public Builder lastUpdateAdmin(Integer lastUpdateAdmin) {
            this.lastUpdateAdmin = lastUpdateAdmin;
            return this;
        }

        public TStudentInfo build() {
            return new TStudentInfo(this);
        }
    }
}