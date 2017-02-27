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

	

	
	
}
