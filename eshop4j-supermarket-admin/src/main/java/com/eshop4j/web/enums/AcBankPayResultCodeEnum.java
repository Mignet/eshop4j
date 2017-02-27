package com.eshop4j.web.enums;

import com.eshop4j.core.base.KeyValueEnum;

public enum AcBankPayResultCodeEnum implements KeyValueEnum{
	/**
	 * 返回错误编号	错误说明
		0000 : 支付成功
		1000 : 身份验证失败
		1001 : 信息填写不全
		1002 : 付款方帐号无效
		1003 : 加密方式不正确
		1004 : 收款方帐号无效
		1005 : 收款联系方式无效
		1006 : 交易描述不能大于100 位
		1007 : 收款超过限额
		1008 : 费用大于付款金额
		1009 : 验签错误
		1010 : IP 地址不符
		1011 : 不匹配的接口类型
		1012 : 支付金额格式不正确
		1014 : 银行卡号不能为空
		1015 : email 或mobile 格式不正确
		1016 : 不匹配的交易类型
		1017 : 货币代码错误
		1018 : 不能给自己付款
		1019 : 查询请求为空
		1020 : 查询类型无效
		1021 : 订单不存在
		1022 : 未指定查询起始时间
		1023 : 时间格式错误
		1024 : 无效数组
		1025 : 商家订单必须是0-9 a-z A-Z 和_的字符组合
		1026 : 超过最大交易笔数限制
		2001 : 服务器计费异常
		2002 : 服务处理异常
		2003 : 付款账户被冻结
		2004 : 其他异常
		3001 : 不是授权的会员
		3002 : 查询结果集为空
		3003 : 匹配收款人名称
		3004 : 不能给非快钱用户支付
		3005 : 通知异常
		3006 : 验签不能为空
		3010 : 付款超过限额
		3014 : 单笔付款超过限额
		3015 : 单笔收款超过限额
		4007 : 开户行不能为空
		4008 : 省份格式不正确
		4009 : 城市格式不正确
		4013 : 银行名称错误
		5201 : 订单不能被退款
		5203 : 不是订单的所有者
		5204 : 超出退款时限
		5211 : 订单号必须输入
		5212 : 商家订单号已经存在
		6001 : 余额不足
		6003 : 收款账户被冻结
		6006 : 交易引擎出错
		7000 : 批次号必须是A-Z /0-9 和_的字符组合
		7001 : 批次号已经存在
		7002 : 付款会员号不能为空
		7003 : 付款账户不能为空
		7004 : 付款账户不存在
		7005 : 付款账户不是人民币账户
		7006 : 会员号和账户号不匹配
		7007 : 不匹配的字符集
		7008 : 主题信息验签不能为空
		7009 : 主题信息验签错误
	 */
	SUCCESS(0000,"支付成功"),
	AUTHENTICATIONFAILED(1000,"身份验证失败"),
	INCOMPLETEINFORMATION(1001,"信息填写不全"),
	SERVERINNERERROR(1002,"付款方帐号无效"),
	ENCRYPTIONNOTCORRECT(1003,"加密方式不正确"),
	ACCOUNTISINVALID(1004,"收款方帐号无效"),
	INVALIDCOLLECTIONCONTACT(1005,"收款联系方式无效"),
	NOTBEGREATERTHAN100(1006,"交易描述不能大于100位"),
	RECEIVABLESEXCEEDLIMITS(1007,"收款超过限额"),
	COSTGREATERTHANPAYMENTS(1008,"费用大于付款金额"),
	CHECKERROR(1009,"验签错误"),
	IPADDRESSNOTMATCH(1010,"IP地址不符"),
	MISMATCHEDINTERFACE(1011,"不匹配的接口类型"),
	PAYAMOUNTNOTRIGHTFORMAT(1012,"支付金额格式不正确"),
	BANKCARDNOTNULL(1014,"银行卡号不能为空"),
	EMAILORMOBILEFORMATNOTRIGHT(1015,"email或mobile格式不正确"),
	NOTMATCHTYPEOFTRANSACTION(1016,"不匹配的交易类型"),
	CURRENCYCODEERROR(1017,"货币代码错误"),
	NOTPAYFORYOURSELF(1018,"不能给自己付款"),
	QUERYREQUESTNOTNULL(1019,"查询请求为空"),
	INVALIDQUERYTYPE(1020,"查询类型无效"),
	ORDERNOTEXIST(1021,"订单不存在"),
	QUERYSTARTTIMENOTSPECIFIED(1022,"未指定查询起始时间"),
	TIMEFORMATERROR(1023,"时间格式错误"),
	INVALIDARRAY(1024,"无效数组"),
	MERORDERSMUSTCOMBINATIONCHAR(1025,"商家订单必须是0-9/a-z/A-Z和_的字符组合"),
	MAXIMUMTRANSACTIONLIMIT(1026,"超过最大交易笔数限制"),
	SERVERBILLINGEXCEPTION(2001,"服务器计费异常"),
	SERVICEHANDLINGEXCEPTION(2002,"服务处理异常"),
	PAYMENTACCOUNTISFROZEN(2003,"付款账户被冻结"),
	OTHERANOMALIES(2004,"其他异常"),
	NOTALICENSEDMEMBER(3001,"不是授权的会员"),
	QUERYRESULTSETISNULL(3002,"查询结果集为空"),
	MATCHINGNAMEOBENEFICIARY(3003,"匹配收款人名称"),
	NOTPAIDNOTBILL99(3004,"不能给非快钱用户支付"),
	NOTIFICATIONEXCEPTION(3005,"通知异常"),
	CHECKCANNOTNULL(3006,"验签不能为空"),
	PAYMENTSEXCEEDLIMITS(3010,"付款超过限额"),
	SINGLEPAYEXCEEDINGLIMIT(3014,"单笔付款超过限额"),
	SINGLEPAYEXCEEDSLIMIT(3015,"单笔收款超过限额"),
	KAIHUHANGNOTNULL(4007,"开户行不能为空"),
	PROVINCEFORMATNOTCORRECT(4008,"省份格式不正确"),
	URBANFORMATNOTCORRECT(4009,"城市格式不正确"),
	BANKNAMEERROR(4013,"银行名称错误"),
	ORDERCANNOTBEREFUNDED(5201,"订单不能被退款"),
	NOTOWNEROFORDER(5203,"不是订单的所有者"),
	BEYONDTIMELIMITFORREFUND(5204,"超出退款时限"),
	ORDERNUMBERMUSTBE(5211,"订单号必须输入"),
	MERORDERALREADYEXISTS(5212,"商家订单号已经存在"),
	CREDITBALANCELOW(6001,"余额不足"),
	ACCOUNTISFROZEN(6003,"收款账户被冻结"),
	TRANSACTIONENGINEERROR(6006,"交易引擎出错"),
	BATCHNOMUSTCOMBINATIONCHAR(7000,"批次号必须是A-Z/0-9和_的字符组合"),
	BATCHNOALREADYEXISTS(7001,"批次号已经存在"),
	PAYMENTMEMBERCANNOTEMPTY(7002,"付款会员号不能为空"),
	PAYMENTACCOUNTCANNOTEMPTY(7003,"付款账户不能为空"),
	PAYMENTACCOUNTDOESNOTEXIST(7004,"付款账户不存在"),
	PAYMENTACCOUNTNOTRMB(7005,"付款账户不是人民币账户"),
	MEMBERNOANDACCOUNTNONOTMATCH(7006,"会员号和账户号不匹配"),
	MISMATCHEDCHARACTERSET(7007,"不匹配的字符集"),
	SUBJECTINFONOTNULL(7008,"主题信息验签不能为空"),
	Subjectinfocheckerror(7009,"主题信息验签错误");

	
	AcBankPayResultCodeEnum(int key,String value){
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
