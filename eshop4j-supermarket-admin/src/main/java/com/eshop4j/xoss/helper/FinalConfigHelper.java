package com.eshop4j.xoss.helper;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.eshop4j.core.constant.SysConfigConstant;
import com.eshop4j.web.enums.RcTypeEnum;

@Component
public class FinalConfigHelper {
	
	@Resource
	private ConfigHelper configHelper;
	
	/**
	 * 获取匿名
	 * @param type
	 * @param userName
	 * @param url
	 * @return
	 */
	public String prewRclcs(int type,String userName,String url){
		if(RcTypeEnum.UPGRADE_SIGNATURE.getKey()==type){
			String content = configHelper.getValue(SysConfigConstant.RC_MESSAGE_SIGN);
			return String.format(content, userName,url);
		}else{
			String content = configHelper.getValue(SysConfigConstant.RC_MESSAGE_ANONYMOUS);
			return String.format(content,url);
		}
	}

	/**
	 * 是否发送短信
	 * @return
	 */
	public boolean isSendMsg(){
		String content = configHelper.getValue(SysConfigConstant.KEY_NO_SEND_VCODE);
		if(content!=null&&"1".equals(content)){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 通讯录邀请客户
	 * @param userName
	 * @param rcCode
	 * @return
	 */
	public String getMaillistRcCustomer(String userName,String mobile){
		return String.format(configHelper.getValue(SysConfigConstant.MAIL_INVITATION_CUSTOMER_URL),
				userName,mobile);
	}
	
	/**
	 * 通讯录邀请理财师
	 * @param userName
	 * @param rcCode
	 * @return
	 */
	public String getMaillistRcLcs(String userName,String rcCode){
		return String.format(configHelper.getValue(SysConfigConstant.MAIL_INVITATION_LCS_URL),
				userName,rcCode);
	}
}
