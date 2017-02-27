package com.linkwee.act.redpacket.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.linkwee.act.redpacket.model.ActRedpacket;
import com.linkwee.act.redpacket.model.ActRedpacketDetail;
import com.linkwee.act.redpacket.model.SendContext;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.enums.CfpNewcomerTaskEnum;
import com.linkwee.web.enums.InvestorNewcomerTaskEnum;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.request.act.RedPacketInfoRequest;
import com.linkwee.web.response.act.RedpacketStatisticsResponse;
 /**
 * 
 * @描述： ActRedpacketService服务接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年07月31日 13:13:55
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActRedpacketService extends GenericService<ActRedpacket,Long>{
	
	public interface SendRedpacketCallback{
		void setRedpacketAttr(CrmUserInfo userInfo,ActRedpacketDetail redpacket,Set<String> mobileOrUserIds)throws Exception;
		String getMsgContent(ActRedpacket redpacket,int sendNum);
		void sendMsgs(Set<String> mobiles,String content);
	}

	/**
	 * 红包是否存在
	 * @param redpacketId
	 * @return
	 */
	boolean isExistRedpacket(String redpacketId);
	
	/**
	 * 使用红包
	 * @param investRecord
	 * @param redpacketDetail
	 * @return
	 * @throws Exception
	 */
	boolean useRedpacket(InvestRecordWrapper investRecord,ActRedpacketDetail redpacketDetail)throws Exception;
	
	/**
	 * 获取红包列表
	 * @param page
	 * @return
	 */
	DataTableReturn getRedpacketList(DataTable dt);
	
	/**
	 * 添加红包
	 * @param redPacketInfo
	 */
	void insertRedpacket(RedPacketInfoRequest  redPacketInfo)throws Exception;
	
	/**
	 * 更新红包
	 * @param redPacketInfo
	 * @throws Exception
	 */
	void updateRedpacket(RedPacketInfoRequest  redPacketInfo)throws Exception;
	
	/**
	 * 根据红包编号获取红包
	 * @param redpacketId
	 * @return
	 */
	ActRedpacket getRedpacket(String redpacketId);
	
	/**
	 * 获取一组红包
	 * @param redpacketId
	 * @return
	 */
	List<ActRedpacket> getRedpackets(String... redpacketId);
	
	/**
	 * 是否发送
	 * @param redpacket
	 * @return
	 */
	boolean isSendRedpacket(String redpacket)throws Exception;
	
	/**
	 * 发送客户红包
	 * @param file 客户信息
	 * @param redpacketId 红包编号
	 * @param sendNum 发送数量
	 * @param expiresDate 红包过期日期
	 * @param operator 操作人
	 */
	Set<String> sendCustomerRedPacket(MultipartFile file,String redpacketId,Integer sendNum,Date expiresDate,String operator)throws Exception;
	
	/**
	 * 发送理财师红包
	 * @param file 理财师信息
	 * @param redpacketId 红包编号
	 * @param sendNum 发送数量
	 * @param expiresDate 红包过期日期
	 * @param operator 操作人
	 */
	Set<String> sendLcsRedPacket(MultipartFile file,String redpacketId,Integer sendNum,Date expiresDate,String operator)throws Exception;
	
	/**
	 * 获取红包信息
	 * @param redpacketId
	 * @return
	 */
	RedPacketInfoRequest getRedPacketInfo(String redpacketId);
	
	/**
	 * 根据日期获取红包统计信息
	 * @param date
	 * @return
	 */
	RedpacketStatisticsResponse getRedpacketStatistics(String date);
	
	/**
	 * 客户注册送红包
	 * @param userInfo
	 */
	 void  customerRegisterRedPacekt(CrmUserInfo userInfo)throws Exception;
	 
	 
	 /**
	  * 理财师任务红包
	  * @param taskEnum
	  * @param userInfo
	  * @throws Exception
	  */
	 void  lcsTaskRedPacekt(CfpNewcomerTaskEnum taskEnum,CrmUserInfo userInfo)throws Exception;
	 
	 /**
	  * 客户任务红包
	  * @param taskEnum
	  * @param userInfo
	  * @throws Exception
	  */
	 void  customerTaskRedPacekt(InvestorNewcomerTaskEnum taskEnum,CrmUserInfo userInfo)throws Exception;
	 
	 /**
	  * 理财师系统红包
	  * @param sendContext
	  * @throws Exception
	  */
	 void lcsSystemRedpacekt(SendContext sendContext) throws Exception;
	 
	 /**
	  * 理财师系统红包 
	  * @param sendContext
	  * @param msg
	  * @throws Exception
	  */
	 void lcsSystemRedpacekt(SendContext sendContext,String msg) throws Exception;
	 
	 /**
	  * 客户系统红包
	  * @param sendContext
	  * @throws Exception
	  */
	 void customerSystemRedpacekt(SendContext sendContext) throws Exception;
	
	 /**
	  * 客户系统红包
	  * @param sendContext
	  * @param msg
	  * @throws Exception
	  */
	 void customerSystemRedpacekt(SendContext sendContext,String msg) throws Exception;
	 
}
