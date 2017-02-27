package com.linkwee.web.model.crm;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：客户详情
 *
 * @author Bob
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class CustomerDetailPageListResp extends BaseEntity {

	private static final long serialVersionUID = -8490369557398571885L;

	/**
	 * 发生时间
	 */
	private Date time;
	/**
	 * 交易类别
	 */
	private Integer tradeType;

	/**
	 * 金额(投资、赎回)
	 */
	private Double amt;

	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 年化
	 */
	private Double yearRate;
	/**
	 * 佣金利率
	 */
	private Double feeRate;
	/**
	 * 起息日期
	 */
	private Date startDate;
	/**
	 * 到期日期
	 */
	private Date endDate;

	/**
	 * 佣金
	 */
	private Double feeProfit;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getYearRate() {
		return yearRate;
	}

	public void setYearRate(Double yearRate) {
		this.yearRate = yearRate;
	}

	public Double getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(Double feeRate) {
		this.feeRate = feeRate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getFeeProfit() {
		return feeProfit;
	}

	public void setFeeProfit(Double feeProfit) {
		this.feeProfit = feeProfit;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
