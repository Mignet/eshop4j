package com.eshop4j.web.model;

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
 * @创建人： liqimoon
 * 
 * @创建时间：2016年11月10日 10:29:41
 * 
 * Copyright (c) 深圳ESHOP有限公司-版权所有
 */
public class SysGrayRelease implements Serializable {
	
	private static final long serialVersionUID = 6960085708324999265L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *产品id/活动编码
     */
	private String pid;
	
    /**
     *0:公共 1:产品 2:机构
     */
	private Integer type;
	
    /**
     *0:启用 1:停用
     */
	private Integer status;
	
    /**
     *手机号码
     */
	private String mobile;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *用户名
     */
	private String userName;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setPid(String pid){
		this.pid = pid;
	}
	
	public String getPid(){
		return pid;
	}
	
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return type;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

