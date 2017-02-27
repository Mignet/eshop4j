package com.linkwee.web.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.mc.SmClassroom;
import com.linkwee.web.service.SmClassroomService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.constant.WebConstants;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.HtmlFilterUtil;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;

 /**
 * 
 * @描述： SmClassroomController控制器
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年11月04日 16:27:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "acc/smclassroom")
@RequestLogging("SmClassroomController控制器")
public class SmClassroomController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmClassroomController.class);

	@Resource
	private SmClassroomService smClassroomService;
	
	@Resource
	private SysConfigService systemConfigService; 
	
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

	
	@RequestMapping("/list_ajax")
	@ResponseBody
	public DataTableReturn getSmClassroomList(SmClassroom classroom, DataTable dataTable) throws Exception{
		dataTable.initOrders();
		DataTableReturn tableReturn = smClassroomService.selectByDatatables(classroom,dataTable);
		return tableReturn;
	}
	
    /**
     * 查看列表
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)
    @RequestLogging("查看列表页面")
    public String smClassroom(Model model) {
    	return "smclassroom/smclassroom-list";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getSmClassrooms(@RequestParam String  _dt_json) {
		LOGGER.debug("SmClassroom list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = smClassroomService.selectByDatatables(null,dataTable);
		return tableReturn;
	}


    
	/**
	 * 新增or修改
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public Object save(@Valid SmClassroom cr,BindingResult bindResult){
		if(ResponseUtil.existsParamsError(bindResult)) {
	    	return ResponseUtil.getErrorParams(bindResult);
        }
		long start = System.currentTimeMillis();
		if(cr.getAppType() == null || cr.getAppType()<=0){
			cr.setAppType(1);
		}
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
		cr.setTitle(HtmlFilterUtil.filterHtml(cr.getTitle()));
		cr.setImg(systemConfigService.getImageUrl(cr.getImg()));
		cr.setShareIcon(systemConfigService.getImageUrl(cr.getShareIcon()));
		try {
			if(cr.getId()!=null && cr.getId().longValue()>0){
				smClassroomService.updateClassroom(cr);
			}else{
				if(cr.getShowInx()==1){
					smClassroomService.overheadClassroom();
				}
				smClassroomService.insert(cr);
			}
			
		} catch (Exception e) {
			result = new ResponseResult(false, "操作失败");
		}
			
		result = new ResponseResult(true, "操作成功");
		logsb.append("smClassroomService SaveClassroom success");
		LOGGER.info(logsb.toString());
	
		long end = System.currentTimeMillis();
		logsb.append("|totaltime=").append(end - start);
		LOGGER.info(logsb.toString());
		return result;
	}
    
    /**
	 * 删除
	 * @return
	 */
	@RequestMapping("del")
	@ResponseBody
	public Object del(@RequestParam String id ){
		ResponseResult result = null;
		try {
			smClassroomService.delete(Long.parseLong(id));
		} catch (Exception e) {
			result = new ResponseResult(false, "删除失败");
		}
		result = new ResponseResult(true, "删除成功");
		return result;
	}
	
	/**
	 * 转编辑页
	 * @return
	 */
	@RequestMapping("toEdit")
	public String toUpdate(String id,Model model){
		String imgServerUrl = systemConfigService.getValuesByKey(WebConstants.IMAGE_SERVER_URL);
		model.addAttribute("img_server",imgServerUrl);
		if(StringUtils.isNotBlank(id)){
			model.addAttribute("classroom", smClassroomService.selectById(Long.parseLong(id)));
			model.addAttribute("actionType","edit");
		}
		return "smclassroom/classroom-edit";
	}
	
}
