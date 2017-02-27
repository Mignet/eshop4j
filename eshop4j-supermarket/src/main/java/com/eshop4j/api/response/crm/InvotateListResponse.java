package com.eshop4j.api.response.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;

public class InvotateListResponse extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3810287999414948782L;

	private String customerId;
	private String customerMobile;
	private String customerName;
	private Integer investFlag;
	private String registerDate;
	
	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public String getCustomerMobile() {
		return customerMobile;
	}


	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getInvestFlag() {
		return investFlag;
	}


	public void setInvestFlag(Integer investFlag) {
		this.investFlag = investFlag;
	}


	public String getRegisterDate() {
		return registerDate;
	}


	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
