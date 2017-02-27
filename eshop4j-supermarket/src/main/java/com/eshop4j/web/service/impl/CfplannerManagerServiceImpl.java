package com.eshop4j.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.dao.CfplannerManagerDao;
import com.eshop4j.web.dao.TorginfoDao;
import com.eshop4j.web.enums.CfplannerOperationType;
import com.eshop4j.web.model.CrmCfplanner;
import com.eshop4j.web.model.CrmInvestor;
import com.eshop4j.web.model.crm.CfpManagerDetailResp;
import com.eshop4j.web.model.crm.CrmCfplannerOperation;
import com.eshop4j.web.response.CfpCustomerProfitListResp;
import com.eshop4j.web.response.CfpTeamListResp;
import com.eshop4j.web.service.CfplannerManagerService;
import com.eshop4j.web.service.CrmCfplannerOperationService;
import com.eshop4j.web.service.CrmCfplannerService;
import com.eshop4j.web.service.CrmInvestorService;
import com.eshop4j.web.service.CrmUserInfoService;


@Service("cfplannerManagerService")
public class CfplannerManagerServiceImpl implements CfplannerManagerService {
	
	@Resource
	private CfplannerManagerDao cfplannerManagerDao;

	@Resource
	private TorginfoDao torginfoDao;
	
	@Resource
	private CrmCfplannerService crmCfplannerService;

	@Resource
	private CrmInvestorService crmInvestorService;

	@Resource
	private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private CrmCfplannerOperationService crmCfplannerOperationService;
	

	@Override
	public CfpManagerDetailResp queryLcsDetail(String mobile) {
		CfpManagerDetailResp resp = cfplannerManagerDao.queryLcsDetail(mobile);
		if(resp == null){
			return null;
		}
		Date disabledLoginEndTime = DateUtils.addDay(resp.getDisabledLoginTime(), 90);
		resp.setDisabledLoginEndTime(disabledLoginEndTime);
		//更换上级记录
		List<CrmCfplannerOperation> changeParentRecordList = queryChangeParentRecordList(resp.getUserId());
		resp.setChangeParentRecordList(changeParentRecordList);
		return resp;
	}

	@Override
	public CfpManagerDetailResp queryLcsInfo(String moible) {
		if(StringUtils.isNotBlank(moible)){
			CfpManagerDetailResp resp = cfplannerManagerDao.queryLcsDetail(moible);
			return resp;
		}
		return null;
	}

	/**
	 * 更换上级记录
	 * @param number
	 * @return
	 */
	private List<CrmCfplannerOperation> queryChangeParentRecordList(String userId) {
		List<CrmCfplannerOperation> list = crmCfplannerOperationService.queryChangeParentRecordList(userId);
		return list;
	}

	@Override
	@Transactional
	public void exitLcs(CrmCfplanner crmCfplanner) {
		// 操作记录
		CrmCfplannerOperation operation = new CrmCfplannerOperation();
		operation.setCfplanner(crmCfplanner.getUserId());
		operation.setCreateTime(new Date());
		operation.setLastUpdateTime(new Date());
		operation.setOperationAdmin("admin");
		operation.setParentId(crmCfplanner.getParentId());
		operation.setRemarks(CfplannerOperationType.QUIT_CFPLANNER.getMessage());
		operation.setType(CfplannerOperationType.QUIT_CFPLANNER.getCode());
		crmCfplannerOperationService.insert(operation);
		
		//将上级设置为理财师
		CrmInvestor crmInvestor = new CrmInvestor();
		crmInvestor.setUserId(crmCfplanner.getUserId());
		crmInvestor.setCfplanner(crmCfplanner.getParentId());
		crmInvestorService.updateByUserId(crmInvestor);

		//删除理财师帐号数据
		crmCfplannerService.delete((long)crmCfplanner.getId());
	}

	@Override
	public boolean removeCfplannerHeadImage(String mobile) throws Exception {
		return cfplannerManagerDao.removeCfplannerHeadImage(mobile)>0;
	}
	
	/**
	 * 更换上级
	 * @param mobile
	 * @param parentMobile
	 * @param changeType
	 */
	@Override
	public void changeParent(String mobile, String parentMobile, String changeType, CrmCfplanner saleUserInfo) {
		CrmCfplanner saleUserForUpdate = new CrmCfplanner();
		if("1".equals(changeType)) {
			//更改新上级
			CrmCfplanner parentNew = crmCfplannerService.queryCfplannerByInvestMobile(parentMobile);
			if(parentNew == null){
				return;
			}
			saleUserForUpdate.setUserId(saleUserInfo.getUserId());
			saleUserForUpdate.setParentId(parentNew.getUserId());
			crmCfplannerService.updateByUserId(saleUserForUpdate);
			//操作记录
			CrmCfplannerOperation operation = new CrmCfplannerOperation();
			operation.setCfplanner(saleUserInfo.getUserId());
			operation.setParentId(parentNew.getUserId());
			operation.setCreateTime(new Date());
			operation.setLastUpdateTime(new Date());
			operation.setOperationAdmin("admin");
			operation.setRemarks(CfplannerOperationType.CHANGE_PARENT.getMessage());
			operation.setType(CfplannerOperationType.CHANGE_PARENT.getCode());
			crmCfplannerOperationService.insert(operation);
			
		} else if ("2".equals(changeType)) {
			//变为一级理财师
			saleUserForUpdate.setUserId(saleUserInfo.getUserId());
			saleUserForUpdate.setParentId("");
			crmCfplannerService.updateByUserId(saleUserForUpdate);
			//操作记录
			CrmCfplannerOperation operation = new CrmCfplannerOperation();
			operation.setCfplanner(saleUserInfo.getUserId());
			operation.setParentId(saleUserInfo.getParentId());
			operation.setCreateTime(new Date());
			operation.setLastUpdateTime(new Date());
			operation.setOperationAdmin("admin");
			operation.setRemarks(CfplannerOperationType.REMOVE_PARENT.getMessage());
			operation.setType(CfplannerOperationType.REMOVE_PARENT.getCode());
			crmCfplannerOperationService.insert(operation);
		}
	}

	@Override
	public boolean hasCustomerOrTeam(String userId) {
		if(crmCfplannerService.queryTeamMemberCount(userId) > 0) {
			return true;
		}
		if(crmCfplannerService.queryCustomerCount(userId) > 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 查询理财师客户列表
	 * @param detailResp
	 * @param dataTable
	 * @return
	 */
	@Override
	public DataTableReturn queryCfpCustomerProfitList(CfpManagerDetailResp detailResp, DataTable dataTable) {
		DataTableReturn dataTableReturn = new DataTableReturn();
		if(null == detailResp || StringUtils.isBlank(detailResp.getUserId())){
			dataTableReturn.setData(new ArrayList<Object>());
			dataTableReturn.setDraw(0);
			dataTableReturn.setRecordsFiltered(0);
			dataTableReturn.setRecordsTotal(0);
			return  dataTableReturn;
		}
		Page<CfpCustomerProfitListResp> page = new Page<CfpCustomerProfitListResp>(dataTable.getStart() / dataTable.getLength()+1,dataTable.getLength());
		List<CfpCustomerProfitListResp> cfpCustomerProfitListRespList = cfplannerManagerDao.queryCfpCustomerProfitList(detailResp,page);
        dataTableReturn.setData(cfpCustomerProfitListRespList);
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		return dataTableReturn;
	}

	/**
	 * 查询理财师团队列表
	 * @param lcsDetailResp
	 * @param dataTable
	 * @return
	 */
	@Override
	public DataTableReturn queryCfpTeamList(CfpManagerDetailResp lcsDetailResp, DataTable dataTable) {
		DataTableReturn dataTableReturn  = new DataTableReturn();
		if(null == lcsDetailResp || StringUtils.isBlank(lcsDetailResp.getUserId())){
			dataTableReturn.setData(new ArrayList<Object>());
			dataTableReturn.setDraw(0);
			dataTableReturn.setRecordsFiltered(0);
			dataTableReturn.setRecordsTotal(0);
			return  dataTableReturn;
		}
		Page<CfpTeamListResp> page  = new Page<CfpTeamListResp>(dataTable.getStart() / dataTable.getLength() +1,dataTable.getLength());
		List<CfpTeamListResp> cfpTeamListRespList = cfplannerManagerDao.queryCfpTeamList(lcsDetailResp,page);
		dataTableReturn.setData(cfpTeamListRespList);
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		return dataTableReturn;
	}
	
	
	@Override
	public Map<String, Object> getLcsDateStaticCount() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("lcs", cfplannerManagerDao.getLcsDateStaticCount());
		map.put("lcsValid", cfplannerManagerDao.getValidLcsDateStaticCount());
		return map;
	}

	@Override
	public Map<String, Object> getLcsDataStatic(Map<String, Object> map) {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
		Date start = (Date)map.get("startDate");
		Date end = (Date)map.get("endDate");
		int searchDay = DateUtils.countDays(start,end)+1;
		
		Map<String, Object> lcs= new LinkedHashMap<String, Object>();
		//lcs.put("total", lcsDataViewDao.getLcsDateStaticTotal(map));
		List<Map<String, Object>> lcsData = cfplannerManagerDao.getLcsDateStatic(map);
		
		List<Object> list =null;
		
		if(lcsData.size() < searchDay){
			Map<String,Object> dataMap = new LinkedHashMap<String, Object>();
			for(Map<String,Object> item:lcsData){
				dataMap.put(item.get("date").toString(),item);
			}
			list = new ArrayList<Object>();
			List<String> dates = DateUtils.getBetweeenTime(start, end, "yyyy-MM-dd");
			for(String date:dates){
				if(!dataMap.containsKey(date)){
					Map<String,Object> temp = new HashMap<String,Object>();
					
					temp.put("date", date);
					temp.put("count", 0);
					list.add(temp);
				}else{
					list.add(dataMap.get(date));
				}
			}
		}
		
		lcs.put("data",list==null?lcsData:list);
		
		
		data.put("lcs", lcs);
		
		lcs= new LinkedHashMap<String, Object>();
		
		//lcs.put("total", lcsDataViewDao.getValidLcsDateStaticTotal(map));
		List<Map<String, Object>> validLcsDate = cfplannerManagerDao.getValidLcsDateStatic(map);
		
		
	
		list =null;
		
		if(validLcsDate.size() < searchDay){
			
			Map<String,Object> dataMap = new LinkedHashMap<String, Object>();
			for(Map<String,Object> item:validLcsDate){
				dataMap.put(item.get("date").toString(),item);
			}
			list = new ArrayList<Object>();
			List<String> dates = DateUtils.getBetweeenTime(start, end, "yyyy-MM-dd");
			for(String date:dates){
				if(!dataMap.containsKey(date)){
					Map<String,Object> temp = new HashMap<String,Object>();
					
					temp.put("date", date);
					temp.put("count", 0);
					list.add(temp);
				}else{
					list.add(dataMap.get(date));
				}
			}
		}
		
		lcs.put("data",list==null?validLcsDate:list);
		data.put("lcsValid", lcs);
		return data;
	}
	

}
