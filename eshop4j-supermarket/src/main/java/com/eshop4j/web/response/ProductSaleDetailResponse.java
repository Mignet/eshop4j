package com.eshop4j.web.response;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProductSaleDetailResponse {

	/**
	 * 投资者姓名
	 */
	private String investorUserName;
	/**
	 * 投资者电话号码
	 */
	private String investorUserMobile;
    /**
     *购买本金
     */
	private BigDecimal investAmt;
    /**
     *计息日期(成交时间)
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date startTime;
	
	public String getInvestorUserName() {
		return investorUserName;
	}
	public void setInvestorUserName(String investorUserName) {
		this.investorUserName = investorUserName;
	}
	public String getInvestorUserMobile() {
		return investorUserMobile;
	}
	public void setInvestorUserMobile(String investorUserMobile) {
		this.investorUserMobile = investorUserMobile;
	}
	public BigDecimal getInvestAmt() {
		return investAmt;
	}
	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
}
