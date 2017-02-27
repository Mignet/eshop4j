package com.linkwee.web.enums;

public enum OrgSaleFeeTypeCodeEnum {
	/**
	 * 收费类型类别,fixed-每个新投资人固定费用,float-根据区间设置类别，proportion根据首投金额比较设置类别,amtproportion-根据用户年化和产品期限按比例计算
	 */
	FIXED("fixed","每个新投资人固定费用"),	
	FLOAT_FIXED("float_fixed","根据投资额区间收取首投固定费用"),
	PROPERTION("propertion","根据首投金额比例计算"),
	YEAR_PROPERTION("year_propertion","根据年化和产品期限设定比例计算"),
	MONTH_AMOUNT_PROPERTION("month_amount_propertion","根据年化和当月平台销售额区间设定比例计算");
	
	OrgSaleFeeTypeCodeEnum(String key,String value){
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
