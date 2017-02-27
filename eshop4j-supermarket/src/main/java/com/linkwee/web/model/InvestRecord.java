package com.linkwee.web.model;

import java.util.Date;

import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年08月12日 09:49:11
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class InvestRecord extends BaseEntity{
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customerId;
	private String customerName;
	 private String investTime;
	 private String productName;
	 private Date endDate;
	 private  String profit;
	 private String investAmount;
	 private int investState;
	 private int isFlow;
	 private double fixRate;
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
	public String getInvestTime() {
		return investTime;
	}
	public void setInvestTime(String investTime) {
		this.investTime = investTime;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	public String getInvestAmount() {
		return investAmount;
	}
	public void setInvestAmount(String investAmount) {
		this.investAmount = investAmount;
	}
	public int getInvestState() {
		return investState;
	}
	public void setInvestState(int investState) {
		this.investState = investState;
	}
	public int getIsFlow() {
		return isFlow;
	}
	public void setIsFlow(int isFlow) {
		this.isFlow = isFlow;
	}
	public double getFixRate() {
		return fixRate;
	}
	public void setFixRate(double fixRate) {
		this.fixRate = fixRate;
	}

	
}