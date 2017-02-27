package com.linkwee.openapi.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
/**
 * 投资记录
 * @author ch
 * @serial 2016-07-25 17:32:02
 *
 */
public class InvestRecordReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2985070032905812928L;

	/**
	 * 投资编号
	 */
	@NotBlank(message="投资编号不能为空")
	@Length(min=1, max=64, message="投资编号长度必须在1-64位之间")  
	private String investId;
	
	/**
	 * 平台编号
	 */
	private String platfromId;
	
	/**
	 * 交易流水
	 */
	@Length(min=1, max=64, message="交易流水长度必须为1-64位之间")
	private String txId;
	
	/**
	 * 用户编号
	 */
	@NotBlank(message="用户编号不能为空")
	@Length(min=1, max=64, message="用户编号长度必须为1-64位之间")
	private String userId;
	
	/**
	 * 产品编号
	 */
	@NotBlank(message="产品编号不能为空")
	@Length(min=1, max=64, message="产品编号长度必须为1-64位之间")
	private String productId;
	
	
	
	/**
	 * 产品购买日期
	 */
	@NotNull(message="产品购买日期不能为空")
	private Date investStartTime;
	
	/**
	 * 产品回款日期
	 */
	@NotNull(message="产品回款日期不能为空")
	@Future
	private Date investEndTime;
	
	/**
	 * 投资金额
	 */
	@NotNull(message="投资金额不能为空")
	@Min(value=1, message="投资金额必须大于0")
	private BigDecimal investAmount;
	
	
	@NotNull(message="预期收益不能为空")
	@Min(value=0, message="预期收益必须大于等于0")
	private BigDecimal profit;
	

	

	public String getInvestId() {
		return investId;
	}




	public void setInvestId(String investId) {
		this.investId = investId;
	}




	public String getPlatfromId() {
		return platfromId;
	}




	public void setPlatfromId(String platfromId) {
		this.platfromId = platfromId;
	}


	public String getTxId() {
		return txId;
	}


	public void setTxId(String txId) {
		this.txId = txId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getProductId() {
		return productId;
	}




	public void setProductId(String productId) {
		this.productId = productId;
	}



	public Date getInvestStartTime() {
		return investStartTime;
	}




	public void setInvestStartTime(Date investStartTime) {
		this.investStartTime = investStartTime;
	}




	public Date getInvestEndTime() {
		return investEndTime;
	}




	public void setInvestEndTime(Date investEndTime) {
		this.investEndTime = investEndTime;
	}




	public BigDecimal getInvestAmount() {
		return investAmount;
	}




	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}




	public BigDecimal getProfit() {
		return profit;
	}




	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}




	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
