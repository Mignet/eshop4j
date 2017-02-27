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

import com.linkwee.web.model.acc.AcWhiteCard;
import com.linkwee.web.dao.AcWhiteCardMapper;
import com.linkwee.web.service.AcWhiteCardService;
import com.linkwee.web.service.impl.AcWhiteCardServiceImpl;


 /**
 * 
 * @描述：AcWhiteCardService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年08月26日 17:45:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("acWhiteCardService")
public class AcWhiteCardServiceImpl extends GenericServiceImpl<AcWhiteCard, Long> implements AcWhiteCardService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AcWhiteCardServiceImpl.class);
	
	@Resource
	private AcWhiteCardMapper acWhiteCardMapper;
	
	@Override
    public GenericDao<AcWhiteCard, Long> getDao() {
        return acWhiteCardMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- AcWhiteCard -- 排序和模糊查询 ");
		Page<AcWhiteCard> page = new Page<AcWhiteCard>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<AcWhiteCard> list = this.acWhiteCardMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public boolean queryAcWhiteCardByBankCard(String bankCard) {
		List<AcWhiteCard> list = acWhiteCardMapper.queryAcWhiteCardByBankCard(bankCard);
		if(list.size()>0){
			return true;
		}
		return false;
	}

}
