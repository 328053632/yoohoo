package com.yoohoo.en.dao.model;

import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable {
    private Long userId;

    private String cname;

    private String ename;

    private String username;

    private String password;

    private String email;

    private String mobile;

    private Byte status;

    private Date createTime;

    private String remarks;

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    private SysUser(Builder b) {
        userId = b.userId;
        cname = b.cname;
        ename = b.ename;
        username = b.username;
        password = b.password;
        email = b.email;
        mobile = b.mobile;
        status = b.status;
        createTime = b.createTime;
        remarks = b.remarks;
    }

    public SysUser() {
        super();
    }

    public static class Builder {
        private Long userId;

        private String cname;

        private String ename;

        private String username;

        private String password;

        private String email;

        private String mobile;

        private Byte status;

        private Date createTime;

        private String remarks;

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder cname(String cname) {
            this.cname = cname;
            return this;
        }

        public Builder ename(String ename) {
            this.ename = ename;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder mobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder status(Byte status) {
            this.status = status;
            return this;
        }

        public Builder createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public Builder remarks(String remarks) {
            this.remarks = remarks;
            return this;
        }

        public SysUser build() {
            return new SysUser(this);
        }
    }
}