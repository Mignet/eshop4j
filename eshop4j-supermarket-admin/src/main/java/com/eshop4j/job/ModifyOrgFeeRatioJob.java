package com.eshop4j.job;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.eshop4j.web.model.CimOrgFeeTimetask;
import com.eshop4j.web.service.CimOrgFeeTimetaskService;
import com.eshop4j.web.service.CimOrginfoService;
/**
 * 
 * 机构佣金修改定时任务
 * 监控地址:http://localhost:9090/elastic-job-console
 * @author yalin
 *
 */
@Component
public class ModifyOrgFeeRatioJob extends AbstractSimpleElasticJob {
	private static final Logger LOGGER = LoggerFactory.getLogger(ModifyOrgFeeRatioJob.class);
	
	@Resource
	private CimOrginfoService cimOrginfoService; //机构服务
	
	@Resource
	private CimOrgFeeTimetaskService cimOrgFeeTimetaskService;  //机构佣金定时设置服务
	
	
    @Override
    public void process(JobExecutionMultipleShardingContext context) {
        try {
        	LOGGER.info("---------机构佣金修改定时任务《启动》-------");
        	List<CimOrgFeeTimetask> orgFeeTasks = cimOrgFeeTimetaskService.queryOrgFeeTimeTaskByStatus(1,null,new Date()); //1,取出机构所有待执行的定时任务
        	//Date1.after(Date2),当Date1大于Date2时，返回TRUE，当小于等于时，返回false
        	//Date1.before(Date2)，当Date1小于Date2时，返回TRUE，当大于等于时，返回false
			if(orgFeeTasks != null && orgFeeTasks.size() > 0){
				for(CimOrgFeeTimetask orgFeeTimetask : orgFeeTasks){
					//定时任务表中 比当前时间小的任务 全部拿出来 执行一遍  然后修改状态
					//if(new Date().after(orgFeeTimetask.getTaskStartTime())){
						cimOrginfoService.updateCimOrgFeeRatio(orgFeeTimetask); //更新机构和产品表的佣金率
					//}
					
				}
			}
			LOGGER.info("---------机构佣金修改定时任务《执行完毕》-------");
		} catch (Exception e) {
			LOGGER.error("执行机构佣金修改定时任务失败！",e);
		}
    }
    
}