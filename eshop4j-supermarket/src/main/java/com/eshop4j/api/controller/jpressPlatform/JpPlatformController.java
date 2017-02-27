package com.eshop4j.api.controller.jpressPlatform;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.SysConfig;
import com.eshop4j.web.model.jpressPlatform.JpPlatform;
import com.eshop4j.web.request.jpressPlatform.PlatformRequest;
import com.eshop4j.web.response.jpressPlatform.PlatformInvestorResponse;
import com.eshop4j.web.service.JpPlatformService;
import com.eshop4j.web.service.SysConfigService;
import com.eshop4j.xoss.api.AppRequestHead;
import com.eshop4j.xoss.interceptors.DateConvertEditor;
import com.eshop4j.xoss.util.AppResponseUtil;
import com.eshop4j.xoss.util.RequestLogging;

 /**
 * 
 * @描述： JpPlatformController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月31日 11:31:46
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/api/jpress/jpplatform")
@RequestLogging("JpPlatformController控制器")
public class JpPlatformController {

	private static final Logger logger = LoggerFactory.getLogger(JpPlatformController.class);

	@Resource
	private JpPlatformService jpPlatformService;
	@Resource
	private SysConfigService sysConfigService;
	
	/**
	 * 转换器
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
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
	@RequestLogging("网贷平台筛选条件")
	public BaseResponse platformHead(AppRequestHead head) throws Exception {
    	logger.debug("机构筛选条件:"+head);
		Map<String,Object> rlt = new HashMap<String,Object>();
		List<SysConfig> sysList = sysConfigService.querySysConfigByName("网贷平台筛选条件-");
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
		rlt.put("level", orgLevelList);
		rlt.put("profit", profitList);
		rlt.put("deadline", deadlineList);
		
		logger.debug("网贷平台筛选条件 | query success!");
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * 网贷平台-分页
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/pageList")
	@ResponseBody
	@RequestLogging("网贷平台列表")
	public BaseResponse newsPageList(@Valid PlatformRequest req,BindingResult result,AppRequestHead head){
    	
    	logger.debug("机构列表请求参数 OrgInfoRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
    	if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
    	Page<JpPlatform> page  = new Page<JpPlatform>(req.getPageIndex(),req.getPageSize()); //默认每页10条
    	Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
    	//机构级别
    	if(StringUtils.isNotBlank(req.getPlatformName())){
    		conditions.put("platformName", req.getPlatformName());
    	}
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
    	
    
    	
		PaginatorResponse<JpPlatform> orgdatas = jpPlatformService.queryPlatformList(page,conditions); //机构列表的分页信息
		
		//投资者
		return AppResponseUtil.getSuccessResponse(orgdatas,PlatformInvestorResponse.class);
	}
	




   
}
