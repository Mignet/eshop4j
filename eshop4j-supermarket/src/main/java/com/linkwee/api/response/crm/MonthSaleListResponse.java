package com.linkwee.api.response.crm;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.model.crm.MonthSaleListResp;
import com.linkwee.xoss.util.WebUtil;

/**
 * 团队销售列表
 * 
 * @Author ZhongLing
 * @Date 2016年1月20日 下午4:20:57
 */
public class MonthSaleListResponse extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	public MonthSaleListResponse() {
	}

	public MonthSaleListResponse(MonthSaleListResp obj) {
		WebUtil.initObj(this, obj);
		this.setFeeRate(obj.getFeeRate() + "%");
		this.setBizTime(DateUtils.format(obj.getBizTime(),DateUtils.FORMAT_MM));
	}
	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 时间
	 */
	private String bizTime;
	/**
	 *产品名称
	 */
	private String productName;
	/**
	 * 销售金额
	 */
	private String SaleAmount;
	
	/**
	 * 推荐奖励
	 */
	private String feeAmount;
	
	/**
	 * 佣金率
	 */
	private String feeRate;
	
	/**
	 * 推荐奖励类别：1直接；2间接
	 */
	private String type;
	
	/**
	 * 头像
	 */
	private String headImage;
	/**
	 * 电话号码
	 */
	private String mobile;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBizTime() {
		return bizTime;
	}

	public void setBizTime(String bizTime) {
		this.bizTime = bizTime;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSaleAmount() {
		return SaleAmount;
	}

	public void setSaleAmount(String saleAmount) {
		SaleAmount = saleAmount;
	}

	public String getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(String feeAmount) {
		this.feeAmount = feeAmount;
	}

	public String getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(String feeRate) {
		this.feeRate = feeRate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	


}
