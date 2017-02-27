package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.SmFeedback;
import com.linkwee.web.response.FeedbackResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月28日 10:43:04
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmFeedbackMapper extends GenericDao<SmFeedback,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SmFeedback> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	 /**
     * 封装DataTable对象查询,查询结果中userId替换成mobile
     * @param dt
     * @param page
     * @return
     */
	List<FeedbackResponse> selectBySearchInfoRes(@Param("dt")DataTable dt,RowBounds page);
}
