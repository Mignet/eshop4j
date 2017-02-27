package com.linkwee.web.response.tc;

import java.math.BigDecimal;
import java.util.Date;

import com.linkwee.core.base.BaseEntity;

public class FeebalanceListResponse extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6779748547803157671L;
	
	//private String userId;
	private String mobile;
	private String name;
	private BigDecimal amount;
	private Integer count;
	private String time;
	private Date sendTime;
	private int status;
	
/*	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}*/
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
	public BigDecimal getAmount() {
		return amount.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getStatus() {
		switch (status) {
		case 0: 
			return "未发放";
		case 1: 
			return "发放中";
		case 2: 
			return "发放成功";
		case 3: 
			return "发放失败";
		default:
			return "";
		}
		
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
