package com.eshop4j.web.model;

import com.eshop4j.core.base.BaseEntity;

public class MyInvitedCustomerPageListResponse extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 860445370728237857L;
	/**
	 * userId
	 */
	private String customerId;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 客户手机号码
	 */
	private String customerMobile;
	/**
	 * 注册时间
	 */
	private String regDate;
	/**
	 * 总投资额
	 */
	private String totalInvest;
	/**
	 * 购买产品数
	 */
	private int proCount;
	
	
	
	public MyInvitedCustomerPageListResponse() {

	}



	public String getCustomerId() {
		return customerId;
	}



	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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



	public String getRegDate() {
		return regDate;
	}



	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}



	public String getTotalInvest() {
		return totalInvest;
	}



	public void setTotalInvest(String totalInvest) {
		this.totalInvest = totalInvest;
	}



	public int getProCount() {
		return proCount;
	}



	public void setProCount(int proCount) {
		this.proCount = proCount;
	}
	
	



}
