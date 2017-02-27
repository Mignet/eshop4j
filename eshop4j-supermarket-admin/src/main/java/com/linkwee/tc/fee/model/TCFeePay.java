package com.linkwee.tc.fee.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.math.BigDecimal;
import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年09月08日 16:07:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class TCFeePay implements Serializable {
	
	private static final long serialVersionUID = -6138450097481448721L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *佣金发放单据编号（理财师编码+年月）
     */
	private String billId;
	
    /**
     *部门
     */
	private String department;
	
    /**
     *理财师编码
     */
	private String userId;
	
    /**
     *理财师手机号码
     */
	private String userMobile;
	
    /**
     *理财师姓名
     */
	private String userName;
	
    /**
     *佣金
     */
	private BigDecimal feeAmount;
	
    /**
     *年月
     */
	private String month;
	
    /**
     *0:默认; 1:发放中 2:发放成功; 3:发放失败
     */
	private Integer status;
	
    /**
     *
     */
	private String resultCode;
	
    /**
     *发放结果描述
     */
	private String resultMsg;
	
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
	
	public void setBillId(String billId){
		this.billId = billId;
	}
	
	public String getBillId(){
		return billId;
	}
	
	public void setDepartment(String department){
		this.department = department;
	}
	
	public String getDepartment(){
		return department;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setUserMobile(String userMobile){
		this.userMobile = userMobile;
	}
	
	public String getUserMobile(){
		return userMobile;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setFeeAmount(BigDecimal feeAmount){
		this.feeAmount = feeAmount;
	}
	
	public BigDecimal getFeeAmount(){
		return feeAmount;
	}
	
	public void setMonth(String month){
		this.month = month;
	}
	
	public String getMonth(){
		return month;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setResultCode(String resultCode){
		this.resultCode = resultCode;
	}
	
	public String getResultCode(){
		return resultCode;
	}
	
	public void setResultMsg(String resultMsg){
		this.resultMsg = resultMsg;
	}
	
	public String getResultMsg(){
		return resultMsg;
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

