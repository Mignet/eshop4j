package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.mc.Classroom;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年11月03日 11:39:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ClassroomMapper extends GenericDao<Classroom,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<Classroom> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 课程列表
	 * @param page
	 * @return
	 */
	List<Classroom> queryClassroomList(Page<Classroom> page, Map<String, Object> conditions);
}
