package com.linkwee.api.response.tc;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.linkwee.core.util.DateUtils;

public class CustomerInvestRecordResponse {
	
	private String uid;
	
	/**
	 * 投资人号码
	 */
	private String mobile;
	/**
	 * 投资人姓名
	 */
	private String name;
	
	/**
	 * 用户头像
	 */
	private String image;
	
	/**
	 * 投资产品名称
	 */
	private String productName;
	/**
	 * 日期
	 */
	private Date date;
	/**
	 * 投资金额
	 */
	private BigDecimal amt;
	
	/**
	 * 投资数量
	 */
	private Integer count;
	
	
	/**
	 * 佣金率
	 */
	private Double feeRate;
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
	
	
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	
	public String getDate() {
		return (date==null ? null : DateUtils.format(date,DateUtils.FORMAT_MM));
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	public String getInvestAmt() {
		return amt.setScale(2,BigDecimal.ROUND_DOWN).toString();
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	
	
	public String getFeeRate() {
		return (feeRate==null? "0.00%" : feeRate.toString()+"%");
	}
	
	public void setFeeRate(Double feeRate) {
		this.feeRate = feeRate;
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
	
	
	
	public String getCount() {
		return (count==null?null:count.toString());
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
}
