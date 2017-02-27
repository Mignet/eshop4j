package com.eshop4j.web.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class InvestorDtlResp implements Serializable{

	private static final long serialVersionUID = 1L;
	private String customerId;
	private String customerName;
	private Date regTime;
	private String customerMobile;
	private int investedNumber;//邀请人数
	private String centType;//证件类型
	private String centNum;//证件号码
	private String cardBankName;//银行名称
	private String cardNo;//银行卡号
	private int regSource;
	private String currentSaleUser;
	private String currentSaleName;
	private String currentSaleMobile;
	private int isCfp;
	private Integer freecustomer;
	private String headImage;//头像
	
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	/**
	 * 归属理财师变更操作记录
	 */
	private List<ChangeLcsRecord> changeLcsRecordList;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public int getInvestedNumber() {
		return investedNumber;
	}
	public void setInvestedNumber(int investedNumber) {
		this.investedNumber = investedNumber;
	}
	public String getCentType() {
		return centType;
	}
	public void setCentType(String centType) {
		this.centType = centType;
	}
	public String getCentNum() {
		return centNum;
	}
	public void setCentNum(String centNum) {
		this.centNum = centNum;
	}
	public String getCardBankName() {
		return cardBankName;
	}
	public void setCardBankName(String cardBankName) {
		this.cardBankName = cardBankName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public int getRegSource() {
		return regSource;
	}
	public void setRegSource(int regSource) {
		this.regSource = regSource;
	}
	public String getCurrentSaleName() {
		return currentSaleName;
	}
	public void setCurrentSaleName(String currentSaleName) {
		this.currentSaleName = currentSaleName;
	}
	public String getCurrentSaleMobile() {
		return currentSaleMobile;
	}
	public void setCurrentSaleMobile(String currentSaleMobile) {
		this.currentSaleMobile = currentSaleMobile;
	}
	public String getCurrentSaleUser() {
		return currentSaleUser;
	}
	public void setCurrentSaleUser(String currentSaleUser) {
		this.currentSaleUser = currentSaleUser;
	}
	public int getIsCfp() {
		return isCfp;
	}
	public void setIsCfp(int isCfp) {
		this.isCfp = isCfp;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Integer getFreecustomer() {
		return freecustomer;
	}
	public void setFreecustomer(Integer freecustomer) {
		this.freecustomer = freecustomer;
	}
	public List<ChangeLcsRecord> getChangeLcsRecordList() {
		return changeLcsRecordList;
	}
	public void setChangeLcsRecordList(List<ChangeLcsRecord> changeLcsRecordList) {
		this.changeLcsRecordList = changeLcsRecordList;
	}
	

}
