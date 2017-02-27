package com.eshop4j.web.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.eshop4j.core.util.EnumUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.enums.cim.ProductTypeEnums;
import com.eshop4j.web.enums.cim.StatusEnum;
import com.eshop4j.xoss.util.BigDecimalUtil;

public class ProductsSalesStatisticsResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 平台名称
	 */
	private String orgName;
    /**
     *产品ID
     */
	private String productId;
    /**
     *产品类型(1=P2P|2=信托 |3=资管|4=基金|401=公募基金|402=阳光私募|403=股权基金|5=保险|6=众筹|999=其他)
     */
	private Integer productType;
    /**
     *产品状态(1-在售|2-售罄|3-募集失败)
     */
	private Integer status;
    /**
     *是否固定期限(1=固定期限|2=浮动期限)
     */
	private Integer isFixedDeadline;
	
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
     *是否浮动利率(1=固定利率|2=浮动利率)
     */
	private Integer isFlow;
	
    /**
     *浮动最小利率
     */
	private BigDecimal flowMinRate;
	
    /**
     *浮动最大利率
     */
	private BigDecimal flowMaxRate;
	/**
     * 产品名称
     */
	private String productName;
	/**
	 * 销售额（万元）
	 */
	private double saleroom;
	/**
	 * 年化销售额（万元）
	 */
	private double saleroomYear;
	/**
	 * 投资人数
	 */
	private Integer investNumberOfPeople;
	/**
	 * 人均投资额（万元）
	 */
	private double investPeopleAverage;
	/**
	 * 销售费用(元)
	 */
	private double saleCost;
	/**
	 * 上架时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date auditPassTime;
	
    /**
     *产品被投资总额
     */
	private BigDecimal buyedTotalMoney;
	
    /**
     *产品已投资人数
     */
	private Integer buyedTotalPeople;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
	/**
     * 产品类型Text
     */
	private String productTypeText;
	/**
	 * 产品利率Text	
	 */
	private String productRateText;
	/**
	 * 产品状态Text
	 */
	private String statusText;
	/**
	 * 期限\天Text
	 */
	private String deadLineValueText;
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
	public String getDeadLineValueText() {
		if (getIsFixedDeadline() == 1){
			if(StringUtils.isNotBlank(getDeadLineMinSelfDefined())){
				deadLineValueText = getDeadLineMinSelfDefined();
			} else {
				deadLineValueText = getDeadLineMinValue()+"天";
			}
		} else {
			if(StringUtils.isNotBlank(getDeadLineMinSelfDefined()) && StringUtils.isNotBlank(getDeadLineMaxSelfDefined())){
				deadLineValueText = getDeadLineMinSelfDefined()+"~"+getDeadLineMaxSelfDefined();
			} else {
				deadLineValueText = getDeadLineMinValue()+"天~"+getDeadLineMaxValue()+"天";
			}
		}
		return deadLineValueText;
	}
	public void setDeadLineValueText(String deadLineValueText) {
		this.deadLineValueText = deadLineValueText;
	}
	public String getProductRateText() {
		if(getIsFlow() == 1){
			productRateText = getFlowMinRate()+"%";
		} else if(getIsFlow() == 2){
			productRateText = getFlowMinRate()+"%~"+getFlowMaxRate()+"%";
		}
		return productRateText;
	}
	public void setProductRateText(String productRateText) {
		this.productRateText = productRateText;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getProductType() {
		return productType;
	}
	public void setProductType(Integer productType) {
		this.productType = productType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsFixedDeadline() {
		return isFixedDeadline;
	}
	public void setIsFixedDeadline(Integer isFixedDeadline) {
		this.isFixedDeadline = isFixedDeadline;
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
	public Integer getIsFlow() {
		return isFlow;
	}
	public void setIsFlow(Integer isFlow) {
		this.isFlow = isFlow;
	}
	public BigDecimal getFlowMinRate() {
		return flowMinRate;
	}
	public void setFlowMinRate(BigDecimal flowMinRate) {
		this.flowMinRate = flowMinRate;
	}
	public BigDecimal getFlowMaxRate() {
		return flowMaxRate;
	}
	public void setFlowMaxRate(BigDecimal flowMaxRate) {
		this.flowMaxRate = flowMaxRate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getSaleroom() {
		return saleroom;
	}
	public void setSaleroom(double saleroom) {
		this.saleroom = saleroom;
	}
	public double getSaleroomYear() {
		return BigDecimalUtil.round(saleroomYear, 2);
	}
	public void setSaleroomYear(double saleroomYear) {
		this.saleroomYear = saleroomYear;
	}
	public Integer getInvestNumberOfPeople() {
		return investNumberOfPeople;
	}
	public void setInvestNumberOfPeople(Integer investNumberOfPeople) {
		this.investNumberOfPeople = investNumberOfPeople;
	}
	public double getInvestPeopleAverage() {
		if(investNumberOfPeople != 0){		
			investPeopleAverage = BigDecimalUtil.round(saleroom/investNumberOfPeople, 2) ;
		}
		return investPeopleAverage;
	}
	public void setInvestPeopleAverage(double investPeopleAverage) {
		this.investPeopleAverage = investPeopleAverage;
	}
	public double getSaleCost() {
		return saleCost;
	}
	public void setSaleCost(double saleCost) {
		this.saleCost = saleCost;
	}
	public Date getAuditPassTime() {
		return auditPassTime;
	}
	public void setAuditPassTime(Date auditPassTime) {
		this.auditPassTime = auditPassTime;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
