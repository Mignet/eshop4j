package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.mc.SysPushArtificialQueue;
import com.linkwee.web.model.mc.SysPushMessage;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月10日 15:50:10
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysPushArtificialQueueMapper extends GenericDao<SysPushArtificialQueue,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SysPushArtificialQueue> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	/**
	 * 分页查询
	 * @param page
	 * @param map
	 * @return
	 */
	public List<SysPushArtificialQueue> querySysPushMessageList(Page<SysPushArtificialQueue> page,Map<String,Object> map);
	
	/**
	 * 批量更新
	 */
	public Integer updateBatch(List<SysPushArtificialQueue> pushMsgList);
}
