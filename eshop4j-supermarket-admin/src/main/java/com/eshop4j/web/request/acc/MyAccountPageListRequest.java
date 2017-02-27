package com.eshop4j.web.request.acc;

import javax.validation.constraints.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.api.PaginatorRequest;

public class MyAccountPageListRequest extends PaginatorRequest {

	private static final long serialVersionUID = -7499687855292274666L;
	
	/**
	 * 账户类型(1=全部明细|2=提现|3=活动奖励|4=红包|5=其他)
	 */
	private String typeValue;

	/**
	 * 类型:1理财师，2投资者
	 */
	@Pattern(regexp = "^[1-2]$", message = "类别只能为1,2")
	private String userType;
	
	
	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
