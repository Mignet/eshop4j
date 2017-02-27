package com.eshop4j.xoss.helper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisCluster;

import com.alibaba.fastjson.JSON;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.web.enums.MsgModuleEnum;
import com.eshop4j.web.service.SmMessageQueueService;
import com.eshop4j.web.service.SysConfigService;
import com.eshop4j.xoss.constant.TimeSetConstants;
import com.eshop4j.xoss.util.AppResponseUtil;
import com.eshop4j.xoss.util.VerifyCodeUtils;
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
	 * 发送短信 返回boolean
	 * @param mobile	用户电话号码
	 * @param appType	APP类型
	 * @param messageModuleId	短信模板ID
	 */
	public boolean sendVerifyCode(String mobile,AppTypeEnum appType,MsgModuleEnum msgModuleEnum){
		BaseResponse baseResponse = sendVerifyCodeBaseResponse(mobile,appType,msgModuleEnum);
		if("0".equals(baseResponse.getCode())){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 发送短信  返回BaseResponse信息
	 * @param mobile	用户电话号码
	 * @param appType	APP类型
	 * @param messageModuleId	短信模板ID
	 */
	public BaseResponse sendVerifyCodeBaseResponse(String mobile,AppTypeEnum appType,MsgModuleEnum msgModuleEnum){
		/**
		 * 生成4位数字的验证码
		 */
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		/**
		 * 将电话号码，短信模板,验证码拼接成待存储的key
		 */
		String toSaveKey = mobile+"#"+msgModuleEnum.getValue()+"#"+verifyCode;
			
		/**
		 * T呗注册、邀请理财师、邀请投资人注册页面,修改登陆密码页面
		 * 同一IP或同一手机号在两个小时内可以正常获取5次短信验证码，每次间隔60秒。
		 * 如果2个小时内将5次机会用完，等2个小时即可再次获得5次机会。
		 * 在2个小时内第6次以后获取验证码时提示“您获取验证码过于频繁，请1小时40分钟后重试”（1小时40分钟=2小时-距离第3次获取验证码的时间）
		 */
		if(redisManager != null){//若redis存在
			if(MsgModuleEnum.REGISTER== msgModuleEnum ||MsgModuleEnum.UPDATELOGINPWD == msgModuleEnum){
				int saveKeyTimes = 1;
				String saveKeyDate = null;
				String toSaveKeyValueString = null;
				/**
				 * 将电话号码，短信模板拼接成待存储的key的次数
				 */
				String toSaveKeyTimes = mobile+"#"+msgModuleEnum.getValue();
				if(redisManager.exists(toSaveKeyTimes)){
					// 获取短信发送的次数和时间
					String[] toSaveKeyValueArray = redisManager.get(toSaveKeyTimes).split("#");
					saveKeyDate = toSaveKeyValueArray[0];
					saveKeyTimes = Integer.parseInt(toSaveKeyValueArray[1]);
					if(saveKeyTimes < 5){
						saveKeyTimes = saveKeyTimes + 1;
					} else {
						//判断时间是否超过2个小时
						long minuteGap= DateUtils.hasMinute(DateUtils.parse(saveKeyDate), new Date());
						if(minuteGap < TimeSetConstants.MSGGAIN_WAIT_TIME/(60*1000)){
							long minuteWait = TimeSetConstants.MSGGAIN_WAIT_TIME/(60*1000) - minuteGap;
							String messageRrror = null;
							int hour =  (int) (minuteWait/60);
							int minute = (int) (minuteWait%60);
							if(hour > 0 && minute > 0) {
								messageRrror = "您获取验证码过于频繁，请"+hour+"小时"+minute+"分钟后重试";
							}else if(hour > 0 && minute == 0){
								messageRrror = "您获取验证码过于频繁，请"+hour+"小时后重试";
							}else {
								messageRrror = "您获取验证码过于频繁，请"+minute+"分钟后重试";
							}
							return AppResponseUtil.getErrorBusi("sendError",messageRrror);	
						} else {
							saveKeyTimes = 1;
						}
					}
				}
				//设置短信验证码有效期
				redisManager.setex(toSaveKey,(int)TimeSetConstants.MSGVERIFYCODE_VALID_DATE/1000 ,toSaveKey);
				//储存该类型短信码的次数和第一次的时间
				toSaveKeyValueString = DateUtils.getNow() + "#" + String.valueOf(saveKeyTimes);
				redisManager.setex(toSaveKeyTimes, (int)TimeSetConstants.MSGGAIN_WAIT_TIME/1000, toSaveKeyValueString);
			} else {
				redisManager.setex(toSaveKey,(int)TimeSetConstants.MSGVERIFYCODE_VALID_DATE/1000 ,toSaveKey);
			}
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
		
		return AppResponseUtil.getSuccessResponse();//发送验证码成功
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
