package com.cana.credit.service.utils;

import com.cana.vbam.common.credit.enums.Institution;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

public class CheckParamUtil {

	public static void checkInstitution(String institutionStr) {
		try {
			Institution.valueOf(institutionStr);
		} catch (Exception e) {
			throw WebException.instance(ReturnCode.TP3001);
		}
	}
}
