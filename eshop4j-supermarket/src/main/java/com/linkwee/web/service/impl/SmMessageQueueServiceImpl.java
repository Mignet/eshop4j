package com.linkwee.web.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.SmMessageQueueMapper;
import com.linkwee.web.dao.SmMessageTemplateMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.MessageSendErrorEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.enums.PersonalMsgTypeEnum;
import com.linkwee.web.enums.SmsChannelTypeEnum;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.SmMessageQueue;
import com.linkwee.web.model.SmMessageTemplate;
import com.linkwee.web.model.SysConfig;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.response.CommonTCSResult;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.xoss.util.HttpClientUtil;
import com.linkwee.xoss.util.InterceptUtility;
import com.linkwee.xoss.util.MessageSendUtil;


 /**
 * 
 * @描述：SmMessageTemplateService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月18日 11:33:49
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smMessageTemplateService")
public class SmMessageQueueServiceImpl extends GenericServiceImpl<SmMessageQueue, Long> implements SmMessageQueueService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmMessageQueueServiceImpl.class);
	
	@Resource
	private SmMessageQueueMapper smMessageQueueMapper;
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private SmMessageTemplateMapper smMessageTemplateMapper;
	
	@Resource
	private SysMsgService sysMsgService;
	
	@Resource
	private CrmUserInfoService userInfoService;
	
	@Override
    public GenericDao<SmMessageQueue, Long> getDao() {
        return smMessageQueueMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmMessageTemplate -- 排序和模糊查询 ");
		Page<SmMessageQueue> page = new Page<SmMessageQueue>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmMessageQueue> list = this.smMessageQueueMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		return tableReturn;
	}

	
	/**
	 * 查询系统短信发送设置
	 * @return
	 */
	public boolean querySendMsgSet() {
		boolean isNeedSendMsg = true;
		String vcodeSet = sysConfigService.getValuesByKey(SysConfigConstant.KEY_NO_SEND_VCODE);
		if(vcodeSet!=null && "1".equals(vcodeSet)){//设置为不发送短信消息
			isNeedSendMsg = false;
		}
		return isNeedSendMsg;
	}

	@Override
	/**
	 * 同内容群发
	 * 如果moduleId 为空将按照传入的 content发送，不为空根据配置的模板发送
	 */
	public CommonTCSResult batchSendMessage(Collection<String> mobiles, AppTypeEnum appType,MsgModuleEnum moduleId,Object...params) {
		//List<SmMessageQueue> sendTmpList = new ArrayList<SmMessageQueue>(); 不记录短信发送日记
		CommonTCSResult retCode = new CommonTCSResult();
		boolean isNeedSendMsg = querySendMsgSet();
		//mobiles 分批处理		
		List<List<String>> mobiless =  Lists.newArrayList();
		InterceptUtility.subsection(Lists.newArrayList(mobiles) , mobiless,100);
		
		String content = "";
		if(isNeedSendMsg){
			//Date startSend = new Date();
			if(moduleId!=null){
				SmMessageTemplate condit = new SmMessageTemplate();
				condit.setModuleId(moduleId.getValue());
				condit.setAppType(appType.getKey());
				SmMessageTemplate tmp = smMessageTemplateMapper.selectOneByCondition(condit);
				content = String.format(tmp.getContent(),params);
			}
			Map<String,String> HttpParams = fillAccInfo(appType);
			for (final List<String> list : mobiless) {
				String code =   sendSameContentMessage(list, content,HttpParams);
				//int sendStatus = 0;
				if(code != null){
					if(MessageSendUtil.isSuccessReturn(Long.valueOf(code))){//发送成功
						//sendStatus = 2;
						retCode = new CommonTCSResult(0,"发送成功");
					}else{
						//sendStatus = 3;
						retCode = new CommonTCSResult(Integer.valueOf(code),EnumUtils.getEnumByKey(Integer.valueOf(code), MessageSendErrorEnum.values()).getValue());
					}
				}
				/*for(String str:list){
					SmMessageQueue model = new SmMessageQueue();
					model.setType(2);//个人消息
					model.setAppType(appType.getKey());
					model.setSendTo(str);
					model.setContent(content);
					model.setCreateTime(startSend);
					model.setStatus(sendStatus);
					model.setSendTime(new Date());
					model.setMsgType((byte)moduleId.getKey());
					sendTmpList.add(model);
				}*/
				LOGGER.info("SmMessageQueueService发送短信执行：code={},message={},mobiles={}",new Object[]{retCode.getCode(),retCode.getMessage(),list});
			}
			
			//smMessageQueueMapper.insertBatch(sendTmpList);
		}else{
			retCode = new CommonTCSResult(100,"系统未开启短信发送功能");
		}
		return retCode;
		
	}

	@Override
	public List<String> queryWaitingMsgTmp(SmMessageQueue condition) {
		List<SmMessageQueue> smMlist = smMessageQueueMapper.selectByCondition(condition);
		List<String> mobiles = new ArrayList<String>();
		for(SmMessageQueue item:smMlist){
			mobiles.add(item.getSendTo());
		}
		return mobiles;
	}
	
	@Override
	public boolean checkVerificationCode(String mobile,MsgModuleEnum moduleId,String inputCode) throws Exception{
		if(StringUtils.isBlank(mobile)){
			throw new Exception("手机号不能为空");
		}
		if(moduleId == null){
			throw new Exception("模块编码不能为空");
		}
		if(StringUtils.isBlank(inputCode)){
			throw new Exception("验证码不能为空");
		}
		boolean ret = false;
		SmMessageQueue t = new SmMessageQueue();
		t.setStatus(2);//发送成功
		t.setModuleId(moduleId.getValue());
		t.setSendTo(mobile);
		SmMessageQueue retMsg = smMessageQueueMapper.checkVcode(t);
		if(inputCode.equals(retMsg.getContent())){
			ret = true;
		}
		return ret;
	}
	/**
	 * 不同内容群发
	 * 群发的必须是同一渠道和端口
	 * @param messages
	 * @return
	 */
	public CommonTCSResult sendDiffContentMessage(List<SmMessageQueue> messages){
		CommonTCSResult retCode =null;
		boolean isNeedSendMsg = querySendMsgSet();
		if(isNeedSendMsg){
			if(messages!=null && messages.size()>100){//同内容批量 一个包一次不能超过100个手机号
				return new CommonTCSResult(MessageSendErrorEnum.MOBILECOUNTOVERFLOW.getKey(),MessageSendErrorEnum.MOBILECOUNTOVERFLOW.getValue());
			}
			Map<String,SmMessageTemplate> mstTepMap = getMsgTepMap();
			SmMessageQueue smMessageQueue = messages.get(0);
			 Map<String, String> params = fillAccInfo(AppTypeEnum.valueOf(String.valueOf(smMessageQueue.getAppType())));
			  StringBuffer multixmt = new StringBuffer();
			  for(int i=0;i<messages.size();i++){
				  multixmt.append("0|");//无流水号
				  multixmt.append("*|");//无通道号
				  multixmt.append(messages.get(i).getSendTo()).append("|");//手机号
				  /**
				   * 根据配置的消息模板发送内容
				   */
				  if(StringUtils.isNotBlank(messages.get(i).getModuleId())){
					  SmMessageTemplate tmp =  mstTepMap.get(messages.get(i).getAppType()+messages.get(i).getModuleId());
					  multixmt.append(String.format(tmp.getContent(), messages.get(i).getContent()));
				  }else{
					  multixmt.append(messages.get(i).getContent());
				  }
				  
				  if(i < messages.size() -1){
					  multixmt.append(",");
				  }
				  
			  }
			  params.put("multixmt", multixmt.toString());
			 
			 /* 0|*|13800138000|xOO6wyy7ttOtyrnTwyE= */
			  try {
				  String url = findMessageServerUrl();
				  String method = "MongateMULTIXSend";
				  String retMsg = HttpClientUtil.httpPost(url+method, params);
				  String code = MessageSendUtil.RegexString(retMsg);
				  int sendStatus = 0;
				  if(code != null){
						if(MessageSendUtil.isSuccessReturn(Long.valueOf(code))){//发送成功
							sendStatus = 2;//成功
							retCode = new CommonTCSResult(0,"发送成功");
						}else{
							sendStatus = 3;//失败
							retCode = new CommonTCSResult(Integer.valueOf(code),EnumUtils.getEnumByKey(Integer.valueOf(code), MessageSendErrorEnum.values()).getValue());
						}
					}
					LOGGER.debug("SmMessageQueueService发送短信执行：code={},message={}",retCode.getCode(),retCode.getMessage());
					//更新状态
					 for(SmMessageQueue item :messages){
						 item.setStatus(sendStatus);
					  }
					smMessageQueueMapper.updateBathch(messages);
				  return retCode;
			} catch (Exception e) {
				return null;
			}
		}else{
			retCode = new CommonTCSResult(100,"系统未开启短信发送功能");
		}
		return retCode;
	}

	public Map<String,SmMessageTemplate> getMsgTepMap() {
		Map<String,SmMessageTemplate> msgTepMap = new HashMap<String,SmMessageTemplate>();
		//加载短信模板信息
		SmMessageTemplate t = new SmMessageTemplate();
		t.setStatus(1);//没停用的模板
		List<SmMessageTemplate> tmpList = smMessageTemplateMapper.selectByCondition(t);
		for(SmMessageTemplate item:tmpList){
			msgTepMap.put(item.getAppType()+item.getModuleId(),item);
		}
		return msgTepMap;
	}
	
	private String sendSingleMessage(String mobile,String content,AppTypeEnum appType){
		 Map<String, String> params = fillAccInfo(appType);
		  params.put("pszMobis", mobile);
		  params.put("pszMsg", content);
		  params.put("iMobiCount", "1");
		  params.put("pszSubPort", "*");
		  params.put("MsgId", "0");
		  
		  try {
			  String url = findMessageServerUrl();
			  String method = "MongateSendSubmit";
			  String retMsg = HttpClientUtil.httpPost(url+method, params);
			  return MessageSendUtil.RegexString(retMsg);
		} catch (Exception e) {
			return null;
		}
	}
	
	private String sendSameContentMessage(Collection<String> mobiles,String content,Map<String, String> params){
		/*if(mobiles!=null && mobiles.size()>100){//同内容批量 一个包一次不能超过100个手机号
			return String.valueOf(MessageSendErrorEnum.MOBILECOUNTOVERFLOW.getKey());
		}*/
		 //Map<String, String> params = fillAccInfo(appType);
		  StringBuffer pszMobis = new StringBuffer();
		  Iterator<String> it = mobiles.iterator();
		  while(it.hasNext()){
			  pszMobis.append(it.next()).append(",");
			  
		  }
		  if(pszMobis.toString().endsWith(",")){	
			  params.put("pszMobis", pszMobis.toString().substring(0, pszMobis.toString().length() - 1));
	      }else{
	    	  params.put("pszMobis", pszMobis.toString());
	      }
		  params.put("pszMsg", content);
		  params.put("iMobiCount", String.valueOf(mobiles.size()));
		  params.put("pszSubPort", "*");
		  params.put("MsgId", "0");
		  
		  try {
			  String url = findMessageServerUrl();
			  String method = "MongateSendSubmit";
			  String retMsg = HttpClientUtil.httpPost(url+method, params);
			  return MessageSendUtil.RegexString(retMsg);
		} catch (Exception e) {
			return null;
		}
		  
	}
	

	public String findMessageServerUrl() {
		String url = sysConfigService.getValuesByKey("mw_send_message_url", 0);
		return url;
	}

	public Map<String, String> fillAccInfo(AppTypeEnum appType) {
		Map<String ,String> params = new HashMap<String,String>();
		
		SysConfig condit = new SysConfig();
		condit.setConfigName("梦网短信");
		condit.setAppType(appType.getKey());
		
		List<SysConfig> syslist = sysConfigService.queryfuzzily(condit);
		
		for(SysConfig item:syslist){
			
			if(item.getConfigKey().endsWith("userId") ){
				params.put("userId", item.getConfigValue());// 账户信息设置
			}
			if(item.getConfigKey().endsWith("pwd")){
				params.put("password", item.getConfigValue());// 账户信息设置
			}
			
		}
		return params;
	}

	@Override
	public CommonTCSResult sendSingleMessage(String mobile,	AppTypeEnum appType, MsgModuleEnum moduleId,Object... contentArgs)
			 {
		CommonTCSResult retCode = new CommonTCSResult();
		if(StringUtils.isBlank(mobile)){
			retCode = new CommonTCSResult(-1,"手机号不能为空");
		}
		if(appType ==null){
			retCode = new CommonTCSResult(-1,"appType不能为空");
		}
		if(moduleId ==null){
			retCode = new CommonTCSResult(-1,"moduleId不能为空");
		}
		
		boolean isNeedSendMsg = querySendMsgSet();
		String content = "";
		if(isNeedSendMsg){
			/*SmMessageQueue model = new SmMessageQueue();
			model.setType(2);//个人消息
			model.setAppType(appType.getKey());
			model.setSendTo(mobile);	
			model.setMsgType((byte)1);*/
			
			if(moduleId!=null){
				SmMessageTemplate condit = new SmMessageTemplate();
				condit.setModuleId(moduleId.getValue());
				condit.setAppType(appType.getKey());
				SmMessageTemplate tmp = smMessageTemplateMapper.selectOneByCondition(condit);
				if(tmp != null){
					content = String.format(tmp.getContent(), contentArgs);
				}
				//model.setContent(content);
			}
			
			//model.setCreateTime(new Date());
			String code = sendSingleMessage(mobile, content,appType);
			
			if(code != null){
				if(MessageSendUtil.isSuccessReturn(Long.valueOf(code))){//发送成功
					//model.setStatus(2);//成功
					retCode = new CommonTCSResult(0,"发送成功");
					LOGGER.info("SmMessageQueueService发送短信执行：mobile={},content={},appType={},moduleId={},code={},message={}",new Object[]{mobile, content,appType,moduleId,retCode.getCode(),retCode.getMessage()});
				}else{
					//model.setStatus(3);//失败
					retCode = new CommonTCSResult(Integer.valueOf(code),EnumUtils.getEnumByKey(Integer.valueOf(code), MessageSendErrorEnum.values()).getValue());
					LOGGER.error("SmMessageQueueService发送短信执行：mobile={},content={},appType={},moduleId={},code={},message={}",new Object[]{mobile, content,appType,moduleId,retCode.getCode(),retCode.getMessage()});
				}
			}
			
			//model.setSendTime(new Date());
			//smMessageQueueMapper.insertSelective(model);
		}else{
			retCode = new CommonTCSResult(100,"系统未开启短信发送功能");
			LOGGER.info("SmMessageQueueService发送短信执行：mobile={},content={},appType={},moduleId={},code={},message={}",new Object[]{mobile, content,appType,moduleId,retCode.getCode(),retCode.getMessage()});
		}		
		return retCode;
	}
	@Override
	public CommonTCSResult sendMessageAndSysMsg(String mobile,String userId,AppTypeEnum appType, MsgModuleEnum moduleId,boolean needSendMsg,Object... contentArgs){
		CommonTCSResult retCode = new CommonTCSResult();
		if(mobile == null && userId == null){
			return  new CommonTCSResult(-1,"mobile 和 userId 不能同时为空！");
		}
		if(appType ==null){
			return new CommonTCSResult(-1,"appType不能为空");
		}
		if(moduleId ==null){
			return new CommonTCSResult(-1,"moduleId不能为空");
		}
		
		
		String content = "";
		if(moduleId!=null){
			SmMessageTemplate condit = new SmMessageTemplate();
			condit.setModuleId(moduleId.getValue());
			condit.setAppType(appType.getKey());
			SmMessageTemplate tmp = smMessageTemplateMapper.selectOneByCondition(condit);
			if(tmp != null){
				content = String.format(tmp.getContent(), contentArgs);
			}
		}
		if(needSendMsg){
			CrmUserInfo userInfo = null;
			if(mobile!=null){
				userInfo = userInfoService.selectCrmUserInfoByMobile(mobile);
				userId = userInfo.getUserId();
			}else{
				userInfo = userInfoService.queryUserInfoByUserId(userId);
				mobile = userInfo.getMobile();
			}
			if(userInfo!=null) {
				SysMsg msg = new SysMsg();
				msg.setContent(content);
				msg.setStatus(0);// 发布
				msg.setUserNumber(userInfo.getUserId());
				msg.setReadStatus(0);// 未读
				msg.setAppType(appType.getKey());
				sysMsgService.addMsg(msg);
			}
		}
		boolean isNeedSendMsg = querySendMsgSet();		
		if(isNeedSendMsg){			
			String code = sendSingleMessage(mobile, content,appType);			
			if(code != null){
				if(MessageSendUtil.isSuccessReturn(Long.valueOf(code))){//发送成功
					retCode = new CommonTCSResult(0,"发送成功");
					LOGGER.info("SmMessageQueueService发送短信执行：mobile={},content={},appType={},moduleId={},code={},message={}",new Object[]{mobile, content,appType,moduleId,retCode.getCode(),retCode.getMessage()});
				}else{
					retCode = new CommonTCSResult(Integer.valueOf(code),EnumUtils.getEnumByKey(Integer.valueOf(code), MessageSendErrorEnum.values()).getValue());
					LOGGER.error("SmMessageQueueService发送短信执行：mobile={},content={},appType={},moduleId={},code={},message={}",new Object[]{mobile, content,appType,moduleId,retCode.getCode(),retCode.getMessage()});
				}
			}
			
		}else{
			retCode = new CommonTCSResult(100,"系统未开启短信发送功能");
			LOGGER.info("SmMessageQueueService发送短信执行：mobile={},content={},appType={},moduleId={},code={},message={}",new Object[]{mobile, content,appType,moduleId,retCode.getCode(),retCode.getMessage()});
		}		
		return retCode;
	}
	
	public CommonTCSResult sendSingleMessage(String mobile,	AppTypeEnum appType, String content, SmsChannelTypeEnum channelType)
			throws Exception {
		if(StringUtils.isBlank(mobile)){
			throw new Exception("手机号不能为空");
		}
		if(appType ==null){
			throw new Exception("appType不能为空");
		}
		if(content ==null){
			throw new Exception("content不能为空");
		}
		CommonTCSResult retCode = new CommonTCSResult();
		boolean isNeedSendMsg = querySendMsgSet();
		if(isNeedSendMsg){
			SmMessageQueue model = new SmMessageQueue();
			//content = new String(content.getBytes(),"utf-8");
			model.setType(2);//个人消息
			model.setAppType(appType.getKey());
			model.setSendTo(mobile);	
			
			model.setCreateTime(new Date());
			LOGGER.debug("SmMessageQueueService发送短信输入：mobile={},content={},appType={},channelType={}", new Object[]{mobile, content,appType,channelType});
			String code = sendSingleMessage(mobile, content,appType);
			
			if(code != null){
				if(MessageSendUtil.isSuccessReturn(Long.valueOf(code))){//发送成功
					model.setStatus(2);//成功
					retCode = new CommonTCSResult(0,"发送成功");
				}else{
					model.setStatus(3);//失败
					retCode = new CommonTCSResult(Integer.valueOf(code),EnumUtils.getEnumByKey(Integer.valueOf(code), MessageSendErrorEnum.values()).getValue());
				}
			}
			LOGGER.debug("SmMessageQueueService发送短信执行：code={},message={}",retCode.getCode(),retCode.getMessage() );
			model.setSendTime(new Date());
			smMessageQueueMapper.insertSelective(model);
		}else{
			retCode = new CommonTCSResult(100,"系统未开启短信发送功能");
		}
		return retCode;
	}

	@Override
	public CommonTCSResult batchSendSmMessageAndPersonalMsg(
			Collection<String> mobiles, AppTypeEnum appType,
			MsgModuleEnum moduleId, boolean isNeedSendPeronalMsg,
			Collection<String> userIds,PersonalMsgTypeEnum msgType, Object... params) {
		List<SmMessageQueue> sendTmpList = new ArrayList<SmMessageQueue>();
		CommonTCSResult retCode = new CommonTCSResult();
		boolean isNeedSendMsg = querySendMsgSet();
		//mobiles 分批处理		
		List<List<String>> mobiless =  Lists.newArrayList();
		InterceptUtility.subsection(Lists.newArrayList(mobiles) , mobiless,100);
		
		String content = "";
		if(moduleId!=null){
			SmMessageTemplate condit = new SmMessageTemplate();
			condit.setModuleId(moduleId.getValue());
			condit.setAppType(appType.getKey());
			SmMessageTemplate tmp = smMessageTemplateMapper.selectOneByCondition(condit);
			content = String.format(tmp.getContent(),params);
		}
		if(isNeedSendMsg){
			Date startSend = new Date();
			
			Map<String,String> HttpParams = fillAccInfo(appType);
			for (final List<String> list : mobiless) {
				String code =   sendSameContentMessage(list, content,HttpParams);
				int sendStatus = 0;
				if(code != null){
					if(MessageSendUtil.isSuccessReturn(Long.valueOf(code))){//发送成功
						sendStatus = 2;
						retCode = new CommonTCSResult(0,"发送成功");
					}else{
						sendStatus = 3;
						retCode = new CommonTCSResult(Integer.valueOf(code),EnumUtils.getEnumByKey(Integer.valueOf(code), MessageSendErrorEnum.values()).getValue());
					}
				}
				for(String str:list){
					SmMessageQueue model = new SmMessageQueue();
					model.setType(2);//个人消息
					model.setAppType(appType.getKey());
					model.setSendTo(str);
					model.setContent(content);
					model.setCreateTime(startSend);
					model.setStatus(sendStatus);
					model.setSendTime(new Date());
					model.setMsgType((byte)moduleId.getKey());
					sendTmpList.add(model);
				}
				LOGGER.debug("SmMessageQueueService发送短信执行：code={},message={}",retCode.getCode(),retCode.getMessage());
			}
			
			smMessageQueueMapper.insertBatch(sendTmpList);
			
		}else{
			retCode = new CommonTCSResult(100,"系统未开启短信发送功能");
		}
		if(isNeedSendPeronalMsg && userIds != null && userIds.size()>0){
			sysMsgService.batchAddMsgs(appType, content, userIds, msgType);
		}
		return retCode;
		
	}
	
	public String queryMessageTemplate(MsgModuleEnum moduleId,AppTypeEnum appType,Object... contentArgs ){
		SmMessageTemplate condit = new SmMessageTemplate();
		condit.setModuleId(moduleId.getValue());
		condit.setAppType(appType.getKey());
		SmMessageTemplate tmp = smMessageTemplateMapper.selectOneByCondition(condit);
		if(tmp != null){
			return String.format(tmp.getContent(), contentArgs);
		}else{
			return null;
		}
		
	}


	

}
