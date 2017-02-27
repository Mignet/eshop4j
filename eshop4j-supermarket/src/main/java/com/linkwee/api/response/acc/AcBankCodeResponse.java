package com.linkwee.api.response.acc;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月27日 10:35:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AcBankCodeResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
     *银行Id
     */
	private Integer bankId;
	
    /**
     *银行code
     */
	private String bankCode;
	
    /**
     *银行名字
     */
	private String bankName;

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public String toString()
	{
	  return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}

