package com.cana.credit.openapi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.vbam.common.credit.openapi.TravelzenBaseResponse;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

public class ExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
	
	public static <T extends TravelzenBaseResponse> void handleException(Exception e, String logString, T response) {
		logger.error(logString, e);
		if(e instanceof WebException) {
			ReturnCode returnCode = ((WebException) e).getRetCode();
			response.setRetCode(returnCode.getRetCode());
			response.setRetMsg(e.getMessage());
		} else {
			response.setRetCode(ReturnCode.ERROR.getRetCode());
			response.setRetMsg(ReturnCode.ERROR.getRetMsg());
		}
	}
	
}
