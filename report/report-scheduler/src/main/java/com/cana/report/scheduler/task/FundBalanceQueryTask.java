package com.cana.report.scheduler.task;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cana.account.api.IAccountApi;
import com.cana.account.dao.mapper.gen.AccountMapper;
import com.cana.account.dao.po.Account;
import com.cana.account.dao.po.AccountExample;
import com.cana.account.service.utils.BankgateHelperUtil;
import com.cana.bankgate.api.BankgateApi;
import com.cana.repayment.dao.mapper.IRepaymentDailyBatchTaskMapper;
import com.cana.report.service.transaction.IFundReportTransactionService;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.bankgate.dto.request.BankAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.BankMainAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceDataDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankMainAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.enums.BankTranStatus;
import com.cana.vbam.common.report.enums.FundBalanceGetState;
import com.cana.vbam.common.service.impl.VbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.google.gson.GsonBuilder;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.retry.dao.mapper.gen.RetryTaskMapper;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.dict.RetryTaskPolicy;
import com.travelzen.framework.retry.dict.RetryTaskType;
import com.travelzen.framework.retry.policy.RetryTaskBackOffPolicy;

@Service
public class FundBalanceQueryTask{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private BankgateApi bankgateApi;
	
	@Resource
	private AccountMapper accountMapper;
	
	@Resource
	private IAccountApi accountApi;
	
	@Resource
	private IFundReportTransactionService fundReportTransactionService;
	
	@Resource
	private RetryTaskMapper retryTaskMapper;
	
	@Resource 
	private VbamCommonService commonService;
	
	@Resource
	private IRepaymentDailyBatchTaskMapper iRepaymentDailyBatchTaskMapper;

	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE * 1)
	public void doTask() throws Exception{
		MDC.put(Constants.TRACE_ID, RandomStringUtils.randomAlphanumeric(10));
		if(iRepaymentDailyBatchTaskMapper.countUnFinishedDefaultDeduct(commonService.getCurrentDate()) != 0 
				|| commonService.isFundReportTaskDone() 
				|| new DateTime(commonService.getCurrentDate()).getDayOfMonth() != 1
				|| new DateTime(new Date()).getHourOfDay() < 1){
			logger.info("未完成账扣的条数: " + iRepaymentDailyBatchTaskMapper.countUnFinishedDefaultDeduct(commonService.getCurrentDate()));
			logger.info(DateTimeUtil.month7(new DateTime(commonService.getCurrentDate()).minus(1)) + "月任务是否完成: " + commonService.isFundReportTaskDone());
			logger.info("现在时间是" + new DateTime(commonService.getCurrentDate()).getDayOfMonth() + "日");
			logger.info("暂不处理" + DateTimeUtil.month7(new DateTime(commonService.getCurrentDate()).minus(1)) + "资金月报表");
			return ;
		}
		logger.info("开始处理" + DateTimeUtil.month7(new DateTime(commonService.getCurrentDate()).minusDays(1)) + "资金月报表");
		AccountExample example = new AccountExample();
		example.createCriteria().andAccountNoIsNotNull();
		List<Account> accountList = accountMapper.selectByExample(example);
		for(Account account : accountList){
			BankAccountBalanceDataDTO bankAccountBalanceDataDTO = queryAccountBalance(account.getAccountNo());
			FundBalanceGetState state = bankAccountBalanceDataDTO == null ? FundBalanceGetState.fail : FundBalanceGetState.success;
			if(StringUtils.equals(state.name(), FundBalanceGetState.fail.name())){
				saveFailJobToRetryTask(account);
			}else{
				saveFundMonthlyReport(account, bankAccountBalanceDataDTO.getAvailableBalance(), state, Boolean.FALSE);
			}
		}
		BankMainAccountBalanceResultDTO result = bankgateApi.queryBankMainAccountBalance(new BankMainAccountBalanceQueryDTO());
		Account account = new Account();
		if (!BankTranStatus.success.equals(result.getStatus())) {
			saveFailJobToRetryTask(account);
	    } else {
	    	account.setAccountNo(result.getBankMainAccountBalanceDatas().get(0).getMainAccountNo());
	    	account.setCompanyName(result.getBankMainAccountBalanceDatas().get(0).getMainAccountName());
	    	saveFundMonthlyReport(account, result.getBankMainAccountBalanceDatas().get(0).getAvailableBalance(), FundBalanceGetState.success, Boolean.TRUE);
	    }
		commonService.markFundReportTaskDone();
		logger.info(DateTimeUtil.month7(new DateTime(commonService.getCurrentDate()).minusDays(1)) + "资金月报表，处理完成");
		MDC.clear();
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
	
	private void saveFundMonthlyReport(Account account, Long balance, FundBalanceGetState state, boolean reportFundMonthly){
		fundReportTransactionService.saveFundMonthlyReport(account, balance, DateTimeUtil.month7(new DateTime(commonService.getCurrentDate()).minusMonths(1)), state, reportFundMonthly);
	}
	
	private void saveFailJobToRetryTask(Account account){
		RetryTask task = new RetryTask();
		task.setTaskType(RetryTaskType.ACCOUT_BALANCE_GET.name());
		task.setTaskId(UUID.randomUUID().toString() + account.getAccountNo() + DateTimeUtil.month7(new DateTime(commonService.getCurrentDate()).minusMonths(1)));
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setFixedBackoffPeriod(120L);
		task.setMaxAttempts(5L);
		task.setData(new GsonBuilder().disableHtmlEscaping().create().toJson(account));
		retryTaskMapper.insertSelective(task);
	}
}
