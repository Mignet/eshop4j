package com.eshop4j.tc.fee.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.eshop4j.core.constant.SysConfigConstant;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.util.NumberUtils;
import com.eshop4j.tc.fee.model.TCFeePay;
import com.eshop4j.tc.fee.service.TCFeePayService;
import com.eshop4j.web.dao.TCFeePayMapper;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.web.enums.MsgModuleEnum;
import com.eshop4j.web.model.SmMessageQueue;
import com.eshop4j.web.model.SmMessageTemplate;
import com.eshop4j.web.model.acc.AcBalanceRecord;
import com.eshop4j.web.model.mc.SysMsg;
import com.eshop4j.web.model.mc.SysPushMessage;
import com.eshop4j.web.model.weixin.WeiXinMsgRequest;
import com.eshop4j.web.service.AcBalanceRecordService;
import com.eshop4j.web.service.SmMessageQueueService;
import com.eshop4j.web.service.SysMsgService;
import com.eshop4j.web.service.WeiXinMsgService;
import com.eshop4j.xoss.helper.DateUtils;
import com.eshop4j.xoss.helper.PushMessageHelper;
import com.eshop4j.xoss.helper.ThreadpoolService;


 /**
 * 
 * @描述：CimFeePayService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年09月08日 16:07:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimFeePayService")
public class TCFeePayServiceImpl extends GenericServiceImpl<TCFeePay, Long> implements TCFeePayService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TCFeePayServiceImpl.class);
	
	@Autowired
	private TCFeePayMapper feePayMapper;
	
	@Autowired
	private AcBalanceRecordService  balanceRecordService;
	@Resource
	private SmMessageQueueService messageQueueService;
	
	@Resource
	private PushMessageHelper pushMessageHelper;
	
	@Resource
	private SysMsgService sysMsgService;
	
	@Resource
	private WeiXinMsgService weiXinMsgService;
	
	@Override
    public GenericDao<TCFeePay, Long> getDao() {
        return feePayMapper;
    }
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override 
	public void payFee(List<TCFeePay> noPayFeeList, String month, Date time,String operator,Collection<SysMsg> sysMsgs,Collection<SysPushMessage> pushMsgs,Collection<SmMessageQueue> messages) throws Exception {
		List<String> noPayFeeIds = Lists.newArrayListWithCapacity(noPayFeeList.size());
		//消息模板
		SmMessageTemplate condit = new SmMessageTemplate();
		condit.setModuleId( MsgModuleEnum.COMMISSIONPAY.getValue());
		condit.setAppType(AppTypeEnum.CHANNEL.getKey());//提现模板 理财师和投资的一致
		SmMessageTemplate messageTmp = messageQueueService.queryMessageTemplete(condit);
		
		final List<WeiXinMsgRequest> wxList = new ArrayList<WeiXinMsgRequest>();
		try{
			List<AcBalanceRecord> balanceRecords = Lists.newArrayListWithCapacity(noPayFeeList.size());
			AcBalanceRecord balanceRecord;
			for (TCFeePay noPayFee : noPayFeeList) {
				noPayFeeIds.add(noPayFee.getBillId());
				balanceRecord = new AcBalanceRecord();
				balanceRecord.setOrderId(com.eshop4j.core.util.StringUtils.getUUID());
				balanceRecord.setUserType(1);
				balanceRecord.setUserId(noPayFee.getUserId());
				balanceRecord.setUserName(noPayFee.getUserName());
				balanceRecord.setMobile(noPayFee.getUserMobile());
				balanceRecord.setTransType(12);
				balanceRecord.setTypeName("佣金与leader奖");
				balanceRecord.setRemark(month +"月佣金与leader奖");
				balanceRecord.setTransAmount(noPayFee.getFeeAmount());
				balanceRecord.setTransDate(time);
				balanceRecord.setSerialNumber(noPayFee.getBillId());
				balanceRecord.setCreatePerson(operator);
				balanceRecords.add(balanceRecord);
				if(noPayFee.getFeeAmount()!=null && noPayFee.getFeeAmount().intValue() > 0 && messageTmp != null){
					String content = String.format(messageTmp.getContent(),DateUtils.getCommissionMonth(),noPayFee.getFeeAmount().setScale(2, BigDecimal.ROUND_DOWN));
					sysMsgs.add(sysMsgService.fillSysMsg(AppTypeEnum.CHANNEL.getKey(),null,noPayFee.getUserId(),content));
					messages.add(messageQueueService.fillSmMessageQueue(AppTypeEnum.CHANNEL.getKey(),noPayFee.getUserMobile(),content,MsgModuleEnum.COMMISSIONPAY));
					pushMsgs.add(new SysPushMessage(noPayFee.getUserId(),month+"佣金发放",content));
				}
				//封装微信消息实体
				WeiXinMsgRequest wx = new WeiXinMsgRequest();
				wx.setTemkey(SysConfigConstant.ARRIVAL_REMINDER_COMMISSION);
				wx.setUseId(noPayFee.getUserId());
				wx.setUseType("1");
				wx.setArrivalAmount(NumberUtils.getFormat(noPayFee.getFeeAmount(), "0.00")+"元");//到账金额
				wx.setArrivalTime(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));//到账时间
				wx.setArrivalDetail(month.substring(0, 4)+"年"+month.substring(4, 6) +"月佣金");//到账详情
				wxList.add(wx);
			}
			feePayMapper.updateStatus(noPayFeeIds, 1, "processing", "处理中");
			balanceRecordService.grantProfit(balanceRecords);
			feePayMapper.updateStatus(noPayFeeIds, 2,"success","成功");
		}catch(Exception e){
			feePayMapper.updateStatus(noPayFeeIds, 3,"failure","失败");
			throw e;
		}
		//推送微信消息
		ThreadpoolService.execute(new Runnable() {
			@Override
			public void run() {
		          weiXinMsgService.sendWeiXinMsgListCommon(wxList);
			}
		});	
	}
    


}
