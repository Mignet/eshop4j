package com.eshop4j.web.model;

import java.util.Date;

import com.eshop4j.core.base.BaseEntity;

public class InvestorProfitResp extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String customerId;
	private String customerName;
	private String customerMobile;
	private Date regTime;
	private Double investTotal;//投资总额
	private Double investingTotal;//在投金额
	private Integer investCount;//投资笔数
	private Double totalProfit;//收益总额
	private Double redpaperAmout;//最近使用红包
	private Double activeReward;//活动奖励
	private Double otherReward;//其他奖励
	private Double recentlyInvest;//最近投资
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
	public Double getInvestTotal() {
		return investTotal;
	}
	public void setInvestTotal(Double investTotal) {
		this.investTotal = investTotal;
	}
	public Double getInvestingTotal() {
		return investingTotal;
	}
	public void setInvestingTotal(Double investingTotal) {
		this.investingTotal = investingTotal;
	}
	public Integer getInvestCount() {
		return investCount;
	}
	public void setInvestCount(Integer investCount) {
		this.investCount = investCount;
	}
	public Double getTotalProfit() {
		return totalProfit;
	}
	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}
	public Double getRedpaperAmout() {
		return redpaperAmout;
	}
	public void setRedpaperAmout(Double redpaperAmout) {
		this.redpaperAmout = redpaperAmout;
	}
	public Double getActiveReward() {
		return activeReward;
	}
	public void setActiveReward(Double activeReward) {
		this.activeReward = activeReward;
	}
	public Double getOtherReward() {
		return otherReward;
	}
	public void setOtherReward(Double otherReward) {
		this.otherReward = otherReward;
	}
	public Double getRecentlyInvest() {
		return recentlyInvest;
	}
	public void setRecentlyInvest(Double recentlyInvest) {
		this.recentlyInvest = recentlyInvest;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	
	
	

}
