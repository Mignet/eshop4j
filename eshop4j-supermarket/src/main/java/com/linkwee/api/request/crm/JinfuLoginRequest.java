package com.linkwee.api.request.crm;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JinfuLoginRequest{
	/**
	 * 手机号码
	 */
	@NotNull(message="key不能为空")
	private String key;
	
	@NotNull(message="code不能为空")
	private String code;
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
