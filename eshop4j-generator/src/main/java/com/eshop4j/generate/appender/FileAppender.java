package com.eshop4j.generate.appender;

import com.eshop4j.generate.config.AppenderConfig;
import com.eshop4j.generate.model.FileVo;

public class FileAppender implements BaseAppender{

	@Override
	public FileVo doInvoke(AppenderConfig appender) {
		FileVo fileVo = new FileVo();
		fileVo.setFileName(appender.getFileName());
		fileVo.setFilePath(appender.getPath());
		fileVo.setTemplateFile(appender.getTemplate());
		return fileVo;
	}
	
}
