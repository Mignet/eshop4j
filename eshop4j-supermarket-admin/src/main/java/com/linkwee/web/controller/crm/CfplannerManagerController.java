package com.linkwee.web.controller.crm;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.export.ExportSupport;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.CrmCfpUpgradeRecord;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.crm.CfpManagerDetailResp;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.request.LcsListRequest;
import com.linkwee.web.response.CommonTCSResult;
import com.linkwee.web.service.CfplannerManagerService;
import com.linkwee.web.service.CrmCfpUpgradeRecordService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.xoss.constant.ResponseConstant;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.util.MD5;
import com.linkwee.xoss.util.RequestLogging;

@Controller
@RequestMapping("lcsList")
@RequestLogging("理财师列表")
public class CfplannerManagerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CfplannerManagerController.class);

	@Resource
	private ExportSupport exportSupport;

	@Resource
	private SysMsgService msgService;

	@Resource
	private SysConfigService sysConfigService;

	@Resource
	private CrmCfplannerService crmCfplannerService;

	@Resource
	private CrmInvestorService crmInvestorService;

	@Resource
	private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private CfplannerManagerService cfplannerManagerService;
	
	@Resource
	private CrmCfpUpgradeRecordService crmCfpUpgradeRecordService;
	
	@Resource
	private PushMessageHelper pushMessageHelper;
	@Resource
	private ConfigHelper configHelper;
	
	/**
	 * 理财师列表页面
	 * 
	 * @return
	 */
	@RequestMapping("lcsListPage")
	public String lcsListPage() {
		return "cfplanner/lcsList";
	}

	/**
	 * 获取理财师列表
	 * 
	 * @param lcsListRequest
	 * @return
	 */
	@RequestMapping("getLcsList")
	@ResponseBody
	@RequestLogging("获取理财师列表")
	public Object getLcsList(LcsListRequest lcsListRequest, DataTable dataTable) {
		DataTableReturn dataTableReturn = cfplannerManagerService.queryLcsList(dataTable, lcsListRequest);
		return dataTableReturn;
	}

	/**
	 * 获取理财师明细
	 * @param mobile
	 * @return
	 */
	@RequestMapping("getLcsDetail")
	@RequestLogging("获取理财师明细")
	public String getLcsDetail(String mobile, Model model) {
		if (StringUtils.isNotBlank(mobile)) {
			CfpManagerDetailResp lcsDetailResp = cfplannerManagerService.queryLcsDetail(mobile);
			if(lcsDetailResp != null && lcsDetailResp.getHeadImage() != null) {
				lcsDetailResp.setHeadImage(sysConfigService.getImageUrl(lcsDetailResp.getHeadImage()));
			}
			model.addAttribute("dtl", lcsDetailResp);
		}
		return "cfplanner/lcsDetail";
	}

	@RequestMapping("/remove/headimage")
	@RequestLogging("删除理财师头像")
	@ResponseBody
	public ResponseResult removeSaleUserHeadImage(String mobile) {
		ResponseResult result = new ResponseResult();
		try {
			if (StringUtils.isNotBlank(mobile) && cfplannerManagerService.removeCfplannerHeadImage(mobile)) {
				CrmCfplanner crmCfplanner = crmCfplannerService.queryCfplannerByMobile(mobile);
				String content = "您的头像图片不符合规定,己被删除!";
				SysMsg msg = new SysMsg();
				msg.setAppType(AppTypeEnum.CHANNEL.getKey());
				msg.setContent(content);
				msg.setStatus(0);// 发布
				msg.setUserNumber(crmCfplanner.getUserId());
				msg.setReadStatus(0);// 未读
				msgService.addMsg(msg);
				result.setIsFlag(true);
			} else {
				result.setIsFlag(false);
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			result.setIsFlag(false);
			result.setMsg("系统异常");
		}
		return result;
	}

	/**
	 * 退出理财师
	 * @param mobile
	 * @return
	 */
	@RequestMapping("exitLcs")
	@ResponseBody
	@RequestLogging("退出理财师")
	public Object exitLcs(String mobile) {
		ResponseResult result = new ResponseResult();
		try {
			if (StringUtils.isBlank(mobile)) {
				result.setIsFlag(false);
				result.setMsg("参数错误!");
				return result;
			}
			CrmCfplanner crmCfplanner = crmCfplannerService.queryCfplannerByMobile(mobile);
			if (crmCfplanner == null) {
				result.setIsFlag(false);
				result.setMsg("理财师不存在!");
				return result;
			}
			if (cfplannerManagerService.hasCustomerOrTeam(crmCfplanner.getUserId())) {
				result.setIsFlag(false);
				result.setMsg("该账号已产生客户或团队数据，不允许取消理财师身份!");
				return result;
			}
			if (crmCfplanner.getParentId() == null) {
				result.setIsFlag(false);
				result.setMsg("该账号无上级理财师，不允许取消理财师身份!");
				return result;
			} 
			// 退出理财师
			cfplannerManagerService.exitLcs(crmCfplanner);
			result.setIsFlag(true);
		} catch (Exception e) {
			result.setIsFlag(false);
			result.setMsg("系统异常!");
			LOGGER.error("退出理财师异常， " + e);
		}
		return result;
	}

	/**
	 * 登录密码重置
	 * @param reqeust
	 * @param mobile
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value = "resetpwd")
	@ResponseBody
	@RequestLogging("密码重置")
	public Object resetPwd(HttpServletRequest reqeust, @RequestParam String mobile, @RequestParam String newPwd) {

		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(newPwd)) {
			return new ResponseResult(ResponseConstant.FAILURE, "参数错误");
		}
		CrmCfplanner crmCfplanner = crmCfplannerService.queryCfplannerByMobile(mobile);
		if (crmCfplanner == null) {
			return new ResponseResult(ResponseConstant.FAILURE, "查询客户信息错误");
		}
		ResponseResult result = new ResponseResult();
		try {
			CrmUserInfo crmUserInfo = new CrmUserInfo();
			crmUserInfo.setPassword(MD5.crypt(newPwd));
			crmUserInfo.setUserId(crmCfplanner.getUserId());
			crmUserInfoService.updateByUserId(crmUserInfo);
			//站内信
			String content = String.format("亲爱的%s,您的密码信息已于%s由管理员更新,敬请留意",
					StringUtils.isNotBlank(crmCfplanner.getUserName()) ? crmCfplanner.getUserName() : crmCfplanner.getMobile(),
					DateUtils.getCurrentDate());
			SysMsg msg = new SysMsg();
			msg.setAppType(AppTypeEnum.CHANNEL.getKey());
			msg.setStatus(0);
			msg.setUserNumber(crmCfplanner.getUserId());
			msg.setContent(content);
			msgService.addMsg(msg);
			result.setIsFlag(true);
		} catch (Exception e) {
			result.setIsFlag(false);
		}
		return result;

	}

	/**
	 * 更改上级
	 * @param reqeust
	 * @param mobile
	 * @param parentMobile
	 * @param changeType
	 * @return
	 */
	@RequestMapping(value = "changeParent")
	@ResponseBody
	@RequestLogging("更改上级")
	public Object changeParent(HttpServletRequest reqeust, @RequestParam String mobile,
			@RequestParam String parentMobile, @RequestParam String changeType) {
		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(changeType)) {
			return new ResponseResult(ResponseConstant.FAILURE, "参数错误");
		}
		CrmCfplanner parent = null;
		if (StringUtils.isNotBlank(changeType) && "1".equals(changeType)) {
			if (StringUtils.isBlank(parentMobile)) {
				return new ResponseResult(ResponseConstant.FAILURE, "新上级理财师号码不正常");
			}
			parent = crmCfplannerService.queryCfplannerByMobile(parentMobile);
			if (parent == null) {
				return new ResponseResult(ResponseConstant.FAILURE, "更换失败，新上级理财师不存在");
			}
		}
		CrmCfplanner crmCfplanner = crmCfplannerService.queryCfplannerByMobile(mobile);
		if (crmCfplanner == null) {
			return new ResponseResult(ResponseConstant.FAILURE, "查询用户信息错误");
		}
		ResponseResult result = new ResponseResult();
		try {
			// 推送消息 要在解绑之前查询相关的信息
			// 该理财师相关信息
			CrmCfplanner saleUserInfo = crmCfplanner;
			// 原先上级理财师相关信息
			CrmCfplanner saleUserInfoTop = null;
			if(crmCfplanner != null &&  crmCfplanner.getUserId() != null){
				saleUserInfoTop = crmCfplannerService.queryParentByUserId(crmCfplanner.getUserId());
			}
			// 现在绑定理财师相关信息
			CrmCfplanner saleUserInfoTopNew = parent;

			//更改上级
			cfplannerManagerService.changeParent(mobile, parentMobile, changeType, crmCfplanner);
			result.setIsFlag(true);

			// 发送消息
			// 上级理财师
			if (StringUtils.isEmpty(saleUserInfo.getUserName())) {
				saleUserInfo.setUserName("");
			}
			if(saleUserInfoTop != null ){
				String nameStrlcs = saleUserInfo.getUserName()+saleUserInfo.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
				String lcsContent = String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_LCS_UNBUNDLING), nameStrlcs);
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("customUrlKey", SmsTypeEnum.LCSJBSJSUCCESS.getMsg());
				CommonTCSResult khjblcsBL = pushMessageHelper.pushMessageAsyn(AppTypeEnum.CHANNEL,SmsTypeEnum.LCSJBSJSUCCESS,
						saleUserInfoTop.getUserId(), "团队成员解除关系",  lcsContent,
						map1, true);
				LOGGER.info("理财师更改上级推送原先上级消息：{}, {}", lcsContent, khjblcsBL.getCode() == 0 ? "推送成功" : "推送失败");
			}
			
			// 该理财师
			if (saleUserInfoTop != null && StringUtils.isEmpty(saleUserInfoTop.getUserName())) {
				saleUserInfoTop.setUserName("");
			}
			String nameStrjf = "";
			if(saleUserInfoTop != null){
				nameStrjf =  saleUserInfoTop.getUserName() + saleUserInfoTop.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
			}
			String jfContent = null;
			if (StringUtils.isNotBlank(changeType) && "1".equals(changeType)) {
				String nameStrjfNew = saleUserInfoTopNew.getUserName() + saleUserInfoTopNew.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
				jfContent = String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_LCS_BUNDLING), nameStrjf, nameStrjfNew);
			} else {
				jfContent = String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_LCS_UNBUNDLING), nameStrjf);
			}
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("customUrlKey", SmsTypeEnum.LCSJBXJSUCCESS.getMsg());
			CommonTCSResult khjbjfBL = pushMessageHelper.pushMessageAsyn(AppTypeEnum.CHANNEL,
					SmsTypeEnum.LCSJBXJSUCCESS, saleUserInfo.getUserId(), "上级理财师变更", jfContent,
					 map2, true);
			LOGGER.info("理财师更改上级推送下级消息：{}, {}", jfContent, khjbjfBL.getCode() == 0 ? "推送成功" : "推送失败");
		} catch (Exception e) {
			LOGGER.error("更改上级错误" + e);
			result.setIsFlag(false);
			result.setMsg("系统异常");
		}
		return result;
	}

	/**
	 * 禁止登录90天
	 * @param reqeust
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "disabledLogin")
	@ResponseBody
	@RequestLogging("禁止登录90天")
	public Object disabledLogin(HttpServletRequest reqeust, @RequestParam String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return new ResponseResult(ResponseConstant.FAILURE, "参数错误");
		}
		CrmCfplanner crmCfplanner = crmCfplannerService.queryCfplannerByMobile(mobile);
		if (crmCfplanner == null) {
			return new ResponseResult(ResponseConstant.FAILURE, "用户不存在");
		}
		ResponseResult result = new ResponseResult();
		try {
			CrmCfplanner crmCfplannerForUpdate = new CrmCfplanner();
			crmCfplannerForUpdate.setDisabledLoginTime(new Date());
			crmCfplannerForUpdate.setUserId(crmCfplanner.getUserId());
			crmCfplannerService.updateByUserId(crmCfplannerForUpdate);
			result.setIsFlag(true);
		} catch (Exception e) {
			LOGGER.error("禁止登录90天" + e);
			result.setIsFlag(false);
			result.setMsg("系统异常");
		}
		return result;
	}
	
	/**
	 * 查询理财师变更记录
	 * @param mobile
	 * @Author Libin
	 * @return
	 * @throws Exception
     */
	@RequestMapping("/querycfphistory/{userId}")
    public ModelAndView queryCFPhistory(@PathVariable(value = "userId") String userId) throws Exception{
		ModelAndView modelAndView  = new ModelAndView("cfplanner/cfplanner_history");
		CrmCfpUpgradeRecord crmCfpUpgradeRecord = new CrmCfpUpgradeRecord();
		crmCfpUpgradeRecord.setUserId(userId);
		List<CrmCfpUpgradeRecord> list = crmCfpUpgradeRecordService.selectListByCondition(crmCfpUpgradeRecord);
		modelAndView.addObject("queryList",list);
		return modelAndView;
	}

	public String fixphoneNmber(String msg) {
		return msg.substring(0, msg.length() - 12) + msg.substring(msg.length() - 5);
	}
	
	/**
     *理财师-团队列表清单
     * @return
     * @throws Exception
     */
    @RequestLogging("团队列表清单")
    @RequestMapping(value = "/teamlist")
    public ModelAndView findCFPTeamInfo(String mobile) throws Exception{
        ModelAndView modelAndView = new ModelAndView("cfplanner/team-list");
        CfpManagerDetailResp detailResp = cfplannerManagerService.queryLcsDetail(mobile);
        modelAndView.addObject("cfp",detailResp);
        return modelAndView;
    }

    /**
     * 理财师-团队列表清单数据
     * @param searchtext
     * @param dataTable
     * @return
     * @throws Exception
     */
   @RequestLogging("团队数据列表清单")
    @RequestMapping("/teamlist_json")
    @ResponseBody
    public DataTableReturn  findCFPTeamInfoJson(String searchtext,String mobile,DataTable dataTable) throws Exception{
	   CfpManagerDetailResp detailResp =null;
        if(StringUtils.isNotBlank(mobile)){
            detailResp = cfplannerManagerService.queryLcsDetail(mobile);
            detailResp.setSearchText(searchtext);
        }
        return cfplannerManagerService.queryCfpTeamList(detailResp,dataTable);
    }

    /**
     *理财师-客户列表清单
     * @return
     * @throws Exception
     */
    @RequestLogging("客户列表清单")
    @RequestMapping(value ="/customelist/{mobile}")
    public ModelAndView  findCFPCustomerInfo(@PathVariable(value = "mobile") String mobile) throws Exception{
    	CfpManagerDetailResp lcsDetailResp = cfplannerManagerService.queryLcsDetail(mobile);
        ModelAndView modelAndView = new ModelAndView("cfplanner/customer-list");
        modelAndView.addObject("mobile",mobile);
        modelAndView.addObject("cfp",lcsDetailResp);
        return modelAndView;
    }

    /**
     * 理财师-客户列表清单数据
     * @param searchtext  = mobile || name
     * @return
     * @throws Exception
     */
    @RequestLogging("客户列表数据清单")
    @RequestMapping(value ="/customelist_json")
    @ResponseBody
    public DataTableReturn findCFPCustomerInfoJson(String mobile,String searchtext,DataTable dataTable) throws Exception{
    	CfpManagerDetailResp detailResp = null;
        if(StringUtils.isNotBlank(mobile)){
            detailResp = cfplannerManagerService.queryLcsDetail(mobile);
            detailResp.setSearchText(searchtext);
        }
        return cfplannerManagerService.queryCfpCustomerProfitList(detailResp,dataTable);
    }
    
	/**
	 * 理财师图表页面
	 */
	@RequestMapping("dataViewPage")
	@RequestLogging("数据概览页面")
	public String initPage(Model model){
		model.addAttribute("data",cfplannerManagerService.getLcsDateStaticCount());
		String start = DateUtils.format(DateUtils.subDay(new Date(), 7),"yyyy-MM-dd");
		String end = DateUtils.format(DateUtils.subDay(new Date(), 1),"yyyy-MM-dd");
		model.addAttribute("start",start);
		model.addAttribute("end",end);
		return "cfplanner/lcsDataView";
	}
	
	/**
	 * 理财师图表数据
	 */
	@RequestMapping("getLcsDataView")
	@ResponseBody
	@RequestLogging("查阅折线图报表")
	public Object getLcsDataView(String start,String end){
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(start)){
			map.put("startDate", DateUtils.parse(start,DateUtils.FORMAT_SHORT));
		}else{
			map.put("startDate", DateUtils.subDay(new Date(), 7));
		}
		if(StringUtils.isNotBlank(end)){
			map.put("endDate", DateUtils.parse(end,DateUtils.FORMAT_SHORT));
		}else{
			map.put("endDate", DateUtils.subDay(new Date(), 1));
		}
		map.put("data", cfplannerManagerService.getLcsDataStatic(map));
		return map;
	}
	
	
}
