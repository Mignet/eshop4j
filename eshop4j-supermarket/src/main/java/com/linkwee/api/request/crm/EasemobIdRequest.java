package com.linkwee.api.request.crm;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class EasemobIdRequest {
	
	/**
	 * 环信帐号
	 */
	@NotNull(message="环信帐号不能为空")
	private String easemobAcct;

	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}


	public String getEasemobAcct() {
		return easemobAcct;
	}


	public void setEasemobAcct(String easemobAcct) {
		this.easemobAcct = easemobAcct;
	}
}
