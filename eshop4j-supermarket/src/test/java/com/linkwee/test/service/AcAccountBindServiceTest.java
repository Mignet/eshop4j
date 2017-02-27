package com.linkwee.test.service;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linkwee.core.util.StringUtils;
import com.linkwee.test.TestSupport;
import com.linkwee.web.model.acc.AcAccountBind;
import com.linkwee.web.model.acc.AcAccountRecharge;
import com.linkwee.web.model.acc.AcBankCardInfo;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.AcBankCardInfoService;
import com.linkwee.web.service.AcWithdrawApplyService;

public class AcAccountBindServiceTest extends TestSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(AcAccountBindServiceTest.class);
	
	@Resource
	private AcAccountBindService acAccountBindService;
	
	@Test//提供的充值接口
	public void testAccountRecharge() throws Exception{
		AcAccountRecharge re = new AcAccountRecharge();
		re.setRedpacketId("000000000000000000");
		re.setRemark("测试红包");
		re.setTransAmount(new BigDecimal(30));
		re.setTransType(3);
		re.setUserType(2);
		re.setUserId("0891f28a9886436d9313ea0af073c7b8");
		acAccountBindService.accountRecharge(re);
	}
	
	@Resource 
	private AcBankCardInfoService acBankCardInfoService;
	
	
	@Test//同步导入的bank_card_id
	public void testDaoData() throws Exception{
		List<AcBankCardInfo> list = acBankCardInfoService.selectListByCondition(null);
		
		for(AcBankCardInfo info : list){
			String bankCardId = StringUtils.getUUID();
			AcBankCardInfo acinfo = new AcBankCardInfo();
			acinfo.setBankCardId(bankCardId);
			acinfo.setUserId(info.getUserId());
			acinfo.setBankCard(info.getBankCard());
			acBankCardInfoService.update(acinfo);
			AcAccountBind ac = new AcAccountBind();
			ac.setUserId(info.getUserId());
			ac.setBankCardId(bankCardId);
			acAccountBindService.update(ac);
		}
	}
	
	
	@Resource
	private AcWithdrawApplyService acWithdrawApplyService;
	
	@Test//测试提现定时任务，同步快钱提现状态
	public void process() {
		System.out.println("-----------------acWithdrawApplyService-------------");
		 try {
		    	acWithdrawApplyService.queryWithdrawforJob();
			} catch (Exception e) {
				LOGGER.error(">>>>>>>>>>>>>>同步提现记录定时任务异常{}",e);
			}
		
	}
}
