package com.linkwee.web.service;

import java.util.Map;

import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.mc.Classroom;
 /**
 * 
 * @描述： ClassroomService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年11月03日 11:39:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ClassroomService extends GenericService<Classroom,Long>{

	/**
	 * 查询Classroom列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 课堂列表
	 * @param page
	 * @return
	 */
	PaginatorResponse<Classroom> queryClassroomList(Page<Classroom> page, Map<String, Object> conditions);

	/**
	 * 根据ID查询课程
	 * @param id
	 * @return
	 */
	Classroom selectById(String id);
	
}
