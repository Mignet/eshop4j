package com.eshop4j.web.response;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.DateUtils;

public class LcsCustomerInvestmentDetailResp extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6547860677217594584L;
	private String name;//名称
	private Double investmentTotalAmount;//投资金额
	private String productName;//产品名称
	private Double tfee;//贡献佣金
	private Date startDate;//开始日期
	private Date endDate;//结束日期
	private String deadLine;//投资期限
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getInvestmentTotalAmount() {
		return investmentTotalAmount;
	}
	public void setInvestmentTotalAmount(Double investmentTotalAmount) {
		this.investmentTotalAmount = investmentTotalAmount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getTfee() {
		return tfee;
	}
	public void setTfee(Double tfee) {
		this.tfee = tfee;
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
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	
	
	public String getStartDateStr() {
		return startDate==null?"-":DateUtils.format(startDate, DateUtils.FORMAT_LONG);
	}
	public String getEndDateStr() {
		return endDate==null?"-":DateUtils.format(endDate, DateUtils.FORMAT_LONG);
	}
	
	
}
