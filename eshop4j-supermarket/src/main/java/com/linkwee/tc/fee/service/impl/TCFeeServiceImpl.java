package com.linkwee.tc.fee.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.api.response.cim.ProductDetailResponse;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.util.StringUtils;
import com.linkwee.tc.fee.common.CalculateTools;
import com.linkwee.tc.fee.model.TCFee;
import com.linkwee.tc.fee.model.vo.FeedetailWrapper;
import com.linkwee.tc.fee.service.TCFeeService;
import com.linkwee.web.dao.TCFeeMapper;


 /**
 * 
 * @描述：CimFeeService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年08月11日 15:59:16
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimFeeService")
public class TCFeeServiceImpl extends GenericServiceImpl<TCFee, Long> implements TCFeeService{
	
	@Resource
	private TCFeeMapper feeMapper;
	
	@Override
    public GenericDao<TCFee, Long> getDao() {
        return feeMapper;
    }
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveFees(FeedetailWrapper... feedetailWrappers){
		TCFee fee = null;
		Date date = new Date();
		for (FeedetailWrapper feedetailWrapper : feedetailWrappers) {
			String billId= feedetailWrapper.getBillId();
			fee = new TCFee();
			fee.setBillId(billId);
			fee.setProfitCfplannerId(feedetailWrapper.getCurCfplannerId());
			fee.setYearpurAmount(feedetailWrapper.getYearPurAmount());
			fee.setFeeAmount(feedetailWrapper.getFeeamount());
			fee.setFeeRate(new BigDecimal(feedetailWrapper.getCurRatio()));
			fee.setFeeType(feedetailWrapper.getFeetype());
			fee.setRemark(feedetailWrapper.getRemak());
			fee.setUpdateTime(date);
			if(feeMapper.isExitFee(billId,fee.getProfitCfplannerId(),feedetailWrapper.getFeetype())){
				feeMapper.updateFee(fee);
			}else{
				fee.setBizId(StringUtils.getUUID());
				fee.setInvestorId(feedetailWrapper.getInvestorId());
				fee.setOriginCfplannerId(feedetailWrapper.getCfplannerId());
				fee.setProductOrgId(feedetailWrapper.getProductOrgId());
				fee.setProductId(feedetailWrapper.getProductId());
				fee.setProductAmount(feedetailWrapper.getInvestmentAmount());
				fee.setCreateTime(date);
				insert(fee);
			}
		}
		
	}


	@Override
	public Double queryFeeByUserId(String userId, String month) {
		return feeMapper.queryFeeByUserId(userId, month);
	}


	@Override
	public Double queryAllowanceByUserId(String userId, String month) {
		return feeMapper.queryAllowanceByUserId(userId, month);
	}


	@Override
	public Double queryLeaderRewardByUserId(String userId, String month) {
		return feeMapper.queryLeaderRewardByUserId(userId, month);
	}

	private BigDecimal amt =  new BigDecimal(10000);
	private BigDecimal investAmt =  new BigDecimal(100000);
	
	@Override
	public String productProfitCalculate(ProductDetailResponse productDetail) {
		int orgFeeType = productDetail.getOrgInfoResponse().getOrgFeeType();
		BigDecimal investAmt = this.investAmt;
		if(orgFeeType==1){
			investAmt = productDetail.getOrgInfoResponse().getOrgAmountLimit();
		}
		BigDecimal productProfitCalculate = CalculateTools.feeAmountCompute(CalculateTools.yearpurAmountCompute(investAmt, productDetail.getDeadLineMinValue()),productDetail.getFeeRatio().doubleValue());
		String amtStr = investAmt.compareTo(amt)>=0 ? investAmt.divide(amt, 0, BigDecimal.ROUND_DOWN).toString()+"万元" : investAmt.setScale(0,BigDecimal.ROUND_DOWN).toString()+"元";
		return String.format("现在投资%s,%s天后收益%s元",amtStr,productDetail.getDeadLineMinValue(),productProfitCalculate.setScale(2,BigDecimal.ROUND_DOWN).toString());
	}
    


}
