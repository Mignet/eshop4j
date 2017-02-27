package com.eshop4j.api.request.crm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @描述：检测手机号码 入参
 *
 * @author Bob
 * @时间  2015年8月8日下午2:32:15
 *
 */
public class CheckMobileRequest {
	
	/**
	 * 手机号码
	 */
	@NotNull(message="手机号码不能为空")
	@Pattern(regexp="^1\\d{10}$",message="手机号码格式不对")
	private String mobile;
	
	/**
	 * 推荐码
	 */
	private String recommendCode;
	
	public String getRecommendCode() {
		return recommendCode;
	}
	public void setRecommendCode(String recommendCode) {
		this.recommendCode = recommendCode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

