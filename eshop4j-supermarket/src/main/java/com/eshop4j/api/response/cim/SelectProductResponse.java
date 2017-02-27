package com.eshop4j.api.response.cim;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.StringUtils;

public class SelectProductResponse extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *  浮动最大利率 
	 */
	private double flowMaxRate;
	/**
	 *  浮动最小利率 
	 */
	private double flowMinRate;
	/**
	 * 1固定利率；2浮动利率 
	 */
	private Integer isFlow;
	/**
	 *  产品id 
	 */
	private String productId;
	/**
	 *  产品名称 
	 */
	private String productName;
    /**
     *产品状态(1-在售|2-售罄|3-募集失败)
     */
	private Integer status;
	/**
	 * 机构编码
	 */
	private String orgNumber;
	/**
	 * 机构名称
	 */
	private String orgName;
	/**
	 * 外部产品uuid
	 */
	private String thirdProductId;
	/**
	 * 是否理财师推荐	0-未推荐 非0-已推荐
	 */
	private String cfpRecommend;
	/**
	 * 	产品总额度
	 */
	private double  buyTotalMoney;
	/**
	 * 产品单笔购买最小额度
	 */
	private double buyMinMoney;
	/**
	 * 产品单笔购买最小额度
	 */
	private double buyMaxMoney;
	/**
	 * 产品被投资总额
	 */
	private double  buyedTotalMoney;
	/**
	 * 产品已投资人数
	 */
	private Integer  buyedTotalPeople;
	/**
	 * 佣金率
	 */
	private double feeRatio;
    /**
     *'是否限额产品。1-限额、2-不限额'
     */
	private Integer isQuota;
    /**
     *产品最小期限天数
     */
	private Integer deadLineMinValue;
	
    /**
     *产品最大期限天数
     */
	private Integer deadLineMaxValue;
	
    /**
     *产品最小期限天数 自定义显示
     */
	private String deadLineMinSelfDefined;
	
    /**
     *产品最大期限天数 自定义显示
     */
	private String deadLineMaxSelfDefined;
	
    /**
     *是否需要募集开始及截止时间(1=不需要|2=需要)
     */
	private Integer isCollect;
	
    /**
     *募集开始时间|格式yyyy-mm-ddhh:mm:ss
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date collectBeginTime;
	
    /**
     *募集截止时间可以为nul格式yyyy-mm-ddhh:mm:ss
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date collectEndTime;
	
    /**
     *是否固定期限(1=固定期限|2=浮动期限)
     */
	private Integer isFixedDeadline;
    /**
     *是否可赎回可转让(0=不支持赎回和转让|1=可赎回|2=可转让|3=可赎回且可转让)
     */
	private Integer isRedemption;
    /**
     *可赎回天数
     */
	private Integer redemptionTime;
    /**
     *可转让天数
     */
	private Integer assignmentTime;
    /**
     *加息利率
     */
	private BigDecimal addRate;
    /**
     *平台返现利率
     */
	private BigDecimal platformCashback;
    /**
     *是否拥有产品进度(0=有|1没有)
     */
	private Integer isHaveProgress;
	/**
	 * 产品详情URL
	 */
	private String productDetailUrl;
    /**
     *	收费类型 
     *	1:cpa-按投资人数量进行收费
     *	2:cps-按投资金额进行收费
     */
	private Integer orgFeeType;
	/**
	 * 产品利率Text
	 */
	private String productRateText;
	/**
	 * 产品期限
	 */
	private String deadLineValueText;
	/**
	 * 标签列表
	 */
	private ArrayList<String> tagList;
	/**
	 * 标签列表-右上角
	 */
	private ArrayList<String> tagListRight;
	/**
	 * 产品平台logo
	 */
	private String productLogo;
	
	public double getFlowMaxRate() {
		return flowMaxRate;
	}
	public void setFlowMaxRate(double flowMaxRate) {
		this.flowMaxRate = flowMaxRate;
	}
	public double getFlowMinRate() {
		return flowMinRate;
	}
	public void setFlowMinRate(double flowMinRate) {
		this.flowMinRate = flowMinRate;
	}
	public Integer getIsFlow() {
		return isFlow;
	}
	public void setIsFlow(Integer isFlow) {
		this.isFlow = isFlow;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOrgNumber() {
		return orgNumber;
	}
	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getThirdProductId() {
		return thirdProductId;
	}
	public void setThirdProductId(String thirdProductId) {
		this.thirdProductId = thirdProductId;
	}
	public String getCfpRecommend() {
		return cfpRecommend;
	}
	public void setCfpRecommend(String cfpRecommend) {
		this.cfpRecommend = cfpRecommend;
	}
	public double getBuyTotalMoney() {
		return buyTotalMoney;
	}
	public void setBuyTotalMoney(double buyTotalMoney) {
		this.buyTotalMoney = buyTotalMoney;
	}
	public double getBuyMinMoney() {
		return buyMinMoney;
	}
	public void setBuyMinMoney(double buyMinMoney) {
		this.buyMinMoney = buyMinMoney;
	}
	public double getBuyMaxMoney() {
		return buyMaxMoney;
	}
	public void setBuyMaxMoney(double buyMaxMoney) {
		this.buyMaxMoney = buyMaxMoney;
	}
	public double getBuyedTotalMoney() {
		return buyedTotalMoney;
	}
	public void setBuyedTotalMoney(double buyedTotalMoney) {
		this.buyedTotalMoney = buyedTotalMoney;
	}
	public Integer getBuyedTotalPeople() {
		return buyedTotalPeople;
	}
	public void setBuyedTotalPeople(Integer buyedTotalPeople) {
		this.buyedTotalPeople = buyedTotalPeople;
	}
	public double getFeeRatio() {
		return feeRatio;
	}
	public void setFeeRatio(double feeRatio) {
		this.feeRatio = feeRatio;
	}
	public Integer getIsQuota() {
		return isQuota;
	}
	public void setIsQuota(Integer isQuota) {
		this.isQuota = isQuota;
	}
	public Integer getDeadLineMinValue() {
		return deadLineMinValue;
	}
	public void setDeadLineMinValue(Integer deadLineMinValue) {
		this.deadLineMinValue = deadLineMinValue;
	}
	public Integer getDeadLineMaxValue() {
		return deadLineMaxValue;
	}
	public void setDeadLineMaxValue(Integer deadLineMaxValue) {
		this.deadLineMaxValue = deadLineMaxValue;
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
	public Integer getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}
	public Date getCollectBeginTime() {
		return collectBeginTime;
	}
	public void setCollectBeginTime(Date collectBeginTime) {
		this.collectBeginTime = collectBeginTime;
	}
	public Date getCollectEndTime() {
		return collectEndTime;
	}
	public void setCollectEndTime(Date collectEndTime) {
		this.collectEndTime = collectEndTime;
	}
	public Integer getIsFixedDeadline() {
		return isFixedDeadline;
	}
	public void setIsFixedDeadline(Integer isFixedDeadline) {
		this.isFixedDeadline = isFixedDeadline;
	}
	public Integer getIsRedemption() {
		return isRedemption;
	}
	public void setIsRedemption(Integer isRedemption) {
		this.isRedemption = isRedemption;
	}
	public Integer getRedemptionTime() {
		return redemptionTime;
	}
	public void setRedemptionTime(Integer redemptionTime) {
		this.redemptionTime = redemptionTime;
	}
	public Integer getAssignmentTime() {
		return assignmentTime;
	}
	public void setAssignmentTime(Integer assignmentTime) {
		this.assignmentTime = assignmentTime;
	}
	public BigDecimal getAddRate() {
		return addRate;
	}
	public void setAddRate(BigDecimal addRate) {
		this.addRate = addRate;
	}
	public BigDecimal getPlatformCashback() {
		return platformCashback;
	}
	public void setPlatformCashback(BigDecimal platformCashback) {
		this.platformCashback = platformCashback;
	}
	public Integer getIsHaveProgress() {
		return isHaveProgress;
	}
	public void setIsHaveProgress(Integer isHaveProgress) {
		this.isHaveProgress = isHaveProgress;
	}
	public String getProductDetailUrl() {
		return productDetailUrl;
	}
	public void setProductDetailUrl(String productDetailUrl) {
		this.productDetailUrl = productDetailUrl;
	}
	public Integer getOrgFeeType() {
		return orgFeeType;
	}
	public void setOrgFeeType(Integer orgFeeType) {
		this.orgFeeType = orgFeeType;
	}
	public String getProductRateText() {
		if(isFlow == 1){
			productRateText = flowMinRate+"%";
		} else if(isFlow == 2){
			productRateText = flowMinRate+"%~"+flowMaxRate+"%";
		}
		return productRateText;
	}
	public void setProductRateText(String productRateText) {
		this.productRateText = productRateText;
	}
	public String getDeadLineValueText() {
		if (isFixedDeadline == 1){
			if(StringUtils.isNotBlank(deadLineMinSelfDefined)){
				deadLineValueText = deadLineMinSelfDefined;
			} else {
				deadLineValueText = deadLineMinValue+"天";
			}
		} else {
			if(StringUtils.isNotBlank(deadLineMinSelfDefined) && StringUtils.isNotBlank(deadLineMaxSelfDefined)){
				deadLineValueText = deadLineMinSelfDefined+"~"+deadLineMaxSelfDefined;
			} else {
				deadLineValueText = deadLineMinValue+"天~"+deadLineMaxValue+"天";
			}
		}
		return StringUtils.separateNumberChinese(deadLineValueText, ",");
	}
	public void setDeadLineValueText(String deadLineValueText) {
		this.deadLineValueText = deadLineValueText;
	}
	public ArrayList<String> getTagList() {
		tagList = new ArrayList<String>();
		if(isRedemption == 1){
			tagList.add("可赎回");
		} else if(isRedemption == 2){
			tagList.add("可转让");
		} else if(isRedemption == 3){
			tagList.add("可赎回");
			tagList.add("可转让");
		}
		return tagList;
	}
	public void setTagList(ArrayList<String> tagList) {
		this.tagList = tagList;
	}
	public ArrayList<String> getTagListRight() {
		tagListRight = new ArrayList<String>();
		if(orgFeeType != null){
			if(orgFeeType == 1){
				tagListRight.add("首投");
			}
		}	
		return tagListRight;
	}
	public void setTagListRight(ArrayList<String> tagListRight) {
		this.tagListRight = tagListRight;
	}
	public String getProductLogo() {
		return productLogo;
	}
	public void setProductLogo(String productLogo) {
		this.productLogo = productLogo;
	}
}
