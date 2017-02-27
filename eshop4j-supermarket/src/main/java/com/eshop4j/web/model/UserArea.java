package com.eshop4j.web.model;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2016年03月09日 14:42:02
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class UserArea extends BaseEntity {
	
	private static final long serialVersionUID = 2028686895886781333L;
	
    /**
     *自增ID
     */
	private Integer id;
	
    /**
     *用户ID
     */
	private String customerId;
	/**
     *手机号码
     */
	private String mobile;
	
    /**
     *地区
     */
	private String area;
	
    /**
     *省份
     */
	private String province;
	
    /**
     *城市
     */
	private String city;
	
    /**
     *创建时间
     */
	private Date createTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}
	
	public String getCustomerId(){
		return customerId;
	}
	
	public void setArea(String area){
		this.area = area;
	}
	
	public String getArea(){
		return area;
	}
	
	public void setProvince(String province){
		this.province = province;
	}
	
	public String getProvince(){
		return province;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public String getCity(){
		return city;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}

