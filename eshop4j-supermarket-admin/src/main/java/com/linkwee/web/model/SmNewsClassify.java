package com.linkwee.web.model;

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
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月03日 13:45:44
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SmNewsClassify implements Serializable {
	
	private static final long serialVersionUID = 1084702233719077077L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *应用端口（1：猎财大师2：T呗）
     */
	private Integer appType;
	
    /**
     *类型名称
     */
	private String name;
	
    /**
     *显示排序
     */
	private Integer showIndex;
	
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
	
	public void setAppType(Integer appType){
		this.appType = appType;
	}
	
	public Integer getAppType(){
		return appType;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setShowIndex(Integer showIndex){
		this.showIndex = showIndex;
	}
	
	public Integer getShowIndex(){
		return showIndex;
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

