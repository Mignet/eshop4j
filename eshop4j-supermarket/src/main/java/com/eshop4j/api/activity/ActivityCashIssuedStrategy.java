package com.eshop4j.api.activity;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.eshop4j.web.model.ActActivityWinningRecord;
import com.eshop4j.web.model.ActivityList;
import com.eshop4j.web.model.acc.AcAccountRecharge;
import com.eshop4j.web.service.AcAccountBindService;

@Component
public class ActivityCashIssuedStrategy implements ActivityPrizeIssuedStrategy{

	@Resource
	private AcAccountBindService accountbindService;
	
	@Override
	public void issue(ActActivityWinningRecord actActivityWinningRecord,String userId,Integer userType,ActivityList activity) {
		//给该用户账号充值
		AcAccountRecharge recharge = new AcAccountRecharge();
		recharge.setRedpacketId(actActivityWinningRecord.getRecordId());
		recharge.setTransAmount(actActivityWinningRecord.getWinningAmt());
		recharge.setUserId(userId);
		recharge.setUserType(userType);
		recharge.setTransType(3);
		recharge.setRemark(activity.getActivityName()+"活动奖励");
		try {
			accountbindService.accountRecharge(recharge);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
