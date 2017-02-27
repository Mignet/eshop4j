package com.eshop4j.web.model.acc;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年08月30日 18:03:38
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AcAccountErrorLog implements Serializable {
	
	private static final long serialVersionUID = -4269635335197797864L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *批次号
     */
	private String batchNo;
	
    /**
     *类名和方法
     */
	private String classMethod;
	
    /**
     *发送参数
     */
	private String sendParm;
	
    /**
     *返回参数
     */
	private String returnParam;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *描述
     */
	private String remark;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setBatchNo(String batchNo){
		this.batchNo = batchNo;
	}
	
	public String getBatchNo(){
		return batchNo;
	}
	
	public void setClassMethod(String classMethod){
		this.classMethod = classMethod;
	}
	
	public String getClassMethod(){
		return classMethod;
	}
	
	public void setSendParm(String sendParm){
		this.sendParm = sendParm;
	}
	
	public String getSendParm(){
		return sendParm;
	}
	
	public void setReturnParam(String returnParam){
		this.returnParam = returnParam;
	}
	
	public String getReturnParam(){
		return returnParam;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
}

