package com.linkwee.web.response.act;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.base.BaseEntity;

public class RedpacketTemplateListResponse extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1321144167611526319L;
	
	/**
    *
    */
	private String redpacketTemplateId;
	
   /**
    *
    */
	private String name;
	
	
   /**
    *
    */
	private Integer expiresDay;
	
   /**
    *
    */
	private BigDecimal money;
	
	private BigDecimal repaymentAmt;
	
	private BigDecimal maxRepaymentAmt;
	
	private String productType;
	
   /**
    *
    */
	private Integer type;
	
  
	
   /**
    *
    */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date time;
	

	
   /**
    *
    */
	private String operator;



public String getRedpacketTemplateId() {
	return redpacketTemplateId;
}



public void setRedpacketTemplateId(String redpacketTemplateId) {
	this.redpacketTemplateId = redpacketTemplateId;
}



public String getName() {
	return name;
}



public void setName(String name) {
	this.name = name;
}






public Integer getExpiresDay() {
	return expiresDay;
}



public void setExpiresDay(Integer expiresDay) {
	this.expiresDay = expiresDay;
}



public BigDecimal getMoney() {
	return money;
}



public void setMoney(BigDecimal money) {
	this.money = money;
}



public Integer getType() {
	return type;
}



public void setType(Integer type) {
	this.type = type;
}






public BigDecimal getMaxRepaymentAmt() {
	return maxRepaymentAmt;
}



public void setMaxRepaymentAmt(BigDecimal maxRepaymentAmt) {
	this.maxRepaymentAmt = maxRepaymentAmt;
}



public String getProductType() {
	return ObjectUtils.equals(productType,0)?"固定":"浮动";
}



public void setProductType(String productType) {
	this.productType = productType;
}



public Date getTime() {
	return time;
}



public void setTime(Date time) {
	this.time = time;
}




public String getOperator() {
	return operator;
}



public void setOperator(String operator) {
	this.operator = operator;
}



public BigDecimal getRepaymentAmt() {
	return repaymentAmt;
}



public void setRepaymentAmt(BigDecimal repaymentAmt) {
	this.repaymentAmt = repaymentAmt;
}



	
}
