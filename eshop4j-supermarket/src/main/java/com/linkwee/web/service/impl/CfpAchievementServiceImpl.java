package com.linkwee.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CfpAchievementDao;
import com.linkwee.web.dao.TorginfoDao;
import com.linkwee.web.model.crm.CfpAchiAnalysisResp;
import com.linkwee.web.model.crm.CfpAchievementResp;
import com.linkwee.web.request.ListDetailRequest;
import com.linkwee.web.service.CfpAchievementService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorOperationService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;

@Service("cfpAchievementService")
public class CfpAchievementServiceImpl implements CfpAchievementService {

	@Resource
	private CfpAchievementDao cfpAchievementDao;

	@Resource
	private TorginfoDao torginfoDao;

	@Resource
	private CrmCfplannerService crmCfplannerService;

	@Resource
	private CrmInvestorService crmInvestorService;

	@Resource
	private CrmUserInfoService crmUserInfoService;

	@Resource
	private CrmInvestorOperationService crmInvestorOperationService;


	
	@Override
	public DataTableReturn selectCfpAchievement(DataTable dataTable, ListDetailRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<CfpAchievementResp> page = new Page<CfpAchievementResp>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<CfpAchievementResp> list = this.cfpAchievementDao.selectCfpAchievement(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}



	@Override
	public List<CfpAchievementResp> selectCfpAchievement(ListDetailRequest req) {
		List<CfpAchievementResp> list = this.cfpAchievementDao.selectCfpAchievement(req);
		return list;
	}



	@Override
	public List<Map<String, Object>> queryCfpAreaData() {
		return cfpAchievementDao.queryCfpAreaData();
	}



	@Override
	public List<Map<String, Object>> queryCfpCustomerCountData() {
		return cfpAchievementDao.queryCfpCustomerCountData();
	}



	@Override
	public List<Map<String, Object>> queryCfpYearAchiData(Map<String, Object> paramsMap) {
		return cfpAchievementDao.queryCfpYearAchiData(paramsMap);
	}
	
	@Override
	public DataTableReturn queryCfpOfCustomerCount(DataTable dataTable, ListDetailRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<CfpAchiAnalysisResp> page = new Page<CfpAchiAnalysisResp>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<CfpAchiAnalysisResp> list = this.cfpAchievementDao.queryCfpOfCustomerCount(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}



	@Override
	public DataTableReturn queryCfpAchiValueList(DataTable dataTable, ListDetailRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<CfpAchiAnalysisResp> page = new Page<CfpAchiAnalysisResp>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<CfpAchiAnalysisResp> list = this.cfpAchievementDao.queryCfpAchiValueList(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
	

}
