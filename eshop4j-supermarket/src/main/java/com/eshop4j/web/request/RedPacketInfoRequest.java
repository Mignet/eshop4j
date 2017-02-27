package com.eshop4j.web.request;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class RedPacketInfoRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7490334549827671603L;
	/**
	 * base info
	 */
	private Integer rtype;//红包类型 1=投资返现红包
	private String name;//红包名称
	private String remark;//红包描述
	private Double money;//红包金额
	private Date validDate;//有效日期
	private Double totalMoney;//总金额
	
	/**
	 * use rule
	 */
	private Integer limitMoney;//0=不限 | 1=限制金额
	private Double investMoney;//最大投资金额  仅limitMoney=1有效 
	//private Integer limitInvestUser;//0=不限|1=首次投资用户使用;
	
	private Integer limitInvestProduct;//0=不限|1=投资期限|2=产品编号
	private Integer operator;//0=等于|1=大于等于  仅limitInvestProduct=1有效
	private Integer deadline;//期限 仅limitInvestProduct=1有效
	
	private String pids;//产品编号 仅limitInvestProduct=3有效

	
	public RedPacketInfoRequest() {}
	
	

	public Integer getRtype() {
		return rtype==null?1:rtype;
	}



	public void setRtype(Integer rtype) {
		this.rtype = rtype;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getLimitMoney() {
		return limitMoney;
	}

	public void setLimitMoney(Integer limitMoney) {
		this.limitMoney = limitMoney;
	}

	
	public Double getInvestMoney() {
		return investMoney;
	}

	public void setInvestMoney(Double investMoney) {
		this.investMoney = investMoney;
	}

	public Integer getLimitInvestProduct() {
		return limitInvestProduct;
	}

	public void setLimitInvestProduct(Integer limitInvestProduct) {
		this.limitInvestProduct = limitInvestProduct;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public Integer getDeadline() {
		return deadline;
	}

	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}

	public String getPids() {
		return pids;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
	
}
