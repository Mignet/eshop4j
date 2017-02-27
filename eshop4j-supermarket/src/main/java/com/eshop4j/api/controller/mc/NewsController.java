package com.eshop4j.api.controller.mc;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop4j.api.request.DynamicNewsPageListRequest;
import com.eshop4j.api.request.NetloanNewsPageListRequest;
import com.eshop4j.api.request.mc.MsgDetailRequest;
import com.eshop4j.api.response.NetloanNewsResponse;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.base.api.ErrorResponse;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.EnumUtils;
import com.eshop4j.web.enums.NetloanNewsTypeEnum;
import com.eshop4j.web.model.SmDynamicNews;
import com.eshop4j.web.service.SmDynamicNewsService;
import com.eshop4j.xoss.api.AppRequestHead;
import com.eshop4j.xoss.helper.StringUtils;
import com.eshop4j.xoss.util.AppResponseUtil;
import com.eshop4j.xoss.util.AppUtils;
import com.eshop4j.xoss.util.RequestLogging;

/**
 * 
 * @描述：新闻动态
 *
 * @author hxb
 * @时间 2015年10月16日上午11:06:20
 *
 */
@Controller
@RequestMapping(value = "/api/dynamicnews")
@RequestLogging("新闻动态")
public class NewsController  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);
	
	@Resource
	private SmDynamicNewsService smDynamicNewsService;
	
	/**
	 * 新闻动态-分页
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("新闻动态列表")
	@RequestMapping("pageList")
	@ResponseBody
	public BaseResponse dynamicNewsPageList(DynamicNewsPageListRequest req,AppRequestHead head) throws Exception {		
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();		
		Page<SmDynamicNews> page  = new Page<SmDynamicNews>(req.getPageIndex(),req.getPageSize()); //默认每页10条
		Map<String,Object> conditions = new HashMap<String, Object>(); //查询条件
        conditions.put("appType", appType);
        conditions.put("typeCode", req.getTypeCode());       
		PaginatorResponse<SmDynamicNews> result = smDynamicNewsService.queryDynamicNews(page, conditions);
		LOGGER.info("新闻动态列表--查询完成");	
		 return AppResponseUtil.getSuccessResponse(result);
	}
	
	/**
	 * 新闻动态详情
	 * @Date 2016年1月25日
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("新闻动态详情")
	@RequestMapping("detail")
	@ResponseBody
	public BaseResponse detail(@Valid MsgDetailRequest req,BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		SmDynamicNews smDynamicNews = smDynamicNewsService.selectById(Long.parseLong(String.valueOf(req.getMsgId())));
		if(smDynamicNews == null){
			return new ErrorResponse("100002","参数错误");
		}else {
			return AppResponseUtil.getSuccessResponse(smDynamicNews);
		}
	}
	
	/**
	 * 新闻动态详情上下页
	 * @Date 2016年1月25日
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("新闻动态详情上下页")
	@RequestMapping("nearNews")
	@ResponseBody
	public BaseResponse nearNews(@Valid MsgDetailRequest req,BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		SmDynamicNews smDynamicNews = smDynamicNewsService.selectById(Long.parseLong(String.valueOf(req.getMsgId())));
		if(smDynamicNews == null){
			return new ErrorResponse("100002","参数错误");
		}
		Map<String, Object> resultMap = smDynamicNewsService.findNearNews(smDynamicNews);
		return AppResponseUtil.getSuccessResponse(resultMap);
		
	}
	
	@RequestLogging("网贷新闻分页")
	@RequestMapping("netloanNews/pageList")
	@ResponseBody
	public BaseResponse netloanNews(NetloanNewsPageListRequest req,AppRequestHead head){		
		Page<NetloanNewsResponse> page  = new Page<NetloanNewsResponse>(req.getPageIndex(),req.getPageSize()); //默认每页10条
		Map<String,Object> conditions = new HashMap<String, Object>(); //查询条件
		
		if(req.getTypeCode()!=null){
			String title = EnumUtils.getValueByKey(req.getTypeCode(), NetloanNewsTypeEnum.values());
			conditions.put("title", title);	
		}else{
			return new ErrorResponse("100002","参数错误");
		}
               
		PaginatorResponse<NetloanNewsResponse> result = smDynamicNewsService.queryNetloanNews(page, conditions);
		return AppResponseUtil.getSuccessResponse(result);
	}
	
	@RequestLogging("网贷新闻详情")
	@RequestMapping("netloanNews/detail")
	@ResponseBody
	public BaseResponse netloanNewsDetail(NetloanNewsPageListRequest req,AppRequestHead head){		
		Map<String,Object> conditions = new HashMap<String, Object>(); //查询条件
		
		if(req.getId()!=null){
			conditions.put("id", req.getId());	
		}else{
			return new ErrorResponse("100002","参数错误");
		}
               
		NetloanNewsResponse result = smDynamicNewsService.queryNetloanNewsDetail(conditions);
		return AppResponseUtil.getSuccessResponse(result);
	}

}
