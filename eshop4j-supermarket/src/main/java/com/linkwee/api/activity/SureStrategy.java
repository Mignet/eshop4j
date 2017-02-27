package com.linkwee.api.activity;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.linkwee.web.model.ActActivityPrizeCase;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.service.ActActivityPrizeCaseService;

@Component
public class SureStrategy implements ActivityBaseStrategy {

	@Resource
	private ActActivityPrizeCaseService actActivityPrizeCaseService;
	
	@Override
	public List<ActActivityPrizeCase> generateAward(ActivityList activity,Integer prizeStyle,Integer conditionCase) {
		ActActivityPrizeCase actActivityPrizeCase = new ActActivityPrizeCase();
		actActivityPrizeCase.setActivityId(String.valueOf(activity.getId()));
		actActivityPrizeCase.setCaseType(2);
		actActivityPrizeCase.setPrizeStyle(prizeStyle);
		actActivityPrizeCase.setConditionCase(conditionCase);
		//目前只处理了一个奖项的情况
		List<ActActivityPrizeCase> actActivityPrizeCaseResult = actActivityPrizeCaseService.selectListByCondition(actActivityPrizeCase);		
		return actActivityPrizeCaseResult;
	}

}
