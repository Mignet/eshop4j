package com.eshop4j.web.model;

import java.util.Date;

import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.DateUtils;

public class MyInvestedCustomerResp extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String customerId;
	private String customerName;
	private String customerMobile;
	private Date regTime;
	private Double totalInvest;
	private int proNum;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public Date getRegTime() {
		
		return regTime;
	}
	public String getRegTimeStr(){
		if(regTime!=null){
		return DateUtils.format(regTime, "yyyy-MM-dd");
		}else{
			return "-";
		}
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public Double getTotalInvest() {
		return totalInvest;
	}
	public void setTotalInvest(Double totalInvest) {
		this.totalInvest = totalInvest;
	}
	public int getProNum() {
		return proNum;
	}
	public void setProNum(int proNum) {
		this.proNum = proNum;
	}
	
	

}
