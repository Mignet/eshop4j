package com.eshop4j.web.model;

 import com.eshop4j.core.base.BaseEntity;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年08月10日 19:31:04
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class PartnerLevel extends BaseEntity{
	private static final long serialVersionUID = -8747394667546139108L;
	
	private Integer id; //流水号
	private String code; //编码
	private String name; //名称
	private String targetText; //指标描述
	private Double allowance; //职务津贴
	private Integer sort; //排序


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setTargetText(String targetText){
		this.targetText = targetText;
	}
	
	public String getTargetText(){
		return targetText;
	}
	
	public void setAllowance(Double allowance){
		this.allowance = allowance;
	}
	
	public Double getAllowance(){
		return allowance;
	}
	
	public void setSort(Integer sort){
		this.sort = sort;
	}
	
	public Integer getSort(){
		return sort;
	}
	
}

