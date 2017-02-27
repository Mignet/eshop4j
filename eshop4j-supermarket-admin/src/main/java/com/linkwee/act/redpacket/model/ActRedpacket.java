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
 * @创建时间：2016年07月31日 13:13:55
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActRedpacket implements Serializable {
	
	private static final long serialVersionUID = 8255456010515633795L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *红包编号
     */
	private String redpacketId;
	
    /**
     *红包名称
     */
	private String name;
	
    /**
     *红包金额
     */
	private BigDecimal money;
	
    /**
     *红包类型 1=投资返现|2=现金红包|3=抵现红包
     */
	private Integer type;
	
    /**
     *红包描述
     */
	private String remark;
	
    /**
     *红包发送次数
     */
	private Integer sendCount;
	
    /**
     *是否关联活动
     */
	private Integer isActivity;
	
    /**
     *活动编码
     */
	private String activityCode;
	
    /**
     *活动名称
     */
	private String activityName;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
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
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setMoney(BigDecimal money){
		this.money = money;
	}
	
	public BigDecimal getMoney(){
		return money;
	}
	
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return type;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setSendCount(Integer sendCount){
		this.sendCount = sendCount;
	}
	
	public Integer getSendCount(){
		return sendCount;
	}
	
	public void setIsActivity(Integer isActivity){
		this.isActivity = isActivity;
	}
	
	public Integer getIsActivity(){
		return isActivity;
	}
	
	public void setActivityCode(String activityCode){
		this.activityCode = activityCode;
	}
	
	public String getActivityCode(){
		return activityCode;
	}
	
	public void setActivityName(String activityName){
		this.activityName = activityName;
	}
	
	public String getActivityName(){
		return activityName;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
	public void setOperator(String operator){
		this.operator = operator;
	}
	
	public String getOperator(){
		return operator;
	}
	
}

