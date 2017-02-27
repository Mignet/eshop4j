package com.eshop4j.api.activity;

import com.eshop4j.web.model.ActActivityPrizeCase;
import com.eshop4j.web.model.ActActivityWinningRecord;

public interface ActivityPrizeGenerateStrategy {
	public ActActivityWinningRecord generateActivityRecord(ActActivityPrizeCase actActivityPrizeCase, String userId);
}
