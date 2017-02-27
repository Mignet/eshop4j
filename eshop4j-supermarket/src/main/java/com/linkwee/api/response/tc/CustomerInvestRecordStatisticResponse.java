package com.linkwee.api.response.tc;

import java.math.BigDecimal;

import com.linkwee.core.base.BaseEntity;

public class CustomerInvestRecordStatisticResponse extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5114862849719100243L;

	/**
	 * 总额
	 */
	private BigDecimal total = BigDecimal.ZERO;
	
	private BigDecimal feeAmt = BigDecimal.ZERO;
	
	/**
	 * 次数
	 */
	private Integer count = new Integer(0);
	
	/**
	 * 人数
	 */
	private Integer number = new Integer(0);
	

	public String getInvestTotal() {
		return total.setScale(2,BigDecimal.ROUND_DOWN).toString();
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	
	public String getFeeAmt() {
		return feeAmt.setScale(2,BigDecimal.ROUND_DOWN).toString();
	}

	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getInvestCount() {
		return count.toString();
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getInvestNumber() {
		return number.toString();
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
