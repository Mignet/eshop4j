package com.eshop4j.job;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.eshop4j.web.model.SysRejectedExecuteLog;
import com.eshop4j.web.service.SysRejectedExecuteLogService;
import com.eshop4j.xoss.helper.ArgsMeta;
import com.eshop4j.xoss.helper.RejectedExecuteRetryHelper;
import com.eshop4j.xoss.helper.SpringHelper;

@Component
public class RejectedExecuteRetryJob extends AbstractSimpleElasticJob{
	
	
	@Autowired
	private SysRejectedExecuteLogService sysRejectedExecuteLogService;
	
	
	
	
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		List<SysRejectedExecuteLog>  lists = sysRejectedExecuteLogService.getRejectedExecuteLogs();
		if(lists==null || lists.isEmpty())return;
		for (SysRejectedExecuteLog sysRejectedExecuteLog : lists) {
			String executeId = sysRejectedExecuteLog.getExecuteId();
			try{
				sysRejectedExecuteLogService.updateProcessStatus(executeId);
				Object obj = SpringHelper.getBean(sysRejectedExecuteLog.getServiceName());
				String parms = sysRejectedExecuteLog.getServiceParm();
				Class<?>[] classs = null;
				Object[] objects = null;
				if (StringUtils.isNotBlank(parms)) {
					List<ArgsMeta> argsMetas = JSONArray.parseArray(parms, ArgsMeta.class);
					classs = new Class<?>[argsMetas.size()];
					objects = new Object[argsMetas.size()];
					try {
						for (ArgsMeta argsMeta : argsMetas) {
							classs[argsMeta.getIndex()] = Class.forName(argsMeta.getClassName());
							objects[argsMeta.getIndex()] = JSON.parseObject(argsMeta.getValue(),classs[argsMeta.getIndex()]);
						}
					} catch (ClassNotFoundException e) {
						String exceptionMsg = e.getMessage();
						exceptionMsg = StringUtils.isBlank(exceptionMsg) ? "未知异常" : exceptionMsg;
						sysRejectedExecuteLogService.updateFaillStatus(executeId, exceptionMsg);
						continue;
					}	
				}
				try {
					RejectedExecuteRetryHelper.setExecuteId(executeId);
					if(classs==null){
						Method method = obj.getClass().getMethod(sysRejectedExecuteLog.getServiceMethod());
						method.invoke(obj);
					}else{
						Method method = obj.getClass().getMethod(sysRejectedExecuteLog.getServiceMethod(),classs);
						method.invoke(obj, objects);
					}
				} catch (Exception e) {
					String exceptionMsg = e.getMessage();
					exceptionMsg = StringUtils.isBlank(exceptionMsg) ? "未知异常" : exceptionMsg;
					sysRejectedExecuteLogService.updateFaillStatus(executeId, exceptionMsg);
					continue;
				}finally{
					RejectedExecuteRetryHelper.cleanExecuteId();
				}
				sysRejectedExecuteLogService.updateSucceedStatus(executeId);
			}catch(Exception e){
				String exceptionMsg = e.getMessage();
				exceptionMsg = StringUtils.isBlank(exceptionMsg) ? "未知异常" : exceptionMsg;
				sysRejectedExecuteLogService.updateFaillStatus(executeId, exceptionMsg);
				continue;
			}
		}
	}
}
