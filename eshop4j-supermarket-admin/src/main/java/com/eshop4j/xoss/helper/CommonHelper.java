package com.eshop4j.xoss.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.eshop4j.core.constant.SysConfigConstant;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.web.model.SysConfig;
import com.eshop4j.web.model.share.ShareContent;
import com.eshop4j.web.service.SysConfigService;

@Component
public class CommonHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonHelper.class);	
	
	@Resource
	private ConfigHelper configHelper;
	
	@Resource
	private SysConfigService sysConfigService;
	
	/**
	 * 获取微信配置
	 * @throws Exception
	 */
	public WeChatConfig getWeChatConfig(AppTypeEnum appEnum) throws Exception{
		WeChatConfig weChatConfig = new WeChatConfig();
		//Map<String,String> urlConfig = configHelper.getValuesByType(SysConfigConstant.TYPE_WECHAT_URL);
		/*List<SysConfig> sysList = sysConfigService.querySysConfigByName("获取微信");
    	Map<String,String> urlConfig = new HashMap<String,String>();
    	for(SysConfig item:sysList){
    		urlConfig.put(item.getConfigKey(),item.getConfigValue());  		  		
    	}  
		weChatConfig.setUrlAccessToken(urlConfig.get(SysConfigConstant.WECHAT_URL_ACCESS_TOKEN));
		weChatConfig.setUrlUserInfo(urlConfig.get(SysConfigConstant.WECHAT_URL_USER_INFO));*/
		weChatConfig.setUrlAccessToken(sysConfigService.querySysConfigByKey(SysConfigConstant.TYPE_WECHAT_URL, SysConfigConstant.WECHAT_URL_ACCESS_TOKEN, 0).getConfigValue());
		weChatConfig.setUrlUserInfo(sysConfigService.querySysConfigByKey(SysConfigConstant.TYPE_WECHAT_URL, SysConfigConstant.WECHAT_URL_USER_INFO, 0).getConfigValue());
		//Map<String,String> config = configHelper.getValuesByType(appEnum.getKey(),SysConfigConstant.TYPE_WECHAT);
		/*List<SysConfig> sysList2 = sysConfigService.querySysConfigByName("微信公众号");		
    	Map<String,String> config = new HashMap<String,String>();
    	for(SysConfig item:sysList2){
    		config.put(item.getConfigKey(),item.getConfigValue());  		  		
    	} 
		weChatConfig.setAppid(config.get(SysConfigConstant.WECHAT_APP_ID));
		weChatConfig.setAppSecret(config.get(SysConfigConstant.WECHAT_APP_SECRET));	*/
		weChatConfig.setAppid(sysConfigService.querySysConfigByKey(SysConfigConstant.TYPE_WECHAT, SysConfigConstant.WECHAT_APP_ID, appEnum.getKey()).getConfigValue());
		weChatConfig.setAppSecret(sysConfigService.querySysConfigByKey(SysConfigConstant.TYPE_WECHAT, SysConfigConstant.WECHAT_APP_SECRET, appEnum.getKey()).getConfigValue());
		return weChatConfig;
	}
	
	/**
	 * 获取微信分享配置
	 * @throws Exception
	 */
	public WechatShareConfig getWechatShareRclcs(String mobile) {
		WechatShareConfig wechatShareConfig = new WechatShareConfig();
		Map<String,String> config = configHelper.getValuesByType(SysConfigConstant.WECHAT_SHARE);
		wechatShareConfig.setDesc(config.get(SysConfigConstant.WECHAT_SHARE_DESC));
		wechatShareConfig.setImgUrl(config.get(SysConfigConstant.WECHAT_SHARE_IMGURL));
		String link = String.format(config.get(SysConfigConstant.WECHAT_SHARE_LINK),mobile);
		wechatShareConfig.setLink(link);
		wechatShareConfig.setTitle(config.get(SysConfigConstant.WECHAT_SHARE_TITLE));
		return wechatShareConfig;
	}
	
	/**
	 * 微信客户分享
	 * @param customerId 客户id
	 * @return
	 */
	public WechatShareConfig getWechatShareCustomer(String mobile) {
		WechatShareConfig wechatShareConfig = new WechatShareConfig();
		Map<String,String> config = configHelper.getValuesByType(SysConfigConstant.WECHAT_SHARE_CUSTOMER);
		wechatShareConfig.setDesc(config.get(SysConfigConstant.WECHAT_SHARE_CUSTOMER_DESC));
		wechatShareConfig.setImgUrl(config.get(SysConfigConstant.WECHAT_SHARE_CUSTOMER_IMGURL));
		String link = String.format(config.get(SysConfigConstant.WECHAT_SHARE_CUSTOMER_LINK), mobile);
		wechatShareConfig.setLink(link);
		wechatShareConfig.setTitle(config.get(SysConfigConstant.WECHAT_SHARE_CUSTOMER_TITLE));
		return wechatShareConfig;
	}
	
	public ShareContent getWechatShareCustomer(String mobile,String name) {
		ShareContent shareContent = new ShareContent();
		//Map<String,String> config = configHelper.getValuesByType(SysConfigConstant.WECHAT_SHARE_CUSTOMER);		
		List<SysConfig> sysList = sysConfigService.querySysConfigByName("微信分享-邀请客户");
    	Map<String,String> config = new HashMap<String,String>();
    	for(SysConfig item:sysList){
    		config.put(item.getConfigKey(),item.getConfigValue());  		  		
    	}      	
		shareContent.setShareDesc(config.get(SysConfigConstant.WECHAT_SHARE_CUSTOMER_DESC));
		shareContent.setShareImgurl(config.get(SysConfigConstant.WECHAT_SHARE_CUSTOMER_IMGURL));
		String link = String.format(config.get(SysConfigConstant.WECHAT_SHARE_CUSTOMER_LINK), mobile,name);
		shareContent.setShareLink(link);
		shareContent.setShareTitle(config.get(SysConfigConstant.WECHAT_SHARE_CUSTOMER_TITLE));
		return shareContent;
	}
	
	public ShareContent getWechatShareRclcs(String mobile,String name) {
		ShareContent shareContent = new ShareContent();
		//Map<String,String> config = configHelper.getValuesByType(SysConfigConstant.WECHAT_SHARE);		
		List<SysConfig> sysList = sysConfigService.querySysConfigByName("微信分享-邀请理财师");
    	Map<String,String> config = new HashMap<String,String>();
    	for(SysConfig item:sysList){
    		config.put(item.getConfigKey(),item.getConfigValue());  		  		
    	}   	
		shareContent.setShareDesc(config.get(SysConfigConstant.WECHAT_SHARE_DESC));
		shareContent.setShareImgurl(config.get(SysConfigConstant.WECHAT_SHARE_IMGURL));
		String link = String.format(config.get(SysConfigConstant.WECHAT_SHARE_LINK),mobile,name);
		shareContent.setShareLink(link);
		shareContent.setShareTitle(config.get(SysConfigConstant.WECHAT_SHARE_TITLE));
		return shareContent;
	}
	
	public ShareContent getWechatShareLcsRcCus(String mobile,String name) {
		ShareContent shareContent = new ShareContent();
		//Map<String,String> config = configHelper.getValuesByType(SysConfigConstant.WECHAT_SHARE_LCS_CUSTOMER);
		
		List<SysConfig> sysList = sysConfigService.querySysConfigByName("微信分享-理财师邀请客户");
    	Map<String,String> config = new HashMap<String,String>();
    	for(SysConfig item:sysList){
    		config.put(item.getConfigKey(),item.getConfigValue());  		  		
    	}
    	
		shareContent.setShareDesc(config.get(SysConfigConstant.WECHAT_SHARE_LCS_CUSTOMER_DESC));
		shareContent.setShareImgurl(config.get(SysConfigConstant.WECHAT_SHARE_LCS_CUSTOMER_IMGURL));
		String link = String.format(config.get(SysConfigConstant.WECHAT_SHARE_LCS_CUSTOMER_LINK),mobile,name);
		shareContent.setShareLink(link);
		shareContent.setShareTitle(config.get(SysConfigConstant.WECHAT_SHARE_LCS_CUSTOMER_TITLE));
		return shareContent;
	}
	
}
