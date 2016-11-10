package com.cana.asset.service.transaction.util;

import java.util.regex.Pattern;

/**
 * @author hu
 *
 */
public class ValidateRules {

	public static final String DATE_REGEX = "^[0-9]{4}[-]([0][1-9]|[1][0-2])[-]([0][1-9]|[1-2][0-9]|[3][0-1])$";
	
	public static final String AMOUNT_REGEX = "^[0-9]+(.[0-9]{1,2})?$";
	
	public static final String AMOUNT_FLOAT_PEGEX = "^(\\d+)(\\.\\d+)?$";
	
	public static final String PERCENT_REGEX = "^[0-9]{1,2}(\\.[0-9]{1,3})?%?$|^100(\\.[0]{1,3})?%?$";
	
	public static boolean regexDateCheck(String value){
		return Pattern.matches(DATE_REGEX, value);
	}
	
	public static boolean regexAmountFlaotCheck(String value){
		return Pattern.matches(AMOUNT_FLOAT_PEGEX, value);
	}
	
	public static boolean regexAmountCheck(String value){
		return Pattern.matches(AMOUNT_REGEX, value);
	}
	
	public static boolean regexPercentCheck(String value){
		return Pattern.matches(PERCENT_REGEX, value);
	}
}