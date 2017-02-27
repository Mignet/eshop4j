package com.eshop4j.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.acc.AcWhiteCard;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年08月26日 17:45:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcWhiteCardMapper extends GenericDao<AcWhiteCard,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<AcWhiteCard> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 银行卡白名单
	 * */
	List<AcWhiteCard> queryAcWhiteCardByBankCard(@Param("bankCard")String bankCard);
}
