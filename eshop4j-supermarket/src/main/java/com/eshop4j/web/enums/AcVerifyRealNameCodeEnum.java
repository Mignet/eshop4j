package com.eshop4j.web.enums;

import com.eshop4j.core.base.KeyValueEnum;

/**
 * 实名验证返回CODE
 * */
public enum AcVerifyRealNameCodeEnum implements KeyValueEnum{
	/**
	 * 返回错误编号	错误说明
		00	认证通过
		98	认证未通过,请检查输入信息
		01	姓名校验不通过
		02	身份证号码有误
		03	银行卡号码有误
		04	手机号码不合法
		05	数据校验不通过
		06	持卡人信息有误,请检查输入信息
		07	未开通无卡支付或在线支付功能
		10	请求条件有缺失，请确认是否遗漏~！
		20	您的体验次数已使用完，请完成正式签约~！
		30	您的签约次数已使用完，请续约购买次数~！
		44	数据渠道已关闭
		55	请求验签失败,请确认加密秘钥和偏移量是否正确~！
		66	未开通该数据渠道
		77	数据渠道已被禁用，暂无限权
		999   数据非法异常
	 */
	SUCCESS(00,"认证通过"),
	CERTIFICATIONNOTPASSED(98,"认证未通过,请检查输入信息"),
    NAMECHECKISNOTPASSED(01,"姓名校验不通过"),
	IDCARDERROR(02,"身份证号码有误"),
	BANKCARDERROR(03,"银行卡号码有误"),
	MOBILEERROR(04,"手机号码不合法"),
	DATAVERIFYNOTPASSED(05,"数据校验不通过"),
	INFOERROR(06,"持卡人信息有误,请检查输入信息"),
	NOTOPEN(07,"未开通无卡支付或在线支付功能"),
	REQUESTMISSING(10,"请求条件有缺失，请确认是否遗漏~!"),
	TRYUSEROUTTIMES(20,"您的体验次数已使用完，请完成正式签约~！"),
	USEROUTTIMES(30,"您的签约次数已使用完，请续约购买次数~！"),
	DATACHANNELCLOSED(44,"数据渠道已关闭"),
	REQUESTCHECKERROR(55,"请求验签失败,请确认加密秘钥和偏移量是否正确~！"),
	NOTOPENCHANNEL(66,"未开通该数据渠道"),
	CHANNELFORBI(77,"数据渠道已被禁用，暂无限权"),
	ILLEGALERROR(999,"数据非法异常");

	
	AcVerifyRealNameCodeEnum(int key,String value){
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
