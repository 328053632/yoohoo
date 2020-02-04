package com.yoohoo.en.dao.model;

import java.io.Serializable;
import java.util.Date;

import com.yoohoo.en.dao.model.common.BaseInfoModel;

public class TStudentLessonSchedule extends BaseInfoModel implements Serializable {
    private Integer recordId;

    private Long scheduleId;

    private Long studentId;

    private Long lessonId;

    private Integer chapterId;

    private Integer type;

    private Integer status;

    private Integer logId;

    private Date lessonTime;

    private Date addtime;

    private Integer rId;

    private Date leaveTime;


    private Long classId;

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    private static final long serialVersionUID = 1L;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Date getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(Date lessonTime) {
        this.lessonTime = lessonTime;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    private TStudentLessonSchedule(Builder b) {
        recordId = b.recordId;
        scheduleId = b.scheduleId;
        studentId = b.studentId;
        lessonId = b.lessonId;
        chapterId = b.chapterId;
        type = b.type;
        status = b.status;
        logId = b.logId;
        lessonTime = b.lessonTime;
        addtime = b.addtime;
        rId = b.rId;
        leaveTime = b.leaveTime;
    }

    public TStudentLessonSchedule() {
        super();
    }

    public static class Builder {
        private Integer recordId;

        private Long scheduleId;

        private Long studentId;

        private Long lessonId;

        private Integer chapterId;

        private Integer type;

        private Integer status;

        private Integer logId;

        private Date lessonTime;

        private Date addtime;

        private Integer rId;

        private Date leaveTime;

        public Builder recordId(Integer recordId) {
            this.recordId = recordId;
            return this;
        }

        public Builder scheduleId(Long scheduleId) {
            this.scheduleId = scheduleId;
            return this;
        }

        public Builder studentId(Long studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder lessonId(Long lessonId) {
            this.lessonId = lessonId;
            return this;
        }

        public Builder chapterId(Integer chapterId) {
            this.chapterId = chapterId;
            return this;
        }

        public Builder type(Integer type) {
            this.type = type;
            return this;
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder logId(Integer logId) {
            this.logId = logId;
            return this;
        }

        public Builder lessonTime(Date lessonTime) {
            this.lessonTime = lessonTime;
            return this;
        }

        public Builder addtime(Date addtime) {
            this.addtime = addtime;
            return this;
        }

        public Builder rId(Integer rId) {
            this.rId = rId;
            return this;
        }

        public Builder leaveTime(Date leaveTime) {
            this.leaveTime = leaveTime;
            return this;
        }

        public TStudentLessonSchedule build() {
            return new TStudentLessonSchedule(this);
        }
    }
}