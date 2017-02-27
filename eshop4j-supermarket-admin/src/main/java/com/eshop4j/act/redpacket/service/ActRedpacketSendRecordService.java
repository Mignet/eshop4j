package com.eshop4j.act.redpacket.service;

import java.util.List;

import com.eshop4j.act.redpacket.model.ActRedpacketDetail;
import com.eshop4j.act.redpacket.model.ActRedpacketSendRecord;
import com.eshop4j.core.generic.GenericService;
 /**
 * 
 * @描述： ActRedpacketSendRecordService服务接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年08月08日 10:36:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActRedpacketSendRecordService extends GenericService<ActRedpacketSendRecord,Long>{
	
	/**
	 * 检查过期红包
	 */
	void checkExpirationRedpacket()throws Exception;
	
	/**
	 * 提前发送红包过期消息
	 */
	void advanceSendExpireMsg();
	
	/**
	 * 获取一组发放记录
	 * @param sendIds
	 * @return
	 */
	List<ActRedpacketSendRecord> getRedpacketSendRecords(String... sendIds);
	
	/**
	 * 更新发送红包
	 * @param sendIds
	 * @param redpackets
	 * @return
	 */
	int updateSendRedpackets(List<ActRedpacketDetail> redpackets);
}
