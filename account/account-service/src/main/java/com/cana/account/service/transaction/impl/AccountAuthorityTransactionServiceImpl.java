/**
 *  Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.account.service.transaction.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.account.dao.mapper.AccountTableLockMapper;
import com.cana.account.dao.mapper.gen.AccountMapper;
import com.cana.account.dao.mapper.gen.AccountTradeApplyMapper;
import com.cana.account.dao.po.Account;
import com.cana.account.dao.po.AccountApply;
import com.cana.account.dao.po.AccountExample;
import com.cana.account.service.transaction.IAccountAuthorityTransactionService;
import com.cana.account.service.transaction.ICustomerTransactionService;
import com.cana.member.dao.po.User;
import com.cana.vbam.common.account.dto.AccountApplyAuditDetail;
import com.cana.vbam.common.account.enums.AccountAccumulationStatus;
import com.cana.vbam.common.account.enums.AccountApplyStatus;
import com.cana.vbam.common.account.enums.AccountStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.account.enums.TradeRuleResult;
import com.cana.vbam.common.member.enums.user.UserType;
import com.travelzen.framework.core.exception.WebException;

/**
 * @author ducer
 *
 */
@Service
public class AccountAuthorityTransactionServiceImpl implements IAccountAuthorityTransactionService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ICustomerTransactionService customerTransactionService;
	@Resource
	private AccountMapper accountMapper;
	@Resource
	private AccountTradeApplyMapper accountTradeApplyMapper;
	@Resource
	private AccountTableLockMapper accountTableLockMapper;

	/**
	 * 是自己的账户，或者是被自己监管的
	 */
	private boolean isOwnOrSupervisedBySelf(User customer, Account account) {
		if (isOwnerAccount(customer, account) || customer.getId().equals(account.getSupervisorId())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是监管账户
	 * 
	 * @param account
	 * @return
	 */
	private boolean isSupervisionAccount(Account account) {
		if (AccountSupervisionStatus.HAVE_SUPERVISION.name().equals(account.getSupervisionStatus())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是一般账户
	 * 
	 * @param account
	 * @return
	 */
	private boolean isGeneralAccount(Account account) {
		if (AccountType.GENERAL.name().equals(account.getAccountType())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是专用账户
	 * 
	 * @param account
	 * @return
	 */
	private boolean isSpecialAccount(Account account) {
		return AccountType.SPECIAL.name().equals(account.getAccountType());
	}

	/**
	 * 是归集账户
	 * 
	 * @param account
	 * @return
	 */
	private boolean isAccumulationAccount(Account account) {
		if (isSpecialAccount(account)) {
			return AccountAccumulationStatus.BE_ACCUMULATED.name().equals(account.getAccumulationStatus());
		}
		if (isGeneralAccount(account)) {
			return AccountAccumulationStatus.HAVE_ACCUMULATION.name().equals(account.getAccumulationStatus());
		}
		return false;
	}

	/**
	 * 是自己的帐号
	 */
	public boolean isOwnerAccount(User customer, Account account) {
		if (StringUtils.isNotBlank(customer.getMasterId())) {
			return account.getCompanyId().equals(customer.getMasterId());
		}
		return account.getCompanyId().equals(customer.getId());
	}

	/**
	 * 融资商转账规则
	 */
	private TradeRuleResult finaceTransferRule(User customer, Account outAccount, Account inAccount) {
		//转入账户是核心企业->拒绝
		User inaccountCustomer = customerTransactionService.checkCustomerIsValid(inAccount.getCompanyId());
		if(UserType.CORECOMPANY.equals(inaccountCustomer.getUserType()))
			return TradeRuleResult.REJECT;
		if (isGeneralAccount(outAccount)) {// 转出账户是一般账户
			if (!isSupervisionAccount(outAccount)) {// 转出账户是一般未监管
				if (isGeneralAccount(inAccount)) {// 转入账户是一般账户
					if (isSupervisionAccount(inAccount)) {
						if (isOwnOrSupervisedBySelf(customer, inAccount)) {
							return TradeRuleResult.ACCEPT;
						} else {
							return TradeRuleResult.REJECT;
						}
					} else {
						return TradeRuleResult.ACCEPT;
					}
				} else {// 转入账户是专用账户
					if (customer.getId().equals(inAccount.getCompanyId())) {
						return TradeRuleResult.ACCEPT;
					} else {
						return TradeRuleResult.REJECT;
					}
				}
			} else {// 转出账户是一般监管的
				if (customer.getId().equals(outAccount.getCompanyId())) {// 转出账户是自己的监管
					if (isGeneralAccount(inAccount)) {
						if (outAccount.getCompanyId().equals(inAccount.getCompanyId())
								|| outAccount.getSupervisorId().equals(inAccount.getCompanyId())) {
							return TradeRuleResult.NEED_AUDIT;
						} else {
							return TradeRuleResult.REJECT;
						}
					} else {// 转出账户是专用
						if (isAccumulationAccount(inAccount)) {// 是归集的
							return TradeRuleResult.REJECT;
						} else {
							return TradeRuleResult.NEED_AUDIT;
						}
					}
				} else {// 转出账户是对方的监管
					if (isGeneralAccount(inAccount)) {
						if (isSupervisionAccount(inAccount)) {
							if (outAccount.getCompanyId().equals(inAccount.getCompanyId())
									|| outAccount.getSupervisorId().equals(inAccount.getCompanyId())) {
								return TradeRuleResult.NEED_AUDIT;
							} else {
								return TradeRuleResult.REJECT;
							}
						} else {
							if (isOwnerAccount(customer, inAccount)) {
								return TradeRuleResult.NEED_AUDIT;
							} else {
								return TradeRuleResult.REJECT;
							}
						}
					} else {// 转出账户是专用
						if (isAccumulationAccount(inAccount)) {// 是归集的
							return TradeRuleResult.REJECT;
						} else {
							return TradeRuleResult.NEED_AUDIT;
						}
					}
				}
			}
		} else {// 转出账户是专用账户
			if (!isAccumulationAccount(outAccount)) {// 转出账户是未归集状态
				if (isGeneralAccount(inAccount)) {
					if (isSupervisionAccount(inAccount)) {
						if (isOwnOrSupervisedBySelf(customer, inAccount)) {
							return TradeRuleResult.ACCEPT;
						} else {
							return TradeRuleResult.REJECT;
						}
					} else {
						return TradeRuleResult.ACCEPT;
					}
				} else {// 转入账户是专用，此时必需是自己的才可转
					if (customer.getId().equals(inAccount.getCompanyId())) {
						return TradeRuleResult.ACCEPT;
					} else {
						return TradeRuleResult.REJECT;
					}
				}
			} else {// 转出账户是归集的
				return TradeRuleResult.REJECT;
			}
		}

	}

	/**
	 * 保理商转账规则
	 */
	private TradeRuleResult factorTransferRule(User customer, Account outAccount, Account inAccount) {
		//转入账户是核心企业->拒绝
		User inaccountCustomer = customerTransactionService.checkCustomerIsValid(inAccount.getCompanyId());
		if(UserType.CORECOMPANY.equals(inaccountCustomer.getUserType()))
			return TradeRuleResult.REJECT;
		
		if (isSupervisionAccount(outAccount)) {// 转出账户是监管的
			if (isGeneralAccount(inAccount)
			        && !isSupervisionAccount(inAccount)
			        && customer.getId().equals(inAccount.getCompanyId())) {// 自己的一般账户
				return TradeRuleResult.NEED_AUDIT;
			} else {
				return TradeRuleResult.REJECT;
			}
		} else { // 转出账户是非监管的
			if (isGeneralAccount(inAccount)) {// 转入账户是一般的
				if (isSupervisionAccount(inAccount)) {// 转入账户是监管的
					if (isOwnOrSupervisedBySelf(customer, inAccount)) {
						return TradeRuleResult.ACCEPT;
					} else {
						return TradeRuleResult.REJECT;
					}
				} else { // 转入账户是非监管的
					return TradeRuleResult.ACCEPT;
				}
			} else { // 转入账户是专用的
				return TradeRuleResult.REJECT;
			}
		}
	}

	/**
	 * cana的转账规则
	 */
	private TradeRuleResult canaTransferRule(User customer, Account outAccount, Account inAccount) {
		// 一般、非监管可转
		if (isGeneralAccount(inAccount) && !isSupervisionAccount(inAccount)) {
			return TradeRuleResult.ACCEPT;
		} else {
			return TradeRuleResult.REJECT;
		}
	}
	
	/**
	 * 核心企业的转账规则
	 * @deprecated 2016-09-08 修改核心企业可以转到任何人的账号中
	 */
	private TradeRuleResult coreCompanyTransferRule(User customer, Account outAccount, Account inAccount) {
		// 转入账户必须为一般、非监管
		if (isGeneralAccount(inAccount) && !isSupervisionAccount(inAccount) && customer.getId().equals(inAccount.getCompanyId())) {
			return TradeRuleResult.ACCEPT;
		} else {
			return TradeRuleResult.REJECT;
		}
	}

	/**
	 * 检查是否可以对转出账户做转账 转出账户必需是自己的，另外融资商可以转与自己有关的保理商监管户
	 */
	private boolean checkAccessForTransferOutAccount(User customer, Account outAccount) {
		if (UserType.FINACE.name().equals(customer.getUserType())) {
			if (!customer.getId().equals(outAccount.getCompanyId())
					&& !customer.getId().equals(outAccount.getSupervisorId())) {
				return false;
			}
		} else {
			if (!customer.getId().equals(outAccount.getCompanyId())) {
				return false;
			}
		}

        // 转出账户状态必须是正常
        if (!AccountStatus.NORMAL.name().equals(outAccount.getAccountStatus())) {
            return false;
        }
		return true;
	}

	@Override
	public TradeRuleResult checkTransferAuthority(User customer, Account outAccount) {
		if (!checkAccessForTransferOutAccount(customer, outAccount)) {
			return TradeRuleResult.REJECT;
		}
		return TradeRuleResult.ACCEPT;
	}
	
	@Override
	public TradeRuleResult checkTransferAuthority(User customer, Account outAccount, Account inAccount) {
		if (!checkAccessForTransferOutAccount(customer, outAccount)) {
			return TradeRuleResult.REJECT;
		}

		// 转入账户规则
		if (UserType.CANA.name().equals(customer.getUserType())) {
			return canaTransferRule(customer, outAccount, inAccount);
		} else if (UserType.FACTOR.name().equals(customer.getUserType())) {
			return factorTransferRule(customer, outAccount, inAccount);
		} else if (UserType.FINACE.name().equals(customer.getUserType())) {
			return finaceTransferRule(customer, outAccount, inAccount);
		} else if (UserType.CORECOMPANY.name().equals(customer.getUserType())){
			return TradeRuleResult.ACCEPT;
//			return coreCompanyTransferRule(customer, outAccount, inAccount);
		} else {
			return TradeRuleResult.REJECT;
		}
	}

	@Override
	public TradeRuleResult checkWithdrawAuthority(User customer, Account account) {
		if (!isOwnerAccount(customer, account)) {// 只能从自己的帐号提现
			return TradeRuleResult.REJECT;
		}
		switch (UserType.valueOf(customer.getUserType())) {
		case CANA:
			if (isGeneralAccount(account)) {// 自己的一般账户
				return TradeRuleResult.ACCEPT;
			}
			return TradeRuleResult.REJECT;
		case FACTOR:
			if (isGeneralAccount(account)) {// 自己的一般账户
			    if (isSupervisionAccount(account)) {// 自己的监管账户
	                return TradeRuleResult.NEED_AUDIT;
	            }
				return TradeRuleResult.ACCEPT;
			}
			return TradeRuleResult.REJECT;
		case FINACE:
		    if (isGeneralAccount(account)) {// 自己的一般账户
		        if (isSupervisionAccount(account)) {
		            return TradeRuleResult.NEED_AUDIT;
		        } else {
		            return TradeRuleResult.ACCEPT;
		        }
		    } else {
		        if (isAccumulationAccount(account)) {
		            return TradeRuleResult.REJECT;
		        } else {
		            return TradeRuleResult.ACCEPT;
		        }
		    }
		case CORECOMPANY:
			if (isGeneralAccount(account)) {
				return TradeRuleResult.ACCEPT;
			}
			return TradeRuleResult.REJECT;
		default:
			return TradeRuleResult.REJECT;
		}
	}

	@Override
	public void checkQueryBalanceAuthority(String customerId, List<String> accountNos) {
		User user = customerTransactionService.findUserById(customerId);
		if (UserType.CANA.name().equals(user.getUserType())) {
			return;
		}
		AccountExample ex = new AccountExample();
		ex.createCriteria().andAccountNoIn(accountNos);
		List<Account> accounts = accountMapper.selectByExample(ex);
		if(CollectionUtils.isEmpty(accounts)){
			logger.error("帐号不存在，操作非法！");
			throw WebException.instance("帐号不存在，操作非法！");
		}
		for (Account account : accounts) {
			if (!customerId.equals(account.getCompanyId()) && !customerId.equals(account.getSupervisorId())) {
				logger.error("对帐号：{} 没有操作权限，操作非法！", account);
				throw WebException.instance("对账号没有操作权限，操作非法！");
			}
		}
	}

	@Override
	public AccountApply checkAuditAgentApplyAuthority(AccountApplyAuditDetail auditDetail) {
		AccountApply accountApply = accountTableLockMapper.lockAccountApplyById(auditDetail.getAccountApplyId());
		if (null == accountApply) {
			logger.error("开户审核不存在，审核失败。");
			throw WebException.instance("开户审核不存在，审核失败。");
		}
		if (!AccountApplyStatus.PENDINGAUDIT.name().equals(accountApply.getApplyStatus())) {
			logger.error("重复提交，审核失败。");
			throw WebException.instance("重复提交，审核失败。");
		}
//		User finaceUser = customerTransactionService.findCustomerByCompanyName(accountApply.getCompanyName(),UserType.FINACE);
//		if (finaceUser != null) {
//			UserType userType = UserType.valueOf(finaceUser.getUserType());
//			if (userType != UserType.FINACE) {
//				logger.error("企业[{}]已经注册为[{}]，审核失败", finaceUser.getCompanyName(), userType.desc());
//				throw WebException.instance("企业[" + finaceUser.getCompanyName() + "]已经注册为" + userType.desc() + "，审核失败");
//			}
//		}
		return accountApply;
	}
}
