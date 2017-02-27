package com.eshop4j.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.dao.ClassroomMapper;
import com.eshop4j.web.model.mc.Classroom;
import com.eshop4j.web.service.ClassroomService;


 /**
 * 
 * @描述：SmClassroomService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年11月03日 11:39:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("classroomService")
public class ClassroomServiceImpl extends GenericServiceImpl<Classroom, Long> implements ClassroomService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassroomServiceImpl.class);
	
	@Resource
	private ClassroomMapper classroomMapper;
	
	@Override
    public GenericDao<Classroom, Long> getDao() {
        return classroomMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- Classroom -- 排序和模糊查询 ");
		Page<Classroom> page = new Page<Classroom>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<Classroom> list = this.classroomMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public PaginatorResponse<Classroom> queryClassroomList(Page<Classroom> page, Map<String, Object> conditions) {
		PaginatorResponse<Classroom> roomResponse = new PaginatorResponse<Classroom>();
		List<Classroom> accountList = classroomMapper.queryClassroomList(page,conditions);
		roomResponse.setDatas(accountList);
		roomResponse.setValuesByPage(page);
		return roomResponse;
	}

	@Override
	public Classroom selectById(String id) {
		 return classroomMapper.selectByPrimaryKey(Long.parseLong(id));
	}

}
