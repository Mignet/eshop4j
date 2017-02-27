package com.linkwee.api.controller.act;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.api.request.act.RedpacketRequest;
import com.linkwee.api.request.act.SendRedPacketRequest;
import com.linkwee.api.response.act.RedpacketResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;

@Controller
@RequestMapping("api/redPacket")
@RequestLogging("红包")
public class RedpacketController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedpacketController.class);
	
	@Autowired
	private RedPacketService redPacketService;
	
	
	@RequestMapping("queryRedPacket")
	@ResponseBody
	@RequestLogging("查询红包")
	public Object queryRedPacket(@Valid RedpacketRequest req,BindingResult result,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		try{
			String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
			if(AppUtils.isChannelApp(head.getOrgNumber())){
				 return AppResponseUtil.getSuccessResponse(redPacketService.queryCfplannerRedPacket(userId, req));
			}
			else if(AppUtils.isInvestorApp(head.getOrgNumber())){
				return  AppResponseUtil.getSuccessResponse(redPacketService.queryInvestorRedPacket(userId, req));
			}
			return AppResponseUtil.getErrorBusi("100002","请使用正确的app类型");
		}
		catch(Exception e){
			LOGGER.error("queryRedPacket exception", e);
		}
		return new BaseResponse(-1,"查询失败，请联系客服");
	}
	
	@RequestMapping("queryAvailableRedPacket")
	@ResponseBody
	@RequestLogging("查询可用红包")
	public Object queryAvailableRedPacket(@Valid RedpacketRequest req,BindingResult result,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		try{
			String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
			if(AppUtils.isInvestorApp(head.getOrgNumber())){
				
				Page<RedpacketResponse> page  = new Page<RedpacketResponse>(req.getPageIndex(),req.getPageSize()>10?10:req.getPageSize()); //默认每页10条
				PaginatorResponse<RedpacketResponse> paginatorResponse = new PaginatorResponse<RedpacketResponse>();
				List<RedpacketResponse> redpackets = Collections.emptyList();
				if(ObjectUtils.equals(req.getType(), 1))
					redpackets = redPacketService.patformRedPacket(userId, req, page);
				else if(ObjectUtils.equals(req.getType(), 2))
					redpackets = redPacketService.productRedPacket(userId, req, page);
				paginatorResponse.setDatas(redpackets);
				paginatorResponse.setValuesByPage(page);
				return  AppResponseUtil.getSuccessResponse(paginatorResponse);
			}
			return AppResponseUtil.getErrorBusi("100002","请使用正确的app类型");
		}
		catch(Exception e){
			LOGGER.error("queryAvailableRedPacket exception", e);
		}
		return new BaseResponse(-1,"查询失败，请联系客服");
	}
	
	
	@RequestMapping("sendRedPacket")
	@ResponseBody
	@RequestLogging("发放红包")
	public Object sendRedPacket(@Valid SendRedPacketRequest req,BindingResult result,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(result)) return AppResponseUtil.getErrorParams(result);
		try{
			return (AppUtils.isChannelApp(head.getOrgNumber()) ? redPacketService.sendRedPacket(JsonWebTokenHepler.getUserIdByToken(head.getToken()), req) :AppResponseUtil.getErrorBusi("100002","请使用正确的app类型"));
		}
		catch(Exception e){
			LOGGER.error("sendRedPacket exception", e);
		}
		return new BaseResponse(-1,"发放失败，请联系客服");
	}

}
