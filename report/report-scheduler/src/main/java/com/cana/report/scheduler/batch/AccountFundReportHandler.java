/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.scheduler.batch;

import java.util.List;

import com.cana.report.service.bo.BalanceReportBO;
import com.cana.report.service.bo.BankFundReportBO;
import com.cana.report.service.bo.DepositReportBO;
import com.cana.report.service.bo.OtherSupervisionBalanceReportBO;
import com.cana.report.service.bo.TradeReportBO;

/**
 * @author ducer
 *
 */
public class AccountFundReportHandler extends AbstractReportHandler<String> {

	@Override
	public void doExecute(List<String> customerIds) {
		for (String customerId : customerIds) {
			logger.info("开始跑客户:{} 的资金报表",customerId);
			TradeReportBO tradeBO = pullCustomerTradeReport(customerId);
			BankFundReportBO bankFundBO = pullBankFundReport(customerId);
			accountFundPullDataService.saveCustomerFundReport(customerId, getYesterDay(), tradeBO, bankFundBO);
			logger.info("结束跑客户:{} 的资金报表",customerId);
		}
	}

	private TradeReportBO pullCustomerTradeReport(String customerId) {
		return accountFundPullDataService.pullCustomerTradeRecord(customerId,getYesterDay());
	}

	private BankFundReportBO pullBankFundReport(String customerId) {
		BankFundReportBO bankFundReportBO = new BankFundReportBO();
		
		BalanceReportBO balanceBO = accountFundPullDataService.pullCustomerBalanceReport(customerId,getYesterDay());
		DepositReportBO depositBO = accountFundPullDataService.pullCustomerDepositReport(customerId, getYesterDay());;
		OtherSupervisionBalanceReportBO suBalanceBO = accountFundPullDataService.pullCustomerOtherSupervisionBalanceReport(customerId, getYesterDay());
		
		bankFundReportBO.setBalanceReportBO(balanceBO);
		bankFundReportBO.setDepositReportBO(depositBO);
		bankFundReportBO.setOtherSupervisionBalanceBO(suBalanceBO);
		
		return bankFundReportBO;
	}
}
