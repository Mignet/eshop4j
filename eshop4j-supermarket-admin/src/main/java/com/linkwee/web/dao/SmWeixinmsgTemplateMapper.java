package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.mc.SmWeixinmsgTemplate;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年11月22日 19:11:04
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmWeixinmsgTemplateMapper extends GenericDao<SmWeixinmsgTemplate,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SmWeixinmsgTemplate> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
}
