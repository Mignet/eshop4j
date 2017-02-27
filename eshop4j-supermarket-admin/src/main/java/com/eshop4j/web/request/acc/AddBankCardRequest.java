package com.eshop4j.web.request.acc;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class AddBankCardRequest {

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
	@NotNull(message = "银行Code不能为空")
	private String bankCode;
	
	/**
	 * 银行Id
	 */
	@NotNull(message = "银行Id不能为空")
	private int bankId;
	
	/**
	 * 真实姓名
	 */
	@NotNull(message = "真实姓名不能为空")
	private String userName;
	
	/**
	 * 身份证号
	 */
	@NotNull(message = "身份证号不能为空")
	private String idCard;
	
	/**
	 * 城市
	 */
	private String city;
	
	/**
	 * 开户行
	 */
	private String kaihuhang;
	
	/**
	 * 用户ID
	 */
    private String userId;
    
    /**
	 * 用户银行预留手机号码
	 */
    @NotNull(message = "手机号码不能为空")
    private String mobile;
    
    /**
   	 * 1理财师，2投资者
   	 */
    private int userType;
    
    /**
   	 * 签名
   	 */
    private String sign;
    
    /**
     *银行预留手机号码
     */
	private String reserveMobile;
    

	public String getReserveMobile() {
		return reserveMobile;
	}




	public void setReserveMobile(String reserveMobile) {
		this.reserveMobile = reserveMobile;
	}




	public int getBankId() {
		return bankId;
	}




	public void setBankId(int bankId) {
		this.bankId = bankId;
	}




	public String getSign() {
		return sign;
	}




	public void setSign(String sign) {
		this.sign = sign;
	}




	public int getUserType() {
		return userType;
	}




	public void setUserType(int userType) {
		this.userType = userType;
	}




	public String getMobile() {
		return mobile;
	}




	public void setMobile(String mobile) {
		this.mobile = mobile;
	}




	public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
		this.userId = userId;
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



	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	
}
