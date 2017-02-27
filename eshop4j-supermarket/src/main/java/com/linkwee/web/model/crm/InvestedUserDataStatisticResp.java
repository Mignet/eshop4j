package com.linkwee.web.model.crm;

import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：已投资用户统计数据
 *
 * @author Bob
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class InvestedUserDataStatisticResp extends BaseEntity {
	private static final long serialVersionUID = -226463495944568640L;

	/**
	 * 今天新增人数
	 */
	private int todayNewCount;
	/**
	 * 今天总人数
	 */
	private int todayTotalCount;
	/**
	 * 昨天新增人数
	 */
	private int yesterdayNewCount;
	/**
	 * 昨天总人数
	 */
	private int yesterdayTotalCount;
	/**
	 * 本月新增人数
	 */
	private int thisMonthNewCount;
	/**
	 * 本月总人数
	 */
	private int thisMonthTotalCount;
	/**
	 * 搜索区间新增人数
	 */
	private int intervalNewCount;
	/**
	 * 搜索区间总人数
	 */
	private int intervalTotalCount;
	/**
	 * 总人数
	 */
	private int totalCount;
	
	public int getTodayNewCount() {
		return todayNewCount;
	}
	public void setTodayNewCount(int todayNewCount) {
		this.todayNewCount = todayNewCount;
	}
	public int getTodayTotalCount() {
		return todayTotalCount;
	}
	public void setTodayTotalCount(int todayTotalCount) {
		this.todayTotalCount = todayTotalCount;
	}
	public int getYesterdayNewCount() {
		return yesterdayNewCount;
	}
	public void setYesterdayNewCount(int yesterdayNewCount) {
		this.yesterdayNewCount = yesterdayNewCount;
	}
	public int getYesterdayTotalCount() {
		return yesterdayTotalCount;
	}
	public void setYesterdayTotalCount(int yesterdayTotalCount) {
		this.yesterdayTotalCount = yesterdayTotalCount;
	}
	public int getThisMonthNewCount() {
		return thisMonthNewCount;
	}
	public void setThisMonthNewCount(int thisMonthNewCount) {
		this.thisMonthNewCount = thisMonthNewCount;
	}
	public int getThisMonthTotalCount() {
		return thisMonthTotalCount;
	}
	public void setThisMonthTotalCount(int thisMonthTotalCount) {
		this.thisMonthTotalCount = thisMonthTotalCount;
	}
	public int getIntervalNewCount() {
		return intervalNewCount;
	}
	public void setIntervalNewCount(int intervalNewCount) {
		this.intervalNewCount = intervalNewCount;
	}
	public int getIntervalTotalCount() {
		return intervalTotalCount;
	}
	public void setIntervalTotalCount(int intervalTotalCount) {
		this.intervalTotalCount = intervalTotalCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
	
}
