package com.linkwee.web.model.acc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AcWithdrawApply implements Serializable {
	
	private static final long serialVersionUID = 4705570091076552752L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *提现流水号(订单号)
     */
	private String orderId;
	
    /**
     *客户id
     */
	private String userId;
	
    /**
     *客户姓名
     */
	private String userName;
	
    /**
     *手机号码
     */
	private String mobile;
	
    /**
     *银行卡账户ID
     */
	private String bankCardId;
	
    /**
     *名称
     */
	private String bisName;
	
    /**
     *提现时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date bisTime;
	
    /**
     *审核时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date confirmTime;
	
    /**
     *通知时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date noticeTime;
	
    /**
     *交易总金额，值等于 提现金额+手续费
     */
	private BigDecimal totalAmount;
	
    /**
     *提值金额
     */
	private BigDecimal amount;
	
    /**
     *手续费
     */
	private BigDecimal fee;
	
    /**
     *提值状态
     */
	private String status;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createdDate;
	
    /**
     *创建人
     */
	private String createdBy;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updatedDate;
	
    /**
     *更新人
     */
	private String updatedBy;
	
    /**
     *1-理财师、2-投资端
     */
	private Integer userType;
	
    /**
     *备注
     */
	private String remark;
	
	/**
     * 预计到账时间
     */
	private String paymentDate;
	
    /**
     *付款方费用
     */
	private BigDecimal debitCharge;
	
    /**
     *返回交易号
     */
	private String dealId;
	
    /**
     *返回代码
     */
	private String resultCode;
	
	/**
     *是否解冻(0否|1已解冻)
     */
	private String isThaw;
	
	/**
     *银行名称
     */
	private String bankName;
	
	/**
     *银行卡号
     */
	private String bankCard;
	
	/**
     *银行CODE
     */
	private String bankCode;
	
	/**
     *城市
     */
	private String city;
	
	/**
     *开户行
     */
	private String kaiHuHang;
	
	
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getKaiHuHang() {
		return kaiHuHang;
	}

	public void setKaiHuHang(String kaiHuHang) {
		this.kaiHuHang = kaiHuHang;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getIsThaw() {
		return isThaw;
	}

	public void setIsThaw(String isThaw) {
		this.isThaw = isThaw;
	}

	public BigDecimal getDebitCharge() {
		return debitCharge;
	}

	public void setDebitCharge(BigDecimal debitCharge) {
		this.debitCharge = debitCharge;
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBankCardId() {
		return bankCardId;
	}

	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}

	public String getBisName() {
		return bisName;
	}

	public void setBisName(String bisName) {
		this.bisName = bisName;
	}

	public Date getBisTime() {
		return bisTime;
	}

	public void setBisTime(Date bisTime) {
		this.bisTime = bisTime;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Date getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
}

