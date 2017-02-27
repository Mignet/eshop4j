package com.eshop4j.web.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.CimOrgUrl;
import com.eshop4j.web.model.CimOrginfo;
import com.eshop4j.web.model.CrmOrgAcctRel;
import com.eshop4j.web.model.cim.CimOrginfoBindSelect;
import com.eshop4j.web.model.cim.CimOrginfoWeb;
import com.eshop4j.web.model.cim.OrgInfo;
import com.eshop4j.web.model.cim.PcOrgInfo;
import com.eshop4j.web.model.crm.PlatformAcctManagerListResp;
import com.eshop4j.web.response.orgInfo.InvestmentStrategyResponse;

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
	public List<CimOrginfo> findRecommendOrg(CimOrginfo orginfo);
	/**
	 * 平台列表
	 * @author yalin 
	 * @date 2016年7月15日 上午10:28:16  
	 * @param page
	 * @return
	 */
	public List<CimOrginfo> queryCimOrginfoList(Page<CimOrginfo> page,Map<String,Object> map);
	
	/**
	 * 移动APP端 平台信息详情
	 * @author yalin 
	 * @date 2016年7月26日 下午6:09:48  
	 * @param orgNo
	 * @return
	 */
	public OrgInfo findOrgInfo(@Param("orgNo")String orgNumber);
	
	/**
	 * PC端 平台信息详情
	 * @author yalin 
	 * @date 2016年7月26日 下午6:09:48  
	 * @param orgNo
	 * @return
	 */
	public PcOrgInfo findPcOrgInfo(@Param("orgNumber")String orgNumber);

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
	public List<CimOrginfo> findPcRecommendOrg(Page<CimOrginfo> page,@Param("isGrayUser") Boolean isGrayUser);
	
	/**
	 * PC端 最新入驻机构
	 * @author yalin 
	 * @date 2016年7月25日 下午5:25:29  
	 * @param o
	 * @return
	 */
	public List<CimOrginfo> queryLatestOrg(@Param("isGrayUser") Boolean isGrayUser);
	
	/**
	 * WEB端 查询机构收费模式
	 * @author yalin 
	 * @date 2016年9月22日 下午2:14:52  
	 * @param orgNumber
	 * @return
	 */
	public CimOrginfoWeb queryOrgFeeInfo(String orgNumber);

	/**
	 * 根据产品id查询该产品对应的机构基本信息
	 * @param productId
	 * @return
	 */
	public CimOrginfo queryCimOrginfoByProductid(String productId);
	
	/**
	 * 查询理财师给投资客户推荐的机构(移动端)
	 * @author yalin 
	 * @date 2016年10月17日 下午5:07:52  
	 * @param investUserId
	 * @param saleUserId
	 * @return
	 */
	public List<CimOrginfo> queryPlannerRecommendPlatfrom(Page<CimOrginfo> page,@Param("investUserId")String investUserId,@Param("saleUserId")String saleUserId);
	
	/**
	 * 查询理财师给投资客户推荐的机构(PC端)
	 * @author yalin 
	 * @date 2016年10月17日 下午5:07:52  
	 * @param investUserId
	 * @param saleUserId
	 * @return
	 */
	public List<CimOrginfo> queryPcPlannerRecommendPlatfrom(Page<CimOrginfo> page,@Param("investUserId")String investUserId,@Param("saleUserId")String saleUserId);

	/**
	 * 查询机构产品-投资攻略
	 * @param orgCode
	 * @return
	 */
	public InvestmentStrategyResponse queryInvestmentStrategy(String orgCode);
	
	/**
	 * 查询机构佣金信息
	 * @author yalin 
	 * @date 2016年10月25日 上午11:19:25  
	 * @param orgNumber
	 * @return
	 */
	public BigDecimal queryOrgFeeRatio(String orgNumber);
	
	/**
	 * 查询机构安全保障
	 * @author yalin 
	 * @date 2016年10月25日 上午11:19:25  
	 * @param orgNumber
	 * @return
	 */
	public String queryOrgSecurity(String orgNumber);

	/**
	 * 根据安全等级查平台
	 * @param conditions
	 * @return
	 */
	public List<CimOrginfo> selectListByGrade(Map<String, Object> conditions);
	
	/**
	 * 查询理财师级差佣金率
	 * @author yalin 
	 * @date 2016年10月25日 上午11:19:25  
	 * @param orgNumber
	 * @return
	 */
	public BigDecimal queryOrgDiffFeeRatio(String orgNumber);
	
	/**
	 * 查询机构可投产品数
	 * @author yalin 
	 * @date 2016年12月19日 下午6:40:20  
	 * @param orgNumber
	 * @return
	 */
	public Integer queryOrgUseProductNums(String orgNumber);
	
	/**
	 * 用户绑定的机构账户数
	 * @param map
	 * @return
	 */
	public List<PlatformAcctManagerListResp> bindOrgAccountCount(Map<String, Object> map);
	
	/**
	 * 用户未绑定的机构账户数
	 * @param map
	 * @return
	 */
	public int unBindOrgAccountCount(Map<String, Object> map);
}
