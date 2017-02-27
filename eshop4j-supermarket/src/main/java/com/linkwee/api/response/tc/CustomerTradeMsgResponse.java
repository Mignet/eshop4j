package com.linkwee.api.response.tc;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;

public class CustomerTradeMsgResponse extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5693447138822271406L;
	/**
	 * 用户编号
	 */
	private String userId;
	
	/**
	 * 用户手机
	 */
	private String mobile;
	
	/**
	 * 用户名称
	 */
	private String name;
	
	/**
	 * 平台名称
	 */
	private String platfrom;
	
	/**
	 * 产品名称
	 */
	private String productName;
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
	
	
	private Date time;
	
	private Date  readTime;
	/**
	 * 类型 (1=申购|2=赎回)
	 */
	private Integer type;
	
	/**
	 * 描述
	 */
	private String remark;
	
	public String getRemark() {
		return feeAmt ==null || feeAmt.compareTo(new BigDecimal(0))==0 ? remark : "";
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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

	
	
	public String getPlatfrom() {
		return platfrom;
	}

	public void setPlatfrom(String platfrom) {
		this.platfrom = platfrom;
	}

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getStartDate() {
		return startDate==null ? null:DateUtils.format(startDate);
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate==null?"--":DateUtils.format(endDate,DateUtils.FORMAT_SHORT);
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getFeeRate() {
		return (feeRate==null? "0.00%" : feeRate.toString()+"%");
	}
	
	public void setFeeRate(Double feeRate) {
		this.feeRate = feeRate;
	}
	public String getProfit() {
		return profit==null?null:profit.setScale(2,BigDecimal.ROUND_DOWN).toString();
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	public String getFeeAmt() {
		return feeAmt.setScale(2,BigDecimal.ROUND_DOWN).toString();
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
	


	public void setIsflow(Integer isflow) {
		this.isflow = isflow;
	}
	
	public void setFlowMinRate(Double flowMinRate) {
		this.flowMinRate = flowMinRate;
	}
	
	public void setFlowMaxRate(Double flowMaxRate) {
		this.flowMaxRate = flowMaxRate;
	}
	
	public String getTime() {
		return DateUtils.format(time,DateUtils.FORMAT_MM);
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	//是否已读(true已读，false未读)
	public boolean isReadFlag() {
		return readTime==null?true:(readTime.compareTo(time)>-1);
	}
	
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	public String getType() {
		return type==null?"":type.toString();
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
