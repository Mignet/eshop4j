package com.linkwee.web.service;

import java.util.List;
import java.util.Map;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.acc.AcAccountBind;
import com.linkwee.web.model.acc.AcAccountRecharge;
import com.linkwee.web.request.acc.ResetPayPwdRequest;
import com.linkwee.web.request.acc.TwoPwdRequest;
import com.linkwee.web.request.acc.UserWithdrawRequest;
import com.linkwee.web.response.acc.AcAccountTypeReponse;
import com.linkwee.web.response.acc.AcBankCodeResponse;
import com.linkwee.web.response.acc.CityInfoResponse;
import com.linkwee.web.response.acc.ProvinceInfoResponse;
import com.linkwee.web.response.acc.WithdrawBankCardResponse;
import com.linkwee.xoss.api.AppRequestHead;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月12日 19:10:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcAccountBindService extends GenericService<AcAccountBind,Long>{

	/**
	 * 查询Bind列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 是否设置了支付密码
	 * @param model
	 * @return true:已经设置,false:未设置支付密码
	 * @throws Exception
	 */
	boolean doVerifyPayPwdState(AppRequestHead head);
	
	/**
	 * 校验原密码是否一致
	 * @param model
	 * @return true:一致,false:不一致
	 * @throws Exception
	 */
	boolean doVerifyPayPwdSame(TwoPwdRequest req,AppRequestHead head);
	
	/**
	 * 个人设置(绑卡、实名验证)
	 * @param model
	 * @return true:已验证,false:未验证
	 * @throws Exception
	 */
	boolean queryAuthentication(String userId);

	/**
	 * 查询省份
	 * @throws Exception
	 */
	List<ProvinceInfoResponse> queryAllProvince();
	
	/**
	 * 查询城市
	 * @param provinceCode
	 * @throws Exception
	 */
	List<CityInfoResponse> queryCityByProvince(String provinceCode);

	/**
	 * 查询银行
	 * @throws Exception
	 */
	List<AcBankCodeResponse> queryAllBank();
	
	/**
	 * 初始化绑卡信息
	 * @throws Exception
	 */
	void initAccountBind(String userId,int userType);

	/**
	 * 提现查询银行卡信息
	 * @throws Exception
	 */
	WithdrawBankCardResponse queryWithdrawBankCard(String userId);

	/**
	 * 用户提现请求
	 * @throws Exception
	 */
	Map<String,String> userWithdrawApply(UserWithdrawRequest req);
	
	/**
	 * 账户余额
	 * @throws Exception
	 */
	double  queryAccountBalance(String userId);
	
	/**
	 * 提现中的金额
	 * @throws Exception
	 */
	double  queryWithdrawingAmount(String userId);
	
	/**
	 * 是否绑卡
	 * @throws Exception
	 */
	boolean isbindBankcard(String userId);

	/**
	 * 重置密码
	 * @throws Exception
	 */
	void resetPayPwd(ResetPayPwdRequest req);
	
	/**
	 * 通过用户ID获取绑定信息
	 * @throws Exception
	 */
	AcAccountBind selectAccountByUserId(String userId);
	
	/**
     * 查询账户类型
     * @throws Exception
     */
	List<AcAccountTypeReponse> queryAllAccountType(String userType);

	/**
     * 发送验证码到手机
     * @throws Exception
     */
	boolean sendVcode(String mobile,AppRequestHead head);

	/**
     * 校验验证码
     * @throws Exception
     */
	boolean checkVerifyCode(String mobile,String verifyCode);

	/**
     * 累计提现金额 
     * @throws Exception
     */
	Double queryWithdrawSummary(String userId);
	
	/**
     * 给用户账户充值
     * @throws Exception
     */
	String accountRecharge(AcAccountRecharge recharge) throws Exception;

	/**
     * 解绑
     * @throws Exception
     */
	void acAccountUnbund(String userId);

	/**
     * 根据bankId获取银行信息
     */
	AcBankCodeResponse queryBankByBankId(int bankId);

	/**
     * 根据bankname获取银行信息
     */
	AcBankCodeResponse queryBankByBankName(String bankname);
}
