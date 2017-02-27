package com.linkwee.api.request.crm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ResetLoginPwdRequest {
	
	/**
	 * 手机号码
	 */
	@NotNull(message = "手机号码不能为空")
	@Pattern(regexp = "^1\\d{10}$", message = "手机号码格式不对")
	private String mobile;
	
	/**
	 * 验证码
	 */
	@NotNull(message="验证码不为空")
	private String vcode;
	
	/**
	 * 新密码
	 */
	@NotNull(message = "密码不能为空")
	private String newPwd;


	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
