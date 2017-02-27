package com.eshop4j.api.activity.response;

public class ActivityBillboardResponse {
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 排行数据
	 */
	private Object orderData;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Object getOrderData() {
		return orderData;
	}
	public void setOrderData(Object orderData) {
		this.orderData = orderData;
	}
	
}
