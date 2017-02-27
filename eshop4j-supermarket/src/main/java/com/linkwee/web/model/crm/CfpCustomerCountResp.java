package com.linkwee.web.model.crm;

import java.util.Date;

import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：理财师客户信息统计
 *
 * @author Bob
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class CfpCustomerCountResp extends BaseEntity {

	private static final long serialVersionUID = 4669440007808370539L;

	/**
	 * 当日投资金额
	 */
	private Double dayInvestAmt;
	/**
	 * 本月投资金额
	 */
	private Double monthInvestAmt;
	/**
	 * 累计投资总额
	 */
	private Double totalInvestAmt;
	/**
	 * 新交易动态数量
	 */
	private int newBuytradeCount;
	/**
	 * 新赎回动态数量
	 */
	private int newBacktradeCount;
	/**
	 * 新客户数量
	 */
	private int newCustomerCount;
	/**
	 * 新消息数量
	 */
	private int newMsgCount;
	/**
	 * 投资搜索最小时间
	 */
	private Date minTime;
	
	/**
	 * 本月佣金
	 */
	private Double thisMonthFee;
	/**
	 * 本月团队销售额
	 */
	private Double thisMonthTeamSaleAmount;
	/**
	 * 本月推荐津贴
	 */
	private Double thisMonthAllowance;
	/**
	 * 团队人数
	 */
	private int teamCount;
	/**
	 * 职级
	 */
	private String level;
	
	/**
	 * 有无客户：1有，0无
	 */
	private int hasCustomer;
	/**
	 * 有无团队成员：1有，0无
	 */
	private int hasTeamMembers;
	
	/**
	 * 是否有广告
	 */
	private int hasAdvertisement;
	
	/**
	 * 广告图片
	 */
	private String advertisementImageUrl;
	
	/**
	 * 广告链接
	 */
	private String advertisementLinkUrl;
	
	
	
	public Double getDayInvestAmt() {
		return dayInvestAmt;
	}
	public void setDayInvestAmt(Double dayInvestAmt) {
		this.dayInvestAmt = dayInvestAmt;
	}
	public Double getMonthInvestAmt() {
		return monthInvestAmt;
	}
	public void setMonthInvestAmt(Double monthInvestAmt) {
		this.monthInvestAmt = monthInvestAmt;
	}
	public Double getTotalInvestAmt() {
		return totalInvestAmt;
	}
	public void setTotalInvestAmt(Double totalInvestAmt) {
		this.totalInvestAmt = totalInvestAmt;
	}
	public int getNewBuytradeCount() {
		return newBuytradeCount;
	}
	public void setNewBuytradeCount(int newBuytradeCount) {
		this.newBuytradeCount = newBuytradeCount;
	}
	public int getNewBacktradeCount() {
		return newBacktradeCount;
	}
	public void setNewBacktradeCount(int newBacktradeCount) {
		this.newBacktradeCount = newBacktradeCount;
	}
	public int getNewCustomerCount() {
		return newCustomerCount;
	}
	public void setNewCustomerCount(int newCustomerCount) {
		this.newCustomerCount = newCustomerCount;
	}
	public int getNewMsgCount() {
		return newMsgCount;
	}
	public void setNewMsgCount(int newMsgCount) {
		this.newMsgCount = newMsgCount;
	}
	public Date getMinTime() {
		return minTime;
	}
	public void setMinTime(Date minTime) {
		this.minTime = minTime;
	}
	public Double getThisMonthFee() {
		return thisMonthFee;
	}
	public void setThisMonthFee(Double thisMonthFee) {
		this.thisMonthFee = thisMonthFee;
	}
	public Double getThisMonthTeamSaleAmount() {
		return thisMonthTeamSaleAmount;
	}
	public void setThisMonthTeamSaleAmount(Double thisMonthTeamSaleAmount) {
		this.thisMonthTeamSaleAmount = thisMonthTeamSaleAmount;
	}
	public Double getThisMonthAllowance() {
		return thisMonthAllowance;
	}
	public void setThisMonthAllowance(Double thisMonthAllowance) {
		this.thisMonthAllowance = thisMonthAllowance;
	}
	public int getTeamCount() {
		return teamCount;
	}
	public void setTeamCount(int teamCount) {
		this.teamCount = teamCount;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getHasCustomer() {
		return hasCustomer;
	}
	public void setHasCustomer(int hasCustomer) {
		this.hasCustomer = hasCustomer;
	}
	public int getHasTeamMembers() {
		return hasTeamMembers;
	}
	public void setHasTeamMembers(int hasTeamMembers) {
		this.hasTeamMembers = hasTeamMembers;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getHasAdvertisement() {
		return hasAdvertisement;
	}
	public void setHasAdvertisement(int hasAdvertisement) {
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
