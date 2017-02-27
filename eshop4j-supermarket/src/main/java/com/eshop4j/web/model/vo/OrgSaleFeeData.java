package com.eshop4j.web.model.vo;

import java.math.BigDecimal;
/**
 * 图片服务器返回值json
 * @author Mignet
 *
 */
public class OrgSaleFeeData {

	public OrgSaleFeeData(){}
	/**
	 * 统计日期
	 */
	private String statDate;
	/**
	 * 天销售额
	 */
	private BigDecimal daySaleAmount;
	/**
	 * 年化销售额
	 */
	private BigDecimal daySaleForYearAmount;
	/**
	 * 当天投资人数
	 */
	private Integer investPersonAmount;
	/**
	 * 人均投资额
	 */
	private BigDecimal avgInvest;
	/**
	 * 新增投资人数
	 */
	private Integer newInvestor;
	/**
	 * 新增投资人投资总额
	 */
	private BigDecimal newInvestAmount;
	/**
	 * 销售费用
	 */
	private BigDecimal totalFeeAmount;
	private String orgName;
	
	public String getStatDate() {
		return statDate;
	}
	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}
	public BigDecimal getDaySaleAmount() {
		return daySaleAmount;
	}
	public void setDaySaleAmount(BigDecimal daySaleAmount) {
		this.daySaleAmount = daySaleAmount;
	}
	public BigDecimal getDaySaleForYearAmount() {
		return daySaleForYearAmount;
	}
	public void setDaySaleForYearAmount(BigDecimal daySaleForYearAmount) {
		this.daySaleForYearAmount = daySaleForYearAmount;
	}
	public Integer getInvestPersonAmount() {
		return investPersonAmount;
	}
	public void setInvestPersonAmount(Integer investPersonAmount) {
		this.investPersonAmount = investPersonAmount;
	}
	public BigDecimal getAvgInvest() {
		return avgInvest;
	}
	public void setAvgInvest(BigDecimal avgInvest) {
		this.avgInvest = avgInvest;
	}
	public Integer getNewInvestor() {
		return newInvestor;
	}
	public void setNewInvestor(Integer newInvestor) {
		this.newInvestor = newInvestor;
	}
	public BigDecimal getNewInvestAmount() {
		return newInvestAmount;
	}
	public void setNewInvestAmount(BigDecimal newInvestAmount) {
		this.newInvestAmount = newInvestAmount;
	}
	public BigDecimal getTotalFeeAmount() {
		return totalFeeAmount;
	}
	public void setTotalFeeAmount(BigDecimal totalFeeAmount) {
		this.totalFeeAmount = totalFeeAmount;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	
	
	
}
