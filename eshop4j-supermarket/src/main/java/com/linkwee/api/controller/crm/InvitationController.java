package com.linkwee.api.controller.crm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.crm.InvotationRequest;
import com.linkwee.api.request.crm.WechatShareRequest;
import com.linkwee.api.response.crm.InvotateListResponse;
import com.linkwee.api.response.crm.InvotateUserListResponse;
import com.linkwee.api.response.crm.WechatShareResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.SysConfig;
import com.linkwee.web.model.cim.CimProductInvestRecord;
import com.linkwee.web.model.crm.InvotateUserListResp;
import com.linkwee.web.model.share.ShareContent;
import com.linkwee.web.model.vo.SingleImgInfo;
import com.linkwee.web.service.CimProductInvestRecordService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.constant.WebConstants;
import com.linkwee.xoss.helper.CommonHelper;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.helper.QRCodeUtil;
import com.linkwee.xoss.helper.WeChatConfig;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;
import com.linkwee.xoss.util.WechatUtil;
import com.linkwee.xoss.util.WechatUtil.WechatResp;

@Controller
@RequestMapping("/api/invitation")
public class InvitationController {
	
	@Resource
	private CommonHelper commonHelper;
	
	@Resource
	private SysConfigService sysConfigService;
	
	@Resource
	private ConfigHelper configHelper;
	
	@Resource
    private CrmInvestorService crmInvestorService;
	
	@Resource
	private CrmCfplannerService crmCfplannerService;
	
	@Resource
    private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private CimProductInvestRecordService cimProductInvestRecordService;

	private static final Logger LOGGER = LoggerFactory.getLogger(InvitationController.class);
	
	private String errorCode = "140003";
	
	/**
	 * 邀请客户
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/customer/homepage")
    @ResponseBody
	@RequestLogging("邀请客户")
    public BaseResponse invitateCustomer(AppRequestHead head,BindingResult result,HttpServletRequest request) throws Exception {
		
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());	
		CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(userId);
		
		ShareContent shareContent = new ShareContent();
		
		if(AppUtils.isInvestorApp(head.getOrgNumber())){
			shareContent = commonHelper.getWechatShareCustomer(crmUserInfo.getMobile(),crmUserInfo.getUserName());
		}else{
			shareContent = commonHelper.getWechatShareLcsRcCus(crmUserInfo.getMobile(),crmUserInfo.getUserName());
		}
						
		CrmInvestor crmInvestor = crmInvestorService.queryInvestorByUserId(userId);
		
		String qrcode;	
		if(StringUtils.isBlank(crmInvestor.getQrcode())){		
			org.springframework.core.io.Resource res = new ClassPathResource("icons/invest.jpg");
			String logoPath = res.getFile().getPath();
			LOGGER.info("ICON路径,logoPath={}",logoPath);
			qrcode = creatRcCode(request,logoPath,shareContent.getShareLink());						
			crmInvestorService.updateInvQrByUserId(userId,qrcode);
		}else{
			qrcode = crmInvestor.getQrcode();
		}
		
		Map<String,Object> resp = new HashMap<String,Object>();
		resp.put("url", qrcode);
		resp.put("shareContent", shareContent);
		return AppResponseUtil.getSuccessResponse(resp);
			
    }
	
	/**
	 * 邀请理财师
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cfp/homepage")
    @ResponseBody
    @RequestLogging("邀请理财师")
    public BaseResponse invitateCfp(AppRequestHead head,BindingResult result,HttpServletRequest request) throws Exception {
		
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());	
		CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(userId);		
		ShareContent shareContent = commonHelper.getWechatShareRclcs(crmUserInfo.getMobile(), crmUserInfo.getUserName());
		
		
		//替换成理财师的信息
		
		CrmCfplanner crmCfplanner = crmCfplannerService.queryCfplannerByUserId(userId);
		
		String qrcode;	
		if(StringUtils.isBlank(crmCfplanner.getQrcode())){
			//生成验证码		
			/*String path = this.getClass().getResource("/").getPath();
			String logoPath = null;
			if(path!=null&&path.length()>0){
				logoPath = path+"icons/lcs.jpg";
			}*/
			org.springframework.core.io.Resource res = new ClassPathResource("icons/lcs.jpg");
			String logoPath = res.getFile().getPath();
			qrcode = creatRcCode(request,logoPath,shareContent.getShareLink());	
			crmCfplannerService.updateCfpQrByUserId(userId,qrcode);
		}else{
			qrcode = crmCfplanner.getQrcode();
		}
		
		Map<String,Object> resp = new HashMap<String,Object>();
		resp.put("url", qrcode);
		resp.put("shareContent", shareContent);
		//直接推荐收益规则
		resp.put("allowanceRule", "被推荐人每笔销售佣金的15%");
		//间接推荐收益规则
		resp.put("childrenAllowanceRule", "被推荐人每笔销售佣金的5%");
		return AppResponseUtil.getSuccessResponse(resp);
			
    }
	
	/*private void creatRcCode(HttpServletRequest request,String logoPath,String content){
		String fileType = "jpg";
		String fileName = UUID.randomUUID().toString()+"."+fileType;
        try {
			FileOutputStream out = new FileOutputStream(new File("D:/share/2015-11-16/"+fileName));
			QRCodeUtil.createQR(out,logoPath,content);		
		} catch (Exception e) {
			LOGGER.error("生成图片失败!",e);
		}finally {
            
        }
	}*/
	
	/**
	 * 创建二维码并上传到图片服务器
	 * @param request
	 * @param content
	 * @return
	 */
	private String creatRcCode(HttpServletRequest request,String logoPath,String content){
		CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream(1024);
			QRCodeUtil.createQR(outStream,logoPath,content);			
			//准备上传图片数据
            byte[] buffer = null;              
            outStream.close();  
            buffer = outStream.toByteArray(); 
          //开始上传
            HttpPost httppost = new HttpPost(sysConfigService.getValuesByKey(WebConstants.IMAGE_SERVER_URL));
            
            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(buffer);
            httppost.setEntity(byteArrayEntity);
            httppost.addHeader("Content-Type", "jpeg");
            
            LOGGER.debug("executing request " + httppost.getRequestLine());
//            Header[] headers =  httppost.getAllHeaders();
            response = httpclient.execute(httppost);
            
            LOGGER.debug("----------------Response-----------------------");
            LOGGER.debug(response.getStatusLine().toString());
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                String resp = EntityUtils.toString(resEntity);
                LOGGER.debug("Response content length: " + resEntity.getContentLength());
                LOGGER.debug("Response content : " + resp);
                //resp = "{\"ret\":true,\"info\":[{\"md5\":\"9452f7a016c638061cb6f954c8dbff66\",\"size\":16807}]}";
                SingleImgInfo o = JsonUtils.fromJsonToObject(resp, SingleImgInfo.class);
                if(o==null || o.getInfo()==null){
                    return null;
                }
                return  o.getInfo().getMd5();
                //根据图片上传返回的结果获取第一个md5值
                /*String md5 = resp.split("\"md5\":\"")[1].split("\",")[0];
                return md5;*/
            }
            EntityUtils.consume(resEntity);
		} catch (Exception e) {
			LOGGER.error("生成图片失败!",e);
		}finally {
            try {
                if(response!=null){
                    response.close();
                    response = null;
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return null;
	}
	
	/**
	 * 通讯录邀请
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/maillist")
	@ResponseBody
	@RequestLogging("通讯录邀请")
	public BaseResponse maillist(InvotationRequest req,BindingResult result, AppRequestHead head) throws Exception {
		
		String mobiles = req.getMobiles();
		String names = req.getNames();
		
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		
		if(StringUtils.isBlank(mobiles)) {
			List<BaseResponse> errors = new ArrayList<BaseResponse>();
			errors.add(new BaseResponse("mobile_notNull","手机号不能为空"));
	    	return AppResponseUtil.getErrorParams(errors);
        }
		
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		String[] mobileArray = mobiles.split(",");
		String[] nameArray = names.split(",");
		List<String> regs = null;
		String content = null;
			
		//Map<String,String> config = configHelper.getValuesByType("mail_invitation");		
		List<SysConfig> sysList = sysConfigService.querySysConfigByName("通讯录-");
    	Map<String,String> config = new HashMap<String,String>();
    	for(SysConfig item:sysList){
    		config.put(item.getConfigKey(),item.getConfigValue());  		  		
    	}
    	
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());	
		CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(userId);
								 
		if(AppUtils.isInvestorApp(head.getOrgNumber())){//客户邀请客户
			regs = crmInvestorService.selectRegInvestors(mobileArray);			
			content = String.format(config.get("mail_customer_invitation_customer"), crmUserInfo.getUserName(), crmUserInfo.getMobile());
		}else{
			if(req.getType() == 1){//理财师邀请客户
				regs = crmInvestorService.selectRegInvestors(mobileArray);
				content = String.format(config.get("mail_invitation_customer"), crmUserInfo.getMobile(), crmUserInfo.getUserName());
			}else{//理财师推荐理财师
				regs = crmCfplannerService.selectRegCfplanners(mobileArray);
				content = String.format(config.get("mail_invitation_lcs"),crmUserInfo.getMobile(), crmUserInfo.getUserName());
			}
		}
		
		List<Map<String,String>> customers = new ArrayList<Map<String,String>>();
		for(int i=0;i<mobileArray.length;i++){
			String mobile = mobileArray[i];
			boolean isReg = false;
			if(regs!=null){
				for(String regMobile:regs){
					if(regMobile.equals(mobile)){
						isReg = true;
						break;
					}
				}
			}
			if(!isReg){//所有未注册的手机号码
				String name = nameArray[i];
				Map<String,String> customer = new HashMap<String,String>();
				customer.put("mobile", mobile);
				customer.put("name", name);
				customers.add(customer);
			}
		}
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("customers",customers);
		ret.put("content",content);
		return AppResponseUtil.getSuccessResponse(ret);
	}
	
	
	/**
	 * 统计 注册客户、投资客户
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/investor/statistics")
	@ResponseBody
	@RequestLogging("统计 注册客户、投资客户")
	public BaseResponse investorStatistics(AppRequestHead head) throws Exception {
		
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());	
		
		List<String> refRegCustomers = crmInvestorService.refRegInvestors(userId);
		Integer regCustomerNum = refRegCustomers.size();
		
		Integer investCustomerNum = null;
		if(regCustomerNum > 0 ){
			List<CimProductInvestRecord> cimProInvestRecord = cimProductInvestRecordService.selectRefInvestRecord(refRegCustomers);
			investCustomerNum = cimProInvestRecord.size();
		}
		
		Map<String,String> ret = new HashMap<String,String>();
		ret.put("regPersons", regCustomerNum==null?"0":regCustomerNum.toString());
		ret.put("investPersons", investCustomerNum==null?"0":investCustomerNum.toString());
		
		return AppResponseUtil.getSuccessResponse(ret);
	}
	
	/**
	 * 邀请客户-列表
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/investor/pageList")
	@ResponseBody
	@RequestLogging("邀请客户-列表")
	public BaseResponse investorPageList(InvotationRequest req,AppRequestHead head) throws Exception {
		
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		
		LOGGER.info("查看邀请客户列表, invotationRequest={}", JSONObject.toJSONString(req));
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		req.setUserId(userId);
    	Page<InvotateListResponse> page  = new Page<InvotateListResponse>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<InvotateListResponse> rlt = crmUserInfoService.invitorMsgPageList(req,page);
		return AppResponseUtil.getSuccessResponse(rlt);					
	}
	
	/**
	 * 微信分享
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/wechat/share")
	@ResponseBody
	@RequestLogging("微信分享")
	public BaseResponse weChatShare(@Valid WechatShareRequest req,BindingResult result,AppRequestHead head) throws Exception {
		
		if(StringUtils.isBlank(head.getOrgNumber())){
			return  new BaseResponse("140002","OrgNumber不能为空");
		}
		
		if(ResponseUtil.existsParamsError(result)) {
	    	return ResponseUtil.getErrorParams(result);
        }
		WeChatConfig weChatConfig = commonHelper.getWeChatConfig(AppUtils.getAppType(head.getOrgNumber()));
		WechatResp ret = WechatUtil.getWeChatShareResponse(commonHelper,req.getUrl(), weChatConfig.getAppid(), weChatConfig.getAppSecret(),
				AppUtils.getAppType(head.getOrgNumber()));
		LOGGER.debug("微信返回："+ret);
		if(ret.isSuccess()){
			WechatShareResponse resp = new WechatShareResponse();
			resp.setAppid(ret.getString("appid"));
			resp.setNonceStr(ret.getString("nonceStr"));
			resp.setSignature(ret.getString("signature"));
			resp.setTimestamp(ret.getString("timestamp"));
        	return ResponseUtil.getSuccessResponse(resp);
		}else{
			return ResponseUtil.getErrorBusi("wechatError",ret.getMsg());
		}
    }
	
	/**
	 * 客户邀请记录
	 */
	@RequestMapping("/customerRecord")
	@ResponseBody
	@RequestLogging("客户邀请记录")
	public BaseResponse customerRecord(InvotationRequest req,AppRequestHead head) throws Exception {
		req.setUserId(JsonWebTokenHepler.getUserIdByToken(head.getToken()));
    	Page<InvotateUserListResp> page  = new Page<InvotateUserListResp>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<InvotateUserListResp> rlt = crmInvestorService.queryInvitationCustomerRecord(req,page);
		return AppResponseUtil.getSuccessResponse(rlt, InvotateUserListResponse.class);	
	}
	
	/**
	 * 理财师邀请记录
	 */
	@RequestMapping("/cfplannerRecord")
	@ResponseBody
	@RequestLogging("理财师邀请记录")
	public BaseResponse cfplannerRecord(InvotationRequest req,AppRequestHead head) throws Exception {
		req.setUserId(JsonWebTokenHepler.getUserIdByToken(head.getToken()));
    	Page<InvotateUserListResp> page  = new Page<InvotateUserListResp>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<InvotateUserListResp> rlt = crmCfplannerService.queryInvitationCfplannerRecord(req,page);
		return AppResponseUtil.getSuccessResponse(rlt, InvotateUserListResponse.class);					
	}
	
	/**
	 * 客户邀请记录统计
	 */
	@RequestMapping("/customerRecordStatistics")
	@ResponseBody
	@RequestLogging("客户邀请记录统计")
	public BaseResponse customerRecordStatistics(AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Map<String, Integer> rlt = crmInvestorService.queryInvitationCustomerRecordStatistics(userId);
		return AppResponseUtil.getSuccessResponse(rlt);	
	}
	
	/**
	 * 理财师邀请记录统计
	 */
	@RequestMapping("/cfplannerRecordStatistics")
	@ResponseBody
	@RequestLogging("理财师邀请记录统计")
	public BaseResponse cfplannerRecordStatistics(AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		int regiteredCount = crmCfplannerService.queryInvitationCfplannerRecordStatistics(userId);
		Map<String, Integer> rlt = new HashMap<String, Integer>();
		rlt.put("regiteredCount", regiteredCount);
		return AppResponseUtil.getSuccessResponse(rlt);					
	}
	
	@RequestMapping("/newQrcode")
    @ResponseBody
    @RequestLogging("新二维码")
    public BaseResponse newQrcode(AppRequestHead head,BindingResult result,HttpServletRequest request,@RequestParam("content")String content,@RequestParam("isLcs")Boolean isLcs) throws Exception {
		
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		String logString;
		if(isLcs){
			logString = "icons/lcs.jpg";
		}else{
			logString = "icons/invest.jpg";
		}
		org.springframework.core.io.Resource res = new ClassPathResource(logString);
		String logoPath = res.getFile().getPath();
		String md5 = creatRcCode(request,logoPath,content);	
		return AppResponseUtil.getSuccessResponse(md5);
			
    }
}
