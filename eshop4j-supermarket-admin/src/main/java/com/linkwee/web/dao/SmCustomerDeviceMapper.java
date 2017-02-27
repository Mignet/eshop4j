package com.linkwee.web.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.SmCustomerDevice;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月14日 15:08:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmCustomerDeviceMapper extends GenericDao<SmCustomerDevice,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SmCustomerDevice> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	 /**
	  * 	
	  */
	Integer deleteByUserIdAndAppType(@Param("userId")String userId,@Param("appType")int appType);
	
	List<SmCustomerDevice> queryUserDeviceToken(@Param("appType")int appType,@Param("userIds")Collection<String> userIds);
	
	
}
