package com.linkwee.web.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.crm.CrmSalesOrg;
import com.linkwee.web.model.crm.OrgSalesDetailResp;
import com.linkwee.web.model.crm.SaleOrgAchiResp;
import com.linkwee.web.model.crm.SalesOrgCfpResp;
import com.linkwee.web.model.crm.TeamStatisticalResponse;
import com.linkwee.web.request.LcsListRequest;
import com.linkwee.web.request.SaleOrgDetailRequest;
import com.xiaoniu.mybatis.paginator.domain.PageList;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月03日 15:12:23
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmSalesOrgMapper extends GenericDao<CrmSalesOrg,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CrmSalesOrg> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 机构列表
	 * @param pageRequest
	 * @param page
	 * @return
	 */
	public PageList<CrmSalesOrg> querySalesOrgList(@Param("query")LcsListRequest req, RowBounds page);

	/**
	 * 理财师列表
	 * @param req
	 * @param page
	 * @return
	 */
	List<SalesOrgCfpResp> querySalesOrgCfpList(@Param("query")SaleOrgDetailRequest req, RowBounds page);

	/**
	 * 机构销售明细
	 * @param req
	 * @param page
	 * @return
	 */
	List<OrgSalesDetailResp> querySalesDetailList(@Param("query")SaleOrgDetailRequest req, RowBounds page);

	/**
	 * 机构业绩
	 * @param salesOrgId
	 * @return
	 */
	SaleOrgAchiResp querySalesOrgAchiByNumber(String salesOrgId);

	/**
	 * 机构销售明细统计
	 * @param req
	 * @return
	 */
	TeamStatisticalResponse getSalesDetailListTotal(@Param("query")SaleOrgDetailRequest req);

	/**
	 * 机构有销售记录的理财师数量
	 * @param salesOrgId
	 * @return
	 */
	int querycfpOfInvestedCount(@Param("query")SaleOrgDetailRequest req);
	
	/**
	 * 获取存量
	 * @param salesOrgId
	 * @param req
	 * @return
	 */
	Map<String, BigDecimal> getStockYearpurAmt(@Param("query")SaleOrgDetailRequest req);

	/**
	 * 名称是否存在
	 * @param id
	 * @param name
	 * @return
	 */
	int checkNameExistForUpdate(@Param("id")Integer id, @Param("orgName")String orgName);

	/**
	 * 电话是否存在
	 * @param id
	 * @param contactMobile
	 * @return
	 */
	int checkMobileExistForUpdate(@Param("id")Integer id, @Param("contactMobile")String contactMobile);

	/**
	 * 管理帐号是否存在
	 * @param id
	 * @param managerAccount
	 * @return
	 */
	int checkAccountExistForUpdate(@Param("id")Integer id, @Param("managerAccount")String managerAccount);
	
	/**
	 * 查销售结构上月存量
	 * @param salesOrgId
	 * @return
	 */
	Map<String, BigDecimal> queryStockYearpurAmtBySalesOrgId(@Param("salesOrgId")String salesOrgId);
	
	
}
