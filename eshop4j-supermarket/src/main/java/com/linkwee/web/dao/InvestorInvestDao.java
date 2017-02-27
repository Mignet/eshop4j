package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.web.model.crm.InvestRecordResp;
import com.linkwee.web.model.crm.InvestorInvestResp;
import com.linkwee.web.request.ListDetailRequest;

/**
 * 
 * @描述： 理财师相关查询
 * 
 * @创建人：ch
 * 
 * @创建时间：2016年04月13日 17:27:15
 * 
 *  Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
	
public interface InvestorInvestDao  {

	/**
	 * 获取邀请的好友列表数据
	 * @param dataTable
	 * @param investRecordRequest
	 * @return
	 */
	public List<InvestorInvestResp> selectInvestorInvest(@Param("query")ListDetailRequest req, RowBounds page);
	
	/**
	 * 获取邀请的好友列表数据
	 * @param dataTable
	 * @param investRecordRequest
	 * @return
	 */
	public List<InvestRecordResp> selectInvestRecord(@Param("query")ListDetailRequest req, RowBounds page);
    
}
