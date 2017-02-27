package com.eshop4j.web.request;

import java.util.Date;

public class ProductSaleDetailRequest {
	
	/**
     * 产品名称或账号
     */
	private String productNaOrNumber;
    /**
     * 成交开始时间
     */
	private Date investStartTime;
    /**
     * 成交截止时间
     */
	private Date investEndTime;
	/**
	 * 产品ID
	 */
	private String productId;
	
	public String getProductNaOrNumber() {
		return productNaOrNumber;
	}
	public void setProductNaOrNumber(String productNaOrNumber) {
		this.productNaOrNumber = productNaOrNumber;
	}
	public Date getInvestStartTime() {
		return investStartTime;
	}
	public void setInvestStartTime(Date investStartTime) {
		this.investStartTime = investStartTime;
	}
	public Date getInvestEndTime() {
		return investEndTime;
	}
	public void setInvestEndTime(Date investEndTime) {
		this.investEndTime = investEndTime;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
}
