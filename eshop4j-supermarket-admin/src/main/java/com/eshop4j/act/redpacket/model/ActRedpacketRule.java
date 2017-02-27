package com.eshop4j.act.redpacket.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年08月19日 15:47:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActRedpacketRule implements Serializable {
	
	private static final long serialVersionUID = 8871015991395981656L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *规则编号
     */
	private String ruleId;
	
    /**
     *红包编号
     */
	private String redpacketId;
	
    /**
     *规则类型 1=生成|2=使用
     */
	private Integer ruleType;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
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
	
	public void setRuleId(String ruleId){
		this.ruleId = ruleId;
	}
	
	public String getRuleId(){
		return ruleId;
	}
	
	public void setRedpacketId(String redpacketId){
		this.redpacketId = redpacketId;
	}
	
	public String getRedpacketId(){
		return redpacketId;
	}
	
	public void setRuleType(Integer ruleType){
		this.ruleType = ruleType;
	}
	
	public Integer getRuleType(){
		return ruleType;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setOperator(String operator){
		this.operator = operator;
	}
	
	public String getOperator(){
		return operator;
	}
	
}

