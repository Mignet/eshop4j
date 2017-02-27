package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.TcInvestMapper;
import com.linkwee.web.model.CustomerInvestDetail;
import com.linkwee.web.model.CustomerInvestStatistics;
import com.linkwee.web.request.CustomerInvestRequest;
import com.linkwee.web.request.tc.InvestorRepaymentRequest;
import com.linkwee.web.response.tc.InvestorRepaymentResponse;
import com.linkwee.web.service.TcInvestService;

@Service("TcInvestService")
public class TcInvestServiceImpl implements TcInvestService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TcInvestServiceImpl.class);
	
	@Autowired
	private TcInvestMapper investMapper;
	
	@Override
	public DataTableReturn queryCustomerInvestStatistics(CustomerInvestRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(req.getDraw()+1);
		Page<CustomerInvestStatistics> page = new Page<CustomerInvestStatistics>(req.getStart()/req.getLength()+1,req.getLength());
		List<CustomerInvestStatistics> list = investMapper.queryCustomerInvestStatistics(req.getPlatfrom(),req.getNameOrMobile(), page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn queryCustomerInvestDetail(CustomerInvestRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(req.getDraw()+1);
		Page<CustomerInvestDetail> page = new Page<CustomerInvestDetail>(req.getStart()/req.getLength()+1,req.getLength());
		List<CustomerInvestDetail> list = investMapper.queryCustomerInvestDetail(req.getPlatfrom(),req.getUserId(), page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public BigDecimal getInvestmentStatisticsTotal(String platfrom,String startTime, String endTime) {
		return investMapper.getInvestmentStatisticsTotal(platfrom, startTime, endTime);
	}

	@Override
	public List<Map<String, BigDecimal>> getInvestmentStatisticsList(
			String platfrom, String startTime, String endTime) {
		return investMapper.getInvestmentStatisticsList(platfrom, startTime, endTime);
	}

	@Override
	public BigDecimal getInvestStatisticsByPlatfromTotal(String platfrom,String startTime, String endTime) {
		return investMapper.getInvestStatisticsByPlatfromTotal(platfrom, startTime, endTime);
	}

	@Override
	public List<Map<String, BigDecimal>> getInvestStatisticsByPlatfrom(String platfrom, String startTime, String endTime) {
		return investMapper.getInvestStatisticsByPlatfrom(platfrom, startTime, endTime);
	}

	@Override
	public DataTableReturn customerImpendRepayment(InvestorRepaymentRequest repaymentRequest) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(repaymentRequest.getDraw()+1);
		Page<InvestorRepaymentResponse> page = new Page<InvestorRepaymentResponse>(repaymentRequest.getStart()/repaymentRequest.getLength()+1,repaymentRequest.getLength());
		
		Integer dateType = repaymentRequest.getDateType();
		DateTime now = DateTime.now();
		int day;
		switch (dateType) {
		case 0:
			day = -1;
			break;
		case 1:
			day = 3;
			break;
		case 2:
			day = 7;
			break;
		case 3:
			day = 30;	
			break;
		default:
			day = 3;
			break;
		}
		String startTime = dateType==0 ? StringUtils.join(new Object[]{now.plusDays(day).toString("yyyy-MM-dd")," 00:00:00"}):StringUtils.join(new Object[]{now.toString("yyyy-MM-dd")," 00:00:00"});
		String endTime = StringUtils.join(new Object[]{now.plusDays(day).toString("yyyy-MM-dd")," 23:59:59"});
		tableReturn.setData(investMapper.customerImpendRepayment(repaymentRequest.getType(), repaymentRequest.getMobileOrName(), startTime, endTime, 0, page));
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
	
	@Override
	public List<InvestorRepaymentResponse> getCustomerImpendRepaymentList(InvestorRepaymentRequest repaymentRequest) {
		Integer dateType = repaymentRequest.getDateType();
		DateTime now = DateTime.now();
		int day;
		switch (dateType) {
		case 0:
			day = -1;
			break;
		case 1:
			day = 3;
			break;
		case 2:
			day = 7;
			break;
		case 3:
			day = 30;	
			break;
		default:
			day = 3;
			break;
		}
		String startTime = dateType==0 ? StringUtils.join(new Object[]{now.plusDays(day).toString("yyyy-MM-dd")," 00:00:00"}):StringUtils.join(new Object[]{now.toString("yyyy-MM-dd")," 00:00:00"});
		String endTime = StringUtils.join(new Object[]{now.plusDays(day).toString("yyyy-MM-dd")," 23:59:59"});
		return investMapper.getCustomerImpendRepaymentList(repaymentRequest.getType(), repaymentRequest.getMobileOrName(), startTime, endTime, 1);
	}

}
