package com.linkwee.api.response.acc;

import java.io.Serializable;

import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.core.util.WebUtil;
import com.linkwee.web.model.acc.AcBalanceRecord;
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
public class MyAccountPageListResponse implements Serializable {
	
	private static final long serialVersionUID = -2979696234831682651L;
	
	public MyAccountPageListResponse(){
		
	}
	
	public MyAccountPageListResponse(AcBalanceRecord obj){
		WebUtil.initObj(this,obj);
		this.setTypeName(obj.getTypeName());
		this.setRemark(obj.getRemark());
		//交易金额前面的加减符合
		String symbol = obj.getTransType()==2||obj.getTransType()==16?"-":"+";
		this.setTransAmount(symbol+NumberUtils.getFormat(obj.getTransAmount(), "0.00"));
		this.setTransDate(DateUtils.format(obj.getTransDate(),DateUtils.FORMAT_LONG));
		this.setFee(NumberUtils.getFormat(obj.getFee(), "0.00"));
		this.setTypeValue(obj.getTransType().toString());
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
     * 手续费
     */
	private String fee;

	
    /**
     * 交易日期
     */
	private String transDate;
 
	
    /**
     * 备注
     */
	private String remark;

	/**
     * 交易类型值
     */
	private String typeValue;

	
	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(String transAmount) {
		this.transAmount = transAmount;
	}

	public void setTransDate(String transDate){
		this.transDate = transDate;
	}
	
	public String getTransDate(){
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

