package com.eshop4j.api.request.crm;

import org.hibernate.validator.constraints.Range;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.api.PaginatorRequest;

/**
 * 
 *
 *
 */
public class NewcomerTaskRequest extends PaginatorRequest {
	private static final long serialVersionUID = -5559748971844616557L;

	/**
	 * 任务类型: 1邀请客户，2推荐产品，3派发红包，4邀请理财师，5购买产品，6查看收益
	 */
	@Range(min=1,max=6,message="任务类型必须为1、2、3、4、5、6")
	private String taskType;


	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}


	public String getTaskType() {
		return taskType;
	}


	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
