package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.ActActivityCondition;
import com.linkwee.web.model.ActivityList;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月04日 09:49:10
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActActivityConditionMapper extends GenericDao<ActActivityCondition,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActActivityCondition> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 用户是否满足本次活动的等级条件
	 * @param conditionSql
	 * @return
	 */
	Boolean execConditionSql(@Param("conditionSql")String conditionSql);

}
