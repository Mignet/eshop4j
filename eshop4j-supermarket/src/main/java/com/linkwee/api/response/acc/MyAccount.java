package com.linkwee.api.response.acc;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
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
public class MyAccount implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
     *账户总金额
     */
	private String totalAmount;
	/**
     *用户类型
     */
    private String userType;
    /**
     *累计收益
     */
    private String totalIncome;
    
    
	public String getTotalIncome() {
		return totalIncome;
	}


	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public String getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}


	public String toString()
	{
	  return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}