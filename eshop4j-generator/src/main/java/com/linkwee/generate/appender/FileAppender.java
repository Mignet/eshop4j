package com.linkwee.generate.appender;

import com.linkwee.generate.config.AppenderConfig;
import com.linkwee.generate.model.FileVo;

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
