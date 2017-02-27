package com.linkwee.act.redpacket.model;



import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.Long;
 import java.lang.String;
 import java.math.BigDecimal;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年12月22日 20:27:29
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActRedpacketTemplate implements Serializable {
	
	private static final long serialVersionUID = 7346161636920895092L;
	
    /**
     *
     */
	private Integer id;
	
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date expiresTime;
	
    /**
     *
     */
	private Integer expiresDay;
	
    /**
     *
     */
	private Long money;
	
    /**
     *
     */
	private String remark;
	
    /**
     *
     */
	private Integer type;
	
    /**
     *
     */
	private Integer platformLimit;
	
    /**
     *
     */
	private String platformId;
	
    /**
     *
     */
	private Integer productLimit;
	
    /**
     *
     */
	private String productId;
	
    /**
     *
     */
	private Integer productDeadline;
	
    /**
     *
     */
	private Integer productType;
	
    /**
     *
     */
	private Integer investLimit;
	
    /**
     *
     */
	private Integer amountLimit;
	
    /**
     *
     */
	private BigDecimal amount;
	
	private BigDecimal repaymentAmt;
	private BigDecimal maxRepaymentAmt;
	
    /**
     *
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
    /**
     *
     */
	private String operator;
	


	public BigDecimal getMaxRepaymentAmt() {
		return maxRepaymentAmt;
	}

	public void setMaxRepaymentAmt(BigDecimal maxRepaymentAmt) {
		this.maxRepaymentAmt = maxRepaymentAmt;
	}

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setRedpacketTemplateId(String redpacketTemplateId){
		this.redpacketTemplateId = redpacketTemplateId;
	}
	
	public String getRedpacketTemplateId(){
		return redpacketTemplateId;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setExpiresTime(Date expiresTime){
		this.expiresTime = expiresTime;
	}
	
	public Date getExpiresTime(){
		return expiresTime;
	}
	
	public void setExpiresDay(Integer expiresDay){
		this.expiresDay = expiresDay;
	}
	
	public Integer getExpiresDay(){
		return expiresDay;
	}
	
	public void setMoney(Long money){
		this.money = money;
	}
	
	public Long getMoney(){
		return money;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return type;
	}
	
	public void setPlatformLimit(Integer platformLimit){
		this.platformLimit = platformLimit;
	}
	
	public Integer getPlatformLimit(){
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
	
	public void setInvestLimit(Integer investLimit){
		this.investLimit = investLimit;
	}
	
	public Integer getInvestLimit(){
		return investLimit;
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
	
	
	
	public BigDecimal getRepaymentAmt() {
		return repaymentAmt;
	}

	public void setRepaymentAmt(BigDecimal repaymentAmt) {
		this.repaymentAmt = repaymentAmt;
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
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}


