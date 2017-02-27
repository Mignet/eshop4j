package com.linkwee.web.enums;

import org.apache.commons.lang.ObjectUtils;

import com.linkwee.core.base.KvmEnum;

/**
 * 
 * @描述：理财师等级
 *
 * @author ch
 * @serial 2016-07-26 16:39:00
 *
 */
public enum CfpLevelEnum implements KvmEnum{
	REGISTERED(1,"REGISTERED","理财师","V1"),
	GOLD(2,"GOLD","黄金理财师","V2"),
	PLATINUM(3,"PLATINUM","白金理财师","V3"),
	CHIEF(4,"CHIEF","首席理财师","V4");
	
	CfpLevelEnum(int key,String value,String msg,String level){
		this.key = key;
		this.value = value;
		this.msg = msg;
		this.level = level;
	}

	private int key;
	private String value;
	private String msg;
	private String level;
	
	public int getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	public String getMsg() {
		return msg;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	
	public static CfpLevelEnum getLevelByKey(int key){
		for (CfpLevelEnum levelEnum : values()) {
			if(ObjectUtils.equals(levelEnum.getKey(), key)){
				return levelEnum;
			}
		}
		return null;
	}
}
