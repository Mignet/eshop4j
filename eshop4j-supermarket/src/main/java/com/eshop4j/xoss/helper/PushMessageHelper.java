package com.eshop4j.xoss.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
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
import com.gexin.rp.sdk.exceptions.PushSingleException;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.EnumUtils;
import com.eshop4j.core.util.JsonUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.web.enums.MobileOsTypeEnum;
import com.eshop4j.web.enums.PersonalMsgTypeEnum;
import com.eshop4j.web.enums.SmsTypeEnum;
import com.eshop4j.web.model.SmCustomerDevice;
import com.eshop4j.web.model.mc.SysMsg;
import com.eshop4j.web.model.mc.SysPushMessage;
import com.eshop4j.web.model.mc.SysPushParameter;
import com.eshop4j.web.response.CommonTCSResult;
import com.eshop4j.web.service.SmCustomerDeviceService;
import com.eshop4j.web.service.SysMsgService;
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
	public CommonTCSResult pushByAppId(AppTypeEnum appType,
			MobileOsTypeEnum osType, SmsTypeEnum smsType, String title,
			String content, Map<String, Object> urlparam) throws Exception {
		if (appType == null) {
			throw new Exception("参数错误，appType不能为空");
		}

		if (osType == null) {
			throw new Exception("参数错误，osType不能为空");
		}
		CommonTCSResult retCode = new CommonTCSResult();
		SysPushParameter contition = new SysPushParameter();
		contition.setAppType(appType.getKey());
		contition.setOsType(osType.getKey());
		SysPushParameter pushParam = sysPushParameterService
				.selectOne(contition);
		LOGGER.info(
				"pushByAppId 输入参数：appType={},osType={},title={},content={},urlparam={}",
				new Object[] { appType, osType, title, content, urlparam });
		IGtPush push = new IGtPush(pushParam.getHost(), pushParam.getAppKey(),
				pushParam.getMasterSecret());
		NotificationTemplate template = fillNotificationTemplate(
				pushParam.getAppId(), pushParam.getAppKey(), title, content,
				 urlparam);
		List<String> appIds = new ArrayList<String>();
		appIds.add(pushParam.getAppId());

		// 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
		AppMessage message = new AppMessage();
		message.setData(template);
		message.setAppIdList(appIds);
		message.setOffline(true);
		message.setOfflineExpireTime(1000L * 600);

		IPushResult ret = push.pushMessageToApp(message);
		if (ret != null) {
			if (ret.getResponse().containsKey("result")) {
				if (PushMessageUtil.pushSuccessResultCodeMap.containsKey(ret
						.getResponse().get("result"))) {
					retCode = new CommonTCSResult(0, "推送成功");
				} else {
					Object result = ret.getResponse().get("result");
					retCode = new CommonTCSResult(-1, result + "");
				}

			}
		} else {
			retCode = new CommonTCSResult(-1, "服务器响应异常");
		}
		LOGGER.info("pushByAppId 推送返回：code={},message={}", retCode.getCode(),
				retCode.getMessage());

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
	 * @return CommonTCSResult#code 0：成功；-1 失败
	 * @throws Exception
	 */
	public CommonTCSResult pushMessage(AppTypeEnum appType,
			SmsTypeEnum smsType, String userId, String title, String content,
			Map<String, Object> urlparam) throws Exception {
		if (appType == null) {
			throw new Exception("参数错误，appType不能为空");
		}

		if (StringUtils.isBlank(userId)) {
			throw new Exception("参数错误，userId不能为空");
		}
		if (smsType == null) {
			throw new Exception("参数错误，smsType不能为空");
		}
		CommonTCSResult retCode = new CommonTCSResult();

		SysPushParameter contition = new SysPushParameter();
		contition.setAppType(appType.getKey());
		SmCustomerDevice customerDevice = smCustomerDeviceService.queryCustomerDevice(appType.getKey(), userId);
		String osType = "";
		boolean isIos = false;
		if (customerDevice != null) {
			osType = customerDevice.getDeviceType().toLowerCase();
			contition.setOsType(osType);
			if ("ios".equals(customerDevice.getDeviceType().toLowerCase())) {
				isIos = true;
			}
		} else {
			throw new Exception("业务数据错误，用户无对应设备信息");
		}
		SysPushParameter pushParam = sysPushParameterService
				.selectOne(contition);
		if (pushParam == null) {
			throw new Exception("系统配置错误，未配置推送APP信息");
		}
		LOGGER.info(
				"pushMessage 输入参数：appId={},appkey={},AppSecret={},MasterSecret={}",
				new Object[] { pushParam.getAppId(), pushParam.getAppKey(),
						pushParam.getAppSecret(), pushParam.getMasterSecret() });
		
		IGtPush push = new IGtPush(pushParam.getHost(), pushParam.getAppKey(),
				pushParam.getMasterSecret());
		if (StringUtils.isNotBlank(EnumUtils.getMsgByKvmEnumKey(
				smsType.getKey(), SmsTypeEnum.values()))
				&& urlparam != null) {
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
			NotificationTemplate template = fillNotificationTemplate(
					pushParam.getAppId(), pushParam.getAppKey(), title, content,
					 urlparam);
			message.setData(template);
		}
		
		// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
		message.setPushNetWorkType(0);
		Target target = new Target();
		target.setAppId(pushParam.getAppId());

		if (customerDevice != null) {
			target.setClientId(customerDevice.getDeviceToken());
			//target.setClientId("9c6743b634c73611fd0e580dba25753f");
		}

		IPushResult ret = null;
		try {
				ret = push.pushMessageToSingle(message, target);
		} catch (RequestException e) {
			ret = push.pushMessageToSingle(message, target);

		}
		if (ret != null) {
			if (ret.getResponse().containsKey("result")) {
				if (PushMessageUtil.pushSuccessResultCodeMap.containsKey(ret
						.getResponse().get("result"))) {
					retCode = new CommonTCSResult(0, "推送成功");
				} else {
					Object result = ret.getResponse().get("result");
					retCode = new CommonTCSResult(-1, result + "");
				}

			}
		} else {
			retCode = new CommonTCSResult(-1, "服务器响应异常");
		}

		LOGGER.info("pushByAppId 推送返回：code={},message={}", retCode.getCode(),
				retCode.getMessage());
		// 推送记录
		SysPushMessage model = new SysPushMessage();
		model.setTitle(title);
		model.setContent(content);
		model.setUserId(userId);
		model.setDeviceToken(customerDevice == null ? "" : customerDevice
				.getDeviceToken());
		model.setOsType(osType);
		model.setModuleId(smsType.getValue());
		model.setHandle(1);// 已处理
		if (retCode.getCode() == 0) {
			model.setStatus(1);// 已发送
		} else {
			model.setStatus(0);// 已发送
		}

		model.setStatusDesc(retCode.getMessage());
		try {
			sysPushMessageService.insert(model);
		} catch (Exception e) {
			retCode = new CommonTCSResult(-1, e.getMessage());
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
				SysMsg msg = new SysMsg();
				msg.setContent(content);
				msg.setStatus(0);// 发布
				msg.setUserNumber(userId);
				msg.setReadStatus(0);// 未读
				msg.setAppType(appType.getKey());
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
				throw new Exception("业务数据错误，用户无对应设备信息");
			}
			SysPushParameter pushParam = sysPushParameterService
					.selectOne(contition);
			if (pushParam == null) {
				throw new Exception("系统配置错误，未配置推送APP信息");
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
			
			if (ret != null) {
				if (ret.getResponse().containsKey("result")) {
					if (PushMessageUtil.pushSuccessResultCodeMap.containsKey(ret
							.getResponse().get("result"))) {
						retCode = new CommonTCSResult(0, "推送成功");
					} else {
						Object result = ret.getResponse().get("result");
						retCode = new CommonTCSResult(-1, result + "");
					}

				}
			} else {
				retCode = new CommonTCSResult(-1, "服务器响应异常");
			}
			LOGGER.info("pushMessage 输入参数：appType={},userId={},deviceToken={},osType={},title={},content={},url={}",
					new Object[] { appType,userId,customerDevice.getDeviceToken(),osType, title, content, urlparam });
			LOGGER.info("pushByAppId 推送返回：code={},message={}", retCode.getCode(),retCode.getMessage());
			// 推送记录
			SysPushMessage model = new SysPushMessage();
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
			sysPushMessageService.insert(model);
			return new CommonTCSResult(0, "处理成功");
		} catch (PushSingleException e) {
			LOGGER.warn("pushMessage exception ", e.getMessage());
		} catch (Exception e) {
			LOGGER.warn("pushMessage exception ", e);
		}
		
		return new CommonTCSResult(-1, "处理失败");

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
			throw new Exception("参数错误，pushMessage不能为null");
		}
		if (pushMessage.getAppType() == null) {
			throw new Exception("参数错误，appType不能为空");
		}

		if (StringUtils.isBlank(pushMessage.getDeviceToken())) {
			throw new Exception("参数错误，deviceToken不能为空");
		}
		if (StringUtils.isBlank(pushMessage.getModuleId())) {
			throw new Exception("参数错误，ModuleId不能为空");
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

		SysPushParameter pushParam = sysPushParameterService
				.selectOne(contition);
		if (pushParam == null) {
			throw new Exception("系统配置错误，未配置推送APP信息");
		}
		LOGGER.info("pushMessage 输入参数：appType={},title={},content={},url={}",
				new Object[] { pushMessage.getAppType(),
						pushMessage.getTitle(), pushMessage.getContent(),
						pushMessage.getExtend1() });
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
		/*NotificationTemplate template = fillNotificationTemplate(
				pushParam.getAppId(), pushParam.getAppKey(),
				pushMessage.getTitle(), pushMessage.getContent(), urlparam);*/
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
		
		if (ret != null) {
			if (ret.getResponse().containsKey("result")) {
				if (PushMessageUtil.pushSuccessResultCodeMap.containsKey(ret.getResponse().get("result"))) {
					retCode = new CommonTCSResult(0, "推送成功");
				} else {
					Object result = ret.getResponse().get("result");
					retCode = new CommonTCSResult(-1, result + "");
				}

			}
		} else {
			retCode = new CommonTCSResult(-1, "服务器响应异常");
		}

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
				throw new Exception("系统配置错误，未配置推送APP信息");
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
			
			if (ret != null) {
				if (ret.getResponse().containsKey("result")) {
					if (PushMessageUtil.pushSuccessResultCodeMap.containsKey(ret.getResponse().get("result"))) {
						retCode = new CommonTCSResult(0, "推送成功");
					} else {
						Object result = ret.getResponse().get("result");
						retCode = new CommonTCSResult(-1, result + "");
					}

				}
			} else {
				retCode = new CommonTCSResult(-1, "服务器响应异常");
			}
			LOGGER.info("pushMessage 输入参数：appType={},userId={},deviceToken={},osType={},title={},content={},url={}",
					new Object[] { temp.getAppType(),userIdList,tokenList,temp.getAppType(),
					temp.getTitle(), temp.getContent(),
					temp.getExtend1() });
			LOGGER.info("pushMessageList 推送返回：code={},message={}", retCode.getCode(),retCode.getMessage());			
			
			return new CommonTCSResult(0, "处理成功");
		} catch (PushSingleException e) {
			LOGGER.warn("pushMessage exception ", e.getMessage());
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
		//template.setLogoUrl("http://image.tophlc.com/0ddbcff59bab92244a29fb2fe16aed55"); 使用默认图标
		// 设置通知是否响铃，震动，或者可清除  前端控制
		//template.setIsRing(true);
		//template.setIsVibrate(true);
		template.setIsClearable(true);
		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		template.setTransmissionType(2);
		JSONObject jsonObject = new JSONObject(urlparam);
		template.setTransmissionContent(jsonObject.toJSONString());
		/* APNPayload payload = new APNPayload();
		 payload.setAutoBadge("+1");
		 template.setAPNInfo(payload);*/
		/*if (isIos) {
			APNPayload payload = new APNPayload();
			payload.setContentAvailable(1);
			payload.setSound("default");
			payload.setCategory("$由客户端定义");
			// 简单模式APNPayload.SimpleMsg
			payload.setAlertMsg(new APNPayload.SimpleAlertMsg(content));
			payload.addCustomMsg("customUrlKey", "cusDetail");
			// 字典模式使用下者
			// payload.setAlertMsg(getDictionaryAlertMsg());
			template.setAPNInfo(payload);
		}*/
		// 设置定时展示时间
		// template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
		return template;
	}
	
	@SuppressWarnings("deprecation")
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
				SysMsg msg = new SysMsg();
				msg.setContent(content);
				msg.setStatus(0);// 发布
				msg.setUserNumber(userId);
				msg.setReadStatus(0);// 未读
				msg.setAppType(appType.getKey());
				sysMsgService.addMsg(msg);
			}
			CommonTCSResult retCode = new CommonTCSResult();
			if (appType == null) {
				throw new Exception("参数错误，appType不能为空");
			}
	
			if (StringUtils.isBlank(userId)) {
				throw new Exception("参数错误，userId不能为空");
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
			LOGGER.warn("pushMessageAsyn exception!", e.getMessage());
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
				throw new Exception("参数错误，appType不能为空");
			}

			if (userIds == null) {
				throw new Exception("参数错误，userId不能为空");
			}
			
			List<SysMsg> msgs = new ArrayList<SysMsg>();
			List<SysPushMessage> sysPushMessageList = new ArrayList<SysPushMessage>();
			Map<String,SmCustomerDevice> deviceTokeMap = smCustomerDeviceService.queryCustomerDevices(appType.getKey(), userIds);
			Iterator<String> pushMsgIt = userIds.iterator();
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
				
				//站内消息
				SysMsg msg = new SysMsg();
				msg.setContent(content);
				msg.setStatus(0);// 发布
				msg.setUserNumber(userId);
				msg.setReadStatus(0);// 未读
				msg.setAppType(appType.getKey());
				msg.setStartTime(new Date());
				msg.setCrtTime(new Date());
				msg.setModifyTime(new Date());
				msgs.add(msg);
			
			}
			
			if (isNeedAppMsg) {// 需要推送系统消息中心消息
				sysMsgService.addMsgs(msgs);
			}
			
			sysPushMessageService.saveBatch(sysPushMessageList);
			retCode = new CommonTCSResult(0, "处理成功");
			return retCode;
			
		 }catch(Exception e){
			LOGGER.warn("pushMessageAsyn exception!", e.getMessage());
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
			sysPushMessageService.renewBatch(msgList);
		}

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

	public static void main(String[] args) {
		/*Map<String, Object> urlParams = new HashMap<String, Object>();
		urlParams.put("customUrlKey", "cusDetail");
		urlParams.put("customerId", "36c00b082d514adaac42a5745d61399f");
		JSONObject jsonObject = new JSONObject(urlParams);
		System.out.println("==============:" + jsonObject.toJSONString());
		String jsonStr = new String(
				"{\"customerId\":\"36c00b082d514adaac42a5745d61399f\",\"customUrlKey\":\"cusDetail\"}");
		System.out.println(JsonUtils.fromJsonToObject(jsonStr, Map.class));*/
		
		System.out.println(StringUtils.getUUID());

	}

}
