package com.eshop4j.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.Long;
 import java.lang.String;
 import java.math.BigDecimal;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月31日 19:12:53
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class JpTaxonomy implements Serializable {
	
	private static final long serialVersionUID = -5199892366324688194L;
	
    /**
     *主键ID
     */
	private Long id;
	
    /**
     *标题
     */
	private String title;
	
    /**
     *内容描述
     */
	private String text;
	
    /**
     *slug
     */
	private String slug;
	
    /**
     *类型
     */
	private String type;
	
    /**
     *图标
     */
	private String icon;
	
    /**
     *对于的内容模型
     */
	private String contentModule;
	
    /**
     *该分类的内容数量
     */
	private Integer contentCount;
	
    /**
     *排序编码
     */
	private Integer orderNumber;
	
    /**
     *父级分类的ID
     */
	private Long parentId;
	
    /**
     *关联的对象ID
     */
	private Long objectId;
	
    /**
     *标识
     */
	private String flag;
	
    /**
     *经度
     */
	private BigDecimal lat;
	
    /**
     *纬度
     */
	private BigDecimal lng;
	
    /**
     *SEO关键字
     */
	private String metaKeywords;
	
    /**
     *SEO描述内容
     */
	private String metaDescription;
	
    /**
     *创建日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date created;
	


	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
	
	public void setSlug(String slug){
		this.slug = slug;
	}
	
	public String getSlug(){
		return slug;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
	
	public void setIcon(String icon){
		this.icon = icon;
	}
	
	public String getIcon(){
		return icon;
	}
	
	public void setContentModule(String contentModule){
		this.contentModule = contentModule;
	}
	
	public String getContentModule(){
		return contentModule;
	}
	
	public void setContentCount(Integer contentCount){
		this.contentCount = contentCount;
	}
	
	public Integer getContentCount(){
		return contentCount;
	}
	
	public void setOrderNumber(Integer orderNumber){
		this.orderNumber = orderNumber;
	}
	
	public Integer getOrderNumber(){
		return orderNumber;
	}
	
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}
	
	public Long getParentId(){
		return parentId;
	}
	
	public void setObjectId(Long objectId){
		this.objectId = objectId;
	}
	
	public Long getObjectId(){
		return objectId;
	}
	
	public void setFlag(String flag){
		this.flag = flag;
	}
	
	public String getFlag(){
		return flag;
	}
	
	public void setLat(BigDecimal lat){
		this.lat = lat;
	}
	
	public BigDecimal getLat(){
		return lat;
	}
	
	public void setLng(BigDecimal lng){
		this.lng = lng;
	}
	
	public BigDecimal getLng(){
		return lng;
	}
	
	public void setMetaKeywords(String metaKeywords){
		this.metaKeywords = metaKeywords;
	}
	
	public String getMetaKeywords(){
		return metaKeywords;
	}
	
	public void setMetaDescription(String metaDescription){
		this.metaDescription = metaDescription;
	}
	
	public String getMetaDescription(){
		return metaDescription;
	}
	
	public void setCreated(Date created){
		this.created = created;
	}
	
	public Date getCreated(){
		return created;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

