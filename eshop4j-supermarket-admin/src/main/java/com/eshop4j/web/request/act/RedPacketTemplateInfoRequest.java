package com.eshop4j.web.request.act;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class RedPacketTemplateInfoRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7490334549827671603L;

	private String redpacketId;
	@NotNull(message="红包类型不能为空")
	private Integer type;//红包类型 1=投资返现红包
	@NotBlank(message="红包名称不能为空")
	private String name;//红包名称
	@NotNull(message="红包金额不能为空")
	@Min(value=1,message="红包最小金额为1")
	private Double money;//红包金额
	@NotBlank(message="红包描述不能为空")
	private String remark;//红包描述
	
	private Integer day;
	
	

	@NotNull(message="金额限制不能为空")
	@Range(min=0,max=2,message="金额限制值必须为0~2之间")
	private Integer limitMoney;//0=不限 | 1=等于金额 | 2=大于金额 
	private Double investMoney;//投资金额  仅limitMoney=1有效
	
	@NotNull(message="金额限制不能为空")
	@Range(min=0,max=2,message="金额限制值必须为0~2之间") 
	private Integer investLlimit;//用户投资限制 0=不限|1=用户首投|2=平台首投
	
	@NotNull(message="产品限制不能为空")
	@Range(min=0,max=2,message="产品限制值必须为0~2之间")
	private Integer limitProduct;//0=不限|1=投资期限|2=产品编号
	private Integer relationalOperator;//0=等于|1=大于等于  仅limitInvestProduct=1有效
	private Integer deadline;//期限 仅limitInvestProduct=1有效
	
	@NotNull(message="回款最小金额不能为空")
	private BigDecimal repaymentAmt;
	@NotNull(message="回款最大金额不能为空")
	private BigDecimal maxRepaymentAmt;
	@NotNull(message="产品类型不能为空")
	private Integer productType;
	
	private String operator;
	
	
	
	public BigDecimal getMaxRepaymentAmt() {
		return maxRepaymentAmt;
	}

	public void setMaxRepaymentAmt(BigDecimal maxRepaymentAmt) {
		this.maxRepaymentAmt = maxRepaymentAmt;
	}







	public Integer getProductType() {
		return productType;
	}







	public void setProductType(Integer productType) {
		this.productType = productType;
	}







	public BigDecimal getRepaymentAmt() {
		return repaymentAmt;
	}







	public void setRepaymentAmt(BigDecimal repaymentAmt) {
		this.repaymentAmt = repaymentAmt;
	}







	public Integer getDay() {
		return day;
	}







	public void setDay(Integer day) {
		this.day = day;
	}







	







	public String getRedpacketId() {
		return redpacketId;
	}







	public void setRedpacketId(String redpacketId) {
		this.redpacketId = redpacketId;
	}







	public Integer getType() {
		return type;
	}







	public void setType(Integer type) {
		this.type = type;
	}







	public String getName() {
		return name;
	}







	public void setName(String name) {
		this.name = name;
	}







	public Double getMoney() {
		return money;
	}







	public void setMoney(Double money) {
		this.money = money;
	}







	public String getRemark() {
		return remark;
	}







	public void setRemark(String remark) {
		this.remark = remark;
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




	


	public Integer getInvestLlimit() {
		return investLlimit;
	}







	public void setInvestLlimit(Integer investLlimit) {
		this.investLlimit = investLlimit;
	}







	public Integer getLimitProduct() {
		return limitProduct;
	}







	public void setLimitProduct(Integer limitProduct) {
		this.limitProduct = limitProduct;
	}







	public Integer getRelationalOperator() {
		return relationalOperator;
	}







	public void setRelationalOperator(Integer relationalOperator) {
		this.relationalOperator = relationalOperator;
	}







	public Integer getDeadline() {
		return deadline;
	}







	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}



	public String getOperator() {
		return operator;
	}







	public void setOperator(String operator) {
		this.operator = operator;
	}







	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
	
}
