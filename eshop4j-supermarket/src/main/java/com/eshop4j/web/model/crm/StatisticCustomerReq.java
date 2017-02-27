package com.eshop4j.web.model.crm;


public class StatisticCustomerReq extends TimeReq{

	private static final long serialVersionUID = -3807038643608680537L;
	/**
	 * 用户编码
	 */
	private String userNumber;
	
	/**
	 * 用户的客户编码
	 */
	private String customerId;
	/**
	 * 用户手机号
	 */
	private String userMobile;
	/**
	 * 交易类别
	 */
	private Integer[] types;
	
	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer[] getTypes() {
		return types;
	}

	public void setTypes(Integer[] types) {
		this.types = types;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	

	

	
}
