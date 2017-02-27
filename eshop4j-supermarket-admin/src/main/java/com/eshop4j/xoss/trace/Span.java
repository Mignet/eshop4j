package com.eshop4j.xoss.trace;

import java.io.Serializable;
import java.util.Arrays;


public class Span implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1854235847367448792L;
	

	//当前轨迹编号
	private String traceId;
	
	//上级span编号
	private String parentId;
	
	//当前span编号
	private String spanId;
	
	private String user;
	//类名
	private String name;
	//当前方法名
	private String methodName;
	
	//参数
	private Object[] args;
	//调用开始时间
	private Long startTime;
	//调用结束时间
	private Long endTime;
	
	private Long timestamp;
	
	//状态 0=调用失败|1=调用成功|2=日志记录|3=调用完成
	private String status;
	//消息
	private String msg;
	
	
	
	
	public Span() {
	}
	
	
	
	public Span(String traceId) {
		super();
		this.traceId = traceId;
		this.spanId = traceId;
	}
	
	public Span(String parentId, String spanId, String traceId, String name,
			String methodName, Object[] args, Long startTime,
			String msg, String user) {
		super();
		this.parentId = parentId;
		this.spanId = spanId;
		this.traceId = traceId;
		this.name = name;
		this.methodName = methodName;
		this.args = args;
		this.startTime = startTime;
		this.msg = msg;
		this.user = user;
	}



	public String getParentId() {
		return parentId;
	}

	public String getSpanId() {
		return spanId;
	}

	public String getTraceId() {
		return traceId;
	}

	public String getName() {
		return name;
	}

	public String getMethodName() {
		return methodName;
	}

	public Object[] getArgs() {
		return args;
	}

	public Long getStartTime() {
		return startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public String getStatus() {
		return status;
	}

	

	public void setStatus(String status) {
		this.status = status;
	}



	public String getMsg() {
		return msg;
	}



	public String getUser() {
		return user;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		if (traceId != null) {
			builder.append("traceId=");
			builder.append(traceId);
			builder.append(", ");
		}
		if (parentId != null) {
			builder.append("parentId=");
			builder.append(parentId);
			builder.append(", ");
		}
		if (spanId != null) {
			builder.append("spanId=");
			builder.append(spanId);
			builder.append(", ");
		}
		if (user != null) {
			builder.append("user=");
			builder.append(user);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (methodName != null) {
			builder.append("methodName=");
			builder.append(methodName);
			builder.append(", ");
		}
		if (args != null) {
			builder.append("args=");
			builder.append(Arrays.toString(args));
			builder.append(", ");
		}
		if (startTime != null) {
			builder.append("startTime=");
			builder.append(startTime);
			builder.append(", ");
		}
		if (endTime != null) {
			builder.append("endTime=");
			builder.append(endTime);
			builder.append(", ");
		}
		if (timestamp != null) {
			builder.append("timestamp=");
			builder.append(timestamp);
			builder.append(", ");
		}
		if (status != null) {
			builder.append("status=");
			builder.append(status);
			builder.append(", ");
		}
		if (msg != null) {
			builder.append("msg=");
			builder.append(msg);
		}
		builder.append("]");
		return builder.toString();
	}



	
	
}
