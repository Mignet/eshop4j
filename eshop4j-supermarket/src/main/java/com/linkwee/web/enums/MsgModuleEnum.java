package com.linkwee.web.enums;

import com.linkwee.core.base.KeyValueEnum;


/**
 * 
 * @描述：合伙人等级
 *
 * @author Bob
 * @时间  2015年8月5日上午9:50:32
 *
 */
public enum MsgModuleEnum implements KeyValueEnum{
	
	//注册 修改登陆密码 修改交易密码  佣金发放
	REGISTER(1,"REGISTER","用户注册"),
	UPDATELOGINPWD(2,"UPDATELOGINPWD","修改登陆密码"),
	UPDATETRADEPWD(3,"UPDATETRADEPWD","修改交易密码"),
	COMMISSIONPAY(4,"COMMISSIONPAY","佣金发放"),
	BACKLEFTONEDAY(5,"BACKLEFTONEDAY","产品剩余一天到期回款"),
    FINISHWITHDRAWAL(6,"FINISHWITHDRAWAL","提现申请处理完成"),
	RECIVEREDPAPERBYSYS(7,"RECIVEREDPAPERBYSYS","收到投资返现红包(系统发)"),
	RECIVEREDPAPERBYLCS(8,"RECIVEREDPAPERBYLCS","收到投资返现红包(理财师发)"),
	REDPAPEREXPIRED(9,"REDPAPEREXPIRED","红包过期提醒"),
    INVESTUSEREDPAPER(10,"INVESTUSEREDPAPER","投资自动使用红包"),
    ADDRESSINVITATIONTOBEINV(11,"ADDRESSINVITATION","通讯录邀请-成为投资客户"),    
    ADDRESSINVITATIONTOBELCS(12,"ADDRESSINVITATION","通讯录邀请-成为理财师"),
    INVESTORPAYBACKINV(13,"INVESTORPAYBACKINV","投资回款-投资端"),
	WITHDRAWALAPPLY(14,"WITHDRAWALAPPLY","提现申请"),
	UPDATEPWDBYOLDPWD(15,"UPDATEPWDBYOLDPWD","修改登录密码-使用原密码修改"),
	UPDATEPAYPWDBYOLDPWD(16,"UPDATEPAYPWDBYOLDPWD","修改支付密码-使用原密码修改");
	
	
	MsgModuleEnum(int key,String value,String msg){
		this.key = key;
		this.value = value;
		this.msg = msg;
	}

	private int key;
	private String value;
	private String msg;
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
