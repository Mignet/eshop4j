package com.eshop4j.web.model;

import java.io.Serializable;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 18:51:52
 * 
 * Copyright (c) 深圳ESHOP有限公司-版权所有
 */
public class CimOrgUrl implements Serializable {
	
	private static final long serialVersionUID = -7916761927781411478L;
	
   
	/**
	 * 平台产品跳转地址
	 */
	private String orgProductUrl;
	/**
	 * 平台用户中心跳转地址
	 */
	private String orgUsercenterUrl;
	
	public String getOrgProductUrl() {
		return orgProductUrl;
	}
	public void setOrgProductUrl(String orgProductUrl) {
		this.orgProductUrl = orgProductUrl;
	}
	public String getOrgUsercenterUrl() {
		return orgUsercenterUrl;
	}
	public void setOrgUsercenterUrl(String orgUsercenterUrl) {
		this.orgUsercenterUrl = orgUsercenterUrl;
	}
	

}

