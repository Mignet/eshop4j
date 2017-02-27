package com.linkwee.web.service.impl;

import java.util.TimerTask;

import javax.annotation.Resource;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.linkwee.web.service.EasemobService;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.task.TaskEaseReg;
import com.linkwee.xoss.helper.MessageHelper;

public class MainExecutor extends TimerTask {

	@Resource
	private SmMessageQueueService smMessageTemplateService;
	@Resource
	private MessageHelper messageHelper;
	private static ThreadPoolTaskExecutor threadPool;

	public void setThreadPool(ThreadPoolTaskExecutor threadPool) {
		MainExecutor.threadPool = threadPool;
	}

	// 在主线程中执行任务线程.....
	@Override
	public void run() {
		//task1
//	TaskSendMessage task = new TaskSendMessage(smMessageTemplateService,messageHelper);
//	threadPool.execute(task);
		
	}
	
	/**
	 * 执行需要发送的消息
	 * @param data
	 * @param easemobService
	 */
	/*public static void addAddSendMsgTask( SysConfigService sysConfigService) {
		TaskSendMessage task = new TaskSendMessage(sysConfigService);
		threadPool.execute(task);
	}
	*/
	
	/**
	 * 环信生成
	 * @param data
	 * @param easemobService
	 */
	public static void addEasemobRegTask(String userId, EasemobService easemobService) {
		TaskEaseReg task = new TaskEaseReg(userId, easemobService);
		threadPool.execute(task);
	}
	
}