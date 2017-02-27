package com.eshop4j.api.response.crm;

import java.util.Date;

import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.EnumUtils;
import com.eshop4j.web.enums.CfpLevelEnum;
import com.eshop4j.web.model.crm.CfpCustomerCountResp;
import com.eshop4j.xoss.util.WebUtil;


/**
 * 理财师客户信息统计
 * 
 * @Date 2016年2月1日 下午6:21:42
 */
public class CfpCustomerCountResponse extends BaseEntity {

	private static final long serialVersionUID = 4669440007808370539L;
	public CfpCustomerCountResponse() {
	}

	public CfpCustomerCountResponse(CfpCustomerCountResp obj){
		WebUtil.initObj(this, obj);
		if(obj.getMinTime() != null) {
			this.setMinTime(DateUtils.format(obj.getMinTime(), DateUtils.FORMAT_SHORT));
		} else {
			this.setMinTime(DateUtils.format(new Date(), DateUtils.FORMAT_SHORT));
		}
		if(obj.getNewMsgCount() >99) {
			this.setNewMsgCount(99 + "+");
		}
	}
	/**
	 * 当日投资金额
	 */
	private String dayInvestAmt;
	/**
	 * 本月投资金额
	 */
	private String monthInvestAmt;
	/**
	 * 累计投资总额
	 */
	private String totalInvestAmt;
	
	/**
	 * 新交易动态数量
	 */
	private String newBuytradeCount;
	/**
	 * 新赎回动态数量
	 */
	private String newBacktradeCount;
	/**
	 * 新客户数量
	 */
	private String newCustomerCount;
	/**
	 * 新消息数量
	 */
	private String newMsgCount;
	/**
	 * 投资搜索最小时间
	 */
	private String minTime;
	
	/**
	 * 本月佣金
	 */
	private String thisMonthFee;
	/**
	 * 本月团队销售额
	 */
	private String thisMonthTeamSaleAmount;
	/**
	 * 本月推荐津贴
	 */
	private String thisMonthAllowance;
	/**
	 * 团队人数
	 */
	private String teamCount;
	/**
	 * 职级
	 */
	private String level;
	
	/**
	 * 有无客户：1有，0无
	 */
	private String hasCustomer;
	/**
	 * 有无团队成员：1有，0无
	 */
	private String hasTeamMembers;
	
	/**
	 * 是否有广告
	 */
	private String hasAdvertisement;
	
	/**
	 * 广告图片
	 */
	private String advertisementImageUrl;
	
	/**
	 * 广告链接
	 */
	private String advertisementLinkUrl;
	
	
	public String getDayInvestAmt() {
		return dayInvestAmt;
	}

	public void setDayInvestAmt(String dayInvestAmt) {
		this.dayInvestAmt = dayInvestAmt;
	}

	public String getMonthInvestAmt() {
		return monthInvestAmt;
	}

	public void setMonthInvestAmt(String monthInvestAmt) {
		this.monthInvestAmt = monthInvestAmt;
	}

	public String getTotalInvestAmt() {
		return totalInvestAmt;
	}

	public void setTotalInvestAmt(String totalInvestAmt) {
		this.totalInvestAmt = totalInvestAmt;
	}

	public String getNewBuytradeCount() {
		return newBuytradeCount;
	}

	public void setNewBuytradeCount(String newBuytradeCount) {
		this.newBuytradeCount = newBuytradeCount;
	}

	public String getNewBacktradeCount() {
		return newBacktradeCount;
	}

	public void setNewBacktradeCount(String newBacktradeCount) {
		this.newBacktradeCount = newBacktradeCount;
	}

	public String getNewCustomerCount() {
		return newCustomerCount;
	}

	public void setNewCustomerCount(String newCustomerCount) {
		this.newCustomerCount = newCustomerCount;
	}

	public String getNewMsgCount() {
		return newMsgCount;
	}

	public void setNewMsgCount(String newMsgCount) {
		this.newMsgCount = newMsgCount;
	}

	public String getMinTime() {
		return minTime;
	}

	public void setMinTime(String minTime) {
		this.minTime = minTime;
	}

	public String getThisMonthFee() {
		return thisMonthFee;
	}

	public void setThisMonthFee(String thisMonthFee) {
		this.thisMonthFee = thisMonthFee;
	}

	public String getThisMonthTeamSaleAmount() {
		return thisMonthTeamSaleAmount;
	}

	public void setThisMonthTeamSaleAmount(String thisMonthTeamSaleAmount) {
		this.thisMonthTeamSaleAmount = thisMonthTeamSaleAmount;
	}

	public String getThisMonthAllowance() {
		return thisMonthAllowance;
	}

	public void setThisMonthAllowance(String thisMonthAllowance) {
		this.thisMonthAllowance = thisMonthAllowance;
	}

	public String getTeamCount() {
		return teamCount;
	}

	public void setTeamCount(String teamCount) {
		this.teamCount = teamCount;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getHasCustomer() {
		return hasCustomer;
	}

	public void setHasCustomer(String hasCustomer) {
		this.hasCustomer = hasCustomer;
	}

	public String getHasTeamMembers() {
		return hasTeamMembers;
	}

	public void setHasTeamMembers(String hasTeamMembers) {
		this.hasTeamMembers = hasTeamMembers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getHasAdvertisement() {
		return hasAdvertisement;
	}

	public void setHasAdvertisement(String hasAdvertisement) {
		this.hasAdvertisement = hasAdvertisement;
	}

	public String getAdvertisementImageUrl() {
		return advertisementImageUrl;
	}

	public void setAdvertisementImageUrl(String advertisementImageUrl) {
		this.advertisementImageUrl = advertisementImageUrl;
	}

	public String getAdvertisementLinkUrl() {
		return advertisementLinkUrl;
	}

	public void setAdvertisementLinkUrl(String advertisementLinkUrl) {
		this.advertisementLinkUrl = advertisementLinkUrl;
	}

	

}
