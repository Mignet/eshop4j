package com.eshop4j.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.SysThirdkeyConfig;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月17日 11:16:29
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysThirdkeyConfigMapper extends GenericDao<SysThirdkeyConfig,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SysThirdkeyConfig> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 通过机构编码更新配置信息
	 * @author yalin 
	 * @date 2016年11月17日 上午10:52:40  
	 * @param config
	 */
	public void updateThirdkeyByOrgNumber(SysThirdkeyConfig config);
}
