package com.eshop4j.web.model.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;

/**
 * 
 * @描述：个人中心
 *
 * @author 何源
 * @时间 2015年10月20日下午6:56:46
 *
 */
public class PersonCenterResp extends BaseEntity {
	private static final long serialVersionUID = 7581132087714889997L;

	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 是否已绑定银行卡
	 */
	private Boolean isBindBankCard;

	/**
	 * 理财师等级
	 */
	private String cfgLevelName;

	/**
	 * 本月收益(元)
	 */
	private Double monthProfit = 0d;
	
	/**
	 * 今日收益(元)
	 */
	private Double todayProfit = 0d;
	
	/**
	 * 累计收益(元)
	 */
	private Double historyProfit = 0d;

	/**
	 * 本月佣金收益(元)
	 */
	private Double feeProfit = 0d;

	/**
	 * 本月推荐收益(元)
	 */
	private Double recommendProfit = 0d;

	/**
	 * 消息
	 */
	private Integer msgCount;
	
	/**
	 * 公告消息
	 */
	private Integer bulletinMsgCount;
	
	/**
	 * 个人消息
	 */
	private Integer personMsgCount;

	/**
	 * 账户余额
	 */
	private Double accountBalance;

	/**
	 * 提现中金额
	 */
	private Double withdrawAmount;
	/**
	 * 新增成员数量
	 */
	private Integer newPartnerCount;

	/**
	 * 职级经验
	 */
	private Integer levelExperience;

	/**
	 * 头像图片
	 */
	private String headImage;

	/**
	 * 可用红包数量
	 */
	private int hongbaoCount;
	
	/**
	 * 报单条数
	 */
	private int unRecordInvestCount;
	
	/**
	 * 未完成新手任务数量
	 */
	private int unFinishNewcomerTaskCount;

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

	public Double getMonthProfit() {
		return monthProfit;
	}

	public void setMonthProfit(Double monthProfit) {
		this.monthProfit = monthProfit;
	}

	public Double getFeeProfit() {
		return feeProfit;
	}

	public void setFeeProfit(Double feeProfit) {
		this.feeProfit = feeProfit;
	}

	public Double getRecommendProfit() {
		return recommendProfit;
	}

	public void setRecommendProfit(Double recommendProfit) {
		this.recommendProfit = recommendProfit;
	}

	public Integer getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(Integer msgCount) {
		this.msgCount = msgCount;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Double getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(Double withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public Integer getNewPartnerCount() {
		return newPartnerCount;
	}

	public void setNewPartnerCount(Integer newPartnerCount) {
		this.newPartnerCount = newPartnerCount;
	}

	public Integer getLevelExperience() {
		return levelExperience;
	}

	public void setLevelExperience(Integer levelExperience) {
		this.levelExperience = levelExperience;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public int getHongbaoCount() {
		return hongbaoCount;
	}

	public void setHongbaoCount(int hongbaoCount) {
		this.hongbaoCount = hongbaoCount;
	}

	public Boolean getIsBindBankCard() {
		return isBindBankCard;
	}

	public void setIsBindBankCard(Boolean isBindBankCard) {
		this.isBindBankCard = isBindBankCard;
	}

	public Double getTodayProfit() {
		return todayProfit;
	}

	public void setTodayProfit(Double todayProfit) {
		this.todayProfit = todayProfit;
	}

	public Double getHistoryProfit() {
		return historyProfit;
	}

	public void setHistoryProfit(Double historyProfit) {
		this.historyProfit = historyProfit;
	}

	public int getUnRecordInvestCount() {
		return unRecordInvestCount;
	}

	public void setUnRecordInvestCount(int unRecordInvestCount) {
		this.unRecordInvestCount = unRecordInvestCount;
	}

	public int getUnFinishNewcomerTaskCount() {
		return unFinishNewcomerTaskCount;
	}

	public void setUnFinishNewcomerTaskCount(int unFinishNewcomerTaskCount) {
		this.unFinishNewcomerTaskCount = unFinishNewcomerTaskCount;
	}

	public Integer getBulletinMsgCount() {
		return bulletinMsgCount;
	}

	public void setBulletinMsgCount(Integer bulletinMsgCount) {
		this.bulletinMsgCount = bulletinMsgCount;
	}

	public Integer getPersonMsgCount() {
		return personMsgCount;
	}

	public void setPersonMsgCount(Integer personMsgCount) {
		this.personMsgCount = personMsgCount;
	}


}
