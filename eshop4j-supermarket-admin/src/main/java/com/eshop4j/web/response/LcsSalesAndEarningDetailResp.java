package com.eshop4j.web.response;

import com.eshop4j.core.base.BaseEntity;

public class LcsSalesAndEarningDetailResp extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7572953013708024747L;
	
	private String number;//理财师编号
	private String name;//理财师名称
	private String mobile;//理财师手机号码
	private Double investmentTotalAmount;//投资总额
	private Long investmentCount;//投资次数
	private Double tfee;//贡献佣金
	private Double profitTotalAmount;//活动发放奖励
	private Double recommendedAmount;//推荐佣金
	private Double amountTotal;
	//private Double accountBalance;//账户余额
	
	
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
	public Double getInvestmentTotalAmount() {
		return investmentTotalAmount;
	}
	public void setInvestmentTotalAmount(Double investmentTotalAmount) {
		this.investmentTotalAmount = investmentTotalAmount;
	}

	public Long getInvestmentCount() {
		return investmentCount;
	}

	public void setInvestmentCount(Long investmentCount) {
		this.investmentCount = investmentCount;
	}

	public Double getTfee() {
		return tfee;
	}
	public void setTfee(Double tfee) {
		this.tfee = tfee;
	}
	public Double getProfitTotalAmount() {
		return profitTotalAmount;
	}
	public void setProfitTotalAmount(Double profitTotalAmount) {
		this.profitTotalAmount = profitTotalAmount;
	}
	public Double getRecommendedAmount() {
		return recommendedAmount;
	}
	public void setRecommendedAmount(Double recommendedAmount) {
		this.recommendedAmount = recommendedAmount;
	}

	public Double getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(Double amountTotal) {
		this.amountTotal = amountTotal;
	}
/*	public Double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}*/
	
	
}
