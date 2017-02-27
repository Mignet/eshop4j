package com.linkwee.web.request.orgInfo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * 
 * @描述：平台-平台详情
 *
 * @author chenchy
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class OrgJumpRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8425617166015755713L;

	/**
	 * 平台编码
	 */
	@NotNull(message="平台编码不能为空")
	private String orgNo;
	
	/**
	 * 产品id
	 */
	@NotNull(message="产品id不能为空")
	private String productId;
	
	

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

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
