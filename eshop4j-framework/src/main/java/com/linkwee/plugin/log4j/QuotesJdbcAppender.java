package com.linkwee.plugin.log4j;

import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.spi.LoggingEvent;

public class QuotesJdbcAppender extends JDBCAppender {
	protected String getLogStatement(LoggingEvent event) {
		if (null != event.getThrowableInformation()
				&& event.getThrowableInformation().getThrowable() instanceof SQLException) {
			SQLException myexce = new SQLException(
					event.getThrowableInformation().getThrowable().getMessage().replaceAll("'", ""),
					event.getThrowableInformation().getThrowable());
			LoggingEvent clone = new LoggingEvent(event.fqnOfCategoryClass, LogManager.getLogger(event.getLoggerName()),
					event.getLevel(), event.getMessage(), myexce);

			return getLayout().format(clone);
		}
		return getLayout().format(event);
	}

}