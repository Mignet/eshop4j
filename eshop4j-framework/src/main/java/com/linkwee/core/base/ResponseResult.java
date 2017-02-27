package com.linkwee.core.base;

import java.io.Serializable;

public class ResponseResult implements Serializable {
	private static final long serialVersionUID = -1822250465600362504L;

	private Boolean isFlag; //（true=成功|false=失败）
	private String msg; //响应消息
	private Object data; //响应结果数据

	public ResponseResult() {
	}

	public ResponseResult(Boolean isFlag, String msg) {
		this.isFlag = isFlag;
		this.msg = msg;
	}

	public ResponseResult(Boolean isFlag, String msg, Object data) {
		this.isFlag = isFlag;
		this.msg = msg;
		this.data = data;
	}

	public Boolean getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(Boolean isFlag) {
		this.isFlag = isFlag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseResult [isFlag=" + isFlag + ", msg=" + msg + ", data="
				+ data + "]";
	}
}