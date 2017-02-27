package com.eshop4j.plugins.huafu.entity;

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
public class AcData implements Serializable {
	
	private static final long serialVersionUID = -6941427020761839097L;
    /**
     *返回数据的数量
     */
	private String recordNum;
    /**
     *返回数据的名称
     */
	private String name;
	

	private List<AcRecord> record;
	

	public List<AcRecord> getRecord() {
		return record;
	}

	public void setRecord(List<AcRecord> record) {
		this.record = record;
	}

	public String getRecordNum() {
		return recordNum;
	}

	public void setRecordNum(String recordNum) {
		this.recordNum = recordNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

