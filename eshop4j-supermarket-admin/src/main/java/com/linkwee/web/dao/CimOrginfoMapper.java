package com.linkwee.web.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CimOrgUrl;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.CrmOrgAcctRel;
import com.linkwee.web.model.cim.CimOrginfoBindSelect;
import com.linkwee.web.model.cim.CimOrginfoWeb;
import com.linkwee.web.model.crm.PlatformAcctManagerListResp;
import com.linkwee.web.response.orgInfo.OrgInfoResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月11日 16:27:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrginfoMapper extends GenericDao<CimOrginfo,Integer>{
	
	 /**
     * 封装DataTable对象查询	
     * @param dt
     * @param page
     * @return
     */
	public List<CimOrginfoWeb> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	 /**
     * 封装对象条件查询
     * @param o
     * @return
     */
	public List<CimOrginfo> selectByCondition(CimOrginfo o);
	
	/**
	 * 通过机构编码更新机构信息
	 * @author yalin 
	 * @date 2016年8月24日 下午6:53:53  
	 * @param o
	 * @return
	 */
	public int updateByOrgNumber(CimOrginfoWeb o);
	
	/**
	 * 插入机构信息
	 * @author yalin 
	 * @date 2016年8月24日 下午6:53:53  
	 * @param o
	 * @return
	 */
	public int insertOrginfo(CimOrginfoWeb o);
	
	
	/**
	 * APP端 优质平台
	 * @author yalin 
	 * @date 2016年7月25日 下午5:25:29  
	 * @param o
	 * @return
	 */
	public List<CimOrginfo> findRecommendOrg(CimOrginfo o);
	/**
	 * 平台列表
	 * @author yalin 
	 * @date 2016年7月15日 上午10:28:16  
	 * @param page
	 * @return
	 */
	public List<CimOrginfo> queryCimOrginfoList(Page<CimOrginfo> page,Map<String,Object> map);
	/**
	 * APP端 平台信息详情
	 * @author yalin 
	 * @date 2016年7月26日 下午6:09:48  
	 * @param orgNo
	 * @return
	 */
	public OrgInfoResponse  findOrgInfo(@Param("orgNo")String orgNumber);

	/**
	 * 平台帐号管理列表
	 * @param query
	 * @param page
	 * @return
	 */
	List<PlatformAcctManagerListResp> queryPlatformAcctManagerPageList(Map<String, Object> query,
			Page<PlatformAcctManagerListResp> page);

	/**
	 * 绑定机构帐号数量
	 * @param userId
	 * @return
	 */
	int queryOrgAccountCount(String userId);

	/**
	 * 绑定机构帐号
	 * @param userId
	 * @param orgNumber
	 */
	void insertOrgAcctRel(CrmOrgAcctRel crmOrgAcctRel);

	/**
	 * 是否绑定机构帐号
	 * @param userId
	 * @param platFromNumber
	 */
	int queryIsBindOrgAcct(@Param("userId")String userId, @Param("platFromNumber")String platFromNumber);
	
	/**
	 * 通过机构编码查询机构配置的URL
	 * @author yalin 
	 * @date 2016年8月17日 下午6:41:01  
	 * @param orgNumber
	 * @return
	 */
	public CimOrgUrl selectOrgUrlByOrgNumber(@Param("orgNo")String orgNumber);
	
	/**
	 * 通过机构编码查询机构信息
	 * @author yalin 
	 * @date 2016年8月17日 下午6:40:52  
	 * @param orgNumber
	 * @return
	 */
	public CimOrginfoWeb selectOrgInfoByOrgNumber(@Param("orgNo")String orgNumber);

	/**
	 * 未绑定机构数量
	 * @param userId
	 * @return
	 */
	public int queryOrgCount();
	
	/**
	 * 通过用户id查询用户第三方机构帐号
	 * @author yalin 
	 * @date 2016年8月9日 下午5:35:17  
	 * @param userId
	 * @param platFromNumber
	 * @return
	 */
	public String queryThirdOrgAccountByUserId(@Param("userId")String userId, @Param("orgNo")String platFromNumber);
	/**
	 * 查询投资者最新登录日志uuid
	 * @author yalin 
	 * @date 2016年8月18日 下午4:30:17  
	 * @param userId
	 * @return
	 */
	public String queryInvestorLoginId(@Param("userId")String userId);
	
	/**
	 * 通过第三方机构帐号查询用户id
	 * @author yalin 
	 * @date 2016年8月19日 下午5:42:21  
	 * @param thirdOrgAccount
	 * @param platFromNumber
	 * @return
	 */
	public String queryUserIdByThirdOrgAccount(@Param("thirdOrgAccount")String thirdOrgAccount, @Param("orgNo")String platFromNumber);

	/**
	 * 更新第三方用户投资状态
	 * @param userId
	 * @param productOrgId
	 */
	public void updateOrgAcctRelInvested(@Param("userId")String userId, @Param("orgNumber")String orgNumber);
	
	/**
	 * 根据合作状态查询机构(1:合作中,0:合作结束)
	 * @author yalin 
	 * @date 2016年8月26日 下午6:01:35  
	 * @return
	 */
	public List<CimOrginfoBindSelect> queryOrgByStatus(int status);
	
	/**
	 * 合作中机构
	 * @param status
	 * @return
	 */
	public List<CimOrginfoBindSelect> queryOrgOfCooperation();
	
	/**
	 * WEB端 平台信息详情
	 * @author yalin 
	 * @date 2016年8月30日 上午10:50:12  
	 * @param orgNumber
	 * @return
	 */
	public CimOrginfoWeb findWebOrgInfo(@Param("orgNumber")String orgNumber);
	
	/**
	 * 是否平台新用户
	 * @param userId
	 * @param orgNumber
	 * @return
	 */
	public boolean isOrgNewUser(@Param("userId")String userId,@Param("orgNumber")String orgNumber);
	
	/**
	 * PC端优质平台
	 * @author yalin 
	 * @date 2016年7月25日 下午5:25:29  
	 * @param o
	 * @return
	 */
	public List<CimOrginfo> findPcRecommendOrg();
	
	/**
	 * PC端 最新入驻机构
	 * @author yalin 
	 * @date 2016年7月25日 下午5:25:29  
	 * @param o
	 * @return
	 */
	public List<CimOrginfo> queryLatestOrg();
	
	/**
	 * WEB端 查询机构收费模式
	 * @author yalin 
	 * @date 2016年9月22日 下午2:14:52  
	 * @param orgNumber
	 * @return
	 */
	public CimOrginfoWeb queryOrgFeeInfo(String orgNumber);
	
	/**
	 * 更新机构佣金
	 * @author yalin 
	 * @date 2016年10月12日 上午10:47:20  
	 * @param orgNumber
	 * @param orgFeeRatio
	 * @return
	 */
	public int updateOrgFeeRatio(@Param("orgNumber")String orgNumber, @Param("orgFeeRatio")BigDecimal orgFeeRatio);
	
	/**
	 * 运营后台接口根据合作状态查询机构(1:合作中,0:合作结束)
	 * @author yalin 
	 * @date 2016年8月26日 下午6:01:35  
	 * @return
	 */
	public List<CimOrginfoBindSelect> queryAllOrgByStatus(int status);
	
	/**
	 * 查询理财师级差佣金率
	 * @author yalin 
	 * @date 2016年10月25日 上午11:19:25  
	 * @param orgNumber
	 * @return
	 */
	public BigDecimal queryOrgDiffFeeRatio(String orgNumber);
	
	/**
	 * 查询机构列表是否存在此排名
	 * @author yalin 
	 * @date 2017年1月4日 下午4:46:42  
	 * @param top
	 * @return
	 */
	public int queryOrgListSort(int top);
	
	/**
	 * 查询机构首页推荐是否存在此排名
	 * @author yalin 
	 * @date 2017年1月4日 下午4:46:45  
	 * @param homepageSort
	 * @return
	 */
	public int queryOrgHomePageSort(int homepageSort);
	
	/**
	 * 通过机构编码更新机构推荐排名信息
	 * @author yalin 
	 * @date 2017年1月5日 下午3:22:46  
	 * @param o
	 * @return
	 */
	public int updateOrgRecommendInfo(CimOrginfoWeb o);
}
