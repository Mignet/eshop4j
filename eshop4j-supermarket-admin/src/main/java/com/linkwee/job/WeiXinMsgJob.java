package com.linkwee.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.web.service.WeiXinMsgService;
@Component
public class WeiXinMsgJob extends AbstractSimpleElasticJob {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeiXinMsgJob.class);
	
	@Resource
	private WeiXinMsgService weiXinMsgService;

	/**
	 * 更新微信accToken
	 * */
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>更新微信accToken start");
		
		weiXinMsgService.updateWeiXinAccToken();
		
		weiXinMsgService.updateWeiXinAccTokenLieCai();
			
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>更新微信accToken end");
	}
   
	
}
