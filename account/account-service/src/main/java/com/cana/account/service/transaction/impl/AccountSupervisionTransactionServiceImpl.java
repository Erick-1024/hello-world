package com.cana.account.service.transaction.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.account.dao.mapper.AccountTableLockMapper;
import com.cana.account.dao.mapper.gen.AccountMapper;
import com.cana.account.dao.mapper.gen.AccountSupervisionMapper;
import com.cana.account.dao.mapper.gen.AccountTradeApplyMapper;
import com.cana.account.dao.po.Account;
import com.cana.account.dao.po.AccountExample;
import com.cana.account.dao.po.AccountSupervision;
import com.cana.account.dao.po.AccountTradeApply;
import com.cana.account.service.converter.AccountTradeApplyConverter;
import com.cana.account.service.transaction.IAccountSupervisionTransactionService;
import com.cana.account.service.transaction.IAccountTransactionService;
import com.cana.account.service.transaction.ICustomerTransactionService;
import com.cana.account.service.utils.AccountIDGenerator;
import com.cana.member.dao.po.User;
import com.cana.vbam.common.account.consts.AccountConsts;
import com.cana.vbam.common.account.dto.AccountSupervisionCreateDTO;
import com.cana.vbam.common.account.dto.AccountTradeApplyDTO;
import com.cana.vbam.common.account.enums.AccountAccumulationStatus;
import com.cana.vbam.common.account.enums.AccountStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountTradeApplyStatus;
import com.cana.vbam.common.account.enums.AccountTradeApplyType;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.member.enums.user.UserType;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.travelzen.framework.core.exception.WebException;

@Service
public class AccountSupervisionTransactionServiceImpl implements IAccountSupervisionTransactionService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ICustomerTransactionService customerTransactionService;
    @Resource
    private AccountTableLockMapper accountTableLockMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AccountSupervisionMapper accountSupervisionMapper;
    @Resource
    private AccountTradeApplyMapper accountTradeApplyMapper;
    @Resource
    private IAccountTransactionService accountTransactionService;
    @Resource
    private AccountTradeApplyConverter accountTradeApplyConverter;

    @Override
    public String createSupervision(String userId,
            AccountSupervisionCreateDTO createDTO) {
        String customerId = customerTransactionService.getCustomerIdByUserId(userId);
        User customer = customerTransactionService.checkCustomerIsValid(customerId);
        User supervisionCustomer = new User();
        logger.info("当前用户的客户类型是:{}",customer.getUserType());
        if(UserType.FACTOR.name().equals(customer.getUserType()))
        	supervisionCustomer = customerTransactionService.findCustomerByCompanyNameAndUserType(createDTO.getSupervisionCompanyName(),UserType.FINACE);
        if(UserType.FINACE.name().equals(customer.getUserType()))
        	supervisionCustomer = customerTransactionService.findCustomerByCompanyNameAndUserType(createDTO.getSupervisionCompanyName(),UserType.FACTOR);
        customerTransactionService.checkCustomerIsValid(supervisionCustomer);
        checkCustomerTypeIsValidForCreateSupervision(customer, supervisionCustomer);
        Account majorAccount = accountTableLockMapper.lockAccountById(createDTO.getSupervisionAccountId());
        checkMajorAccountCanCreateSupervision(customer, supervisionCustomer, majorAccount);
        List<Account> subAccounts = null;
        if (CollectionUtils.isNotEmpty(createDTO.getSpecialAccountIds())) {
            subAccounts = lockAccountsByIds(createDTO.getSpecialAccountIds());
            checkSubAccountCanCreateSupervision(customer, supervisionCustomer, subAccounts);
        }
        updateAccountStatus(majorAccount, subAccounts, AccountStatus.HANDLING);
        return insertSupervisionCreateApply(customer, supervisionCustomer.getId(), createDTO, majorAccount);
    }
    
    private void checkCustomerTypeIsValidForCreateSupervision(User customer, User supervisionCustomer) {
        if (customer.getUserType().equals(supervisionCustomer.getUserType())
                || UserType.CANA.name().equals(customer.getUserType())
                || UserType.CANA.name().equals(supervisionCustomer.getUserType())
                || UserType.CORECOMPANY.name().equals(customer.getUserType())
                || UserType.CORECOMPANY.name().equals(supervisionCustomer.getUserType()))
            throw WebException.instance("客户类型错误，不能创建监管关系");
    }

    @Override
    public String removeSupervision(String userId,
            List<String> accountIds) {
        String customerId = customerTransactionService.getCustomerIdByUserId(userId);
        User customer = customerTransactionService.checkCustomerIsValid(customerId);
        String majorAccountId = getMajorAccountId(accountIds);
        Account majorAccount = accountTableLockMapper.lockAccountById(majorAccountId);
        List<Account> subAccounts = accountTableLockMapper.lockAccountByAccumulationId(majorAccountId);
        checkAccountIdsIsValid(accountIds, majorAccount, subAccounts);
        checkAccountsCanRemoveSupervision(customer, majorAccount, subAccounts);
        updateAccountStatus(majorAccount, subAccounts, AccountStatus.HANDLING);
        AccountTradeApply apply = insertSupervisionRemoveApply(customer, majorAccount, subAccounts, accountIds);
        if (UserType.FACTOR.name().equals(customer.getUserType())) {    //保理商解除时，自动通过审核
            updateSupervisionApplyAuditInfo(apply, userId, true, "系统自动审核通过");
            auditSupervisionRemoveApply(majorAccount, apply, true);
        }
        return apply.getId();
    }

    private void checkAccountsCanRemoveSupervision(User customer, Account majorAccount, List<Account> subAccounts) {
        checkAccountsCanRemoveSupervision(customer, majorAccount);
        if (CollectionUtils.isNotEmpty(subAccounts)) {
            for (Account subAccount : subAccounts) {
                checkAccountsCanRemoveSupervision(customer, subAccount);
            }
        }
    }
    private void checkAccountsCanRemoveSupervision(User customer, Account account) {
        AccountStatus accountStatus = AccountStatus.valueOf(account.getAccountStatus());
        if (!customer.getId().equals(account.getCompanyId())
                && !customer.getId().equals(account.getSupervisorId()))
                throw WebException.instance("无权解除账户" + account.getAccountNo() + "的监管关系");
        if (!AccountStatus.NORMAL.equals(accountStatus)) 
            throw WebException.instance("账户" + account.getAccountNo()
                + "状态为" + accountStatus.desc() + "，不能解除监管关系");
        if (AccountSupervisionStatus.NO_SUPERVISION.name().equals(account.getSupervisionStatus()))
            throw WebException.instance("账户" + account.getAccountNo()
                + "状态为" + AccountSupervisionStatus.NO_SUPERVISION.desc() + "，不能解除监管关系");
    }

    /** 检查accountIds是不是 majorAccount 和 subAccounts 的子集 */
    private void checkAccountIdsIsValid(List<String> accountIds, Account majorAccount, List<Account> subAccounts) {
        List<String> ids = Lists.newArrayList();
        ids.add(majorAccount.getId());
        if (CollectionUtils.isNotEmpty(subAccounts)) {
            for (Account sub : subAccounts) {
                ids.add(sub.getId());
            }
        }
        for (String id : accountIds) {
            if (!ids.contains(id)) {
                logger.info("请求解除监管关系的ID列表无效：" + accountIds.toString());
                throw WebException.instance("请求解除监管关系的ID列表无效");
            }
        }
    }

    private String getMajorAccountId(List<String> accountIds) {
        checkAccountIdsNotEmptyAndDuplicate(accountIds);
        Account account = accountMapper.selectByPrimaryKey(accountIds.get(0));
        if (account == null)
            throw WebException.instance("账户[id:" + accountIds.get(0) + "]不存在");
        String majorId = account.getId();
        if (StringUtils.isNotBlank(account.getAccumulationId()))
            majorId = account.getAccumulationId();
        return majorId;
    }

    private void checkSubAccountCanCreateSupervision(User customer, User supervisionCustomer, List<Account> subAccounts) {
        String finaceName = "";
        if (UserType.FINACE.name().equals(customer.getUserType())) {
            finaceName = customer.getCompanyName();
        } else if (UserType.FINACE.name().equals(supervisionCustomer.getUserType())) {
            finaceName = supervisionCustomer.getCompanyName();
        } else {
            throw WebException.instance("客户类型错误");
        }
        for (Account subAccount : subAccounts) {
            checkAccountStatusForCreateSupervision(subAccount);
            if (!AccountType.SPECIAL.name().equals(subAccount.getAccountType()))
                throw WebException.instance("账户" + subAccount.getAccountNo() + "不是专有账户");
            if (!finaceName.equals(subAccount.getCompanyName()))
                throw WebException.instance("账户" + subAccount.getAccountNo() + "不属于" + finaceName);
        }
    }
    private void checkMajorAccountCanCreateSupervision(User customer, User supervisionCustomer, Account majorAccount) {
        if (majorAccount == null)
            throw WebException.instance("监管账户ID错误");
        checkAccountStatusForCreateSupervision(majorAccount);
        if (!AccountType.GENERAL.name().equals(majorAccount.getAccountType()))
            throw WebException.instance("监管账户类型为" + AccountType.SPECIAL.desc() + "，不能新建监管关系");
        if (!customer.getId().equals(majorAccount.getCompanyId())
                && !supervisionCustomer.getId().equals(majorAccount.getCompanyId()))
            throw WebException.instance("监管账户不属于当前客户和对方客户，不能新建监管关系");
        if (BooleanUtils.isTrue(majorAccount.getIsTransferInAccount())) {
            throw WebException.instance("监管账户不能是回款账户");
        }
    }
    private void checkAccountStatusForCreateSupervision(Account account) {
        AccountStatus status = AccountStatus.valueOf(account.getAccountStatus());
        if (!AccountStatus.NORMAL.equals(status))
            throw WebException.instance("账户" + account.getAccountNo()
                + "状态为" + status.desc() + "，不能新建监管关系");
        if (AccountSupervisionStatus.HAVE_SUPERVISION.name().equals(account.getSupervisionStatus()))
            throw WebException.instance("账户" + account.getAccountNo()
                + "已经处于监管状态，不能新建监管关系");
        if (!AccountAccumulationStatus.NO_ACCUMULATION.name().equals(account.getAccumulationStatus()))
            throw WebException.instance("账户" + account.getAccountNo()
                + "已经处于归集状态，不能新建监管关系");
    }

    private List<Account> lockAccountsByIds(List<String> accountIds) {
        checkAccountIdsNotEmptyAndDuplicate(accountIds);
        List<Account> subAccounts = Lists.newArrayList();
        for (String id : accountIds) {
            Account subAccount = accountTableLockMapper.lockAccountById(id);
            if (subAccount == null)
                throw WebException.instance("专用账户ID错误");
            subAccounts.add(subAccount);
        }
        return subAccounts;
    }

    private void checkAccountIdsNotEmptyAndDuplicate(List<String> accountIds) {
        if (CollectionUtils.isEmpty(accountIds))
            throw WebException.instance("账户列表不能为空");
        Set<String> accountIdSet = Sets.newHashSet();
        for (String id : accountIds) {
            if (accountIdSet.contains(id)) {
                throw WebException.instance("账户列表不能重复");
            }
            accountIdSet.add(id);
        }
    }

    private void updateAccountStatus(Account majorAccount, List<Account> subAccounts, AccountStatus status) {
        Account updateAccount = new Account();
        updateAccount.setAccountStatus(status.name());
        updateAccount.setUpdateTime(new Date());
        AccountExample example = new AccountExample();

        List<String> pendingUpdateIds = Lists.newArrayList();
        if (majorAccount != null) {
            pendingUpdateIds.add(majorAccount.getId());
            majorAccount.setAccountStatus(status.name());
            majorAccount.setUpdateTime(updateAccount.getUpdateTime());
        }
        if (CollectionUtils.isNotEmpty(subAccounts)) {
            for (Account account : subAccounts) {
                pendingUpdateIds.add(account.getId());
                account.setAccountStatus(status.name());
                account.setUpdateTime(updateAccount.getUpdateTime());
            }
        }
        if (pendingUpdateIds.size() == 0)
            return;
        example.createCriteria().andIdIn(pendingUpdateIds);
        int count = accountMapper.updateByExampleSelective(updateAccount, example);
        if (count != pendingUpdateIds.size()) {
            logger.error("更新数据库异常，期望更新条数：{}，实际更新条数：{}", pendingUpdateIds.size(), count);
            throw WebException.instance("更新数据库异常");
        }
    }

    private String insertSupervisionCreateApply(User customer, String oppositeCustomerId,
            AccountSupervisionCreateDTO createDTO, Account majorAccount) {
        AccountTradeApply apply = new AccountTradeApply();
        apply.setId(AccountIDGenerator.generateAccountTradeApplyId());
        apply.setTradeType(AccountTradeApplyType.CREATE_SUPERVISION.name());
        apply.setStatus(AccountTradeApplyStatus.PENDINGAUDIT.name());
        apply.setApplyCompanyId(customer.getId());
        apply.setApplyCompanyName(customer.getCompanyName());
        apply.setAuditCompanyId(oppositeCustomerId);
        apply.setAccountId(majorAccount.getId());
        apply.setAccountNo(majorAccount.getAccountNo());
        apply.setAccountName(majorAccount.getCompanyName());
        apply.setIsDefaultRepayment(createDTO.getIsDefaultRepayment());
        apply.setCreateTime(new Date());
        if (CollectionUtils.isNotEmpty(createDTO.getSpecialAccountIds())) {
            apply.setSpecialAccountIds(StringUtils.join(createDTO.getSpecialAccountIds(), AccountConsts.SEMICOLON));
        }
        accountTradeApplyMapper.insert(apply);
        return apply.getId();
    }

    private AccountTradeApply insertSupervisionRemoveApply(User customer, Account majorAccount,
            List<Account> subAccounts, List<String> removeAccountIds) {
        AccountTradeApply apply = new AccountTradeApply();
        apply.setId(AccountIDGenerator.generateAccountTradeApplyId());
        apply.setTradeType(AccountTradeApplyType.REMOVE_SUPERVISION.name());
        apply.setStatus(AccountTradeApplyStatus.PENDINGAUDIT.name());
        apply.setApplyCompanyId(customer.getId());
        apply.setApplyCompanyName(customer.getCompanyName());
        if (customer.getId().equals(majorAccount.getCompanyId())) {
            apply.setAuditCompanyId(majorAccount.getSupervisorId());
        } else {
            apply.setAuditCompanyId(majorAccount.getCompanyId());
        }
        apply.setAccountId(majorAccount.getId());
        apply.setAccountNo(majorAccount.getAccountNo());
        apply.setAccountName(majorAccount.getCompanyName());
        if (CollectionUtils.isNotEmpty(subAccounts)) {
            List<String> specialAccountIds = Lists.newArrayList();
            for (Account subAccount : subAccounts) {
                specialAccountIds.add(subAccount.getId());
            }
            apply.setSpecialAccountIds(StringUtils.join(specialAccountIds, AccountConsts.SEMICOLON));
        }
        if (removeAccountIds.contains(majorAccount.getId())) {
            apply.setIsRemoveAllSupervision(true);
        } else {
            apply.setIsRemoveAllSupervision(false);
            apply.setRemoveSpecialAccountIds(StringUtils.join(removeAccountIds, AccountConsts.SEMICOLON));
        }
        apply.setCreateTime(new Date());
        accountTradeApplyMapper.insert(apply);
        return apply;
    }

    
    @Override
    public boolean auditSupervision(String auditUserId,
            String supervisionApplyId, boolean isAgree,
            String message) {
        String customerId = customerTransactionService.getCustomerIdByUserId(auditUserId);
        User auditCustomer = customerTransactionService.checkCustomerIsValid(customerId);
        AccountTradeApply apply = accountTableLockMapper.lockAccountTradeApplyById(supervisionApplyId);
        checkCustomerCanAuditSupervisionApply(auditCustomer, apply);
        updateSupervisionApplyAuditInfo(apply, auditUserId, isAgree, message);

        String majorAccountId = getMajorAccountIdFromSupervisionApply(apply);
        Account majorAccount = accountTableLockMapper.lockAccountById(majorAccountId);
        if (AccountTradeApplyType.CREATE_SUPERVISION.name().equals(apply.getTradeType())) {
            auditSupervisionCreateApply(auditCustomer, majorAccount, apply, isAgree);
        } else {
            auditSupervisionRemoveApply(majorAccount, apply, isAgree);
        }
        return true;
    }

    /** 审核解除监管申请 */
    private void auditSupervisionRemoveApply(Account majorAccount, AccountTradeApply apply, boolean isAgree) {
        List<Account> subAccounts = accountTableLockMapper.lockAccountByAccumulationId(majorAccount.getId());
        if (isAgree) {
            boolean removeAllSupervision = apply.getIsRemoveAllSupervision() == null ? false : apply.getIsRemoveAllSupervision();
            List<String> removeSubAccountIds = Lists.newArrayList();
            if (StringUtils.isNotBlank(apply.getRemoveSpecialAccountIds())) {
                removeSubAccountIds = Lists.newArrayList(apply.getRemoveSpecialAccountIds().split(AccountConsts.SEMICOLON));
            }
            removeSupervision(majorAccount, subAccounts, removeAllSupervision, removeSubAccountIds);
        }
        updateAccountStatus(majorAccount, subAccounts, AccountStatus.NORMAL);
    }

    /** 审核新建监管申请 */
    private void auditSupervisionCreateApply(User auditCustomer, Account majorAccount, AccountTradeApply apply, boolean isAgree) {
        List<Account> subAccounts = null;
        if (StringUtils.isNotBlank(apply.getSpecialAccountIds())) {
            List<String> subAccountIds = Lists.newArrayList(apply.getSpecialAccountIds().split(AccountConsts.SEMICOLON));
            subAccounts = accountTableLockMapper.lockAccountByIds(subAccountIds);
        }
        if (isAgree) {
            User applyCustomer = customerTransactionService.checkCustomerIsValid(apply.getApplyCompanyId());
            createSupervision(applyCustomer, auditCustomer, majorAccount, subAccounts);
            if (apply.getIsDefaultRepayment()) {
                accountTransactionService.updateAccountIsDefaultRepayment(majorAccount);
            }
        }
        updateAccountStatus(majorAccount, subAccounts, AccountStatus.NORMAL);
    }

    private void removeSupervision(Account majorAccount, List<Account> subAccounts,
            boolean removeAllSupervision, List<String> removeSubAccountIds) {

        if (removeAllSupervision) {
            AccountSupervision majorSupervision = accountSupervisionMapper
                    .selectByPrimaryKey(majorAccount.getAccountSupervisionId());
            majorSupervision.setSupervisionEndTime(new Date());
            accountSupervisionMapper.updateByPrimaryKeySelective(majorSupervision);
            
            majorAccount.setAccountSupervisionId(null);
            majorAccount.setSupervisorId(null);
            majorAccount.setSupervisorName(null);
            majorAccount.setAccumulationStatus(AccountAccumulationStatus.NO_ACCUMULATION.name());
            majorAccount.setSupervisionStatus(AccountSupervisionStatus.NO_SUPERVISION.name());
            majorAccount.setIsDefaultRepayment(false);
            accountMapper.updateByPrimaryKey(majorAccount);
        }
        if (CollectionUtils.isNotEmpty(subAccounts)) {
            for (Account subAccount : subAccounts) {
                if (removeAllSupervision || removeSubAccountIds.contains(subAccount.getId())) {
                    AccountSupervision subSupervision = accountSupervisionMapper
                            .selectByPrimaryKey(subAccount.getAccountSupervisionId());
                    subSupervision.setSupervisionEndTime(new Date());
                    accountSupervisionMapper.updateByPrimaryKeySelective(subSupervision);
                    
                    subAccount.setAccountSupervisionId(null);
                    subAccount.setSupervisorId(null);
                    subAccount.setSupervisorName(null);
                    subAccount.setAccumulationStatus(AccountAccumulationStatus.NO_ACCUMULATION.name());
                    subAccount.setSupervisionStatus(AccountSupervisionStatus.NO_SUPERVISION.name());
                    subAccount.setAccumulationId(null);
                    accountMapper.updateByPrimaryKey(subAccount);
                }
            }
            if (!removeAllSupervision && removeSubAccountIds.size() == subAccounts.size()) {
                majorAccount.setAccumulationStatus(AccountAccumulationStatus.NO_ACCUMULATION.name());
                accountMapper.updateByPrimaryKey(majorAccount);
            }
        }
    }


    @Override
    public void createSupervision(User applyCustomer, User oppositeCustomer,
            Account majorAccount, List<Account> subAccounts) {
        if (majorAccount.getCompanyId().equals(applyCustomer.getId())) {
            majorAccount.setSupervisorId(oppositeCustomer.getId());
            majorAccount.setSupervisorName(oppositeCustomer.getCompanyName());
        } else {
            majorAccount.setSupervisorId(applyCustomer.getId());
            majorAccount.setSupervisorName(applyCustomer.getCompanyName());
        }
        if (CollectionUtils.isNotEmpty(subAccounts)) {
            majorAccount.setAccumulationStatus(AccountAccumulationStatus.HAVE_ACCUMULATION.name());
        }
        majorAccount.setSupervisionStatus(AccountSupervisionStatus.HAVE_SUPERVISION.name());
        String majorSupervisionId = this.insertAccountSupervisionRecord(majorAccount);
        majorAccount.setAccountSupervisionId(majorSupervisionId);
        majorAccount.setUpdateTime(new Date());
        accountMapper.updateByPrimaryKeySelective(majorAccount);
        if (CollectionUtils.isNotEmpty(subAccounts)) {
            for (Account subAccount : subAccounts) {
                if (subAccount.getCompanyId().equals(applyCustomer.getId())) {
                    subAccount.setSupervisorId(oppositeCustomer.getId());
                    subAccount.setSupervisorName(oppositeCustomer.getCompanyName());
                } else {
                    subAccount.setSupervisorId(applyCustomer.getId());
                    subAccount.setSupervisorName(applyCustomer.getCompanyName());
                }
                subAccount.setAccumulationStatus(AccountAccumulationStatus.BE_ACCUMULATED.name());
                subAccount.setSupervisionStatus(AccountSupervisionStatus.HAVE_SUPERVISION.name());
                String subSupervisionId = this.insertAccountSupervisionRecord(subAccount);
                subAccount.setAccountSupervisionId(subSupervisionId);
                subAccount.setAccumulationId(majorAccount.getId());
                subAccount.setUpdateTime(new Date());
                accountMapper.updateByPrimaryKeySelective(subAccount);
            }
        }
    }

    /** 根据已经建立好监管关系的account，插入一条监管关系历史记录 */
    private String insertAccountSupervisionRecord(Account account) {
        AccountSupervision supervision = new AccountSupervision();
        String supervisionId = AccountIDGenerator.generateAccountSupervisionId();
        supervision.setId(supervisionId);
        supervision.setAccountId(account.getId());
        supervision.setUserType(account.getUserType());
        supervision.setCompanyId(account.getCompanyId());
        supervision.setCompanyName(account.getCompanyName());
        supervision.setSupervisorId(account.getSupervisorId());
        supervision.setSupervisorName(account.getSupervisorName());
        supervision.setSupervisionStartTime(new Date());
        accountSupervisionMapper.insert(supervision);
        return supervisionId;
    }

    /** 检查当前客户可以对监管申请做审核操作 */
    private void checkCustomerCanAuditSupervisionApply(User customer, AccountTradeApply apply) {
        if (!customer.getId().equals(apply.getAuditCompanyId()))
            throw WebException.instance("无权审核该申请");
        if (!AccountTradeApplyStatus.PENDINGAUDIT.name().equals(apply.getStatus()))
            throw WebException.instance("已经审核过了");
        if (!AccountTradeApplyType.CREATE_SUPERVISION.name().equals(apply.getTradeType())
                && !AccountTradeApplyType.REMOVE_SUPERVISION.name().equals(apply.getTradeType())) {
            throw WebException.instance("错误的审核类型");
        }
    }

    /** 从监管申请中获取主账号ID，如果是解除子账户监管申请，则从数据库中获取主账户ID */
    private String getMajorAccountIdFromSupervisionApply(AccountTradeApply apply) {
        if (StringUtils.isBlank(apply.getAccountId())) {
            throw WebException.instance("无效申请");
        }
        return apply.getAccountId();
    }

    /** 更新监管申请记录的审核信息 */
    private void updateSupervisionApplyAuditInfo(AccountTradeApply apply,
            String auditUserId, boolean isAgree, String message) {
        if (isAgree) {
            apply.setStatus(AccountTradeApplyStatus.ACCEPTED.name());
        } else {
            apply.setStatus(AccountTradeApplyStatus.REJECTED.name());
        }
        apply.setAuditUserId(auditUserId);
        apply.setAuditMessage(message);
        apply.setAuditTime(new Date());
        accountTradeApplyMapper.updateByPrimaryKeySelective(apply);
    }

    @Override
    public AccountTradeApplyDTO getSupervisionApply(String userId,
            String supervisionApplyId) {
        String customerId = customerTransactionService.getCustomerIdByUserId(userId);
        User customer = customerTransactionService.checkCustomerIsValid(customerId);
        AccountTradeApply apply = accountTradeApplyMapper.selectByPrimaryKey(supervisionApplyId);
        return accountTradeApplyConverter.convertSupervisionApplyDetail(customer, apply);
    }

    @Override
    public boolean createSupervisionWithoutAudit(String applyUserId, String accountNo, String supervisionCustomerId) {
        String customerId = customerTransactionService.getCustomerIdByUserId(applyUserId);
        User customer = customerTransactionService.checkCustomerIsValid(customerId);
        User supervisionCustomer = customerTransactionService.checkCustomerIsValid(supervisionCustomerId);
        checkCustomerTypeIsValidForCreateSupervision(customer, supervisionCustomer);
        Account majorAccount = accountTableLockMapper.lockAccountByAccountNo(accountNo);
        checkMajorAccountCanCreateSupervision(customer, supervisionCustomer, majorAccount);
        createSupervision(customer, supervisionCustomer, majorAccount, null);
        return true;
    }
}
