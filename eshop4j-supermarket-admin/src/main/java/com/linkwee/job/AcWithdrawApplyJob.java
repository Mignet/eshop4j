package com.linkwee.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.web.service.AcWithdrawApplyService;

@Component
public class AcWithdrawApplyJob extends AbstractSimpleElasticJob{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AcWithdrawApplyJob.class);
	
	@Resource
	private AcWithdrawApplyService acWithdrawApplyService;
	
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>同步提现记录状态start");
	    try {
	    	acWithdrawApplyService.queryWithdrawforJob();//批量支付接口
		} catch (Exception e) {
			LOGGER.error(">>>>>>>>>>>>>>同步批量提现记录接口定时任务异常{}",e);
			e.printStackTrace();
		}
	    
//	    try {
//	    	acWithdrawApplyService.queryOnePayWithdrawforJob();//单个支付接口
//		} catch (Exception e) {
//			LOGGER.error(">>>>>>>>>>>>>>同步单个提现记录接口定时任务异常{}",e);
//			e.printStackTrace();
//		}
		
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>同步提现记录状态end");
	}
	
}
