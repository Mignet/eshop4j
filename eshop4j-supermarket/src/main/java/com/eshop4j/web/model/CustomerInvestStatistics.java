package com.eshop4j.web.model;

import java.math.BigDecimal;

import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.xoss.util.BigDecimalUtil;


public class CustomerInvestStatistics extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5106422353260445884L;
	
	private String name;
	private String mobile;
	private String userId;
	private String rectInvestTime;
	private BigDecimal totalAmt;
	private BigDecimal investamt;
	private Integer investCount;
	private BigDecimal profitamt;
	private BigDecimal saleamt;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRectInvestTime() {
		return rectInvestTime;
	}
	public void setRectInvestTime(String rectInvestTime) {
		this.rectInvestTime = rectInvestTime;
	}
	public BigDecimal getTotalAmt() {
		return BigDecimalUtil.divide(totalAmt, 10000).setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}
	public BigDecimal getInvestamt() {
		return BigDecimalUtil.divide(investamt, 10000).setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setInvestamt(BigDecimal investamt) {
		this.investamt = investamt;
	}
	public Integer getInvestCount() {
		return investCount;
	}
	public void setInvestCount(Integer investCount) {
		this.investCount = investCount;
	}
	public BigDecimal getProfitamt() {
		return profitamt.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setProfitamt(BigDecimal profitamt) {
		this.profitamt = profitamt;
	}
	public BigDecimal getSaleamt() {
		return saleamt.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setSaleamt(BigDecimal saleamt) {
		this.saleamt = saleamt;
	}
	
}
