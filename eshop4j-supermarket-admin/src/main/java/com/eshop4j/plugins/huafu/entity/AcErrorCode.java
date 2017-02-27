package com.eshop4j.plugins.huafu.entity;

import java.io.Serializable;
 /**
 * 
 * @描述： 实名验证
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年08月03日 14:35:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AcErrorCode implements Serializable {
	
	private static final long serialVersionUID = -6941427020761839097L;
    /**
     *错误代码描述
     */
	private String codeDesc;
	
	/**
     *错误代码
     */
	private String code;

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}

