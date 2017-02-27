package com.linkwee.web.model.acc;

import java.io.Serializable;

public class AcAccountBindRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4977432770614746364L;
	
	 /**
     *客户姓名
     */
	private String userName;
	
    /**
     *银行预留手机号码
     */
	private String reserveMobile;
	
	 /**
     *注册手机号码
     */
	private String mobile;
	
    /**
     *银行卡号
     */
	private String bankCard;
	
    /**
     *银行编码
     */
	private String bankCode;
	
    /**
     *银行名称
     */
	private String bankName;
	
	 /**
     *银行Id
     */
	private String bankId;
	
	/**
     *身份证号
     */
	private String idCard;

	
	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReserveMobile() {
		return reserveMobile;
	}

	public void setReserveMobile(String reserveMobile) {
		this.reserveMobile = reserveMobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	
}
