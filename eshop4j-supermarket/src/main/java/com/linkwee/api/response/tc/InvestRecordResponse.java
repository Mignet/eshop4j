package com.linkwee.api.response.tc;

import java.util.Date;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.base.BaseEntity;

public class InvestRecordResponse extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 95321881945525769L;
	
	private String platfrom;
	
	private String platfromName;
	
	/**
	 * 产品编号
	 */
	private String productId;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 投资金额
	 */
	private Double investAmount;
	/**
	 * 收益
	 */
	private Double profit;
	
	/**
	 * 是否固定期限(1=固定期限|2=浮动期限)
	 */
	private Integer dayType;
	
	/**
	 * 期限
	 */
	private Integer minDay;
	private Integer maxDay;
	private String selfMinDay;
	private String selfMaxDay;
	
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")   
	private Date updateDate;
	
	/**
	 * 起息日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")   
	private Date startDate;
	/**
	 * 到期日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")   
	private Date endDate;
	
	
	public String getPlatfrom() {
		return platfrom;
	}
	public void setPlatfrom(String platfrom) {
		this.platfrom = platfrom;
	}
	
	public String getPlatfromName() {
		return platfromName;
	}
	public void setPlatfromName(String platfromName) {
		this.platfromName = platfromName;
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
	public Double getInvestAmount() {
		return investAmount;
	}
	public void setInvestAmount(Double investAmount) {
		this.investAmount = investAmount;
	}
	public Double getProfit() {
		return profit;
	}
	public void setProfit(Double profit) {
		this.profit = profit;
	}
	
	public void setDayType(Integer dayType) {
		this.dayType = dayType;
	}
	
	public String getDay() {
		String day = "";
		if (ObjectUtils.equals(dayType, 1)){
			if(StringUtils.isNotBlank(selfMinDay)){
				day =  selfMinDay;
			} else {
				day = minDay+"天";
			}
		} else {
			if(StringUtils.isNotBlank(selfMinDay) && StringUtils.isNotBlank(selfMaxDay)){
				day = selfMinDay+"~"+selfMaxDay;
			} else {
				day = minDay+"天~"+maxDay+"天";
			}
		}
		return com.linkwee.core.util.StringUtils.separateNumberChinese(day, ",");
	}

	
	public void setMinDay(Integer minDay) {
		this.minDay = minDay;
	}

	
	public void setMaxDay(Integer maxDay) {
		this.maxDay = maxDay;
	}

	
	
	
	public void setSelfMinDay(String selfMinDay) {
		this.selfMinDay = selfMinDay;
	}
	public void setSelfMaxDay(String selfMaxDay) {
		this.selfMaxDay = selfMaxDay;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
