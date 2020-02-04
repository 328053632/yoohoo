package com.yoohoo.en.dao.model;

import javax.persistence.Column;


import javax.persistence.Id;
import javax.persistence.Transient;

import com.yoohoo.en.dao.model.common.BaseInfoModel;

public class TLessonTeacher extends BaseInfoModel {
	@Id
    private Long id;

	@Column
    private Integer lessonId;

	@Column
    private Long teacherId;

	@Column
    private Integer status;

	@Transient
    private String teacherName;

	@Transient
    private String lessonName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
}