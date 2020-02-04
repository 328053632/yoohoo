package com.yoohoo.en.dao.model;

import javax.persistence.Column;


import com.yoohoo.en.dao.model.common.BaseInfoModel;

public class TStudentDynamic extends BaseInfoModel {
	
	private static final long serialVersionUID = 1L;

	@Column
    private Long id;


	/**
	 * 学生id
	 */
	@Column
    private Long studentId;

	/**
	 * 需求
	 */
	@Column
    private String demand;

	/**
	 * 学生当前阶段
	 */
	@Column
    private String stage;

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

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}
}