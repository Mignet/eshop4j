package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.crm.CrmShareUserList;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年01月03日 16:52:39
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmShareUserListMapper extends GenericDao<CrmShareUserList,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CrmShareUserList> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
}
