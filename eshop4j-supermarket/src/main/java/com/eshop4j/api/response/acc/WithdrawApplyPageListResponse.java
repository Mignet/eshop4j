package com.eshop4j.api.response.acc;

import java.io.Serializable;

import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.NumberUtils;
import com.eshop4j.core.util.WebUtil;
import com.eshop4j.web.model.acc.AcWithdrawApply;
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
public class WithdrawApplyPageListResponse implements Serializable {
	
	private static final long serialVersionUID = -2979696234831682651L;
	
	public WithdrawApplyPageListResponse(){
		
	}
	
	public WithdrawApplyPageListResponse(AcWithdrawApply obj){
		WebUtil.initObj(this,obj);
//		this.setOrderId(obj.getOrderId());
		this.setUserId(obj.getUserId());
		this.setUserName(obj.getUserName());
		this.setBisName("提现");
		this.setTransDate(DateUtils.format(obj.getBisTime(),DateUtils.FORMAT_LONG));
//		this.setTransAmount(NumberUtils.getFormat(obj.getTotalAmount(), "0.00"));
		this.setAmount(NumberUtils.getFormat(obj.getAmount(), "0.00"));
		this.setFee(obj.getFee()==null?"0":NumberUtils.getFormat(obj.getFee(), "0"));
		this.setStatus(obj.getStatus());
		this.setPaymentDate(obj.getPaymentDate());
		this.setBankName(obj.getBankName());
		this.setBankCard(encrypTion(obj.getBankCard()));
	}
	
	/**
     *提现流水号(订单号)
     */
//	private String orderId;
	
    /**
     *客户id
     */
	private String userId;
	
	/**
     *客户姓名
     */
	private String userName;
	
	/**
     *交易名称
     */
	private String bisName;
	
	/**
     *交易日期 
     */
	private String transDate;
	
	/**
     *交易金额
     */
//	private String transAmount;
	
	/**
     *提现金额
     */
	private String amount;
	
	/**
     *手续费
     */
	private String fee;
	
	/**
	 * 提现处理状态
	 * */
	private String status;
	
	/**
     * 预计到账时间
     */
	private String paymentDate;
	
	
	/**
     *银行名称
     */
	private String bankName;
	
	/**
     *银行卡号
     */
	private String bankCard;

	
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

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
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

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBisName() {
		return bisName;
	}

	public void setBisName(String bisName) {
		this.bisName = bisName;
	}
	
	public String encrypTion(String str) {
		String  copy = "**************************************";
		StringBuilder  restr = new StringBuilder();
		if(str!=null&&str.length()>7){
			int i = str.length()-7;
			restr = restr.append(str.substring(0, 3)).append(copy.substring(0, i)).append(str.substring(str.length()-4, str.length()));
			return restr.toString();
		}else{
			return str;
		}
	}
	
}

