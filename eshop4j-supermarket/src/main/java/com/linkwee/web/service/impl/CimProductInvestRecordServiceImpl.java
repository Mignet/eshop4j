package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ObjectUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.linkwee.api.request.tc.CfplannerCustomerInvestRecordRequest;
import com.linkwee.api.response.tc.CustomerInvestRecordResponse;
import com.linkwee.api.response.tc.CustomerInvestRecordStatisticResponse;
import com.linkwee.api.response.tc.CustomerTradeMsgResponse;
import com.linkwee.api.response.tc.InvestRecordResponse;
import com.linkwee.api.response.tc.RepaymentResponse;
import com.linkwee.api.response.tc.TradeNewlyDynamicResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.constant.ApiInvokeLogConstant;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.openapi.request.InvestRecordReq;
import com.linkwee.web.dao.CimProductInvestRecordMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.model.CimProduct;
import com.linkwee.web.model.SysApiInvokeLog;
import com.linkwee.web.model.cim.CimProductInvestRecord;
import com.linkwee.web.model.cim.OrgInfo;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CimProductInvestRecordService;
import com.linkwee.web.service.CimProductService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.InvestRecordAware;
import com.linkwee.web.service.SysApiInvokeLogService;
import com.linkwee.xoss.helper.DisruptorHelper;
import com.linkwee.xoss.helper.DisruptorHelper.AbstractEventHandler;
import com.linkwee.xoss.util.RejectedExecuteRetry;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： hxb
 * 
 * @创建时间：2016年07月14日 18:04:53
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductInvestRecordService")
public class CimProductInvestRecordServiceImpl extends GenericServiceImpl<CimProductInvestRecord, Long> implements CimProductInvestRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductInvestRecordServiceImpl.class);
	
	private static final String KEY = CimProductInvestRecordServiceImpl.class.getName()+".investRecordProcess";
	
	@Autowired
	private CimProductInvestRecordMapper investRecordMapper;
	
	@Autowired
	private CrmInvestorService investorService;
	
	@Autowired
	private CimProductService productService;
	
	@Autowired
	private List<InvestRecordAware> investRecordAwares;
	
	@Autowired
	private SysApiInvokeLogService sysApiInvokeLogService;
	
	@Autowired
	private CimOrginfoService orginfoService;
	
	@Autowired
	private DisruptorHelper disruptorHelper;
	
	
	/*protected InvestRecordWorker investmentRecordWorker = new InvestRecordWorker();
	
	public CimProductInvestRecordServiceImpl() {
		new Thread(investmentRecordWorker).start();
	}*/
	
	@Override
    public GenericDao<CimProductInvestRecord, Long> getDao() {
        return investRecordMapper;
    }
	
	public DisruptorHelper getDisruptorHelper() {
		return disruptorHelper;
	}

	
	@PostConstruct
	private void init() throws Exception{
		registeredDispatcherEventHandler();
	}
	
	@SuppressWarnings("unchecked")
	private void registeredDispatcherEventHandler() throws Exception{
		try {
			getDisruptorHelper().createRingBuffer(KEY);
			LOGGER.info("registeredDispatcherEventHandler_investRecordAwares : {}",investRecordAwares);
			List<AbstractEventHandler<InvestRecordWrapper>> eventHandlers = Lists.newArrayList();
			
			for (final InvestRecordAware investRecordAware : investRecordAwares) {
				eventHandlers.add(new AbstractEventHandler<InvestRecordWrapper>() {
					@Override
					protected void onEvent(InvestRecordWrapper investRecordWrapper) throws Exception {
						try{
							InvestRecordWrapper wrapper = copy(investRecordWrapper);
							investRecordAware.investRecordProcess(wrapper);
							LOGGER.debug("DispatcherEventHandler service = {} InvestRecordWrapper={}",AopUtils.getTargetClass(investRecordAware).getAnnotation(Service.class).value(),wrapper);
						}catch(Exception e){
							LOGGER.error("DispatcherEventHandler Exception service = {} investRecordProcess()  param={} Exception={} ", new Object[]{AopUtils.getTargetClass(investRecordAware).getAnnotation(Service.class).value(),investRecordWrapper,e});							
						}
					}
				});	
			}
			getDisruptorHelper().addEventProcessor(KEY, eventHandlers.toArray(new AbstractEventHandler[0]));
		} catch (Exception e) {
			LOGGER.error("registeredDispatcherEventHandler Exception", e);
			throw e;
		}
	}
	private InvestRecordWrapper copy(InvestRecordWrapper investRecordWrapper) throws Exception{
		InvestRecordWrapper wrapper =  new InvestRecordWrapper();
		BeanUtils.copyProperties(wrapper,investRecordWrapper);
		return wrapper;
	}

	/**
	 * 查询被推荐已投资用户的userId
	 */
	@Override
	public List<CimProductInvestRecord> selectRefInvestRecord(List<String> refRegCustomers) {
		return investRecordMapper.selectRefInvestRecord(refRegCustomers);
	}
	
	@Override
	public void insertInvestRecordProcess(InvestRecordReq investRecordReq)throws Exception {
		try {
				
				CimProductInvestRecord record = new CimProductInvestRecord();
				record.setInvestId(investRecordReq.getInvestId());
				if(selectOne(record)!=null)throw new ServiceException("已经存在的投资记录: "+investRecordReq.getInvestId());
				if(investorService.queryInvestorByUserId(investRecordReq.getUserId())==null)throw new ServiceException("无效的用户编号: "+investRecordReq.getUserId());
				
				CimProduct product = new CimProduct();
				product.setThirdProductId(investRecordReq.getProductId());
				product.setOrgNumber(investRecordReq.getPlatfromId());
				product = productService.selectOne(product);
				if(product==null) throw new ServiceException("无效的平台产品: "+investRecordReq.getPlatfromId()+","+investRecordReq.getProductId());
				
				Date time = Calendar.getInstance().getTime();
				//保存投资记录
				CimProductInvestRecord investRecord = insertInvestRecord(investRecordReq, product, time);
				
				//创建投资记录包装类
				InvestRecordWrapper investRecordWrapper = createInvestRecordWrapper(product, investRecord);
				
				getDisruptorHelper().publish(KEY, investRecordWrapper);		
				//investmentRecordWorker.investRecordDispatcher(investRecordWrapper);
			
		} catch (Exception e) {
			LOGGER.error("insertInvestRecord exception investRecordReq={},exception={}", investRecordReq,e);
			throw e;
		}
		
	}
	
	@Override
	public void updateRepaymentStatus(String investRecordNo,Integer status,Date repaymentTime,BigDecimal repaymentAmount,BigDecimal accurateProfit) {
		investRecordMapper.updateRepaymentStatus(investRecordNo,status,repaymentTime,repaymentAmount, accurateProfit);
	}
	
	
	@Override
	public Double queryCustomerInvestTotalAmount(String userId) {
		return investRecordMapper.queryCustomerInvestTotalAmount(userId).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
	}
	
	@Override
	public Double queryCustomerInvestTotalProfit(String userId) {
		return investRecordMapper.queryCustomerInvestTotalProfit(userId).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
	}
	

	@Override
	public PaginatorResponse<InvestRecordResponse> queryCustomerInvestRecord(String userId,	Integer status, Page<InvestRecordResponse> page) {
		PaginatorResponse<InvestRecordResponse> paginatorResponse = new PaginatorResponse<InvestRecordResponse>();
		paginatorResponse.setDatas(investRecordMapper.queryCustomerInvestRecord(userId, status, page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}
	
	/**
	 * 根据产品编号查询在投投资记录 
	 * @param product 产品
	 * @return
	 */
	@Override
	public List<InvestRecordWrapper> getInvestRecordByProduct(CimProduct product){
		//根据产品编号查询投资记录
		List<CimProductInvestRecord> investRecords = investRecordMapper.getInvestRecordByProductIds(Sets.newHashSet(product.getProductId()));
		if(investRecords==null|| investRecords.isEmpty())return Lists.newArrayListWithCapacity(0);
		//投资记录包装类
		List<InvestRecordWrapper> investRecordWrappers = Lists.newArrayListWithCapacity(investRecords.size());
		for (CimProductInvestRecord investRecord : investRecords) {
			investRecordWrappers.add(createInvestRecordWrapper(product, investRecord));
		}
		return investRecordWrappers;
	}
	
	/**
	 * 根据产品编号查询在投投资记录 
	 * @param products 产品集合
	 * @return
	 */
	@Override
	public List<InvestRecordWrapper> getInvestRecordByProducts(List<CimProduct> products){
		//映射产品 方便遍历投资记录时根据产品编号获取具体产品信息
		Map<String, CimProduct> productMaps = Maps.newHashMap();
		for (CimProduct cimProduct : products) {
			productMaps.put(cimProduct.getProductId(), cimProduct);
		}
		
		//根据产品编号查询投资记录
		List<CimProductInvestRecord> investRecords = investRecordMapper.getInvestRecordByProductIds(productMaps.keySet());
		if(investRecords==null|| investRecords.isEmpty())return Lists.newArrayListWithCapacity(0);
		//格式化 当前日期 格式 yyyy-MM-dd
		SimpleDateFormat format = new SimpleDateFormat(DateUtils.FORMAT_SHORT);
		String curTime = format.format(DateUtils.getCurrentDate());
		//投资日期 与 解除日期 映射 以便减少解锁日期计算次数
		Map<String, String> timeMaps = Maps.newLinkedHashMap();
		
		//投资记录包装类
		List<InvestRecordWrapper> investRecordWrappers = Lists.newArrayListWithCapacity(investRecords.size());
		
		for (CimProductInvestRecord investRecord : investRecords) {
			//根据投资记录中的产品编号获取具体产品信息
			CimProduct p = productMaps.get(investRecord.getProductId());
			if(p==null) continue;
			//不需要募集 取投资时间,否则取募集成功时间
			Date investSuccessTime = ObjectUtils.equals(p.getIsCollect(), 1) ? investRecord.getStartTime() : p.getSaleEndTime();
			
			if(investSuccessTime==null) continue;
			//缓存key = 产品编号与日期
			String key = org.apache.commons.lang.StringUtils.join(new Object[]{investRecord.getProductId(),format.format(investSuccessTime)});
			//根据 key 获取  该笔投资的 的 解除日期 没有则计算 
			String lockTime = timeMaps.get(key);
			if(StringUtils.isBlank(lockTime)){
				
				//起息日  = 投资成功时间或者募集成功时间 + 1 
				lockTime =format.format(org.apache.commons.lang.time.DateUtils.addDays(investSuccessTime, p.getDeadLineMinValue()+1));
				timeMaps.put(key, lockTime);	
			}
			
			//当前日期大于解锁日期 创建投资记录包装类
			if(curTime.compareTo(lockTime)>0){
				investRecordWrappers.add(createInvestRecordWrapper(p, investRecord));
			}
			
		}
		return investRecordWrappers;
	}

	/**
	 * 保存投资记录
	 * @param investRecordReq
	 * @param product
	 * @param time
	 * @return
	 */
	private CimProductInvestRecord insertInvestRecord(InvestRecordReq investRecordReq, CimProduct product, Date time) {
		
		CimProductInvestRecord investRecord = new CimProductInvestRecord();
		investRecord.setInvestId(StringUtils.getUUID());
		investRecord.setInvestRecordNo(investRecordReq.getInvestId());
		investRecord.setTxId(investRecordReq.getTxId());
		investRecord.setBizTime(time);
		investRecord.setUserId(investRecordReq.getUserId());
		investRecord.setProductId(product.getProductId());
		investRecord.setThirdProductId(investRecordReq.getProductId());
		investRecord.setProductFeeRate(product.getFeeRatio());
		investRecord.setStartTime(investRecordReq.getInvestStartTime());
		investRecord.setEndTime(investRecordReq.getInvestEndTime());
		investRecord.setInvestAmt(investRecordReq.getInvestAmount());
		investRecord.setProfit(investRecordReq.getProfit());
		investRecord.setPlatfrom(investRecordReq.getPlatfromId());
		boolean isPlatfromFirstInvest = ObjectUtils.equals(investRecordMapper.queryUserPlatfromInvestCount(investRecordReq.getUserId(),investRecordReq.getPlatfromId()), 0);
		investRecord.setIsPlatfromFirstInvest(isPlatfromFirstInvest?1:0);
		boolean isFirstInvest = ObjectUtils.equals(investRecordMapper.queryUserInvestCount(investRecordReq.getUserId()), 0);
		investRecord.setIsFirstInvest(isFirstInvest?1:0);
		investRecord.setStatus((byte)1);
		investRecord.setCreateTime(time);
		investRecord.setUpdateTime(time);
		
		//插入投资记录
		insert(investRecord);
		return investRecord;
	}
	/**
	 * 创建投资记录包装类
	 * @param investRecordReq
	 * @param product
	 * @param investRecord
	 * @return
	 */
	private InvestRecordWrapper createInvestRecordWrapper(CimProduct product,CimProductInvestRecord investRecord) {
		InvestRecordWrapper investRecordWrapper = new InvestRecordWrapper();
		investRecordWrapper.setBizId(investRecord.getInvestId());
		investRecordWrapper.setInvestId(investRecord.getInvestRecordNo());
		investRecordWrapper.setUserId(investRecord.getUserId());
		investRecordWrapper.setProductId(product.getProductId());
		investRecordWrapper.setProductType(product.getIsCollect());
		investRecordWrapper.setProductName(product.getProductName());
		//如果是平台老用户不计算佣金
		boolean isOrgNewUser = orginfoService.isOrgNewUser(investRecord.getUserId(), investRecord.getPlatfrom());
		investRecordWrapper.setFeeRatio(isOrgNewUser ? investRecord.getProductFeeRate():BigDecimal.ZERO);
		//平台老用户
		if(!isOrgNewUser){
			OrgInfo orgInfoResponse =  orginfoService.findOrgInfo(investRecord.getPlatfrom());
			if(orgInfoResponse!=null)
				investRecordWrapper.setRemark(isOrgNewUser?null:orgInfoResponse.getOrgName() + "平台老用户,不计算佣金".intern());
		}
		investRecordWrapper.setIsPlatfromNewUser(isOrgNewUser);
		investRecordWrapper.setProductOrgId(investRecord.getPlatfrom());
		investRecordWrapper.setProductDays(product.getDeadLineMinValue());
		investRecordWrapper.setIsRedemption(product.getIsRedemption());
		investRecordWrapper.setDeadLineMinValue(product.getDeadLineMinValue());
		investRecordWrapper.setDeadLineMaxValue(product.getDeadLineMaxValue());
		investRecordWrapper.setInvestAmt(investRecord.getInvestAmt());
		investRecordWrapper.setInvestTime(investRecord.getStartTime());
		investRecordWrapper.setFirstInvest(ObjectUtils.equals(investRecord.getIsFirstInvest(), 1));
		investRecordWrapper.setPlatfromFirstInvest(ObjectUtils.equals(investRecord.getIsPlatfromFirstInvest(), 1));
		investRecordWrapper.setEndTime(investRecord.getEndTime());
		return investRecordWrapper;
	}
	
	/*class InvestRecordWorker implements Runnable{
		
		boolean running=true;
		
		volatile boolean iswait =false;
		
		private static final int DEFAULT_SIZE=15000;
		BlockingQueue<InvestRecordWrapper> investRecordQueue;
		
		public InvestRecordWorker() {
			this(DEFAULT_SIZE);
		}
		
		public InvestRecordWorker(int queueSize) {
			investRecordQueue = new TraceBlockingQueue<InvestRecordWrapper>(queueSize);
		}
		
		public void investRecordDispatcher(InvestRecordWrapper recordWrapper){
			try {
				investRecordQueue.offer(recordWrapper, 600l,TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				LOGGER.warn("investRecordDispatcher Exception", e);
			}
		}
		
	
		@Override
		public void run() {
			
			while (running) {
				try {
					if(investRecordQueue.isEmpty())continue;
					
					InvestRecordWrapper investRecordWrapper = investRecordQueue.poll(600l,TimeUnit.SECONDS);
					
					if(investRecordWrapper==null)continue;
					
					for (InvestRecordAware investRecordAware : investRecordAwares) {
						try{
							investRecordAware.investRecordProcess(investRecordWrapper);
						}catch(Exception e){
							LOGGER.warn( "service = {} investRecordProcess()  param={} Exception={} ", new Object[]{AopUtils.getTargetClass(investRecordAware).getAnnotation(Service.class).value(),investRecordWrapper,e});							
						}
					}
				} catch (Exception e) {
					LOGGER.warn("InvestmentRecordWorker Exception", e);
				}
			}
		}
		
	}*/

	@Override
	public CustomerInvestRecordStatisticResponse queryCfplannerCustomerInvestRecordStatistic(CfplannerCustomerInvestRecordRequest req) {
		return investRecordMapper.queryCfplannerCustomerInvestRecordStatistic(req.getUserId(), req.getDateType(), DateUtils.parse(req.getDate(),DateUtils.FORMAT_SHORT));
	}

	@Override
	public PaginatorResponse<CustomerInvestRecordResponse> queryCfplannerCustomerInvestRecord(CfplannerCustomerInvestRecordRequest req) {
		Page<CustomerInvestRecordResponse> page  = new Page<CustomerInvestRecordResponse>(req.getPageIndex(),req.getPageSize()>10?10:req.getPageSize()); //默认每页10条
		PaginatorResponse<CustomerInvestRecordResponse> paginatorResponse = new PaginatorResponse<CustomerInvestRecordResponse>();
		paginatorResponse.setDatas(investRecordMapper.queryCfplannerCustomerInvestRecord(req.getUserId(), req.getDateType(), DateUtils.parse(req.getDate(),DateUtils.FORMAT_SHORT), page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public PaginatorResponse<CustomerInvestRecordResponse> queryCfplannerInvestCustomerDetail(CfplannerCustomerInvestRecordRequest req) {
		Page<CustomerInvestRecordResponse> page  = new Page<CustomerInvestRecordResponse>(req.getPageIndex(),req.getPageSize()>10?10:req.getPageSize()); //默认每页10条
		PaginatorResponse<CustomerInvestRecordResponse> paginatorResponse = new PaginatorResponse<CustomerInvestRecordResponse>();
		paginatorResponse.setDatas(investRecordMapper.queryCfplannerInvestCustomerDetail(req.getUserId(), req.getDateType(), DateUtils.parse(req.getDate(),DateUtils.FORMAT_SHORT), page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}
	
	

	@Override
	public PaginatorResponse<RepaymentResponse> queryCustomerRepayment(String userId,String customerId,Page<RepaymentResponse> page) {
		PaginatorResponse<RepaymentResponse> paginatorResponse = new PaginatorResponse<RepaymentResponse>();
		if(StringUtils.isBlank(customerId)) customerId = null;
		//DateTime now = DateTime.now();
		paginatorResponse.setDatas(investRecordMapper.queryCustomerRepayment(userId,customerId,page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public int queryCustomerInvestTradeMsgCount(String userId) {
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_CUSTOMER_TRADELIST_SQ, userId, AppTypeEnum.CHANNEL.getKey());
		Date date = apiInvokeLog!=null ? apiInvokeLog.getChgTime(): DateUtils.parse("1990-01-01 00:00:00");
		return investRecordMapper.queryCustomerInvestTradeMsgCount(date, userId);
	}

	@Override
	public PaginatorResponse<CustomerTradeMsgResponse> queryCustomerInvestTradeMsg(String userId,String customerId, Page<CustomerTradeMsgResponse> page) {
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_CUSTOMER_TRADELIST_SQ, userId, AppTypeEnum.CHANNEL.getKey());
		String date = apiInvokeLog!=null ?  DateUtils.format(apiInvokeLog.getChgTime()): DateUtils.format(DateUtils.parse("1990-01-01 00:00:00"));
		PaginatorResponse<CustomerTradeMsgResponse> paginatorResponse = new PaginatorResponse<CustomerTradeMsgResponse>();
		paginatorResponse.setDatas(investRecordMapper.queryCustomerInvestTradeMsg(date,customerId,userId, page));
		paginatorResponse.setValuesByPage(page);
		sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.NAME_CUSTOMER_TRADELIST_SQ, userId,AppTypeEnum.CHANNEL.getKey());
		return paginatorResponse;
	}
	

	@Override
	public int queryCustomerRepaymentTradeMsgCount(String userId) {
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_CUSTOMER_TRADELIST_SH, userId, AppTypeEnum.CHANNEL.getKey());
		Date date = apiInvokeLog!=null ? apiInvokeLog.getChgTime(): DateUtils.parse("1990-01-01 00:00:00");
		return investRecordMapper.queryCustomerRepaymentTradeMsgCount(date, userId);
	}
	
	@Override
	public PaginatorResponse<CustomerTradeMsgResponse> queryCustomerRepaymentTradeMsg(String userId,String customerId, Page<CustomerTradeMsgResponse> page) {
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_CUSTOMER_TRADELIST_SH, userId, AppTypeEnum.CHANNEL.getKey());
		String date = apiInvokeLog!=null ?  DateUtils.format(apiInvokeLog.getChgTime()): DateUtils.format(DateUtils.parse("1990-01-01 00:00:00"));
		PaginatorResponse<CustomerTradeMsgResponse> paginatorResponse = new PaginatorResponse<CustomerTradeMsgResponse>();
		paginatorResponse.setDatas(investRecordMapper.queryCustomerRepaymentTradeMsg( date,customerId,userId,page));
		paginatorResponse.setValuesByPage(page);
		sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.NAME_CUSTOMER_TRADELIST_SH, userId,AppTypeEnum.CHANNEL.getKey());
		return paginatorResponse;
	}

	@Override
	public PaginatorResponse<TradeNewlyDynamicResponse> queryCfpNewlyDynamic(Page<TradeNewlyDynamicResponse> page, String userId) {
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.INVESTRECORD_CFP_DYNAMIC_PAGELIST, userId, AppTypeEnum.CHANNEL.getKey());
		String date = apiInvokeLog!=null ?  DateUtils.format(apiInvokeLog.getChgTime()): DateUtils.format(DateUtils.parse("1990-01-01 00:00:00"));
		PaginatorResponse<TradeNewlyDynamicResponse> paginatorResponse = new PaginatorResponse<TradeNewlyDynamicResponse>();
		List<TradeNewlyDynamicResponse> dynamicList = investRecordMapper.queryCfpNewlyDynamic(userId,date, page);
		/*for(TradeNewlyDynamicResponse item:dynamicList){
			String time = item.getTime();
			if(DateUtils.compareDate(DateUtils.parse(date),DateUtils.parse(time))==1 ){
				item.setReadFlag(true);
			}else{
				item.setReadFlag(false);
			}
			time = DateUtils.format(DateUtils.parse(time), DateUtils.FORMAT_MM);
			item.setTime(time);
		}*/
		paginatorResponse.setDatas(dynamicList);
		paginatorResponse.setValuesByPage(page);
		sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.INVESTRECORD_CFP_DYNAMIC_PAGELIST, userId,AppTypeEnum.CHANNEL.getKey());
		return paginatorResponse;
	}

	@Override
	public Double queryCurrInvestAmount(String userId) {
		return investRecordMapper.queryCurrInvestAmount(userId).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
	}


	@Override
	public int queryCfpNewlyDynamicUnReadCount(String userId) {
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.INVESTRECORD_CFP_DYNAMIC_PAGELIST, userId, AppTypeEnum.CHANNEL.getKey());
		String date = apiInvokeLog!=null ?  DateUtils.format(apiInvokeLog.getChgTime()): DateUtils.format(DateUtils.parse("1990-01-01 00:00:00"));
		return investRecordMapper.queryCfpNewlyDynamicUnReadCount(userId,date);
	}

	@Override
	public PaginatorResponse<CustomerTradeMsgResponse> queryCustomerTradeMsg(String customerId, String userId, Page<CustomerTradeMsgResponse> page) {
		PaginatorResponse<CustomerTradeMsgResponse> paginatorResponse = new PaginatorResponse<CustomerTradeMsgResponse>();
		paginatorResponse.setDatas(investRecordMapper.queryCustomerTradeMsg(customerId, userId, page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@RejectedExecuteRetry
	@Override
	public boolean updateInvestRecordEndTimeByProductId(CimProductInvestRecord investRecord)throws Exception {
		return investRecordMapper.updateInvestRecordEndTimeByProductId(investRecord)>0;
	}

	
	@Override
	public Map<String, String> getInvestRecordCounts(String userId) {
		return investRecordMapper.getInvestRecordCounts(userId);
	}

	@Override
	public int queryUserPlatfromInvestCount(String userId, String platfromId) {
		return investRecordMapper.queryUserPlatfromInvestCount(userId, platfromId);
	}

	
	
}
