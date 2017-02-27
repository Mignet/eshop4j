package com.eshop4j.web.model.weixin;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年11月22日 19:39:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SmWeixinmsgTemplate implements Serializable {
	
	private static final long serialVersionUID = 8398877456337206565L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *类型1系统2个人
     */
	private Integer type;
	
    /**
     *应用类别0公共，1理财师，2投资者
     */
	private Integer appType;
	
    /**
     *模板查询key
     */
	private String temkey;
	
    /**
     *模板Id
     */
	private String templateId;
	
    /**
     *标题
     */
	private String title;
	
    /**
     *微信第一行内容
     */
	private String first;
	
    /**
     *备注
     */
	private String remark;
	
    /**
     *1可用0停用
     */
	private Integer status;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *创建人
     */
	private String createPerson;
	
    /**
     *修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date lastUpdateTime;
	
    /**
     *修改人
     */
	private String lastUpdatePerson;
	
    /**
     *案例
     */
	private String example;
	
	 /**
     *跳转 url
     */
	private String linkUrl;


	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return type;
	}
	
	public void setAppType(Integer appType){
		this.appType = appType;
	}
	
	public Integer getAppType(){
		return appType;
	}
	
	public void setTemkey(String temkey){
		this.temkey = temkey;
	}
	
	public String getTemkey(){
		return temkey;
	}
	
	public void setTemplateId(String templateId){
		this.templateId = templateId;
	}
	
	public String getTemplateId(){
		return templateId;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setFirst(String first){
		this.first = first;
	}
	
	public String getFirst(){
		return first;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setCreatePerson(String createPerson){
		this.createPerson = createPerson;
	}
	
	public String getCreatePerson(){
		return createPerson;
	}
	
	public void setLastUpdateTime(Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public Date getLastUpdateTime(){
		return lastUpdateTime;
	}
	
	public void setLastUpdatePerson(String lastUpdatePerson){
		this.lastUpdatePerson = lastUpdatePerson;
	}
	
	public String getLastUpdatePerson(){
		return lastUpdatePerson;
	}
	
	public void setExample(String example){
		this.example = example;
	}
	
	public String getExample(){
		return example;
	}
	
}

