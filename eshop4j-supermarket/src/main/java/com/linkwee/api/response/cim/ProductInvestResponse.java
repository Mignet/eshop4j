package com.linkwee.api.response.cim;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProductInvestResponse {
	/**
	 * 投资者姓名
	 */
	private String user;
	/**
	 * 投资者电话号码
	 */
	private String mobile;
    /**
     *购买本金
     */
	private BigDecimal amt;
    /**
     *计息日期(成交时间)
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date time;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMobile() {
		StringBuilder sb = new StringBuilder(mobile);
		return sb.replace(3, 7, "****").toString();
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAmt() {
		return amt.setScale(2,BigDecimal.ROUND_DOWN).toString();
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

}
