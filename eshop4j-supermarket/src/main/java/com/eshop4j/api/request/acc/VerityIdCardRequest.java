package com.eshop4j.api.request.acc;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class VerityIdCardRequest {

	/**
	 * 身份证号码
	 */
	@NotNull(message = "身份证号码不能为空")
	private String 	idCardNo;
	
	/**
	 * 姓名
	 */
	@NotNull(message = "姓名不能为空")
	private String name;
	
	/**
	 * 用户ID
	 */
	private String userId;



	public String getIdCardNo() {
		return idCardNo;
	}



	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
