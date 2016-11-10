/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.service.transaction.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.cana.account.dao.po.Account;
import com.cana.report.dao.mapper.ReportFundMonthlySumMapper;
import com.cana.report.dao.mapper.gen.ReportAccountFundCountMapper;
import com.cana.report.dao.mapper.gen.ReportAccountFundDailyMapper;
import com.cana.report.dao.mapper.gen.ReportAccountFundYearMapper;
import com.cana.report.dao.mapper.gen.ReportBankAccountTradeFlowMapper;
import com.cana.report.dao.mapper.gen.ReportBankFlowPullFailRecordMapper;
import com.cana.report.dao.mapper.gen.ReportFundMonthlyMapper;
import com.cana.report.dao.po.ReportAccountFundCount;
import com.cana.report.dao.po.ReportAccountFundCountExample;
import com.cana.report.dao.po.ReportAccountFundDaily;
import com.cana.report.dao.po.ReportAccountFundDailyExample;
import com.cana.report.dao.po.ReportAccountFundYear;
import com.cana.report.dao.po.ReportAccountFundYearExample;
import com.cana.report.dao.po.ReportBankAccountTradeFlow;
import com.cana.report.dao.po.ReportBankAccountTradeFlowExample;
import com.cana.report.dao.po.ReportBankFlowPullFailRecord;
import com.cana.report.dao.po.ReportBankFlowPullFailRecordExample;
import com.cana.report.dao.po.ReportFundMonthly;
import com.cana.report.dao.po.ReportFundMonthlyExample;
import com.cana.report.dao.po.ReportFundMonthlyExample.Criteria;
import com.cana.report.service.bo.BalanceReportBO;
import com.cana.report.service.bo.BankFundReportBO;
import com.cana.report.service.bo.DepositReportBO;
import com.cana.report.service.bo.OtherSupervisionBalanceReportBO;
import com.cana.report.service.bo.TradeReportBO;
import com.cana.report.service.transaction.IFundReportTransactionService;
import com.cana.vbam.common.account.dto.AccountGroupDTO;
import com.cana.vbam.common.account.enums.AccountStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.bankgate.dto.response.BankAccountTradeFlowDataDTO;
import com.cana.vbam.common.bankgate.enums.BankTradeType;
import com.cana.vbam.common.bankgate.enums.DebitCreditTag;
import com.cana.vbam.common.bankgate.enums.FundBizType;
import com.cana.vbam.common.bankgate.enums.TradeStatusFlag;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.report.dto.ReportFundMonthlyResultDTO;
import com.cana.vbam.common.report.dto.ReportFundMonthlySearchCriteria;
import com.cana.vbam.common.report.enums.FundBalanceGetState;
import com.cana.vbam.common.report.enums.FundReportState;
import com.cana.vbam.common.utils.AccountNoUtil;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

/**
 * @author ducer
 *
 */
@Service
public class FundReportTransactionServiceImpl implements IFundReportTransactionService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private ReportAccountFundDailyMapper reportAccountFundDailyMapper;
	@Resource
	private ReportAccountFundYearMapper reportAccountFundYearMapper;
	@Resource
	private ReportAccountFundCountMapper reportAccountFundCountMapper;
	@Resource
	private ReportBankAccountTradeFlowMapper reportBankAccountTradeFlowMapper;
	@Resource
	private ReportBankFlowPullFailRecordMapper reportBankFlowPullFailRecordMapper;
	@Resource
	private ReportFundMonthlyMapper reportFundMonthlyMapper;
	@Resource
	private ReportFundMonthlySumMapper reportFundMonthlySumMapper;
	@Resource
	private SequenceGenerator seqGen;

	@Override
	public synchronized void saveBankDepositFlowSuccess(AccountGroupDTO group, DateTime reportDate,
			List<BankAccountTradeFlowDataDTO> tradeFlows, boolean isRepull) {
		if (isRepull) {
			deleteBankFlowPullFailRecord(group, reportDate);
		}
		if (CollectionUtils.isEmpty(tradeFlows)) {
			return;
		}
		for (BankAccountTradeFlowDataDTO tradeFlow : tradeFlows) {
			// 入金，只保存入金流水，其他的流水全部查平台流水，保存的全是有效记录
			if (BankTradeType.ordinary_external_transfer.equals(tradeFlow.getBankTradeType())
					&& DebitCreditTag.credit.equals(tradeFlow.getDebitCreditTag())) {
				ReportBankAccountTradeFlow flow = new ReportBankAccountTradeFlow();
				flow.setCustomerId(group.getCustomerId());
				flow.setMainAccountNo(group.getMainAccountNo());
				flow.setAccountNo(group.getAccountNo());
				flow.setBankUserName(group.getBankUserName());
				flow.setTradeType(FundBizType.deposit_fund.name());
				flow.setTradeDate(tradeFlow.getTradeDate());
				flow.setTradeTime(tradeFlow.getTradeTime());
				flow.setTradeAmount(tradeFlow.getAmount());
				flow.setTradeState(TradeStatusFlag.success.name());
				flow.setTradeFee(tradeFlow.getFee());
				flow.setOppositeAccountName(tradeFlow.getOppositeAccountName());
				flow.setOppositeAccountNo(tradeFlow.getOppositeAccountNo());
				flow.setCashTransferfFlag(tradeFlow.getDebitCreditTag().name());
				flow.setAccountBalance(tradeFlow.getAccountBalance());
				flow.setCreateTime(new Date());

				AccountSupervisionStatus superStatus = getSupervisionStatus(group, tradeFlow);
				flow.setSupervisionStatus(superStatus.name());

				reportBankAccountTradeFlowMapper.insertSelective(flow);
			}
		}
	}

	private void deleteBankFlowPullFailRecord(AccountGroupDTO group, DateTime reportDate) {
		Date start = reportDate.withTime(0, 0, 0, 0).toDate();
		Date end = reportDate.withTime(23, 59, 59, 999).toDate();

		ReportBankFlowPullFailRecordExample ex = new ReportBankFlowPullFailRecordExample();
		ReportBankFlowPullFailRecordExample.Criteria criteria = ex.createCriteria();
		criteria.andCustomerIdEqualTo(group.getCustomerId());
		criteria.andReportDateBetween(start, end);
		if(StringUtils.isNotBlank(group.getBankUserName())) {
			criteria.andBankUserNameEqualTo(group.getBankUserName());
		}
		if(StringUtils.isNotBlank(group.getMainAccountNo())) {
			criteria.andMainAccountNoEqualTo(group.getMainAccountNo());
		}
		if(StringUtils.isNotBlank(group.getAccountNo())) {
			criteria.andAccountNoEqualTo(group.getAccountNo());
		}
		int count = reportBankFlowPullFailRecordMapper.countByExample(ex);
		if (count > 0) {
			reportBankFlowPullFailRecordMapper.deleteByExample(ex);
		}
	}
	
	private AccountSupervisionStatus getSupervisionStatus(AccountGroupDTO group,
			BankAccountTradeFlowDataDTO tradeFlow) {
		try {
			String temp = tradeFlow.getTradeDate() + tradeFlow.getTradeTime();
			Date bankDate = DateTimeUtil.parseDatetime14(temp).toDate();
			return group.isSupervisionWhen(bankDate);
		} catch (Exception e) {
			logger.error("转换银行记录时间失败，视为此条记录不发生在监管状态中", e);
			return AccountSupervisionStatus.NO_SUPERVISION;
		}
	}

	@Override
	public void saveBankFlowPullFailRecord(AccountGroupDTO group, DateTime reportDate) {
		ReportBankFlowPullFailRecord record = new ReportBankFlowPullFailRecord();
		record.setCustomerId(group.getCustomerId());
		record.setBankUserName(group.getBankUserName());
		record.setMainAccountNo(group.getMainAccountNo());
		record.setAccountNo(group.getAccountNo());
		record.setTradeType(FundBizType.deposit_fund.name());
		record.setReportDate(reportDate.toDate());
		record.setCreateTime(new Date());
		reportBankFlowPullFailRecordMapper.insertSelective(record);
	}

	@Override
	public void saveFundReport(String customerId, DateTime reportDate, TradeReportBO tradeBO,
			BankFundReportBO bankFundBO) {
		if (!existReport(customerId, reportDate)) {
			saveAccountFundDailyReport(customerId, reportDate, tradeBO, bankFundBO);
			saveAccountFundYearReport(customerId, reportDate, tradeBO, bankFundBO);
			saveAccountFundDailyCountReport(customerId, reportDate, tradeBO, bankFundBO);
		} else {
			logger.error("插入资金报表记录-异常-已经存在资金报表，重复插入，不再执行插入操作");
		}
	}

	private boolean existReport(String customerId, DateTime date) {
		ReportAccountFundDailyExample ex = new ReportAccountFundDailyExample();
		ReportAccountFundDailyExample.Criteria creteria = ex.createCriteria();
		creteria.andCustomerIdEqualTo(customerId).andReportDateEqualTo(DateTimeUtil.date10(date));
		int count = reportAccountFundDailyMapper.countByExample(ex);
		return count > 0;
	}

	private void saveAccountFundDailyReport(String customerId, DateTime reportDate, TradeReportBO tradeBO,
			BankFundReportBO bankFundBO) {
		String date = DateTimeUtil.date10(reportDate);
		ReportAccountFundDaily daily = new ReportAccountFundDaily();
		DepositReportBO depositBO = bankFundBO.getDepositReportBO();
		BalanceReportBO balanceBO = bankFundBO.getBalanceReportBO();
		OtherSupervisionBalanceReportBO suBalanceBO = bankFundBO.getOtherSupervisionBalanceBO();
		ReportAccountFundDaily yesterday = getYesterdayBalance(customerId, reportDate);

		daily.setCustomerId(customerId);
		daily.setReportDate(date);
		daily.setCreateTime(new Date());
		int state = tradeBO.getFundReportState().getCode() | depositBO.getFundReportState().getCode()
				| balanceBO.getFundReportState().getCode() | suBalanceBO.getFundReportState().getCode();
		daily.setFundReportState(state);

		daily.setTransferFund(tradeBO.getTransferFund());
		daily.setWithdrawFund(tradeBO.getWithdrawFund());
		daily.setWithdrawFee(tradeBO.getWithdrawFee());
		daily.setRefundFund(tradeBO.getRefundFund());
		daily.setFreezeFund(tradeBO.getFreezeFund());
		daily.setUnfreezeFund(tradeBO.getUnfreezeFund());

		daily.setSupervisionTransferFund(tradeBO.getSupervisionTransferFund());
		daily.setSupervisionWithdrawFund(tradeBO.getSupervisionWithdrawFund());
		daily.setSupervisionWithdrawFee(tradeBO.getSupervisionWithdrawFee());
		daily.setSupervisionRefundFund(tradeBO.getSupervisionRefundFund());
		daily.setSupervisionFreezeFund(tradeBO.getSupervisionFreezeFund());
		daily.setSupervisionUnfreezeFund(tradeBO.getSupervisionUnfreezeFund());
		
		daily.setDepositFund(depositBO.getDepositFund());
		daily.setSupervisionDepositFund(depositBO.getSupervisionDepositFund());

		daily.setCurrentBalance(balanceBO.getCurrentBalance());
		daily.setLastBalance(yesterday == null ? 0L : yesterday.getCurrentBalance());
		
		daily.setCurrentOwnSupervisionBalance(balanceBO.getCurrentOwnSupervisionBalance());
		daily.setLastOwnSupervisionBalance(yesterday == null ? 0L : yesterday.getCurrentOwnSupervisionBalance());

		daily.setCurrentOtherSupervisionBalance(suBalanceBO.getCurrentOherSupervisionBalance());
		daily.setLastOtherSupervisionBalance(yesterday == null ? 0L : yesterday.getCurrentOtherSupervisionBalance());

		try {
			reportAccountFundDailyMapper.insertSelective(daily);
		} catch (Exception e) {// 加了customerId reportDate的唯一索引
			if (ExceptionUtils.indexOfThrowable(e, DuplicateKeyException.class) != -1) {
				logger.error("插入报表数据-异常-重复插入同一个客户同一天的报表数据，客户ID:{},时间:{}", customerId, date);
				logger.error("重复插入直接抛出异常回滚");
			}
			throw e;
		}
	}

	private void saveAccountFundDailyCountReport(String customerId, DateTime reportDate, TradeReportBO tradeBO,
			BankFundReportBO bankFundBO) {
		ReportAccountFundCount count = new ReportAccountFundCount();
		DepositReportBO depositBO = bankFundBO.getDepositReportBO();
		BalanceReportBO balanceBO = bankFundBO.getBalanceReportBO();
		OtherSupervisionBalanceReportBO suBalanceBO = bankFundBO.getOtherSupervisionBalanceBO();

		count.setCustomerId(customerId);
		count.setReportDate(DateTimeUtil.date10(reportDate));
		count.setCreateTime(new Date());
		int state = tradeBO.getFundReportState().getCode() | depositBO.getFundReportState().getCode()
				| balanceBO.getFundReportState().getCode() | suBalanceBO.getFundReportState().getCode();
		count.setFundReportState(state);

		count.setTransferInCount(tradeBO.getTransferInCount());
		count.setTransferOutCount(tradeBO.getTransferOutCount());
		count.setWithdrawCount(tradeBO.getWithdrawCount());
		count.setRefundCount(tradeBO.getRefundCount());
		count.setFreezeCount(tradeBO.getFreezeCount());
		count.setUnfreezeCount(tradeBO.getUnfreezeCount());

		count.setSupervisionTransferInCount(tradeBO.getSupervisionTransferInCount());
		count.setSupervisionTransferOutCount(tradeBO.getSupervisionTransferOutCount());
		count.setSupervisionWithdrawCount(tradeBO.getSupervisionWithdrawCount());
		count.setSupervisionRefundCount(tradeBO.getSupervisionRefundCount());
		count.setSupervisionFreezeCount(tradeBO.getSupervisionFreezeCount());
		count.setSupervisionUnfreezeCount(tradeBO.getSupervisionUnfreezeCount());
		
		count.setDepositCount(depositBO.getDepositCount());
		count.setSupervisionDepositCount(depositBO.getSupervisionDepositCount());

		reportAccountFundCountMapper.insertSelective(count);
	}

	private void saveAccountFundYearReport(String customerId, DateTime reportDate, TradeReportBO tradeBO,
			BankFundReportBO bankFundBO) {
		String date = DateTimeUtil.date4(reportDate);
		ReportAccountFundYear annual = selectOrNewFundYearReport(customerId, date);
		DepositReportBO depositBO = bankFundBO.getDepositReportBO();
		BalanceReportBO balanceBO = bankFundBO.getBalanceReportBO();
		OtherSupervisionBalanceReportBO suBalanceBO = bankFundBO.getOtherSupervisionBalanceBO();
		ReportAccountFundYear lastYear = getLastYearBalance(customerId, reportDate);

		annual.setCustomerId(customerId);
		annual.setReportDate(date);
		annual.setCreateTime(new Date());
		int state = tradeBO.getFundReportState().getCode() | depositBO.getFundReportState().getCode()
				| balanceBO.getFundReportState().getCode() | suBalanceBO.getFundReportState().getCode();
		annual.setFundReportState(state);

		if (FundReportState.tradePass(tradeBO.getFundReportState().getCode())) {
			annual.setTransferFund(tradeBO.getTransferFund() + nullDefault(annual.getTransferFund(), 0L));
			annual.setWithdrawFund(tradeBO.getWithdrawFund() + nullDefault(annual.getWithdrawFund(), 0L));
			annual.setWithdrawFee(tradeBO.getWithdrawFee() + nullDefault(annual.getWithdrawFee(), 0L));
			annual.setRefundFund(tradeBO.getRefundFund() + nullDefault(annual.getRefundFund(), 0L));
			annual.setFreezeFund(tradeBO.getFreezeFund() + nullDefault(annual.getFreezeFund(), 0L));
			annual.setUnfreezeFund(tradeBO.getUnfreezeFund() + nullDefault(annual.getUnfreezeFund(), 0L));
			
			annual.setSupervisionTransferFund(tradeBO.getSupervisionTransferFund() + nullDefault(annual.getSupervisionTransferFund(), 0L));
			annual.setSupervisionWithdrawFund(tradeBO.getSupervisionWithdrawFund() + nullDefault(annual.getSupervisionWithdrawFund(), 0L));
			annual.setSupervisionWithdrawFee(tradeBO.getSupervisionWithdrawFee() + nullDefault(annual.getSupervisionWithdrawFee(), 0L));
			annual.setSupervisionRefundFund(tradeBO.getSupervisionRefundFund() + nullDefault(annual.getSupervisionRefundFund(), 0L));
			annual.setSupervisionFreezeFund(tradeBO.getSupervisionFreezeFund() + nullDefault(annual.getSupervisionFreezeFund(), 0L));
			annual.setSupervisionUnfreezeFund(tradeBO.getSupervisionUnfreezeFund() + nullDefault(annual.getSupervisionUnfreezeFund(), 0L));
		}

		if (FundReportState.depositPass(depositBO.getFundReportState().getCode())) {
			annual.setDepositFund(depositBO.getDepositFund() + nullDefault(annual.getDepositFund(), 0L));
			annual.setSupervisionDepositFund(depositBO.getSupervisionDepositFund() + nullDefault(annual.getSupervisionDepositFund(), 0L));
		}

		if (FundReportState.balancePass(balanceBO.getFundReportState().getCode())) {
			annual.setCurrentBalance(balanceBO.getCurrentBalance());
			annual.setLastBalance(lastYear == null ? 0L : lastYear.getCurrentBalance());
			
			annual.setCurrentOwnSupervisionBalance(balanceBO.getCurrentOwnSupervisionBalance());
			annual.setLastOwnSupervisionBalance(lastYear == null ? 0L : lastYear.getCurrentOwnSupervisionBalance());
		}
		
		if(FundReportState.supervisionBalancePass(suBalanceBO.getFundReportState().getCode())) {
			annual.setCurrentOtherSupervisionBalance(suBalanceBO.getCurrentOherSupervisionBalance());
			annual.setLastOtherSupervisionBalance(lastYear == null ? 0L : lastYear.getCurrentOtherSupervisionBalance());
		}

		if (annual.getId() == null) {
			reportAccountFundYearMapper.insertSelective(annual);
		} else {
			reportAccountFundYearMapper.updateByPrimaryKeySelective(annual);
		}
	}

	private Long nullDefault(Long value, Long defaultV) {
		if (value == null) {
			return defaultV;
		}
		return value;
	}

	/**
	 * 获取数据库资金年报表，如果不存在，返回一个新的记录
	 * 
	 * @param customerId
	 * @param reportDate
	 * @return
	 */
	private ReportAccountFundYear selectOrNewFundYearReport(String customerId, String reportDate) {
		ReportAccountFundYearExample ex = new ReportAccountFundYearExample();
		ReportAccountFundYearExample.Criteria criteria = ex.createCriteria();
		criteria.andCustomerIdEqualTo(customerId).andReportDateEqualTo(reportDate);
		List<ReportAccountFundYear> annuals = reportAccountFundYearMapper.selectByExample(ex);
		if (CollectionUtils.isNotEmpty(annuals)) {
			return annuals.get(0);
		}
		return new ReportAccountFundYear();
	}

	@Override
	public List<ReportBankAccountTradeFlow> getDepositTradeFlows(String customerId, DateTime reportDate, int page,
			int pageSize) {
		ReportBankAccountTradeFlowExample ex = new ReportBankAccountTradeFlowExample();
		ReportBankAccountTradeFlowExample.Criteria criteria = ex.createCriteria();
		if (StringUtils.isNotBlank(customerId)) {
			criteria.andCustomerIdEqualTo(customerId);
		}
		criteria.andTradeDateEqualTo(DateTimeUtil.date8(reportDate));
		ex.setOrderByClause("id asc");
		ex.setLimitStart((page - 1) * pageSize);
		ex.setLimitEnd(pageSize);
		return reportBankAccountTradeFlowMapper.selectByExample(ex);
	}

	@Override
	public void updateFundReport(String customerId, DateTime reportDate, TradeReportBO tradeBO,
			BankFundReportBO bankFundBO) {
		ReportAccountFundDaily daily = getAccountFundFailDailyReport(customerId, reportDate);
		ReportAccountFundCount count = getAccountFundFailCountReport(customerId, reportDate);
		ReportAccountFundYear year = getAccountFundYearReport(customerId, reportDate);
		ReportAccountFundYear lastYear = getLastYearBalance(customerId, reportDate);
		ReportAccountFundDaily yesterday = getYesterdayBalance(customerId, reportDate);

		BalanceReportBO balanceBO = bankFundBO.getBalanceReportBO();
		DepositReportBO depositBO = bankFundBO.getDepositReportBO();
		OtherSupervisionBalanceReportBO suBalanceBO = bankFundBO.getOtherSupervisionBalanceBO();

		if (balanceBO != null && FundReportState.balancePass(balanceBO.getFundReportState().getCode())) {
			daily.setCurrentBalance(balanceBO.getCurrentBalance());
			daily.setLastBalance(yesterday == null ? 0L : yesterday.getCurrentBalance());
			daily.setCurrentOwnSupervisionBalance(balanceBO.getCurrentOwnSupervisionBalance());
			daily.setLastOwnSupervisionBalance(yesterday == null ? 0L : yesterday.getCurrentOwnSupervisionBalance());
			daily.setFundReportState((daily.getFundReportState() | balanceBO.getFundReportState().getCode()));

			year.setCurrentBalance(balanceBO.getCurrentBalance());
			year.setLastBalance(lastYear == null ? 0L : lastYear.getCurrentBalance());
			year.setCurrentOwnSupervisionBalance(balanceBO.getCurrentOwnSupervisionBalance());
			year.setLastOwnSupervisionBalance(lastYear == null ? 0L : lastYear.getCurrentOwnSupervisionBalance());
			year.setFundReportState((year.getFundReportState() | balanceBO.getFundReportState().getCode()));
		}
		if (tradeBO != null && FundReportState.tradePass(tradeBO.getFundReportState().getCode())) {

			daily.setFundReportState((daily.getFundReportState() | tradeBO.getFundReportState().getCode()));
			
			daily.setTransferFund(tradeBO.getTransferFund());
			daily.setWithdrawFund(tradeBO.getWithdrawFund());
			daily.setWithdrawFee(tradeBO.getWithdrawFee());
			daily.setRefundFund(tradeBO.getRefundFund());
			daily.setFreezeFund(tradeBO.getFreezeFund());
			daily.setUnfreezeFund(tradeBO.getUnfreezeFund());
			
			daily.setSupervisionTransferFund(tradeBO.getSupervisionTransferFund());
			daily.setSupervisionWithdrawFund(tradeBO.getSupervisionWithdrawFund());
			daily.setSupervisionWithdrawFee(tradeBO.getSupervisionWithdrawFee());
			daily.setSupervisionRefundFund(tradeBO.getSupervisionRefundFund());
			daily.setSupervisionFreezeFund(tradeBO.getSupervisionFreezeFund());
			daily.setSupervisionUnfreezeFund(tradeBO.getSupervisionUnfreezeFund());
			
			year.setFundReportState((year.getFundReportState() | tradeBO.getFundReportState().getCode()));
			
			year.setTransferFund(tradeBO.getTransferFund() + year.getTransferFund());
			year.setWithdrawFund(tradeBO.getWithdrawFund() + year.getWithdrawFund());
			year.setWithdrawFee(tradeBO.getWithdrawFee() + year.getWithdrawFee());
			year.setRefundFund(tradeBO.getRefundFund() + year.getRefundFund());
			year.setFreezeFund(tradeBO.getFreezeFund() + year.getFreezeFund());
			year.setUnfreezeFund(tradeBO.getUnfreezeFund() + year.getUnfreezeFund());
			
			year.setSupervisionTransferFund(tradeBO.getSupervisionTransferFund() + year.getSupervisionTransferFund());
			year.setSupervisionWithdrawFund(tradeBO.getSupervisionWithdrawFund() + year.getSupervisionWithdrawFund());
			year.setSupervisionWithdrawFee(tradeBO.getSupervisionWithdrawFee() + year.getSupervisionWithdrawFee());
			year.setSupervisionRefundFund(tradeBO.getSupervisionRefundFund() + year.getSupervisionRefundFund());
			year.setSupervisionFreezeFund(tradeBO.getSupervisionFreezeFund() + year.getSupervisionFreezeFund());
			year.setSupervisionUnfreezeFund(tradeBO.getSupervisionUnfreezeFund() + year.getSupervisionUnfreezeFund());
			
			count.setFundReportState((count.getFundReportState() | tradeBO.getFundReportState().getCode()));
			
			count.setTransferInCount(tradeBO.getTransferInCount());
			count.setTransferOutCount(tradeBO.getTransferOutCount());
			count.setWithdrawCount(tradeBO.getWithdrawCount());
			count.setRefundCount(tradeBO.getRefundCount());
			count.setFreezeCount(tradeBO.getFreezeCount());
			count.setUnfreezeCount(tradeBO.getUnfreezeCount());
			
			count.setSupervisionTransferInCount(tradeBO.getSupervisionTransferInCount());
			count.setSupervisionTransferOutCount(tradeBO.getSupervisionTransferOutCount());
			count.setSupervisionWithdrawCount(tradeBO.getSupervisionWithdrawCount());
			count.setSupervisionRefundCount(tradeBO.getSupervisionRefundCount());
			count.setSupervisionFreezeCount(tradeBO.getSupervisionFreezeCount());
			count.setSupervisionUnfreezeCount(tradeBO.getSupervisionUnfreezeCount());
		}
		if (depositBO != null && FundReportState.depositPass(depositBO.getFundReportState().getCode())) {

			daily.setDepositFund(depositBO.getDepositFund());
			daily.setSupervisionDepositFund(depositBO.getSupervisionDepositFund());
			daily.setFundReportState((daily.getFundReportState() | depositBO.getFundReportState().getCode()));

			year.setDepositFund(depositBO.getDepositFund() + year.getDepositFund());
			year.setSupervisionDepositFund(depositBO.getSupervisionDepositFund() + year.getSupervisionDepositFund());
			year.setFundReportState((year.getFundReportState() | depositBO.getFundReportState().getCode()));

			count.setDepositCount(depositBO.getDepositCount());
			count.setSupervisionDepositCount(depositBO.getSupervisionDepositCount());
			count.setFundReportState((count.getFundReportState() | depositBO.getFundReportState().getCode()));
		}
		if(suBalanceBO != null && FundReportState.supervisionBalancePass(suBalanceBO.getFundReportState().getCode())) {
			daily.setCurrentOtherSupervisionBalance(suBalanceBO.getCurrentOherSupervisionBalance());
			daily.setLastOtherSupervisionBalance(yesterday == null ? 0L : yesterday.getCurrentOtherSupervisionBalance());
			daily.setFundReportState((daily.getFundReportState() | suBalanceBO.getFundReportState().getCode()));

			year.setCurrentOtherSupervisionBalance(suBalanceBO.getCurrentOherSupervisionBalance());
			year.setLastOtherSupervisionBalance(lastYear == null ? 0L : lastYear.getCurrentOtherSupervisionBalance());
			year.setFundReportState((year.getFundReportState() | suBalanceBO.getFundReportState().getCode()));
		}

		reportAccountFundDailyMapper.updateByPrimaryKeySelective(daily);

		reportAccountFundYearMapper.updateByPrimaryKeySelective(year);

		reportAccountFundCountMapper.updateByPrimaryKeySelective(count);
	}

	private ReportAccountFundDaily getAccountFundFailDailyReport(String customerId, DateTime reportDate) {
		String date = DateTimeUtil.date10(reportDate);
		ReportAccountFundDailyExample dailyEx = new ReportAccountFundDailyExample();
		ReportAccountFundDailyExample.Criteria criteria = dailyEx.createCriteria();
		criteria.andCustomerIdEqualTo(customerId);
		criteria.andReportDateEqualTo(date);
		criteria.andFundReportStateNotEqualTo(FundReportState.success.getCode());
		List<ReportAccountFundDaily> dailyReports = reportAccountFundDailyMapper.selectByExample(dailyEx);
		if (CollectionUtils.isNotEmpty(dailyReports)) {
			return dailyReports.get(0);
		}
		logger.error("不存在客户：{},时间:{} 条件下无效的资金日报表记录，无法进行更新", customerId, date);
		String msg = String.format("不存在客户：%s,时间:%s 条件下无效的资金日报表记录，无法进行更新", customerId, date);
		throw new RuntimeException(msg);
	}

	private ReportAccountFundCount getAccountFundFailCountReport(String customerId, DateTime reportDate) {
		String date = DateTimeUtil.date10(reportDate);
		ReportAccountFundCountExample countEx = new ReportAccountFundCountExample();
		ReportAccountFundCountExample.Criteria criteria = countEx.createCriteria();
		criteria.andCustomerIdEqualTo(customerId);
		criteria.andReportDateEqualTo(date);
		criteria.andFundReportStateNotEqualTo(FundReportState.success.getCode());
		List<ReportAccountFundCount> countReports = reportAccountFundCountMapper.selectByExample(countEx);
		if (CollectionUtils.isNotEmpty(countReports)) {
			return countReports.get(0);
		}
		logger.error("不存在客户：{},时间:{} 条件下无效的资金统计日报表记录，无法进行更新", customerId, date);
		String msg = String.format("不存在客户：%s,时间:%s 条件下无效的资金统计日报表记录，无法进行更新", customerId, date);
		throw new RuntimeException(msg);
	}

	/**
	 * 年报表并没有独立的无效记录，日报表有效则有效，无效则无效，所以不加状态标志. 年报表、统计表的操作和日报表都是放在一个事务里面
	 * 
	 * @param customerId
	 * @param date
	 * @return
	 */
	private ReportAccountFundYear getAccountFundYearReport(String customerId, DateTime date) {
		String year = DateTimeUtil.date4(date);
		ReportAccountFundYearExample annualEx = new ReportAccountFundYearExample();
		ReportAccountFundYearExample.Criteria criteria = annualEx.createCriteria();
		criteria.andCustomerIdEqualTo(customerId).andReportDateEqualTo(year);
		List<ReportAccountFundYear> annualReports = reportAccountFundYearMapper.selectByExample(annualEx);
		if (CollectionUtils.isNotEmpty(annualReports)) {
			return annualReports.get(0);
		}
		logger.error("不存在客户：{},时间:{} 条件下的资金年报表记录，无法进行更新", customerId, year);
		String msg = String.format("不存在客户：%s,时间:%s 条件下的资金年报表记录，无法进行更新", customerId, year);
		throw new RuntimeException(msg);
	}

	/**
	 * 获取上一年的年报，没有则返回null
	 * 
	 * @param customerId
	 * @param date
	 * @return
	 */
	private ReportAccountFundYear getLastYearBalance(String customerId, DateTime date) {
		String lastYear = DateTimeUtil.date4(date.minusYears(1));
		ReportAccountFundYearExample annualEx = new ReportAccountFundYearExample();
		ReportAccountFundYearExample.Criteria criteria = annualEx.createCriteria();
		criteria.andCustomerIdEqualTo(customerId).andReportDateEqualTo(lastYear);
		List<ReportAccountFundYear> annualReports = reportAccountFundYearMapper.selectByExample(annualEx);
		if (CollectionUtils.isNotEmpty(annualReports)) {
			return annualReports.get(0);
		}
		return null;
	}
	
	/**
	 * 获取上一日的年报，没有则返回null
	 * 
	 * @param customerId
	 * @param date
	 * @return
	 */
	private ReportAccountFundDaily getYesterdayBalance(String customerId, DateTime date) {
		String lastYear = DateTimeUtil.date10(date.minusDays(1));
		ReportAccountFundDailyExample dailyEx = new ReportAccountFundDailyExample();
		ReportAccountFundDailyExample.Criteria criteria = dailyEx.createCriteria();
		criteria.andCustomerIdEqualTo(customerId).andReportDateEqualTo(lastYear);
		List<ReportAccountFundDaily> annualReports = reportAccountFundDailyMapper.selectByExample(dailyEx);
		if (CollectionUtils.isNotEmpty(annualReports)) {
			return annualReports.get(0);
		}
		return null;
	}

	@Override
	public List<ReportBankFlowPullFailRecord> getDepositPullFailRecord(String customerId, DateTime reportDate) {
		ReportBankFlowPullFailRecordExample ex = new ReportBankFlowPullFailRecordExample();
		Date start = reportDate.withTime(0, 0, 0, 0).toDate();
		Date end = reportDate.withTime(23, 59, 59, 999).toDate();
		ex.createCriteria().andCustomerIdEqualTo(customerId).andReportDateBetween(start, end);
		List<ReportBankFlowPullFailRecord> flows = reportBankFlowPullFailRecordMapper.selectByExample(ex);
		return flows;
	}

	@Override
	public boolean isFirstPullDepositFlow(String customerId, DateTime reportDate) {
		Date start = reportDate.withTime(0, 0, 0, 0).toDate();
		Date end = reportDate.withTime(23, 59, 59, 999).toDate();

		ReportBankFlowPullFailRecordExample failEx = new ReportBankFlowPullFailRecordExample();
		failEx.createCriteria().andCustomerIdEqualTo(customerId).andReportDateBetween(start, end);
		int failCount = reportBankFlowPullFailRecordMapper.countByExample(failEx);

		ReportBankAccountTradeFlowExample flowEx = new ReportBankAccountTradeFlowExample();
		flowEx.createCriteria().andCustomerIdEqualTo(customerId).andTradeDateEqualTo(DateTimeUtil.date8(reportDate));
		int flowCount = reportBankAccountTradeFlowMapper.countByExample(flowEx);

		ReportAccountFundDailyExample reportEx = new ReportAccountFundDailyExample();
		reportEx.createCriteria().andCustomerIdEqualTo(customerId).andReportDateEqualTo(DateTimeUtil.date10(reportDate));
		int reportCount = reportAccountFundDailyMapper.countByExample(reportEx);

		return failCount == 0 && flowCount == 0 && reportCount == 0;
	}

	@Override
	public ListResult<ReportFundMonthlyResultDTO> queryFundMonthlyReportData(ReportFundMonthlySearchCriteria searchCriteria) {
		ReportFundMonthlyExample example = assembleDaySearchCriteria(searchCriteria);
		example.setLimitStart((searchCriteria.getPage() - 1) * searchCriteria.getPageSize());
		example.setLimitEnd(searchCriteria.getPageSize());
		example.setOrderByClause("update_time desc");
		List<ReportFundMonthly> reportFundMonthlyList = reportFundMonthlyMapper.selectByExample(example);
		return ListResult.success(convertReportFundMonthlyToDTO(reportFundMonthlyList), reportFundMonthlyMapper.countByExample(example));
	}

	@Override
	public List<ReportFundMonthlyResultDTO> queryFundMonthlyReportDataWithoutPage(ReportFundMonthlySearchCriteria searchCriteria) {
		ReportFundMonthlyExample example = assembleDaySearchCriteria(searchCriteria);
		List<ReportFundMonthly> reportFundMonthlyList = reportFundMonthlyMapper.selectByExample(example);
		return convertReportFundMonthlyToDTO(reportFundMonthlyList);
	}
	

	private ReportFundMonthlyExample assembleDaySearchCriteria(ReportFundMonthlySearchCriteria searchCriteria){
		ReportFundMonthlyExample example = new ReportFundMonthlyExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(searchCriteria.getAccountNo())){
			criteria.andAccountNoLike("%" + AccountNoUtil.parseBankAccountNo(searchCriteria.getAccountNo()) + "%");
		}
		if(StringUtils.isNotBlank(searchCriteria.getFinanceName())){
			criteria.andFinanceNameLike("%" + searchCriteria.getFinanceName() + "%");
		}
		if(StringUtils.isNotBlank(searchCriteria.getAccountName())){
			criteria.andAccountNameLike("%" + searchCriteria.getAccountName() + "%");
		}
		if(StringUtils.isNotBlank(searchCriteria.getFactorName())){
			criteria.andFactorNameLike("%" + searchCriteria.getFactorName() + "%");
		}
		if(null != searchCriteria.getAccountType()){
			criteria.andAccountTypeEqualTo(searchCriteria.getAccountType().name());
		}
		if(null != searchCriteria.getSupervisionStatus()){
			criteria.andSupervisionStatusEqualTo(searchCriteria.getSupervisionStatus().name());
		}
		if(null != searchCriteria.getTransferInAccount()){
			criteria.andIsTransferInAccountEqualTo(searchCriteria.getTransferInAccount());
		}
		if(null != searchCriteria.getAccountStatus()){
			criteria.andAccountStatusEqualTo(searchCriteria.getAccountStatus().name());
		}
		if(null != searchCriteria.getFundBalanceGetState()){
			criteria.andResultEqualTo(searchCriteria.getFundBalanceGetState().name());
		}
		
		if(StringUtils.isNotBlank(searchCriteria.getReportDate())){
			criteria.andMonthEqualTo(searchCriteria.getReportDate());
		}
		criteria.andIsMainAccountEqualTo(Boolean.FALSE);
		return example;
	}
	
	private List<ReportFundMonthlyResultDTO> convertReportFundMonthlyToDTO(List<ReportFundMonthly> reportFundMonthlyList){
		List<ReportFundMonthlyResultDTO> reportFundMonthlyResultList = Lists.newArrayList();
		for(ReportFundMonthly reportFundMonthly:reportFundMonthlyList){
			ReportFundMonthlyResultDTO reportFundMonthlyResultDTO = new ReportFundMonthlyResultDTO();
			BeanUtils.copyProperties(reportFundMonthly, reportFundMonthlyResultDTO);
			reportFundMonthlyResultDTO.setAccountNo(AccountNoUtil.formatBankAccountNo(reportFundMonthly.getAccountNo()));
			reportFundMonthlyResultDTO.setAccountType(AccountType.valueOf(reportFundMonthly.getAccountType()).desc());
			reportFundMonthlyResultDTO.setSupervisionStatus(AccountSupervisionStatus.valueOf(reportFundMonthly.getSupervisionStatus()).desc());
			reportFundMonthlyResultDTO.setIsTransferInAccount(reportFundMonthly.getIsTransferInAccount() == true ? "是":"否");
			reportFundMonthlyResultDTO.setAccountStatus(AccountStatus.valueOf(reportFundMonthly.getAccountStatus()).desc());
			reportFundMonthlyResultDTO.setBalance(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(reportFundMonthly.getBalance())));
			reportFundMonthlyResultDTO.setResult(FundBalanceGetState.valueOf(reportFundMonthly.getResult()).desc());
			reportFundMonthlyResultDTO.setUpdateTime(DateTimeUtil.convertDateToString(DateTimeUtil.DATE_TIME_PATTERN, new DateTime(reportFundMonthly.getUpdateTime())));
			reportFundMonthlyResultList.add(reportFundMonthlyResultDTO);
		}
		return reportFundMonthlyResultList;
	}

	@Override
	public void saveFundMonthlyReport(Account account, Long balance, String month, FundBalanceGetState state, boolean isMainAccount) {
		ReportFundMonthly reportFundMonthly = new ReportFundMonthly();
		reportFundMonthly.setId(generateReportFundMonthlyId());
		if(isMainAccount){
			reportFundMonthly.setId(generateReportFundMonthlyId());
			reportFundMonthly.setMonth(month);
			reportFundMonthly.setCreateTime(null);
			reportFundMonthly.setUpdateTime(null);
			if(StringUtils.equals(state.name(), FundBalanceGetState.success.name())){
				reportFundMonthly.setAccountName(account.getCompanyName());
			}
			reportFundMonthly.setBalance(balance);
			reportFundMonthly.setIsMainAccount(isMainAccount);
			reportFundMonthly.setResult(state.name());
			reportFundMonthlyMapper.insertSelective(reportFundMonthly);
			return;
		}
		BeanUtils.copyProperties(account, reportFundMonthly);
		reportFundMonthly.setCreateTime(null);
		reportFundMonthly.setUpdateTime(null);
		reportFundMonthly.setAccountName(account.getCompanyName());
		reportFundMonthly.setBalance(balance);
		if(StringUtils.equals(AccountSupervisionStatus.HAVE_SUPERVISION.name(), account.getSupervisionStatus())){
			if(StringUtils.equals(UserType.FACTOR.name(), account.getUserType())){
				reportFundMonthly.setFactorId(account.getCompanyId());
				reportFundMonthly.setFactorName(account.getCompanyName());
				reportFundMonthly.setFinanceName(account.getSupervisorName());
			}else if(StringUtils.equals(UserType.FINACE.name(), account.getUserType())){
				reportFundMonthly.setFactorId(account.getSupervisorId());
				reportFundMonthly.setFactorName(account.getSupervisorName());
				reportFundMonthly.setFinanceName(account.getCompanyName());
			}
		}else{
			if(StringUtils.equals(UserType.FACTOR.name(), account.getUserType())){
				reportFundMonthly.setFactorId(account.getCompanyId());
				reportFundMonthly.setFactorName(account.getCompanyName());
			}else if(StringUtils.equals(UserType.FINACE.name(), account.getUserType())){
				reportFundMonthly.setFinanceName(account.getCompanyName());
			}
		}
		reportFundMonthly.setId(generateReportFundMonthlyId());
		reportFundMonthly.setIsMainAccount(isMainAccount);
		reportFundMonthly.setIsTransferInAccount(account.getIsTransferInAccount());
		reportFundMonthly.setMonth(month);
		reportFundMonthly.setResult(state.name());
		reportFundMonthlyMapper.insertSelective(reportFundMonthly);
	}

	@Override
	public ObjectResult<String> getMonthlyBalanceSum(ReportFundMonthlySearchCriteria searchCriteria) {
		if(StringUtils.isNotBlank(searchCriteria.getAccountNo())){
			searchCriteria.setAccountNo(AccountNoUtil.parseBankAccountNo(searchCriteria.getAccountNo()));
		}
		Long balanceSum = reportFundMonthlySumMapper.getCurrentMonthAccountBalanceSum(searchCriteria);
		if(null == balanceSum){
			return ObjectResult.success("", MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(0l)));
		}
		return ObjectResult.success("获取成功", MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(balanceSum)));
	}

	@Override
	public String getMainAccountBalance(String reportDate) {
		if(StringUtils.isBlank(reportDate)){
			return "获取主账户余额异常";
		}
		ReportFundMonthlyExample example = new ReportFundMonthlyExample();
		example.createCriteria().andMonthEqualTo(reportDate).andIsMainAccountEqualTo(Boolean.TRUE);
		List<ReportFundMonthly> result = reportFundMonthlyMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(result)){
			return "获取主账户余额异常";
		}
		if(StringUtils.equals(FundBalanceGetState.fail.name(), result.get(0).getResult())){
			return "获取主账户余额失败";
		}
		return MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(result.get(0).getBalance()));
	}
	
	private String generateReportFundMonthlyId(){
		return DateTimeUtil.datetime12()
				+ seqGen.getNextSeq(Constants.REPORT_FUND_MONTHLY_ID, 4);
	}


}
