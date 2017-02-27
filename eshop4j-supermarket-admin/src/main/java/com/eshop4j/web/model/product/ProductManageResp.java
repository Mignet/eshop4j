package com.eshop4j.web.model.product;

import java.util.Date;

import com.eshop4j.core.base.BaseEntity;

/**
 * 产品(渠道管理)
 * 
 * @Author ZhongLing
 * @Date 2015年12月25日 下午5:18:12
 */
public class ProductManageResp extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String productId;
	private Integer productTypeId;
	/**
	 * 产品类别名称 产品id
	 */
	private String cateName;
	/**
	 * 产品名称
	 */
	private String productName;

	/**
	 * 固定利率
	 */
	private Double fixRate;
	
	/**
	 * 期限
	 */
	private int deadLineType;
	private Integer deadLineValue;
	
	/**
	 * 募集总额(单位元)
	 */
	private Double buyTotalMoney;
	private int saleStatus;
	private String saleStatusName;
	private Date beginSaleTime;
	private int isRecommended;
	private String listRecommended;
	private Integer listSort;
	private Double feeRatio;
	private int greyFlag;//是否开启灰度
	private String proDays;//产品期限
	private String proRecomend;//产品推荐（首页，列表）
	private Double flowMinRate;
	private Double flowMaxRate;
	private Integer collectLineMinValue;
	private Integer collectLineMaxValue;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getFixRate() {
		return fixRate;
	}
	public void setFixRate(Double fixRate) {
		this.fixRate = fixRate;
	}
	public int getDeadLineType() {
		return deadLineType;
	}
	public void setDeadLineType(int deadLineType) {
		this.deadLineType = deadLineType;
	}
	public Integer getDeadLineValue() {
		return deadLineValue;
	}
	public void setDeadLineValue(Integer deadLineValue) {
		this.deadLineValue = deadLineValue;
	}
	public Double getBuyTotalMoney() {
		return buyTotalMoney;
	}
	public void setBuyTotalMoney(Double buyTotalMoney) {
		this.buyTotalMoney = buyTotalMoney;
	}
	public int getSaleStatus() {
		return saleStatus;
	}
	public void setSaleStatus(int saleStatus) {
		this.saleStatus = saleStatus;
	}
	public Date getBeginSaleTime() {
		return beginSaleTime;
	}
	public void setBeginSaleTime(Date beginSaleTime) {
		this.beginSaleTime = beginSaleTime;
	}
	public int getIsRecommended() {
		return isRecommended;
	}
	public void setIsRecommended(int isRecommended) {
		this.isRecommended = isRecommended;
	}
	public String getListRecommended() {
		return listRecommended;
	}
	public void setListRecommended(String listRecommended) {
		this.listRecommended = listRecommended;
	}
	public Integer getListSort() {
		return listSort;
	}
	public void setListSort(Integer listSort) {
		this.listSort = listSort;
	}
	public Double getFeeRatio() {
		return feeRatio;
	}
	public void setFeeRatio(Double feeRatio) {
		this.feeRatio = feeRatio;
	}
	public String getProDays() {
		return proDays;
	}
	public void setProDays(String proDays) {
		this.proDays = proDays;
	}
	public String getProRecomend() {
		return proRecomend;
	}
	public void setProRecomend(String proRecomend) {
		this.proRecomend = proRecomend;
	}
	public String getSaleStatusName() {
		return saleStatusName;
	}
	public void setSaleStatusName(String saleStatusName) {
		this.saleStatusName = saleStatusName;
	}
	public int getGreyFlag() {
		return greyFlag;
	}
	public void setGreyFlag(int greyFlag) {
		this.greyFlag = greyFlag;
	}
	public Double getFlowMinRate() {
		return flowMinRate;
	}
	public void setFlowMinRate(Double flowMinRate) {
		this.flowMinRate = flowMinRate;
	}
	public Double getFlowMaxRate() {
		return flowMaxRate;
	}
	public void setFlowMaxRate(Double flowMaxRate) {
		this.flowMaxRate = flowMaxRate;
	}
	public Integer getCollectLineMinValue() {
		return collectLineMinValue;
	}
	public void setCollectLineMinValue(Integer collectLineMinValue) {
		this.collectLineMinValue = collectLineMinValue;
	}
	public Integer getCollectLineMaxValue() {
		return collectLineMaxValue;
	}
	public void setCollectLineMaxValue(Integer collectLineMaxValue) {
		this.collectLineMaxValue = collectLineMaxValue;
	}
	

}
