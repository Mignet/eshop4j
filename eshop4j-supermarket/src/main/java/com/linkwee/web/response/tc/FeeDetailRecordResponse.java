package com.linkwee.web.response.tc;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.base.BaseEntity;

public class FeeDetailRecordResponse extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7050141242868321892L;
	private String name;
	private String mobile;
	private String productName;
	private BigDecimal investAmt;
	private BigDecimal feeAmt;
	private Double rate;
	private String type;
	private String remark;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date time;
	
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getInvestAmt() {
		return investAmt.setScale(4,BigDecimal.ROUND_HALF_UP);
	}
	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}
	public BigDecimal getFeeAmt() {
		return feeAmt.setScale(4,BigDecimal.ROUND_HALF_UP);
	}
	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
}
