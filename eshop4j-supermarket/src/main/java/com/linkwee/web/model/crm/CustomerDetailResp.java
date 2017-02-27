package com.linkwee.web.model.crm;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：客户详情
 *
 * @author Bob
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class CustomerDetailResp extends BaseEntity {
	private static final long serialVersionUID = -226463495944568640L;

	/**
	 * 客户名称
	 */
	private String userName;
	/**
	 * 客户手机号码
	 */
	private String mobile;
	/**
	 * 注册时间
	 */
	private Date registerDate;
	/**
	 * 首单时间
	 */
	private Date firstRcpDate;
	/**
	 * 历史投资总额(单位元)
	 */
	private Double totalInvestAmt;
	/**
	 * 当前在投(单位元)
	 */
	private Double currInvestAmt;
	/**
	 * 获取佣金(单位元)
	 */
	private Double feeAmt;

	/**
	 * 是否重要客户
	 */
	private Integer important;
	/**
	 * 头像
	 */
	private String headImage;
	
	/**
	 * 已注册平台数量
	 */
	private int registeredOrgCount;
	
	/**
	 * 已注册平台列表
	 */
	private List<OrgSimpleResp> registeredOrgList;

	public Integer getImportant() {
		return important;
	}

	public void setImportant(Integer important) {
		this.important = important;
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

	public Double getTotalInvestAmt() {
		return totalInvestAmt;
	}

	public void setTotalInvestAmt(Double totalInvestAmt) {
		this.totalInvestAmt = totalInvestAmt;
	}

	public Double getCurrInvestAmt() {
		return currInvestAmt;
	}

	public void setCurrInvestAmt(Double currInvestAmt) {
		this.currInvestAmt = currInvestAmt;
	}

	public Double getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(Double feeAmt) {
		this.feeAmt = feeAmt;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
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

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public int getRegisteredOrgCount() {
		return registeredOrgCount;
	}

	public void setRegisteredOrgCount(int registeredOrgCount) {
		this.registeredOrgCount = registeredOrgCount;
	}

	public List<OrgSimpleResp> getRegisteredOrgList() {
		return registeredOrgList;
	}

	public void setRegisteredOrgList(List<OrgSimpleResp> registeredOrgList) {
		this.registeredOrgList = registeredOrgList;
	}

}
