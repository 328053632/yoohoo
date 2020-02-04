package com.yoohoo.en.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.yoohoo.en.dao.model.common.BaseInfoModel;

public class TFeetemplate extends BaseInfoModel implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String teacherMemo;
	
	@Column
	private Integer fee;
	
	@Column
	private Integer status = 1;
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTeacherMemo() {
		return teacherMemo;
	}
	public void setTeacherMemo(String teacherMemo) {
		this.teacherMemo = teacherMemo;
	}
	public Integer getFee() {
		return fee;
	}
	public void setFee(Integer fee) {
		this.fee = fee;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}