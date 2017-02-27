package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimOrgPicture;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月27日 14:51:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgPictureMapper extends GenericDao<CimOrgPicture,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimOrgPicture> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 根据图片类型和端口查询该机构所有图片
	 * @author yalin 
	 * @date 2016年10月27日 下午3:18:53  
	 * @param orgNumber
	 * @return
	 */
	public List<CimOrgPicture> queryOrgPictureList(@Param("orgNumber")String orgNumber,@Param("pictureType")int pictureType,@Param("source")int source);
	
	/**
	 * 图片批量插入
	 * @author yalin 
	 * @date 2016年8月18日 下午3:20:55  
	 * @param teams
	 */
	public void insertBatchPicture(List<CimOrgPicture> pictures);
	
	/**
	 * 图片批量更新
	 * @author yalin 
	 * @date 2016年8月18日 下午3:20:55  
	 * @param teams
	 */
	public void updateBatchPicture(List<CimOrgPicture> pictures);
}
