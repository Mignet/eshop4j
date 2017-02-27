package com.eshop4j.api.response.acc;

import java.io.Serializable;
import java.util.List;
/**
* 
* @描述： 月份收益统计
* 
* @创建人： chenjl
* 
* @创建时间：2016年07月22日 17:10:52
* 
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class MonthProfixStatisticsResponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
     *账户总收益
     */
	private String totalProfix;
	
	/**
	 * 是否发放 1已发放，0未发放
	 */
	private String isGrant;
	
	/**
     *发放描述
     */
	private String grantDesc;
	
	/**
	 * 收益
	 */
	private List<ProfixTypeListRespsone> profixList;

	public String getTotalProfix() {
		return totalProfix;
	}

	public void setTotalProfix(String totalProfix) {
		this.totalProfix = totalProfix;
	}


	public String getIsGrant() {
		return isGrant;
	}

	public void setIsGrant(String isGrant) {
		this.isGrant = isGrant;
	}

	public String getGrantDesc() {
		return grantDesc;
	}

	public void setGrantDesc(String grantDesc) {
		this.grantDesc = grantDesc;
	}

	public List<ProfixTypeListRespsone> getProfixList() {
		return profixList;
	}

	public void setProfixList(List<ProfixTypeListRespsone> profixList) {
		this.profixList = profixList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}