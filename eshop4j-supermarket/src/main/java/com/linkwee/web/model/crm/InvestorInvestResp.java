package com.linkwee.web.model.crm;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月03日 15:53:27
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class InvestorInvestResp extends BaseEntity {

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
	 * 最后投资时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date rectInvestTime;
	/**
	 * 销售收入
	 */
	private Double saleProfix;
	/**
	 * 投资总额
	 */
	private Double totalInvestAmount;
	/**
	 * 在投金额
	 */
	private Double currInvestAmount;
	/**
	 * 投资笔数
	 */
	private int investCount;
	/**
	 * 收益总额
	 */
	private Double totalProfit;
	/**
	 * 红包金额
	 */
	private Double hongbaoAmount;
	/**
	 * 其他奖励
	 */
	private Double otherReward;
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
	public Double getTotalInvestAmount() {
		return totalInvestAmount;
	}
	public void setTotalInvestAmount(Double totalInvestAmount) {
		this.totalInvestAmount = totalInvestAmount;
	}
	public Double getCurrInvestAmount() {
		return currInvestAmount;
	}
	public void setCurrInvestAmount(Double currInvestAmount) {
		this.currInvestAmount = currInvestAmount;
	}
	public int getInvestCount() {
		return investCount;
	}
	public void setInvestCount(int investCount) {
		this.investCount = investCount;
	}
	public Double getTotalProfit() {
		return totalProfit;
	}
	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}
	public Double getHongbaoAmount() {
		return hongbaoAmount;
	}
	public void setHongbaoAmount(Double hongbaoAmount) {
		this.hongbaoAmount = hongbaoAmount;
	}
	public Double getOtherReward() {
		return otherReward;
	}
	public void setOtherReward(Double otherReward) {
		this.otherReward = otherReward;
	}
	public Date getRectInvestTime() {
		return rectInvestTime;
	}
	public void setRectInvestTime(Date rectInvestTime) {
		this.rectInvestTime = rectInvestTime;
	}
	public Double getSaleProfix() {
		return saleProfix;
	}
	public void setSaleProfix(Double saleProfix) {
		this.saleProfix = saleProfix;
	}


}
