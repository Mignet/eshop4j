package com.linkwee.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.linkwee.test.TestSupport;
import com.linkwee.web.model.mc.SysPushMessage;
import com.linkwee.web.service.SysPushMessageService;

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
