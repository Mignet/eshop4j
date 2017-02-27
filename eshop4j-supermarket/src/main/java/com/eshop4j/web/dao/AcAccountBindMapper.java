package com.eshop4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.acc.AcAccountBind;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月12日 19:10:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcAccountBindMapper extends GenericDao<AcAccountBind,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<AcAccountBind> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 根据用户ID查询用户的绑卡信息
	 * */
	AcAccountBind selectAccountByUserId(@Param("userId")String userId);

	/**
	 * 用户解绑银行卡
	 * */
	void acAccountUnbund(@Param("userId")String userId);
	
	/**
	 * 运营人员禁止提现
	 * */
	int isForbiWithdrawUser(@Param("userId")String userId);

	/**
	 * 发放奖励-添加金额
	 * */
	void acGrantProfit(@Param("transAmount")String transAmount, @Param("userId")String userId);

	/**
	 * 是否需要开户行
	 * */
	int isNeedkaiHuHang(@Param("bankCardId")String bankCardId);

	/**
	 * 测试绑卡用
	 * */
	List<AcAccountBind> selectBankByBankName();

	/**
	 * 修改绑卡次数
	 * */
	void updateBindTimes(AcAccountBind upbind);

	/**
	 * 判断身份证的唯一性 
	 * */
	List<AcAccountBind> checkIdCardOnly(@Param("idCard")String idCard);

	/**
	 * 理财师活动奖励
	 * @param userId
	 * @return
	 */
	Double queryCfpActivityReward(@Param("userId")String userId, @Param("month")String month);

}
