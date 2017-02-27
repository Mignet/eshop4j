package com.linkwee.core.Import;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.Import.bean.ImportModelDefinition;
import com.linkwee.core.Import.context.DefaultContext;
import com.linkwee.core.Import.tag.TitleTag;


public class PoiImport {
	
	
	
	public static <T> List<T> dataImport(InputStream in,Class<T> entityClass) throws Exception{
		try {
			if(in!=null){
				Workbook wb = new HSSFWorkbook(new BufferedInputStream(in)); //03版及以下
				         //wb = new XSSFWorkbook(new BufferedInputStream(in));//07版及以上

				return parseWorkbook(wb,entityClass);
			}
		}finally{
			if(in!=null){
				in.close();
			}
		}
		return null;
	}
	
	
	/**
	 * 处理workbook
	 * @param wb 
	 * @throws InterruptedException 
	 */
	private static <T> List<T>  parseWorkbook(Workbook wb,Class<T> entityClass) throws InterruptedException {
		List<T> objs = new ArrayList<T>();
		int sheetCount = wb.getNumberOfSheets();
		for (int i = 0; i < sheetCount; i++) {
			Sheet sheet = wb.getSheetAt(i);
			int rowNum = sheet.getLastRowNum();
			if(rowNum>0){
				Row row = sheet.getRow(0);
				int cellNum = row.getLastCellNum();
				 List<CellDefinition> sheetTitles = resolveSheetTitle(sheet);
				 if(!sheetTitles.isEmpty()){
					 objs.addAll( getRowInfo(sheet,rowNum,cellNum,sheetTitles,entityClass));
				 } 
			}
		}
		return objs;
	}
	
	
	private static <T> List<T> getRowInfo(Sheet sheet,int rowNum,int cellNum,List<CellDefinition> sheetTitles,Class<T> entityClass){
		Row row =null;
		Map<String, Object> map =null;
		List<T> objs = new ArrayList<T>(rowNum);
		for(int rowIndex  = 1; rowIndex<=rowNum; rowIndex++){
			map = new HashMap<String, Object>(cellNum);
			row = sheet.getRow(rowIndex);
			if(row!=null){
				getCellInfo(row,cellNum,sheetTitles,entityClass,map);
				if(!map.isEmpty()){
					objs.add(JSONObject.parseObject(JSONObject.toJSONString(map), entityClass));
				}
				
				map =null;
			}
		}
		return objs;
	}
	
	private static <T> void getCellInfo(Row row,int cellNum,List<CellDefinition> sheetTitles,Class<T> entityClass,Map<String, Object> map){
		Cell cell;
		CellDefinition  cellDefinition;
		for(int cellIndex=0;cellIndex<cellNum;cellIndex++){
			cell = row.getCell(cellIndex);
			if(cell!=null){
				
				Object cellValue = PoiUtil.getValue(cell);
				cellDefinition = sheetTitles.get(cellIndex);
				if(cellDefinition!=null){
					ImportModelDefinition modelDefinition = DefaultContext.getModel(entityClass, cellDefinition.getKey());
					if(modelDefinition!=null){
						if(cellValue==null){
							cellValue = modelDefinition.getDefaultValue();
						}
						if(cellValue!=null){
							map.put(modelDefinition.getName(), cellValue);
						}
					}
				}
			}
		}
	}
	
	
	
	
	private static List<CellDefinition> resolveSheetTitle(Sheet sheet){
		List<CellDefinition> cellDefinitions = new ArrayList<CellDefinition>();
		/*
		 * 解析表单标题
		 */
		Row row = sheet.getRow(0);
		if(row!=null){
			PoiTag tag = new TitleTag();
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				if(cell != null){
					String value = String.valueOf(PoiUtil.getValue(cell));
						CellDefinition c = tag.parse(cell, value);
						if(c!=null){
							cellDefinitions.add(c);
					}
				}
			}
			sheet.removeRow(row);
		}
		
		return cellDefinitions;
	}

}
