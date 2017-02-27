package com.linkwee.tc.fee.model.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * 佣金明细包装类
 * @author ch
 *
 */
public class FeedetailWrapper {
	
	private String billId; //单据编号
	private String investorId; //用户id
	private String cfplannerId; //理财师编号
	private Double ratio; // 当前理财师提成比例
	private String cfplannerRemak;//理财师佣金描述

	private String productId; //产品编码
	private String productName;//产品名称
	private String productOrgId; //产品所属机构编号
	private BigDecimal InvestmentAmount=new BigDecimal(0d); //购买金额
	private BigDecimal yearPurAmount=new BigDecimal(0d); //年化购买金额
	private Integer productDays; //产品固定期限
	private String parentCfplannerId; // 上级理财师编码
	private Double parentRatio; // 上级理财师提成比例
	private String parentCfplannerRemak;//上级理财师佣金描述
	
	private String grandParentCfplannerId; // 上上级理财师编码
	private Double grandparentratio; // 上上级理财师提成比例
	private String grandParentCfplannerRemak;//上上级理财师佣金描述
	
	private BigDecimal feeamount=new BigDecimal(0d);//佣金
	private String feetype;// 佣金类型
	
	private String remak;
	private Double curRatio; // 当前理财师提成比例
	private String curCfplannerId;//当前理财师编号
	private Date investDate = new Date();
	private Date endDate = new Date();
	
	public FeedetailWrapper() {
	}
	

	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getInvestorId() {
		return investorId;
	}
	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}
	public String getCfplannerId() {
		return cfplannerId;
	}
	public void setCfplannerId(String cfplannerId) {
		this.cfplannerId = cfplannerId;
	}
	
	public Double getCurRatio() {
		return curRatio;
	}

	public void setCurRatio(Double curRatio) {
		this.curRatio = curRatio;
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


	public String getProductOrgId() {
		return productOrgId;
	}
	public void setProductOrgId(String productOrgId) {
		this.productOrgId = productOrgId;
	}
	public BigDecimal getInvestmentAmount() {
		return InvestmentAmount;
	}
	public void setInvestmentAmount(BigDecimal investmentAmount) {
		InvestmentAmount = investmentAmount;
	}
	public BigDecimal getYearPurAmount() {
		return yearPurAmount;
	}
	public void setYearPurAmount(BigDecimal yearPurAmount) {
		this.yearPurAmount = yearPurAmount;
	}
	public Integer getProductDays() {
		return productDays;
	}
	public void setProductDays(Integer productDays) {
		this.productDays = productDays;
	}
	public String getParentCfplannerId() {
		return parentCfplannerId;
	}
	public void setParentCfplannerId(String parentCfplannerId) {
		this.parentCfplannerId = parentCfplannerId;
	}
	public Double getParentRatio() {
		return parentRatio;
	}
	public void setParentRatio(Double parentRatio) {
		this.parentRatio = parentRatio;
	}
	public String getGrandParentCfplannerId() {
		return grandParentCfplannerId;
	}
	public void setGrandParentCfplannerId(String grandParentCfplannerId) {
		this.grandParentCfplannerId = grandParentCfplannerId;
	}
	public Double getGrandparentratio() {
		return grandparentratio;
	}
	public void setGrandparentratio(Double grandparentratio) {
		this.grandparentratio = grandparentratio;
	}
	
	public BigDecimal getFeeamount() {
		return feeamount;
	}

	public void setFeeamount(BigDecimal feeamount) {
		this.feeamount = feeamount;
	}

	public String getFeetype() {
		return feetype;
	}

	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}
	
	
	public String getRemak() {
		return remak;
	}

	public void setRemak(String remak) {
		this.remak = remak;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public String getCurCfplannerId() {
		return curCfplannerId;
	}

	public void setCurCfplannerId(String curCfplannerId) {
		this.curCfplannerId = curCfplannerId;
	}
	
	

	public String getCfplannerRemak() {
		return cfplannerRemak;
	}

	public void setCfplannerRemak(String cfplannerRemak) {
		this.cfplannerRemak = cfplannerRemak;
	}

	public String getParentCfplannerRemak() {
		return parentCfplannerRemak;
	}

	public void setParentCfplannerRemak(String parentCfplannerRemak) {
		this.parentCfplannerRemak = parentCfplannerRemak;
	}

	public String getGrandParentCfplannerRemak() {
		return grandParentCfplannerRemak;
	}

	public void setGrandParentCfplannerRemak(String grandParentCfplannerRemak) {
		this.grandParentCfplannerRemak = grandParentCfplannerRemak;
	}
	
	public Date getInvestDate() {
		return investDate;
	}


	public void setInvestDate(Date investDate) {
		this.investDate = investDate;
	}

	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
