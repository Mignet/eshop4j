package com.eshop4j.web.model.acc;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
/**
* 
* @描述： 实体Bean
* 
* @创建人： chenjl
* 
* @创建时间：2016年07月22日 17:10:52
* 
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class MonthProfixDetailListResp implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
     *金额
     */
	private Double amount;
	/**
     *时间
     */
	private Date createTime;
	/**
     *描述
     */
	private String description;
	/**
     *收益类型名称
     */
	private String profixTypeName;
	
	/**
     *收益类型:1销售佣金，2推荐津贴，3活动奖励，4团队leader奖励
     */
	private String profixType;
	
	/**
     *年化佣金率
     */
	private Double feeRate;
	
	/**
     *产品期限
     */
	private String deadline;
	
	private String feeType;
	
	private String originCfplannerName;
	
	private String productName;
	
	private Double productAmount;
	
	private String remark;
	
	/**
	 * 是否可赎回转让
	 */
	private int isRedemption;
	
	/**
	 * 收费类型
	 */
	private int orgFeeType;
	
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
	 * 产品期限 (30天~6个月)
	 */
	private String deadLineValueNewText;
	public String getDeadLineValueNewText() {
		if(getIsFixedDeadline() != null) {
			if (getIsFixedDeadline() == 1){
				if(StringUtils.isNotBlank(getDeadLineMinSelfDefined())){
					deadLineValueNewText = getDeadLineMinSelfDefined();
				} else {
					deadLineValueNewText = getDeadLineMinValue()+"天";
				}
			} else {
				if(StringUtils.isNotBlank(getDeadLineMinSelfDefined()) && StringUtils.isNotBlank(getDeadLineMaxSelfDefined())){
					deadLineValueNewText = getDeadLineMinSelfDefined()+"~"+getDeadLineMaxSelfDefined();
				} else {
					deadLineValueNewText = getDeadLineMinValue()+"天~"+getDeadLineMaxValue()+"天";
				}
			}
		}
		return deadLineValueNewText;
	}

	public void setDeadLineValueNewText(String deadLineValueNewText) {
		this.deadLineValueNewText = deadLineValueNewText;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfixTypeName() {
		return profixTypeName;
	}

	public void setProfixTypeName(String profixTypeName) {
		this.profixTypeName = profixTypeName;
	}

	public String getProfixType() {
		return profixType;
	}

	public void setProfixType(String profixType) {
		this.profixType = profixType;
	}

	public Double getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(Double feeRate) {
		this.feeRate = feeRate;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getOriginCfplannerName() {
		return originCfplannerName;
	}

	public void setOriginCfplannerName(String originCfplannerName) {
		this.originCfplannerName = originCfplannerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(Double productAmount) {
		this.productAmount = productAmount;
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

	public int getIsRedemption() {
		return isRedemption;
	}

	public void setIsRedemption(int isRedemption) {
		this.isRedemption = isRedemption;
	}

	public int getOrgFeeType() {
		return orgFeeType;
	}

	public void setOrgFeeType(int orgFeeType) {
		this.orgFeeType = orgFeeType;
	}



	
	

}