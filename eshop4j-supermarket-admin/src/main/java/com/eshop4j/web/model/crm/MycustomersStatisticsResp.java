package com.eshop4j.web.model.crm;

import com.eshop4j.core.base.BaseEntity;

/**
 * 客户列表-累计
 * 
 * @author xuzhao
 * @Date 2016年1月23日 下午5:03:36
 */
public class MycustomersStatisticsResp extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 注册客户
	 */
	private Integer regCustomer;
	/**
	 * 投资客户
	 */
	private Integer investCustomer;
	public Integer getRegCustomer() {
		return regCustomer;
	}
	public void setRegCustomer(Integer regCustomer) {
		this.regCustomer = regCustomer;
	}
	public Integer getInvestCustomer() {
		return investCustomer;
	}
	public void setInvestCustomer(Integer investCustomer) {
		this.investCustomer = investCustomer;
	}
	
}
