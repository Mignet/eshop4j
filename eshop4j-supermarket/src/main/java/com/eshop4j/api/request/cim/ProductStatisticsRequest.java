package com.eshop4j.api.request.cim;

import com.eshop4j.core.base.BaseEntity;

public class ProductStatisticsRequest extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 产品分类列表      (对应产品分类表tcim_product_cate分类id)
	 * 单个直接数字  如：1
	 * 多个请使用,分开       如：1,2,3,4,5
	 * 特殊产品分类(非产品分类表分类)  801-理财师推荐产品   901-首投标  902-复投标
	 */
	private String cateIdList;
	/**
	 * 理财师id
	 * 当cateIdList = 801(理财师推荐产品) 必需
	 */
	private String cfplannerUserId;
	/**
	 * 是否拥有灰度权限 true=拥有灰度权限  false=没有灰度权限
	 */
	private Boolean ifHaveGray;
	
	public String getCateIdList() {
		return cateIdList;
	}

	public void setCateIdList(String cateIdList) {
		this.cateIdList = cateIdList;
	}

	public String getCfplannerUserId() {
		return cfplannerUserId;
	}

	public void setCfplannerUserId(String cfplannerUserId) {
		this.cfplannerUserId = cfplannerUserId;
	}

	public Boolean getIfHaveGray() {
		return ifHaveGray;
	}

	public void setIfHaveGray(Boolean ifHaveGray) {
		this.ifHaveGray = ifHaveGray;
	}
}
