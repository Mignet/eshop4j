package com.eshop4j.api.response.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.model.crm.PersonCenterResp;
import com.eshop4j.xoss.util.WebUtil;

/**
 * 
 * @描述：个人中心首页返回
 *
 * @author 何源
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class PersonCenterResponse extends BaseEntity {

	private static final long serialVersionUID = 8459996570900278358L;

	public PersonCenterResponse() {

	}

	public PersonCenterResponse(PersonCenterResp obj) {
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
	 * 用户名
	 */
	private String userName;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 是否绑银行卡
	 */
	private boolean isBindBankCard;

	/**
	 * 理财师等级
	 */
	private String cfgLevelName;
	/**
	 * 本月收益(元)
	 */
	private String monthProfit;
	
	/**
	 * 今日收益(元)
	 */
	private String todayProfit;
	
	/**
	 * 累计收益(元)
	 */
	private String historyProfit;

	/**
	 * 本月佣金收益(元)
	 */
	private String feeProfit;
	/**
	 * 本月推荐收益(元)
	 */
	private String recommendProfit;

	/**
	 * 消息
	 */
	private String msgCount;
	/**
	 * 公告消息
	 */
	private String bulletinMsgCount;
	
	/**
	 * 个人消息
	 */
	private String personMsgCount;

	/**
	 * 提现中金额
	 */
	private String withdrawAmount;

	/**
	 * 账户余额
	 */
	private String accountBalance;
	/**
	 * 新增成员数量
	 */
	private String newPartnerCount;
	/**
	 * 职级经验
	 */
	private String levelExperience;
	/**
	 * 头像图片
	 */
	private String headImage;

	/**
	 * 可用红包数量
	 */
	private String hongbaoCount;
	

	/**
	 * 报单条数
	 */
	private String unRecordInvestCount;
	
	/**
	 * 未完成新手任务数量
	 */
	private String unFinishNewcomerTaskCount;

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

	public String getMonthProfit() {
		return monthProfit;
	}

	public void setMonthProfit(String monthProfit) {
		this.monthProfit = monthProfit;
	}

	public String getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(String msgCount) {
		this.msgCount = msgCount;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getFeeProfit() {
		return feeProfit;
	}

	public void setFeeProfit(String feeProfit) {
		this.feeProfit = feeProfit;
	}

	public String getRecommendProfit() {
		return recommendProfit;
	}

	public void setRecommendProfit(String recommendProfit) {
		this.recommendProfit = recommendProfit;
	}

	public String getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(String withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public String getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getNewPartnerCount() {
		return newPartnerCount;
	}

	public void setNewPartnerCount(String newPartnerCount) {
		this.newPartnerCount = newPartnerCount;
	}

	public String getLevelExperience() {
		return levelExperience;
	}

	public void setLevelExperience(String levelExperience) {
		this.levelExperience = levelExperience;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getHongbaoCount() {
		return hongbaoCount;
	}

	public void setHongbaoCount(String hongbaoCount) {
		this.hongbaoCount = hongbaoCount;
	}

	public Boolean getIsBindBankCard() {
		return isBindBankCard;
	}

	public void setIsBindBankCard(Boolean isBindBankCard) {
		this.isBindBankCard = isBindBankCard;
	}

	public String getTodayProfit() {
		return todayProfit;
	}

	public void setTodayProfit(String todayProfit) {
		this.todayProfit = todayProfit;
	}

	public String getHistoryProfit() {
		return historyProfit;
	}

	public void setHistoryProfit(String historyProfit) {
		this.historyProfit = historyProfit;
	}

	public String getUnRecordInvestCount() {
		return unRecordInvestCount;
	}

	public void setUnRecordInvestCount(String unRecordInvestCount) {
		this.unRecordInvestCount = unRecordInvestCount;
	}

	public void setBindBankCard(boolean isBindBankCard) {
		this.isBindBankCard = isBindBankCard;
	}

	public String getUnFinishNewcomerTaskCount() {
		return unFinishNewcomerTaskCount;
	}

	public void setUnFinishNewcomerTaskCount(String unFinishNewcomerTaskCount) {
		this.unFinishNewcomerTaskCount = unFinishNewcomerTaskCount;
	}

	public String getBulletinMsgCount() {
		return bulletinMsgCount;
	}

	public void setBulletinMsgCount(String bulletinMsgCount) {
		this.bulletinMsgCount = bulletinMsgCount;
	}

	public String getPersonMsgCount() {
		return personMsgCount;
	}

	public void setPersonMsgCount(String personMsgCount) {
		this.personMsgCount = personMsgCount;
	}



}
