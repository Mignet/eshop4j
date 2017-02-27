package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.String;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月07日 21:30:33
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActActivityPrizeIssue implements Serializable {
	
	private static final long serialVersionUID = 3800488933015332351L;
	
    /**
     *结果流水号
     */
	private Integer id;
	
    /**
     *活动编号
     */
	private String activityId;
	
    /**
     *条件等级
     */
	private Integer conditionCase;
	
    /**
     *奖品等级
     */
	private Integer prizeCase;
	
    /**
     *结果sql
     */
	private String resultSql;
	
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
	
	public void setActivityId(String activityId){
		this.activityId = activityId;
	}
	
	public String getActivityId(){
		return activityId;
	}
	
	public void setConditionCase(Integer conditionCase){
		this.conditionCase = conditionCase;
	}
	
	public Integer getConditionCase(){
		return conditionCase;
	}
	
	public void setPrizeCase(Integer prizeCase){
		this.prizeCase = prizeCase;
	}
	
	public Integer getPrizeCase(){
		return prizeCase;
	}
	
	public void setResultSql(String resultSql){
		this.resultSql = resultSql;
	}
	
	public String getResultSql(){
		return resultSql;
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

