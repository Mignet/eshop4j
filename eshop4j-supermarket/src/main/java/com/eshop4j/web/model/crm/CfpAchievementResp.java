package com.eshop4j.web.model.crm;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.eshop4j.core.base.BaseEntity;
 /**
 * 
 * @描述：理财师业绩
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月07日 10:42:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CfpAchievementResp extends BaseEntity {
	
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
     *理财师城市
     */
	private String city;
	/**
     *投资人姓名
     */
	private String investorName;
	  /**
     *投资人手机
     */
	private String investorMobile;
    /**
     *投资金额
     */
	private BigDecimal investAmount;
    /**
     *投资平台
     */
	private String platfromName;
    /**
     *投资时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date bizTime;
	
	private String bizTimeStr;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getInvestorName() {
		return investorName;
	}
	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}
	public String getInvestorMobile() {
		return investorMobile;
	}
	public void setInvestorMobile(String investorMobile) {
		this.investorMobile = investorMobile;
	}
	public BigDecimal getInvestAmount() {
		return investAmount;
	}
	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}
	public String getPlatfromName() {
		return platfromName;
	}
	public void setPlatfromName(String platfromName) {
		this.platfromName = platfromName;
	}
	public Date getBizTime() {
		return bizTime;
	}
	public void setBizTime(Date bizTime) {
		this.bizTime = bizTime;
	}
	public String getBizTimeStr() {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(bizTime !=null && !"".equals(bizTime)){
			return dateFormater.format(bizTime);
		}
		return bizTimeStr;
	}
	public void setBizTimeStr(String bizTimeStr) {
		this.bizTimeStr = bizTimeStr;
	}
	
	
	
}

