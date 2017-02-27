package com.eshop4j.web.model.crm;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;

/**
 * 团队成员销售记录
 * 
 * @Author ZhongLing
 * @Date 2016年1月20日 下午4:20:57
 */
public class PartnerSaleRecordResp extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 业务时间
	 */
	private Date bizDate;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 销售额
	 */
	private Double purAmount;
	/**
	 * 收益额
	 */
	private Double allowance;
	/**
	 * 津贴率
	 */
	private Double feeRate;
	/**
	 * 收益类别名称
	 */
	private int allowanceType;

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getAllowanceType() {
		return allowanceType;
	}

	public void setAllowanceType(int allowanceType) {
		this.allowanceType = allowanceType;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public Double getPurAmount() {
		return purAmount;
	}

	public void setPurAmount(Double purAmount) {
		this.purAmount = purAmount;
	}

	public Double getAllowance() {
		return allowance;
	}

	public void setAllowance(Double allowance) {
		this.allowance = allowance;
	}

	public Double getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(Double feeRate) {
		this.feeRate = feeRate;
	}

}
