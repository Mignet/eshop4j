package com.linkwee.web.model.acc;

import java.io.Serializable;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月25日 10:14:37
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AcWithdrawTimes implements Serializable {
	
	private static final long serialVersionUID = -7091615727957695237L;
	
    /**
     *本月提现次数
     */
	private Integer times;
	
    /**
     *本月提现手续费
     */
	private Double fee;

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}
	
	
	
}

