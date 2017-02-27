package com.linkwee.api.response.crm;

import java.util.List;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.model.crm.CustomerDetailResp;
import com.linkwee.web.model.crm.OrgSimpleResp;
import com.linkwee.xoss.util.WebUtil;

/**
 * 
 * @描述：客户详情
 *
 * @author 何源
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class CustomerDetailResponse extends BaseEntity {

	private static final long serialVersionUID = 8459996570900278358L;

	public CustomerDetailResponse() {

	}

	public CustomerDetailResponse(CustomerDetailResp obj) {
		WebUtil.initObj(this, obj);
		this.setFirstRcpDate(DateUtils.format(obj.getFirstRcpDate(), DateUtils.FORMAT_SHORT));
		this.setRegisterDate(DateUtils.format(obj.getRegisterDate(), DateUtils.FORMAT_SHORT));
		this.setImportant(obj.getImportant() != null && obj.getImportant()==1);
		if(obj.getRegisteredOrgCount() > 0) {
			this.setRegisteredOrgCount(obj.getRegisteredOrgCount() + "个");
		} else {
			this.setRegisteredOrgCount("暂无");
		}
		this.setRegisteredOrgList(obj.getRegisteredOrgList());
	}

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
	private String registerDate;
	/**
	 * 首单时间
	 */
	private String firstRcpDate;
	/**
	 * 历史投资总额(单位元)
	 */
	private String totalInvestAmt;
	/**
	 * 当前在投(单位元)
	 */
	private String currInvestAmt;
	/**
	 * 获取佣金(单位元)
	 */
	private String feeAmt;

	/**
	 * 是否重要客户
	 */
	private boolean important;
	/**
	 * 头像
	 */
	private String headImage;
	
	/**
	 * 已注册平台数量
	 */
	private String registeredOrgCount;
	
	/**
	 * 已注册平台列表
	 */
	private List<OrgSimpleResp> registeredOrgList;

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

	public String getTotalInvestAmt() {
		return totalInvestAmt;
	}

	public void setTotalInvestAmt(String totalInvestAmt) {
		this.totalInvestAmt = totalInvestAmt;
	}

	public String getCurrInvestAmt() {
		return currInvestAmt;
	}

	public void setCurrInvestAmt(String currInvestAmt) {
		this.currInvestAmt = currInvestAmt;
	}

	public String getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(String feeAmt) {
		this.feeAmt = feeAmt;
	}

	public boolean isImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getRegisteredOrgCount() {
		return registeredOrgCount;
	}

	public void setRegisteredOrgCount(String registeredOrgCount) {
		this.registeredOrgCount = registeredOrgCount;
	}

	public List<OrgSimpleResp> getRegisteredOrgList() {
		return registeredOrgList;
	}

	public void setRegisteredOrgList(List<OrgSimpleResp> registeredOrgList) {
		this.registeredOrgList = registeredOrgList;
	}


}
