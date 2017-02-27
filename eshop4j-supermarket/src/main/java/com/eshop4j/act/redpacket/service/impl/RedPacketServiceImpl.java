package com.eshop4j.act.redpacket.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.eshop4j.act.redpacket.model.ActRedpacket;
import com.eshop4j.act.redpacket.model.ActRedpacketDetail;
import com.eshop4j.act.redpacket.model.ActRedpacketRuleDetail;
import com.eshop4j.act.redpacket.model.SendContext;
import com.eshop4j.act.redpacket.service.ActRedpacketRuleService;
import com.eshop4j.act.redpacket.service.ActRedpacketSendRecordService;
import com.eshop4j.act.redpacket.service.ActRedpacketService;
import com.eshop4j.act.redpacket.service.RedPacketService;
import com.eshop4j.api.request.act.RedpacketRequest;
import com.eshop4j.api.request.act.SendRedPacketRequest;
import com.eshop4j.api.response.act.RedpacketResponse;
import com.eshop4j.api.response.cim.ProductDetailResponse;
import com.eshop4j.api.response.cim.ProductPageListResponse;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.dao.ActRedpacketDetailMapper;
import com.eshop4j.web.dao.CimProductInvestRecordMapper;
import com.eshop4j.web.enums.ActicityRedPacketEnum;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.web.enums.MsgModuleEnum;
import com.eshop4j.web.enums.PersonalMsgTypeEnum;
import com.eshop4j.web.enums.SmsTypeEnum;
import com.eshop4j.web.model.CimOrginfo;
import com.eshop4j.web.model.CrmInvestor;
import com.eshop4j.web.model.CrmUserInfo;
import com.eshop4j.web.model.vo.InvestRecordWrapper;
import com.eshop4j.web.service.CimProductService;
import com.eshop4j.web.service.CrmInvestorService;
import com.eshop4j.web.service.CrmUserInfoService;
import com.eshop4j.web.service.InvestRecordAware;
import com.eshop4j.web.service.SmMessageQueueService;
import com.eshop4j.web.service.SysConfigService;
import com.eshop4j.web.service.SysMsgService;
import com.eshop4j.xoss.helper.PushMessageHelper;
import com.eshop4j.xoss.helper.ThreadpoolService;
import com.eshop4j.xoss.util.AppResponseUtil;

@Service("redPacketService")
public class RedPacketServiceImpl implements RedPacketService,InvestRecordAware{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RedPacketServiceImpl.class);
	
	@Autowired
	private ActRedpacketDetailMapper  redpacketDetailMapper;
	
	
	@Autowired 
	private ActRedpacketService redpacketService;
	
	@Autowired
	private CrmInvestorService investorService;
	
	@Autowired
	private CrmUserInfoService userInfoService;
	
	@Autowired
	private CimProductService productService;
	
	@Autowired
	private ActRedpacketRuleService redpacketRuleService;
	
	@Autowired
	private ActRedpacketSendRecordService redpacketSendRecordService ;
	
	@Autowired
	private SmMessageQueueService messageQueueService;
	
	@Autowired
	private SysMsgService sysMsgService;
	
	@Autowired
	private CimProductInvestRecordMapper productInvestRecordMapper;

	@Autowired
	private PushMessageHelper pushMessageHelper;

	@Autowired
	private SysConfigService sysConfigService;

	
	@Override
	public Integer queryInvestorRedPacketCount(String userId) {
		return redpacketDetailMapper.queryInvestorRedPacketCount(userId);
	}

	@Override
	public Integer queryCfplannerRedPacketCount(String userId) {
		return redpacketDetailMapper.queryCfplannerRedPacketCount(userId);
	}
	
	
	@Override
	public PaginatorResponse<RedpacketResponse> queryInvestorRedPacket(String userId, RedpacketRequest req) {
		Page<RedpacketResponse> page  = new Page<RedpacketResponse>(req.getPageIndex(),req.getPageSize()>10?10:req.getPageSize()); //默认每页10条
		PaginatorResponse<RedpacketResponse> paginatorResponse = new PaginatorResponse<RedpacketResponse>();
		List<RedpacketResponse> redpackets = redpacketDetailMapper.queryInvestorRedPacket(userId, req.getType(), page);
		redpackets = redpacketUnionRule(redpackets,null);
		paginatorResponse.setDatas(redpackets);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}


	
	@Override
	public PaginatorResponse<RedpacketResponse> queryCfplannerRedPacket(String userId, RedpacketRequest req) {		
		Page<RedpacketResponse> page  = new Page<RedpacketResponse>(req.getPageIndex(),req.getPageSize()>10?10:req.getPageSize()); //默认每页10条
		PaginatorResponse<RedpacketResponse> paginatorResponse = new PaginatorResponse<RedpacketResponse>();
		List<RedpacketResponse> redpackets = redpacketDetailMapper.queryCfplannerRedPacket(userId, req.getType(), page);
		redpackets = redpacketUnionRule(redpackets,null);
		paginatorResponse.setDatas(redpackets);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}
	
	
	
	
	
	
	
	private List<RedpacketResponse> redpacketUnionRule(List<RedpacketResponse> redpackets,List<ActRedpacketRuleDetail> redpacketRules){
		
		if(redpackets == null || redpackets.isEmpty() ) return Collections.emptyList();
		
		Map<String, List<RedpacketResponse>> redpacketMap = Maps.newLinkedHashMap();
		
		for (RedpacketResponse redpacket : redpackets) {
			List<RedpacketResponse> values =redpacketMap.get(redpacket.getRid());
			if(values==null){
				values =  Lists.newArrayList(redpacket);
				redpacketMap.put(redpacket.getRid(), values);
			}else{
				values.add(redpacket);
			}
		}
		if(CollectionUtils.isEmpty(redpacketRules))redpacketRules = redpacketDetailMapper.getRedpacketRulesByRid(redpacketMap.keySet());
		
		Map<String, String[]> pnames = Maps.newLinkedHashMap();
		
		Set<String> pids = Sets.newLinkedHashSet();
		
		
		for (ActRedpacketRuleDetail redpacketRule : redpacketRules) {
			
			List<RedpacketResponse> values = redpacketMap.get(redpacketRule.getRedpacketId());
			if(CollectionUtils.isEmpty(values)) continue;
			for (RedpacketResponse redpacket : values) {
				redpacket.setInvestLimit(redpacketRule.getInvestLlimit());
				redpacket.setPlatformLimit(redpacketRule.isPlatformLimit());
				redpacket.setPlatform(redpacketRule.getPlatformName());
				redpacket.setProductLimit(redpacketRule.getProductLimit());
				redpacket.setDeadline(redpacketRule.getProductDeadline());
			}
			
			if(ObjectUtils.equals(redpacketRule.getProductLimit(),1001)){
				String[] pidStr = StringUtils.split(redpacketRule.getProductId(), ",");
				pnames.put(redpacketRule.getRedpacketId(),pidStr);
				pids.addAll(Arrays.asList(pidStr));
			}
		}
		
		if(pnames.isEmpty()) return result(redpacketMap);
		
		List<Map<String, String>> pmap = redpacketDetailMapper.getProductNames(pids);
		Map<String, String> p= Maps.newLinkedHashMap();
		
		for (Map<String, String> map : pmap) {
			p.put(map.get("pid"), map.get("pname"));
		}
		
		for (Entry<String,  String[]> entry : pnames.entrySet()) {
			
			List<String> names = Lists.newArrayList();
			for (String pid : entry.getValue()) {
				String value = p.get(pid);
				if(StringUtils.isBlank(value))continue;
				names.add(value);
			}
			if(names.isEmpty())continue;
			
			
			List<RedpacketResponse> values = redpacketMap.get(entry.getKey());
			
			for (RedpacketResponse redpacket : values) {
				redpacket.setProductName(StringUtils.join(names, ","));
			}
			
		}
		
		return result(redpacketMap);
	}
	
	private List<RedpacketResponse> result(Map<String, List<RedpacketResponse>> redpacketMap){
		List<RedpacketResponse> redpacketResponses = Lists.newArrayList();
		for (List<RedpacketResponse> values: redpacketMap.values()) {
			redpacketResponses.addAll(values);
		}
		return redpacketResponses;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public BaseResponse sendRedPacket(String userId,SendRedPacketRequest sendRedPacketRequest) throws Exception {
		final ActRedpacket redpacket =  redpacketService.getRedpacket(sendRedPacketRequest.getRid());
		if(redpacket==null){
			return AppResponseUtil.getErrorBusi(new BaseResponse("100002", "红包不存在"));
		}	
		
		String[] userMobiles =StringUtils.split(sendRedPacketRequest.getUserMobiles(), ",");
		if(userMobiles == null || userMobiles.length <=0)return AppResponseUtil.getErrorBusi(new BaseResponse("100002", "用户手机号不能为空")); 
		
		List<CrmInvestor> existUserMobile = Lists.newArrayList();
		List<String> unExistUserMobile = Lists.newArrayList();
		for (String userMobile : userMobiles) {
			CrmInvestor investor = investorService.queryInvestorByMobile(userMobile);
			if(investor!=null &&  ObjectUtils.equals(userId,investor.getCfplanner())){
				existUserMobile.add(investor);
			}else{
				unExistUserMobile.add(userMobile);
			}
		}
		if(!existUserMobile.isEmpty()){
			try{
				List<String> rids = redpacketDetailMapper.getSendRedpacketIds(userId, sendRedPacketRequest.getRid(), existUserMobile.size());
				if(rids==null || rids.isEmpty())return AppResponseUtil.getErrorBusi(new BaseResponse("100002", "红包数量为零"));  
				if(rids.size() < existUserMobile.size())return AppResponseUtil.getErrorBusi(new BaseResponse("100002", "红包数量小于发放人数")); 
				
				ActRedpacketDetail redpacketDetail = null;
				final List<String> mobiles =  Lists.newArrayListWithCapacity(existUserMobile.size());
				final List<String> userIds =  Lists.newArrayListWithCapacity(existUserMobile.size());
				for (int i = 0; i < existUserMobile.size(); i++) {
					CrmInvestor investor = existUserMobile.get(i);
					redpacketDetail = new ActRedpacketDetail();
					redpacketDetail.setRedpacketDetailId(rids.get(i));
					redpacketDetail.setUserId(investor.getUserId());
					redpacketDetail.setUserMobile(investor.getMobile());
					redpacketDetail.setUserName(investor.getUserName());
					redpacketDetailMapper.sendRedpacket(redpacketDetail);
					mobiles.add(investor.getMobile());
					userIds.add(investor.getUserId());
				}
				ThreadpoolService.execute(new Runnable() {
					@Override
					public void run() {
						messageQueueService.batchSendSmMessageAndPersonalMsg(mobiles, AppTypeEnum.INVESTOR, MsgModuleEnum.RECIVEREDPAPERBYLCS,true,userIds, PersonalMsgTypeEnum.REDPACKET_INV,redpacket.getMoney().setScale(2, BigDecimal.ROUND_DOWN));
						pushMessageHelper.BatchSinglePush(AppTypeEnum.INVESTOR, SmsTypeEnum.MYREDPACKET_INC, userIds, "理财师派发红包", messageQueueService.queryMessageTemplate(MsgModuleEnum.RECIVEREDPAPERBYLCS, AppTypeEnum.INVESTOR, redpacket.getMoney().setScale(2, BigDecimal.ROUND_DOWN)), null, false, null);
					}
				});
				return AppResponseUtil.getSuccessResponse();
			}catch(Exception e){
				LOGGER.error("sendRedPacket exception userId={},sendRedPacketRequest={},exception", new Object[]{userId,sendRedPacketRequest,e});
				throw e;
			}
		}
		if(!unExistUserMobile.isEmpty())return AppResponseUtil.getErrorBusi (new BaseResponse("100002", "用户不存在或用户不是理财师的客户 "+StringUtils.join(unExistUserMobile.toArray(new String[0]), ','))); 
		
		return AppResponseUtil.getErrorBusi(new BaseResponse("100002", "未知错误"));
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void investRecordProcess(InvestRecordWrapper investRecord)throws Exception {
		useRedPacket(investRecord);
	}

	
	private void useRedPacket(InvestRecordWrapper investRecord)throws Exception{
		try{
			LOGGER.info("start use redpacket investRecordId={}",investRecord.getInvestId());
			List<ActRedpacketDetail> redpackets =	redpacketDetailMapper.getUserUsableRedpackets(investRecord.getUserId());
			if(redpackets==null || redpackets.isEmpty())return;
			Map<String, ActRedpacketDetail> redpacketMpas = Maps.newHashMap();
			//去除相同红包,相同红包以快过期红包为最优
			for (ActRedpacketDetail redpacket : redpackets) {
				if(redpacketMpas.containsKey(redpacket.getRedpacketId())){
					ActRedpacketDetail	existRedpacket =   redpacketMpas.get(redpacket.getRedpacketId());
					if(existRedpacket.getExpiresDate().compareTo(redpacket.getExpiresDate())==-1){
						redpacket = existRedpacket;
					}
				}
				redpacketMpas.put(redpacket.getRedpacketId(), redpacket);
			}
			ActRedpacketDetail optimalRedpacket = redpacketRuleService.matcheRedPacket(investRecord, redpacketMpas);
			if(optimalRedpacket == null) return;
			LOGGER.debug("matcheRedPacket optimalRedpacket = {}",optimalRedpacket);
			//红包使用成功 发送消息
			if(redpacketService.useRedpacket(investRecord,optimalRedpacket)){
				try{
					LOGGER.debug("matcheRedPacket optimalRedpacket use suceess investRecord={} optimalRedpacket= {}",optimalRedpacket);
					messageQueueService.sendSingleMessage(optimalRedpacket.getUserMobile(), AppTypeEnum.INVESTOR, MsgModuleEnum.INVESTUSEREDPAPER, investRecord.getProductName());
					List<String> userIds = Lists.newArrayList();
					userIds.add(optimalRedpacket.getUserId());	
					//typeValue
					Map<String,Object> urlparam = Maps.newHashMap();
					urlparam.put("typeValue", 1);
					pushMessageHelper.BatchSinglePush(AppTypeEnum.INVESTOR, SmsTypeEnum.REWARDBALANCE_INC, userIds, "投资使用红包", messageQueueService.queryMessageTemplate(MsgModuleEnum.INVESTUSEREDPAPER, AppTypeEnum.INVESTOR, investRecord.getProductName()), urlparam, true, PersonalMsgTypeEnum.REDPACKET_INV);
				}catch(Exception e){
					LOGGER.warn("sendSingleMessage exception userMobile={},money={},exception={}",new Object[]{optimalRedpacket.getUserMobile(),optimalRedpacket.getMoney(),e.getMessage()} );
				}
				return;
			}
			useRedPacket(investRecord);
		}catch(Exception e){
			LOGGER.error("useRedPacket exception InvestRecordWrapper={}", investRecord,e);
			throw e;
		}
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void customerRegisterRedPacekt(CrmUserInfo userInfo)throws Exception {
		redpacketService.customerRegisterRedPacekt(userInfo);
	}


	
	
	

	@Override
	public void productRedPacketTag(List<ProductPageListResponse> products,String userId) {
		
		if(CollectionUtils.isEmpty(products) || StringUtils.isEmpty(userId)) return;
		
		CrmInvestor investor = investorService.queryInvestorByUserId(userId);
		if(investor==null)return;
		List<ActRedpacketRuleDetail> ruleDetails = redpacketDetailMapper.getRedpacketRulesByUid(userId);
		if(CollectionUtils.isEmpty(ruleDetails)) return;
		boolean isInvestLlimit,isPatformLimit,isProductLimit;
		
		isInvestLlimit=isPatformLimit=isProductLimit=false;
		
		Set<String> orgNumber =	Sets.newLinkedHashSet();
		for (ProductPageListResponse product  : products) {
			orgNumber.add(product.getOrgNumber());
		}
		List<String> lists = productInvestRecordMapper.queryPlatfromInvestCount(userId, orgNumber);
		Set<String> platfroms = CollectionUtils.isEmpty(ruleDetails) ? Sets.<String>newHashSet():Sets.<String>newHashSet(lists);
	
		
		for (ProductPageListResponse product : products) {
			for (ActRedpacketRuleDetail ruleDetail : ruleDetails) {
				
				if(ruleDetail.isPlatformLimit()) isPatformLimit = ObjectUtils.equals(ruleDetail.getPlatformId(), product.getOrgNumber());
				else isPatformLimit=true;
				
				if(!isPatformLimit)continue;
				
				int investLlimit = ruleDetail.getInvestLlimit();
				switch (investLlimit) {
				case 0:
					isInvestLlimit = true;
					break;
				case 1:
					isInvestLlimit = (null == investor.getFirstInvestTime());
					break;
				case 2:
					isInvestLlimit = !platfroms.contains(product.getOrgNumber());
					break;
				}
				if(!isInvestLlimit)continue;
				
				int productLimit = ruleDetail.getProductLimit();
				if(ObjectUtils.equals(productLimit, 1000))isProductLimit = true;
			    //产品匹配
			    else if(ObjectUtils.equals(productLimit, 1001))isProductLimit = Sets.newHashSet(org.apache.commons.lang.StringUtils.split(ruleDetail.getProductId(), ",")).contains(product.getProductId());
			    else if(ObjectUtils.equals(productLimit, 1002))isProductLimit = ObjectUtils.equals(ruleDetail.getProductDeadline(), product.getDeadLineMinValue());
			    else if(ObjectUtils.equals(productLimit, 1003))isProductLimit = product.getDeadLineMinValue().compareTo(ruleDetail.getProductDeadline())>=0;
				
			    if(!isProductLimit)continue;
			    
			    product.setHasRedPacket(true);
		    	break;
			}
		}
	}

	@Override
	public void patformRedPacketTag(List<CimOrginfo> orgInfos, String userId) {
		if(CollectionUtils.isEmpty(orgInfos) || StringUtils.isEmpty(userId)) return;
		CrmInvestor investor = investorService.queryInvestorByUserId(userId);
		if(investor==null)return;
		List<ActRedpacketRuleDetail> ruleDetails = redpacketDetailMapper.getRedpacketRulesByUid(userId);
		if(CollectionUtils.isEmpty(ruleDetails)) return;
		boolean isInvestLlimit,isPatformLimit;
		
		isInvestLlimit=isPatformLimit=false;
		
		Set<String> orgNumber =	Sets.newLinkedHashSet();
		for (CimOrginfo orgInfo  : orgInfos) {
			orgNumber.add(orgInfo.getOrgNumber());
		}
		List<String> lists = productInvestRecordMapper.queryPlatfromInvestCount(userId, orgNumber);
		Set<String> platfroms = CollectionUtils.isEmpty(ruleDetails) ? Sets.<String>newHashSet():Sets.<String>newHashSet(lists);
	
		
		for (CimOrginfo orgInfo  : orgInfos) {
			for (ActRedpacketRuleDetail ruleDetail : ruleDetails) {
				
				if(ruleDetail.isPlatformLimit()) isPatformLimit = ObjectUtils.equals(ruleDetail.getPlatformId(), orgInfo.getOrgNumber());
				else isPatformLimit=true;
				
				if(!isPatformLimit)continue;
				
				int investLlimit = ruleDetail.getInvestLlimit();
				switch (investLlimit) {
				case 0:
					isInvestLlimit = true;
					break;
				case 1:
					isInvestLlimit = (null == investor.getFirstInvestTime());
					break;
				case 2:
					isInvestLlimit = !platfroms.contains(orgInfo.getOrgNumber());
					break;
				}
				if(!isInvestLlimit)continue;
				
				orgInfo.setHashRedpacket(true);
		    	break;
			}
		}
	}

	@Override
	public int productRedPacketCount(ProductDetailResponse productDetail ,String userId) {
		
		int count = 0;
		
		if(null == productDetail || StringUtils.isEmpty(userId)) return count;
		
		CrmInvestor investor = investorService.queryInvestorByUserId(userId);
		
		if(investor==null)return count;
		
		List<ActRedpacketRuleDetail> ruleDetails = redpacketDetailMapper.getRedpacketRulesByUid(userId);
		
		if(CollectionUtils.isEmpty(ruleDetails)) return count;
		
		boolean isInvestLlimit,isPatformLimit,isProductLimit;
		
		isInvestLlimit=isPatformLimit=isProductLimit=false;

		int investCount = productInvestRecordMapper.queryUserPlatfromInvestCount(userId,productDetail.getOrgNumber());
		Set<String> rids = Sets.newHashSetWithExpectedSize(ruleDetails.size());
		for (ActRedpacketRuleDetail ruleDetail : ruleDetails) {
			if(ruleDetail.isPlatformLimit()) isPatformLimit = ObjectUtils.equals(ruleDetail.getPlatformId(), productDetail.getOrgNumber());
			else isPatformLimit=true;
			if(!isPatformLimit)continue;
			int investLlimit = ruleDetail.getInvestLlimit();
			switch (investLlimit) {
			case 0:
				isInvestLlimit = true;
				break;
			case 1:
				isInvestLlimit = (null == investor.getFirstInvestTime());
				break;
			case 2:
				isInvestLlimit = investCount==0;
				break;
			}
			if(!isInvestLlimit)continue;
			
			int productLimit = ruleDetail.getProductLimit();
			if(ObjectUtils.equals(productLimit, 1000))isProductLimit = true;
		    //产品匹配
		    else if(ObjectUtils.equals(productLimit, 1001))isProductLimit = Sets.newHashSet(org.apache.commons.lang.StringUtils.split(ruleDetail.getProductId(), ",")).contains(productDetail.getProductId());
		    else if(ObjectUtils.equals(productLimit, 1002))isProductLimit = ObjectUtils.equals(ruleDetail.getProductDeadline(), productDetail.getDeadLineMinValue());
		    else if(ObjectUtils.equals(productLimit, 1003))isProductLimit = productDetail.getDeadLineMinValue().compareTo(ruleDetail.getProductDeadline())>=0;
			
		    if(!isProductLimit)continue;
		    rids.add(ruleDetail.getRedpacketId());
		}
		return !rids.isEmpty()? redpacketDetailMapper.getUserRedpacketCountByRid(userId, rids) : count;
		
	}
	
	public List<RedpacketResponse> productRedPacket(String userId,RedpacketRequest req,Page<RedpacketResponse> page){
		if(StringUtils.isEmpty(req.getPatform()) || StringUtils.isEmpty(req.getProductId()) || req.getDeadline()==null || req.getDeadline() <= 0 || StringUtils.isEmpty(userId)) return Collections.emptyList();
		
		CrmInvestor investor = investorService.queryInvestorByUserId(userId); 
		
		if(investor==null)return Collections.emptyList();
		
		List<ActRedpacketRuleDetail> ruleDetails = redpacketDetailMapper.getRedpacketRulesByUid(userId);
		if(CollectionUtils.isEmpty(ruleDetails)) return Collections.emptyList();
		
		boolean isInvestLlimit,isPatformLimit,isProductLimit;
		
		isInvestLlimit=isPatformLimit=isProductLimit=false;
		
		int investCount = productInvestRecordMapper.queryUserPlatfromInvestCount(userId,req.getPatform());
		Set<String> rids = Sets.newHashSetWithExpectedSize(ruleDetails.size());
		for (ActRedpacketRuleDetail ruleDetail : ruleDetails) {
			if(ruleDetail.isPlatformLimit()) isPatformLimit = ObjectUtils.equals(ruleDetail.getPlatformId(), req.getPatform());
			else isPatformLimit=true;
			if(!isPatformLimit)continue;
			int investLlimit = ruleDetail.getInvestLlimit();
			switch (investLlimit) {
			case 0:
				isInvestLlimit = true;
				break;
			case 1:
				isInvestLlimit = (null == investor.getFirstInvestTime());
				break;
			case 2:
				isInvestLlimit = investCount==0;
				break;
			}
			if(!isInvestLlimit)continue;
			
			int productLimit = ruleDetail.getProductLimit();
			if(ObjectUtils.equals(productLimit, 1000))isProductLimit = true;
		    //产品匹配
		    else if(ObjectUtils.equals(productLimit, 1001))isProductLimit = Sets.newHashSet(org.apache.commons.lang.StringUtils.split(ruleDetail.getProductId(), ",")).contains(req.getProductId());
		    else if(ObjectUtils.equals(productLimit, 1002))isProductLimit = ObjectUtils.equals(ruleDetail.getProductDeadline(),req.getDeadline());
		    else if(ObjectUtils.equals(productLimit, 1003))isProductLimit = req.getDeadline().compareTo(ruleDetail.getProductDeadline())>=0;
			
		    if(!isProductLimit)continue;
		    rids.add(ruleDetail.getRedpacketId());
		}
		if(rids.isEmpty()) return Collections.emptyList();
		List<RedpacketResponse> redpackets = redpacketDetailMapper.getUserRedpacketByRid(userId, rids, page);
		redpackets = redpacketUnionRule(redpackets,ruleDetails);
		return redpackets;
	}
	
	

	@Override
	public int patformRedPacketCount(String patform,int model,String userId) {
		
		int count = 0;
		if(StringUtils.isEmpty(patform) || StringUtils.isEmpty(userId)) return count;
		
		CrmInvestor investor = investorService.queryInvestorByUserId(userId); 
		
		if(investor==null)return count;
		
		List<ActRedpacketRuleDetail> ruleDetails = redpacketDetailMapper.getRedpacketRulesByUid(userId);
		
		if(CollectionUtils.isEmpty(ruleDetails)) return count;
		
		boolean isInvestLlimit,isPatformLimit;
		
		isInvestLlimit=isPatformLimit=false;
		
		int investCount = productInvestRecordMapper.queryUserPlatfromInvestCount(userId,patform);
		Set<String> rids = Sets.newHashSetWithExpectedSize(ruleDetails.size());
		for (ActRedpacketRuleDetail ruleDetail : ruleDetails) {
			
			if(ruleDetail.isPlatformLimit()) isPatformLimit = ObjectUtils.equals(ruleDetail.getPlatformId(), patform);
			else isPatformLimit=true;
			
			if(!isPatformLimit)continue;
			
			int investLlimit = ruleDetail.getInvestLlimit();
			switch (investLlimit) {
			case 0:
				isInvestLlimit = true;
				break;
			case 1:
				isInvestLlimit = (null == investor.getFirstInvestTime());
				break;
			case 2:
				isInvestLlimit = investCount==0;
				break;
			}
			if(!isInvestLlimit)continue;
			rids.add(ruleDetail.getRedpacketId());
			
		}
		return !rids.isEmpty()? redpacketDetailMapper.getUserRedpacketCountByRid(userId, rids) : count;
	}
	
	public List<RedpacketResponse> patformRedPacket(String userId,RedpacketRequest req,Page<RedpacketResponse> page){
		if(StringUtils.isEmpty(req.getPatform()) || StringUtils.isEmpty(userId)) return Collections.emptyList();
		
		CrmInvestor investor = investorService.queryInvestorByUserId(userId); 
		
		if(investor==null)return Collections.emptyList();
		
		List<ActRedpacketRuleDetail> ruleDetails = redpacketDetailMapper.getRedpacketRulesByUid(userId);
		if(CollectionUtils.isEmpty(ruleDetails)) return Collections.emptyList();
		
		boolean isInvestLlimit,isPatformLimit;
		
		isInvestLlimit=isPatformLimit=false;
		
		int investCount = productInvestRecordMapper.queryUserPlatfromInvestCount(userId,req.getPatform());
		Set<String> rids = Sets.newHashSetWithExpectedSize(ruleDetails.size());
		for (ActRedpacketRuleDetail ruleDetail : ruleDetails) {
			if(ruleDetail.isPlatformLimit())isPatformLimit = ObjectUtils.equals(ruleDetail.getPlatformId(), req.getPatform());
			else isPatformLimit=true;
			
			if(!isPatformLimit)continue;
			
			int investLlimit = ruleDetail.getInvestLlimit();
			switch (investLlimit) {
			case 0:
				isInvestLlimit = true;
				break;
			case 1:
				isInvestLlimit = (null == investor.getFirstInvestTime());
				break;
			case 2:
				isInvestLlimit = investCount==0;
				break;
			}
			if(!isInvestLlimit)continue;
			rids.add(ruleDetail.getRedpacketId());
		}
		if(rids.isEmpty()) return Collections.emptyList();
		List<RedpacketResponse> redpackets = redpacketDetailMapper.getUserRedpacketByRid(userId, rids, page);
		redpackets = redpacketUnionRule(redpackets,ruleDetails);
		return redpackets;
	}
	
	
	@Override
	public void lcsActicityRedPacket(String userId,ActicityRedPacketEnum acticityRedPacketEnum) throws Exception {
		CrmUserInfo userInfo = userInfoService.queryUserInfoByUserId(userId);
		if(userInfo == null){
			LOGGER.info("send lcsActicityRedPacket The user does not exist.  userId={},acticity code={}",userId,acticityRedPacketEnum.getValue());
			return;
		}
		String vlueStr = sysConfigService.getValuesByKey(acticityRedPacketEnum.getValue());
		if(StringUtils.isBlank(vlueStr)){
			LOGGER.info("send lcsActicityRedPacket The acticity code value does not exist.  userId={},acticity code={}",userId,acticityRedPacketEnum.getValue());
			return;
		}
		String[] values= StringUtils.split(vlueStr, ",");
		redpacketService.lcsSystemRedpacekt(new SendContext(userInfo,new String[]{values[0]},new String[]{values[1]}));
	}
	
}
