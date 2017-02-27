package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.cim.OrgInfo;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.service.AppMsgPushService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.util.RejectedExecuteRetry;

@Service("appMsgPushService")
public class AppMsgPushServiceImpl implements AppMsgPushService{
	protected static final Logger LOGGER = LoggerFactory.getLogger(AppMsgPushServiceImpl.class);
	
	private static final String[] MSG_KEYS = { SysConfigConstant.PUSHMESSAGE_LCUSTOMERBUY,SysConfigConstant.PUSHMESSAGE_LGRADEONESALE,SysConfigConstant.PUSHMESSAGE_LGRADETWOSALE};

	
	private static final SmsTypeEnum[] INVEST_KEYS = { SmsTypeEnum.LCUSTOMERBUY,SmsTypeEnum.LGRADEONESALE,SmsTypeEnum.LGRADETWOSALE};
	
	@Autowired
	private CrmUserInfoService userInfoService;
	
	@Autowired
	private CrmCfplannerService cfplannerService;
	
	@Autowired
	private CrmInvestorService investorService;
	
	@Autowired
	private SysMsgService msgService;
	
	@Resource 
	private PushMessageHelper pushMessageHelper;
	
	@Resource
	private SysMsgService sysMsgService;//站内个人消息服务
	
	@Autowired
	private CimOrginfoService orginfoService;
	
	@Resource
	private ConfigHelper configHelper;

	/**
	 * 向理财师推送客户购买记录
	 * @param investRecord
	 */
	protected void pushInvestorPurchaseMsg(InvestRecordWrapper investRecord){
		CrmInvestor investor = investorService.queryInvestorByUserId(investRecord.getUserId());
		if(investor==null){
			LOGGER.warn("pushInvestorPurchaseMsg Investors do not exist id={} ", investRecord.getUserId());
			return;
		}
		//购买成功给投资客户推个人消息
		OrgInfo orgInfoResponse =  orginfoService.findOrgInfo(investRecord.getProductOrgId());
		String content = String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_INVEST_INV),investRecord.getInvestAmt().setScale(2, BigDecimal.ROUND_DOWN),orgInfoResponse == null ? "" : orgInfoResponse.getOrgName(),investRecord.getProductName());
		/*final SysMsg msg = new SysMsg();
		msg.setContent(content);
		msg.setStatus(0);// 发布
		msg.setUserNumber(investRecord.getUserId());
		msg.setReadStatus(0);// 未读
		msg.setAppType(AppTypeEnum.INVESTOR.getKey());
		msg.setTypeName(PersonalMsgTypeEnum.INVITATIONINV.getValue());
		sysMsgService.addMsg(msg);*/
		pushMessageHelper.pushMessage(AppTypeEnum.INVESTOR, SmsTypeEnum.PERSONALMSGCT_INC, investRecord.getUserId(), "投资成功", content, null, true);
		
		//佣金率为0不提醒理财师
		if(investRecord.getFeeRatio() == null || (investRecord.getFeeRatio() != null && investRecord.getFeeRatio().compareTo(BigDecimal.ZERO) == 0 ) )return;
		
		CrmCfplanner cfplanner = new CrmCfplanner();
		cfplanner.setUserName(investor.getUserName());
		cfplanner.setMobile( investor.getMobile());
		cfplanner.setParentId(investor.getCfplanner());
		
		/**
		 * 依次向理财师,一级理财师,二级理推送投资消息
		 */
		for (int i = 0; i < MSG_KEYS.length; i++) {
			String userInfoMsg = ObjectUtils.equals(SysConfigConstant.PUSHMESSAGE_LGRADETWOSALE,  MSG_KEYS[i]) ? null: StringUtils.join(new Object[]{cfplanner.getUserName(),cfplanner.getMobile().substring(cfplanner.getMobile().length()-4)},'*');
			Map<String, Object> urlmap = Maps.newHashMap();
			if( ObjectUtils.equals(SmsTypeEnum.LCUSTOMERBUY,  INVEST_KEYS[i])){
				urlmap.put("customerId", investRecord.getUserId());
			}else{
				urlmap.put("userNumber",cfplanner.getParentId());
			}	
			cfplanner = sendCfplannerMsg(cfplanner.getParentId(),userInfoMsg, investRecord.getProductName(),investRecord.getInvestAmt(), MSG_KEYS[i],INVEST_KEYS[i],urlmap);
			if(cfplanner==null) return;
		}
		
	}

	/**
	 * 消息拼装
	 * @param name+mobile 用户信息
	 * @param productName 产品名称
	 * @param investAmt 投资金额
	 * @param key 消息模板key
	 * @return 拼装后的消息
	 */
	private String getMsg(String key,Object... msgs){
		return String.format(configHelper.getValue(key),msgs);
	}
	
	/**
	 * 发送消息
	 * @param userId 理财师编号
	 * @param userInfoMsg 理财师名称
	 * @param productName 产品名称
	 * @param investAmt 投资金额
	 * @param key 消息模板key
	 * @return 根据理财师编号查询的理财师信息
	 */
	private CrmCfplanner sendCfplannerMsg(String userId,String userInfoMsg,String productName,BigDecimal investAmt,String key,SmsTypeEnum smsTypeEnum,Map<String, Object> urlmap){
		
		if(StringUtils.isBlank(userId))return null;
		
		CrmCfplanner cfplanner = cfplannerService.queryCfplannerByUserId(userId);
		if(cfplanner==null){
			LOGGER.warn("pushInvestorPurchaseMsg cfplanner do not exist id={} ",userId);
			return null;
		}
		String message = (userInfoMsg==null) ? getMsg(key, productName, investAmt) : getMsg(key,userInfoMsg, productName, investAmt);
		
		SysMsg cfplannerMsg = new SysMsg();
		cfplannerMsg.setAppType(1);
		cfplannerMsg.setContent(message);
		cfplannerMsg.setUserNumber(userId);
		cfplannerMsg.setStatus(0);
		//msgService.addMsg(cfplannerMsg);
		
		try {
			pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, smsTypeEnum, userId, "销售消息", message, urlmap, true);
		} catch (Exception e) {
			LOGGER.warn("push message error:{}",e.getMessage());
		}
		
		
		return cfplanner;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@RejectedExecuteRetry
	@Override
	public void investRecordProcess(InvestRecordWrapper investRecord)throws Exception {
		try {
			pushInvestorPurchaseMsg(investRecord);
		} catch (Exception e) {
			LOGGER.warn("AppMsgPushService investRecordProcess exception", e);
			throw e;
		}
		
	}

}
