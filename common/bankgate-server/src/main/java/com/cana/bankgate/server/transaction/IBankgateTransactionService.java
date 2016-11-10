/**
 * 
 */
package com.cana.bankgate.server.transaction;

import java.util.List;

import com.cana.bankgate.server.po.BankgateTrans;
import com.cana.bankgate.server.request.account.BankAccountGroup;
import com.cana.vbam.common.bankgate.dto.request.BankAccountBaseDTO;
import com.cana.vbam.common.bankgate.dto.request.BankAccountCreateDTO;
import com.cana.vbam.common.bankgate.dto.request.UnfreezeFundDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountDTO;
import com.cana.vbam.common.bankgate.dto.response.BankgateTransDTO;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.cana.vbam.common.bankgate.enums.BankTranStatus;
import com.cana.vbam.common.bankgate.enums.FundBizType;

/**
 * @author ducer
 *
 */
public interface IBankgateTransactionService {

  /**
   * 交易前保存网关流水
   */
  BankgateTrans saveCreateBefore(BankBizType type, BankAccountCreateDTO createDTO);
  
  /**
   * 创建附属账号前，保存网关流水
   */
  <T extends BankAccountBaseDTO> BankgateTrans saveTradeBefore(BankBizType type, T tradeDTO);
  
  /**
   * 查询网关流水
   */
  List<BankgateTransDTO> query(List<String> bizSeqs);

  /**
   * 根据附属账号提取附属账号所属的主账号和主账号用户名.若附属账号为空，则默认取平台主账号和用户名
   */
  BankAccountGroup extractBankAccountGroup(String accountNo);

  /**
   * 解冻和解冻支付的时候获取前步操作网关流水ID
   */
  String extractOriginId(BankBizType type, UnfreezeFundDTO dto);

  /**
   * 创建附属账号后把创建的账号信息更新到网关流水表
   */
  void updateAfterCreateAccount(String id, BankAccountDTO dto);

  /**
   * 通过ID更新流水状态，加锁修改，会验证状态
   */
  void updateStatusById(String id, BankTranStatus status);

  /**
   * 通过业务端流水查询网关流水,如果网关不存在该交易流水，则返回null
   */
  BankgateTrans queryGateTransByBizTypeAndSeq(FundBizType type, String bizSeq);
}
