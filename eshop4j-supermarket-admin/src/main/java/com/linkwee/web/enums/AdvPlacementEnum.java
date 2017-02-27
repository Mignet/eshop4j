package com.linkwee.web.enums;


/**
 * 
 * @描述：广告类目 *
 * @author chenchy
 * @serial 2016-09-05 10:05:00
 *
 */
public enum AdvPlacementEnum {
	APP_OPENING("app_opening","开屏",0),
	APP_BANNER("app_home_page","Banner页",2),
	PC_IDX_MIDDLE("pc_idx_middle","pc版投呗首页页中（停用）",2),
	PC_BANNER("pc_banner","pc版banner",2),
	PLATFORM_BANNER("platform_banner","平台banner",0),
	PRODUCT_BANNER("product_banner","产品banner",0);
	
	AdvPlacementEnum(String key,String value,int appType){
		this.key = key;
		this.value = value;
		this.appType = appType;
		
	}
	
	private String key;
	private String value;
	private int appType;
	
	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}

	public int getAppType() {
		return appType;
	}
	
	

}
