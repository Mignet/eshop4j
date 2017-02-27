package com.eshop4j.web.model.acc;

import java.io.Serializable;
import java.math.BigDecimal;
 /**
 * 
 * @描述： 给用户账户充值实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月25日 10:14:37
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AcAccountRecharge implements Serializable {
	
	private static final long serialVersionUID = -7091615727957695237L;
	
	 /**
     *1理财师，2投资者
     */
	private Integer userType;
	
    /**
     *客户id
     */
	private String userId;
	
	/**
     *红包ID
     */
	private String redpacketId;
	
    /**
     * 交易类型(1=全部明细|2=提现|3=活动奖励|4=红包|5=佣金|6=其他)
     */
	private Integer transType;
	
    /**
     * 交易金额
     */
	private BigDecimal transAmount;

    /**
     *备注
     */
	private String remark;


	public Integer getUserType() {
		return userType;
	}


	public void setUserType(Integer userType) {
		this.userType = userType;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Integer getTransType() {
		return transType;
	}


	public void setTransType(Integer transType) {
		this.transType = transType;
	}


	public String getRedpacketId() {
		return redpacketId;
	}


	public void setRedpacketId(String redpacketId) {
		this.redpacketId = redpacketId;
	}


	public BigDecimal getTransAmount() {
		return transAmount;
	}


	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}

	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}

