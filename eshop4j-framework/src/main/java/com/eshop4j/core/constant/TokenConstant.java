package com.eshop4j.core.constant;

/**
 * 
 * @描述：token相关常量
 *
 * @author Bob
 * @时间  2015年8月5日上午9:51:11
 *
 */
public class TokenConstant {
	/**
	 * token类别
	 */
	public static final String BID = "CHANNEL";
	/**
	 * 推荐信息保存时间(单位秒)
	 */
	public static final int RECOMMEND_TIMEOUT = 60*24*30;
	/**
	 * 登录超时时间
	 */
	public static final int LOGIN_TIMEOUT = 60*24;
	
	/**
	 * 图片验证码超时时间
	 */
	public static final int PIC_VCODE_TIMEOUT = 60*10;
	
	/**
	 * 修改交易密码超时时间
	 */
	public static final int RESET_PAYPWD_TIMEOUT = 15;
	
	/**
	 * ticket失效
	 */
	public static final int WECHAT_TICKET_TIMEOUT = 60+40;
	
	/**
	 * 微信票据键
	 */
	public static final String WECHAT_TICKET_KEY = "wechat_ticket";
	
	/**
	 * 推荐信息保存时间(单位秒)
	 */
	public static final int LOGINCODE_TIMEOUT = 1;
	
	/**
	 * 验证码token类别
	 */
	public static final String TOKEN_TYPE_PIC_VCODE = "pic_vcode";
}
