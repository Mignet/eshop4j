package com.eshop4j.web.response;

import com.eshop4j.core.util.EnumUtils;
import com.eshop4j.web.enums.cim.AuditStatusEnum;
import com.eshop4j.web.enums.cim.InterestWayEnum;
import com.eshop4j.web.enums.cim.IsCollectEnum;
import com.eshop4j.web.enums.cim.IsFixedDeadlineEnum;
import com.eshop4j.web.enums.cim.IsFlowEnum;
import com.eshop4j.web.enums.cim.IsHaveProgressEnum;
import com.eshop4j.web.enums.cim.IsQuotaEnum;
import com.eshop4j.web.enums.cim.IsRedemptionEnum;
import com.eshop4j.web.enums.cim.MoneyTypeEnum;
import com.eshop4j.web.enums.cim.ProductTypeEnums;
import com.eshop4j.web.enums.cim.RepaymentWayEnum;
import com.eshop4j.web.enums.cim.RiskControlTypeEnum;
import com.eshop4j.web.enums.cim.RiskLevelEnum;
import com.eshop4j.web.enums.cim.StatusEnum;
import com.eshop4j.web.model.CimProduct;


public class ProductDetailForManageResponse extends CimProduct{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	产品已被投资总金额
	 */
	private double  buyedTotalMoney;
	/**
	 * 产品已投资人数
	 */
	private Integer  buyedTotalPeople;
	/**
	 * 还本付息方式 名称
	 */
	private String repaymentWayText;
	/**
     * 产品类型Text
     */
	private String productTypeText;
    /**
     * 产品状态Text
     */
	private String statusText;
	/**
	 * 审核状态Text
	 */
	private String auditStatusText;
	/**
	 * 是否限额产品Text
	 */
	private String isQuotaText;
	/**
	 * 是否可赎回可转让Text
	 */
	private String isRedemptionText;
	/**
	 * 货币类型Text
	 */
	private String moneyTypeText;
	/**
	 * 风控类型Text
	 */
	private String riskControlTypeText;
	/**
	 * 风险级别Text
	 */
	private String riskLevelText;
	/**
	 * 是否需要募集开始及截止时间  Text
	 */
	private String isCollectText;
	/**
	 * 起息方式
	 */
	private String interestWayText;
	/**
	 * 是否固定期限
	 */
	private String isFixedDeadlineText;
	/**
	 * 是否浮动利率
	 */
	private String isFlowText;
	/**
	 * 是否拥有产品进度
	 */
	private String isHaveProgressText;

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

	public String getRepaymentWayText() {
		repaymentWayText = EnumUtils.getValueByKeyNull(getRepaymentWay(), RepaymentWayEnum.values());
		return repaymentWayText;
	}

	public void setRepaymentWayText(String repaymentWayText) {
		this.repaymentWayText = repaymentWayText;
	}

	public String getProductTypeText() {
    	productTypeText = EnumUtils.getValueByKeyNull(getProductType(), ProductTypeEnums.values());
		return productTypeText;
	}
	public void setProductTypeText(String productTypeText) {
		this.productTypeText = productTypeText;
	}
	public String getStatusText() {
    	statusText = EnumUtils.getValueByKeyNull(getStatus(), StatusEnum.values());
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getAuditStatusText() {
    	auditStatusText = EnumUtils.getValueByKeyNull(getAuditStatus(), AuditStatusEnum.values());
		return auditStatusText;
	}
	public void setAuditStatusText(String auditStatusText) {
		this.auditStatusText = auditStatusText;
	}
	public String getIsQuotaText() {
		isQuotaText = EnumUtils.getValueByKeyNull(getIsQuota(), IsQuotaEnum.values());
		return isQuotaText;
	}

	public void setIsQuotaText(String isQuotaText) {
		this.isQuotaText = isQuotaText;
	}

	public String getIsRedemptionText() {
		isRedemptionText = EnumUtils.getValueByKeyNull(getIsRedemption(), IsRedemptionEnum.values());
		return isRedemptionText;
	}

	public void setIsRedemptionText(String isRedemptionText) {
		this.isRedemptionText = isRedemptionText;
	}

	public String getMoneyTypeText() {
		moneyTypeText = EnumUtils.getValueByKeyNull(getMoneyType(), MoneyTypeEnum.values());
		return moneyTypeText;
	}

	public void setMoneyTypeText(String moneyTypeText) {
		this.moneyTypeText = moneyTypeText;
	}

	public String getRiskControlTypeText() {
		riskControlTypeText = EnumUtils.getValueByKeyNull(getRiskControlType(), RiskControlTypeEnum.values());
		return riskControlTypeText;
	}

	public void setRiskControlTypeText(String riskControlTypeText) {
		this.riskControlTypeText = riskControlTypeText;
	}

	public String getRiskLevelText() {
		riskLevelText = EnumUtils.getValueByKeyNull(getRiskLevel(), RiskLevelEnum.values());
		return riskLevelText;
	}

	public void setRiskLevelText(String riskLevelText) {
		this.riskLevelText = riskLevelText;
	}

	public String getIsCollectText() {
		isCollectText = EnumUtils.getValueByKeyNull(getIsCollect(), IsCollectEnum.values());
		return isCollectText;
	}

	public void setIsCollectText(String isCollectText) {
		this.isCollectText = isCollectText;
	}

	//1=购买当日|2=购买次日|3=募集完成当日|4=募集完成次日|5=指定日期
	public String getInterestWayText() {
		interestWayText = EnumUtils.getValueByKeyNull(getInterestWay(), InterestWayEnum.values());
		return interestWayText;
	}

	public void setInterestWayText(String interestWayText) {
		this.interestWayText = interestWayText;
	}

	//是否固定期限(1=固定期限|2=浮动期限)
	public String getIsFixedDeadlineText() {
		isFixedDeadlineText = EnumUtils.getValueByKeyNull(getIsFixedDeadline(), IsFixedDeadlineEnum.values());
		return isFixedDeadlineText;
	}

	public void setIsFixedDeadlineText(String isFixedDeadlineText) {
		this.isFixedDeadlineText = isFixedDeadlineText;
	}

	//是否浮动利率(1=固定利率|2=浮动利率)
	public String getIsFlowText() {
		isFlowText = EnumUtils.getValueByKeyNull(getIsFlow(), IsFlowEnum.values());
		return isFlowText;
	}

	public void setIsFlowText(String isFlowText) {
		this.isFlowText = isFlowText;
	}

	//是否拥有产品进度(0=有|1=没有)
	public String getIsHaveProgressText() {
		isHaveProgressText = EnumUtils.getValueByKeyNull(getIsHaveProgress(), IsHaveProgressEnum.values());
		return isHaveProgressText;
	}

	public void setIsHaveProgressText(String isHaveProgressText) {
		this.isHaveProgressText = isHaveProgressText;
	}
}
