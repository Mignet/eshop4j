package com.linkwee.web.model.crm;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.StringUtils;
 /**
 * 
 * @描述： 理财师佣金明细列表
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月07日 10:42:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AllowanceDetailListResp extends BaseEntity {
	
	private static final long serialVersionUID = 3625951331734709011L;
	
    /**
     * 自增长主键
     */
	private Integer id;
	/**
	 * 用户
	 */
	private String userName;
	/**
	 * 电话
	 */
	private String mobile;
	/**
	 * 关系等级
	 */
	private String relLevel;
	/**
     * 产品名称
     */
	private String productName;
	/**
     * 机构
     */
	private String platfrom;
	/**
     * 机构名称
     */
	private String platfromName;
	
	/**
     * 销售金额
     */
	private String saleAmount;
	/**
     * 收益率
     */
	private BigDecimal allowanceRate;
	/**
     * 销售时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date createTime;
    /**
     * 推荐收益
     */
	private BigDecimal allowance;
	
	/**
	 * 产品期限
	 */
	private String deadLine;
	
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
	public String getSaleAmount() {
		return saleAmount;
	}
	public void setSaleAmount(String saleAmount) {
		this.saleAmount = saleAmount;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRelLevel() {
		return relLevel;
	}
	public void setRelLevel(String relLevel) {
		this.relLevel = relLevel;
	}
	public BigDecimal getAllowanceRate() {
		return allowanceRate;
	}
	public void setAllowanceRate(BigDecimal allowanceRate) {
		this.allowanceRate = allowanceRate;
	}
	public BigDecimal getAllowance() {
		return allowance;
	}
	public void setAllowance(BigDecimal allowance) {
		this.allowance = allowance;
	}
	
	
}

