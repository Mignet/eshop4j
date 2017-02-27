package com.linkwee.web.service.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linkwee.web.service.EasemobService;

/**
 * 环信注册任务
 * @Author ZhongLing
 * @Date 2015年12月24日 下午7:02:28
 */
public class TaskEaseReg implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(TaskEaseReg.class);
	private String  userId;
	private EasemobService easemobService;
	
	public TaskEaseReg(String  userId, EasemobService easemobService) {
		this.userId = userId;
		this.easemobService = easemobService;
	}
	
	public void run() {
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("TaskEaseReg|run|data=").append("userId = " + userId);
		try {
			easemobService.generateEasemob(userId);
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
	}

}