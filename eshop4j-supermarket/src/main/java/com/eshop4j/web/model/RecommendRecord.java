package com.eshop4j.web.model;

import java.util.Date;

import com.eshop4j.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年12月28日 18:12:44
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class RecommendRecord extends BaseEntity {
	
	private static final long serialVersionUID = 87908125634795514L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *推荐人编号
     */
	private String userNumber;
	
    /**
     *被推荐人手机号
     */
	private String mobile;
	
    /**
     *被推荐人编号
     */
	private String customerId;
	
    /**
     *推荐类别
     */
	private Integer rcType;
	
    /**
     *推荐时间
     */
	private Date rcTime;
	
    /**
     *创建时间
     */
	private Date crtTime;
	
    /**
     *修改时间
     */
	private Date chgTime;
	
    /**
     *注册状态
     */
	private Integer status;
	
    /**
     *推荐码
     */
	private String rcCode;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setUserNumber(String userNumber){
		this.userNumber = userNumber;
	}
	
	public String getUserNumber(){
		return userNumber;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}
	
	public String getCustomerId(){
		return customerId;
	}
	
	public void setRcType(Integer rcType){
		this.rcType = rcType;
	}
	
	public Integer getRcType(){
		return rcType;
	}
	
	public void setRcTime(Date rcTime){
		this.rcTime = rcTime;
	}
	
	public Date getRcTime(){
		return rcTime;
	}
	
	public void setCrtTime(Date crtTime){
		this.crtTime = crtTime;
	}
	
	public Date getCrtTime(){
		return crtTime;
	}
	
	public void setChgTime(Date chgTime){
		this.chgTime = chgTime;
	}
	
	public Date getChgTime(){
		return chgTime;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setRcCode(String rcCode){
		this.rcCode = rcCode;
	}
	
	public String getRcCode(){
		return rcCode;
	}
	
}

