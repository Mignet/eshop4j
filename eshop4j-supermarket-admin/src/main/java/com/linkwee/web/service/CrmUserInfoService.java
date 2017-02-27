package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.base.ErrorCode;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CrmUserInfo;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmUserInfoService extends GenericService<CrmUserInfo,Long>{

	public static enum Error implements ErrorCode {
		REGISTER_ISEXISTS(140011, "手机号码已经存在"), 
		CHECK_CFGRECOMMEND_USER_ISREG(140012, "被推荐用户已经注册"), 
		CHECK_CFGRECOMMEND_USER_PROTECTED(140013,"被推荐用户处于保护期内"),
		CHECK_CFGRECOMMEND_USER_FROZEN(140014,"被推荐用户处于回退限制期内");

		Error(int code, String message) {
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
	}
	
	/**
	 * 查询CrmUserInfo列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 根据电话获取用户基础信息
	 * @param mobile
	 * @return
	 */
	CrmUserInfo selectCrmUserInfoByMobile(String mobile);
	
	/**
	 * 根据电话获取用户Id
	 * @param mobiles
	 * @return
	 */
    public List<String> selectRegUsersUserId(String[] mobiles);
    


	/**
	 * 用户是否存在
	 * @param head
	 * @param mobile
	 * @return
	 */
	boolean isExistsUserInfo(String mobile);

	/**
	 * 校验登录密码
	 * @param mobile
	 * @param password
	 * @return
	 */
	boolean docheckLoginPwd(String mobile, String password);

	/**
	 * 根据用户ID查用户信息
	 * @param userId
	 * @return
	 */
	CrmUserInfo queryUserInfoByUserId(String userId);

	/**
	 * 更新登录密码
	 * @param userId
	 * @param newPwd
	 */
	void updateLoginPassword(String userId, String newPwd);
	
	/**
	 * 根据userId修改用户信息
	 * @param crmUserInfo
	 */
	void updateByUserId(CrmUserInfo crmUserInfo);
	
	/**
	 * 根据电话号码批量查用户信息
	 * @param mobileList
	 * @return
	 */
	List<CrmUserInfo> queryUserListByMobileList(List<String> mobileList);

}
