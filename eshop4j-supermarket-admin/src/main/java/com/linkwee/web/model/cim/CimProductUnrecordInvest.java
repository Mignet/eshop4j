package com.linkwee.web.model.cim;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
 * @创建时间：2016年10月20日 14:50:43
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimProductUnrecordInvest implements Serializable {
	
	private static final long serialVersionUID = -3060677543443250121L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *理财师编号
     */
	private String cfplannerId;
	
    /**
     *用户编号
     */
	private String userId;
	
    /**
     *用户手机
     */
	private String userMobile;
	
    /**
     *用户姓名
     */
	private String userName;
	
    /**
     *平台编号
     */
	private String platfrom;
	
    /**
     *平台名称
     */
	private String platfromName;
	
    /**
     *投资产品
     */
	private String productName;
	
    /**
     *产品期限类型 : 1=天|2=月|3=年
     */
	private Integer productDeadLineType;
	
    /**
     *产品期限（天数）
     */
	private Integer productDeadLineValue;
	
    /**
     *产品期限
     */
	private String productDeadLine;
	
    /**
     *佣金率
     */
	private BigDecimal feeRate;
	
    /**
     *佣金
     */
	private BigDecimal feeAmt;
	
    /**
     *投资编号
     */
	private String investId;
	
    /**
     *投资金额
     */
	private BigDecimal investAmt;
	
    /**
     *投资时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date investTime;
	
    /**
     *回款时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date endTime;
	
    /**
     *投资截图
     */
	private String investImg;
	
    /**
     *状态 0=未审核|1=审核通过|2=审核不通过
     */
	private Integer status;
	
    /**
     *描述
     */
	private String remark;
	
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
     *操作人
     */
	private String operator;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setCfplannerId(String cfplannerId){
		this.cfplannerId = cfplannerId;
	}
	
	public String getCfplannerId(){
		return cfplannerId;
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
	
	public void setPlatfrom(String platfrom){
		this.platfrom = platfrom;
	}
	
	public String getPlatfrom(){
		return platfrom;
	}
	
	public void setPlatfromName(String platfromName){
		this.platfromName = platfromName;
	}
	
	public String getPlatfromName(){
		return platfromName;
	}
	
	public void setProductName(String productName){
		this.productName = productName;
	}
	
	public String getProductName(){
		return productName;
	}
	
	public void setProductDeadLineType(Integer productDeadLineType){
		this.productDeadLineType = productDeadLineType;
	}
	
	public Integer getProductDeadLineType(){
		return productDeadLineType;
	}
	
	public void setProductDeadLineValue(Integer productDeadLineValue){
		this.productDeadLineValue = productDeadLineValue;
	}
	
	public Integer getProductDeadLineValue(){
		return productDeadLineValue;
	}
	
	public void setProductDeadLine(String productDeadLine){
		this.productDeadLine = productDeadLine;
	}
	
	public String getProductDeadLine(){
		return productDeadLine;
	}
	
	public void setFeeRate(BigDecimal feeRate){
		this.feeRate = feeRate;
	}
	
	public BigDecimal getFeeRate(){
		return feeRate;
	}
	
	public void setFeeAmt(BigDecimal feeAmt){
		this.feeAmt = feeAmt;
	}
	
	public BigDecimal getFeeAmt(){
		return feeAmt;
	}
	
	public void setInvestId(String investId){
		this.investId = investId;
	}
	
	public String getInvestId(){
		return investId;
	}
	
	public void setInvestAmt(BigDecimal investAmt){
		this.investAmt = investAmt;
	}
	
	public BigDecimal getInvestAmt(){
		return investAmt;
	}
	
	public void setInvestTime(Date investTime){
		this.investTime = investTime;
	}
	
	public Date getInvestTime(){
		return investTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	
	public Date getEndTime(){
		return endTime;
	}
	
	public void setInvestImg(String investImg){
		this.investImg = investImg;
	}
	
	public String getInvestImg(){
		return investImg;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
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
	
	public void setOperator(String operator){
		this.operator = operator;
	}
	
	public String getOperator(){
		return operator;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

