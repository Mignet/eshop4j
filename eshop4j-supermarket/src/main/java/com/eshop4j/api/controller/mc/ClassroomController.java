package com.eshop4j.api.controller.mc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.eshop4j.api.response.mc.ClassroomPageListResponse;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.base.api.PaginatorRequest;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.mc.Classroom;
import com.eshop4j.web.service.ClassroomService;
import com.eshop4j.xoss.api.AppRequestHead;
import com.eshop4j.xoss.helper.JsonWebTokenHepler;
import com.eshop4j.xoss.interceptors.DateConvertEditor;
import com.eshop4j.xoss.util.AppResponseUtil;
import com.eshop4j.xoss.util.RequestLogging;

 /**
 * 
 * @描述： ClassroomController控制器
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年11月03日 11:39:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/api/classroom")
@RequestLogging("ClassroomController控制器")
public class ClassroomController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassroomController.class);
	
	private String errorCode = "-1";
	
	@Resource
    private JsonWebTokenHepler jsonWebTokenHepler;

	@Resource
	private ClassroomService classroomService;
	
	/**
	 * 课程列表(classroom.queryClassRoomList)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/queryClassroomList")
	@RequestLogging("课程列表")
	@ResponseBody
	public BaseResponse queryClassroomList(@Valid PaginatorRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
		Page<Classroom> page  = new Page<Classroom>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<Classroom> datas =  null;
		try {
			datas = classroomService.queryClassroomList(page,conditions);
		} catch (Exception e) {
			LOGGER.error("查询课程列表异常", e);
			return  new BaseResponse(errorCode,"查询课程列表失败");
		}
		return AppResponseUtil.getSuccessResponse(datas,ClassroomPageListResponse.class);
	}
	
	/**
	 * 课程详情(classroom.queryClassRoomDetail)
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryClassRoomDetail")
	@RequestLogging("课程详情")
	@ResponseBody
	public BaseResponse queryClassRoomDetail(String id,HttpServletResponse response) throws Exception {
		Classroom classRoom =  null;
		try {
			classRoom = classroomService.selectById(id);
		} catch (Exception e) {
			LOGGER.error("查询课程列表异常", e);
			return  new BaseResponse(errorCode,"查询课程列表失败");
		}
		return AppResponseUtil.getSuccessResponse(classRoom,ClassroomPageListResponse.class);
	}
	
	
	
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

  
	
}
