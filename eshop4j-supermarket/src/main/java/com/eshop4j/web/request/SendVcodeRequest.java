package com.eshop4j.web.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


public class SendVcodeRequest{
	
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 发送类别(1、注册    2、重置登录密码   3、重置支付密码)
	 */
	private int type;
	
	/**
	 * 图片验证码
	 */
	private String vcode;
	
	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
