package com.eshop4j.api.request.tc;

import java.util.Date;

import org.hibernate.validator.constraints.Range;

import com.eshop4j.core.base.api.PaginatorRequest;
import com.eshop4j.core.util.DateUtils;

public class CfplannerCustomerInvestRecordRequest extends PaginatorRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7087946238137647637L;
	
	private String userId;
	
	/**
	 * 时间类别: 1:年；2:季度；3:月；4:日；5:周; 6：历史累计   (默认表示当月)
	 */
	@Range(min=1,max=6,message="请选择1-6之间的时间类别")
	private Integer dateType = 3;
	
	/**
	 * 时间 格式 2015-10-01
	 */
	private String date = DateUtils.format(new Date(),DateUtils.FORMAT_SHORT);
	
	/**
	 * 类型 1投资记录，2投资客户
	 */
	@Range(min=1,max=2,message="请选择1-2之间的类别")
	private Integer type =1;
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getDateType() {
		return dateType;
	}

	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
