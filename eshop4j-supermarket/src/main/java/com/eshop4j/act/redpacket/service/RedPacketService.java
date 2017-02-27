package com.eshop4j.act.redpacket.service;

import java.util.List;

import com.eshop4j.api.request.act.RedpacketRequest;
import com.eshop4j.api.request.act.SendRedPacketRequest;
import com.eshop4j.api.response.act.RedpacketResponse;
import com.eshop4j.api.response.cim.ProductDetailResponse;
import com.eshop4j.api.response.cim.ProductPageListResponse;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.enums.ActicityRedPacketEnum;
import com.eshop4j.web.model.CimOrginfo;
import com.eshop4j.web.model.CrmUserInfo;

public interface RedPacketService {
	
	/**
	 * 查询客户可用红包数量
	 * @param userId
	 * @return
	 */
	Integer queryInvestorRedPacketCount(String userId);
	

	
	/**
	 * 查询客户红包信息 
	 * @param userId
	 * @param req
	 * @return
	 */
	PaginatorResponse<RedpacketResponse> queryInvestorRedPacket(String userId, RedpacketRequest req); 
	
	
	/**
	 * 查询理财师可派发红包数量
	 * @param userId
	 * @return
	 */
	Integer queryCfplannerRedPacketCount(String userId);
	
	/**
	 * 查询理财师红包信息
	 * @param userId
	 * @param req
	 * @return
	 */
	PaginatorResponse<RedpacketResponse> queryCfplannerRedPacket(String userId,RedpacketRequest req);
	
	/**
	 * 理财师发放红包
	 * @param userId
	 * @param sendRedPacketRequest
	 * @return
	 */
	BaseResponse sendRedPacket(String userId,SendRedPacketRequest sendRedPacketRequest) throws Exception;
	

	
	/**
	 * 客户注册送红包
	 * @param userInfo
	 */
	 void  customerRegisterRedPacekt(CrmUserInfo userInfo)throws Exception;
	 

	 
	 /**
	  * 理财师奖励红包
	  * @param userInfo
	  * @throws Exception
	  */
	 void lcsActicityRedPacket(String userId,ActicityRedPacketEnum acticityRedPacketEnum)throws Exception;
	 
	 /**
	  * 产品是否有红包标识
	  * @param products
	  * @return
	  */
	 void productRedPacketTag(List<ProductPageListResponse> products,String userId);
	 
	 /**
	  * 平台是否有红包标识
	  * @param products
	  * @return
	  */
	 void patformRedPacketTag(List<CimOrginfo> orgInfo,String userId);
	 
	 /**
	  * 产品红包数量
	  * @param products
	  * @return
	  */
	 int productRedPacketCount(ProductDetailResponse productDetail ,String userId);
	 
	 /**
	  * 产品红包
	  * @param userId
	  * @param req
	  * @param page
	  * @return
	  */
	 List<RedpacketResponse> productRedPacket(String userId,RedpacketRequest req,Page<RedpacketResponse> page);
	 
	 /**
	  * 平台红包数量
	  * @param products
	  * @return
	  */
	 int patformRedPacketCount(String patform,int model, String userId);
	 
	 /**
	  * 平台红包
	  * @param userId
	  * @param req
	  * @param page
	  * @return
	  */
	 List<RedpacketResponse> patformRedPacket(String userId,RedpacketRequest req,Page<RedpacketResponse> page);
}

