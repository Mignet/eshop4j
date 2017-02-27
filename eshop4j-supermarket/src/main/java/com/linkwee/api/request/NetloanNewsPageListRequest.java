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
public class NetloanNewsPageListRequest extends PaginatorRequest {

	private static final long serialVersionUID = -6403667546670342519L;
	/**
	 * 新闻动态类别（）
	 */
	private Integer typeCode;
	
	private Integer id;
	
	public Integer getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
