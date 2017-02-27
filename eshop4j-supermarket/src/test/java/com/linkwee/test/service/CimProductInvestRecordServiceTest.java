package com.linkwee.test.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.linkwee.test.TestSupport;
import com.linkwee.web.service.CimProductInvestRecordService;

public class CimProductInvestRecordServiceTest extends TestSupport {
	
	@Resource
	private CimProductInvestRecordService cimProductInvestRecordService;
	 @Test
	    public void testSysMsgPage() throws Exception {
	    	start();
	    	System.out.println(cimProductInvestRecordService.queryCfpNewlyDynamicUnReadCount("0891f28a9886436d9313ea0af073c7b8"));;
	        end();
	    }
}
