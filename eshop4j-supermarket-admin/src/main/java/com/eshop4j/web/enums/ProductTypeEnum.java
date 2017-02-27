package com.eshop4j.web.enums;

import com.eshop4j.core.base.KeyValueEnum;

/**
 * 
 * @描述：消息类别
 *
 * @author Bob
 * @时间  2015年10月27日上午10:27:00
 *
 */
public enum ProductTypeEnum implements KeyValueEnum{
	HQ(1,"活期产品"),
	FIX(2,"固定收益"),
	FLOW(3,"浮动收益");
	
	private int key;
	private String value;

	ProductTypeEnum(int key,String value){
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}


	public String getValue() {
		return value;
	}

	
}
