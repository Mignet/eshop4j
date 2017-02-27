package com.eshop4j.web.model;

import com.eshop4j.core.base.BaseEntity;

public class investorDateViewResp extends BaseEntity {
	
	private static final long serialVersionUID = -6454238333125345993L;
	
 /**
  * 统计值
  */
	private Integer personAmount;
	private Double  investMoney;
	
  /**
   * 时间
   */
	private String statDate;
	/**
	 * 
	 */

	public String getStatDate() {
		return statDate;
	}
	
	public Integer getPersonAmount() {
		return personAmount;
	}

	public void setPersonAmount(Integer personAmount) {
		this.personAmount = personAmount;
	}

	public Double getInvestMoney() {
		return investMoney;
	}

	public void setInvestMoney(Double investMoney) {
		this.investMoney = investMoney;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}
   
	
}

