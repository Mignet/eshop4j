package com.eshop4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eshop4j.act.redpacket.model.ActRepaymentRedpacketPool;
import com.eshop4j.core.generic.GenericDao;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年12月22日 22:14:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActRepaymentRedpacketPoolMapper extends GenericDao<ActRepaymentRedpacketPool,Long>{
	
	String getMainPlatFormByModel(@Param("model") Integer model);
	
	int updateStatus(ActRepaymentRedpacketPool redpacketPool);
	
	int setMainPlatForm(ActRepaymentRedpacketPool redpacketPool);
	
	List<ActRepaymentRedpacketPool> getRepaymentRedpackets(ActRepaymentRedpacketPool repaymentRedpacketPool);
}
