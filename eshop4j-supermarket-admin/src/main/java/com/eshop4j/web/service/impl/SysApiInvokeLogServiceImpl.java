package com.eshop4j.web.service.impl;

import java.util.Date;
import java.util.List;
import java.lang.Long;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.model.SysApiInvokeLog;
import com.eshop4j.web.dao.SysApiInvokeLogMapper;
import com.eshop4j.web.service.SysApiInvokeLogService;
import com.eshop4j.web.service.impl.SysApiInvokeLogServiceImpl;


 /**
 * 
 * @描述：SysApiInvokeLogService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月21日 09:32:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("sysApiInvokeLogService")
public class SysApiInvokeLogServiceImpl extends GenericServiceImpl<SysApiInvokeLog, Long> implements SysApiInvokeLogService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysApiInvokeLogServiceImpl.class);
	
	@Resource
	private SysApiInvokeLogMapper sysApiInvokeLogMapper;
	
	@Override
    public GenericDao<SysApiInvokeLog, Long> getDao() {
        return sysApiInvokeLogMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SysApiInvokeLog -- 排序和模糊查询 ");
		Page<SysApiInvokeLog> page = new Page<SysApiInvokeLog>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SysApiInvokeLog> list = this.sysApiInvokeLogMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
    
    public void updateApiInvokeLog(String apiName,String userId,Integer appType){
    	SysApiInvokeLog apiInvokeLog = queryApiInvokeLog(apiName,userId,appType);
		if(apiInvokeLog==null){
			apiInvokeLog = new SysApiInvokeLog();
			apiInvokeLog.setUserId(userId);
			apiInvokeLog.setApiName(apiName);
			apiInvokeLog.setAppType(appType);
			sysApiInvokeLogMapper.insertSelective(apiInvokeLog);
		}else{
			apiInvokeLog.setAccessCount(apiInvokeLog.getAccessCount() + 1);
			apiInvokeLog.setChgTime(new Date());
			sysApiInvokeLogMapper.updateByPrimaryKeySelective(apiInvokeLog);
		}
	}
	/**
	 * 调用api中具体数据信息
	 * @param apiName
	 * @param contentId
	 * @param userId
	 * @param appType
	 */
	public void updateApiInvokeLog(String apiName,String contentId,String userId,Integer appType){
		String key = apiName;
		if(StringUtils.isNotBlank(contentId)){
			key+=apiName+"!"+contentId;
		}
		SysApiInvokeLog apiInvokeLog = queryApiInvokeLog(key,userId,appType);
		if(apiInvokeLog==null){
			apiInvokeLog = new SysApiInvokeLog();
			apiInvokeLog.setUserId(userId);
			apiInvokeLog.setApiName(key);
			apiInvokeLog.setAppType(appType);
			sysApiInvokeLogMapper.insertSelective(apiInvokeLog);
		}else{
			apiInvokeLog.setAccessCount(apiInvokeLog.getAccessCount() + 1);
			apiInvokeLog.setChgTime(new Date());
			sysApiInvokeLogMapper.updateByPrimaryKeySelective(apiInvokeLog);
		}
	}
	
	public SysApiInvokeLog queryApiInvokeLog(String apiName,String userId,Integer appType){
		SysApiInvokeLog apiInvokeLog = new SysApiInvokeLog();
		apiInvokeLog.setUserId(userId);
		apiInvokeLog.setApiName(apiName);
		apiInvokeLog.setAppType(appType);
		List<SysApiInvokeLog> list = sysApiInvokeLogMapper.selectByCondition(apiInvokeLog);
		if(list==null||list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	}

}
