package com.eshop4j.api.response.acc;

import java.io.Serializable;

import com.eshop4j.web.model.acc.ProfixTypeListResp;
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
public class ProfixTypeListRespsone implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public ProfixTypeListRespsone() {
	}
	public ProfixTypeListRespsone(ProfixTypeListResp obj) {
		WebUtil.initObj(this, obj);
	}
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
	private String amount;
	
	
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}