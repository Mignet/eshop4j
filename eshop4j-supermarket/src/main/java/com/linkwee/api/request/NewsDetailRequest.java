package com.linkwee.api.request;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.api.PaginatorRequest;

/**
 * 
 * @描述：资讯-资讯详情
 *
 * @author chenchy
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class NewsDetailRequest  extends PaginatorRequest{

	private static final long serialVersionUID = 1L;
	/**
	 * 资讯ID
	 */
	@NotNull(message="资讯ID不能为空")
	private String newsId;

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
