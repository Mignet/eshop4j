package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.SysNoticeReadLogMapper;
import com.linkwee.web.model.mc.SysNoticeReadLog;
import com.linkwee.web.service.SysNoticeReadLogService;


 /**
 * 
 * @描述：SysNoticeReadLogService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月04日 17:09:02
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("sysNoticeReadLogService")
public class SysNoticeReadLogServiceImpl extends GenericServiceImpl<SysNoticeReadLog, Long> implements SysNoticeReadLogService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysNoticeReadLogServiceImpl.class);
	
	@Resource
	private SysNoticeReadLogMapper sysNoticeReadLogMapper;
	
	@Override
    public GenericDao<SysNoticeReadLog, Long> getDao() {
        return sysNoticeReadLogMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SysNoticeReadLog -- 排序和模糊查询 ");
		Page<SysNoticeReadLog> page = new Page<SysNoticeReadLog>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SysNoticeReadLog> list = this.sysNoticeReadLogMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public void updateNoticeReadLog(String apiName, String userId,
			Integer appType, Integer noticeId) {
		SysNoticeReadLog noticeReadLog = queryNoticeReadLog(apiName,userId,appType,noticeId);
		if(noticeReadLog==null){
			noticeReadLog = new SysNoticeReadLog();
			noticeReadLog.setUserId(userId);
			noticeReadLog.setApiName(apiName);
			noticeReadLog.setAppType(appType);
			noticeReadLog.setNoticeId(noticeId);
			sysNoticeReadLogMapper.insertSelective(noticeReadLog);
		}else{
			noticeReadLog.setAccessCount(noticeReadLog.getAccessCount() + 1);
			noticeReadLog.setChgTime(new Date());
			sysNoticeReadLogMapper.updateByPrimaryKeySelective(noticeReadLog);
		}
		
		
	}

	@Override
	public SysNoticeReadLog queryNoticeReadLog(String apiName, String userId,
			Integer appType, Integer noticeId) {
		SysNoticeReadLog noticeReadLog = new SysNoticeReadLog();
		noticeReadLog.setUserId(userId);
		noticeReadLog.setApiName(apiName);
		noticeReadLog.setAppType(appType);
		noticeReadLog.setNoticeId(noticeId);
		List<SysNoticeReadLog> list = sysNoticeReadLogMapper.selectByCondition(noticeReadLog);
		if(list==null||list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	}

	@Override
	/**
	 * 将用户 userId 在 appType 端的所以通知设置为已读
	 */
	public void setNoticeReaded(String userId, Integer appType) throws Exception{
		try {
			sysNoticeReadLogMapper.addNoticeReadLogByUserId(userId, appType);
		} catch (Exception e) {
		  throw new Exception(e.getMessage());	
		}
	}

}
