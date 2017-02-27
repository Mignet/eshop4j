package com.linkwee.web.request;

import com.linkwee.core.datatable.DataTable;

public class CimProductEditDataTableRequest extends DataTable{

	/**
     *id
     */
	private Integer id;
	
    /**
     *机构编码
     */
	private String orgNumber;
	
    /**
     *产品名称
     */
	private String productName;
	
    /**
     *产品描述
     */
	private String productDesc;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setProductName(String productName){
		this.productName = productName;
	}
	
	public String getProductName(){
		return productName;
	}
	
	public void setProductDesc(String productDesc){
		this.productDesc = productDesc;
	}
	
	public String getProductDesc(){
		return productDesc;
	}
}
