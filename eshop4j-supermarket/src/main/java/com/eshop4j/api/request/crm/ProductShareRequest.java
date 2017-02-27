package com.eshop4j.api.request.crm;

import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 产品ID
 * 
 * @Author hxb
 * @Date 
 */
public class ProductShareRequest {

	/**
	 * 产品ID
	 */
	@NotBlank(message = "产品ID不能为空")
	private String productId;

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
}
