package com.linkwee.web.model.product;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年05月25日 20:48:54
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class ProductCate extends BaseEntity {
	
	private static final long serialVersionUID = 8662670384443636690L;
	
    /**
     *分类ID
     */
	private String cateId;
	
    /**
     *分类名称
     */
	private String cateName;
	
    /**
     *上级分类ID
     */
	private String parentCateId;
	
    /**
     *排序
     */
	private Integer orderNum;
	
    /**
     *点击次数
     */
	private Integer hitCount;
	
    /**
     *产品数量
     */
	private Integer productsCount;
	
    /**
     *子分类数量
     */
	private Integer childCount;
	
    /**
     *分类LOGO
     */
	private String cateLogo;
	
    /**
     *是否可用：0-可用 1-不可用
     */
	private String disabled;
	
    /**
     *最近修改时间
     */
	private Date modifyTime;
	
    /**
     *分类描述
     */
	private String description;
	
    /**
     *编号
     */
	private String identifier;
	
    /**
     *是否为公共分类,0-公共分类，1-非公共分类
     */
	private Integer ispublic;
	


	public void setCateId(String cateId){
		this.cateId = cateId;
	}
	
	public String getCateId(){
		return cateId;
	}
	
	public void setCateName(String cateName){
		this.cateName = cateName;
	}
	
	public String getCateName(){
		return cateName;
	}
	
	public void setParentCateId(String parentCateId){
		this.parentCateId = parentCateId;
	}
	
	public String getParentCateId(){
		return parentCateId;
	}
	
	public void setOrderNum(Integer orderNum){
		this.orderNum = orderNum;
	}
	
	public Integer getOrderNum(){
		return orderNum;
	}
	
	public void setHitCount(Integer hitCount){
		this.hitCount = hitCount;
	}
	
	public Integer getHitCount(){
		return hitCount;
	}
	
	public void setProductsCount(Integer productsCount){
		this.productsCount = productsCount;
	}
	
	public Integer getProductsCount(){
		return productsCount;
	}
	
	public void setChildCount(Integer childCount){
		this.childCount = childCount;
	}
	
	public Integer getChildCount(){
		return childCount;
	}
	
	public void setCateLogo(String cateLogo){
		this.cateLogo = cateLogo;
	}
	
	public String getCateLogo(){
		return cateLogo;
	}
	
	public void setDisabled(String disabled){
		this.disabled = disabled;
	}
	
	public String getDisabled(){
		return disabled;
	}
	
	public void setModifyTime(Date modifyTime){
		this.modifyTime = modifyTime;
	}
	
	public Date getModifyTime(){
		return modifyTime;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setIdentifier(String identifier){
		this.identifier = identifier;
	}
	
	public String getIdentifier(){
		return identifier;
	}
	
	public void setIspublic(Integer ispublic){
		this.ispublic = ispublic;
	}
	
	public Integer getIspublic(){
		return ispublic;
	}
	

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

