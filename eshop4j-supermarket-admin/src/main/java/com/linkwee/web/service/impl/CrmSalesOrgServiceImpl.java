package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.CrmSalesOrgMapper;
import com.linkwee.web.model.crm.CrmSalesOrg;
import com.linkwee.web.model.crm.OrgSalesDetailResp;
import com.linkwee.web.model.crm.SaleOrgAchiResp;
import com.linkwee.web.model.crm.SalesOrgCfpResp;
import com.linkwee.web.model.crm.SalseOrgStockYearpurAmtResp;
import com.linkwee.web.model.crm.TeamStatisticalResponse;
import com.linkwee.web.request.LcsListRequest;
import com.linkwee.web.request.SaleOrgDetailRequest;
import com.linkwee.web.service.CrmSalesOrgService;


 /**
 * 
 * @描述：CrmSalesOrgService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月03日 15:12:23
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmSalesOrgService")
public class CrmSalesOrgServiceImpl extends GenericServiceImpl<CrmSalesOrg, Long> implements CrmSalesOrgService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmSalesOrgServiceImpl.class);
	
	@Resource
	private CrmSalesOrgMapper crmSalesOrgMapper;
	
	@Override
    public GenericDao<CrmSalesOrg, Long> getDao() {
        return crmSalesOrgMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmSalesOrg -- 排序和模糊查询 ");
		Page<CrmSalesOrg> page = new Page<CrmSalesOrg>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmSalesOrg> list = this.crmSalesOrgMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
    
    @Override
	public DataTableReturn querySalesOrgList(DataTable dataTable, LcsListRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<CrmSalesOrg> page = new Page<CrmSalesOrg>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<CrmSalesOrg> list = crmSalesOrgMapper.querySalesOrgList(req, page);
		for (int i = 0; i < list.size(); i++) {
			CrmSalesOrg org = list.get(i);
			SalseOrgStockYearpurAmtResp salseOrgStockYearpurAmtResp = this.queryStockYearpurAmtBySalesOrgId(org.getSalesOrgId());
			org.setLastMonthStockYearSales(salseOrgStockYearpurAmtResp.getLastMonthStockYearSales().setScale(4,BigDecimal.ROUND_DOWN));
			org.setLastMonthStockSales(salseOrgStockYearpurAmtResp.getLastMonthStockSales().setScale(4,BigDecimal.ROUND_DOWN));
			list.set(i, org);
		}
		
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	private SalseOrgStockYearpurAmtResp queryStockYearpurAmtBySalesOrgId(String salesOrgId) {
		SalseOrgStockYearpurAmtResp bo = new SalseOrgStockYearpurAmtResp();
		//计算存量
		Map<String, BigDecimal> nh = crmSalesOrgMapper.queryStockYearpurAmtBySalesOrgId(salesOrgId);
		//时间段总年化
		BigDecimal znh = nh.get("znh");
		//固定期产品(不需要过了锁定期后每日佣金计算的产品) 总年化
		BigDecimal gdnh = nh.get("gdnh");
		////固定期产品(不需要过了锁定期后每日佣金计算的产品) 总投资额
		BigDecimal gdinvest = nh.get("gdinvest");
		//存量年化 
		bo.setLastMonthStockYearSales(znh);
		//总年化 - 固定年化 = 每日计算所产总年化
		BigDecimal jsnh = znh.subtract(gdnh);
		if(jsnh.compareTo(new BigDecimal(0))==0){
			bo.setLastMonthStockSales(gdinvest);
		}else{
			//存量总投资额 = 每日计算所产总投资额 +  固定期产品总投资额
			//每日计算所产总投资额 = 每日计算所产总年化 * 360 / 时间段天数
			BigDecimal stockAmt =(jsnh.multiply(new BigDecimal(360)).divide(new BigDecimal(30), 4, BigDecimal.ROUND_DOWN) .add(gdinvest));
			bo.setLastMonthStockSales(stockAmt);
		}
		return bo;
	}

	@Override
	public DataTableReturn querySalesOrgCfpList(DataTable dataTable, SaleOrgDetailRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<SalesOrgCfpResp> page = new Page<SalesOrgCfpResp>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<SalesOrgCfpResp> list = crmSalesOrgMapper.querySalesOrgCfpList(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn querySalesDetailList(DataTable dataTable, SaleOrgDetailRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<OrgSalesDetailResp> page = new Page<OrgSalesDetailResp>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<OrgSalesDetailResp> list = crmSalesOrgMapper.querySalesDetailList(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public SaleOrgAchiResp querySalesOrgAchiByNumber(String salesOrgId) {
		SaleOrgAchiResp resp = crmSalesOrgMapper.querySalesOrgAchiByNumber(salesOrgId);
		return resp;
	}

	@Override
	public TeamStatisticalResponse getSalesDetailListTotal(SaleOrgDetailRequest req) {
		TeamStatisticalResponse resp = crmSalesOrgMapper.getSalesDetailListTotal(req);
		DateTime now = DateTime.now();
		if( StringUtils.isBlank(req.getStartTimeForSearch()) && StringUtils.isBlank(req.getEndTimeForSearch())){
			String month = now.toString("yyyy-MM");
			req.setStartTimeForSearch(month + "-01");
			req.setEndTimeForSearch(now.toString("yyyy-MM-dd"));
		} else if(StringUtils.isBlank(req.getStartTimeForSearch()) && !StringUtils.isBlank(req.getEndTimeForSearch())){
			req.setStartTimeForSearch("2000-01-01");
		} else if(!StringUtils.isBlank(req.getStartTimeForSearch()) && StringUtils.isBlank(req.getEndTimeForSearch())){
			req.setEndTimeForSearch(now.toString("yyyy-MM-dd"));
		}
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd"); 
	 	LocalDate start=new LocalDate(DateTime.parse(req.getStartTimeForSearch(), format));    
        LocalDate end=new LocalDate(DateTime.parse(req.getEndTimeForSearch(), format));  
        int days = Days.daysBetween(start, end).getDays();
        days++; 
        if(days< 0){
        	return resp;
        }
		//计算存量
		Map<String, BigDecimal> nh = crmSalesOrgMapper.getStockYearpurAmt(req);
		//时间段总年化
		BigDecimal znh = nh.get("znh");
		//固定期产品(不需要过了锁定期后每日佣金计算的产品) 总年化
		BigDecimal gdnh = nh.get("gdnh");
		////固定期产品(不需要过了锁定期后每日佣金计算的产品) 总投资额
		BigDecimal gdinvest = nh.get("gdinvest");
		//存量年化 
		resp.setStockYearpurAmt(znh);
		//总年化 - 固定年化 = 每日计算所产总年化
		BigDecimal jsnh = znh.subtract(gdnh);
		if(jsnh.compareTo(new BigDecimal(0))==0){
			resp.setStockpurAmt(gdinvest);
		}else{
			//存量总投资额 = 每日计算所产总投资额 +  固定期产品总投资额
			//每日计算所产总投资额 = 每日计算所产总年化 * 360 / 时间段天数
			BigDecimal stockAmt =(jsnh.multiply(new BigDecimal(360)).divide(new BigDecimal(days), 4, BigDecimal.ROUND_DOWN) .add(gdinvest));
			resp.setStockpurAmt(stockAmt);
		}
				
		return resp;
	}

	@Override
	public int querycfpOfInvestedCount(SaleOrgDetailRequest req) {
		return crmSalesOrgMapper.querycfpOfInvestedCount(req);
	}

	@Override
	public boolean checkNameExistForUpdate(Integer id, String name) {
		int ct = crmSalesOrgMapper.checkNameExistForUpdate(id, name);
		if(ct > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkMobileExistForUpdate(Integer id, String contactMobile) {
		int ct = crmSalesOrgMapper.checkMobileExistForUpdate(id, contactMobile);
		if(ct > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkAccountExistForUpdate(Integer id, String managerAccount) {
		int ct = crmSalesOrgMapper.checkAccountExistForUpdate(id, managerAccount);
		if(ct > 0) {
			return true;
		}
		return false;
	}

}
