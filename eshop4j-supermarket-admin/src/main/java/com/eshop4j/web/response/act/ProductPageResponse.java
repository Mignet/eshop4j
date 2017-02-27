package com.eshop4j.web.response.act;

import com.eshop4j.core.util.StringUtils;

public class ProductPageResponse {

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
	 * 佣金率
	 */
	private double feeRatio;
	
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
	 * 产品期限
	 */
	private String deadLineValueText;
	/**
	 * 产品利率Text
	 */
	private String productRateText;
	
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
	
	public String getFeeRatio() {
		return feeRatio+"%";
	}
	public void setFeeRatio(double feeRatio) {
		this.feeRatio = feeRatio;
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
		return deadLineValueText;
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
	public Integer getIsFixedDeadline() {
		return isFixedDeadline;
	}
	public void setIsFixedDeadline(Integer isFixedDeadline) {
		this.isFixedDeadline = isFixedDeadline;
	}
	
	
}
