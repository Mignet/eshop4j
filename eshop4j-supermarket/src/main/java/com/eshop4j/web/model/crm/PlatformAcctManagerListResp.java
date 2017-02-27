package com.eshop4j.web.model.crm;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;

/**
 * 平台帐号管理列表
 * 
 * @Author ZhongLing
 * @Date 2016年1月20日 下午4:20:57
 */
public class PlatformAcctManagerListResp extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 平台名称
	 */
	private String orgName;
	/**
	 * 平台帐号
	 */
	private String orgAccount;
	/**
	 * 是否新用户
	 */
	private int isNewUser;
	/**
	 * 是否投资
	 */
	private int isInvested;
	/**
	 * 绑定时间
	 */
	private Date bindDate;
	/**
	 * 平台编码
	 */
	private String orgNumber;
	
	/**
     *是否静态产品 (1：是 ,0：否)
     */
	private Integer orgIsstaticproduct;
	
	/**
	 * 图标
	 */
	private String platformListIco;
	
	/**
	 * 广告图
	 */
	private String orgAdvertisement;
	
	 /**
     *安全评级
     */
	private String grade;
	
   /**
     *最小年化收益
     */
	private String minProfit;
	/**
	 *最大年化收益
	 */
	private String maxProfit;
	
	/**
	 * 最小产品期限
	 */
	private String minDeadLine;
	
	/**
	 * 最大产品期限
	 */
	private String maxDeadLine;
	
	/**
	 * 平台下可投的产品数量
	 */
	private String usableProductNums;
	
	/**
     *产品最小期限天数 自定义显示
     */
	private String deadLineMinSelfDefined;
	
    /**
     *产品最大期限天数 自定义显示
     */
	private String deadLineMaxSelfDefined;
	
	/**
	 * 产品期限
	 */
	private String deadLineValueText;
	/**
	 * 投资金额
	 */
	private Double investAmount;
	/**
	 * 投资次数
	 */
	private int investCount;
	/**
	 * 累计收益
	 */
	private Double totalProfix;
	
	/**
	 * 机构合作结束跳转地址
	 */
	private String cooperationEndUrl; 
	
	/**
     * 合作状态.0-合作结束，1-合作中
     */
	private Integer status;
	
	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCooperationEndUrl() {
		return cooperationEndUrl;
	}

	public void setCooperationEndUrl(String cooperationEndUrl) {
		this.cooperationEndUrl = cooperationEndUrl;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public int getIsNewUser() {
		return isNewUser;
	}

	public void setIsNewUser(int isNewUser) {
		this.isNewUser = isNewUser;
	}

	public int getIsInvested() {
		return isInvested;
	}

	public void setIsInvested(int isInvested) {
		this.isInvested = isInvested;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgAccount() {
		return orgAccount;
	}

	public void setOrgAccount(String orgAccount) {
		this.orgAccount = orgAccount;
	}

	public Date getBindDate() {
		return bindDate;
	}

	public void setBindDate(Date bindDate) {
		this.bindDate = bindDate;
	}

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}

	public Integer getOrgIsstaticproduct() {
		return orgIsstaticproduct;
	}

	public void setOrgIsstaticproduct(Integer orgIsstaticproduct) {
		this.orgIsstaticproduct = orgIsstaticproduct;
	}

	public String getPlatformListIco() {
		return platformListIco;
	}

	public void setPlatformListIco(String platformListIco) {
		this.platformListIco = platformListIco;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getMinProfit() {
		return minProfit;
	}

	public void setMinProfit(String minProfit) {
		this.minProfit = minProfit;
	}

	public String getMaxProfit() {
		return maxProfit;
	}

	public void setMaxProfit(String maxProfit) {
		this.maxProfit = maxProfit;
	}

	public String getMinDeadLine() {
		return minDeadLine;
	}

	public void setMinDeadLine(String minDeadLine) {
		this.minDeadLine = minDeadLine;
	}

	public String getMaxDeadLine() {
		return maxDeadLine;
	}

	public void setMaxDeadLine(String maxDeadLine) {
		this.maxDeadLine = maxDeadLine;
	}

	public String getUsableProductNums() {
		return usableProductNums;
	}

	public void setUsableProductNums(String usableProductNums) {
		this.usableProductNums = usableProductNums;
	}

	public String getDeadLineMinSelfDefined() {
		return deadLineMinSelfDefined;
	}

	public void setDeadLineMinSelfDefined(String deadLineMinSelfDefined) {
		this.deadLineMinSelfDefined = deadLineMinSelfDefined;
	}

	public String getDeadLineMaxSelfDefined() {
		return deadLineMaxSelfDefined;
	}

	public void setDeadLineMaxSelfDefined(String deadLineMaxSelfDefined) {
		this.deadLineMaxSelfDefined = deadLineMaxSelfDefined;
	}

	public String getDeadLineValueText() {
		return deadLineValueText;
	}

	public void setDeadLineValueText(String deadLineValueText) {
		this.deadLineValueText = deadLineValueText;
	}

	public Double getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(Double investAmount) {
		this.investAmount = investAmount;
	}

	public int getInvestCount() {
		return investCount;
	}

	public void setInvestCount(int investCount) {
		this.investCount = investCount;
	}

	public Double getTotalProfix() {
		return totalProfix;
	}

	public void setTotalProfix(Double totalProfix) {
		this.totalProfix = totalProfix;
	}

	public String getOrgAdvertisement() {
		return orgAdvertisement;
	}

	public void setOrgAdvertisement(String orgAdvertisement) {
		this.orgAdvertisement = orgAdvertisement;
	}

	
	

}
