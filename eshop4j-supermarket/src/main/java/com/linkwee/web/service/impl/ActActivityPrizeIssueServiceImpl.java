package com.linkwee.web.service.impl;

import java.util.List;
import java.lang.Long;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActActivityPrizeIssue;
import com.linkwee.web.model.ActActivityWinningRecord;
import com.linkwee.web.dao.ActActivityPrizeIssueMapper;
import com.linkwee.web.service.ActActivityPrizeIssueService;
import com.linkwee.web.service.impl.ActActivityPrizeIssueServiceImpl;


 /**
 * 
 * @描述：ActActivityPrizeIssueService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月07日 21:30:33
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actActivityPrizeIssueService")
public class ActActivityPrizeIssueServiceImpl extends GenericServiceImpl<ActActivityPrizeIssue, Long> implements ActActivityPrizeIssueService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActActivityPrizeIssueServiceImpl.class);
	
	@Resource
	private ActActivityPrizeIssueMapper actActivityPrizeIssueMapper;
	
	@Override
    public GenericDao<ActActivityPrizeIssue, Long> getDao() {
        return actActivityPrizeIssueMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActActivityPrizeIssue -- 排序和模糊查询 ");
		Page<ActActivityPrizeIssue> page = new Page<ActActivityPrizeIssue>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActActivityPrizeIssue> list = this.actActivityPrizeIssueMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public void prizeIssue(ActActivityWinningRecord actActivityWinningRecord,String userId) {
		ActActivityPrizeIssue actActivityPrizeIssue = new ActActivityPrizeIssue();
		actActivityPrizeIssue.setActivityId(actActivityWinningRecord.getActivityId());
		actActivityPrizeIssue.setConditionCase(actActivityWinningRecord.getConditionCase());
		actActivityPrizeIssue.setPrizeCase(actActivityWinningRecord.getPrizeCase());
		List<ActActivityPrizeIssue> actActivityPrizeIssueList = selectListByCondition(actActivityPrizeIssue);
		for(ActActivityPrizeIssue actActivityPrizeIssueTemp : actActivityPrizeIssueList){
			actActivityPrizeIssueMapper.execPrizeIssuedSQL(actActivityPrizeIssueTemp.getResultSql());
		}
	}

}
