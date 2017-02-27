package com.linkwee.api.request;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.base.api.PaginatorRequest;

/**
 * 
 * @描述：系统配置
 *
 * @author hxb
 * @时间 2015年8月6日上午10:23:39
 *
 */
public class SysConfigRequest extends PaginatorRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8636580345007432075L;

	/**
     *流水号
     */
	private Integer id;
	
    /**
     *名称
     */
	private String configName;
	
    /**
     *类别
     */
	private String configType;
	
    /**
     *键
     */
	private String configKey;
	
    /**
     *值
     */
	private String configValue;
	
    /**
     *备注
     */
	private String remark;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date crtTime;
	
    /**
     *应用类别:0全局，1理财师，2投资者
     */
	private Integer appType;
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getConfigName() {
		return configName;
	}


	public void setConfigName(String configName) {
		this.configName = configName;
	}


	public String getConfigType() {
		return configType;
	}


	public void setConfigType(String configType) {
		this.configType = configType;
	}


	public String getConfigKey() {
		return configKey;
	}


	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}


	public String getConfigValue() {
		return configValue;
	}


	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Date getCrtTime() {
		return crtTime;
	}


	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}


	public Integer getAppType() {
		return appType;
	}


	public void setAppType(Integer appType) {
		this.appType = appType;
	}


	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
