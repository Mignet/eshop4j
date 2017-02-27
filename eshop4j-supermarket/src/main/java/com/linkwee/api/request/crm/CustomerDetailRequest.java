package com.linkwee.api.request.crm;

import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @描述：客户详情
 *
 * @author 何源
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class CustomerDetailRequest {

	/**
	 * 客户编码
	 */
	@NotBlank(message = "客户不能为空")
	private String userId;

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
