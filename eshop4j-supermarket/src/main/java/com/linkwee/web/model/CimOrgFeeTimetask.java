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
 * @创建时间：2016年10月11日 17:22:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrgFeeTimetask implements Serializable {
	
	private static final long serialVersionUID = 5864114694799346438L;
	
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
     *定时任务启动时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date taskStartTime;
	
	/**
     *机构新佣金率
     */
	private BigDecimal orgFeeRatio;
	
    /**
     *机构原佣金率
     */
	private BigDecimal orgOldFeeRatio;
	
    /**
     *定时任务类型(1:活动开始任务,2:活动结束任务)
     */
	private Integer taskType;
	
    /**
     *定时任务状态 (1,已触发待执行 2,执行完毕 3,出错)
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
	
	public void setTaskStartTime(Date taskStartTime){
		this.taskStartTime = taskStartTime;
	}
	
	public Date getTaskStartTime(){
		return taskStartTime;
	}
	
	public void setOrgFeeRatio(BigDecimal orgFeeRatio){
		this.orgFeeRatio = orgFeeRatio;
	}
	
	public BigDecimal getOrgFeeRatio(){
		return orgFeeRatio;
	}
	
	public void setTaskType(Integer taskType){
		this.taskType = taskType;
	}
	
	public Integer getTaskType(){
		return taskType;
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

