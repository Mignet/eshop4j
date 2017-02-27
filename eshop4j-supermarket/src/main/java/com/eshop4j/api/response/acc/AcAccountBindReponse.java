package com.eshop4j.api.response.acc;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.eshop4j.core.util.WebUtil;
import com.eshop4j.web.model.acc.AcAccountBind;
/**
* 
* @描述： 实体Bean
* 
* @创建人： chenjl
* 
* @创建时间：2016年07月22日 17:10:52
* 
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class AcAccountBindReponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public AcAccountBindReponse(){
		
	}
	
	public AcAccountBindReponse(AcAccountBind obj){
		WebUtil.initObj(this,obj);
		this.setBankCard(obj.getBankCard());
		this.setBankName(obj.getBankName());
		this.setIdCard(obj.getIdCard());
		this.setUserName(obj.getUserName());
	}
	
	/**
	 * 银行卡号
	 */
	private String bankCard;
	
	/**
	 * 银行名称
	 */
	private String bankName;
	
	/**
	 * 真实姓名
	 */
	private String userName;
	
	/**
	 * 身份证号
	 */
	private String idCard;
	
	

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



	public String toString()
	{
	  return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}