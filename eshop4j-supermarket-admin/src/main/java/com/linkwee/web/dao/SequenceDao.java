package com.linkwee.web.dao;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.base.BaseDao;

public interface SequenceDao extends BaseDao{
	
	public String getSequence(@Param("tableName")String tableName);
	
}
