package com.yoohoo.en.dao.model.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class BaseInfoModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
    private Date addtime;

	@Column
    private Long addUserId;

	@Column
    private Date updateTime;

	@Column
    private Long updateUserId;

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getAddUserId() {
		return addUserId;
	}

	public void setAddUserId(Long addUserId) {
		this.addUserId = addUserId;
	}
    
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public void setAddBaseInfo(long userId){
		this.addtime = new Date();
		this.addUserId = userId;
		this.updateTime = addtime;
		this.updateUserId = userId;
	}
	
	public void setModifyBaseInfo(long userId){
		updateTime = new Date();
		updateUserId = userId;
	}
    
}