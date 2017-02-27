package com.eshop4j.api.request;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @描述：客户详情列表
 *
 * @author 何源
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class FeedbackRequest {

	/**
	 * 反馈内容
	 */
	@NotNull(message = "内容不能为空")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
