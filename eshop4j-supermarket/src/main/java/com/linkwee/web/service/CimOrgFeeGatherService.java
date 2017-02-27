package com.linkwee.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.cim.CimOrgFeeGather;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.model.vo.OrgSaleFeeData;
import com.linkwee.web.service.CimOrgFeeGatherService;
 /**
 * 
 * @描述： CimOrgFeedetailService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月27日 18:06:58
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgFeeGatherService extends GenericService<CimOrgFeeGather,Long>{

	/**
	 * 查询CimOrgFeedetail列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 设定机构销售订单费用详情表回款时间
	 */
	Integer setRepaymentTime(String investId,Date repaymentTime);
	
	public Boolean IsFirstInvest(InvestRecordWrapper investRecord);
	
	/**
	 * 查询后台管理 -产品投资统计-总体数据
	 * @param params
	 * @param dataTable
	 * @return
	 * @throws Exception
	 */
	public DataTableReturn queryOrgSaleFee(Map<String,Object> params, DataTable dataTable) throws Exception;	
	Double queryInvestAmount(Map<String,Object> params) throws Exception;
	List<OrgSaleFeeData> queryOrgSaleFee(Map<String,Object> params) throws Exception;
}
