package com.eshop4j.web.model.acc;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月25日 10:14:37
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AcAccountBind implements Serializable {
	
	private static final long serialVersionUID = -7091615727957695237L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *银行卡账户ID
     */
	private String bankCardId;
	
    /**
     *客户id
     */
	private String userId;
	
    /**
     *客户姓名
     */
	private String userName;
	
    /**
     *银行预留手机号码
     */
	private String reserveMobile;
	
	 /**
     *注册手机号码
     */
	private String mobile;
	
    /**
     *银行卡号
     */
	private String bankCard;
	
    /**
     *银行编码
     */
	private String bankCode;
	
    /**
     *银行名称
     */
	private String bankName;
	
    /**
     *城市
     */
	private String city;
	
    /**
     *开户行
     */
	private String kaiHuHang;
	
    /**
     *身份证号
     */
	private String idCard;
	
    /**
     *1-理财师、2-投资端
     */
	private Integer userType;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *状态(0=未绑定，1=绑定 , 2=解绑)
     */
	private Integer status;
	
    /**
     *交易密码(md5加密字符串)
     */
	private String tranPwd;
	
	/**
     *账户总金额
     */
	private String totalAmount;
	
	/**
     *冻结金额
     */
	private String freezeAmount;
	
	/**
     *提现金额
     */
	private String withdrawAmount;
	
	/**
     *绑卡日期
     */
	private String bindDate;
	
	/**
     *每日绑卡限制次数
     */
	private Integer bindTimes;
	

	public String getBindDate() {
		return bindDate;
	}

	public void setBindDate(String bindDate) {
		this.bindDate = bindDate;
	}

	public Integer getBindTimes() {
		return bindTimes;
	}

	public void setBindTimes(Integer bindTimes) {
		this.bindTimes = bindTimes;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFreezeAmount() {
		return freezeAmount;
	}

	public void setFreezeAmount(String freezeAmount) {
		this.freezeAmount = freezeAmount;
	}

	public String getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(String withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setBankCardId(String bankCardId){
		this.bankCardId = bankCardId;
	}
	
	public String getBankCardId(){
		return bankCardId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public String getReserveMobile() {
		return reserveMobile;
	}

	public void setReserveMobile(String reserveMobile) {
		this.reserveMobile = reserveMobile;
	}

	public void setBankCard(String bankCard){
		this.bankCard = bankCard;
	}
	
	public String getBankCard(){
		return bankCard;
	}
	
	public void setBankCode(String bankCode){
		this.bankCode = bankCode;
	}
	
	public String getBankCode(){
		return bankCode;
	}
	
	public void setBankName(String bankName){
		this.bankName = bankName;
	}
	
	public String getBankName(){
		return bankName;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public String getCity(){
		return city;
	}
	
	public void setKaiHuHang(String kaiHuHang){
		this.kaiHuHang = kaiHuHang;
	}
	
	public String getKaiHuHang(){
		return kaiHuHang;
	}
	
	public void setIdCard(String idCard){
		this.idCard = idCard;
	}
	
	public String getIdCard(){
		return idCard;
	}
	
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setTranPwd(String tranPwd){
		this.tranPwd = tranPwd;
	}
	
	public String getTranPwd(){
		return tranPwd;
	}
	
}

