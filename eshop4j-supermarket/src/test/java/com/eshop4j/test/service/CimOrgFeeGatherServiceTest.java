package com.eshop4j.test.service;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;

import com.eshop4j.core.util.DateUtils;
import com.eshop4j.test.TestSupport;
import com.eshop4j.web.model.vo.InvestRecordWrapper;
import com.eshop4j.web.service.impl.CimOrgFeeGatherServiceImpl;

public class CimOrgFeeGatherServiceTest extends TestSupport{
	
	 @Resource
	 private CimOrgFeeGatherServiceImpl cimOrgFeedetailServiceImpl;
	
    @Test
    public void testSysMsgPage() throws Exception {
    	start();
		
		//investRecordAwares 不为空 则转发投资记录信息
		InvestRecordWrapper investRecordWrapper = new InvestRecordWrapper();
		investRecordWrapper.setBizId("9eca6d6a2ef941d5915f994c69faffb2");//80c4cf1b33ea4dd599249efd019754fe
		investRecordWrapper.setInvestId("3577847");
		investRecordWrapper.setUserId("8b61eedb3ef54522bb751f6a3e529d10");
		investRecordWrapper.setProductId("13FF3406B8C24DC1BA6FB019049075E7");
		investRecordWrapper.setProductType(1);
		investRecordWrapper.setProductName("月月牛Y60201602");
		investRecordWrapper.setProductOrgId("OPEN_XIAONIUZAIXIAN_WEB");
		investRecordWrapper.setProductDays(60);
		investRecordWrapper.setIsRedemption(1);
		investRecordWrapper.setDeadLineMaxValue(360);
		
		investRecordWrapper.setInvestAmt(new BigDecimal(100.0000));
		investRecordWrapper.setInvestTime(DateUtils.parse("2016-08-31 23:08:24", DateUtils.FORMAT_LONG));
		investRecordWrapper.setPlatfromFirstInvest(true);
		
    	
		cimOrgFeedetailServiceImpl.investRecordProcess(investRecordWrapper);
        end();
    }
}
