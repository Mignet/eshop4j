package com.eshop4j.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月01日 19:40:07
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActWheelWinningRecord implements Serializable {
	
	private static final long serialVersionUID = 5395031379381448956L;
	
    /**
     *中奖记录流水号
     */
	private Integer id;
	
    /**
     *序列号
     */
	private String wheelId;
	
    /**
     *用户编码
     */
	private String userId;
	
    /**
     *中奖投资人手机号码
     */
	private String mobile;
	
    /**
     *中奖等级
     */
	private Integer winningOrder;
	
    /**
     *等级描述
     */
	private String orderDesc;
	
    /**
     *消耗的抽奖次数
     */
	private Integer drawTimes;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date crtTime;
	
    /**
     *是否发放（0：未发放，1：已发放）
     */
	private Integer issued;
	
    /**
     *
     */
	private String extends1;
	
    /**
     *
     */
	private String extends2;
	
    /**
     *
     */
	private String extends3;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setWheelId(String wheelId){
		this.wheelId = wheelId;
	}
	
	public String getWheelId(){
		return wheelId;
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
	
	public void setWinningOrder(Integer winningOrder){
		this.winningOrder = winningOrder;
	}
	
	public Integer getWinningOrder(){
		return winningOrder;
	}
	
	public void setOrderDesc(String orderDesc){
		this.orderDesc = orderDesc;
	}
	
	public String getOrderDesc(){
		return orderDesc;
	}
	
	public void setDrawTimes(Integer drawTimes){
		this.drawTimes = drawTimes;
	}
	
	public Integer getDrawTimes(){
		return drawTimes;
	}
	
	public void setCrtTime(Date crtTime){
		this.crtTime = crtTime;
	}
	
	public Date getCrtTime(){
		return crtTime;
	}
	
	public void setIssued(Integer issued){
		this.issued = issued;
	}
	
	public Integer getIssued(){
		return issued;
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
	
	public void setExtends3(String extends3){
		this.extends3 = extends3;
	}
	
	public String getExtends3(){
		return extends3;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

