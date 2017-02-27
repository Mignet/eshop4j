package com.eshop4j.api.request.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.api.PaginatorRequest;

/**
 * 
 * @描述：我的团队-分页
 *
 * @author 何源
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class PartnerPageListRequest extends PaginatorRequest {

	private static final long serialVersionUID = -6403667546670342519L;
	/**
	 * 姓名或电话号码
	 */
	private String name;
	
	/**
	 * 理财师编号
	 */
	private String userNumber;
	
	
	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
