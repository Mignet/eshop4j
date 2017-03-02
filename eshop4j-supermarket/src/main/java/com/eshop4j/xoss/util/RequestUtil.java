package com.eshop4j.xoss.util;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

public class RequestUtil {

	public static String readData(HttpServletRequest request) {
		BufferedReader br = null;
		try {
			br = request.getReader();
			return IOUtils.toString(br);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			IOUtils.closeQuietly(br);
		}
	}
}
