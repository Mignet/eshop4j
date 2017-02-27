package com.eshop4j.web.request;

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
 * @创建时间：2016年10月11日 17:22:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrgFeeTimetaskRequest implements Serializable {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4572702416622883463L;

	/**
     *主键
     */
	private Integer fid;
	
    /**
     *机构编码
     */
	private String orgNumber;
	
    /**
     *机构名称
     */
	private String orgName;
	
    /**
     *活动开始定时任务启动时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date activityStartTaskTime;
	
	/**
     *活动结束定时任务启动时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date activityEndTaskTime;
	
	/**
     *机构原佣金率
     */
	private BigDecimal orgOldFeeRatio;
	
	/**
	 *活动开始定时任务设置的机构新佣金率
	 */
	private BigDecimal activityStartOrgFeeRatio;
	
	/**
	 *活动结束定时任务设置的机构新佣金率
	 */
	private BigDecimal activityEndOrgFeeRatio;
	
    /**
     *活动开始定时任务类型(1:活动开始任务,2:活动结束任务)
     */
	private Integer activityStartTaskType;
	
	/**
     *活动结束定时任务类型(1:活动开始任务,2:活动结束任务)
     */
	private Integer activityEndTaskType;
	
    /**
     *定时任务状态 (1,已触发待执行 2,执行中 3,执行完毕 4,出错)
     */
	private Integer taskStatus;
	
    /**
     *佣金设置定时任务创建原因
     */
	private String taskCreateReason;
	
    /**
     *创建人
     */
	private String creater;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *更新人
     */
	private String updater;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
    /**
     *备注
     */
	private String remark;
	
	
	


	public BigDecimal getOrgOldFeeRatio() {
		return orgOldFeeRatio;
	}

	public void setOrgOldFeeRatio(BigDecimal orgOldFeeRatio) {
		this.orgOldFeeRatio = orgOldFeeRatio;
	}

	public void setFid(Integer fid){
		this.fid = fid;
	}
	
	public Integer getFid(){
		return fid;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setOrgName(String orgName){
		this.orgName = orgName;
	}
	
	public String getOrgName(){
		return orgName;
	}
	
	public Date getActivityStartTaskTime() {
		return activityStartTaskTime;
	}

	public void setActivityStartTaskTime(Date activityStartTaskTime) {
		this.activityStartTaskTime = activityStartTaskTime;
	}

	public Date getActivityEndTaskTime() {
		return activityEndTaskTime;
	}

	public void setActivityEndTaskTime(Date activityEndTaskTime) {
		this.activityEndTaskTime = activityEndTaskTime;
	}

	public BigDecimal getActivityStartOrgFeeRatio() {
		return activityStartOrgFeeRatio;
	}

	public void setActivityStartOrgFeeRatio(BigDecimal activityStartOrgFeeRatio) {
		this.activityStartOrgFeeRatio = activityStartOrgFeeRatio;
	}

	public BigDecimal getActivityEndOrgFeeRatio() {
		return activityEndOrgFeeRatio;
	}

	public void setActivityEndOrgFeeRatio(BigDecimal activityEndOrgFeeRatio) {
		this.activityEndOrgFeeRatio = activityEndOrgFeeRatio;
	}


	public Integer getActivityStartTaskType() {
		return activityStartTaskType;
	}

	public void setActivityStartTaskType(Integer activityStartTaskType) {
		this.activityStartTaskType = activityStartTaskType;
	}

	public Integer getActivityEndTaskType() {
		return activityEndTaskType;
	}

	public void setActivityEndTaskType(Integer activityEndTaskType) {
		this.activityEndTaskType = activityEndTaskType;
	}

	public void setTaskStatus(Integer taskStatus){
		this.taskStatus = taskStatus;
	}
	
	public Integer getTaskStatus(){
		return taskStatus;
	}
	
	public void setTaskCreateReason(String taskCreateReason){
		this.taskCreateReason = taskCreateReason;
	}
	
	public String getTaskCreateReason(){
		return taskCreateReason;
	}
	
	public void setCreater(String creater){
		this.creater = creater;
	}
	
	public String getCreater(){
		return creater;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setUpdater(String updater){
		this.updater = updater;
	}
	
	public String getUpdater(){
		return updater;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

