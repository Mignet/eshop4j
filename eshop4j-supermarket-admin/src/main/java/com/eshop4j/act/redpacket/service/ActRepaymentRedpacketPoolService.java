package com.eshop4j.act.redpacket.service;

import java.util.List;

import com.eshop4j.act.redpacket.model.ActRepaymentRedpacketPool;
import com.eshop4j.core.base.ResponseResult;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.request.act.RedPacketTemplateInfoRequest;
 /**
 * 
 * @描述： ActRepaymentRedpacketPoolService服务接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年12月22日 22:14:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActRepaymentRedpacketPoolService extends GenericService<ActRepaymentRedpacketPool,Long>{

	void savePlatFormRedpacket(String redPacketTemplateId,String ids,Integer model,RedPacketTemplateInfoRequest redPacketInfo)throws Exception;
	
	void updatePlatFormRedpacketRule(String redPacketTemplateId,String ids,Integer model,RedPacketTemplateInfoRequest redPacketInfo)throws Exception;
	
	String getMainPlatFormByModel(Integer model);
	
	ResponseResult setMainPlatFormByModel(String platform,Integer model,String operator);
	
	List<ActRepaymentRedpacketPool> getRepaymentRedpackets(ActRepaymentRedpacketPool repaymentRedpacketPool);
	
	
	void updateStatus(String platform,Integer model,Integer status,Integer grayStatus,String operator);
}
