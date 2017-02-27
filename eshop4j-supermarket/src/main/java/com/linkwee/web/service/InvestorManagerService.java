package com.linkwee.web.service;

import java.util.Map;

import com.linkwee.core.base.ErrorCode;
import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.crm.InvestedUserDataStatisticResp;
import com.linkwee.web.model.crm.InvestorManagerDetailResp;
import com.linkwee.web.model.crm.UserDataStatisticResp;
import com.linkwee.web.request.ListDetailRequest;

/**
 * 
 * @描述：理财师销列表
 *
 * @author ch
 * @时间 2016年4月8日下午5:43:30
 *
 */
public interface InvestorManagerService {

	public static enum Error implements ErrorCode{
		SESSION_EXPIRE(142001, "会话已过期，请重新登录"),
		SALE_USER_NOT_EXIST(142002, "理财师不存在"),
		CUSTOMER_NOT_FREE(150000, "客户为非自由客户"),
		CUSTOMER_NOT_EXIST(150001, "客户不存在"),
		SELF_IS_CFP(150001, "客户自己是理财师"),
		CFP_MOBILE_ERROR(150001, "新理财师帐号错误"),
		PARAM_ERROR(141005, "参数错误");
		Error(int code,String message){
			this.code = code;
			this.message = message;
		}
		private int code = 0;
		private String message = "";
		public int getCode() {
			return code;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}
	
	/**
	 * 查询投资者详情,根据手机号码
	 * 
	 * @param mobile
	 * @return
	 */
	public InvestorManagerDetailResp queryInvestorDetail(String mobile);

	/**
	 * 重新绑定理财师
	 * @param mobile
	 * @param lcsMobile
	 * @param changeType
	 * @return
	 */
	public void changeCfplanner(CrmInvestor investor, CrmCfplanner cfplanner, String changeType);

	/**
	 * 获取邀请的好友列表数据
	 * @param dataTable
	 * @param investRecordRequest
	 * @return
	 */
	public DataTableReturn selectFreindsList(DataTable dataTable, ListDetailRequest req);
	
	public Map<String,Object> queryInvestorAndMoney();
	
	public Map<String, Object> queryInvestorAndMoneyByDate(String startTime, String endTime);
	
	/**
	 * 总投资人数和投资额
	 * @return
	 */
	public Map<String, Object> queryTotalInvestorAndMoney();

	/**
	 * 用户注册数据统计
	 * @return
	 */
	public UserDataStatisticResp queryUserRegisterTotalData();

	/**
	 * 用户投资数据统计
	 * @return
	 */
	public InvestedUserDataStatisticResp queryUserInvestTotalData();
	
}
