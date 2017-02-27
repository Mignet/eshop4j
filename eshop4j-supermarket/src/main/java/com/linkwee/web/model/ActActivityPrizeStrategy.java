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
 * @创建时间：2016年12月09日 16:07:46
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActActivityPrizeStrategy implements Serializable {
	
	private static final long serialVersionUID = -1179238579257551164L;
	
    /**
     *策略ID
     */
	private Integer id;
	
    /**
     *活动编号
     */
	private String activityId;
	
    /**
     *类型：1,按概率；2，确定型
     */
	private Integer strategyType;
	
    /**
     *抽奖方式
     */
	private Integer prizeStyle;
	
    /**
     *发放次数
     */
	private Integer issueTimes;
	
    /**
     *奖品等级
     */
	private Integer conditionCase;
	
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
	
	public void setStrategyType(Integer strategyType){
		this.strategyType = strategyType;
	}
	
	public Integer getStrategyType(){
		return strategyType;
	}
	
	public void setPrizeStyle(Integer prizeStyle){
		this.prizeStyle = prizeStyle;
	}
	
	public Integer getPrizeStyle(){
		return prizeStyle;
	}
	
	public void setIssueTimes(Integer issueTimes){
		this.issueTimes = issueTimes;
	}
	
	public Integer getIssueTimes(){
		return issueTimes;
	}
	
	public void setConditionCase(Integer conditionCase){
		this.conditionCase = conditionCase;
	}
	
	public Integer getConditionCase(){
		return conditionCase;
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

