package com.linkwee.web.controller.act;



import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.linkwee.act.redpacket.model.ActRepaymentRedpacketPool;
import com.linkwee.act.redpacket.service.ActRedpacketTemplateService;
import com.linkwee.act.redpacket.service.ActRepaymentRedpacketPoolService;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.User;
import com.linkwee.web.request.act.RedPacketInfoRequest;
import com.linkwee.web.request.act.RedPacketTemplateInfoRequest;
import com.linkwee.web.response.act.RedpacketTemplateListResponse;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;

 /**
 * 
 * @描述： ActRedpacketTemplateController控制器
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年12月22日 20:13:13
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "redpackettemplate")
@RequestLogging("ActRedpacketTemplateController控制器")
public class ActRedpacketTemplateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActRedpacketTemplateController.class);

	@Resource
	private ActRedpacketTemplateService actRedpacketTemplateService;
	
	@Resource
	private ActRepaymentRedpacketPoolService repaymentRedpacketPoolService;
	
	@Resource
	private CimOrginfoService cimOrginfoService; //机构服务
	
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
    @RequestMapping(value="page")
    public String initPage(Model model) {
    	CimOrginfo orginfo = new CimOrginfo();
    	orginfo.setOrgFeeType(1);
    	orginfo.setStatus(1);
    	List<CimOrginfo> orginfos = cimOrginfoService.selectListByCondition(orginfo);
    	List<Map<String, String>> cpaInfos = Lists.newLinkedList();
    	Map<String, String> info = null;
    	for (CimOrginfo cimOrginfo : orginfos) {
    		info = Maps.newHashMap();
    		info.put("platform", cimOrginfo.getName());
    		info.put("platformId", cimOrginfo.getOrgNumber());
    		cpaInfos.add(info);
    	}
    	
    	model.addAttribute("cpaMainPlatform", repaymentRedpacketPoolService.getMainPlatFormByModel(1));
    	model.addAttribute("cpa", cpaInfos);
    	
    	orginfo = new CimOrginfo();
    	orginfo.setOrgFeeType(2);
    	orginfo.setStatus(1);
    	orginfos = cimOrginfoService.selectListByCondition(orginfo);
    	List<Map<String, String>> cpsInfos = Lists.newLinkedList();
    	for (CimOrginfo cimOrginfo : orginfos) {
    		info = Maps.newHashMap();
    		info.put("platform", cimOrginfo.getName());
    		info.put("platformId", cimOrginfo.getOrgNumber());
    		cpsInfos.add(info);
    	}
    	
    	model.addAttribute("cpsMainPlatform", repaymentRedpacketPoolService.getMainPlatFormByModel(2));
    	model.addAttribute("cps", cpsInfos);
    	
    	return "redpacket/actredpackettemplate-page";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getActRedpacketTemplates(@RequestParam String  _dt_json) {
    	DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		return actRedpacketTemplateService.getRedpacketTemplateList(dataTable);
	}
    
    /**
     * 添加页面
     */
    @RequestMapping(value="addPage")
    public String getRedpacketAddPage(Model model) {
    	return "redpacket/actredpackettemplate-add-page";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	@RequestLogging("add操作")
	public Object add(@Valid RedPacketTemplateInfoRequest  redPacketInfo,BindingResult bindResult,HttpSession session) { 
    	if(ResponseUtil.existsParamsError(bindResult)) {
   	    	return ResponseUtil.getErrorParams(bindResult);
        }
    	try {
    		User user = (User) session.getAttribute("userInfo");
    		redPacketInfo.setOperator(user.getUsername());
    		actRedpacketTemplateService.insetRedpacketTemplate(redPacketInfo);
			return new ResponseResult(true,"添加成功");
		} catch (Exception e) {
			LOGGER.error("redpacketAdd exception : {}", e.getMessage());
		}
    	return new ResponseResult(false,"添加失败");
	}
    
    
    
    /**
     * 修改页面
     */
    @RequestMapping(value="{redpacketId}/editPage")
    public String  getRedpacketEditPage(@PathVariable("redpacketId")String redPacketTemplateId,Model model) {
    	try {
    		if(StringUtils.isNotBlank(redPacketTemplateId)){
    			RedPacketTemplateInfoRequest redPacketInfo = actRedpacketTemplateService.getRedPacketTemplateInfo(redPacketTemplateId);  			
    			model.addAttribute("redpacketId", redPacketTemplateId);
    			model.addAttribute("redpacket",  redPacketInfo);
    		}
		}catch (ServiceException e) {
			LOGGER.error("redpacketEdit exception : {}", e.getMessage());
			model.addAttribute("errorMgs",e.getMessage());
		}  catch (Exception e) {
			LOGGER.error("redpacketEdit exception : {}", e.getMessage());
			model.addAttribute("errorMgs","查询红包失败");
		}
    	return "redpacket/actredpackettemplate-edit-page";
    }
    
    @RequestMapping(value="edit")
    @ResponseBody
    @RequestLogging("编辑红包")
	public Object redpacketEdit(@Valid RedPacketTemplateInfoRequest redPacketInfo,BindingResult bindResult,HttpSession session) {
    	if(ResponseUtil.existsParamsError(bindResult)) {
   	    	return ResponseUtil.getErrorParams(bindResult);
        }
    	try {
    		if(StringUtils.isBlank(redPacketInfo.getRedpacketId()))return new ResponseResult(true,"红包不存在");  		
			User user = (User) session.getAttribute("userInfo"); 
    		redPacketInfo.setOperator(user.getUsername());
    		actRedpacketTemplateService.updateRedpacketTemplate(redPacketInfo);
			//redpacketService.updateRedpacket(redPacketInfo);
			return new ResponseResult(true,"更新成功");
		}catch (ServiceException e) {
			LOGGER.error("redpacketEdit exception : {}", e.getMessage());
			return new ResponseResult(false,e.getMessage());
		} catch (Exception e) {
			LOGGER.error("redpacketEdit exception : {}", e.getMessage());
		}
    	return new ResponseResult(false,"更新失败");
	}
    
    
    
    /**
     * 平台
     */
    @RequestMapping(value="platformPage")
    public String getplatformPage(@RequestParam("redpacketId")String redPacketTemplateId,Model model) {
    	try {
    		if(StringUtils.isNotBlank(redPacketTemplateId)){
    			ActRepaymentRedpacketPool repaymentRedpacketPool = new ActRepaymentRedpacketPool();
    			repaymentRedpacketPool.setRedpacketTemplateId(redPacketTemplateId);
    			repaymentRedpacketPool.setStatus(0);
    			List<ActRepaymentRedpacketPool> repaymentRedpacketPools =repaymentRedpacketPoolService.selectListByCondition(repaymentRedpacketPool);
    			String[] ids= null;
    			if(CollectionUtils.isEmpty(repaymentRedpacketPools)) {
    				ids =new String[0];
    			}else{
    				List<String> lists= Lists.newArrayListWithCapacity(repaymentRedpacketPools.size());
    				for (ActRepaymentRedpacketPool actRepaymentRedpacketPool : repaymentRedpacketPools) {
    					lists.add(actRepaymentRedpacketPool.getPlatformId());
					}
    				ids = lists.toArray(new String[lists.size()]);
    			}
    			model.addAttribute("redpacketId", redPacketTemplateId );
    			model.addAttribute("ids", JSONArray.toJSONString(ids) );
    		}
		}catch (ServiceException e) {
			LOGGER.error("redpacketEdit exception : {}", e.getMessage());
			model.addAttribute("errorMgs",e.getMessage());
		}  catch (Exception e) {
			LOGGER.error("redpacketEdit exception : {}", e.getMessage());
			model.addAttribute("errorMgs","查询红包失败");
		}
    	return "redpacket/platform-page";
    }
    
    /**
     * 平台
     */
    @RequestMapping(value="setMainPlatform")
    @ResponseBody
    public Object setMainPlatform(@RequestParam("platform")String platform,@RequestParam("model")Integer model,HttpSession session) {
    	
    	if(model==null)return new ResponseResult(false,"选择收费模式");
   
    	User user = (User) session.getAttribute("userInfo"); 
    	return repaymentRedpacketPoolService.setMainPlatFormByModel(platform, model, user.getUsername());
    }
    
    /**
     * 平台
     */
    @RequestMapping(value="getPlatform")
    @ResponseBody
    public Object getplatformPage(@RequestParam String  _dt_json) {
    	@SuppressWarnings("unchecked")
		Map<String, String> map = JsonUtils.fromJsonToObject(_dt_json, Map.class);
    	CimOrginfo orginfo = new CimOrginfo();
    	String model = map.get("model");
    	if(!StringUtils.isBlank(model))
    		orginfo.setOrgFeeType(Integer.parseInt(model));
    	orginfo.setStatus(1);
    	List<CimOrginfo> orginfos = cimOrginfoService.selectListByCondition(orginfo);
    	List<Map<String, String>> infos = Lists.newLinkedList();
    	Map<String, String> info = null;
    	for (CimOrginfo cimOrginfo : orginfos) {
    		info = Maps.newHashMap();
    		info.put("platform", cimOrginfo.getName());
    		info.put("platformId", cimOrginfo.getOrgNumber());
    		Integer modelType = cimOrginfo.getOrgFeeType();
    		info.put("model", ObjectUtils.equals(modelType, 1)?"cpa":"cps");
    		infos.add(info);
		}
    	
    	DataTableReturn tableReturn = new DataTableReturn();
		
		tableReturn.setData(infos);
    	return tableReturn;
    }
    
    @RequestMapping(value="bindingPlatform")
    @ResponseBody
    public Object bindingPlatform(@RequestParam("platformId") String  platformId,@RequestParam("model") Integer model,@RequestParam("status") Integer status,@RequestParam("redpacketId") String redPacketTemplateId,HttpSession session) throws Exception {
    	if(StringUtils.isBlank(redPacketTemplateId) || StringUtils.isBlank(platformId) || model==null /*|| ( !ObjectUtils.equals(model, 1) || !ObjectUtils.equals(model, 2) )*/ || status==null)return null;
    	
    	if( ObjectUtils.equals(status, 0)){
			RedPacketTemplateInfoRequest redPacketInfo = actRedpacketTemplateService.getRedPacketTemplateInfo(redPacketTemplateId); 
			User user = (User) session.getAttribute("userInfo"); 
			redPacketInfo.setOperator(user.getUsername());
    		repaymentRedpacketPoolService.savePlatFormRedpacket(redPacketTemplateId, platformId, model, redPacketInfo);
		}else if(ObjectUtils.equals(status, 1)){
			ActRepaymentRedpacketPool redpacketPool = new ActRepaymentRedpacketPool();
			redpacketPool.setPlatformId(platformId);
			redpacketPool.setModel(model);
			redpacketPool.setRedpacketTemplateId(redPacketTemplateId);
			redpacketPool.setStatus(0);
			ActRepaymentRedpacketPool result =  repaymentRedpacketPoolService.selectOne(redpacketPool);
			if(result==null ) return new ResponseResult(false,"操作失败");
			ActRepaymentRedpacketPool update = new ActRepaymentRedpacketPool();
			update.setId(result.getId());
			update.setStatus(1);
			update.setUpdateTime(new Date());
			User user = (User) session.getAttribute("userInfo"); 
			update.setOperator(user.getUsername());
			repaymentRedpacketPoolService.update(update);
		}
    	return new ResponseResult(true,"操作成功");
    }
	
    
}
