package com.eshop4j.web.model.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


public class InvestRecordWrapper {
	
	/**
	 * 业务编号(系统内部UUID)
	 */
	private String bizId;
	
	/**
	 * 投资记录编号
	 */
	private String investId;
	
	
    /**
     *用户id
     */
	private String userId;
	
	/**
	 * 机构编码
	 */
	private String productOrgId;
	
    /**
     *产品id
     */
	private String productId;
	
	 /**
     *产品编号
     */
	private String productName;
	
	/**
	 * 产品期限
	 */
	private Integer productDays;
	
	/**
	 * '1不 需要募集 2 需要募集'
	 */
	private Integer productType;
	
	/**
	 * 产品佣金率
	 */
	private BigDecimal feeRatio;
	
    /**
     *购买本金
     */
	private BigDecimal investAmt;
	
	
    /**
     *投资日期
     */ 
	private Date investTime;
	
    /**
     *是否可赎回可转让(0=不支持赎回和转让|1=可赎回|2=可转让)
     */
	private Integer isRedemption;
	
	private Integer DeadLineMinValue;
	
    /**
     *产品最大期限
     */
	private Integer DeadLineMaxValue;
	
	/**
	 * 是否平台新用户
	 */
	private Boolean isPlatfromNewUser;
	
	/**
	 * 是否首次投资 1是 0否
	 */
	private boolean isFirstInvest;
	/**
	 * 是否平台首次投资1是 0否
	 */
	private boolean isPlatfromFirstInvest;
	
	/**
	 * 描述
	 */
	private String remark;
	
	/**
	 * 还款日期
	 */
	private Date endTime;

	
	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public String getInvestId() {
		return investId;
	}


	public void setInvestId(String investId) {
		this.investId = investId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	public Integer getProductType() {
		return productType;
	}


	public void setProductType(Integer productType) {
		this.productType = productType;
	}
	
	
	public BigDecimal getFeeRatio() {
		return feeRatio;
	}


	public void setFeeRatio(BigDecimal feeRatio) {
		this.feeRatio = feeRatio;
	}


	public BigDecimal getInvestAmt() {
		return investAmt;
	}


	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}


	public Date getInvestTime() {
		return investTime;
	}


	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}


	public String getBizId() {
		return bizId;
	}


	public void setBizId(String bizId) {
		this.bizId = bizId;
	}


	public String getProductOrgId() {
		return productOrgId;
	}


	public void setProductOrgId(String productOrgId) {
		this.productOrgId = productOrgId;
	}


	public Integer getProductDays() {
		return productDays;
	}


	public void setProductDays(Integer productDays) {
		this.productDays = productDays;
	}

	
	public Integer getIsRedemption() {
		return isRedemption;
	}


	public void setIsRedemption(Integer isRedemption) {
		this.isRedemption = isRedemption;
	}

	
	

	public Integer getDeadLineMinValue() {
		return DeadLineMinValue;
	}


	public void setDeadLineMinValue(Integer deadLineMinValue) {
		DeadLineMinValue = deadLineMinValue;
	}


	public Integer getDeadLineMaxValue() {
		return DeadLineMaxValue;
	}


	public void setDeadLineMaxValue(Integer deadLineMaxValue) {
		DeadLineMaxValue = deadLineMaxValue;
	}
	
	
	public Boolean getIsPlatfromNewUser() {
		return isPlatfromNewUser;
	}


	public void setIsPlatfromNewUser(Boolean isPlatfromNewUser) {
		this.isPlatfromNewUser = isPlatfromNewUser;
	}


	public boolean isFirstInvest() {
		return isFirstInvest;
	}


	public void setFirstInvest(boolean isFirstInvest) {
		this.isFirstInvest = isFirstInvest;
	}
	
	public boolean isPlatfromFirstInvest() {
		return isPlatfromFirstInvest;
	}


	public void setPlatfromFirstInvest(boolean isPlatfromFirstInvest) {
		this.isPlatfromFirstInvest = isPlatfromFirstInvest;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
	

}
