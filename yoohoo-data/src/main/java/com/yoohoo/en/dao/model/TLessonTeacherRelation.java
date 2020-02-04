package com.yoohoo.en.dao.model;

import com.yoohoo.en.dao.model.common.BaseInfoModel;

public class TLessonTeacherRelation extends BaseInfoModel {
    private Integer id;

    private Integer lessonId;

    private Integer positionType;

    private Integer teacherId;

    private String teacherName;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getPositionType() {
        return positionType;
    }

    public void setPositionType(Integer positionType) {
        this.positionType = positionType;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TLessonTeacherRelation{" +
                "id=" + id +
                ", lessonId=" + lessonId +
                ", positionType=" + positionType +
                ", teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", status=" + status +
                '}';
    }
}