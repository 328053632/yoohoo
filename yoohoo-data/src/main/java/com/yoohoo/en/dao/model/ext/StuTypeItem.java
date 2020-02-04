package com.yoohoo.en.dao.model.ext;

import java.io.Serializable;

public class StuTypeItem implements Serializable {

	private static final long serialVersionUID = -365028912938898954L;
	
	private Integer type;
	private String typeName;
	private Integer price=0;
	private String feeMemo;
	
	public StuTypeItem(Integer type, String typeName, Integer price) {
		super();
		this.type = type;
		this.typeName = typeName;
		this.price = price;
	}
	public StuTypeItem(Integer type) {
		super();
		this.type = type;
	}
	public StuTypeItem() {
		super();
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getFeeMemo() {
		return feeMemo;
	}
	public void setFeeMemo(String feeMemo) {
		this.feeMemo = feeMemo;
	}
}
