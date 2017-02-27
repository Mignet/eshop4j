package com.eshop4j.web.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.api.request.cim.ProductRecommendChooseRequest;
import com.eshop4j.api.request.crm.InvotationRequest;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.CrmInvestor;
import com.eshop4j.web.model.CrmInvestorRecommend;
import com.eshop4j.web.model.crm.InvestorBindPlatformDatable;
import com.eshop4j.web.model.crm.InvestorBindPlatformListResp;
import com.eshop4j.web.model.crm.InvotateUserListResp;
import com.eshop4j.web.request.orgInfo.OrgRecommendChooseRequest;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmInvestorMapper extends GenericDao<CrmInvestor,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CrmInvestor> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	 /**
     * 封装对象条件查询
     * @param o
     * @return
     */
	List<CrmInvestor> selectByCondition(CrmInvestor o);
	
	/**
	 * 更新投资用户二维码
	 * @param record
	 * @return
	 */
	int updateInvQrByUserId(CrmInvestor record);
	
	/**
	 * 查询手机号码已注册的投资用户userId
	 * @param mobiles
	 * @return
	 */
	public List<String> selectRegInvestors(String[] mobiles);
	
	/**
	 * 在投资用户表中查询被该用户推荐的用户userId
	 * @param userId
	 * @return
	 */
	List<String> selectRefRegInvestors(String userId);

	/**
	 * 根据环信帐号查投资用户信息
	 * @param userId
	 * @return
	 */
	List<CrmInvestor> queryInvestorByEasemob(List<String> easemobAcctList);

	/**
	 * 查环信token
	 * @return
	 */
	Map<String, Object> queryEaseToken();

	/**
	 * 更新环信token
	 */
	void updateEaseToken(String token);

	/**
	 * 根据userId更新投资者信息
	 * @param crmInvestor
	 * @return
	 */
	int updateByUserId(CrmInvestor crmInvestor);

	/**
	 * 首单时间
	 * @param userId
	 * @return
	 */
	Date queryFirstRcpDate(String userId);

	/**
	 * 删除归属理财师
	 * @param userId
	 */
	void removeCfplanner(String userId);
	
	
	/**
	 * 查询投资人的绑定的机构信息
	 * @author yalin 
	 * @date 2016年9月29日 下午2:32:32  
	 * @param dt
	 * @param page
	 * @return
	 */
	public List<InvestorBindPlatformListResp> queryInvestorBindPlatformList(@Param("dt") InvestorBindPlatformDatable dt,RowBounds page);
    /**
     * 查询多个对象
     *
     * @return 对象集合
     * 其中电话号码，姓名可以进行模糊查询
     */
	List<CrmInvestor> selectListByConditionNew(CrmInvestor t);
	
	/**
	 * 通过投资用户id查询用户的当前理财师
	 * @author yalin 
	 * @date 2016年10月17日 下午4:58:16  
	 * @param investUserId
	 * @return
	 */
	public CrmInvestor queryPlannerByInvestUserId(String investUserId);

	/**
	 * 根据客户条件筛选理财师所有客户 含是否推荐该产品信息
	 * @param productRecommendChooseRequest
	 * @return
	 */
	List<CrmInvestorRecommend> selectCrmInvestorRecommend(ProductRecommendChooseRequest productRecommendChooseRequest);

	/**
	 * 根据客户条件筛选理财师所有客户 含是否推荐该机构信息
	 * @param orgRecommendChooseRequest
	 * @return
	 */
	List<CrmInvestorRecommend> selectCrmInvestorRecommendOrg(OrgRecommendChooseRequest orgRecommendChooseRequest);

	/**
	 * 客户邀请记录列表
	 * @param req
	 * @param page
	 * @return
	 */
	List<InvotateUserListResp> queryInvitationCustomerRecord(InvotationRequest req, Page<InvotateUserListResp> page);

	/**
	 * 客户邀请统计
	 * @param userId
	 * @return
	 */
	Map<String, Integer> queryInvitationCustomerRecordStatistics(String userId);
	
	
}
