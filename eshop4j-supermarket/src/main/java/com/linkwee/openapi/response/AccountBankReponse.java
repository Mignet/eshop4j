package com.linkwee.openapi.response;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.linkwee.core.util.WebUtil;
import com.linkwee.web.model.acc.AcAccountBind;
/**
* 
* @描述： 实体Bean
* 
* @创建人： chenjl
* 
* @创建时间：2016年08月04日 17:10:52
* 
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class AccountBankReponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public AccountBankReponse(){
		
	}
	
	public AccountBankReponse(AcAccountBind obj){
		WebUtil.initObj(this,obj);
		this.setBankCard(obj.getBankCard());
		this.setMobile(obj.getReserveMobile());
		this.setIdcard(obj.getIdCard());
		this.setUserName(obj.getUserName());
		this.setBankCode(obj.getBankCode());
		this.setBankName(obj.getBankName());
	}
	
	/**
	 * 身份证
	 */
	private String idcard;
	
	/**
	 * 姓名
	 */
	private String userName;
	
	/**
	 * 手机
	 */
	private String mobile;
	
	/**
	 * 银行卡号
	 */
	private String bankCard;
	
	/**
	 * 银行code
	 */
	private String bankCode;
	
	/**
	 * 银行名称
	 */
	private String bankName;

	
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

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
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

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String toString()
	{
	  return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}