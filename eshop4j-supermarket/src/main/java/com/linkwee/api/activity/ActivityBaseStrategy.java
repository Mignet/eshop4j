package com.linkwee.api.activity;

import java.util.List;

import com.linkwee.web.model.ActActivityPrizeCase;
import com.linkwee.web.model.ActivityList;

public interface ActivityBaseStrategy {

	/**
	 * 活动奖励生产策略
	 * @param activity
	 * @param prizeStyle
	 * @return
	 */
	public List<ActActivityPrizeCase> generateAward(ActivityList activity,Integer prizeStyle,Integer conditionCase);
}
