package com.linkwee.tc.fee.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.linkwee.api.request.tc.CfplannerProfitRequest;
import com.linkwee.api.response.tc.CfplannerProfitTotalResponse;
import com.linkwee.api.response.tc.ProfitItemsResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.tc.fee.model.TCFeedetail;
import com.linkwee.tc.fee.model.vo.FeedetailWrapper;
import com.linkwee.tc.fee.service.TCFeeDetailService;
import com.linkwee.tc.fee.service.TCFeeService;
import com.linkwee.web.dao.TCFeeMapper;
import com.linkwee.web.dao.TCFeedetailMapper;
import com.linkwee.web.model.crm.PersonCenterResp;
import com.linkwee.web.service.CrmCfplannerService;

@Service
public class TCFeeDetailServiceImpl extends GenericServiceImpl<TCFeedetail, Long> implements TCFeeDetailService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TCFeeDetailServiceImpl.class);
	
	@Autowired
	private TCFeedetailMapper feedetailMapper;
	
	@Autowired
	private TCFeeMapper feeMapper;
	
	@Autowired
	private TCFeeService feeService;
	
	@Autowired
	private CrmCfplannerService cfplannerService;
	
	
	@Override
    public GenericDao<TCFeedetail, Long> getDao() {
        return feedetailMapper;
    }
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void insertFeedetail(FeedetailWrapper... feedetailWrappers) {
		try{
			Date date = new Date();
			List<TCFeedetail> feedetails = Lists.newArrayListWithCapacity(feedetailWrappers.length);
			TCFeedetail feedetail = null;
			for (FeedetailWrapper feedetailWrapper : feedetailWrappers) {
				feedetail = new TCFeedetail();
				feedetail.setBizId(StringUtils.getUUID());
				feedetail.setBillId(feedetailWrapper.getBillId());
				feedetail.setInvestorId(feedetailWrapper.getInvestorId());
				feedetail.setProfitCfplannerId(feedetailWrapper.getCurCfplannerId());
				feedetail.setOriginCfplannerId(feedetailWrapper.getCfplannerId());
				feedetail.setProductOrgId(feedetailWrapper.getProductOrgId());
				feedetail.setProductId(feedetailWrapper.getProductId());
				feedetail.setProductAmount(feedetailWrapper.getInvestmentAmount());
				feedetail.setYearpurAmount(feedetailWrapper.getYearPurAmount());
				feedetail.setFeeRate(new BigDecimal(feedetailWrapper.getCurRatio()));
				feedetail.setFeeAmount(feedetailWrapper.getFeeamount());
				feedetail.setFeeType(feedetailWrapper.getFeetype());
				feedetail.setRemark(feedetailWrapper.getRemak());
				feedetail.setCreateTime(date);
				feedetail.setUpdateTime(date);
				feedetails.add(feedetail);
			}
			if(!feedetails.isEmpty()){
				feedetailMapper.inserts(feedetails);
				feeService.saveFees(feedetailWrappers);
			}
			
		}catch(Exception e){
			LOGGER.error("insertFeedetail Exception feedetailWrappers={},exception={}", feedetailWrappers,e);
		}
	}
	
	@Override
	public PersonCenterResp queryCfplannerMonthProfitTotal(String userId) {
		return feedetailMapper.queryCfplannerMonthProfitTotal(userId);
	}

	@Override
	public CfplannerProfitTotalResponse queryCfplannerProfitTotal(String userId, Integer dateType, Date date){
		CfplannerProfitTotalResponse profitTotalResponse = feedetailMapper.queryCfplannerProfitTotal(userId, dateType, date);
		profitTotalResponse.setItems(feedetailMapper.queryCfplannerProfitItemsTotal(userId, dateType, date));
		return profitTotalResponse;
	}
	
	
	@Override
	public Double queryCfplannerProfitItemTotal(String userId,Integer dateType, Date date, Integer profitType) {
		return feedetailMapper.queryCfplannerProfitItemTotal(userId, dateType, date, profitType).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
	}

	@Override
	public PaginatorResponse<ProfitItemsResponse> queryCfplannerProfitItem(String userId,CfplannerProfitRequest profitRequest) {
		Page<ProfitItemsResponse> page  = new Page<ProfitItemsResponse>(profitRequest.getPageIndex(),profitRequest.getPageSize()>10?10:profitRequest.getPageSize()); //默认每页10条
		PaginatorResponse<ProfitItemsResponse> paginatorResponse = new PaginatorResponse<ProfitItemsResponse>();
		paginatorResponse.setDatas(feedetailMapper.queryCfplannerProfitItem(userId, profitRequest.getDateType(), DateUtils.parse(profitRequest.getDate(),DateUtils.FORMAT_SHORT), profitRequest.getProfitType(), page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public TCFeedetail queryFeedetailByUserIdAndMonthLimitOne(String userId, String month) {
		return feedetailMapper.queryFeedetailByUserIdAndMonthLimitOne(userId,  month);
	}

	@Override
	public boolean isGrantFeeByMonth(String month) {
		int count = feedetailMapper.isGrantFeeByMonth(month);
		if(count > 0 ) {
			return true;
		}
		return false;
	}

}
