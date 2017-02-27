package com.linkwee.web.model;



import com.linkwee.core.base.BaseEntity;
/**
 * 我的资产
 * 
 * @author xuzhao
 * @Date 2016年1月18日 下午3:44:00
 */
public class MyAssetResp extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String totalAmount;// 总资产(元)
	private String accountBalance;// 账户余额
	private String currentAmount;// 活期产品总额
	private String fixedAmount;// 固定收益产品总额
	private String floatAmount;// 浮动收益产品总额

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(String currentAmount) {
		this.currentAmount = currentAmount;
	}

	public String getFixedAmount() {
		return fixedAmount;
	}

	public void setFixedAmount(String fixedAmount) {
		this.fixedAmount = fixedAmount;
	}

	public String getFloatAmount() {
		return floatAmount;
	}

	public void setFloatAmount(String floatAmount) {
		this.floatAmount = floatAmount;
	}

}
