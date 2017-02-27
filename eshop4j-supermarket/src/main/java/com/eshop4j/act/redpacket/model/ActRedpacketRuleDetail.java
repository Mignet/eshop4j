package com.eshop4j.act.redpacket.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Boolean;
 import java.lang.Integer;
 import java.lang.String;
 import java.math.BigDecimal;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年08月19日 15:52:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActRedpacketRuleDetail implements Serializable {
	
	private static final long serialVersionUID = -8555010219597816915L;
	
    /**
     *
     */
	private Integer id;
	
	private String redpacketId;
	
    /**
     *规则编号
     */
	private String ruleId;
	
    /**
     *平台限制 1=限制|0=不限制
     */
	private Boolean platformLimit;
	
    /**
     *平台编号 platform_limit=1时有效
     */
	private String platformId;
	
	/**
     *平台名称 platform_limit=1时有效
     */
	private String platformName;
	
    /**
     *产品限制 1000=不限|1001=限制产品编号|1002=限制产品期限|1003=限制产品类型
     */
	private Integer productLimit;
	
    /**
     *产品编号 product_limit=1001时有效
     */
	private String productId;
	
    /**
     *产品期限 product_limit=1002时有效
     */
	private Integer productDeadline;
	
    /**
     *产品类型 product_limit=1003时有效
     */
	private Integer productType;
	
	/**
	 * 用户投资限制 0=不限|1=用户首投|2=平台首投
	 */
	private Integer investLlimit;
	
    /**
     *金额限制 0=不限|1=大于|2=大于等于
     */
	private Integer amountLimit;
	
    /**
     *金额
     */
	private BigDecimal amount;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
    /**
     *
     */
	private String operator;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setRuleId(String ruleId){
		this.ruleId = ruleId;
	}
	
	public String getRuleId(){
		return ruleId;
	}
	
	
	public String getRedpacketId() {
		return redpacketId;
	}

	public void setRedpacketId(String redpacketId) {
		this.redpacketId = redpacketId;
	}

	public void setPlatformLimit(Boolean platformLimit){
		this.platformLimit = platformLimit;
	}
	
	public Boolean isPlatformLimit(){
		return platformLimit;
	}
	
	public void setPlatformId(String platformId){
		this.platformId = platformId;
	}
	
	public String getPlatformId(){
		return platformId;
	}
	
	public void setProductLimit(Integer productLimit){
		this.productLimit = productLimit;
	}
	
	public Integer getProductLimit(){
		return productLimit;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setProductDeadline(Integer productDeadline){
		this.productDeadline = productDeadline;
	}
	
	public Integer getProductDeadline(){
		return productDeadline;
	}
	
	public void setProductType(Integer productType){
		this.productType = productType;
	}
	
	public Integer getProductType(){
		return productType;
	}
	
	
	public Integer getInvestLlimit() {
		return investLlimit;
	}

	public void setInvestLlimit(Integer investLlimit) {
		this.investLlimit = investLlimit;
	}

	public void setAmountLimit(Integer amountLimit){
		this.amountLimit = amountLimit;
	}
	
	public Integer getAmountLimit(){
		return amountLimit;
	}
	
	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}
	
	public BigDecimal getAmount(){
		return amount;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
	public void setOperator(String operator){
		this.operator = operator;
	}
	
	public String getOperator(){
		return operator;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	
	
}

