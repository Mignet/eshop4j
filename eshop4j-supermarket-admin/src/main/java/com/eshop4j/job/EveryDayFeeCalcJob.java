package com.eshop4j.job;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.eshop4j.tc.fee.service.FeeCalcService;

@Component
public class EveryDayFeeCalcJob extends AbstractSimpleElasticJob{
	
	@Resource
	private FeeCalcService feeCalcService;
	
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		try {
			feeCalcService.everyDayFeeCalc( new Date());
		} catch (Exception e) {
		}
	}
	
}
