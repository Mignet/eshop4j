package com.linkwee.api.controller.cim;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.api.request.crm.BindOrgAcctRequest;
import com.linkwee.api.request.crm.PlatformManagerListRequest;
import com.linkwee.api.request.crm.WeiXinMsgRequest;
import com.linkwee.api.response.cim.PlatformAcctManagerListResponse;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.base.api.SuccessResponse;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.SignUtils;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.PlatformEnum;
import com.linkwee.web.enums.RequestTypeEnums;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.CimOrgAdvertises;
import com.linkwee.web.model.CimOrgDynamic;
import com.linkwee.web.model.CimOrgPicture;
import com.linkwee.web.model.CimOrgRisk;
import com.linkwee.web.model.CimOrgUrl;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.CimProduct;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.CrmOrgAcctRel;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.SysConfig;
import com.linkwee.web.model.SysThirdkeyConfig;
import com.linkwee.web.model.acc.AcAccountBind;
import com.linkwee.web.model.cim.OrgInfo;
import com.linkwee.web.model.cim.PcOrgInfo;
import com.linkwee.web.model.crm.PlatformAcctManagerListResp;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.request.OrgInfoRequest;
import com.linkwee.web.request.orgInfo.OrgDetailRequest;
import com.linkwee.web.request.orgInfo.OrgJumpRequest;
import com.linkwee.web.request.orgInfo.OrgRecommendByChooseRequest;
import com.linkwee.web.request.orgInfo.OrgRecommendChooseRequest;
import com.linkwee.web.response.CimNewOrgListResponse;
import com.linkwee.web.response.CimOrgListResponse;
import com.linkwee.web.response.CimPcOrgListResponse;
import com.linkwee.web.response.PcPlannerRecommendResponse;
import com.linkwee.web.response.PlannerRecommendResponse;
import com.linkwee.web.response.orgInfo.CimOrginfoChannelResponse;
import com.linkwee.web.response.orgInfo.CimOrginfoInvestorResponse;
import com.linkwee.web.response.orgInfo.CimPcOrginfoInvestorResponse;
import com.linkwee.web.response.orgInfo.InvestmentStrategyResponse;
import com.linkwee.web.response.orgInfo.OrgInfoResponse;
import com.linkwee.web.response.orgInfo.OrgRecommendChooseResponse;
import com.linkwee.web.response.orgInfo.OrgSaleProductResponse;
import com.linkwee.web.response.orgInfo.PcOrgInfoResponse;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.CimOrgAdvertisesService;
import com.linkwee.web.service.CimOrgDynamicService;
import com.linkwee.web.service.CimOrgPictureService;
import com.linkwee.web.service.CimOrgRiskService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CimProductService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysGrayReleaseService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.web.service.SysThirdkeyConfigService;
import com.linkwee.web.service.WeiXinMsgService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.helper.ThreadpoolService;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.OpenHttpUtils;
import com.linkwee.xoss.util.RequestLogging;

/**
 * 机构接口控制器
 *
 * @author Mignet
 * @since 2014年5月28日 下午3:54:00
 **/
@Controller
@RequestMapping(value = "/api/platfrom")
@RequestLogging("机构")
public class PlatfromController extends BaseController{
	
	@Resource
	private CimOrginfoService cimOrgInfoService;
	@Resource
	private SysConfigService sysconfigService;
	@Resource
	private CimProductService cimProductService;
	@Resource
	private SysThirdkeyConfigService sysThirdkeyConfigService;
	@Resource
	private  AcAccountBindService accountbindService;
	@Resource
	private CrmUserInfoService crmUserInfoService;
	@Resource
	private SysConfigService sysConfigService; //系统配置
	@Resource
	private CrmInvestorService crmInvestorService;  //投资用户服务
	@Resource
	private CimOrgPictureService cimOrgPictureService;  //机构图片配置服务
	@Resource
	private CimOrgDynamicService cimOrgDynamicService;  //机构动态服务
	@Resource
	private ActivityListService activityListService; //活动服务
	@Resource
	private SysGrayReleaseService sysGrayReleaseService; //灰度服务
	@Resource
	private CimOrgAdvertisesService cimOrgAdvertisesService;  //机构活动宣传图配置服务
	@Resource
	private SysMsgService sysMsgService;//站内个人消息服务
	@Resource
	private CimOrgRiskService cimOrgRiskService; //机构风控信息服务
	@Resource 
	private WeiXinMsgService weiXinMsgService;
	@Resource
	private RedPacketService redPacketService; //红包服务
	
	@Resource
	private ConfigHelper configHelper;

	/**
     * 热门机构/优质机构  不分页
     * @return
     */
    @RequestMapping(value="/highQualityPlatform")
    @ResponseBody
    @RequestLogging("优质机构")
	public BaseResponse getRecommendOrgInfo(AppRequestHead head,PaginatorRequest req) {
    	String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken()); //获取用户id
    	Boolean isGrayUser = sysGrayReleaseService.ifHaveGrayPermission(userId, "0,2"); //判断是否灰度用户
    	
    	logger.debug("优质机构 request = {}", JSON.toJSONString(head));
    	//判断是否PC端投呗
    	if(PlatformEnum.WEB == AppUtils.getPlatform(head.getOrgNumber()) && AppUtils.isInvestorApp(head.getOrgNumber())){
    		Page<CimOrginfo> page  = new Page<CimOrginfo>(req.getPageIndex(),req.getPageSize()); //默认每页10条
    		PaginatorResponse<CimOrginfo> pcRecommendOrg = cimOrgInfoService.findPcRecommendOrg(page,isGrayUser);
    		
    		return AppResponseUtil.getSuccessResponse(pcRecommendOrg,CimPcOrgListResponse.class); 
    		
    	}
    	
		List<CimOrginfo> orgDatas = cimOrgInfoService.findRecommendOrg(isGrayUser); //移动端
		
		return AppResponseUtil.getSuccessResponse(orgDatas,CimOrgListResponse.class);
		
	}
    
    /**
     * PC端 投呗 最新入驻机构  
     * 固定返回8家 不分页
     * @return
     */
    @RequestMapping(value="/queryLatestOrg")
    @ResponseBody
    @RequestLogging("最新入驻机构")
	public BaseResponse queryLatestOrg(AppRequestHead head) {
    	String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken()); //获取用户id
    	Boolean isGrayUser = sysGrayReleaseService.ifHaveGrayPermission(userId, "0,2"); //判断是否灰度用户
    	logger.debug("PC端最新入驻机构 request = {}", JSON.toJSONString(head));
    	List<CimOrginfo> latestDatas = cimOrgInfoService.queryLatestOrg(isGrayUser);
    	return AppResponseUtil.getSuccessResponse(latestDatas,CimNewOrgListResponse.class);
    }

	/**
	 * 机构分页-分页
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/pageList")
	@ResponseBody
	@RequestLogging("机构列表")
	public BaseResponse newsPageList(@Valid OrgInfoRequest req,BindingResult result,AppRequestHead head){
    	logger.debug("机构列表请求参数 OrgInfoRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
    	if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
    	String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken()); //获取用户id
    	Boolean isGrayUser = sysGrayReleaseService.ifHaveGrayPermission(userId, "0,2"); //判断是否灰度用户
    	
    	Page<CimOrginfo> page  = new Page<CimOrginfo>(req.getPageIndex(),req.getPageSize()); //默认每页10条
    	Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
    	
    	conditions.put("userId", userId);
    	
    	//灰度用户判定
    	conditions.put("isGrayUser", isGrayUser);
    	
    	//机构级别
    	if(StringUtils.isNotBlank(req.getSecurityLevel())){
    		conditions.put("securityLevel", req.getSecurityLevel());
    	}
    	//产品期限
    	if(StringUtils.isNotBlank(req.getProductDeadLine()) && req.getProductDeadLine().contains(",")){
    		String[] deadLine = req.getProductDeadLine().split(",");
    		if(deadLine.length == 2){
    			String minDeadLine = deadLine[0];
    			String maxDeadLine = deadLine[1];
    			conditions.put("minDeadLine", minDeadLine);
    			conditions.put("maxDeadLine", maxDeadLine);
    		}
    	}
    	//年化收益
    	if(StringUtils.isNotBlank(req.getYearProfit()) && req.getYearProfit().contains(",")){
    		String[] yearProfit = req.getYearProfit().split(",");
    		if(yearProfit.length == 2){
    			String minYearProfit = yearProfit[0];
    			String maxYearProfit = yearProfit[1];
    			conditions.put("minYearProfit", minYearProfit);
    			conditions.put("maxYearProfit", maxYearProfit);
    		}
    	}
    	
    	//判断是否PC端投呗
    	if(PlatformEnum.WEB == AppUtils.getPlatform(head.getOrgNumber()) && AppUtils.isInvestorApp(head.getOrgNumber())){

    		if(StringUtils.isNotBlank(req.getBackground())){ //机构背景
    			conditions.put("background", req.getBackground());
    		}
    		
    		if(StringUtils.isNotBlank(req.getCity())){ //机构所在城市
    			if(!req.getCity().equals("other")){
    				conditions.put("city", req.getCity());
    			}else{
    				//查询机构所在城市
    				List<SysConfig> orgCityList = sysConfigService.querySysConfigByName("PC端机构筛选条件-所在城市"); //机构背景配置
    				conditions.put("orgCityList", orgCityList);
    				conditions.put("city", "other");
    			}
    		}
    		
    		conditions.put("appType",head.getOrgNumber()); 
    		PaginatorResponse<CimOrginfo> orgPcdatas = cimOrgInfoService.queryOrgList(page,conditions); //PC版投呗
    		
    		return AppResponseUtil.getSuccessResponse(orgPcdatas,CimPcOrginfoInvestorResponse.class);

    		
    	}
    	conditions.put("appType",head.getOrgNumber()); //1理财师，2投资者
		PaginatorResponse<CimOrginfo> orgdatas = cimOrgInfoService.queryOrgList(page,conditions); //机构列表的分页信息
		//PaginatorSevResp
		
		//理财师
		if(AppUtils.isChannelApp(head.getOrgNumber())){
			return AppResponseUtil.getSuccessResponse(orgdatas,CimOrginfoChannelResponse.class);
		}
		//投资者
		return AppResponseUtil.getSuccessResponse(orgdatas,CimOrginfoInvestorResponse.class);
	}
	
    
	/**
	 * 移动端 机构详情
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/detail")
	@ResponseBody
	@RequestLogging("机构信息详情")
	public BaseResponse orgDetail(@Valid OrgDetailRequest req,BindingResult result, AppRequestHead head){
    	logger.debug("机构信息详情请求参数 OrgDetailRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		
		//判断是否PC端投呗
    	if(PlatformEnum.WEB == AppUtils.getPlatform(head.getOrgNumber()) && AppUtils.isInvestorApp(head.getOrgNumber())){
			OrgInfo orgInfo = cimOrgInfoService.findOrgInfo(req.getOrgNo());
			
			if(orgInfo != null){
				return AppResponseUtil.getSuccessResponse(orgInfo,OrgInfoResponse.class);
			}else{
				return AppResponseUtil.getErrorBusi("orgNotExist","此平台不存在");
			}
    	}else{
    		//移动端
    		OrgInfo orgInfo = cimOrgInfoService.findOrgInfo(req.getOrgNo());
    		
    		if(orgInfo != null){
    			//平台可用红包数
        		int platformRedPacketCount = redPacketService.patformRedPacketCount(orgInfo.getOrgNo(),orgInfo.getOrgFeeType(), JsonWebTokenHepler.getUserIdByToken(head.getToken()));
        		orgInfo.setPlatformRedPacketCount(platformRedPacketCount);
        		
    			List<CimOrgAdvertises> orgAdvertisesList = cimOrgAdvertisesService.queryOrgAdvertisesList(req.getOrgNo()); //[] 机构活动宣传图
        		List<CimOrgPicture> orgPapersList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),1,1); //公司证件照
        		List<CimOrgPicture> orgEnvironmentList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),2,1); //办公环境照
        		List<CimOrgPicture> orgHonorList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),3,1); //荣誉证书
        		Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
        		conditions.put("activityPlatform", orgInfo.getOrgName()); //平台名称
        		//理财师
        		if(AppUtils.isChannelApp(head.getOrgNumber())){
        			conditions.put("appType", 1); //理财师
        		}else{
        			conditions.put("appType", 2); //T呗
        		}
        		List<ActivityList> orgActivityList = activityListService.queryPlatformActivities(conditions); //[] 机构活动宣传图
        		List<CimOrgDynamic> orgDynamicList = cimOrgDynamicService.queryCimOrgDynamicList(req.getOrgNo()); //[] 机构动态
        		
        		if(orgAdvertisesList != null){
        			orgInfo.setOrgAdvertises(orgAdvertisesList);
        		}
        		
        		if(orgPapersList != null){
        			orgInfo.setOrgPapersList(orgPapersList);
        		}
        		if(orgEnvironmentList != null){
        			orgInfo.setOrgEnvironmentList(orgEnvironmentList);
        		}
        		if(orgHonorList != null){
        			orgInfo.setOrgHonorList(orgHonorList);
        		}
        		
        		if(orgActivityList != null){
        			orgInfo.setOrgActivitys(orgActivityList);
        		}
        		
        		if(orgDynamicList != null){
        			orgInfo.setOrgDynamicList(orgDynamicList);
        		}
        		return AppResponseUtil.getSuccessResponse(orgInfo,OrgInfoResponse.class);
    		}else{
				return AppResponseUtil.getErrorBusi("orgNotExist","此平台不存在");
			}
    	}
		
	}
    

	/**
	 * PC端T呗机构详情
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/pcOrgDetail")
	@ResponseBody
	@RequestLogging("PC端T呗机构信息详情")
	public BaseResponse orgPcDetail(@Valid OrgDetailRequest req,BindingResult result, AppRequestHead head){
    	logger.debug("PC端T呗机构信息详情请求参数 OrgDetailRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		//判断是否PC端投呗
    	if(PlatformEnum.WEB == AppUtils.getPlatform(head.getOrgNumber()) && AppUtils.isInvestorApp(head.getOrgNumber())){
    		
			PcOrgInfo pcOrgInfo = cimOrgInfoService.findPcOrgInfo(req.getOrgNo());
			if(pcOrgInfo == null){
				return AppResponseUtil.getErrorBusi("orgNotExist","PC端此平台不存在");
			}
			
			//平台可用红包数
    		int platformRedPacketCount = redPacketService.patformRedPacketCount(pcOrgInfo.getOrgNumber(),pcOrgInfo.getOrgFeeType(), JsonWebTokenHepler.getUserIdByToken(head.getToken()));
    		pcOrgInfo.setPlatformRedPacketCount(platformRedPacketCount);
    		
			Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
    		conditions.put("activityPlatform", pcOrgInfo.getOrgName()); //平台名称
    		conditions.put("appType", 2); //T呗
    		
    		List<ActivityList> orgActivityList = activityListService.queryPlatformActivities(conditions); //[] 机构活动宣传图
    		List<CimOrgPicture> orgPapersList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),1,2); //公司证件照
    		List<CimOrgPicture> orgEnvironmentList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),2,2); //办公环境照
    		List<CimOrgPicture> orgHonorList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),3,2); //荣誉证书
    		List<CimOrgRisk> orgRiskList = cimOrgRiskService.queryOrgRiskInfoByOrgNumber(req.getOrgNo()); //机构风控信息
    		
    		if(orgRiskList != null){
    			pcOrgInfo.setOrgRiskList(orgRiskList);
    		}
    		if(orgPapersList != null){
    			pcOrgInfo.setOrgPapersList(orgPapersList);
    		}
    		if(orgEnvironmentList != null){
    			pcOrgInfo.setOrgEnvironmentList(orgEnvironmentList);
    		}
    		if(orgHonorList != null){
    			pcOrgInfo.setOrgHonorList(orgHonorList);
    		}
    		if(orgActivityList != null){
    			pcOrgInfo.setOrgActivityList(orgActivityList);
    		}
			return AppResponseUtil.getSuccessResponse(pcOrgInfo,PcOrgInfoResponse.class);
    	}else{
    		return AppResponseUtil.getErrorBusi("requestParamError","不是PC端投呗");
    	}
	}
    /**
	 * 机构筛选条件
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/platformHead")
	@ResponseBody
	@RequestLogging("机构筛选条件")
	public BaseResponse platformHead(AppRequestHead head) throws Exception {
    	logger.debug("机构筛选条件:"+head);
		Map<String,Object> rlt = new HashMap<String,Object>();
		List<SysConfig> sysList = sysconfigService.querySysConfigByName("机构筛选条件-");
		List<Map<String,Object>> orgLevelList = new ArrayList<Map<String,Object>>(); //机构评级
		List<Map<String,Object>> profitList = new ArrayList<Map<String,Object>>(); //机构年化收益
		List<Map<String,Object>> deadlineList = new ArrayList<Map<String,Object>>(); //机构产品期限
		for(SysConfig item:sysList){
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("key", item.getConfigKey());
			data.put("value", item.getConfigValue());
			if(item.getConfigType().endsWith("grade")){ //评级
				orgLevelList.add(data);
			}else if(item.getConfigType().endsWith("profit")){ //年化收益
				profitList.add(data);
			}else if(item.getConfigType().endsWith("days")){ //产品期限
				deadlineList.add(data);
			}
		}
		rlt.put("orgLevel", orgLevelList);
		rlt.put("profit", profitList);
		rlt.put("deadline", deadlineList);
		
		//判断是否PC端投呗
    	if(PlatformEnum.WEB == AppUtils.getPlatform(head.getOrgNumber()) && AppUtils.isInvestorApp(head.getOrgNumber())){
    		List<SysConfig> pcSysList = sysconfigService.querySysConfigByName("PC端机构筛选条件-");
    		List<Map<String,Object>> orgCityList = new ArrayList<Map<String,Object>>(); //所在城市
    		List<Map<String,Object>> orgBackgroundList = new ArrayList<Map<String,Object>>(); //机构背景
    		
    		for(SysConfig config : pcSysList){
    			Map<String, Object> data = new HashMap<String, Object>();
    			data.put("key", config.getConfigKey());
    			data.put("value", config.getConfigValue());
    			if(config.getConfigType().endsWith("background")){ //机构背景
    				orgBackgroundList.add(data);
    			}else if(config.getConfigType().endsWith("city")){ //所在城市
    				orgCityList.add(data);
    			}
    		}
    		rlt.put("background", orgBackgroundList);
    		rlt.put("city", orgCityList);
    	}
		logger.debug("机构筛选条件 | query success!");
		return AppResponseUtil.getSuccessResponse(rlt);
	}
    
    
    /**
     * 机构在售产品
     * @author yalin 
     * @date 2016年7月26日 下午2:04:55  
     * @param req
     * @param validResult
     * @param head
     * @return
     * @throws Exception
     */
    @RequestMapping("platformSaleProducts")
	@ResponseBody
	@RequestLogging("机构在售产品")
	public BaseResponse platformSaleProducts( @Valid OrgDetailRequest req,BindingResult validResult,AppRequestHead head) throws Exception {
    	if (AppResponseUtil.existsParamsError(validResult)) {
			return AppResponseUtil.getErrorParams(validResult);
		}
    	Page<OrgSaleProductResponse> page  = new Page<OrgSaleProductResponse>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<OrgSaleProductResponse> result = cimProductService.queryOrgSaleProducts(req.getOrgNo(), page);
		return AppResponseUtil.getSuccessResponse(result);
	}
    
    
    /**
	 * 机构帐号管理列表
	 */
	@RequestMapping("accountManager/pageList")
	@ResponseBody
	@RequestLogging("机构帐号管理列表")
	public BaseResponse platformAcctManagerPageList(@Valid PlatformManagerListRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Boolean isGrayUser = sysGrayReleaseService.ifHaveGrayPermission(userId, "0,2");
		Map<String ,Object> query = new HashMap<String ,Object>();
		query.put("type", req.getType());
		query.put("userId", userId);
		query.put("isGrayUser", isGrayUser);
		Page<PlatformAcctManagerListResp> page = new Page<PlatformAcctManagerListResp>(req.getPageIndex(), req.getPageSize());
		PaginatorSevResp<PlatformAcctManagerListResp> rlt = cimOrgInfoService.queryPlatformAcctManagerPageList(query, page);
		return AppResponseUtil.getSuccessResponse(rlt,PlatformAcctManagerListResponse.class);
	}
	
	/**
	 * 机构帐号管理统计
	 */
	@RequestMapping("accountManager/statistics")
	@ResponseBody
	@RequestLogging("机构帐号管理统计")
	public BaseResponse platformAcctManagerStatistics(AppRequestHead head) throws Exception {
		Map<String ,String > map = new HashMap<String, String>();
		Map<String ,Object > query = new HashMap<String, Object>();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Boolean isGrayUser = sysGrayReleaseService.ifHaveGrayPermission(userId, "0,2");
		query.put("userId", userId);
		query.put("isGrayUser", isGrayUser);
		//int bindOrgAccountCount = cimOrgInfoService.queryOrgAccountCount(JsonWebTokenHepler.getUserIdByToken(head.getToken()));
		//int unBindOrgAccountCount = cimOrgInfoService.queryOrgCount() - bindOrgAccountCount;
		int unBindOrgAccountCount = cimOrgInfoService.unBindOrgAccountCount(query);
		List<PlatformAcctManagerListResp> bindList = cimOrgInfoService.bindOrgAccountCount(query);
		List<PlatformAcctManagerListResp> removeList = new ArrayList<PlatformAcctManagerListResp>();
		
		if(bindList != null && bindList.size() > 0){
			for(PlatformAcctManagerListResp bo : bindList) {
				//查看合作结束的平台并且用户在平台的投资记录数大于0的平台
				if((bo.getStatus() == null || bo.getStatus() == 0) && bo.getInvestCount() == 0){
					removeList.add(bo);
				}
			}
		}
		bindList.removeAll(removeList); //集合中移除合作结束并且投资记录数为0的平台
		map.put("bindOrgAccountCount", "" + bindList.size());//绑定机构帐号数
		map.put("unBindOrgAccountCount", "" + unBindOrgAccountCount);//未绑定机构数
		return AppResponseUtil.getSuccessResponse(map);
	}
	
	/**
	 * 绑定机构帐号
	 */
	@RequestMapping("bindOrgAcct")
	@ResponseBody
	@RequestLogging("绑定机构帐号")
	public BaseResponse bindOrgAcct(@Valid BindOrgAcctRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		final String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if(cimOrgInfoService.isBindOrgAcct(userId, req.getPlatFromNumber())){
			return AppResponseUtil.getErrorBusi("orgAccountExist","用户已绑定此第三方平台");
		}
		AcAccountBind account = accountbindService.selectAccountByUserId(userId);
		if(account == null || account.getBankCard() == null || account.getIdCard() == null) {
			return AppResponseUtil.getErrorBusi("userNotBindBankCard","用户未绑卡不能进行第三方平台绑定");
		}
		String rlt = null;
		try {
			CimOrginfo org = new CimOrginfo();
			org.setOrgNumber(req.getPlatFromNumber());
			org = cimOrgInfoService.selectOne(org);
			SysThirdkeyConfig sysThirdkeyConfig = new SysThirdkeyConfig();
			sysThirdkeyConfig.setOrgNumber(req.getPlatFromNumber());
			sysThirdkeyConfig = sysThirdkeyConfigService.selectOne(sysThirdkeyConfig);
			if(sysThirdkeyConfig == null || org == null || org.getOrgBindUserUrl() == null){
				logger.info("绑定第三方平台第三方配置数据不存在: platFormNumber = {}; userId = {}" , req.getPlatFromNumber(), userId);
				return AppResponseUtil.getErrorBusi("dataError","第三方配置数据异常，请联系管理员");
			}
			CrmUserInfo user = crmUserInfoService.queryUserInfoByUserId(userId);
			
			logger.debug("绑定第三方平台请求参数 : url = {}" , org.getOrgBindUserUrl());
			Map<String , String> param = new HashMap<String , String>();
			param.put("userId", userId);
			param.put("mobile", user.getMobile());
			rlt = OpenHttpUtils.httpRequest(sysThirdkeyConfig, RequestTypeEnums.GET, org.getOrgBindUserUrl(), param);
			logger.debug("绑定第三方平台返回数据" + rlt);
			@SuppressWarnings("unchecked")
			SuccessResponse<Map<String ,Object >> jb = JSONObject.toJavaObject(JSONObject.parseObject(rlt), SuccessResponse.class);
			if(jb == null) {
				logger.info("绑定第三方平台第三方返回数据异常: platFormNumber = {}; userId = {}" , req.getPlatFromNumber(), userId);
				return AppResponseUtil.getErrorBusi("dataError","第三方返回数据异常，请联系管理员");
			}
			if("0".equals(jb.getCode())){
				CrmOrgAcctRel bo = new CrmOrgAcctRel();
				bo.setUserId(userId);
				bo.setOrgNumber(req.getPlatFromNumber());
				bo.setOrgAccount(jb.getData().get("orgAccount").toString());
				bo.setIsInvested(jb.getData().get("isInvested").toString().equals("Y") ? 1 : 0);
				bo.setIsNewUser(jb.getData().get("isNewUser").toString().equals("Y") ? 1 : 0);
				bo.setOrgAccountType(1);
				bo.setUpdateTime(new Date());
				bo.setCreatTime(new Date());
				cimOrgInfoService.bindOrgAcct(bo);
				
				//站内信
				final String platformName = org == null ? "" : org.getName();
			    String content = String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_THIRD_ACC_BIND_SUCCESS_INV),platformName,platformName);
			    final SysMsg msg = new SysMsg();
				msg.setContent(content);
				msg.setStatus(0);// 发布
				msg.setUserNumber(userId);
				msg.setReadStatus(0);// 未读
				msg.setAppType(AppTypeEnum.INVESTOR.getKey());
				ThreadpoolService.execute(new Runnable() {
					@Override
					public void run() {
						sysMsgService.addMsg(msg);
					}
				});
				
				//异步发微信消息
				ThreadpoolService.execute(new Runnable() {
					@Override
					public void run() {
						WeiXinMsgRequest wxreq = new WeiXinMsgRequest();
						wxreq.setUseId(userId);
						wxreq.setTemkey(SysConfigConstant.OPEN_THIRD_ACCOUNT_SUCCESS);
						wxreq.setPlatformName(platformName);//平台名称
						wxreq.setOpenTime(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));
						wxreq.setUseType(2);
						weiXinMsgService.sendWeiXinMsgCommon(wxreq);
					}
				});
				
			} else {
				if("OPEN_XIAONIUZAIXIAN_WEB".equals(org.getOrgNumber()) && jb.getMsg().contains("手机号码已存在") ) {
					//小牛用户绑定 老用户不能绑定提示
					logger.warn("绑定第三方平台失败: {}" ,jb.getMsg());
					return AppResponseUtil.getErrorBusi("old_user_error", "您是小牛在线老用户，通过T呗投资不能享受红包等奖励，建议购买其他平台产品");
				}
				logger.warn("绑定第三方平台失败: {}" ,jb.getMsg());
				return AppResponseUtil.getErrorBusi("system_error", jb.getMsg());
			}
		} catch (Exception e) {
			logger.error("绑定机构帐号异常: req={},resp={}", new Object[]{req,rlt,e});
			return AppResponseUtil.getErrorBusi("system_error","绑定失败，请联系管理员");
		}
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 平台产品购买跳转地址
	 * @author yalin 
	 * @date 2016年8月3日 下午4:45:30  
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/getOrgProductUrl")
	@ResponseBody
	@RequestLogging("机构产品跳转地址")
	public BaseResponse getOrgProductUrl(@Valid OrgJumpRequest req,BindingResult result, AppRequestHead head){
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		//查询第三方api接口配置
		SysThirdkeyConfig sysThirdkeyConfig = new SysThirdkeyConfig();
		sysThirdkeyConfig.setOrgNumber(req.getOrgNo());
		sysThirdkeyConfig = sysThirdkeyConfigService.selectOne(sysThirdkeyConfig);
		//查询产品跳转链接
		CimOrgUrl orgurl = cimOrgInfoService.selectOrgUrlByOrgNumber(req.getOrgNo());
		//根据产品id查询产品信息
		CimProduct pro = new CimProduct();
		pro.setProductId(req.getProductId());
		pro = cimProductService.selectOne(pro); 
		//拼装跳转参数
		//ProductJumpUrlMsgResponse r = new ProductJumpUrlMsgResponse();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		//通过userid和机构编码 查询用户的 第三方机构用户账号
		String thirdOrgAccount = cimOrgInfoService.queryThirdOrgAccountByUserId(userId, req.getOrgNo());
		//获取投资者最后一次登录id(交易流水号)
		String txId = cimOrgInfoService.queryInvestorLoginId(userId);
		if(txId == null){
			txId = "NONE";
		}
		Map<String,String> paramsMap = new HashMap<String,String>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		paramsMap.put("orgNumber", sysThirdkeyConfig.getOrgNumber());//机构编码
		paramsMap.put("orgKey",sysThirdkeyConfig.getOrgKey());//机构公钥
		paramsMap.put("timestamp",simpleDateFormat.format(new Date()));
		paramsMap.put("thirdProductId",pro.getThirdProductId());//第三方机构产品id
		paramsMap.put("orgAccount",thirdOrgAccount);//第三方机构用户账号
		paramsMap.put("txId",txId);//投资者最后一次登录id(交易流水号)
		//请求来源,判断是否PC端
    	if(PlatformEnum.WEB == AppUtils.getPlatform(head.getOrgNumber())){
    		paramsMap.put("requestFrom","web");//PC端
    	}else{
    		paramsMap.put("requestFrom","wap");//移动端
    	}
        String sign = SignUtils.sign(paramsMap,sysThirdkeyConfig.getOrgSecret());//机构私钥
        paramsMap.put("sign", sign); //生成签名
        paramsMap.put("orgProductUrl", orgurl.getOrgProductUrl()); //机构产品跳转地址
        logger.info("机构产品跳转地址最终参数列表={}",JSONObject.toJSONString(paramsMap));
        //生成购买签名后的URl
      	//String productPurchaseUrl =  OpenHttpUtils.generateSignUrl(sysThirdkeyConfig, orgurl.getOrgProductUrl(), productJumpUrlMsgResponse);
		return AppResponseUtil.getSuccessResponse(paramsMap);
	}
	
	/**
	 * 平台用户中心跳转地址
	 * @author yalin 
	 * @date 2016年8月3日 下午4:45:49  
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/getOrgUserCenterUrl")
	@ResponseBody
	@RequestLogging("机构用户中心跳转地址")
	public BaseResponse getOrgUserCenterUrl(@Valid OrgDetailRequest req,BindingResult result,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		//查询第三方api接口配置
		SysThirdkeyConfig sysThirdkeyConfig = new SysThirdkeyConfig();
		sysThirdkeyConfig.setOrgNumber(req.getOrgNo());
		sysThirdkeyConfig = sysThirdkeyConfigService.selectOne(sysThirdkeyConfig);
			
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		String thirdOrgAccount = cimOrgInfoService.queryThirdOrgAccountByUserId(userId,req.getOrgNo());//通过userid和机构编码 查询用户的 第三方机构用户账号
		CimOrgUrl orgurl = cimOrgInfoService.selectOrgUrlByOrgNumber(req.getOrgNo());
		//UserCenterJumpUrlMsgResponse res = new UserCenterJumpUrlMsgResponse();
		
		Map<String,String> paramsMap = new HashMap<String,String>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		paramsMap.put("orgNumber", sysThirdkeyConfig.getOrgNumber());//机构编码
		paramsMap.put("orgKey",sysThirdkeyConfig.getOrgKey());//机构公钥
		paramsMap.put("timestamp",simpleDateFormat.format(new Date()));
		paramsMap.put("orgAccount",thirdOrgAccount);//第三方机构用户账号
		//判断是否PC端
    	if(PlatformEnum.WEB == AppUtils.getPlatform(head.getOrgNumber())){
    		paramsMap.put("requestFrom","web");//PC端
    	}else{
    		paramsMap.put("requestFrom","wap");//移动端
    	}
    	
        String sign = SignUtils.sign(paramsMap,sysThirdkeyConfig.getOrgSecret());//机构私钥
        paramsMap.put("sign", sign); //生成签名
        paramsMap.put("orgUsercenterUrl", orgurl.getOrgUsercenterUrl()); //用户中心跳转地址
        
        logger.info("机构用户中心跳转地址最终参数列表={}",JSONObject.toJSONString(paramsMap));
		return AppResponseUtil.getSuccessResponse(paramsMap);
	}
	/**
	 * 是否绑定机构帐号
	 */
	@RequestMapping("isBindOrgAcct")
	@ResponseBody
	@RequestLogging("是否绑定机构帐号")
	public BaseResponse isBindOrgAcct(@Valid BindOrgAcctRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		boolean flag = cimOrgInfoService.isBindOrgAcct(JsonWebTokenHepler.getUserIdByToken(head.getToken()),req.getPlatFromNumber());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isBind", flag);
		return AppResponseUtil.getSuccessResponse(map);
	}
	
	/**
	 * 帐号是否存在于第三方平台
	 */
	@RequestMapping("isExistInPlatform")
	@ResponseBody
	@RequestLogging("帐号是否存在于第三方平台")
	public BaseResponse isExistInPlatform(@Valid BindOrgAcctRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		CimOrginfo org = new CimOrginfo();
		org.setOrgNumber(req.getPlatFromNumber());
		org = cimOrgInfoService.selectOne(org);
		SysThirdkeyConfig sysThirdkeyConfig = new SysThirdkeyConfig();
		sysThirdkeyConfig.setOrgNumber(req.getPlatFromNumber());
		sysThirdkeyConfig = sysThirdkeyConfigService.selectOne(sysThirdkeyConfig);
		if(sysThirdkeyConfig == null || org == null){
			logger.info("帐号是否存在于第三方平台配置数据错误");
			return AppResponseUtil.getErrorBusi("dataError","第三方配置数据异常");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if(org.getOrgUserExistUrl() == null || "".equals(org.getOrgUserExistUrl())) {
			map.put("isExist", false);
			return AppResponseUtil.getSuccessResponse(map);
		}
		CrmUserInfo user = crmUserInfoService.queryUserInfoByUserId(userId);
		
		logger.debug("帐号是否存在于第三方平台 : url = {}" , org.getOrgBindUserUrl());
		Map<String , String> param = new HashMap<String , String>();
		param.put("mobile", user.getMobile());
		param.put("userId", user.getUserId());
		String rlt = OpenHttpUtils.httpRequest(sysThirdkeyConfig, RequestTypeEnums.POST, org.getOrgUserExistUrl(), param);
		logger.info("帐号是否存在于第三方平台返回数据" + rlt);
		try {
			@SuppressWarnings("unchecked")
			SuccessResponse<Map<String ,Object >> jb = JSONObject.toJavaObject(JSONObject.parseObject(rlt), SuccessResponse.class);
			if(jb == null || jb.getData() == null) {
				return AppResponseUtil.getErrorBusi("Third party returns data error", "网络繁忙，请联系客服");
			}
			if("0".equals(jb.getCode()) ){
				if("Y".equals(jb.getData().get("isExist"))){
					map.put("isExist", true);
				}else {
					map.put("isExist", false);
				}
			} else {
				return AppResponseUtil.getErrorBusi("Third party returns data error", "网络繁忙，请联系客服");
			}
		} catch (Exception e) {
			return AppResponseUtil.getErrorBusi("Third party returns data error", "网络繁忙，请联系客服");
		}
		return AppResponseUtil.getSuccessResponse(map);
	}
	
	/**
	 * 机构推荐选择列表
	 * @param orgRecommendChooseRequest
	 * @param head
	 * @return
	 */
	@ResponseBody
	@RequestLogging("机构推荐选择列表")
	@RequestMapping("/recommendChooseList")
	public BaseResponse recommendChooseList(AppRequestHead head,OrgRecommendChooseRequest orgRecommendChooseRequest){
		if(StringUtils.isBlank(orgRecommendChooseRequest.getOrgCode())){
			return AppResponseUtil.getErrorBusi("org_number_isNull", "机构编码为null,机构推荐失败");
		}
		OrgRecommendChooseResponse orgRecommendChooseResponse = cimOrgInfoService.recommendChooseList(head,orgRecommendChooseRequest);
		return AppResponseUtil.getSuccessResponse(orgRecommendChooseResponse);
	}
	
	/**
	 * 产品选择推荐
	 * @param orgRecommendByChooseRequest
	 * @param head
	 * @return
	 */
	@ResponseBody
	@RequestLogging("机构选择推荐")
	@RequestMapping("/recommendByChoose")
	public BaseResponse recommendByChoose(AppRequestHead head,OrgRecommendByChooseRequest orgRecommendByChooseRequest){
		if(StringUtils.isBlank(orgRecommendByChooseRequest.getOrgCode())){
			return AppResponseUtil.getErrorBusi("org_number_isNull", "机构编码为null,机构推荐失败");
		}
		cimOrgInfoService.recommendByChoose(head,orgRecommendByChooseRequest);
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 选择未对接机构
	 */
	@RequestMapping("selectPlatfrom")
	@ResponseBody
	@RequestLogging("选择未对接机构")
	public BaseResponse selectPlatfrom(AppRequestHead head) throws Exception {
		return AppResponseUtil.getSuccessResponse(cimOrgInfoService.queryOrgByStatus(1));
	}
	
	/**
	 * 查询理财师给投资客户推荐的平台
	 */
	@RequestMapping("/queryPlannerRecommendPlatfrom")
	@ResponseBody
	@RequestLogging("查询理财师给投资客户推荐的平台")
	public BaseResponse queryPlannerRecommendPlatfrom(PaginatorRequest req,AppRequestHead head) throws Exception {
		/**
		 * 获取用户id
		 */
		String investUserId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		CrmInvestor investor = crmInvestorService.queryPlannerByInvestUserId(investUserId); //查询用户的上级理财师
		Page<CimOrginfo> page  = new Page<CimOrginfo>(req.getPageIndex(),req.getPageSize()); //默认每页10条
		
		//判断是否PC端投呗
    	if(PlatformEnum.WEB == AppUtils.getPlatform(head.getOrgNumber()) && AppUtils.isInvestorApp(head.getOrgNumber())){
    		if(StringUtils.isNotBlank(investor.getCfplanner())){ //token为空未登录(广告图片隐藏)
    			PaginatorResponse<CimOrginfo> pcRecommendPlatfroms = cimOrgInfoService.queryPcPlannerRecommendPlatfrom(page, investUserId, investor.getCfplanner());
    			return  AppResponseUtil.getSuccessResponse(pcRecommendPlatfroms,PcPlannerRecommendResponse.class);
    		}else{
    			return  AppResponseUtil.getErrorBusi("planner_notExist","客户没有理财师!"); 
    		}
    	}
    	
    	//移动端
		if(StringUtils.isNotBlank(investor.getCfplanner())){
			PaginatorResponse<CimOrginfo> recommendPlatfroms = cimOrgInfoService.queryPlannerRecommendPlatfrom(page, investUserId, investor.getCfplanner());
			return AppResponseUtil.getSuccessResponse(recommendPlatfroms,PlannerRecommendResponse.class);
		}else{
			return AppResponseUtil.getErrorBusi("planner_notExist","客户没有理财师!");
		}
	}
	
	/**
	 * 投资攻略
	 */
	@RequestMapping("/investmentStrategy")
	@ResponseBody
	@RequestLogging("投资攻略")
	public BaseResponse queryInvestmentStrategy(String orgCode) throws Exception {
		InvestmentStrategyResponse investmentStrategyResponse = cimOrgInfoService.queryInvestmentStrategy(orgCode);
		return AppResponseUtil.getSuccessResponse(investmentStrategyResponse);
	}
	
	/**
	 * 查询机构动态信息
	 */
	@RequestMapping("/queryOrgDynamicInfo")
	@ResponseBody
	@RequestLogging("查询机构动态信息")
	public BaseResponse queryOrgDynamicInfo(int orgDynamicId,AppRequestHead head) throws Exception {
		CimOrgDynamic orgDynamicInfo = cimOrgDynamicService.queryOrgDynamicInfo(orgDynamicId);
		return AppResponseUtil.getSuccessResponse(orgDynamicInfo);
	}
	
	/**
	 * 查询机构安全保障
	 */
	@RequestMapping("/queryOrgSecurity")
	@ResponseBody
	@RequestLogging(" 查询机构安全保障")
	public BaseResponse queryOrgSecurity(String orgNumber) throws Exception {
		Map<String , String> param = new HashMap<String , String>();
		String orgSecurity = cimOrgInfoService.queryOrgSecurity(orgNumber);
		param.put("orgSecurity", orgSecurity);
		return AppResponseUtil.getSuccessResponse(param);
	}

}
