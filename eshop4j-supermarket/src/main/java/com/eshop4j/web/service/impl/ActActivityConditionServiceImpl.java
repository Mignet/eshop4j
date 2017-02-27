package com.eshop4j.web.service.impl;

import java.util.ArrayList;
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
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.model.ActActivityCondition;
import com.eshop4j.web.model.ActivityList;
import com.eshop4j.web.dao.ActActivityConditionMapper;
import com.eshop4j.web.service.ActActivityConditionService;
import com.eshop4j.web.service.impl.ActActivityConditionServiceImpl;


 /**
 * 
 * @描述：ActActivityConditionService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月04日 09:49:10
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actActivityConditionService")
public class ActActivityConditionServiceImpl extends GenericServiceImpl<ActActivityCondition, Long> implements ActActivityConditionService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActActivityConditionServiceImpl.class);
	
	@Resource
	private ActActivityConditionMapper actActivityConditionMapper;
	
	@Override
    public GenericDao<ActActivityCondition, Long> getDao() {
        return actActivityConditionMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActActivityCondition -- 排序和模糊查询 ");
		Page<ActActivityCondition> page = new Page<ActActivityCondition>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActActivityCondition> list = this.actActivityConditionMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public String queryLeftTimeConditionSQL(ActivityList activity) {
		ActActivityCondition actActivityCondition = new ActActivityCondition();
		actActivityCondition.setConditionType(1);
		actActivityCondition.setActivityId(String.valueOf(activity.getId()));
		List<ActActivityCondition> result = actActivityConditionMapper.selectByCondition(actActivityCondition);
		String leftTimeConditionSQL = null;
		if(result != null && result.size() > 0){
			leftTimeConditionSQL = result.get(0).getConditionSql(); 
		}
		return leftTimeConditionSQL;
	}

	@Override
	public List<Integer> queryConditionCase(String userId, ActivityList activity) {
		
		ActActivityCondition actActivityCondition = new ActActivityCondition();
		actActivityCondition.setActivityId(String.valueOf(activity.getId()));
		actActivityCondition.setConditionType(2);
		List<ActActivityCondition> actActivityConditionList = selectListByCondition(actActivityCondition);
		
		List<Integer> resultList = new ArrayList<Integer>();
		
		for(ActActivityCondition actActivityConditionTemp : actActivityConditionList){
			String conditionSql = actActivityConditionTemp.getConditionSql();
			conditionSql = conditionSql.replaceAll("#\\{userId\\}", "'"+userId+"'").replaceAll("#\\{startDate\\}", "'"+DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG)+"'").replaceAll("#\\{endDate\\}","'"+DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG)+"'");
			Boolean result = actActivityConditionMapper.execConditionSql(conditionSql);
			if(result){
				resultList.add(actActivityConditionTemp.getConditionCase());
			}
		}
				
		return resultList;
	}
	
	@Override
	public List<ActActivityCondition> queryConditionTypes(String userId, ActivityList activity) {
		
		ActActivityCondition actActivityCondition = new ActActivityCondition();
		actActivityCondition.setActivityId(String.valueOf(activity.getId()));
		actActivityCondition.setConditionType(2);
		List<ActActivityCondition> actActivityConditionList = selectListByCondition(actActivityCondition);
		
		List<ActActivityCondition> resultList = new ArrayList<ActActivityCondition>();
		
		for(ActActivityCondition actActivityConditionTemp : actActivityConditionList){
			String conditionSql = actActivityConditionTemp.getConditionSql();
			conditionSql = conditionSql.replaceAll("#\\{userId\\}", "'"+userId+"'").replaceAll("#\\{startDate\\}", "'"+DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG)+"'").replaceAll("#\\{endDate\\}","'"+DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG)+"'");
			Boolean result = actActivityConditionMapper.execConditionSql(conditionSql);
			if(result){
				resultList.add(actActivityConditionTemp);
			}
		}
				
		return resultList;
	}

	@Override
	public String queryUnknownPrizeCaseConditionSQL(ActivityList activity) {
		ActActivityCondition actActivityCondition = new ActActivityCondition();
		actActivityCondition.setConditionType(3);
		actActivityCondition.setActivityId(String.valueOf(activity.getId()));
		List<ActActivityCondition> result = actActivityConditionMapper.selectByCondition(actActivityCondition);
		String conditionSQL = null;
		if(result != null && result.size() > 0){
			conditionSQL = result.get(0).getConditionSql(); 
		}
		return conditionSQL;
	}

}
