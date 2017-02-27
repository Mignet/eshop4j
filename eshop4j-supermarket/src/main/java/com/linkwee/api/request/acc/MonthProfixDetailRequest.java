package com.linkwee.api.request.acc;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.linkwee.core.base.api.PaginatorRequest;

/**
 * 
 * @描述：月度收益
 *
 *
 */
public class MonthProfixDetailRequest extends PaginatorRequest {
	private static final long serialVersionUID = -5559748971844616557L;

	/**
	 * 月份
	 */
	@NotNull(message = "月份不能为空")
	private String month;

	/**
	 * 收益类型：1销售佣金，2推荐津贴，3活动奖励，4团队leader奖励
	 */
	@Range(min=1,max=4,message="收益类型必须为1、2、3、4")
	private String profixType;
	
	private String userId;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getProfixType() {
		return profixType;
	}

	public void setProfixType(String profixType) {
		this.profixType = profixType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
