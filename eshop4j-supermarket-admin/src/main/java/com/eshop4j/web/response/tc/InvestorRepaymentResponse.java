package com.eshop4j.web.response.tc;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.eshop4j.core.base.BaseEntity;

public class InvestorRepaymentResponse extends BaseEntity{
	private BigDecimal amt =  new BigDecimal(10000);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String investorId;
	private String investorName;
	private String investorMobiel;
	private String cfpName;
	private String cfpMobile;
	private BigDecimal repaymentTotalAmt;
	private Integer productCount;
	private BigDecimal investAmt;
	private BigDecimal completionInvestAmt;
	private BigDecimal profit;
	private Integer isExport;
	
	
	public void setIsExport(Integer isExport) {
		this.isExport = isExport;
	}
	public String getInvestorId() {
		return investorId;
	}
	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}
	public String getInvestorName() {
		return investorName;
	}
	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}
	public String getInvestorMobiel() {
		if(isExport==0)
			return StringUtils.join( new Object[]{investorMobiel.substring(0,3),"****",investorMobiel.substring(investorMobiel.length()-4)});
		else 
			return investorMobiel; 
	}
	public void setInvestorMobiel(String investorMobiel) {
		this.investorMobiel = investorMobiel;
	}
	public String getCfpName() {
		return cfpName;
	}
	public void setCfpName(String cfpName) {
		this.cfpName = cfpName;
	}
	public String getCfpMobile() {
		if(isExport==0)
			return StringUtils.join( new Object[]{cfpMobile.substring(0,3),"****",cfpMobile.substring(cfpMobile.length()-4)});
		else 
			return cfpMobile; 
	}
	public void setCfpMobile(String cfpMobile) {
		this.cfpMobile = cfpMobile;
	}
	public String getRepaymentTotalAmt() {
		String amtStr = repaymentTotalAmt.compareTo(amt)>=0 ? repaymentTotalAmt.divide(amt, 4, BigDecimal.ROUND_DOWN).toString()+"万元" : repaymentTotalAmt.setScale(2,BigDecimal.ROUND_DOWN).toString()+"元";
		return amtStr;
	}
	public void setRepaymentTotalAmt(BigDecimal repaymentTotalAmt) {
		this.repaymentTotalAmt = repaymentTotalAmt;
	}
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	public String getInvestAmt() {
		String amtStr = investAmt.compareTo(amt)>=0 ? investAmt.divide(amt, 4, BigDecimal.ROUND_DOWN).toString()+"万元" : investAmt.setScale(2,BigDecimal.ROUND_DOWN).toString()+"元";
		return amtStr;
	}
	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}
	public String getCompletionInvestAmt() {
		String amtStr = completionInvestAmt.compareTo(amt)>=0 ? completionInvestAmt.divide(amt, 4, BigDecimal.ROUND_DOWN).toString()+"万元" : completionInvestAmt.setScale(2,BigDecimal.ROUND_DOWN).toString()+"元";
		return amtStr;
	}
	public void setCompletionInvestAmt(BigDecimal completionInvestAmt) {
		this.completionInvestAmt = completionInvestAmt;
	}
	public String getProfit() {
		return profit.setScale(2,BigDecimal.ROUND_DOWN).toString();
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	
}
