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

import com.linkwee.web.model.mc.SmClassroom;
import com.linkwee.web.dao.SmClassroomMapper;
import com.linkwee.web.service.SmClassroomService;
import com.linkwee.web.service.impl.SmClassroomServiceImpl;


 /**
 * 
 * @描述：SmClassroomService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年11月04日 16:27:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smClassroomService")
public class SmClassroomServiceImpl extends GenericServiceImpl<SmClassroom, Long> implements SmClassroomService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmClassroomServiceImpl.class);
	
	@Resource
	private SmClassroomMapper smClassroomMapper;
	
	@Override
    public GenericDao<SmClassroom, Long> getDao() {
        return smClassroomMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(SmClassroom cr,DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmClassroom -- 排序和模糊查询 ");
		Page<SmClassroom> page = new Page<SmClassroom>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmClassroom> list = this.smClassroomMapper.selectBySearchInfo(cr.getLabel(),cr.getTitle(),dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public void updateClassroom(SmClassroom smClassroom) {
		//先根据ID查询出原来showIndex
		SmClassroom queryClassroom = new SmClassroom();
		queryClassroom.setId(smClassroom.getId());
		SmClassroom oldClassroom = smClassroomMapper.selectOneByCondition(queryClassroom);
		if(smClassroom.getShowInx()==1&&oldClassroom!=null){//顶置 
			//先修改比当前数据showIndex更加小的数据(showIndex)
			smClassroomMapper.updateShowIndex(oldClassroom.getShowInx());
		}
		
		smClassroomMapper.updateByPrimaryKeySelective(smClassroom);
	}

	@Override
	public void overheadClassroom() {
		smClassroomMapper.overheadClassroom();
	}

}
