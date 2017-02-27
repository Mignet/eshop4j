package com.eshop4j.job;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.eshop4j.xoss.helper.PushMessageHelper;

@Component
public class SysPushMessageThreadJob extends AbstractSimpleElasticJob{
	
	@Resource
	private PushMessageHelper pushMessageHelper;
	
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		pushMessageHelper.SysPushMessageTask();
	}
	
}
