package com.eshop4j.web.dao;

import org.apache.ibatis.annotations.Param;

import com.eshop4j.core.base.BaseDao;

public interface SequenceDao extends BaseDao{
	
	public String getSequence(@Param("tableName")String tableName);
	
}
