package com.linkwee.web.enums;

import com.linkwee.core.base.KeyValueEnum;

/**
 * 消息推送parterId
 * 
 * @author xuzhao
 * @Date 2016年3月14日 下午2:52:56
 */
public enum MsgPartnerIdEnum implements KeyValueEnum{
	LHLC(1,"LHLC"),
	LHJF(2,"LHJF");
	
	MsgPartnerIdEnum(int key,String value){
		this.key = key;
		this.value = value;
	}

	private int key;
	private String value;
	
	
	public int getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}

}
