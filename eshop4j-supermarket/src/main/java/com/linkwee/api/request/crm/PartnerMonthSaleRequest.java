package com.linkwee.api.request.crm;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.api.PaginatorRequest;

/**
 * 
 * @描述：我的团队-团队详情
 *
 * @author 何源
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class PartnerMonthSaleRequest extends PaginatorRequest {

	private static final long serialVersionUID = 1L;

	private String date;
	
	private String dateType;
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
}
