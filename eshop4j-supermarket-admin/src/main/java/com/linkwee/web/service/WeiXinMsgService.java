package com.linkwee.web.service;

import java.util.List;

import com.linkwee.web.model.acc.AcOfflineRewardDraft;
import com.linkwee.web.model.weixin.WeiXinMsgRequest;

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
	String updateWeiXinAccToken();
	
	/**
	 * 给微信端推送消息
	 * */
	void sendWeiXinMsg(WeiXinMsgRequest req);
	
	/**
	 * 给微信端推送消息(公共)
	 * */
	void sendWeiXinMsgCommon(WeiXinMsgRequest req);

	/**
     * 猎财大师-更新微信accToken
     * */
	String updateWeiXinAccTokenLieCai();

	/**
     * 猎财大师-后端发放奖励
     * */
	void sendGrantRewardList(List<AcOfflineRewardDraft> list);
	
	/**
	 * 给微信端推送消息(公共)
	 * */
	void sendWeiXinMsgListCommon(List<WeiXinMsgRequest> req);
}
