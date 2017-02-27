package com.linkwee.api.request.crm;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.api.PaginatorRequest;

/**
 * 
 * @描述：我的团队-团队详情
 *
 * @author ZL
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class PlatformManagerListRequest extends PaginatorRequest {

	private static final long serialVersionUID = 1L;
	/**
	 * 绑定类型
	 */
	@NotNull(message = "类型不能为空")
	private String type;

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
