package com.eshop4j.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.web.model.CrmInvestor;
import com.eshop4j.web.model.crm.InvestorBindPlatformDatable;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmInvestorService extends GenericService<CrmInvestor,Long>{

	/**
	 * 查询CrmInvestor列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 投资用户是否存在
	 * @param mobile
	 * @return
	 */
	boolean isExistsInvestor(String mobile);

	/**
	 * 根据电话号码查投资用户
	 * @param recommendCode
	 * @return
	 */
	CrmInvestor queryInvestorByMobile(String mobile);

	/**
	 * 投资者注册
	 * @param mobile
	 * @param password
	 * @param refUserId
	 * @return
	 */
	String registerInvestor(String mobile, String password, String refUserId, String fromUrl, String accessUrl);
	
	/**
	 * 保存投资者表数据
	 * @param mobile
	 * @param password
	 * @param refUserId
	 * @param type
	 */
	void saveInvestor(String mobile, String userId, String refUserId, AppTypeEnum type);
	
	/**
	 * 查投资用户信息
	 * @param userId
	 * @return
	 */
	public CrmInvestor queryInvestorByUserId(String userId);
    
	/**
	 * @author hxb
	 * 更新投资用户二维码
	 * @param userId
	 * @param qrcode
	 */
    public void updateInvQrByUserId(String userId, String qrcode);
    
    /**
	 * @author hxb
	 * 根据用户userId更新投资用户信息
	 * @param userId
	 * @param qrcode
	 */
    int updateByUserId(CrmInvestor crmInvestor);
    
    /**
     * 查询手机号码已注册的投资用户userId
     * @param mobileArray
     * @return
     */
    public List<String> selectRegInvestors(String[] mobileArray);
    
    /**
     * 在投资用户表中查询被该用户推荐的用户userId
     * @param userId
     * @return
     */
    List<String> refRegInvestors(String userId);

    /**
	 * 根据环信帐号查投资用户信息
	 * @param userId
	 * @return
	 */
	List<CrmInvestor> queryInvestorByEasemob(List<String> easemobAcctList);

	/**
	 * 查环信token
	 * @return
	 */
	Map<String, Object> queryEaseToken();

	/**
	 * 更新环信token
	 * @param token
	 */
	void updateEaseToken(String token);

	/**
	 * 首次投资时间
	 * @param userId
	 * @return
	 */
	Date queryFirstRcpDate(String userId);

	/**
	 * 删除归属理财师
	 * @param userId
	 */
	void removeCfplanner(String userId);

	/**
	 * 用户是否锁定
	 * @param mobile
	 * @return
	 */
	boolean isLockedInventor(String mobile);
	
	/**
	 * 分配理财师
	 */
	public void allotCfplanner(String userId, String mobile);
	
	/**
	 * 查询投资人的绑定的机构信息
	 * @author yalin 
	 * @date 2016年9月29日 下午2:32:32  
	 * @param dt
	 * @param page
	 * @return
	 */
	public DataTableReturn queryInvestorBindPlatformList(InvestorBindPlatformDatable dt);
	
	/**
	 * 生成投资用户
	 * @param mobile 投资用户电话
	 * @param cfplaner 归属理财师userId
	 * @return
	 */
	CrmInvestor generateInvestor(String mobile, String cfplanerId) throws Exception ;

	/**
	 * 查询用户微信openId
	 */
	String queryWeiXinOpenId(String useId);
	
}
