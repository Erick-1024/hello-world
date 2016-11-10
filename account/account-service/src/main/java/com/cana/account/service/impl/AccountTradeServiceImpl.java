package com.cana.account.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cana.account.dao.mapper.AccountTableLockMapper;
import com.cana.account.dao.mapper.gen.AccountMapper;
import com.cana.account.dao.mapper.gen.AccountTradeRecordMapper;
import com.cana.account.dao.po.Account;
import com.cana.account.dao.po.AccountTradeRecord;
import com.cana.account.dao.po.AccountTradeRecordExample;
import com.cana.account.service.IAccountMessageService;
import com.cana.account.service.IAccountTradeService;
import com.cana.account.service.transaction.IAccountAuthorityTransactionService;
import com.cana.account.service.transaction.IAccountTradeTransactionService;
import com.cana.account.service.transaction.IAccountTransactionService;
import com.cana.account.service.transaction.ICustomerTransactionService;
import com.cana.account.service.utils.AccountIDGenerator;
import com.cana.account.service.utils.BankgateHelperUtil;
import com.cana.bankgate.api.BankgateApi;
import com.cana.member.dao.po.User;
import com.cana.vbam.common.account.consts.AccountConsts;
import com.cana.vbam.common.account.dto.AccountPrintCodeDTO;
import com.cana.vbam.common.account.dto.AccountPrintCodeResultDTO;
import com.cana.vbam.common.account.dto.AccountTradeAuditResult;
import com.cana.vbam.common.account.dto.AccountTradeRecordBasicInfo;
import com.cana.vbam.common.account.dto.DeductFundRequestDTO;
import com.cana.vbam.common.account.dto.TransferFundForCreditRequestDTO;
import com.cana.vbam.common.account.dto.TransferFundRequestDTO;
import com.cana.vbam.common.account.dto.WithdrawFundRequestDTO;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.account.enums.AccountTradeType;
import com.cana.vbam.common.account.enums.TradeRuleResult;
import com.cana.vbam.common.bankgate.dto.request.BankAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.BankTradeDetailQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.PlatformWithdrawFundDTO;
import com.cana.vbam.common.bankgate.dto.request.TradeStatusQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.TransferFundDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceDataDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankBaseResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankTradeDetailDataDTO;
import com.cana.vbam.common.bankgate.dto.response.BankTradeDetailResultDTO;
import com.cana.vbam.common.bankgate.dto.response.TradeStatusResultDTO;
import com.cana.vbam.common.bankgate.enums.BankFlag;
import com.cana.vbam.common.bankgate.enums.BankTranStatus;
import com.cana.vbam.common.bankgate.enums.FundBizType;
import com.dianping.cat.Cat;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;

@Service
public class AccountTradeServiceImpl implements IAccountTradeService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Gson gson = new Gson();

	@Resource
	private ICustomerTransactionService customerTransactionService;
	@Resource
	private IAccountTransactionService accountTransactionService;
	@Resource
	private AccountTableLockMapper accountTableLockMapper;
	@Resource
	private AccountMapper accountMapper;
	@Resource
	private IAccountTradeTransactionService accountTradeTransactionService;
	@Resource
	private AccountTradeRecordMapper accountTradeRecordMapper;
	@Resource
	private IAccountAuthorityTransactionService accountAuthorityTransactionService;
	@Resource
	private BankgateApi bankgateApi;
	@Resource
	private IAccountMessageService accountMessageService;

	private static final String catKeyPrefix = "账户请求网关";
	private static final String catKeyTransferSuccess = catKeyPrefix + "转账成功";
	private static final String catKeyTransferError = catKeyPrefix + "转账失败";
	private static final String catKeyWithdrawSuccess = catKeyPrefix + "提现成功";
	private static final String catKeyWithdrawError = catKeyPrefix + "提现失败";
	private static final String catKeyBalanceSuccess = catKeyPrefix + "查询余额成功";
	private static final String catKeyBalanceError = catKeyPrefix + "查询余额失败";

	@Override
	public AccountTradeStatus transferFund(TransferFundRequestDTO request) {
		AccountTradeStatus tradeStatus = AccountTradeStatus.BANKAPI_REQUEST;
		String bizSeq = AccountIDGenerator.generateBusinessSeq();
		Pair<TradeRuleResult, String> result = accountTradeTransactionService.transferFund(bizSeq, request);
		TradeRuleResult rule = result.getLeft();
		switch (rule) {
		case ACCEPT:
			tradeStatus = submitTransferFundRequest2Bank(bizSeq, request);
			accountTradeTransactionService.updateTradeRecordStatus(bizSeq, tradeStatus);
			return tradeStatus;
		case NEED_AUDIT:
			accountMessageService.sendNotificationForTradeApply(request.getUserId(), result.getRight());
			return AccountTradeStatus.TRADE_HANDLING;
		default:
			logger.error("不支持的转账:{}", gson.toJson(request));
			throw WebException.instance("不支持的转账");
		}
	}

	@Override
	public AccountTradeStatus deductFund(DeductFundRequestDTO request) {
		String receiveAccountName = accountTradeTransactionService.deductFund(request);
		TransferFundDTO transferDTO = new TransferFundDTO();
		transferDTO.setBusinessSeq(request.getBusinessSeq());
		transferDTO.setAmount(request.getAmount());
		transferDTO.setAccountNo(request.getTransferOutAccountNo());
		transferDTO.setReceiveAccountName(receiveAccountName);
		transferDTO.setReceiveAccountNo(request.getTransferInAccountNo());
		transferDTO.setTransDate(DateTimeUtil.date8());
		transferDTO.setMemo("账扣" + MoneyUtil.cent2Yuan(request.getAmount()) + "元");
		try {
			logger.info("账扣请求：{}", gson.toJson(transferDTO));
			BankBaseResultDTO result = bankgateApi.transferFund(transferDTO);
			logger.info("账扣响应：{}", gson.toJson(result));
			AccountTradeStatus tradeStatus = BankgateHelperUtil.parseStatus(result.getStatus());
			accountTradeTransactionService.updateTradeRecordStatus(request.getBusinessSeq(), tradeStatus);
			Cat.logMetricForCount(catKeyTransferSuccess);
			return tradeStatus;
		} catch (Exception e) {
			Cat.logMetricForCount(catKeyTransferError);
			logger.error(e.getMessage(), e);
			return AccountTradeStatus.BANKAPI_REQUEST;
		}
	}

	@Override
	public AccountTradeStatus transferFundForCredit(TransferFundForCreditRequestDTO request) {
		String receiveAccountName = accountTradeTransactionService.transferFundForCredit(request);
		TransferFundDTO transferDTO = new TransferFundDTO();
		transferDTO.setBusinessSeq(request.getBusinessSeq());
		transferDTO.setAmount(request.getAmount());
		transferDTO.setAccountNo(request.getTransferOutAccountNo());
		transferDTO.setReceiveAccountName(receiveAccountName);
		transferDTO.setReceiveAccountNo(request.getTransferInAccountNo());
		transferDTO.setTransDate(DateTimeUtil.date8());
		transferDTO.setMemo("授信转账" + MoneyUtil.cent2Yuan(request.getAmount()) + "元");
		if (request.getBankRemark() != null)
			transferDTO.setMemo(transferDTO.getMemo() + "，" + request.getBankRemark());
		try {
			logger.info("授信转账请求：{}", gson.toJson(transferDTO));
			BankBaseResultDTO result = bankgateApi.transferFund(transferDTO);
			logger.info("授信转账响应：{}", gson.toJson(result));
			AccountTradeStatus tradeStatus = BankgateHelperUtil.parseStatus(result.getStatus());
			accountTradeTransactionService.updateTradeRecordStatus(request.getBusinessSeq(), tradeStatus);
			Cat.logMetricForCount(catKeyTransferSuccess);
			return tradeStatus;
		} catch (Exception e) {
			Cat.logMetricForCount(catKeyTransferError);
			logger.error(e.getMessage(), e);
			return AccountTradeStatus.BANKAPI_REQUEST;
		}
	}

	@Override
	public void auditTransferFund(AccountTradeAuditResult audit) {
		switch (audit.getAuditStatus()) {
		case ACCEPTED:
			String bizSeq = AccountIDGenerator.generateBusinessSeq();
			TransferFundRequestDTO seqAndRequest = accountTradeTransactionService.auditTransferFundApply(bizSeq, audit);
			AccountTradeStatus tradeStatus = submitTransferFundRequest2Bank(bizSeq, seqAndRequest);
			accountTradeTransactionService.updateTradeRecordStatus(bizSeq, tradeStatus);
			break;
		case REJECTED:
			accountTradeTransactionService.auditTransferFundApply(null, audit);
			break;
		default:
			logger.error("不存在的审核结果！操作非法:{}", gson.toJson(audit));
			throw WebException.instance("不存在的审核结果！操作非法");
		}
	}

	/**
	 * 调用银行接口转帐
	 * 
	 * @param bizSeq
	 * @param transferFundRequest
	 */
	private AccountTradeStatus submitTransferFundRequest2Bank(String bizSeq, TransferFundRequestDTO reuqest) {
		Account master = accountTransactionService.getAccountPOByNo(reuqest.getAccountNo());
		Account receive = accountTransactionService.getAccountPOByNo(reuqest.getReceiveAccountNo());
		TransferFundDTO transferDTO = new TransferFundDTO();
		transferDTO.setBusinessSeq(bizSeq);
		transferDTO.setAmount(MoneyUtil.yuan2Cent(reuqest.getAmount()));
		transferDTO.setAccountNo(master.getAccountNo());
		transferDTO.setReceiveAccountName(receive.getCompanyName());
		transferDTO.setReceiveAccountNo(receive.getAccountNo());
		transferDTO.setTransDate(DateTimeUtil.date8());
		transferDTO.setMemo("转帐" + reuqest.getAmount() + "元");
		try {
			logger.info("转账请求：{}", gson.toJson(transferDTO));
			BankBaseResultDTO result = bankgateApi.transferFund(transferDTO);
			logger.info("转账响应：{}", gson.toJson(result));
			AccountTradeStatus tradeStatus = BankgateHelperUtil.parseStatus(result.getStatus());
			if (tradeStatus == AccountTradeStatus.TRADE_FAIL) {
				Cat.logMetricForCount(catKeyTransferError);
				logger.error("请求网关转帐交易失败，失败原因：{}", result.getStatusText());
			} else {
				Cat.logMetricForCount(catKeyTransferSuccess);
			}
			return tradeStatus;
		} catch (Exception e) {
			Cat.logMetricForCount(catKeyTransferError);
			logger.error(e.getMessage(), e);
			throw WebException.instance(e.getMessage());
		}
	}

	@Override
	public void auditWithdrawFund(AccountTradeAuditResult audit) {
		switch (audit.getAuditStatus()) {
		case ACCEPTED:
			String bizSeq = AccountIDGenerator.generateBusinessSeq();
			WithdrawFundRequestDTO result = accountTradeTransactionService.auditWithdrawFundApply(bizSeq, audit);
			AccountTradeStatus tradeStatus = submitWithdrawFundRequest2Bank(bizSeq, result);
			accountTradeTransactionService.updateTradeRecordStatus(bizSeq, tradeStatus);
			break;
		case REJECTED:
			accountTradeTransactionService.auditWithdrawFundApply(null, audit);
			break;
		default:
			logger.error("不存在的审核结果！操作非法:{}", gson.toJson(audit));
			throw WebException.instance("不存在的审核结果！操作非法");
		}

	}

	@Override
	public AccountTradeStatus withdrawFund(WithdrawFundRequestDTO request) {
		AccountTradeStatus tradeStatus = AccountTradeStatus.BANKAPI_REQUEST;
		String bizSeq = AccountIDGenerator.generateBusinessSeq();
		Pair<TradeRuleResult, String> result = accountTradeTransactionService.withdrawFund(bizSeq, request);
		TradeRuleResult rule = result.getLeft();
		switch (rule) {
		case ACCEPT:
			tradeStatus = submitWithdrawFundRequest2Bank(bizSeq, request);
			accountTradeTransactionService.updateTradeRecordStatus(bizSeq, tradeStatus);
			return tradeStatus;
		case NEED_AUDIT:
			accountMessageService.sendNotificationForTradeApply(request.getUserId(), result.getRight());
			return AccountTradeStatus.TRADE_HANDLING;
		default:
			logger.error("不支持的转帐！:{}", gson.toJson(request));
			throw WebException.instance("不支持的转账");
		}
	}
    
    public AccountTradeStatus withdrawFundForYundaEx(WithdrawFundRequestDTO request) {
    	AccountTradeStatus tradeStatus = AccountTradeStatus.BANKAPI_REQUEST;
    	accountTradeTransactionService.withdrawFundForYundaEx(request);
		tradeStatus = submitWithdrawFundRequest2BankEx(request.getBusinessSeq(), request);
		accountTradeTransactionService.updateTradeRecordStatus(request.getBusinessSeq(), tradeStatus);
		return tradeStatus;
    }

	private AccountTradeStatus submitWithdrawFundRequest2Bank(String bizSeq, WithdrawFundRequestDTO request) {
		Account account = accountTransactionService.getAccountPOByNo(request.getAccountNo());
		PlatformWithdrawFundDTO withdraw = new PlatformWithdrawFundDTO();
		if (!request.getReceiveBankName().contains(AccountConsts.citic_bank_name)) {
			withdraw.setBankFlag(BankFlag.other_bank);
		}
		withdraw.setBusinessSeq(bizSeq);
		withdraw.setAccountNo(account.getAccountNo());
		withdraw.setAmount(MoneyUtil.yuan2Cent(request.getAmount()));
		withdraw.setReceiveAccountNo(request.getReceiveAccountNo());
		withdraw.setReceiveAccountName(account.getCompanyName());// TODO 设置为
		withdraw.setReceiveBankName(request.getReceiveAccountName());
		withdraw.setReceiveBankNo(request.getLianHangNo());// 支付联行号，optional
		withdraw.setMemo("提现" + request.getAmount() + "元");
		withdraw.setTransDate(DateTime.now().toString("yyyyMMdd"));
		try {
			logger.info("提现请求：{}", gson.toJson(withdraw));
			BankBaseResultDTO result = bankgateApi.platformWithdrawFund(withdraw);
			logger.info("提现响应：{}", gson.toJson(result));
			AccountTradeStatus tradeStatus = BankgateHelperUtil.parseStatus(result.getStatus());
			if (tradeStatus == AccountTradeStatus.TRADE_FAIL) {
				Cat.logMetricForCount(catKeyWithdrawError);
				logger.error("请求网关提现交易失败，失败原因：{}", result.getStatusText());
			} else {
				Cat.logMetricForCount(catKeyWithdrawSuccess);
			}
			return tradeStatus;
		} catch (WebException e) {
			Cat.logMetricForCount(catKeyWithdrawError);
			logger.error(e.getMessage(), e);
			throw WebException.instance(e.getMessage());
		} catch (Exception e) {
			Cat.logMetricForCount(catKeyWithdrawError);
			logger.error("未知异常", e);
			throw e;
		}
	}
	
	private AccountTradeStatus submitWithdrawFundRequest2BankEx(String bizSeq, WithdrawFundRequestDTO request) {
		Account account = accountTransactionService.getAccountPOByNo(request.getAccountNo());
		PlatformWithdrawFundDTO withdraw = new PlatformWithdrawFundDTO();
		if (!AccountConsts.citic_bank_name.contains(request.getReceiveBankName())) {
			withdraw.setBankFlag(BankFlag.other_bank);
		}
		withdraw.setBusinessSeq(bizSeq);
		withdraw.setAccountNo(account.getAccountNo());
		withdraw.setAmount(MoneyUtil.yuan2Cent(request.getAmount()));
		withdraw.setReceiveAccountNo(request.getReceiveAccountNo());
		withdraw.setReceiveAccountName(request.getReceiveAccountName());// TODO 设置为
		withdraw.setReceiveBankName(request.getReceiveBankName());
		withdraw.setReceiveBankNo(request.getLianHangNo());// 支付联行号，optional
		withdraw.setMemo("提现" + request.getAmount() + "元");
		withdraw.setTransDate(DateTime.now().toString("yyyyMMdd"));
		if (!AccountConsts.citic_bank_name.equals(request.getReceiveBankName())) {
			withdraw.setBankFlag(BankFlag.other_bank);
		}
		try {
			logger.info("提现请求：{}", gson.toJson(withdraw));
			BankBaseResultDTO result = bankgateApi.platformWithdrawFund(withdraw);
			logger.info("提现响应：{}", gson.toJson(result));
			AccountTradeStatus tradeStatus = BankgateHelperUtil.parseStatus(result.getStatus());
			if (tradeStatus == AccountTradeStatus.TRADE_FAIL) {
				Cat.logMetricForCount(catKeyWithdrawError);
				logger.error("请求网关提现交易失败，失败原因：{}", result.getStatusText());
			} else {
				Cat.logMetricForCount(catKeyWithdrawSuccess);
			}
			return tradeStatus;
		} catch (Exception e) {
			Cat.logMetricForCount(catKeyWithdrawError);
			logger.error(e.getMessage(), e);
			throw WebException.instance(e.getMessage());
		}
	}

	@Override
	public List<BankAccountBalanceDataDTO> queryAccountBalance(String customerId, String... accountNoStrings) {
		List<String> accountNos = Arrays.asList(accountNoStrings);
		accountAuthorityTransactionService.checkQueryBalanceAuthority(customerId, accountNos);
		List<BankAccountBalanceDataDTO> datas = Lists.newArrayList();
		for (String accountNo : accountNos) {
			BankAccountBalanceDataDTO data = queryAccountBalance(accountNo);
			if (data == null)
				logger.error("查询账户余额失败！账号：{}", accountNo);
			datas.add(data);
		}
		return datas;
	}

	private BankAccountBalanceDataDTO queryAccountBalance(String accountNo) {
		BankAccountBalanceQueryDTO query = new BankAccountBalanceQueryDTO();
		query.setAccountNo(accountNo);
		try {
			BankAccountBalanceResultDTO result = bankgateApi.queryAccountBalance(query);
			AccountTradeStatus status = BankgateHelperUtil.parseStatus(result.getStatus());
			if (status == AccountTradeStatus.TRADE_SUCCESS
					&& CollectionUtils.isNotEmpty(result.getBankAccountBalanceDatas())) {
				Cat.logMetricForCount(catKeyBalanceSuccess);
				BankAccountBalanceDataDTO bankData = result.getBankAccountBalanceDatas().get(0);
				return bankData;
			}
			logger.error("网关查询账户余额失败！账号：{}，错误原因：{}", accountNo, result.getStatusText());
			Cat.logMetricForCount(catKeyBalanceError);
		} catch (Exception e) {
			Cat.logMetricForCount(catKeyBalanceError);
			logger.error("查询账户余额失败！账号：{}", accountNo, e);
		}
		return null;
	}

	@Override
	public void checkAccountTradeAble(String customerId, String accountNo, AccountTradeType... types) {
		User customer = customerTransactionService.checkCustomerIsValid(customerId);
		Account account = accountTransactionService.getAccountPOByNo(accountNo);
		if (account == null) {
			logger.error("账号不存在!");
			throw WebException.instance("账号不存在!");
		}
		for (AccountTradeType type : types) {
			TradeRuleResult result = null;
			switch (type) {
			case TRANSFER_FUND:
				result = accountAuthorityTransactionService.checkTransferAuthority(customer, account);
				break;
			case WITHDRAW_FUND:
				result = accountAuthorityTransactionService.checkWithdrawAuthority(customer, account);
				break;
			default:
				result = TradeRuleResult.REJECT;
			}
			if (result == TradeRuleResult.REJECT) {
				logger.error("交易许可审核失败!对当前操作无权限!");
				throw WebException.instance("交易许可审核失败!对当前操作无权限!");
			}
		}
	}

	@Override
	public Map<String, String> batchQueryTradeStatus(List<String> businessSeqs) {
		if (CollectionUtils.isEmpty(businessSeqs))
			throw WebException.instance("业务流水号不能为空");
		AccountTradeRecordExample accountTradeRecordExample = new AccountTradeRecordExample();
		accountTradeRecordExample.createCriteria().andBusinessSeqIn(businessSeqs);
		List<AccountTradeRecord> accountTradeRecords = accountTradeRecordMapper
				.selectByExample(accountTradeRecordExample);
		Map<String, String> returnValue = new HashMap<>();
		for (AccountTradeRecord accountTradeRecord : accountTradeRecords)
			returnValue.put(accountTradeRecord.getBusinessSeq(), accountTradeRecord.getStatus());
		return returnValue;
	}

	@Override
	public String queryTradeStatus(String business) {
		AccountTradeRecordExample accountTradeRecordExample = new AccountTradeRecordExample();
		accountTradeRecordExample.createCriteria().andBusinessSeqEqualTo(business);
		List<AccountTradeRecord> accountTradeRecords = accountTradeRecordMapper
				.selectByExample(accountTradeRecordExample);
		if (accountTradeRecords.size() == 0)
			return ReturnCode.NOT_FOUND.getRetCode();
		else
			return accountTradeRecords.get(0).getStatus();
	}

	@Override
	public AccountTradeRecordBasicInfo queryTradeRecordBasicInfo(String business) {
		AccountTradeRecordExample accountTradeRecordExample = new AccountTradeRecordExample();
		accountTradeRecordExample.createCriteria().andBusinessSeqEqualTo(business);
		List<AccountTradeRecord> accountTradeRecords = accountTradeRecordMapper
				.selectByExample(accountTradeRecordExample);
		if (accountTradeRecords.size() == 0)
			throw WebException.instance(ReturnCode.NOT_FOUND, "交易不存在");

		AccountTradeRecord record = accountTradeRecords.get(0);
		AccountTradeRecordBasicInfo info = new AccountTradeRecordBasicInfo();
		info.setBusinessSeq(record.getBusinessSeq());
		info.setStatus(AccountTradeStatus.valueOf(record.getStatus()));

		if (record.getAmount() < 0) {
			info.setAccountName(record.getAccountName());
			info.setAccountNo(record.getAccountNo());
			info.setOppositeAccountName(record.getOppositeAccountName());
			info.setOppositeAccountNo(record.getOppositeAccountNo());
			info.setAmount(-record.getAmount());
		} else {
			info.setAccountName(record.getOppositeAccountName());
			info.setAccountNo(record.getOppositeAccountNo());
			info.setOppositeAccountName(record.getAccountName());
			info.setOppositeAccountNo(record.getAccountNo());
			info.setAmount(record.getAmount());
		}
		return info;
	}

	@Override
	public void relaunchingWithdrawOperate(String tradeRecordId) throws Exception{
		// 查询需要
		AccountTradeRecord accountTradeRecord = accountTradeRecordMapper.selectByPrimaryKey(tradeRecordId);
		if(null == accountTradeRecord){
			throw WebException.instance("交易流水不存在");
		}
		TradeStatusQueryDTO queryDTO =  new TradeStatusQueryDTO();
		queryDTO.setBusinessSeq(accountTradeRecord.getBusinessSeq());
		queryDTO.setFundBizType(FundBizType.platform_withdraw_fund);
		TradeStatusResultDTO tradeStatus = null;
		try{
			tradeStatus = bankgateApi.queryTradeStatus(queryDTO);
			if(StringUtils.equals(tradeStatus.getStatus().name(), BankTranStatus.fail.name())){
				throw WebException.instance("系统繁忙，请稍后重试");
			}
		}catch(WebException e){
			logger.error(e.getMessage(), e);
			throw e;
		}catch(Exception e){
			logger.error("未知异常", e);
			throw e;
		}
		String bizSeq = AccountIDGenerator.generateBusinessSeq();
		WithdrawFundRequestDTO request = accountTradeTransactionService.queryWithdrawInfoAndUpdateState(tradeRecordId, bizSeq, tradeStatus);
		submitWithdrawFundRequest2Bank(bizSeq, request);
	}

	@Override
	public List<AccountPrintCodeResultDTO> queryBankTradeDetailNonLogin(AccountPrintCodeDTO codeDTO) throws Exception {
		BankTradeDetailQueryDTO bankTradeDetailQueryDTO = new BankTradeDetailQueryDTO();
		BeanUtils.copyProperties(codeDTO, bankTradeDetailQueryDTO);
		bankTradeDetailQueryDTO.setStartDate(DateTimeUtil.date8(DateTimeUtil.getDate10(codeDTO.getStartDate())));
		bankTradeDetailQueryDTO.setEndDate(DateTimeUtil.date8(DateTimeUtil.getDate10(codeDTO.getEndDate())));
		BankTradeDetailResultDTO detailResultDTO = bankgateApi.queryBankTradeDetailNonLogin(bankTradeDetailQueryDTO);
		List<AccountPrintCodeResultDTO> resultDTOs = new ArrayList<>();
		for(BankTradeDetailDataDTO dataDTO : detailResultDTO.getBankTradeDetailDatas()) {
			AccountPrintCodeResultDTO codeResultDTO = new AccountPrintCodeResultDTO();
			BeanUtils.copyProperties(dataDTO, codeResultDTO);
			codeResultDTO.setBankTradeTypeDesc(dataDTO.getBankTradeType().getDesc());
			codeResultDTO.setDebitCreditTagDesc(dataDTO.getDebitCreditTag().getDesc());
			resultDTOs.add(codeResultDTO);
		}
		return resultDTOs;
	}
}
