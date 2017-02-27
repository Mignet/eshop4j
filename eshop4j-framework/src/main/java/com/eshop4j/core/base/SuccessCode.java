package com.eshop4j.core.base;

import java.io.Serializable;

/**
 * 
 * 描述：dubbo接口返回信息head-执行成功实现类
 *
 * @创建人： Bob
 *
 * @时间：2015年11月30日上午11:03:50
 *
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class SuccessCode implements Serializable,ReturnCode{

	private static final long serialVersionUID = -5218204754100867976L;
	/**
	 * 返回码
	 */
	public int code = 0;
	/**
	 * 返回信息
	 */
	public String message = "success";
	@Override
	public int getCode() {
		return code;
	}
	@Override
	public String getMessage() {
		return message;
	}
	@Override
	public String toString() {
		return "SuccessCode [code=" + code + ", message=" + message + "]";
	}
	
}
