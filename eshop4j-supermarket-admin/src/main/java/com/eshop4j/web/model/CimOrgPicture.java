package com.eshop4j.web.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月26日 16:02:33
 * 
 * Copyright (c) 深圳ESHOP有限公司-版权所有
 */
public class CimOrgPicture implements Serializable {
	
	private static final long serialVersionUID = -426521759197131250L;
	
    /**
     *
     */
	private Integer pid;
	
	/**
     *机构编码
     */
	private String orgNumber;
	
    /**
     *机构图片
     */
	private String orgPicture;
	
    /**
     *机构图片链接
     */
	private String orgPictureUrl;
	
    /**
     *机构图片类型,1-机构证件照，2-办公环境照
     */
	private Integer orgPictureType;
	
    /**
     *排序
     */
	private Integer sort;
	
    /**
     *是否有效,0-有效，1-失效
     */
	private Integer isshow;
	
    /**
     *
     */
	private String remark;
	
	/**
	 * 1:移动端图片
	 * 2:PC端图片
	 */
	private Integer source;
	
	

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public void setPid(Integer pid){
		this.pid = pid;
	}
	
	public Integer getPid(){
		return pid;
	}
	
	
	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}

	public void setOrgPicture(String orgPicture){
		this.orgPicture = orgPicture;
	}
	
	public String getOrgPicture(){
		return orgPicture;
	}
	
	public void setOrgPictureUrl(String orgPictureUrl){
		this.orgPictureUrl = orgPictureUrl;
	}
	
	public String getOrgPictureUrl(){
		return orgPictureUrl;
	}
	
	public void setOrgPictureType(Integer orgPictureType){
		this.orgPictureType = orgPictureType;
	}
	
	public Integer getOrgPictureType(){
		return orgPictureType;
	}
	
	public void setSort(Integer sort){
		this.sort = sort;
	}
	
	public Integer getSort(){
		return sort;
	}
	
	public void setIsshow(Integer isshow){
		this.isshow = isshow;
	}
	
	public Integer getIsshow(){
		return isshow;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

