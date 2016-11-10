/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.bankgate.api.BankgateApi;
import com.cana.bankgate.server.enums.TransferFundType;
import com.cana.bankgate.server.lock.ILockManager;
import com.cana.bankgate.server.po.BankgateTrans;
import com.cana.bankgate.server.service.BankgateServiceImpl;
import com.cana.bankgate.server.service.BankgateServiceInvoker;
import com.cana.bankgate.server.service.IBankgateService;
import com.cana.bankgate.server.service.IBankgateTestCenterService;
import com.cana.bankgate.server.transaction.IBankgateTransactionService;
import com.cana.bankgate.server.utils.LogExceptionUtil;
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
import com.cana.vbam.common.bankgate.dto.request.FreezeFundDTO;
import com.cana.vbam.common.bankgate.dto.request.FreezeInfoQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.PlatformWithdrawFundDTO;
import com.cana.vbam.common.bankgate.dto.request.TradeStatusQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.TransferFundDTO;
import com.cana.vbam.common.bankgate.dto.request.UnfreezeFundDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceDataDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountSignUpStateResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountTradeFlowResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankBaseResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankInfoResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankMainAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankMainAccountTradeFlowResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankTradeDetailResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankgateTransDTO;
import com.cana.vbam.common.bankgate.dto.response.FreezeInfoResultDTO;
import com.cana.vbam.common.bankgate.dto.response.TradeStatusResultDTO;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.cana.vbam.common.bankgate.enums.BankFlag;
import com.cana.vbam.common.bankgate.enums.BankTranStatus;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

/**
 * @author ducer
 *
 */
@Service("bankgateApiImpl")
public class BankgateApiImpl implements BankgateApi {
  @Resource
  private IBankgateTransactionService transactionService;
  @Resource
  private IVbamCommonService commonService;
  @Resource
  private IBankgateTestCenterService bankgateTestCenterService;
  @Resource(name = "citicQueryLockManager")
  private ILockManager citicQueryLockManager;

  // 开户
  @Override
  public BankAccountDTO createBankAccount(BankAccountCreateDTO createDTO) {
    BankgateTrans trans = transactionService.saveCreateBefore(BankBizType.create_bank_account, createDTO);
    IBankgateService service = BankgateServiceInvoker.bind(
        new BankgateServiceImpl(),
        BankBizType.create_bank_account, 
        BankAccountDTO.class);
    BankAccountDTO result = service.createBankAccount(createDTO);
    transactionService.updateAfterCreateAccount(trans.getId(), result);
    return result;
  }

  // 平台出金，即提现
  @Override
  public BankBaseResultDTO platformWithdrawFund(PlatformWithdrawFundDTO dto) {
    if (dto.getBankFlag() != BankFlag.citic_bank) {
      if (StringUtils.isBlank(dto.getReceiveBankNo())
          && StringUtils.isBlank(dto.getReceiveBankName())) {
        LogExceptionUtil.validateFail("当提现银行为非中信银行时，收款行支付联行号和名称至少一项不为空.");
      }
    }
//    if (!AccountNoUtil.luhnBankAccountNoPass(dto.getReceiveAccountNo())) {
//      LogExceptionUtil.validateFail("提现账号不合法，提现账号不能被LUHN算法校验通过");
//    }
    BankgateTrans trans = transactionService.saveTradeBefore(BankBizType.platform_withdraw_fund, dto);
    String gateSeq = trans.getGateSeq();
    IBankgateService service = BankgateServiceInvoker.bind(
        new BankgateServiceImpl(),
        BankBizType.platform_withdraw_fund, 
        BankBaseResultDTO.class, 
        true, 
        trans.getId());
    return service.platformWithdrawFund(gateSeq, dto);
  }

  // 查询账户余额
  @Override
  public BankAccountBalanceResultDTO queryAccountBalance(BankAccountBalanceQueryDTO queryDTO) throws Exception {
	if (!bankgateTestCenterService.isProdEnv()) {
		BankAccountBalanceResultDTO virtualBalance = bankgateTestCenterService.queryAccountBalance(queryDTO);
		if (virtualBalance != null){
			return virtualBalance;
		}else{
			if(commonService.getIsSetAllAccountVirtualBalanceFlag()){
				commonService.saveVirtualBalance(queryDTO.getAccountNo(), (long)(Math.random() * 100 * 100 * 10000 ), 72);
				return bankgateTestCenterService.queryAccountBalance(queryDTO);
			}
		}
	}
	if(!citicQueryLockManager.acquire(Constants.WAITING_LOCK_TIMEOUT)){
		throw WebException.instance("查询过于频繁，请稍后再试");
	}
    IBankgateService service = BankgateServiceInvoker.bind(
        new BankgateServiceImpl(),
        BankBizType.query_account_balance, 
        BankAccountBalanceResultDTO.class);
    BankAccountBalanceResultDTO bankAccountBalanceResultDTO = service.queryAccountBalance(queryDTO);
    if(StringUtils.equals(bankAccountBalanceResultDTO.getStatusText(), Constants.QUERY_LIMIT_FLAG)){
    	citicQueryLockManager.enable();
    }
    return bankAccountBalanceResultDTO;
  }

   // 查询账户余额 微信端
    @Override
    public BankAccountBalanceResultDTO queryAccountBalanceByWechat(String customerId, String accountNo) {
	  BankAccountBalanceQueryDTO queryDTO = new BankAccountBalanceQueryDTO();
	  queryDTO.setAccountNo(accountNo);
	  if (!bankgateTestCenterService.isProdEnv()) {
			BankAccountBalanceResultDTO virtualBalance = bankgateTestCenterService.queryAccountBalance(queryDTO);
			if (virtualBalance != null){
				return virtualBalance;
			}else{
				if(commonService.getIsSetAllAccountVirtualBalanceFlag()){
					commonService.saveVirtualBalance(queryDTO.getAccountNo(), (long)(Math.random() * 100 * 100 * 10000 ), 72);
					return bankgateTestCenterService.queryAccountBalance(queryDTO);
				}
			}
		}
	 // 去中信银行查询
	 IBankgateService service = BankgateServiceInvoker.bind(
			 new BankgateServiceImpl(),
			 BankBizType.query_account_balance, 
			 BankAccountBalanceResultDTO.class);
	 BankAccountBalanceResultDTO bankAccountBalanceResultDTO = service.queryAccountBalance(queryDTO);
	 if(StringUtils.equals(bankAccountBalanceResultDTO.getStatusText(), Constants.QUERY_LIMIT_FLAG)){
		 BankAccountBalanceResultDTO accountBalanceByWechatFromRedis = commonService.getAccountBalanceByWechatFromRedis(Constants.SEQUENCE_NAME_ACCOUNT_BALANCE_WECHAT);
		 if(null != accountBalanceByWechatFromRedis)
			 return accountBalanceByWechatFromRedis;
		 else
			 return bankAccountBalanceResultDTO;
	 }if(bankAccountBalanceResultDTO.getStatus().equals(BankTranStatus.success)){
		 // 银行查询到账户余额
		 commonService.saveAccountBalanceByWechatToRedis(bankAccountBalanceResultDTO);
		 return bankAccountBalanceResultDTO;
	 }else{
		 return bankAccountBalanceResultDTO;
	 }
	}
  
  // 查询交易状态
  @Override
  public TradeStatusResultDTO queryTradeStatus(TradeStatusQueryDTO queryDTO) throws Exception{
	  if (!bankgateTestCenterService.isProdEnv()) {
		  TradeStatusResultDTO tradeStatusResultDTO = bankgateTestCenterService.queryTradeStatusResult(queryDTO);
		  if (tradeStatusResultDTO != null)
			  return tradeStatusResultDTO;
	  }
	  if(!citicQueryLockManager.acquire(Constants.WAITING_LOCK_TIMEOUT)){
			throw WebException.instance("查询过于频繁，请稍后再试");
	  }
	  BankgateTrans trans = transactionService.queryGateTransByBizTypeAndSeq(queryDTO.getFundBizType(), queryDTO.getBusinessSeq());
	  if(null == trans) {
		  logger.warn("查询交易记录不存在：{}", queryDTO.getBusinessSeq());
		  throw WebException.instance(ReturnCode.NOT_FOUND);
	  }
	  IBankgateService service = BankgateServiceInvoker.bind(
			  new BankgateServiceImpl(),
			  BankBizType.query_trade_status, 
			  TradeStatusResultDTO.class);
	  TradeStatusResultDTO result = service.queryTradeStatus(trans, queryDTO);
	  if(BankTranStatus.success.equals(result.getStatus()) && 
			  CollectionUtils.isNotEmpty(result.getTradeStatusDatas()) &&
			  !BankTranStatus.success.equals(BankTranStatus.valueOf(trans.getStatus()))) {
		  transactionService.updateStatusById(trans.getId(), result.getTradeStatusDatas().get(0).getStatus());
	  }
	  if(StringUtils.equals(result.getStatusText(), Constants.QUERY_LIMIT_FLAG)){
	    	citicQueryLockManager.enable();
	  }
	  return result;
  	}

  // 在线销户
  @Override
  public BankAccountDTO cancelAccount(BankAccountBaseDTO baseDTO) {
    BankgateTrans trans = transactionService.saveTradeBefore(BankBizType.online_cancel_account, baseDTO);
    IBankgateService service = BankgateServiceInvoker.bind(
        new BankgateServiceImpl(),
        BankBizType.online_cancel_account, 
        BankAccountDTO.class, 
        true, 
        trans.getId());
    return service.cancelAccount(baseDTO);
  }

  // 冻结历史信息查询
  @Override
  public FreezeInfoResultDTO queryFreezeInfo(FreezeInfoQueryDTO queryDTO) {
    IBankgateService service = BankgateServiceInvoker.bind(
        new BankgateServiceImpl(),
        BankBizType.query_freeze_info, 
        FreezeInfoResultDTO.class);
    return service.queryFreezeInfo(queryDTO);
  }

  // 转帐
  @Override
  public BankBaseResultDTO transferFund(TransferFundDTO transferDTO) {
    return transferFundForce(BankBizType.transfer_fund, TransferFundType.transfer_fund, transferDTO);
  }

  // 调账
  @Override
  public BankBaseResultDTO adjustFund(TransferFundDTO transferDTO) {
    return transferFundForce(BankBizType.adjust_fund, TransferFundType.adjust_fund, transferDTO);
  }

  // 冻结账户资金
  @Override
  public BankBaseResultDTO freezeFund(FreezeFundDTO freezeDTO) {
    return transferFundForce(BankBizType.freeze_fund, TransferFundType.freeze_fund, freezeDTO);
  }

  // 解冻
  @Override
  public BankBaseResultDTO unfreezeFund(UnfreezeFundDTO unfreezeDTO) {
    return transferFundForce(BankBizType.unfreeze_fund, TransferFundType.unfreeze_fund,
        unfreezeDTO);
  }

  // 解冻支付
  @Override
  public BankBaseResultDTO unfreezePay(UnfreezeFundDTO unfreezeDTO) {
    return transferFundForce(BankBizType.unfreeze_pay, TransferFundType.unfreeze_pay, unfreezeDTO);
  }

  // 支付冻结
  @Override
  public BankBaseResultDTO freezePay(TransferFundDTO transferDTO) {
    return transferFundForce(BankBizType.freeze_pay, TransferFundType.freeze_pay, transferDTO);
  }

  /**
   * 请求同一个银行接口:强制转账
   */
  private <T extends BankAccountBaseDTO> BankBaseResultDTO transferFundForce(BankBizType bType, 
      TransferFundType tType, T dto) {
    BankgateTrans trans = transactionService.saveTradeBefore(bType, dto);
    String gateSeq = trans.getGateSeq();
    if (!bankgateTestCenterService.isProdEnv()) {
    	BankBaseResultDTO virtualTransferResult = bankgateTestCenterService.transferFundForce(tType, gateSeq, dto);
    	if (virtualTransferResult != null)
    		return virtualTransferResult;
    }
    IBankgateService service = BankgateServiceInvoker.bind(
        new BankgateServiceImpl(), 
        bType,
        BankBaseResultDTO.class, 
        true, 
        trans.getId());
    return service.transferFundForce(tType, gateSeq, dto);
  }

  // 查询银行信息
  public BankInfoResultDTO queryBankInfo(BankInfoQueryDTO queryDTO) {
    IBankgateService service = BankgateServiceInvoker.bind(
        new BankgateServiceImpl(),
        BankBizType.query_bank_info, 
        BankInfoResultDTO.class);
    return service.queryBankInfo(queryDTO);
  }

  @Override
  public List<BankgateTransDTO> queryBankgateTrans(List<String> bizSeqs) {
    return transactionService.query(bizSeqs);
  }

  @Override
  public BankAccountTradeFlowResultDTO queryBankAccountTradeFlow(BankAccountTradeFlowQueryDTO queryDTO) throws Exception {
	if(!citicQueryLockManager.acquire(Constants.WAITING_LOCK_TIMEOUT)){
		throw WebException.instance("查询过于频繁，请稍后再试");
	}
    IBankgateService service = BankgateServiceInvoker.bind(
        new BankgateServiceImpl(),
        BankBizType.query_account_trade_flow, 
        BankAccountTradeFlowResultDTO.class);
    BankAccountTradeFlowResultDTO bankAccountTradeFlowResultDTO = service.queryBankAccountTradeFlow(queryDTO);
    if(StringUtils.equals(bankAccountTradeFlowResultDTO.getStatusText(), Constants.QUERY_LIMIT_FLAG)){
    	citicQueryLockManager.enable();
    }
    return bankAccountTradeFlowResultDTO;
  }

  @Override
  public BankMainAccountTradeFlowResultDTO queryBankMainAccountTradeFlow(BankMainAccountTradeFlowQueryDTO queryDTO) throws Exception {
	if(!citicQueryLockManager.acquire(Constants.WAITING_LOCK_TIMEOUT)){
		throw WebException.instance("查询过于频繁，请稍后再试");
	}
    IBankgateService service = BankgateServiceInvoker.bind(
        new BankgateServiceImpl(),
        BankBizType.query_main_account_trade_flow, 
        BankMainAccountTradeFlowResultDTO.class);
    BankMainAccountTradeFlowResultDTO bankMainAccountTradeFlowResultDTO = service.queryBankMainAccountTradeFlow(queryDTO);
    if(StringUtils.equals(bankMainAccountTradeFlowResultDTO.getStatusText(), Constants.QUERY_LIMIT_FLAG)){
    	citicQueryLockManager.enable();
    }
    return bankMainAccountTradeFlowResultDTO;
  }

  @Override
  public BankMainAccountBalanceResultDTO queryBankMainAccountBalance(BankMainAccountBalanceQueryDTO queryDTO) throws Exception {
	if(!citicQueryLockManager.acquire(Constants.WAITING_LOCK_TIMEOUT)){
		throw WebException.instance("查询过于频繁，请稍后再试");
	}
    IBankgateService service = BankgateServiceInvoker.bind(
        new BankgateServiceImpl(),
        BankBizType.query_main_account_balance, 
        BankMainAccountBalanceResultDTO.class);
    BankMainAccountBalanceResultDTO queryBankMainAccountBalance = service.queryBankMainAccountBalance(queryDTO);
    if(StringUtils.equals(queryBankMainAccountBalance.getStatusText(), Constants.QUERY_LIMIT_FLAG)){
    	citicQueryLockManager.enable();
    }
    return queryBankMainAccountBalance;
  }

	@Override
	public BankTradeDetailResultDTO queryBankTradeDetailNonLogin(BankTradeDetailQueryDTO queryDTO) throws Exception {
		if(!citicQueryLockManager.acquire(Constants.WAITING_LOCK_TIMEOUT)){
			throw WebException.instance("查询过于频繁，请稍后再试");
		}
	    IBankgateService service = BankgateServiceInvoker.bind(
	        new BankgateServiceImpl(),
	        BankBizType.nonlogin_query_trade_detail, 
	        BankTradeDetailResultDTO.class);
	    BankTradeDetailResultDTO bankTradeDetailResultDTO = service.queryBankTradeDetailNonLogin(queryDTO);
	    if(StringUtils.equals(bankTradeDetailResultDTO.getStatusText(), Constants.QUERY_LIMIT_FLAG)){
	    	citicQueryLockManager.enable();
	    }
	    return bankTradeDetailResultDTO;
	}

	@Override
	public BankBaseResultDTO alterAccountParam(BankAccountParamAlterDTO alterDTO) {
		 IBankgateService service = BankgateServiceInvoker.bind(
			        new BankgateServiceImpl(),
			        BankBizType.account_param_mangerment, 
			        BankBaseResultDTO.class);
		return service.alterAccountParam(alterDTO);
	}

	@Override
	public BankAccountSignUpStateResultDTO queryBankAccountSingUpState(BankAccountSignUpStateQueryDTO queryDTO) throws Exception {
		if(!citicQueryLockManager.acquire(Constants.WAITING_LOCK_TIMEOUT)){
			throw WebException.instance("查询过于频繁，请稍后再试");
		}
		IBankgateService service = BankgateServiceInvoker.bind(
		        new BankgateServiceImpl(),
		        BankBizType.account_signup_state, 
		        BankAccountSignUpStateResultDTO.class);
		BankAccountSignUpStateResultDTO bankAccountSignUpStateResultDTO = service.queryBankAccountSingUpState(queryDTO);
	    if(StringUtils.equals(bankAccountSignUpStateResultDTO.getStatusText(), Constants.QUERY_LIMIT_FLAG)){
	    	citicQueryLockManager.enable();
	    }
	    return bankAccountSignUpStateResultDTO;
	}

}
