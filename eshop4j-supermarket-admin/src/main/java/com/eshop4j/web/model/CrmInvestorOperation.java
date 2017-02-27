package com.eshop4j.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月11日 18:17:22
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmInvestorOperation implements Serializable {
	
	private static final long serialVersionUID = -8013637259459762791L;
	
    /**
     *自增主键
     */
	private Integer id;
	
    /**
     *投资用户
     */
	private String investor;
	
    /**
     *操作类型
     */
	private Integer type;
	
    /**
     *归属理财师
     */
	private String cfplanner;
	
    /**
     *操作人
     */
	private String operationAdmin;
	
    /**
     *备注
     */
	private String remarks;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date lastUpdateTime;
	
	private String cfplannerMobile;
	
	private String cfplannerName;


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setInvestor(String investor){
		this.investor = investor;
	}
	
	public String getInvestor(){
		return investor;
	}
	
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return type;
	}
	
	public void setCfplanner(String cfplanner){
		this.cfplanner = cfplanner;
	}
	
	public String getCfplanner(){
		return cfplanner;
	}
	
	public void setOperationAdmin(String operationAdmin){
		this.operationAdmin = operationAdmin;
	}
	
	public String getOperationAdmin(){
		return operationAdmin;
	}
	
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}
	
	public String getRemarks(){
		return remarks;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setLastUpdateTime(Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public Date getLastUpdateTime(){
		return lastUpdateTime;
	}

	public String getCfplannerMobile() {
		return cfplannerMobile;
	}

	public void setCfplannerMobile(String cfplannerMobile) {
		this.cfplannerMobile = cfplannerMobile;
	}

	public String getCfplannerName() {
		return cfplannerName;
	}

	public void setCfplannerName(String cfplannerName) {
		this.cfplannerName = cfplannerName;
	}
	
}

