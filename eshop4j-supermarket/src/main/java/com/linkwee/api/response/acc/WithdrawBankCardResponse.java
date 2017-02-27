package com.linkwee.api.response.acc;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class WithdrawBankCardResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 银行名称
     */
	private String bankName;
	
	/**
     * 银行卡号
     */
	private String bankCard;
	
	/**
     * 客户姓名
     */
	private String userName;
	
	/**
     * 银行预留手机号码
     */
	private String mobile;
	
	/**
     * 手续费金额,单位: 元
     */
	private String fee; 
	
	/**
     * 是否需要提现手续费
     */
	private boolean hasFee;
	
	/**
     * 剩余免费提现次数
     */
	private int limitTimes;
	
	/**
     * 账户余额
     */
	private String totalAmount;
	
    /**
     * 城市
     */
	private String city;
	
    /**
     * 开户行
     */
	private String kaiHuHang;
	
	/**
     * 省份
     */
	private String province;
	
	
	/**
     * 是否需要开户行
     */
	private boolean  needkaiHuHang;
	
	/**
     * 预计到账时间
     */
	private String paymentDate;
	

	public String getPaymentDate() {
		return paymentDate;
	}



	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}



	public boolean getNeedkaiHuHang() {
		return needkaiHuHang;
	}



	public void setNeedkaiHuHang(boolean needkaiHuHang) {
		this.needkaiHuHang = needkaiHuHang;
	}



	public String getProvince() {
		return province;
	}



	public void setProvince(String province) {
		this.province = province;
	}



	public String getBankName() {
		return bankName;
	}



	public void setBankName(String bankName) {
		this.bankName = bankName;
	}



	public String getBankCard() {
		return bankCard;
	}



	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getFee() {
		return fee;
	}



	public void setFee(String fee) {
		this.fee = fee;
	}



	public boolean isHasFee() {
		return hasFee;
	}



	public void setHasFee(boolean hasFee) {
		this.hasFee = hasFee;
	}



	public int getLimitTimes() {
		return limitTimes;
	}



	public void setLimitTimes(int limitTimes) {
		this.limitTimes = limitTimes;
	}



	public String getTotalAmount() {
		return totalAmount;
	}



	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getKaiHuHang() {
		return kaiHuHang;
	}



	public void setKaiHuHang(String kaiHuHang) {
		this.kaiHuHang = kaiHuHang;
	}



	public String toString()
	{
	  return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}