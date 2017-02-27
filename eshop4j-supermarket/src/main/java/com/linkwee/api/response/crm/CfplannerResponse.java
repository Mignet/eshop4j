package com.linkwee.api.response.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.web.enums.CfpLevelEnum;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.xoss.util.WebUtil;

/**
 * 
 * @描述：用户信息
 *
 * @author 何源
 * @时间 2015年10月29日下午3:26:10
 *
 */
public class CfplannerResponse extends BaseEntity {
	private static final long serialVersionUID = 3405963387464644389L;

	public CfplannerResponse() {

	}

	public CfplannerResponse(CrmCfplanner obj) {
		WebUtil.initObj(this,obj,DateUtils.FORMAT_LONG);
		this.setCfpLevelName(EnumUtils.getMsgByKvmEnumValue(this.getCfpLevel(),CfpLevelEnum.values()));
		this.setCfpLevel(WebUtil.getDefaultFormat(EnumUtils.getKeyByKvmEnumValue(this.getCfpLevel(),CfpLevelEnum.values())));
		if(obj != null) {
			this.setUserName(obj.getUserName());
			this.setUserId(obj.getUserId());
		}
	}

	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 姓名
	 */
	private String userName;

	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 理财师等级
	 */
	private String cfpLevel;
	/**
	 * 理财师等级名称
	 */
	private String cfpLevelName;
	/**
	 * 理财师注册时间
	 */
	private String cfpRegTime;
	/**
	 * 理财师转正时间
	 */
	private String cfpBenormalTime;

	/**
	 * 理财师更新时间
	 */
	private String cfpUpdateTime;

	/**
	 * 环信帐号
	 */
	private String easemobAcct;
	/**
	 * 环信密码
	 */
	private String easemobPassword;
	/**
	 * 图片
	 */
	private String headImage;
	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCfpLevel() {
		return cfpLevel;
	}

	public void setCfpLevel(String cfpLevel) {
		this.cfpLevel = cfpLevel;
	}

	public String getCfpLevelName() {
		return cfpLevelName;
	}

	public void setCfpLevelName(String cfpLevelName) {
		this.cfpLevelName = cfpLevelName;
	}

	public String getCfpRegTime() {
		return cfpRegTime;
	}

	public void setCfpRegTime(String cfpRegTime) {
		this.cfpRegTime = cfpRegTime;
	}

	public String getCfpBenormalTime() {
		return cfpBenormalTime;
	}

	public void setCfpBenormalTime(String cfpBenormalTime) {
		this.cfpBenormalTime = cfpBenormalTime;
	}

	public String getCfpUpdateTime() {
		return cfpUpdateTime;
	}

	public void setCfpUpdateTime(String cfpUpdateTime) {
		this.cfpUpdateTime = cfpUpdateTime;
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

	public String getEasemobPassword() {
		return easemobPassword;
	}

	public void setEasemobPassword(String easemobPassword) {
		this.easemobPassword = easemobPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

}
