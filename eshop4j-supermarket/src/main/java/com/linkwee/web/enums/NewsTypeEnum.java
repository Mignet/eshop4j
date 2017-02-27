package com.linkwee.web.enums;

import com.linkwee.core.base.KeyValueEnum;

/**
 * 
 * @描述：资讯类别
 *
 * @author chenchy
 * @时间  2016年5月12日上午10:27:00
 *
 */
public enum NewsTypeEnum implements KeyValueEnum{
	FINANCE(1,"财经"),
	INVEST(2,"投资"),
	MANAGE(3,"管理"),
	VIEWPOINT(4,"观点");
	
	private int key;
	private String value;

	NewsTypeEnum(int key,String value){
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
