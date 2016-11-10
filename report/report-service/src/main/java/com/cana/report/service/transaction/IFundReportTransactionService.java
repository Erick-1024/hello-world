/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.service.transaction;

import java.util.List;

import org.joda.time.DateTime;

import com.cana.account.dao.po.Account;
import com.cana.report.dao.po.ReportBankAccountTradeFlow;
import com.cana.report.dao.po.ReportBankFlowPullFailRecord;
import com.cana.report.service.bo.BankFundReportBO;
import com.cana.report.service.bo.TradeReportBO;
import com.cana.vbam.common.account.dto.AccountGroupDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountTradeFlowDataDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.report.dto.ReportFundMonthlyResultDTO;
import com.cana.vbam.common.report.dto.ReportFundMonthlySearchCriteria;
import com.cana.vbam.common.report.enums.FundBalanceGetState;

/**
 * 用于操作资金报表相关数据库表的事务Service.<br/>
 * @author ducer
 *
 */
public interface IFundReportTransactionService {

	/**
	 * 是否是第一次拉取入金流水
	 */
	boolean isFirstPullDepositFlow(String customerId, DateTime reportDate);
	
	/**
	 * 获取银行入金流水，如果customerId为空，获取该日所有的入金流水
	 * 
	 * @param customerId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<ReportBankAccountTradeFlow> getDepositTradeFlows(String customerId, DateTime tradeDate, int page, int pageSize);

	/**
	 * 获取需要重新拉取银行交易流水的账号组
	 * @param customerId
	 * @return
	 */
	List<ReportBankFlowPullFailRecord> getDepositPullFailRecord(String customerId, DateTime reportDate);
	
	/**
	 * 拉取成功保存银行流水
	 * 
	 * @param tradeFlows
	 */
	void saveBankDepositFlowSuccess(AccountGroupDTO group, DateTime reportDate, List<BankAccountTradeFlowDataDTO> tradeFlows, boolean isRepull);

	/**
	 * 当失败的时候把这个账号保存到流水年里面，但是拉取状态是失败
	 * 
	 * @param group
	 */
	void saveBankFlowPullFailRecord(AccountGroupDTO group, DateTime reportDate);

	/**
	 * 三个报表数据是相关的，所以放到同一个事务里面
	 * @param customerId
	 * @param tradeBO
	 * @param depositBO
	 * @param balanceBO
	 */
	void saveFundReport(String customerId, DateTime reportDate, TradeReportBO tradeBO, BankFundReportBO bankFundBO);

	/**
	 * 更新资金报表中的：资金日报表、资金年报表、资金日报计数表.
	 * 只更新非空的业务对象的数据.
	 * @param customerId
	 * @param reportDate
	 * @param balanceBO
	 * @param tradeBO
	 * @param depositBO
	 */
	void updateFundReport(String customerId, DateTime reportDate, TradeReportBO tradeBO, BankFundReportBO bankFundBO);
	
	/**
	 * 查询资金月报表数据
	 * @param searchCriteria
	 * @return
	 */
	public ListResult<ReportFundMonthlyResultDTO> queryFundMonthlyReportData(ReportFundMonthlySearchCriteria searchCriteria);

	/**
	 * 查询资金月报表数据
	 * @param searchCriteria
	 * @return
	 */
	public List<ReportFundMonthlyResultDTO> queryFundMonthlyReportDataWithoutPage(ReportFundMonthlySearchCriteria searchCriteria);
	
	/**
	 * 保存报表状态
	 * @param account
	 * @param balance
	 * @param month
	 */
	public void saveFundMonthlyReport(Account account, Long balance, String month, FundBalanceGetState state, boolean isMainAccount);
	
	/**
	 * 获取当月总余额
	 * @param reportDate
	 * @return
	 */
	ObjectResult<String> getMonthlyBalanceSum(ReportFundMonthlySearchCriteria searchCriteria);
	
	/**
	 * 获取月报表主账户余额
	 * @param reportDate
	 * @return
	 */
	String getMainAccountBalance(String reportDate);
}
