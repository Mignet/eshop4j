package com.eshop4j.web.model.crm;

import java.math.BigDecimal;

import com.eshop4j.core.base.BaseEntity;

/**
 * 
 * @描述： 理财师销售与收益列表
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月03日 15:53:27
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SalseOrgStockYearpurAmtResp extends BaseEntity {

	private static final long serialVersionUID = -1442643838535851690L;


	/**
	 * 销售机构编码
	 */
	private String salesOrgId;

	/**
	 * 上月存量销售额
	 */
	private BigDecimal lastMonthStockSales;
	
	/**
	 * 上月存量年化销售额
	 */
	private BigDecimal lastMonthStockYearSales;
	

	public String getSalesOrgId() {
		return salesOrgId;
	}

	public void setSalesOrgId(String salesOrgId) {
		this.salesOrgId = salesOrgId;
	}

	public BigDecimal getLastMonthStockSales() {
		return lastMonthStockSales;
	}

	public void setLastMonthStockSales(BigDecimal lastMonthStockSales) {
		this.lastMonthStockSales = lastMonthStockSales;
	}

	public BigDecimal getLastMonthStockYearSales() {
		return lastMonthStockYearSales;
	}

	public void setLastMonthStockYearSales(BigDecimal lastMonthStockYearSales) {
		this.lastMonthStockYearSales = lastMonthStockYearSales;
	}


	

}
