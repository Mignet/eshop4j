package com.eshop4j.web.enums;

import com.eshop4j.core.base.KeyValueEnum;

/**
 * 
 * @描述：网贷新闻类型
 *
 * @author hxb
 * @时间  2016年5月12日上午10:27:00
 *
 */
public enum NetloanNewsTypeEnum implements KeyValueEnum{
	FINANCE(1,"投资攻略"),
	INVEST(2,"政策法规"),
	MANAGE(3,"权威观点"),
	VIEWPOINT(4,"行业动态");
	
	private int key;
	private String value;

	NetloanNewsTypeEnum(int key,String value){
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
