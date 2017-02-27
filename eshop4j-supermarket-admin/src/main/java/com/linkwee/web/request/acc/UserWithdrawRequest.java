package com.linkwee.web.request.acc;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class UserWithdrawRequest {

	/**
	 * 真实姓名
	 */
//	@NotNull(message = "真实姓名不能为空")
	private String userName;
	
	/**
	 * 身份证号
	 */
	private String idCard;
	
	/**
	 * 提现金额
	 */
	@NotNull(message = "提现金额不能为空")
	private String amount;
	
	/**
	 * 银行卡号
	 */
	@NotNull(message = "银行卡号不能为空")
	private String bankCard;
	
	/**
	 * 银行名称
	 */
	@NotNull(message = "银行名称不能为空")
	private String bankName;
	
	/**
	 * 银行Code
	 */
//	@NotNull(message = "银行编号不能为空")
	private String bankCode;
	
	/**
	 * 城市
	 */
	private String city;
	
	/**
	 * 开户行
	 */
	private String kaihuhang;
	
    /**
   	 * 1理财师，2投资者
   	 */
    private int userType;
    
    /**
   	 * 用户ID
   	 */
    private String userId;

    
	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getIdCard() {
		return idCard;
	}



	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}



	public String getAmount() {
		return amount;
	}



	public void setAmount(String amount) {
		this.amount = amount;
	}



	public String getBankCard() {
		return bankCard;
	}



	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}



	public String getBankName() {
		return bankName;
	}



	public void setBankName(String bankName) {
		this.bankName = bankName;
	}



	public String getBankCode() {
		return bankCode;
	}



	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getKaihuhang() {
		return kaihuhang;
	}



	public void setKaihuhang(String kaihuhang) {
		this.kaihuhang = kaihuhang;
	}



	public int getUserType() {
		return userType;
	}



	public void setUserType(int userType) {
		this.userType = userType;
	}



	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	
}
