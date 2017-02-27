package com.eshop4j.web.model.crm;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;

/**
 * 
 * @描述：我的团队-团队详情
 *
 * @author Bob
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class PartnerDetailResp extends BaseEntity {
	private static final long serialVersionUID = 9117301712001026112L;

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
	private Date registerDate;
	/**
	 * 首单时间
	 */
	private Date firstRcpDate;
	/**
	 * 下级人数
	 */
	private Integer childrenCount;
	/**
	 * 直接津贴
	 */
	private Double allowance;
	/**
	 * 间接津贴
	 */
	private Double childrenAllowance;

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

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getFirstRcpDate() {
		return firstRcpDate;
	}

	public void setFirstRcpDate(Date firstRcpDate) {
		this.firstRcpDate = firstRcpDate;
	}

	public Integer getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(Integer childrenCount) {
		this.childrenCount = childrenCount;
	}

	public Double getAllowance() {
		return allowance;
	}

	public void setAllowance(Double allowance) {
		this.allowance = allowance;
	}

	public Double getChildrenAllowance() {
		return childrenAllowance;
	}

	public void setChildrenAllowance(Double childrenAllowance) {
		this.childrenAllowance = childrenAllowance;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
