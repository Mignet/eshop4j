package com.eshop4j.api.response.acc;

import java.io.Serializable;

import com.eshop4j.web.model.acc.MonthProfixTotalListResp;
import com.eshop4j.xoss.util.WebUtil;
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
public class MonthProfixTotalListResponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	public MonthProfixTotalListResponse() {
	}
	
	public MonthProfixTotalListResponse(MonthProfixTotalListResp obj) {
		WebUtil.initObj(this, obj);
	}
	
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
	private String totalAmount;
	
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
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}