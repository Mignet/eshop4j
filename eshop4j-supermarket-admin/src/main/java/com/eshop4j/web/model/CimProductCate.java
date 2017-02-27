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
 * @创建时间：2016年11月25日 11:25:46
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimProductCate implements Serializable {
	
	private static final long serialVersionUID = -353679880901793437L;
	
    /**
     *分类id
     */
	private Integer cateId;
	
    /**
     *分类名称
     */
	private String cateName;
	
    /**
     *上级分类id
     */
	private String parentCateId;
	
    /**
     *排序
     */
	private Integer orderNum;
	
    /**
     *分类logo 投资者端 
     */
	private String cateLogoInvestor;
	
    /**
     *分类logo 猎才大师
     */
	private String cateLogoChannel;
	
    /**
     *是否可用：0-可用 1-不可用
     */
	private String disabled;
	
    /**
     *最近修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
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
     *是否为公共分类,0-公共分类（投呗和猎才大师），1-仅投呗  2-仅猎才大师
     */
	private Integer ispublic;
	
    /**
     *是否需要在产品标签列表展示  0-需要  1-不需要
     */
	private Integer ifShow;
	
    /**
     *分类图片跳转链接
     */
	private String urlLink;
	
    /**
     *分类说明
     */
	private String cateDeclare;
	
    /**
     *备用字段
     */
	private Integer cateData;
	
    /**
     *主推平台
     */
	private String majorRecommendationPlatform;
	
	public void setCateId(Integer cateId){
		this.cateId = cateId;
	}
	
	public Integer getCateId(){
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
	
	public void setCateLogoInvestor(String cateLogoInvestor){
		this.cateLogoInvestor = cateLogoInvestor;
	}
	
	public String getCateLogoInvestor(){
		return cateLogoInvestor;
	}
	
	public void setCateLogoChannel(String cateLogoChannel){
		this.cateLogoChannel = cateLogoChannel;
	}
	
	public String getCateLogoChannel(){
		return cateLogoChannel;
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
	
	public void setIfShow(Integer ifShow){
		this.ifShow = ifShow;
	}
	
	public Integer getIfShow(){
		return ifShow;
	}
	
	public void setUrlLink(String urlLink){
		this.urlLink = urlLink;
	}
	
	public String getUrlLink(){
		return urlLink;
	}
	
	public void setCateDeclare(String cateDeclare){
		this.cateDeclare = cateDeclare;
	}
	
	public String getCateDeclare(){
		return cateDeclare;
	}
	
	public void setCateData(Integer cateData){
		this.cateData = cateData;
	}
	
	public Integer getCateData(){
		return cateData;
	}
	
	public void setMajorRecommendationPlatform(String majorRecommendationPlatform){
		this.majorRecommendationPlatform = majorRecommendationPlatform;
	}
	
	public String getMajorRecommendationPlatform(){
		return majorRecommendationPlatform;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

