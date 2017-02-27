package com.eshop4j.generate.appender;

import com.eshop4j.generate.config.AppenderConfig;
import com.eshop4j.generate.model.FileVo;

public interface BaseAppender {
	
	public abstract FileVo doInvoke(AppenderConfig appender);
}
