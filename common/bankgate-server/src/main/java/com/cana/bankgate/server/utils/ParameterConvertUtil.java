/**
 * Copyright (c) 2016-2100 Cana, Inc. All rights reserved.
 */
package com.cana.bankgate.server.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cana.bankgate.server.constants.BankgateConstant;
import com.cana.bankgate.server.enums.TransferFundType;
import com.cana.bankgate.server.po.BankgateTrans;
import com.cana.bankgate.server.request.account.BankAccountBalanceQuery;
import com.cana.bankgate.server.request.account.BankAccountCancel;
import com.cana.bankgate.server.request.account.BankAccountCreate;
import com.cana.bankgate.server.request.account.BankAccountGroup;
import com.cana.bankgate.server.request.account.BankAccountParamAlter;
import com.cana.bankgate.server.request.account.BankAccountSignUpStateQuery;
import com.cana.bankgate.server.request.account.BankInfoQuery;
import com.cana.bankgate.server.request.account.BankMainAccountBalanceQuery;
import com.cana.bankgate.server.request.flow.BankAccountTradeFlowQuery;
import com.cana.bankgate.server.request.flow.BankMainAccountTradeFlowQuery;
import com.cana.bankgate.server.request.fund.FreezeInfoQuery;
import com.cana.bankgate.server.request.fund.PlatformWithdrawFund;
import com.cana.bankgate.server.request.fund.TradeStatusQuery;
import com.cana.bankgate.server.request.fund.TransferFundForce;
import com.cana.bankgate.server.request.trade.BankTradeDetailQuery;
import com.cana.bankgate.server.transaction.IBankgateTransactionService;
import com.cana.vbam.common.bankgate.dto.request.BankAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.BankAccountBaseDTO;
import com.cana.vbam.common.bankgate.dto.request.BankAccountCreateDTO;
import com.cana.vbam.common.bankgate.dto.request.BankAccountParamAlterDTO;
import com.cana.vbam.common.bankgate.dto.request.BankAccountSignUpStateQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.BankAccountTradeFlowQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.BankInfoQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.BankMainAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.BankMainAccountTradeFlowQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.BankTradeDetailQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.FreezeInfoQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.PlatformWithdrawFundDTO;
import com.cana.vbam.common.bankgate.dto.request.TradeStatusQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.UnfreezeFundDTO;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.google.common.collect.Lists;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

/**
 * @author ducer
 *
 */
public class ParameterConvertUtil {

  private static IBankgateTransactionService transactionService = SpringApplicationContext.getApplicationContext().getBean(IBankgateTransactionService.class);

  private static BankAccountGroup extractBankAccountGroup(String accountNo) {
    return transactionService.extractBankAccountGroup(accountNo);
  }

  public static BankMainAccountBalanceQuery convertParameter(BankMainAccountBalanceQueryDTO queryDTO) {
    if (StringUtils.isNotBlank(queryDTO.getBankUserName()) && CollectionUtils.isEmpty(queryDTO.getMainAccountNos())) {
      LogExceptionUtil.validateFail("主账户（实体账户）集合不能为空");
    }
    BankMainAccountBalanceQuery query = new BankMainAccountBalanceQuery(BankBizType.query_main_account_balance);
    if (StringUtils.isBlank(queryDTO.getBankUserName())) {
      LogExceptionUtil.log("查询主账号余额-调用端没有传用户名和主账号，视为查询平台主账号余额");
      query.setBankUserName(BankgateConstant.config.getBankUserName());
      query.setMainAccountNos(Lists.newArrayList(BankgateConstant.config.getMainAccountNo()));
    } else {
      query.setBankUserName(queryDTO.getBankUserName());
      query.setMainAccountNos(queryDTO.getMainAccountNos());
    }
    return query;
  }
  
  public static BankTradeDetailQuery convertParameter(BankTradeDetailQueryDTO queryDTO) {
    if (StringUtils.isBlank(queryDTO.getAccountNo())) {
      LogExceptionUtil.validateFail("银行账户不能为空");
    }
    BankTradeDetailQuery query = new BankTradeDetailQuery(BankBizType.nonlogin_query_trade_detail);
    query.setBankUserName(BankgateConstant.config.getBankUserName());
    query.setMainAccountNo(BankgateConstant.config.getMainAccountNo());
    query.setAccountNo(queryDTO.getAccountNo());
    query.setStartDate(queryDTO.getStartDate());
    query.setEndDate(queryDTO.getEndDate());
    query.setPageIndex(queryDTO.getStartIndex());
    query.setPageSize(queryDTO.getPageSize());
    return query;
  }

  public static BankMainAccountTradeFlowQuery convertParameter(BankMainAccountTradeFlowQueryDTO queryDTO) {
    BankMainAccountTradeFlowQuery query = new BankMainAccountTradeFlowQuery(BankBizType.query_main_account_trade_flow);
    query.setStartDate(queryDTO.getStartDate());
    query.setEndDate(queryDTO.getEndDate());
    query.setMinAmount(queryDTO.getMinAmount());
    query.setMaxAmount(queryDTO.getMaxAmount());
    query.setPageIndex(queryDTO.getPageIndex());
    query.setPageSize(queryDTO.getPageSize());

    if (StringUtils.isNoneBlank(queryDTO.getBankUserName(), queryDTO.getMainAccountNo())) {
      query.setBankUserName(queryDTO.getBankUserName());
      query.setMainAccountNo(queryDTO.getMainAccountNo());
    } else {
      query.setBankUserName(BankgateConstant.config.getBankUserName());
      query.setMainAccountNo(BankgateConstant.config.getMainAccountNo());
    }
    return query;
  }

  public static BankAccountTradeFlowQuery convertParameter(BankAccountTradeFlowQueryDTO queryDTO) {
    BankAccountTradeFlowQuery query = new BankAccountTradeFlowQuery(BankBizType.query_account_trade_flow);
    query.setBankUserName(BankgateConstant.config.getBankUserName());
    query.setMainAccountNo(BankgateConstant.config.getMainAccountNo());
    query.setAccountNo(queryDTO.getAccountNo());
    query.setBankTradeType(queryDTO.getBankTradeType());
    query.setStartDate(queryDTO.getStartDate());
    query.setEndDate(queryDTO.getEndDate());
    query.setQueryType(queryDTO.getQueryType());
    query.setPageIndex(queryDTO.getPageIndex());
    query.setPageSize(queryDTO.getPageSize());

    return query;
  }
  
  public static BankInfoQuery convertParameter(BankInfoQueryDTO queryDTO) {
    if (StringUtils.isBlank(queryDTO.getBankPaymentNo())
        && StringUtils.isBlank(queryDTO.getBankPaymentName())) {
      LogExceptionUtil.validateFail("支付联行号和支付联行名称必须至少一个不为空!");
    }
    BankInfoQuery query = new BankInfoQuery(BankBizType.query_bank_info);
    query.setBankUserName(BankgateConstant.config.getBankUserName());
    query.setProvinceName(queryDTO.getProvinceName());
    query.setCityName(queryDTO.getCityName());
    query.setBankName(queryDTO.getBankName());
    query.setBankPaymentName(queryDTO.getBankPaymentName());
    query.setBankPaymentNo(queryDTO.getBankPaymentNo());
    return query;
  }
  
  public static BankAccountCancel convertParameter(BankAccountBaseDTO dto) {
    BankAccountGroup group = extractBankAccountGroup(dto.getAccountNo());
    BankAccountCancel cancel = new BankAccountCancel(BankBizType.online_cancel_account);
    cancel.setBankUserName(group.getBankUserName());
    cancel.setMainAccountNo(group.getMainAccountNo());
    cancel.setAccountNo(dto.getAccountNo());
    return cancel;
  }
  
  public static PlatformWithdrawFund convertParameter(String gateSeq, PlatformWithdrawFundDTO dto) {
    BankAccountGroup group = extractBankAccountGroup(dto.getAccountNo());

    PlatformWithdrawFund plat = new PlatformWithdrawFund(BankBizType.platform_withdraw_fund);
    plat.setBankUserName(group.getBankUserName());
    plat.setAccountNo(dto.getAccountNo());
    plat.setAmount(dto.getAmount());
    plat.setAppointmentDate(dto.getAppointmentDate());
    plat.setAppointmentTime(dto.getAppointmentTime());
    plat.setAppointmentFlag(dto.getAppointmentFlag());
    plat.setBankFlag(dto.getBankFlag());
    plat.setMemo(dto.getMemo());
    plat.setReceiveAccountName(dto.getReceiveAccountName());
    plat.setReceiveAccountNo(dto.getReceiveAccountNo());
    plat.setReceiveBankName(dto.getReceiveBankName());
    plat.setReceiveBankNo(dto.getReceiveBankNo());
    plat.setGateSeq(gateSeq);
    return plat;
  }
  
  public static <T extends BankAccountBaseDTO> TransferFundForce convertParameter(TransferFundType type, String gateSeq, T dto) {
    BankAccountGroup group = extractBankAccountGroup(dto.getAccountNo());

    TransferFundForce transfer = new TransferFundForce(BankBizType.transfer_fund);
    BeanUtils.copyProperties(dto, transfer);
    if(TransferFundType.unfreeze_fund.equals(type)) {
      // 当为解冻的时候，需要把收款帐号和付款账号对换
      UnfreezeFundDTO unfreeze = (UnfreezeFundDTO) dto;
      transfer.setAccountNo(unfreeze.getReceiveAccountNo());
      transfer.setReceiveAccountNo(unfreeze.getAccountNo());
    }
    transfer.setBankUserName(group.getBankUserName());
    transfer.setMainAccountNo(group.getMainAccountNo());
    transfer.setTranFlag(BankgateConstant.config.getTranFlag());
    transfer.setGateSeq(gateSeq);
    transfer.setTranType(type);
    return transfer;
  }
  
  public static FreezeInfoQuery convertParameter(FreezeInfoQueryDTO queryDTO) {
    BankAccountGroup group = extractBankAccountGroup(queryDTO.getAccountNo());

    FreezeInfoQuery query = new FreezeInfoQuery(BankBizType.query_freeze_info);
    query.setAccountNo(queryDTO.getAccountNo());
    query.setBankUserName(group.getBankUserName());
    query.setMainAccountNo(group.getMainAccountNo());
    query.setStartDate(queryDTO.getStartDate());
    query.setEndDate(queryDTO.getEndDate());
    return query;
  }
  
  public static TradeStatusQuery convertParameter(BankgateTrans trans, TradeStatusQueryDTO queryDTO) {
    BankAccountGroup group = extractBankAccountGroup(trans.getAccountNo());

    TradeStatusQuery query = new TradeStatusQuery(BankBizType.query_trade_status);
    query.setGateSeq(trans.getGateSeq());
    query.setBankUserName(group.getBankUserName());
    query.setFundBizType(queryDTO.getFundBizType());
    return query;
  }
  
  public static BankAccountBalanceQuery convertParameter(BankAccountBalanceQueryDTO queryDTO) {
    BankAccountGroup group = extractBankAccountGroup(queryDTO.getAccountNo());

    BankAccountBalanceQuery query = new BankAccountBalanceQuery(BankBizType.query_account_balance);
    query.setBankUserName(group.getBankUserName());
    query.setMainAccountNo(group.getMainAccountNo());
    query.setAccountNo(queryDTO.getAccountNo());
    return query;
  }
  
  public static BankAccountCreate convertParameter(BankAccountCreateDTO infoDTO) {
    BankAccountCreate info = new BankAccountCreate(BankBizType.create_bank_account);
    BeanUtils.copyProperties(BankgateConstant.config, info);
    if (StringUtils.isNoneBlank(infoDTO.getBankUserName(), infoDTO.getMainAccountNo())) {
      info.setBankUserName(infoDTO.getBankUserName());
      info.setMainAccountNo(infoDTO.getMainAccountNo());
    } else {
      info.setBankUserName(BankgateConstant.config.getBankUserName());
      info.setMainAccountNo(BankgateConstant.config.getMainAccountNo());
    }
    info.setAccountNo(RandomStringUtils.randomNumeric(14)); // 这个是为了防止银行端对相同报文进行拦截,最多只能十四位
    info.setAccountName(infoDTO.getAccountName());
    return info;
  }
  
  public static BankAccountParamAlter convertParameter(BankAccountParamAlterDTO alterDTO) {
	  BankAccountParamAlter info = new BankAccountParamAlter(BankBizType.account_param_mangerment);
	  if (StringUtils.isBlank(alterDTO.getAccountNo())) {
	      LogExceptionUtil.validateFail("银行账户不能为空");
	  }
	  info.setAccountNo(alterDTO.getAccountNo());
	  info.setMainAccountNo(BankgateConstant.config.getMainAccountNo());
	  info.setBankUserName(BankgateConstant.config.getBankUserName());
	  info.setAccountName(alterDTO.getAccountName());
	  info.setAutoAssignInterestFlag(alterDTO.getAutoAssignInterestFlag());
	  info.setAutoAssignTranFeeFlag(alterDTO.getAutoAssignTranFeeFlag());
//	  info.setBankBizType(BankBizType.account_param_mangerment);
	  info.setCalculateInterestFlag(alterDTO.getCalculateInterestFlag());
	  info.setFeeType(alterDTO.getFeeType());
	  info.setInterestRate(alterDTO.getInterestRate());
	  return info;
  }
  
  public static BankAccountSignUpStateQuery convertParameter(BankAccountSignUpStateQueryDTO queryDTO){
	  BankAccountSignUpStateQuery info = new BankAccountSignUpStateQuery(BankBizType.account_signup_state);
	  info.setMainAccountNo(BankgateConstant.config.getMainAccountNo());
	  info.setBankUserName(BankgateConstant.config.getBankUserName());
	  info.setAccountNo(queryDTO.getAccountNo());
//	  info.setBankBizType(BankBizType.account_signup_state);
	  info.setState(queryDTO.getState());
	  info.setStartTime(queryDTO.getStartTime());
	  info.setEndTime(queryDTO.getEndTime());
	  return info;
  }
}
