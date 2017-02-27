package com.eshop4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.cim.CimOrgFeeRecord;
import com.eshop4j.web.model.cim.CimOrgFeeRuleDetail;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月09日 18:26:40
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgFeeRecordMapper extends GenericDao<CimOrgFeeRecord,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimOrgFeeRecord> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 根据平台编码查询平台配置的销售收费规则
	 * @param orgNumber
	 * @return
	 */
	List<CimOrgFeeRuleDetail> queryOrgFeeDetail(@Param("orgNumber")String  orgNumber);
	
	/**
	 * 根据平台编码查询平台配置的收费模式信息
	 * @param orgNumber
	 * @return
	 */
	List<CimOrgFeeRuleDetail> queryOrgFeeInfo(@Param("orgNumber")String  orgNumber);
	
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
	public void insertBatchFee(List<CimOrgFeeRecord> feeRecordList);
	
	/**
	 * 收费记录批量更新
	 * @author yalin 
	 * @date 2016年9月19日 下午4:57:20  
	 * @param feeRecordList
	 */
	public void updateBatchFee(List<CimOrgFeeRecord> feeRecordList);
	
	
	/**
	 * 收费记录批量删除
	 * @author yalin 
	 * @date 2016年9月23日 下午2:40:40  
	 * @param feeRecordList
	 */
	public void deleteBatchFee(List<CimOrgFeeRecord> feeRecordList);
	
	/**
	 * 机构收费记录按收费类型批量删除 
	 * @author yalin 
	 * @date 2016年9月23日 下午5:08:27  
	 * @param feeAttr
	 */
	public void deleteBatchFeeForFeeAttrByOrgnumber(@Param("feeAttr") String feeAttr,@Param("orgNumber")String  orgNumber);
}
