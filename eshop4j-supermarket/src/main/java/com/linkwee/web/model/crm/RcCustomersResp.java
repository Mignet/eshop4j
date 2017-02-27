package com.linkwee.web.model.crm;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：客户列表
 *
 * @author Bob
 * @时间 2015年10月14日下午3:32:46
 *
 */
public class RcCustomersResp extends BaseEntity {

	private static final long serialVersionUID = 888272075085059203L;

	/**
	 * 客户id
	 */
	private String customerId;

	/**
	 * 客户名称
	 */
	private String customerName;

	/**
	 * 客户手机号
	 */
	private String customerMobile;

	/**
	 * 注册时间
	 */
	private Date registerDate;

	/**
	 * 首投时间
	 */
	private Date firstRcpDate;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getFirstRcpDate() {
		return firstRcpDate;
	}

	public void setFirstRcpDate(Date firstRcpDate) {
		this.firstRcpDate = firstRcpDate;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
