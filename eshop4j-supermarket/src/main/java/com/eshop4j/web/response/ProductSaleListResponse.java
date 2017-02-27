package com.eshop4j.web.response;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.EnumUtils;
import com.eshop4j.web.enums.cim.ProductTypeEnums;
import com.eshop4j.web.enums.cim.StatusEnum;

public class ProductSaleListResponse extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     *产品ID
     */
	private String productId;
	/**
	 * 平台名称
	 */
	private String orgName;
    /**
     *产品名称
     */
	private String productName;
    /**
     *产品总额度
     */
	private BigDecimal buyTotalMoney;
	/**
	 * 	产品已被投资总金额
	 */
	private double  buyedTotalMoney;
	/**
	 * 	剩余额度
	 */
	private double  surplusMoney;
    /**
     *产品销售开始时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date saleStartTime;
    /**
     *产品销售结束时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date saleEndTime;
    /**
     *产品类型(1=P2P|2=信托 |3=资管|4=基金|401=公募基金|402=阳光私募|403=股权基金|5=保险|6=众筹|999=其他)
     */
	private Integer productType;
    /**
     *产品状态(1-在售|2-售罄|3-募集失败)
     */
	private Integer status;
    /**
     * 产品状态Text
     */
	private String statusText;
	/**
     * 产品类型Text
     */
	private String productTypeText;	
	
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getBuyTotalMoney() {
		return buyTotalMoney;
	}

	public void setBuyTotalMoney(BigDecimal buyTotalMoney) {
		this.buyTotalMoney = buyTotalMoney;
	}

	public double getBuyedTotalMoney() {
		return buyedTotalMoney;
	}

	public void setBuyedTotalMoney(double buyedTotalMoney) {
		this.buyedTotalMoney = buyedTotalMoney;
	}

	public double getSurplusMoney() {
		return surplusMoney;
	}

	public void setSurplusMoney(double surplusMoney) {
		this.surplusMoney = surplusMoney;
	}

	public String getStatusText() {
    	statusText = EnumUtils.getValueByKeyNull(getStatus(), StatusEnum.values());
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public Date getSaleStartTime() {
		return saleStartTime;
	}

	public void setSaleStartTime(Date saleStartTime) {
		this.saleStartTime = saleStartTime;
	}

	public Date getSaleEndTime() {
		return saleEndTime;
	}

	public void setSaleEndTime(Date saleEndTime) {
		this.saleEndTime = saleEndTime;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
}
