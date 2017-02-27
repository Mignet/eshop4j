package com.linkwee.web.model.crm;

import java.math.BigDecimal;
import java.util.Date;

import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述： 机构销售业绩明细
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月03日 15:53:27
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class OrgSalesDetailResp extends BaseEntity {

	private static final long serialVersionUID = -1442643838535851690L;

	/**
	 * 流水号
	 */
	private Integer id;

	/**
	 * 用户名
	 */
	private String cfpName;

	/**
	 * 手机号码
	 */
	private String cfpMobile;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 用户名
	 */
	private String investorName;

	/**
	 * 手机号码
	 */
	private String investorMobile;
	
	/**
	 * 投资金额
	 */
	private BigDecimal investAmount;
	
	/**
	 * 投资平台
	 */
	private String platform;
	/**
	 * 投资时间
	 */
	private Date investTime;
	
	/**
	 * 销售费用
	 */
	private BigDecimal salesCost;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCfpName() {
		return cfpName;
	}
	public void setCfpName(String cfpName) {
		this.cfpName = cfpName;
	}
	public String getCfpMobile() {
		return cfpMobile;
	}
	public void setCfpMobile(String cfpMobile) {
		this.cfpMobile = cfpMobile;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getInvestorName() {
		return investorName;
	}
	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}
	public String getInvestorMobile() {
		return investorMobile;
	}
	public void setInvestorMobile(String investorMobile) {
		this.investorMobile = investorMobile;
	}
	public BigDecimal getInvestAmount() {
		return investAmount.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public Date getInvestTime() {
		return investTime;
	}
	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}
	public BigDecimal getSalesCost() {
		return salesCost.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setSalesCost(BigDecimal salesCost) {
		this.salesCost = salesCost;
	}


}
