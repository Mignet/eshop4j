package com.linkwee.api.activity;

import com.linkwee.web.model.ActActivityWinningRecord;
import com.linkwee.web.model.ActivityList;

public interface ActivityPrizeIssuedStrategy {

	/**
	 * 活动奖励发放策略
	 * @param actActivityWinningRecord
	 */
	public void issue(ActActivityWinningRecord actActivityWinningRecord,String userId,Integer userType,ActivityList activity);
}
