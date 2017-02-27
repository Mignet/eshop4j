package com.linkwee.web.response.crm;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.WebUtil;
import com.linkwee.web.model.crm.investmentRateResp;
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
public class investmentRateResponse extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public investmentRateResponse() {

	}

	public investmentRateResponse(investmentRateResp obj) {
	    WebUtil.initObj(this,obj);
	    this.setInvestingRate(this.getInvestingRate() + "%");
	    this.setInvestmentRate(this.getInvestmentRate() + "%");
	    this.setReInvestmentRate(this.getReInvestmentRate() + "%");
	}

	/**
	 * 注册人数
	 */
	private String registerCount;
	
	/**
	 * 登录人数
	 */
	private String loginCount;
	
	/**
	 * 投资人数：通过T呗投资过的人数
	 */
	private String investmentCount;
	
	/**
	 * 复投人数：通过T呗投资过两次及以上的人数
	 */
	private String reInvestmentCount;
	
	/**
	 * 正在投资人数
	 */
	private String investingCount;
	
	/**
	 * 投资率=投资人数 / 注册人数
	 */
	private String investmentRate;
	
	/**
	 * 复投率=复投人数 / 投资人数
	 */
	private String reInvestmentRate;
	
	/**
	 * 在投率=在投人数 / 投资人数
	 */
	private String investingRate;

	public String getRegisterCount() {
		return registerCount;
	}

	public void setRegisterCount(String registerCount) {
		this.registerCount = registerCount;
	}

	public String getInvestmentCount() {
		return investmentCount;
	}

	public void setInvestmentCount(String investmentCount) {
		this.investmentCount = investmentCount;
	}

	public String getReInvestmentCount() {
		return reInvestmentCount;
	}

	public void setReInvestmentCount(String reInvestmentCount) {
		this.reInvestmentCount = reInvestmentCount;
	}

	public String getInvestingCount() {
		return investingCount;
	}

	public void setInvestingCount(String investingCount) {
		this.investingCount = investingCount;
	}

	public String getInvestmentRate() {
		return investmentRate;
	}

	public void setInvestmentRate(String investmentRate) {
		this.investmentRate = investmentRate;
	}

	public String getReInvestmentRate() {
		return reInvestmentRate;
	}

	public void setReInvestmentRate(String reInvestmentRate) {
		this.reInvestmentRate = reInvestmentRate;
	}

	public String getInvestingRate() {
		return investingRate;
	}

	public void setInvestingRate(String investingRate) {
		this.investingRate = investingRate;
	}

	public String getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(String loginCount) {
		this.loginCount = loginCount;
	}
	
	
}

