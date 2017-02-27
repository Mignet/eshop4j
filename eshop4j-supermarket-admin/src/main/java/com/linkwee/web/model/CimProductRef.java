package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月21日 19:55:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimProductRef implements Serializable {
	
	private static final long serialVersionUID = -921216499437153867L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *理财师用户中心ID
     */
	private String saleUserId;
	
    /**
     *产品id
     */
	private String productId;
	
    /**
     *推荐时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date recommendTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setSaleUserId(String saleUserId){
		this.saleUserId = saleUserId;
	}
	
	public String getSaleUserId(){
		return saleUserId;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setRecommendTime(Date recommendTime){
		this.recommendTime = recommendTime;
	}
	
	public Date getRecommendTime(){
		return recommendTime;
	}
	
}

