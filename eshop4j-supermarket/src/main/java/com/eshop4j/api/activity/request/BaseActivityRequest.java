package com.eshop4j.api.activity.request;

public class BaseActivityRequest {
	/**
	 * 活动名称
	 */
	private String activityName;
	/**
	 * 活动平台
	 */
	private String activityPlatform;
	/**
	 * 条件等级
	 */
	private Integer conditionCase;
	/**
	 * 抽奖方式
	 */
	private Integer prizeStyle;
	
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityPlatform() {
		return activityPlatform;
	}
	public void setActivityPlatform(String activityPlatform) {
		this.activityPlatform = activityPlatform;
	}
	public Integer getConditionCase() {
		return conditionCase;
	}
	public void setConditionCase(Integer conditionCase) {
		this.conditionCase = conditionCase;
	}
	public Integer getPrizeStyle() {
		return prizeStyle;
	}
	public void setPrizeStyle(Integer prizeStyle) {
		this.prizeStyle = prizeStyle;
	}
}
