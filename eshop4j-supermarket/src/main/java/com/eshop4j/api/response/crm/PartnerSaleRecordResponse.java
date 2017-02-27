package com.eshop4j.api.response.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.web.model.crm.PartnerSaleRecordResp;
import com.eshop4j.xoss.util.WebUtil;

/**
 * 团队成员销售记录
 * 
 * @Author ZhongLing
 * @Date 2016年1月20日 下午4:20:57
 */
public class PartnerSaleRecordResponse extends BaseEntity {
	private static final long serialVersionUID = -5418637411733755341L;

	public PartnerSaleRecordResponse() {

	}

	public PartnerSaleRecordResponse(PartnerSaleRecordResp obj) {
		WebUtil.initObj(this, obj);
		this.setBizDate(DateUtils.format(obj.getBizDate(), DateUtils.FORMAT_MM));
		this.setAllowanceType(obj.getAllowanceType() == 1 ? "我的直接收益" : "我的间接收益");
		this.setFeeRate(obj.getFeeRate() + "%");
	}

	/**
	 * 业务时间
	 */
	private String bizDate;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 销售额
	 */
	private String purAmount;
	/**
	 * 收益额
	 */
	private String allowance;
	/**
	 * 收益率
	 */
	private String feeRate;
	/**
	 * 收益类别名称
	 */
	private String allowanceType;

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getBizDate() {
		return bizDate;
	}

	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPurAmount() {
		return purAmount;
	}

	public void setPurAmount(String purAmount) {
		this.purAmount = purAmount;
	}

	public String getAllowance() {
		return allowance;
	}

	public void setAllowance(String allowance) {
		this.allowance = allowance;
	}

	public String getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(String feeRate) {
		this.feeRate = feeRate;
	}

	public String getAllowanceType() {
		return allowanceType;
	}

	public void setAllowanceType(String allowanceType) {
		this.allowanceType = allowanceType;
	}
}
