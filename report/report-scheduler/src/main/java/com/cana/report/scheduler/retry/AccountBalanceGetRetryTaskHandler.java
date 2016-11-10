package com.cana.report.scheduler.retry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.account.dao.po.Account;
import com.cana.account.service.utils.BankgateHelperUtil;
import com.cana.bankgate.api.BankgateApi;
import com.cana.report.service.transaction.IFundReportTransactionService;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.bankgate.dto.request.BankAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.BankMainAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceDataDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankMainAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.enums.BankTranStatus;
import com.cana.vbam.common.report.enums.FundBalanceGetState;
import com.cana.vbam.common.service.IVbamCommonService;
import com.google.gson.Gson;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.handler.AbstractRetryTaskHandler;
import com.travelzen.framework.retry.handler.HandlerStatus;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class AccountBalanceGetRetryTaskHandler extends AbstractRetryTaskHandler{
	
	private BankgateApi bankgateApi = SpringApplicationContext.getApplicationContext().getBean(BankgateApi.class);
	
	private IFundReportTransactionService fundReportTransactionService = SpringApplicationContext.getApplicationContext().getBean(IFundReportTransactionService.class);
	
	private IVbamCommonService commonService = SpringApplicationContext.getApplicationContext().getBean(IVbamCommonService.class);
	
	private static Logger logger = LoggerFactory.getLogger(AccountBalanceGetRetryTaskHandler.class);

	@Override
	public void execute(RetryTask task, HandlerStatus status) throws Exception {
		Account account = new Gson().fromJson(task.getData(), Account.class);
		if(account == null || StringUtils.isBlank(account.getAccountNo())){
			BankMainAccountBalanceResultDTO result = bankgateApi.queryBankMainAccountBalance(new BankMainAccountBalanceQueryDTO());
			if (!BankTranStatus.success.equals(result.getStatus())) {
				logger.error("查询主账户余额失败");
				throw new Exception("查询主账户余额失败");
		    } else {
		    	account.setAccountNo(result.getBankMainAccountBalanceDatas().get(0).getMainAccountNo());
		    	account.setCompanyName(result.getBankMainAccountBalanceDatas().get(0).getMainAccountName());
		    	fundReportTransactionService.saveFundMonthlyReport(account, result.getBankMainAccountBalanceDatas().get(0).getAvailableBalance(),
		    			DateTimeUtil.month7(new DateTime(commonService.getCurrentDate()).minusMonths(1)), FundBalanceGetState.success, Boolean.TRUE);
		    }
		}else{
			BankAccountBalanceDataDTO accountBalanceData = queryAccountBalance(account.getAccountNo());
			if(null != accountBalanceData){
				fundReportTransactionService.saveFundMonthlyReport(account, accountBalanceData.getAvailableBalance(), 
						DateTimeUtil.month7(new DateTime(commonService.getCurrentDate()).minusMonths(1)), FundBalanceGetState.success, Boolean.FALSE);
			}else{
				logger.error("余额查询失败");
				throw new Exception("余额查询失败");
			}
		}
	}
	
	public void onFail(RetryTask task) throws Exception{
		Account account = new Gson().fromJson(task.getData(), Account.class);
		if( account == null || StringUtils.isBlank(account.getAccountNo())){
			fundReportTransactionService.saveFundMonthlyReport(account, 0l, 
					DateTimeUtil.month7(new DateTime(commonService.getCurrentDate()).minusMonths(1)), FundBalanceGetState.fail, Boolean.TRUE);
		}else{
			fundReportTransactionService.saveFundMonthlyReport(account, 0l, 
					DateTimeUtil.month7(new DateTime(commonService.getCurrentDate()).minusMonths(1)), FundBalanceGetState.fail, Boolean.FALSE);
		}
	}

	private BankAccountBalanceDataDTO queryAccountBalance(String accountNo){
		BankAccountBalanceQueryDTO query = new BankAccountBalanceQueryDTO();
		query.setAccountNo(accountNo);
		BankAccountBalanceDataDTO bankData = null;
		try {
			BankAccountBalanceResultDTO result = bankgateApi.queryAccountBalance(query);
			AccountTradeStatus status = BankgateHelperUtil.parseStatus(result.getStatus());
			if (status == AccountTradeStatus.TRADE_SUCCESS
					&& CollectionUtils.isNotEmpty(result.getBankAccountBalanceDatas())) {
				bankData = result.getBankAccountBalanceDatas().get(0);
			}
			logger.error("网关查询账户余额失败！账号：{}，错误原因：{}", accountNo, result.getStatusText());
			return bankData;
		} catch (Exception e) {
			logger.error("查询账户余额失败！账号：{}", accountNo, e);
		}
		return bankData;
	}
}
