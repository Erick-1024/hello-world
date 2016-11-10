/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.scheduler.batch;

import java.util.List;

import org.joda.time.DateTime;

import com.cana.report.dao.po.ReportAccountFundDaily;
import com.cana.report.service.bo.BalanceReportBO;
import com.cana.report.service.bo.DepositReportBO;
import com.cana.report.service.bo.OtherSupervisionBalanceReportBO;
import com.cana.report.service.bo.TradeReportBO;
import com.cana.vbam.common.report.enums.FundReportState;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * @author ducer
 *
 */
public class AccountFundReportRetryHandler extends AbstractReportHandler<ReportAccountFundDaily> {

	@Override
	public void doExecute(List<ReportAccountFundDaily> tasks) {
		for (ReportAccountFundDaily task : tasks) {
			DateTime reportDate = DateTimeUtil.parseDate10(task.getReportDate());
			rePullCustomerFundReport(task.getCustomerId(), reportDate, task.getFundReportState());
		}
	}

	private void rePullCustomerFundReport(String customerId, DateTime reportDate, Integer fundReportState) {
		BalanceReportBO balanceBO = null;
		TradeReportBO tradeBO = null;
		DepositReportBO depositBO = null;
		OtherSupervisionBalanceReportBO suBalanceBO = null;

		List<FundReportState> states = FundReportState.parseFailReport(fundReportState);
		for (FundReportState state : states) {
			switch (state) {
			case balance:
				logger.info("重跑资金报表-重跑客户:{} 的余额报表",customerId);
				balanceBO = accountFundPullDataService.pullCustomerBalanceReport(customerId,reportDate);
				break;
			case deposit:
				logger.info("重跑资金报表-重跑客户:{} 的入金报表",customerId);
				depositBO = accountFundPullDataService.pullCustomerDepositReport(customerId, reportDate);
				break;
			case trade:
				logger.info("重跑资金报表-重跑客户:{} 的平台交易记录报表",customerId);
				tradeBO = accountFundPullDataService.pullCustomerTradeRecord(customerId, reportDate);
				break;
			case supervision_balance:
				logger.info("重跑资金报表-重跑客户:{} 的非自有监管账户余额报表",customerId);
				suBalanceBO = accountFundPullDataService.pullCustomerOtherSupervisionBalanceReport(customerId, reportDate);
			default:
				break;
			}
		}
		accountFundPullDataService.updateCustomerFundReport(customerId, reportDate, balanceBO, tradeBO, depositBO, suBalanceBO);
	}
}
