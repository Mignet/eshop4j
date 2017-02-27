package com.eshop4j.xoss.util;

import java.security.MessageDigest;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.eshop4j.core.constant.TokenConstant;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.xoss.helper.CommonHelper;
import com.eshop4j.xoss.helper.WeChatConfig;

/**
 * 
 * @描述：微信接口工具类
 *
 * @author Bob
 * @时间  2015年8月20日下午2:25:02
 *
 */
public class WechatUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(WechatUtil.class);
	
	//private static String appid = "wx7193cd3aad46ab2c";
	//private static String appSecret = "355c8666b6fd043f110d3f9fa89c3c99";
	//public static final String url_access_token = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+appSecret+"&code={0}&grant_type=authorization_code";
	//public static final String url_user_info = "https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}&lang=zh_CN";
	//public static final String url_link = "https://open.weixin.qq.com/connect/qrconnect?appid="+appid+"&redirect_uri={0}&response_type=code&scope=snsapi_login&state={1}#wechat_redirect";
	
	public static final class WechatResp {
		public boolean success = true;
		public String msg;
		private Map<String, Object> data = new HashMap<String, Object>();
		
		public void putData(String key, Object val) {
			data.put(key, val);
		}
		
		public Object getObject(String key) {
			return data.get(key);
		}
		
		public String getString(String key) {
			Object obj = data.get(key);
			return obj == null ? null : obj.toString();
		}
		
		public boolean isSuccess() {
			return success;
		}
		
		public void setSuccess(boolean success) {
			this.success = success;
		}
		
		public String getMsg() {
			return msg;
		}
		
		public void setMsg(String msg) {
			this.msg = msg;
		}

		@Override
		public String toString() {
			return "WechatResp [success=" + success + ", msg=" + msg
					+ ", data=" + data + "]";
		}
		
	}
/*	
	public static final String getAccessLink(String backurl,String state){
		return MessageFormat.format(WechatUtil.url_access_token,backurl,state);
	}*/
	
	/**
	 * 使用微信code换取access_token
	 * @param code
	 * @return
	 */
	public static final WechatResp wechatAccessToken(WeChatConfig config,final String code) {
		WechatResp resp = new WechatResp();
		String url = MessageFormat.format(config.getUrlAccessToken(),config.getAppid(),config.getAppSecret(), code);
		logger.info("微信访问url:"+url);
		String accessTokenResp = HttpClientUtil.get(url);
		logger.info("微信获取access_token,code={},返回结果={}",code,accessTokenResp);
		JSONObject accessTokenRespJson = JSONObject.parseObject(accessTokenResp);
		if (accessTokenRespJson.get("errcode") == null) {
			resp.putData("access_token", accessTokenRespJson.get("access_token"));
			resp.putData("openid", accessTokenRespJson.get("openid"));
		} else {
			resp.setSuccess(false);
			resp.setMsg(accessTokenRespJson.get("errmsg") == null ? "无效的code" : accessTokenRespJson.get("errmsg").toString());
		}
		
		return resp;
	}
	
	/**
	 * 获取微信用户个人信息
	 * @param accessToken	调用凭证
	 * @param openid	用户标识,对当前开发者帐号唯一
	 * @return
	 */
	public static final WechatResp wechatUserinfo(WeChatConfig config,final String accessToken, final String openid) {
		WechatResp resp = new WechatResp();
		String url = MessageFormat.format(config.getUrlUserInfo(), accessToken, openid);
		logger.info("微信访问url:"+url);
		String userinfoResp = HttpClientUtil.httpsGet(url);
		logger.info("微信获取userinfo,access_token={},openid={},返回结果={}", new Object[] { accessToken, openid, userinfoResp });
		JSONObject userinfoRespJson = JSONObject.parseObject(userinfoResp);
		if (userinfoRespJson.get("errcode") == null) {
			resp.putData("access_token", userinfoRespJson.get("access_token"));
			resp.putData("openid", userinfoRespJson.get("openid"));
			resp.putData("nickname", userinfoRespJson.get("nickname"));
			resp.putData("unionid", userinfoRespJson.get("unionid"));
		} else {
			resp.setSuccess(false);
			resp.setMsg(userinfoRespJson.get("errmsg") == null ? "无效的access_token" : userinfoRespJson.get("errmsg").toString());
		}
		return resp;
	}
	
	

	/**
     * 获取access_token
     * @return
     */
	public static WechatResp getAccessToken(String appid,String appsecret){
    	WechatResp resp = new WechatResp();
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appsecret ;
        JSONObject json = JSONObject.parseObject(HttpClientUtil.get(url));
        logger.debug("微信获取token返回结果",json);
        if (json.get("errcode") == null) {
        	resp.putData("token", json.getString("access_token"));
        } else {
			logger.error("获取微信token失败:"+json.get("errmsg").toString());
			resp.setSuccess(false);
			resp.setMsg(json.get("errmsg") == null ? "获取微信token失败" : json.get("errmsg").toString());
		}
        return resp;
    }
    
    
    /**
     * 获得分享链接的签名。
     * @param ticket
     * @param nonceStr
     * @param timeStamp
     * @param url
     * @return
     * @throws Exception
     */
    private static String getSignature(String ticket, String nonceStr, String timeStamp, String url) throws Exception {
        String sKey = "jsapi_ticket=" + ticket+ "&noncestr=" + nonceStr + "&timestamp=" + timeStamp+ "&url=" + url;
        return getSignature(sKey);
    }
 
 /**
     * 验证签名。
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    private static String getSignature(String sKey) throws Exception {
        String ciphertext = null;
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(sKey.toString().getBytes());
        ciphertext = byteToStr(digest);
        return ciphertext.toLowerCase();
    }
 
 /** 
     * 将字节数组转换为十六进制字符串 
     *  
     * @param byteArray 
     * @return 
     */ 
    private static String byteToStr(byte[] byteArray) {  
        String strDigest = "";  
        for (int i = 0; i < byteArray.length; i++) {  
            strDigest += byteToHexStr(byteArray[i]);  
        }  
        return strDigest;  
    }  
  /** 
     * 将字节转换为十六进制字符串 
     *  
     * @param mByte 
     * @return 
     */ 
    private static String byteToHexStr(byte mByte) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);  
        return s;  
    }
    
    /**
     * 获取jsticket
     * @return
     * @throws Exception 
     */
    private static WechatResp getJsTicket(CommonHelper commonHelper,String token,AppTypeEnum appTypeEnum) throws Exception{
    	String key = TokenConstant.WECHAT_TICKET_KEY+"#"+appTypeEnum.getKey();
    	//String ticket = commonHelper.getCache(key);
    	String ticket = null;
    	WechatResp resp = new WechatResp();
    	if(ticket==null){
    		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + token + "&type=jsapi";
            JSONObject json = JSONObject.parseObject(HttpClientUtil.get(url));
            logger.debug("获取微信ticket返回结果",json);
            if (json.get("errcode")!= null&&"0".equals(json.get("errcode").toString())) {
            	ticket = json.getString("ticket");
            	resp.putData("ticket",ticket );
            	//commonHelper.saveCache(key,ticket,TokenConstant.WECHAT_TICKET_TIMEOUT);
            } else {
    			logger.error("获取微信ticket失败:"+json.get("errmsg").toString());
    			resp.setSuccess(false);
    			resp.setMsg(json.get("errmsg") == null ? "获取微信ticket失败" : json.get("errmsg").toString());
    		}
    	}else{
    		resp.putData("ticket",ticket );
    	}
        return resp;
    }
    
    /**
     *
     * @param url
     * @param appid
     * @param appsecret
     * @return
     * @throws Exception
     */
    public static WechatResp getWeChatShareResponse(CommonHelper commonHelper,String url,String appid,String appsecret,AppTypeEnum appTypeEnum) throws Exception {
        String timestamp = (System.currentTimeMillis() / 1000)+"";
        String nonceStr = UUID.randomUUID().toString();
        WechatResp tokenResp = getAccessToken(appid,appsecret);
        String signature = "";
        if(tokenResp.isSuccess()){
        	WechatResp ticketResp = getJsTicket(commonHelper,tokenResp.getString("token"),appTypeEnum);
        	 if(ticketResp.isSuccess()){
        		 signature = getSignature(ticketResp.getString("ticket"), nonceStr, timestamp,url);
        		 WechatResp resp = new WechatResp();
        		 resp.putData("appid", appid);
        		 resp.putData("nonceStr", nonceStr);
        		 resp.putData("signature", signature);
        		 resp.putData("timestamp", timestamp);
        		 return resp;
        	}else{
        		return ticketResp;
        	}
        }else{
        	return tokenResp;
        }
    }
    	
}
