package com.linkwee.plugins.huafu.entity;

import java.io.Serializable;
import java.util.List;
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
public class AcVerifyRealName implements Serializable {
	
	private static final long serialVersionUID = -6941427020761839097L;
    /**
     *头部
     */
	private AcHeader header;
    /**
     *数据
     */
	private List<AcData> data;
	/**
     *错误返回的数据
     */
    private AcErrorCode msg;


	public AcErrorCode getMsg() {
		return msg;
	}

	public void setMsg(AcErrorCode msg) {
		this.msg = msg;
	}

	public AcHeader getHeader() {
		return header;
	}

	public void setHeader(AcHeader header) {
		this.header = header;
	}

	public List<AcData> getData() {
		return data;
	}

	public void setData(List<AcData> data) {
		this.data = data;
	}

	
}

