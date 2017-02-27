package com.eshop4j.tc.fee.model;

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
 * @创建时间：2016年07月29日 10:49:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class TCFeeTypeConfig implements Serializable {
	
	private static final long serialVersionUID = 1861858012147623458L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *编码
     */
	private String configCode;
	
    /**
     *名称
     */
	private String configName;
	
    /**
     *描述
     */
	private String configDesc;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setConfigCode(String configCode){
		this.configCode = configCode;
	}
	
	public String getConfigCode(){
		return configCode;
	}
	
	public void setConfigName(String configName){
		this.configName = configName;
	}
	
	public String getConfigName(){
		return configName;
	}
	
	public void setConfigDesc(String configDesc){
		this.configDesc = configDesc;
	}
	
	public String getConfigDesc(){
		return configDesc;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
}

