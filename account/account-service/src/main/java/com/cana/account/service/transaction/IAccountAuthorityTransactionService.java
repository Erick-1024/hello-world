/**
 *  Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.account.service.transaction;

import java.util.List;

import com.cana.account.dao.po.Account;
import com.cana.account.dao.po.AccountApply;
import com.cana.member.dao.po.User;
import com.cana.vbam.common.account.dto.AccountApplyAuditDetail;
import com.cana.vbam.common.account.enums.TradeRuleResult;

/**
 * 账户操作权限检查事务类
 * @author ducer
 *
 */
public interface IAccountAuthorityTransactionService {
	
	/**
	 * 对当前账户是否有转出的权限
	 */
	public TradeRuleResult checkTransferAuthority(User customer, Account outAccount);
	
	/**
	 * 转帐权限
	 */
	public TradeRuleResult checkTransferAuthority(User customer, Account outAccount, Account inAccount);
	
	
	/**
	 * 提现权限
	 */
	public TradeRuleResult checkWithdrawAuthority(User customer, Account account);
	
	/**
	 * 查询账户余额权限
	 */
	public void checkQueryBalanceAuthority(String customerId,List<String> accountNos);
	
	/**
	 * 审核待开户权限和能否代开户
	 */
	public AccountApply checkAuditAgentApplyAuthority(AccountApplyAuditDetail auditDetail);
}
