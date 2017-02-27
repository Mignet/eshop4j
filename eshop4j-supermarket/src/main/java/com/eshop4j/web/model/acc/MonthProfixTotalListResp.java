package com.eshop4j.web.model.acc;

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
public class MonthProfixTotalListResp implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
     *发放描述
     */
	private String grantDesc;
	/**
     *月份
     */
	private String month;
	/**
     *月份描述
     */
	private String monthDesc;
	/**
     *总计
     */
	private Double totalAmount;
	public String getGrantDesc() {
		return grantDesc;
	}
	public void setGrantDesc(String grantDesc) {
		this.grantDesc = grantDesc;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getMonthDesc() {
		return monthDesc;
	}
	public void setMonthDesc(String monthDesc) {
		this.monthDesc = monthDesc;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}