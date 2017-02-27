package com.linkwee.plugins.huafu.entity;

import java.io.Serializable;
 /**
 * 
 * @描述： 实名验证
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年08月03日 14:35:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AcHeader implements Serializable {
	
	private static final long serialVersionUID = -6941427020761839097L;
	
    /**
     *查询批次号
     */
	private String qryBatchNo;
	
    /**
     *商户编号
     */
	private String userCode;
	
	/**
     *查询原因
     */
	private String qryReason;
	
	/**
     *查询日期
     */
	private String qryDate;
	
	/**
     *查询时间
     */
	private String qryTime;
	
	

	public String getQryBatchNo() {
		return qryBatchNo;
	}

	public void setQryBatchNo(String qryBatchNo) {
		this.qryBatchNo = qryBatchNo;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getQryReason() {
		return qryReason;
	}

	public void setQryReason(String qryReason) {
		this.qryReason = qryReason;
	}

	public String getQryDate() {
		return qryDate;
	}

	public void setQryDate(String qryDate) {
		this.qryDate = qryDate;
	}

	public String getQryTime() {
		return qryTime;
	}

	public void setQryTime(String qryTime) {
		this.qryTime = qryTime;
	}

}

