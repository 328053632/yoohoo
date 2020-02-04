package com.yoohoo.en.bean.request;

import java.io.Serializable;

public class RechargeRequest implements Serializable {

	private static final long serialVersionUID = 4510516085325159547L;

	private Integer amount;
	private String remark;
	private Long userId;
	private Integer reChargeType;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getReChargeType() {
		return reChargeType;
	}

	public void setReChargeType(Integer reChargeType) {
		this.reChargeType = reChargeType;
	}
}
