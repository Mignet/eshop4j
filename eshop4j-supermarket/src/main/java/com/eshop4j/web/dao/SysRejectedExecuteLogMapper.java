package com.eshop4j.web.dao;

import java.util.List;

import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.SysRejectedExecuteLog;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月17日 02:51:21
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysRejectedExecuteLogMapper extends GenericDao<SysRejectedExecuteLog,Long>{
	
	/**
	 * 获取拒绝处理日志列表
	 * @return
	 */
	List<SysRejectedExecuteLog> getRejectedExecuteLogs();
}
