package com.eshop4j.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.String;
 import java.math.BigDecimal;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月09日 10:46:20
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActActivityPrizeCase implements Serializable {
	
	private static final long serialVersionUID = 3138301860396756413L;
	
    /**
     *奖品等级ID
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
     *等级描述
     */
	private String caseDesc;
	
    /**
     *中奖概率（权重）
     */
	private Integer caseVariable;
	
    /**
     *类型：1,按概率算；2，确定型
     */
	private Integer caseType;
	
    /**
     *发放次数
     */
	private Integer issuedTimes;
	
    /**
     *发放该奖励消耗的抽奖机会次数
     */
	private Integer wasteTimes;
	
    /**
     *抽奖方式(抽一次，抽十次)
     */
	private Integer prizeStyle;
	
    /**
     *中奖金额
     */
	private BigDecimal prizeAmt;
	
    /**
     *奖品类型（1,现金，系统可发放（可扩展）;8888,实物、系统不可发放）
     */
	private Integer prizeType;
	
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
	
	public void setCaseDesc(String caseDesc){
		this.caseDesc = caseDesc;
	}
	
	public String getCaseDesc(){
		return caseDesc;
	}
	
	public void setCaseVariable(Integer caseVariable){
		this.caseVariable = caseVariable;
	}
	
	public Integer getCaseVariable(){
		return caseVariable;
	}
	
	public void setCaseType(Integer caseType){
		this.caseType = caseType;
	}
	
	public Integer getCaseType(){
		return caseType;
	}
	
	public void setIssuedTimes(Integer issuedTimes){
		this.issuedTimes = issuedTimes;
	}
	
	public Integer getIssuedTimes(){
		return issuedTimes;
	}
	
	public void setWasteTimes(Integer wasteTimes){
		this.wasteTimes = wasteTimes;
	}
	
	public Integer getWasteTimes(){
		return wasteTimes;
	}
	
	public void setPrizeStyle(Integer prizeStyle){
		this.prizeStyle = prizeStyle;
	}
	
	public Integer getPrizeStyle(){
		return prizeStyle;
	}
	
	public void setPrizeAmt(BigDecimal prizeAmt){
		this.prizeAmt = prizeAmt;
	}
	
	public BigDecimal getPrizeAmt(){
		return prizeAmt;
	}
	
	public void setPrizeType(Integer prizeType){
		this.prizeType = prizeType;
	}
	
	public Integer getPrizeType(){
		return prizeType;
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

