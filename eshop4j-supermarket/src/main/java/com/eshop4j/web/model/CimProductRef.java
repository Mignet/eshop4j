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
 * @创建人： liqimoon
 * 
 * @创建时间：2016年10月11日 14:15:23
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimProductRef implements Serializable {
	
	private static final long serialVersionUID = -2111231498449894579L;
	
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
	
    /**
     *投资人用户中心ID
     */
	private String investorUserId;
	
    /**
     *备注
     */
	private String remarks;
	


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
	
	public void setInvestorUserId(String investorUserId){
		this.investorUserId = investorUserId;
	}
	
	public String getInvestorUserId(){
		return investorUserId;
	}
	
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}
	
	public String getRemarks(){
		return remarks;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

