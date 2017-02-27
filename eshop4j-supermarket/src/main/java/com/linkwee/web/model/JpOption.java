package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Long;
 import java.lang.String;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月26日 16:59:04
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class JpOption implements Serializable {
	
	private static final long serialVersionUID = 3127451754734603681L;
	
    /**
     *主键ID
     */
	private Long id;
	
    /**
     *配置KEY
     */
	private String optionKey;
	
    /**
     *配置内容
     */
	private String optionValue;
	


	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setOptionKey(String optionKey){
		this.optionKey = optionKey;
	}
	
	public String getOptionKey(){
		return optionKey;
	}
	
	public void setOptionValue(String optionValue){
		this.optionValue = optionValue;
	}
	
	public String getOptionValue(){
		return optionValue;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

