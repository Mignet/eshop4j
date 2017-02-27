package com.linkwee.api.response.acc;

import java.io.Serializable;
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
public class AccountMonthProfixResponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
     *总计
     */
	private String totalAmount;
	/**
     *月份描述
     */
	private String MonthDesc;
	/**
     *发放描述
     */
	private String grantDesc;
	/**
     *月份
     */
	private String Month;
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getMonthDesc() {
		return MonthDesc;
	}
	public void setMonthDesc(String monthDesc) {
		MonthDesc = monthDesc;
	}
	public String getGrantDesc() {
		return grantDesc;
	}
	public void setGrantDesc(String grantDesc) {
		this.grantDesc = grantDesc;
	}
	public String getMonth() {
		return Month;
	}
	public void setMonth(String month) {
		Month = month;
	}
	
	

}