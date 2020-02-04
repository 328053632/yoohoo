package com.yoohoo.en.dao.model;

import java.io.Serializable;
import java.util.Date;

public class TOperationLog implements Serializable {
    private Integer operateId;

    private String type;

    private String bid;

    private String msg;

    private Date addtime;

    private static final long serialVersionUID = 1L;

    public Integer getOperateId() {
        return operateId;
    }

    public void setOperateId(Integer operateId) {
        this.operateId = operateId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    private TOperationLog(Builder b) {
        operateId = b.operateId;
        type = b.type;
        bid = b.bid;
        msg = b.msg;
        addtime = b.addtime;
    }

    public TOperationLog() {
        super();
    }

    public static class Builder {
        private Integer operateId;

        private String type;

        private String bid;

        private String msg;

        private Date addtime;

        public Builder operateId(Integer operateId) {
            this.operateId = operateId;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder bid(String bid) {
            this.bid = bid;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder addtime(Date addtime) {
            this.addtime = addtime;
            return this;
        }

        public TOperationLog build() {
            return new TOperationLog(this);
        }
    }
}