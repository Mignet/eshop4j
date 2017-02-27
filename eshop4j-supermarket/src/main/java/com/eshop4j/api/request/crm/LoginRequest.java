package com.eshop4j.api.request.crm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class LoginRequest{
	/**
	 * 手机号码
	 */
	@NotNull(message="手机号码不能为空")
	@Pattern(regexp="^1\\d{10}$",message="手机号码格式不对")
	private String mobile;
	
	/**
	 * 密码
	 */
	@NotNull(message="密码不能为空")
	private String password;
	
	/**
	 * 来源地址
	 */
	private String fromUrl;
	
	/**
	 * 受访地址
	 */
	private String accessUrl;
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getFromUrl() {
		return fromUrl;
	}

	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}
	
}
