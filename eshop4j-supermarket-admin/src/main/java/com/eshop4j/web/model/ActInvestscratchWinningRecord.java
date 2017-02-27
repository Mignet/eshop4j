package com.eshop4j.web.model;

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
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月25日 17:04:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActInvestscratchWinningRecord implements Serializable {
	
	private static final long serialVersionUID = -1907673101823752301L;
	
    /**
     *中奖记录流水号
     */
	private Integer id;
	
    /**
     *用户编码
     */
	private String userId;
	
    /**
     *中奖投资人手机号码
     */
	private String mobile;
	
    /**
     *中奖金额
     */
	private BigDecimal winningAmt;
	
    /**
     *是否已经刮开
     */
	private Integer isshaved;
	
    /**
     *中奖时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date winningTime;
	
    /**
     *
     */
	private String investscratchId;
	
    /**
     *
     */
	private String extends1;
	
    /**
     *
     */
	private String extends2;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setWinningAmt(BigDecimal winningAmt){
		this.winningAmt = winningAmt;
	}
	
	public BigDecimal getWinningAmt(){
		return winningAmt;
	}
	
	public void setIsshaved(Integer isshaved){
		this.isshaved = isshaved;
	}
	
	public Integer getIsshaved(){
		return isshaved;
	}
	
	public void setWinningTime(Date winningTime){
		this.winningTime = winningTime;
	}
	
	public Date getWinningTime(){
		return winningTime;
	}
	
	public void setInvestscratchId(String investscratchId){
		this.investscratchId = investscratchId;
	}
	
	public String getInvestscratchId(){
		return investscratchId;
	}
	
	public void setExtends1(String extends1){
		this.extends1 = extends1;
	}
	
	public String getExtends1(){
		return extends1;
	}
	
	public void setExtends2(String extends2){
		this.extends2 = extends2;
	}
	
	public String getExtends2(){
		return extends2;
	}
	
}

