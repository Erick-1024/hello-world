/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.scheduler.batch;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.MDC;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.report.service.IAccountFundPullDataService;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

/**
 * @author ducer
 *
 */
public abstract class AbstractReportHandler<T> implements IHandler<T> {

	protected final int pageSize = 100;
	protected final DateTime yesterday = DateTime.now().minusDays(1);
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	protected IAccountFundPullDataService accountFundPullDataService = SpringApplicationContext.getApplicationContext().getBean(IAccountFundPullDataService.class);
	protected IVbamCommonService commonService = SpringApplicationContext.getApplicationContext().getBean(IVbamCommonService.class);
	
	@Override
	public void execute(List<T> tasks) {
		Transaction transaction = Cat.newTransaction("scheduler", "account-fund-report-task");
		MDC.put(Constants.SEQUENCE_ACCOUNT_FUND_REPORT_TASK_ID, RandomStringUtils.randomAlphanumeric(10));
		transaction.addData("account_fund_report_task_id", MDC.get(Constants.SEQUENCE_ACCOUNT_FUND_REPORT_TASK_ID));
		try {
			doExecute(tasks);
		} catch (Exception e) {
			logger.error("", e);
			Cat.getProducer().logError(e);
			transaction.setStatus(e);
			Cat.logMetricForCount("account-fund-report-task-error");
		} finally {
			MDC.clear();
			transaction.complete();
		}
	}

	public abstract void doExecute(List<T> tasks);
	
	protected DateTime getYesterDay() {
		String date = commonService.getCurrentDate();
		if (StringUtils.isBlank(date) || DateTime.now().isBefore(DateTimeUtil.parseDate10(date).plusDays(1))) {
			return yesterday;
		} else {
			return DateTimeUtil.parseDate10(date);
		}
	}
}
