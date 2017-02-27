package com.linkwee.core.base;

/**
 * 
 * 描述：dubbo接口返回信息head-执行失败实现类
 *
 * @创建人： Bob
 *
 * @时间：2015年11月30日上午10:43:03
 *
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class ErrorCodeSupport implements ErrorCode{
	/**
	 * 错误码
	 */
	private int code;
	/**
	 * 错误信息
	 */
	private String msg;
	
	public ErrorCodeSupport(int code, String msg) {
		this.code =code;
		this.msg = msg;
	}

	@Override
	public int getCode() {
		// TODO Auto-generated method stub
		return code;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return msg;
	}

}
