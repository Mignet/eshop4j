package com.linkwee.api.response.acc;

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
public class CityInfoResponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 城市Id 如:4401
	 * */
	public String cityId;
	
	/**
	 * 城市名字
	 * */
	public String cityName;
	
	/**
	 * 关联省的ID
	 * */
	public String provinceId;
	

	public String getCityId() {
		return cityId;
	}



	public void setCityId(String cityId) {
		this.cityId = cityId;
	}



	public String getProvinceId() {
		return provinceId;
	}



	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}


	public String getCityName() {
		return cityName;
	}



	public void setCityName(String cityName) {
		this.cityName = cityName;
	}



	public String toString()
	{
	  return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}