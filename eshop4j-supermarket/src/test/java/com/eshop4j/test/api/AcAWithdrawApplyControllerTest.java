package com.eshop4j.test.api;

import org.junit.Test;

import com.eshop4j.api.response.acc.AcAccountTypeReponse;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.openapi.response.AccountBankReponse;
import com.eshop4j.test.BaseTest;
import com.eshop4j.test.TestHelper;
import com.eshop4j.test.enums.AppEnum;
import com.eshop4j.test.enums.PathEnum;

public class AcAWithdrawApplyControllerTest extends BaseTest{
	
	
	@Test//审核提现
	public void getApproveWithdraw() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/acwithdrawapply/approveWithdraw",this.getToken(),AcAccountTypeReponse.class,null);
		logger.debug(baseResponse.toString());
	}
}
