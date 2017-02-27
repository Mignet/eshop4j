package com.linkwee.web.model;

 import com.linkwee.core.base.BaseEntity;
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
public class CfgLevel extends BaseEntity{
	private static final long serialVersionUID = -2480883602372216241L;
	
	private Integer id; //流水号
	private String code; //编码 
	private String name; //名称
	private Double target; //指标
	private String targetText; //指标描述
	private Double allowance; //职务津贴
	private Integer sort; //排序
	private String level; //理财师等级

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
	
	public void setTarget(Double target){
		this.target = target;
	}
	
	public Double getTarget(){
		return target;
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}

