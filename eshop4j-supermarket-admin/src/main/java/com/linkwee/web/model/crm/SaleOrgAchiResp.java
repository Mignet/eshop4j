package com.linkwee.web.model.crm;

import java.math.BigDecimal;

import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：机构销售业绩
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月03日 15:53:27
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SaleOrgAchiResp extends BaseEntity {

	private static final long serialVersionUID = -1442643838535851690L;

	/**
	 * 历史累计销售额
	 */
	private BigDecimal historySales;
	
	/**
	 * 本月销售额
	 */
	private BigDecimal thisMonthSales;
	/**
	 * 本月销佣金
	 */
	private BigDecimal thisMonthFee;
	
	/**
	 * 理财师数量
	 */
	private int cfplannerCount;
	
	public BigDecimal getHistorySales() {
		return historySales.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setHistorySales(BigDecimal historySales) {
		this.historySales = historySales;
	}
	public BigDecimal getThisMonthSales() {
		return thisMonthSales.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setThisMonthSales(BigDecimal thisMonthSales) {
		this.thisMonthSales = thisMonthSales;
	}
	public BigDecimal getThisMonthFee() {
		return thisMonthFee.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setThisMonthFee(BigDecimal thisMonthFee) {
		this.thisMonthFee = thisMonthFee;
	}
	public int getCfplannerCount() {
		return cfplannerCount;
	}
	public void setCfplannerCount(int cfplannerCount) {
		this.cfplannerCount = cfplannerCount;
	}


}
