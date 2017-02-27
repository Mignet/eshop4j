package com.linkwee.act.redpacket.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.linkwee.act.redpacket.model.ActRedpacketDetail;
import com.linkwee.act.redpacket.model.ActRedpacketSendRecord;
import com.linkwee.act.redpacket.service.ActRedpacketSendRecordService;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.ActRedpacketDetailMapper;
import com.linkwee.web.dao.ActRedpacketSendRecordMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.enums.PersonalMsgTypeEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.helper.PushMessageHelper;


 /**
 * 
 * @描述：ActRedpacketSendRecordService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年08月08日 10:36:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actRedpacketSendRecordService")
public class ActRedpacketSendRecordServiceImpl extends GenericServiceImpl<ActRedpacketSendRecord, Long> implements ActRedpacketSendRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActRedpacketSendRecordServiceImpl.class);
	
	@Autowired
	private ActRedpacketSendRecordMapper redpacketSendRecordMapper;
	
	@Autowired
	private ActRedpacketDetailMapper  redpacketDetailMapper;
	
	@Autowired
	private SmMessageQueueService messageQueueService;
	
	@Autowired
	private PushMessageHelper pushMessageHelper;
	
	@Resource
	private ConfigHelper configHelper;
	
	@Override
    public GenericDao<ActRedpacketSendRecord, Long> getDao() {
        return redpacketSendRecordMapper;
    }

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void checkExpirationRedpacket() throws Exception{
		try{
			updateByDay(redpacketSendRecordMapper.getExpiresRedpacketByDay());
			
			updateByTime(redpacketSendRecordMapper.getExpiresRedpacketByTime());
		}catch(Exception e){
			LOGGER.warn("expirationProcess exception", e.getMessage());
			throw e;
		}
	}
	
	private void updateByDay(List<String> sendRedpackets){
		if(sendRedpackets==null || sendRedpackets.isEmpty()) return;
		redpacketDetailMapper.updateExpirationStatusByDay(sendRedpackets);
	}
	
	private void updateByTime(List<String> sendRedpackets){
		if(sendRedpackets==null || sendRedpackets.isEmpty()) return;
		redpacketDetailMapper.updateExpirationStatus(sendRedpackets);
		redpacketSendRecordMapper.updateExpiresStatus(sendRedpackets);
	}


	@Override
	public void advanceSendExpireMsg() {
		String expireDate=null;
		try{
			int day;
			String dayStr = configHelper.getValue(SysConfigConstant.PUSHMESSAGE_REDPACKETEXPIREDAY);
			if(StringUtils.isNotBlank(dayStr))day = Integer.parseInt(dayStr);
			else day=2;
			expireDate = DateTime.now().plusDays(day).toString(DateUtils.FORMAT_SHORT);
			//expireDate=	com.linkwee.core.util.DateUtils.format(DateUtils.addDays(new Date(), day), com.linkwee.core.util.DateUtils.FORMAT_SHORT);
			expireDate = Joiner.on(" ").join(new Object[]{expireDate,"00:00:00"});
			//获取红包即将过期用户手机号码
			List<Map<String,Object>> mobilesAndUserIds = redpacketSendRecordMapper.getAdvanceExpiresRemindMobiles(expireDate);
			if(mobilesAndUserIds==null || mobilesAndUserIds.isEmpty())return;
			List<String> mobiles = Lists.newArrayList();
			List<String> userIds = Lists.newArrayList();
			for(Map<String,Object> map : mobilesAndUserIds){
				mobiles.add(String.valueOf(map.get("mobile")));
				userIds.add(String.valueOf(map.get("userId")));
			}
			
			//messageQueueService.batchSendMessage(mobiles, AppTypeEnum.INVESTOR, MsgModuleEnum.REDPAPEREXPIRED);	
			messageQueueService.batchSendSmMessageAndPersonalMsg(mobiles, AppTypeEnum.INVESTOR, MsgModuleEnum.REDPAPEREXPIRED, true, userIds, PersonalMsgTypeEnum.REDPACKET_INV);
			String content = messageQueueService.queryMessageTemplate(MsgModuleEnum.REDPAPEREXPIRED, AppTypeEnum.INVESTOR, "");
			pushMessageHelper.BatchSinglePush(AppTypeEnum.INVESTOR, SmsTypeEnum.MYREDPACKET_INC,userIds,"红包即将过期",content, null,false,null);
		}catch(Exception e){
			LOGGER.warn("advanceSendExpireMsg exception date = {}", expireDate,e.getMessage());
		}
	
	}
	
	@Override	
	public List<ActRedpacketSendRecord> getRedpacketSendRecords(String... sendIds){
		return redpacketSendRecordMapper.getRedpacketSendRecords(Lists.newArrayList(sendIds));
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int updateSendRedpackets(List<ActRedpacketDetail> redpackets) {
		return redpacketSendRecordMapper.updateSendRedpackets(redpackets);
	}	

}
