package com.eshop4j.api.response.tc;


import java.math.BigDecimal;
import java.util.Date;

import com.eshop4j.core.base.BaseEntity;

/**
 * 
 * @描述：客户收益项目明细
 *
 * @author ch
 * @时间 2016-07-28 16:16:19
 *
 */
public class ProfitItemsResponse extends BaseEntity {

	private static final long serialVersionUID = -7499687855292274666L;

	public ProfitItemsResponse() {}


	/**
	 * 收益项id
	 */
	private String profitType;
	/**
	 * 收益项名称
	 */
	private String profitName;

	/**
	 * 佣金金额
	 */
	private BigDecimal amt;
	
	/**
	 * 日期
	 */
	private Date date;
	
	/**
	 * 描述
	 */
	private String remark;
	

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	public String getProfitName() {
		return profitName;
	}

	public void setProfitName(String profitName) {
		this.profitName = profitName;
	}

	public String getAmt() {
		return amt.setScale(2,BigDecimal.ROUND_DOWN).toString();
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
}
