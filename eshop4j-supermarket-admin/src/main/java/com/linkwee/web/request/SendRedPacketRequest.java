package com.linkwee.web.request;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class SendRedPacketRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private String activityId;//活动编号
	private String redPacketTypeId;//红包类型id
	private Double money;//红包金额
	private Integer lcsType;//理财师类型 (1:所有,2:按级别,3:Excel导入)
	private String lcsLevel;//理财师级别(类型为2有效)
	private Integer day;//有效时间 day与date二选一,date指定具体日期,day指定几天后
	private Date date;//时间
	private Integer sendType; // 发放类型 (1:发放理财师  2:发放白名单表理财师)
	private List<String> cfpLevels;
	private Integer sendNums; //发放红包的数量
	private String loginUserName; //登录用户
	
	
	
	
	
	public String getLoginUserName() {
		return loginUserName;
	}




	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}




	public Integer getSendNums() {
		return sendNums;
	}




	public void setSendNums(Integer sendNums) {
		this.sendNums = sendNums;
	}




	public List<String> getCfpLevels() {
		return cfpLevels;
	}




	public void setCfpLevels(List<String> cfpLevels) {
		this.cfpLevels = cfpLevels;
	}




	public Integer getSendType() {
		return sendType;
	}




	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}




	public Integer getDay() {
		return day;
	}




	public void setDay(Integer day) {
		this.day = day;
	}




	public Date getDate() {
		return date;
	}




	public void setDate(Date date) {
		this.date = date;
	}




	public String getActivityId() {
		return activityId;
	}




	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}




	public String getRedPacketTypeId() {
		return redPacketTypeId;
	}




	public void setRedPacketTypeId(String redPacketTypeId) {
		this.redPacketTypeId = redPacketTypeId;
	}






	public Double getMoney() {
		return money;
	}




	public void setMoney(Double money) {
		this.money = money;
	}




	public Integer getLcsType() {
		return lcsType;
	}




	public void setLcsType(Integer lcsType) {
		this.lcsType = lcsType;
	}




	public String getLcsLevel() {
		return lcsLevel;
	}




	public void setLcsLevel(String lcsLevel) {
		this.lcsLevel = lcsLevel;
	}




	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
	

}
