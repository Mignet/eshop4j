package com.eshop4j.web.request.act;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.api.PaginatorRequest;

/**
 * 
 * @描述：广告-分页
 *
 * @author chenchy
 * @时间 2016年9月13日上午11:16:39
 *
 */
public class AdvPageListRequest extends PaginatorRequest {

	private static final long serialVersionUID = -6403667546670342519L;
	/**
	 * 应用端口（1：理财师2：投资端）
	 */
	private Integer appType;
	/**
	 * 广告位置
	 */
	@NotNull(message="广告位置不能为空")
	private String advPlacement;
	
	public Integer getAppType() {
		return appType;
	}
	public void setAppType(Integer appType) {
		this.appType = appType;
	}
	public String getAdvPlacement() {
		return advPlacement;
	}
	public void setAdvPlacement(String advPlacement) {
		this.advPlacement = advPlacement;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
