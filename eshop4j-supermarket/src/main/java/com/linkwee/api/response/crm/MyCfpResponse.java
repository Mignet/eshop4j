package com.linkwee.api.response.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.web.enums.CfpLevelEnum;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.xoss.util.WebUtil;

/**
 * 我的理财师
 * @Date 2015年12月29日 下午5:43:49
 */
public class MyCfpResponse extends BaseEntity {
	private static final long serialVersionUID = 7927095839321282006L;
	public MyCfpResponse() {

	}

	public MyCfpResponse(CrmCfplanner obj) {
		WebUtil.initObj(this,obj);
		this.setCfpLevelName(EnumUtils.getMsgByKvmEnumValue(obj.getCfpLevel(),CfpLevelEnum.values()));
		this.setCfpLevel(WebUtil.getDefaultFormat(EnumUtils.getKeyByKvmEnumValue(obj.getCfpLevel(),CfpLevelEnum.values())));
	}
	/**
	 * 姓名
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
	 * 环信密码
	 */
	private String easemobPassword;
	/**
	 * 理财师等级
	 */
	private String cfpLevel;
	/**
	 * 理财师等级名称
	 */
	private String cfpLevelName;

	/**
	 * 头像图片
	 */
	private String headImage = "";
	
	/**
	 * 经验值
	 */
	private String levelExperience;
	
	/**
	 * 自己是否是理财师
	 */
	private String cfpIsSelf;
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}


	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getLevelExperience() {
		return levelExperience;
	}

	public void setLevelExperience(String levelExperience) {
		this.levelExperience = levelExperience;
	}

	public String getCfpIsSelf() {
		return cfpIsSelf;
	}

	public void setCfpIsSelf(String cfpIsSelf) {
		this.cfpIsSelf = cfpIsSelf;
	}



}
