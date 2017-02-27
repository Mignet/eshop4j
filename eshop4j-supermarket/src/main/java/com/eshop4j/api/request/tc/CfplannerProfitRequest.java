package com.eshop4j.api.request.tc;

import java.util.Date;

import org.hibernate.validator.constraints.Range;

import com.eshop4j.core.base.api.PaginatorRequest;
import com.eshop4j.core.util.DateUtils;

public class CfplannerProfitRequest extends PaginatorRequest{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7239713694666767185L;

	/**
	 * 时间 格式 2015-10-01
	 */
	private String date = DateUtils.format(new Date(),DateUtils.FORMAT_SHORT);
	
	/**
	 * 时间类别: 1:年；2:季度；3:月；4:日 ；5：历史累计(默认表示当月)
	 */
	@Range(min=1,max=5,message="请选择1-5之间的时间类别")
	private Integer dateType = 3;
	
	/**
	 * 收益类型id(为空表示全部收益)
	 */
	private Integer profitType ;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getDateType() {
		return dateType;
	}
	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}
	public Integer getProfitType() {
		return profitType;
	}
	public void setProfitType(Integer profitType) {
		this.profitType = profitType;
	}
	
	

}
