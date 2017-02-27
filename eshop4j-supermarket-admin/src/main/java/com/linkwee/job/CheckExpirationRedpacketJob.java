package com.linkwee.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.act.redpacket.service.ActRedpacketSendRecordService;
import com.linkwee.web.service.impl.AcBankCardInfoServiceImpl;

@Component
public class CheckExpirationRedpacketJob extends AbstractSimpleElasticJob{
	private static final Logger LOGGER = LoggerFactory.getLogger(AcBankCardInfoServiceImpl.class);
	
	@Autowired
	private ActRedpacketSendRecordService redpacketSendRecordService;

	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		try {
			redpacketSendRecordService.checkExpirationRedpacket();
		} catch (Exception e) {
			LOGGER.warn("checkExpirationRedpacket exception", e.getMessage());
		}
	}
}
