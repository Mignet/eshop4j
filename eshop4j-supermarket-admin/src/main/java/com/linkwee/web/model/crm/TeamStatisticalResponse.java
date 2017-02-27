package com.linkwee.web.model.crm;

import java.math.BigDecimal;

public class TeamStatisticalResponse {
	
	/**
	 * 成交单数
	 */
	private Integer investCount;
	/**
	 * 投资总额
	 */
	private BigDecimal totalAmt = new BigDecimal(0.0);
	/**
	 * 年化投资额
	 */
	private BigDecimal totalYearpurAmt = new BigDecimal(0.0);
	/**
	 * 理财师平均销售额
	 */
	private BigDecimal avgSalesAmt = new BigDecimal(0.0);
	/**
	 * 佣金总额
	 */
	private BigDecimal totalFeeAmt = new BigDecimal(0.0);
	
	/**
	 * 存量投资额年化
	 */
	private BigDecimal stockYearpurAmt= new BigDecimal(0.0);
	
	/**
	 * 存量投资额
	 */
	private BigDecimal stockpurAmt= new BigDecimal(0.0);
	
	public Integer getInvestCount() {
		return investCount;
	}
	public void setInvestCount(Integer investCount) {
		this.investCount = investCount;
	}
	public BigDecimal getTotalAmt() {
		return totalAmt.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}
	public BigDecimal getTotalYearpurAmt() {
		return totalYearpurAmt.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setTotalYearpurAmt(BigDecimal totalYearpurAmt) {
		this.totalYearpurAmt = totalYearpurAmt;
	}
	public BigDecimal getAvgSalesAmt() {
		return avgSalesAmt.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setAvgSalesAmt(BigDecimal avgSalesAmt) {
		this.avgSalesAmt = avgSalesAmt;
	}
	public BigDecimal getTotalFeeAmt() {
		return totalFeeAmt.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setTotalFeeAmt(BigDecimal totalFeeAmt) {
		this.totalFeeAmt = totalFeeAmt;
	}
	public BigDecimal getStockYearpurAmt() {
		return stockYearpurAmt.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setStockYearpurAmt(BigDecimal stockYearpurAmt) {
		this.stockYearpurAmt = stockYearpurAmt;
	}
	public BigDecimal getStockpurAmt() {
		return stockpurAmt.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setStockpurAmt(BigDecimal stockpurAmt) {
		this.stockpurAmt = stockpurAmt;
	}
	


}
