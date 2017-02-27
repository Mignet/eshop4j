package com.eshop4j.web.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;

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
public class ChangeLcsRecord extends BaseEntity {
	
	private static final long serialVersionUID = -1706359021864014286L;
	
    /**
     *自增id
     */
	private Integer id;
	
    /**
     *创建时间
     */
	private Date createTime;
	
	/**
	 * 上级姓名
	 */
	private String lcsName;
	
	/**
	 * 上级电话
	 */
	private String lcsMobile;
	
	/**
	 * 类型
	 */
	private String type;
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLcsName() {
		return lcsName;
	}

	public void setLcsName(String lcsName) {
		this.lcsName = lcsName;
	}

	public String getLcsMobile() {
		return lcsMobile;
	}

	public void setLcsMobile(String lcsMobile) {
		this.lcsMobile = lcsMobile;
	}
}

