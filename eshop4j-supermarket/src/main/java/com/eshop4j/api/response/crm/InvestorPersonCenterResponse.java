package com.eshop4j.api.response.crm;

import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.model.crm.InvestorPersonCenterResp;
import com.eshop4j.xoss.util.WebUtil;

/**
 * 投资者端 个人中心 首页
 * 
 * @Date 2016年1月20日 下午5:36:22
 */
public class InvestorPersonCenterResponse extends BaseEntity {
	private static final long serialVersionUID = 7581132087714889997L;

	public InvestorPersonCenterResponse() {

	}

	public InvestorPersonCenterResponse(InvestorPersonCenterResp obj) {
		WebUtil.initObj(this, obj);
		if (StringUtils.isBlank(this.getUserName()) || StringUtils.isNumeric(this.getUserName())) {
			this.setUserName("未认证");
		}
		this.setIsBindBankCard(obj.getIsBindBankCard());
		if(obj.getMsgCount() >99) {
			this.setMsgCount(99 + "+");
		}
	}

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
	 * 息数
	 */
	private String msgCount;
	/**
	 * 总收益(元)
	 */
	private String totalProfit;
	/**
	 * 提现中金额
	 */
	private String withdrawAmount;
	/**
	 * 投资总额
	 */
	private String investAmount;
	/**
	 * 账户余额
	 */
	private String accountBalance;
	/**
	 * 可用红包数
	 */
	private String hongbaoCount;
	/**
	 * 头像图片
	 */
	private String headImage;
	/**
	 * 绑定机构账户数
	 */
	private String orgAccountCount;
	/**
	 * 未绑定绑定机构数
	 */
	private String unBindOrgAccountCount;

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


	public String getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(String msgCount) {
		this.msgCount = msgCount;
	}

	public String getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(String totalProfit) {
		this.totalProfit = totalProfit;
	}

	public String getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(String withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public String getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(String investAmount) {
		this.investAmount = investAmount;
	}

	public String getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getHongbaoCount() {
		return hongbaoCount;
	}

	public void setHongbaoCount(String hongbaoCount) {
		this.hongbaoCount = hongbaoCount;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getOrgAccountCount() {
		return orgAccountCount;
	}

	public void setOrgAccountCount(String orgAccountCount) {
		this.orgAccountCount = orgAccountCount;
	}

	public Boolean getIsBindBankCard() {
		return isBindBankCard;
	}

	public void setIsBindBankCard(Boolean isBindBankCard) {
		this.isBindBankCard = isBindBankCard;
	}

	public String getUnBindOrgAccountCount() {
		return unBindOrgAccountCount;
	}

	public void setUnBindOrgAccountCount(String unBindOrgAccountCount) {
		this.unBindOrgAccountCount = unBindOrgAccountCount;
	}

}
