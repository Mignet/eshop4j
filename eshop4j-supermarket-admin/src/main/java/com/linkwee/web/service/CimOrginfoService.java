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
import com.linkwee.web.model.CimOrgFeeTimetask;
import com.linkwee.web.model.CimOrgUrl;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.CrmOrgAcctRel;
import com.linkwee.web.model.cim.CimOrginfoBindSelect;
import com.linkwee.web.model.cim.CimOrginfoWeb;
import com.linkwee.web.model.crm.PlatformAcctManagerListResp;
import com.linkwee.web.response.orgInfo.OrgInfoResponse;
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
	public List<CimOrginfo> findRecommendOrg();
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
	public OrgInfoResponse  findOrgInfo(String orgNo);

	
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
	 * 合作中机构
	 * @return
	 */
	public List<CimOrginfoBindSelect> queryOrgOfCooperation();

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
	 * 更新机构下所有产品佣金
	 * @author yalin 
	 * @date 2016年10月10日 下午6:52:33  
	 * @param orgNumber
	 * @param orgFeeRatio
	 */
	public void updateCimOrgFeeRatio(CimOrgFeeTimetask orgFeeTask);
	
	/**
	 * 运营后台接口根据合作状态查询机构(1:合作中,0:合作结束)
	 * @author yalin 
	 * @date 2016年11月03日 下午6:01:35  
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
	 * 通过机构编码更新机构推荐排名信息
	 * @author yalin 
	 * @date 2016年8月18日 下午2:17:32  
	 * @param cimOrginfo
	 * @param createUser
	 * @return
	 */
	public void updateOrgRecommendInfo(CimOrginfoWeb cimOrginfo);
	
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
	
	
}
