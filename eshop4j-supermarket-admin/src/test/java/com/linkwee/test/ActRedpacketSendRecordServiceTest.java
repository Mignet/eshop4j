package com.linkwee.test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.linkwee.act.redpacket.service.ActRedpacketSendRecordService;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.enums.PersonalMsgTypeEnum;
import com.linkwee.web.model.SmMessageQueue;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.service.AcWithdrawApplyService;
import com.linkwee.web.service.SmMessageQueueService;

public class ActRedpacketSendRecordServiceTest extends TestSupport{
	@Resource
	private ActRedpacketSendRecordService actRedpacketSendRecordService;
	
	@Resource
	private AcWithdrawApplyService acWithdrawApplyService;
	
	@Resource
	private SmMessageQueueService smMessageQueueService;
	
	  @Test
	    public void TestAdvanceSendExpireMsg() throws Exception {
	    	start();
	    	actRedpacketSendRecordService.advanceSendExpireMsg();
	        end();
	    }
	  
	 
	  @Test
	    public void TestWithdraw() throws Exception {
	    	start();
	    	acWithdrawApplyService.queryWithdrawforJob();
	        end();
	    }
	  
	  @Test
	    public void TestBatchSendDiffContentMessageAndPmsg() throws Exception {
	    	start();
	    	List<SysMsg> listmsg = Lists.newArrayList();
	    	listmsg.add(fillSysMsg(2,PersonalMsgTypeEnum.WITHDRAWALS_INV,"1d06a0942eb54adfa6eafabf2f959033","您于(%s)的提现申请已处理完毕，请查看您的银行账户"));
	    	//listmsg.add(fillSysMsg(1,PersonalMsgTypeEnum.WITHDRAWALS_INV,"5eb56d12c3ff4fd5b585350637bf67fd","您于（X年X月X日xx:xx）的提现申请已处理完毕，请查看您的银行账户"));
	    	List<SmMessageQueue> listmessage = Lists.newArrayList();
	    	String msg = String.format("您于(%s)的提现申请已处理完毕，请查看您的银行账户", DateUtils.format(new Date(), DateUtils.FORMAT_SHORT_CN));
	    	//msg = "您11月14日的提现已经到账，请查看您的银行账户。 感谢您对T呗信赖与支持，常回来看看，我们有更多给力活动继续上新中。";
	    	System.out.println("msg:============="+msg);
	    	//encode(list.get(i).getContent().getBytes("gbk"))
	    	
	    	//listmessage.add(fillSmMessageQueue(2,"15919781141",msg,MsgModuleEnum.FINISHWITHDRAWAL));
	    	listmessage.add(fillSmMessageQueue(2,"18576651144",msg,MsgModuleEnum.FINISHWITHDRAWAL));
	    	smMessageQueueService.batchSendDiffContentMessageAndPmsg(listmessage, true, listmsg);
	    	
	    	
	        end();
	    }
	  
	  private SysMsg fillSysMsg(int appType,PersonalMsgTypeEnum msgType,String userId,String content){
			SysMsg msg = new SysMsg();
			msg.setContent(content);
			msg.setStatus(0);// 发布
			msg.setUserNumber(userId);
			msg.setReadStatus(0);// 未读
			msg.setAppType(appType);
			msg.setTypeName(msgType != null ? msgType.getValue() : null);
			msg.setStartTime(new Date());
			msg.setCrtTime(new Date());
			msg.setModifyTime(new Date());
			return msg;
			
		}
		
		private SmMessageQueue fillSmMessageQueue(int appType,String mobile,String content,MsgModuleEnum moduleId){
			SmMessageQueue model = new SmMessageQueue();
			model.setType(2);//个人消息
			model.setAppType(appType);
			model.setSendTo(mobile);
			model.setContent(content);
			model.setCreateTime(new Date());
			model.setStatus(1);//
			model.setSendTime(new Date());
			model.setMsgType((byte)1);
			model.setModuleId(moduleId.getValue());
			return model;
			
		}
	 
}
