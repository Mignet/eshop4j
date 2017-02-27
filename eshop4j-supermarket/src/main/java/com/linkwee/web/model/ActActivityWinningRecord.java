package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
 * @创建时间：2016年12月11日 16:31:47
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActActivityWinningRecord implements Serializable {
	
	private static final long serialVersionUID = 2619555499522523977L;
	
    /**
     *中奖记录流水号
     */
	private Integer id;
	
    /**
     *序列号
     */
	private String recordId;
	
    /**
     *活动编号
     */
	private String activityId;
	
    /**
     *用户编码
     */
	private String userId;
	
    /**
     *中奖投资人手机号码
     */
	private String mobile;
	
    /**
     *条件等级
     */
	private Integer conditionCase;
	
    /**
     *中奖等级
     */
	private Integer prizeCase;
	
    /**
     *等级描述
     */
	private String orderDesc;
	
    /**
     *消耗的抽奖次数
     */
	private Integer wasteTimes;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date crtTime;
	
    /**
     *发放时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date issuedTime;
	
    /**
     *发放人
     */
	private String creator;
	
    /**
     *是否发放（0：未发放，1：已发放）
     */
	private Integer issued;
	
    /**
     *奖品类型(抽一次，抽十次)
     */
	private Integer winningType;
	
    /**
     *中奖金额
     */
	private BigDecimal winningAmt;
	
    /**
     *排行数据
     */
	private String orderData;
	
    /**
     *
     */
	private String extends1;
	
    /**
     *
     */
	private String extends2;
	
    /**
     *
     */
	private String extends3;
	
    /**
     *奖品抽取方式（抽一次，抽十次）
     */
	private Integer prizeStyle;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setRecordId(String recordId){
		this.recordId = recordId;
	}
	
	public String getRecordId(){
		return recordId;
	}
	
	public void setActivityId(String activityId){
		this.activityId = activityId;
	}
	
	public String getActivityId(){
		return activityId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setConditionCase(Integer conditionCase){
		this.conditionCase = conditionCase;
	}
	
	public Integer getConditionCase(){
		return conditionCase;
	}
	
	public void setPrizeCase(Integer prizeCase){
		this.prizeCase = prizeCase;
	}
	
	public Integer getPrizeCase(){
		return prizeCase;
	}
	
	public void setOrderDesc(String orderDesc){
		this.orderDesc = orderDesc;
	}
	
	public String getOrderDesc(){
		return orderDesc;
	}
	
	public void setWasteTimes(Integer wasteTimes){
		this.wasteTimes = wasteTimes;
	}
	
	public Integer getWasteTimes(){
		return wasteTimes;
	}
	
	public void setCrtTime(Date crtTime){
		this.crtTime = crtTime;
	}
	
	public Date getCrtTime(){
		return crtTime;
	}
	
	public void setIssuedTime(Date issuedTime){
		this.issuedTime = issuedTime;
	}
	
	public Date getIssuedTime(){
		return issuedTime;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	
	public String getCreator(){
		return creator;
	}
	
	public void setIssued(Integer issued){
		this.issued = issued;
	}
	
	public Integer getIssued(){
		return issued;
	}
	
	public void setWinningType(Integer winningType){
		this.winningType = winningType;
	}
	
	public Integer getWinningType(){
		return winningType;
	}
	
	public void setWinningAmt(BigDecimal winningAmt){
		this.winningAmt = winningAmt;
	}
	
	public BigDecimal getWinningAmt(){
		return winningAmt;
	}
	
	public void setOrderData(String orderData){
		this.orderData = orderData;
	}
	
	public String getOrderData(){
		return orderData;
	}
	
	public void setExtends1(String extends1){
		this.extends1 = extends1;
	}
	
	public String getExtends1(){
		return extends1;
	}
	
	public void setExtends2(String extends2){
		this.extends2 = extends2;
	}
	
	public String getExtends2(){
		return extends2;
	}
	
	public void setExtends3(String extends3){
		this.extends3 = extends3;
	}
	
	public String getExtends3(){
		return extends3;
	}
	
	public void setPrizeStyle(Integer prizeStyle){
		this.prizeStyle = prizeStyle;
	}
	
	public Integer getPrizeStyle(){
		return prizeStyle;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

