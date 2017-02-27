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
 * @创建时间：2016年05月25日 20:51:57
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class ProductCateSort extends BaseEntity {
	
	private static final long serialVersionUID = -1198036001542290692L;
	
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
     *应用类别:0全局，1理财师，2投资者
     */
	private Integer appType;
	


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
	
	public void setAppType(Integer appType){
		this.appType = appType;
	}
	
	public Integer getAppType(){
		return appType;
	}
	

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

