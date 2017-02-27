package com.linkwee.test.api;

import org.junit.Test;

import com.linkwee.api.response.acc.AcAccountTypeReponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.openapi.response.AccountBankReponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;

public class AcAWithdrawApplyControllerTest extends BaseTest{
	
	
	@Test//审核提现
	public void getApproveWithdraw() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/acwithdrawapply/approveWithdraw",this.getToken(),AcAccountTypeReponse.class,null);
		logger.debug(baseResponse.toString());
	}
}
