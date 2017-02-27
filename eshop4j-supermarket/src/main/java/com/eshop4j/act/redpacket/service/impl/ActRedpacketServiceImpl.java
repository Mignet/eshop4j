package com.eshop4j.act.redpacket.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.eshop4j.act.redpacket.model.ActRedpacket;
import com.eshop4j.act.redpacket.model.ActRedpacketDetail;
import com.eshop4j.act.redpacket.model.ActRedpacketSendRecord;
import com.eshop4j.act.redpacket.model.RedpacketImportModel;
import com.eshop4j.act.redpacket.model.SendContext;
import com.eshop4j.act.redpacket.service.ActRedpacketRuleService;
import com.eshop4j.act.redpacket.service.ActRedpacketSendRecordService;
import com.eshop4j.act.redpacket.service.ActRedpacketService;
import com.eshop4j.act.redpacket.service.ActRedpacketUseRecordService;
import com.eshop4j.core.Import.PoiImport;
import com.eshop4j.core.constant.SysConfigConstant;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.exception.ServiceException;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.dao.ActRedpacketDetailMapper;
import com.eshop4j.web.dao.ActRedpacketMapper;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.web.enums.CfpNewcomerTaskEnum;
import com.eshop4j.web.enums.InvestorNewcomerTaskEnum;
import com.eshop4j.web.enums.MsgModuleEnum;
import com.eshop4j.web.enums.SmsTypeEnum;
import com.eshop4j.web.model.CrmUserInfo;
import com.eshop4j.web.model.acc.AcAccountRecharge;
import com.eshop4j.web.model.vo.InvestRecordWrapper;
import com.eshop4j.web.request.act.RedPacketInfoRequest;
import com.eshop4j.web.response.act.RedpacketListResponse;
import com.eshop4j.web.response.act.RedpacketStatisticsResponse;
import com.eshop4j.web.service.AcAccountBindService;
import com.eshop4j.web.service.CrmUserInfoService;
import com.eshop4j.web.service.SmMessageQueueService;
import com.eshop4j.web.service.SysConfigService;
import com.eshop4j.xoss.helper.ConfigHelper;
import com.eshop4j.xoss.helper.DateUtils;
import com.eshop4j.xoss.helper.PushMessageHelper;
import com.eshop4j.xoss.helper.ThreadpoolService;
import com.eshop4j.xoss.util.InterceptUtility;


 /**
 * 
 * @描述：ActRedpacketService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年07月31日 13:13:55
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actRedpacketService")
public class ActRedpacketServiceImpl extends GenericServiceImpl<ActRedpacket, Long> implements ActRedpacketService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActRedpacketServiceImpl.class);
	

	@Autowired
	private ActRedpacketMapper actRedpacketMapper;
	
	@Autowired
	private ActRedpacketDetailMapper  redpacketDetailMapper;
	
	@Autowired
	private ActRedpacketRuleService redpacketRuleService;
	
	@Autowired
	private ActRedpacketUseRecordService redpacketUseRecordService;
	
	@Autowired
	private AcAccountBindService accountBindService;
	
	@Autowired
	private CrmUserInfoService userInfoService;
	
	@Autowired
	private SmMessageQueueService messageQueueService;
	
	@Autowired
	private PushMessageHelper pushMessageHelper;
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@Resource
	private ConfigHelper configHelper;

	
	@Autowired
	private ActRedpacketSendRecordService redpacketSendRecordService;
	
	@Value("${REDPACKET_INSERT_NUMBER}") 
	private int redpacket_insert_numer;
	
	@Value("${REDPACKET_NUMBER_THAN_USE_SUBSECTION}") 
	private int redpacket_number_than_use_subsection;
	
	
	
	private SendRedpacketCallback customerSendRedpacketCallback = new SendRedpacketCallback(){
		public void setRedpacketAttr(CrmUserInfo userInfo,ActRedpacketDetail redpacket,Set<String> mobileOrUserIds) throws Exception{
			redpacket.setUserId(userInfo.getUserId());
			redpacket.setUserMobile(userInfo.getMobile());
			redpacket.setUserName(userInfo.getUserName());
			redpacket.setStatus(2);
			mobileOrUserIds.add(userInfo.getMobile());
		}

		@Override
		public void sendMsgs(Set<String> mobiles,String content) {
			try{
				messageQueueService.batchSendMessage(mobiles,AppTypeEnum.INVESTOR, MsgModuleEnum.RECIVEREDPAPERBYSYS,content);
			}catch(Exception e){
				LOGGER.warn("Send customer Redpacket Msg Exception ", e);
			}
		}

		@Override
		public String getMsgContent(ActRedpacket redpacket, int sendNum) {
			return redpacket.getMoney().setScale(2, BigDecimal.ROUND_DOWN).toString();
		}
		
	};
	
	private SendRedpacketCallback lcsSendRedpacketCallback = new SendRedpacketCallback(){
		@Override
		public void setRedpacketAttr(CrmUserInfo userInfo,ActRedpacketDetail redpacket,Set<String> mobileOrUserIds) throws Exception{
			redpacket.setCfplannerId(userInfo.getUserId());
			redpacket.setCfplannerMobile(userInfo.getMobile());
			redpacket.setCfplannerName(userInfo.getUserName());
			redpacket.setStatus(1);
			mobileOrUserIds.add(userInfo.getUserId());
		}

		@Override
		public void sendMsgs(Set<String> mobileOrUserIds,String content) {
			try{
				pushMessageHelper.pushMessageListAsyn(AppTypeEnum.CHANNEL, SmsTypeEnum.LCSRECEIVESYSREDPAPER , mobileOrUserIds, "系统消息",content, null, true);
			}catch(Exception e){
				LOGGER.warn("Send lcs Redpacket Msg Exception ", e);
			}
		}

		@Override
		public String getMsgContent(ActRedpacket redpacket, int sendNum) {
			return String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_LCSRECEIVESYSREDPAPER),sendNum,redpacket.getMoney().setScale(2, BigDecimal.ROUND_DOWN));
		}
		
	};
	
	@Override
    public GenericDao<ActRedpacket, Long> getDao() {
        return actRedpacketMapper;
    }

	@Override
	public boolean isExistRedpacket(String redpacketId) {
		return actRedpacketMapper.isExistRedpacket(redpacketId);
	}
	
	

	@Override
	public boolean useRedpacket(InvestRecordWrapper investRecord,ActRedpacketDetail redpacket) throws Exception {
		LOGGER.debug("start use redpacket investRecordId={},redpacketId ={}",investRecord.getInvestId(), redpacket.getRedpacketDetailId());
		boolean success = ObjectUtils.equals(redpacketDetailMapper.useRedpacket(redpacket.getRedpacketDetailId(),redpacket.getUpdateTime()), 1);
		if(success){
			LOGGER.debug("use redpacket succes investRecordId={},redpacketId ={}",investRecord.getInvestId(), redpacket.getRedpacketDetailId());
			String rechargeId = recharge(redpacket);
			LOGGER.debug("redpacket recharge  success rechargeId={}", rechargeId);
			redpacketUseRecordService.insertRedpacketUseRecord(rechargeId, investRecord, redpacket);
		}
		return success;
	}
    
	private String recharge(ActRedpacketDetail redpacketDetail) throws Exception{
		AcAccountRecharge recharge = new AcAccountRecharge();
		recharge.setRedpacketId(redpacketDetail.getRedpacketDetailId());
		recharge.setTransAmount(redpacketDetail.getMoney());
		recharge.setUserId(redpacketDetail.getUserId());
		recharge.setUserType(2);
		recharge.setTransType(4);
		recharge.setRemark("投资返现红包到账");
		return accountBindService.accountRecharge(recharge);
	}

	@Override
	public DataTableReturn getRedpacketList(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		Page<RedpacketListResponse> page = new Page<RedpacketListResponse>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<RedpacketListResponse> list = actRedpacketMapper.getRedpacketList(page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void insertRedpacket(RedPacketInfoRequest redPacketInfo)throws Exception {
		try{
			Date date = new Date();
			boolean result =  insert(redPacketInfo, date);
			if(result){
				result = redpacketRuleService.insertRedpacketRule(redPacketInfo, date);
			}
			if(!result)throw new ServiceException("新增失败");
		}catch(Exception e){
			LOGGER.error("insertRedpacket exception redPacketInfo={}",redPacketInfo, e);
			throw e;
		}
	}

	private boolean insert(RedPacketInfoRequest redPacketInfo,Date date){
		ActRedpacket redpacket = createRedpacket(StringUtils.getUUID(), redPacketInfo, date);
		redpacket.setCreateTime(date);
		boolean result = insert(redpacket)>0;
		if(result)redPacketInfo.setRedpacketId(redpacket.getRedpacketId());
		return result;
	}
	
	private ActRedpacket createRedpacket(String redpacketId,RedPacketInfoRequest redPacketInfo,Date date){
		ActRedpacket redpacket = new ActRedpacket();
		redpacket.setRedpacketId(redpacketId);
		redpacket.setName(redPacketInfo.getName());
		redpacket.setMoney(new BigDecimal(redPacketInfo.getMoney()));
		redpacket.setRemark(redPacketInfo.getRemark());
		redpacket.setIsActivity(StringUtils.isNotBlank(redPacketInfo.getActivityId()) ? 1 : 0);
		redpacket.setActivityCode(redPacketInfo.getActivityId());
		redpacket.setActivityName(redPacketInfo.getActivityName());
		redpacket.setUpdateTime(date);
		redpacket.setOperator(redPacketInfo.getOperator());
		return redpacket;
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void updateRedpacket(RedPacketInfoRequest redPacketInfo)throws Exception {
		String redpacketId = redPacketInfo.getRedpacketId();
		if(StringUtils.isBlank(redpacketId) || !isExistRedpacket(redpacketId))throw new ServiceException("红包不存在!");
		
		Date date= new Date();
		ActRedpacket redpacket = createRedpacket(redPacketInfo.getRedpacketId(), redPacketInfo,date);
		boolean result = actRedpacketMapper.updateRedpacket(redpacket)>0;
		if(result){
			result = redpacketRuleService.updateRedpacketRule(redPacketInfo, date);
		}
		if(!result)throw new ServiceException("更新失败");
	}
	
	/**
	 * 根据编号查询红包
	 * @param redpacketId
	 * @return
	 */
	@Override
	public ActRedpacket getRedpacket(String redpacketId){
		List<ActRedpacket> redpackets = getRedpackets(redpacketId);
		return redpackets==null||redpackets.isEmpty()?null:redpackets.get(0);
	}
	
	
	@Override
	public List<ActRedpacket> getRedpackets(String... redpacketId) {
		return actRedpacketMapper.getRedpackets(Lists.newArrayList(redpacketId));
	}
	

	@Override
	public boolean isSendRedpacket(String redpacketId) throws Exception{
		ActRedpacket redpacket = getRedpacket(redpacketId);
		if(redpacket==null)throw new ServiceException("红包不存在");
		return redpacket.getSendCount()>0;
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Set<String> sendCustomerRedPacket(MultipartFile file, String redpacketId,Integer sendNum, Date expiresDate,String operator) throws Exception {
		return sendRedpacekt(file, redpacketId, sendNum, expiresDate,operator,customerSendRedpacketCallback);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Set<String> sendLcsRedPacket(MultipartFile file, String redpacketId,Integer sendNum, Date expiresDate,String operator)throws Exception {
		return sendRedpacekt(file, redpacketId, sendNum, expiresDate,operator,lcsSendRedpacketCallback);
	}
	
	private List<RedpacketImportModel> getRedpacketImportModels(MultipartFile file)throws Exception{
		 InputStream inputStream = file.getInputStream();
		 return PoiImport.dataImport(inputStream, RedpacketImportModel.class);
	}
	
	private Set<String> sendRedpacekt(MultipartFile file, String redpacketId,final Integer sendNum, final Date expiresDate,final String operator,final SendRedpacketCallback callback)throws Exception{
		long s = System.currentTimeMillis();
		LOGGER.debug("start Redpacket");
		Validate.notEmpty(redpacketId,"红包编号不能为空");
		final ActRedpacket redpacket = getRedpacket(redpacketId);
		Validate.notNull(redpacket,"红包不存在");
		//解析导入模板
		final List<RedpacketImportModel>  redpacketImportModels = getRedpacketImportModels(file);
		Validate.notEmpty(redpacketImportModels,"导入用户为空");
		final String sendId = StringUtils.getUUID();
		final Date time = new Date();
		
		final Set<ActRedpacketDetail> redpacketDetails = Sets.newConcurrentHashSet();
		final Set<String> msg  = Sets.newConcurrentHashSet();
		final Set<String> mobileOrUserIds  = Sets.newConcurrentHashSet();
		List<List<RedpacketImportModel>>  redpacketImportModelss = Lists.newArrayList();
		// 按 redpacket_insert_numer的4倍进行 用户数据  分段
		InterceptUtility.subsection(redpacketImportModels, redpacketImportModelss,redpacket_insert_numer*4);
		
		
		final CountDownLatch countDownLatch = new CountDownLatch(redpacketImportModelss.size());
		/**
		 * 分批次 进行多线程创建红包
		 */
		for (final List<RedpacketImportModel> list : redpacketImportModelss) {
			ThreadpoolService.execute(new Runnable() {
				@Override
				public void run() {
					try{
						List<String> sendRedpacketUserMobiles = Lists.newArrayListWithCapacity(list.size());
						for (RedpacketImportModel redpacketImportModel : list) {
							sendRedpacketUserMobiles.add(redpacketImportModel.getMobile());
						}
						//查询本批次发放的所有用户信息 避免单次慢查询
						List<CrmUserInfo> userInfos =  userInfoService.queryUserListByMobileList(sendRedpacketUserMobiles);
						if(userInfos==null )userInfos =  Lists.newArrayListWithCapacity(1);
						
						Map<String, CrmUserInfo> userMaps = Maps.newHashMapWithExpectedSize(userInfos.size());
						//映射用户手机号码与用户信息 方便查询用户信息
						for (CrmUserInfo crmUserInfo : userInfos) {
							userMaps.put(crmUserInfo.getMobile(), crmUserInfo);
						}
						//清空无用数据 
						sendRedpacketUserMobiles=null;
						userInfos = null;
						
						for (RedpacketImportModel redpacketImportModel : list) {
							CrmUserInfo crmUserInfo = userMaps.get(redpacketImportModel.getMobile());
							if(crmUserInfo ==null){
								msg.add("用户不存在_"+redpacketImportModel.getMobile());
								continue;
							}
							//创建红包
							redpacketDetails.add(createRedpacketDetail(crmUserInfo, redpacket, sendId, expiresDate, time, callback,mobileOrUserIds));
						}
					}catch(Exception e){
						LOGGER.warn("创建红包",e);
					}finally{
						countDownLatch.countDown();
					}
				}
			});
		}
		countDownLatch.await();
		
     		//可发放红包为空 返回
		if(redpacketDetails.isEmpty()) return msg;
		
		//一次红包 * 发放数量 = 发放总红包数据
		int size = redpacketDetails.size() * sendNum;
		
		List<List<ActRedpacketDetail>> redpacketss = Lists.newArrayList();
		//红包总数据大于  redpacket_number_than_use_subsection 
		//true : 分批插入  | false :  一次插入
		if(size > redpacket_number_than_use_subsection ){
			InterceptUtility.subsection(new ArrayList<ActRedpacketDetail>(redpacketDetails),redpacketss,redpacket_insert_numer);
		}else{
			redpacketss.add(new ArrayList<ActRedpacketDetail>(redpacketDetails));
		}
		//批量插入红包
		for (int i = 0; i < sendNum; i++) {
			for (List<ActRedpacketDetail> redpacketList : redpacketss) {
				for (ActRedpacketDetail redpacketDetail : redpacketList) {
					redpacketDetail.setRedpacketDetailId(StringUtils.getUUID());
				}
				redpacketDetailMapper.inserts(redpacketList);
			}
		}
		redpacketss = null;
		//保存发放信息
		saveRedpacketSendRecord(redpacket, sendId, redpacket.getMoney().multiply(new BigDecimal(size)), size,mobileOrUserIds.size(), expiresDate, time,operator);
		updateSendRedpacket(redpacketId, operator, time);
		LOGGER.debug("send Redpacket end time = {}",  System.currentTimeMillis()-s);
		//异步发送消息
		ThreadpoolService.execute(new Runnable(){
			@Override
			public void run() {
				try{
					callback.sendMsgs(mobileOrUserIds,callback.getMsgContent(redpacket, sendNum));
				}catch(Exception e){
					LOGGER.warn("sendMsgs exception",e);
				}
			}});
		
		return msg;
	}

	private void updateSendRedpacket(String redpacketId, final String operator, final Date time) {
		//更新红包信息
		ActRedpacket redpacketNew = new ActRedpacket();
		redpacketNew.setRedpacketId(redpacketId);
		redpacketNew.setUpdateTime(time);
		redpacketNew.setOperator(operator);
		actRedpacketMapper.updateSendRedpacket(redpacketNew);
	}
	
	

	
	/**
	 * 
	 * @param redpacket
	 * @param sendId 发放编号
	 * @param money 发放金额
	 * @param count 发放数量
	 * @param numer 发放人数
	 * @param expiresDate 过期时间
	 * @param time 时间
	 * @param operator 
	 * @return
	 */
	private void saveRedpacketSendRecord(ActRedpacket redpacket, String sendId,BigDecimal money,Integer count,Integer numer,Date expiresDate,Date time,String operator){
		ActRedpacketSendRecord sendRecord = new ActRedpacketSendRecord();
		sendRecord.setRedpacketId(redpacket.getRedpacketId());
		sendRecord.setSendId(sendId);
		sendRecord.setExpiresTime(expiresDate);
		sendRecord.setExpiresStatus(0);
		sendRecord.setSendMoney(money);
		sendRecord.setSendCount(count);
		sendRecord.setSendNumber(numer);
		sendRecord.setSendTime(time);
		sendRecord.setOperator(operator);
		redpacketSendRecordService.insert(sendRecord);
	}
	
	/**
	 * 创建红包明细
	 * @param mobile
	 * @param redpacket
	 * @param expiresDate
	 * @param time
	 * @param callback
	 * @return
	 * @throws Exception
	 */
	private ActRedpacketDetail createRedpacketDetail(CrmUserInfo userInfo,ActRedpacket redpacket,String sendId, Date expiresDate,Date time,SendRedpacketCallback callback,Set<String> mobileOrUserIds) throws Exception{
		ActRedpacketDetail redpacketDetail = new ActRedpacketDetail();
		redpacketDetail.setRedpacketId(redpacket.getRedpacketId());
		redpacketDetail.setSendId(sendId);
		redpacketDetail.setName(redpacket.getName());
		redpacketDetail.setMoney(redpacket.getMoney());
		redpacketDetail.setRemark(redpacket.getRemark());
		redpacketDetail.setType(redpacket.getType());
		redpacketDetail.setExpiresDate(expiresDate);
		if(null != callback)callback.setRedpacketAttr(userInfo, redpacketDetail,mobileOrUserIds);
		redpacketDetail.setCreateTime(time);
		redpacketDetail.setUpdateTime(time);
		return redpacketDetail;
	}

	
	@Override
	public RedPacketInfoRequest getRedPacketInfo(String redpacketId) {
		RedPacketInfoRequest redPacketInfo = new RedPacketInfoRequest();
		ActRedpacket redpacket = getRedpacket(redpacketId);
		if(redpacket==null)throw new ServiceException("红包不存在");
		setRedPacketInfo(redPacketInfo, redpacket);
		redpacketRuleService.getRedPacketRuleInfo(redPacketInfo, redpacketId);
		return redPacketInfo;
	}
	
	private void setRedPacketInfo(RedPacketInfoRequest redPacketInfo,ActRedpacket redpacket){
		redPacketInfo.setName(redpacket.getName());
		redPacketInfo.setMoney(redpacket.getMoney().doubleValue());
		redPacketInfo.setRemark(redpacket.getRemark());
	}

	@Override
	public RedpacketStatisticsResponse getRedpacketStatistics(String date) {
		if(StringUtils.isBlank(date)){
			date = DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
		}
		if(StringUtils.isBlank(date)) return null;
		Date start = DateUtils.parse(Joiner.on(" ").join(new Object[]{date,"00:00:00"}));
		Date end =DateUtils.parse(Joiner.on(" ").join(new Object[]{date,"23:59:59"}));
		return actRedpacketMapper.getRedpacketStatistics(start, end);
	}
	
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void  customerRegisterRedPacekt(CrmUserInfo userInfo)throws Exception {
		String msg = sysConfigService.getValuesByKey(SysConfigConstant.REGISTER_CUSTOMER_REDPACEKT_MSG);
		SendContext sendContext =new SendContext(userInfo, SysConfigConstant.REGISTER_REDPACEKT_SWITCH,true, SysConfigConstant.REGISTER_REDPACEKT_IDS, SysConfigConstant.REGISTER_REDPACEKT_SEND_IDS);
		customerSystemRedpacekt(sendContext,msg);
		//registerRedPacekt(userInfo, customerSendRedpacketCallback,StringUtils.isBlank(msg)?"一组580.00":msg);
	}
	

	
	
	
	/*private void registerRedPacekt(CrmUserInfo userInfo,SendRedpacketCallback sendRedpacketCallback,String msg) throws Exception {
		LOGGER.info("registerRedPacekt userInfo={}",userInfo);
		String switchStr = sysConfigService.getValuesByKey(SysConfigConstant.REGISTER_REDPACEKT_SWITCH);
		if(StringUtils.isBlank(switchStr))return;	
		boolean isSwitch = Boolean.valueOf(switchStr);
		if(!isSwitch)return;
		//获取红包配置
		String  registerRedpacektIds = sysConfigService.getValuesByKey(SysConfigConstant.REGISTER_REDPACEKT_IDS); 
		String  registerRedpacektSendIds = sysConfigService.getValuesByKey(SysConfigConstant.REGISTER_REDPACEKT_SEND_IDS); 
		if(StringUtils.isBlank(registerRedpacektIds)||StringUtils.isBlank(registerRedpacektSendIds))return;	
		
		String[] registerRedpacektIdArray = org.apache.commons.lang.StringUtils.split(registerRedpacektIds, ",");
		String[] registerRedpacektSendIdArray = org.apache.commons.lang.StringUtils.split(registerRedpacektSendIds, ",");
		//查询注册红包
		List<ActRedpacket> redpackets =  getRedpackets(registerRedpacektIdArray);
		if(redpackets==null || redpackets.isEmpty())return;
		//发送记录
		List<ActRedpacketSendRecord> redpacketSends = redpacketSendRecordService.getRedpacketSendRecords(registerRedpacektSendIdArray);
		if(redpacketSends==null || redpacketSends.isEmpty())return;
		
		Map<String, ActRedpacketSendRecord> redpacketSendRecordMaps =  Maps.newHashMapWithExpectedSize(redpacketSends.size());
		for (ActRedpacketSendRecord redpacketSendRecord : redpacketSends) {
			redpacketSendRecordMaps.put(redpacketSendRecord.getRedpacketId(), redpacketSendRecord);
		}
		List<ActRedpacketDetail> redpacketDetails = Lists.newArrayListWithCapacity(redpackets.size());
		Set<String> mobiles = Sets.newHashSet();
		Date time = new Date();
		//创建红包
		for (ActRedpacket redpacket : redpackets) {
			ActRedpacketSendRecord redpacketSendRecord = redpacketSendRecordMaps.get(redpacket.getRedpacketId());
			if(redpacketSendRecord==null) continue;
			Date expiresTime = redpacketSendRecord.getExpiresTime();
			if( expiresTime == null ){
				expiresTime = DateTime.now().plusDays(redpacketSendRecord.getExpiresDay()).toDate();
			}
			ActRedpacketDetail redpacketDetail = createRedpacketDetail(userInfo, redpacket, redpacketSendRecord.getSendId(),expiresTime, time, sendRedpacketCallback, mobiles);
			redpacketDetail.setRedpacketDetailId(StringUtils.getUUID());
			redpacketDetails.add(redpacketDetail);
		}
		//发送红包
		if(!redpacketDetails.isEmpty()){
			redpacketDetailMapper.inserts(redpacketDetails);
			redpacketSendRecordService.updateSendRedpackets(redpacketDetails);
			actRedpacketMapper.updateSendRedpackets(redpacketSendRecordMaps.keySet());
			try{
				sendRedpacketCallback.sendMsgs(mobiles,msg);
			}catch(Throwable e){
				LOGGER.warn("Send  Redpacket Msg Exception ", e);
			}
		};
	}*/
	


	@Override
	public void lcsTaskRedPacekt(CfpNewcomerTaskEnum taskEnum,CrmUserInfo userInfo) throws Exception {
		String redpacektIdAndSendId = sysConfigService.getValuesByKey(taskEnum.getCode()); 
		if(StringUtils.isBlank(redpacektIdAndSendId))return;
		String values[] =  org.apache.commons.lang.StringUtils.split(redpacektIdAndSendId,"_");
		lcsSystemRedpacekt(new SendContext(userInfo,SysConfigConstant.LCS_TASK_REDPACEKT_CONFIG, new String[]{values[0]},new String[]{values[1]}));
		//taskRedPacekt(SysConfigConstant.LCS_TASK_REDPACEKT_CONFIG, taskEnum.getCode(), userInfo, lcsSendRedpacketCallback);
	}

	@Override
	public void customerTaskRedPacekt(InvestorNewcomerTaskEnum taskEnum,CrmUserInfo userInfo) throws Exception {
		
		String redpacektIdAndSendId = sysConfigService.getValuesByKey(taskEnum.getCode()); 
		if(StringUtils.isBlank(redpacektIdAndSendId))return;
		String values[] =  org.apache.commons.lang.StringUtils.split(redpacektIdAndSendId,"_");
		customerSystemRedpacekt(new SendContext(userInfo,SysConfigConstant.CUSTOMER_TASK_REDPACEKT_CONFIG, new String[]{values[0]},new String[]{values[1]}));
		//taskRedPacekt(SysConfigConstant.CUSTOMER_TASK_REDPACEKT_CONFIG, taskEnum.getCode(), userInfo, customerSendRedpacketCallback);
	}
	
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void lcsSystemRedpacekt(SendContext sendContext) throws Exception{
		sendSystemRedpacekt(sendContext, lcsSendRedpacketCallback, false, null);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void lcsSystemRedpacekt(SendContext sendContext,String msg) throws Exception{
		sendSystemRedpacekt(sendContext, lcsSendRedpacketCallback, true, msg);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void customerSystemRedpacekt(SendContext sendContext) throws Exception{
		sendSystemRedpacekt(sendContext, customerSendRedpacketCallback, false, null);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void customerSystemRedpacekt(SendContext sendContext,String msg) throws Exception{
		sendSystemRedpacekt(sendContext, customerSendRedpacketCallback, true, msg);
	}
	
	
	/*private void taskRedPacekt(String taskKey,String taskCode,CrmUserInfo userInfo,SendRedpacketCallback callback) throws Exception{
		LOGGER.info("{} task redpacket userInfo={}",taskCode,userInfo);
		String switchStr = sysConfigService.getValuesByKey(taskKey);
		if(StringUtils.isBlank(switchStr))return;	
		boolean isSwitch = Boolean.valueOf(switchStr);
		if(!isSwitch)return;
		String  redpacektIdAndSendId = sysConfigService.getValuesByKey(taskCode); 
		if(StringUtils.isBlank(redpacektIdAndSendId))return;	
		String redpacektIdAndSendIdAry[] =  org.apache.commons.lang.StringUtils.split(redpacektIdAndSendId,"_");
		//查询红包编号
		List<ActRedpacket> redpackets =  getRedpackets(redpacektIdAndSendIdAry[0]);
		if(redpackets==null || redpackets.isEmpty())return;
		//发送记录
		List<ActRedpacketSendRecord> redpacketSends = redpacketSendRecordService.getRedpacketSendRecords(redpacektIdAndSendIdAry[1]);
		if(redpacketSends==null || redpacketSends.isEmpty())return;
		Date time = new Date();
		
		//创建红包
		ActRedpacket redpacket = redpackets.get(0);
		ActRedpacketSendRecord redpacketSendRecord = redpacketSends.get(0);
	
		Date expiresTime = redpacketSendRecord.getExpiresTime();
		if( expiresTime == null ){
			expiresTime = DateTime.now().plusDays(redpacketSendRecord.getExpiresDay()).toDate();
		}
		ActRedpacketDetail redpacketDetail = createRedpacketDetail(userInfo, redpacket, redpacketSendRecord.getSendId(),expiresTime, time, callback, Sets.<String>newHashSetWithExpectedSize(1));
		redpacketDetail.setRedpacketDetailId(StringUtils.getUUID());
		List<ActRedpacketDetail> redpacketDetails = Lists.newArrayList(redpacketDetail);
		redpacketDetailMapper.inserts(redpacketDetails);
		redpacketSendRecordService.updateSendRedpackets(redpacketDetails);
		actRedpacketMapper.updateSendRedpackets(Sets.newHashSet(redpacket.getRedpacketId()));
	}*/
	
	/**
	 * 获取开关
	 * @param switchKey
	 * @return
	 */
	private boolean getSwitching(String switchKey){
		String switchStr = sysConfigService.getValuesByKey(switchKey);
		return StringUtils.isBlank(switchStr) ? false : Boolean.valueOf(switchStr);
	}
	
	/**
	 * 设置红包与发放编号
	 * @param sendContext
	 */
	private void setRedpacektIdAndSendId(SendContext sendContext){
		//获取红包配置
		String  redpacektIdStr = sysConfigService.getValuesByKey(sendContext.getRedpacektIdKey()); 
		String  redpacektSendIdStr = sysConfigService.getValuesByKey(sendContext.getRedpacektSendIdKey());
		if(StringUtils.isBlank(redpacektIdStr)||StringUtils.isBlank(redpacektSendIdStr)){
			LOGGER.info("send redpacekt redpacektIdStr or redpacektSendIdStr is empty.  sendContext  ={}",sendContext);
			return;
		}
		if(sendContext.isMulti()){
			sendContext.setRedpacektIds( org.apache.commons.lang.StringUtils.split(redpacektIdStr, ","));
			sendContext.setRedpacektSendIds( org.apache.commons.lang.StringUtils.split(redpacektSendIdStr, ","));
		}else{
			sendContext.setRedpacektIds(new String[]{redpacektIdStr});
			sendContext.setRedpacektSendIds(new String[]{redpacektSendIdStr});
		}
	}
	
	/**
	 * 发送系统红包
	 * @param sendContext
	 * @param sendRedpacketCallback
	 * @param needMsg
	 * @param msg
	 * @throws Exception
	 */
	private void sendSystemRedpacekt(SendContext sendContext,SendRedpacketCallback sendRedpacketCallback,boolean needMsg,String msg) throws Exception{
		LOGGER.info("sendRedpacekt userInfo={}",sendContext.getUserInfo());
		//获取红包开关
		if(sendContext.isNeedSwitch()){
			sendContext.setSwitching(getSwitching(sendContext.getSwitchKey()));
		}
		//获取红包开关 关闭 不发放
		if(sendContext.isNeedSwitch() && !sendContext.isSwitching()){
			LOGGER.info("send redpacekt switch is close.  sendContext  ={}",sendContext);
			return;
		}
		//查询值
		if(!sendContext.isUseValue()){
			setRedpacektIdAndSendId(sendContext);
		}
		//查询红包
		List<ActRedpacket> redpackets = getRedpackets(sendContext.getRedpacektIds());
		if(CollectionUtils.isEmpty(redpackets) || redpackets.size() != sendContext.getRedpacektIds().length){
			LOGGER.info("query redpacekts  is empty or result size not equals send size.  result={},sendContext  ={}",redpackets,sendContext);
			return;
		}
		//查询红包发送记录
		List<ActRedpacketSendRecord> redpacketSends = redpacketSendRecordService.getRedpacketSendRecords(sendContext.getRedpacektSendIds());
		if(CollectionUtils.isEmpty(redpacketSends) || redpacketSends.size() != sendContext.getRedpacektSendIds().length){
			LOGGER.info("query redpacketSends  is empty or result size not equals send size.  ,result={},sendContext  ={}",redpackets,sendContext);
			return;
		}
		if(redpackets.size() != redpacketSends.size()){
			LOGGER.info("redpackets  not equals redpacketSends size.  redpackets={},redpacketSends={},sendContext={}",new Object[]{redpackets,redpacketSends,sendContext});
		}
		
		Map<String, ActRedpacketSendRecord> redpacketSendMaps = Maps.newHashMap();
		for (ActRedpacketSendRecord redpacketSendRecord : redpacketSends) {
			redpacketSendMaps.put(redpacketSendRecord.getRedpacketId(), redpacketSendRecord);
		}
		List<ActRedpacketDetail> redpacketDetails = Lists.newArrayListWithCapacity(redpackets.size());
		DateTime dateTime = DateTime.now();
		Date time = dateTime.toDate();
		Set<String> mobiles = Sets.newHashSet();
		
		getRedPackets(sendContext, sendRedpacketCallback, redpackets,redpacketSendMaps, redpacketDetails, dateTime, time, mobiles);
		//发送红包
		if(!redpacketDetails.isEmpty()){
			redpacketDetailMapper.inserts(redpacketDetails);
			redpacketSendRecordService.updateSendRedpackets(redpacketDetails);
			actRedpacketMapper.updateSendRedpackets(redpacketSendMaps.keySet());
			if(needMsg){
				try{
					sendRedpacketCallback.sendMsgs(mobiles,msg);
				}catch(Throwable e){
					LOGGER.warn("Send  Redpacket Msg Exception ", e);
				}
			}
		};
	}
	/**
	 * 获取红包明细信息
	 * @param sendContext
	 * @param sendRedpacketCallback
	 * @param redpackets
	 * @param redpacketSendMaps
	 * @param redpacketDetails
	 * @param dateTime
	 * @param time
	 * @param mobiles
	 * @throws Exception
	 */
	private void getRedPackets(SendContext sendContext,SendRedpacketCallback sendRedpacketCallback,List<ActRedpacket> redpackets,Map<String, ActRedpacketSendRecord> redpacketSendMaps,
			List<ActRedpacketDetail> redpacketDetails, DateTime dateTime,Date time, Set<String> mobiles) throws Exception {
		for (ActRedpacket redpacket : redpackets) {
			ActRedpacketSendRecord redpacketSendRecord = redpacketSendMaps.get(redpacket.getRedpacketId());
			if(redpacketSendRecord==null) continue;
			Date expiresTime = redpacketSendRecord.getExpiresTime();
			if( expiresTime == null ){
				expiresTime = dateTime.plusDays(redpacketSendRecord.getExpiresDay()).toDate();
			}
			ActRedpacketDetail redpacketDetail = createRedpacketDetail(sendContext.getUserInfo(), redpacket, redpacketSendRecord.getSendId(),expiresTime, time, sendRedpacketCallback, mobiles);
			redpacketDetail.setRedpacketDetailId(StringUtils.getUUID());
			redpacketDetails.add(redpacketDetail);
		}
	}
	
}
