package com.eshop4j.web.model.crm;

import com.eshop4j.core.base.BaseEntity;

/**
 * 理财师客户投资统计
 * 
 * @author xuzhao
 * @Date 2016年1月21日 下午4:47:26
 */
public class CustomerInvestStatisticResp extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5752782361115143470L;

	/**
	 * 投资总额
	 */
	private Double investAmt;

	/**
	 * 投资笔数
	 */
	private Integer investCount;

	/**
	 * 投资人数
	 */
	private Integer investPersonCount;


	public Double getInvestAmt() {
		return investAmt;
	}

	public void setInvestAmt(Double investAmt) {
		this.investAmt = investAmt;
	}

	public Integer getInvestCount() {
		return investCount;
	}

	public void setInvestCount(Integer investCount) {
		this.investCount = investCount;
	}

	public Integer getInvestPersonCount() {
		return investPersonCount;
	}

	public void setInvestPersonCount(Integer investPersonCount) {
		this.investPersonCount = investPersonCount;
	}

}
