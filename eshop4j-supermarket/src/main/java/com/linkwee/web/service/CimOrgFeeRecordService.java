package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.cim.CimOrgFeeRecord;
import com.linkwee.web.model.cim.CimOrgFeeRuleDetail;
import com.linkwee.web.model.cim.CimOrginfoWeb;
import com.linkwee.web.request.orgInfo.CimOrgFeeRequest;
 /**
 * 
 * @描述： CimOrgFeeRecordService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月26日 15:17:13
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgFeeRecordService extends GenericService<CimOrgFeeRecord,Long>{

	/**
	 * 查询CimOrgFeeRecord列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 根据平台编码查询平台配置的销售收费规则
	 * @param orgNumber
	 * @return
	 */
	public List<CimOrgFeeRuleDetail> queryOrgFeeDetail(String  orgNumber);
	
	/**
	 * 查询机构收费规则id
	 * @author yalin 
	 * @date 2016年9月19日 下午4:10:06  
	 * @param feeAttr
	 * @return
	 */
	public int queryOrgFeeRuleId(String feeAttr);
	
	/**
	 * 收费记录批量插入
	 * @author yalin 
	 * @date 2016年9月19日 下午4:57:20  
	 * @param feeRecordList
	 */
	public void insertBatchFee(List<CimOrgFeeRecord> feeRecordList,CimOrginfoWeb cimOrginfo);
	
	/**
	 * 收费记录批量更新
	 * @author yalin 
	 * @date 2016年9月19日 下午4:57:20  
	 * @param feeRecordList
	 */
	public void updateBatchFee(List<CimOrgFeeRecord> feeRecordList,CimOrginfoWeb cimOrginfo,CimOrgFeeRequest request);
	
	/**
	 * 根据平台编码查询平台配置的收费模式信息
	 * @param orgNumber
	 * @return
	 */
	public List<CimOrgFeeRuleDetail> queryOrgFeeInfo(String  orgNumber);
	
}
