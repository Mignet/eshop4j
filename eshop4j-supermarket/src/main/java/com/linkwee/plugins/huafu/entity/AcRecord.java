package com.linkwee.plugins.huafu.entity;

import java.io.Serializable;
 /**
 * 
 * @描述： 实名验证
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年08月03日 14:35:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AcRecord implements Serializable {
	
	private static final long serialVersionUID = -6941427020761839097L;
	
    /**
     *身份证号码
     */
	private String idCard;
    /**
     *认证结果
     */
	private String resCode;
	/**
     *姓名
     */
	private String realName;
	/**
     *认证描述
     */
	private String resDesc;
	/**
     *银行卡号码
     */
	private String bankCard;
	
	/**
     *银行预留手机号码
     */
	private String mobile;
	
	/**
	 * 以下字段,银行卡信息验证接口添加
	 */
     
	private String bankCardNum;
	
	private String bankName;
	
	private String bankCardName;
	
	private String atm;
	
	private String pos;
	
	private String bankCardType;
	
	private String  bankPhone;
	
	
	public String getBankCardNum() {
		return bankCardNum;
	}

	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCardName() {
		return bankCardName;
	}

	public void setBankCardName(String bankCardName) {
		this.bankCardName = bankCardName;
	}

	public String getAtm() {
		return atm;
	}

	public void setAtm(String atm) {
		this.atm = atm;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getBankCardType() {
		return bankCardType;
	}

	public void setBankCardType(String bankCardType) {
		this.bankCardType = bankCardType;
	}

	public String getBankPhone() {
		return bankPhone;
	}

	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getResDesc() {
		return resDesc;
	}

	public void setResDesc(String resDesc) {
		this.resDesc = resDesc;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	
	
}

