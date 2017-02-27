package com.eshop4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.CimProductExtends;
import com.eshop4j.web.model.share.ShareContent;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月21日 17:02:43
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductExtendsMapper extends GenericDao<CimProductExtends,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimProductExtends> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 根据产品id查询产品分享信息
	 * @param productId
	 * @return
	 */
	ShareContent selectShareContentByProductId(@Param("productId")String productId);
}
