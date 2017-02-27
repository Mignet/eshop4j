package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.web.model.crm.CfpManagerDetailResp;
import com.linkwee.web.request.LcsListRequest;
import com.linkwee.web.response.CfpCustomerProfitListResp;
import com.linkwee.web.response.CfpTeamListResp;
import com.xiaoniu.mybatis.paginator.domain.PageList;

/**
 * 
 * @描述： 理财师相关查询
 * 
 * @创建人：ch
 * 
 * @创建时间：2016年04月13日 17:27:15
 * 
 *  Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
	
public interface CfplannerManagerDao  {
	
	
	/**
	 * 查询理财师列表
	 * @param request
	 * @return
	 */
	public PageList<CfpManagerDetailResp> queryLcsList(@Param("query")LcsListRequest pageRequest,RowBounds page);
	
	/**
	 * 查询理财师详情
	 * @param mobile
	 * @return
	 */
	public CfpManagerDetailResp queryLcsDetail(@Param("mobile")String mobile);

	/**
	 * 删除理财师头像
	 * @param mobile
	 * @return
     */
	public int removeCfplannerHeadImage(@Param("mobile") String mobile);
	
	/**
     * 查询理财师客户列表
     */
    public List<CfpCustomerProfitListResp> queryCfpCustomerProfitList(@Param("query") CfpManagerDetailResp detailResp,RowBounds rowBounds);

    /**
     * 查询理财师团队列表
     */
    public List<CfpTeamListResp> queryCfpTeamList(@Param("query") CfpManagerDetailResp lcsDetailResp,RowBounds rowBounds);
    
	
	/**
	 * 查询理财师数据
	 * @return
	 */
	public Map<String, Object> getLcsDateStaticCount();
	
	/**
	 * 根据日期查询理财师数据
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Map<String, Object>> getLcsDateStatic(Map<String, Object> map);
	
	/**
	 * 查询有效理财师数据
	 * @return
	 */
	public Map<String, Object> getValidLcsDateStaticCount();
	
	/**
	 * 根据日期查询有效理财师数据
	 * @param start
	 * @param end
	 * @return
	 */
	public  List<Map<String, Object>> getValidLcsDateStatic(Map<String, Object> map);
    
}
