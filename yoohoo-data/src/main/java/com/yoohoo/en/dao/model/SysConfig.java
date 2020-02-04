package com.yoohoo.en.dao.model;

import java.io.Serializable;

public class SysConfig implements Serializable {
    private Long id;

    private String key;

    private String value;

    private Byte status;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    private SysConfig(Builder b) {
        id = b.id;
        key = b.key;
        value = b.value;
        status = b.status;
        remark = b.remark;
    }

    public SysConfig() {
        super();
    }

    public static class Builder {
        private Long id;

        private String key;

        private String value;

        private Byte status;

        private String remark;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Builder status(Byte status) {
            this.status = status;
            return this;
        }

        public Builder remark(String remark) {
            this.remark = remark;
            return this;
        }

        public SysConfig build() {
            return new SysConfig(this);
        }
    }
}