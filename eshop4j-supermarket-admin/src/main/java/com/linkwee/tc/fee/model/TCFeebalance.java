package com.linkwee.tc.fee.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.lang.Byte;
import java.lang.Integer;
import java.lang.String;
import java.math.BigDecimal;
import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月05日 18:31:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class TCFeebalance implements Serializable {
	
	private static final long serialVersionUID = -1097527615746921892L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *结算编号(理财师编码+年月)
     */
	private String bizId;
	
    /**
     *理财师编码
     */
	private String userId;
	
    /**
     *理财师手机
     */
	private String userMobile;
	
    /**
     *理财师姓名
     */
	private String userName;
	
    /**
     *佣金
     */
	private BigDecimal feeAmount;
	
    /**
     *推荐用户数
     */
	private Integer recommendPeople;
	
    /**
     *推荐投资金额
     */
	private BigDecimal recommendInvest;
	
    /**
     *年月
     */
	private String month;
	
    /**
     *0：初始 1：审核
     */
	private Byte billStatus;
	
    /**
     *审核时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date auditTime;
	
    /**
     *0：正常 1：删除
     */
	private Byte delStatus;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setBizId(String bizId){
		this.bizId = bizId;
	}
	
	public String getBizId(){
		return bizId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setUserMobile(String userMobile){
		this.userMobile = userMobile;
	}
	
	public String getUserMobile(){
		return userMobile;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setFeeAmount(BigDecimal feeAmount){
		this.feeAmount = feeAmount;
	}
	
	public BigDecimal getFeeAmount(){
		return feeAmount;
	}
	
	public void setRecommendPeople(Integer recommendPeople){
		this.recommendPeople = recommendPeople;
	}
	
	public Integer getRecommendPeople(){
		return recommendPeople;
	}
	
	public void setRecommendInvest(BigDecimal recommendInvest){
		this.recommendInvest = recommendInvest;
	}
	
	public BigDecimal getRecommendInvest(){
		return recommendInvest;
	}
	
	public void setMonth(String month){
		this.month = month;
	}
	
	public String getMonth(){
		return month;
	}
	
	public void setBillStatus(Byte billStatus){
		this.billStatus = billStatus;
	}
	
	public Byte getBillStatus(){
		return billStatus;
	}
	
	public void setAuditTime(Date auditTime){
		this.auditTime = auditTime;
	}
	
	public Date getAuditTime(){
		return auditTime;
	}
	
	public void setDelStatus(Byte delStatus){
		this.delStatus = delStatus;
	}
	
	public Byte getDelStatus(){
		return delStatus;
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
	
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

