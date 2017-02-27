package com.eshop4j.web.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.dao.TcInvestMapper;
import com.eshop4j.web.model.CustomerInvestDetail;
import com.eshop4j.web.model.CustomerInvestStatistics;
import com.eshop4j.web.request.CustomerInvestRequest;
import com.eshop4j.web.service.TcInvestService;

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

}
