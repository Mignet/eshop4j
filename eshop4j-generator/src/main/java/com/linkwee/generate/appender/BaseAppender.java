package com.linkwee.generate.appender;

import com.linkwee.generate.config.AppenderConfig;
import com.linkwee.generate.model.FileVo;

public interface BaseAppender {
	
	public abstract FileVo doInvoke(AppenderConfig appender);
}
