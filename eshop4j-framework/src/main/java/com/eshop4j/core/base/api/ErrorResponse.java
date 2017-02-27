package com.eshop4j.core.base.api;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @描述：api返回数据
 *
 * @author Bob
 * @时间  2015年7月31日上午10:16:16
 *
 */
public class ErrorResponse extends BaseResponse{
	
	private static final long serialVersionUID = -7076970690575275023L;
	
	/**
	 * api返回数据 
	 */
	private List<BaseResponse> errors;
	
	public ErrorResponse(){
		
	}
	
	public ErrorResponse(String code, String msg) {
		super(code,msg);
	}

	public List<BaseResponse> getErrors() {
		return errors;
	}

	public void setErrors(List<BaseResponse> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
