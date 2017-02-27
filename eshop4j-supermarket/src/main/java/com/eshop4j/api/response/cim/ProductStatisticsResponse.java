package com.eshop4j.api.response.cim;

import java.math.BigDecimal;

public class ProductStatisticsResponse extends ProductStatisticsPreferenceResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**	
	 * 产品数量
	 */
	private Integer count;
    /**
     *浮动最小利率统计
     */
	private BigDecimal flowMinRateStatistics;
	
    /**
     *浮动最大利率统计
     */
	private BigDecimal flowMaxRateStatistics;
	
    /**
     *投资者端 分类logo
     */
	private String cateLogoInvestor;
	
    /**
     *分类logo 猎才大师
     */
	private String cateLogoChannel;
	
    /**
     *分类图片跳转链接
     */
	private String urlLink;
	
    /**
     *分类说明
     */
	private String cateDeclare;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getFlowMinRateStatistics() {
		return flowMinRateStatistics;
	}

	public void setFlowMinRateStatistics(BigDecimal flowMinRateStatistics) {
		this.flowMinRateStatistics = flowMinRateStatistics;
	}

	public BigDecimal getFlowMaxRateStatistics() {
		return flowMaxRateStatistics;
	}

	public void setFlowMaxRateStatistics(BigDecimal flowMaxRateStatistics) {
		this.flowMaxRateStatistics = flowMaxRateStatistics;
	}

	public String getCateLogoInvestor() {
		return cateLogoInvestor;
	}

	public void setCateLogoInvestor(String cateLogoInvestor) {
		this.cateLogoInvestor = cateLogoInvestor;
	}

	public String getCateLogoChannel() {
		return cateLogoChannel;
	}

	public void setCateLogoChannel(String cateLogoChannel) {
		this.cateLogoChannel = cateLogoChannel;
	}

	public String getUrlLink() {
		return urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}

	public String getCateDeclare() {
		return cateDeclare;
	}

	public void setCateDeclare(String cateDeclare) {
		this.cateDeclare = cateDeclare;
	}
}
