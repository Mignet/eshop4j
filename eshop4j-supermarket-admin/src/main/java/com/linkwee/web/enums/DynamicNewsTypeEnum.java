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
public enum DynamicNewsTypeEnum implements KeyValueEnum{
	MEDIA_COVERAGE(1,"媒体报道"),
	TBEI_DYNAMIC(2,"T呗动态");
	
	private int key;
	private String value;

	DynamicNewsTypeEnum(int key,String value){
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
