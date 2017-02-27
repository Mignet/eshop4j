package com.eshop4j.web.response;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.eshop4j.core.base.api.PaginatorRequest;

public class DynamicNewsResponse extends PaginatorRequest{

private static final long serialVersionUID = 7762849411193246515L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *应用端口（1：猎财大师2：T呗）
     */
	private String appType;
	
    /**
     *类型标识
     */
	private String typeCode;
	
    /**
     *类型名称
     */
	private String typeName;
	
    /**
     *图标
     */
	private String img;
	
    /**
     *标题
     */
	private String title;
	
    /**
     *摘要
     */
	private String summary;
	
    /**
     *链接
     */
	private String linkUrl;
	
    /**
     *内容
     */
	private String content;
	
    /**
     *状态:0发布,1删除2初始化类别
     */
	private Integer status;
	
    /**
     *
     */
	private String creator;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date crtTime;
	
    /**
     *显示排序
     */
	private Integer showIndex;
	
    /**
     *生效时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date validBegin;
	
    /**
     *结束时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date validEnd;
	
    /**
     *修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date modifiyTime;
	
    /**
     *默认为0，0=不置顶,1=置顶
     */
	private Short isStick;
	
    /**
     *扩张字段1
     */
	private String extends1;
	
    /**
     *扩张字段2
     */
	private String extends2;
	
    /**
     *扩张字段3
     */
	private String extends3;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setAppType(String appType){
		this.appType = appType;
	}
	
	public String getAppType(){
		return appType;
	}
	
	public void setTypeCode(String typeCode){
		this.typeCode = typeCode;
	}
	
	public String getTypeCode(){
		return typeCode;
	}
	
	public void setTypeName(String typeName){
		this.typeName = typeName;
	}
	
	public String getTypeName(){
		return typeName;
	}
	
	public void setImg(String img){
		this.img = img;
	}
	
	public String getImg(){
		return img;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setSummary(String summary){
		this.summary = summary;
	}
	
	public String getSummary(){
		return summary;
	}
	
	public void setLinkUrl(String linkUrl){
		this.linkUrl = linkUrl;
	}
	
	public String getLinkUrl(){
		return linkUrl;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	
	public String getCreator(){
		return creator;
	}
	
	public void setCrtTime(Date crtTime){
		this.crtTime = crtTime;
	}
	
	public Date getCrtTime(){
		return crtTime;
	}
	
	public void setShowIndex(Integer showIndex){
		this.showIndex = showIndex;
	}
	
	public Integer getShowIndex(){
		return showIndex;
	}
	
	public void setValidBegin(Date validBegin){
		this.validBegin = validBegin;
	}
	
	public Date getValidBegin(){
		return validBegin;
	}
	
	public void setValidEnd(Date validEnd){
		this.validEnd = validEnd;
	}
	
	public Date getValidEnd(){
		return validEnd;
	}
	
	public void setModifiyTime(Date modifiyTime){
		this.modifiyTime = modifiyTime;
	}
	
	public Date getModifiyTime(){
		return modifiyTime;
	}
		
	public void setIsStick(Short isStick){
		this.isStick = isStick;
	}
	
	public Short getIsStick(){
		return isStick;
	}
	
	public void setExtends1(String extends1){
		this.extends1 = extends1;
	}
	
	public String getExtends1(){
		return extends1;
	}
	
	public void setExtends2(String extends2){
		this.extends2 = extends2;
	}
	
	public String getExtends2(){
		return extends2;
	}
	
	public void setExtends3(String extends3){
		this.extends3 = extends3;
	}
	
	public String getExtends3(){
		return extends3;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
