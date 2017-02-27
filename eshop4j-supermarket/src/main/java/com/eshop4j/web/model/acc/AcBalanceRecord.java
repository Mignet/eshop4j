package com.eshop4j.web.model.acc;

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
public class AcBalanceRecord implements Serializable {
	
	private static final long serialVersionUID = -2979696234831682651L;
	
    /**
     *自增id
     */
	private Integer id;
	
    /**
     *1理财师，2投资者
     */
	private Integer userType;
	
    /**
     *客户id
     */
	private String userId;
	
    /**
     *银行卡账户ID
     */
	private String bankCardId;
	
    /**
     * 交易类型
     */
	private Integer transType;
	
	
	/**
     * 交易名称
     */
	private String typeName;
	
    /**
     * 订单号(我方生成)
     */
	private String orderId;
	
	 /**
     * 快钱交易号
     */
	private String dealId;
	
    /**
     * 交易金额
     */
	private BigDecimal transAmount;
	
	 /**
     * 手续费(提现)
     */
	private BigDecimal fee;

	
    /**
     * 交易日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date transDate;
	
    /**
     *流水号
     */
	private String serialNumber;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *创建人
     */
	private String createPerson;
	
    /**
     *最后更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date lastUpdateTime;
	
    /**
     *最后更新人
     */
	private String lastUpdatePerson;
	
    /**
     *备注
     */
	private String remark;
	
    /**
     *签名
     */
	private String signature;
	
    /**
     *扩展字段1
     */
	private String extend1;
	
    /**
     *扩展字段2
     */
	private String extend2;
	
	/**
     *用户名字
     */
	private String userName;
	
	/**
     *用户手机号
     */
	private String mobile;
	
	/**
     *创建类型 
     */
	private Integer createType;
	


	public Integer getCreateType() {
		return createType;
	}

	public void setCreateType(Integer createType) {
		this.createType = createType;
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

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setUserType(Integer userType){
		this.userType = userType;
	}
	
	public Integer getUserType(){
		return userType;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setBankCardId(String bankCardId){
		this.bankCardId = bankCardId;
	}
	
	public String getBankCardId(){
		return bankCardId;
	}
	
	public void setTransType(Integer transType){
		this.transType = transType;
	}
	
	public Integer getTransType(){
		return transType;
	}

	public void setTransDate(Date transDate){
		this.transDate = transDate;
	}
	
	public Date getTransDate(){
		return transDate;
	}
	
	public void setSerialNumber(String serialNumber){
		this.serialNumber = serialNumber;
	}
	
	public String getSerialNumber(){
		return serialNumber;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setCreatePerson(String createPerson){
		this.createPerson = createPerson;
	}
	
	public String getCreatePerson(){
		return createPerson;
	}
	
	public void setLastUpdateTime(Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public Date getLastUpdateTime(){
		return lastUpdateTime;
	}
	
	public void setLastUpdatePerson(String lastUpdatePerson){
		this.lastUpdatePerson = lastUpdatePerson;
	}
	
	public String getLastUpdatePerson(){
		return lastUpdatePerson;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setSignature(String signature){
		this.signature = signature;
	}
	
	public String getSignature(){
		return signature;
	}
	
	public void setExtend1(String extend1){
		this.extend1 = extend1;
	}
	
	public String getExtend1(){
		return extend1;
	}
	
	public void setExtend2(String extend2){
		this.extend2 = extend2;
	}
	
	public String getExtend2(){
		return extend2;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}

