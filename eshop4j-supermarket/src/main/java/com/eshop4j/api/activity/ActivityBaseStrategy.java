package com.eshop4j.api.activity;

import java.util.List;

import com.eshop4j.web.model.ActActivityPrizeCase;
import com.eshop4j.web.model.ActivityList;

public interface ActivityBaseStrategy {

	/**
	 * 活动奖励生产策略
	 * @param activity
	 * @param prizeStyle
	 * @return
	 */
	public List<ActActivityPrizeCase> generateAward(ActivityList activity,Integer prizeStyle,Integer conditionCase);
}
