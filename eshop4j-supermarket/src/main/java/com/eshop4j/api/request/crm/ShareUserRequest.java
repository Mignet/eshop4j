package com.eshop4j.api.request.crm;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ShareUserRequest {

	/**
	 * 用户名
	 */
	@NotNull(message = "用户名不能为空")
	private String userName;
	
	/**
	 * 手机号码
	 */
	@NotNull(message = "手机号码不能为空")
	private String mobile;
	
	/**
	 * 	活动类型
	 */
	private String activityType;



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getActivityType() {
		return activityType;
	}



	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}



	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
