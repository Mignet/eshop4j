package com.eshop4j.core.export;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.eshop4j.core.export.strategy.OnceQueryConcurrentStrategy;



@Component
public class ExportSupport {
	
	protected static final  Logger log = Logger.getLogger(ExportSupport.class);
	
	/**
	 * 导出数据
	 * @param request
	 * @param response
	 * @param tempFileName 导出模板文件名称
	 * @param params 参数
	 */
	public void export(HttpServletRequest request, HttpServletResponse response, String tempFileName, Map<String, Object> params){
		ConcurrentStrategy concurrentStrategy = new OnceQueryConcurrentStrategy(params);
		writeExcel(request, response, tempFileName, concurrentStrategy);
	}
	
	public <T> void export(HttpServletRequest request, HttpServletResponse response, String tempFileName, List<T> lists){
		Map<String, Object> params = Maps.newHashMap();
		boolean isEmpty = CollectionUtils.isEmpty(lists);
		params.put("list", isEmpty ? Collections.emptyList() : lists);
		params.put("size", isEmpty ? 0l: Long.valueOf(lists.size()));
		ConcurrentStrategy concurrentStrategy = new OnceQueryConcurrentStrategy(params);
		writeExcel(request, response, tempFileName, concurrentStrategy);
	}

	
	/**
	 * 导出
	 * @param request
	 * @param response
	 * @param tempFileName 模版名称
	 * @param concurrentStrategy 导出策略
	 */
	private void writeExcel(HttpServletRequest request, HttpServletResponse response,String tempFileName,ConcurrentStrategy concurrentStrategy){
    	try {
    		response.setContentType("application/vnd.ms-excel");     
            response.setHeader("Content-disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");
            InputStream tempStream = request.getServletContext().getResourceAsStream("/WEB-INF/xls/" + tempFileName);
            
    		ExcelWriter excelWriter = new ExcelWriter(concurrentStrategy);
    		Long s = System.currentTimeMillis();
    		excelWriter.writeExcel(tempStream,  response.getOutputStream());
    		Long e = System.currentTimeMillis();
			log.info("导出时间---"+(e-s));
    		
		} catch (Exception e){
			log.error("导出数据出错，请重试", e);
			response.reset();
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out;
			String url = request.getContextPath() + "/main.htm";
			try {
				out = response.getWriter();
				StringBuffer buffer = new StringBuffer();
				buffer.append("<script language='javascript' type='text/javascript'>");
				buffer.append("alert('导出数据出错，请重试！');window.top.location.href='" + url + "'");
				buffer.append("</script>");
				out.println(buffer.toString());
			} catch (IOException ee) {
			}
		}
	}

}
