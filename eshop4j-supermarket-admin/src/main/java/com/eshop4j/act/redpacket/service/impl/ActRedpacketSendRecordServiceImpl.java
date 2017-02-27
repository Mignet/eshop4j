package com.eshop4j.act.redpacket.service.impl;

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
import com.eshop4j.act.redpacket.model.ActRedpacketDetail;
import com.eshop4j.act.redpacket.model.ActRedpacketSendRecord;
import com.eshop4j.act.redpacket.service.ActRedpacketSendRecordService;
import com.eshop4j.core.constant.SysConfigConstant;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.dao.ActRedpacketDetailMapper;
import com.eshop4j.web.dao.ActRedpacketSendRecordMapper;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.web.enums.MsgModuleEnum;
import com.eshop4j.web.enums.PersonalMsgTypeEnum;
import com.eshop4j.web.enums.SmsTypeEnum;
import com.eshop4j.web.service.SmMessageQueueService;
import com.eshop4j.xoss.helper.ConfigHelper;
import com.eshop4j.xoss.helper.PushMessageHelper;


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
			//expireDate=	com.eshop4j.core.util.DateUtils.format(DateUtils.addDays(new Date(), day), com.eshop4j.core.util.DateUtils.FORMAT_SHORT);
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
