package com.linkwee.web.response;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;

public class LcsRecommendedIncomeDetailResp extends BaseEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7712963245349588136L;
	private Integer id;
	private String mobile;//手机号码
	private String name;//名称
	private String level;//级别
	private String proportion;//比例
	private String productName;//产品名称
	private Double sales;//销售额
	private Double earnings;//收益额
	private String startDate;//开始时间
	private String endDate;//结束时间
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getProportion() {
		return proportion;
	}
	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getSales() {
		return sales;
	}
	public void setSales(Double sales) {
		this.sales = sales;
	}
	public Double getEarnings() {
		return earnings;
	}
	public void setEarnings(Double earnings) {
		this.earnings = earnings;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
