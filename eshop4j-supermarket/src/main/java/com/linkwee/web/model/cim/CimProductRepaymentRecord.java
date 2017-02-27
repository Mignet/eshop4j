package com.linkwee.web.model.cim;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
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
 * @创建时间：2016年07月27日 17:36:05
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimProductRepaymentRecord implements Serializable {
	
	private static final long serialVersionUID = -6249940792731316983L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *回款编号
     */
	private String repaymentId;
	
    /**
     *用户编号
     */
	private String userId;
	
    /**
     *投资编号
     */
	private String investRecordId;
	
    /**
     *产品ID
     */
	private String productId;
	
    /**
     *外部产品编号
     */
	private String thirdProductId;
	
    /**
     *还款日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date repaymentTime;
	
    /**
     *本金
     */
	private BigDecimal repaymentAmount;
	
    /**
     *精准收益
     */
	private BigDecimal profit;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *回款状态（2=回款中|3=回款成功）
     */
	private Integer status;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setRepaymentId(String repaymentId){
		this.repaymentId = repaymentId;
	}
	
	public String getRepaymentId(){
		return repaymentId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setInvestRecordId(String investRecordId){
		this.investRecordId = investRecordId;
	}
	
	public String getInvestRecordId(){
		return investRecordId;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setThirdProductId(String thirdProductId){
		this.thirdProductId = thirdProductId;
	}
	
	public String getThirdProductId(){
		return thirdProductId;
	}
	
	public void setRepaymentTime(Date repaymentTime){
		this.repaymentTime = repaymentTime;
	}
	
	public Date getRepaymentTime(){
		return repaymentTime;
	}
	
	public BigDecimal getRepaymentAmount() {
		return repaymentAmount;
	}

	public void setRepaymentAmount(BigDecimal repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public void setProfit(BigDecimal profit){
		this.profit = profit;
	}
	
	public BigDecimal getProfit(){
		return profit;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

