package com.eshop4j.api.request.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.api.PaginatorRequest;

/**
 * 
 * @描述：交易动态
 *
 * @author 何源
 * @时间 2015年8月6日上午10:23:39
 *
 */
public class MycustomersRequest extends PaginatorRequest {
	private static final long serialVersionUID = -5559748971844616557L;

	/**
	 * 客户姓名、电话
	 */
	private String name;

	/**
	 * 客户类别   1:投资客户2:未投资客户 3:重要客户 ;为空表示全部
	 */
	private String customerType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
