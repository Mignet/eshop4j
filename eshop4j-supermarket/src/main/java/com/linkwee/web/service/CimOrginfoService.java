package com.linkwee.web.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CimOrgUrl;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.CrmOrgAcctRel;
import com.linkwee.web.model.cim.CimOrginfoBindSelect;
import com.linkwee.web.model.cim.CimOrginfoWeb;
import com.linkwee.web.model.cim.OrgInfo;
import com.linkwee.web.model.cim.PcOrgInfo;
import com.linkwee.web.model.crm.PlatformAcctManagerListResp;
import com.linkwee.web.request.orgInfo.OrgRecommendByChooseRequest;
import com.linkwee.web.request.orgInfo.OrgRecommendChooseRequest;
import com.linkwee.web.response.orgInfo.InvestmentStrategyResponse;
import com.linkwee.web.response.orgInfo.OrgRecommendChooseResponse;
import com.linkwee.xoss.api.AppRequestHead;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月11日 16:27:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrginfoService extends GenericService<CimOrginfo,Integer>{

	/**
	 * 查询CimOrginfo列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	public DataTableReturn selectByDatatables(DataTable dataTable);
	
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
	 * 优质平台
	 * @author yalin 
	 * @date 2016年7月26日 下午6:13:10  
	 * @return
	 */
	public List<CimOrginfo> findRecommendOrg(Boolean isGrayUser);
	/**
	 * 平台列表 分页
	 * @author yalin 
	 * @date 2016年7月15日 下午2:28:45  
	 * @param page
	 * @param conditions
	 * @return
	 */
	public PaginatorResponse<CimOrginfo> queryOrgList(Page<CimOrginfo> page,Map<String,Object> conditions);
	
	/**
	 * APP端 平台信息详情
	 * @author yalin 
	 * @date 2016年7月26日 下午6:12:35  
	 * @param orgNo
	 * @return
	 */
	public OrgInfo findOrgInfo(String orgNo);
	
	/**
	 * PC端 平台信息详情
	 * @author yalin 
	 * @date 2016年7月26日 下午6:09:48  
	 * @param orgNo
	 * @return
	 */
	public PcOrgInfo findPcOrgInfo(String orgNumber);

	
	/**
	 * 平台帐号管理列表
	 * @param query
	 * @param page
	 * @return
	 */
	PaginatorSevResp<PlatformAcctManagerListResp> queryPlatformAcctManagerPageList(Map<String, Object> query,
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
	void bindOrgAcct(CrmOrgAcctRel  bo);

	/**
	 * 是否已绑定机构帐号
	 * @param userId
	 * @param platFromNumber
	 * @return
	 */
	boolean isBindOrgAcct(String userId, String platFromNumber);
	
	/**
	 * 通过平台编码查询平台URL信息
	 * @author yalin 
	 * @date 2016年8月3日 下午6:45:19  
	 * @param orgNumber
	 * @return
	 */
	public CimOrgUrl selectOrgUrlByOrgNumber(String orgNumber);
	
	/**
	 * 通过机构编码查询机构信息
	 * @author yalin 
	 * @date 2016年8月17日 下午6:40:52  
	 * @param orgNumber
	 * @return
	 */
	public CimOrginfoWeb selectOrgInfoByOrgNumber(String orgNumber);
	
	/**
	 * 通过机构编码查询机构信息
	 * @author yalin 
	 * @date 2016年8月17日 下午6:40:52  
	 * @param orgNumber
	 * @return
	 */
	public CimOrginfo queryOrgInfoByOrgNumber(String orgNumber);
	
	/**
	 * 查询用户第三方机构账户
	 * @author yalin 
	 * @date 2016年8月9日 下午5:35:17  
	 * @param userId
	 * @param platFromNumber
	 * @return
	 */
	public String queryThirdOrgAccountByUserId(String userId,String platFromNumber);

	/**
	 * 机构数量
	 * @param userId
	 * @return
	 */
	int queryOrgCount();
	
	/**
	 * 新增机构完整信息
	 * @author yalin 
	 * @date 2016年8月18日 下午2:17:32  
	 * @param cimOrginfo
	 * @param createUser
	 * @return
	 */
	public void insertOrgFullInfo(CimOrginfoWeb cimOrginfo,String createUser);
	
	/**
	 * 更新机构完整信息
	 * @author yalin 
	 * @date 2016年8月18日 下午2:17:32  
	 * @param cimOrginfo
	 * @param createUser
	 * @return
	 */
	public void updateOrgFullInfo(CimOrginfoWeb cimOrginfo);
	
	/**
	 * 查询投资者最新登录日志uuid
	 * @author yalin 
	 * @date 2016年8月18日 下午4:30:17  
	 * @param userId
	 * @return
	 */
	public String queryInvestorLoginId(String userId);
	
	/**
	 * 通过第三方机构帐号查询用户id
	 * @author yalin 
	 * @date 2016年8月19日 下午5:42:21  
	 * @param thirdOrgAccount
	 * @param platFromNumber
	 * @return
	 */
	public String queryUserIdByThirdOrgAccount(String thirdOrgAccount,String platFromNumber);
	
	/**
	 * 根据合作状态查询机构(1:合作中,0:合作结束)
	 * @author yalin 
	 * @date 2016年8月26日 下午6:01:35  
	 * @return
	 */
	public List<CimOrginfoBindSelect> queryOrgByStatus(int status);

	/**
	 * 更新第三方用户为已投资
	 * @param userId
	 * @param productOrgId
	 */
	public void updateOrgAcctRelInvested(String userId, String productOrgId);
	
	/**
	 * WEB端 平台信息详情
	 * @author yalin 
	 * @date 2016年8月30日 上午10:50:12  
	 * @param orgNumber
	 * @return
	 */
	public CimOrginfoWeb findWebOrgInfo(String orgNumber);
	
	/**
	 * 该用户是否平台新用户
	 * @param userId
	 * @param String
	 * @return
	 */
	public boolean isOrgNewUser(String userId, String orgNumber);
	
	/**
	 * PC端优质平台
	 * @author yalin 
	 * @date 2016年7月25日 下午5:25:29  
	 * @param o
	 * @return
	 */
	public PaginatorResponse<CimOrginfo> findPcRecommendOrg(Page<CimOrginfo> page,Boolean isGrayUser);
	
	/**
	 * PC端 最新入驻机构
	 * @author yalin 
	 * @date 2016年7月25日 下午5:25:29  
	 * @param o
	 * @return
	 */
	public List<CimOrginfo> queryLatestOrg(Boolean isGrayUser);
	
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
	 * 机构推荐选择列表
	 * @param head
	 * @param orgRecommendChooseRequest
	 * @return
	 */
	public OrgRecommendChooseResponse recommendChooseList(AppRequestHead head,OrgRecommendChooseRequest orgRecommendChooseRequest);

	/**
	 * 机构选择推荐
	 * @param head
	 * @param orgRecommendByChooseRequest
	 */
	public void recommendByChoose(AppRequestHead head,OrgRecommendByChooseRequest orgRecommendByChooseRequest);
	
	/**
	 * 查询理财师给投资客户推荐的机构(移动端)
	 * @author yalin 
	 * @date 2016年7月15日 下午2:28:45  
	 * @param page
	 * @param conditions
	 * @return
	 */
	public PaginatorResponse<CimOrginfo> queryPlannerRecommendPlatfrom(Page<CimOrginfo> page,String investUserId,String saleUserId);
	
	/**
	 * 查询理财师给投资客户推荐的机构(PC端)
	 * @author yalin 
	 * @date 2016年7月15日 下午2:28:45  
	 * @param page
	 * @param conditions
	 * @return
	 */
	public PaginatorResponse<CimOrginfo> queryPcPlannerRecommendPlatfrom(Page<CimOrginfo> page,String investUserId,String saleUserId);
	
	/**
	 * 查询机构佣金信息
	 * @author yalin 
	 * @date 2016年10月25日 上午11:19:25  
	 * @param orgNumber
	 * @return
	 */
	public BigDecimal queryOrgFeeRatio(String orgNumber);

	/**
	 * 查询机构产品-投资攻略
	 * @param orgCode
	 * @return
	 */
	public InvestmentStrategyResponse queryInvestmentStrategy(String orgCode);
	
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
	 * @param securityLevel
	 * @return
	 */
	public List<CimOrginfo> selectListByGrade(String securityLevel,Boolean ifHaveGray);
	
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
