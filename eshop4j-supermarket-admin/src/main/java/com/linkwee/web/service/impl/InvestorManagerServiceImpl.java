package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.web.controller.crm.CfplannerManagerController;
import com.linkwee.web.dao.InvestorManagerDao;
import com.linkwee.web.dao.TorginfoDao;
import com.linkwee.web.enums.InvestorOperationType;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.CrmInvestorOperation;
import com.linkwee.web.model.FreindsResp;
import com.linkwee.web.model.crm.InvestedUserDataStatisticResp;
import com.linkwee.web.model.crm.InvestorManagerDetailResp;
import com.linkwee.web.model.crm.UserDataStatisticResp;
import com.linkwee.web.request.LcsListRequest;
import com.linkwee.web.request.ListDetailRequest;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorOperationService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.InvestorManagerService;
import com.linkwee.web.service.SysConfigService;

@Service("investorManagerService")
public class InvestorManagerServiceImpl implements InvestorManagerService {

	private static final Logger logger = LoggerFactory.getLogger(CfplannerManagerController.class);
	@Resource
	private InvestorManagerDao investorManagerDao;

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
	
	@Resource
	private SysConfigService systemConfigService;

	@Override
	public DataTableReturn queryInvestorList(DataTable dataTable, LcsListRequest request) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<InvestorManagerDetailResp> page = new Page<InvestorManagerDetailResp>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<InvestorManagerDetailResp> list = investorManagerDao.queryInvestorList(request, page);
		for(InvestorManagerDetailResp investorManagerDetailResp : list){
			investorManagerDetailResp.setCfplannerHeadImage(systemConfigService.getImageUrl(investorManagerDetailResp.getCfplannerHeadImage()));
		}
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public InvestorManagerDetailResp queryInvestorDetail(String mobile) {
		InvestorManagerDetailResp resp = investorManagerDao.queryInvestorDetail(mobile);
		if(resp == null) {
			return resp;
		}
		List<CrmInvestorOperation> list = crmInvestorOperationService.queryChangeCfpRecordList(resp.getUserId());
		resp.setChangeLcsRecordList(list);
		return resp;
	}

	/**
	 * 重新绑定理财师
	 * 
	 * @param mobile
	 * @param lcsMobile
	 * @param changeType
	 * @return
	 */
	@Override
	public void changeCfplanner(CrmInvestor investor, CrmCfplanner cfplanner, String changeType) {
		if (changeType != null && "1".equals(changeType)) {
			CrmInvestor crmInvestorForUpdate = new CrmInvestor();
			crmInvestorForUpdate.setUserId(investor.getUserId());
			crmInvestorForUpdate.setCfplanner(cfplanner.getUserId());
			crmInvestorService.updateByUserId(crmInvestorForUpdate);
			// 操作记录
			CrmInvestorOperation record = new CrmInvestorOperation();
			record.setCfplanner(cfplanner.getUserId());
			record.setCreateTime(new Date());
			record.setInvestor(investor.getUserId());
			record.setLastUpdateTime(new Date());
			record.setOperationAdmin("admin");
			record.setRemarks(InvestorOperationType.CHANGE_CFPLANNER.getMessage());
			record.setType(InvestorOperationType.CHANGE_CFPLANNER.getCode());
			crmInvestorOperationService.insert(record);

		} else if ("2".equals(changeType)) {
			crmInvestorService.removeCfplanner(investor.getUserId());
			// 操作记录
			CrmInvestorOperation record = new CrmInvestorOperation();
			record.setCfplanner(investor.getCfplanner());
			record.setCreateTime(new Date());
			record.setInvestor(investor.getUserId());
			record.setLastUpdateTime(new Date());
			record.setOperationAdmin("admin");
			record.setRemarks(InvestorOperationType.REMOVE_CFPLANNER.getMessage());
			record.setType(InvestorOperationType.REMOVE_CFPLANNER.getCode());
			crmInvestorOperationService.insert(record);
		} 
	}

	@Override
	public DataTableReturn selectFreindsList(DataTable dataTable, ListDetailRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<FreindsResp> page = new Page<FreindsResp>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<FreindsResp> list = this.investorManagerDao.selectFreindsList(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public Map<String, Object> queryInvestorAndMoney() {
	
		Map<String,Object> mapRet = new HashMap<String,Object>();
		Map<String,Object> personAmountMap = investorManagerDao.personAmoutStat();
		Map<String,Object> investMoneyMap = investorManagerDao.investMoneyStat();
		for(Map.Entry<String, Object> item :personAmountMap.entrySet()){
			personAmountMap.put(item.getKey(), NumberUtils.getFormat(new BigDecimal(String.valueOf(item.getValue())), "0"));
		}
		for(Map.Entry<String, Object> item :investMoneyMap.entrySet()){
			investMoneyMap.put(item.getKey(), NumberUtils.getFormat(new BigDecimal(String.valueOf(item.getValue())), "0.00"));
		}
		
		mapRet.put("personAmout", personAmountMap);
		mapRet.put("investMoney", investMoneyMap);
		return mapRet;
	}

	@Override
	public Map<String, Object> queryInvestorAndMoneyByDate(String startDate, String endDate) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> investor= new LinkedHashMap<String, Object>();
		Date start = DateUtils.parse(startDate,"yyyy-MM-dd");
		Date end = DateUtils.parse(endDate,"yyyy-MM-dd");
		int searchDay = DateUtils.countDays(start,end)+1;
		List<Map<String, Object>> listInvestorNum = investorManagerDao.queryInvestorNumByTime(startDate, endDate);
		List<Map<String, Object>> listInvestMoney = investorManagerDao.queryInvestMoneyByTime(startDate, endDate);
		List<Object> list =null;
		if(listInvestorNum.size() < searchDay){
			list =  new ArrayList<Object>();
			List<String> strList = DateUtils.getBetweeenTime(start, end, "yyyy-MM-dd");
			/**
			 * 手动补全投资人数
			 */
			for(String str:strList){
				boolean isExist = false;
				for(Map<String,Object> item:listInvestorNum){
					if(item.get("investDate") !=null && item.get("investDate").equals(str)){
						isExist = true;
						list.add(item);
						continue;
					}
					
				}
				if(!isExist){//没有查询数据手动补0
					Map<String,Object> temp = new HashMap<String,Object>();
					temp.put("investDate", str);
					temp.put("personNum", 0);
					list.add(temp);
				}else{
					
				}
			}
			investor.put("person", list);
			/**
			 * 手动补全投资额
			 */
			list =  new ArrayList<Object>();
			for(String str:strList){
				boolean isExist = false;
				for(Map<String,Object> item:listInvestMoney){
					//if(item.containsKey(str)){
					if(item.get("investDate") !=null && item.get("investDate").equals(str)){
						isExist = true;
						list.add(item);
						continue;
					}
					
				}
				if(!isExist){//没有查询数据手动补0
					Map<String,Object> temp = new HashMap<String,Object>();
					temp.put("investDate", str);
					temp.put("investTotal", 0);
					list.add(temp);
				}
			}
			investor.put("money", list);
			
		}else{
			investor.put("person", listInvestorNum);
			investor.put("money", listInvestMoney);
		}
		
		
		map.put("data", investor);
		return map;
	}
	
	@Override
	public Map<String, Object> queryTotalInvestorAndMoney() {
		Map<String,Object> mapRet = new HashMap<String,Object>();
		Map<String,Object> investorTotal = investorManagerDao.investorTotal();
		Map<String,Object> investMoneyTotal = investorManagerDao.investMoneyTotal();
		
		mapRet.put("totalperson", NumberUtils.getFormat(new BigDecimal(String.valueOf(investorTotal.get("totalperson"))), "0"));
		mapRet.put("totalmoney", NumberUtils.getFormat(new BigDecimal(String.valueOf(investMoneyTotal.get("totalmoney"))), "0"));
		return mapRet;
	}

	/**
	 * 用户注册数据统计
	 * @return
	 */
	@Override
	public UserDataStatisticResp queryUserRegisterTotalData() {
		return investorManagerDao.queryUserRegisterTotalData();
	}

	/**
	 * 用户投资数据统计
	 * @return
	 */
	@Override
	public InvestedUserDataStatisticResp queryUserInvestTotalData() {
		return investorManagerDao.queryUserInvestTotalData();
	}

}
