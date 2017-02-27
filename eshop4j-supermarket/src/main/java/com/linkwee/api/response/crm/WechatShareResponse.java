package com.linkwee.api.response.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：微信分享接口返回
 *
 * @author 何源
 * @时间  2015年8月21日下午4:57:50
 *
 */
public class WechatShareResponse extends BaseEntity{

	private static final long serialVersionUID = 7319654653527179077L;

	/**
	 * 微信标识
	 */
	private String appid;
	
	/**
	 * 时间戳
	 */
	private String timestamp;
	
	/**
	 * 
	 */
	private String nonceStr;
	/**
	 * 签名
	 */
	private String signature;
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
	
}
