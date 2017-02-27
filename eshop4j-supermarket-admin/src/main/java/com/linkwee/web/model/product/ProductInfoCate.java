package com.linkwee.web.model.product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年05月26日 11:31:15
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class ProductInfoCate extends BaseEntity {
	
	private static final long serialVersionUID = -3009821407988631243L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *产品id
     */
	private String productId;
	
    /**
     *分类ID
     */
	private String cateId;
	
    /**
     *排序字段
     */
	private Integer sort;
	
    /**
     *
     */
	private String description;
	


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
	
	public void setCateId(String cateId){
		this.cateId = cateId;
	}
	
	public String getCateId(){
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
	

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

