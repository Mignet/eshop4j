package ${service.strPackage};

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import ${entity.fullName};
import ${service.fullName};
 /**
 * 
 * @描述： ${service.shortName}服务接口
 * 
 * @创建人： ${generate.author}
 * 
 * @创建时间：${generate.createDate}
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ${service.shortName} extends GenericService<${entity.shortName},Long>{

	/**
	 * 查询${entity.shortName}列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
