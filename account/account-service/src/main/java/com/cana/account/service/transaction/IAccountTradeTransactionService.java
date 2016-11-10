package com.cana.account.service.transaction;

import org.apache.commons.lang3.tuple.Pair;

import com.cana.account.dao.po.AccountTradeRecord;
import com.cana.vbam.common.account.dto.AccountTradeApplyDTO;
import com.cana.vbam.common.account.dto.AccountTradeApplyQueryCriteria;
import com.cana.vbam.common.account.dto.AccountTradeAuditResult;
import com.cana.vbam.common.account.dto.DeductFundRequestDTO;
import com.cana.vbam.common.account.dto.TransferFundForCreditRequestDTO;
import com.cana.vbam.common.account.dto.TransferFundRequestDTO;
import com.cana.vbam.common.account.dto.WithdrawFundRequestDTO;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.account.enums.TradeRuleResult;
import com.cana.vbam.common.bankgate.dto.response.BankAccountTradeFlowDataDTO;
import com.cana.vbam.common.bankgate.dto.response.TradeStatusResultDTO;
import com.cana.vbam.common.dto.PageResult;

/**
 * 账户交易相关业务处理
 * 
 * @author XuMeng
 *
 */
public interface IAccountTradeTransactionService {

	/**
	 * 转帐，保证事务性，所有操作放到一个事务里面.包括了需要申请的时候创建申请
	 * 
	 * @return 若创建了转账申请，则返回带有申请ID
	 */
	public Pair<TradeRuleResult, String> transferFund(String bizSeq, TransferFundRequestDTO request);
    
    /**
     * 提现(韵达用)
     * @param bizSeq
     * @param withdrawFundRequest
     */
    public void withdrawFundForYundaEx(WithdrawFundRequestDTO withdrawFundRequest);
    
	/**
	 * 提现,保证事务性，所有操作放到一个事物里面.包括了需要申请的时候创建申请
	 * 
	 * @param withdrawFundRequest
	 * @return 若创建了提现申请，则返回带有申请ID
	 */
	public Pair<TradeRuleResult, String> withdrawFund(String bizSeq, WithdrawFundRequestDTO withdrawFundRequest);

	/**
	 * 账扣，满足条件则创建交易流水
	 * 
	 * @return 转出账号的账号名称
	 */
	public String deductFund(DeductFundRequestDTO request);

	/**
	 * 授信转账
	 * 
	 * @return 转出账号的账号名称
	 */
	public String transferFundForCredit(TransferFundForCreditRequestDTO request);

	/**
	 * 不管审核成功与否，更新转出帐号状态和交易申请记录。如果审核通过，则创建一条交易记录
	 * 
	 * @param tradeAuditResult
	 * @return
	 */
	public TransferFundRequestDTO auditTransferFundApply(String bizSeq, AccountTradeAuditResult tradeAuditResult);

	/**
	 * 不管审核成功与否，更新转出帐号状态和交易申请记录。如果审核通过，则创建一条交易记录
	 * 
	 * @param tradeAuditResult
	 * @return
	 */
	public WithdrawFundRequestDTO auditWithdrawFundApply(String bizSeq, AccountTradeAuditResult tradeAuditResult);

    /**
     * 更新交易状态
     * @param businessSeq
     * @param tradeStatus
     */
    public void updateTradeRecordStatus(String businessSeq, AccountTradeStatus tradeStatus);
    
    /**
     * 查询申请
     * @param userId
     * @param criteria
     * @return
     */
    public PageResult<AccountTradeApplyDTO> queryTradeApplys(String userId, AccountTradeApplyQueryCriteria criteria);
    
    /**
     * 查询交易申请，包括转帐和提现
     * @param userId
     * @param applyId
     * @return
     */
    public AccountTradeApplyDTO queryTradeApply(String userId,String applyId);
    
    /**
     * 
     * @param businessSeq
     * @param tradeStatus
     */
    public void insertWithdrawFeeRecord(AccountTradeRecord tradeRecord, BankAccountTradeFlowDataDTO tradeDTO);
    
    /**
     * 重新发起提现信息获取及更新
     * @param tradeRecordId
     * @return
     */
    public WithdrawFundRequestDTO queryWithdrawInfoAndUpdateState(String tradeRecordId, String bizSeq, TradeStatusResultDTO queryTradeStatus);
}
