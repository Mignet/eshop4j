package com.eshop4j.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年09月13日 20:10:31
 * 
 * Copyright (c) 深圳ESHOP有限公司-版权所有
 */
public class CimProductInfoCate implements Serializable {
	
	private static final long serialVersionUID = 2532955485795639366L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *产品id
     */
	private String productId;
	
    /**
     *分类id
     */
	private Integer cateId;
	
    /**
     *排序字段
     */
	private Integer sort;
	
    /**
     *
     */
	private String description;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setCateId(Integer cateId){
		this.cateId = cateId;
	}
	
	public Integer getCateId(){
		return cateId;
	}
	
	public void setSort(Integer sort){
		this.sort = sort;
	}
	
	public Integer getSort(){
		return sort;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

