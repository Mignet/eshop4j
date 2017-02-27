package com.eshop4j.api.request.cim;

import com.eshop4j.core.base.BaseEntity;

public class ProductRecommendChooseRequest extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     *产品ID
     */
	private String productId;
    /**
     *查询内容（姓名或手机号）
     */
	private String searchValue;
	/**
	 * 理财师userId
	 */
	private String userId;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
