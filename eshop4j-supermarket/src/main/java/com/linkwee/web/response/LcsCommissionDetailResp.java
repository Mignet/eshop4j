package com.linkwee.web.response;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;

public class LcsCommissionDetailResp extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8752220886162485453L;
	private String name;//客户名称
	private String mobile;//客户手机号码
	private Double investmentAmount;//投资总额
	private Double tfee;//佣金
	private String productName;//产品名称
	private String fix;//年利率
	private Date startDate;//开始日期
	private Date endDate;//结束日期
	private Integer foptype;//投资状态
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
	public Double getInvestmentAmount() {
		return investmentAmount;
	}
	public void setInvestmentAmount(Double investmentAmount) {
		this.investmentAmount = investmentAmount;
	}
	public Double getTfee() {
		return tfee;
	}
	public void setTfee(Double tfee) {
		this.tfee = tfee;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getFix() {
		return fix;
	}
	public void setFix(String fix) {
		this.fix = fix;
	}
	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getFoptype() {
		return foptype;
	}
	public void setFoptype(Integer foptype) {
		this.foptype = foptype;
	}
	
	public String getStartDateStr() {
		return startDate==null?"-":DateUtils.format(startDate, DateUtils.FORMAT_LONG);
	}
	public String getEndDateStr() {
		return endDate==null?"-":DateUtils.format(endDate, DateUtils.FORMAT_LONG);
	}
	
	
	
}
