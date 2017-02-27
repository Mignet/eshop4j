package com.linkwee.openapi.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
/**
 * 回款记录
 * @author ch
 * @serial 2016-07-25 17:32:02
 *
 */
public class RepaymentRecordReq implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6796152648891062972L;
	/**
	 * 回款编号
	 */
	@NotBlank(message="回款编号不能为空")
	@Length(min=1, max=64, message="回款编号长度必须为1-64位之间")
	private String repaymentId;
	/**
	 * 投资编号
	 */
	@NotBlank(message="投资编号不能为空")
	@Length(min=1, max=64, message="投资编号长度必须为1-64位之间")
	private String investId;
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
	 * 回款日期
	 */
	@NotNull(message="回款时间不能为空")
	private Date repaymentTime;
	/**
	 * 投资金额
	 */
	@NotNull(message="回款金额不能为空")
	@Min(value=0,message="回款金额必须大于0")
	private BigDecimal repaymentAmount;
	
	/**
	 * 精准收益
	 */
	@NotNull(message="投资收益不能为空")
	@Min(value=0,message="投资收益必须大于等于0")
	private BigDecimal profit;
	
	/**
	 * 回款状态 2=回款中 | 3=回款成功 |4=提前赎回部分本金
	 */
	@NotNull(message="回款状态不能为空")
	@Range(min=2,max=4,message="回款状态必须为2-4之间")
	private Integer status;

	
	public String getRepaymentId() {
		return repaymentId;
	}


	public void setRepaymentId(String repaymentId) {
		this.repaymentId = repaymentId;
	}


	public String getInvestId() {
		return investId;
	}


	public void setInvestId(String investId) {
		this.investId = investId;
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


	public Date getRepaymentTime() {
		return repaymentTime;
	}


	public void setRepaymentTime(Date repaymentTime) {
		this.repaymentTime = repaymentTime;
	}


	public BigDecimal getRepaymentAmount() {
		return repaymentAmount;
	}


	public void setRepaymentAmount(BigDecimal repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}


	public BigDecimal getProfit() {
		return profit;
	}


	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	

	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
