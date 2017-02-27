package com.eshop4j.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.eshop4j.web.model.weixin.WeiXinMsgRequest;
import com.eshop4j.core.base.ResponseResult;
import com.eshop4j.core.constant.SysConfigConstant;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.NumberUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.dao.CimProductInvestRecordMapper;
import com.eshop4j.web.dao.CimProductUnrecordInvestMapper;
import com.eshop4j.web.dao.TCFeeMapper;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.web.enums.SmsTypeEnum;
import com.eshop4j.web.model.CrmInvestor;
import com.eshop4j.web.model.cim.CimProductUnrecordInvest;
import com.eshop4j.web.model.vo.InvestRecordWrapper;
import com.eshop4j.web.request.tc.AuditUnrecordInvestRequest;
import com.eshop4j.web.request.tc.UnrecordInvestRequest;
import com.eshop4j.web.response.tc.UnrecordInvestListResponse;
import com.eshop4j.web.service.CimProductUnrecordInvestService;
import com.eshop4j.web.service.CrmInvestorService;
import com.eshop4j.web.service.InvestRecordAware;
import com.eshop4j.web.service.WeiXinMsgService;
import com.eshop4j.xoss.helper.ConfigHelper;
import com.eshop4j.xoss.helper.PushMessageHelper;
import com.eshop4j.xoss.helper.ThreadpoolService;
import com.eshop4j.xoss.util.RandomTextCreator;


 /**
 * 
 * @描述：CimProductUnrecordInvestService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年09月09日 14:27:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductUnrecordInvestService")
public class CimProductUnrecordInvestServiceImpl extends GenericServiceImpl<CimProductUnrecordInvest, Long> implements CimProductUnrecordInvestService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductUnrecordInvestServiceImpl.class);
	
	@Resource
	private CimProductUnrecordInvestMapper unrecordInvestMapper;
	@Resource
	private TCFeeMapper feeMapper;
	
	@Resource
	private CrmInvestorService  investorService;
	
	@Resource
	private PushMessageHelper pushMessageHelper;
	
	
	@Resource
	private CimProductInvestRecordMapper investRecordMapper;
	
	@Resource
	private ConfigHelper configHelper;
	
	private List<InvestRecordAware> investRecordAwares;
	
/*	@Autowired
	private CimOrginfoService orginfoService;*/
	
	@Resource(name="crmCfgLevelService")
	private InvestRecordAware levelService;
	
	@Resource(name="feeCalcService")
	private InvestRecordAware feeCalcService;
	
	
	@Resource(name="actRedpacketService")
	private InvestRecordAware redpacketService;
	
	@Resource
	private WeiXinMsgService weiXinMsgService;
	
	@PostConstruct
	private void init(){
		investRecordAwares = Lists.newArrayList(redpacketService,levelService,(InvestRecordAware)investorService,feeCalcService);
	}
	
	
	@Override
    public GenericDao<CimProductUnrecordInvest, Long> getDao() {
        return unrecordInvestMapper;
    }



	@Override
	public DataTableReturn getUnrecordInvestList(UnrecordInvestRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(req.getDraw()+1);
		Page<UnrecordInvestListResponse> page = new Page<UnrecordInvestListResponse>(req.getStart()/req.getLength()+1,req.getLength());
		String imgServerUrl = configHelper.getValue(2,"img_server_url");
		List<UnrecordInvestListResponse> unrecordInvestListResponseLists = unrecordInvestMapper.getUnrecordInvestList(req.getMobile(),req.getInvestorsMobiel(), req.getStatus(), page);
		for(UnrecordInvestListResponse unrecordInvestListResponseTemp : unrecordInvestListResponseLists){
			unrecordInvestListResponseTemp.setImg(imgServerUrl+unrecordInvestListResponseTemp.getImg());
		}
		tableReturn.setData(unrecordInvestListResponseLists);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	
	private InvestRecordWrapper createInvestRecordWrapper(CimProductUnrecordInvest  req){
		InvestRecordWrapper investRecordWrapper = new InvestRecordWrapper();
		investRecordWrapper.setBizId(req.getInvestId());
		investRecordWrapper.setInvestId(req.getInvestId());
		investRecordWrapper.setUserId(req.getUserId());
		investRecordWrapper.setProductName(req.getProductName());
		investRecordWrapper.setFeeRatio(req.getFeeRate());
		investRecordWrapper.setRemark(req.getPlatfromName()+"-"+req.getProductName() +" 报单佣金");
		investRecordWrapper.setProductOrgId(req.getPlatfrom());
		investRecordWrapper.setProductDays(req.getProductDeadLineValue());
		investRecordWrapper.setInvestAmt(req.getInvestAmt());
		investRecordWrapper.setInvestTime(req.getInvestTime());
		investRecordWrapper.setPlatfromFirstInvest(true);
		investRecordWrapper.setFirstInvest(ObjectUtils.equals(investRecordMapper.queryUserInvestCount(req.getUserId()), 0));
		return investRecordWrapper;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public ResponseResult audit(AuditUnrecordInvestRequest req, String userName) throws Exception{
		 try{
			  LOGGER.debug("开始更新审核状态 req={},userName={}",req,userName);
			  boolean audit ;
			  switch (req.getStatus()) {
				case 1:
					audit = true;
					break;
				case 2:
					audit = false;		
					break;
				default:
					return new ResponseResult(false,"更新审核状态失败,请重试");
				}
			   CimProductUnrecordInvest updateUnrecordInvest= null;
			   CimProductUnrecordInvest unrecordInvest  = new CimProductUnrecordInvest();
			   unrecordInvest.setId(req.getId());
			   unrecordInvest = selectOne(unrecordInvest);
			   //审核通过计算佣金，职级以及发放用户红包
			   if(audit){
				   unrecordInvest.setInvestId(StringUtils.getUUID());
				   if(StringUtils.isBlank(unrecordInvest.getUserId())){
					   //自动生成用户
					   CrmInvestor investor = investorService.generateInvestor(unrecordInvest.getUserMobile(), unrecordInvest.getCfplannerId());
					   if(investor==null){
						   LOGGER.debug("自动生成用户失败 mobiel={},cfplannerId={}",unrecordInvest.getUserMobile(),unrecordInvest.getCfplannerId());
						   return new ResponseResult(false,"更新审核状态失败,请重试");
					   }
					   unrecordInvest.setUserId(investor.getUserId());
				   }
				   InvestRecordWrapper investRecordWrapper =  createInvestRecordWrapper(unrecordInvest);
				   dispatchRecords(investRecordWrapper);
				   updateUnrecordInvest = auditThrough(req, unrecordInvest, userName);
			   }else{
				   updateUnrecordInvest = auditNotThrough(req, userName);
			   }
			   if(updateUnrecordInvest==null) return new ResponseResult(false, "更新审核状态失败,请重试");
			   boolean isUpdate = update(updateUnrecordInvest) > 0 ;
			   if(isUpdate){
				   pushNoticeThread(unrecordInvest, audit, req.getRemark());
			   }
			   
			   //微信消息推送
			   final WeiXinMsgRequest wxreq = new WeiXinMsgRequest();
			   wxreq.setUseId(unrecordInvest.getCfplannerId());
			   wxreq.setUseType("1");
			   wxreq.setAuditTime(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));
			   if(audit){//报单审核通过
			        wxreq.setTemkey(SysConfigConstant.AUDIT_PASSED);
			    	wxreq.setReason("成功");
			   }else{//报单审核未通过
			    	wxreq.setTemkey(SysConfigConstant.AUDIT_NOT_PASSED);
			    	wxreq.setReason(req.getRemark());//未通过原因
			   }
			   //详情：xxx购买xxx元的《产品名称》  platfromName userMobile userName productName
			   String wxMobile = RandomTextCreator.encrypTion(unrecordInvest.getUserMobile());
			   String wxAmount = NumberUtils.getFormat(unrecordInvest.getInvestAmt(), "0.00");
			   wxreq.setRemark("您的用户 "+unrecordInvest.getUserName()+"("+wxMobile+") 购买 "
					   			+"《"+unrecordInvest.getPlatfromName()+"-"+unrecordInvest.getProductName()+"》"+wxAmount+"元 ");
			   wxreq.setProductName(unrecordInvest.getProductName());
			   ThreadpoolService.execute(new Runnable() {			
				   @Override
					public void run() {
					   weiXinMsgService.sendWeiXinMsgCommon(wxreq);
					}
			   });
			   
			   return isUpdate ? new ResponseResult(true, "更新审核状态成功"): new ResponseResult(false, "更新审核状态失败,请重试");
		   }catch(Exception e){
			   LOGGER.error("更新审核状态异常",e);
			   throw e;
		   }
	}
	
	/**
	 * 审核通过
	 * @param req
	 * @param unAuditrecord
	 * @param userName
	 * @return
	 */
	private CimProductUnrecordInvest auditThrough(AuditUnrecordInvestRequest req,CimProductUnrecordInvest unAuditrecord, String userName){
		CimProductUnrecordInvest unrecordInvest = new CimProductUnrecordInvest();
		unrecordInvest.setId(req.getId());
		unrecordInvest.setInvestId(unAuditrecord.getInvestId());
		unrecordInvest.setUserId(unAuditrecord.getUserId());
		unrecordInvest.setStatus(req.getStatus());
		unrecordInvest.setFeeAmt(feeMapper.getFeeAmt(unAuditrecord.getInvestId(), "1001"));
		unrecordInvest.setUpdateTime(new Date());
		unrecordInvest.setOperator(userName);
		return unrecordInvest;
	}
	
	
	/**
	 * 审核不通过
	 * @param req
	 * @param userName
	 * @return
	 */
	private CimProductUnrecordInvest auditNotThrough(AuditUnrecordInvestRequest req, String userName){
		if (StringUtils.isBlank(req.getRemark())){
			LOGGER.debug("审核不通过审核失败原因不能为空，req={}",req);
			return null;
		}
		CimProductUnrecordInvest unrecordInvest = new CimProductUnrecordInvest();
		unrecordInvest.setId(req.getId());
		unrecordInvest.setStatus(req.getStatus());
		unrecordInvest.setRemark(req.getRemark());
		unrecordInvest.setUpdateTime(new Date());
		unrecordInvest.setOperator(userName);
		return  unrecordInvest;
	}
	
	/**
	 * 发送消息
	 * @param unrecodrInvestId
	 * @param audit
	 * @param reason
	 */
	 private void pushNoticeThread(final CimProductUnrecordInvest formInfo,final boolean audit,final String reason) {
	   	ThreadpoolService.execute(new Runnable() {
				@Override
				public void run() {
					 String msg = "";
					 if(audit){//审核通过
						 msg = String.format(configHelper.getValue(SysConfigConstant.DECLARATIONFORM_PASS_NOTICE),formInfo.getUserName(),formInfo.getInvestAmt().setScale(2, BigDecimal.ROUND_DOWN),formInfo.getProductName());
					 }else { //审核不通过
						 msg = String.format(configHelper.getValue(SysConfigConstant.DECLARATIONFORM_FAIL_NOTICE),formInfo.getUserName(),formInfo.getInvestAmt().setScale(2, BigDecimal.ROUND_DOWN),formInfo.getProductName(),reason);
					 }
					 pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.LCSDECLARATIONFORMAUDITNOTICE, formInfo.getCfplannerId(), "报单审核通知", msg, null, true);
				}
			});
		}
	 
	 /**
	  * 相关计算
	  * @param investRecordWrapper
	  */
	 private void dispatchRecords(InvestRecordWrapper investRecordWrapper ) throws Exception{
		try {
			for (InvestRecordAware investRecordAware : investRecordAwares) {
				investRecordAware.investRecordProcess(investRecordWrapper);
			}
		} catch (Exception e) {
			LOGGER.warn(" investRecordProcess()  param={} Exception={} ",
					new Object[] { investRecordWrapper, e });
			throw e;
		}
	 }
}
