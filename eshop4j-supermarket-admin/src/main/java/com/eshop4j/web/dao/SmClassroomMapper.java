package com.eshop4j.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.mc.SmClassroom;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年11月04日 16:27:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmClassroomMapper extends GenericDao<SmClassroom,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SmClassroom> selectBySearchInfo(@Param("label")String label,@Param("title")String title,@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 修改课堂showIndex
	 * */
	void updateShowIndex(@Param("showInx")Integer showInx);

	/**
	 * 课堂顶置
	 * */
	void overheadClassroom();
}
