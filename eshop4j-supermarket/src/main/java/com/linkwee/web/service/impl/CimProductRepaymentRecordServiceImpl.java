package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.linkwee.api.request.crm.WeiXinMsgRequest;
import com.linkwee.api.response.cim.ProductDetailResponse;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.openapi.request.RepaymentRecordReq;
import com.linkwee.web.dao.CimProductRepaymentRecordMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.CimProduct;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.cim.CimProductInvestRecord;
import com.linkwee.web.model.cim.CimProductRepaymentRecord;
import com.linkwee.web.model.cim.OrgInfo;
import com.linkwee.web.service.CimOrgFeeGatherService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CimProductInvestRecordService;
import com.linkwee.web.service.CimProductRepaymentRecordService;
import com.linkwee.web.service.CimProductService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.web.service.WeiXinMsgService;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.helper.ThreadpoolService;
import com.linkwee.xoss.util.RandomTextCreator;


 /**
 * 
 * @描述：CimProductRepaymentRecordService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年07月25日 17:15:45
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductRepaymentRecordService")
public class CimProductRepaymentRecordServiceImpl extends GenericServiceImpl<CimProductRepaymentRecord, Long> implements CimProductRepaymentRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductRepaymentRecordServiceImpl.class);
	
	private static final byte REPAYMENT_SUCCESS_STATUS = 0x03;
	
	@Autowired
	private CrmInvestorService investorService;
	
	@Resource
	private CimProductInvestRecordService investRecordService;
	
	@Resource
	private CimProductRepaymentRecordMapper repaymentRecordMapper;
	
	@Resource
	private CimProductService productService;
	
	@Resource
	private CimOrgFeeGatherService cimOrgFeedetailService;
	
    @Resource
    private PushMessageHelper pushMessageHelper;

    @Resource
    private SmMessageQueueService messageQueueService;

    @Resource
    private CrmCfplannerService crmCfplannerService;
    
    @Resource
    private CrmInvestorService crmInvestorService;
    
	@Resource
	private SysMsgService sysMsgService;
	
	@Resource
	private WeiXinMsgService weiXinMsgService;
	
	@Resource
	private CimOrginfoService cimOrginfoService;
	
	@Resource
	private ConfigHelper configHelper;
	
	@Override
    public GenericDao<CimProductRepaymentRecord, Long> getDao() {
        return repaymentRecordMapper;
    }

	@Override
	public void insertRepaymentRecord(RepaymentRecordReq recordReq)throws Exception {
		try{
			CimProductInvestRecord investRecord = new CimProductInvestRecord();
			investRecord.setInvestRecordNo(recordReq.getInvestId());
			investRecord = investRecordService.selectOne(investRecord);
			if(investRecord==null)throw new ServiceException("无效的投资记录: "+recordReq.getInvestId());
			if(ObjectUtils.equals(investRecord.isStatus(),REPAYMENT_SUCCESS_STATUS))throw new ServiceException("投资记录已回款完成: "+recordReq.getInvestId());
			
			CimProductRepaymentRecord record = new CimProductRepaymentRecord();
			record.setInvestRecordId(recordReq.getInvestId());
			record.setRepaymentId(recordReq.getRepaymentId());
			if(selectOne(record)!=null)throw new ServiceException("已经存在的回款记录: "+recordReq.getRepaymentId());
			
			CrmInvestor investor = investorService.queryInvestorByUserId(recordReq.getUserId());
			if(investor==null)throw new ServiceException("无效的用户编号: "+recordReq.getUserId());
			CimProduct product = new CimProduct();
			product.setThirdProductId(recordReq.getProductId());
			product = productService.selectOne(product);
			if(product==null)throw new ServiceException("无效的产品编号: "+recordReq.getProductId());
			CimProductRepaymentRecord repaymentRecord = new CimProductRepaymentRecord();
			repaymentRecord.setRepaymentId(recordReq.getRepaymentId());
			repaymentRecord.setInvestRecordId(recordReq.getInvestId());
			repaymentRecord.setUserId(recordReq.getUserId());
			repaymentRecord.setRepaymentAmount(recordReq.getRepaymentAmount());
			repaymentRecord.setProductId(product.getProductId());
			repaymentRecord.setThirdProductId(recordReq.getProductId());
			repaymentRecord.setProfit(recordReq.getProfit());
			repaymentRecord.setRepaymentTime(recordReq.getRepaymentTime());
			repaymentRecord.setStatus(recordReq.getStatus());
			repaymentRecord.setCreateTime(new Date());
			insert(repaymentRecord);
			investRecordService.updateRepaymentStatus(recordReq.getInvestId(),recordReq.getStatus(),recordReq.getRepaymentTime(),recordReq.getRepaymentAmount(), recordReq.getProfit());
			//修改可赎回、转让 订单费用表为已经回款
			cimOrgFeedetailService.setRepaymentTime(recordReq.getInvestId(), recordReq.getRepaymentTime());
			//消息中心推送
			pushNoticeThread(recordReq,product);
			//微信推送 回款提醒
			if(recordReq.getStatus()==3){
				pushWeiXinMsgTread(recordReq,investor,investRecord,product);
			}
		}catch(Exception e){
			LOGGER.warn("insertRepaymentRecord exception repaymentRecordReq={},exception={}", recordReq,e);
			throw e;
		}
	}
	
	//微信消息推送
	private void pushWeiXinMsgTread(final RepaymentRecordReq recordReq,final CrmInvestor investor,final CimProductInvestRecord investRecord,CimProduct product){
		final List<WeiXinMsgRequest> wxList = new ArrayList<WeiXinMsgRequest>();
		
		OrgInfo org = cimOrginfoService.findOrgInfo(investRecord.getPlatfrom());
		String platformName = org!=null?org.getOrgName():"";//平台名称
		
		ProductDetailResponse pro = productService.queryProductDetail(investRecord.getProductId());
		String productName = pro!=null?pro.getProductName():"";//产品名称
		
		String paymentDate = DateUtils.format(recordReq.getRepaymentTime(),DateUtils.FORMAT_LONG);
		
		boolean flag = true;
		String amount = recordReq.getRepaymentAmount()+"元";
		if(product.getIsFixedDeadline()==2){
			DateTime repaymentTime = new DateTime(recordReq.getRepaymentTime().getTime());//回款时间
			//回款日期-产品最小期限天数>购买时间  && 回款日期-产品最大期限天数<购买时间
			if(repaymentTime.plusDays(-product.getDeadLineMinValue()).toDate().getTime()>investRecord.getStartTime().getTime()
					&&repaymentTime.plusDays(-product.getDeadLineMaxValue()).toDate().getTime()<investRecord.getStartTime().getTime()){
				flag = false;
			}
		}
		
		if(flag){
			final WeiXinMsgRequest wxreq = new WeiXinMsgRequest();
			wxreq.setUseId(recordReq.getUserId());
			wxreq.setTemkey(SysConfigConstant.PAYMENT_REMINDER);
			wxreq.setPlatformName(platformName);//平台名称
			wxreq.setProductName(productName);//产品名称
			wxreq.setPaymentDate(paymentDate);// 回款时间 
			wxreq.setAmount(amount);//回款金额
			wxreq.setUseType(2);
			wxList.add(wxreq);
		}
		//理财师   平台名称 产品名称 回款时间 回款金额
		final WeiXinMsgRequest wxreq2 = new WeiXinMsgRequest();
		wxreq2.setUseId(investor.getCfplanner());//用户的理财师 
		wxreq2.setUseType(1);
		wxreq2.setTemkey(flag==true?SysConfigConstant.LIECAI_PAYMENT_REMINDER:SysConfigConstant.PAYMENT_REMINDER_ACTIVE);
		wxreq2.setPaymentDate(paymentDate);// 回款时间 
		wxreq2.setUserName(investor.getUserName()+RandomTextCreator.encrypTion(investor.getMobile()));
		wxreq2.setAmount(amount);//回款金额
		wxreq2.setPlatformName(platformName);//平台名称
		wxreq2.setProductName(productName);//产品名称
		wxList.add(wxreq2);
		ThreadpoolService.execute(new Runnable() {
			@Override
			public void run() {
				weiXinMsgService.sendWeiXinMsgListCommon(wxList);
			}
		});
	}
	
    //消息中心推送
    private void pushNoticeThread(final RepaymentRecordReq recordReq,final CimProduct product) {
       
           ThreadpoolService.execute(new Runnable() {
               @Override
               public void run() {
            	   String unwantedOrgNumber = configHelper.getValue(SysConfigConstant.PUSHMESSAGE_UNWANRWS_ORGNUMBER);
            	   if(unwantedOrgNumber == null || (unwantedOrgNumber != null && !unwantedOrgNumber.contains(product.getOrgNumber())) ){
            		    CrmCfplanner crmCfplanner =  crmCfplannerService.queryCfplannerByInvestor(recordReq.getUserId());
            	        CrmInvestor  crmInvestor = crmInvestorService.queryInvestorByUserId(recordReq.getUserId());

                       //给投资客户站内信
                      /*  SysMsg msg = new SysMsg();*/
                        boolean collectFail = ObjectUtils.equals(product.getIsCollect(),2) && ObjectUtils.equals(product.getStatus(),3);

                        String content = String.format(configHelper.getValue(collectFail ? SysConfigConstant.PUSHMESSAGE_COLLECT_FAIL_INV : SysConfigConstant.PUSHMESSAGE_PAYBACK_INV),product.getProductName(),recordReq.getRepaymentAmount() ==null ? "" :recordReq.getRepaymentAmount().setScale(2, BigDecimal.ROUND_DOWN));
                      /*  msg.setContent(content);
                        msg.setContent(String.format(configHelper.getValue(collectFail ? SysConfigConstant.PUSHMESSAGE_COLLECT_FAIL_INV : SysConfigConstant.PUSHMESSAGE_PAYBACK_INV),product.getProductName(),recordReq.getRepaymentAmount() ==null ? "" :recordReq.getRepaymentAmount().setScale(2, BigDecimal.ROUND_DOWN)));
                        msg.setStatus(0);// 发布
	       				msg.setUserNumber(recordReq.getUserId());
	       				msg.setReadStatus(0);// 未读
	       				msg.setAppType(AppTypeEnum.INVESTOR.getKey());
	       				sysMsgService.addMsg(msg);*/
                        if(StringUtils.isBlank(content))return;
                        Map<String,Object> urlParam = Maps.newConcurrentMap();
                        urlParam.put("status", 3);
	       				pushMessageHelper.pushMessage(AppTypeEnum.INVESTOR, SmsTypeEnum.MYINVESTRECORD_INC, recordReq.getUserId(), "回款提醒", content, urlParam, true);
       					
                      // messageQueueService.batchSendSmMessageAndPersonalMsg(mobiles, AppTypeEnum.INVESTOR, MsgModuleEnum.INVESTORPAYBACKINV,true,userIds,PersonalMsgTypeEnum.PROJECTINVEST_INV,opName,recordReq.getRepaymentAmount().setScale(2, BigDecimal.ROUND_DOWN),product.getProductName());
	       			   //推送给投资客户的理财师
	       			   String opName = "";
                       String pushMsg = "";
                       String svalues = StringUtils.isNotBlank(crmInvestor.getUserName()) ? crmInvestor.getUserName()+"*"+crmInvestor.getMobile().substring(crmInvestor.getMobile().length() - 4)
   							: "*"+crmInvestor.getMobile().substring(crmInvestor.getMobile().length() - 4);
                       if(collectFail){//
                    	   pushMsg = String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_COLLECT_FAIL_LCS),svalues,product.getProductName(),recordReq.getRepaymentAmount() ==null ? "" :recordReq.getRepaymentAmount().setScale(2, BigDecimal.ROUND_DOWN));
                       }else{
                    	   if(ObjectUtils.equals(recordReq.getStatus(), 3)){//该笔投资记录已经回款完成
	                    	   opName = "投资";
	                    	   pushMsg = String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_INV_PAYBACK_LCS),svalues,opName,product.getProductName(),recordReq.getRepaymentAmount() ==null ? "" :recordReq.getRepaymentAmount().setScale(2, BigDecimal.ROUND_DOWN));
	                       }else{
	                    	   opName = "赎回";
	                    	   pushMsg = String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_INV_RANSOM_LCS),svalues,opName,product.getProductName(),recordReq.getRepaymentAmount() ==null ? "" :recordReq.getRepaymentAmount().setScale(2, BigDecimal.ROUND_DOWN));
	                       }
                       }
                      
                       
                       urlParam = Maps.newHashMap();
                       urlParam.put("customerId",recordReq.getUserId());
                       pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.LCUSTOMERFIXEDRETURN, crmCfplanner.getUserId(), "回款提醒", pushMsg, urlParam, true);
                       
            	   }
                   
               }
           });
    }

  

}
