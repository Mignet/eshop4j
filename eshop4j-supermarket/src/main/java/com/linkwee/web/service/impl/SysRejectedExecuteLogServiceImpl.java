package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.SysRejectedExecuteLogMapper;
import com.linkwee.web.model.SysRejectedExecuteLog;
import com.linkwee.web.service.SysRejectedExecuteLogService;
import com.linkwee.xoss.helper.ArgsMeta;

@Service("sysRejectedExecuteLogService")
public class SysRejectedExecuteLogServiceImpl extends GenericServiceImpl<SysRejectedExecuteLog, Long>  implements SysRejectedExecuteLogService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysRejectedExecuteLogServiceImpl.class);
	
	@Autowired
	private SysRejectedExecuteLogMapper rejectedExecuteLogMapper;

	@Override
	public GenericDao<SysRejectedExecuteLog, Long> getDao() {
		return rejectedExecuteLogMapper;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public int saveRejectedExecuteLog(String remark,String serviceName,String serviceMethod, ArgsMeta... argsMetas) {
		SysRejectedExecuteLog rejectedExecuteLog = new SysRejectedExecuteLog();
		rejectedExecuteLog.setExecuteId(StringUtils.getUUID());
		rejectedExecuteLog.setServiceName(serviceName);
		rejectedExecuteLog.setServiceMethod(serviceMethod);
		if(argsMetas != null && argsMetas.length > 0){
			rejectedExecuteLog.setServiceParm(JSONArray.toJSONString(Lists.newArrayList(argsMetas)));
		}
		Date time  = DateTime.now().toDate();
		rejectedExecuteLog.setRejectedRemark(remark);
		rejectedExecuteLog.setStatus(1);
		rejectedExecuteLog.setFaillTime(time);
		rejectedExecuteLog.setCreateTime(time);
		rejectedExecuteLog.setUpdateTime(time);
		return insert(rejectedExecuteLog);
	}

	@Override
	public int updateFaillStatus(String executeId,String remark) {
		return updateStatus(executeId,3,remark);
	}

	@Override
	public int updateProcessStatus(String executeId) {
		return updateStatus(executeId,2,"处理中");
	}

	@Override
	public int updateSucceedStatus(String executeId) {
		return updateStatus(executeId,4,"处理成功");
	}
	
	private SysRejectedExecuteLog getSysRejectedExecuteLog(String executeId){
		SysRejectedExecuteLog rejectedExecuteLog = new SysRejectedExecuteLog();
		rejectedExecuteLog.setExecuteId(executeId);
		rejectedExecuteLog = selectOne(rejectedExecuteLog);
		return rejectedExecuteLog;
	}
	
	private int updateStatus(String executeId,Integer status,String remark){
		SysRejectedExecuteLog rejectedExecuteLog = getSysRejectedExecuteLog(executeId);
		if(rejectedExecuteLog==null)return 0;
		SysRejectedExecuteLog updateRejectedExecuteLog = new SysRejectedExecuteLog();
		updateRejectedExecuteLog.setExecuteId(executeId);
		updateRejectedExecuteLog.setStatus(status);
		updateRejectedExecuteLog.setRejectedRemark(remark);
		Date time  = DateTime.now().toDate();
		if(ObjectUtils.equals(status, 3)){
			updateRejectedExecuteLog.setFaillTime(time);
		}
		updateRejectedExecuteLog.setUpdateTime(time);
		return update(updateRejectedExecuteLog);
	}

	@Override
	public List<SysRejectedExecuteLog> getRejectedExecuteLogs() {
		return rejectedExecuteLogMapper.getRejectedExecuteLogs();
	}

}
