package com.linkwee.api.activity.utils;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.linkwee.api.activity.ActivityBaseStrategy;
import com.linkwee.api.activity.ActivityCashIssuedStrategy;
import com.linkwee.api.activity.ActivityPrizeGenerateStrategy;
import com.linkwee.api.activity.ActivityPrizeIssuedStrategy;
import com.linkwee.api.activity.MoneyPrizeGenerateStrategy;
import com.linkwee.api.activity.NormalPrizeGenerateStrategy;
import com.linkwee.api.activity.ProbabilityStrategy;
import com.linkwee.api.activity.SureStrategy;
import com.linkwee.web.model.ActActivityCondition;
import com.linkwee.web.model.ActActivityPrizeCase;
import com.linkwee.web.model.ActActivityPrizeIssue;
import com.linkwee.web.model.ActActivityPrizeStrategy;
import com.linkwee.web.model.ActActivityWinningRecord;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.service.ActActivityPrizeIssueService;
import com.linkwee.web.service.ActActivityPrizeStrategyService;
import com.linkwee.web.service.ActActivityWinningRecordService;

@Component
public class BaseActivityHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseActivityHelper.class);
	
	@Resource
	private ActActivityPrizeStrategyService actActivityPrizeStrategyService;
	
	@Resource
	private ProbabilityStrategy probabilityStrategy;
	
	@Resource
	private SureStrategy sureStrategy;
	
	@Resource
	private ActActivityWinningRecordService actActivityWinningRecordService;
	
	@Resource
	private MoneyPrizeGenerateStrategy moneyPrizeGenerateStrategy;
	
	@Resource
	private NormalPrizeGenerateStrategy normalPrizeGenerateStrategy;
	
	@Resource
	private ActivityCashIssuedStrategy activityCashIssuedStrategy;
	
	@Resource
	private ActActivityPrizeIssueService actActivityPrizeIssueService;
	
    public List<ActActivityPrizeCase> generatePrizeCase(ActivityList activity,Integer prizeStyle,Integer conditionCase) {
    	
    	//一个活动对应一个奖品策略............一个活动一个等级对应一个奖品策略
    	ActActivityPrizeStrategy actActivityPrizeStrategy = new ActActivityPrizeStrategy();
    	actActivityPrizeStrategy.setActivityId(String.valueOf(activity.getId()));
    	//抽奖方式
    	actActivityPrizeStrategy.setPrizeStyle(prizeStyle);
    	actActivityPrizeStrategy.setConditionCase(conditionCase);
    	ActActivityPrizeStrategy actActivityPrizeStrategyResult = actActivityPrizeStrategyService.selectOne(actActivityPrizeStrategy);
    	ActivityBaseStrategy activityBaseStrategy =  null;
    	if(actActivityPrizeStrategyResult.getStrategyType() == 1){
    		//概率型
    		activityBaseStrategy = probabilityStrategy;
    	}else if(actActivityPrizeStrategyResult.getStrategyType() == 2){
    		//确定型
    		activityBaseStrategy = sureStrategy;		
    	}else{
    		
    	}
    	List<ActActivityPrizeCase> actActivityPrizeCaseList = activityBaseStrategy.generateAward(activity,prizeStyle,conditionCase);
    	return actActivityPrizeCaseList;
    }
    
    public List<ActActivityWinningRecord> generateWinningRecord(ActivityList activity, String userId, ActActivityCondition actActivityCondition){
    	//生成奖品等级时，先查询是否有未分发的奖品
		ActActivityWinningRecord actActivityWinningRecord = new ActActivityWinningRecord();
		actActivityWinningRecord.setActivityId(String.valueOf(activity.getId()));
		actActivityWinningRecord.setUserId(userId);
		actActivityWinningRecord.setPrizeStyle(1);
		actActivityWinningRecord.setIssued(0);
		actActivityWinningRecord.setConditionCase(actActivityCondition.getConditionCase());
			
		List<ActActivityWinningRecord> actActivityWinningRecordList = actActivityWinningRecordService.queryNotIssueWinningRecord(actActivityWinningRecord);
		if(actActivityWinningRecordList == null || actActivityWinningRecordList.size() == 0){						
			List<ActActivityPrizeCase> actActivityPrizeCaseList = generatePrizeCase(activity,1,actActivityCondition.getConditionCase());					 						
			for(ActActivityPrizeCase actActivityPrizeCase : actActivityPrizeCaseList){
				ActivityPrizeGenerateStrategy activityPrizeIssuedStrategy = null;
				//确定型奖励，按多次分发
				if(actActivityPrizeCase.getCaseType() == 2 && actActivityPrizeCase.getIssuedTimes() > 1){					
					//按发放策略分发
					activityPrizeIssuedStrategy = moneyPrizeGenerateStrategy;
	    		}else if(actActivityPrizeCase.getIssuedTimes() == 1){
	    			activityPrizeIssuedStrategy = normalPrizeGenerateStrategy;					
	    		}else{
	    			LOGGER.info("奖品发放次数小于1，不合理，请重新设置！！！！");
	    		}					
				ActActivityWinningRecord actActivityWinningRecordTemp = activityPrizeIssuedStrategy.generateActivityRecord(actActivityPrizeCase,userId);
				actActivityWinningRecordList.add(actActivityWinningRecordTemp);
			}						
		}
		
		return actActivityWinningRecordList;
    }
    
    public List<ActActivityWinningRecord> generateWinningRecordInGradeCase(ActivityList activity, String userId, ActActivityCondition actActivityCondition){
    	//生成奖品等级时，先查询是否有未分发的奖品
		ActActivityWinningRecord actActivityWinningRecord = new ActActivityWinningRecord();
		actActivityWinningRecord.setActivityId(String.valueOf(activity.getId()));
		actActivityWinningRecord.setUserId(userId);
		actActivityWinningRecord.setPrizeStyle(1);
		actActivityWinningRecord.setIssued(0);
		actActivityWinningRecord.setConditionCase(actActivityCondition.getConditionCase());
		
		List<ActActivityWinningRecord> actActivityWinningRecordList = actActivityWinningRecordService.queryNotIssueWinningRecord(actActivityWinningRecord);
		if(actActivityWinningRecordList == null || actActivityWinningRecordList.size() == 0){						
			List<ActActivityPrizeCase> actActivityPrizeCaseList = generatePrizeCase(activity,1,actActivityCondition.getConditionCase());					 						
			for(ActActivityPrizeCase actActivityPrizeCase : actActivityPrizeCaseList){		
				//已经发放完成的等级奖励不再发放
				ActActivityWinningRecord actActivityWinningRecordTemp = new ActActivityWinningRecord();
				actActivityWinningRecordTemp.setActivityId(actActivityPrizeCase.getActivityId());
				actActivityWinningRecordTemp.setConditionCase(actActivityPrizeCase.getConditionCase());
				actActivityWinningRecordTemp.setPrizeStyle(actActivityPrizeCase.getPrizeStyle());
				actActivityWinningRecordTemp.setUserId(userId);
				actActivityWinningRecordTemp.setPrizeCase(actActivityPrizeCase.getPrizeCase());
				List<ActActivityWinningRecord> actActivityWinningRecordListTemp = actActivityWinningRecordService.selectListByCondition(actActivityWinningRecordTemp);	
				Integer wasteTimes=0;
				for(ActActivityWinningRecord temp : actActivityWinningRecordListTemp){
					wasteTimes += temp.getWasteTimes();
				}
				if(actActivityPrizeCase.getIssuedTimes() == wasteTimes && actActivityPrizeCase.getIssuedTimes() != 0){
					continue;
				}
				
				ActivityPrizeGenerateStrategy activityPrizeIssuedStrategy = null;
				//确定型奖励，按多次分发
				if(actActivityPrizeCase.getCaseType() == 2 && actActivityPrizeCase.getIssuedTimes() > 1){					
					//按发放策略分发
					activityPrizeIssuedStrategy = moneyPrizeGenerateStrategy;
	    		}else if(actActivityPrizeCase.getIssuedTimes() == 1){
	    			activityPrizeIssuedStrategy = normalPrizeGenerateStrategy;					
	    		}else{
	    			LOGGER.info("奖品发放次数小于1，不合理，请重新设置！！！！");
	    		}					
				ActActivityWinningRecord actActivityWinningRecordTemp2 = activityPrizeIssuedStrategy.generateActivityRecord(actActivityPrizeCase,userId);
				actActivityWinningRecordList.add(actActivityWinningRecordTemp2);
			}						
		}
		return actActivityWinningRecordList;
    }
    
    public void issueWinningRecords(ActivityList activity, String userId, ActActivityCondition actActivityCondition,Integer userType,Integer prizeStyle){
    	ActActivityWinningRecord actActivityWinningRecord = new ActActivityWinningRecord();
		actActivityWinningRecord.setActivityId(String.valueOf(activity.getId()));
		actActivityWinningRecord.setUserId(userId);
		actActivityWinningRecord.setPrizeStyle(prizeStyle);
		actActivityWinningRecord.setIssued(0);
		actActivityWinningRecord.setConditionCase(actActivityCondition.getConditionCase());
		List<ActActivityWinningRecord> actActivityWinningRecordList = actActivityWinningRecordService.queryNotIssueWinningRecord(actActivityWinningRecord);
		if(actActivityWinningRecordList != null && actActivityWinningRecordList.size() > 0){
			for(ActActivityWinningRecord actActivityWinningRecordTemp : actActivityWinningRecordList){
				ActivityPrizeIssuedStrategy activityPrizeIssuedStrategy = null;
				if(actActivityWinningRecordTemp.getWinningType() == 1){
					activityPrizeIssuedStrategy = activityCashIssuedStrategy;
				}
				if(activityPrizeIssuedStrategy != null){
					activityPrizeIssuedStrategy.issue(actActivityWinningRecordTemp, userId, userType, activity);
					actActivityWinningRecordTemp.setIssued(1);
    				actActivityWinningRecordTemp.setIssuedTime(new Date());
        			actActivityWinningRecordService.update(actActivityWinningRecordTemp);
				}else {
					ActActivityPrizeIssue actActivityPrizeIssue = new ActActivityPrizeIssue();
					actActivityPrizeIssue.setActivityId(actActivityWinningRecordTemp.getActivityId());
					actActivityPrizeIssue.setConditionCase(actActivityWinningRecordTemp.getConditionCase());
					actActivityPrizeIssue.setPrizeCase(actActivityWinningRecordTemp.getPrizeCase());
					List<ActActivityPrizeIssue> actActivityPrizeIssueList = actActivityPrizeIssueService.selectListByCondition(actActivityPrizeIssue);
					if(actActivityPrizeIssueList != null && actActivityPrizeIssueList.size() > 0){
						actActivityPrizeIssueService.prizeIssue(actActivityWinningRecordTemp,userId);
						actActivityWinningRecordTemp.setIssued(1);
	    				actActivityWinningRecordTemp.setIssuedTime(new Date());
	        			actActivityWinningRecordService.update(actActivityWinningRecordTemp);
					}else {
						actActivityWinningRecordTemp.setIssuedTime(new Date());
	        			actActivityWinningRecordService.update(actActivityWinningRecordTemp);
					}
					
				}
			}
		}
    }
    
    public List<ActActivityWinningRecord> generateWinningRecordMultiTimes(ActivityList activity, String userId, ActActivityCondition actActivityCondition, Integer prizeStyle){
    	//生成奖品等级时，先查询是否有未分发的奖品
		ActActivityWinningRecord actActivityWinningRecord = new ActActivityWinningRecord();
		actActivityWinningRecord.setActivityId(String.valueOf(activity.getId()));
		actActivityWinningRecord.setUserId(userId);
		actActivityWinningRecord.setPrizeStyle(prizeStyle);
		actActivityWinningRecord.setIssued(0);	
		actActivityWinningRecord.setConditionCase(actActivityCondition.getConditionCase());
			
		List<ActActivityWinningRecord> actActivityWinningRecordList = actActivityWinningRecordService.queryNotIssueWinningRecord(actActivityWinningRecord);
		if(actActivityWinningRecordList == null || actActivityWinningRecordList.size() == 0){	
			
			ActActivityPrizeStrategy actActivityPrizeStrategy = new ActActivityPrizeStrategy();
	    	actActivityPrizeStrategy.setActivityId(String.valueOf(activity.getId()));
	    	//抽奖方式
	    	actActivityPrizeStrategy.setPrizeStyle(prizeStyle);
	    	actActivityPrizeStrategy.setConditionCase(actActivityCondition.getConditionCase());
	    	ActActivityPrizeStrategy actActivityPrizeStrategyResult = actActivityPrizeStrategyService.selectOne(actActivityPrizeStrategy);
	    	
	    	for(int i = 0; i < actActivityPrizeStrategyResult.getIssueTimes(); i++){
	    		List<ActActivityPrizeCase> actActivityPrizeCaseList = generatePrizeCase(activity,prizeStyle,actActivityCondition.getConditionCase());					 						
				for(ActActivityPrizeCase actActivityPrizeCase : actActivityPrizeCaseList){
					ActivityPrizeGenerateStrategy activityPrizeIssuedStrategy = null;
					//确定型奖励，按多次分发
					if(actActivityPrizeCase.getCaseType() == 2 && actActivityPrizeCase.getIssuedTimes() > 1){					
						//按发放策略分发
						activityPrizeIssuedStrategy = moneyPrizeGenerateStrategy;
		    		}else if(actActivityPrizeCase.getIssuedTimes() == 1){
		    			activityPrizeIssuedStrategy = normalPrizeGenerateStrategy;					
		    		}else{
		    			LOGGER.info("奖品发放次数小于1，不合理，请重新设置！！！！");
		    		}					
					ActActivityWinningRecord actActivityWinningRecordTemp = activityPrizeIssuedStrategy.generateActivityRecord(actActivityPrizeCase,userId);
					actActivityWinningRecordList.add(actActivityWinningRecordTemp);
				}
	    	}															
		}
		return actActivityWinningRecordList;
    }
}
