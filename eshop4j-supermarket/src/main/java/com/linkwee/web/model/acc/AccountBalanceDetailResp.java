package com.linkwee.web.model.acc;

import java.io.Serializable;
import java.util.List;

import com.linkwee.api.response.acc.ProfixTypeListRespsone;
/**
* 
* @描述： 实体Bean
* 
* @创建人： chenjl
* 
* @创建时间：2016年07月22日 17:10:52
* 
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class AccountBalanceDetailResp implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
     *账户余额
     */
	private Double accountBalance;
	/**
     *账户总收益
     */
	private Double totalProfix;
	
	/**
	 * 收益
	 */
	private List<ProfixTypeListRespsone> profixList;
	


	public Double getTotalProfix() {
		return totalProfix;
	}

	public void setTotalProfix(Double totalProfix) {
		this.totalProfix = totalProfix;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ProfixTypeListRespsone> getProfixList() {
		return profixList;
	}

	public void setProfixList(List<ProfixTypeListRespsone> profixList) {
		this.profixList = profixList;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}


	

}