package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.acc.AcWithdrawRecord;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcWithdrawRecordMapper extends GenericDao<AcWithdrawRecord,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<AcWithdrawRecord> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
}
