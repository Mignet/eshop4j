package com.linkwee.api.controller.act;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.api.request.act.AdvPageListRequest;
import com.linkwee.api.request.act.AdvertisementPageListRequest;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.enums.PlatformEnum;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.news.SmAdvertisement;
import com.linkwee.web.response.BannersResponse;
import com.linkwee.web.service.AdvertisementService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.WebUtil;



/**
 * 
 * @描述： 实体控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月11日 16:27:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 *
 */
@Controller
@RequestMapping(value = "/api/homepage")
@RequestLogging("首页")
public class HomepageController  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomepageController.class);
	
	@Resource
	private AdvertisementService advertisementService;
	
	@Resource
    private CrmCfplannerService crmCfplannerService;
	
	/**
	 * 首页广告条
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/banners")
	@ResponseBody
	@RequestLogging("首页banner")
	public BaseResponse banners(AppRequestHead head) {
		LOGGER.info("首页head:"+head);
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		List<SmAdvertisement> advertisementList = advertisementService.queryBanner(appType);
		List<BannersResponse> rlt = new ArrayList<BannersResponse>();
		Map<String,String> params = new HashMap<String,String>();
		if(StringUtils.isNotBlank(head.getToken())){
			PlatformEnum platform =  AppUtils.getPlatform(head.getOrgNumber());
			if(PlatformEnum.IOS.equals(platform)||PlatformEnum.ANDROID.equals(platform)){
				params.put("token",head.getToken());
			}
		}
		if(null != advertisementList){
			for(SmAdvertisement obj:advertisementList){
				obj.setLinkUrl(WebUtil.creatUrl(obj.getLinkUrl(), params));
				rlt.add(new BannersResponse(obj));
			}
		}
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("datas",rlt);
		LOGGER.info("bannerreturn:"+AppResponseUtil.getSuccessResponse(ret));
		//更新理财师访问时间
		if(AppUtils.isChannelApp(head.getOrgNumber())){
			CrmCfplanner crmCfplanner = new CrmCfplanner();
			crmCfplanner.setUserId(JsonWebTokenHepler.getUserIdByToken(head.getToken()));
			crmCfplanner.setRectVisitTime(new Date());
			crmCfplannerService.updateByUserId(crmCfplanner);
		}
		return AppResponseUtil.getSuccessResponse(ret);
	}
	/**
	 * 开屏广告
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/opening")
	@ResponseBody
	@RequestLogging("开屏广告")
	public BaseResponse partnerPageList(@Valid AdvertisementPageListRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		SmAdvertisement adv = new SmAdvertisement();
		adv.setAppType(appType);
		adv.setPageIndex("app_opening");
		adv.setStatus(0);
		
		List<SmAdvertisement> rlt = advertisementService.findAdvertisementDtl(adv);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * 广告
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/advs")
	@ResponseBody
	@RequestLogging("广告")
	public BaseResponse advPageList(@Valid AdvPageListRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		SmAdvertisement adv = new SmAdvertisement();
		adv.setAppType(appType);
		adv.setPageIndex(req.getAdvPlacement());
		adv.setStatus(0);	
		List<SmAdvertisement> rlt = advertisementService.findAdvertisementDtl(adv);
		
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
}
