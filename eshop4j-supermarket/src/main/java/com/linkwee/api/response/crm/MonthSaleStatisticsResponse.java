package com.linkwee.api.response.crm;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.web.model.crm.MonthSaleStatisticsResp;
import com.linkwee.xoss.util.WebUtil;

/**
 * 团队销售统计
 * 
 * @Author ZhongLing
 * @Date 2016年1月20日 下午4:20:57
 */
public class MonthSaleStatisticsResponse extends BaseEntity {

	public MonthSaleStatisticsResponse() {
	}

	public MonthSaleStatisticsResponse(MonthSaleStatisticsResp obj) {
		WebUtil.initObj(this, obj);
	}
	
	private static final long serialVersionUID = 1L;
	/**
	 * 销售总额
	 */
	private String totalAmount;
	/**
	 * 总收益
	 */
	private String totalProfit;
	/**
	 * 直接推荐奖励
	 */
	private String allowance;
	/**
	 * 间接推荐奖励
	 */
	private String childrenAllowance;
	
	/**
	 * 销售笔数
	 */
	private String salesCount;

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(String totalProfit) {
		this.totalProfit = totalProfit;
	}

	public String getAllowance() {
		return allowance;
	}

	public void setAllowance(String allowance) {
		this.allowance = allowance;
	}

	public String getChildrenAllowance() {
		return childrenAllowance;
	}

	public void setChildrenAllowance(String childrenAllowance) {
		this.childrenAllowance = childrenAllowance;
	}

	public String getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(String salesCount) {
		this.salesCount = salesCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
