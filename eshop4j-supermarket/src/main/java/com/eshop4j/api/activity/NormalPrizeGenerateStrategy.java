package com.eshop4j.api.activity;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.model.ActActivityPrizeCase;
import com.eshop4j.web.model.ActActivityWinningRecord;
import com.eshop4j.web.model.CrmUserInfo;
import com.eshop4j.web.service.ActActivityWinningRecordService;
import com.eshop4j.web.service.CrmUserInfoService;

@Component
public class NormalPrizeGenerateStrategy implements ActivityPrizeGenerateStrategy {
	
	@Resource
	private ActActivityWinningRecordService actActivityWinningRecordService;
	
	@Resource
    private CrmUserInfoService crmUserInfoService;

	@Override
	public ActActivityWinningRecord generateActivityRecord(ActActivityPrizeCase actActivityPrizeCase, String userId) {
		ActActivityWinningRecord actActivityWinningRecordResult = new ActActivityWinningRecord();
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
		actActivityWinningRecordResult.setWinningAmt(actActivityPrizeCase.getPrizeAmt());
		actActivityWinningRecordResult.setConditionCase(actActivityPrizeCase.getConditionCase());
		actActivityWinningRecordResult.setWinningType(actActivityPrizeCase.getPrizeType());
		actActivityWinningRecordResult.setPrizeCase(actActivityPrizeCase.getPrizeCase());
		actActivityWinningRecordResult.setWasteTimes(actActivityPrizeCase.getWasteTimes());
		actActivityWinningRecordService.insert(actActivityWinningRecordResult);
		return actActivityWinningRecordResult;
	}

}
