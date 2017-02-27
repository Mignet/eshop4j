package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.response.act.AdvertismentDtlResp;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.news.SmAdvertisement;
import com.xiaoniu.mybatis.paginator.domain.PageList;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 14:50:27
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmAdvertisementMapper extends GenericDao<SmAdvertisement,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SmAdvertisement> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	public PageList<SmAdvertisement> query(PageRequest req);
	
	public List<SmAdvertisement> findAdvList( SmAdvertisement pageRequest , RowBounds bounds) throws Exception;
	
	public List<AdvertismentDtlResp> findAdvertisementDtl(SmAdvertisement adv);
	
	/**
	 * 查询Advertisement
	 * @param adv
	 * @return
	 */
	public List<SmAdvertisement> queryAdvertisement(SmAdvertisement adv);
}
