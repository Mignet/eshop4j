package com.eshop4j.api.activity;

import com.eshop4j.web.model.ActActivityWinningRecord;
import com.eshop4j.web.model.ActivityList;

public interface ActivityPrizeIssuedStrategy {

	/**
	 * 活动奖励发放策略
	 * @param actActivityWinningRecord
	 */
	public void issue(ActActivityWinningRecord actActivityWinningRecord,String userId,Integer userType,ActivityList activity);
}
