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
 * @创建时间：2016年08月03日 14:35:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AcAccountType implements Serializable {
	
	private static final long serialVersionUID = -6941427020761839097L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *账户类型名称
     */
	private String typeName;
	
    /**
     *账户类型(1=全部明细|2=提现|3=活动奖励|4=红包|5=其他)
     */
	private Integer typeValue;
	
    /**
     *1=正常|2=已删除
     */
	private Integer status;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *备注
     */
	private String remark;
	
	 /**
     *用户类型 1-理财师、2-投资者
     */
	private String userType;
	

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setTypeName(String typeName){
		this.typeName = typeName;
	}
	
	public String getTypeName(){
		return typeName;
	}
	
	public void setTypeValue(Integer typeValue){
		this.typeValue = typeValue;
	}
	
	public Integer getTypeValue(){
		return typeValue;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
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

