package com.linkwee.api.request.cim;

import com.linkwee.core.base.BaseEntity;

public class ProductCateForShowRequest extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *产品id 
	 */
	private String productId;
	
	/**
	 * APP类型  investor,channel
	 */
	private String appKind;
    /**
     *是否可赎回可转让(0=不支持赎回和转让|1=可赎回|2=可转让|3=可赎回且可转让)
     */
	private Integer isRedemption;
	
    /**
     *可赎回天数
     */
	private Integer redemptionTime;
	
    /**
     *可转让天数
     */
	private Integer assignmentTime;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getAppKind() {
		return appKind;
	}

	public void setAppKind(String appKind) {
		this.appKind = appKind;
	}

	public Integer getIsRedemption() {
		return isRedemption;
	}

	public void setIsRedemption(Integer isRedemption) {
		this.isRedemption = isRedemption;
	}

	public Integer getRedemptionTime() {
		return redemptionTime;
	}

	public void setRedemptionTime(Integer redemptionTime) {
		this.redemptionTime = redemptionTime;
	}

	public Integer getAssignmentTime() {
		return assignmentTime;
	}

	public void setAssignmentTime(Integer assignmentTime) {
		this.assignmentTime = assignmentTime;
	}
}
