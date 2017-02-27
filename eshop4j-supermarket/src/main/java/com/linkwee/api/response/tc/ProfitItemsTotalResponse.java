package com.linkwee.api.response.tc;


import java.math.BigDecimal;

import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：客户收益项目总额
 *
 * @author ch
 * @时间 2016-07-28 16:16:19
 *
 */
public class ProfitItemsTotalResponse extends BaseEntity {

	private static final long serialVersionUID = -7499687855292274666L;

	public ProfitItemsTotalResponse() {}


	/**
	 * 收益项id
	 */
	private String profitType;
	/**
	 * 收益项名称
	 */
	private String profitName;

	/**
	 * 金额
	 */
	private BigDecimal amt;
	

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	public String getProfitName() {
		return profitName;
	}

	public void setProfitName(String profitName) {
		this.profitName = profitName;
	}

	public String getAmt() {
		return amt.setScale(2,BigDecimal.ROUND_DOWN).toString();
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	
}
