package com.linkwee.act.redpacket.service;

import java.util.Date;
import java.util.Map;

import com.linkwee.act.redpacket.model.ActRedpacketDetail;
import com.linkwee.act.redpacket.model.ActRedpacketRule;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.request.act.RedPacketInfoRequest;
 /**
 * 
 * @描述： ActRedpacketRuleService服务接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年08月19日 15:47:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActRedpacketRuleService extends GenericService<ActRedpacketRule,Long>{

	/**
	 * 匹配红包
	 * @param investRecord
	 * @param redpacketDetailMpas
	 * @return
	 */
	ActRedpacketDetail matcheRedPacket(InvestRecordWrapper investRecord,Map<String, ActRedpacketDetail> redpacketDetailMpas);
	
	boolean insertRedpacketRule(RedPacketInfoRequest  redPacketInfo,Date date)throws Exception;
	
	boolean updateRedpacketRule(RedPacketInfoRequest  redPacketInfo,Date date)throws Exception;
	
	void getRedPacketRuleInfo(RedPacketInfoRequest redPacketInfo,String redpacketId);
}
