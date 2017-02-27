package com.linkwee.web.model.crm;

import java.util.Date;

import com.linkwee.core.base.BaseEntity;

public class CustomerExpireRedeemResp  extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6381055503957943240L;
	
	//客户id
	private String customerId;
	//客户头像
	private String customerImg;
	//昵称
	private String customerName;
	//电话 
	private String phone;
	//购买日期
	private Date buyDate;
	//赎回日期
	private Date nearEndDate;
	//产品名称
	private String productName;
	//产品id
	private String productId;
	//年化
	private double fixRate;
	//佣金率
	private double commissionRate;
	//客户端收益
	private double earnings;
	//我的佣金
	private double commission;
	//赎回额
	private double expiRedeemMoney;
	//期限类型
	private int deadlinetype;
	//产品期限天数或月数值
	private int deadlinevalue;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerImg() {
		return customerImg;
	}
	public void setCustomerImg(String customerImg) {
		this.customerImg = customerImg;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public Date getNearEndDate() {
		return nearEndDate;
	}
	public void setNearEndDate(Date nearEndDate) {
		this.nearEndDate = nearEndDate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public double getFixRate() {
		return fixRate;
	}
	public void setFixRate(double fixRate) {
		this.fixRate = fixRate;
	}
	public double getCommissionRate() {
		return commissionRate;
	}
	public void setCommissionRate(double commissionRate) {
		this.commissionRate = commissionRate;
	}
	public double getEarnings() {
		return earnings;
	}
	public void setEarnings(double earnings) {
		this.earnings = earnings;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	public double getExpiRedeemMoney() {
		return expiRedeemMoney;
	}
	public void setExpiRedeemMoney(double expiRedeemMoney) {
		this.expiRedeemMoney = expiRedeemMoney;
	}
	public int getDeadlinetype() {
		return deadlinetype;
	}
	public void setDeadlinetype(int deadlinetype) {
		this.deadlinetype = deadlinetype;
	}
	public int getDeadlinevalue() {
		return deadlinevalue;
	}
	public void setDeadlinevalue(int deadlinevalue) {
		this.deadlinevalue = deadlinevalue;
	}

}
