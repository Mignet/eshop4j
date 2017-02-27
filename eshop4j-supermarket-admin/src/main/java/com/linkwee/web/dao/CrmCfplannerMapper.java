package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CrmCfplanner;

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
	 * 修改销售机构编码
	 */
	void updatesSalesOrgId(@Param("salesOrgId")String salesOrgId, @Param("mobile")String mobile);

	/**
	 * 查询用户微信openId
	 */
	String queryWeiXinOpenId(String useId);

}
