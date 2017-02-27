package com.linkwee.web.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;

import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2016年05月26日 16:26:48
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class ChangeParentRecord extends BaseEntity {
	
	private static final long serialVersionUID = -1706359021864014286L;
	
    /**
     *自增id
     */
	private Integer id;
	
    /**
     *理财师编码
     */
	private String number;
	
    /**
     *操作人id
     */
	private String operationId;
	
    /**
     *上级理财师编码
     */
	private String parentId;
	
    /**
     *创建时间
     */
	private Date createTime;
	
    /**
     *最后更新时间
     */
	private Date lastUpdateTime;
	
    /**
     *操作类型 1绑定 2解除
     */
	private Integer type;
	
	/**
	 * 上级姓名
	 */
	private String parentName;
	
	/**
	 * 上级电话
	 */
	private String parentMobile;
	
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setNumber(String number){
		this.number = number;
	}
	
	public String getNumber(){
		return number;
	}
	
	public void setOperationId(String operationId){
		this.operationId = operationId;
	}
	
	public String getOperationId(){
		return operationId;
	}
	
	public void setParentId(String parentId){
		this.parentId = parentId;
	}
	
	public String getParentId(){
		return parentId;
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
	
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return type;
	}
	

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentMobile() {
		return parentMobile;
	}

	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}
}

