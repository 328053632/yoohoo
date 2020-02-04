package com.yoohoo.en.dao.model;

import java.io.Serializable;
import java.util.Date;

public class TVerifycode implements Serializable {
    private String msisdn;

    private Integer serviceType;

    private String verifyCode;

    private Date createTime;

    private Date overTime;

    private static final long serialVersionUID = 1L;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn == null ? null : msisdn.trim();
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode == null ? null : verifyCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    private TVerifycode(Builder b) {
        msisdn = b.msisdn;
        serviceType = b.serviceType;
        verifyCode = b.verifyCode;
        createTime = b.createTime;
        overTime = b.overTime;
    }

    public TVerifycode() {
        super();
    }

    public static class Builder {
        private String msisdn;

        private Integer serviceType;

        private String verifyCode;

        private Date createTime;

        private Date overTime;

        public Builder msisdn(String msisdn) {
            this.msisdn = msisdn;
            return this;
        }

        public Builder serviceType(Integer serviceType) {
            this.serviceType = serviceType;
            return this;
        }

        public Builder verifyCode(String verifyCode) {
            this.verifyCode = verifyCode;
            return this;
        }

        public Builder createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public Builder overTime(Date overTime) {
            this.overTime = overTime;
            return this;
        }

        public TVerifycode build() {
            return new TVerifycode(this);
        }
    }
}