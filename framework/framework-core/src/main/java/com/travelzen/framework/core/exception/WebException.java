package com.travelzen.framework.core.exception;

import com.travelzen.framework.core.common.ReturnCode;

public class WebException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ReturnCode retCode;

	public static WebException instance(ReturnCode retCode) {
        return new WebException(retCode, retCode.getRetMsg(), null);
    }

	public static WebException instance(ReturnCode retCode, String retMsg) {
        return new WebException(retCode, retMsg, null);
    }

	public static WebException instance(String retMsg) {
		return new WebException(ReturnCode.ERROR, retMsg, null);
	}

	public WebException(ReturnCode retCode, String retMsg, Throwable thr) {
	    super(retMsg, thr);
	    this.retCode = retCode;
	}

	public ReturnCode getRetCode() {
        return retCode;
    }
}
