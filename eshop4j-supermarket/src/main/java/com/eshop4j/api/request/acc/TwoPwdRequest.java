package com.eshop4j.api.request.acc;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class TwoPwdRequest {
	
	/**
	 * 旧密码
	 */
	@NotNull(message="旧密码不能为空")
	private String oldPwd;
	
	/**
	 * 新密码
	 */
	@NotNull(message="新密码不能为空")
	private String newPwd;

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
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
