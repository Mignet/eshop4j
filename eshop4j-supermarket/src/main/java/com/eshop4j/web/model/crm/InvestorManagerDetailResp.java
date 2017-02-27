package com.eshop4j.web.model.crm;

import java.util.Date;
import java.util.List;

import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.web.model.CrmInvestorOperation;

public class InvestorManagerDetailResp extends BaseEntity {

	private static final long serialVersionUID = 1840107652864359699L;

	/**
	 * ID
	 */
	private String id;
	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 电话
	 */
	private String mobile;
	/**
	 * 创建时间
	 */

	private Date createTime;
	/**
	 * 理财师名称
	 */
	private String cfplannerName;
	/**
	 * 理财师电话
	 */
	private String cfplannerMobile;
	
	/**
	 * 邀请数量
	 */
	private String freindsCount;
	
	/**
	 * 头像
	 */
	private String headImage;
	/**
	 * 银行
	 */
	private String bankCard;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 身份证
	 */
	private String idCard;
	
	private int isfreeCustomer;
	
	private List<CrmInvestorOperation> changeLcsRecordList;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCfplannerName() {
		return cfplannerName;
	}

	public void setCfplannerName(String cfplannerName) {
		this.cfplannerName = cfplannerName;
	}

	public String getCfplannerMobile() {
		return cfplannerMobile;
	}

	public void setCfplannerMobile(String cfplannerMobile) {
		this.cfplannerMobile = cfplannerMobile;
	}

	public String getFreindsCount() {
		return freindsCount;
	}

	public void setFreindsCount(String freindsCount) {
		this.freindsCount = freindsCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public int getIsfreeCustomer() {
		return isfreeCustomer;
	}

	public void setIsfreeCustomer(int isfreeCustomer) {
		this.isfreeCustomer = isfreeCustomer;
	}

	public List<CrmInvestorOperation> getChangeLcsRecordList() {
		return changeLcsRecordList;
	}

	public void setChangeLcsRecordList(List<CrmInvestorOperation> changeLcsRecordList) {
		this.changeLcsRecordList = changeLcsRecordList;
	}

}
