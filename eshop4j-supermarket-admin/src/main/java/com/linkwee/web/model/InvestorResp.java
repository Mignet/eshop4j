package com.linkwee.web.model;

import java.util.Date;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;

public class InvestorResp extends BaseEntity {
	
	private static final long serialVersionUID = -6454238333125345993L;
	
 /**
  * 
  */
	private String customerId;
	
  /**
   * 
   */
	private String customerName;
	/**
	 * 
	 */
	private String customerMobile;
	/**
	 * 
	 */
 
	private Date regtime;
	/*
	 * 
	 */
	private String currsaleuser ;
	/**
	 * 
	 */
	private int investNum;
	/**
	 * 
	 */
	private Date rectranstime;
	/**
	 * 
	 */
	private Date rectlogintime;
	/**
	 * 
	 */
	private String currSaleuserNumber;
	
	public String getCurrSaleuserNumber() {
		return currSaleuserNumber;
	}
	public void setCurrSaleuserNumber(String currSaleuserNumber) {
		this.currSaleuserNumber = currSaleuserNumber;
	}
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
	public Date getRegtime() {
		return regtime;
	}
	public String getRegTimeStr() {
		if(regtime!=null){
			return DateUtils.format(regtime, "yyyy-MM-dd");
		}else{
			return "-";
		}
			
	}
	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}
	public String getCurrsaleuser() {
		return currsaleuser;
	}
	public void setCurrsaleuser(String currsaleuser) {
		this.currsaleuser = currsaleuser;
	}
	public int getInvestNum() {
		return investNum;
	}
	public void setInvestNum(int investNum) {
		this.investNum = investNum;
	}
	public Date getRectranstime() {
		return rectranstime;
	}
	public void setRectranstime(Date rectranstime) {
		this.rectranstime = rectranstime;
	}
	public Date getRectlogintime() {
		return rectlogintime;
	}
	public void setRectlogintime(Date rectlogintime) {
		this.rectlogintime = rectlogintime;
	}
   
	
}

