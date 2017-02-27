package com.linkwee.core.Import;

import org.apache.poi.ss.usermodel.Cell;



/**
 * 自定义标签接口
 * @author ch
 *
 */
public interface PoiTag {
	String NAME_PREFIX_START = "#foreach";
	
	String NAME_PREFIX_END = "#end";
	
	/**
	 * 属性名前缀
	 */
	String NAME_PREFIX = "${";
	
	/**
	 * 属性名后缀
	 */
	String NAME_Suffix = "}";
	
	/**
	 * 解析单元格标签
	 * @param tag
	 * @return
	 */
	CellDefinition parse(Cell cell,String tag);
	
	
	

}
