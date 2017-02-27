package com.linkwee.web.service;

import java.util.List;

import com.linkwee.api.request.crm.WeiXinMsgRequest;
import com.linkwee.tc.fee.model.vo.FeedetailWrapper;

/**
 * 
 * @描述： WeiXinMsgService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年11月21日 15:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface WeiXinMsgService {

	
    /**
     * 更新微信accToken,accToken有效时间2小时
     * */
	String updateWeiXinAccToken(Integer useType);
	
	/**
	 * 给微信端推送消息
	 * */
	void sendWeiXinMsg(WeiXinMsgRequest req);
	
	/**
	 * 给微信端推送消息(公共)
	 * */
	void sendWeiXinMsgCommon(WeiXinMsgRequest req);
	
	/**
	 * 给微信端推送消息(公共)
	 * */
	void sendWeiXinMsgListCommon(List<WeiXinMsgRequest> req);
	
	/**
	 * 用户投资成功后发送理财师(当前、一级、二级)微信消息
	 * */
	void investSuccesssendWeiXinMsg(List<FeedetailWrapper> feedetails);
	
}
