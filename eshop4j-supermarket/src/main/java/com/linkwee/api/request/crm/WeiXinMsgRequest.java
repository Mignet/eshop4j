package com.linkwee.api.request.crm;

import javax.validation.constraints.NotNull;

import com.linkwee.core.base.BaseEntity;

public class WeiXinMsgRequest extends BaseEntity{
	
	private static final long serialVersionUID = 6672500236184266901L;

	/**
	 * 用户微信code
	 */
	@NotNull(message="用户微信code")
	private String 	code;
	
	/**
	 *  是否推送注册信息（默认0不推送，1为推送）
	 */
	@NotNull(message="是否推送注册信息isPush")
	private String isPush;
	
	
	/**
	 * 用户类型 1-理财师、2-投资者
	 * */
	private Integer useType;
	
	/**
	 * 用户ID
	 * */
	private String useId;
	
	/**
	 * 模板key
	 * */
	private String temkey;
	
	//投资成功通知
	/**
	 * 项目名称
	 * */
	private String productName;
	
	/**
	 * 预期年化收益率
	 * */
	private String returnRate;
	
	/**
	 * 项目期限
	 * */
	private String term;
	
	/**
	 * 投资金额
	 * */
	private String amount;
	
	//推荐成功通知
	/**
	 * 推荐人
	 * */
	private String recommendPerson;

	/**
	 * 被推荐人
	 * */
	private String recommendedPerson;
	
	//邀请注册成功
	/**
	 * 推荐人用户名
	 * */
	private String recommendUserName;
	
	/**
	 * 推荐人手机号码
	 * */
	private String recommendMobile;
	
	/**
	 * 注册理财师手机号码
	 * */
	private String cfpMobile;
	
	/**
	 * 注册时间
	 * */
	private String registerTime;
	
	//回款提醒(产品剩余3天到期回款) or 回款提醒
	/**
	 * 回款编号
	 * */
	private String paymentNumber;
	
	/**
	 * 回款日期
	 * */
	private String paymentDate;
	
	/**
	 * 客户
	 * */
	private String  customer;
	
	//提现到账提醒 
	/**
	 * 提现金额
	 * */
	private String  withdrawAmount;
	/**
	 * 提现账户
	 * */
	private String  account;
	/**
	 * 提现时间
	 * */
	private String  withdrawTime;
	/**
	 * 到账时间
	 * */
	private String  noticeTime;

	//提现申请通知
	/**
	 * 昵称
	 * */
	private String  nickName;
	
	/**
	 * 提现方式
	 * */
	private String  withdrawType;
	
	//绑定成功通知
	/**
	 * 绑定姓名
	 * */
	private String  bindName;
	
	/**
	 * 绑定账户
	 * */
	private String  bindAccount;
	
	/**
	 * 绑定时间
	 * */
	private String  bindTime;
	
	//解绑通知(解绑理财师)
	/**
	 * 用户名
	 * */
	private String userName;
	
	/**
	 * 用户类型
	 * */
	private String userType;
	
	/**
	 * 解绑时间
	 * */
	private String releaseTime;
	
	//第三方账户开通成功通知
	/**
	 * 平台名称
	 * */
	private String platformName;
	/**
	 * 开通时间
	 * */
	private String openTime;
	
	//到账提醒
	/**
	 * 到账详情
	 * */
	private String arrivalDetail;
	/**
	 * 到账时间
	 * */
	private String arrivalTime;
	/**
	 * 到账金额
	 * */
	private String arrivalAmount;
	
	//活动完成通知
	/**
	 * 活动名称
	 * */
	private String activityName;
	/**
	 * 奖励金额
	 * */
	private String activityAmount;
	//等级变更通知
	/**
	 * 变更类型
	 * */
	private String changeType;
	//审核未通过提醒
	/**
	 * 未通过原因
	 * */
	private String reason;
	
	/**
	 * 审核时间
	 * */
	private String auditTime;
	//投资成功-理财师推荐奖励
	/**
	 * 佣金
	 * */
	private String fee;
	
	
	
	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getCfpMobile() {
		return cfpMobile;
	}

	public void setCfpMobile(String cfpMobile) {
		this.cfpMobile = cfpMobile;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityAmount() {
		return activityAmount;
	}

	public void setActivityAmount(String activityAmount) {
		this.activityAmount = activityAmount;
	}

	public String getArrivalDetail() {
		return arrivalDetail;
	}

	public void setArrivalDetail(String arrivalDetail) {
		this.arrivalDetail = arrivalDetail;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getArrivalAmount() {
		return arrivalAmount;
	}

	public void setArrivalAmount(String arrivalAmount) {
		this.arrivalAmount = arrivalAmount;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getBindName() {
		return bindName;
	}

	public void setBindName(String bindName) {
		this.bindName = bindName;
	}

	public String getBindAccount() {
		return bindAccount;
	}

	public void setBindAccount(String bindAccount) {
		this.bindAccount = bindAccount;
	}

	public String getBindTime() {
		return bindTime;
	}

	public void setBindTime(String bindTime) {
		this.bindTime = bindTime;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getWithdrawType() {
		return withdrawType;
	}

	public void setWithdrawType(String withdrawType) {
		this.withdrawType = withdrawType;
	}

	public String getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(String withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getWithdrawTime() {
		return withdrawTime;
	}

	public void setWithdrawTime(String withdrawTime) {
		this.withdrawTime = withdrawTime;
	}

	public String getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(String noticeTime) {
		this.noticeTime = noticeTime;
	}

	public String getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getRecommendUserName() {
		return recommendUserName;
	}

	public void setRecommendUserName(String recommendUserName) {
		this.recommendUserName = recommendUserName;
	}

	public String getRecommendMobile() {
		return recommendMobile;
	}

	public void setRecommendMobile(String recommendMobile) {
		this.recommendMobile = recommendMobile;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIsPush() {
		return isPush;
	}

	public void setIsPush(String isPush) {
		this.isPush = isPush;
	}

	public Integer getUseType() {
		return useType;
	}

	public void setUseType(Integer useType) {
		this.useType = useType;
	}

	public String getUseId() {
		return useId;
	}

	public void setUseId(String useId) {
		this.useId = useId;
	}

	public String getTemkey() {
		return temkey;
	}

	public void setTemkey(String temkey) {
		this.temkey = temkey;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getReturnRate() {
		return returnRate;
	}

	public void setReturnRate(String returnRate) {
		this.returnRate = returnRate;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRecommendPerson() {
		return recommendPerson;
	}

	public void setRecommendPerson(String recommendPerson) {
		this.recommendPerson = recommendPerson;
	}

	public String getRecommendedPerson() {
		return recommendedPerson;
	}

	public void setRecommendedPerson(String recommendedPerson) {
		this.recommendedPerson = recommendedPerson;
	}



}
