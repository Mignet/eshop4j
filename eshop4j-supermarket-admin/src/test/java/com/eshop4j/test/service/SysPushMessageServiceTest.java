package com.eshop4j.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.eshop4j.test.TestSupport;
import com.eshop4j.web.model.mc.SysPushMessage;
import com.eshop4j.web.service.SysPushMessageService;

public class SysPushMessageServiceTest extends TestSupport{
	@Resource
	private SysPushMessageService sysPushMessageService;
	
	  @Test
	    public void testDeleteBatch() throws Exception {
	    	start();
	    	List<SysPushMessage> list = Lists.newArrayList();
	    	SysPushMessage condit = new SysPushMessage();
	    	condit.setUserId("1d06a0942eb54adfa6eafabf2f959033");
	    	list= sysPushMessageService.selectListByCondition(condit);
	    	System.out.println(sysPushMessageService.deleteBatch(list));
	        end();
	    }
	  

	  
	
}
