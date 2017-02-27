package com.linkwee.web.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.crm.InvestorBindPlatformDatable;
import com.linkwee.web.model.crm.InvestorBindPlatformListResp;

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
	 * 查询用户微信openId
	 * @param userId
	 */
	String queryWeiXinOpenId(String useId);

}
