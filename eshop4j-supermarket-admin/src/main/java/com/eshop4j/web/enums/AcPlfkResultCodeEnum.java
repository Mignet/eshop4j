package com.eshop4j.web.enums;

import com.eshop4j.core.base.KeyValueEnum;
/**
 * 批量扣款返回code
 * @author chenjl
 *
 */
public enum AcPlfkResultCodeEnum implements KeyValueEnum{
	/**
	 * 返回错误编号	错误说明
		1101 IP地址不符
		1102 不是授权的会员
		1103 送盘文件大小超过限制
		1104 送盘文件不存在
		1105 解压缩失败
		1106 加密失败
		1107 解密失败
		1108 文件格式不符
		1109 总订单总金额不一致
		1110 批次号填写错误
		1111 批次号重复
		1112 序号填写错误
		1113 会员号填写错误
		1114 付款方账号填写错误
		1115 发起日期填写错误
		1116 付款商户名称填写错误
		1117 总金额填写错误
		1118 总笔数填写错误
		1119 付费方式填写错误
		1120 币种填写错误
		1121 格式错误-是否验证金额笔数
		1122 格式错误-是否整批失败
		1123 充值方式填写错误
		1124 格式错误-是否自动退款
		1125 格式错误-是否短信通知
		1126 商家订单号填写错误
		1127 金额填写错误
		1128 银行名称填写错误
		1129 户名填写错误
		1130 卡号填写错误
		1131 开户行填写错误
		1132 对公对私填写错误
		1133 省份填写错误
		1134 银行名称与开户行不匹配
		1135 收款方电子邮箱格式不正确
		1136 收款方手机格式不正确
		1137 付款账户被注销
		1138 付款账户被冻结
		1139 付款账户止出
		1140 付款账户不能付款
		1201 商家订单号重复
		1202 余额不足
		1301 批次不存在
		1302 交易不存在
		1303 未指定查询时间范围
		1304 开始或结束时间格式不正确
		1305 批次号不允许为空
		1306 请求频率过快
		1307 备注长度超过限制
		1308 总笔数与明细不符
		1309 总金额与明细不符
		1310 付款金额低于费用
		1311 不能对个人付款
		1312 收款方不在限定名单范围之内
		1313 付款明细有误
		1314 单笔超过支付限额
		1315 单笔超过当日支付限额
		1316 报文格式有误
		1317 格式错误-是否要显示明细填写错误
		1318 页码显示条数填写错误
		1319 错误代码格式错误
		1320 没有符合条件的记录
		9101 户名有误
		9102 账户不存在或状态异常
		9103 开户行信息有误
		9104 账户冻结
		9105 账户已挂失
		9106 账户已销户
		9107 不能对此类账户做此类交易
		9108 账户状态异常
		9109 不支持旧账号
		9110 卡号不合法或不存在
		9111 因账户长期不动帐，请到柜台办理业务
		9112 银行信息有误
		9113 客户姓名不符
		9114 证件号码或姓名与账户不符
		9115 账户不存在
		9116 账户关闭
		9117 账户余额不足
		9118 账户类型有误
		9119 账号有误
		9120 账户非法
		9121 账号或户名有误
		9122 过期卡
		9123 不支持存折账号
		9124 不允许跨省市交易或不支持存折
		9125 退票-非结算账户无法入账
		9126 不支持对公账户
		9201 银行区域有误
		9202 金额超限
		9303 超过额度限制
		9501 此账户未缴纳小额账户管理费
		9601 输入账号卡号错
		9602 发起方账户未签约
		9603 发起方账户未激活
		9604 发起方账户无权限
		9605 发起方账户不存在或状态异常
		9606 未设置清算户
		9607 未设置业务额度
		9608 同批次中不能有相同卡折号的记录存在
		9609 柜员无此类凭证
		9610 金额有误
		9611 币种有误
		9612 无效业务
		9613 处理活期产品主档错误
		9614 无一笔入帐成功
		9615 没有符合条件的记录
		9616 提交失败
		9617 文件中记录条数超过限制
		9618 总笔数非法
		9619 已超过此交易当天允许执行最大次数
		9620 用途信息不符合企业财务室要求
		9621 收费金额超过单笔最大金额
		9622 未注册企业财务室
		9623 指令处理失败
		9624 错误代码获取有误
		9625 报文异常
		9626 通讯失败
		9627 外部系统错误
		9628 外部系统通讯失败
		9999 其它错误
	 */
	IPERROR(1101,"IP地址不符"),
	NOTMEMBER(1102,"不是授权的会员"),
	SENDLIMIT(1103,"送盘文件大小超过限制"),
	DISKNOTEXIST(1104,"送盘文件不存在"),
	DECOMPRESSIONFAIL(1105,"解压缩失败"),
	ENCRYPTIONFAIL(1106,"加密失败"),
	DECRYPTFAIL(1107,"解密失败"),
	FORMATNOTMATCH(1108,"文件格式不符"),
	TOTALAMOUNTERROR(1109,"总订单总金额不一致"),
	BATCHERROR(1110,"批次号填写错误"),
	BATCHREPEAT(1111,"批次号重复"),
	SERIALFAIL(1112,"序号填写错误"),
	MEMBERFAIL(1113,"会员号填写错误"),
	ACCOUNTFAIL(1114,"付款方账号填写错误"),
	DATEERROR(1115,"发起日期填写错误"),
	MERCHANTERROR(1116,"付款商户名称填写错误"),
	TOTALAMOUNTTIANERROR(1117,"总金额填写错误"),
	TOTALNUMBERERROR(1118,"总笔数填写错误"),
	PAYFILLERROR(1119,"付费方式填写错误"),
	CURRENCYFILLERROR(1120,"币种填写错误"),
	VERIFYAMOUNTERROR(1121,"格式错误-是否验证金额笔数"),
	WHOLEBATCHERROR(1122,"格式错误-是否整批失败"),
	FILLUPERROR(1123,"充值方式填写错误"),
	AUTOMATICREFUND(1124,"格式错误-是否自动退款"),
	SMSNOTIFY(1125,"格式错误-是否短信通知"),
	MERCHANTORDERERROR(1126,"商家订单号填写错误"),
	AMOUNTFILLERROR(1127,"金额填写错误"),
	BANKNAMEFILLERROR(1128,"银行名称填写错误"),
	NAMEFILLERROR(1129,"户名填写错误"),
	CARDNOFILLERROR(1130,"卡号填写错误"),
	KAIHUHANGERROR(1131,"开户行填写错误"),
	PUBLICFILLERROR(1132,"对公对私填写错误"),
	PROVINCEFILLERROR(1133,"省份填写错误"),
	BANKKAIHUHANGERROR(1134,"银行名称与开户行不匹配"),
	EMAILERROR(1135,"收款方电子邮箱格式不正确"),
	MOBILEERROR(1136,"收款方手机格式不正确"),
	ACCOUNTCANCELLATION(1137,"付款账户被注销"),
	ACCOUNTFROZEN(1138,"付款账户被冻结"),
	PAYMENTCHECKOUT(1139,"付款账户止出"),
	ACCOUNTNOPAY(1140,"付款账户不能付款"),
	ORDERREPEAT(1201,"商家订单号重复"),
	ACCOUNTLOW(1202,"余额不足"),
	BATCHNOEXIST(1301,"批次不存在"),
	TRANSACTIONNOEXIST(1302,"交易不存在"),
	TIMERANGENOQUERY(1303,"未指定查询时间范围"),
	STARTENDTIMEERROR(1304,"开始或结束时间格式不正确"),
	BATCHNONULL(1305,"批次号不允许为空"),
	FREQUENCYTOOFAST(1306,"请求频率过快"),
	REMARKLENGHTLIMIT(1307,"备注长度超过限制"),
	TOTALNUMBERNOTMATCHDETAIL(1308,"总笔数与明细不符"),
	TOTALAMOUNTONTMATCHDETAIL(1309,"总金额与明细不符"),
	PAYMENTLOW(1310,"付款金额低于费用"),
	NOPAYINDIVIDUAL(1311,"不能对个人付款"),
	NOTSCOPELIMIT(1312,"收款方不在限定名单范围之内"),
	PAYDETAILERROR(1313,"付款明细有误"),
	SINGLEPAYOVERLIMIT(1314,"单笔超过支付限额"),
	SINGLEPAYOVERTODAYLIMIT(1315,"单笔超过当日支付限额"),
	MESSAGEFORMATERROR(1316,"报文格式有误"),
	FORMATERRORDETAIL(1317,"格式错误-是否要显示明细填写错误"),
	FORMATERRORNUMBER(1318,"页码显示条数填写错误"),
	CODEFORMATERROR(1319,"错误代码格式错误"),
	RECORDWITHOUTCONDITION(1320,"没有符合条件的记录"),
	ACCOUNTNAMEERROR(9101,"户名有误"),
	ACCOUNTNOEXIST(9102,"账户不存在或状态异常"),
	KAIHUHANGINFOERROR(9103,"开户行信息有误"),
	ACCOUNTFREZEN(9104,"账户冻结"),
	ACCOUNTREPORTED(9105,"账户已挂失"),
	ACCOUNTCANCELLED(9106,"账户已销户"),
	DONOTSUCHTHISPAY(9107,"不能对此类账户做此类交易"),
	ACCOUNTSTATUSEXCEPTION(9108,"账户状态异常"),
	OLDACCOUNT(9109,"不支持旧账号"),
	CARDNOINVALID(9110,"卡号不合法或不存在"),
	ACCOUNTLONGTERM(9111,"因账户长期不动帐，请到柜台办理业务"),
	BANKINFOERROR(9112,"银行信息有误"),
	USERNAMEERROR(9113,"客户姓名不符"),
	IDCARDACCOUNTNOMATCH(9114,"证件号码或姓名与账户不符"),
	ACACCOUNTNOEXIST(9115,"账户不存在"),
	ACCOUNTCLOSED(9116,"账户关闭"),
	ACCOUNTBALANCELOW(9117,"账户余额不足"),
	ACCOUNTTYPEERROR(9118,"账户类型有误"),
	ACCOUNTNOERROR(9119,"账号有误"),
	ILLEGALACCOUNT(9120,"账户非法"),
	ACCOUNTORNAMEERROR(9121,"账号或户名有误"),
	EXPIREDCARD(9122,"过期卡"),
	NOTSUPPORTPASSBOOK (9123,"不支持存折账号"),
	NOTSUPPORTPROVINCIAL(9124,"不允许跨省市交易或不支持存折"),
	REFUNDATICKET(9125,"退票-非结算账户无法入账"),
	NOTSUPPORTPUBLICACCOUNT(9126,"不支持对公账户"),
	BANKAREAERROR(9201,"银行区域有误"),
	AMOUNTLIMIT(9202,"金额超限"),
	OVERLIMIT(9303,"超过额度限制"),
	NOTPAIDTHISACCOUNT(9501,"此账户未缴纳小额账户管理费"),
	BANKCARDNOERROR(9601,"输入账号卡号错"),
	NOTSIGNED(9602,"发起方账户未签约"),
	NOTACTIVE(9603,"发起方账户未激活"),
	NOTPERMISSIONS(9604,"发起方账户无权限"),
	SPONSORNOEXIST(9605,"发起方账户不存在或状态异常"),
	NOCLEARING(9606,"未设置清算户"),
	NOLIMITBUSINESS(9607,"未设置业务额度"),
	NOTHESAMECARDNO(9608,"同批次中不能有相同卡折号的记录存在"),
	NOSUCHCERTIFICATE(9609,"柜员无此类凭证"),
	AMOUNTERROR(9610,"金额有误"),
	CURRENCYERROR(9611,"币种有误"),
	INVALIDSERVICE(9612,"无效业务"),
	PRODUCTMASTERERROR(9613,"处理活期产品主档错误"),
	NOSUCCESSFULENTRY(9614,"无一笔入帐成功"),
	RECORDWITHOUTCONDIT(9615,"没有符合条件的记录"),
    SUBMISSIONFAILED(9616,"提交失败"),
	FILERECORDOVERLIMIT(9617,"文件中记录条数超过限制"),
	TOTALNUMBERILLAGE(9618,"总笔数非法"),
	OVERTIMES(9619,"已超过此交易当天允许执行最大次数"),
	NOMATCHFINANCIALROOM(9620,"用途信息不符合企业财务室要求"),
	CHARGESOVERMAXAMOUNT(9621,"收费金额超过单笔最大金额"),
	UNREGISTEREDFINANCIAL(9622,"未注册企业财务室"),
	PROCESSINGERROR(9623,"指令处理失败"),
	ERRORCODEQUERYERROR(9624,"错误代码获取有误"),
	MESSAGEEXCEPTION(9625,"报文异常"),
	COMMUNICATIONFAILURE(9626,"通讯失败"),
	EXTERNALSYSTEMERROR(9627,"外部系统错误"),
	EXTERNALSYSTEMCOMMUERROR(9628,"外部系统通讯失败"),
	OTHERERROR(9999,"其它错误");

	
	AcPlfkResultCodeEnum(int key,String value){
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
