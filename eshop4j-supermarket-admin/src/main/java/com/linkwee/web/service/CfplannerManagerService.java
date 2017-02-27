package com.linkwee.web.service;

import java.util.Map;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.crm.CfpManagerDetailResp;
import com.linkwee.web.request.LcsListRequest;

/**
 * 
 * @描述：理财师销列表
 *
 * @author ch
 * @时间 2016年4月8日下午5:43:30
 *
 */
public interface CfplannerManagerService {


	/**
	 * 查询理财师列表
	 * 
	 * @param request
	 * @return
	 */
	public DataTableReturn queryLcsList(DataTable dataTable, LcsListRequest request);

	/**
	 * 查询理财师详情,根据手机号码
	 * 
	 * @param mobile
	 * @return
	 */
	public CfpManagerDetailResp queryLcsDetail(String mobile);

	/**
	 * 获得理财师信息，不包括上级记录
	 * 
	 * @param mobile
	 * @return
	 */
	public CfpManagerDetailResp queryLcsInfo(String mobile);

	/**
	 * 退出理财师
	 * 
	 * @param mobile
	 */
	public void exitLcs(CrmCfplanner crmCfplanner);

	/**
	 * 删除头像图片
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	public boolean removeCfplannerHeadImage(String mobile) throws Exception;
	
	/**
	 * 更改上级理财师
	 * @param mobile
	 * @param parentMobile
	 * @param changeType
	 * @param saleUserInfo
	 */
	void changeParent(String mobile, String parentMobile, String changeType, CrmCfplanner saleUserInfo);

	/**
	 * 理财师是否有下级团队或者客户
	 * @return
	 */
	public boolean hasCustomerOrTeam(String userId);

	/**
	 * 查询理财师一级客户投资情况不包括非直属投资客户
	 * @param detailResp
	 * @param dataTable
     * @return
     */
	public DataTableReturn queryCfpCustomerProfitList(CfpManagerDetailResp detailResp, DataTable dataTable);
	
	/**
	 * 查询理财师下一级与二级理财师的信息与推荐收益情况
	 * @param lcsDetailResp
	 * @param dataTable
     * @return
     */
	public DataTableReturn queryCfpTeamList(CfpManagerDetailResp lcsDetailResp,DataTable dataTable);
	
	/**
	 * 查询数据统计信息
	 * @return
	 */
	public Map<String, Object> getLcsDateStaticCount();
	
	/**
	 * 根据日期查询lcs数据统计
	 * @param start
	 * @param end
	 * @return
	 */
	public Map<String, Object> getLcsDataStatic(Map<String, Object> map);
	
}
