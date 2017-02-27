package com.eshop4j.api.request.tc;

import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 佣金收益计算
 * 
 * @Author ch
 * @Date 2016-07-25 15:25:30
 */
public class ProfitCalculateRequest {

	/**
	 * 产品ID
	 */
	@NotBlank(message = "产品编号不能为空")
	private String productId;
	
	/**
	 * 销售金额
	 */
	private Double amount;
	
	/**
	 * 产品期限
	 */
	private Integer day;



	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	
}
