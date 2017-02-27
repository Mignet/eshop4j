package com.eshop4j.web.request.acc;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class InputVcodeRequest {

	/**
	 *  验证码
	 */
	@NotNull(message = "验证码不能为空")
	private String vcode;

	public String getVcode() {
		return vcode;
	}


	public void setVcode(String vcode) {
		this.vcode = vcode;
	}


	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
