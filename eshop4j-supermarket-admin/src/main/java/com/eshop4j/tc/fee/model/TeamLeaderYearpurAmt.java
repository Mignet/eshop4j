package com.eshop4j.tc.fee.model;

import java.math.BigDecimal;

public class TeamLeaderYearpurAmt {

	private String userId;
	private BigDecimal investmentAmt;
	private BigDecimal yearpurAmt;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public BigDecimal getInvestmentAmt() {
		return investmentAmt;
	}
	public void setInvestmentAmt(BigDecimal investmentAmt) {
		this.investmentAmt = investmentAmt;
	}
	public BigDecimal getYearpurAmt() {
		return yearpurAmt;
	}
	public void setYearpurAmt(BigDecimal yearpurAmt) {
		this.yearpurAmt = yearpurAmt;
	}
	
	
}
