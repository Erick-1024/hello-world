/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.service;

import org.joda.time.DateTime;

import com.cana.report.service.bo.BalanceReportBO;
import com.cana.report.service.bo.BankFundReportBO;
import com.cana.report.service.bo.DepositReportBO;
import com.cana.report.service.bo.OtherSupervisionBalanceReportBO;
import com.cana.report.service.bo.TradeReportBO;

/**
 * @author ducer
 *
 */
public interface IAccountFundPullDataService {

	/**
	 * 拉取账户系统中的交易记录，返回统计后的交易报表BO
	 * @param customerId
	 * @return
	 */
	TradeReportBO pullCustomerTradeRecord(String customerId, DateTime reportDate);
	
	/**
	 * 只拉取入金报表
	 * @param customerId
	 * @param reportDate
	 * @return
	 */
	DepositReportBO pullCustomerDepositReport(String customerId, DateTime reportDate);
	
	/**
	 * 只拉取余额报表
	 * @param customerId
	 * @return
	 */
	BalanceReportBO pullCustomerBalanceReport(String customerId, DateTime reportDate);
	
	/**
	 * 拉取企业监管的非自有账户的余额报表
	 * @param customerId
	 * @return
	 */
	OtherSupervisionBalanceReportBO pullCustomerOtherSupervisionBalanceReport(String customerId, DateTime reportDate);
	
	/**
	 * 保存报表
	 * @param customerId
	 * @param tradeBO
	 * @param fundBO
	 */
	void saveCustomerFundReport(String customerId, DateTime reportDate, TradeReportBO tradeBO, BankFundReportBO bankFundBO);
	
	/**
	 * 更新资金报表中的：资金日报表、资金年报表、资金日报计数表.
	 * 只更新非空的业务对象的数据.
	 * @param customerId
	 * @param reportDate
	 * @param balanceBO
	 * @param tradeBO
	 * @param depositBO
	 */
	void updateCustomerFundReport(String customerId, DateTime reportDate, BalanceReportBO balanceBO, TradeReportBO tradeBO,
			DepositReportBO depositBO, OtherSupervisionBalanceReportBO suBalanceBO);
}
