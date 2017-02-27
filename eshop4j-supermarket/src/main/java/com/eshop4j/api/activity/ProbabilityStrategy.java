package com.eshop4j.api.activity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.eshop4j.web.model.ActActivityPrizeCase;
import com.eshop4j.web.model.ActivityList;
import com.eshop4j.web.service.ActActivityPrizeCaseService;

@Component
public class ProbabilityStrategy implements ActivityBaseStrategy{
	
	@Resource
	private ActActivityPrizeCaseService actActivityPrizeCaseService;

	public List<ActActivityPrizeCase> generateAward(ActivityList activity,Integer prizeStyle,Integer conditionCase) {
		ActActivityPrizeCase actActivityPrizeCase = new ActActivityPrizeCase();
		actActivityPrizeCase.setActivityId(String.valueOf(activity.getId()));
		actActivityPrizeCase.setCaseType(1);
		actActivityPrizeCase.setPrizeStyle(prizeStyle);
		actActivityPrizeCase.setConditionCase(conditionCase);
		List<ActActivityPrizeCase> actActivityPrizeCaseList = actActivityPrizeCaseService.selectListByCondition(actActivityPrizeCase);
		int sum = 0;
		for(ActActivityPrizeCase actActivityPrizeCaseTemp : actActivityPrizeCaseList){
			sum += actActivityPrizeCaseTemp.getCaseVariable();
		}
		long result = randomnum(1, sum);
        int line = 0;
        int temp = 0;
        ActActivityPrizeCase returnobj = null;
        for (int i = 0; i < actActivityPrizeCaseList.size(); i++) {
        	ActActivityPrizeCase obj2 = actActivityPrizeCaseList.get(i);
            int c = obj2.getCaseVariable();
            temp = temp + c;
            line = sum - temp;
            if (c != 0) {
                if (result > line && result <= (line + c)) {
                    returnobj = obj2;
                    break;
                }
            }
        }
        List<ActActivityPrizeCase> resultList = new ArrayList<ActActivityPrizeCase>();
        resultList.add(returnobj);
        return resultList;
    }
	
	// 获取2个值之间的随机数
    private static long randomnum(int smin, int smax){
        int range = smax - smin;
        double rand = Math.random();
        return (smin + Math.round(rand * range));
    }
}
