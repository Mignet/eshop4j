package com.linkwee.web.response.tc;

import java.math.BigDecimal;

import com.linkwee.core.base.BaseEntity;

public class FeeTopDetailResponse extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2966404939355183691L;
	private BigDecimal amt;
	private String mobile;
	private String name;
	private String city;
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
