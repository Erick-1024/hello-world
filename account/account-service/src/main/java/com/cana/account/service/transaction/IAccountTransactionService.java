package com.cana.account.service.transaction;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.cana.account.dao.po.Account;
import com.cana.member.dao.po.User;
import com.cana.vbam.common.account.dto.AccountAgentCreateDTO;
import com.cana.vbam.common.account.dto.AccountApplyAuditDetail;
import com.cana.vbam.common.account.dto.AccountBalancesAndNumberDTO;
import com.cana.vbam.common.account.dto.AccountCustomerTradeRecordQueryDTO;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.dto.AccountGroupDTO;
import com.cana.vbam.common.account.dto.AccountQueryCriteria;
import com.cana.vbam.common.account.dto.AccountTradeRecordCriteria;
import com.cana.vbam.common.account.dto.AccountTradeRecordDTO;
import com.cana.vbam.common.account.dto.BankAccountGroupDTO;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;

public interface IAccountTransactionService {

	/**
	 * 批量创建平台账户，主动开户
	 * @param user notnull
	 * @param bankAccounts notnull
	 * @param buyerNames nullable
	 * @return 银行帐号集合
	 */
	public  List<Account> batchCreateAccountSelf(User user, List<Pair<String, String>> bankAccounts, List<String> buyerNames);

	/**
	 * 代开户
	 */
	public String createAccountApplyByAgent(User agentUser, User accountUser, AccountAgentCreateDTO accountAgentCreateDTO);

	/**
	 * 审核账户的同时创建账号，写在同一个事务里面，避免审核通过后账户没有创建成功.<br>
	 * 审核不通过，则只保存审核结果，而不创建账号
	 * @param auditor 审核者
	 * @param detail 审核详情
	 * @param seqAndNos 银行开户结果
	 * @return
	 */
	public boolean auditAccountApplyAndCreateAccount(User auditor, AccountApplyAuditDetail detail,List<Pair<String, String>> seqAndNos);
	
	/**
	 * 根据当前登录客户和条件查询账户列表
	 */
	public PageResult<AccountDTO> queryAccounts(String customerId,
            AccountQueryCriteria accountQueryCriteria);

	 /**
     * 查询当前用户的账户信息
     * @param customerId
     * @return
     */
	public List<AccountDTO> queryAccountsByCustomerId(String customerId);
	
	public List<AccountDTO> queryRepaymentAccounts(String factorId,
            String finaceName);
	/**
     * 根据当前登录客户和账户Id获取账户详情
     */
    public AccountDTO getAccount(String customerId, String accountId);
    
    public AccountDTO getOwnAccountByNo(String customerId, String accountNo);
    
    public AccountDTO getOwnAccountByNameAndNo(String customerName, String accountNo);
    
    public List<AccountDTO> getAccountByNos(String customerId,List<String> accountNos);
    
    public Account getAccountPOByNo(String accountNo);
    
    /**
     * 根据账户Id查询账户的收支明细
     * @param customerId
     * @param accountId
     * @return
     */
    public PageResult<AccountTradeRecordDTO> getTradeRecord(String customerId, AccountTradeRecordCriteria accountTradeRecordCriteria);
    
    /**
     * 账户冻结
     */
    public boolean accountFreeze(String userId, String customerId, String accountId);
    
    /**
     * 账户解冻
     */
    public boolean accountUnfreeze(String userId, String customerId, String accountId);

    /**
     * 设为默认账户（账户列表入口）
     */
    public boolean accountSetDefaultRepayment(String customerId, String accountId);

    /**
     * 对当前账户监管关系双方以前的默认账户设为false，并将当前账户的默认账户设为true
     * @param account
     */
    public void updateAccountIsDefaultRepayment(Account account);

    /**
     * 查询流水明细列表
     * @param customerId
     * @param criteria
     * @return
     */
	public PageResult<AccountTradeRecordDTO> queryTradeRecords(String customerId, AccountTradeRecordCriteria criteria);
	
	public List<CustomerDetailDTO> querySupervisorsByFactorId(String factorId);
	
	/**
     * 获取给定用户的账户余额和数量
     * @param customerId
     * @return
     */
    public AccountBalancesAndNumberDTO getAccountBalancesAndNumber(String customerId);
    
    public boolean setFactorTransferInAccount(String factorId, String accountNo);
    
    /**
     * 分页获取主账号和附属账号组集合
     */
    public List<AccountGroupDTO> getOwnAccountGroups(String customerId);
    
    /**
     * 分页获取不属于自己但被自己监管的别人的主账号和附属账号组集合 
     */
    public List<AccountGroupDTO> getOtherSupervisionAccountGroups(String customerId);
    
    public List<BankAccountGroupDTO> getBankAccountGroups();
    
    /**
     * 查询客户的交易记录
     */
    public List<AccountTradeRecordDTO> getCustomerTradeRecord(AccountCustomerTradeRecordQueryDTO query);

}
