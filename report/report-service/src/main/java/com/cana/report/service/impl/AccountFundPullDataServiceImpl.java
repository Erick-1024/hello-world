/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cana.account.api.IAccountApi;
import com.cana.bankgate.api.BankgateApi;
import com.cana.member.api.IUserApi;
import com.cana.report.dao.po.ReportBankAccountTradeFlow;
import com.cana.report.dao.po.ReportBankFlowPullFailRecord;
import com.cana.report.service.IAccountFundPullDataService;
import com.cana.report.service.IAccountFundReportService;
import com.cana.report.service.bo.BalanceReportBO;
import com.cana.report.service.bo.BankFundReportBO;
import com.cana.report.service.bo.DepositReportBO;
import com.cana.report.service.bo.OtherSupervisionBalanceReportBO;
import com.cana.report.service.bo.TradeReportBO;
import com.cana.report.service.transaction.IFundReportTransactionService;
import com.cana.vbam.common.account.dto.AccountCustomerTradeRecordQueryDTO;
import com.cana.vbam.common.account.dto.AccountGroupDTO;
import com.cana.vbam.common.account.dto.AccountTradeRecordDTO;
import com.cana.vbam.common.account.dto.BankAccountGroupDTO;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.account.enums.AccountTradeType;
import com.cana.vbam.common.bankgate.dto.request.BankAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.BankAccountTradeFlowQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.BankMainAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountTradeFlowDataDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountTradeFlowResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankMainAccountBalanceDataDTO;
import com.cana.vbam.common.bankgate.dto.response.BankMainAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.enums.BankTradeType;
import com.cana.vbam.common.bankgate.enums.BaseTranStatus;
import com.cana.vbam.common.bankgate.enums.FundBizType;
import com.cana.vbam.common.bankgate.enums.TradeStatusFlag;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.report.enums.FundReportState;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * @author ducer
 *
 */
@Service
public class AccountFundPullDataServiceImpl implements IAccountFundPullDataService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Gson gson = new Gson();
	private final int pageSize = 100; // 每次拉取数据100条
	@Resource
	private IAccountApi accountApi;
	@Resource
	private BankgateApi bankgateApi;
	@Resource
	private IUserApi userApi;
	@Resource
	private IFundReportTransactionService fundReportTransactionService;
	@Resource
	private IAccountFundReportService accountFundReportService;

	@Override
	public TradeReportBO pullCustomerTradeRecord(String customerId, DateTime reportDate) {
		TradeReportBO bo = new TradeReportBO();
		bo.setFundReportState(FundReportState.trade);
		for (int page = 0;;) {
			page++;
			AccountCustomerTradeRecordQueryDTO query = getParam(customerId, reportDate, page, pageSize);
			List<AccountTradeRecordDTO> records = null;
			try {
				records = accountApi.getCustomerTradeRecord(query);
			} catch (Throwable e) {
				logger.error("平台交易报表-异常-查询交易记录异常，查询条件:{}", gson.toJson(query));
				logger.info("平台交易报表-终止数据拉取，不抛出异常，失败状态会在fund_report_state中标志，由重试定时任务重新执行");
				logger.error("", e);
				bo.setFundReportState(FundReportState.fail);
				return bo;
			}
			if (CollectionUtils.isEmpty(records)) {
				logger.info("平台交易报表-执行-执行完企业:{} 的交易报表统计", customerId);
				break;
			}
			for (AccountTradeRecordDTO record : records) {
				if (AccountTradeStatus.TRADE_SUCCESS == record.getStatus()) {
					bo.update(record.getTradeType(), record.getAccountSupervisionStatus(), record.getAmount(), record.getOperateType());
				}
			}
		}
		return bo;
	}

	/**
	 * 获取查询参数
	 */
	private AccountCustomerTradeRecordQueryDTO getParam(String customerId,DateTime reportDate, int page, int pageSize) {
		AccountCustomerTradeRecordQueryDTO query = new AccountCustomerTradeRecordQueryDTO();
		query.setCustomerId(customerId);
		query.setAccountTradeTypes(Lists.newArrayList(
				AccountTradeType.TRANSFER_FUND,
				AccountTradeType.WITHDRAW_FUND,
				AccountTradeType.FREEZE,
				AccountTradeType.UNFREEZE));
		query.setMinEndDate(reportDate.withTime(0, 0, 0, 0).toDate());
		query.setMaxEndDate(reportDate.plusDays(1).withTime(0 , 0, 0, 0).toDate());
		query.setPage(page);
		query.setPageSize(pageSize);
		return query;
	}

	@Override
	public DepositReportBO pullCustomerDepositReport(String customerId, DateTime reportDate) {
		DepositReportBO bo = new DepositReportBO();
		if (!isFirstPullDepositFlow(customerId, reportDate)) {
			repullCustomerBankDepositFlowData(customerId, reportDate);
		} else {
			pullCustomerBankDepositFlowData(customerId, reportDate);
		}
		if (!isDepositPullSuccess(customerId, reportDate)) {
			bo.setFundReportState(FundReportState.fail);
			return bo;
		}
		bo.setFundReportState(FundReportState.deposit);
		boolean isCanaManager = isCanaManager(customerId); // 是否CANA员工，CANA员工可以看到的是整个平台的报表
		for (int page = 0;;) {
			page++;
			List<ReportBankAccountTradeFlow> flows = null;
			if (isCanaManager) {
				flows = fundReportTransactionService.getDepositTradeFlows(null, reportDate, page, pageSize);
			} else {
				flows = fundReportTransactionService.getDepositTradeFlows(customerId, reportDate, page, pageSize);
			}
			if (CollectionUtils.isEmpty(flows)) {
				break;
			}
			for (ReportBankAccountTradeFlow flow : flows) {
				if (TradeStatusFlag.success.name().equals(flow.getTradeState())
						&& FundBizType.deposit_fund.name().equals(flow.getTradeType())) {
					bo.update(flow.getSupervisionStatus(), flow.getTradeAmount());
				}
			}
		}
		return bo;
	}
	
	/**
	 * 拉取客户银行入金流水记录到银行交易流水表
	 */
	private void pullCustomerBankDepositFlowData(String customerId, DateTime reportDate) {
		List<AccountGroupDTO> groups = null;
		try {
			groups = accountApi.getOwnAccountGroups(customerId);
		} catch (Throwable e) {
			logger.error("", e);
			AccountGroupDTO group = new AccountGroupDTO();
			group.setCustomerId(customerId);
			fundReportTransactionService.saveBankFlowPullFailRecord(group, reportDate);
			return;
		}
		if (!CollectionUtils.isEmpty(groups)) {
			logger.info("本次拉取客户:{}的银行入金流水记录，客户账号数:{}", customerId, groups.size());
			pullAccountBankDepositFlowData(groups, reportDate, false);
		}
	}

	private void pullAccountBankDepositFlowData(List<AccountGroupDTO> groups, DateTime reportDate, boolean isRepull) {
		for (AccountGroupDTO group : groups) {
			BankAccountTradeFlowQueryDTO query = getDepositQueryParam(group, reportDate);
			try {
				BankAccountTradeFlowResultDTO result = bankgateApi.queryBankAccountTradeFlow(query);
				if (BaseTranStatus.success.equals(result.getStatus().toBaseStatus())) {
					if (result.getSize() <= 0) {
						logger.info("拉取银行入金流水-成功-没有入金流水记录");
					}
					List<BankAccountTradeFlowDataDTO> datas = result.getBankAccountTradeFlowDatas();
					fundReportTransactionService.saveBankDepositFlowSuccess(group, reportDate, datas, isRepull);
				} else {
					throw new RuntimeException("调用网关接口查询银行入金流水失败,原因:" + result.getStatusText());// 抛到catch块
				}
			} catch (Throwable e) {
				if (!isRepull) {
					fundReportTransactionService.saveBankFlowPullFailRecord(group, reportDate);
					logger.error("拉取银行入金流水-异常-拉取异常，保存失败的帐号记录到拉取失败记录，用于二次拉取", e);
				}
				logger.error("拉取入金流水失败，主账号：{},附属帐号:{},用户名{},报表日期:{}", group.getMainAccountNo(), group.getAccountNo(),
						group.getBankUserName(), query.getStartDate());
			}
		}
	}

	/**
	 * 拉取账号在银行的所有流水，按时间排序，最晚的一个流水的余额作为当日余额创建日报记录
	 * <p>Note:准确的说不是入金，而是包含了入金和出金,入金在返回结果里面进行区分
	 */
	private BankAccountTradeFlowQueryDTO getDepositQueryParam(AccountGroupDTO group, DateTime reportDate) {
		BankAccountTradeFlowQueryDTO query = new BankAccountTradeFlowQueryDTO();
		query.setAccountNo(group.getAccountNo());
		query.setStartDate(DateTimeUtil.date8(reportDate));
		query.setEndDate(DateTimeUtil.date8(reportDate));
		query.setBankTradeType(BankTradeType.ordinary_external_transfer);
		// 外部转账包含入金和出金，通过DebitCreditTag区分是入金还是出金
		return query;
	}

	private boolean isCanaManager(String customerId) {
		CustomerDetailDTO customer = null;
		try {
			customer = userApi.queryCustomerDetail(customerId);
		} catch (Throwable e) {
			logger.error("获取用户详情-异常-默认视为该用户是普通用户，不是CANA");
			logger.error("", e);
			return false;
		}
		if (UserType.CANA == customer.getUserType()) {
			return true;
		}
		return false;
	}
	
	private boolean isFirstPullDepositFlow(String customerId, DateTime reportDate) {
		return fundReportTransactionService.isFirstPullDepositFlow(customerId, reportDate);
	}
	
	private boolean isDepositPullSuccess(String customerId, DateTime reportDate) {
		List<ReportBankFlowPullFailRecord> records = fundReportTransactionService.getDepositPullFailRecord(customerId,reportDate);
		if (CollectionUtils.isEmpty(records)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 重新拉取之前拉取失败的银行流水,存在全部重新拉取和部分重新拉取情况
	 */
	private void repullCustomerBankDepositFlowData(String customerId, DateTime reportDate) {
		List<ReportBankFlowPullFailRecord> records = fundReportTransactionService.getDepositPullFailRecord(customerId,reportDate);
		if (!CollectionUtils.isEmpty(records)) {
			if (needRepullAllDepositFlowForCustomer(records)) {
				pullCustomerBankDepositFlowData(customerId, reportDate);
				logger.info("客户:{} 所有的交易流水需要重新拉取", customerId);
			} else {
				logger.info("客户:{} 只有{}条交易流水需要重新拉取", customerId, records.size());
				List<AccountGroupDTO> groups = extractGropFromPullFailRecord(customerId, records);
				pullAccountBankDepositFlowData(groups, reportDate, true);
			}
		}
	}
	
	private List<AccountGroupDTO> extractGropFromPullFailRecord(String customerId,
			List<ReportBankFlowPullFailRecord> records) {
		List<AccountGroupDTO> groups = Lists.newArrayList();
		if (CollectionUtils.isEmpty(records)) {
			return groups;
		}
		try {
			groups = accountApi.getOwnAccountGroups(customerId);
		} catch (Throwable e) {
			logger.error("", e);
		}
		Map<String, AccountGroupDTO> maps = Maps.newHashMap();
		for (ReportBankFlowPullFailRecord record : records) {
			AccountGroupDTO group = new AccountGroupDTO();
			group.setCustomerId(record.getCustomerId());
			group.setBankUserName(record.getBankUserName());
			group.setMainAccountNo(record.getMainAccountNo());
			group.setAccountNo(record.getAccountNo());
			maps.put(record.getAccountNo(), group);
		}
		for (AccountGroupDTO group : groups) {
			if (null != maps.get(group.getAccountNo())) {
				maps.get(group.getAccountNo()).setSupervisionDates(group.getSupervisionDates());
			}
		}
		return Lists.newArrayList(maps.values());
	}
	
	private boolean needRepullAllDepositFlowForCustomer(List<ReportBankFlowPullFailRecord> records) {
		for (ReportBankFlowPullFailRecord record : records) {
			if (StringUtils.isBlank(record.getAccountNo())) { // 如果存在账号为空，则重新拉取该客户的所有银行入金记录
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void saveCustomerFundReport(String customerId, DateTime reportDate, TradeReportBO tradeBO,
			BankFundReportBO bankFundBO) {
		fundReportTransactionService.saveFundReport(customerId, reportDate, tradeBO, bankFundBO);
	}

	@Override
	public BalanceReportBO pullCustomerBalanceReport(String customerId, DateTime reportDate) {
		if (isCanaManager(customerId)) {
			return pullCanaPlatformBalanceReport(customerId, reportDate);
		} else {
			return pullSimpleCustomerBalanceReport(customerId, reportDate);
		}
	}

	/**
	 * 获取一般客户的余额报表
	 */
	private BalanceReportBO pullSimpleCustomerBalanceReport(String customerId, DateTime reportDate) {
		BalanceReportBO bo = new BalanceReportBO();
		bo.setFundReportState(FundReportState.balance);
		Long currentBalance = 0L;
		Long currentOwnSupervisionBalance = 0L;
		List<AccountGroupDTO> groups = null;
		try {
			groups = accountApi.getOwnAccountGroups(customerId);
		} catch (Throwable e) {
			logger.error("", e);
			bo.setFundReportState(FundReportState.fail);
			return bo;
		}
		if (CollectionUtils.isEmpty(groups)) {
			logger.info("本次拉取客户:{}的银行入金流水记录，客户账号数为零，不需要拉取");
			return bo;
		}
		logger.info("本次拉取客户:{}的银行入金流水记录，客户账号数:{}", customerId, groups.size());
		try {
			for (AccountGroupDTO group : groups) {
				Long balance = pullBalance(group);
				currentBalance += balance;
				Date yesterday24 = DateTime.now().withTime(0, 0, 0, 0).toDate();
				if (AccountSupervisionStatus.HAVE_SUPERVISION.equals(group.isSupervisionWhen(yesterday24))) {
					currentOwnSupervisionBalance += balance;
				}
			}
		} catch (Throwable e) {
			logger.error("拉取客户账户余额-异常，抛出异常");
			logger.error("", e);
			bo.setFundReportState(FundReportState.fail);
			return bo;
		}
		bo.setCurrentBalance(currentBalance);
		bo.setCurrentOwnSupervisionBalance(currentOwnSupervisionBalance);
		return bo;
	}
	
	/**
	 * 获取Cana保理平台总的余额报表
	 */
	private BalanceReportBO pullCanaPlatformBalanceReport(String customerId, DateTime reportDate) {
		BalanceReportBO bo = new BalanceReportBO();
		bo.setFundReportState(FundReportState.balance);
		Long currentBalance = 0L;
		List<BankAccountGroupDTO> groups = null;
		try {
			groups = accountApi.getBankAccountGroups();
		} catch (Throwable e) {
			logger.error("", e);
			bo.setFundReportState(FundReportState.fail);
			return bo;
		}
		if (CollectionUtils.isEmpty(groups)) {
			logger.info("拉取平台所有主账号-为空-不存在平台主账号，这种情况不应该存在");
			bo.setFundReportState(FundReportState.fail);
			return bo;
		}
		for (BankAccountGroupDTO group : groups) {
			String userName = group.getBankUserName();
			List<String> mainAccountNos = group.getMainAccountNos();
			try {
				Long balance = pullPlatformAllAccountBalance(userName, mainAccountNos);
				currentBalance += balance;
			} catch (Throwable e) {
				logger.error("拉取平台主账号余额-异常，抛出异常，查询条件,bankUserName:{},mainAccountNos:{}", userName,
						gson.toJson(mainAccountNos));
				logger.error("", e);
				bo.setFundReportState(FundReportState.fail);
				break;
			}
		}
		bo.setCurrentBalance(currentBalance);
		return bo;
	}
	
	private Long pullBalance(AccountGroupDTO group) throws Exception {
		BankAccountBalanceQueryDTO query = new BankAccountBalanceQueryDTO();
		query.setAccountNo(group.getAccountNo());
		try {
			BankAccountBalanceResultDTO result = bankgateApi.queryAccountBalance(query);
			if (result.getStatus().toBaseStatus().equals(BaseTranStatus.success)
					&& !CollectionUtils.isEmpty(result.getBankAccountBalanceDatas())) {
				return result.getBankAccountBalanceDatas().get(0).getAvailableBalance();
			} else {
				throw new RuntimeException("调用网关接口查询银行余额数据失败,原因:" + result.getStatusText());
			}
		} catch (Throwable e) {
			logger.error("拉取客户账户余额-异常-参数:{}", gson.toJson(group));
			throw e;
		}
	}

	private Long pullPlatformAllAccountBalance(String userName, List<String> mainAccountNos) throws Exception {
		BankMainAccountBalanceQueryDTO query = new BankMainAccountBalanceQueryDTO();
		query.setBankUserName(userName);
		query.setMainAccountNos(mainAccountNos);
		Long mainAccountBalance = 0L;
		try {
			BankMainAccountBalanceResultDTO result = bankgateApi.queryBankMainAccountBalance(query);
			if (result.getStatus().toBaseStatus().equals(BaseTranStatus.success)
					&& !CollectionUtils.isEmpty(result.getBankMainAccountBalanceDatas())) {
				for (BankMainAccountBalanceDataDTO data : result.getBankMainAccountBalanceDatas()) {
					mainAccountBalance += data.getAvailableBalance();
				}
			} else {
				throw new RuntimeException("调用网关接口查询银行主账号余额数据失败,原因:" + result.getStatusText());
			}
		} catch (Throwable e) {
			logger.error("拉取平台所有账户的余额-异常-参数：{}", gson.toJson(query));
			throw e;
		}
		return mainAccountBalance;
	}
	
	@Override
	public void updateCustomerFundReport(String customerId, DateTime reportDate, BalanceReportBO balanceBO,
			TradeReportBO tradeBO, DepositReportBO depositBO, OtherSupervisionBalanceReportBO suBalanceBO) {
		BankFundReportBO bankFundBO = new BankFundReportBO();
		bankFundBO.setBalanceReportBO(balanceBO);
		bankFundBO.setDepositReportBO(depositBO);
		bankFundBO.setOtherSupervisionBalanceBO(suBalanceBO);
		fundReportTransactionService.updateFundReport(customerId, reportDate, tradeBO, bankFundBO);

	}

	@Override
	public OtherSupervisionBalanceReportBO pullCustomerOtherSupervisionBalanceReport(String customerId,
			DateTime reportDate) {
		OtherSupervisionBalanceReportBO bo = new OtherSupervisionBalanceReportBO();
		bo.setFundReportState(FundReportState.supervision_balance);
		Long currentOherSupervisionBalance = 0L;
		List<AccountGroupDTO> groups = null;
		try {
			groups = accountApi.getOtherSupervisionAccountGroups(customerId);
		} catch (Throwable e) {
			logger.info("拉取客户账号-异常-客户ID:{}", customerId);
			logger.error("", e);
			bo.setFundReportState(FundReportState.fail);
			return bo;
		}
		if (CollectionUtils.isEmpty(groups)) {
			logger.info("拉取完客户ID:{}的所有账号,账号数为零", customerId);
			return bo;
		}
		try {
			for (AccountGroupDTO group : groups) {
				Long balance = pullBalance(group);
				currentOherSupervisionBalance += balance;
			}
		} catch (Throwable e) {
			logger.error("拉取非自有监管客户账户余额-异常，抛出异常");
			logger.error("", e);
			bo.setFundReportState(FundReportState.fail);
			return bo;
		}
		bo.setCurrentOherSupervisionBalance(currentOherSupervisionBalance);
		return bo;
	}
}
