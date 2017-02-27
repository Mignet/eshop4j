package com.eshop4j.web.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.xoss.util.BigDecimalUtil;

public class CustomerInvestDetail extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4404712741069525151L;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 浮动最小
	 */
	private Double flowMinRate;
	/**
	 * 浮动最大
	 */
	private Double flowMaxRate;
	/**
	 * 是否浮动
	 */
	private Integer isflow;
	/**
	 * 投资金额
	 */
	private BigDecimal investAmt;
	
	/**
	 * 收益
	 */
	private BigDecimal profit;
	
	/**
	 * 起息时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date  endTime;
	
	private Integer status;
	
	private BigDecimal feeAmt;
	

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getRate() {
		if( isflow==null) return null;
		return (ObjectUtils.equals(1, isflow))?
			(new BigDecimal(flowMinRate).setScale(2,BigDecimal.ROUND_DOWN).toString()+"%".intern())
		: 
			StringUtils.join(new Object[]{new BigDecimal(flowMinRate).setScale(2,BigDecimal.ROUND_DOWN).toString()+"%".intern(),new BigDecimal(flowMaxRate).setScale(2,BigDecimal.ROUND_DOWN).toString()+"%".intern()},"~".intern());
	}
	
	public void setFlowMinRate(Double flowMinRate) {
		this.flowMinRate = flowMinRate;
	}

	public void setFlowMaxRate(Double flowMaxRate) {
		this.flowMaxRate = flowMaxRate;
	}

	

	public void setIsflow(Integer isflow) {
		this.isflow = isflow;
	}

	public BigDecimal getInvestAmt() {
		return BigDecimalUtil.divide(investAmt, 10000).setScale(4,BigDecimal.ROUND_DOWN);
	}

	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}

	public BigDecimal getProfit() {
		return profit.setScale(4,BigDecimal.ROUND_DOWN);
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		if(ObjectUtils.equals(status, 1))return "投资中";
		else if(ObjectUtils.equals(status, 2))return "回款中";
		else if(ObjectUtils.equals(status, 3))return "回款完成";
		else if(ObjectUtils.equals(status, 4))return "提前赎回部分本金";
		return "";
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getFeeAmt() {
		return feeAmt==null ? new BigDecimal(0) : feeAmt.setScale(4,BigDecimal.ROUND_DOWN);
	}

	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}
	
}
