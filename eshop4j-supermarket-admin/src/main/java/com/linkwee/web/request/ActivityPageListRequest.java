package com.linkwee.web.request;

import javax.validation.constraints.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.api.PaginatorRequest;

/**
 * 
 * @描述：活动列表
 *
 * @author 何源
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class ActivityPageListRequest extends PaginatorRequest {

	private static final long serialVersionUID = -7499687855292274666L;

	/**
	 * 活动类别:0公共，1理财师，2投资者
	 */
	@Pattern(regexp = "^[1-2]$", message = "类别只能为1,2")
	private String appType;
	
	
	
	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}


	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
