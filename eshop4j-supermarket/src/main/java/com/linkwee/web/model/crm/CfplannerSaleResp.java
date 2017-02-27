package com.linkwee.web.model.crm;

import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述： 理财师销售与收益列表
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月03日 15:53:27
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CfplannerSaleResp extends BaseEntity {

	private static final long serialVersionUID = -1442643838535851690L;

	/**
	 * 流水号
	 */
	private Integer id;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 累计销售
	 */
	private Double totalSales;
	/**
	 * 销售笔数
	 */
	private Double countSales;
	/**
	 * 佣金
	 */
	private Double fee;
	/**
	 * 推荐收益
	 */
	private Double allowance;
	/**
	 * 活动奖励
	 */
	private Double activityReward;
	/**
	 * 客户在投
	 */
	private Double currInvestAmount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Double getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(Double totalSales) {
		this.totalSales = totalSales;
	}
	public Double getCountSales() {
		return countSales;
	}
	public void setCountSales(Double countSales) {
		this.countSales = countSales;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Double getActivityReward() {
		return activityReward;
	}
	public void setActivityReward(Double activityReward) {
		this.activityReward = activityReward;
	}
	public Double getCurrInvestAmount() {
		return currInvestAmount;
	}
	public void setCurrInvestAmount(Double currInvestAmount) {
		this.currInvestAmount = currInvestAmount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Double getAllowance() {
		return allowance;
	}
	public void setAllowance(Double allowance) {
		this.allowance = allowance;
	}


}
