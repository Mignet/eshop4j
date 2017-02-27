package com.eshop4j.test.job;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eshop4j.test.TestSupport;
import com.eshop4j.web.service.WeiXinMsgService;

public class WeiXinMsgServiceTest extends TestSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeiXinMsgServiceTest.class);
	
	@Resource
	private WeiXinMsgService weiXinMsgService;
	
	@Test
	public void process() {
		System.out.println("-----------------WeiXinMsgService-------------");
		 try {
			 weiXinMsgService.updateWeiXinAccToken();
			} catch (Exception e) {
				LOGGER.error(">>>>>>>>>>>>>>更新微信accToken{}",e);
			}
		
	}
}
