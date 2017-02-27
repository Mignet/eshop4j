package com.eshop4j.plugins.huafu.entity;

import java.io.Serializable;
 /**
 * 
 * @描述： 信用卡验证
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年08月03日 14:35:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AcVerifyIsCredit implements Serializable {
	
	private static final long serialVersionUID = -6941427020761839097L;
    /**
     *返回结果 "查询成功"
     */
	private String reason;

	/**
     *返回结果实体
     */
    private CreditResult result;
	
    /**
     *错误代码
     */
    private String error_code;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public CreditResult getResult() {
		return result;
	}

	public void setResult(CreditResult result) {
		this.result = result;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
    
    
}

