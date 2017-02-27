package com.eshop4j.web.model.crm;

import com.eshop4j.core.base.BaseEntity;

/**
 * 投资者端 个人中心 首页
 * 
 * @Date 2016年1月20日 下午5:36:22
 */
public class InvestorPersonCenterResp extends BaseEntity {
	private static final long serialVersionUID = 7581132087714889997L;

	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 是否实名验证(true)
	 */
	private Boolean isBindBankCard;
	/**
	 * 消息息数
	 */
	private int msgCount;
	/**
	 * 总收益(元)
	 */
	private Double totalProfit;
	/**
	 * 提现中金额
	 */
	private Double withdrawAmount;
	/**
	 * 投资总额
	 */
	private Double investAmount;
	/**
	 * 账户余额
	 */
	private Double accountBalance;
	/**
	 * 可用红包数
	 */
	private int hongbaoCount;
	/**
	 * 头像图片
	 */
	private String headImage;
	
	/**
	 * 绑定机构账户数
	 */
	private int orgAccountCount;
	/**
	 * 未绑定绑定机构数
	 */
	private int unBindOrgAccountCount;

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


	public int getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(int msgCount) {
		this.msgCount = msgCount;
	}

	public Double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}

	public Double getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(Double withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public Double getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(Double investAmount) {
		this.investAmount = investAmount;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public int getHongbaoCount() {
		return hongbaoCount;
	}

	public void setHongbaoCount(int hongbaoCount) {
		this.hongbaoCount = hongbaoCount;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public int getOrgAccountCount() {
		return orgAccountCount;
	}

	public void setOrgAccountCount(int orgAccountCount) {
		this.orgAccountCount = orgAccountCount;
	}

	public Boolean getIsBindBankCard() {
		return isBindBankCard;
	}

	public void setIsBindBankCard(Boolean isBindBankCard) {
		this.isBindBankCard = isBindBankCard;
	}

	public int getUnBindOrgAccountCount() {
		return unBindOrgAccountCount;
	}

	public void setUnBindOrgAccountCount(int unBindOrgAccountCount) {
		this.unBindOrgAccountCount = unBindOrgAccountCount;
	}

}
