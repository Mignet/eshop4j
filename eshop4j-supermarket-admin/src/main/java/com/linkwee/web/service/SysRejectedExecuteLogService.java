package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.SysRejectedExecuteLog;
import com.linkwee.xoss.helper.ArgsMeta;
/**
 * 服务拒绝执行service
 * @author ch
 *
 */
public interface SysRejectedExecuteLogService extends GenericService<SysRejectedExecuteLog,Long>{
	
	List<SysRejectedExecuteLog> getRejectedExecuteLogs();
	
	/**
	 * 插入拒绝日志
	 * @param serviceName
	 * @param serviceMethod
	 * @param serviceParms
	 * @return
	 */
	public int saveRejectedExecuteLog(String remark,String serviceName,String serviceMethod, ArgsMeta... argsMetas);
	
	/**
	 * 更新为处理中状态
	 * @param id
	 * @return
	 */
	int updateProcessStatus(String executeId);
	
	/**
	 * 更新为失败状态
	 * @param id
	 * @return
	 */
	int updateFaillStatus(String executeId,String remark);
	
	/**
	 *  更新为成功状态
	 * @param id
	 * @return
	 */
	int updateSucceedStatus(String executeId);
}
