package com.eshop4j.core.base;


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
public class ServerErrorCode extends ErrorCodeSupport{
	

	public ServerErrorCode(){
		super(-1, "server error");
	}
	
	public ServerErrorCode(int code, String msg) {
		super(-1, "server error");
	
	}
}
