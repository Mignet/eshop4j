package com.linkwee.web.model;

import com.linkwee.core.base.BaseEntity;

public class CustomerInvestStatisticPageListResponse extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 860445370728237857L;
	/**
	 * 发生时间
	 */
	private String time;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 客户手机号码
	 */
	private String customerMobile;
	/**
	 * 金额
	 */
	private String amt;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 年化
	 */
	private String yearRate;
	/**
	 * 佣金
	 */
	private String feeRate;
	/**
	 * 投资笔数
	 */
	private String investCount;
	
	
	public CustomerInvestStatisticPageListResponse() {

	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getYearRate() {
		return yearRate;
	}

	public void setYearRate(String yearRate) {
		this.yearRate = yearRate;
	}

	public String getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(String feeRate) {
		this.feeRate = feeRate;
	}

	public String getInvestCount() {
		return investCount;
	}

	public void setInvestCount(String investCount) {
		this.investCount = investCount;
	}

}
