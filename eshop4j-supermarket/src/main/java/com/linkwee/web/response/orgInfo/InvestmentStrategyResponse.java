package com.linkwee.web.response.orgInfo;

import com.linkwee.core.base.BaseEntity;

public class InvestmentStrategyResponse extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     *投资攻略
     */
	private String orgInvestStrategy;
	
    /**
     *猎财攻略
     */
	private String orgPlannerStrategy;

	public String getOrgInvestStrategy() {
		return orgInvestStrategy;
	}

	public void setOrgInvestStrategy(String orgInvestStrategy) {
		this.orgInvestStrategy = orgInvestStrategy;
	}

	public String getOrgPlannerStrategy() {
		return orgPlannerStrategy;
	}

	public void setOrgPlannerStrategy(String orgPlannerStrategy) {
		this.orgPlannerStrategy = orgPlannerStrategy;
	}
}
