package com.eshop4j.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.mc.SysPushMessage;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月25日 16:17:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysPushMessageMapper extends GenericDao<SysPushMessage,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SysPushMessage> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	/**
	 * 分页查询
	 * @param page
	 * @param map
	 * @return
	 */
	public List<SysPushMessage> querySysPushMessageList(Page<SysPushMessage> page,Map<String,Object> map);
	/**
	 * 批量更新
	 */
	public Integer updateBatch(List<SysPushMessage> pushMsgList);
	/**
	 * 批量保存
	 */
	public Integer saveBatch(List<SysPushMessage> list);
	/**
	 * 批量删除
	 */
	public Integer deleteBatch(List<SysPushMessage> list);
}
