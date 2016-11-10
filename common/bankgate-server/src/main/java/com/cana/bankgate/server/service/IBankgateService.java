/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.bankgate.server.service;

import com.cana.bankgate.server.enums.TransferFundType;
import com.cana.bankgate.server.po.BankgateTrans;
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
 * @author ducer
 *
 */
public interface IBankgateService {


  /**
   * 开户，此处的意思是附属账户预签约
   */
  public BankAccountDTO createBankAccount(BankAccountCreateDTO infoDTO);

  /**
   * 查询账户余额
   */
  public BankAccountBalanceResultDTO queryAccountBalance(BankAccountBalanceQueryDTO queryDTO);

  /**
   * 查询交易状态
   */
  public TradeStatusResultDTO queryTradeStatus(BankgateTrans trans, TradeStatusQueryDTO queryDTO);

  /**
   * 查询冻结信息历史
   */
  public FreezeInfoResultDTO queryFreezeInfo(FreezeInfoQueryDTO queryDTO);

  /**
   * BF：转账；BG：解冻；BH：解冻支付；BR：冻结；BS：支付冻结 都属于强制转账<br>
   */
  public <T extends BankAccountBaseDTO> BankBaseResultDTO transferFundForce(TransferFundType type, String gateSeq, T dto);

  /**
   * 平台出金，现在所有的出金都使用平台出金
   */
  public BankBaseResultDTO platformWithdrawFund(String gateSeq, PlatformWithdrawFundDTO dto);

  /**
   * 在线销户
   */
  public BankAccountDTO cancelAccount(BankAccountBaseDTO dto);

  /**
   * 查询银行支付联行号和行名称
   */
  public BankInfoResultDTO queryBankInfo(BankInfoQueryDTO queryDTO);

  /**
   * 查询附属账号交易明细
   */
  public BankAccountTradeFlowResultDTO queryBankAccountTradeFlow(BankAccountTradeFlowQueryDTO queryDTO);

  /**
   * 查询主账号交易明细<br/>
   * Note:如果登录名或主账号为空，则默认为查询平台CANA主账号的余额
   */
  public BankMainAccountTradeFlowResultDTO queryBankMainAccountTradeFlow(BankMainAccountTradeFlowQueryDTO queryDTO);

  /**
   * 查询主账号余额.<br/> 
   * Note:如果登录名和主账号为空，则默认为查询平台CANA主账号的余额，如果登录名不为空，主账号为空，视为非法数据
   */
  public BankMainAccountBalanceResultDTO queryBankMainAccountBalance(BankMainAccountBalanceQueryDTO queryDTO);
  
  /**
   * 非登录打印明细查询
   * @param queryDTO
   * @return
   */
  public BankTradeDetailResultDTO queryBankTradeDetailNonLogin(BankTradeDetailQueryDTO queryDTO);

  /**
   * 修改预签约部分参数
   * @param alterDTO
   * @return
   */
  public BankBaseResultDTO alterAccountParam(BankAccountParamAlterDTO alterDTO);
  
  /**
   * 查询预签约参数
   * @param queryDTO
   * @return
   */
  public BankAccountSignUpStateResultDTO queryBankAccountSingUpState(BankAccountSignUpStateQueryDTO queryDTO);
}
