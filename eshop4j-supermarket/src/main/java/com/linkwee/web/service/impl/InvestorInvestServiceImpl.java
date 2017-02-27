package com.linkwee.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.InvestorInvestDao;
import com.linkwee.web.dao.TorginfoDao;
import com.linkwee.web.model.crm.InvestRecordResp;
import com.linkwee.web.model.crm.InvestorInvestResp;
import com.linkwee.web.request.ListDetailRequest;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorOperationService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.InvestorInvestService;

@Service("investorInvestService")
public class InvestorInvestServiceImpl implements InvestorInvestService {

	@Resource
	private InvestorInvestDao investorInvestDao;

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
	public DataTableReturn selectInvestorInvest(DataTable dataTable, ListDetailRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<InvestorInvestResp> page = new Page<InvestorInvestResp>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<InvestorInvestResp> list = this.investorInvestDao.selectInvestorInvest(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
	
	@Override
	public DataTableReturn selectInvestRecord(DataTable dataTable, ListDetailRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<InvestRecordResp> page = new Page<InvestRecordResp>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<InvestRecordResp> list = this.investorInvestDao.selectInvestRecord(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
