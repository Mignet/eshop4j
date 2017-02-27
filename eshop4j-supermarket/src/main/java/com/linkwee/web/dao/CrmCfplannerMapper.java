package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.request.crm.InvotationRequest;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.crm.InvotateUserListResp;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:25:55
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfplannerMapper extends GenericDao<CrmCfplanner,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CrmCfplanner> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	 /**
     * 封装对象条件查询
     * @param o
     * @return
     */
	List<CrmCfplanner> selectByCondition(CrmCfplanner o);

	/**
	 * 查投资用户的理财师
	 * @param mobile
	 * @return CrmCfplanner
	 */
	CrmCfplanner queryCfplannerByInvestMobile(String investorMobile);
	
	/**
	 * 根据环信帐号查用户信息
	 * @param easemobAcctList
	 * @return
	 */
	List<CrmCfplanner> queryCfplannerByEasemob(List<String> easemobAcctList);
	
	/**
	 * 查投资者的理财师
	 * @param investorUserId 投资人userId
	 * @return
	 */
	CrmCfplanner queryCfplannerByInvestor(String investorUserId);
	
	/**
	 * 查询手机号码已注册的理财师userId
	 * @param mobiles
	 * @return
	 */
	List<String> selectRegCfplanners(String[] mobiles);
	
	/**
	 * 更新理财师二维码字段
	 * @param record
	 * @return
	 */
	int updateCfpQrByUserId(CrmCfplanner record);

	/**
	 * 查理财师的团队人数
	 * @param userId
	 * @return
	 */
	int queryTeamMemberCount(String userId);

	/**
	 * 修改理财师信息
	 * @param crmCfplanner
	 * @return
	 */
	int updateByUserId(CrmCfplanner crmCfplanner);

	
	/**
	 * 更新理财师等级与经验
	 * @param userId 理财师用户编号
	 * @param level 理财师等级
	 * @param experience 理财师增加经验
	 */
	void updateCfplannerRankExperience(@Param("userId")String userId,@Param("level")String level,@Param("experience")Integer experience);

	/**
	 * 查理财师的所有团队成员
	 * @param userId
	 * @return
	 */
	List<CrmCfplanner> queryTeamAllMember(String userId);

	/**
	 * 符合分配规则的理财师
	 * @return
	 */
	List<String> queryConformAllotRuleCfps();
	
	/**
	 * 7天内登录过的理财师
	 * @return
	 */
	List<String> queryLoginInSevenDaysCfp();

	/**
	 * 理财师客户数量
	 * @param userId
	 * @return
	 */
	int queryCustomerCount(String userId);

	/**
	 * 查规定分配自由用户的理财师
	 * @return
	 */
	List<String> querySpecifiedCfps();

	/**
	 * 理财师邀请记录列表
	 * @param req
	 * @param page
	 * @return
	 */
	List<InvotateUserListResp> queryInvitationCfplannerRecord(InvotationRequest req, Page<InvotateUserListResp> page);

	/**
	 * 理财师邀请记录统计
	 * @param userId
	 * @return
	 */
	int queryInvitationCfplannerRecordStatistics(String userId);

	/**
	 * 查销售机构数量
	 * @param salesOrgId
	 * @return
	 */
	int querySalesOrgCount(String salesOrgId);

	
}
