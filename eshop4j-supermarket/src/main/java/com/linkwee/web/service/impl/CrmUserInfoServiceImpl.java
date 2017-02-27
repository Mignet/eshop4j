package com.linkwee.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.api.request.crm.InvotationRequest;
import com.linkwee.api.response.crm.InvotateListResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CrmUserInfoMapper;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.xoss.util.MD5;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmUserInfoService")
public class CrmUserInfoServiceImpl extends GenericServiceImpl<CrmUserInfo, Long> implements CrmUserInfoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmUserInfoServiceImpl.class);
	
	@Resource
	private CrmUserInfoMapper crmUserInfoMapper;
	
	@Override
    public GenericDao<CrmUserInfo, Long> getDao() {
        return crmUserInfoMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmUserInfo -- 排序和模糊查询 ");
		Page<CrmUserInfo> page = new Page<CrmUserInfo>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmUserInfo> list = this.crmUserInfoMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

    /**
	 * 根据电话获取用户基础信息
	 * @param mobile
	 * @return
	 */
	@Override
	public CrmUserInfo selectCrmUserInfoByMobile(String mobile) {
		CrmUserInfo CrmUserInfo = new CrmUserInfo();
		CrmUserInfo.setMobile(mobile);
		CrmUserInfo = this.selectOne(CrmUserInfo);
		return CrmUserInfo;
	}
	
	/**
	 * 用户是否存在
	 * @param head
	 * @param mobile
	 * @return
	 */
	@Override
	public boolean isExistsUserInfo(String mobile) {
		CrmUserInfo bo = new CrmUserInfo();
		bo.setMobile(mobile);
		bo = this.selectOne(bo);
		if(bo != null){
			return true;
		}
		return false;
	}

	/**
	 * 校验登录密码是否正确
	 * @param mobile
	 * @param password
	 * @return
	 */
	@Override
	public boolean docheckLoginPwd(String mobile, String password) {
		CrmUserInfo bo = new CrmUserInfo();
		bo.setMobile(mobile);
		bo = this.selectOne(bo);
		if(MD5.crypt(password).equals(bo.getPassword())){
			return true;
		}
		return false;
	}

	/**
	 * 根据用户ID查用户信息
	 * @param userId
	 * @return
	 */
	@Override
	public CrmUserInfo queryUserInfoByUserId(String userId) {
		CrmUserInfo crmUserInfo = new CrmUserInfo();
		crmUserInfo.setUserId(userId);
		crmUserInfo = this.selectOne(crmUserInfo);
		return crmUserInfo;
	}

	/**
	 * 查询手机号码已注册的用户Id
	 * @param request
	 * @return
	 */
	@Override
	public List<String> selectRegUsersUserId(String[] mobiles) {
		List<String> mobilesReg = crmUserInfoMapper.selectRegCustomers(mobiles);
		return mobilesReg;
	}

	/**
	 * 查询邀请客户信息
	 * @param request
	 * @return
	 */
	@Override
	public PaginatorResponse<InvotateListResponse> invitorMsgPageList(InvotationRequest req,Page<InvotateListResponse> page) {
		PaginatorResponse<InvotateListResponse> paginatorResponse = new PaginatorResponse<InvotateListResponse>();
		paginatorResponse.setDatas(crmUserInfoMapper.investorPageList(req,page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
		
	}

	/**
	 * 更新登录密码
	 * @param userId
	 * @param newPwd
	 */
	@Override
	public void updateLoginPassword(String userId, String newPwd) {
		CrmUserInfo crmUserInfo = new CrmUserInfo();
		crmUserInfo.setUserId(userId);
		crmUserInfo.setPassword(MD5.crypt(newPwd));
		this.updateByUserId(crmUserInfo);
	}

	/**
	 * 根据userId修改用户信息
	 * @param crmUserInfo
	 */
	public void updateByUserId(CrmUserInfo crmUserInfo) {
		crmUserInfoMapper.updateByUserId(crmUserInfo);
	}

	/**
	 * 根据电话号码批量查用户信息
	 * @param mobileList
	 * @return
	 */
	@Override
	public List<CrmUserInfo> queryUserListByMobileList(List<String> mobileList) {
		return crmUserInfoMapper.queryUserListByMobileList(mobileList);
	}
	
}
