package com.eshop4j.api.response.cim;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.model.crm.PlatformAcctManagerListResp;
import com.eshop4j.xoss.util.WebUtil;

/**
 * 平台帐号管理列表
 * 
 * @Author ZhongLing
 * @Date 2016年1月20日 下午4:20:57
 */
public class PlatformAcctManagerListResponse extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public PlatformAcctManagerListResponse() {
	}

	public PlatformAcctManagerListResponse(PlatformAcctManagerListResp obj) {
		WebUtil.initObj(this, obj);
		this.setBindDate(DateUtils.format(obj.getBindDate(), DateUtils.FORMAT_SHORT));
		this.setOrgUserProperties("新用户");
		this.setOrgAccount(obj.getOrgAccount());
		if(obj.getBindDate() != null && !"".equals(obj.getBindDate())) {
			this.setIsBind("1");
		} else {
			this.setIsBind("0");
		}
	}

	/**
	 * 平台名称
	 */
	private String orgName;
	/**
	 * 平台帐号
	 */
	private String orgAccount;
	/**
	 * 是否投资
	 */
	private String isInvested;
	/**
	 * 绑定时间
	 */
	private String bindDate;

	/**
	 * 平台用户属性
	 */
	private String orgUserProperties;
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
	private String investAmount;
	/**
	 * 投资次数
	 */
	private String investCount;
	/**
	 * 累计收益
	 */
	private String totalProfix;
	
	/**
	 * 机构合作结束跳转地址
	 */
	private String cooperationEndUrl; 
	
	/**
     * 合作状态.0-合作结束，1-合作中
     */
	private Integer status;
	
	/**
	 * 是否已绑定 0否，1是
	 */
	private String isBind;
	

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

	public String getIsInvested() {
		return isInvested;
	}

	public void setIsInvested(String isInvested) {
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

	public String getOrgUserProperties() {
		return orgUserProperties;
	}

	public void setOrgUserProperties(String orgUserProperties) {
		this.orgUserProperties = orgUserProperties;
	}

	public String getBindDate() {
		return bindDate;
	}

	public void setBindDate(String bindDate) {
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
		if (minDeadLine != null  && maxDeadLine != null && minDeadLine.equals(maxDeadLine)){
			if(StringUtils.isNotBlank(deadLineMinSelfDefined)){
				deadLineValueText = deadLineMinSelfDefined;
			} else {
				deadLineValueText = minDeadLine+"天";
			}
		} else {
			if(StringUtils.isNotBlank(deadLineMinSelfDefined) && StringUtils.isNotBlank(deadLineMaxSelfDefined)){
				deadLineValueText = deadLineMinSelfDefined+"~"+deadLineMaxSelfDefined;
			} else {
				deadLineValueText = minDeadLine+"天~"+maxDeadLine+"天";
			}
		}
		
		return StringUtils.separateNumberChinese(deadLineValueText, ",");
	}

	public void setDeadLineValueText(String deadLineValueText) {
		this.deadLineValueText = deadLineValueText;
	}

	public String getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(String investAmount) {
		this.investAmount = investAmount;
	}

	public String getInvestCount() {
		return investCount;
	}

	public void setInvestCount(String investCount) {
		this.investCount = investCount;
	}

	public String getTotalProfix() {
		return totalProfix;
	}

	public void setTotalProfix(String totalProfix) {
		this.totalProfix = totalProfix;
	}

	public String getIsBind() {
		return isBind;
	}

	public void setIsBind(String isBind) {
		this.isBind = isBind;
	}

	public String getOrgAdvertisement() {
		return orgAdvertisement;
	}

	public void setOrgAdvertisement(String orgAdvertisement) {
		this.orgAdvertisement = orgAdvertisement;
	}

	
	

}
