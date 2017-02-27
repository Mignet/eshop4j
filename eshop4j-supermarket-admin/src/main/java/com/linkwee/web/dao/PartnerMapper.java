package com.linkwee.web.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.base.BaseDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.crm.PartnerListResp;
import com.linkwee.web.model.crm.PartnerSaleRecordResp;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface PartnerMapper  extends BaseDao {

	/**
	 * 直接推荐人数
	 * @param userId
	 * @return
	 */
	public Integer queryMyRcCount(String userId);
	
	/**
	 * 间接推荐人数
	 * @param userId
	 * @return
	 */
	public Integer queryChildrenRcCount(String userId);
	
	/**
	 * 团队列表
	 * @param pageRequest
	 * @return
	 */
	public List<PartnerListResp> queryPartnerList(Map<String, Object> query, Page<PartnerListResp> page);

	/**
	 * 理财师销售首单时间
	 * @param userId
	 * @return
	 */
	public Date queryFirstRcpDate(@Param("parentId") String parentId, @Param("userId") String userId);

	/**
	 * 直接津贴
	 * @param parentId
	 * @param userId
	 * @return
	 */
	public Double queryAllowance(@Param("parentId") String parentId, @Param("userId") String userId);

	/**
	 * 间接津贴
	 * @param parentId
	 * @param userId
	 * @return
	 */
	public Double queryChildrenAllowance(@Param("parentId") String parentId, @Param("userId") String userId);

	/**
	 * 团队成员销售记录
	 * @param query
	 * @param page
	 * @return
	 */
	public List<PartnerSaleRecordResp> queryPartnerSaleRecord(Map<String, Object> query, Page<PartnerSaleRecordResp> page);

	/**
	 * 未读团队成员数量
	 * @param userId
	 * @param date
	 * @return
	 */
	public Integer queryNewPartnerCount(@Param("userId") String userId, @Param("date") Date date);
	
}
