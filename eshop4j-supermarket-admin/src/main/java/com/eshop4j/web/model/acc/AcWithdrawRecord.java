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
public class AcWithdrawRecord implements Serializable {
	
	private static final long serialVersionUID = 8149850021095018280L;
	
    /**
     *自增id
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
     *请求时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date requestTime;
	
    /**
     *通知时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date noticeTime;
	
    /**
     *状态
     */
	private String status;
	
    /**
     *返回交易号
     */
	private String dealId;
	
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
     *付款方费用
     */
	private BigDecimal debitCharge;
	
    /**
     *返回代码
     */
	private String resultCode;
	
    /**
     *银行执行结果(0失败,1成功)
     */
	private String resultFlag;
	
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
	private int userType;
	
    /**
     *备注
     */
	private String remark;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setOrderId(String orderId){
		this.orderId = orderId;
	}
	
	public String getOrderId(){
		return orderId;
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
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setBankCardId(String bankCardId){
		this.bankCardId = bankCardId;
	}
	
	public String getBankCardId(){
		return bankCardId;
	}
	
	public void setBisName(String bisName){
		this.bisName = bisName;
	}
	
	public String getBisName(){
		return bisName;
	}
	
	public void setBisTime(Date bisTime){
		this.bisTime = bisTime;
	}
	
	public Date getBisTime(){
		return bisTime;
	}
	
	public void setRequestTime(Date requestTime){
		this.requestTime = requestTime;
	}
	
	public Date getRequestTime(){
		return requestTime;
	}
	
	public void setNoticeTime(Date noticeTime){
		this.noticeTime = noticeTime;
	}
	
	public Date getNoticeTime(){
		return noticeTime;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setDealId(String dealId){
		this.dealId = dealId;
	}
	
	public String getDealId(){
		return dealId;
	}
	
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getDebitCharge() {
		return debitCharge;
	}

	public void setDebitCharge(BigDecimal debitCharge) {
		this.debitCharge = debitCharge;
	}

	public void setResultCode(String resultCode){
		this.resultCode = resultCode;
	}
	
	public String getResultCode(){
		return resultCode;
	}
	
	public void setResultFlag(String resultFlag){
		this.resultFlag = resultFlag;
	}
	
	public String getResultFlag(){
		return resultFlag;
	}
	
	public void setCreatedDate(Date createdDate){
		this.createdDate = createdDate;
	}
	
	public Date getCreatedDate(){
		return createdDate;
	}
	
	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}
	
	public String getCreatedBy(){
		return createdBy;
	}
	
	public void setUpdatedDate(Date updatedDate){
		this.updatedDate = updatedDate;
	}
	
	public Date getUpdatedDate(){
		return updatedDate;
	}
	
	public void setUpdatedBy(String updatedBy){
		this.updatedBy = updatedBy;
	}
	
	public String getUpdatedBy(){
		return updatedBy;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
	
}

