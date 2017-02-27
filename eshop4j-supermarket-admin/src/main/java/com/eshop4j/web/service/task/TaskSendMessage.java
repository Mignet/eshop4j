package com.eshop4j.web.service.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eshop4j.web.model.SmMessageQueue;
import com.eshop4j.web.service.SmMessageQueueService;

/**
 * 发送消息
 * @Author chenchy
 * @Date 2015年12月24日 下午7:02:28
 */
public class TaskSendMessage implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(TaskSendMessage.class);
	private SmMessageQueueService smMessageQueueService;
	
	public TaskSendMessage( SmMessageQueueService smMessageQueueService) {
		this.smMessageQueueService = smMessageQueueService;
	}
	
	public void run() {
		
		logger.info("sendmessageBegin :"+System.currentTimeMillis());
		long startTime = System.currentTimeMillis();
		SmMessageQueue t = new SmMessageQueue();
		t.setStatus(1);//待发送
		List<SmMessageQueue> waitTmpList = smMessageQueueService.selectListByCondition(t);
		//处理待发送列表
		 smMessageQueueService.sendDiffContentMessage(waitTmpList);
		
		//更新数据发送状态
		
		long endTime = System.currentTimeMillis();
		logger.info("costTime :"+(endTime - startTime));
	}

}