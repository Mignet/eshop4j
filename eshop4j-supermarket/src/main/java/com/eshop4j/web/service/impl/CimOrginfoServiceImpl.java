package com.eshop4j.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.eshop4j.act.redpacket.service.RedPacketService;
import com.eshop4j.core.base.PaginatorSevResp;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.constant.SysConfigConstant;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.ApplicationUtils;
import com.eshop4j.web.dao.CimOrginfoMapper;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.web.enums.PersonalMsgTypeEnum;
import com.eshop4j.web.enums.SmsTypeEnum;
import com.eshop4j.web.model.ActivityList;
import com.eshop4j.web.model.CimOrgMemberInfo;
import com.eshop4j.web.model.CimOrgRef;
import com.eshop4j.web.model.CimOrgUrl;
import com.eshop4j.web.model.CimOrginfo;
import com.eshop4j.web.model.CrmCfplanner;
import com.eshop4j.web.model.CrmInvestor;
import com.eshop4j.web.model.CrmInvestorRecommend;
import com.eshop4j.web.model.CrmOrgAcctRel;
import com.eshop4j.web.model.SysThirdkeyConfig;
import com.eshop4j.web.model.cim.CimOrginfoBindSelect;
import com.eshop4j.web.model.cim.CimOrginfoWeb;
import com.eshop4j.web.model.cim.CimProductInvestRecord;
import com.eshop4j.web.model.cim.OrgInfo;
import com.eshop4j.web.model.cim.PcOrgInfo;
import com.eshop4j.web.model.crm.PlatformAcctManagerListResp;
import com.eshop4j.web.model.mc.SysMsg;
import com.eshop4j.web.request.orgInfo.OrgRecommendByChooseRequest;
import com.eshop4j.web.request.orgInfo.OrgRecommendChooseRequest;
import com.eshop4j.web.response.orgInfo.InvestmentStrategyResponse;
import com.eshop4j.web.response.orgInfo.OrgRecommendChooseResponse;
import com.eshop4j.web.service.ActivityListService;
import com.eshop4j.web.service.CimOrgMemberinfoService;
import com.eshop4j.web.service.CimOrgRefService;
import com.eshop4j.web.service.CimOrginfoService;
import com.eshop4j.web.service.CimProductInvestRecordService;
import com.eshop4j.web.service.CrmCfplannerService;
import com.eshop4j.web.service.CrmInvestorService;
import com.eshop4j.web.service.CrmOrgAcctRelService;
import com.eshop4j.web.service.SysConfigService;
import com.eshop4j.web.service.SysMsgService;
import com.eshop4j.web.service.SysThirdkeyConfigService;
import com.eshop4j.xoss.api.AppRequestHead;
import com.eshop4j.xoss.helper.ConfigHelper;
import com.eshop4j.xoss.helper.JsonWebTokenHepler;
import com.eshop4j.xoss.helper.PushMessageHelper;
import com.eshop4j.xoss.helper.ThreadpoolService;
import com.eshop4j.xoss.util.AppUtils;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月11日 16:27:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrginfoService")
public class CimOrginfoServiceImpl extends GenericServiceImpl<CimOrginfo, Integer> implements CimOrginfoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrginfoServiceImpl.class);
	
	@Resource
	private CimOrginfoMapper cimOrginfoMapper;
	@Resource
	private CimOrgMemberinfoService cimOrgMemberinfoService; //机构团队详情
	@Resource
	private SysThirdkeyConfigService SysThirdkeyConfigService; //第三方api接口配置
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private CrmInvestorService crmInvestorService;
	@Resource
	private CrmOrgAcctRelService crmOrgAcctRelService;
	@Resource
	private CimProductInvestRecordService cimProductInvestRecordService;
	@Resource
	private CimOrgRefService cimOrgRefService;
	@Resource
	private ActivityListService activityListService;
	@Resource
	private SysMsgService sysMsgService;
    @Resource
    private CrmCfplannerService crmCfplannerService;
    @Resource
	private RedPacketService redPacketService; //红包服务
    @Resource
    private PushMessageHelper pushMessageHelper;
    
    @Resource
	private ConfigHelper configHelper;
    
	@Override
    public GenericDao<CimOrginfo, Integer> getDao() {
        return cimOrginfoMapper;
    }
	
	@Override
    public int insert(CimOrginfo model) {
        return cimOrginfoMapper.insertSelective(model);
    }

    @Override
    public int update(CimOrginfo model) {
        return cimOrginfoMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public int delete(Integer id) {
    	//roleMapper.deleteUserRolesByUserid(id);
        return cimOrginfoMapper.deleteByPrimaryKey(id);
    }

 

    @Override
    public CimOrginfo selectById(Integer id) {
        return cimOrginfoMapper.selectByPrimaryKey(id);
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrginfo -- 排序和模糊查询 ");
		Page<CimOrginfoWeb> page = new Page<CimOrginfoWeb>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrginfoWeb> list = this.cimOrginfoMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CimOrginfo> findRecommendOrg(Boolean isGrayUser) {
		CimOrginfo orginfo = new CimOrginfo();
		//orginfo.setRecommend(1); //仅查询优质平台
		if(isGrayUser){
			orginfo.setOrgGrayStatus(1); //灰度用户
		}
		return cimOrginfoMapper.findRecommendOrg(orginfo);
	}
	
	
	@Override
	public PaginatorResponse<CimOrginfo> queryOrgList(Page<CimOrginfo> page,Map<String,Object> conditions) {
		PaginatorResponse<CimOrginfo> paginatorResponse = new PaginatorResponse<CimOrginfo>();
		List<CimOrginfo> queryCimOrginfoList = cimOrginfoMapper.queryCimOrginfoList(page,conditions);
		String appType = (String) conditions.get("appType");
		redPacketService.patformRedPacketTag(queryCimOrginfoList, conditions.get("userId").toString()); //查询机构是否有红包
		conditions.clear();
		if(queryCimOrginfoList != null){
			for(CimOrginfo org : queryCimOrginfoList){
				org.setUsableProductNums(cimOrginfoMapper.queryOrgUseProductNums(org.getOrgNumber()) == null ? 0 : cimOrginfoMapper.queryOrgUseProductNums(org.getOrgNumber()));
				if(AppUtils.isChannelApp(appType)){
					conditions.put("appType", 1); //猎财
				}else{
					conditions.put("appType", 2); //T呗
				}
	    		conditions.put("activityPlatform", org.getName()); //平台名称
	    		List<ActivityList> orgActivityList = activityListService.queryPlatformActivities(conditions); //[] 机构活动宣传图
	    		if(orgActivityList != null){
	    			org.setOrgActivitys(orgActivityList);
	    		}
			}
		}
		
		paginatorResponse.setDatas(queryCimOrginfoList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}
	
	
	@Override
	public OrgInfo findOrgInfo(String orgNo) {
		return cimOrginfoMapper.findOrgInfo(orgNo);
	}
	
	@Override
	public PcOrgInfo findPcOrgInfo(String orgNumber) {
		return cimOrginfoMapper.findPcOrgInfo(orgNumber);
	}

	@Override
	public PaginatorSevResp<PlatformAcctManagerListResp> queryPlatformAcctManagerPageList(Map<String, Object> query,
			Page<PlatformAcctManagerListResp> page) {
		PaginatorSevResp<PlatformAcctManagerListResp> paginatorResponse = new PaginatorSevResp<PlatformAcctManagerListResp>();
		List<PlatformAcctManagerListResp> list = cimOrginfoMapper.queryPlatformAcctManagerPageList(query, page);
		
		List<PlatformAcctManagerListResp> removeList = new ArrayList<PlatformAcctManagerListResp>();
		
		if(list != null && list.size() > 0){
			for(PlatformAcctManagerListResp bo : list) {
				if(bo != null && bo.getPlatformListIco() != null && !"".equals(bo.getPlatformListIco())){
					bo.setPlatformListIco(sysConfigService.getImageUrl(bo.getPlatformListIco()));
					bo.setOrgAdvertisement(sysConfigService.getImageUrl(bo.getOrgAdvertisement()));
				}
				//查看合作结束的平台并且用户在平台的投资记录数大于0的平台
				if((bo.getStatus() == null || bo.getStatus() == 0) && bo.getInvestCount() == 0){
					removeList.add(bo);
				}
			}
		}
		list.removeAll(removeList); //集中移除合作结束并且投资记录数为0的平台
		paginatorResponse.setDatas(list);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public int queryOrgAccountCount(String userId) {
		return cimOrginfoMapper.queryOrgAccountCount(userId);
	}

	@Override
	public void bindOrgAcct(CrmOrgAcctRel  bo) {
		cimOrginfoMapper.insertOrgAcctRel(bo);
	}

	@Override
	public CimOrgUrl selectOrgUrlByOrgNumber(String orgNumber) {
		return cimOrginfoMapper.selectOrgUrlByOrgNumber(orgNumber);
	}

	@Override
	public boolean isBindOrgAcct(String userId, String platFromNumber) {
		if(cimOrginfoMapper.queryIsBindOrgAcct(userId, platFromNumber) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public String queryThirdOrgAccountByUserId(String userId, String platFromNumber) {
		return cimOrginfoMapper.queryThirdOrgAccountByUserId(userId, platFromNumber);
	}

	@Override
	public int queryOrgCount() {
		return cimOrginfoMapper.queryOrgCount();
	}

	@Override
	public CimOrginfoWeb selectOrgInfoByOrgNumber(String orgNumber) {
		return cimOrginfoMapper.selectOrgInfoByOrgNumber(orgNumber);
	}

	@Override
	public void insertOrgFullInfo(CimOrginfoWeb cimOrginfo, String createUser) {
		LOGGER.debug("新增机构时 插入数据到机构表:cimOrginfo = {}", JSON.toJSONString(cimOrginfo));
		cimOrginfoMapper.insertOrginfo(cimOrginfo);
		LOGGER.debug("插入数据到机构表成功！ ");
		if(!cimOrginfo.getTeams().isEmpty() && cimOrginfo.getTeams().size() > 0){
			for(CimOrgMemberInfo team :cimOrginfo.getTeams()){
				team.setOrgId(cimOrginfo.getId()); //机构主键
			}
			LOGGER.debug("新增机构时 批量插入团队信息到团队信息表:teams = {}", JSON.toJSONString(cimOrginfo.getTeams()));
			cimOrgMemberinfoService.insertBatch(cimOrginfo.getTeams());
			LOGGER.debug("插入数据到团队信息表成功！ ");
		}
		
		SysThirdkeyConfig thirdkeyConfig = new SysThirdkeyConfig();
		//第三方api接口配置表生成公钥和私钥
		thirdkeyConfig.setOrgNumber(cimOrginfo.getOrgNumber()); //机构编码
		thirdkeyConfig.setOrgKey(ApplicationUtils.randomUUID(true, true)); //生成公钥
		thirdkeyConfig.setOrgSecret(ApplicationUtils.randomUUID(true, true)); //生成私钥
		thirdkeyConfig.setOrgStatus("y"); //y：开启，n：关闭
		thirdkeyConfig.setCreateTime(new Date());
		thirdkeyConfig.setArchive(0); //'逻辑删除：0:可用，1：删除'
		thirdkeyConfig.setCreateUser(createUser); //创建人
		LOGGER.debug("新增机构时 插入数据到第三方api接口配置表:thirdkeyConfig = {} ", JSON.toJSONString(thirdkeyConfig));
		SysThirdkeyConfigService.insert(thirdkeyConfig); //插入
		LOGGER.debug("插入数据到第三方api接口配置表成功！ ");
	}
	
	@Override
	public void updateOrgFullInfo(CimOrginfoWeb cimOrginfo) {
		LOGGER.debug("更新机构 更新数据到机构表:cimOrginfo = {}", JSON.toJSONString(cimOrginfo));
		cimOrginfoMapper.updateByOrgNumber(cimOrginfo);
		LOGGER.debug("更新机构表数据成功！ ");
		List<CimOrgMemberInfo> teams = cimOrginfo.getTeams(); //获取团队信息
		//判断团队id是否null
		List<CimOrgMemberInfo> newTeams = new ArrayList<CimOrgMemberInfo>(); //新增加的团队成员信息
		
		if(teams != null){
			for(CimOrgMemberInfo newTeam : teams){
				if(newTeam.getTid() == null){
					newTeams.add(newTeam); //保存id为null的成员信息 执行批量新增
				}
			}
			
			if(newTeams.size() > 0){
				teams.removeAll(newTeams); //从集合中删除id为null的团队成员
			}
			
			/**
			 * 执行批量更新操作
			 */
			if(!teams.isEmpty() && teams.size() > 0){
				LOGGER.debug("更新机构 批量更新团队信息到团队信息表:teams = {}", JSON.toJSONString(teams));
				cimOrgMemberinfoService.updateBatchTeam(teams);
				LOGGER.debug("更新机构 批量更新数据到团队信息表成功！ ");
			}
			
		}
		
		/**
		 * 执行批量插入操作
		 */
		if(!newTeams.isEmpty() && newTeams.size() > 0){
			for(CimOrgMemberInfo team : newTeams){
				team.setOrgId(cimOrginfo.getId()); //设置机构主键到团队信息表
			}
			LOGGER.debug("更新机构 批量新增团队信息到团队信息表:newTeams = {}", JSON.toJSONString(newTeams));
			cimOrgMemberinfoService.insertBatch(newTeams);
			LOGGER.debug("更新机构 批量新增数据到团队信息表成功！ ");
		}
	}

	@Override
	public String queryInvestorLoginId(String userId) {
		return cimOrginfoMapper.queryInvestorLoginId(userId);
	}

	@Override
	public String queryUserIdByThirdOrgAccount(String thirdOrgAccount,String platFromNumber) {
		return cimOrginfoMapper.queryUserIdByThirdOrgAccount(thirdOrgAccount, platFromNumber);
	}

	@Override
	public int updateByOrgNumber(CimOrginfoWeb o) {
		return cimOrginfoMapper.updateByOrgNumber(o);
	}

	@Override
	public int insertOrginfo(CimOrginfoWeb o) {
		return cimOrginfoMapper.insertOrginfo(o);
	}

	@Override
	public List<CimOrginfoBindSelect> queryOrgByStatus(int status) {
		return cimOrginfoMapper.queryOrgByStatus(status);
	}

	@Override
	public void updateOrgAcctRelInvested(String userId, String productOrgId) {
		cimOrginfoMapper.updateOrgAcctRelInvested(userId,  productOrgId);
		
	}

	@Override
	public CimOrginfoWeb findWebOrgInfo(String orgNumber) {
		return cimOrginfoMapper.findWebOrgInfo(orgNumber);
	}

	@Override
	public boolean isOrgNewUser(String userId, String orgNumber) {
		return cimOrginfoMapper.isOrgNewUser(userId, orgNumber);
	}

	@Override
	public PaginatorResponse<CimOrginfo> findPcRecommendOrg(Page<CimOrginfo> page,Boolean isGrayUser) {
		PaginatorResponse<CimOrginfo> paginatorResponse = new PaginatorResponse<CimOrginfo>();
		List<CimOrginfo> queryCimOrginfoList = cimOrginfoMapper.findPcRecommendOrg(page,isGrayUser);
		paginatorResponse.setDatas(queryCimOrginfoList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public List<CimOrginfo> queryLatestOrg(Boolean isGrayUser) {
		return cimOrginfoMapper.queryLatestOrg(isGrayUser);
	}

	@Override
	public CimOrginfoWeb queryOrgFeeInfo(String orgNumber) {
		return cimOrginfoMapper.queryOrgFeeInfo(orgNumber);
	}

	@Override
	public CimOrginfo queryCimOrginfoByProductid(String productId) {
		return cimOrginfoMapper.queryCimOrginfoByProductid(productId);
	}
	
	@Override
	public OrgRecommendChooseResponse recommendChooseList(AppRequestHead head,OrgRecommendChooseRequest orgRecommendChooseRequest) {
		
		OrgRecommendChooseResponse orgRecommendChooseResponse = new OrgRecommendChooseResponse();
		List<CrmInvestorRecommend> allFeeList = new ArrayList<CrmInvestorRecommend>();
		List<CrmInvestorRecommend> haveFeeList = new ArrayList<CrmInvestorRecommend>();
		List<CrmInvestorRecommend> notHaveFeeList = new ArrayList<CrmInvestorRecommend>();
		
		/**
		 * 获取理财师id
		 */
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		orgRecommendChooseRequest.setUserId(userId);
		/**
		 * 根据客户条件筛选理财师所有客户 含是否推荐该机构信息
		 */
		allFeeList = crmInvestorService.selectCrmInvestorRecommendOrg(orgRecommendChooseRequest);
		/**
		 * 查询机构详情 
		 */
		OrgInfo orgInfoResponse = findOrgInfo(orgRecommendChooseRequest.getOrgCode());
		orgRecommendChooseResponse.setOrgIsstaticproduct(orgInfoResponse.getOrgIsstaticproduct());//是否对接机构
		/**
		 * 推荐
		 * 1：未进行对接    直接列出所有用户
		 * 2：已技术对接
		 * 2.1 有佣金的用户   2.11 CPS  我们带过去的新用户            2.12  CPA 我们带过去的新用户未投资
		 * 2.2 无佣金的用户   2.21 CPS  不是我们带过去的用户       2.22  CPA 不是我们带过去用户 或者是我们带过去的用户（已投资）
		 */
			
		if(orgInfoResponse.getOrgIsstaticproduct() == 0){//已技术对接
			
			/** 收费类型   1:cpa-按投资人数量进行收费	2:cps-按投资金额进行收费 */
			Integer orgFeeType = orgInfoResponse.getOrgFeeType();
			
			/**
			 * 遍历所有客户进行是否拥有佣金分类
			 */
			for(CrmInvestorRecommend crmInvestorRecommend:allFeeList){
				CrmOrgAcctRel crmOrgAcctRel =  new CrmOrgAcctRel();
				crmOrgAcctRel.setOrgNumber(orgRecommendChooseRequest.getOrgCode());
				crmOrgAcctRel.setUserId(crmInvestorRecommend.getUserId());
				crmOrgAcctRel = crmOrgAcctRelService.selectOne(crmOrgAcctRel);
				if(crmOrgAcctRel == null){//未绑定平台用户
					haveFeeList.add(crmInvestorRecommend);
				} else if(crmOrgAcctRel.getIsNewUser() == 1){//平台新用户
					if(orgFeeType == 1){//CPA
						/**
						 * 判断该用户是否投资过该机构,若投资过,则没有佣金，否则有佣金
						 */
						CimProductInvestRecord cimProductInvestRecord = new CimProductInvestRecord();
						cimProductInvestRecord.setUserId(crmInvestorRecommend.getUserId());
						cimProductInvestRecord.setPlatfrom(orgRecommendChooseRequest.getOrgCode());
						List<CimProductInvestRecord>  cimProductInvestRecordList= cimProductInvestRecordService.selectListByCondition(cimProductInvestRecord);
						if(cimProductInvestRecordList != null && cimProductInvestRecordList.size() > 0){
							notHaveFeeList.add(crmInvestorRecommend);
						} else {
							haveFeeList.add(crmInvestorRecommend);
						}
					} else if(orgFeeType == 2){//CPS
						haveFeeList.add(crmInvestorRecommend);
					}
				} else {//平台老用户
					notHaveFeeList.add(crmInvestorRecommend);
				}
			}
		}
		orgRecommendChooseResponse.setAllFeeList(allFeeList);
		orgRecommendChooseResponse.setHaveFeeList(haveFeeList);
		orgRecommendChooseResponse.setNotHaveFeeList(notHaveFeeList);
		
		return orgRecommendChooseResponse;
	}
	@Override
	public PaginatorResponse<CimOrginfo> queryPlannerRecommendPlatfrom(Page<CimOrginfo> page, String investUserId, String saleUserId) {
		PaginatorResponse<CimOrginfo> paginatorResponse = new PaginatorResponse<CimOrginfo>();
		List<CimOrginfo> queryCimOrginfoList = cimOrginfoMapper.queryPlannerRecommendPlatfrom(page,investUserId,saleUserId);
		redPacketService.patformRedPacketTag(queryCimOrginfoList,investUserId); //查询机构是否有红包
		paginatorResponse.setDatas(queryCimOrginfoList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public void recommendByChoose(AppRequestHead head,OrgRecommendByChooseRequest orgRecommendByChooseRequest) {
		/**
		 * 获取理财师id
		 */
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		final String orgCode = orgRecommendByChooseRequest.getOrgCode();
		String userIdString = orgRecommendByChooseRequest.getUserIdString();
		
		//批量删除原有推荐信息  
		CimOrgRef cimOrgRefNew = new CimOrgRef();
		cimOrgRefNew.setOrgNumber(orgRecommendByChooseRequest.getOrgCode());
		cimOrgRefNew.setSaleUserId(userId);
		cimOrgRefService.deleteByCondition(cimOrgRefNew);
		
		
		//推送个人消息列表
		final  List<SysMsg> msgList = Lists.newArrayList();
		final  List<String> userIds = Lists.newArrayList();
		//理财师信息
		CrmCfplanner crmCfplanner =  crmCfplannerService.queryCfplannerByInvestor(userId);
		OrgInfo orgInfo = findOrgInfo(orgRecommendByChooseRequest.getOrgCode());

		String contentTemp = configHelper.getValue(SysConfigConstant.PUSHMESSAGE_RECOMEND_PLATFORM_INV);
		final String content = contentTemp == null ? null :String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_RECOMEND_PLATFORM_INV),crmCfplanner == null ? "" : crmCfplanner.getUserName() == null ? "" :crmCfplanner.getUserName() +crmCfplanner.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"),orgInfo == null ? "" :orgInfo.getOrgName());

		//插入机构推荐信息
		if(StringUtils.isNotBlank(userIdString)){
			String[] investorArray = orgRecommendByChooseRequest.getUserIdString().split(",");
			for(String investorUserId : investorArray){			
				//如果该投资人不存在  直接跳出当前循环进入下一次循环
				CrmInvestor crmInvestor = new CrmInvestor();
				crmInvestor.setUserId(investorUserId);
				crmInvestor = crmInvestorService.selectOne(crmInvestor);
				if(crmInvestor == null) continue;
				
				CimOrgRef cimOrgRef = new CimOrgRef();
				cimOrgRef.setOrgNumber(orgCode);
				cimOrgRef.setSaleUserId(userId);
				cimOrgRef.setInvestorUserId(investorUserId);
				cimOrgRef.setRecommendTime(new Date());
				cimOrgRef.setRemarks("理财师选择推荐机构");
				cimOrgRefService.insert(cimOrgRef);
				//构建个人消息对象
				SysMsg msg = new SysMsg();				
				msg.setContent(content);
				msg.setStatus(0);// 发布
				msg.setUserNumber(investorUserId);
				msg.setReadStatus(0);// 未读
				msg.setAppType(AppTypeEnum.INVESTOR.getKey());
				msg.setTypeName(PersonalMsgTypeEnum.PROJECTINVEST_INV.getValue());
				msg.setStartTime(new Date());
				msg.setCrtTime(new Date());
				msg.setModifyTime(new Date());
				msg.setLinkBtnTxt("立即查看");
				msg.setLinkUrlKey(PersonalMsgTypeEnum.RECOMMEND_PLATFORM.getMsg());
				msgList.add(msg);
				//
				userIds.add(investorUserId);
			}	
		}
		//给推荐成功的投资客户发个人消息
		if(content != null && msgList.size() > 0){
			ThreadpoolService.execute(new Runnable() {
				@Override
				public void run() {
					sysMsgService.addMsgs(msgList);
					Map<String,Object> urlparam = Maps.newHashMap();
					urlparam.put("orgNo",orgCode);
					pushMessageHelper.BatchSinglePush(AppTypeEnum.INVESTOR, SmsTypeEnum.PLATFORMDTL_INC, userIds, "平台推荐", content, urlparam, false, PersonalMsgTypeEnum.RECOMMEND_PLATFORM);
				}
			});
		}
	}

	@Override
	public PaginatorResponse<CimOrginfo> queryPcPlannerRecommendPlatfrom(Page<CimOrginfo> page, String investUserId, String saleUserId) {
		PaginatorResponse<CimOrginfo> paginatorResponse = new PaginatorResponse<CimOrginfo>();
		List<CimOrginfo> queryPcCimOrginfoList = cimOrginfoMapper.queryPcPlannerRecommendPlatfrom(page,investUserId,saleUserId);
		redPacketService.patformRedPacketTag(queryPcCimOrginfoList,investUserId); //查询机构是否有红包
		paginatorResponse.setDatas(queryPcCimOrginfoList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public BigDecimal queryOrgFeeRatio(String orgNumber) {
		return cimOrginfoMapper.queryOrgFeeRatio(orgNumber);
	}

	@Override
	public InvestmentStrategyResponse queryInvestmentStrategy(String orgCode) {
		return cimOrginfoMapper.queryInvestmentStrategy(orgCode);
	}

	@Override
	public String queryOrgSecurity(String orgNumber) {
		return cimOrginfoMapper.queryOrgSecurity(orgNumber);
	}

	@Override
	public List<CimOrginfo> selectListByGrade(String securityLevel,Boolean ifHaveGray) {
		// TODO Auto-generated method stub
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("securityLevel", securityLevel);
		conditions.put("ifHaveGray", ifHaveGray);
		return cimOrginfoMapper.selectListByGrade(conditions);
	}

	@Override
	public BigDecimal queryOrgDiffFeeRatio(String orgNumber) {
		return cimOrginfoMapper.queryOrgDiffFeeRatio(orgNumber);
	}

	@Override
	public CimOrginfo queryOrgInfoByOrgNumber(String orgNumber) {
		// TODO Auto-generated method stub
		CimOrginfo cimOrginfo = new CimOrginfo();
		cimOrginfo.setOrgNumber(orgNumber);
		cimOrginfo = cimOrginfoMapper.selectOneByCondition(cimOrginfo);
		return cimOrginfo;
	}

	@Override
	public Integer queryOrgUseProductNums(String orgNumber) {
		return cimOrginfoMapper.queryOrgUseProductNums(orgNumber);
	}

	@Override
	public List<PlatformAcctManagerListResp> bindOrgAccountCount(Map<String, Object> map) {
		return cimOrginfoMapper.bindOrgAccountCount(map);
	}

	@Override
	public int unBindOrgAccountCount(Map<String, Object> map) {
		return cimOrginfoMapper.unBindOrgAccountCount(map);
	}
}
