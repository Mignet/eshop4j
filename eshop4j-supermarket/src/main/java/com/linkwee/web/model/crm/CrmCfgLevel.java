package com.linkwee.web.model.crm;

import java.io.Serializable;
import java.math.BigDecimal;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年07月26日 18:27:55
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmCfgLevel implements Serializable {
	
	private static final long serialVersionUID = -9024300937962402082L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *编码 
     */
	private String code;
	
    /**
     *名称
     */
	private String name;
	
    /**
     *个人业绩（升级条件1）
     */
	private BigDecimal individualPerformance;
	
    /**
     *团队业绩（升级条件2）
     */
	private BigDecimal teamPerformance;
	
    /**
     *团队人数（升级条件2）
     */
	private Integer teamCount;
	
    /**
     *指标描述
     */
	private String targetText;
	
    /**
     *季度年化(相应级别,每个季度年化必须满足该值才能领取相应奖励）
     */
	private Integer quarterYearpurAmount;
	
    /**
     *职务津贴
     */
	private BigDecimal allowance;
	
    /**
     *排序
     */
	private Integer sort;
	


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
	
	public void setIndividualPerformance(BigDecimal individualPerformance){
		this.individualPerformance = individualPerformance;
	}
	
	public BigDecimal getIndividualPerformance(){
		return individualPerformance;
	}
	
	public void setTeamPerformance(BigDecimal teamPerformance){
		this.teamPerformance = teamPerformance;
	}
	
	public BigDecimal getTeamPerformance(){
		return teamPerformance;
	}
	
	public void setTeamCount(Integer teamCount){
		this.teamCount = teamCount;
	}
	
	public Integer getTeamCount(){
		return teamCount;
	}
	
	public void setTargetText(String targetText){
		this.targetText = targetText;
	}
	
	public String getTargetText(){
		return targetText;
	}
	
	public void setQuarterYearpurAmount(Integer quarterYearpurAmount){
		this.quarterYearpurAmount = quarterYearpurAmount;
	}
	
	public Integer getQuarterYearpurAmount(){
		return quarterYearpurAmount;
	}
	
	public void setAllowance(BigDecimal allowance){
		this.allowance = allowance;
	}
	
	public BigDecimal getAllowance(){
		return allowance;
	}
	
	public void setSort(Integer sort){
		this.sort = sort;
	}
	
	public Integer getSort(){
		return sort;
	}
	
}

