package com.linkwee.web.model.crm;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * @描述： 理财师佣金明细列表
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月07日 10:42:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActivityRewardListResp extends BaseEntity {
	
	private static final long serialVersionUID = 3625951331734709011L;
	
    /**
     * 自增长主键
     */
	private Integer id;
	/**
     * 活动名称
     */
	private String activityName;
	/**
     * 奖励时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date createTime;
    /**
     * 奖励金额
     */
	private BigDecimal amount;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}

