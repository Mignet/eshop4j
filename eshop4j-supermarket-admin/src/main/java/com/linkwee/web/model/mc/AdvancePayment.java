package com.linkwee.web.model.mc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月20日 15:59:52
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AdvancePayment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String investorUserId;
	private BigDecimal investAmt;
	private String investorMobile;
	private String investorName;
	private String cfpUserId;
	private String productName;
	private Date bizTime;
	private Integer isRedemption;
	public String getInvestorUserId() {
		return investorUserId;
	}
	public void setInvestorUserId(String investorUserId) {
		this.investorUserId = investorUserId;
	}
	public BigDecimal getInvestAmt() {
		return investAmt;
	}
	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}
	public String getInvestorMobile() {
		return investorMobile;
	}
	public void setInvestorMobile(String investorMobile) {
		this.investorMobile = investorMobile;
	}
	public String getInvestorName() {
		return investorName;
	}
	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}
	public String getCfpUserId() {
		return cfpUserId;
	}
	public void setCfpUserId(String cfpUserId) {
		this.cfpUserId = cfpUserId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Date getBizTime() {
		return bizTime;
	}
	public void setBizTime(Date bizTime) {
		this.bizTime = bizTime;
	}
	public Integer getIsRedemption() {
		return isRedemption;
	}
	public void setIsRedemption(Integer isRedemption) {
		this.isRedemption = isRedemption;
	}
	
	
	
}

