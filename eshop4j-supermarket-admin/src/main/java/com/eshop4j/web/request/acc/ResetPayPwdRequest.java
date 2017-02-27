package com.eshop4j.web.request.acc;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ResetPayPwdRequest {

	/**
	 * 新密码
	 */
	@NotNull(message = "新密码不能为空")
	private String pwd;
	
	/**
	 * 重置密码token
	 */
	private String resetPayPwdToken;
	
	/**
	 * 用户ID
	 */
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getResetPayPwdToken() {
		return resetPayPwdToken;
	}

	public void setResetPayPwdToken(String resetPayPwdToken) {
		this.resetPayPwdToken = resetPayPwdToken;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
