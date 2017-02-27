package com.linkwee.test.api;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.linkwee.test.TestSupport;
import com.linkwee.web.dao.AcAccountBindMapper;
import com.linkwee.web.model.acc.AcAccountBind;
import com.linkwee.xoss.util.MD5;

public class AccountBindMapperTest extends TestSupport {
    @Resource
    private AcAccountBindMapper bindMapper;
    
    //@Test
    public void doVerifyPayPwdState() {
		AcAccountBind bind = new AcAccountBind();
		//String userId = WebUtil.getUserId(head.getToken());
		bind.setUserId("2895bf4b-85b47-4a6d-b14a-5ef9d010a174");
		List<AcAccountBind> ab = bindMapper.selectByCondition(bind);
		if(ab.size()>0&&ab.get(0).getTranPwd()!=null&&ab.get(0).getTranPwd().length()>0){
			System.out.println("true");//密码
		}else{
			System.out.println("false");//密码空
		}
		
	}
    
    @Test
    public void doVerifyPayPwdSame() {
    	AcAccountBind bind = new AcAccountBind();
		bind.setUserId("2895bf4b-85b7-4a6d-b14a-5ef9d010a174");
		bind.setTranPwd(MD5.crypt("197854"));
//		int ab = bindMapper.updateByPrimaryKeySelective(bind);
//		System.out.println(ab);
		
		List<AcAccountBind> ab = bindMapper.selectByCondition(bind);
		
		String oldPwd = MD5.crypt("197854");
		if(ab.size()>0&&oldPwd!=null&&oldPwd.equals(ab.get(0).getTranPwd())){
			System.out.println("--------------源码正确------------------");//源码正确
		}else{
			System.out.println("--------------密码空-----------------");//密码空
		}
	}
}
