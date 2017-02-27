package com.eshop4j.web.model.crm;

import java.util.Date;

import com.eshop4j.core.base.BaseEntity;

public class CustomerInvestStatisticPageListResp extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 860445370728237857L;
	/**
	 * 发生时间
	 */
	private Date time;
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
	private Double amt;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 年化收益率
	 */
	private Double yearRate;
	/**
	 * 佣金率
	 */
	private Double feeRate;
	/**
	 * 投资笔数
	 */
	private Integer investCount;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
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

	public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getYearRate() {
		return yearRate;
	}

	public void setYearRate(Double yearRate) {
		this.yearRate = yearRate;
	}

	public Double getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(Double feeRate) {
		this.feeRate = feeRate;
	}

	public Integer getInvestCount() {
		return investCount;
	}

	public void setInvestCount(Integer investCount) {
		this.investCount = investCount;
	}

}
