package com.eshop4j.api.response.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.web.model.CrmCfplanner;
import com.eshop4j.web.model.CrmInvestor;
import com.eshop4j.xoss.util.WebUtil;


public class UserInfoResponse extends BaseEntity {
	private static final long serialVersionUID = 3405963387464644389L;

	public UserInfoResponse() {

	}

	public UserInfoResponse(CrmInvestor obj) {
		WebUtil.initObj(this,obj);
	}
	
	public UserInfoResponse(CrmCfplanner obj) {
		WebUtil.initObj(this,obj);
	}
	
	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 环信帐号
	 */
	private String easemobAcct;
	/**
	 * 图片
	 */
	private String headImage;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getEasemobAcct() {
		return easemobAcct;
	}

	public void setEasemobAcct(String easemobAcct) {
		this.easemobAcct = easemobAcct;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	
}
