package com.linkwee.api.response.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：理财师登录
 *
 * @author Bob
 * @时间  2015年7月31日上午10:34:59
 *
 */
public class LoginResponse extends BaseEntity{
	private static final long serialVersionUID = -6961843898868728908L;
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
	
}
