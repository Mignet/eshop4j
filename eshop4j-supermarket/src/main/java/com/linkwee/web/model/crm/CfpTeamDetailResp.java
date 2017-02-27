package com.linkwee.web.model.crm;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;

public class CfpTeamDetailResp extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7343635404882675305L;
	
	private String number;
	public String name;//名称
	private String mobile;//手机号码
	private String idcard;//身份证号码
	private String teamLevel;//团队关系
	private Date bindTime;//绑定时间
	private Double sales;//销售额
	private Integer saleCount;//销售数量
	private Double earnings;//收益额
	
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
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getTeamLevel() {
		return teamLevel;
	}
	public void setTeamLevel(String teamLevel) {
		this.teamLevel = teamLevel;
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
	public Double getSales() {
		return sales;
	}
	public void setSales(Double sales) {
		this.sales = sales;
	}
	public Integer getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}
	public Double getEarnings() {
		return earnings;
	}
	public void setEarnings(Double earnings) {
		this.earnings = earnings;
	}
	
}
