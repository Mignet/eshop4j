package com.linkwee.web.model.cim;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月20日 18:15:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimProductInvestRecord implements Serializable {
	private static final long serialVersionUID = 9185752969089546763L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *投资记录id(系统产生的唯一标识)
     */
	private String investId;
	
    /**
     *平台返回投资流水号
     */
	private String investRecordNo;
	
	/**
	 * 会话编号
	 */
	private String txId;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *产品id
     */
	private String productId;
	
    /**
     *外部产品编号
     */
	private String thirdProductId;
	
	/**
	 * 产品佣金率
	 */
	private BigDecimal productFeeRate;
	
    /**
     *购买本金
     */
	private BigDecimal investAmt;
	
    /**
     *收益
     */
	private BigDecimal profit;
	
    /**
     *精准收益
     */
	private BigDecimal accurateProfit;
	
    /**
     *计息日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date startTime;
	
    /**
     *回款日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date endTime;
	
    /**
     *状态(2=投资成功|3=回款成功)
     */
	private Byte status;
	
    /**
     *机构编码
     */
	private String platfrom;
	
	/**
	 * 是否平台首次投资
	 */
	private Integer isPlatfromFirstInvest;
	
	/**
	 * 是否首次投资 1是 0否
	 */
	private Integer isFirstInvest;
	
    /**
     *业务日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date bizTime;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *更新日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setInvestId(String investId){
		this.investId = investId;
	}
	
	public String getInvestId(){
		return investId;
	}
	
	public void setInvestRecordNo(String investRecordNo){
		this.investRecordNo = investRecordNo;
	}
	
	public String getInvestRecordNo(){
		return investRecordNo;
	}


	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setThirdProductId(String thirdProductId){
		this.thirdProductId = thirdProductId;
	}
	
	public String getThirdProductId(){
		return thirdProductId;
	}
	
	public void setInvestAmt(BigDecimal investAmt){
		this.investAmt = investAmt;
	}
	
	public BigDecimal getInvestAmt(){
		return investAmt;
	}
	
	public void setProfit(BigDecimal profit){
		this.profit = profit;
	}
	
	public BigDecimal getProfit(){
		return profit;
	}
	
	public void setAccurateProfit(BigDecimal accurateProfit){
		this.accurateProfit = accurateProfit;
	}
	
	public BigDecimal getAccurateProfit(){
		return accurateProfit;
	}
	
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	public Date getStartTime(){
		return startTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	
	public Date getEndTime(){
		return endTime;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	
	public Byte isStatus(){
		return status;
	}
	
	public void setPlatfrom(String platfrom){
		this.platfrom = platfrom;
	}
	
	public String getPlatfrom(){
		return platfrom;
	}
	
	
	public Integer getIsPlatfromFirstInvest() {
		return isPlatfromFirstInvest;
	}

	public void setIsPlatfromFirstInvest(Integer isPlatfromFirstInvest) {
		this.isPlatfromFirstInvest = isPlatfromFirstInvest;
	}

	public Integer getIsFirstInvest() {
		return isFirstInvest;
	}

	public void setIsFirstInvest(Integer isFirstInvest) {
		this.isFirstInvest = isFirstInvest;
	}

	public void setBizTime(Date bizTime){
		this.bizTime = bizTime;
	}
	
	public Date getBizTime(){
		return bizTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}

	public BigDecimal getProductFeeRate() {
		return productFeeRate;
	}

	public void setProductFeeRate(BigDecimal productFeeRate) {
		this.productFeeRate = productFeeRate;
	}
	
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
	
}

