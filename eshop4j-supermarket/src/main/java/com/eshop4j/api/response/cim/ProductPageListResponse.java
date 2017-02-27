package com.eshop4j.api.response.cim;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.StringUtils;

public class ProductPageListResponse extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  浮动最大利率 
	 */
	private BigDecimal flowMaxRate;
	/**
	 *  浮动最小利率 
	 */
	private BigDecimal flowMinRate;
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
	private BigDecimal  buyTotalMoney;
	/**
	 * 产品单笔购买最小额度
	 */
	private BigDecimal buyMinMoney;
	/**
	 * 产品单笔购买最小额度
	 */
	private BigDecimal buyMaxMoney;
	/**
	 * 产品被投资总额
	 */
	private BigDecimal  buyedTotalMoney;
	/**
	 * 产品已投资人数
	 */
	private Integer  buyedTotalPeople;
	/**
	 * 佣金率
	 */
	private BigDecimal feeRatio;
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
	 * 产品期限
	 */
	private String deadLineValueText;
	/**
	 * 产品利率Text
	 */
	private String productRateText;
	/**
	 * 标签列表
	 */
	private ArrayList<String> tagList;
	/**
	 * 标签列表-右上角
	 */
	private ArrayList<String> tagListRight;
	/**
	 * 标签列表-右上角新手标（区分样式）
	 */
	private ArrayList<String> tagListRightNewer;
	/**
	 * 产品logo
	 */
	private String productLogo;
    /**
     *产品销售开始时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date saleStartTime;
    /**
     *系统当前时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date timeNow;
	/**
     *是否虚拟机构 (1：是 ,0：否)
     */
	private Integer orgIsstaticproduct;
    /**
     *安全评级 1-B,2-BB,3-BBB,4-A,5-AA,6-AAA
     */
	private String grade;
    /**
     *金额限制(元)
     */
	private BigDecimal orgAmountLimit;
	
	/**
	 * 可购买金额
	 */
	private BigDecimal couldbuyMoney;
	/**
	 * 是否有可使用红包
	 */
	private boolean hasRedPacket;
	/**
	 * 对接的机构类型(0:移动+PC端，1:移动端，2:PC端)
	 */
	private Integer orgJointType;
	
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
	public Integer getBuyedTotalPeople() {
		return buyedTotalPeople;
	}
	public void setBuyedTotalPeople(Integer buyedTotalPeople) {
		this.buyedTotalPeople = buyedTotalPeople;
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
	public String getDeadLineValueText() {
		if(isFixedDeadline != null){
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
		} else {
			return "";
		}
	}
	public void setDeadLineValueText(String deadLineValueText) {
		this.deadLineValueText = deadLineValueText;
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
	public ArrayList<String> getTagList() {
		return tagList;
	}
	public void setTagList(ArrayList<String> tagList) {
		this.tagList = tagList;
	}
	public ArrayList<String> getTagListRight() {
		return tagListRight;
	}
	public void setTagListRight(ArrayList<String> tagListRight) {
		this.tagListRight = tagListRight;
	}
	public ArrayList<String> getTagListRightNewer() {
		return tagListRightNewer;
	}
	public void setTagListRightNewer(ArrayList<String> tagListRightNewer) {
		this.tagListRightNewer = tagListRightNewer;
	}
	public String getProductLogo() {
		return productLogo;
	}
	public void setProductLogo(String productLogo) {
		this.productLogo = productLogo;
	}
	public Date getSaleStartTime() {
		return saleStartTime;
	}
	public void setSaleStartTime(Date saleStartTime) {
		this.saleStartTime = saleStartTime;
	}
	public Date getTimeNow() {
		timeNow = new Date();
		return timeNow;
	}
	public void setTimeNow(Date timeNow) {
		this.timeNow = timeNow;
	}
	public Integer getOrgIsstaticproduct() {
		return orgIsstaticproduct;
	}
	public void setOrgIsstaticproduct(Integer orgIsstaticproduct) {
		this.orgIsstaticproduct = orgIsstaticproduct;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public BigDecimal getOrgAmountLimit() {
		return orgAmountLimit;
	}
	public void setOrgAmountLimit(BigDecimal orgAmountLimit) {
		this.orgAmountLimit = orgAmountLimit;
	}
	public boolean isHasRedPacket() {
		return hasRedPacket;
	}
	public void setHasRedPacket(boolean hasRedPacket) {
		this.hasRedPacket = hasRedPacket;
	}
	public Integer getOrgJointType() {
		return orgJointType;
	}
	public void setOrgJointType(Integer orgJointType) {
		this.orgJointType = orgJointType;
	}
	public BigDecimal getBuyTotalMoney() {
		return buyTotalMoney;
	}
	public void setBuyTotalMoney(BigDecimal buyTotalMoney) {
		this.buyTotalMoney = buyTotalMoney;
	}
	public BigDecimal getBuyMinMoney() {
		return buyMinMoney;
	}
	public void setBuyMinMoney(BigDecimal buyMinMoney) {
		this.buyMinMoney = buyMinMoney;
	}
	public BigDecimal getBuyMaxMoney() {
		return buyMaxMoney;
	}
	public void setBuyMaxMoney(BigDecimal buyMaxMoney) {
		this.buyMaxMoney = buyMaxMoney;
	}
	public BigDecimal getBuyedTotalMoney() {
		return buyedTotalMoney;
	}
	public void setBuyedTotalMoney(BigDecimal buyedTotalMoney) {
		this.buyedTotalMoney = buyedTotalMoney;
	}
	public BigDecimal getCouldbuyMoney() {
		if(buyTotalMoney.compareTo(buyedTotalMoney) > 0 && buyMaxMoney != null && buyMaxMoney.compareTo(new BigDecimal(0)) > 0 && buyTotalMoney.subtract(buyedTotalMoney).compareTo(buyMaxMoney)>= 0){
			couldbuyMoney = buyMaxMoney;
		}else {
			couldbuyMoney = buyTotalMoney.subtract(buyedTotalMoney);		
		}
		return couldbuyMoney;
	}
	public void setCouldbuyMoney(BigDecimal couldbuyMoney) {
		this.couldbuyMoney = couldbuyMoney;
	}
	public BigDecimal getFlowMaxRate() {
		return flowMaxRate;
	}
	public void setFlowMaxRate(BigDecimal flowMaxRate) {
		this.flowMaxRate = flowMaxRate;
	}
	public BigDecimal getFlowMinRate() {
		return flowMinRate;
	}
	public void setFlowMinRate(BigDecimal flowMinRate) {
		this.flowMinRate = flowMinRate;
	}
	public BigDecimal getFeeRatio() {
		return feeRatio;
	}
	public void setFeeRatio(BigDecimal feeRatio) {
		this.feeRatio = feeRatio;
	}

}
