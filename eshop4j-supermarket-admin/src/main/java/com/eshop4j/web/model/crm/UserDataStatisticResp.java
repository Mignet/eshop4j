package com.eshop4j.web.model.crm;

import com.eshop4j.core.base.BaseEntity;

/**
 * 
 * @描述：用户统计数据
 *
 * @author Bob
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class UserDataStatisticResp extends BaseEntity {
	private static final long serialVersionUID = -226463495944568640L;

	/**
	 * 今天人数
	 */
	private int todayCount;
	/**
	 * 昨天人数
	 */
	private int yesterdayCount;
	/**
	 * 本月人数
	 */
	private int thisMonthCount;
	/**
	 * 搜索区间人数
	 */
	private int intervalCount;
	/**
	 * 总人数
	 */
	private int totalCount;
	
	public int getTodayCount() {
		return todayCount;
	}
	public void setTodayCount(int todayCount) {
		this.todayCount = todayCount;
	}
	public int getYesterdayCount() {
		return yesterdayCount;
	}
	public void setYesterdayCount(int yesterdayCount) {
		this.yesterdayCount = yesterdayCount;
	}
	public int getThisMonthCount() {
		return thisMonthCount;
	}
	public void setThisMonthCount(int thisMonthCount) {
		this.thisMonthCount = thisMonthCount;
	}
	public int getIntervalCount() {
		return intervalCount;
	}
	public void setIntervalCount(int intervalCount) {
		this.intervalCount = intervalCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
}
