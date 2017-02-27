package com.linkwee.openapi.request;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.linkwee.core.base.BaseEntity;

public class ProductPushRequest extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     *购买递增金额
     */
	@NotNull(message="购买递增金额不能为空")
	@DecimalMin(value="0", message="购买递增金额必须大于等于0")
	private BigDecimal buyIncreaseMoney;
	
    /**
     *产品单笔购买最大额度
     */
	private BigDecimal buyMaxMoney;
	
	/**
     *产品单笔购买最小额度
     */
	private BigDecimal buyMinMoney;
	
    /**
     *产品总额度产品募集要达到的总额度数不能为空作为募集完成时间确定条件之一
     */
	@NotNull(message="产品总额度不能为空")
	@DecimalMin(value="0", message="产品总额度必须大于等于0")
	private BigDecimal buyTotalMoney;
	
    /**
     *产品被投资总额
     */
	@NotNull(message="产品被投资总额不能为空")
	@DecimalMin(value="0", message="产品被投资总额必须大于等于0")
	private BigDecimal buyedTotalMoney;
	
    /**
     *产品已投资人数
     */
	@NotNull(message="产品已投资人数不能为空")
	@Min(value=0, message="产品已投资人数必须大于等于0")
	private Integer buyedTotalPeople;
	
    /**
     *募集开始时间|格式yyyy-mm-ddhh:mm:ss
     */ 
	private Date collectBeginTime;
	
    /**
     *募集截止时间可以为nul格式yyyy-mm-ddhh:mm:ss
     */ 
	private Date collectEndTime;
	
    /**
     *产品最小期限天数
     */
	@NotNull(message="产品最小期限天数不能为空")
	@Min(value=0, message="产品最小期限天数必须大于等于0")
	private Integer deadLineMinValue;
	
    /**
     *产品最大期限天数
     */
	@NotNull(message="产品最大期限天数不能为空")
	@Min(value=0, message="产品最大期限天数必须大于等于0")
	private Integer deadLineMaxValue;
	
    /**
     *产品最小期限 【自定义显示】
     */
	@NotBlank(message="产品最小期限 【自定义显示】不能为空")
	@Pattern(regexp="^[0-9]+[\u4e00-\u9fa5]+$")
	private String deadLineMinSelfDefined;
	
    /**
     *产品最大期限【自定义显示】
     */
	@NotBlank(message="产品最大期限【自定义显示】不能为空")
	@Pattern(regexp="^[0-9]+[\u4e00-\u9fa5]+$")
	private String deadLineMaxSelfDefined;
	
	/**
	 *浮动最大利率
	 */
	@NotNull(message="浮动最大利率不能为空")
	@Min(value=0, message="浮动最大利率必须大于等于0")
	private BigDecimal flowMaxRate;
	
    /**
     *浮动最小利率
     */
	@NotNull(message="浮动最小利率不能为空")
	@Min(value=0, message="浮动最小利率必须大于等于0")
	private BigDecimal flowMinRate;
	
    /**
     *起息方式(1=购买当日|2=购买次日|3=募集完成当日|4=募集完成次日|5=指定日期)
     */
	@NotNull(message="起息方式不能为空")
	@Range(min=1,max=5,message="起息方式必需在1-5之间")
	private Integer interestWay;
	
    /**
     *是否需要募集开始及截止时间(1=不需要|2=需要)
     */
	@NotNull(message="是否需要募集开始及截止时间不能为空")
	@Range(min=1,max=2,message="是否需要募集开始及截止时间必需在1-2之间")
	private Integer isCollect;
	
    /**
     *是否固定期限(1=固定期限|2=浮动期限)
     */
	@NotNull(message="是否固定期限不能为空")
	@Range(min=1,max=2,message="是否固定期限必需在1-2之间")
	private Integer isFixedDeadline;
	
    /**
     *是否浮动利率(1=固定利率|2=浮动利率)
     */
	@NotNull(message="是否浮动利率不能为空")
	@Range(min=1,max=2,message="是否浮动利率必需在1-2之间") 
	private Integer isFlow;
	
    /**
     *'是否限额产品。1-限额、2-不限额'
     */
	@NotNull(message="是否限额产品不能为空")
	@Range(min=1,max=2,message="是否限额产品必需在1-2之间")
	private Integer isQuota = 2;
	
    /**
     *是否可赎回可转让(0=不支持赎回和转让|1=可赎回|2=可转让|3=可赎回且可转让)
     */
	@NotNull(message="是否可赎回不能为空")
	@Range(min=0,max=3,message="是否可赎回可转让必需在0-3之间")
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
     *货币类型(1=rmb|2=港币|3=美元)
     */  
	private Integer moneyType = 1;
	
    /**
     *产品描述
     */
	@NotNull(message="产品描述不能为空")
	private String productDesc;
	
	/**
     *产品名称
     */
	@NotNull(message="产品名称不能为空")
	private String productName;
	
    /**
     *产品类型(1=P2P|2=信托 |3=资管|4=基金|401=公募基金|402=阳光私募|403=股权基金|5=保险|6=众筹|999=其他)
     */
	@NotNull(message="产品类型不能为空")
	private Integer productType;
	
    /**
     *还本付息方式(1=一次性到期|101=一次性按日|102=一次性按月|103=一次性按季|2=等额本息|3=等额本金|4=先息后本)
     */
	@NotNull(message="还本付息方式不能为空")
	private Integer repaymentWay;
	
    /**
     *风控类型(1=抵押|2=担保|3=信贷)
     */
	private Integer riskControlType;
	
    /**
     *风险级别(1=一般|2=重要|3=紧急|4=非常紧急)
     */
	private Integer riskLevel = 1;
	
    /**
     *产品销售开始时间
     */
	@NotNull(message="产品销售开始时间") 
	private Date saleStartTime;
	
    /**
     *外部产品uuid
     */
	@NotBlank(message="第三方产品Id不能为空") 
	private String thirdProductId;
	
    /**
     *产品起息日格式yyyy-mm-dd
     */
	private Date validBeginDate;
	
    /**
     *产品到期日格式yyyy-mm-dd
     */ 
	private Date validEndDate;
    /**
     *加息利率
     */
	private BigDecimal addRate;
    /**
     *是否拥有产品进度(0=有|1没有)
     */
	@NotNull(message="是否拥有产品进度不能为空")
	@Range(min=0,max=1,message="是否拥有产品进度必需在0-1之间")
	private Integer isHaveProgress;
    /**
     *是否是新手标(1=新手标|2=非新手标)
     */
	private Integer ifRookie = 2;
	
	public BigDecimal getBuyIncreaseMoney() {
		return buyIncreaseMoney;
	}

	public void setBuyIncreaseMoney(BigDecimal buyIncreaseMoney) {
		this.buyIncreaseMoney = buyIncreaseMoney;
	}

	public BigDecimal getBuyMaxMoney() {
		return buyMaxMoney;
	}

	public void setBuyMaxMoney(BigDecimal buyMaxMoney) {
		this.buyMaxMoney = buyMaxMoney;
	}

	public BigDecimal getBuyMinMoney() {
		return buyMinMoney;
	}

	public void setBuyMinMoney(BigDecimal buyMinMoney) {
		this.buyMinMoney = buyMinMoney;
	}

	public BigDecimal getBuyTotalMoney() {
		return buyTotalMoney;
	}

	public void setBuyTotalMoney(BigDecimal buyTotalMoney) {
		this.buyTotalMoney = buyTotalMoney;
	}

	public BigDecimal getBuyedTotalMoney() {
		return buyedTotalMoney;
	}

	public void setBuyedTotalMoney(BigDecimal buyedTotalMoney) {
		this.buyedTotalMoney = buyedTotalMoney;
	}

	public Integer getBuyedTotalPeople() {
		return buyedTotalPeople;
	}

	public void setBuyedTotalPeople(Integer buyedTotalPeople) {
		this.buyedTotalPeople = buyedTotalPeople;
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

	public Integer getInterestWay() {
		return interestWay;
	}

	public void setInterestWay(Integer interestWay) {
		this.interestWay = interestWay;
	}

	public Integer getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}

	public Integer getIsFixedDeadline() {
		return isFixedDeadline;
	}

	public void setIsFixedDeadline(Integer isFixedDeadline) {
		this.isFixedDeadline = isFixedDeadline;
	}

	public Integer getIsFlow() {
		return isFlow;
	}

	public void setIsFlow(Integer isFlow) {
		this.isFlow = isFlow;
	}

	public Integer getIsQuota() {
		return isQuota;
	}

	public void setIsQuota(Integer isQuota) {
		this.isQuota = isQuota;
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

	public Integer getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(Integer moneyType) {
		this.moneyType = moneyType;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Integer getRepaymentWay() {
		return repaymentWay;
	}

	public void setRepaymentWay(Integer repaymentWay) {
		this.repaymentWay = repaymentWay;
	}

	public Integer getRiskControlType() {
		return riskControlType;
	}

	public void setRiskControlType(Integer riskControlType) {
		this.riskControlType = riskControlType;
	}

	public Integer getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(Integer riskLevel) {
		this.riskLevel = riskLevel;
	}

	public Date getSaleStartTime() {
		return saleStartTime;
	}

	public void setSaleStartTime(Date saleStartTime) {
		this.saleStartTime = saleStartTime;
	}

	public String getThirdProductId() {
		return thirdProductId;
	}

	public void setThirdProductId(String thirdProductId) {
		this.thirdProductId = thirdProductId;
	}

	public Date getValidBeginDate() {
		return validBeginDate;
	}

	public void setValidBeginDate(Date validBeginDate) {
		this.validBeginDate = validBeginDate;
	}

	public Date getValidEndDate() {
		return validEndDate;
	}

	public void setValidEndDate(Date validEndDate) {
		this.validEndDate = validEndDate;
	}

	public BigDecimal getAddRate() {
		return addRate;
	}

	public void setAddRate(BigDecimal addRate) {
		this.addRate = addRate;
	}

	public Integer getIsHaveProgress() {
		return isHaveProgress;
	}

	public void setIsHaveProgress(Integer isHaveProgress) {
		this.isHaveProgress = isHaveProgress;
	}

	public Integer getIfRookie() {
		return ifRookie;
	}

	public void setIfRookie(Integer ifRookie) {
		this.ifRookie = ifRookie;
	}
	
}
