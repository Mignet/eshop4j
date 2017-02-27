package com.eshop4j.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.tc.fee.common.CalculateTools;
import com.eshop4j.web.dao.CimOrgFeeGatherMapper;
import com.eshop4j.web.dao.CimOrgFeePerDayMapper;
import com.eshop4j.web.dao.CimOrgFeeRecordMapper;
import com.eshop4j.web.dao.CimOrginfoMapper;
import com.eshop4j.web.dao.CimProductInvestRecordMapper;
import com.eshop4j.web.enums.OrgSaleFeeTypeCodeEnum;
import com.eshop4j.web.model.cim.CimOrgFeeGather;
import com.eshop4j.web.model.cim.CimOrgFeePerDay;
import com.eshop4j.web.model.cim.CimOrgFeeRuleDetail;
import com.eshop4j.web.model.vo.InvestRecordWrapper;
import com.eshop4j.web.model.vo.OrgSaleFeeData;
import com.eshop4j.web.service.CimOrgFeeGatherService;
import com.eshop4j.web.service.InvestRecordAware;
import com.eshop4j.xoss.util.BigDecimalUtil;
import com.eshop4j.xoss.util.RejectedExecuteRetry;


 /**
 * 
 * @描述：CimOrgFeedetailService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月27日 18:06:58
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgFeeGatherService")
public class CimOrgFeeGatherServiceImpl extends GenericServiceImpl<CimOrgFeeGather, Long> implements CimOrgFeeGatherService,InvestRecordAware{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgFeeGatherServiceImpl.class);
	
	@Resource
	private CimOrgFeeGatherMapper cimOrgFeeGatherMapper;
	@Autowired
	private CimOrginfoMapper cimOrginfoMapper;
	@Autowired
	private CimOrgFeeRecordMapper cimOrgFeeRecordMapper;
	@Autowired
	private CimProductInvestRecordMapper cimProductInvestRecordMapper;
	@Autowired
	private CimOrgFeePerDayMapper cimOrgFeePerDayMapper;
	@Resource
	private CimOrgFeeGatherMapper cimOrgFeedetailMapper;
	
	@Override
    public GenericDao<CimOrgFeeGather, Long> getDao() {
        return cimOrgFeeGatherMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgFeedetail -- 排序和模糊查询 ");
		Page<CimOrgFeeGather> page = new Page<CimOrgFeeGather>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgFeeGather> list = this.cimOrgFeeGatherMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	public Boolean IsFirstInvest(InvestRecordWrapper investRecord,CimOrgFeeGather model) {
		if(model != null){
			return model.getIsFirstInvest().intValue() == 1 ? true : false ;
		}
		return  cimProductInvestRecordMapper.queryFirsInvestTime(investRecord.getUserId(),investRecord.getProductOrgId()) == 1 ;
		
	}

	@Override
	/**
	 * 计算平台销售费用
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@RejectedExecuteRetry
	public void investRecordProcess(InvestRecordWrapper investRecord) throws Exception {
		//如果是募集产品不计算费用，募集成功重新推送计算 推送募集成功产品类型当做固定类型产品计算
		if(ObjectUtils.equals(investRecord.getProductType(), 2))return;//是募集产品
		
		//是否已经有该投资ID的费用汇总记录
		CimOrgFeeGather condition = new CimOrgFeeGather();
		condition.setInvestId(investRecord.getBizId());
		CimOrgFeeGather model = cimOrgFeeGatherMapper.selectOneByCondition(condition);
		// 查询机构收费规则信息
		String orgNumber = investRecord.getProductOrgId();
		List<CimOrgFeeRuleDetail> orgFeeRecordList = cimOrgFeeRecordMapper.queryOrgFeeDetail(orgNumber);
		
		//是否首投
		boolean isFirstInvest = investRecord.isPlatfromFirstInvest();//IsFirstInvest(investRecord, model);
		//计算该笔投资的年化额  可赎回和转让的产品用产品的锁定时间算年化
		Integer proLockDays = investRecord.getProductDays();
		
		BigDecimal yearAmount = CalculateTools.yearpurAmountCompute(investRecord.getInvestAmt(), proLockDays);
		//fixedAmount
		BigDecimal fixedAmount = new BigDecimal(0);
		//floatFixedAmount
		BigDecimal floatFixedAmount = new BigDecimal(0);
		//proportionAmount
		BigDecimal proportionAmount = new BigDecimal(0);
		BigDecimal proportionRatio = new BigDecimal(0);
		//yearProportionAmount
		BigDecimal yearProportionAmount = new BigDecimal(0);
		BigDecimal yearProportionRatio = new BigDecimal(0);
		//month_amount_propertion
		BigDecimal monthAmountProportionAmount = new BigDecimal(0);
		// 该笔投资额产生的销售费用
		BigDecimal fee = new BigDecimal(0);
		//当月该平台总销售额
		BigDecimal monthInvestAmount = new BigDecimal(0);
		try {
			if(model == null){ //该订单的第一笔费用
				if(orgFeeRecordList!=null && orgFeeRecordList.size() >0){
					//计算平台销售费用
					monthInvestAmount = cimProductInvestRecordMapper.queryMonthInvestAmount(orgNumber);
					for(CimOrgFeeRuleDetail item :orgFeeRecordList){
				    	if(item.getFeeAttr().equals(OrgSaleFeeTypeCodeEnum.FIXED.getKey())){//首投固定收费
				    		if(isFirstInvest){
				    			fixedAmount = item.getFeeVal();
				    			fee = BigDecimalUtil.add(fee,fixedAmount);
				    		}
				    	}
				    	if(item.getFeeAttr().equals(OrgSaleFeeTypeCodeEnum.FLOAT_FIXED.getKey())){//按区间固定收费
				    		if(isFirstInvest){
					    		if((item.getIntervalMinVal()!=null && 
					    		  investRecord.getInvestAmt().intValue()>=item.getIntervalMinVal().intValue() &&
					    		  item.getIntervalMaxVal() ==null) ||
					    		  (item.getIntervalMinVal() !=null &&
							       item.getIntervalMaxVal() !=null &&
							       investRecord.getInvestAmt().intValue()>=item.getIntervalMinVal().intValue() &&
								   investRecord.getInvestAmt().intValue()<=item.getIntervalMaxVal().intValue())){
					    			floatFixedAmount = item.getFeeVal();
					    			fee = BigDecimalUtil.add(fee,floatFixedAmount);
					    		}
					    		   
				    		}
				    		
				    	}else if(item.getFeeAttr().equals(OrgSaleFeeTypeCodeEnum.PROPERTION.getKey())){//按首投比例定收费
				    		if(isFirstInvest){
				    			proportionRatio = item.getFeeRatio();
				    			proportionAmount = BigDecimalUtil.divide(BigDecimalUtil.multiply(item.getFeeRatio(), investRecord.getInvestAmt()), 100);
				    			fee = BigDecimalUtil.add(fee,proportionAmount);
				    		}
				    	}else if(item.getFeeAttr().equals(OrgSaleFeeTypeCodeEnum.YEAR_PROPERTION.getKey())){//按年化计算
				    		if((item.getIntervalMinVal()!=null &&						
								 investRecord.getDeadLineMaxValue()>=item.getIntervalMinVal().intValue() && 
								 item.getIntervalMaxVal() == null) ||
				    			(item.getIntervalMinVal()!=null && 
						       item.getIntervalMaxVal() !=null &&
						       investRecord.getDeadLineMaxValue()>=item.getIntervalMinVal().intValue() && 
						       investRecord.getDeadLineMaxValue()<=item.getIntervalMaxVal().intValue())){
				    			yearProportionRatio = item.getFeeRatio();
				    			 yearProportionAmount = BigDecimalUtil.divide(BigDecimalUtil.multiply(item.getFeeRatio(), yearAmount), 100);
				    			fee = BigDecimalUtil.add(fee,yearProportionAmount);
				    		}
				    	}else if(item.getFeeAttr().equals(OrgSaleFeeTypeCodeEnum.MONTH_AMOUNT_PROPERTION.getKey())){//按年化计算
				    		if((item.getIntervalMinVal()!=null &&						
				    				monthInvestAmount.intValue() >= item.getIntervalMinVal().intValue() && 
									 item.getIntervalMaxVal() == null) ||
					    			(item.getIntervalMinVal()!=null && 
							       item.getIntervalMaxVal() !=null &&
							       monthInvestAmount.intValue()>=item.getIntervalMinVal().intValue() && 
							       monthInvestAmount.intValue()<=item.getIntervalMaxVal().intValue())){
				    			yearProportionRatio = item.getFeeRatio();
				    			monthAmountProportionAmount = BigDecimalUtil.divide(BigDecimalUtil.multiply(item.getFeeRatio(), yearAmount), 100);
				    			fee = BigDecimalUtil.add(fee,monthAmountProportionAmount);
				    		}
				    	}
					}
					
					
				}
			}else{//该订单的天费用
				//同一笔订单产生的费用用同一费率计算
				yearProportionRatio = model.getYearProportionRatio();
				fee = BigDecimalUtil.divide(BigDecimalUtil.multiply(yearProportionRatio, yearAmount), 100);
				
			}
			
			//记录销售汇总费用		
			String uuId = "";
			boolean isFirstCalculate = true;
			if(model == null){
				isFirstCalculate = true;
				model = new CimOrgFeeGather();
				uuId = StringUtils.getUUID();
				model.setFeedetailId(uuId);
				model.setInvestId(investRecord.getBizId());
				model.setUserId(investRecord.getUserId());
				model.setInvestTime(investRecord.getInvestTime());
				model.setProductOrgId(investRecord.getProductOrgId());
				model.setProductId(investRecord.getProductId());
				model.setProductAmount(investRecord.getInvestAmt());
				model.setProductDeadline(investRecord.getProductDays());
				model.setIsRedemption(investRecord.getIsRedemption());
				if(investRecord.getIsRedemption() != 0){
					model.setRedemptionDate(investRecord.getProductDays());
				}
				model.setYearpurAmount(yearAmount);
				if(isFirstInvest){
					model.setIsFirstInvest(1);
				}else{
					model.setIsFirstInvest(0);
				}
				
				model.setFixedAmount(fixedAmount);
				model.setFloatFixedAmount(floatFixedAmount);
				model.setProportionRatio(proportionRatio);
				model.setProportionAmount(proportionAmount);     
				model.setYearProportionRatio(yearProportionRatio);
				model.setYearProportionAmount(yearProportionAmount);
				model.setFeeAmount(fee);
				model.setBalanceStatus((byte)0);
				model.setCreateTime(new Date());
				cimOrgFeeGatherMapper.insertSelective(model);
			}else{
				isFirstCalculate = false;
				uuId = model.getFeedetailId();
				model.setYearProportionAmount(BigDecimalUtil.add(model.getYearProportionAmount(), fee));
				model.setYearpurAmount(BigDecimalUtil.add(model.getYearpurAmount(), yearAmount));
				model.setFeeAmount(BigDecimalUtil.add(model.getFeeAmount(), fee));
				model.setUpdateTime(new Date());
				cimOrgFeeGatherMapper.updateByPrimaryKeySelective(model);
			}

			//记录每天销售费用明细
			CimOrgFeePerDay  cimOrgFeePerDay = new CimOrgFeePerDay();
			cimOrgFeePerDay.setInvestId(investRecord.getBizId());
			if(isFirstCalculate){
				cimOrgFeePerDay.setFeeGenerateTime(investRecord.getInvestTime());
			}else{
				cimOrgFeePerDay.setFeeGenerateTime(new Date());
			}
			cimOrgFeePerDay = cimOrgFeePerDayMapper.queryByInvestIdAndFeeDate(investRecord.getBizId(),DateUtils.format(cimOrgFeePerDay.getFeeGenerateTime(), DateUtils.FORMAT_SHORT));
			if(cimOrgFeePerDay == null){
				cimOrgFeePerDay =  new CimOrgFeePerDay();
				cimOrgFeePerDay.setInvestId(investRecord.getBizId());
				if(isFirstCalculate){
					cimOrgFeePerDay.setFeeGenerateTime(investRecord.getInvestTime());
				}else{
					cimOrgFeePerDay.setFeeGenerateTime(new Date());
				}
			}
			cimOrgFeePerDay.setFeedetailId(uuId);
			cimOrgFeePerDay.setInvestId(investRecord.getBizId());
			cimOrgFeePerDay.setProductDeadline(investRecord.getProductDays());
			cimOrgFeePerDay.setYearProportionRatio(yearProportionRatio);
			cimOrgFeePerDay.setFeeAmount(fee);
			cimOrgFeePerDay.setYearAmount(yearAmount);
			cimOrgFeePerDay.setBalanceStatus((byte)0);
			cimOrgFeePerDay.setCreateTime(new Date());
			if(cimOrgFeePerDay.getId()!=null){
				cimOrgFeePerDayMapper.updateByPrimaryKeySelective(cimOrgFeePerDay);
			}else{
				cimOrgFeePerDayMapper.insertSelective(cimOrgFeePerDay);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * 按天计算可赎回、转让产品锁定期后销售费用
	 * 
	 * @param investRecord
	 */
	@SuppressWarnings("static-access")
	public void calOrgSaleFeeOncePerDay() {
		Page<CimOrgFeeGather> page  = new Page<CimOrgFeeGather>(1,100); //第一页，默认每页100条
		Map<String,Object> params = new HashMap<String,Object>();
		List<CimOrgFeeGather> detailList = cimOrgFeeGatherMapper.queryRedemptionDetialByPate(page, params);
		String feeGenerateDate = DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
		List<CimOrgFeePerDay> feePerDayList = cimOrgFeePerDayMapper.queryByFeeDetialIdAndDate(detailList,feeGenerateDate);
		Map<String,CimOrgFeePerDay> feePerDayMap = new HashMap<String,CimOrgFeePerDay>();
		for(CimOrgFeePerDay item : feePerDayList){
			feePerDayMap.put(item.getFeedetailId(), item);
		}
		List<CimOrgFeePerDay> toSaveFeePerDayList = new ArrayList<CimOrgFeePerDay>();
		List<CimOrgFeePerDay> toUpdateFeePerDayList = new ArrayList<CimOrgFeePerDay>();
		for(CimOrgFeeGather item :detailList){
			if(feePerDayMap.get(item.getFeedetailId()) == null){
				CimOrgFeePerDay insertFeePerDay = new CimOrgFeePerDay();	
				//insertFeePerDay
				toSaveFeePerDayList.add(insertFeePerDay);
				
			}else{
				toUpdateFeePerDayList.add(feePerDayMap.get(item.getFeedetailId()));
			}
			
		}
		cimOrgFeePerDayMapper.addBatch(toSaveFeePerDayList);
		cimOrgFeePerDayMapper.updateBatch(toUpdateFeePerDayList);
		Thread current = Thread.currentThread();  
    	try {
			current.sleep(1000*10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Integer setRepaymentTime(String investId,Date repaymentTime) {
		CimOrgFeeGather model = new CimOrgFeeGather();
		model.setInvestId(investId);
		model.setRepaymentTime(repaymentTime);
		return cimOrgFeeGatherMapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public Boolean IsFirstInvest(InvestRecordWrapper investRecord) {
		return  cimProductInvestRecordMapper.queryFirsInvestTime(investRecord.getUserId(),investRecord.getProductOrgId()) == 1 ;
	}
	
    @Override
	 public DataTableReturn queryOrgSaleFee(Map<String,Object> params, DataTable dataTable) throws Exception {
		 Page<OrgSaleFeeData> page = new Page<OrgSaleFeeData>(dataTable.getStart() / dataTable.getLength() + 1,dataTable.getLength());
		 List<OrgSaleFeeData> feeList = cimOrgFeedetailMapper.queryOrgSaleFee(page, params);
		 for(OrgSaleFeeData item :feeList){
			 if(item.getDaySaleAmount()!=null){
				 item.setDaySaleAmount(BigDecimalUtil.divide(item.getDaySaleAmount(), 10000));
				 item.setAvgInvest(BigDecimalUtil.divide(item.getDaySaleAmount(), item.getInvestPersonAmount()));
			 }
			 if(item.getDaySaleForYearAmount()!=null){
				 item.setDaySaleForYearAmount(BigDecimalUtil.divide(item.getDaySaleForYearAmount(), 10000));
			 }
			 if(item.getNewInvestAmount()!=null){
				 item.setNewInvestAmount(BigDecimalUtil.divide(item.getNewInvestAmount(), 10000));
			 }
			 
		 }
		 DataTableReturn dataTableReturn =new DataTableReturn();
		 dataTableReturn.setRecordsFiltered(page.getTotalCount());
		 dataTableReturn.setRecordsTotal(page.getTotalCount());
		 dataTableReturn.setData(feeList);
		 return dataTableReturn;
	 }

	@Override
	public Double queryInvestAmount(Map<String, Object> params)
			throws Exception {
		return cimOrgFeeGatherMapper.queryInvestAmount(params);
	}
	
	 public List<OrgSaleFeeData> queryOrgSaleFee(Map<String,Object> params) throws Exception {
		 List<OrgSaleFeeData> feeList = cimOrgFeedetailMapper.queryOrgSaleFee( params);
		 for(OrgSaleFeeData item :feeList){
			 if(item.getDaySaleAmount()!=null){
				 item.setDaySaleAmount(BigDecimalUtil.divide(item.getDaySaleAmount(), 10000));
				 item.setAvgInvest(BigDecimalUtil.divide(item.getDaySaleAmount(), item.getInvestPersonAmount()));
			 }
			 if(item.getDaySaleForYearAmount()!=null){
				 item.setDaySaleForYearAmount(BigDecimalUtil.divide(item.getDaySaleForYearAmount(), 10000));
			 }
			 if(item.getNewInvestAmount()!=null){
				 item.setNewInvestAmount(BigDecimalUtil.divide(item.getNewInvestAmount(), 10000));
			 }
			 
		 }
		 return feeList;
	 }

}
