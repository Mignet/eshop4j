package com.eshop4j.api.request.crm;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
/**
 * 
 * @描述：请求分享入参
 *
 * @author 何源
 * @时间  2015年8月21日下午5:04:42
 *
 */
public class WechatShareRequest {
	/**
	 * 分享url
	 */
	@NotNull(message="url不能为空")
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
