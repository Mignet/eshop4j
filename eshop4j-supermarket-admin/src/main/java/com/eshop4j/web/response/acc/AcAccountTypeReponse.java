package com.eshop4j.web.response.acc;

import java.io.Serializable;
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
public class AcAccountTypeReponse implements Serializable {
	
	private static final long serialVersionUID = -6941427020761839097L;
	
    /**
     *账户类型名称
     */
	private String typeName;
	
    /**
     *账户类型(1=全部明细|2=提现|3=活动奖励|4=红包|5=其他)
     */
	private Integer typeValue;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(Integer typeValue) {
		this.typeValue = typeValue;
	}
	
   
}

