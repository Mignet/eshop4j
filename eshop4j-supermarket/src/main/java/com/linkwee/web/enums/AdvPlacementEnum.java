package com.linkwee.web.enums;


/**
 * 
 * @描述：广告类目 *
 * @author chenchy
 * @serial 2016-09-05 10:05:00
 *
 */
public enum AdvPlacementEnum {
	APP_OPENING("app_opening","开屏"),
	APP_BANNER("app_home_page","Banner页"),
	PC_IDX_MIDDLE("pc_idx_middle","pc版投呗首页页中"),
	PC_BANNER("pc_banner","pc版banner");
	
	AdvPlacementEnum(String key,String value){
		this.key = key;
		this.value = value;
	}
	
	private String key;
	private String value;
	
	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
	

}
