package com.eshop4j.web.model.acc;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年08月26日 17:45:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AcWhiteCard implements Serializable {
	
	private static final long serialVersionUID = -8988307210705498453L;
	
    /**
     *自增id
     */
	private Integer id;
	
    /**
     *银行卡号
     */
	private String bankCard;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *备注
     */
	private String remark;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setBankCard(String bankCard){
		this.bankCard = bankCard;
	}
	
	public String getBankCard(){
		return bankCard;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
}

