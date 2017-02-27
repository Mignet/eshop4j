package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.act.redpacket.service.ActRedpacketService;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CrmCfpNewcomerTaskMapper;
import com.linkwee.web.enums.CfpNewcomerTaskEnum;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.crm.CrmCfpNewcomerTask;
import com.linkwee.web.service.CrmCfpNewcomerTaskService;
import com.linkwee.web.service.CrmUserInfoService;


 /**
 * 
 * @描述：CrmCfpNewcomerTaskService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月08日 16:32:41
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmCfpNewcomerTaskService")
public class CrmCfpNewcomerTaskServiceImpl extends GenericServiceImpl<CrmCfpNewcomerTask, Long> implements CrmCfpNewcomerTaskService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmCfpNewcomerTaskServiceImpl.class);
	
	@Resource
	private CrmCfpNewcomerTaskMapper crmCfpNewcomerTaskMapper;
	
	@Resource
	private ActRedpacketService actRedpacketService;
	
	@Resource
	private CrmUserInfoService crmUserInfoService;
	
	@Override
    public GenericDao<CrmCfpNewcomerTask, Long> getDao() {
        return crmCfpNewcomerTaskMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmCfpNewcomerTask -- 排序和模糊查询 ");
		Page<CrmCfpNewcomerTask> page = new Page<CrmCfpNewcomerTask>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmCfpNewcomerTask> list = this.crmCfpNewcomerTaskMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	@Transactional
	public void receiveTaskReward(String userId, String taskType)  throws Exception{
		CrmCfpNewcomerTask task = new CrmCfpNewcomerTask();
		task.setUserId(userId);
		task = this.selectOne(task);
		CrmCfpNewcomerTask bo = new CrmCfpNewcomerTask();
		bo.setId(task.getId());
		//检查是否符合发放条件：已完成，未发放。
		String exc = "Does not conform newcomer task reward condition";
		CrmUserInfo userInfo = crmUserInfoService.queryUserInfoByUserId(userId);
		if("1".equals(taskType)) {
			if(task.getInviteCustomerStatus() != 1) {
				throw new Exception(exc);
			} else {
				bo.setInviteCustomerStatus(2);
				actRedpacketService.lcsTaskRedPacekt(CfpNewcomerTaskEnum.INVITE_CUSTOMER, userInfo);
			}
		} else if("4".equals(taskType)) {
			if(task.getInviteCfplannerStatus() != 1) {
				throw new Exception(exc);
			} else {
				bo.setInviteCfplannerStatus(2);
				actRedpacketService.lcsTaskRedPacekt(CfpNewcomerTaskEnum.INVITE_CFPLANNER, userInfo);
			}
		} else if("2".equals(taskType)) {
			if(task.getRecommendProductStatus() != 1) {
				throw new Exception(exc);
			} else {
				bo.setRecommendProductStatus(2);
				actRedpacketService.lcsTaskRedPacekt(CfpNewcomerTaskEnum.RECOMMEND_PRODUCT, userInfo);
			}
		} else if("5".equals(taskType)) {
			if(task.getRecommendPlatformStatus() != 1) {
				throw new Exception(exc);
			}else {
				bo.setRecommendPlatformStatus(2);
				actRedpacketService.lcsTaskRedPacekt(CfpNewcomerTaskEnum.RECOMMEND_PLATFORM, userInfo);
			}
		} else if("3".equals(taskType)) {
			if(task.getGrantHongbaoStatus() != 1) {
				throw new Exception(exc);
			}else {
				bo.setGrantHongbaoStatus(2);
				actRedpacketService.lcsTaskRedPacekt(CfpNewcomerTaskEnum.GRANT_HONGBAO, userInfo);
			}
		} else if("6".equals(taskType)) {
			if(task.getSeeProfitStatus() != 1) {
				 throw new Exception(exc);
			}else {
				bo.setSeeProfitStatus(2);
				actRedpacketService.lcsTaskRedPacekt(CfpNewcomerTaskEnum.SEE_PROFIT, userInfo);
			}
		}
		bo.setLastUpdateTime(new Date());
		this.update(bo);
		
	}

	@Override
	public int queryUnFinishNewcomerTaskCount(String userId) {
		int count = 0;
		CrmCfpNewcomerTask bo = new CrmCfpNewcomerTask();
		bo.setUserId(userId);
		bo = this.selectOne(bo);
		if(bo == null) {
			return 0;
		}
		if(bo.getGrantHongbaoStatus() == 0) {
			count ++;
		}
		if(bo.getInviteCfplannerStatus() == 0) {
			count ++;
		}
		if(bo.getInviteCustomerStatus() == 0) {
			count ++;
		}
		if(bo.getRecommendPlatformStatus() == 0) {
			count ++;	
		}
		if(bo.getRecommendProductStatus() == 0) {
			count ++;
		}
		if(bo.getSeeProfitStatus() == 0) {
			count ++;
		}
		return count;
	}

}
