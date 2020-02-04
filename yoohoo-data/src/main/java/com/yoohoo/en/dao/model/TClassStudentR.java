package com.yoohoo.en.dao.model;

import java.io.Serializable;
import java.util.Date;

public class TClassStudentR implements Serializable {
    private Long classId;

    private Long studentId;

    private Date addTime;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    private TClassStudentR(Builder b) {
        classId = b.classId;
        studentId = b.studentId;
        addTime = b.addTime;
        status = b.status;
    }

    public TClassStudentR() {
        super();
    }

    public static class Builder {
        private Long classId;

        private Long studentId;

        private Date addTime;

        private Integer status;

        public Builder classId(Long classId) {
            this.classId = classId;
            return this;
        }

        public Builder studentId(Long studentId) {
            this.studentId = studentId;
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

        public TClassStudentR build() {
            return new TClassStudentR(this);
        }
    }
}