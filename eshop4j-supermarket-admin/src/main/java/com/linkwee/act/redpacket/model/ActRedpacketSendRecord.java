package com.linkwee.act.redpacket.model;

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
 * @创建时间：2016年08月08日 10:36:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActRedpacketSendRecord implements Serializable {
	
	private static final long serialVersionUID = 5456456918620948700L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *红包编号
     */
	private String redpacketId;
	
    /**
     *发放编号
     */
	private String sendId;
	
    /**
     *过期时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date expiresTime;
	
	/**
     *过期日期
     */
	private Integer expiresDay;
	
    /**
     *发放红包数量
     */
	private Integer sendCount;
	
    /**
     *发放金额
     */
	private BigDecimal sendMoney;
	
    /**
     *发放人数
     */
	private Integer sendNumber;
	
    /**
     *发放时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date sendTime;
	
    /**
     *过期状态 0=未过期|1=过期
     */
	private Integer expiresStatus;
	
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
	
	public void setRedpacketId(String redpacketId){
		this.redpacketId = redpacketId;
	}
	
	public String getRedpacketId(){
		return redpacketId;
	}
	
	public void setSendId(String sendId){
		this.sendId = sendId;
	}
	
	public String getSendId(){
		return sendId;
	}
	
	
	public Integer getExpiresDay() {
		return expiresDay;
	}

	public void setExpiresDay(Integer expiresDay) {
		this.expiresDay = expiresDay;
	}

	public void setExpiresTime(Date expiresTime){
		this.expiresTime = expiresTime;
	}
	
	public Date getExpiresTime(){
		return expiresTime;
	}
	
	public void setSendCount(Integer sendCount){
		this.sendCount = sendCount;
	}
	
	public Integer getSendCount(){
		return sendCount;
	}
	
	public void setSendMoney(BigDecimal sendMoney){
		this.sendMoney = sendMoney;
	}
	
	public BigDecimal getSendMoney(){
		return sendMoney;
	}
	
	public void setSendNumber(Integer sendNumber){
		this.sendNumber = sendNumber;
	}
	
	public Integer getSendNumber(){
		return sendNumber;
	}
	
	public void setSendTime(Date sendTime){
		this.sendTime = sendTime;
	}
	
	public Date getSendTime(){
		return sendTime;
	}
	
	public void setExpiresStatus(Integer expiresStatus){
		this.expiresStatus = expiresStatus;
	}
	
	public Integer getExpiresStatus(){
		return expiresStatus;
	}
	
	public void setOperator(String operator){
		this.operator = operator;
	}
	
	public String getOperator(){
		return operator;
	}
	
}

