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
 * @创建时间：2016年11月25日 17:32:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimProductEdit implements Serializable {
	
	private static final long serialVersionUID = 5928014783811296389L;
	
    /**
     *id
     */
	private Integer id;
	
    /**
     *机构编码
     */
	private String orgNumber;
	
    /**
     *产品名称
     */
	private String productName;
	
    /**
     *产品描述
     */
	private String productDesc;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setProductName(String productName){
		this.productName = productName;
	}
	
	public String getProductName(){
		return productName;
	}
	
	public void setProductDesc(String productDesc){
		this.productDesc = productDesc;
	}
	
	public String getProductDesc(){
		return productDesc;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

