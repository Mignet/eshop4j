package com.linkwee.api.request.cim;

import com.linkwee.core.base.BaseEntity;

public class ProductDetailRequest extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *产品id 
	 */
	private String productId;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * APP类型  investor,channel
	 */
	private String appKind;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAppKind() {
		return appKind;
	}
	public void setAppKind(String appKind) {
		this.appKind = appKind;
	}
}
