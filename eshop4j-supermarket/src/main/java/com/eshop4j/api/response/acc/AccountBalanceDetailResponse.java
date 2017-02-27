package com.eshop4j.api.response.acc;

import java.io.Serializable;
import java.util.List;

import com.eshop4j.web.model.acc.AccountBalanceDetailResp;
import com.eshop4j.xoss.util.WebUtil;
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
public class AccountBalanceDetailResponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public AccountBalanceDetailResponse(AccountBalanceDetailResp obj) {
		WebUtil.initObj(this, obj);
		this.setProfixList(obj.getProfixList());
	}
	
	/**
     *账户余额
     */
	private String accountBalance;
	/**
     *账户总收益
     */
	private String totalProfix;
	
	/**
     *收益
     */
	private List<ProfixTypeListRespsone> profixList;


	public String getTotalProfix() {
		return totalProfix;
	}

	public void setTotalProfix(String totalProfix) {
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

	public String getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	

}