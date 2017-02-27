package com.linkwee.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.util.ApplicationUtils;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.CimOrgPicture;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.SysConfig;
import com.linkwee.web.model.SysThirdkeyConfig;
import com.linkwee.web.model.User;
import com.linkwee.web.model.cim.CimOrgFeeInterval;
import com.linkwee.web.model.cim.CimOrgFeeRecord;
import com.linkwee.web.model.cim.CimOrgFeeRuleDetail;
import com.linkwee.web.model.cim.CimOrginfoDataTable;
import com.linkwee.web.model.cim.CimOrginfoWeb;
import com.linkwee.web.request.orgInfo.CimOrgFeeRequest;
import com.linkwee.web.request.orgInfo.CimOrgRecommendRequest;
import com.linkwee.web.request.orgInfo.CimOrginfoRequest;
import com.linkwee.web.service.CimOrgFeeRecordService;
import com.linkwee.web.service.CimOrgMemberinfoService;
import com.linkwee.web.service.CimOrgPictureService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysThirdkeyConfigService;
import com.linkwee.xoss.constant.WebConstants;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;

 /**
 * 
 * @描述： 机构信息控制器
 * 
 * @创建人： yalin
 * 
 * @创建时间：2016年07月11日 16:27:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "cim/cimorginfo")
@RequestLogging("机构后台管理模块")
public class CimOrginfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrginfoController.class);

	@Resource
	private CimOrginfoService cimOrginfoService; //机构服务
	@Resource
	private SysConfigService sysConfigService; //系统配置
	@Resource
	private CimOrgMemberinfoService cimOrgMemberinfoService; //机构团队详情
	@Resource
	private SysThirdkeyConfigService SysThirdkeyConfigService; //第三方api接口配置
	@Resource
	private CimOrgFeeRecordService cimOrgFeeRecordService; //平台收费模式
	@Resource
	private CimOrgPictureService cimOrgPictureService; //平台图片
	 
	/**
	 * 转换器
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		//binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false, false)); //XSS过滤
	}
	
	/**
	 *
	 * @return
    */
	@RequestMapping("/ueditor_config")
	@ResponseBody
	public Object ueditorConfig(){
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("imageActionName","zimg");
		result.put("imageFieldName","userfile");
		result.put("imageAllowFiles",new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp"});
		return result;
	}

    /**
     * 基于角色 比如拥有OPERATION_MANAGER角色，才可以查看列表.
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)
   //@RequiresRoles(value = RoleSign.OPERATION_MANAGER)
    @RequestLogging("跳到机构列表页面")
    public String toCimOrgInfoListView(Model model) {
    	return "cimorginfo/cimorginfo-list";
    }

    /**
     * 机构列表
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看机构列表")
	public DataTableReturn cimOrgInfoList(@RequestParam String  _dt_json) {
		LOGGER.debug("CimOrginfo list _dt_json={}", _dt_json);
		CimOrginfoDataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, CimOrginfoDataTable.class);
		String conditions = dataTable.getSearch().getValue(); //查询条件
		if(StringUtils.isNotBlank(dataTable.getOrgName()) && StringUtils.isBlank(conditions)){
			dataTable.getSearch().setValue(dataTable.getOrgName());
		}
		dataTable.initOrders();
		DataTableReturn tableReturn = cimOrginfoService.selectByDatatables(dataTable);
		return tableReturn;
	}
    
    /**
     * 跳到新增页面
     * @return
     */
    @RequestMapping(value="/toAdd",method=RequestMethod.GET)
    @RequestLogging("跳到机构新增页面")
	public ModelAndView toAddView(){
    	ModelAndView modelAndView = new ModelAndView("cimorginfo/cimorginfo-add");
		String imgServerUrl = sysConfigService.getValuesByKey(WebConstants.IMAGE_SERVER_URL);//图片服务器地址
		List<SysConfig> orgBackgroundList = sysConfigService.querySysConfigByName("PC端机构筛选条件-机构背景"); //机构背景配置
		//List<SysConfig> orgGradeList = sysConfigService.querySysConfigByName("机构级别选项"); //机构级别选项配置
		modelAndView.addObject("img_server",imgServerUrl);
		modelAndView.addObject("orgBackgroundList",orgBackgroundList);
		//modelAndView.addObject("orgGradeList",orgGradeList);
		return modelAndView;
	}
    
    /**
     * 跳到编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value="/toEdit",method=RequestMethod.GET)
    @RequestLogging("跳到机构编辑页面")
    public String toEditView(String orgNumber, ModelMap model) {
    	LOGGER.debug("机构信息编辑请求参数 orgNumber = {}" ,orgNumber);
		String imgServerUrl = sysConfigService.getValuesByKey(WebConstants.IMAGE_SERVER_URL);//图片服务器地址
		if(StringUtils.isNotBlank(orgNumber)){
			CimOrginfoWeb orgInfo = cimOrginfoService.findWebOrgInfo(orgNumber); //WEB端 平台信息详情
			List<SysConfig> orgBackgroundList = sysConfigService.querySysConfigByName("PC端机构筛选条件-机构背景"); //机构背景配置
			List<CimOrgPicture> orgPapersList = cimOrgPictureService.queryOrgPictureList(orgNumber,1,1); //移动端-公司证件照
    		List<CimOrgPicture> orgEnvironmentList = cimOrgPictureService.queryOrgPictureList(orgNumber,2,1); //移动端-办公环境照
    		List<CimOrgPicture> orgHonorList = cimOrgPictureService.queryOrgPictureList(orgNumber,3,1); //移动端-荣誉证书
    		List<CimOrgPicture> orgPcPapersList = cimOrgPictureService.queryOrgPictureList(orgNumber,1,2); //PC端-公司证件照
    		List<CimOrgPicture> orgPcEnvironmentList = cimOrgPictureService.queryOrgPictureList(orgNumber,2,2); //PC端-办公环境照
    		List<CimOrgPicture> orgPcHonorList = cimOrgPictureService.queryOrgPictureList(orgNumber,3,2); //PC端-荣誉证书
    		if(orgPapersList != null){
    			StringBuilder orgPapers  = new StringBuilder();
    			for(CimOrgPicture pic : orgPapersList){
    				orgPapers.append(pic.getPid()).append("-").append(pic.getOrgPicture()).append(",");
    			}
    			model.addAttribute("orgPapers",orgPapers);
    		}
    		if(orgEnvironmentList != null){
    			StringBuilder orgEnvironments  = new StringBuilder();
    			for(CimOrgPicture pic : orgEnvironmentList){
    				orgEnvironments.append(pic.getPid()).append("-").append(pic.getOrgPicture()).append(",");
    			}
    			model.addAttribute("orgEnvironments",orgEnvironments);
    		}
    		if(orgHonorList != null){
    			StringBuilder orgHonors  = new StringBuilder();
    			for(CimOrgPicture pic : orgHonorList){
    				orgHonors.append(pic.getPid()).append("-").append(pic.getOrgPicture()).append(",");
    			}
    			model.addAttribute("orgHonors",orgHonors);
    		}
    		
    		if(orgPcPapersList != null){
    			StringBuilder orgPcPapers  = new StringBuilder();
    			for(CimOrgPicture pic : orgPcPapersList){
    				orgPcPapers.append(pic.getPid()).append("-").append(pic.getOrgPicture()).append(",");
    			}
    			model.addAttribute("orgPcPapers",orgPcPapers);
    		}
    		
    		if(orgPcEnvironmentList != null){
    			StringBuilder orgPcEnvironments  = new StringBuilder();
    			for(CimOrgPicture pic : orgPcEnvironmentList){
    				orgPcEnvironments.append(pic.getPid()).append("-").append(pic.getOrgPicture()).append(",");
    			}
    			model.addAttribute("orgPcEnvironments",orgPcEnvironments);
    		}
    		
    		if(orgPcHonorList != null){
    			StringBuilder orgPcHonors  = new StringBuilder();
    			for(CimOrgPicture pic : orgPcHonorList){
    				orgPcHonors.append(pic.getPid()).append("-").append(pic.getOrgPicture()).append(",");
    			}
    			model.addAttribute("orgPcHonors",orgPcHonors);
    		}
			model.addAttribute("orgInfo",orgInfo);
			model.addAttribute("orgBackgroundList",orgBackgroundList);
		}
		model.addAttribute("img_server",imgServerUrl);
    	return "cimorginfo/cimorginfo-edit";
    }
    
    /**
     * 跳到团队模板页面
     * @return
     */
    @RequestMapping(value="/toTeamTemplate",method=RequestMethod.GET)
    @RequestLogging("跳到团队模板页面")
	public ModelAndView toTeamTemplate(){
    	ModelAndView modelAndView = new ModelAndView("cimorginfo/team-template");
		return modelAndView;
	}
    
    
    /**
	 * 机构详情
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/toDetail")
	@RequestLogging("机构信息详情展示")
	public ModelAndView toOrgDetail(String orgNumber){
    	LOGGER.debug("机构信息详情请求参数 orgNumber = {}" ,orgNumber);
    	ModelAndView modelAndView = new ModelAndView("cimorginfo/cimorginfo-detail");
    	String imgServerUrl = sysConfigService.getValuesByKey(WebConstants.IMAGE_SERVER_URL);//图片服务器地址
		if(StringUtils.isNotBlank(orgNumber)){
			CimOrginfoWeb orgInfo = cimOrginfoService.findWebOrgInfo(orgNumber); //WEB端 平台信息详情
			SysThirdkeyConfig thirdkeyConfig = new SysThirdkeyConfig();
			thirdkeyConfig.setOrgNumber(orgNumber);
			thirdkeyConfig = SysThirdkeyConfigService.selectOne(thirdkeyConfig); 
			modelAndView.addObject("orgInfo",orgInfo);
			modelAndView.addObject("thirdkeyConfig",thirdkeyConfig); //第三方api接口配置服务： 查询机构私钥、公钥
		}
		modelAndView.addObject("img_server",imgServerUrl);
		return modelAndView;
	}
    
    /**
	 * 机构推荐信息
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/toRecommend")
	@RequestLogging("机构推荐信息")
    @ResponseBody
	public BaseResponse orgRecommend(Integer id){
    	LOGGER.debug("机构推荐信息请求参数 id = {}" ,id);
    	CimOrginfo orgInfo = null;
		if(null !=id && id>0){
			 orgInfo = cimOrginfoService.selectById(id);//机构详情
		}
		return ResponseUtil.getSuccessResponse(orgInfo);
	}
    
    /**
	 * 更新机构推荐信息
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/updateOrgRecommendInfo")
	@RequestLogging("更新机构推荐信息")
    @ResponseBody
	public ResponseResult updateOrgRecommend(CimOrgRecommendRequest request){
    	LOGGER.debug("更新机构推荐信息-请求参数 CimOrginfoRequest = {}",JSON.toJSONString(request));
   		long start = System.currentTimeMillis();
   		StringBuilder logsb = new StringBuilder();
   		ResponseResult result = null;
   		try {
   			CimOrginfoWeb orginfo = convertOrgRecommendToCimOrginfo(request);
   			cimOrginfoService.updateOrgRecommendInfo(orginfo);
   			result = new ResponseResult(true, "更新机构推荐信息成功！");
   			logsb.append("更新机构推荐信息 success");
   		} catch (Exception e) {
   			logsb.append("更新机构推荐信息 fail");
   			LOGGER.error("更新机构推荐信息失败！", e);
   			result = new ResponseResult(false, "更新机构推荐信息失败！");
   		}
   		long end = System.currentTimeMillis();
   		logsb.append("更新机构推荐信息总耗时 |totaltime=").append(end - start).append("ms");
   		LOGGER.info(logsb.toString());
   		return result;
	}
    
    
    /**
     * 封装机构推荐信息
     * @author yalin 
     * @date 2016年12月15日 下午12:58:24  
     * @param request
     * @param string
     * @return
     */
    private CimOrginfoWeb convertOrgRecommendToCimOrginfo(CimOrgRecommendRequest request) {
    	CimOrginfoWeb orgInfo = new CimOrginfoWeb();
    	if(request != null){
    		orgInfo.setOrgNumber(request.getOrgNumber());
    		orgInfo.setTop(request.getTop());
    		orgInfo.setRecommend(request.getRecommend());
    		orgInfo.setHomepageSort(request.getHomepageSort());
    	}
		return orgInfo;
	}

	/**
   	 * 校验机构是否存在
   	 * @param req
   	 * @param head
   	 * @return
   	 * @throws Exception
   	 */
    @RequestMapping(value="/checkOrgExist")
   	@RequestLogging("校验机构是否存在")
    @ResponseBody
   	public Object checkOrgExist(String orgNumber){
       	LOGGER.debug("校验机构是否存在请求参数 orgNumber = {}" ,orgNumber);
       	CimOrginfoWeb orgInfo = null;
   		if(null != orgNumber){
   			 orgNumber = String.format("OPEN_%s_WEB",orgNumber);
   			 orgInfo = cimOrginfoService.findWebOrgInfo(orgNumber); //WEB端 平台信息详情
   		}
   		if(orgInfo == null){
   			return new ResponseResult(false, "此机构不存在！");
   		}else{
   			return new ResponseResult(true, "此机构已存在！");
   		}
   	}
       
    
    /**
     * 更新机构
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("更新机构信息")
    public Object updateCimOrgInfo(@RequestBody CimOrginfoRequest request,HttpSession session) {
    	//机构更新请求参数 CimOrginfoRequest = {"homepageSort":"1","orgNumber":"OPEN_CESHILIQI_WEB","recommend":"1","top":"11"}
    		LOGGER.debug("机构更新请求参数 CimOrginfoRequest = {}",JSON.toJSONString(request));
       		long start = System.currentTimeMillis();
       		StringBuilder logsb = new StringBuilder();
       		ResponseResult result = null;
       		try {
       			User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
       			CimOrginfoWeb orginfo = convertToCimOrginfo(request,"edit");
       			orginfo.setOrgUpdater(user.getUsername()); //获取修改人用户名
       			cimOrginfoService.updateOrgFullInfo(orginfo);
       			result = new ResponseResult(true, "更新机构信息成功！");
       			logsb.append("更新机构信息 success");
       		} catch (Exception e) {
       			logsb.append("更新机构信息 fail");
       			LOGGER.error("更新机构信息失败！", e);
       			result = new ResponseResult(false, "更新机构信息失败！");
       		}
       		long end = System.currentTimeMillis();
       		logsb.append("更新机构信息总耗时 |totaltime=").append(end - start).append("ms");
       		LOGGER.info(logsb.toString());
       		return result;
    		
    }
    
    /**
   	 * 新增机构
   	 * @return
   	 */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
   	@ResponseBody
   	@RequestLogging("新增机构") 
   	public Object addCimOrgInfo(@RequestBody CimOrginfoRequest request,HttpSession session){
    	LOGGER.debug("新增机构请求参数 CimOrginfoRequest = {}",JSON.toJSONString(request));
   		long start = System.currentTimeMillis();
   		StringBuilder logsb = new StringBuilder();
   		ResponseResult result = null;
   		try {
   			User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
   			CimOrginfoWeb orginfo = convertToCimOrginfo(request,"add");
   			orginfo.setOrgCreator(user.getUsername()); //获取创建人用户名
   			//插入机构完整信息
   			cimOrginfoService.insertOrgFullInfo(orginfo, user.getUsername());
   			result = new ResponseResult(true, "新增机构成功！");
   			logsb.append("新增机构 success");
   		} catch (Exception e) {
   			logsb.append("新增机构 fail");
   			LOGGER.error("新增机构失败！", e);
   			result = new ResponseResult(false, "新增机构失败！");
   		}
   		long end = System.currentTimeMillis();
   		logsb.append("新增机构总耗时 |totaltime=").append(end - start).append("ms");
   		LOGGER.info(logsb.toString());
   		return result;
   	}
    
    /**
   	 * 删除团队成员
   	 * @return
   	 */
    @RequestMapping(value = "/deleteTeam", method = RequestMethod.POST)
   	@ResponseBody
   	@RequestLogging("删除团队成员") 
   	public Object deleteTeam(Long id,String orgNumber,HttpSession session){
    	LOGGER.debug("删除团队成员请求参数 id = {}",JSON.toJSONString(id));
   		long start = System.currentTimeMillis();
   		StringBuilder logsb = new StringBuilder();
   		ResponseResult result = null;
   		try {
   			
   			//删除团队成员
   			if(null != id && id > 0){
   				int deleteRows = cimOrgMemberinfoService.delete(id);
   				if(deleteRows > 0){
   					User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
   					CimOrginfoWeb org = new CimOrginfoWeb();
   					org.setOrgNumber(orgNumber);
   					org.setOrgUpdater(user.getUsername()); //更新操作用户
   					org.setUpdateTime(new Date()); //更新时间
   					cimOrginfoService.updateByOrgNumber(org); //更新机构表信息
   					result = new ResponseResult(true, "删除此团队成员成功！");
   					logsb.append("删除此团队成员成功！ success");
   				}
   			}
   		} catch (Exception e) {
   			logsb.append("删除此团队成员失败！ fail");
   			LOGGER.error("删除此团队成员失败！", e);
   			result = new ResponseResult(false, "删除此团队成员失败！");
   		}
   		long end = System.currentTimeMillis();
   		logsb.append("删除此团队成员总耗时 |totaltime=").append(end - start).append("ms");
   		LOGGER.info(logsb.toString());
   		return result;
   	}
    
    /**
     * 封装请求参数
     * @author yalin 
     * @date 2016年8月10日 上午10:34:58  
     * @param req
     * @return
     */
    private CimOrginfoWeb convertToCimOrginfo(CimOrginfoRequest req,String handleMethod){
    	CimOrginfoWeb org = new CimOrginfoWeb();
		if(req != null){
			org.setId(req.getId()); //机构主键id
			org.setStatus(req.getStatus()); 
			org.setName(req.getOrgName()); //机构名称
			org.setGrade(req.getGrade()); //机构评级
			org.setOrgFeeRatio(req.getOrgFeeRatio()); //机构佣金
			org.setUpTime(req.getUpTime());//上线时间
			if(handleMethod.equals("add")){
				org.setOrgNumber(String.format("OPEN_%s_WEB",req.getOrgNumber())); //机构编码占位符  OPEN_xxxxx_WEB
				//org.setStatus(0);//新增状态时默认不上线,  合作状态.0-合作结束，1-合作中' 2-待上线
				org.setCreateTime(new Date()); //机构录入时间
				//org.setOrgAccount(req.getOrgAccount());	//机构后台账户  不能编辑
			}
			if(handleMethod.equals("edit")){
				org.setOrgAccount(req.getOrgAccount());	//机构后台账户
				org.setOrgNumber(req.getOrgNumber());
				org.setUpdateTime(new Date());  //机构更新时间
			}
			if(StringUtils.isNotBlank(req.getOrgPassword())){
				org.setOrgPassword(ApplicationUtils.sha256Hex(req.getOrgPassword())); //机构密码  sha256加密
			}
			org.setContext(req.getContext()); //机构背景
			org.setCapital(req.getCapital());	//注册资本
			org.setCity(req.getCity());	//所在城市
			org.setIcpFiling(req.getIcpFiling());	//ICP备案
			org.setRepresentative(req.getRepresentative()); //机构法人
			org.setContact(req.getContact());	//机构联系电话
			org.setRemark(req.getRemark()); //备注
			org.setHomepageSort(req.getHomepageSort());	//机构首页推荐排名
			org.setRecommend(req.getRecommend());	//是否推荐机构
			org.setTop(req.getTop());	//机构列表排名
			org.setTrusteeship(req.getTrusteeship()); //资金托管
			org.setOrgUrl(req.getOrgUrl()); //机构官网的url
			org.setOrgProductUrl(req.getOrgProductUrl()); //平台产品跳转地址
			org.setOrgUsercenterUrl(req.getOrgUsercenterUrl()); //平台用户中心跳转地址
			org.setOrgBindUserUrl(req.getOrgBindUserUrl()); //绑定用户地址
			org.setOrgUserbalanceUrl(req.getOrgUserbalanceUrl()); //用户资产余额查询接口
			org.setOrgUserExistUrl(req.getOrgUserExistUrl()); //机构用户是否存在校验接口
			org.setPlatformIco(req.getPlatformImg()); //机构首页推荐logo
			org.setPlatformlistIco(req.getPlatformListImg()); //机构列表logo
			org.setPlatformDetailImg(req.getPlatformDetailImg()); //机构详情图片
			org.setBusinessLicense(req.getBusinessLicenseImg());  //机构营业执照
			org.setOrgSecurity(req.getOrgSecurity()); //安全保障
			org.setOrgProfile(req.getOrgProfile()); //平台简介
			org.setTeams(req.getTeams()); //团队信息
			org.setOrgIsstaticproduct(req.getOrgIsstaticproduct()); //是否虚拟机构(1：是 ,0：否)
			org.setOrgAdvertisement(req.getOrgAdvertisement()); //pc端广告图片
			org.setOrgAdvertisementUrl(req.getOrgAdvertisementUrl());//pc端广告图片跳转链接
			org.setOrgInvestigationReport(req.getOrgInvestigationReport()); //机构考查报告名称
			org.setOrgInvestigationReportUrl(req.getOrgInvestigationReportUrl()); //机构考查报告名称下载地址
			org.setOrgMargin(req.getOrgMargin()); //机构缴纳的保证金
			
			StringBuilder tags = new StringBuilder();  //机构标签拼接
			
			if(StringUtils.isNotBlank(req.getContext())){
				tags.append(req.getContext()+"系,");
			}
			if(StringUtils.isNotBlank(req.getTrusteeship()) && !req.getTrusteeship().startsWith("无")){
				tags.append("资金托管,");
			}
			if(req.getOrgIsstaticproduct() != null && req.getOrgIsstaticproduct() == 0){ //机构是否进行技术对接
				tags.append("账户直通");
			}
			org.setOrgTag(tags.toString());//机构标签 (多个以英文逗号分隔)
			org.setOrgProductTag(req.getOrgProductTag()); //T呗产品预设标签(多个以英文逗号分隔)
			org.setOrgPlannerProductTag(req.getOrgPlannerProductTag()); //猎财端产品预设标签(多个以英文逗号分隔)
			org.setOrgInvestTag(req.getOrgInvestTag()); //投呗端平台自定义标签(多个以英文逗号分隔)
			org.setOrgPlannerTag(req.getOrgPlannerTag()); //猎财端平台自定义标签(多个以英文逗号分隔)
			org.setOrgAdvantage(req.getOrgAdvantage()); //机构亮点(多个以英文逗号分隔)
			org.setOrgGrayStatus(req.getOrgGrayStatus()); //机构灰度状态(0:否，1:是)
			org.setOrgNewestImg(req.getOrgNewestImg()); //最新入驻平台图片(PC端)
			org.setOrgJoinType(req.getOrgJoinType()); //对接的机构类型 (0:移动+PC端，1:移动端，2:PC端)
			
			org.setPcPlatformImg(req.getPcPlatformImg()); //平台主页logo(PC端)
			org.setPcPlatformListImg(req.getPcPlatformListImg()); //平台列表logo(PC端)
			org.setPcPlatformDetailImg(req.getPcPlatformDetailImg()); //平台详情图片(PC端)
			org.setOrgDebentureTransfer(req.getOrgDebentureTransfer()); //债券转让(PC端)
			org.setOrgBidSecurity(req.getOrgBidSecurity()); //投标保障(PC端)
			org.setOrgSecurityMode(req.getOrgSecurityMode()); //保障模式(PC端)
			org.setDiffFeeRatio(req.getDiffFeeRatio()); //级差佣金率
			org.setCooperationEndUrl(req.getCooperationEndUrl()); //机构合作结束跳转地址
			/**
			 *  富文本拼接
			 */
			String prefix = "<section style=\"padding:0 20px;\">";
			String endfix = "</section>";
			
			if(StringUtils.isNotBlank(req.getOrgWebsiteRecords()) && !req.getOrgWebsiteRecords().startsWith(prefix)){
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(prefix);
				stringBuilder.append(req.getOrgWebsiteRecords());
				stringBuilder.append(endfix);
				req.setOrgWebsiteRecords(stringBuilder.toString());
			}
			org.setOrgWebsiteRecords(req.getOrgWebsiteRecords()); //网站备案详情(富文本PC端)
			
			if(StringUtils.isNotBlank(req.getOrgContactDetails()) && !req.getOrgContactDetails().startsWith(prefix)){
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(prefix);
				stringBuilder.append(req.getOrgContactDetails());
				stringBuilder.append(endfix);
				req.setOrgContactDetails(stringBuilder.toString());
			}
			
			org.setOrgContactDetails(req.getOrgContactDetails()); //机构联系方式详情(富文本PC端)
			
			if(StringUtils.isNotBlank(req.getOrgInvestStrategy()) && !req.getOrgInvestStrategy().startsWith(prefix)){
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(prefix);
				stringBuilder.append(req.getOrgInvestStrategy());
				stringBuilder.append(endfix);
				req.setOrgInvestStrategy(stringBuilder.toString());
				
			}
			//设置投资攻略
			org.setOrgInvestStrategy(req.getOrgInvestStrategy());
			
			org.setOrgHonor(req.getOrgHonor());
			
			/**
		     *	猎财攻略  富文本拼接
		     */
			if(StringUtils.isNotBlank(req.getOrgPlannerStrategy()) && !req.getOrgPlannerStrategy().startsWith(prefix)){
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(prefix);
				stringBuilder.append(req.getOrgPlannerStrategy());
				stringBuilder.append(endfix);
				req.setOrgPlannerStrategy(stringBuilder.toString());
				
			}
			//设置理财攻略
			org.setOrgPlannerStrategy(req.getOrgPlannerStrategy());
			
			//封装机构图片
			List<CimOrgPicture> pictures = this.pictureHandle(handleMethod, req.getOrgNumber(),req.getOrgEnvironmentPicture(),req.getOrgPaperPicture(),req.getOrgHonorPicture(),req.getOrgPcEnvironmentPicture(),req.getOrgPcPaperPicture(),req.getOrgPcHonorPicture());
			
			org.setOrgPictures(pictures); //保存图片
			
		}
		return org;
		
	}
    
    /**
     * 跳到机构收费模式页面
     * @return
     */
    @RequestMapping(value="/toOrgFeeView",method=RequestMethod.GET)
    @RequestLogging("跳到机构收费模式页面")
	public ModelAndView toOrgFeeView(String orgNumber){
    	LOGGER.debug("跳到机构收费模式页面请求参数 orgNumber = {}" ,orgNumber);
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("orgNumber",orgNumber); //机构编码
    	//根据机构编码查询机构收费模式
    	List<CimOrgFeeRuleDetail> orgFeeList = cimOrgFeeRecordService.queryOrgFeeInfo(orgNumber); //[]
    	if(orgFeeList.isEmpty() && orgFeeList.size() == 0){
    		modelAndView.setViewName("cimorginfo/cimorgfeerecord-add");
    	}else{
    		modelAndView.addObject("orgFeeList",orgFeeList);
    		for(CimOrgFeeRuleDetail feeRuleDetail : orgFeeList){
    			if(feeRuleDetail.getFeeTypeCode().equals("cpa")){ //判断是否为cpa收费模式
    				//为cpa时查询机构的限投金额和限投期限
    				CimOrginfoWeb orgFeeInfo = cimOrginfoService.queryOrgFeeInfo(orgNumber);
    				modelAndView.addObject("orgFeeInfo",orgFeeInfo);
    				modelAndView.addObject("cpaFeeTypeCode",feeRuleDetail.getFeeTypeCode());
    				if(feeRuleDetail.getFeeAttr().equals("fixed")){ //cpa根据新投资人固定费用
    					modelAndView.addObject("cpaFeeAttr",feeRuleDetail.getFeeAttr());
    					modelAndView.addObject("cpaFeeVal",feeRuleDetail.getFeeVal());
    					modelAndView.addObject("feeRecordId",feeRuleDetail.getFeeRecordId()); //收费模式记录表主键id
    				}else if(feeRuleDetail.getFeeAttr().equals("propertion")){ //cpa根据首投金额比例
    					modelAndView.addObject("cpaFeeAttr",feeRuleDetail.getFeeAttr());
    					modelAndView.addObject("cpaFeeRatio",feeRuleDetail.getFeeRatio());
    					modelAndView.addObject("feeRecordId",feeRuleDetail.getFeeRecordId()); //收费模式记录表主键id
    				}else if(feeRuleDetail.getFeeAttr().equals("float_fixed")){ //cpa根据首投金额区间
    					modelAndView.addObject("cpaFeeAttr",feeRuleDetail.getFeeAttr());
    				}
    				break;
    			}
    		}
    		for(CimOrgFeeRuleDetail feeRuleDetail : orgFeeList){
    			if(feeRuleDetail.getFeeTypeCode().equals("cps")){ //判断是否为cps收费模式
    				modelAndView.addObject("cpsFeeTypeCode",feeRuleDetail.getFeeTypeCode());
    				if(feeRuleDetail.getFeeAttr().equals("year_propertion")){ //cps根据产品期限
    					modelAndView.addObject("cpsFeeAttr",feeRuleDetail.getFeeAttr());
    				}else if(feeRuleDetail.getFeeAttr().equals("month_amount_propertion")){ //cps根据月销售总额
    					modelAndView.addObject("cpsFeeAttr",feeRuleDetail.getFeeAttr());
    				}
    				break;
    			}
    		}
    		modelAndView.setViewName("cimorginfo/cimorgfeerecord-edit");
    	}
		return modelAndView;
	}
    
    /**
   	 * 新增机构收费模式
   	 * @return
   	 */
    @RequestMapping(value = "/addOrgFee", method = RequestMethod.POST)
   	@ResponseBody
   	@RequestLogging("新增机构收费模式") 
   	public Object addCimOrgFee(@RequestBody CimOrgFeeRequest request,HttpSession session){
    	LOGGER.debug("新增机构收费模式请求参数 CimOrgFeeRequest = {}",JSON.toJSONString(request));
   		long start = System.currentTimeMillis();
   		StringBuilder logsb = new StringBuilder();
   		ResponseResult result = null;
   		try {
   			User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
   			request.setCreator(user.getUsername());//获取系统当前登录用户
   			CimOrginfoWeb cimOrginfo = new CimOrginfoWeb();
   			List<CimOrgFeeRecord> feeRecordList = convertToAddCimOrgFeeRecord(request,"add",cimOrginfo); //封装请求参数
   			//插入数据到机构收费记录表并更新机构表
   			cimOrgFeeRecordService.insertBatchFee(feeRecordList,cimOrginfo);
   			result = new ResponseResult(true, "新增机构收费模式成功！");
   			logsb.append("新增机构收费模式 success");
   		} catch (Exception e) {
   			logsb.append("新增机构收费模式 fail");
   			LOGGER.error("新增机构收费模式失败！", e);
   			result = new ResponseResult(false, "新增机构收费模式失败！");
   		}
   		long end = System.currentTimeMillis();
   		logsb.append("新增机构收费模式总耗时 |totaltime=").append(end - start).append("ms");
   		LOGGER.info(logsb.toString());
   		return result;
   	}
    
    /**
   	 * 更新机构收费模式
   	 * @return
   	 */
    @RequestMapping(value = "/updateOrgFee", method = RequestMethod.POST)
   	@ResponseBody
   	@RequestLogging("更新机构收费模式") 
   	public Object updateCimOrgFee(@RequestBody CimOrgFeeRequest request,HttpSession session){
    	LOGGER.debug("更新机构收费模式请求参数 CimOrgFeeRequest = {}",JSON.toJSONString(request));
   		long start = System.currentTimeMillis();
   		StringBuilder logsb = new StringBuilder();
   		ResponseResult result = null;
   		try {
   			User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
   			request.setUpdater(user.getUsername());//获取系统当前用户
   			CimOrginfoWeb cimOrginfo = new CimOrginfoWeb();
   			List<CimOrgFeeRecord> feeRecordList = convertToEditCimOrgFeeRecord(request,"edit",cimOrginfo); //封装请求参数
   			//更新数据到机构收费记录表并更新机构表
   			cimOrgFeeRecordService.updateBatchFee(feeRecordList,cimOrginfo,request);
   			result = new ResponseResult(true, "更新机构收费模式成功！");
   			logsb.append("更新机构收费模式 success");
   		} catch (Exception e) {
   			logsb.append("更新机构收费模式 fail");
   			LOGGER.error("更新机构收费模式失败！", e);
   			result = new ResponseResult(false, "更新机构收费模式失败！");
   		}
   		long end = System.currentTimeMillis();
   		logsb.append("更新机构收费模式总耗时 |totaltime=").append(end - start).append("ms");
   		LOGGER.info(logsb.toString());
   		return result;
   	}
    
    /**
   	 * 删除机构收费模式记录
   	 * @return
   	 */
    @RequestMapping(value = "/deleteOrgFee", method = RequestMethod.POST)
   	@ResponseBody
   	@RequestLogging("删除机构收费模式区间记录") 
   	public Object deleteOrgFee(Long id,HttpSession session){
    	LOGGER.debug("删除机构收费模式区间记录 id = {}",JSON.toJSONString(id));
   		long start = System.currentTimeMillis();
   		StringBuilder logsb = new StringBuilder();
   		ResponseResult result = null;
   		try {
   			//删除机构收费模式区间记录
   			if(null != id && id > 0){
   				int deleteRows = cimOrgFeeRecordService.delete(id);
   				if(deleteRows > 0){
   					//User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
   					result = new ResponseResult(true, "删除机构收费模式区间记录成功！");
   					logsb.append("删除机构收费模式区间记录成功！ success");
   				}
   			}
   		} catch (Exception e) {
   			logsb.append("删除机构收费模式区间记录失败！ fail");
   			LOGGER.error("删除机构收费模式区间记录失败！", e);
   			result = new ResponseResult(false, "删除机构收费模式区间记录失败！");
   		}
   		long end = System.currentTimeMillis();
   		logsb.append("删除机构收费模式区间记录总耗时 |totaltime=").append(end - start).append("ms");
   		LOGGER.info(logsb.toString());
   		return result;
   	}
    
    /**
     * 封装编辑收费模式请求参数
     * @author yalin 
     * @date 2016年9月19日 下午2:36:32  
     * @param request
     * @param string
     * @return
     */
	private List<CimOrgFeeRecord> convertToEditCimOrgFeeRecord(CimOrgFeeRequest request,String method,CimOrginfoWeb cimOrginfo) {
		
		List<CimOrgFeeRecord> feeRecordList = new ArrayList<CimOrgFeeRecord>();
		CimOrgFeeRecord feeRecord = new CimOrgFeeRecord();
		if(method.equals("add")){
			feeRecord.setCreator(request.getCreator());
			feeRecord.setCreattime(new Date());
		}else if(method.equals("edit")){
			feeRecord.setUpdater(request.getUpdater());
			feeRecord.setUpdatetime(new Date());
		}
		
		cimOrginfo.setOrgNumber(request.getOrgNumber()); //机构编号
		cimOrginfo.setUpdateTime(new Date()); //机构信息更新时间
		//cpa收费模式
		if(StringUtils.isNotBlank(request.getCpaFeeAttr()) && StringUtils.isNotBlank(request.getCpaFeeType()) && request.getCpaFeeType().equals("cpa")){
			//查询机构收费规则id
			int orgFeeRuleId = cimOrgFeeRecordService.queryOrgFeeRuleId(request.getCpaFeeAttr());
			
			cimOrginfo.setOrgAmountLimit(request.getOrgAmountLimit()); //限投金额
			cimOrginfo.setOrgInvestdeadlineLimit(request.getOrgInvestdeadlineLimit()); //限投期限
			cimOrginfo.setOrgFeeType(1); //机构收费类型 1:cpa-按投资人数量进行收费,2:cps-按投资金额进行收费'
			
			if(request.getCpaFeeAttr().equals("fixed")){ //按新投资人固定费用收取
				feeRecord.setId(request.getId()); //主键id
				feeRecord.setFeeRuleId(String.valueOf(orgFeeRuleId));
				feeRecord.setFeeVal(request.getFixedMoney()); //收费金额
				feeRecord.setOrgNumber(request.getOrgNumber());
				feeRecord.setRemark("cpa按新投资人固定费用");
				feeRecordList.add(feeRecord);
				
				/**
				 * 删除掉之前选中的区间数据
				 */
				if(request.getOrgFeeRecords() != null && request.getOrgFeeRecords().size() > 0){
					for(CimOrgFeeInterval feeInterval : request.getOrgFeeRecords()){ 
						if(feeInterval.getFeeVal() != null){ //cpa
							feeRecord = new CimOrgFeeRecord();
							feeRecord.setId(feeInterval.getFeeRecordId()); //主键id
							feeRecordList.add(feeRecord);
						}
						
					}
				}
				
			}else if(request.getCpaFeeAttr().equals("propertion")){ //按首投金额比例收取
				feeRecord.setId(request.getId()); //主键id
				feeRecord.setFeeRuleId(String.valueOf(orgFeeRuleId));
				feeRecord.setFeeRatio(request.getFixedMoneyRatio()); //收费比例
				feeRecord.setOrgNumber(request.getOrgNumber());
				feeRecord.setRemark("cpa按首投金额比例");
				feeRecordList.add(feeRecord);
				/**
				 * 删除掉之前选中的区间数据
				 */
				if(request.getOrgFeeRecords() != null && request.getOrgFeeRecords().size() > 0){
					for(CimOrgFeeInterval feeInterval : request.getOrgFeeRecords()){ 
						if(feeInterval.getFeeVal() != null){ //cpa
							feeRecord = new CimOrgFeeRecord();
							feeRecord.setId(feeInterval.getFeeRecordId()); //主键id
							feeRecordList.add(feeRecord);
						}
						
					}
				}
			}else if(request.getCpaFeeAttr().equals("float_fixed")){ //按首投金额区间收取
				if(request.getOrgFeeRecords() != null && request.getOrgFeeRecords().size() > 0){
					for(CimOrgFeeInterval feeInterval : request.getOrgFeeRecords()){ 
						if(feeInterval.getFeeVal() != null){ //cpa
							feeRecord = new CimOrgFeeRecord();
							feeRecord.setId(feeInterval.getFeeRecordId()); //主键id
							feeRecord.setCreator(request.getCreator());
							feeRecord.setCreattime(new Date());
							feeRecord.setFeeRuleId(String.valueOf(orgFeeRuleId));
							feeRecord.setOrgNumber(request.getOrgNumber());
							feeRecord.setFeeVal(feeInterval.getFeeVal()); //区间收费金额
							feeRecord.setIntervalMinVal(feeInterval.getIntervalMinVal()); //首投金额最小值
							feeRecord.setIntervalMaxVal(feeInterval.getIntervalMaxVal()); //首投金额最大值
							feeRecord.setIntervalUnit("元"); //收费区间单位:首投金额元,产品期限天,月销售额万
							feeRecord.setRemark("cpa按首投金额区间");
							feeRecordList.add(feeRecord);
						}
						
					}
				}
			}
			
		}
		
		//cps收费模式
		if(StringUtils.isNotBlank(request.getCpsFeeAttr()) && StringUtils.isNotBlank(request.getCpsFeeType()) && request.getCpsFeeType().equals("cps")){
			//查询机构收费规则id
			int orgFeeRuleId = cimOrgFeeRecordService.queryOrgFeeRuleId(request.getCpsFeeAttr());
			cimOrginfo.setOrgFeeType(2); //机构收费类型 1:cpa-按投资人数量进行收费,2:cps-按投资金额进行收费'
			
			if(request.getCpsFeeAttr().equals("year_propertion")){ //cps按产品期限收取
				if(request.getOrgFeeRecords() != null && request.getOrgFeeRecords().size() > 0){
					for(CimOrgFeeInterval feeInterval : request.getOrgFeeRecords()){
						if(feeInterval.getMoneyRatio() != null ){ //cps
							feeRecord = new CimOrgFeeRecord();
							feeRecord.setId(feeInterval.getFeeRecordId()); //主键id
							feeRecord.setCreator(request.getCreator());
							feeRecord.setCreattime(new Date());
							feeRecord.setFeeRuleId(String.valueOf(orgFeeRuleId));
							feeRecord.setOrgNumber(request.getOrgNumber());
							feeRecord.setFeeRatio(feeInterval.getMoneyRatio()); //收费比例
							feeRecord.setIntervalMinVal(feeInterval.getIntervalMinVal()); //产品期限最小值
							feeRecord.setIntervalMaxVal(feeInterval.getIntervalMaxVal()); //产品期限最大值
							feeRecord.setIntervalUnit("天"); //收费区间单位:首投金额元,产品期限天,月销售额万
							feeRecord.setRemark("cps按产品期限收取");
							feeRecordList.add(feeRecord);
						}
					}
				}
			}else if(request.getCpsFeeAttr().equals("month_amount_propertion")){ //cps按月销售总额收取
				if(request.getOrgFeeRecords() != null && request.getOrgFeeRecords().size() > 0){
					for(CimOrgFeeInterval feeInterval : request.getOrgFeeRecords()){
						if(feeInterval.getMoneyRatio() != null ){ //cps
							feeRecord = new CimOrgFeeRecord();
							feeRecord.setId(feeInterval.getFeeRecordId()); //主键id
							feeRecord.setCreator(request.getCreator());
							feeRecord.setCreattime(new Date());
							feeRecord.setFeeRuleId(String.valueOf(orgFeeRuleId));
							feeRecord.setOrgNumber(request.getOrgNumber());
							feeRecord.setFeeRatio(feeInterval.getMoneyRatio()); //收费比例
							if(feeInterval.getIntervalMinVal() != null){
								//feeRecord.setIntervalMinVal(feeInterval.getIntervalMinVal().multiply(new BigDecimal(10000.00))); //月销售额最小值(万元转换成元)
								feeRecord.setIntervalMinVal(feeInterval.getIntervalMinVal()); //月销售额最小值
							}
							if(feeInterval.getIntervalMaxVal() != null){
								//feeRecord.setIntervalMaxVal(feeInterval.getIntervalMaxVal().multiply(new BigDecimal(10000.00))); //月销售额最大值(万元转换成元)
								feeRecord.setIntervalMaxVal(feeInterval.getIntervalMaxVal()); //月销售额最大值(万元转换成元)
							}
							feeRecord.setIntervalUnit("元"); //收费区间单位:首投金额元,产品期限天,月销售额万
							feeRecord.setRemark("cps按月销售总额收取");
							feeRecordList.add(feeRecord);
						}
						
					}
				}
			}
		}
		
		//cpa/cps同时选中按cps收费
		if(StringUtils.isNotBlank(request.getCpaFeeType()) && StringUtils.isNotBlank(request.getCpsFeeType())){
			if(request.getCpaFeeType().equals("cpa") && request.getCpsFeeType().equals("cps")){
				cimOrginfo.setOrgFeeType(2); //机构收费类型 1:cpa-按投资人数量进行收费,2:cps-按投资金额进行收费'
			}
		}
		
		return feeRecordList;
	}
    
    /**
     * 封装新增收费模式请求参数
     * @author yalin 
     * @date 2016年9月19日 下午2:36:32  
     * @param request
     * @param string
     * @return
     */
	private List<CimOrgFeeRecord> convertToAddCimOrgFeeRecord(CimOrgFeeRequest request,String method,CimOrginfoWeb cimOrginfo) {
		List<CimOrgFeeRecord> feeRecordList = new ArrayList<CimOrgFeeRecord>();
		CimOrgFeeRecord feeRecord = new CimOrgFeeRecord();
		if(method.equals("add")){
			feeRecord.setCreator(request.getCreator());
			feeRecord.setCreattime(new Date());
		}else if(method.equals("edit")){
			feeRecord.setUpdater(request.getUpdater());
			feeRecord.setUpdatetime(new Date());
		}
		
		cimOrginfo.setOrgNumber(request.getOrgNumber()); //机构编号
		cimOrginfo.setUpdateTime(new Date()); //机构信息更新时间
		//cpa收费模式
		if(StringUtils.isNotBlank(request.getCpaFeeAttr()) && StringUtils.isNotBlank(request.getCpaFeeType()) && request.getCpaFeeType().equals("cpa")){
			//查询机构收费规则id
			int orgFeeRuleId = cimOrgFeeRecordService.queryOrgFeeRuleId(request.getCpaFeeAttr());
			
			cimOrginfo.setOrgAmountLimit(request.getOrgAmountLimit()); //限投金额
			cimOrginfo.setOrgInvestdeadlineLimit(request.getOrgInvestdeadlineLimit()); //限投期限
			cimOrginfo.setOrgFeeType(1); //机构收费类型 1:cpa-按投资人数量进行收费,2:cps-按投资金额进行收费'
			
			if(request.getCpaFeeAttr().equals("fixed")){ //按新投资人固定费用收取
				//feeRecord.setId(request.getId()); //主键id
				feeRecord.setFeeRuleId(String.valueOf(orgFeeRuleId));
				feeRecord.setFeeVal(request.getFixedMoney()); //收费金额
				feeRecord.setOrgNumber(request.getOrgNumber());
				feeRecord.setRemark("cpa按新投资人固定费用");
				feeRecordList.add(feeRecord);
			}else if(request.getCpaFeeAttr().equals("propertion")){ //按首投金额比例收取
				//feeRecord.setId(request.getId()); //主键id
				feeRecord.setFeeRuleId(String.valueOf(orgFeeRuleId));
				feeRecord.setFeeRatio(request.getFixedMoneyRatio()); //收费比例
				feeRecord.setOrgNumber(request.getOrgNumber());
				feeRecord.setRemark("cpa按首投金额比例");
				feeRecordList.add(feeRecord);
			}else if(request.getCpaFeeAttr().equals("float_fixed")){ //按首投金额区间收取
				if(request.getOrgFeeRecords() != null && request.getOrgFeeRecords().size() > 0){
					for(CimOrgFeeInterval feeInterval : request.getOrgFeeRecords()){ 
						if(feeInterval.getFeeVal() != null){ //cpa
							feeRecord = new CimOrgFeeRecord();
							//feeRecord.setId(feeInterval.getFeeRecordId()); //主键id
							feeRecord.setCreator(request.getCreator());
							feeRecord.setCreattime(new Date());
							feeRecord.setFeeRuleId(String.valueOf(orgFeeRuleId));
							feeRecord.setOrgNumber(request.getOrgNumber());
							feeRecord.setFeeVal(feeInterval.getFeeVal()); //区间收费金额
							feeRecord.setIntervalMinVal(feeInterval.getIntervalMinVal()); //首投金额最小值
							feeRecord.setIntervalMaxVal(feeInterval.getIntervalMaxVal()); //首投金额最大值
							feeRecord.setIntervalUnit("元"); //收费区间单位:首投金额元,产品期限天,月销售额万
							feeRecord.setRemark("cpa按首投金额区间");
							feeRecordList.add(feeRecord);
						}
						
					}
				}
			}
			
		}
		
		//cps收费模式
		if(StringUtils.isNotBlank(request.getCpsFeeAttr()) && StringUtils.isNotBlank(request.getCpsFeeType()) && request.getCpsFeeType().equals("cps")){
			//查询机构收费规则id
			int orgFeeRuleId = cimOrgFeeRecordService.queryOrgFeeRuleId(request.getCpsFeeAttr());
			cimOrginfo.setOrgFeeType(2); //机构收费类型 1:cpa-按投资人数量进行收费,2:cps-按投资金额进行收费'
			
			if(request.getCpsFeeAttr().equals("year_propertion")){ //cps按产品期限收取
				if(request.getOrgFeeRecords() != null && request.getOrgFeeRecords().size() > 0){
					for(CimOrgFeeInterval feeInterval : request.getOrgFeeRecords()){
						if(feeInterval.getMoneyRatio() != null ){ //cps
							feeRecord = new CimOrgFeeRecord();
							//feeRecord.setId(feeInterval.getFeeRecordId()); //主键id
							feeRecord.setCreator(request.getCreator());
							feeRecord.setCreattime(new Date());
							feeRecord.setFeeRuleId(String.valueOf(orgFeeRuleId));
							feeRecord.setOrgNumber(request.getOrgNumber());
							feeRecord.setFeeRatio(feeInterval.getMoneyRatio()); //收费比例
							feeRecord.setIntervalMinVal(feeInterval.getIntervalMinVal()); //产品期限最小值
							feeRecord.setIntervalMaxVal(feeInterval.getIntervalMaxVal()); //产品期限最大值
							feeRecord.setIntervalUnit("天"); //收费区间单位:首投金额元,产品期限天,月销售额万
							feeRecord.setRemark("cps按产品期限收取");
							feeRecordList.add(feeRecord);
						}
					}
				}
			}else if(request.getCpsFeeAttr().equals("month_amount_propertion")){ //cps按月销售总额收取
				if(request.getOrgFeeRecords() != null && request.getOrgFeeRecords().size() > 0){
					for(CimOrgFeeInterval feeInterval : request.getOrgFeeRecords()){
						if(feeInterval.getMoneyRatio() != null ){ //cps
							feeRecord = new CimOrgFeeRecord();
							//feeRecord.setId(feeInterval.getFeeRecordId()); //主键id
							feeRecord.setCreator(request.getCreator());
							feeRecord.setCreattime(new Date());
							feeRecord.setFeeRuleId(String.valueOf(orgFeeRuleId));
							feeRecord.setOrgNumber(request.getOrgNumber());
							feeRecord.setFeeRatio(feeInterval.getMoneyRatio()); //收费比例
							if(feeInterval.getIntervalMinVal() != null){
								//feeRecord.setIntervalMinVal(feeInterval.getIntervalMinVal().multiply(new BigDecimal(10000.00))); //月销售额最小值(万元转换成元)
								feeRecord.setIntervalMinVal(feeInterval.getIntervalMinVal());
							}
							if(feeInterval.getIntervalMaxVal() != null){
								//feeRecord.setIntervalMaxVal(feeInterval.getIntervalMaxVal().multiply(new BigDecimal(10000.00))); //月销售额最大值(万元转换成元)
								feeRecord.setIntervalMaxVal(feeInterval.getIntervalMaxVal());
							}
							feeRecord.setIntervalUnit("元"); //收费区间单位:首投金额元,产品期限天,月销售额万
							feeRecord.setRemark("cps按月销售总额收取");
							feeRecordList.add(feeRecord);
						}
						
					}
				}
			}
		}
		
		//cpa/cps同时选中按cps收费
		if(StringUtils.isNotBlank(request.getCpaFeeType()) && StringUtils.isNotBlank(request.getCpsFeeType())){
			if(request.getCpaFeeType().equals("cpa") && request.getCpsFeeType().equals("cps")){
				cimOrginfo.setOrgFeeType(2); //机构收费类型 1:cpa-按投资人数量进行收费,2:cps-按投资金额进行收费'
			}
		}
		
		return feeRecordList;
	}
	
	/**
   	 * 删除图片
   	 * @return
   	 */
    @RequestMapping(value = "/deletePicture", method = RequestMethod.POST)
   	@ResponseBody
   	@RequestLogging("删除图片") 
   	public Object deletePicture(Long key,HttpSession session){
    	LOGGER.debug("删除图片请求参数 id = {}",JSON.toJSONString(key));
   		long start = System.currentTimeMillis();
   		StringBuilder logsb = new StringBuilder();
   		ResponseResult result = null;
   		try {
   			//删除图片
   			if(null != key && key > 0){
   				int deleteRows = cimOrgPictureService.delete(key);
   				if(deleteRows > 0){
   					result = new ResponseResult(true, "删除图片成功！");
   					logsb.append("删除图片成功！ success");
   				}
   			}
   		} catch (Exception e) {
   			logsb.append("删除图片失败！ fail");
   			LOGGER.error("删除图片失败！", e);
   			result = new ResponseResult(false, "删除图片失败！");
   		}
   		long end = System.currentTimeMillis();
   		logsb.append("删除图片总耗时 |totaltime=").append(end - start).append("ms");
   		LOGGER.info(logsb.toString());
   		return result;
   	}
    
    /**
     * 封装机构图片
     * @author yalin 
     * @date 2016年11月23日 下午4:08:45  
     * @param orgEnvironmentPicture 移动端-环境图片
     * @param orgPaperPicture 移动端-其他证件图片
     * @param orgHonorPicture 移动端-荣誉证书
     * @param orgPcEnvironmentPicture PC端-环境图片
     * @param orgPcPaperPicture PC端-其他证件图片
     * @param orgPcHonorPicture PC端-荣誉证书
     * @return
     */
    private List<CimOrgPicture> pictureHandle(String handleMethod,String orgNumber,String orgEnvironmentPicture,String orgPaperPicture,String orgHonorPicture,String orgPcEnvironmentPicture,String orgPcPaperPicture,String orgPcHonorPicture){
    	List<CimOrgPicture> pictures = new ArrayList<CimOrgPicture>();
    	//封装移动端-机构环境照
    	if(StringUtils.isNotBlank(orgEnvironmentPicture)){
			if(orgEnvironmentPicture.contains(",")){
				String[] orgEnvironments = orgEnvironmentPicture.split(",");
				for(String ev : orgEnvironments){
					CimOrgPicture pic = new CimOrgPicture();
					//String.format("OPEN_%s_WEB",req.getOrgNumber())
					if(handleMethod.equals("add")){
						pic.setOrgNumber(String.format("OPEN_%s_WEB",orgNumber));
					}else{
						pic.setOrgNumber(orgNumber);
					}
					pic.setOrgPicture(ev);
					pic.setOrgPictureType(2);//机构图片类型,1-机构证件照，2-办公环境照，3-荣誉证书
					pic.setSource(1); //1:移动端图片,2:PC端图片
					pictures.add(pic);
				}
			}else{
				CimOrgPicture pic = new CimOrgPicture();
				//String.format("OPEN_%s_WEB",req.getOrgNumber())
				if(handleMethod.equals("add")){
					pic.setOrgNumber(String.format("OPEN_%s_WEB",orgNumber));
				}else{
					pic.setOrgNumber(orgNumber);
				}
				pic.setOrgPicture(orgEnvironmentPicture);
				pic.setOrgPictureType(2);//机构图片类型,1-机构证件照，2-办公环境照，3-荣誉证书
				pic.setSource(1); //1:移动端图片,2:PC端图片
				pictures.add(pic);
			}
			
			
		}
		
		//封装移动端-机构资格证
		if(StringUtils.isNotBlank(orgPaperPicture)){
			if(orgPaperPicture.contains(",")){
				String[] orgEnvironments = orgPaperPicture.split(",");
				for(String ev : orgEnvironments){
					CimOrgPicture pic = new CimOrgPicture();
					if(handleMethod.equals("add")){
						pic.setOrgNumber(String.format("OPEN_%s_WEB",orgNumber));
					}else{
						pic.setOrgNumber(orgNumber);
					}
					pic.setOrgPicture(ev);
					pic.setOrgPictureType(1);//机构图片类型,1-机构证件照，2-办公环境照，3-荣誉证书
					pic.setSource(1); //1:移动端图片,2:PC端图片
					pictures.add(pic);
				}
			}else{
				CimOrgPicture pic = new CimOrgPicture();
				//String.format("OPEN_%s_WEB",req.getOrgNumber())
				if(handleMethod.equals("add")){
					pic.setOrgNumber(String.format("OPEN_%s_WEB",orgNumber));
				}else{
					pic.setOrgNumber(orgNumber);
				}
				pic.setOrgPicture(orgPaperPicture);
				pic.setOrgPictureType(1);//机构图片类型,1-机构证件照，2-办公环境照，3-荣誉证书
				pic.setSource(1); //1:移动端图片,2:PC端图片
				pictures.add(pic);
			}
			
			
		}
		
		//封装移动端-机构荣誉证书
		if(StringUtils.isNotBlank(orgHonorPicture)){
			if(orgHonorPicture.contains(",")){
				String[] orgEnvironments = orgHonorPicture.split(",");
				for(String ev : orgEnvironments){
					CimOrgPicture pic = new CimOrgPicture();
					if(handleMethod.equals("add")){
						pic.setOrgNumber(String.format("OPEN_%s_WEB",orgNumber));
					}else{
						pic.setOrgNumber(orgNumber);
					}
					pic.setOrgPicture(ev);
					pic.setOrgPictureType(3);//机构图片类型,1-机构证件照，2-办公环境照，3-荣誉证书
					pic.setSource(1); //1:移动端图片,2:PC端图片
					pictures.add(pic);
				}
			}else{
				CimOrgPicture pic = new CimOrgPicture();
				//String.format("OPEN_%s_WEB",req.getOrgNumber())
				if(handleMethod.equals("add")){
					pic.setOrgNumber(String.format("OPEN_%s_WEB",orgNumber));
				}else{
					pic.setOrgNumber(orgNumber);
				}
				pic.setOrgPicture(orgHonorPicture);
				pic.setOrgPictureType(3);//机构图片类型,1-机构证件照，2-办公环境照，3-荣誉证书
				pic.setSource(1); //1:移动端图片,2:PC端图片
				pictures.add(pic);
			}
			
			
		}
		
		
		//封装PC端-机构环境照
    	if(StringUtils.isNotBlank(orgPcEnvironmentPicture)){
			if(orgPcEnvironmentPicture.contains(",")){
				String[] orgEnvironments = orgPcEnvironmentPicture.split(",");
				for(String ev : orgEnvironments){
					CimOrgPicture pic = new CimOrgPicture();
					//String.format("OPEN_%s_WEB",req.getOrgNumber())
					if(handleMethod.equals("add")){
						pic.setOrgNumber(String.format("OPEN_%s_WEB",orgNumber));
					}else{
						pic.setOrgNumber(orgNumber);
					}
					pic.setOrgPicture(ev);
					pic.setOrgPictureType(2);//机构图片类型,1-机构证件照，2-办公环境照，3-荣誉证书
					pic.setSource(2); //1:移动端图片,2:PC端图片
					pictures.add(pic);
				}
			}else{
				CimOrgPicture pic = new CimOrgPicture();
				//String.format("OPEN_%s_WEB",req.getOrgNumber())
				if(handleMethod.equals("add")){
					pic.setOrgNumber(String.format("OPEN_%s_WEB",orgNumber));
				}else{
					pic.setOrgNumber(orgNumber);
				}
				pic.setOrgPicture(orgPcEnvironmentPicture);
				pic.setOrgPictureType(2);//机构图片类型,1-机构证件照，2-办公环境照，3-荣誉证书
				pic.setSource(2); //1:移动端图片,2:PC端图片
				pictures.add(pic);
			}
			
			
		}
    	
    	//封装PC端-机构资格证
    	if(StringUtils.isNotBlank(orgPcPaperPicture)){
			if(orgPcPaperPicture.contains(",")){
				String[] orgEnvironments = orgPcPaperPicture.split(",");
				for(String ev : orgEnvironments){
					CimOrgPicture pic = new CimOrgPicture();
					if(handleMethod.equals("add")){
						pic.setOrgNumber(String.format("OPEN_%s_WEB",orgNumber));
					}else{
						pic.setOrgNumber(orgNumber);
					}
					pic.setOrgPicture(ev);
					pic.setOrgPictureType(1);//机构图片类型,1-机构证件照，2-办公环境照，3-荣誉证书
					pic.setSource(2); //1:移动端图片,2:PC端图片
					pictures.add(pic);
				}
			}else{
				CimOrgPicture pic = new CimOrgPicture();
				//String.format("OPEN_%s_WEB",req.getOrgNumber())
				if(handleMethod.equals("add")){
					pic.setOrgNumber(String.format("OPEN_%s_WEB",orgNumber));
				}else{
					pic.setOrgNumber(orgNumber);
				}
				pic.setOrgPicture(orgPcPaperPicture);
				pic.setOrgPictureType(1);//机构图片类型,1-机构证件照，2-办公环境照，3-荣誉证书
				pic.setSource(2); //1:移动端图片,2:PC端图片
				pictures.add(pic);
			}
			
			
		}
    	
    	//封装PC端-机构荣誉证书
		if(StringUtils.isNotBlank(orgPcHonorPicture)){
			if(orgPcHonorPicture.contains(",")){
				String[] orgEnvironments = orgPcHonorPicture.split(",");
				for(String ev : orgEnvironments){
					CimOrgPicture pic = new CimOrgPicture();
					if(handleMethod.equals("add")){
						pic.setOrgNumber(String.format("OPEN_%s_WEB",orgNumber));
					}else{
						pic.setOrgNumber(orgNumber);
					}
					pic.setOrgPicture(ev);
					pic.setOrgPictureType(3);//机构图片类型,1-机构证件照，2-办公环境照，3-荣誉证书
					pic.setSource(2); //1:移动端图片,2:PC端图片
					pictures.add(pic);
				}
			}else{
				CimOrgPicture pic = new CimOrgPicture();
				//String.format("OPEN_%s_WEB",req.getOrgNumber())
				if(handleMethod.equals("add")){
					pic.setOrgNumber(String.format("OPEN_%s_WEB",orgNumber));
				}else{
					pic.setOrgNumber(orgNumber);
				}
				pic.setOrgPicture(orgPcHonorPicture);
				pic.setOrgPictureType(3);//机构图片类型,1-机构证件照，2-办公环境照，3-荣誉证书
				pic.setSource(2); //1:移动端图片,2:PC端图片
				pictures.add(pic);
			}
			
		}
		
    	return pictures;
    }
    
    
    /**
   	 * 校验机构列表是否存在此排名
   	 * @param req
   	 * @param head
   	 * @return
   	 * @throws Exception
   	 */
    @RequestMapping(value="/checkOrgListSort")
   	@RequestLogging("校验机构列表是否存在此排名")
    @ResponseBody
   	public ResponseResult checkOrgListSort(Integer top,String orgNumber){
       	LOGGER.debug("校验机构列表是否存在此排名请求参数 top = {}" ,top);
       	int count = 0;
   		if(null != top && null != orgNumber){
   			CimOrginfoWeb orgInfo = cimOrginfoService.findWebOrgInfo(orgNumber); //WEB端 平台信息详情
   			if(top.equals(orgInfo.getTop())){
   				return new ResponseResult(false, "相同的排名不验证！");
   			}
   			count = cimOrginfoService.queryOrgListSort(top); //WEB端 平台信息详情
   		}
   		if(count > 0){
   			return new ResponseResult(true, "此机构列表排名已存在！");
   		}else{
   			return new ResponseResult(false, "此机构列表排名不存在！");
   		}
   	}
    
    
    /**
   	 * 校验机构首页推荐是否存在此排名
   	 * @param req
   	 * @param head
   	 * @return
   	 * @throws Exception
   	 */
    @RequestMapping(value="/checkOrgHomePageSort")
   	@RequestLogging("校验机构首页推荐是否存在此排名")
    @ResponseBody
   	public ResponseResult checkOrgHomePageSort(Integer homepageSort,String orgNumber){
       	LOGGER.debug("校验机构首页推荐是否存在此排名请求参数 top = {}" ,homepageSort);
       	int count = 0;
   		if(null != homepageSort && null != orgNumber){
   			CimOrginfoWeb orgInfo = cimOrginfoService.findWebOrgInfo(orgNumber); //WEB端 平台信息详情
   			if(homepageSort.equals(orgInfo.getHomepageSort())){
   				return new ResponseResult(false, "相同的首页推荐排名不验证！");
   			}
   			count = cimOrginfoService.queryOrgHomePageSort(homepageSort); //WEB端 平台信息详情
   		}
   		if(count > 0){
   			return new ResponseResult(true, "此机构首页推荐排名已存在！");
   		}else{
   			return new ResponseResult(false, "此机构首页推荐排名不存在！");
   		}
   	}
    
   
}
