/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.vbam.common.annotations.NotBlank;
import com.cana.vbam.common.bankgate.dto.request.BankAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.BankAccountBaseDTO;
import com.cana.vbam.common.bankgate.dto.request.BankAccountCreateDTO;
import com.cana.vbam.common.bankgate.dto.request.BankAccountParamAlterDTO;
import com.cana.vbam.common.bankgate.dto.request.BankAccountSignUpStateQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.BankAccountTradeFlowQueryDTO;
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
import com.cana.vbam.common.bankgate.dto.response.BankMainAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankMainAccountTradeFlowResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankTradeDetailResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankgateTransDTO;
import com.cana.vbam.common.bankgate.dto.response.FreezeInfoResultDTO;
import com.cana.vbam.common.bankgate.dto.response.TradeStatusResultDTO;
import com.cana.vbam.common.bankgate.enums.DebitCreditTag;

/**
 * 银行网关接口.Cana项目内的所有资金计算全是以分为单位，用Long.所有@NotBlank的都不能为空.<br>
 * 被参数校验拦截的业务,流水表不会记录，因为没有产生和银行之间的交互，但是业务端需要记录.
 * 
 * @author ducer
 *
 */
public interface BankgateApi {

  public Logger logger = LoggerFactory.getLogger(BankgateApi.class);

  /**
   * 银行附属帐号开户.业务端叫开户，银行端叫附属账户预签约
   * 
   * @param customerName 客户名称
   * @return
   * @throws Exception
   */
  public BankAccountDTO createBankAccount(@NotBlank BankAccountCreateDTO infoDTO);

  /**
   * 出金，能跨行，类似提现.所有出金都用的银行端提供的平台出金接口，而不是出金接口
   * 
   * @param withdrawFundDTO
   * @return
   * @throws Exception
   */
  public BankBaseResultDTO platformWithdrawFund(@NotBlank PlatformWithdrawFundDTO withdrawDTO);

  /**
   * 附属账户余额查询.所有查询不保存流水信息
   * 
   * @param queryDTO
   * @return
   * @throws Exception
   */
  public BankAccountBalanceResultDTO queryAccountBalance(@NotBlank BankAccountBalanceQueryDTO queryDTO) throws Exception;

  /**
   * 查询交易状态
   * 
   * @param queryDTO
   * @return {@link TradeStatusResultDTO#getStatus()}标志的是查询操作处理状态<br>
   *         而真正的交易状态是 {@link TradeStatusQueryResultDTO#getTradeStatusDatas()中的状态}<br>
   *         因为可以用交易类型查询，所以返回的是一个集合.
   * @throws Exception
   */
  public TradeStatusResultDTO queryTradeStatus(@NotBlank TradeStatusQueryDTO queryDTO) throws Exception;

  /**
   * 帐号冻结历史信息查询，账号是冻结金额所在的账号.<br/>
   * Note: 冻结的时候，查冻结信息需要传发起方账号，冻结支付的时候，查冻结信息需要传收款方账号，
   * 因为冻结的同时已经支付到了对方账号上
   * 
   * @param queryDTO
   * @return
   * @throws Exception
   */
  public FreezeInfoResultDTO queryFreezeInfo(@NotBlank FreezeInfoQueryDTO queryDTO);

  /**
   * 转账，不能跨行，只能在虚拟账户之间进行转账
   * 
   * @param transferDTO
   * @return
   * @throws Exception
   */
  public BankBaseResultDTO transferFund(@NotBlank TransferFundDTO transferDTO);

  /**
   * 调账，和逻辑上和转帐是等价的，只是业务上的概念不同
   * 
   * @param transferDTO
   * @return
   * @throws Exception
   */
  public BankBaseResultDTO adjustFund(@NotBlank TransferFundDTO transferDTO);

  /**
   * 冻结，冻结资金的冻结编号只能通过查询得到，本接口不会返回冻结编号.<br>
   * 冻结对应解冻支付，是冻结在付款方账户的
   * 
   * @param freezeDTO
   * @return
   * @throws Exception
   */
  public BankBaseResultDTO freezeFund(@NotBlank FreezeFundDTO freezeDTO);

  /**
   * 支付冻结，支付冻结对应解冻，是冻结在收款方账户的
   * 
   * @param transferDTO
   * @return
   * @throws Exception
   */
  public BankBaseResultDTO freezePay(@NotBlank TransferFundDTO transferDTO);

  /**
   * 解冻
   * 
   * @param freezeDTO
   * @return
   * @throws Exception
   */
  public BankBaseResultDTO unfreezeFund(@NotBlank UnfreezeFundDTO freezeDTO);

  /**
   * 解冻支付
   * 
   * @param freezeDTO
   * @return
   * @throws Exception
   */
  public BankBaseResultDTO unfreezePay(@NotBlank UnfreezeFundDTO freezeDTO);

  /**
   * 在线销户
   * 
   * @param baseDTO
   * @return
   * @throws Exception
   */
  public BankAccountDTO cancelAccount(@NotBlank BankAccountBaseDTO baseDTO);

  /**
   * 通过业务系统的业务流水查询网关的流水记录
   * 
   * @param bizSeqs
   * @return
   */
  public List<BankgateTransDTO> queryBankgateTrans(@NotBlank List<String> bizSeqs);

  /**
   * 查询附属账户体系内附属账户的交易明细（包括附属账户的入金），支持分页查询。<br/>
   * 当输入分页查询条件（起始记录号、查询记录条数）时，每页最多返回10条记录。 <br/>
   * 当分页查询条件为空时，查询符合查询条件的全部交易明细。<br/>
   * {@link DebitCreditTag},财务上的借贷标志,这里标志入金还是出金.入金:{@link DebitCreditTag#credit},出金{@link DebitCreditTag#debit}
   * 
   * @param queryDTO
   * @return
   */
  public BankAccountTradeFlowResultDTO queryBankAccountTradeFlow(@NotBlank BankAccountTradeFlowQueryDTO queryDTO) throws Exception;

  /**
   * 主账户查询到的只是主账户入金明细，该交易要求分页查询，每页最多显示20条记录。<br/>
   * Note:如果银行登录用户名或者主账号有一个为空，则默认使用平台登录用户名和主账号
   * 
   * @param queryDTO
   * @return
   */
  public BankMainAccountTradeFlowResultDTO queryBankMainAccountTradeFlow(@NotBlank BankMainAccountTradeFlowQueryDTO queryDTO) throws Exception;

  /**
   * 查询主账号余额，该接口是查询活期账号余额的接口，但本平台只有主账号是活期账号.<br/>
   * 由于速度的原因，不建议同时查询多个账号。一般情况下，查询账号不建议多于10个<br/>
   * Note:如果登录名和主账号为空，则默认为查询平台CANA主账号的余额，如果登录名不为空，主账号为空，视为非法数据
   * 
   * @param queryDTO
   * @return
   * @throws Exception
   */
  public BankMainAccountBalanceResultDTO queryBankMainAccountBalance(@NotBlank BankMainAccountBalanceQueryDTO queryDTO) throws Exception;

  /**
   * 非登录打印明细查询
   * @param queryDTO
   * @return
   */
  public BankTradeDetailResultDTO queryBankTradeDetailNonLogin(@NotBlank BankTradeDetailQueryDTO queryDTO) throws Exception;
  
  /**
   * 修改预签约部分参数
   * @param alterDTO
   * @return
   */
  public BankBaseResultDTO alterAccountParam(@NotBlank BankAccountParamAlterDTO alterDTO);
  
  /**
   * 查询预签约参数
   * @param queryDTO
   * @return
   */
  public BankAccountSignUpStateResultDTO queryBankAccountSingUpState(@NotBlank BankAccountSignUpStateQueryDTO queryDTO) throws Exception;

  /**
   * 微信端查询账户余额
   * @param customerId
   * @param accountNo
   * @return
   */
  public BankAccountBalanceResultDTO queryAccountBalanceByWechat(String customerId, String accountNo);
}
