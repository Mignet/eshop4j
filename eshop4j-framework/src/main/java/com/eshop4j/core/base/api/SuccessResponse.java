package com.eshop4j.core.base.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class SuccessResponse<T> extends BaseResponse{
	protected static final Logger logger = LoggerFactory.getLogger(BaseResponse.class);
	private static final long serialVersionUID = -7671756385477179547L;

	/**
	 * api返回数据 
	 */
	private T data;
	
	public SuccessResponse() {
		super("0","success");
	}

	public SuccessResponse(T data) {
		super("0","success");
		logger.debug("outputObject:"+data);
		this.data = data;
	}


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
