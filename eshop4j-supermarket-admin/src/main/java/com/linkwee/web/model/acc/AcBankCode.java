package com.linkwee.web.model.acc;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.Long;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月27日 10:35:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AcBankCode implements Serializable {
	
	private static final long serialVersionUID = -3090521628351465059L;
	
    /**
     *使用状态(0停用,1使用)
     */
	private Integer status;
	
    /**
     *对接机构
     */
	private String providerName;
	
    /**
     *银行Id
     */
	private Integer bankId;
	
    /**
     *银行code
     */
	private String bankCode;
	
    /**
     *银行名字
     */
	private String bankName;
	
    /**
     *
     */
	private Long recordLimitAmount;
	
    /**
     *天限制额度
     */
	private Long dayLimitAmount;
	
    /**
     *月限制额度
     */
	private Long monthLimitAmount;
	
    /**
     *银行服务电话
     */
	private String servicePhone;
	
    /**
     *备注
     */
	private String remark;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *最后更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date lastUpdateTime;
	


	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setProviderName(String providerName){
		this.providerName = providerName;
	}
	
	public String getProviderName(){
		return providerName;
	}
	
	public void setBankId(Integer bankId){
		this.bankId = bankId;
	}
	
	public Integer getBankId(){
		return bankId;
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
	
	public void setRecordLimitAmount(Long recordLimitAmount){
		this.recordLimitAmount = recordLimitAmount;
	}
	
	public Long getRecordLimitAmount(){
		return recordLimitAmount;
	}
	
	public void setDayLimitAmount(Long dayLimitAmount){
		this.dayLimitAmount = dayLimitAmount;
	}
	
	public Long getDayLimitAmount(){
		return dayLimitAmount;
	}
	
	public void setMonthLimitAmount(Long monthLimitAmount){
		this.monthLimitAmount = monthLimitAmount;
	}
	
	public Long getMonthLimitAmount(){
		return monthLimitAmount;
	}
	
	public void setServicePhone(String servicePhone){
		this.servicePhone = servicePhone;
	}
	
	public String getServicePhone(){
		return servicePhone;
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
	
	public void setLastUpdateTime(Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public Date getLastUpdateTime(){
		return lastUpdateTime;
	}
	
}

