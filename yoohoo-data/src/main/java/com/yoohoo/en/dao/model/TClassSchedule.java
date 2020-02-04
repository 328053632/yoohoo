package com.yoohoo.en.dao.model;

import java.util.Date;

import com.yoohoo.en.dao.model.common.BaseInfoModel;

public class TClassSchedule extends BaseInfoModel {
    private Long scheduleId;

    private Long classId;

    private Long lessonId;

    private Integer chapterId;

    private Date lessonTime;

    private Date endTime;

    private Integer teacherId;

    private Integer eteacherId;

    private Integer masterteacherId;

    private String roomId;

    private String roomTeacherPasswd;

    private String roomTeacherUrl;

    private String roomStuPasswd;

    private Integer status;

    private String dateLabel;

    private Integer checkStatus;

    private Integer isUpload;

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
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

    public Date getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(Date lessonTime) {
        this.lessonTime = lessonTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getEteacherId() {
        return eteacherId;
    }

    public void setEteacherId(Integer eteacherId) {
        this.eteacherId = eteacherId;
    }

    public Integer getMasterteacherId() {
        return masterteacherId;
    }

    public void setMasterteacherId(Integer masterteacherId) {
        this.masterteacherId = masterteacherId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId == null ? null : roomId.trim();
    }

    public String getRoomTeacherPasswd() {
        return roomTeacherPasswd;
    }

    public void setRoomTeacherPasswd(String roomTeacherPasswd) {
        this.roomTeacherPasswd = roomTeacherPasswd == null ? null : roomTeacherPasswd.trim();
    }

    public String getRoomTeacherUrl() {
        return roomTeacherUrl;
    }

    public void setRoomTeacherUrl(String roomTeacherUrl) {
        this.roomTeacherUrl = roomTeacherUrl == null ? null : roomTeacherUrl.trim();
    }

    public String getRoomStuPasswd() {
        return roomStuPasswd;
    }

    public void setRoomStuPasswd(String roomStuPasswd) {
        this.roomStuPasswd = roomStuPasswd == null ? null : roomStuPasswd.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(String dateLabel) {
        this.dateLabel = dateLabel == null ? null : dateLabel.trim();
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(Integer isUpload) {
        this.isUpload = isUpload;
    }
}