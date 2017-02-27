package com.eshop4j.api.request.cim;

import com.eshop4j.core.base.BaseEntity;

public class ProductRecommendByChooseRequest extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     *产品ID
     */
	private String productId;
	
    /**
     *用户userIdString  多个 用,分割
     */
	private String userIdString;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUserIdString() {
		return userIdString;
	}

	public void setUserIdString(String userIdString) {
		this.userIdString = userIdString;
	}
}
