package com.linkwee.web.model.cim;

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
 * @创建时间：2016年08月09日 18:26:21
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrgFeePerDay implements Serializable {
	
	private static final long serialVersionUID = -6426344976204296363L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *费用uuid
     */
	private String feedetailId;
	
   
	
    /**
     *费用产生时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date feeGenerateTime;
	
    /**
     *投资订单编号
     */
	private String investId;
	
    /**
     *计算费用天数（天）
     */
	private Integer productDeadline;
	
    /**
     *年化投资收费比例
     */
	private BigDecimal yearProportionRatio;
	/**
	 * 计算费用天数对应的产品年化额
	 */
	private BigDecimal yearAmount;
	
    /**
     *销售费用总额
     */
	private BigDecimal feeAmount;
	
    /**
     *结算状态：0=未结算|1=结算中|2=结算成功|3=结算失败
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
	
    /**
     *描述
     */
	private String remark;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setFeedetailId(String feedetailId){
		this.feedetailId = feedetailId;
	}
	
	public String getFeedetailId(){
		return feedetailId;
	}
	
	
	public void setFeeGenerateTime(Date feeGenerateTime){
		this.feeGenerateTime = feeGenerateTime;
	}
	
	public Date getFeeGenerateTime(){
		return feeGenerateTime;
	}
	
	public void setInvestId(String investId){
		this.investId = investId;
	}
	
	public String getInvestId(){
		return investId;
	}
	
	
	public void setProductDeadline(Integer productDeadline){
		this.productDeadline = productDeadline;
	}
	
	public Integer getProductDeadline(){
		return productDeadline;
	}
	
	public void setYearProportionRatio(BigDecimal yearProportionRatio){
		this.yearProportionRatio = yearProportionRatio;
	}
	
	public BigDecimal getYearProportionRatio(){
		return yearProportionRatio;
	}
	
	public void setFeeAmount(BigDecimal feeAmount){
		this.feeAmount = feeAmount;
	}
	
	public BigDecimal getFeeAmount(){
		return feeAmount;
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
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}

	public BigDecimal getYearAmount() {
		return yearAmount;
	}

	public void setYearAmount(BigDecimal yearAmount) {
		this.yearAmount = yearAmount;
	}
	
	
}

