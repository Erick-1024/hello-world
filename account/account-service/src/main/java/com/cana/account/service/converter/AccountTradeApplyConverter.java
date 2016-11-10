package com.cana.account.service.converter;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cana.account.dao.mapper.gen.AccountMapper;
import com.cana.account.dao.po.Account;
import com.cana.account.dao.po.AccountExample;
import com.cana.account.dao.po.AccountTradeApply;
import com.cana.member.dao.mapper.gen.UserMapper;
import com.cana.member.dao.po.User;
import com.cana.vbam.common.account.consts.AccountConsts;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.dto.AccountTradeApplyDTO;
import com.cana.vbam.common.account.enums.AccountAccumulationStatus;
import com.cana.vbam.common.account.enums.AccountStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountTradeApplyStatus;
import com.cana.vbam.common.account.enums.AccountTradeApplyType;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.member.enums.user.UserType;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.util.MoneyUtil;

@Component
public class AccountTradeApplyConverter {
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private AccountConverter accountConverter;

    /**
     * 交易申请转换类
     * customer，当前登录客户
     */
    public List<AccountTradeApplyDTO> convertBaseInfos(User customer, List<AccountTradeApply> applys) {
        List<AccountTradeApplyDTO> applyDTOs = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(applys)) {
            for (AccountTradeApply apply : applys) {
                AccountTradeApplyDTO dto = new AccountTradeApplyDTO();
                dto.setId(apply.getId());
                dto.setAccountId(apply.getAccountId());
                dto.setAccountNo(apply.getAccountNo());
                dto.setAccountName(apply.getAccountName());
                AccountTradeApplyType applyType = AccountTradeApplyType.valueOf(apply.getTradeType());
                dto.setApplyType(applyType);
                dto.setApplyTypeDesc(applyType.desc());
                AccountTradeApplyStatus applyStatus = AccountTradeApplyStatus.valueOf(apply.getStatus());
                dto.setApplyStatus(applyStatus);
                dto.setApplyStatusDesc(applyStatus.getDesc());
                dto.setCreateTime(apply.getCreateTime());
                dto.setAuditMessage(apply.getAuditMessage());
                dto.setApplyCompanyId(apply.getApplyCompanyId());
                dto.setApplyCompanyName(apply.getApplyCompanyName());
                dto.setAuditCompanyId(apply.getAuditCompanyId());
                dto.setAuditUserId(apply.getAuditUserId());
                dto.setAuditTime(apply.getAuditTime());
                if (customer != null
                        && customer.getId().equals(apply.getAuditCompanyId())
                        && AccountTradeApplyStatus.PENDINGAUDIT.equals(applyStatus)) {
                    dto.setAllowAudit(true);
                }
                applyDTOs.add(dto);
            }
        }
        return applyDTOs;
    }

    /**
     * 转换交易申请详情，转帐和提现通用
     * @param customer
     * @param apply
     * @param account
     * @return
     */
	public AccountTradeApplyDTO convertTradeApplyDetail(User customer, AccountTradeApply apply) {
	    if (apply == null) {
	        return null;
	    }
		AccountTradeApplyDTO dto = convertBaseInfos(customer, Lists.newArrayList(apply)).get(0);
		dto.setOppositeAccountId(apply.getOppositeAccountId());
		dto.setOppositeAccountName(apply.getOppositeAccountName());
		dto.setOppositeAccountNo(apply.getOppositeAccountNo());
		dto.setWithdraw_bank(apply.getWithdrawBank());
		dto.setWithdraw_bank_address(apply.getWithdrawBankAddress());
		dto.setAmount(MoneyUtil.cent2Yuan(apply.getAmount() == null ? 0L : apply.getAmount()));
		dto.setCharge(MoneyUtil.cent2Yuan(apply.getCharge() == null ? 0L : apply.getCharge()));
		dto.setRemark(apply.getRemark());
		Account account = accountMapper.selectByPrimaryKey(apply.getAccountId());
		convertSupervisionAndAccumulation(dto, apply, account);
		return dto;
	}
    
    /**
     * 转换监管申请
     * customer，当前登录客户
     */
    public AccountTradeApplyDTO convertSupervisionApplyDetail(User customer, AccountTradeApply apply) {
        if (apply == null) {
            return null;
        }
        if (!AccountTradeApplyType.CREATE_SUPERVISION.name().equals(apply.getTradeType())
                && !AccountTradeApplyType.REMOVE_SUPERVISION.name().equals(apply.getTradeType())) {
            return null;
        }
        AccountTradeApplyDTO dto = convertBaseInfos(customer, Lists.newArrayList(apply)).get(0);
        Account account = accountMapper.selectByPrimaryKey(apply.getAccountId());
        if (account.getCompanyId().equals(apply.getApplyCompanyId())) {
            User oppositeCustomer = userMapper.selectByPrimaryKey(apply.getAuditCompanyId());
            if (UserType.FACTOR.name().equals(account.getUserType())) {
                dto.setFactorName(account.getCompanyName());
                dto.setFinaceName(oppositeCustomer.getCompanyName());
            } else if (UserType.FINACE.name().equals(account.getUserType())) {
                dto.setFactorName(oppositeCustomer.getCompanyName());
                dto.setFinaceName(account.getCompanyName());
            }
        } else if (account.getCompanyId().equals(apply.getAuditCompanyId())) {
            if (UserType.FACTOR.name().equals(account.getUserType())) {
                dto.setFactorName(account.getCompanyName());
                dto.setFinaceName(apply.getApplyCompanyName());
            } else if (UserType.FINACE.name().equals(account.getUserType())) {
                dto.setFactorName(apply.getApplyCompanyName());
                dto.setFinaceName(account.getCompanyName());
            }
        }
        List<AccountDTO> specialAccountDTOs = Lists.newArrayList();
        if (StringUtils.isNotBlank(apply.getSpecialAccountIds())) {
            List<String> specialAccountIds = Lists.newArrayList(apply.getSpecialAccountIds().split(AccountConsts.SEMICOLON));
            AccountExample example = new AccountExample();
            example.createCriteria().andIdIn(specialAccountIds);
            List<Account> specialAccounts = accountMapper.selectByExample(example);
            for (Account specialAccount : specialAccounts) {
                specialAccountDTOs.add(convertAccount2DTO(specialAccount));
            }
            dto.setSpecialAccounts(specialAccountDTOs);
        }
        if (AccountTradeApplyType.CREATE_SUPERVISION.name().equals(apply.getTradeType())) {
            dto.setDefaultRepayment(BooleanUtils.isTrue(apply.getIsDefaultRepayment()));
        } else if (AccountTradeApplyType.REMOVE_SUPERVISION.name().equals(apply.getTradeType())) {
            dto.setDefaultRepayment(BooleanUtils.isTrue(account.getIsDefaultRepayment()));
            List<AccountDTO> removes = Lists.newArrayList();
            if (apply.getIsRemoveAllSupervision() != null && apply.getIsRemoveAllSupervision()) {
                removes.add(convertAccount2DTO(account));
                removes.addAll(specialAccountDTOs);
            } else {
                List<String> removeAccountIds = Lists.newArrayList(apply.getRemoveSpecialAccountIds().split(AccountConsts.SEMICOLON));
                for (AccountDTO specialAccount : specialAccountDTOs) {
                    if (removeAccountIds.contains(specialAccount.getAccountId())) {
                        removes.add(specialAccount);
                    }
                }
            }
            dto.setRemoveSupervisionAccounts(removes);
        }
        convertSupervisionAndAccumulation(dto,apply,account);
        return dto;
    }

    private void convertSupervisionAndAccumulation(AccountTradeApplyDTO dto, AccountTradeApply apply, Account account){
    	if (AccountTradeApplyStatus.PENDINGAUDIT.name().equals(apply.getStatus())) {
    		dto.setAccountType(AccountType.valueOf(account.getAccountType()));
            dto.setAccountTypeDesc(AccountType.valueOf(account.getAccountType()).desc());
            dto.setAccountStatusDesc(AccountStatus.valueOf(account.getAccountStatus()).desc());
            dto.setAccumulationStatus(AccountAccumulationStatus.valueOf(account.getAccumulationStatus()));
            dto.setAccumulationStatusDesc(AccountAccumulationStatus.valueOf(account.getAccumulationStatus()).desc());
            dto.setSupervisionStatus(AccountSupervisionStatus.valueOf(account.getSupervisionStatus()));
            dto.setSupervisionStatusDesc(AccountSupervisionStatus.valueOf(account.getSupervisionStatus()).desc());
            dto.setAccountSupervisionId(account.getAccountSupervisionId());
        }
    }
    
    private AccountDTO convertAccount2DTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setAccountId(account.getId());
        dto.setAccountNo(account.getAccountNo());
        dto.setAccountName(account.getCompanyName());
        dto.setAccountTypeDesc(AccountType.valueOf(account.getAccountType()).desc());
        dto.setAccountStatusDesc(AccountStatus.valueOf(account.getAccountStatus()).desc());
        dto.setBuyerName(account.getBuyerName());
        return dto;
    }
}
