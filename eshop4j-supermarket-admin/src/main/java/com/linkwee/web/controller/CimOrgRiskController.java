package com.linkwee.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.web.model.CimOrgRisk;
import com.linkwee.web.model.User;
import com.linkwee.web.model.cim.CimOrgRiskDataTable;
import com.linkwee.web.model.cim.CimOrginfoBindSelect;
import com.linkwee.web.request.orgInfo.CimOrgRiskRequest;
import com.linkwee.web.service.CimOrgRiskService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： CimOrgRiskController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月22日 11:03:54
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "cim/cimorgrisk")
@RequestLogging("CimOrgRiskController控制器")
public class CimOrgRiskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgRiskController.class);

	@Resource
	private CimOrgRiskService cimOrgRiskService;
	@Resource
	private CimOrginfoService cimOrginfoService;
	
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
     * 查看列表
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)
    @RequestLogging("查看列表页面")
    public String cimOrgRisk(Model model) {
    	List<CimOrginfoBindSelect> orgList = cimOrginfoService.queryAllOrgByStatus(1);
    	model.addAttribute("orgList", orgList);
    	return "cimorgrisk/cimorgrisk-list";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getCimOrgRisks(@RequestParam String  _dt_json) {
		LOGGER.debug("CimOrgRisk list _dt_json={}", _dt_json);
		CimOrgRiskDataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, CimOrgRiskDataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = cimOrgRiskService.selectByDatatables(dataTable);
		return tableReturn;
	}
    
    /**
     * 跳到新增页面
     * @return
     */
    @RequestMapping(value="/toAdd",method=RequestMethod.GET)
    @RequestLogging("跳到机构风控信息新增页面")
	public ModelAndView toAddView(){
    	ModelAndView modelAndView = new ModelAndView("cimorgrisk/cimorgrisk-add");
    	//ModelAndView modelAndView = new ModelAndView("cimorgrisk/cimorgrisk-modal");
		List<CimOrginfoBindSelect> orgList = cimOrginfoService.queryAllOrgByStatus(1);//查询已经合作的机构
		modelAndView.addObject("orgList", orgList);
		//modelAndView.addObject("action", "add");
		return modelAndView;
	}
    
    /**
     * 跳到编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value="/toEdit",method=RequestMethod.GET)
    @RequestLogging("跳到机构风控信息编辑页面")
    public String toEditView(Integer id, ModelMap model) {
    	LOGGER.debug("机构风控信息编辑请求参数 id = {}" ,id);
		if(id != null){
			CimOrgRisk cimOrgRisk = cimOrgRiskService.queryOrgRiskInfo(id); //WEB端 机构动态信息详情
			List<CimOrginfoBindSelect> orgList = cimOrginfoService.queryAllOrgByStatus(1);//查询已经合作的机构
			model.addAttribute("orgList", orgList);
			model.addAttribute("cimOrgRisk",cimOrgRisk);
		}
		model.addAttribute("action","edit");
    	return "cimorgrisk/cimorgrisk-modal";
    }
    
    /**
   	 * 新增机构风控信息
   	 * @return
   	 */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
   	@ResponseBody
   	@RequestLogging("新增机构风控信息") 
   	public Object addOrgRiskInfo(CimOrgRiskRequest request,HttpSession session){
    	LOGGER.debug("新增机构风控信息请求参数 CimOrgRisk = {}",JSON.toJSONString(request));
   		long start = System.currentTimeMillis();
   		StringBuilder logsb = new StringBuilder();
   		ResponseResult result = null;
   		try {
   			User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
   			List<CimOrgRisk>  risks = this.convertToRiskList(request,user.getUsername());
   			//批量插入
   			cimOrgRiskService.insertBatchRisk(risks);
   			result = new ResponseResult(true, "新增机构风控信息成功！");
   			logsb.append("新增机构风控信息 success");
   		} catch (Exception e) {
   			logsb.append("新增机构风控信息 fail");
   			LOGGER.error("新增机构风控信息失败！", e);
   			result = new ResponseResult(false, "新增机构风控信息失败！");
   		}
   		long end = System.currentTimeMillis();
   		logsb.append("新增机构风控信息总耗时 |totaltime=").append(end - start).append("ms");
   		LOGGER.info(logsb.toString());
   		return result;
   	}
    
    /**
     * 封装请求参数
     * @author yalin 
     * @date 2016年12月7日 下午3:39:15  
     * @param request
     * @return
     */
    private List<CimOrgRisk> convertToRiskList(CimOrgRiskRequest request,String creator) {
    	List<CimOrgRisk> risks = new ArrayList<CimOrgRisk>();
    	if(request != null){
    		
    		if(request.getBackgroundScore() != null){
    			CimOrgRisk risk = new CimOrgRisk();
        		risk.setOrgName(request.getOrgName());
        		risk.setOrgNumber(request.getOrgNumber());
        		risk.setCreator(creator);
        		risk.setCreateTime(new Date());
        		risk.setSort(request.getSort());
        		risk.setIsshow(request.getIsshow());
    			risk.setIndicatorName("背景实力");
    			risk.setIndicatorScore(request.getBackgroundScore());
    			risks.add(risk);
    		}
    		
    		if(request.getInforDisclosureScore() != null){
    			CimOrgRisk risk = new CimOrgRisk();
        		risk.setOrgName(request.getOrgName());
        		risk.setOrgNumber(request.getOrgNumber());
        		risk.setCreator(creator);
        		risk.setCreateTime(new Date());
        		risk.setSort(request.getSort());
        		risk.setIsshow(request.getIsshow());
    			risk.setIndicatorName("信息披露");
    			risk.setIndicatorScore(request.getInforDisclosureScore());
    			risks.add(risk);
    		}
    		if(request.getRiskControlScore() != null){
    			CimOrgRisk risk = new CimOrgRisk();
        		risk.setOrgName(request.getOrgName());
        		risk.setOrgNumber(request.getOrgNumber());
        		risk.setCreator(creator);
        		risk.setCreateTime(new Date());
        		risk.setSort(request.getSort());
        		risk.setIsshow(request.getIsshow());
    			risk.setIndicatorName("风险控制");
    			risk.setIndicatorScore(request.getRiskControlScore());
    			risks.add(risk);
    		}
    		if(request.getRunPowerScore() != null){
    			CimOrgRisk risk = new CimOrgRisk();
        		risk.setOrgName(request.getOrgName());
        		risk.setOrgNumber(request.getOrgNumber());
        		risk.setCreator(creator);
        		risk.setCreateTime(new Date());
        		risk.setSort(request.getSort());
        		risk.setIsshow(request.getIsshow());
    			risk.setIndicatorName("运营能力");
    			risk.setIndicatorScore(request.getRunPowerScore());
    			risks.add(risk);
    		}
    		if(request.getUserExperienceScore() != null){
    			CimOrgRisk risk = new CimOrgRisk();
        		risk.setOrgName(request.getOrgName());
        		risk.setOrgNumber(request.getOrgNumber());
        		risk.setCreator(creator);
        		risk.setCreateTime(new Date());
        		risk.setSort(request.getSort());
        		risk.setIsshow(request.getIsshow());
    			risk.setIndicatorName("用户体验");
    			risk.setIndicatorScore(request.getUserExperienceScore());
    			risks.add(risk);
    		}
    		
    	}
		return risks;
	}

	/**
     * 更新机构风控信息
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("更新机构风控信息")
    public Object updateOrgDynamicInfo(CimOrgRisk request,HttpSession session) {
    		LOGGER.debug("机构风控信息更新请求参数 CimOrgRisk = {}",JSON.toJSONString(request));
       		long start = System.currentTimeMillis();
       		StringBuilder logsb = new StringBuilder();
       		ResponseResult result = null;
       		try {
       			User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
       			request.setUpdater(user.getUsername()); //获取修改人用户名
       			request.setUpdateTime(new Date());
       			cimOrgRiskService.update(request);
       			result = new ResponseResult(true, "更新机构风控信息成功！");
       			logsb.append("更新机构风控信息 success");
       		} catch (Exception e) {
       			logsb.append("更新机构风控信息 fail");
       			LOGGER.error("更新机构风控信息失败！", e);
       			result = new ResponseResult(false, "更新机构风控信息失败！");
       		}
       		long end = System.currentTimeMillis();
       		logsb.append("更新机构风控信息总耗时 |totaltime=").append(end - start).append("ms");
       		LOGGER.info(logsb.toString());
       		return result;
    		
    }
    
    /**
   	 * 删除机构风控信息
   	 * @return
   	 */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
   	@ResponseBody
   	@RequestLogging("删除机构风控信息") 
   	public Object deleteDynamic(Long rid){
    	LOGGER.debug("删除机构风控信息 rid = {}",JSON.toJSONString(rid));
   		long start = System.currentTimeMillis();
   		StringBuilder logsb = new StringBuilder();
   		ResponseResult result = null;
   		try {
   			//删除机构风控信息
   			if(null != rid && rid > 0){
   				int deleteRows = cimOrgRiskService.delete(rid);
   				if(deleteRows > 0){
   					result = new ResponseResult(true, "删除机构风控信息成功！");
   					logsb.append("删除机构风控信息成功！ success");
   				}
   			}
   		} catch (Exception e) {
   			logsb.append("删除机构风控信息失败！ fail");
   			LOGGER.error("删除机构风控信息失败！", e);
   			result = new ResponseResult(false, "删除机构风控信息失败！");
   		}
   		long end = System.currentTimeMillis();
   		logsb.append("删除机构风控信息总耗时 |totaltime=").append(end - start).append("ms");
   		LOGGER.info(logsb.toString());
   		return result;
   	}


}
