package com.linkwee.web.model.product;

import java.util.Date;

import com.linkwee.core.base.BaseEntity;

/**
 * 产品(渠道管理)
 * 
 * @Author ZhongLing
 * @Date 2015年12月25日 下午5:18:12
 */
public class ProductSaleManageResp extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String productId;
	private String productName;
	private Double buyTotalMoney;
	private Double buyedTotalMoney;
	private Double restMoney;
	private Integer buyedTotalPeople;
	private Double saleReward;
	private Integer recomentSaleNumber;
	private Integer saleCount;
	private Date beginSaleTime;
	private Integer saleStatus;
	private String saleStatusName;
	private Date collectFinishTime;
	private String cateName;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProdutName() {
		return productName;
	}
	public void setProdutName(String produtName) {
		this.productName = produtName;
	}
	public Double getBuyTotalMoney() {
		return buyTotalMoney;
	}
	public void setBuyTotalMoney(Double buyTotalMoney) {
		this.buyTotalMoney = buyTotalMoney;
	}
	public Double getBuyedTotalMoney() {
		return buyedTotalMoney;
	}
	public void setBuyedTotalMoney(Double buyedTotalMoney) {
		this.buyedTotalMoney = buyedTotalMoney;
	}
	public Double getRestMoney() {
		return restMoney;
	}
	public void setRestMoney(Double restMoney) {
		this.restMoney = restMoney;
	}
	public Integer getBuyedTotalPeople() {
		return buyedTotalPeople;
	}
	public void setBuyedTotalPeople(Integer buyedTotalPeople) {
		this.buyedTotalPeople = buyedTotalPeople;
	}
	public Double getSaleReward() {
		return saleReward;
	}
	public void setSaleReward(Double saleReward) {
		this.saleReward = saleReward;
	}
	public Integer getRecomentSaleNumber() {
		return recomentSaleNumber;
	}
	public void setRecomentSaleNumber(Integer recomentSaleNumber) {
		this.recomentSaleNumber = recomentSaleNumber;
	}
	public Integer getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Date getBeginSaleTime() {
		return beginSaleTime;
	}
	public void setBeginSaleTime(Date beginSaleTime) {
		this.beginSaleTime = beginSaleTime;
	}
	public Integer getSaleStatus() {
		return saleStatus;
	}
	public void setSaleStatus(Integer saleStatus) {
		this.saleStatus = saleStatus;
	}
	public String getSaleStatusName() {
		return saleStatusName;
	}
	public void setSaleStatusName(String saleStatusName) {
		this.saleStatusName = saleStatusName;
	}
	public Date getCollectFinishTime() {
		return collectFinishTime;
	}
	public void setCollectFinishTime(Date collectFinishTime) {
		this.collectFinishTime = collectFinishTime;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	
	
}
