package com.eshop4j.xoss.helper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.gexin.rp.sdk.base.IBatch;
import com.gexin.rp.sdk.base.IIGtPush;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.gexin.rp.sdk.exceptions.PushAppException;
import com.gexin.rp.sdk.exceptions.PushSingleException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.constant.SysConfigConstant;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.EnumUtils;
import com.eshop4j.core.util.JsonUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.web.enums.MobileOsTypeEnum;
import com.eshop4j.web.enums.PersonalMsgTypeEnum;
import com.eshop4j.web.enums.SmsTypeEnum;
import com.eshop4j.web.model.SmCustomerDevice;
import com.eshop4j.web.model.mc.AdvancePayment;
import com.eshop4j.web.model.mc.SysMsg;
import com.eshop4j.web.model.mc.SysPushArtificialQueue;
import com.eshop4j.web.model.mc.SysPushMessage;
import com.eshop4j.web.model.mc.SysPushParameter;
import com.eshop4j.web.response.CommonTCSResult;
import com.eshop4j.web.service.SmCustomerDeviceService;
import com.eshop4j.web.service.SysMsgService;
import com.eshop4j.web.service.SysPushArtificialQueueService;
import com.eshop4j.web.service.SysPushMessageService;
import com.eshop4j.web.service.SysPushParameterService;
import com.eshop4j.xoss.util.InterceptUtility;
import com.eshop4j.xoss.util.PushMessageUtil;

@Component
public class PushMessageHelper {

	@Resource
	private SysPushMessageService sysPushMessageService;
	@Resource
	private SysPushParameterService sysPushParameterService;
	@Resource
	private SmCustomerDeviceService smCustomerDeviceService;
	@Resource
	private SysMsgService sysMsgService;
	@Resource
	private SysPushArtificialQueueService sysPushArtificialQueueService;
	@Resource
	private ConfigHelper configHelper;

	private static final Logger LOGGER = LoggerFactory.getLogger(PushMessageHelper.class);
	

	/**
	 * 站点推送
	 * 
	 * @param appType
	 *            appType
	 * @param osType
	 *            手机操作系统
	 * @param title
	 * @param content
	 * @param urlparam
	 *            SmsTypeEnum msg值为：cusDetail
	 *            urlparam.put("customerId","1111111") msg值为：teamDetail
	 *            urlparam.put("userNumber","1111111")
	 * @return CommonTCSResult#code 0：成功；-1 失败
	 * @throws Exception
	 */
	public CommonTCSResult pushByAppId(AppTypeEnum appType,	 SmsTypeEnum smsType, String title,	String content, Map<String, Object> urlparam)  {
		CommonTCSResult retCode;
		try {
			if (appType == null) {
				return new CommonTCSResult(-1, "参数错误，appType不能为空");
			}
			retCode = new CommonTCSResult();
			SysPushParameter contition = new SysPushParameter();
			contition.setAppType(appType.getKey());
			contition.setOsType(MobileOsTypeEnum.ANDROID.getKey());// 无论android IOS推送参数一致
			SysPushParameter pushParam = sysPushParameterService.selectOne(contition);
			if (StringUtils.isNotBlank(EnumUtils.getMsgByKvmEnumKey(smsType.getKey(), SmsTypeEnum.values()))) {
				if( urlparam == null){
					urlparam = Maps.newHashMap();
				}
				urlparam.put("customUrlKey", smsType.getMsg());
			}
			LOGGER.info("pushByAppId 输入参数：appType={},title={},content={},urlparam={}",	new Object[] { appType,  title, content, urlparam });
			IGtPush push = new IGtPush(pushParam.getHost(), pushParam.getAppKey(),pushParam.getMasterSecret());
			
			List<String> appIds = new ArrayList<String>();
			appIds.add(pushParam.getAppId());
			
			//新增机型 ANDROID
			AppConditions cdt1 = new AppConditions();	       
	        List<String> phoneTypes1 = new ArrayList<String>();
	        phoneTypes1.add("ANDROID");
	        cdt1.addCondition(AppConditions.PHONE_TYPE, phoneTypes1);
	        //安卓使用通知模板
	        NotificationTemplate template1 = fillNotificationTemplate(pushParam.getAppId(), pushParam.getAppKey(), title, content,urlparam);
	        
			// 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
			AppMessage message1 = new AppMessage();
			message1.setData(template1);
			message1.setAppIdList(appIds);
			message1.setOffline(true);
			message1.setOfflineExpireTime(24 * 3600 * 1000L);
			message1.setConditions(cdt1);

			IPushResult ret = push.pushMessageToApp(message1);
			retCode = loggingPushRlt(retCode, ret);
			LOGGER.info("pushByAppId 推送返回：code={},message={}", retCode.getCode(),retCode.getMessage());
			//新增机型
			AppConditions cdt2 = new AppConditions();	       
	        List<String> phoneTypes2 = new ArrayList<String>();
	        phoneTypes2.add("IOS");//IOS
	        cdt2.addCondition(AppConditions.PHONE_TYPE, phoneTypes2);


			//ios使用
	        TransmissionTemplate template2 =  fillTransmissionTemplate(pushParam.getAppId(), pushParam.getAppKey(), title, content, urlparam);
	        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
	        AppMessage message2 = new AppMessage();
			message2.setData(template2);
			message2.setAppIdList(appIds);
			message2.setOffline(true);
			message2.setOfflineExpireTime(24 * 3600 * 1000L);
			message2.setConditions(cdt2);
			
			ret = push.pushMessageToApp(message2);
			retCode = loggingPushRlt(retCode, ret);
			LOGGER.info("pushByAppId 推送返回：code={},message={}", retCode.getCode(),retCode.getMessage());
			return new CommonTCSResult(0, "推送成功");
		} catch (PushAppException e) {
			LOGGER.warn("pushByAppId exception ", e);
		} catch (Exception e) {
			LOGGER.warn("pushByAppId exception ", e);
		}

		return new CommonTCSResult(-1, "处理失败");

	}


	public CommonTCSResult loggingPushRlt(CommonTCSResult retCode,IPushResult ret) {
		if (ret != null) {
			if (ret.getResponse().containsKey("result")) {
				if (PushMessageUtil.pushSuccessResultCodeMap.containsKey(ret.getResponse().get("result"))) {
					retCode = new CommonTCSResult(0, "推送成功");
				} else {
					Object result = ret.getResponse().get("result");
					retCode = new CommonTCSResult(-1, String.valueOf(result));
				}

			}
		} else {
			retCode = new CommonTCSResult(-1, "服务器响应异常");
		}
		return retCode;
	}


	/**
	 * 单用户推送
	 * 
	 * @param appType
	 *            appType
	 * @param smsType
	 *            业务模块
	 * @param userId
	 *            用户基础信息表用户ID
	 * @param title
	 * @param content
	 * @param urlparam
	 *            前端打开页面所需参数, SmsTypeEnum msg值为：cusDetail
	 *            urlparam.put("customerId","1111111") msg值为：teamDetail
	 *            urlparam.put("userNumber","1111111")
	 * @param isNeedAppMsg
	 *            是否需要推送系统消息中心消息 true 同时推送系统消息中心消息
	 * @return CommonTCSResult#code 0：成功；-1 失败
	 * @throws Exception
	 */
	public CommonTCSResult pushMessage(AppTypeEnum appType,
			SmsTypeEnum smsType, String userId, String title, String content,
			Map<String, Object> urlparam, boolean isNeedAppMsg)
			 {
		
		CommonTCSResult retCode;
		try {
			if (isNeedAppMsg) {// 需要推送系统消息中心消息
				SysMsg msg = fillPersonalMsg(appType, userId, content);
				sysMsgService.addMsg(msg);
				
			}
			retCode = new CommonTCSResult();

			SysPushParameter contition = new SysPushParameter();
			contition.setAppType(appType.getKey());
			SmCustomerDevice customerDevice = smCustomerDeviceService.queryCustomerDevice(appType.getKey(), userId);
			String osType = "";
			boolean isIos = false;
			if (customerDevice != null) {
				osType = customerDevice.getDeviceType();
				contition.setOsType(osType);
				if ("ios".equals(customerDevice.getDeviceType().toLowerCase())) {
					isIos = true;
				}
			} else {
				return new CommonTCSResult(-1, "业务数据错误，用户无对应设备信息");
			}
			SysPushParameter pushParam = sysPushParameterService.selectOne(contition);
			if (pushParam == null) {
				return new CommonTCSResult(-1, "系统配置错误，未配置推送APP信息");
			}
			IGtPush push = new IGtPush(pushParam.getHost(), pushParam.getAppKey(),
					pushParam.getMasterSecret());
			
			if (StringUtils.isNotBlank(EnumUtils.getMsgByKvmEnumKey(smsType.getKey(), SmsTypeEnum.values()))) {
				if( urlparam == null){
					urlparam = Maps.newHashMap();
				}
				urlparam.put("customUrlKey", smsType.getMsg());
			}
			
			SingleMessage message = new SingleMessage();
			message.setOffline(true);
			// 离线有效时间，单位为毫秒，可选
			message.setOfflineExpireTime(24 * 3600 * 1000L);
			if(isIos){
				TransmissionTemplate template =  fillTransmissionTemplate(pushParam.getAppId(), pushParam.getAppKey(), title, content, urlparam);
				message.setData(template);
			}else{
				NotificationTemplate template = fillNotificationTemplate(pushParam.getAppId(), pushParam.getAppKey(), title, content, urlparam);
				message.setData(template);
			}
			// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
			message.setPushNetWorkType(0);
			Target target = new Target();
			target.setAppId(pushParam.getAppId());
			
			if (customerDevice != null) {
				target.setClientId(customerDevice.getDeviceToken());
			}

			IPushResult ret = null;
			
			//ret = push.pushAPNMessageToSingle(pushParam.getAppId(),
			//push.setBadgeForCID("+1", pushParam.getAppId(),Lists.newArrayList(customerDevice.getDeviceToken())); //badge设置
			ret = push.pushMessageToSingle(message, target);
			
			retCode = loggingPushRlt(retCode, ret);
			LOGGER.info("pushMessage 输入参数：appType={},userId={},deviceToken={},osType={},title={},content={},url={}",
					new Object[] { appType,userId,customerDevice.getDeviceToken(),osType, title, content, urlparam });
			LOGGER.info("pushByAppId 推送返回：code={},message={}", retCode.getCode(),retCode.getMessage());
			// 推送记录  在LOGGER日记中记录
			/*SysPushMessage model = new SysPushMessage();
			model.setTitle(title);
			model.setContent(content);
			model.setUserId(userId);
			model.setDeviceToken(customerDevice == null ? "" : customerDevice
					.getDeviceToken());
			model.setOsType(osType);
			model.setHandle(1);// 已处理
			if (retCode.getCode() == 0) {
				model.setStatus(1);// 已发送
			} else {
				model.setStatus(0);// 已发送
			}

			model.setStatusDesc(retCode.getMessage());
			sysPushMessageService.insert(model);*/
			return new CommonTCSResult(0, "处理成功");
		} catch (PushSingleException e) {
			LOGGER.warn("pushMessage exception ", e);
		} catch (Exception e) {
			LOGGER.warn("pushMessage exception ", e);
		}
		
		return new CommonTCSResult(-1, "处理失败");

	}

	/**
	 * 封装站内消息
	 * @param appType
	 * @param userId
	 * @param content
	 * @return
	 */
	public SysMsg fillPersonalMsg(AppTypeEnum appType, String userId,String content) {
		SysMsg msg = new SysMsg();
		msg.setContent(content);
		msg.setStatus(0);// 发布
		msg.setUserNumber(userId);
		msg.setReadStatus(0);// 未读
		msg.setAppType(appType.getKey());
		return msg;
	}
	/**
	 * 封装站内消息
	 * @param appType
	 * @param userId
	 * @param content
	 * @param personalMsgType 个人消息类型
	 * @return
	 */
	public SysMsg fillPersonalMsg(AppTypeEnum appType, String userId,String content,PersonalMsgTypeEnum personalMsgType) {
		SysMsg msg = new SysMsg();
		msg.setContent(content);
		msg.setStatus(0);// 发布
		msg.setUserNumber(userId);
		msg.setReadStatus(0);// 未读
		msg.setAppType(appType.getKey());
		msg.setTypeName(personalMsgType != null ? personalMsgType.getValue() : null);
		return msg;
	}
	/**
	 * 
	 * @param appType app类型
	 * @param smsType 推送触发业务类型
	 * @param userId 推送用户
	 * @param title  推送标题
	 * @param content 推送内容
	 * @param urlparam 前端打开页面所需参数, SmsTypeEnum msg值为：cusDetail
	 *            urlparam.put("customerId","1111111") msg值为：teamDetail
	 *            urlparam.put("userNumber","1111111")
	 * @param isNeedAppMsg 是否需要推送站内通知
	 * @param personalMsgType 个人消息类型
	 * @return
	 */
	public CommonTCSResult pushMessage(AppTypeEnum appType,
			SmsTypeEnum smsType, String userId, String title, String content,
			Map<String, Object> urlparam, boolean isNeedAppMsg,PersonalMsgTypeEnum personalMsgType)
			 {
		CommonTCSResult retCode;
		try {
			if (isNeedAppMsg) {// 需要推送系统消息中心消息
				SysMsg msg = fillPersonalMsg(appType, userId, content,personalMsgType);
				sysMsgService.addMsg(msg);
				
			}
			retCode = new CommonTCSResult();

			SysPushParameter contition = new SysPushParameter();
			contition.setAppType(appType.getKey());
			SmCustomerDevice customerDevice = smCustomerDeviceService.queryCustomerDevice(appType.getKey(), userId);
			String osType = "";
			boolean isIos = false;
			if (customerDevice != null) {
				osType = customerDevice.getDeviceType();
				contition.setOsType(osType);
				if ("ios".equals(customerDevice.getDeviceType().toLowerCase())) {
					isIos = true;
				}
			} else {
				return new CommonTCSResult(-1, "业务数据错误，用户无对应设备信息");
			}
			SysPushParameter pushParam = sysPushParameterService.selectOne(contition);
			if (pushParam == null) {
				return new CommonTCSResult(-1, "系统配置错误，未配置推送APP信息");
			}
			IGtPush push = new IGtPush(pushParam.getHost(), pushParam.getAppKey(),
					pushParam.getMasterSecret());
			
			if (StringUtils.isNotBlank(EnumUtils.getMsgByKvmEnumKey(smsType.getKey(), SmsTypeEnum.values()))) {
				if( urlparam == null){
					urlparam = Maps.newHashMap();
				}
				urlparam.put("customUrlKey", smsType.getMsg());
			}
			
			SingleMessage message = new SingleMessage();
			message.setOffline(true);
			// 离线有效时间，单位为毫秒，可选
			message.setOfflineExpireTime(24 * 3600 * 1000L);
			if(isIos){
				TransmissionTemplate template =  fillTransmissionTemplate(pushParam.getAppId(), pushParam.getAppKey(), title, content, urlparam);
				message.setData(template);
			}else{
				NotificationTemplate template = fillNotificationTemplate(pushParam.getAppId(), pushParam.getAppKey(), title, content, urlparam);
				message.setData(template);
			}
			// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
			message.setPushNetWorkType(0);
			Target target = new Target();
			target.setAppId(pushParam.getAppId());
			
			if (customerDevice != null) {
				target.setClientId(customerDevice.getDeviceToken());
			}

			IPushResult ret = push.pushMessageToSingle(message, target);			
			retCode = loggingPushRlt(retCode, ret);
			
			LOGGER.info("pushMessage 输入参数：appType={},userId={},deviceToken={},osType={},title={},content={},url={}",
					new Object[] { appType,userId,customerDevice.getDeviceToken(),osType, title, content, urlparam });
			LOGGER.info("pushByAppId 推送返回：code={},message={}", retCode.getCode(),retCode.getMessage());
			// 推送记录 在LOGGER日记中记录
			/*SysPushMessage model = new SysPushMessage();
			model.setTitle(title);
			model.setContent(content);
			model.setUserId(userId);
			model.setDeviceToken(customerDevice == null ? "" : customerDevice
					.getDeviceToken());
			model.setOsType(osType);
			model.setHandle(1);// 已处理
			if (retCode.getCode() == 0) {
				model.setStatus(1);// 已发送
			} else {
				model.setStatus(0);// 已发送
			}

			model.setStatusDesc(retCode.getMessage());
			sysPushMessageService.insert(model);*/
			return new CommonTCSResult(0, "处理成功");
		} catch (PushSingleException e) {
			LOGGER.warn("pushMessage exception ", e);
		} catch (Exception e) {
			LOGGER.warn("pushMessage exception ", e);
		}
		
		return new CommonTCSResult(-1, "处理失败");

	}

	/**
	 * 单用户推送
	 * 
	 * @param appType
	 *            appType
	 * @param smsType
	 *            业务模块
	 * @param userId
	 *            用户基础信息表用户ID
	 * @param title
	 * @param content
	 * @param urlparam
	 *            前端打开页面所需参数(cusDetail－customerId;teamDetail－userNumber)
	 * @return CommonTCSResult#code 0：成功；-1 失败
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public CommonTCSResult pushMessage(SysPushMessage pushMessage)
			throws Exception {
		if (pushMessage == null) {
			return new CommonTCSResult(-1, "参数错误，pushMessage不能为null");
		}
		if (pushMessage.getAppType() == null) {
			return new CommonTCSResult(-1, "参数错误，appType不能为空");
		}

		if (StringUtils.isBlank(pushMessage.getDeviceToken())) {
			return new CommonTCSResult(-1, "参数错误，deviceToken不能为空");
		}
		if (StringUtils.isBlank(pushMessage.getModuleId())) {
			return new CommonTCSResult(-1, "参数错误，ModuleId不能为空");
		}
		CommonTCSResult retCode = new CommonTCSResult();

		SysPushParameter contition = new SysPushParameter();
		contition.setAppType(pushMessage.getAppType());

		String osType = "";
		boolean isIos = false;

		osType = pushMessage.getOsType().toLowerCase();
		contition.setOsType(osType);
		if ("ios".equals(osType)) {
			isIos = true;
		}

		SysPushParameter pushParam = sysPushParameterService.selectOne(contition);
		if (pushParam == null) {
			return new CommonTCSResult(-1, "系统配置错误，未配置推送APP信息");
		}
		
		IGtPush push = new IGtPush(pushParam.getHost(), pushParam.getAppKey(),
				pushParam.getMasterSecret());
		Map<String, Object> urlparam = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(pushMessage.getExtend1())) {
			urlparam = JsonUtils.fromJsonToObject(pushMessage.getExtend1(),
					Map.class);
		}

		SmsTypeEnum smsType = (SmsTypeEnum) EnumUtils.getKvmEnumByValue(
				pushMessage.getModuleId(), SmsTypeEnum.values());
		if (StringUtils.isNotBlank(EnumUtils.getMsgByKvmEnumKey(
				smsType.getKey(), SmsTypeEnum.values()))
				) {
			if( urlparam == null){
				urlparam = Maps.newHashMap();
			}
			urlparam.put("customUrlKey", smsType.getMsg());
		}
		
		SingleMessage message = new SingleMessage();
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 3600 * 1000L);
		if(isIos){
			TransmissionTemplate template =  fillTransmissionTemplate(pushParam.getAppId(), pushParam.getAppKey(), pushMessage.getTitle(),  pushMessage.getContent(), urlparam);
			message.setData(template);
		}else{
			NotificationTemplate template = fillNotificationTemplate(
					pushParam.getAppId(), pushParam.getAppKey(),  pushMessage.getTitle(),  pushMessage.getContent(),
					 urlparam);
			message.setData(template);
		}
		// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
		message.setPushNetWorkType(0);
		Target target = new Target();
		target.setAppId(pushParam.getAppId());
		target.setClientId(pushMessage.getDeviceToken());

		IPushResult ret = null;
		ret = push.pushMessageToSingle(message, target);
		
		retCode = loggingPushRlt(retCode, ret);
		LOGGER.info("pushMessage 输入参数：appType={},userId={},deviceToken={},osType={},title={},content={},url={}",
				new Object[] { pushMessage.getAppType(),pushMessage.getUserId(),pushMessage.getDeviceToken(),pushMessage.getOsType(),
						pushMessage.getTitle(), pushMessage.getContent(),
						pushMessage.getExtend1() });
		LOGGER.info("pushByAppId 推送返回：code={},message={}", retCode.getCode(),retCode.getMessage());
		return retCode;

	}
	
	
	@SuppressWarnings("unchecked")
	public CommonTCSResult pushMessageList(String taskId) {

		CommonTCSResult retCode;
		try {
			
			retCode = new CommonTCSResult();
			Page<SysPushMessage> page = new Page<SysPushMessage>(1, 100); // 分批执行
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("extend2", taskId);
			PaginatorResponse<SysPushMessage> msgPage = sysPushMessageService.querySysPushMessageList(page, conditions);
			List<SysPushMessage> pushList = msgPage.getDatas();
			if(pushList == null || (pushList!=null && pushList.size() < 1)){
				return new CommonTCSResult(-1, "无待处理数据，处理成功");
			}
			
			
			SysPushMessage temp = pushList.get(0);
			SysPushParameter contition = new SysPushParameter();
			contition.setAppType(temp.getAppType());
			contition.setOsType(temp.getOsType());
			
			SysPushParameter pushParam = sysPushParameterService.selectOne(contition);
			if (pushParam == null) {
				return new CommonTCSResult(-1, "系统配置错误，未配置推送APP信息");
			}
			
			IGtPush push = new IGtPush(pushParam.getHost(), pushParam.getAppKey(),	pushParam.getMasterSecret());
			Map<String,Object> urlparam = Maps.newHashMap();
			if (StringUtils.isNotBlank(EnumUtils.getMsgByKvmEnumKey(SmsTypeEnum.valueOf(temp.getModuleId()).getKey(), SmsTypeEnum.values()))) {
				urlparam.put("customUrlKey", temp.getModuleId());
			}
			if (StringUtils.isNotBlank(temp.getExtend1())) {
				urlparam = JsonUtils.fromJsonToObject(temp.getExtend1(),Map.class);
			}
			
			ListMessage message = new ListMessage();
			message.setOffline(true);
			// 离线有效时间，单位为毫秒，可选
			message.setOfflineExpireTime(24 * 3600 * 1000L);
			if ("ios".equals(temp.getOsType())) {
				TransmissionTemplate template =  fillTransmissionTemplate(pushParam.getAppId(), pushParam.getAppKey(), temp.getTitle(), temp.getContent(), urlparam);
				message.setData(template);
			}else{
				NotificationTemplate template = fillNotificationTemplate(pushParam.getAppId(), pushParam.getAppKey(),  temp.getTitle(), temp.getContent(), urlparam);
				message.setData(template);
			}
			
			// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
			message.setPushNetWorkType(0);
			// 配置推送目标
	        List<Target> targets = new ArrayList<Target>();
	        List<String> userIdList = Lists.newArrayList();
	        List<String> tokenList = Lists.newArrayList();
	        for(SysPushMessage item : pushList){
	        	Target target = new Target();	        
		        target.setAppId(pushParam.getAppId());
		        if (StringUtils.isNotBlank(item.getDeviceToken())) {
					target.setClientId(item.getDeviceToken());
				}
		        
		        targets.add(target);
		        userIdList.add(item.getUserId());
		        tokenList.add(item.getDeviceToken());
	        }
	        
	       
	        // taskId用于在推送时去查找对应的message
	        String pushTaskId = push.getContentId(message);
	        IPushResult ret = push.pushMessageToList(pushTaskId, targets);				
			
			retCode = loggingPushRlt(retCode, ret);
			LOGGER.info("pushMessage 输入参数：appType={},userId={},deviceToken={},osType={},title={},content={},url={}",
					new Object[] { temp.getAppType(),userIdList,tokenList,temp.getAppType(),
					temp.getTitle(), temp.getContent(),
					temp.getExtend1() });
			LOGGER.info("pushMessageList 推送返回：code={},message={}", retCode.getCode(),retCode.getMessage());			
			
			return new CommonTCSResult(0, "处理成功");
		} catch (PushSingleException e) {
			LOGGER.warn("pushMessage exception ", e);
		} catch (Exception e) {
			LOGGER.warn("pushMessage exception ",e);
		}
		
		return new CommonTCSResult(-1, "处理失败");

	}



	public static NotificationTemplate fillNotificationTemplate(String appId,
			String appkey, String title, String content,Map<String, Object> urlparam) {
		NotificationTemplate template = new NotificationTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appId);
		template.setAppkey(appkey);
		// 设置通知栏标题与内容
		if(StringUtils.isNotBlank(title)){
			template.setTitle(title);
		}
		template.setText(content);
		// 配置通知栏图标
		// template.setLogo("icon.png");
		// 配置通知栏网络图标
		//template.setLogoUrl("http://image.tophlc.com/0ddbcff59bab92244a29fb2fe16aed55?f=png");
		// 设置通知是否响铃，震动，或者可清除 前端控制
		//template.setIsRing(true);
		//template.setIsVibrate(true);
		template.setIsClearable(true);
		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		template.setTransmissionType(2);
		JSONObject jsonObject = new JSONObject(urlparam);
		template.setTransmissionContent(jsonObject.toJSONString());
		
		// 设置定时展示时间
		// template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
		return template;
	}
	
	public static TransmissionTemplate  fillTransmissionTemplate (String appId,
			String appkey, String title, String content, Map<String, Object> urlparam) {
		
		TransmissionTemplate template = new TransmissionTemplate();
	    template.setAppId(appId);
	    template.setAppkey(appkey);
	    JSONObject jsonObject = new JSONObject(urlparam);
		template.setTransmissionContent(jsonObject.toJSONString());
	    template.setTransmissionType(2);
	    APNPayload payload = new APNPayload();
	    //payload.setBadge(1);
	    payload.setContentAvailable(1);
	    //payload.setSound("default"); 前端控制
	    payload.setCategory("$由客户端定义");
	    //简单模式APNPayload.SimpleMsg 
	    payload.setAlertMsg(new APNPayload.SimpleAlertMsg(content));
	    payload.setAutoBadge("+1");
	    //字典模式使用下者
	    //payload.setAlertMsg(getDictionaryAlertMsg());
	    template.setAPNInfo(payload);
	    return template;
		 
	}


	/**
	 * 异步推送消息
	 * 
	 * @param appType
	 * @param smsType
	 * @param userId
	 * @param title
	 * @param content
	 * @param urlparam
	 * @return
	 * @throws Exception
	 */
	public CommonTCSResult pushMessageAsyn(AppTypeEnum appType,
			SmsTypeEnum smsType, String userId, String title, String content,
			Map<String, Object> urlparam, boolean isNeedAppMsg)
			 {
		try{
			
			if (isNeedAppMsg) {// 需要推送系统消息中心消息
				SysMsg msg = fillPersonalMsg(appType, userId, content);
				sysMsgService.addMsg(msg);
			}
			CommonTCSResult retCode = new CommonTCSResult();
			if (appType == null) {
				return new CommonTCSResult(-1, "参数错误，appType不能为空");
			}
	
			if (StringUtils.isBlank(userId)) {
				return new CommonTCSResult(-1, "参数错误，userId不能为空");
			}
			SysPushMessage model = new SysPushMessage();
			model.setTitle(title);
			model.setContent(content);
			model.setUserId(userId);
			SmCustomerDevice customerDevice = smCustomerDeviceService.queryCustomerDevice(appType.getKey(),userId);
			if (customerDevice == null) {
				throw new Exception("userId错误（无设备信息）");
			}
			model.setDeviceToken(customerDevice.getDeviceToken());
			model.setAppType(appType.getKey());
			model.setOsType(customerDevice.getDeviceType());
			model.setModuleId(smsType.getValue());
			model.setHandle(0);// 1 已处理 0 未处理
			model.setStatus(0);// 已发送
			
			if(urlparam!=null){
			JSONObject jsonObject = new JSONObject(urlparam);
			model.setExtend1(jsonObject.toJSONString());
			}
			sysPushMessageService.insert(model);
			
			retCode = new CommonTCSResult(0, "处理成功");
			return retCode;
			
		 }catch(Exception e){
			LOGGER.warn("pushMessageAsyn exception!", e);
		}
		return new CommonTCSResult(-1, "处理失败");

	}
	/**
	 * 
	 */
	public CommonTCSResult pushMessageAsyn(AppTypeEnum appType,
			SmsTypeEnum smsType, String userId, String title, String content,
			Map<String, Object> urlparam, boolean isNeedAppMsg,PersonalMsgTypeEnum personalMsgType)
			 {
		try{
			
			if (isNeedAppMsg) {// 需要推送系统消息中心消息
				SysMsg msg = fillPersonalMsg(appType, userId, content,personalMsgType);
				sysMsgService.addMsg(msg);
			}
			CommonTCSResult retCode = new CommonTCSResult();
			if (appType == null) {
				return new CommonTCSResult(-1, "参数错误，appType不能为空");
			}
	
			if (StringUtils.isBlank(userId)) {
				return new CommonTCSResult(-1, "参数错误，userId不能为空");
			}
			SysPushMessage model = new SysPushMessage();
			model.setTitle(title);
			model.setContent(content);
			model.setUserId(userId);
			SmCustomerDevice customerDevice = smCustomerDeviceService.queryCustomerDevice(appType.getKey(),userId);
			if (customerDevice == null) {
				throw new Exception("userId错误（无设备信息）");
			}
			model.setDeviceToken(customerDevice.getDeviceToken());
			model.setAppType(appType.getKey());
			model.setOsType(customerDevice.getDeviceType());
			model.setModuleId(smsType.getValue());
			model.setHandle(0);// 1 已处理 0 未处理
			model.setStatus(0);// 已发送
			
			if(urlparam!=null){
			JSONObject jsonObject = new JSONObject(urlparam);
			model.setExtend1(jsonObject.toJSONString());
			}
			sysPushMessageService.insert(model);
			
			retCode = new CommonTCSResult(0, "处理成功");
			return retCode;
			
		 }catch(Exception e){
			LOGGER.warn("pushMessageAsyn exception!", e);
		}
		return new CommonTCSResult(-1, "处理失败");

	}
	/**
	 * 
	 * @param appType
	 * @param smsType
	 * @param userId
	 * @param title
	 * @param content
	 * @param urlparam
	 * @param isNeedAppMsg
	 * @return
	 */
	public CommonTCSResult pushMessageListAsyn(AppTypeEnum appType,
			                                   SmsTypeEnum smsType,
			                                   Collection<String> userIds,
			                                   String title,
			                                   String content,
			                                   Map<String, Object> urlparam,
			                                   boolean isNeedAppMsg) {
		
		
		try{
			CommonTCSResult retCode = new CommonTCSResult();
			if (appType == null) {
				return new CommonTCSResult(-1, "参数错误，appType不能为空");
			}

			if (userIds == null) {
				return new CommonTCSResult(-1, "参数错误，userId不能为空");
			}
			//userIds 分批处理		
			List<List<String>> userIdss =  Lists.newArrayList();
			InterceptUtility.subsection(Lists.newArrayList(userIds) , userIdss,200);
			for(final List<String> list :userIdss){
				List<SysMsg> msgs = new ArrayList<SysMsg>();
				List<SysPushMessage> sysPushMessageList = new ArrayList<SysPushMessage>();
				Map<String,SmCustomerDevice> deviceTokeMap = smCustomerDeviceService.queryCustomerDevices(appType.getKey(), list);
				Iterator<String> pushMsgIt = list.iterator();
				String uuId = StringUtils.getUUID();
				while(pushMsgIt.hasNext()){
					SysPushMessage model = new SysPushMessage();
					String userId = pushMsgIt.next();
					SmCustomerDevice device = deviceTokeMap.get(userId);
					if(device ==null ||(device != null && StringUtils.isBlank(device.getDeviceToken()))) {
						LOGGER.warn("userId:{},无设备信息",userId);
					}
					model.setTitle(title);
					model.setContent(content);
					
					model.setUserId(userId);	
					model.setDeviceToken(device == null ? "" :device.getDeviceToken());
					model.setAppType(appType.getKey());
					model.setOsType(device == null ? "" : device.getDeviceType());
					if(device !=null ){
						model.setExtend2(uuId + device.getDeviceType());
					}
					
					model.setModuleId(smsType.getValue());
					model.setHandle(0);// 1 已处理 0 未处理
					model.setStatus(0);// 已发送
					model.setCreateTime(new Date());
					model.setUpdateTime(new Date());
					
					if(StringUtils.isNotBlank(EnumUtils.getMsgByKvmEnumKey(
							smsType.getKey(), SmsTypeEnum.values()))
							) {
						if( urlparam == null){
							urlparam = Maps.newHashMap();
						}
						urlparam.put("customUrlKey", smsType.getMsg());
					}
					
					if(urlparam!=null){				
					JSONObject jsonObject = new JSONObject(urlparam);
					model.setExtend1(jsonObject.toJSONString());
					}
					sysPushMessageList.add(model);
					
					SysMsg msg = fillPersonalMsg(appType, userId, content);
					msg.setStartTime(new Date());
					msg.setCrtTime(new Date());
					msg.setModifyTime(new Date());
					msgs.add(msg);
				
				}
				
				if (isNeedAppMsg) {// 需要推送系统消息中心消息
					sysMsgService.addMsgs(msgs);
				}
				
				sysPushMessageService.saveBatch(sysPushMessageList);
			}
			
			retCode = new CommonTCSResult(0, "处理成功");
			return retCode;
			
		 }catch(Exception e){
			LOGGER.warn("pushMessageAsyn exception!", e);
		}
		return new CommonTCSResult(-1, "处理失败");
		
	}
	
	public CommonTCSResult pushMessageListAsyn(AppTypeEnum appType,
            SmsTypeEnum smsType,
            Collection<String> userIds,
            String title,
            String content,
            Map<String, Object> urlparam,
            boolean isNeedAppMsg,
            PersonalMsgTypeEnum personalMsgType
			) {

			
			try{
			CommonTCSResult retCode = new CommonTCSResult();
			if (appType == null) {
			return new CommonTCSResult(-1, "参数错误，appType不能为空");
			}
			
			if (userIds == null) {
			return new CommonTCSResult(-1, "参数错误，userId不能为空");
			}
			//userIds 分批处理		
			List<List<String>> userIdss =  Lists.newArrayList();
			InterceptUtility.subsection(Lists.newArrayList(userIds) , userIdss,200);
			for(final List<String> list :userIdss){
			List<SysMsg> msgs = new ArrayList<SysMsg>();
			List<SysPushMessage> sysPushMessageList = new ArrayList<SysPushMessage>();
			Map<String,SmCustomerDevice> deviceTokeMap = smCustomerDeviceService.queryCustomerDevices(appType.getKey(), list);
			Iterator<String> pushMsgIt = list.iterator();
			String uuId = StringUtils.getUUID();
			while(pushMsgIt.hasNext()){
			SysPushMessage model = new SysPushMessage();
			String userId = pushMsgIt.next();
			SmCustomerDevice device = deviceTokeMap.get(userId);
			if(device ==null ||(device != null && StringUtils.isBlank(device.getDeviceToken()))) {
			LOGGER.warn("userId:{},无设备信息",userId);
			}
			model.setTitle(title);
			model.setContent(content);
			
			model.setUserId(userId);	
			model.setDeviceToken(device == null ? "" :device.getDeviceToken());
			model.setAppType(appType.getKey());
			model.setOsType(device == null ? "" : device.getDeviceType());
			if(device !=null ){
			model.setExtend2(uuId + device.getDeviceType());
			}
			
			model.setModuleId(smsType.getValue());
			model.setHandle(0);// 1 已处理 0 未处理
			model.setStatus(0);// 已发送
			model.setCreateTime(new Date());
			model.setUpdateTime(new Date());
			
			if(StringUtils.isNotBlank(EnumUtils.getMsgByKvmEnumKey(
			smsType.getKey(), SmsTypeEnum.values()))
			) {
			if( urlparam == null){
			urlparam = Maps.newHashMap();
			}
			urlparam.put("customUrlKey", smsType.getMsg());
			}
			
			if(urlparam!=null){				
			JSONObject jsonObject = new JSONObject(urlparam);
			model.setExtend1(jsonObject.toJSONString());
			}
			sysPushMessageList.add(model);
			
			SysMsg msg = fillPersonalMsg(appType, userId, content,personalMsgType);
			msg.setStartTime(new Date());
			msg.setCrtTime(new Date());
			msg.setModifyTime(new Date());
			msgs.add(msg);
			
			}
			
			if (isNeedAppMsg) {// 需要推送系统消息中心消息
			sysMsgService.addMsgs(msgs);
			}
			
			sysPushMessageService.saveBatch(sysPushMessageList);
			}
			
			retCode = new CommonTCSResult(0, "处理成功");
			return retCode;
			
			}catch(Exception e){
			LOGGER.warn("pushMessageAsyn exception!", e);
			}
			return new CommonTCSResult(-1, "处理失败");
			
			}

	public void pushMessageAsynTask() {
		Page<SysPushMessage> page = new Page<SysPushMessage>(1, 100); // 分批执行
		Map<String, Object> conditions = new HashMap<String, Object>();
		PaginatorResponse<SysPushMessage> msgPage = sysPushMessageService.querySysPushMessageList(page, conditions);
		List<SysPushMessage> msgList = msgPage.getDatas();
		CommonTCSResult retCode = null ;
		for (SysPushMessage item : msgList) {
			try {
				
				if(StringUtils.isBlank(item.getExtend2())){ //单条推送
					retCode = pushMessage(item);
				}else{//按taskId 批量推送
					retCode = pushMessageList(item.getExtend2());
				}
				item.setHandle(1);// 已经处理
				item.setStatusDesc(retCode.getMessage());
				if (retCode.getCode() == 0) { // 成功
					item.setStatus(1);// 发送成功
				} else {
					item.setStatus(0);// 发送失败
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//
		if (msgList.size() > 0) {
			//sysPushMessageService.renewBatch(msgList);
			sysPushMessageService.deleteBatch(msgList);
		}

	}
	@SuppressWarnings("unchecked")
	public void SysPushMessageTask() {
		Page<SysPushArtificialQueue> page = new Page<SysPushArtificialQueue>(1, 100); // 分批执行
		Map<String, Object> conditions = new HashMap<String, Object>();
		PaginatorResponse<SysPushArtificialQueue> msgPage = sysPushArtificialQueueService.querySysPushMessageList(page, conditions);
		List<SysPushArtificialQueue> msgList = msgPage.getDatas();
		for (SysPushArtificialQueue item : msgList) {
			//Integer dateResult= com.eshop4j.core.util.DateUtils.compareDate(new Date(), item.getStartTime());
			//if(item.getStartType().intValue() == 0|| (item.getStartTime() !=null && dateResult != null && dateResult != -1)){//即时 或设置定时到指定时间
				Map<String,Object> urlParam = Maps.newHashMap();
				if(item.getSendType()!=null && item.getSendType().intValue() == 0){ //公告推送
					 urlParam.put("msgUrl", item.getLink());
					 pushByAppId((AppTypeEnum)EnumUtils.getEnumByKey(item.getAppType(), AppTypeEnum.values()),SmsTypeEnum.LCSSYSNOTICERELEASE,"站内公告",item.getContent(),urlParam);
				}else{ // 系统推送
					urlParam.put("activityUrl", item.getLink());
					if(ObjectUtils.equals(item.getSendObjectType(), 0)){ //全站推送
						pushByAppId((AppTypeEnum)EnumUtils.getEnumByKey(item.getAppType(), AppTypeEnum.values()),SmsTypeEnum.LCSSYSACTIVITYRELEASE,"站内活动",item.getContent(),urlParam);
					}else{//指定用户推送
						if(StringUtils.isNotBlank(item.getUserIds()))
						    BatchSinglePush((AppTypeEnum)EnumUtils.getEnumByKey(item.getAppType(), AppTypeEnum.values()), SmsTypeEnum.LCSSYSACTIVITYRELEASE, JsonUtils.fromJsonToObject(item.getUserIds(), List.class), "站内活动", item.getContent(), urlParam, false, null);
					}
				}
				item.setStatus(1);//已经推送
			//}
			
		}
		//更新状态
		if (msgList.size() > 0) {
			sysPushArtificialQueueService.renewBatch(msgList);
		}

	}
	/**
	 * 客户定期产品在投资额超（含）10万元，且3天后到期回款
	 */
	public void advancePaymentReminder() {
		DateTime now = DateTime.now();
		List<AdvancePayment> list = sysMsgService.queryAdvancePayment(now.toString("yyyy-MM-dd"), now.plusDays(3).toString("yyyy-MM-dd"));
		List<List<AdvancePayment>> listss =  Lists.newArrayList();
		InterceptUtility.subsection(Lists.newArrayList(list) , listss,100);
		LOGGER.info("advancePaymentReminder 推送数量 size={}",list.size());
		String contentTemplate = "";
		String invContentTemplate = "";
		if(list != null && list.size() > 0){
			contentTemplate = configHelper.getValue(SysConfigConstant.PUSHMESSAGE_LCUSTOMERBIGAMOUNTRETURN);
			invContentTemplate = configHelper.getValue(SysConfigConstant.PUSHMESSAGE_CUSTOMERBIGAMOUNTRETURN_INV_FLOAT);
			for(List<AdvancePayment> listItem :listss){
				for(AdvancePayment item :listItem){
					Map<String,Object> urlparam = Maps.newHashMap();
					//urlparam.put("status", 2);
					pushMessage(AppTypeEnum.INVESTOR, SmsTypeEnum.PERSONALMSGCT_INC, item.getInvestorUserId(), "即将回款提醒", String.format(invContentTemplate,DateUtils.format(item.getBizTime(), DateUtils.FORMAT_SHORT_CN) ,item.getProductName(),item.getInvestAmt() == null ? "" : item.getInvestAmt().setScale(2, BigDecimal.ROUND_DOWN)), null, true);
					if(item.getIsRedemption() != null && item.getIsRedemption().intValue() != 0){
						urlparam.clear();
						urlparam.put("customerId", item.getInvestorUserId());
						pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.LCUSTOMERBIGAMOUNTRETURN, item.getCfpUserId(), "客户即将回款提醒", String.format(contentTemplate,item.getInvestorName()+item.getInvestorMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"),item.getProductName(),item.getInvestAmt() == null ? "" : item.getInvestAmt().setScale(2, BigDecimal.ROUND_DOWN)), urlparam, true);
					}
					
				}
			}
		}
	}
	/**
	 * 批量单推 （不同内容推送）		减少与服务端的交付
	 * 使用场景：推送一批用户，并且每个用户推送的你内容统一（包括显示内容和透传的数据都相同）
	 * @param appType  		猎财OrT呗
	 * @param smsType  		业务模块
	 * @param userIds  		用户ID集合
	 * @param title	                        标题
	 * @param content       内容
	 * @param urlparam      推送透传参数
	 * @param isNeedAppMsg  是否同时推送站内信
	 * @return
	 */
	public CommonTCSResult BatchSinglePush(AppTypeEnum appType,
            SmsTypeEnum smsType,
            Collection<String> userIds,
            String title,
            String content,
            Map<String, Object> urlparam,
            boolean isNeedAppMsg,
            PersonalMsgTypeEnum personalMsgType) {
		
		CommonTCSResult retCode;
		try {
			
			if (appType == null) {
				return new CommonTCSResult(-1, "参数错误，appType不能为空");
			}
			retCode = new CommonTCSResult();
			
			long addStart = System.currentTimeMillis();			
			SysPushParameter contition = new SysPushParameter();
			contition.setAppType(appType.getKey());
			contition.setOsType(MobileOsTypeEnum.ANDROID.getKey());// 无论android IOS推送参数一致
																	
			SysPushParameter pushParam = sysPushParameterService.selectOne(contition);
			if (StringUtils.isNotBlank(EnumUtils.getMsgByKvmEnumKey(
					smsType.getKey(), SmsTypeEnum.values()))) {
				if (urlparam == null) {
					urlparam = Maps.newHashMap();
				}
				urlparam.put("customUrlKey", smsType.getMsg());
			}
			LOGGER.info("BatchSinglePush 输入参数：appType={},smsType={},title={},content={},urlparam={},userIds={}",
					            new Object[] {  appType,   smsType,   title,   content,   urlparam,   userIds });
			IIGtPush push = new IGtPush(pushParam.getHost(), pushParam.getAppKey(),	pushParam.getMasterSecret());
			IBatch batch = push.getBatch();

			

			//userIds 分批处理		
			List<List<String>> userIdss =  Lists.newArrayList();
			InterceptUtility.subsection(Lists.newArrayList(userIds) , userIdss,200);
			for(List<String> list : userIdss){
				
				Map<String, SmCustomerDevice> deviceTokeMap = smCustomerDeviceService.queryCustomerDevices(appType.getKey(), list);
				List<String> pushedUserIds = Lists.newArrayList();
				List<SysMsg> msgs = Lists.newArrayList();
				for(String item : list){
					
					//个人通知
					if(isNeedAppMsg){
						SysMsg msg = fillPersonalMsg(appType, item, content,personalMsgType);
						msg.setStartTime(new Date());
						msg.setCrtTime(new Date());
						msg.setModifyTime(new Date());
						msgs.add(msg);
					}
					
					SmCustomerDevice device = deviceTokeMap.get(item);
					if (device == null || (device != null && StringUtils.isBlank(device.getDeviceToken()))) {
						LOGGER.warn("userId:{},无设备信息", item);
						continue;
					}
					pushedUserIds.add(item);
					//message
					SingleMessage message = new SingleMessage();
					message.setOffline(true);
					// 离线有效时间，单位为毫秒，可选
					message.setOfflineExpireTime(24 * 3600 * 1000L);
					// 设置推送目标，填入appid和clientId
					Target target = new Target();
					target.setAppId(pushParam.getAppId());
					target.setClientId(device.getDeviceToken());

					if (ObjectUtils.equals(device.getDeviceType().toLowerCase(), "ios")) {// ios推送模板
																							
						TransmissionTemplate template = fillTransmissionTemplate(
								pushParam.getAppId(), pushParam.getAppKey(), title,
								content, urlparam);
						message.setData(template);
					} else {
						NotificationTemplate template = fillNotificationTemplate(
								pushParam.getAppId(), pushParam.getAppKey(), title,
								content, urlparam);
						message.setData(template);
					}
					batch.add(message, target);
					
				}
				if(isNeedAppMsg){
					sysMsgService.addMsgs(msgs);
				}
				// 提交到个推服务
				IPushResult ret = batch.submit();
				retCode = loggingPushRlt(retCode, ret);
				long addEnd = System.currentTimeMillis();
				
				LOGGER.info("BatchSinglePush 批量单推:sendTotal={}条消息共耗时：spendTime={} ms", new Object[]{pushedUserIds.size(),(addEnd - addStart)});
				LOGGER.info("BatchSinglePush 推送返回：code={},message={},pushedUserIds={}", new Object[]{retCode.getCode(),retCode.getMessage(),pushedUserIds});
				
			}
			
			return new CommonTCSResult(0, "批量单推成功");
			
		} catch (IOException e) {
			LOGGER.warn("BatchSinglePush exception!", e);
		} catch (Exception e) {
			LOGGER.warn("BatchSinglePush exception!", e);
		}

		return new CommonTCSResult(-1, "批量单推失败");
		
	}
	/**
	 * 批量单推 （不同内容推送）		减少与服务端的交付
	 * 使用场景：推送一批用户，并且每个用户推送的内容不统一（包括显示内容和透传的数据都相同）,前端推送的APP地址也由
	 * 每个对应的SysPushMessage 的getExtend1 JSon数据确定
	 * @param appType  		猎财OrT呗
	 * @param smsType  		业务模块
	 * @param userIds  		用户ID集合
	 * @param title	                        标题
	 * @param content       内容
	 * @param urlparam      推送透传参数
	 * @param isNeedAppMsg  是否同时推送站内信
	 * @return
	 */
	public CommonTCSResult BatchSinglePush(AppTypeEnum appType,
			SmsTypeEnum smsType,           
            Map<String, Object> urlparam,
            Collection<SysPushMessage> pushMsgs,Collection<SysMsg> msgs) {
		
		CommonTCSResult retCode;
		try {
			
			if (appType == null) {
				return new CommonTCSResult(-1, "参数错误，appType不能为空");
			}
			
			retCode = new CommonTCSResult();
			
			//站内消息
			if(msgs!=null && msgs.size()>0){
				List<List<SysMsg>> msgss =  Lists.newArrayList();
				InterceptUtility.subsection(Lists.newArrayList(msgs) , msgss,200);
				for(List<SysMsg> list : msgss){
				sysMsgService.addMsgs(list);
				}
			}
			
			long addStart = System.currentTimeMillis();			
			SysPushParameter contition = new SysPushParameter();
			contition.setAppType(appType.getKey());
			contition.setOsType(MobileOsTypeEnum.ANDROID.getKey());// 无论android IOS推送参数一致
																	
			SysPushParameter pushParam = sysPushParameterService.selectOne(contition);
			IIGtPush push = new IGtPush(pushParam.getHost(), pushParam.getAppKey(),	pushParam.getMasterSecret());
			IBatch batch = push.getBatch();

			

			//userIds 分批处理		
			List<List<SysPushMessage>> pushMsgss =  Lists.newArrayList();
			InterceptUtility.subsection(Lists.newArrayList(pushMsgs) , pushMsgss,200);
			for(List<SysPushMessage> list : pushMsgss){
				List<String> userIds = Lists.newArrayList();
				for(SysPushMessage item : list){
					userIds.add(item.getUserId());
				}
				Map<String, SmCustomerDevice> deviceTokeMap = smCustomerDeviceService.queryCustomerDevices(appType.getKey(), userIds);
				List<String> pushedUserIds = Lists.newArrayList();
				for(SysPushMessage item : list){
					
					SmCustomerDevice device = deviceTokeMap.get(item.getUserId());
					if (device == null || (device != null && StringUtils.isBlank(device.getDeviceToken()))) {
						LOGGER.warn("userId:{},无设备信息", item);
						continue;
					}
					pushedUserIds.add(item.getUserId());
					//message
					SingleMessage message = new SingleMessage();
					message.setOffline(true);
					// 离线有效时间，单位为毫秒，可选
					message.setOfflineExpireTime(24 * 3600 * 1000L);
					// 设置推送目标，填入appid和clientId
					Target target = new Target();
					target.setAppId(pushParam.getAppId());
					target.setClientId(device.getDeviceToken());
					/*if (StringUtils.isBlank(item.getExtend1())) {
						LOGGER.warn("urlParam:{},userId:{},推送透传数据错误",item.getExtend1(), item);
						continue;
					}*/
					if (StringUtils.isNotBlank(EnumUtils.getMsgByKvmEnumKey(
							smsType.getKey(), SmsTypeEnum.values()))) {
						if (urlparam == null) {
							urlparam = Maps.newHashMap();
						}
						urlparam.put("customUrlKey", smsType.getMsg());
					}
					if (ObjectUtils.equals(device.getDeviceType().toLowerCase(), "ios")) {// ios推送模板
																							
						TransmissionTemplate template = fillTransmissionTemplate(
								pushParam.getAppId(), pushParam.getAppKey(), item.getTitle(),
								item.getContent(), urlparam);
						message.setData(template);
					} else {
						NotificationTemplate template = fillNotificationTemplate(
								pushParam.getAppId(), pushParam.getAppKey(), item.getTitle(),
								item.getContent(), urlparam);
						message.setData(template);
					}
					batch.add(message, target);
					
				}
				
				// 提交到个推服务
				IPushResult ret = batch.submit();
				retCode = loggingPushRlt(retCode, ret);
				long addEnd = System.currentTimeMillis();
				
				LOGGER.info("BatchSinglePush 批量单推:sendTotal={}条消息共耗时：spendTime={} ms", new Object[]{pushedUserIds.size(),(addEnd - addStart)});
				LOGGER.info("BatchSinglePush 推送返回：code={},message={},pushedUserIds={}", new Object[]{retCode.getCode(),retCode.getMessage(),pushedUserIds});
				
			}
			
		return new CommonTCSResult(0, "批量单推成功");
		
			
		} catch (IOException e) {
			LOGGER.warn("BatchSinglePush exception!", e);
		} catch (Exception e) {
			LOGGER.warn("BatchSinglePush exception!", e);
		}

		return new CommonTCSResult(-1, "批量单推失败");
		
	}
	

	public static void main(String[] args) {
		Map<String, Object> urlParams = new HashMap<String, Object>();
		urlParams.put("customUrlKey", "cusDetail");
		urlParams.put("customerId", "36c00b082d514adaac42a5745d61399f");
		JSONObject jsonObject = new JSONObject(urlParams);
		System.out.println("==============:" + jsonObject.toJSONString());
		String jsonStr = new String(
				"{\"customerId\":\"36c00b082d514adaac42a5745d61399f\",\"customUrlKey\":\"cusDetail\"}");
		System.out.println(JsonUtils.fromJsonToObject(jsonStr, Map.class));

	}

}
