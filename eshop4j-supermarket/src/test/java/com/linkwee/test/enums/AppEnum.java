package com.linkwee.test.enums;


public enum AppEnum{
	
	/**
	 * 金服-安卓
	 */
	INVESTOR_ANDROID("App_investor_android","6FC103186EA54739B1052C4D463E4030"),
	/**
	 * 金服-IOS
	 */
	INVESTOR_IOS("App_investor_ios","4D97CC43F1A644F59C5E4C6A301C2AB4"),
	/**
	 * 金服-微信
	 */
	INVESTOR_WECHAT("App_investor_wechat","E592A953D4B7496BB57CB18A18024228"),
	/**
	 * 金服-wap(手机端)
	 */
	INVESTOR_WAP("App_investor_wab","955A06AF004F4691A2FBCE78684D58ED"),
	/**
	 * 金服-web（PC端）
	 */
	INVESTOR_WEB("App_investor_web","7C7B636E8D324E96AEAAD4B22C3F1E81"),
	/**
	 * 理财师-安卓
	 */
	CHANNEL_ANDROID("App_channel_android","1786221FA2D4415C96B0906C32F4F4B2"),
	/**
	 * 理财师-IOS
	 */
	CHANNEL_IOS("App_channel_ios","F1D007ECF30A4E9EBEC2E0277C60C637"),
	/**
	 * 理财师-微信
	 */
	CHANNEL_WECHAT("App_channel_wechat","5F22886BFD484BF28A9EA76B7CBA8E27"),
	/**
	 * 理财师-wap(手机端)
	 */
	CHANNEL_WAP("App_channel_wab","2608249831104EF7BF0C3CCCE27E6180"),
	/**
	 * 理财师-web（PC端）
	 */
	CHANNEL_WEB("App_channel_web","303CEACD72E24DDD88281B72D12F6A01");
	
	AppEnum(String key,String value){
		this.key = key;
		this.value = value;
	}

	private String key;
	private String value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}
