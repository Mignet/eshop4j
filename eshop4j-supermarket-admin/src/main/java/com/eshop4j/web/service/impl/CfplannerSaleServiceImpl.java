package com.eshop4j.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.dao.CfplannerSaleDao;
import com.eshop4j.web.model.crm.ActivityRewardListResp;
import com.eshop4j.web.model.crm.AllowanceDetailListResp;
import com.eshop4j.web.model.crm.CfpFeeDetailResp;
import com.eshop4j.web.model.crm.CfplannerSaleResp;
import com.eshop4j.web.model.crm.CustomerInvestListResp;
import com.eshop4j.web.request.ListDetailRequest;
import com.eshop4j.web.service.CfplannerSaleService;
import com.eshop4j.web.service.CrmCfplannerService;
import com.eshop4j.web.service.CrmInvestorService;

@Service("cfplannerSaleService")
public class CfplannerSaleServiceImpl implements CfplannerSaleService {

	@Resource
	private CfplannerSaleDao cfplannerSaleDao;

	@Resource
	private CrmCfplannerService crmCfplannerService;

	@Resource
	private CrmInvestorService crmInvestorService;

	@Override
	public DataTableReturn selectCfplannerSaleList(DataTable dataTable, ListDetailRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<CfplannerSaleResp> page = new Page<CfplannerSaleResp>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<CfplannerSaleResp> list = this.cfplannerSaleDao.selectCfplannerSaleList(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn selectFeeDetailList(DataTable dataTable, ListDetailRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<CfpFeeDetailResp> page = new Page<CfpFeeDetailResp>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<CfpFeeDetailResp> list = this.cfplannerSaleDao.selectFeeDetailList(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn selectAllowanceDetailList(DataTable dataTable, ListDetailRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<AllowanceDetailListResp> page = new Page<AllowanceDetailListResp>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<AllowanceDetailListResp> list = this.cfplannerSaleDao.selectAllowanceDetailList(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn selectActivityRewardList(DataTable dataTable, ListDetailRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<ActivityRewardListResp> page = new Page<ActivityRewardListResp>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<ActivityRewardListResp> list = this.cfplannerSaleDao.selectActivityRewardList(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn selectCustomerInvestList(DataTable dataTable, ListDetailRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<CustomerInvestListResp> page = new Page<CustomerInvestListResp>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<CustomerInvestListResp> list = this.cfplannerSaleDao.selectCustomerInvestList(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
