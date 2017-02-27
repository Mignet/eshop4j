package com.eshop4j.web.model.cim;

import java.io.Serializable;
import java.math.BigDecimal;
 /**
 * 
 * @描述： 收费区间
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月09日 18:26:40
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrgFeeInterval implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7463909585885873092L;
	
	/**
	 * 主键id
	 */
	private Integer feeRecordId;

	/**
     *区间收费额度
     */
	private BigDecimal feeVal;
	
	/**
	 *区间收费比例
	 */
	private BigDecimal moneyRatio;
	
    /**
     *区间最小值
     */
	private BigDecimal intervalMinVal;
	
    /**
     *区间最大值
     */
	private BigDecimal intervalMaxVal;
	
    /**
     *收费区间单位,首投金额元,产品期限天,月销售额万
     */
	private String intervalUnit;
	
	
	
	public Integer getFeeRecordId() {
		return feeRecordId;
	}

	public void setFeeRecordId(Integer feeRecordId) {
		this.feeRecordId = feeRecordId;
	}

	public BigDecimal getMoneyRatio() {
		return moneyRatio;
	}

	public void setMoneyRatio(BigDecimal moneyRatio) {
		this.moneyRatio = moneyRatio;
	}

	public BigDecimal getFeeVal() {
		return feeVal;
	}

	public void setFeeVal(BigDecimal feeVal) {
		this.feeVal = feeVal;
	}

	public BigDecimal getIntervalMinVal() {
		return intervalMinVal;
	}

	public void setIntervalMinVal(BigDecimal intervalMinVal) {
		this.intervalMinVal = intervalMinVal;
	}

	public BigDecimal getIntervalMaxVal() {
		return intervalMaxVal;
	}

	public void setIntervalMaxVal(BigDecimal intervalMaxVal) {
		this.intervalMaxVal = intervalMaxVal;
	}

	public String getIntervalUnit() {
		return intervalUnit;
	}

	public void setIntervalUnit(String intervalUnit) {
		this.intervalUnit = intervalUnit;
	}
	
   
	
}

