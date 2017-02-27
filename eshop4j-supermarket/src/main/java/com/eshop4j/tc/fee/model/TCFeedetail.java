package com.eshop4j.tc.fee.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.lang.Byte;
import java.lang.Integer;
import java.lang.String;
import java.math.BigDecimal;
import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月22日 15:50:31
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class TCFeedetail implements Serializable {
	
	private static final long serialVersionUID = 8436085031821827914L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *佣金计算编号
     */
	private String bizId;
	
    /**
     *投资订单编号
     */
	private String billId;
	
    /**
     *投资用户编号
     */
	private String investorId;
	
    /**
     *获利理财师编号
     */
	private String profitCfplannerId;
	
    /**
     *原始理财师编号
     */
	private String originCfplannerId;
	
    /**
     *产品所属机构编号
     */
	private String productOrgId;
	
    /**
     *产品编号
     */
	private String productId;
	
    /**
     *购买产品金额
     */
	private BigDecimal productAmount;
	
    /**
     *年化金额
     */
	private BigDecimal yearpurAmount;
	
    /**
     *描述
     */
	private String remark;
	

	
    /**
     *佣金费率
     */
	private BigDecimal feeRate;
	
    /**
     *佣金
     */
	private BigDecimal feeAmount;
	
    /**
     *佣金类型：1001=佣金|1002=推荐津贴|1003=新人津贴|1004=理财师职位津贴|1005=合伙人职位津贴|1006=季度奖励|1007=年度分红|1008=活动奖励|1009=销售奖励
     */
	private String feeType;
	
    /**
     *结算状态：0=初始|1=结算中|2=结算成功|3=结算失败
     */
	private Byte balanceStatus;
	
    /**
     *结算单号
     */
	private String balanceNumber;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setBizId(String bizId){
		this.bizId = bizId;
	}
	
	public String getBizId(){
		return bizId;
	}
	
	public void setBillId(String billId){
		this.billId = billId;
	}
	
	public String getBillId(){
		return billId;
	}
	
	public void setInvestorId(String investorId){
		this.investorId = investorId;
	}
	
	public String getInvestorId(){
		return investorId;
	}
	
	public void setProfitCfplannerId(String profitCfplannerId){
		this.profitCfplannerId = profitCfplannerId;
	}
	
	public String getProfitCfplannerId(){
		return profitCfplannerId;
	}
	
	public void setOriginCfplannerId(String originCfplannerId){
		this.originCfplannerId = originCfplannerId;
	}
	
	public String getOriginCfplannerId(){
		return originCfplannerId;
	}
	
	public void setProductOrgId(String productOrgId){
		this.productOrgId = productOrgId;
	}
	
	public String getProductOrgId(){
		return productOrgId;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setProductAmount(BigDecimal productAmount){
		this.productAmount = productAmount;
	}
	
	public BigDecimal getProductAmount(){
		return productAmount;
	}
	
	public void setYearpurAmount(BigDecimal yearpurAmount){
		this.yearpurAmount = yearpurAmount;
	}
	
	public BigDecimal getYearpurAmount(){
		return yearpurAmount;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	
	public void setFeeRate(BigDecimal feeRate){
		this.feeRate = feeRate;
	}
	
	public BigDecimal getFeeRate(){
		return feeRate;
	}
	
	public void setFeeAmount(BigDecimal feeAmount){
		this.feeAmount = feeAmount;
	}
	
	public BigDecimal getFeeAmount(){
		return feeAmount;
	}
	
	public void setFeeType(String feeType){
		this.feeType = feeType;
	}
	
	public String getFeeType(){
		return feeType;
	}
	
	public void setBalanceStatus(Byte balanceStatus){
		this.balanceStatus = balanceStatus;
	}
	
	public Byte getBalanceStatus(){
		return balanceStatus;
	}
	
	public void setBalanceNumber(String balanceNumber){
		this.balanceNumber = balanceNumber;
	}
	
	public String getBalanceNumber(){
		return balanceNumber;
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
	
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

