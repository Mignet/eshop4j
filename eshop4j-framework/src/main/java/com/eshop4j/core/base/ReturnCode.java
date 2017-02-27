package com.eshop4j.core.base;

/**
 * 
 * 描述：dubbo服务返回头接口
 *
 * @author Bob
 * @时间  2015年7月29日下午5:29:37
 *
 */
public interface ReturnCode {
	/**
	 * 获取错误码
	 * @return
	 */
	public int getCode();
	/**
	 * 获取错误信息
	 * @return
	 */
	public String getMessage();
	
}
