package com.linkwee.web.model;


/**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年10月29日 19:30:26
 * 
 *                   Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class ProductInfoExtResp extends ProductInfoExt {

	private static final long serialVersionUID = 3231019910148788618L;
	/**
	 * 产品费率
	 */
	private Double commission;

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

}
