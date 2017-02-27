package com.linkwee.web.response.tc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.ObjectUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.xoss.helper.ConfigHelper;

public class UnrecordInvestListResponse implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 2376730982373179040L;
	private Integer id;
	private String name;
	private String mobile;
	private String platfromName;
	private String productName;
	private BigDecimal investAmt;
	private String remark;
	private Integer status;
	private String img;
	private String cfpName;
	private String card; 
	
	/**
     *产品期限类型 : 1=天|2=月|3=年
     */
	private Integer productDeadLineType;
	
    /**
     *产品期限（天数）
     */
	private Integer productDeadLineValue;
	
    /**
     *产品期限
     */
	private String productDeadLine;
	
    /**
     *佣金率
     */
	private BigDecimal feeRate;
	/**
	 * 佣金
	 */
	private BigDecimal feeAmt;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date time;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date shTime;
	  /**
     *投资时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date investTime;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPlatfromName() {
		return platfromName;
	}
	public void setPlatfromName(String platfromName) {
		this.platfromName = platfromName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getInvestAmt() {
		return investAmt.setScale(2,BigDecimal.ROUND_DOWN).toString();
	}
	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCfpName() {
		return cfpName;
	}
	public void setCfpName(String cfpName) {
		this.cfpName = cfpName;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	
	
	public Date getShTime() {
		return shTime;
	}
	public void setShTime(Date shTime) {
		this.shTime = shTime;
	}
	
	public Integer getProductDeadLineValue() {
		return productDeadLineValue;
	}
	public void setProductDeadLineValue(Integer productDeadLineValue) {
		this.productDeadLineValue = productDeadLineValue;
	}
	
	public void setProductDeadLineType(Integer productDeadLineType) {
		this.productDeadLineType = productDeadLineType;
	}
	
	
	public void setProductDeadLine(String productDeadLine) {
		this.productDeadLine = productDeadLine;
	}
	
	
	public String getDeadLine() {
		if(ObjectUtils.equals(productDeadLineType, 0))return productDeadLine;
		
		StringBuilder sb = new StringBuilder(productDeadLine);
		switch (productDeadLineType) {
		case 1:
			sb.append("天");
			break;
		case 2:
			sb.append("个月");
			break;
		case 3:
			sb.append("年");
			break;
		}
		return sb.toString();
	}
	
	public BigDecimal getFeeRate() {
		return feeRate==null ?   new BigDecimal(0): feeRate.setScale(2,BigDecimal.ROUND_HALF_UP);
	}
	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}
	
	public Date getInvestTime() {
		return investTime;
	}
	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}
	
	
	
	public BigDecimal getFeeAmt() {
		return feeAmt==null ?   new BigDecimal(0): feeAmt.setScale(2,BigDecimal.ROUND_HALF_UP);
	}
	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
