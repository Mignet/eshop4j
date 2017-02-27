 package com.linkwee.web.request;

import com.linkwee.core.base.api.PaginatorRequest;

public class InvestRecordRequest extends PaginatorRequest {

	private static final long serialVersionUID = -4977432770614746364L;
	
    /**
     * 查询开始时间
     */
	private String startTimeForSearch;
	
    /**
     * 查询结束时间
     */
	private String endTimeForSearch;
	/**
	 * 用户id
	 */
	private String customerId;
	/**
	 * 产品名称
	 */
	private String productName;
	
	/**
	 * 状态
	 */
	private String status;

	public String getStartTimeForSearch() {
		return startTimeForSearch;
	}

	public void setStartTimeForSearch(String startTimeForSearch) {
		this.startTimeForSearch = startTimeForSearch;
	}

	public String getEndTimeForSearch() {
		return endTimeForSearch;
	}

	public void setEndTimeForSearch(String endTimeForSearch) {
		this.endTimeForSearch = endTimeForSearch;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	
	
}
