package com.yoohoo.en.dao.model;

import java.io.Serializable;
import java.util.Date;

public class TMessage implements Serializable {
    private Integer mId;

    private Long uId;

    private Integer uType;

    private Integer mType;

    private Date addTime;

    private Integer status;

    private String cotent;

    private static final long serialVersionUID = 1L;

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Integer getuType() {
        return uType;
    }

    public void setuType(Integer uType) {
        this.uType = uType;
    }

    public Integer getmType() {
        return mType;
    }

    public void setmType(Integer mType) {
        this.mType = mType;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCotent() {
        return cotent;
    }

    public void setCotent(String cotent) {
        this.cotent = cotent == null ? null : cotent.trim();
    }

    private TMessage(Builder b) {
        mId = b.mId;
        uId = b.uId;
        uType = b.uType;
        mType = b.mType;
        addTime = b.addTime;
        status = b.status;
        cotent = b.cotent;
    }

    public TMessage() {
        super();
    }

    public static class Builder {
        private Integer mId;

        private Long uId;

        private Integer uType;

        private Integer mType;

        private Date addTime;

        private Integer status;

        private String cotent;

        public Builder mId(Integer mId) {
            this.mId = mId;
            return this;
        }

        public Builder uId(Long uId) {
            this.uId = uId;
            return this;
        }

        public Builder uType(Integer uType) {
            this.uType = uType;
            return this;
        }

        public Builder mType(Integer mType) {
            this.mType = mType;
            return this;
        }

        public Builder addTime(Date addTime) {
            this.addTime = addTime;
            return this;
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder cotent(String cotent) {
            this.cotent = cotent;
            return this;
        }

        public TMessage build() {
            return new TMessage(this);
        }
    }
}