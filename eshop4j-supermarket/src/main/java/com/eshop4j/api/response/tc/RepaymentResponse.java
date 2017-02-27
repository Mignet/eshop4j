package com.eshop4j.api.response.tc;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.DateUtils;

public class RepaymentResponse extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1163898858421616004L;
	
	/**
	 * 用户手机
	 */
	private String mobile;
	/**
	 * 用户名称
	 */
	private String name;
	/**
	 * 头像
	 */
	private String image;
	/**
	 * 平台名称
	 */
	private String platfrom;
	/**
	 * 产品名称
	 */
	private String productName;
	
	private Integer investState; 
	/**
	 * 投资时间
	 */
	private Date startDate;
	/**
	 * 回款时间
	 */
	private Date endDate;
	/**
	 * 佣金率
	 */
	private Double feeRate;
	/**
	 * 收益
	 */
	private BigDecimal profit;
	/**
	 * 佣金
	 */
	private BigDecimal feeAmt;
	/**
	 * 投资金额
	 */
	private BigDecimal amt;
	
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
	
	
	
	
	public String getPlatfrom() {
		return platfrom;
	}
	public void setPlatfrom(String platfrom) {
		this.platfrom = platfrom;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getStartDate() {
		return DateUtils.format(startDate);
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate==null?"--":DateUtils.format(endDate);
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getFeeRate() {
		return null == feeRate  ? "0.00%" :feeRate.toString()+"%";
	}
	public void setFeeRate(Double feeRate) {
		this.feeRate = feeRate;
	}
	public String getProfit() {
		return profit.setScale(2,BigDecimal.ROUND_DOWN).toString();
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	public String getFeeAmt() {
		return feeAmt==null? "--" : feeAmt.setScale(2,BigDecimal.ROUND_DOWN).toString();
	}
	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}
	public String getAmt() {
		return amt.setScale(2,BigDecimal.ROUND_DOWN).toString();
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	
	public String getRate() {
		if( isflow==null) return null;
		return (ObjectUtils.equals(1, isflow))?
			(new BigDecimal(flowMinRate).setScale(2,BigDecimal.ROUND_DOWN).toString()+"%".intern())
		: 
			StringUtils.join(new Object[]{new BigDecimal(flowMinRate).setScale(2,BigDecimal.ROUND_DOWN).toString()+"%".intern(),new BigDecimal(flowMaxRate).setScale(2,BigDecimal.ROUND_DOWN).toString()+"%".intern()},"~".intern());
	}
	
	
	
	

	public Integer getInvestState() {
		return investState;
	}
	public void setInvestState(Integer investState) {
		this.investState = investState;
	}
	public void setIsflow(Integer isflow) {
		this.isflow = isflow;
	}
	
	public void setFlowMinRate(Double flowMinRate) {
		this.flowMinRate = flowMinRate;
	}
	
	public void setFlowMaxRate(Double flowMaxRate) {
		this.flowMaxRate = flowMaxRate;
	}

}
