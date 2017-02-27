package com.linkwee.job;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.web.model.CimProduct;
import com.linkwee.web.service.CimProductService;
/**
 * 
 * 演示分布式任务<br>
 * 监控地址:http://localhost:9090/elastic-job-console
 * @author Mignet
 *
 */
@Component
public class MySimpleJob extends AbstractSimpleElasticJob {
	private static final Logger LOGGER = LoggerFactory.getLogger(MySimpleJob.class);
	
	@Resource
	private CimProductService productService;
	
	private static AtomicInteger count = new AtomicInteger(0);
	
    @Override
    public void process(JobExecutionMultipleShardingContext context) {
        // do something by sharding items
    	LOGGER.info("第"+count.addAndGet(1)+"次执行，当前分片号为："+context.getShardingItemParameters());
    	 List<CimProduct> result = productService.selectListByCondition(null);// get data from database by sharding items
    	 for(CimProduct u:result){
    		 u.setProductDesc("ElasticJob after:"+count);
    		 productService.update(u);
    	 }
    	 LOGGER.info("---complete---");
    }
    
}