package com.linkwee.xoss.trace;


public class SpanBuider {
	
	
	//上级span编号
	private String parentId;
	//当前span编号
	private String spanId;
	//当前轨迹编号
	private String traceId;
	//类名
	private String name;
	//当前方法名
	private String methodName;
	//参数
	private Object[] args;
	//调用开始时间
	private Long startTime;
	
	
	//消息
	private String msg;
	
	private String user;
	
	private String version;
	
	public static SpanBuider buider(){
		return new SpanBuider();
	}
	
	
	public SpanBuider parentId(String parentId) {
		this.parentId = parentId;
		return this;
	}

	public SpanBuider spanId(String spanId) {
		this.spanId =spanId;
		return this;
	}

	public SpanBuider traceId(String traceId) {
		this.traceId=traceId;
		return this;
	}

	public SpanBuider name(String name) {
		this.name=name;
		return this;
	}

	public SpanBuider methodName(String methodName) {
		this.methodName=methodName;
		return this;
	}
	
	public SpanBuider startTime(Long startTime) {
		this.startTime =startTime;
		return this;
	}

	public SpanBuider msg(String msg) {
		this.msg =msg;
		return this;
	}
	
	public SpanBuider user(String user) {
		this.user =user;
		return this;
	}
	
	public SpanBuider args(Object[] args) {
		this.args =args;
		return this;
	}
	
	public SpanBuider version(String version) {
		this.version = version;
		return this;
	}


	public Span build(){
		return new Span(parentId, spanId, traceId, name, methodName, args, startTime, msg, user,version);
	}
	
}
