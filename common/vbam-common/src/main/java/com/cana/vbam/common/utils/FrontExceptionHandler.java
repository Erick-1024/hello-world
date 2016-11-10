package com.cana.vbam.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.vbam.common.dto.BaseResponse;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

public class FrontExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(FrontExceptionHandler.class);
	
	public static ObjectResult<?> handleObjectResultException(Exception e) {
		if(e instanceof WebException) {
			WebException webException = (WebException)e;
			logger.warn(webException.getMessage(), e);
			return ObjectResult.fail(webException.getMessage());
		} else {
			logger.error("系统异常", e);
			return ObjectResult.fail(ReturnCode.ERROR.getRetMsg());
		}
	}
	
	public static ListResult<?> handleListResultException(Exception e) {
		if(e instanceof WebException) {
			WebException webException = (WebException)e;
			logger.warn(webException.getMessage(), e);
			return ListResult.fail(webException.getMessage());
		} else {
			logger.error("系统异常", e);
			return ListResult.fail(ReturnCode.ERROR.getRetMsg());
		}
	}
	
	public static <T extends BaseResponse> T handleBaseResponseException(Exception e, T response) {
		if(e instanceof WebException) {
			WebException webException = (WebException)e;
			logger.warn(webException.getMessage(), e);
			response.setRetCode(webException.getRetCode());
			response.setRetMsg(webException.getMessage());
		} else {
			logger.error("系统异常", e);
			response.setRetCode(ReturnCode.ERROR);
			response.setRetMsg("系统异常");
		}
		return response;
	}
	
}
