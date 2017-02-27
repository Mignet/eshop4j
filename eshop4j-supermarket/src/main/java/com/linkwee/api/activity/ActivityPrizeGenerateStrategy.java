package com.linkwee.api.activity;

import com.linkwee.web.model.ActActivityPrizeCase;
import com.linkwee.web.model.ActActivityWinningRecord;

public interface ActivityPrizeGenerateStrategy {
	public ActActivityWinningRecord generateActivityRecord(ActActivityPrizeCase actActivityPrizeCase, String userId);
}
