package com.eshop4j.tc.fee.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.eshop4j.core.base.ResponseResult;
import com.eshop4j.core.constant.SysConfigConstant;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.tc.fee.common.config.FeeConfig;
import com.eshop4j.tc.fee.model.TCFee;
import com.eshop4j.tc.fee.model.TCFeebalance;
import com.eshop4j.tc.fee.model.TeamLeaderYearpurAmt;
import com.eshop4j.tc.fee.model.vo.FeedetailWrapper;
import com.eshop4j.tc.fee.service.TCFeeBalanceService;
import com.eshop4j.tc.fee.service.TCFeeDetailService;
import com.eshop4j.tc.fee.service.TCFeePayService;
import com.eshop4j.tc.fee.service.TCFeeService;
import com.eshop4j.web.dao.TCFeeMapper;
import com.eshop4j.web.model.crm.CrmCfgLevel;
import com.eshop4j.web.service.CrmCfgLevelService;
import com.eshop4j.web.service.SysConfigService;
import com.eshop4j.xoss.helper.ThreadpoolService;


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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TCFeeServiceImpl.class);
	
	@Resource
	private TCFeeMapper feeMapper;
	
	
	@Resource
	private TCFeeBalanceService feeBalanceService ;
	
	@Resource
	private TCFeeDetailService feeDetailService;
	
	
	@Autowired
	private TCFeePayService feePayService;
	
	@Autowired
	private FeeConfig feeConfig;
	

	@Autowired
	private SysConfigService sysConfigService;
	
	@Autowired
	private CrmCfgLevelService levelService;
	
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
			fee.setUpdateTime(date);
			if(feeMapper.isExitFee(billId,fee.getProfitCfplannerId(),feedetailWrapper.getFeetype())){
				feeMapper.updateFee(fee);
			}else{
				fee.setBizId(com.eshop4j.core.util.StringUtils.getUUID());
				fee.setInvestorId(feedetailWrapper.getInvestorId());
				fee.setOriginCfplannerId(feedetailWrapper.getCfplannerId());
				fee.setProductOrgId(feedetailWrapper.getProductOrgId());
				fee.setProductId(feedetailWrapper.getProductId());
				fee.setProductAmount(feedetailWrapper.getInvestmentAmount());
				fee.setRemark(feedetailWrapper.getRemak());
				fee.setCreateTime(feedetailWrapper.getCreateTime()==null? date : feedetailWrapper.getCreateTime());
				insert(fee);
			}
		}
		
	}
	
	
	protected  String getMonth() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		if (month == 0) {
			//1月份处理去年12月份
			year = year - 1;
			month = 12;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(year).append("-");
		if (month < 10) {
			sb.append("0");
		}
		sb.append(month);
		return sb.toString();
	}

	public void teamLeaderFeeCalculate(){
		
		String quarter_remarkStr = sysConfigService.getValuesByKey(SysConfigConstant.QUARTER_REMARK);
		
		if(StringUtils.isBlank(quarter_remarkStr))return;
		String[] quarter_remarks =  StringUtils.split(quarter_remarkStr,",");
		if(ArrayUtils.isEmpty(quarter_remarks))return;
		Map<String, String[]> quarter_remarkMap = Maps.newHashMap();
		for (String quarter_remark : quarter_remarks) {
			String[] quarter_remarkAry =  StringUtils.split(quarter_remark,"_");
			quarter_remarkMap.put(quarter_remarkAry[0], StringUtils.split(quarter_remarkAry[1],":"));
		}
		DateTime now = DateTime.now();
		int nowMonth = now.getMonthOfYear();
		String[] months = quarter_remarkMap.get(String.valueOf(nowMonth));
		if(ArrayUtils.isEmpty(months))return;
		int year = now.getYear();
		if (nowMonth == 1) {
			//1月份处理去年的
			year = year - 1;
		}
		final String begintime = StringUtils.join(new Object[]{year,months[0],"01 00:00:00"},'-');
		final String endtime = StringUtils.join(new Object[]{year,months[1],"31 23:59:59"},'-');
		final String teamLeaderMonth = StringUtils.join(new Object[]{year,months[1]},'-');
		
		int lastDay = DateTimeFormat.forPattern("yyyy-MM").parseDateTime(teamLeaderMonth).dayOfMonth().withMaximumValue().dayOfMonth().get();
		final String teamLeaderTime = StringUtils.join(new Object[]{year,months[1],lastDay+" 23:59:59"},'-');
		
		int count = feeMapper.teamLeaderCount(teamLeaderTime);
		//不重复计算
		if(count>0)return;
		final String remark = String.format("%s年第%s季度团队leader奖励",year,months[2]);
		final Date createTime = DateUtils.parse(teamLeaderTime);
		List<CrmCfgLevel>  levels = levelService.selectListByCondition(null);
		final CountDownLatch countDownLatch = new CountDownLatch(levels.size()-1);
		for (final CrmCfgLevel crmCfgLevel : levels) {
			if( ObjectUtils.equals("REGISTERED", crmCfgLevel.getCode()) )continue;
			ThreadpoolService.execute(new Runnable() {
				@Override
				public void run() {
					try{
						//展示佣金率
						Double ratio = crmCfgLevel.getAllowance().multiply(new BigDecimal(0.1d)).doubleValue();
						//实际计算佣金率
						BigDecimal teamLeaderRatio = crmCfgLevel.getAllowance().multiply(new BigDecimal(0.001d));
						List<TeamLeaderYearpurAmt> cfpYearpurAmts = feeMapper.getCfpYearpurAmt(crmCfgLevel, begintime, endtime);
						if(CollectionUtils.isEmpty(cfpYearpurAmts))return;
						Map<String, TeamLeaderYearpurAmt> teamLeaderYearpurMap = Maps.newHashMap();
						for (TeamLeaderYearpurAmt teamLeaderYearpur : cfpYearpurAmts) {
							teamLeaderYearpurMap.put(teamLeaderYearpur.getUserId(), teamLeaderYearpur);
						}
						List<TeamLeaderYearpurAmt> subYearpurAmts = feeMapper.getSubYearpurAmt(teamLeaderYearpurMap.keySet(), begintime, endtime);
						List<FeedetailWrapper> wrappers = null;
						//无下级
						if(CollectionUtils.isEmpty(subYearpurAmts)){
							wrappers = Lists.newArrayListWithCapacity(cfpYearpurAmts.size());
							for (TeamLeaderYearpurAmt cfpYearpurAmt : cfpYearpurAmts) {
								FeedetailWrapper wrapper = new FeedetailWrapper();
								BigDecimal investmentAmt = cfpYearpurAmt.getInvestmentAmt();
								BigDecimal yearpurAmt = cfpYearpurAmt.getYearpurAmt();
								BigDecimal feeAmt= yearpurAmt.multiply(teamLeaderRatio).setScale(4,BigDecimal.ROUND_DOWN);				;
								wrapper.setBillId(com.eshop4j.core.util.StringUtils.getUUID());
								wrapper.setInvestorId(cfpYearpurAmt.getUserId());
								wrapper.setCurCfplannerId(cfpYearpurAmt.getUserId());
								wrapper.setCfplannerId(cfpYearpurAmt.getUserId());
								wrapper.setProductOrgId("none");
								wrapper.setInvestmentAmount(investmentAmt);
								wrapper.setYearPurAmount(yearpurAmt);
								wrapper.setCurRatio(ratio);
								wrapper.setFeeamount(feeAmt);
								wrapper.setFeetype("1004");
								wrapper.setRemak(remark);
								wrapper.setCreateTime(createTime);
								wrappers.add(wrapper);
							}
						}else{ //有下级
							wrappers = Lists.newArrayListWithCapacity(subYearpurAmts.size());
						
							for (TeamLeaderYearpurAmt subYearpurAmt : subYearpurAmts) {
								FeedetailWrapper wrapper = new FeedetailWrapper();
								TeamLeaderYearpurAmt cfpTeamLeaderYearpurAmt =  teamLeaderYearpurMap.get(subYearpurAmt.getUserId());
								BigDecimal investmentAmt = subYearpurAmt.getInvestmentAmt().add(cfpTeamLeaderYearpurAmt.getInvestmentAmt());
								BigDecimal yearpurAmt = subYearpurAmt.getYearpurAmt().add(cfpTeamLeaderYearpurAmt.getYearpurAmt());
								BigDecimal feeAmt= yearpurAmt.multiply(teamLeaderRatio).setScale(4,BigDecimal.ROUND_DOWN);			
								wrapper.setBillId(com.eshop4j.core.util.StringUtils.getUUID());
								wrapper.setInvestorId(subYearpurAmt.getUserId());
								wrapper.setCurCfplannerId(subYearpurAmt.getUserId());
								wrapper.setCfplannerId(subYearpurAmt.getUserId());
								wrapper.setProductOrgId("none");
								wrapper.setInvestmentAmount(investmentAmt);
								wrapper.setYearPurAmount(yearpurAmt);
								wrapper.setCurRatio(ratio);
								wrapper.setFeeamount(feeAmt);
								wrapper.setFeetype("1004");
								wrapper.setRemak(remark);
								wrapper.setCreateTime(createTime);
								wrappers.add(wrapper);
							}
						}
						if(CollectionUtils.isEmpty(wrappers))return;
						feeDetailService.insertFeedetail(wrappers.toArray(new FeedetailWrapper[wrappers.size()]));
					}catch(Exception e){
						LOGGER.error("{} teamLeaderFeeCalculate Exception", crmCfgLevel.getCode(),e);
					}finally{
						countDownLatch.countDown();
					}
				}
			});
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
		}
	}
	
	
	
	
	@Override
	public ResponseResult feeBalanceProcess() throws Exception{
		
		
		teamLeaderFeeCalculate();
		
		final String month = getMonth();
		String date[] =StringUtils.split(month, "-");
		final String monthTrim = StringUtils.join(new Object[]{date[0],date[1]});
		final String begintime = StringUtils.join(new Object[]{month,"-01 00:00:00"});
		final String endtime = StringUtils.join(new Object[]{month,"-31 23:59:59"});


		int feeSummaryTotalCount = feeBalanceService.updateStatusAndGetCalculateCount(begintime, endtime, Lists.newArrayList(0,3), 1);
		
		if(feeSummaryTotalCount==0)return new ResponseResult(false, "佣金已计算,不需要重复计算");
		
		int scanCount = feeConfig.getFee_scan_count();
		
		int totalPage = feeSummaryTotalCount/scanCount;
		
		totalPage = feeSummaryTotalCount % scanCount >0 ? totalPage+1: totalPage;
		
		final Date time = new Date();
		
		final CountDownLatch downLatch = new CountDownLatch(totalPage);
		final List<Boolean> result= Lists.newArrayListWithCapacity(totalPage);
		if(totalPage==1){
			final Page<TCFeebalance> page= new Page<TCFeebalance>(1,scanCount);
			ThreadpoolService.execute(new Runnable() {
				@Override
				public void run() {
					try{
						feeBalanceService.insertFeebalances(monthTrim, time, begintime, endtime, page);
					} catch (Exception e) {
						result.add(false);
						LOGGER.error("insertFeebalances Exception month={},page={},exception={}", new Object[]{month,page,e});
					}finally{
						downLatch.countDown();
					}
				}
			});
		}else{
			
			
			for (int i = 1; i <= totalPage; i++) {
				final Page<TCFeebalance> page= new Page<TCFeebalance>(i,scanCount);
				ThreadpoolService.execute(new Runnable() {
					@Override
					public void run() {
						try{
							feeBalanceService.insertFeebalances(monthTrim, time, begintime, endtime, page);
						} catch (Exception e) {
							result.add(false);
							LOGGER.error("insertFeebalances Exception month={},page={},exception={}", new Object[]{month,page,e});
						}finally{
							downLatch.countDown();
						}
					}
				});
			}
			
			
		}
		try {
			downLatch.await();
		} catch (InterruptedException e) {
		}
		return  result.isEmpty() ? new ResponseResult(true, "佣金计算成功") :new ResponseResult(false, "佣金计算异常,请联系开发处理");
	}


}
