package com.linkwee.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.linkwee.api.response.mc.MsgResp;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.test.TestSupport;
import com.linkwee.web.enums.MsgTypeEnum;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.SysMsgService;

public class SMSServiceTest extends TestSupport {

    @Resource
    private SysMsgService sysMsgService;
    @Resource
    private CimOrginfoService cimOrginfoService;

 
    //@Test
    public void testAddMsg() throws Exception {
    	start();
    	SysMsg msg = new SysMsg();
    	msg.setAppType(1);//理财师
    	msg.setContent("亲爱的理财师，欢迎加入领会!现在就去邀请客户注册【领会金服】并投资吧，超高佣金等着你哦~");
    	msg.setCrtTime(new Date());
    	msg.setStatus(0);
    	msg.setUserNumber("6e7614fba8c149afa382761aa4427c26");
    	sysMsgService.addMsg(msg);
        end();
    }
    //@Test
    public void testAddMsgBatch() throws Exception {
    	start();
    	SysMsg msg1 = new SysMsg();
    	msg1.setAppType(1);//理财师
    	msg1.setContent("亲爱的理财师，欢迎加入领会!现在就去邀请客户注册【领会金服】并投资吧，超高佣金等着你哦~");
    	
    	msg1.setCrtTime(new Date());
    	msg1.setStatus(0);
    	msg1.setStartTime(new Date());
    	msg1.setModifyTime(new Date());
    	
    	msg1.setUserNumber("5e543d3adbc44ce4891e5008c3cb753b");
    	SysMsg msg2 = new SysMsg();
    	msg2.setAppType(1);//理财师
    	msg2.setContent("亲爱的理财师，欢迎加入领会!现在就去邀请客户注册【领会金服】并投资吧，超高佣金等着你哦~");
    	msg2.setCrtTime(new Date());
    	msg2.setStatus(0);
    	msg2.setStartTime(new Date());
    	msg2.setModifyTime(new Date());
    	msg2.setUserNumber("6e7614fba8c149afa382761aa4427c26");
    	List<SysMsg> msgList = new ArrayList<SysMsg>();
    	msgList.add(msg1);
    	msgList.add(msg2);
    	sysMsgService.addMsgs(msgList);
        end();
    }
   // @Test
    public void testMsgDetail() throws Exception {
    	start();
    	SysMsg msg = sysMsgService.queryMsgDetail(String.valueOf(3));
    	System.out.println(msg.getContent());
        end();
    }
    @Test
    public void testSysMsgPage() throws Exception {
    	start();
    	Page<MsgResp> page  = new Page<MsgResp>(1,10); //默认每页10条
		Map<String,Object> conditions = new HashMap<String, Object>(); //查询条件
		conditions.put("msgType", "sys");//查询系统消息（公告）
		conditions.put("date", DateUtils.parse("1990-01-01",DateUtils.FORMAT_SHORT));
		//conditions.put("date", "2016-06-15");
       // conditions.put("type", MsgTypeEnum.SYS.getKey());
        conditions.put("appType", 1);
        PaginatorResponse<MsgResp> msgs = sysMsgService.queryMsgResp(page, conditions);
    	System.out.println(msgs.getTotalCount());
        end();
    }
    
    //CimOrginfoService
    //@Test
    public void testorgInfoPage() throws Exception {
    	start();
    	//Page<MsgResp> page  = new Page<MsgResp>(1,10); //默认每页10条
    	Page<CimOrginfo> page  = new Page<CimOrginfo>(1,10); //默认每页10条
		Map<String,Object> conditions = new HashMap<String, Object>(); //查询条件
		conditions.put("msgType", "sys");//查询系统消息（公告）
		conditions.put("date", DateUtils.parse("1990-01-01",DateUtils.FORMAT_SHORT));
        conditions.put("type", MsgTypeEnum.SYS.getKey());
        conditions.put("appType", 1);
        PaginatorResponse<CimOrginfo> msgs = cimOrginfoService.queryOrgList(page,conditions );
    	System.out.println(msgs.getTotalCount());
        end();
    }
    
}
