package com.eshop4j.web.model.crm;

import java.math.BigDecimal;

import com.eshop4j.core.base.BaseEntity;
 /**
 * 
 * @描述：理财师业绩分析
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月07日 10:42:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CfpAchiAnalysisResp extends BaseEntity {
	
	private static final long serialVersionUID = 3625951331734709011L;
	
    /**
     *自增长主键
     */
	private Integer id;
	/**
     *理财师姓名
     */
	private String cfpName;
	/**
     *理财师手机
     */
	private String cfpMobile;
    /**
     *年化销售
     */
	private BigDecimal yearAmount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCfpName() {
		return cfpName;
	}
	public void setCfpName(String cfpName) {
		this.cfpName = cfpName;
	}
	public String getCfpMobile() {
		return cfpMobile;
	}
	public void setCfpMobile(String cfpMobile) {
		this.cfpMobile = cfpMobile;
	}
	public BigDecimal getYearAmount() {
		return yearAmount;
	}
	public void setYearAmount(BigDecimal yearAmount) {
		this.yearAmount = yearAmount;
	}
	
	
}

