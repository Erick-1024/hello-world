package com.travelzen.tops.mediaserver.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

public class HttpServletHelper {
	/**
	 * print the reqeust header
	 * 
	 * @param request
	 */
	@SuppressWarnings("rawtypes")
	public static void printHeader(HttpServletRequest request, Logger logger) {
		Enumeration headername = request.getHeaderNames();
		while (headername.hasMoreElements()) {
			Object o = headername.nextElement();
			logger.info(o.toString());
		}
	}
}
