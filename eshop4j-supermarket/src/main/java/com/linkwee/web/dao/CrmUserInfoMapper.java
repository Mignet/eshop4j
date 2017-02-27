package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.request.crm.InvotationRequest;
import com.linkwee.api.response.crm.InvotateListResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CrmUserInfo;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmUserInfoMapper extends GenericDao<CrmUserInfo,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CrmUserInfo> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	 /**
     * 封装对象条件查询
     * @param o
     * @return
     */
	List<CrmUserInfo> selectByCondition(CrmUserInfo o);
	
	/**
	 * 根据userId修改用户信息
	 * @param crmUserInfo
	 */
	void updateByUserId(CrmUserInfo crmUserInfo);
	
    /**
     * 查询手机号码已注册的用户Id
     * @param mobiles
     * @return
     */
    public List<String> selectRegCustomers(String[] mobiles);
      
    /**
     * 分页查询邀请客户信息
     * @param req
     * @param page
     * @return
     */
    public List<InvotateListResponse> investorPageList(InvotationRequest req, RowBounds page);

    /**
	 * 根据电话号码批量查用户信息
	 * @param mobileList
	 * @return
	 */
	List<CrmUserInfo> queryUserListByMobileList(@Param("mobileList")List<String> mobileList);

}
