package com.travelzen.framework.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionUtil {

	private final static Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);
	
	public static String stackTrace2String(Throwable t) {
		StringWriter sw = new StringWriter();
		String result = "";
		try (PrintWriter pw = new PrintWriter(sw);) {
			t.printStackTrace(pw);
			result = sw.toString();
		} catch (Exception e) {
			logger.error("", e);
		}
		return result;
	}
	
}
