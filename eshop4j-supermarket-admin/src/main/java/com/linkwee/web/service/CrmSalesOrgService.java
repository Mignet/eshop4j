package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.crm.CrmSalesOrg;
import com.linkwee.web.model.crm.SaleOrgAchiResp;
import com.linkwee.web.model.crm.TeamStatisticalResponse;
import com.linkwee.web.request.LcsListRequest;
import com.linkwee.web.request.SaleOrgDetailRequest;
 /**
 * 
 * @描述： CrmSalesOrgService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月03日 15:12:23
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmSalesOrgService extends GenericService<CrmSalesOrg,Long>{

	/**
	 * 查询CrmSalesOrg列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 机构列表
	 * @param request
	 * @return
	 */
	public DataTableReturn querySalesOrgList(DataTable dataTable, LcsListRequest req);

	/**
	 * 理财师列表
	 * @param request
	 * @return
	 */
	DataTableReturn querySalesOrgCfpList(DataTable dataTable, SaleOrgDetailRequest req);

	/**
	 * 销售业绩明细
	 * @param request
	 * @return
	 */
	DataTableReturn querySalesDetailList(DataTable dataTable, SaleOrgDetailRequest req);

	/**
	 * 机构业绩
	 * @param salesOrgId
	 * @return
	 */
	SaleOrgAchiResp querySalesOrgAchiByNumber(String salesOrgId);

	/**
	 * 销售业绩明细
	 * @param req
	 * @return
	 */
	TeamStatisticalResponse getSalesDetailListTotal(SaleOrgDetailRequest req);

	/**
	 * 机构有销售记录的理财师数量
	 * @param salesOrgId
	 * @return
	 */
	int querycfpOfInvestedCount(SaleOrgDetailRequest req);

	/**
	 * 检查名称是否已存在
	 * @param id
	 * @param name
	 * @return
	 */
	boolean checkNameExistForUpdate(Integer id, String name);

	/**
	 * 电话是否已存在
	 * @param id
	 * @param contactMobile
	 * @return
	 */
	boolean checkMobileExistForUpdate(Integer id, String contactMobile);

	/**
	 * 管理账户是否存在
	 * @param id
	 * @param managerAccount
	 * @return
	 */
	boolean checkAccountExistForUpdate(Integer id, String managerAccount);
	
	
}
