package com.eshop4j.api.response.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.model.crm.PartnerDetailResp;
import com.eshop4j.xoss.util.WebUtil;

/**
 * 
 * @描述：我的团队-团队详情
 *
 * @author Bob
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class PartnerDetailResponse extends BaseEntity {
	private static final long serialVersionUID = -5418637411733755341L;

	public PartnerDetailResponse() {

	}

	public PartnerDetailResponse(PartnerDetailResp obj) {
		WebUtil.initObj(this, obj);
		if (StringUtils.isBlank(this.getUserName()) || isNumeric(this.getUserName())) {
			this.setUserName("未认证");
		}
		this.setRegisterDate(DateUtils.format(obj.getRegisterDate(), DateUtils.FORMAT_SHORT));
		this.setFirstRcpDate(DateUtils.format(obj.getFirstRcpDate(), DateUtils.FORMAT_SHORT));
	}

	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 理财师等级名称
	 */
	private String cfgLevelName;
	/**
	 * 注册时间
	 */
	private String registerDate;
	/**
	 * 首单时间
	 */
	private String firstRcpDate;
	/**
	 * 下级人数
	 */
	private String childrenCount;

	/**
	 * 直接津贴
	 */
	private String allowance;
	/**
	 * 间接津贴
	 */
	private String childrenAllowance;
	
	/**
	 * 头像
	 */
	private String headImage;

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

	public String getCfgLevelName() {
		return cfgLevelName;
	}

	public void setCfgLevelName(String cfgLevelName) {
		this.cfgLevelName = cfgLevelName;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getFirstRcpDate() {
		return firstRcpDate;
	}

	public void setFirstRcpDate(String firstRcpDate) {
		this.firstRcpDate = firstRcpDate;
	}

	public String getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(String childrenCount) {
		this.childrenCount = childrenCount;
	}

	public String getAllowance() {
		return allowance;
	}

	public void setAllowance(String allowance) {
		this.allowance = allowance;
	}

	public String getChildrenAllowance() {
		return childrenAllowance;
	}

	public void setChildrenAllowance(String childrenAllowance) {
		this.childrenAllowance = childrenAllowance;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	private boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
}
