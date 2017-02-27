package com.linkwee.web.model.acc;

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
public class ProfixTypeListResp implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
     *月份
     */
	private String profixTypeName;
	/**
     *月份描述
     */
	private String profixType;
	/**
     *总计
     */
	private Double amount;
	public String getProfixTypeName() {
		return profixTypeName;
	}
	public void setProfixTypeName(String profixTypeName) {
		this.profixTypeName = profixTypeName;
	}
	public String getProfixType() {
		return profixType;
	}
	public void setProfixType(String profixType) {
		this.profixType = profixType;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}