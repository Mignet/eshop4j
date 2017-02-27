package com.linkwee.api.request.cim;

import org.hibernate.validator.constraints.NotBlank;

import com.linkwee.core.base.api.PaginatorRequest;

public class ProductInvestRequest extends PaginatorRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8440347173513036253L;
	
	@NotBlank(message="产品编号不能为空")
	private String productId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}
