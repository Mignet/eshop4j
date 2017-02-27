package com.eshop4j.web.response.tc;

import java.math.BigDecimal;

import com.eshop4j.core.base.BaseEntity;

public class InvestmentTopDetailResponse extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2966404939355183691L;
	private BigDecimal yearpurAmount;
	private BigDecimal productAmount;
	private String mobile;
	private String userName;
	private String cfpCity;
	private String cfpMobile;
	private String cfpName;
	public BigDecimal getYearpurAmount() {
		return yearpurAmount.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setYearpurAmount(BigDecimal yearpurAmount) {
		this.yearpurAmount = yearpurAmount;
	}
	public BigDecimal getProductAmount() {
		return productAmount.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCfpCity() {
		return cfpCity;
	}
	public void setCfpCity(String cfpCity) {
		this.cfpCity = cfpCity;
	}
	public String getCfpMobile() {
		return cfpMobile;
	}
	public void setCfpMobile(String cfpMobile) {
		this.cfpMobile = cfpMobile;
	}
	public String getCfpName() {
		return cfpName;
	}
	public void setCfpName(String cfpName) {
		this.cfpName = cfpName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
