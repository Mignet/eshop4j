package com.linkwee.api.controller.crm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.crm.PartnerDetailRequest;
import com.linkwee.api.request.crm.PartnerMonthSaleRequest;
import com.linkwee.api.request.crm.PartnerPageListRequest;
import com.linkwee.api.response.crm.MonthSaleListResponse;
import com.linkwee.api.response.crm.MonthSaleStatisticsResponse;
import com.linkwee.api.response.crm.PartnerDetailResponse;
import com.linkwee.api.response.crm.PartnerListResponse;
import com.linkwee.api.response.crm.PartnerSaleRecordResponse;
import com.linkwee.api.response.crm.PartnerStatisticsResponse;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.constant.ApiInvokeLogConstant;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.SysApiInvokeLog;
import com.linkwee.web.model.crm.MonthSaleListResp;
import com.linkwee.web.model.crm.MonthSaleStatisticsResp;
import com.linkwee.web.model.crm.PartnerDetailResp;
import com.linkwee.web.model.crm.PartnerListResp;
import com.linkwee.web.model.crm.PartnerSaleRecordResp;
import com.linkwee.web.model.crm.PartnerStatisticsResp;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.PartnerService;
import com.linkwee.web.service.SysApiInvokeLogService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;

/**
 * 团队
 * @author Mignet
 * @since 2014年5月28日 下午3:54:00
 **/
@Controller
@RequestMapping(value = "/api/personcenter/partner")
public class PartnerController extends BaseController{
	
    @Resource
    private CrmCfplannerService crmCfplannerService;

    @Resource
    private CrmInvestorService crmInvestorService;
    
    @Resource
    private CrmUserInfoService crmUserInfoService;
    
    @Resource
    private PartnerService partnerService;
    
    @Resource
	private SysApiInvokeLogService sysApiInvokeLogService;
    
	/**
	 * 团队信息统计
	 * @param head
	 * @return
	 */
	@RequestMapping("")
	@ResponseBody
	public BaseResponse partner(AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		PartnerStatisticsResp rlt = new PartnerStatisticsResp();
		rlt.setMyRcCount(partnerService.queryMyRcCount(userId));
		rlt.setChildrenRcCount(partnerService.queryChildrenRcCount(userId));
		return AppResponseUtil.getSuccessResponse(rlt,PartnerStatisticsResponse.class);
	}
	
	/**
	 * 团队列表
	 * @param head
	 * @return
	 */
	@RequestMapping("pageList")
	@ResponseBody
	public BaseResponse partnerPageList(PartnerPageListRequest req,AppRequestHead head) throws Exception {
		logger.info("团队列表, pageRequest={}", JSONObject.toJSONString(req));
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Map<String ,Object> query = new HashMap<String ,Object>();
		query.put("userId", userId);
		if(StringUtils.isNotBlank(req.getName())){
			query.put("userName",req.getName());
		}
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_PERSONCENTER_PARTNER, userId, appType);
		Date date = null;
		if(apiInvokeLog!=null){
			date = apiInvokeLog.getChgTime();
		}else{
			date = DateUtils.parse("1990-01-01",DateUtils.FORMAT_SHORT);
		}
		query.put("date",date);
		if(req.getSort() != null || req.getOrder() != null){
			query.put("sort",req.getSort());
			query.put("order", req.getOrder());
		}
		Page<PartnerListResp> page = new Page<PartnerListResp>(req.getPageIndex(), req.getPageSize());
		PaginatorSevResp<PartnerListResp> rlt = partnerService.queryPartnerList(query,page);
		sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.NAME_PERSONCENTER_PARTNER, userId,appType);
		return AppResponseUtil.getSuccessResponse(rlt, PartnerListResponse.class);		
	}
	
	/**
	 * 团队成员详情
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	@ResponseBody
	public BaseResponse partnerDetail(@Valid PartnerDetailRequest req, BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		PartnerDetailResp rlt = partnerService.queryDetailResp(req.getUserId());
		return AppResponseUtil.getSuccessResponse(rlt,PartnerDetailResponse.class);
	}
	
	
	/**
	 * 团队成员销售记录
	 */
	@RequestMapping("salesRecordList")
	@ResponseBody
	public BaseResponse partnerSalesRecordPageList(@Valid PartnerDetailRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String ,Object> query = new HashMap<String ,Object>();
		query.put("userId", req.getUserId());
		query.put("parentId", JsonWebTokenHepler.getUserIdByToken(head.getToken()));
		Page<PartnerSaleRecordResp> page = new Page<PartnerSaleRecordResp>(req.getPageIndex(), req.getPageSize());
		PaginatorSevResp<PartnerSaleRecordResp> rlt = partnerService.queryPartnerSaleRecord(query, page);
		return AppResponseUtil.getSuccessResponse(rlt,PartnerSaleRecordResponse.class);
	}
	
	/**
	 * 团队本月销售统计
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("monthSaleStatistics")
	@ResponseBody
	public BaseResponse partnerMonthSaleStatistics(@Valid PartnerMonthSaleRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String ,Object> query = new HashMap<String ,Object>();
		query.put("userId", JsonWebTokenHepler.getUserIdByToken(head.getToken()));
		if(req.getDate() != null && req.getDate() != "" && req.getDateType() != null && req.getDateType() != "") {
			/*if(req.getDateType() != null && req.getDateType().equals("3")) {
				SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd" );
				Date date = format.parse(req.getDate());
				query.put("date", DateUtils.format(date, "yyyy-MM"));
			} else {
				query.put("date", req.getDate());
			}*/
			if(req.getDateType().equals("3") && req.getDate().length() > 7) {
				String dateStr = req.getDate().trim().substring(0, 7);
				query.put("date", dateStr);
			} else {
				query.put("date", req.getDate());
			}
			query.put("dateType", req.getDateType());
		} else {
			query.put("date", DateUtils.format(new Date(), "yyyy-MM"));
			query.put("dateType", "3");
		}
		
		MonthSaleStatisticsResp rlt = partnerService.queryPartnerMonthSaleStatistics(query);
		return AppResponseUtil.getSuccessResponse(rlt, MonthSaleStatisticsResponse.class);
		
	}
	
	/**
	 * 团队本月销售列表
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("monthSaleList")
	@ResponseBody
	public BaseResponse partnerMonthSaleList(@Valid PartnerMonthSaleRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String ,Object> query = new HashMap<String ,Object>();
		query.put("userId", JsonWebTokenHepler.getUserIdByToken(head.getToken()));
		if(req.getDate() != null && req.getDate() != "" && req.getDateType() != null && req.getDateType() != "") {
			if(req.getDateType().equals("3") && req.getDate().length() > 7) {
				String dateStr = req.getDate().trim().substring(0, 7);
				query.put("date", dateStr);
			} else {
				query.put("date", req.getDate());
			}
			query.put("dateType", req.getDateType());
		} else {
			query.put("date", DateUtils.format(new Date(), "yyyy-MM"));
			query.put("dateType", "3");
		}
		Page<MonthSaleListResp> page = new Page<MonthSaleListResp>(req.getPageIndex(), req.getPageSize());
		PaginatorSevResp<MonthSaleListResp> rlt = partnerService.queryPartnerMonthSaleList(query, page);
		return AppResponseUtil.getSuccessResponse(rlt, MonthSaleListResponse.class);
	}
	
	
}
