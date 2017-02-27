package com.eshop4j.web.model.crm;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.DateUtils;

public class CfpCustomeDetailResp extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6535074673518232348L;
	
	private String number;//编号
	private String name;//姓名
	private String mobile;//手机
	private String idcrad;//身份证
	private String from;//来源
	private Date bindTime;//绑定时间
	private Double investmentTotalAmount;//投资总额
	private Integer investmentCount;//投资次数
	private Double nowInvestmentAmount;//再投金额
	private Integer nowInvestmentCount;//再投笔数
	private Double tfee;//贡献佣金
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
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
	public String getIdcrad() {
		return idcrad;
	}
	public void setIdcrad(String idcrad) {
		this.idcrad = idcrad;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	public Date getBindTime() {
		return bindTime;
	}
	
	public String getBindTimeStr() {
		return bindTime==null?"-":DateUtils.format(bindTime, DateUtils.FORMAT_LONG);
	}
	
	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}
	public Double getInvestmentTotalAmount() {
		return investmentTotalAmount;
	}
	public void setInvestmentTotalAmount(Double investmentTotalAmount) {
		this.investmentTotalAmount = investmentTotalAmount;
	}
	public Integer getInvestmentCount() {
		return investmentCount;
	}
	public void setInvestmentCount(Integer investmentCount) {
		this.investmentCount = investmentCount;
	}
	
	public Double getNowInvestmentAmount() {
		return nowInvestmentAmount;
	}
	public void setNowInvestmentAmount(Double nowInvestmentAmount) {
		this.nowInvestmentAmount = nowInvestmentAmount;
	}
	public Integer getNowInvestmentCount() {
		return nowInvestmentCount;
	}
	public void setNowInvestmentCount(Integer nowInvestmentCount) {
		this.nowInvestmentCount = nowInvestmentCount;
	}
	public Double getTfee() {
		return tfee;
	}
	public void setTfee(Double tfee) {
		this.tfee = tfee;
	}
	
}
