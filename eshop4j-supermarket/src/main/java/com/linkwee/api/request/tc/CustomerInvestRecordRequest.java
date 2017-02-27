package com.linkwee.api.request.tc;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.linkwee.core.base.api.PaginatorRequest;
/**
 * 客户投资记录请求
 * @author ch
 * @serial 2016-07-27 18:21:29
 *
 */
public class CustomerInvestRecordRequest extends PaginatorRequest{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4108828351337074238L;
	@NotNull(message="投资状态不能为空")
	@Range(min=1,max=3,message="投资状态投资状态必须为1-3之间的数值")
	private Integer status;
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
