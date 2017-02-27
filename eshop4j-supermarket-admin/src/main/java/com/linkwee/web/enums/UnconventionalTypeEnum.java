package com.linkwee.web.enums;

import com.linkwee.core.base.BaseEnum;

/**
 * 
 * @描述：理财师非常规操作类型
 *
 * @author Bob
 * @时间  2015年8月5日上午9:50:32
 *
 */
public enum UnconventionalTypeEnum implements BaseEnum{
	REJECT(1,"理财师回退业务员"),//1理财师回退业务员
	REGIST(2,"业务员注册理财师"),//2业务员注册理财师
	CHANGE(3,"用户变更理财师"),//3用户变更理财师
	BEFREECFP(4,"转换为自由理财师"),//转换为自由理财师
	BEXCFCFP(5,"转换为新财富理财师"),//转换为新财富理财师
	BEFREECUSTOMER(6,"解绑为自由客户"),//客户和理财师解除绑定关系
	REGISTNEW(7,"新用户注册理财师"),//新用户注册
	UPGRADE(8,"理财师升级"),//理财师升级
	DOWNGRADE(9,"理财师降级");//理财师降级
	
	UnconventionalTypeEnum(int code,String message){
		this.code = code;
		this.message = message;
	}

	private int code;
	private String message;
	
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
}
