package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.CimOrgFeeRecordMapper;
import com.linkwee.web.model.cim.CimOrgFeeRecord;
import com.linkwee.web.model.cim.CimOrgFeeRuleDetail;
import com.linkwee.web.model.cim.CimOrginfoWeb;
import com.linkwee.web.request.orgInfo.CimOrgFeeRequest;
import com.linkwee.web.service.CimOrgFeeRecordService;
import com.linkwee.web.service.CimOrginfoService;


 /**
 * 
 * @描述：CimOrgFeeRecordService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月26日 15:17:13
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgFeeRecordService")
public class CimOrgFeeRecordServiceImpl extends GenericServiceImpl<CimOrgFeeRecord, Long> implements CimOrgFeeRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgFeeRecordServiceImpl.class);
	
	@Resource
	private CimOrgFeeRecordMapper cimOrgFeeRecordMapper;
	
	@Resource
	private CimOrginfoService cimOrginfoService; //机构服务
	
	@Override
    public GenericDao<CimOrgFeeRecord, Long> getDao() {
        return cimOrgFeeRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgFeeRecord -- 排序和模糊查询 ");
		Page<CimOrgFeeRecord> page = new Page<CimOrgFeeRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgFeeRecord> list = this.cimOrgFeeRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CimOrgFeeRuleDetail> queryOrgFeeDetail(String orgNumber) {
		return cimOrgFeeRecordMapper.queryOrgFeeDetail(orgNumber);
	}

	@Override
	public int queryOrgFeeRuleId(String feeAttr) {
		return cimOrgFeeRecordMapper.queryOrgFeeRuleId(feeAttr);
	}

	@Override
	public void insertBatchFee(List<CimOrgFeeRecord> feeRecordList,CimOrginfoWeb cimOrginfo){
		cimOrgFeeRecordMapper.insertBatchFee(feeRecordList);
		//更新机构表
		cimOrginfoService.updateByOrgNumber(cimOrginfo);
	}

	@Override
	public void updateBatchFee(List<CimOrgFeeRecord> feeRecordList,CimOrginfoWeb cimOrginfo,CimOrgFeeRequest request) {
		List<CimOrgFeeRecord> newFeeRecordList = new ArrayList<CimOrgFeeRecord>();//cpa,cps新增的收费区间
		
		if(feeRecordList != null){
			for(CimOrgFeeRecord fee : feeRecordList){
				if(fee.getId() == null){
					newFeeRecordList.add(fee); //id为空的收费记录保存到新集合中  执行批量插入
				}
			}
			
			if(newFeeRecordList.size() > 0){
				feeRecordList.removeAll(newFeeRecordList); //移除id为空的收费记录  剩下的记录执行批量更新
			}
		}
		
		/**
		 * 执行批量新增
		 */
		if(newFeeRecordList != null && newFeeRecordList.size() > 0){
			cimOrgFeeRecordMapper.insertBatchFee(newFeeRecordList);
		}
		
		
		/**
		 * cpa未被选中
		 */
		if(StringUtils.isBlank(request.getCpaFeeType())){
			CimOrginfoWeb orgFeeInfo = cimOrginfoService.queryOrgFeeInfo(request.getOrgNumber());//查询机构收费模式
			//删除cpa收费模式记录
			cimOrgFeeRecordMapper.deleteBatchFeeForFeeAttrByOrgnumber("cpa",request.getOrgNumber());
			orgFeeInfo.setOrgAmountLimit(new BigDecimal(0.00));
			orgFeeInfo.setOrgInvestdeadlineLimit(0);
			if(orgFeeInfo.getOrgFeeType() == 1){
				orgFeeInfo.setOrgFeeType(2); //cps
			}
			orgFeeInfo.setUpdateTime(new Date());
			//更新机构表
			cimOrginfoService.updateByOrgNumber(orgFeeInfo);
		}else{
			//更新机构表
			cimOrginfoService.updateByOrgNumber(cimOrginfo);
			
			if(request.getCpaFeeAttr().equals("float_fixed")){ //如果选中cpa 首投区间单选框收费
				if(request.getId() != null){
					cimOrgFeeRecordMapper.deleteByPrimaryKey(Long.valueOf(request.getId())); //删除之前选中的收费类型
				}
				
				/**
				 * 执行批量更新
				 */
				if(!feeRecordList.isEmpty() && feeRecordList.size() > 0){
					cimOrgFeeRecordMapper.updateBatchFee(feeRecordList);
				}
				//return;
			}else if(request.getCpaFeeAttr().equals("fixed")){
				/**第一个 第二个单选框之间互选*/
				if(request.getId() != null && !feeRecordList.isEmpty() && feeRecordList.size() > 0){
					cimOrgFeeRecordMapper.updateBatchFee(feeRecordList); //执行更新
					//return;
				}else{
					cimOrgFeeRecordMapper.deleteBatchFeeForFeeAttrByOrgnumber("cpa按首投金额区间",request.getOrgNumber());
				}
			}else if(request.getCpaFeeAttr().equals("propertion")){
				/**第一个 第二个单选框之间互选*/
				if(request.getId() != null && !feeRecordList.isEmpty() && feeRecordList.size() > 0){
					cimOrgFeeRecordMapper.updateBatchFee(feeRecordList); //执行更新
					//return;
				}else{
					cimOrgFeeRecordMapper.deleteBatchFeeForFeeAttrByOrgnumber("cpa按首投金额区间",request.getOrgNumber());
				}
			}
		}
		
		/**
		 * cps未被选中
		 */
		if(StringUtils.isBlank(request.getCpsFeeType())){
			CimOrginfoWeb orgFeeInfo = cimOrginfoService.queryOrgFeeInfo(request.getOrgNumber());//查询机构收费模式
			//删除cps收费模式记录
			cimOrgFeeRecordMapper.deleteBatchFeeForFeeAttrByOrgnumber("cps",request.getOrgNumber());
			if(orgFeeInfo.getOrgFeeType() == 2){
				orgFeeInfo.setOrgFeeType(1); //cpa
			}
			orgFeeInfo.setUpdateTime(new Date());
			//更新机构表
			cimOrginfoService.updateByOrgNumber(orgFeeInfo);
		}else{
			//更新机构表
			cimOrginfoService.updateByOrgNumber(cimOrginfo);
			/**
			 * 执行批量更新
			 */
			if(!feeRecordList.isEmpty() && feeRecordList.size() > 0){
				cimOrgFeeRecordMapper.updateBatchFee(feeRecordList);
			}
			
			if(request.getCpsFeeAttr().equals("year_propertion")){ //cps按产品期限收取
				/**
				 * 批量删除月销售总额记录
				 */
				cimOrgFeeRecordMapper.deleteBatchFeeForFeeAttrByOrgnumber("cps按月销售总额收取",request.getOrgNumber()); 
				
				
			}else if(request.getCpsFeeAttr().equals("month_amount_propertion")){ //cps按月销售总额收取
				/**
				 * 批量删除新增产品期限记录
				 */
				cimOrgFeeRecordMapper.deleteBatchFeeForFeeAttrByOrgnumber("cps按产品期限收取",request.getOrgNumber());
				
			}
			
			
		}
		
		
		
	}

	@Override
	public List<CimOrgFeeRuleDetail> queryOrgFeeInfo(String orgNumber) {
		return cimOrgFeeRecordMapper.queryOrgFeeInfo(orgNumber);
	}

}
