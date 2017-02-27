package com.eshop4j.web.response.orgInfo;

import java.math.BigDecimal;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.eshop4j.core.base.BaseEntity;

public class OrgSaleProductResponse extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4276272134251955970L;
	
	
	
	/**
	 * 机构编号
	 */
	private String orgNo;
	
	/**
	 * 产品编号
	 */
	private String proId;
	
	/**
	 * 产品名称
	 */
	private String proName;
	
	/**
	 * 是否浮动利率(1=固定利率|2=浮动利率)
	 */
	private  Integer isFlow=1;
	
	/**
	 * 浮动最小利率
	 */
	private  Double feeRateMin;
	/**
	 * 浮动最大利率
	 */
	private  Double feeRateMax;
	/**
	 * 固定利率
	 */
	private  Double fixRate;
	
	
	/**
	 * 产品期限类型  1=天数|2=自然月
	 */
	private Integer proDayType;
	
	/**
	 * 产品期限
	*/
	private Integer proDay;
	
	
	
	
	/**
	 * 总额
	 */
	private BigDecimal totalMoney;
	
	/**
	 * 已投资金额
	 */
	private BigDecimal investmentAmount;
	
	public OrgSaleProductResponse() {}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public void setIsFlow(Integer isFlow) {
		this.isFlow = isFlow;
	}



	public void setFeeRateMin(Double feeRateMin) {
		this.feeRateMin = feeRateMin;
	}


	public void setFeeRateMax(Double feeRateMax) {
		this.feeRateMax = feeRateMax;
	}
	
	


	public void setFixRate(Double fixRate) {
		this.fixRate = fixRate;
	}

	public String getFeeRate() {
		return ObjectUtils.equals(this.isFlow,1) ? String.valueOf(this.fixRate):StringUtils.join(new Object[]{this.feeRateMin,this.feeRateMax}, '-');
	}

	public void setProDayType(Integer proDayType) {
		this.proDayType = proDayType;
	}

	public String getDay() {
		return ObjectUtils.equals(this.proDayType,1)  ?  StringUtils.join(new Object[]{proDay,"天".intern()}, null):StringUtils.join(new Object[]{proDay,"个月".intern()}, null);
	}

	public void setProDay(Integer proDay) {
		this.proDay = proDay;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public BigDecimal getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(BigDecimal investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	
}
