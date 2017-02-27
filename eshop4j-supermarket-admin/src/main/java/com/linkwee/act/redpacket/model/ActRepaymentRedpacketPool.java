package com.linkwee.act.redpacket.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.math.BigDecimal;
import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年12月22日 22:14:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActRepaymentRedpacketPool implements Serializable {
	
private static final long serialVersionUID = 4460095456707209904L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *业务编号
     */
	private String bizId;
	
    /**
     *红包模板编号
     */
	private String redpacketTemplateId;
	
    /**
     *红包编号
     */
	private String redpacketId;
	
    /**
     *红包发放编号
     */
	private String redpacketSendId;
	
	  /**
    *
    */
	private Integer productType;
	
    /**
     *回款金额
     */
	private Long repaymentAmt;
	
	private Long maxRepaymentAmt;
	
    /**
     *平台编号
     */
	private String platformId;
	
    /**
     *主推机构
     */
	private Integer mainPlatform;
	
    /**
     *模式
     */
	private Integer model;
	
    /**
     *状态 0=新增|1=删除
     */
	private Integer status;
	
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
	

	
	
	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Long getMaxRepaymentAmt() {
		return maxRepaymentAmt;
	}

	public void setMaxRepaymentAmt(Long maxRepaymentAmt) {
		this.maxRepaymentAmt = maxRepaymentAmt;
	}

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
	
	public void setRedpacketTemplateId(String redpacketTemplateId){
		this.redpacketTemplateId = redpacketTemplateId;
	}
	
	public String getRedpacketTemplateId(){
		return redpacketTemplateId;
	}
	
	public void setRedpacketId(String redpacketId){
		this.redpacketId = redpacketId;
	}
	
	public String getRedpacketId(){
		return redpacketId;
	}
	
	public void setRedpacketSendId(String redpacketSendId){
		this.redpacketSendId = redpacketSendId;
	}
	
	public String getRedpacketSendId(){
		return redpacketSendId;
	}
	
	public void setRepaymentAmt(Long repaymentAmt){
		this.repaymentAmt = repaymentAmt;
	}
	
	public Long getRepaymentAmt(){
		return repaymentAmt;
	}
	
	public void setPlatformId(String platformId){
		this.platformId = platformId;
	}
	
	public String getPlatformId(){
		return platformId;
	}
	
	public void setMainPlatform(Integer mainPlatform){
		this.mainPlatform = mainPlatform;
	}
	
	public Integer getMainPlatform(){
		return mainPlatform;
	}
	
	public void setModel(Integer model){
		this.model = model;
	}
	
	public Integer getModel(){
		return model;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
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

