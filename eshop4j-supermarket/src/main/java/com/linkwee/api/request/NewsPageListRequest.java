package com.linkwee.api.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.api.PaginatorRequest;

/**
 * 
 * @描述：资讯-分页
 *
 * @author chenchy
 * @时间 2016年5月13日上午11:16:39
 *
 */
public class NewsPageListRequest extends PaginatorRequest {

	private static final long serialVersionUID = -6403667546670342519L;
	/**
	 * 应用端口（1：理财师2：投资端）
	 */
	private Integer appType;
	/**
	 * 资讯类别（）
	 */
	private String typeCode;
	
	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
