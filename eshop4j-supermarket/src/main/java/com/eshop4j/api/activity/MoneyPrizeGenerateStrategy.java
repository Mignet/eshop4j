package com.eshop4j.api.activity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.eshop4j.core.util.NumberUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.model.ActActivityPrizeCase;
import com.eshop4j.web.model.ActActivityWinningRecord;
import com.eshop4j.web.model.CrmUserInfo;
import com.eshop4j.web.service.ActActivityWinningRecordService;
import com.eshop4j.web.service.CrmUserInfoService;

@Component
public class MoneyPrizeGenerateStrategy implements ActivityPrizeGenerateStrategy {

	@Resource
	private ActActivityWinningRecordService actActivityWinningRecordService;
	
	@Resource
    private CrmUserInfoService crmUserInfoService;
	
	@Override
	public ActActivityWinningRecord generateActivityRecord(ActActivityPrizeCase actActivityPrizeCase, String userId) {
		ActActivityWinningRecord actActivityWinningRecordResult = new ActActivityWinningRecord();
		ActActivityWinningRecord actActivityWinningRecord = actActivityWinningRecordService.queryNotIssuedPrize(actActivityPrizeCase,userId);
		if(actActivityWinningRecord != null){
			//奖品记录已存在未分发
			actActivityWinningRecordResult = actActivityWinningRecord;
		}else{
			List<ActActivityWinningRecord> actActivityWinningRecordList = actActivityWinningRecordService.queryIssuedPrize(actActivityPrizeCase,userId);
			
			BigDecimal leftPrizeMoney = actActivityPrizeCase.getPrizeAmt();
			BigDecimal nextPrizeMoney;
			
			if(actActivityWinningRecordList.size() != (actActivityPrizeCase.getIssuedTimes() -1)){
				for(ActActivityWinningRecord actActivityWinningRecordTemp : actActivityWinningRecordList){
					leftPrizeMoney = new BigDecimal(leftPrizeMoney.doubleValue() - actActivityWinningRecordTemp.getWinningAmt().doubleValue());
				}
				Random random = new Random();
				nextPrizeMoney = new BigDecimal(NumberUtils.getFormat(random.nextDouble() * leftPrizeMoney.doubleValue(), "0.0"));
			}else {
				for(ActActivityWinningRecord actActivityWinningRecordTemp : actActivityWinningRecordList){
					leftPrizeMoney = new BigDecimal(leftPrizeMoney.doubleValue() - actActivityWinningRecordTemp.getWinningAmt().doubleValue());
				}
				nextPrizeMoney = leftPrizeMoney;
			}
					
			actActivityWinningRecordResult.setActivityId(actActivityPrizeCase.getActivityId());
			actActivityWinningRecordResult.setCreator("sys");
			actActivityWinningRecordResult.setCrtTime(new Date());
			actActivityWinningRecordResult.setPrizeStyle(actActivityPrizeCase.getPrizeStyle());
			actActivityWinningRecordResult.setIssued(0);
			CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(userId);
			actActivityWinningRecordResult.setMobile(crmUserInfo.getMobile());
			actActivityWinningRecordResult.setOrderDesc(actActivityPrizeCase.getCaseDesc());
			actActivityWinningRecordResult.setRecordId(StringUtils.getUUID());
			actActivityWinningRecordResult.setUserId(userId);
			actActivityWinningRecordResult.setWinningAmt(nextPrizeMoney);
			actActivityWinningRecordResult.setConditionCase(actActivityPrizeCase.getConditionCase());
			actActivityWinningRecordResult.setWinningType(actActivityPrizeCase.getPrizeType());
			actActivityWinningRecordResult.setPrizeCase(actActivityPrizeCase.getPrizeCase());
			actActivityWinningRecordResult.setWasteTimes(actActivityPrizeCase.getWasteTimes());
			actActivityWinningRecordService.insert(actActivityWinningRecordResult);
		}
		return actActivityWinningRecordResult;
	}

}
