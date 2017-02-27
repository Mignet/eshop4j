package ${serviceImpl.strPackage};

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

import ${entity.fullName};
import ${dao.fullName};
import ${service.fullName};
import ${serviceImpl.fullName};


 /**
 * 
 * @描述：${service.shortName} 服务实现类
 * 
 * @创建人： ${generate.author}
 * 
 * @创建时间：${generate.createDate}
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("${service.aliasName}")
public class ${serviceImpl.shortName} extends GenericServiceImpl<${entity.shortName}, Long> implements ${service.shortName}{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(${serviceImpl.shortName}.class);
	
	@Resource
	private ${dao.shortName} ${dao.aliasName};
	
	@Override
    public GenericDao<${entity.shortName}, Long> getDao() {
        return ${dao.aliasName};
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ${entity.shortName} -- 排序和模糊查询 ");
		Page<${entity.shortName}> page = new Page<${entity.shortName}>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<${entity.shortName}> list = this.${dao.aliasName}.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
