/**
 * Copyright © 2016-2019 Cana. All rights reserved.
 */
package com.cana.flight.finance.dao.utils;

import com.cana.flight.finance.common.util.Constants;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

/**
 * @author ducer
 *
 */
public class IDGenerator {

	private static SequenceGenerator generator = SpringApplicationContext.getApplicationContext().getBean(SequenceGenerator.class);
	
	public static String generateTest(){
		return generator.getNextSeq("test-generate-id",5);
	}
	
	public static String generateWhiteCustomerId(){
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_WHITE_CUSTOMER_ID,3);
	}
	
	public static String generateDailyBillIdPrefix() {
		return DateTimeUtil.datetime14() + generator.getNextSeq(Constants.SEQUENCE_DAILY_BILL_ID, 10);
	}
	
	public static String generateDailyBillId(String prefix, int index) {
		StringBuilder suffix = new StringBuilder(String.valueOf(index));
		while (suffix.length() < 8) {
			suffix.insert(0, '0');
		}
		return suffix.insert(0, prefix).toString();
	}
}
