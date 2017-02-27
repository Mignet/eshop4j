package com.linkwee.api.response.cim;

import com.linkwee.core.base.BaseEntity;

public class ProductStatisticsPreferenceResponse extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     *分类id
     */
	private Integer cateId;
	
    /**
     *分类名称
     */
	private String cateName;
	
	/**
	 * 每个产品分类  对应的一个产品 (PC端)
	 */
	private ProductPageListResponse productPageListResponse;

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public ProductPageListResponse getProductPageListResponse() {
		return productPageListResponse;
	}

	public void setProductPageListResponse(
			ProductPageListResponse productPageListResponse) {
		this.productPageListResponse = productPageListResponse;
	}
}
