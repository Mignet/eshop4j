package com.eshop4j.api.request.crm;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.api.PaginatorRequest;

/**
 * 
 * @描述：我的团队-团队详情
 *
 * @author 何源
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class PartnerDetailRequest extends PaginatorRequest {

	private static final long serialVersionUID = 1L;
	/**
	 * 用户编码
	 */
	@NotNull(message = "用户ID不能为空")
	private String userId;

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
