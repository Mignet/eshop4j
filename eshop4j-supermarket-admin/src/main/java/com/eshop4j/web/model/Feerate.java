package com.eshop4j.web.model;

import java.io.Serializable;
import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年10月28日 16:33:54
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class Feerate implements Serializable {
	
	private static final long serialVersionUID = -1807547505821590642L;
	
    /**
     *
     */
	private String number;
	
    /**
     *
     */
	private String name;
	
    /**
     *
     */
	private String biznumber;
	
    /**
     *
     */
	private String productnumber;
	
    /**
     *
     */
	private String orgnumber;
	
    /**
     *
     */
	private Integer minpuramount;
	
    /**
     *
     */
	private Double feeratio;
	/**
	 * 募集期佣金率
	 */
	private Double collectRatio;
	
    /**
     *
     */
	private Byte triggertype;
	
    /**
     *
     */
	private Byte type;
	
    /**
     *
     */
	private Byte enable;
	
    /**
     *
     */
	private Date createtime;
	
    /**
     *
     */
	private Date updatetime;
	
    /**
     *
     */
	private Byte delstatus;
	


	public void setNumber(String number){
		this.number = number;
	}
	
	public String getNumber(){
		return number;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setBiznumber(String biznumber){
		this.biznumber = biznumber;
	}
	
	public String getBiznumber(){
		return biznumber;
	}
	
	public void setProductnumber(String productnumber){
		this.productnumber = productnumber;
	}
	
	public String getProductnumber(){
		return productnumber;
	}
	
	public void setOrgnumber(String orgnumber){
		this.orgnumber = orgnumber;
	}
	
	public String getOrgnumber(){
		return orgnumber;
	}
	
	public void setMinpuramount(Integer minpuramount){
		this.minpuramount = minpuramount;
	}
	
	public Integer getMinpuramount(){
		return minpuramount;
	}
	
	public void setFeeratio(Double feeratio){
		this.feeratio = feeratio;
	}
	
	public Double getFeeratio(){
		return feeratio;
	}
	
	public Double getCollectRatio() {
		return collectRatio;
	}

	public void setCollectRatio(Double collectRatio) {
		this.collectRatio = collectRatio;
	}

	public void setTriggertype(Byte triggertype){
		this.triggertype = triggertype;
	}
	
	public Byte getTriggertype(){
		return triggertype;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	
	public Byte getType(){
		return type;
	}
	
	public void setEnable(Byte enable){
		this.enable = enable;
	}
	
	public Byte getEnable(){
		return enable;
	}
	
	public void setCreatetime(Date createtime){
		this.createtime = createtime;
	}
	
	public Date getCreatetime(){
		return createtime;
	}
	
	public void setUpdatetime(Date updatetime){
		this.updatetime = updatetime;
	}
	
	public Date getUpdatetime(){
		return updatetime;
	}
	
	public void setDelstatus(Byte delstatus){
		this.delstatus = delstatus;
	}
	
	public Byte getDelstatus(){
		return delstatus;
	}
	
}

