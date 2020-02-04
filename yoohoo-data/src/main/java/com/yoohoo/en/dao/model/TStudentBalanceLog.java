package com.yoohoo.en.dao.model;

import java.io.Serializable;
import java.util.Date;

import com.yoohoo.en.dao.model.common.BaseInfoModel;

public class TStudentBalanceLog extends BaseInfoModel implements Serializable {
    private Integer logId;

    private Long userId;

    private Long scheduleId;

    private Integer recordId;

    private Integer type;

    private Integer bType;

    private Integer amount;

    private Integer afterBalance;

    private Date addtime;

    private String mark;

    private String memo;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getbType() {
        return bType;
    }

    public void setbType(Integer bType) {
        this.bType = bType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAfterBalance() {
        return afterBalance;
    }

    public void setAfterBalance(Integer afterBalance) {
        this.afterBalance = afterBalance;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	private TStudentBalanceLog(Builder b) {
        logId = b.logId;
        userId = b.userId;
        scheduleId = b.scheduleId;
        recordId = b.recordId;
        type = b.type;
        bType = b.bType;
        amount = b.amount;
        afterBalance = b.afterBalance;
        addtime = b.addtime;
        mark = b.mark;
        memo = b.memo;
        status = b.status;
    }

    public TStudentBalanceLog() {
        super();
    }

    public static class Builder {
        private Integer logId;

        private Long userId;

        private Long scheduleId;

        private Integer recordId;

        private Integer type;

        private Integer bType;

        private Integer amount;

        private Integer afterBalance;

        private Date addtime;

        private String mark;

        private String memo;

        private Integer status;

        public Builder logId(Integer logId) {
            this.logId = logId;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder scheduleId(Long scheduleId) {
            this.scheduleId = scheduleId;
            return this;
        }

        public Builder recordId(Integer recordId) {
            this.recordId = recordId;
            return this;
        }

        public Builder type(Integer type) {
            this.type = type;
            return this;
        }

        public Builder bType(Integer bType) {
            this.bType = bType;
            return this;
        }

        public Builder amount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public Builder afterBalance(Integer afterBalance) {
            this.afterBalance = afterBalance;
            return this;
        }

        public Builder addtime(Date addtime) {
            this.addtime = addtime;
            return this;
        }

        public Builder mark(String mark) {
            this.mark = mark;
            return this;
        }

        public Builder memo(String memo) {
            this.memo = memo;
            return this;
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public TStudentBalanceLog build() {
            return new TStudentBalanceLog(this);
        }
    }
}