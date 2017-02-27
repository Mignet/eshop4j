package com.eshop4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.crm.ActivityRewardListResp;
import com.eshop4j.web.model.crm.AllowanceDetailListResp;
import com.eshop4j.web.model.crm.CfpFeeDetailResp;
import com.eshop4j.web.model.crm.CfplannerSaleResp;
import com.eshop4j.web.model.crm.CustomerInvestListResp;
import com.eshop4j.web.request.ListDetailRequest;

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
	
public interface CfplannerSaleDao  {

	/**
	 * 理财师销售与收益列表
	 * @param dataTable
	 * @param req
	 * @return
	 */
	public List<CfplannerSaleResp> selectCfplannerSaleList(@Param("query")ListDetailRequest req, RowBounds page);
	

	/**
	 * 佣金明细
	 * @param req
	 * @param page
	 * @return
	 */
	public List<CfpFeeDetailResp> selectFeeDetailList(@Param("query")ListDetailRequest req, Page<CfpFeeDetailResp> page);

	/**
	 * 推荐收益明细
	 * @param req
	 * @param page
	 * @return
	 */
	public List<AllowanceDetailListResp> selectAllowanceDetailList(@Param("query")ListDetailRequest req, Page<AllowanceDetailListResp> page);

	/**
	 * 活动奖励明细
	 * @param req
	 * @param page
	 * @return
	 */
	public List<ActivityRewardListResp> selectActivityRewardList(@Param("query")ListDetailRequest req, Page<ActivityRewardListResp> page);

	/**
	 * 客户投资明细
	 * @param req
	 * @param page
	 * @return
	 */
	public List<CustomerInvestListResp> selectCustomerInvestList(@Param("query")ListDetailRequest req, Page<CustomerInvestListResp> page);
    
}
