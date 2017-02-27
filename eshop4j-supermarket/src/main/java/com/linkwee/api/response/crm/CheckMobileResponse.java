package com.linkwee.api.response.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：理财师登录
 *
 * @author Bob
 * @时间 2015年7月31日上午10:34:59
 *
 */
public class CheckMobileResponse extends BaseEntity {
	private static final long serialVersionUID = 8257727749491849571L;

	/**
	 * 0未注册(非第三方账号),1未注册(第三方账号) 2已注册
	 */
	private String regFlag;
	/**
	 * 第三方账号来源
	 */
	private String regSource;

	public String getRegFlag() {
		return regFlag;
	}

	public void setRegFlag(String regFlag) {
		this.regFlag = regFlag;
	}

	public String getRegSource() {
		return regSource;
	}

	public void setRegSource(String regSource) {
		this.regSource = regSource;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
