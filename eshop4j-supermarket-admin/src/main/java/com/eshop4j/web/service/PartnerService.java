package com.eshop4j.web.service;

import java.util.Date;
import java.util.Map;

import com.eshop4j.core.base.PaginatorSevResp;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.crm.PartnerDetailResp;
import com.eshop4j.web.model.crm.PartnerListResp;
import com.eshop4j.web.model.crm.PartnerSaleRecordResp;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface PartnerService {

	/**
	 * 理财师直接推荐人数
	 * @param userId
	 * @return
	 */
	Integer queryMyRcCount(String userId);

	/**
	 * 理财师间接推荐人数
	 * @param userId
	 * @return
	 */
	Integer queryChildrenRcCount(String userId);

	/**
	 * 团队列表
	 * @param pageRequest
	 * @return
	 */
	PaginatorSevResp<PartnerListResp> queryPartnerList(Map<String, Object> query, Page<PartnerListResp> page);

	/**
	 * 团队成员详情
	 * @param userId
	 * @return
	 */
	PartnerDetailResp queryDetailResp(String userId);

	/**
	 * 团队成员销售记录
	 * @param query
	 * @param page
	 * @return
	 */
	PaginatorSevResp<PartnerSaleRecordResp> queryPartnerSaleRecord(Map<String, Object> query,
			Page<PartnerSaleRecordResp> page);

	/**
	 * 未读团队成员数量
	 * @param userId
	 * @param date
	 * @return
	 */
	Integer queryNewPartnerCount(String userId, Date date);

	
	
}
