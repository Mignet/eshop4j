package com.linkwee.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.SysNoticeMapper;
import com.linkwee.web.dao.SysPushArtificialQueueMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.model.mc.SysNotice;
import com.linkwee.web.model.mc.SysPushArtificialQueue;
import com.linkwee.web.service.SysPushArtificialQueueService;
import com.linkwee.xoss.helper.ConfigHelper;


 /**
 * 
 * @描述：SysPushArtificialQueueService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月10日 15:50:10
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("sysPushArtificialQueueService")
public class SysPushArtificialQueueServiceImpl extends GenericServiceImpl<SysPushArtificialQueue, Long> implements SysPushArtificialQueueService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysPushArtificialQueueServiceImpl.class);
	
	@Resource
	private SysPushArtificialQueueMapper sysPushArtificialQueueMapper;
	
	@Autowired
	private SysNoticeMapper sysNoticeMapper;
	
	@Resource
	private ConfigHelper configHelper;
	
	@Override
    public GenericDao<SysPushArtificialQueue, Long> getDao() {
        return sysPushArtificialQueueMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SysPushArtificialQueue -- 排序和模糊查询 ");
		Page<SysPushArtificialQueue> page = new Page<SysPushArtificialQueue>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SysPushArtificialQueue> list = this.sysPushArtificialQueueMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
    
	@Override
	public PaginatorResponse<SysPushArtificialQueue> querySysPushMessageList(
			Page<SysPushArtificialQueue> page, Map<String, Object> conditions) {
		PaginatorResponse<SysPushArtificialQueue> paginatorResponse = new PaginatorResponse<SysPushArtificialQueue>();
		List<SysPushArtificialQueue> queryCimOrginfoList = sysPushArtificialQueueMapper.querySysPushMessageList(page,conditions);
		paginatorResponse.setDatas(queryCimOrginfoList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public Integer renewBatch(List<SysPushArtificialQueue> list) {
		return sysPushArtificialQueueMapper.updateBatch(list);
	}

	@Override	
	@Transactional(propagation=Propagation.REQUIRED)
	public void releaseNotice(SysNotice notice, SysPushArtificialQueue queue) {
		sysNoticeMapper.insertSelective(notice);
		queue.setLink(getPushLinkUrl(notice));
		sysPushArtificialQueueMapper.insertSelective(queue);
	}
	/**
	 * 活期系统配置的公告模板页面地址
	 * @param r
	 * @return
	 */
	private String getPushLinkUrl(SysNotice r){
		StringBuffer msgUrl = new StringBuffer();
		 msgUrl.append(configHelper.getValue(AppTypeEnum.CHANNEL.getKey(),SysConfigConstant.LCS_PUSH_NOTICE_RELEASE_TMP_URL));
		 msgUrl.append("?msgId=");
		 msgUrl.append(r.getId());
		 return msgUrl.toString();
	}

}
