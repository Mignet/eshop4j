package com.eshop4j.web.model.acc;

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
 * @创建时间：2017年01月05日 14:11:49
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AcOfflineRewardDraft implements Serializable {
	
	private static final long serialVersionUID = -6030762731198255946L;
	
    /**
     *自增id
     */
	private Integer id;
	
    /**
     *1理财师，2投资者
     */
	private Integer userType;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *交易类型
     */
	private Integer transType;
	
    /**
     *交易金额
     */
	private BigDecimal transAmount;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *最后更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date lastUpdateTime;
	
    /**
     *创建人
     */
	private String createPerson;
	
    /**
     *最后更新人
     */
	private String lastUpdatePerson;
	
    /**
     *备注
     */
	private String remark;
	
    /**
     *批次
     */
	private String batch;
	
    /**
     *状态 0未发放，1已发放
     */
	private Integer status;
	/**
	 * 奖励时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")   
	private Date rewardTime;
	/**
	 * 发放时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date grantTime;
	
	private String userName;
	
	private String mobile;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setUserType(Integer userType){
		this.userType = userType;
	}
	
	public Integer getUserType(){
		return userType;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setTransType(Integer transType){
		this.transType = transType;
	}
	
	public Integer getTransType(){
		return transType;
	}
	
	public void setTransAmount(BigDecimal transAmount){
		this.transAmount = transAmount;
	}
	
	public BigDecimal getTransAmount(){
		return transAmount;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setLastUpdateTime(Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public Date getLastUpdateTime(){
		return lastUpdateTime;
	}
	
	public void setCreatePerson(String createPerson){
		this.createPerson = createPerson;
	}
	
	public String getCreatePerson(){
		return createPerson;
	}
	
	public void setLastUpdatePerson(String lastUpdatePerson){
		this.lastUpdatePerson = lastUpdatePerson;
	}
	
	public String getLastUpdatePerson(){
		return lastUpdatePerson;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setBatch(String batch){
		this.batch = batch;
	}
	
	public String getBatch(){
		return batch;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getRewardTime() {
		return rewardTime;
	}

	public void setRewardTime(Date rewardTime) {
		this.rewardTime = rewardTime;
	}

	public Date getGrantTime() {
		return grantTime;
	}

	public void setGrantTime(Date grantTime) {
		this.grantTime = grantTime;
	}
}

