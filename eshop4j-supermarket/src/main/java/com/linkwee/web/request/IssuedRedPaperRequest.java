package com.linkwee.web.request;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class IssuedRedPaperRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private String userMobile;//用户手机
	private String saleUserMobile;//理财师手机
	private Double money;//红包金额
	private Integer busType;//业务类型
	private String activityId;//活动编号
	private Integer day;//有效时间 day与date二选一,date指定具体日期,day指定几天后
	private Date date;//时间
	
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getSaleUserMobile() {
		return saleUserMobile;
	}
	public void setSaleUserMobile(String saleUserMobile) {
		this.saleUserMobile = saleUserMobile;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getBusType() {
		return busType;
	}
	public void setBusType(Integer busType) {
		this.busType = busType;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
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
	
	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
	

}
