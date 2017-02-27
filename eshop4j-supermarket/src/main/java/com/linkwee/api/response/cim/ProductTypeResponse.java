package com.linkwee.api.response.cim;

import com.linkwee.core.base.BaseEntity;

public class ProductTypeResponse extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2542481590125992254L;

	/**
	 * 标的分类id
	 */
	private String cateId;
	
	/**
	 * 标的分类名称
	 */
	private String name;

	public String getCateId() {
		return cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
