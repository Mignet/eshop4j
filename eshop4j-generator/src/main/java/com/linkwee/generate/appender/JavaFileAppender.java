package com.linkwee.generate.appender;

import java.io.File;

import com.linkwee.generate.config.AppenderConfig;
import com.linkwee.generate.model.FileVo;
import com.linkwee.generate.model.JavaVo;
import com.linkwee.generate.utils.StringUtils;

public class JavaFileAppender implements BaseAppender{

	@Override
	public FileVo doInvoke(AppenderConfig appender) {
		JavaVo javaVo = new JavaVo();
		String fullName = appender.getName();
		String shortName = fullName.substring(fullName.lastIndexOf(".")+1);
		String strPackage = fullName.substring(0,fullName.lastIndexOf("."));
		String aliasName = StringUtils.lowerCaseFirstChar(shortName);
		String packagePath = strPackage.replace(".",File.separator);
		
		javaVo.setFileName(shortName+".java");
		javaVo.setFilePath(appender.getPath()+File.separator+packagePath);
		javaVo.setTemplateFile(appender.getTemplate());
		
		javaVo.setAliasName(aliasName);
		javaVo.setFullName(fullName);
		javaVo.setShortName(shortName);
		javaVo.setStrPackage(strPackage);
		return javaVo;
	}

}
