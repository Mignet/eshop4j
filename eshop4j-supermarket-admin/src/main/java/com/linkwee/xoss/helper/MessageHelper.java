package com.linkwee.xoss.helper;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisCluster;

import com.alibaba.fastjson.JSON;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.constant.TimeSetConstants;
import com.linkwee.xoss.util.VerifyCodeUtils;
/**
 * 消息中心<br>
 * 自动注入redisManager
 */
@Component
public class MessageHelper {
	
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private SmMessageQueueService smMessageTemplateService;
	@Resource
	private SmMessageQueueService messageQueueService;
	
	@Resource
	private JedisCluster redisManager;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageHelper.class);
	
	private static Map<String, Long> verifyCodeMap = new HashMap<String, Long>();
	
	
	/**
	 * 发送短信
	 * @param userMobile	用户电话号码
	 * @param messageModuleId	短信模板ID
	 */
	public boolean sendVerifyCode(String mobile,AppTypeEnum appType,MsgModuleEnum msgModuleEnum){
		//生成4位数字的验证码
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		//将电话号码，短信模板,验证码拼接成待存储的key
		String toSaveKey = mobile+"#"+msgModuleEnum.getValue()+"#"+verifyCode;
		
		if(redisManager != null){//若redis存在
			redisManager.setex(toSaveKey,(int)TimeSetConstants.MSGVERIFYCODE_VALID_DATE/1000 ,toSaveKey);
		} else {
			if(verifyCodeMap.size() >= 10000){
				//将过期的验证码清除
				for(Map.Entry<String, Long> entry: verifyCodeMap.entrySet()){
					if(System.currentTimeMillis() > entry.getValue()){
						verifyCodeMap.remove(entry.getKey());
					}
				}
			}
			verifyCodeMap.put(toSaveKey, System.currentTimeMillis()+TimeSetConstants.MSGVERIFYCODE_VALID_DATE); //有效期
		}
		
		//发送短信
		try {
			messageQueueService.sendSingleMessage(mobile, appType, msgModuleEnum,verifyCode);
		} catch (Exception e) {
			LOGGER.warn("发送短信异常,mobile={},appType={},msgModuleEnum={},exception={}",new Object[]{mobile,JSON.toJSONString(appType),JSON.toJSONString(msgModuleEnum),e});
		}

		LOGGER.info(">>>>>>>>>>>>>>>>>");
		LOGGER.info(">>>>>>>>>>>>>>>>> 发送短信验证码 : phone = {} | code = {}" ,mobile, verifyCode);
		LOGGER.info(">>>>>>>>>>>>>>>>>");
		
		return true;//发送验证码成功

	}
	
	/**
	 * 校验验证码是否正确
	 * @param verifyCode	验证码
	 * @return
	 */
	public boolean checkVerifyCode(String userMobile,MsgModuleEnum msgModuleEnum, String verifyCode){
		String toSaveKey = userMobile+"#"+msgModuleEnum.getValue()+"#"+verifyCode;
		if(redisManager != null){//若redis存在
			return redisManager.exists(toSaveKey);
		} else {
			if(verifyCodeMap.containsKey(toSaveKey) && verifyCodeMap.get(toSaveKey) >= System.currentTimeMillis()){
				return true;
			} else {
				return false;
			}
		}
	}
	
	
	

	
	
}
