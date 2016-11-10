package com.cana.credit.limit.dao.utils;

import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class CreditLimitIDGenerator {

	private static SequenceGenerator generator = SpringApplicationContext.getApplicationContext()
			.getBean(SequenceGenerator.class);

	public static String generateCreditLimitId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_CREDIT_LIMIT_ID, 3);
	}

	public static String generateCreditLimitAuditId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_CREDIT_LIMIT_AUDIT_ID, 7);
	}

}
