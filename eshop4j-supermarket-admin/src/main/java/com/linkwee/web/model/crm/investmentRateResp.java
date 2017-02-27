package com.linkwee.web.model.crm;

import com.linkwee.core.base.BaseEntity;
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
public class investmentRateResp extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 注册人数
	 */
	private int registerCount;
	
	/**
	 * 登录人数
	 */
	private int loginCount;
	
	/**
	 * 投资人数：通过T呗投资过的人数
	 */
	private int investmentCount;
	
	/**
	 * 复投人数：通过T呗投资过两次及以上的人数
	 */
	private int reInvestmentCount;
	
	/**
	 * 正在投资人数
	 */
	private int investingCount;
	
	/**
	 * 投资率=投资人数 / 注册人数
	 */
	private Double investmentRate;
	
	/**
	 * 复投率=复投人数 / 投资人数
	 */
	private Double reInvestmentRate;
	
	/**
	 * 在投率=在投人数 / 投资人数
	 */
	private Double investingRate;

	public int getRegisterCount() {
		return registerCount;
	}

	public void setRegisterCount(int registerCount) {
		this.registerCount = registerCount;
	}

	public int getInvestmentCount() {
		return investmentCount;
	}

	public void setInvestmentCount(int investmentCount) {
		this.investmentCount = investmentCount;
	}

	public int getReInvestmentCount() {
		return reInvestmentCount;
	}

	public void setReInvestmentCount(int reInvestmentCount) {
		this.reInvestmentCount = reInvestmentCount;
	}

	public int getInvestingCount() {
		return investingCount;
	}

	public void setInvestingCount(int investingCount) {
		this.investingCount = investingCount;
	}

	public Double getInvestmentRate() {
		return investmentRate;
	}

	public void setInvestmentRate(Double investmentRate) {
		this.investmentRate = investmentRate;
	}

	public Double getReInvestmentRate() {
		return reInvestmentRate;
	}

	public void setReInvestmentRate(Double reInvestmentRate) {
		this.reInvestmentRate = reInvestmentRate;
	}

	public Double getInvestingRate() {
		return investingRate;
	}

	public void setInvestingRate(Double investingRate) {
		this.investingRate = investingRate;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}
	
	
}

