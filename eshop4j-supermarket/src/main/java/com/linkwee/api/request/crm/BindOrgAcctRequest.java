package com.linkwee.api.request.crm;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @描述：绑定机构帐号 入参
 *
 * @author Bob
 * @时间  2015年8月8日下午2:32:15
 *
 */
public class BindOrgAcctRequest {
	
	/**
	 * 机构编码
	 */
	@NotNull(message="机构编码不能为空")
	private String platFromNumber;
	
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}


	public String getPlatFromNumber() {
		return platFromNumber;
	}


	public void setPlatFromNumber(String platFromNumber) {
		this.platFromNumber = platFromNumber;
	}




}

