package com.linkwee.test.service;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.linkwee.tc.fee.model.vo.FeedetailWrapper;
import com.linkwee.test.TestSupport;
import com.linkwee.web.service.WeiXinMsgService;

public class WeiXinMsgTest extends TestSupport {

    @Resource
    private WeiXinMsgService weiXinMsgService;

    @Test
    public void testInvestSuccesssendWeiXinMsg() throws NoSuchAlgorithmException, InvalidKeySpecException {
    	start();
    	List<FeedetailWrapper> feedetails = new ArrayList<FeedetailWrapper>(); 
    	FeedetailWrapper fee1= new FeedetailWrapper();
    	fee1.setProductOrgId("OPEN_XIAONIUZAIXIAN_WEB");
    	fee1.setProductName("日日牛");
    	fee1.setInvestmentAmount(new BigDecimal(1000));
    	fee1.setProductId("7B70F90B259B43C6B080221F1BC3309C");
    	fee1.setInvestorId("2895bf4b85b74a6db14a5ef9d010a174");
    	fee1.setFeeamount(new BigDecimal(10));
    	fee1.setCfplannerId("3a56eba944334f67bd2f85bf69981a7b");
    	
    	FeedetailWrapper fee2= new FeedetailWrapper();
    	fee2.setProductOrgId("OPEN_XIAONIUZAIXIAN_WEB");
    	fee2.setProductName("日日牛");
    	fee2.setInvestmentAmount(new BigDecimal(1000));
    	fee2.setProductId("7B70F90B259B43C6B080221F1BC3309C");
    	fee2.setInvestorId("2895bf4b85b74a6db14a5ef9d010a174");
    	fee2.setFeeamount(new BigDecimal(20));
    	fee2.setParentCfplannerId("3a56eba944334f67bd2f85bf69981a7b");
    	
    	FeedetailWrapper fee3= new FeedetailWrapper();
    	fee3.setProductOrgId("OPEN_XIAONIUZAIXIAN_WEB");
    	fee3.setProductName("日日牛");
    	fee3.setInvestmentAmount(new BigDecimal(1000));
    	fee3.setProductId("7B70F90B259B43C6B080221F1BC3309C");
    	fee3.setInvestorId("2895bf4b85b74a6db14a5ef9d010a174");
    	fee3.setFeeamount(new BigDecimal(30));
    	fee3.setGrandParentCfplannerId("3a56eba944334f67bd2f85bf69981a7b");
    	feedetails.add(fee1);
    	feedetails.add(fee2);
    	feedetails.add(fee3);
    	weiXinMsgService.investSuccesssendWeiXinMsg(feedetails);
    	
    	
    	
    	
        end();
    }
}
