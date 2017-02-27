package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Double;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月24日 14:18:12
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActivityProfit implements Serializable {
	
	private static final long serialVersionUID = 7214367966568902783L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *活动编码
     */
	private String activityCode;
	
    /**
     *理财师编码
     */
	private String userNumber;
	
    /**
     *收益类别:1红包
     */
	private String profitType;
	
    /**
     *收益
     */
	private Double profit;
	
    /**
     *发放日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date issueTime;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date crtTime;
	
    /**
     *修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date modifyTime;
	
    /**
     *交易单号
     */
	private String partnerTradeNo;
	
    /**
     *充值流水号
     */
	private String inRecordNo;
	
    /**
     *用户id
     */
	private String customerid;
	
    /**
     *
     */
	private String remark;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setActivityCode(String activityCode){
		this.activityCode = activityCode;
	}
	
	public String getActivityCode(){
		return activityCode;
	}
	
	public void setUserNumber(String userNumber){
		this.userNumber = userNumber;
	}
	
	public String getUserNumber(){
		return userNumber;
	}
	
	public void setProfitType(String profitType){
		this.profitType = profitType;
	}
	
	public String getProfitType(){
		return profitType;
	}
	
	public void setProfit(Double profit){
		this.profit = profit;
	}
	
	public Double getProfit(){
		return profit;
	}
	
	public void setIssueTime(Date issueTime){
		this.issueTime = issueTime;
	}
	
	public Date getIssueTime(){
		return issueTime;
	}
	
	public void setCrtTime(Date crtTime){
		this.crtTime = crtTime;
	}
	
	public Date getCrtTime(){
		return crtTime;
	}
	
	public void setModifyTime(Date modifyTime){
		this.modifyTime = modifyTime;
	}
	
	public Date getModifyTime(){
		return modifyTime;
	}
	
	public void setPartnerTradeNo(String partnerTradeNo){
		this.partnerTradeNo = partnerTradeNo;
	}
	
	public String getPartnerTradeNo(){
		return partnerTradeNo;
	}
	
	public void setInRecordNo(String inRecordNo){
		this.inRecordNo = inRecordNo;
	}
	
	public String getInRecordNo(){
		return inRecordNo;
	}
	
	public void setCustomerid(String customerid){
		this.customerid = customerid;
	}
	
	public String getCustomerid(){
		return customerid;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
}

