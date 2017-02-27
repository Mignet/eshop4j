package com.eshop4j.core.Import.tag;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

import com.eshop4j.core.Import.CellDefinition;
import com.eshop4j.core.Import.PoiTag;



public class TitleTag implements PoiTag{

	@Override
	public CellDefinition parse(Cell cell, String tag) {
		CellDefinition cellDefinition =null;
		if(StringUtils.isNotBlank(tag)&&!tag.startsWith("#")&&!tag.startsWith(NAME_PREFIX)&&!tag.endsWith(NAME_Suffix)){
			cellDefinition = new CellDefinition(cell, tag);
		}
		return cellDefinition;
	}

}
