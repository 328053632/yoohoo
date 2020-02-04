package com.yoohoo.en.dao.model;

import javax.persistence.Column;


import javax.persistence.Id;

import com.yoohoo.en.dao.model.common.BaseInfoModel;

public class TTeacherRate extends BaseInfoModel {
	@Id
    private Long id;
	
	@Column
    private Long teacherId;

	@Column
    private String des;

	@Column
    private Integer value;

	@Column
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
}