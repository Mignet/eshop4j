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
 * @创建时间：2016年12月04日 10:17:43
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActActivityCondition implements Serializable {
	
	private static final long serialVersionUID = -994703322307045894L;
	
    /**
     *中奖条件流水号
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
     *条件类型：1,活动次数;2,奖励条件
     */
	private Integer conditionType;
	
    /**
     *条件sql
     */
	private String conditionSql;
	
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
	
	public void setConditionType(Integer conditionType){
		this.conditionType = conditionType;
	}
	
	public Integer getConditionType(){
		return conditionType;
	}
	
	public void setConditionSql(String conditionSql){
		this.conditionSql = conditionSql;
	}
	
	public String getConditionSql(){
		return conditionSql;
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

