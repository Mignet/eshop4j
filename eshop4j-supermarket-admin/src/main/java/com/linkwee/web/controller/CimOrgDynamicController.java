package com.linkwee.web.controller;

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
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.CimOrgDynamic;
import com.linkwee.web.model.User;
import com.linkwee.web.model.cim.CimOrgDynamicDataTable;
import com.linkwee.web.model.cim.CimOrginfoBindSelect;
import com.linkwee.web.request.orgInfo.CimOrgDynamicRequest;
import com.linkwee.web.service.CimOrgDynamicService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.constant.WebConstants;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述：平台动态
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月02日 14:59:21
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "cim/cimorgdynamic")
@RequestLogging("CimOrgDynamicController控制器")
public class CimOrgDynamicController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgDynamicController.class);

	@Resource
	private CimOrgDynamicService cimOrgDynamicService;
	
	@Resource
	private CimOrginfoService cimOrginfoService;
	
	@Resource
	private SysConfigService sysConfigService; //系统配置
	
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
    public String cimOrgDynamic(Model model) {
    	List<CimOrginfoBindSelect> orgList = cimOrginfoService.queryAllOrgByStatus(1);
    	model.addAttribute("orgList", orgList);
    	return "cimorgdynamic/cimorgdynamic-list";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getCimOrgDynamics(@RequestParam String  _dt_json) {
		LOGGER.debug("CimOrgDynamic list _dt_json={}", _dt_json);
		CimOrgDynamicDataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, CimOrgDynamicDataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = cimOrgDynamicService.selectByDatatables(dataTable);
		return tableReturn;
	}

    /**
     * 跳到新增页面
     * @return
     */
    @RequestMapping(value="/toAdd",method=RequestMethod.GET)
    @RequestLogging("跳到机构动态信息新增页面")
	public ModelAndView toAddView(){
    	ModelAndView modelAndView = new ModelAndView("cimorgdynamic/cimorgdynamic-view");
		String imgServerUrl = sysConfigService.getValuesByKey(WebConstants.IMAGE_SERVER_URL);//图片服务器地址
		List<CimOrginfoBindSelect> orgList = cimOrginfoService.queryAllOrgByStatus(1);//查询已经合作的机构
		modelAndView.addObject("orgList", orgList);
		modelAndView.addObject("img_server",imgServerUrl);
		modelAndView.addObject("action", "add");
		return modelAndView;
	}
    
    /**
     * 跳到编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value="/toEdit",method=RequestMethod.GET)
    @RequestLogging("跳到机构动态信息编辑页面")
    public String toEditView(Integer id, ModelMap model) {
    	LOGGER.debug("机构动态信息编辑请求参数 id = {}" ,id);
		String imgServerUrl = sysConfigService.getValuesByKey(WebConstants.IMAGE_SERVER_URL);//图片服务器地址
		if(id != null){
			CimOrgDynamic orgDynamicInfo = cimOrgDynamicService.queryOrgDynamicInfo(id); //WEB端 机构动态信息详情
			List<CimOrginfoBindSelect> orgList = cimOrginfoService.queryAllOrgByStatus(1);//查询已经合作的机构
			model.addAttribute("orgList", orgList);
			model.addAttribute("orgDynamicInfo",orgDynamicInfo);
		}
		model.addAttribute("img_server",imgServerUrl);
		model.addAttribute("action","edit");
    	return "cimorgdynamic/cimorgdynamic-view";
    }
    
    /**
     * 更新机构动态信息
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("更新机构动态信息")
    public Object updateOrgDynamicInfo(CimOrgDynamicRequest request,HttpSession session) {
    		LOGGER.debug("机构动态信息更新请求参数 CimOrginfoRequest = {}",JSON.toJSONString(request));
       		long start = System.currentTimeMillis();
       		StringBuilder logsb = new StringBuilder();
       		ResponseResult result = null;
       		try {
       			User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
       			CimOrgDynamic orginfo = convertToOrgDynamicInfo(request,"edit");
       			orginfo.setUpdater(user.getUsername()); //获取修改人用户名
       			cimOrgDynamicService.update(orginfo);
       			result = new ResponseResult(true, "更新机构动态信息成功！");
       			logsb.append("更新机构动态信息 success");
       		} catch (Exception e) {
       			logsb.append("更新机构动态信息 fail");
       			LOGGER.error("更新机构动态信息失败！", e);
       			result = new ResponseResult(false, "更新机构动态信息失败！");
       		}
       		long end = System.currentTimeMillis();
       		logsb.append("更新机构动态信息总耗时 |totaltime=").append(end - start).append("ms");
       		LOGGER.info(logsb.toString());
       		return result;
    		
    }
    
	/**
   	 * 新增机构动态信息
   	 * @return
   	 */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
   	@ResponseBody
   	@RequestLogging("新增机构动态信息") 
   	public Object addOrgDynamicInfo(CimOrgDynamicRequest request,HttpSession session){
    	LOGGER.debug("新增机构动态信息请求参数 CimOrginfoRequest = {}",JSON.toJSONString(request));
   		long start = System.currentTimeMillis();
   		StringBuilder logsb = new StringBuilder();
   		ResponseResult result = null;
   		try {
   			User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
   			CimOrgDynamic orginfo = convertToOrgDynamicInfo(request,"add");
   			orginfo.setCreator(user.getUsername()); //获取创建人用户名
   			//插入机构完整信息
   			cimOrgDynamicService.insert(orginfo);
   			result = new ResponseResult(true, "新增机构动态信息成功！");
   			logsb.append("新增机构动态信息 success");
   		} catch (Exception e) {
   			logsb.append("新增机构动态信息 fail");
   			LOGGER.error("新增机构动态信息失败！", e);
   			result = new ResponseResult(false, "新增机构动态信息失败！");
   		}
   		long end = System.currentTimeMillis();
   		logsb.append("新增机构动态信息总耗时 |totaltime=").append(end - start).append("ms");
   		LOGGER.info(logsb.toString());
   		return result;
   	}
    
    /**
   	 * 删除机构动态
   	 * @return
   	 */
    @RequestMapping(value = "/deleteDynamic", method = RequestMethod.POST)
   	@ResponseBody
   	@RequestLogging("删除机构动态") 
   	public Object deleteDynamic(Long id){
    	LOGGER.debug("删除机构动态 id = {}",JSON.toJSONString(id));
   		long start = System.currentTimeMillis();
   		StringBuilder logsb = new StringBuilder();
   		ResponseResult result = null;
   		try {
   			//删除机构收费模式区间记录
   			if(null != id && id > 0){
   				int deleteRows = cimOrgDynamicService.delete(id);
   				if(deleteRows > 0){
   					result = new ResponseResult(true, "删除机构动态成功！");
   					logsb.append("删除机构动态成功！ success");
   				}
   			}
   		} catch (Exception e) {
   			logsb.append("删除机构动态失败！ fail");
   			LOGGER.error("删除机构动态失败！", e);
   			result = new ResponseResult(false, "删除机构动态失败！");
   		}
   		long end = System.currentTimeMillis();
   		logsb.append("删除机构动态总耗时 |totaltime=").append(end - start).append("ms");
   		LOGGER.info(logsb.toString());
   		return result;
   	}
    
    /**
     * 封装机构动态信息请求参数
     * @author yalin 
     * @date 2016年11月8日 下午3:42:57  
     * @param req
     * @param method
     * @return
     */
    private CimOrgDynamic convertToOrgDynamicInfo(CimOrgDynamicRequest req,String method) {
    	CimOrgDynamic orgDynamic = new CimOrgDynamic();
		if(req != null){
			orgDynamic.setId(req.getId()); //机构动态信息主键id
			orgDynamic.setOrgName(req.getOrgName());
			orgDynamic.setOrgNumber(req.getOrgNumber());
			orgDynamic.setOrgTitle(req.getOrgTitle());
			orgDynamic.setOrgSummary(req.getOrgSummary());
			orgDynamic.setReleaseTime(req.getReleaseTime()); //发布时间
			if(req.getDynamicrdo().equals("orgDynamicUrl")){ //单选框被选中
				orgDynamic.setOrgDynamicUrl(req.getOrgDynamicUrl());
			}
			if(method.equals("add")){
				orgDynamic.setCreateTime(new Date());
			}
			if(method.equals("edit")){
				orgDynamic.setUpdateTime(new Date());
			}
			
			if(req.getDynamicrdo().equals("orgContent")){ //富文本单选框被选中
				/**
				 * 动态信息 富文本拼接
				 */
				String prefix = "<section style=\"padding:0 20px;\">";
				String endfix = "</section>";
				
				if(StringUtils.isNotBlank(req.getOrgContent()) && !req.getOrgContent().startsWith(prefix)){
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append(prefix);
					stringBuilder.append(req.getOrgContent());
					stringBuilder.append(endfix);
					req.setOrgContent(stringBuilder.toString());
					
				}
				//设置动态信息
				orgDynamic.setOrgContent(req.getOrgContent());
			}
			
			
		}
		return orgDynamic;
	}

}
