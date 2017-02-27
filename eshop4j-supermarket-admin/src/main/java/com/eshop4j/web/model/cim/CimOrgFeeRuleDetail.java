package com.eshop4j.web.model.cim;

import java.math.BigDecimal;

import com.eshop4j.core.base.BaseEntity;



/**
 * 机构销售费用计算规则
 * 
 * @Author chenchy
 * @Date 2015年12月25日 下午5:18:12
 */
public class CimOrgFeeRuleDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	public CimOrgFeeRuleDetail() {}
	
	private Integer feeRecordId;
	
	private String orgNumber;
	private String feeTypeCode ;
	/**
	 * 收费类型('fixed','float_fixed','propertion','year_propertion','month_amount_propertion')
	 */
	private String feeAttr;
	private BigDecimal intervalMinVal;
	private BigDecimal intervalMaxVal; 
	private String intervalUnit;
	private BigDecimal feeRatio;
	private BigDecimal feeVal;
	

	public Integer getFeeRecordId() {
		return feeRecordId;
	}
	public void setFeeRecordId(Integer feeRecordId) {
		this.feeRecordId = feeRecordId;
	}
	public String getOrgNumber() {
		return orgNumber;
	}
	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
	public String getFeeTypeCode() {
		return feeTypeCode;
	}
	public void setFeeTypeCode(String feeTypeCode) {
		this.feeTypeCode = feeTypeCode;
	}
	public String getFeeAttr() {
		return feeAttr;
	}
	public void setFeeAttr(String feeAttr) {
		this.feeAttr = feeAttr;
	}
	public BigDecimal getIntervalMinVal() {
		return intervalMinVal;
	}
	public void setIntervalMinVal(BigDecimal intervalMinVal) {
		this.intervalMinVal = intervalMinVal;
	}
	public BigDecimal getIntervalMaxVal() {
		return intervalMaxVal;
	}
	public void setIntervalMaxVal(BigDecimal intervalMaxVal) {
		this.intervalMaxVal = intervalMaxVal;
	}
	public String getIntervalUnit() {
		return intervalUnit;
	}
	public void setIntervalUnit(String intervalUnit) {
		this.intervalUnit = intervalUnit;
	}
	public BigDecimal getFeeRatio() {
		return feeRatio;
	}
	public void setFeeRatio(BigDecimal feeRatio) {
		this.feeRatio = feeRatio;
	}
	public BigDecimal getFeeVal() {
		return feeVal;
	}
	public void setFeeVal(BigDecimal feeVal) {
		this.feeVal = feeVal;
	}
	
	
	
	

}
