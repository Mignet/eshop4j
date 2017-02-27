package com.eshop4j.api.response.acc;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
* 
* @描述： 实体Bean
* 
* @创建人： chenjl
* 
* @创建时间：2016年07月22日 17:10:52
* 
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class ProvinceInfoResponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 省份代码 如:44
	 * */
	public String provinceId;
	
	/**
	 * 省份简称 如:粤
	 * */
	public String provinceCode;
	
	/**
	 * 省份名称
	 * */
	public String provinceName;
	
	
	
	public String getProvinceId() {
		return provinceId;
	}



	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}



	public String getProvinceCode() {
		return provinceCode;
	}



	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}



	public String getProvinceName() {
		return provinceName;
	}



	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}



	public String toString()
	{
	  return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}