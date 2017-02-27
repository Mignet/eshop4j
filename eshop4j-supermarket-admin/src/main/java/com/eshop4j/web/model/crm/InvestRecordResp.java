package com.eshop4j.web.model.crm;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.StringUtils;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月07日 10:42:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class InvestRecordResp extends BaseEntity {
	
	private static final long serialVersionUID = 3625951331734709011L;
	
    /**
     *自增长主键
     */
	private Integer id;
	/**
     *产品名称
     */
	private String productName;
	/**
     *机构
     */
	private String platfrom;
	/**
     *机构名称
     */
	private String platfromName;
	/**
     *销售收入
     */
	private String saleProfix;
	  /**
     *购买本金
     */
	private BigDecimal investAmt;
    /**
     *收益
     */
	private BigDecimal profit;
    /**
     *业务日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date bizTime;
    /**
     *计息日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date startTime;
	
    /**
     *回款日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date endTime;
  
    /**
     *状态(1=初始申请|2=投资成功|3=回款成功)
     */
	private int status;
	
	/**
	 *  浮动最大利率 
	 */
	private double flowMaxRate;
	/**
	 *  浮动最小利率 
	 */
	private double flowMinRate;
	/**
	 * 1固定利率；2浮动利率 
	 */
	private Integer isFlow;
	
	 /**
     *是否固定期限(1=固定期限|2=浮动期限)
     */
	private Integer isFixedDeadline;
    
    /**
     *产品最小期限天数
     */
	private Integer deadLineMinValue;
	
    /**
     *产品最大期限天数
     */
	private Integer deadLineMaxValue;
	
    /**
     *产品最小期限天数 自定义显示
     */
	private String deadLineMinSelfDefined;
	
    /**
     *产品最大期限天数 自定义显示
     */
	private String deadLineMaxSelfDefined;
	/**
	 * 产品期限
	 */
	private String deadLine;
	/**
	 * 产品利率Text
	 */
	private String rate;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPlatfrom() {
		return platfrom;
	}

	public void setPlatfrom(String platfrom) {
		this.platfrom = platfrom;
	}

	public String getPlatfromName() {
		return platfromName;
	}

	public void setPlatfromName(String platfromName) {
		this.platfromName = platfromName;
	}

	public String getSaleProfix() {
		return saleProfix;
	}

	public void setSaleProfix(String saleProfix) {
		this.saleProfix = saleProfix;
	}

	public BigDecimal getInvestAmt() {
		return investAmt;
	}

	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public Date getBizTime() {
		return bizTime;
	}

	public void setBizTime(Date bizTime) {
		this.bizTime = bizTime;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getFlowMaxRate() {
		return flowMaxRate;
	}

	public void setFlowMaxRate(double flowMaxRate) {
		this.flowMaxRate = flowMaxRate;
	}

	public double getFlowMinRate() {
		return flowMinRate;
	}

	public void setFlowMinRate(double flowMinRate) {
		this.flowMinRate = flowMinRate;
	}

	public Integer getIsFlow() {
		return isFlow;
	}

	public void setIsFlow(Integer isFlow) {
		this.isFlow = isFlow;
	}

	public Integer getIsFixedDeadline() {
		return isFixedDeadline;
	}

	public void setIsFixedDeadline(Integer isFixedDeadline) {
		this.isFixedDeadline = isFixedDeadline;
	}

	public Integer getDeadLineMinValue() {
		return deadLineMinValue;
	}

	public void setDeadLineMinValue(Integer deadLineMinValue) {
		this.deadLineMinValue = deadLineMinValue;
	}

	public Integer getDeadLineMaxValue() {
		return deadLineMaxValue;
	}

	public void setDeadLineMaxValue(Integer deadLineMaxValue) {
		this.deadLineMaxValue = deadLineMaxValue;
	}

	public String getDeadLineMinSelfDefined() {
		return deadLineMinSelfDefined;
	}

	public void setDeadLineMinSelfDefined(String deadLineMinSelfDefined) {
		this.deadLineMinSelfDefined = deadLineMinSelfDefined;
	}

	public String getDeadLineMaxSelfDefined() {
		return deadLineMaxSelfDefined;
	}

	public void setDeadLineMaxSelfDefined(String deadLineMaxSelfDefined) {
		this.deadLineMaxSelfDefined = deadLineMaxSelfDefined;
	}

	public String getDeadLine() {
		deadLine = "-";
		if (isFixedDeadline != null && isFixedDeadline == 1){
			if(StringUtils.isNotBlank(deadLineMinSelfDefined)){
				deadLine = deadLineMinSelfDefined;
			} else {
				deadLine = deadLineMinValue+"天";
			}
		} else {
			if(StringUtils.isNotBlank(deadLineMinSelfDefined) && StringUtils.isNotBlank(deadLineMaxSelfDefined)){
				deadLine = deadLineMinSelfDefined+"~"+deadLineMaxSelfDefined;
			} else {
				deadLine = deadLineMinValue+"天~"+deadLineMaxValue+"天";
			}
		}
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	public String getRate() {
		rate = "-";
		if(isFlow != null && isFlow == 1){
			rate = flowMinRate+"%";
		} else if(isFlow != null && isFlow == 2){
			rate = flowMinRate+"%~"+flowMaxRate+"%";
		}
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
	
}

