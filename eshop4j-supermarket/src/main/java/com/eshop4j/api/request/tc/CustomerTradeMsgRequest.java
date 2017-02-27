package com.eshop4j.api.request.tc;

import org.hibernate.validator.constraints.Range;

import com.eshop4j.core.base.api.PaginatorRequest;

public class CustomerTradeMsgRequest extends PaginatorRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -616976917367897950L;
	
	/**
	 * 消息类型 1=申购 | 2=赎回
	 */
	@Range(min=1,max=2,message="消息类型必须为1或者2")
	private Integer type = 1;
	
	/**
	 * 用户编号
	 */
	private String customerId;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
}
