package com.eshop4j.web.model.crm;

import java.util.Date;

public class CustomerInvestStatisticReq {
	/**
	 * 用户编码
	 */
	private String userNumber;
	
	/**
	 * 用户的客户编码
	 */
	private String customerId;
	/**
	 * 时间类别: 1:年；2:季度；3:月；4:日 ;5:历史累计
	 */
	private Integer timeType;
	/**
	 * 起始日期
	 */
	private Date startTime;
	/**
	 * 截止日期
	 */
	private Date endTime;
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
	public Integer getTimeType() {
		return timeType;
	}
	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}
