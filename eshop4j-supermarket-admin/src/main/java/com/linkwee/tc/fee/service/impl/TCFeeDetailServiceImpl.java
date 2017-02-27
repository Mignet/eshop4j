package com.linkwee.tc.fee.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.util.StringUtils;
import com.linkwee.tc.fee.model.TCFeedetail;
import com.linkwee.tc.fee.model.vo.FeedetailWrapper;
import com.linkwee.tc.fee.service.TCFeeDetailService;
import com.linkwee.tc.fee.service.TCFeeService;
import com.linkwee.web.dao.TCFeedetailMapper;
import com.linkwee.web.service.CrmCfplannerService;

@Service
public class TCFeeDetailServiceImpl extends GenericServiceImpl<TCFeedetail, Long> implements TCFeeDetailService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TCFeeDetailServiceImpl.class);
	
	@Autowired
	private TCFeedetailMapper feedetailMapper;
	
	
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
				feedetail.setCreateTime(feedetailWrapper.getCreateTime()==null? date : feedetailWrapper.getCreateTime());
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

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public int batchUpdateBalanceStatus(List<String> cfplannerIds,List<Map<String, String>> balanceMaps, int balanceStatus,String begintime, String endTime) {
		return feedetailMapper.batchUpdateBalanceStatus(cfplannerIds,balanceMaps,balanceStatus, begintime, endTime);
	}

}
