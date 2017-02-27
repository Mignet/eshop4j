package com.eshop4j.generate;

import java.util.HashMap;
import java.util.Map;

import com.eshop4j.generate.appender.BaseAppender;
import com.eshop4j.generate.appender.FileAppender;
import com.eshop4j.generate.config.AppenderConfig;
import com.eshop4j.generate.config.ConfigFactory;
import com.eshop4j.generate.db.TableParseImpl;
import com.eshop4j.generate.handler.EntityVoHandler;
import com.eshop4j.generate.model.EntityVo;
import com.eshop4j.generate.model.FileVo;
import com.eshop4j.generate.model.Table;
import com.eshop4j.generate.utils.FreemarkUtils;
import com.eshop4j.generate.utils.PropertiesUtils;
import com.eshop4j.generate.utils.StringUtils;


public class GenerateFactory {
	
	
	private static void init(Map<String, Object> save,String type,AppenderConfig appender) throws Exception{
		BaseAppender  appenderHandler = null;
		if(StringUtils.isNotBlank(appender.getHandler())){
			appenderHandler = (BaseAppender) Class.forName(appender.getHandler()).newInstance();
		}else{
			appenderHandler = new FileAppender();
		}
		if(appenderHandler!=null){
			PropertiesUtils.refresh(appender,PropertiesUtils.obj2Map(save));
			FileVo fileVo = appenderHandler.doInvoke(appender);
			save.put(type, fileVo);
		}
	}
	
	private static void init(Map<String, Object> save) throws Exception{
		Map<String, AppenderConfig> src = new HashMap<String, AppenderConfig>();
		src.putAll(ConfigFactory.config.getAppender());
		while(src.size()>0){
			Object[] arr = src.keySet().toArray();
			for(int i=0;i<arr.length;i++){
				String type = arr[i].toString();
				AppenderConfig appender = src.get(type);
				String strDependency = appender.getDependency();
				if(StringUtils.isNotBlank(strDependency)){
					String[] dependencys = strDependency.split(",");
					boolean isCanInit = true;
					for(String dependency:dependencys){
						if(save.get(dependency)==null){
							if(src.get(dependency)==null){
								throw new IllegalArgumentException("dependency:"+dependency+" not exists!");
							}
							isCanInit = false;
						}
					}
					if(isCanInit){
						init(save,type,appender);
						src.remove(type);
					}
				}else{
					init(save,type,appender);
					src.remove(type);
				}
			}
		}
	}
	
	
	public static void codeGenerate(String configFile) throws Exception {
		ConfigFactory.init(configFile);
		//从表 名开始
		String strTables =  ConfigFactory.config.getTarget();
		if(StringUtils.isNotBlank(strTables)){
			String[] tableNames = strTables.split(",");
			FreemarkUtils.init(ConfigFactory.config.getTemplateDir());
			for(String tableName:tableNames){
				Table table = (new TableParseImpl()).getTable(tableName);
				EntityVo vo = EntityVoHandler.createVo(table);
				Map<String, Object> root = new HashMap<String, Object>();
				root.put("vo", vo);
				root.put("generate", ConfigFactory.config);
				init(root);
				FreemarkUtils.WriterPage(root);
				ConfigFactory.reset();
			}
		} else {
			//配置数据库所有的表
			String[] tableNames = (new TableParseImpl()).getAllTableName();
			FreemarkUtils.init(ConfigFactory.config.getTemplateDir());
			for(String tableName:tableNames){
				Table table = (new TableParseImpl()).getTable(tableName);
				try {
					table.getPrimaryKey().getColumnName();
				} catch (Exception e) {
					System.out.println("表"+tableName+"缺少主键，已跳过");
					continue;
				}
				EntityVo vo = EntityVoHandler.createVo(table);
				Map<String, Object> root = new HashMap<String, Object>();
				root.put("vo", vo);
				root.put("generate", ConfigFactory.config);
				init(root);
				FreemarkUtils.WriterPage(root);
				ConfigFactory.reset();
			}
		}
		
	}
	
	


}