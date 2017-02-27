package com.eshop4j.web.service;

import java.util.Map;

import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.mc.Classroom;
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
