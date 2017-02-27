package com.eshop4j.web.model.crm;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.web.model.Product;

/**
 * 
 * @描述：交易动态
 *
 * @author Bob
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class CustomerTradelistResp extends Product {

	private static final long serialVersionUID = -5142804811486368481L;

	/**
	 * 交易类别
	 */
	private Integer tradeType;

	/**
	 * 发生时间
	 */
	private Date time;

	/**
	 * 客户预收益
	 */
	private Double profit;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 客户手机号码
	 */
	private String customerMobile;

	/**
	 * 金额(投资、赎回)
	 */
	private Double amt;

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
	/**
	 * 是否已读(true已读，false未读)
	 */
	private Boolean readFlag;

	/**
	 * 投资笔数
	 */
	private Integer investCount;
	/**
	 * 客户来源：领会投资端|钱罐子|新财富
	 */
	private String regbizfrom;

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
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

	public Boolean getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(Boolean readFlag) {
		this.readFlag = readFlag;
	}

	public Integer getInvestCount() {
		return investCount;
	}

	public void setInvestCount(Integer investCount) {
		this.investCount = investCount;
	}

	public String getRegbizfrom() {
		return regbizfrom;
	}

	public void setRegbizfrom(String regbizfrom) {
		this.regbizfrom = regbizfrom;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
