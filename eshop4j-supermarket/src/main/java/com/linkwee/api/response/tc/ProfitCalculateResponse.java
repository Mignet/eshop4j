package com.linkwee.api.response.tc;

import java.io.Serializable;


/**
 * 理财师佣金收益计算
 * 
 * @Author ch
 * 
 * @serial 2016-07-25 15:35:17
 */
public class ProfitCalculateResponse  implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 305958897392696937L;
	/**
	 * 收益类别
	 */
	private String profiltType;
	/**
	 * 收益名称
	 */
	private String profiltName;
	/**
	 * 收益值
	 */
	private String profiltValue;
	
	private Boolean isEdit;
	
	
	public ProfitCalculateResponse() {}

	
	public ProfitCalculateResponse(String profiltType,String profiltName,String profiltValue) {
		this(profiltType,profiltName,profiltValue,false);
	}
	
	public ProfitCalculateResponse(String profiltType,String profiltName,String profiltValue,Boolean isEdit) {
		this.profiltType=profiltType;
		this.profiltName=profiltName;
		this.profiltValue=profiltValue;
		this.isEdit=isEdit;
	}

	public String getProfiltType() {
		return profiltType;
	}

	public void setProfiltType(String profiltType) {
		this.profiltType = profiltType;
	}

	public String getProfiltName() {
		return profiltName;
	}

	public void setProfiltName(String profiltName) {
		this.profiltName = profiltName;
	}

	public String getProfiltValue() {
		return profiltValue;
	}

	public void setProfiltValue(String profiltValue) {
		this.profiltValue = profiltValue;
	}


	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}
	
	
}
