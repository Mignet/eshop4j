package com.eshop4j.web.request.orgInfo;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.api.PaginatorRequest;


/**
 * 
 * @描述：平台-平台详情
 *
 * @author chenchy
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class OrgDetailRequest  extends PaginatorRequest{

	private static final long serialVersionUID = 1L;
	/**
	 * 平台编码
	 */
	@NotNull(message="平台编码不能为空")
	private String orgNo;
	
	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
