/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.service;

import org.springframework.stereotype.Service;

import com.cana.bankgate.server.enums.TransferFundType;
import com.cana.bankgate.server.po.BankgateTrans;
import com.cana.bankgate.server.request.account.BankAccountBalanceQuery;
import com.cana.bankgate.server.request.account.BankAccountCancel;
import com.cana.bankgate.server.request.account.BankAccountCreate;
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
import com.cana.bankgate.server.response.BankBaseResult;
import com.cana.bankgate.server.response.account.BankAccountBalanceResult;
import com.cana.bankgate.server.response.account.BankAccountBaseResult;
import com.cana.bankgate.server.response.account.BankAccountSignUpStateResult;
import com.cana.bankgate.server.response.account.BankInfoResult;
import com.cana.bankgate.server.response.account.BankMainAccountBalanceResult;
import com.cana.bankgate.server.response.flow.BankAccountTradeFlowResult;
import com.cana.bankgate.server.response.flow.BankMainAccountTradeFlowResult;
import com.cana.bankgate.server.response.fund.FreezeInfoResult;
import com.cana.bankgate.server.response.fund.TradeStatusResult;
import com.cana.bankgate.server.response.trade.BankTradeDetailResult;
import com.cana.bankgate.server.utils.HttpUtil;
import com.cana.bankgate.server.utils.ParameterConvertUtil;
import com.cana.bankgate.server.utils.ResultConvertUtil;
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
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountSignUpStateResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountTradeFlowResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankBaseResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankInfoResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankMainAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankMainAccountTradeFlowResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankTradeDetailResultDTO;
import com.cana.vbam.common.bankgate.dto.response.FreezeInfoResultDTO;
import com.cana.vbam.common.bankgate.dto.response.TradeStatusResultDTO;


/**
 * 非事务性的业务流程
 * 
 * @author ducer
 *
 */
@Service("bankgateService")
public class BankgateServiceImpl implements IBankgateService {

  /**
   * 开户，此处的意思是附属账户预签约
   */
  @Override
  public BankAccountDTO createBankAccount(BankAccountCreateDTO infoDTO) {
    BankAccountCreate info = ParameterConvertUtil.convertParameter(infoDTO);
    BankAccountBaseResult result = HttpUtil.request(info, BankAccountBaseResult.class);
    return ResultConvertUtil.convertResult(result);
  }

  /**
   * 查询账户余额
   */
  @Override
  public BankAccountBalanceResultDTO queryAccountBalance(BankAccountBalanceQueryDTO queryDTO) {
    BankAccountBalanceQuery query = ParameterConvertUtil.convertParameter(queryDTO);
    BankAccountBalanceResult result = HttpUtil.request(query, BankAccountBalanceResult.class);
    return ResultConvertUtil.convertResult(result);
  }

  /**
   * 查询交易状态
   */
  @Override
  public TradeStatusResultDTO queryTradeStatus(BankgateTrans trans, TradeStatusQueryDTO queryDTO) {
    TradeStatusQuery query = ParameterConvertUtil.convertParameter(trans, queryDTO);
    TradeStatusResult result = HttpUtil.request(query, TradeStatusResult.class);
    return ResultConvertUtil.convertResult(result);
  }

  /**
   * 查询冻结信息历史
   */
  @Override
  public FreezeInfoResultDTO queryFreezeInfo(FreezeInfoQueryDTO queryDTO) {
    FreezeInfoQuery query = ParameterConvertUtil.convertParameter(queryDTO);
    FreezeInfoResult result = HttpUtil.request(query, FreezeInfoResult.class);
    return ResultConvertUtil.convertResult(result);
  }

  /**
   * BF：转账；BG：解冻；BH：解冻支付；BR：冻结；BS：支付冻结 都属于强制转账<br>
   */
  @Override
  public <T extends BankAccountBaseDTO> BankBaseResultDTO transferFundForce(TransferFundType type,
      String gateSeq, T dto) {
    TransferFundForce transfer = ParameterConvertUtil.convertParameter(type, gateSeq, dto);
    BankBaseResult result = HttpUtil.request(transfer, BankBaseResult.class);
    return ResultConvertUtil.convertResult(result);
  }

  /**
   * 平台出金，现在所有的出金都使用平台出金
   */
  @Override
  public BankBaseResultDTO platformWithdrawFund(String gateSeq, PlatformWithdrawFundDTO dto) {
    PlatformWithdrawFund plat = ParameterConvertUtil.convertParameter(gateSeq, dto);
    BankBaseResult result = HttpUtil.request(plat, BankBaseResult.class);
    return ResultConvertUtil.convertResult(result);
  }

  /**
   * 在线销户
   */
  @Override
  public BankAccountDTO cancelAccount(BankAccountBaseDTO dto) {
    BankAccountCancel cancel = ParameterConvertUtil.convertParameter(dto);
    BankAccountBaseResult result = HttpUtil.request(cancel, BankAccountBaseResult.class);
    return ResultConvertUtil.convertResult(result);
  }

  /**
   * 查询银行支付联行号和行名称
   */
  @Override
  public BankInfoResultDTO queryBankInfo(BankInfoQueryDTO queryDTO) {
    BankInfoQuery query = ParameterConvertUtil.convertParameter(queryDTO);
    BankInfoResult result = HttpUtil.request(query, BankInfoResult.class);
    return ResultConvertUtil.convertResult(result);
  }

  /**
   * 查询附属账号交易明细
   * 
   * @param queryDTO
   * @return
   * @throws Exception
   */
  @Override
  public BankAccountTradeFlowResultDTO queryBankAccountTradeFlow(BankAccountTradeFlowQueryDTO queryDTO) {
    BankAccountTradeFlowQuery query = ParameterConvertUtil.convertParameter(queryDTO);
    BankAccountTradeFlowResult result = HttpUtil.request(query, BankAccountTradeFlowResult.class);
    return ResultConvertUtil.convertResult(result);
  }

  /**
   * 查询主账号交易明细
   */
  @Override
  public BankMainAccountTradeFlowResultDTO queryBankMainAccountTradeFlow(BankMainAccountTradeFlowQueryDTO queryDTO) {
    BankMainAccountTradeFlowQuery query = ParameterConvertUtil.convertParameter(queryDTO);
    BankMainAccountTradeFlowResult result = HttpUtil.request(query, BankMainAccountTradeFlowResult.class);
    return ResultConvertUtil.convertResult(result);
  }

  /**
   * 查询主账号余额
   */
  @Override
  public BankMainAccountBalanceResultDTO queryBankMainAccountBalance(BankMainAccountBalanceQueryDTO queryDTO) {
    BankMainAccountBalanceQuery query = ParameterConvertUtil.convertParameter(queryDTO);
    BankMainAccountBalanceResult result = HttpUtil.request(query, BankMainAccountBalanceResult.class);
    return ResultConvertUtil.convertResult(result);
  }

  @Override
  public BankTradeDetailResultDTO queryBankTradeDetailNonLogin(BankTradeDetailQueryDTO queryDTO) {
    BankTradeDetailQuery query = ParameterConvertUtil.convertParameter(queryDTO);
    BankTradeDetailResult result = HttpUtil.request(query, BankTradeDetailResult.class);
    return ResultConvertUtil.convertResult(result);
  }

	@Override
	public BankBaseResultDTO alterAccountParam(BankAccountParamAlterDTO alterDTO) {
		BankAccountParamAlter alter = ParameterConvertUtil.convertParameter(alterDTO);
		BankBaseResult result = HttpUtil.request(alter, BankBaseResult.class);
		return ResultConvertUtil.convertResult(result);
	}

	@Override
	public BankAccountSignUpStateResultDTO queryBankAccountSingUpState(BankAccountSignUpStateQueryDTO queryDTO) {
		BankAccountSignUpStateQuery query = ParameterConvertUtil.convertParameter(queryDTO);
		BankAccountSignUpStateResult result = HttpUtil.request(query, BankAccountSignUpStateResult.class);
		return ResultConvertUtil.convertResult(result);
	}
}
