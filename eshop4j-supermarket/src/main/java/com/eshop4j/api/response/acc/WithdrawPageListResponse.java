package com.eshop4j.api.response.acc;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.eshop4j.core.util.NumberUtils;
import com.eshop4j.core.util.WebUtil;
import com.eshop4j.web.model.acc.AcBalanceRecord;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class WithdrawPageListResponse implements Serializable {
	
	private static final long serialVersionUID = -2979696234831682651L;
	
	public WithdrawPageListResponse(){
		
	}
	
	public WithdrawPageListResponse(AcBalanceRecord obj){
		WebUtil.initObj(this,obj);
		this.setTypeName("【佣金明细】");
		this.setRemark(obj.getRemark());
		this.setTransAmount(NumberUtils.getFormat(obj.getTransAmount(), "0.00"));
		this.setTransDate(obj.getTransDate());
	}
	
	/**
     * 交易类型
     */
	private String typeName;
  
	
    /**
     * 交易金额
     */
	private String transAmount;

	
    /**
     * 交易日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date transDate;
 
	
    /**
     * 备注
     */
	private String remark;


	public String getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(String transAmount) {
		this.transAmount = transAmount;
	}

	public void setTransDate(Date transDate){
		this.transDate = transDate;
	}
	
	public Date getTransDate(){
		return transDate;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}

