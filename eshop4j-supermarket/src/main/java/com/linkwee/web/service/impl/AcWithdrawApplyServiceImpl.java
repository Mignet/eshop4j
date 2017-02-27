package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bill99.schema.fo.settlement.BatchSettlementApplyResponse;
import com.bill99.schema.fo.settlement.BatchidQueryResponse;
import com.bill99.schema.fo.settlement.Pay2bankResultType;
import com.bill99.schema.fo.settlement.SettlementPkiApiResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.plugins.plfk.api.CustomerTool;
import com.linkwee.plugins.plfk.entity.DealInfoEntity;
import com.linkwee.plugins.plfk.entity.OrderInfoEntity;
import com.linkwee.web.dao.AcAccountErrorLogMapper;
import com.linkwee.web.dao.AcWithdrawApplyMapper;
import com.linkwee.web.model.acc.AcAccountBind;
import com.linkwee.web.model.acc.AcAccountErrorLog;
import com.linkwee.web.model.acc.AcBalanceRecord;
import com.linkwee.web.model.acc.AcBankCardInfo;
import com.linkwee.web.model.acc.AcWithdrawApply;
import com.linkwee.web.model.acc.Bill99Config;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.AcBalanceRecordService;
import com.linkwee.web.service.AcBankCardInfoService;
import com.linkwee.web.service.AcWithdrawApplyService;


 /**
 * 
 * @描述：AcWithdrawApplyService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("acWithdrawApplyService")
public class AcWithdrawApplyServiceImpl extends GenericServiceImpl<AcWithdrawApply, Long> implements AcWithdrawApplyService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AcWithdrawApplyServiceImpl.class);
	
	@Resource
	private AcWithdrawApplyMapper acWithdrawApplyMapper;
	
	@Resource
	private AcBankCardInfoService acBankCardInfoService;
	
	@Resource
	private AcBalanceRecordService acBalanceRecordService;
	
	@Resource
	private AcAccountBindService acAccountBindService;
	
	@Resource
	private Bill99Config bill99config;
	
	@Resource 
	private AcAccountErrorLogMapper acAccountErrorLogMapper;
	
	@Override
    public GenericDao<AcWithdrawApply, Long> getDao() {
        return acWithdrawApplyMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- AcWithdrawApply -- 排序和模糊查询 ");
		Page<AcWithdrawApply> page = new Page<AcWithdrawApply>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<AcWithdrawApply> list = this.acWithdrawApplyMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
    
    
    @Override
	public DataTableReturn selectDetailByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- AcWithdrawApply -- 排序和模糊查询 ");
		Page<AcWithdrawApply> page = new Page<AcWithdrawApply>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<AcWithdrawApply> list = this.acWithdrawApplyMapper.selectDetailBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public PaginatorResponse<AcWithdrawApply> queryWithdrawLog(Page<AcWithdrawApply> page,
			Map<String, Object> conditions) {
		PaginatorResponse<AcWithdrawApply> acWithdrawApplyResponse = new PaginatorResponse<AcWithdrawApply>();
		List<AcWithdrawApply> accountList = acWithdrawApplyMapper.queryWithdrawLog(page,conditions);
		acWithdrawApplyResponse.setDatas(accountList);
		acWithdrawApplyResponse.setValuesByPage(page);
		return acWithdrawApplyResponse;
	}

	@Override
	@Transactional
	public Map<String, String> approveWithdraw(List<String> listStr) {
		Map<String,String> returnStr = new HashMap<String, String>();
		List<AcWithdrawApply> list = new ArrayList<AcWithdrawApply>();
		for(String id: listStr){
			//根据ID查询提现记录
			AcWithdrawApply with = acWithdrawApplyMapper.selectWithdrawById(id);
			if(with!=null){
				list.add(with);
			}
			if(with!=null&&with.getDealId()!=null){
				returnStr.put("code", String.format("id【%s】用户名【%s】提现流水号【%s】存在批次号【%s】时间【%s】请技术人员进行排查", id,with.getUserName(),with.getOrderId(),with.getDealId(),with.getConfirmTime()));
				return returnStr;
			}
		}
		
		if(listStr.size()!=list.size()){
			returnStr.put("code", "前端传来的数据跟后端查询出来数据数量不匹配");
			return returnStr;
		}
		
		Subject currentUser = ThreadContext.getSubject();//获取当前操作用户
		
		String version = bill99config.getVersion();//版本号 固定值：1.0，接口版本识别号
		String serviceType = bill99config.getPlfkServiceType();//"fo.batch.settlement.pay";//提交类型
		String memberCode = bill99config.getMemberCode();//"10080659594";  //商户编号
		String featureCode = bill99config.getFeatureCode();//"F889";//加密类型
		String payerAcctCode = bill99config.getPayerAcctCode();//memberCode + "01";//付款方帐号 快钱给商户的账户
		String batchNo = "batchNo_"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//批次号  大小写字母，数字和符号
		String applyDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//发起日期 YYYYMMDDHHMMSS
		String merchantName = bill99config.getMerchantName();//"深圳市前海领会科技有限公司";//付款商户名称  60 个字符或数字，30个中文
		String totalCnt = list.size()+"";//总笔额
		String feeType = "1";//付费方式
		String cur = "RMB";//币种 按默认值填写：RMB
		String checkAmtCnt = "0";//是否验证金额 0:验证; 1:不验证
		String batchFail = "1";//是否整批失败  0:整批失败; 1:不整批失败
		String rechargeType = "1";//充值方式 0:代扣，1:充值，2:垫资
		String autoRefund = "0";//是否自动退款 0:自动退款; 1:不自动退款
		String phoneNoteFlag = "0";//是否短信通知 0:通知; 1:不通知
		
		/** 对公对私0:企业; 1:个人 */ 
		String payeeType = "1";
		/** 快钱交易备注 */
		String memo = "领会科技提现";
		/** 银行交易用备注 */
		String bankPurpose = "银行交易备注";
		/** 银行交易备注 */
//		String bankMemo = "交易备注";
		/** 收款方通知知内容 */
		String payeeNote = "投呗提现";
		/** 到账时效 */
		String period = "";
        BigDecimal totalamount = new BigDecimal(0);//总金额  无小数点，个位代表分
        DecimalFormat df = new DecimalFormat("#");
        
		List<OrderInfoEntity> orders = new ArrayList<OrderInfoEntity>();
		for (AcWithdrawApply apply:list) {
			OrderInfoEntity orderInfo = new OrderInfoEntity();
			AcBankCardInfo card = acBankCardInfoService.selectByBankCardId(apply.getBankCardId());
			BigDecimal applyAmount = apply.getAmount().multiply(new BigDecimal(100));//快钱批量接口以分为单位
			totalamount = totalamount.add(applyAmount);
			orderInfo.setMerchantId(apply.getOrderId());//商家订单号 不能填写中文
			orderInfo.setAmt(df.format(applyAmount));
			orderInfo.setBank(card.getBankName());//"中国工行"
			orderInfo.setName(apply.getUserName());//户名
			orderInfo.setBankCardNo(card.getBankCard());//银行卡号
			orderInfo.setBranchBank(card.getKaiHuHang());//开户行
			orderInfo.setPayeeType(payeeType);//对公对私0:企业; 1:个人 
			orderInfo.setCity(card.getCity());
			orderInfo.setMemo(memo);
			orderInfo.setBankPurpose(bankPurpose);
			orderInfo.setPayeeNote(payeeNote);
			orderInfo.setPayeeMobile(apply.getMobile());
			orderInfo.setPeriod(period);
			orders.add(orderInfo);
			//先保存批次号
			AcWithdrawApply updateApply = new AcWithdrawApply();
			updateApply.setOrderId(apply.getOrderId());
			updateApply.setDealId(batchNo);
			updateApply.setConfirmTime(new Date());//审核时间
			acWithdrawApplyMapper.updateByPrimaryKeySelective(updateApply);
		}

		DealInfoEntity dealInfo = new DealInfoEntity();
		dealInfo.setPayerAcctCode(payerAcctCode);
		dealInfo.setBatchNo(batchNo);
		dealInfo.setApplyDate(applyDate);
		dealInfo.setName(merchantName);
		dealInfo.setTotalAmt(df.format(totalamount));
		dealInfo.setTotalCnt(totalCnt);
		dealInfo.setFeeType(feeType);
		dealInfo.setCur(cur);
		dealInfo.setCheckAmtCnt(checkAmtCnt);
		dealInfo.setBatchFail(batchFail);
		dealInfo.setRechargeType(rechargeType);
		dealInfo.setAutoRefund(autoRefund);
		dealInfo.setPhoneNoteFlag(phoneNoteFlag);
		dealInfo.setOrdersInfo(orders);

		dealInfo.setServiceType(serviceType);//提交类型
		dealInfo.setVersion(version);//版本号
		dealInfo.setFeatureCode(featureCode);//加密类型
		dealInfo.setMemberCode(memberCode);//商户编号

		CustomerTool ct = new CustomerTool();
		
		//请求快钱批量接口
		SettlementPkiApiResponse response = ct.apply_ws(dealInfo);
		//返回数据进行解密
		BatchSettlementApplyResponse bsar = (BatchSettlementApplyResponse) ct.unseal(response, dealInfo);
		
		if(bsar==null){
			LOGGER.error(">>>>>>>>>批量扣款失败,解密数据为空");
			returnStr.put("code", "审批异常,快钱返回数据为空");
			AcAccountErrorLog log = new AcAccountErrorLog();
			log.setBatchNo(batchNo);
			log.setClassMethod("AcWithdrawApplyServiceImpl【approveWithdraw】-(bsar==null)");
			log.setCreateTime(new Date());
			log.setSendParm(JSONArray.toJSONString(dealInfo).toString());
			log.setReturnParam(JSONArray.toJSONString(response).toString());
			log.setRemark("审批异常,快钱返回数据为空");
			acAccountErrorLogMapper.insertSelective(log);
			return returnStr;
		}
		
		if(bsar!=null&&bsar.getResponseBody()!=null&&bsar.getResponseBody().getBatchNo()!=null
				&&Integer.parseInt(bsar.getResponseBody().getTotalApplySuccCnt())>0){
			LOGGER.debug(">>>>>>>>>>提交的批次号为:{},申请成功笔数为:{}",bsar.getResponseBody().getBatchNo(),bsar.getResponseBody().getTotalApplySuccCnt());
		    if(bsar.getResponseBody().getPay2bankLists().size()>0){
		    	for(Pay2bankResultType pay: bsar.getResponseBody().getPay2bankLists()){
		    		
		    		if(pay.getPay2bank().getMerchantId()==null){
		    			returnStr.put("code", "快钱返回数据异常,订单号为空");
		    		}
		    		AcWithdrawApply apply = new AcWithdrawApply();
		    		apply.setOrderId(pay.getPay2bank().getMerchantId());
		    		apply.setDealId(bsar.getResponseBody().getBatchNo());
		    		apply.setDebitCharge(pay.getFee()!=null?new BigDecimal(pay.getFee()):null);
		    		apply.setResultCode(pay.getStatus());
		    		apply.setStatus("101".equals(pay.getStatus())?"2":returnStatus(pay.getStatus()));
		    		
		    		if("112".equals(pay.getStatus())||"114".equals(pay.getStatus())){
			    		AcWithdrawApply acw = new AcWithdrawApply();
			    		acw.setOrderId(pay.getPay2bank().getMerchantId());
			    		//原提现申请记录
			    		AcWithdrawApply with = acWithdrawApplyMapper.selectOneByCondition(acw);
			    		AcAccountBind bind = new AcAccountBind();
			    		bind.setUserId(with.getUserId());
			    		//失败需要解冻
				    	if("0".equals(with.getIsThaw())){
				    		//用户绑卡账户
				    		AcAccountBind rebind = acAccountBindService.selectAccountByUserId(with.getUserId());
				    		//总金额=原金额+提现总金额  补回金额
				    		BigDecimal oldTotalAmount = new BigDecimal(rebind.getTotalAmount());
				    		BigDecimal oldFreezeAmount = new BigDecimal(rebind.getFreezeAmount());
				    		BigDecimal oldWithdrawAmount = new BigDecimal(rebind.getWithdrawAmount());
				    		bind.setTotalAmount(oldTotalAmount.add(with.getTotalAmount()).toString());
				    		bind.setFreezeAmount(oldFreezeAmount.subtract(with.getTotalAmount()).toString());
				    		bind.setWithdrawAmount(oldWithdrawAmount.subtract(with.getTotalAmount()).toString());
				    		acAccountBindService.update(bind);
				    		apply.setIsThaw("1");//已解冻
				    		
				    	}
		    		}
		    		apply.setUpdatedDate(new Date());
		    		apply.setUpdatedBy(currentUser != null?JSON.toJSONString(currentUser.getPrincipal()):null);
		    		apply.setConfirmTime(new Date());//审核时间
		    		apply.setRemark(remarkInfo(pay));
			    	acWithdrawApplyMapper.updateByPrimaryKeySelective(apply);
			    }
		    }
			
		}else{
			LOGGER.error(">>>>>>>>>批量扣款失败:{}",JSONArray.toJSONString(bsar).toString());
			AcAccountErrorLog log = new AcAccountErrorLog();
			log.setBatchNo(batchNo);
			log.setClassMethod("AcWithdrawApplyServiceImpl【approveWithdraw】-else");
			log.setCreateTime(new Date());
			log.setSendParm(JSONArray.toJSONString(dealInfo).toString());
			log.setReturnParam(JSONArray.toJSONString(bsar).toString());
			log.setRemark("快钱返回异常");
			acAccountErrorLogMapper.insertSelective(log);
			if(bsar!=null&&bsar.getResponseBody()!=null){
				returnStr.put("code", "快钱返回异常,状态为:"+bsar.getResponseBody().getStatus());
				return returnStr;
			}
		}
		returnStr.put("code", "00");
		return returnStr;
	}
	
	public String remarkInfo(Pay2bankResultType pay){
		String remark = null;
		if(pay.getErrorCode()!=null&&pay.getErrorCode().length()>0){
			remark = "ErrorCode:"+pay.getErrorCode();
		}
		if(pay.getErrorMsg()!=null&&pay.getErrorMsg().length()>0){
			remark = "ErrorMsg:"+pay.getErrorMsg();
		}
		if(pay.getBankErrorCode()!=null&&pay.getBankErrorCode().length()>0){
			remark = "BankErrorCode:"+pay.getBankErrorCode();
		}
		if(pay.getBankErrorMsg()!=null&&pay.getBankErrorMsg().length()>0){
			remark = "BankErrorMsg:"+pay.getBankErrorMsg();
		}
		return remark;
	}

	@Override
	@Transactional
	public List<AcWithdrawApply> queryWithdrawforJob() {
		//1.根据条件查询需要查询的批次号 status in('2','8') and result_code='101'
		List<AcWithdrawApply> list = acWithdrawApplyMapper.queryWithdrawforJob();

		//2.循环批次号查询结果
		for(AcWithdrawApply apply:list){
			String version = bill99config.getVersion();//"1.0";
			String serviceType = bill99config.getQueryServiceType();//"fo.batch.settlement.batchidquery";
			String memberCode = bill99config.getMemberCode();//10080659594";//商户号
			String featureCode = bill99config.getFeatureCode();//"F889";
			String batchNo = apply.getDealId();
			String listFlag = "0";// 是否显示详细明细 0：查询返回明细；1：不返回明细
			String page = "1";//页码为空，则默认为1
			String pageSize = "1000";//页码显示条数

			DealInfoEntity dealInfo = new DealInfoEntity();
			dealInfo.setBatchNo(batchNo);
			dealInfo.setListFlag(listFlag);
			dealInfo.setPage(page);
			dealInfo.setPageSize(pageSize);
			dealInfo.setServiceType(serviceType);
			dealInfo.setVersion(version);
			dealInfo.setFeatureCode(featureCode);
			dealInfo.setMemberCode(memberCode);

			CustomerTool ct = new CustomerTool();
			
			SettlementPkiApiResponse response = ct.apply_ws(dealInfo);
			BatchidQueryResponse bidqr = (BatchidQueryResponse) ct.unseal(response,dealInfo);// 得到解密数据
			
			if(bidqr!=null&&bidqr.getResponseBody()!=null&&bidqr.getResponseBody().getBatchList()!=null){
				LOGGER.debug("批次号【{}】查询到的付款成功笔数为{}",batchNo,bidqr.getResponseBody().getBatchList().getTotalApplySuccCnt());
			}else{
				LOGGER.error("批次号【{}】查询的结果【{}】",batchNo,JSONArray.toJSONString(response).toString());
			}
			
			//3.根据结果同步数据状态，返回结果状态，审批状态
			if(bidqr!=null&&bidqr.getResponseBody()!=null&&bidqr.getResponseBody().getBatchList()!=null&&
					bidqr.getResponseBody().getBatchList().getPay2bankLists().size()>0){
		    	for(Pay2bankResultType pay: bidqr.getResponseBody().getBatchList().getPay2bankLists()){
		    		AcWithdrawApply newapply = new AcWithdrawApply();
		    		AcWithdrawApply acw = new AcWithdrawApply();
		    		acw.setOrderId(pay.getPay2bank().getMerchantId());
		    		//原提现申请记录
		    		AcWithdrawApply with = acWithdrawApplyMapper.selectOneByCondition(acw);
		    		//用户绑卡账户
		    		AcAccountBind rebind = acAccountBindService.selectAccountByUserId(with.getUserId());
		    		AcAccountBind bind = new AcAccountBind();
		    		bind.setUserId(with.getUserId());
		    		
			    	//状态成功后，添加明细记录到银行账户记录表tac_balance_record  
		    		//同时将冻结的金额、提现的金额清除
			    	if("111".equals(pay.getStatus())&&"0".equals(with.getIsThaw())){
			    		AcBalanceRecord bala = new AcBalanceRecord();
			    		bala.setUserType(with.getUserType());
			    		bala.setUserId(with.getUserId());
			    		bala.setBankCardId(with.getBankCardId());
			    		bala.setTransType(2);//提现 
			    		bala.setOrderId(with.getOrderId());
			    		bala.setDealId(with.getDealId());
			    		bala.setTransAmount(with.getAmount());
			    		bala.setFee(with.getFee());
			    		bala.setTransDate(new Date());
			    		bala.setCreateTime(new Date());
			    		bala.setRemark("提现");
			    		acBalanceRecordService.insert(bala);
			    		
			    		//同时将冻结的金额、提现的金额清除
			    		BigDecimal oldFreezeAmount = new BigDecimal(rebind.getFreezeAmount());
			    		BigDecimal oldWithdrawAmount = new BigDecimal(rebind.getWithdrawAmount());
			    		bind.setFreezeAmount(oldFreezeAmount.subtract(with.getTotalAmount()).toString());
			    		bind.setWithdrawAmount(oldWithdrawAmount.subtract(with.getTotalAmount()).toString());
			    		acAccountBindService.update(bind);
			    		newapply.setIsThaw("2");//已清除
			    		
			    	}
			    	//失败需要解冻
			    	if(("112".equals(pay.getStatus())||"114".equals(pay.getStatus()))&&"0".equals(with.getIsThaw())){
			    		//总金额=原金额+提现总金额  补回金额
			    		BigDecimal oldTotalAmount = new BigDecimal(rebind.getTotalAmount());
			    		BigDecimal oldFreezeAmount = new BigDecimal(rebind.getFreezeAmount());
			    		BigDecimal oldWithdrawAmount = new BigDecimal(rebind.getWithdrawAmount());
			    		bind.setTotalAmount(oldTotalAmount.add(with.getTotalAmount()).toString());
			    		bind.setFreezeAmount(oldFreezeAmount.subtract(with.getTotalAmount()).toString());
			    		bind.setWithdrawAmount(oldWithdrawAmount.subtract(with.getTotalAmount()).toString());
			    		acAccountBindService.update(bind);
			    		newapply.setIsThaw("1");//已解冻
			    		
			    	}
			    	
			    	//更新提现记录信息
		    		newapply.setResultCode(pay.getStatus());
		    		newapply.setStatus(returnStatus(pay.getStatus()));
		    		newapply.setUpdatedDate(new Date());
		    		newapply.setOrderId(pay.getPay2bank().getMerchantId());
		    		newapply.setRemark(remarkInfo(pay));
		    		newapply.setNoticeTime(new Date());
		    		acWithdrawApplyMapper.updateByPrimaryKeySelective(newapply);
			    }
		    }
			
		}
		
		return null;
	}
	
	public String returnStatus(String status){
		if("101".equals(status)){//进行中
			return "8";//打款处理中
		}
		if("111".equals(status)){//出款成功
			return "5";
		}
		if("112".equals(status)){//出款失败
			return "6";
		}
		if("114".equals(status)){//已经退款
			return "7";
		}
		return null;
	}
	

}
