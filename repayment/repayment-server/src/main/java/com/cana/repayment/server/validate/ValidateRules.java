package com.cana.repayment.server.validate;

public class ValidateRules {

	public static final String DATE_REGEX = "^[0-9]{4}[-]([0][1-9]|[1][0-2])[-]([0][1-9]|[1-2][0-9]|[3][0-1])$";
	
	public static final String AMOUNT_REGEX = "^[0-9]+(.[0-9]{1,2})?$";
	
	public static final String DIGITAL_REGEX = "^[0-9]+$";
	
	public static final String PERCENT_REGEX = "^[0-9]+(.[0-9]{1,3})?(%)$";
	
}
