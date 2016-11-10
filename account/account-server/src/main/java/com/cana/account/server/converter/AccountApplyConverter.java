package com.cana.account.server.converter;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cana.account.dao.mapper.gen.AccountAuditMapper;
import com.cana.account.dao.mapper.gen.AccountMapper;
import com.cana.account.dao.po.Account;
import com.cana.account.dao.po.AccountApply;
import com.cana.account.dao.po.AccountAudit;
import com.cana.account.dao.po.AccountAuditExample;
import com.cana.account.dao.po.AccountExample;
import com.cana.account.service.transaction.ICustomerTransactionService;
import com.cana.member.dao.mapper.gen.RoleMapper;
import com.cana.member.dao.mapper.gen.UserMapper;
import com.cana.member.dao.po.Role;
import com.cana.member.dao.po.User;
import com.cana.vbam.common.account.consts.AccountConsts;
import com.cana.vbam.common.account.dto.AccountApplyAuditDetail;
import com.cana.vbam.common.account.dto.AccountApplyDTO;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.enums.AccountApplyStatus;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.member.enums.user.UserStatus;
import com.cana.vbam.common.member.enums.user.UserType;
import com.google.common.collect.Lists;

/**
 * 账户申请转换
 * @author XuMeng
 *
 */
@Component
public class AccountApplyConverter {

    @Resource
    private AccountAuditMapper accountAuditMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
	private ICustomerTransactionService customerTransactionService;

    /**
     * 将开户申请转换为适用开户列表的数据结构
     */
    public List<AccountApplyDTO> convertForList(List<AccountApply> accountApplys) {
        if (accountApplys == null || accountApplys.isEmpty())
            return null;

        List<AccountApplyDTO> accountApplyDTOs = Lists.newArrayList();
        for (AccountApply accountApply : accountApplys) {
            AccountApplyDTO applyDTO = new AccountApplyDTO();
            fillCommonInfo(applyDTO, accountApply);
            accountApplyDTOs.add(applyDTO);
        }
        return accountApplyDTOs;
    }

    /**
     * 将开户申请转换为适用开户详情的数据结构
     */
    public AccountApplyDTO convertForDetail(AccountApply accountApply) {
        if (accountApply == null)
            return null;

        AccountApplyDTO applyDTO = new AccountApplyDTO();

        applyDTO.setAccountNumber(accountApply.getAccountNumber());
        applyDTO.setAccountType(AccountType.valueOf(accountApply.getAccountType()));
        if (StringUtils.isNotBlank(accountApply.getSupervisionAccountId())) {
            Account account = accountMapper.selectByPrimaryKey(accountApply.getSupervisionAccountId());
            if (account != null) {
                applyDTO.setSupervisionAccountId(account.getId());
                applyDTO.setSupervisionAccountNo(account.getAccountNo());
                applyDTO.setSupervisionAccountName(account.getCompanyName());
            }
        }
        if (StringUtils.isNotBlank(accountApply.getBuyerNames())) {
            String[] buyerNames = accountApply.getBuyerNames().split(AccountConsts.SEMICOLON);
            applyDTO.setBuyerNames(Lists.newArrayList(buyerNames));
        }
        applyDTO.setContactName(accountApply.getContactName());
        applyDTO.setContactJobTitle(accountApply.getContactJobTitle());
        applyDTO.setContactTel(accountApply.getContactTel());
        applyDTO.setContactMail(accountApply.getContactMail());
        applyDTO.setContactIdentityCardFrontMediaId(accountApply.getContactIdentityCardFrontMediaId());
        applyDTO.setContactIdentityCardBackMediaId(accountApply.getContactIdentityCardBackMediaId());
        applyDTO.setAuthorizationLetterId(accountApply.getAuthorizationLetterId());
        applyDTO.setOrganizationCode(accountApply.getOrganizationCode());
        applyDTO.setOrganizationCodeCertificateMediaId(accountApply.getOrganizationCodeCertificateMediaId());
        applyDTO.setBusinessLicenceCode(accountApply.getBusinessLicenceCode());
        applyDTO.setBusinessLicenceMediaId(accountApply.getBusinessLicenceMediaId());
        applyDTO.setLegalPersonIdentityCardFrontMediaId(accountApply.getLegalPersonIdentityCardFrontMediaId());
        applyDTO.setLegalPersonIdentityCardBackMediaId(accountApply.getLegalPersonIdentityCardBackMediaId());
        applyDTO.setTaxRegistrationCertificateCode(accountApply.getTaxRegistrationCertificateCode());
        applyDTO.setTaxRegistrationCertificateMediaId(accountApply.getTaxRegistrationCertificateMediaId());

        fillCommonInfo(applyDTO, accountApply);

        AccountApplyStatus status = AccountApplyStatus.valueOf(accountApply.getApplyStatus());
        if (AccountApplyStatus.ACCEPTED.equals(status)) {
            AccountExample example = new AccountExample();
            example.createCriteria().andAccountApplyIdEqualTo(accountApply.getId());
            List<Account> accounts = accountMapper.selectByExample(example);
            if (accounts != null) {
                List<AccountDTO> accountDTOs = Lists.newArrayList();
                for (Account account : accounts) {
                    AccountDTO accountDTO = new AccountDTO();
                    accountDTO.setAccountTypeDesc(AccountType.valueOf(account.getAccountType()).getDesc());
                    accountDTO.setAccountName(account.getCompanyName());
                    accountDTO.setAccountNo(account.getAccountNo());
                    accountDTO.setBuyerName(account.getBuyerName());
                    accountDTOs.add(accountDTO);
                }
                applyDTO.setAccounts(accountDTOs);
            }

            if (applyDTO.getAuditDetail() != null) {
                String roleId = applyDTO.getAuditDetail().getRoleId();
                if (StringUtils.isNotBlank(roleId)) {
                    Role role = roleMapper.selectByPrimaryKey(roleId);
                    if (role != null) {
                        applyDTO.getAuditDetail().setRoleName(role.getRoleName());
                    }
                }
            }
        }
        if (AccountApplyStatus.PENDINGAUDIT.equals(status)) {
        	User user = customerTransactionService.findCustomerByCompanyNameAndUserType(applyDTO.getCompanyName(),UserType.valueOf(accountApply.getUserType()));
        	if (user == null || UserStatus.PENDINGAUDIT.name().equals(user.getUserStatus())) {
        		applyDTO.setShowRoles(true);
        	}
        }
        return applyDTO;
    }

    /**
     * 填充公共信息
     */
    private void fillCommonInfo(AccountApplyDTO applyDTO,
            AccountApply accountApply) {
        UserType type = UserType.valueOf(accountApply.getUserType());
        applyDTO.setUserType(type);
        applyDTO.setUserTypeDesc(type.desc());
        applyDTO.setUsername(accountApply.getUsername());
        applyDTO.setCompanyName(accountApply.getCompanyName());
        applyDTO.setAgentCompanyName(accountApply.getAgentCompanyName());
        applyDTO.setId(accountApply.getId());
        applyDTO.setCreateTime(accountApply.getCreateTime());
        applyDTO.setUpdateTime(accountApply.getUpdateTime());

        AccountApplyStatus status = AccountApplyStatus.valueOf(accountApply.getApplyStatus());
        applyDTO.setApplyStatus(status);
        applyDTO.setAuditResultDesc(status.getAuditResult());
        applyDTO.setAuditStatusDesc(status.getAuditStatus());
        if (!AccountApplyStatus.PENDINGAUDIT.equals(status)) {
            fillAuditInfo(applyDTO, accountApply);
        }
    }

    private void fillAuditInfo(AccountApplyDTO applyDTO, AccountApply accountApply) {
        AccountAuditExample example = new AccountAuditExample();
        example.createCriteria().andAccountApplyIdEqualTo(accountApply.getId());
        List<AccountAudit> audits = accountAuditMapper.selectByExample(example);
        if (audits != null && audits.size() > 0) {
            AccountAudit audit = audits.get(0);
            applyDTO.setAuditorName(getUsernameById(audit.getAuditorId()));
            AccountApplyAuditDetail auditDetail = new AccountApplyAuditDetail();
            auditDetail.setAuditorId(audit.getAuditorId());
            auditDetail.setAccountApplyId(audit.getAccountApplyId());
            auditDetail.setAuditMessage(audit.getAuditMessage());
            auditDetail.setIntAccountAuditStatus(audit.getAuditStatus());
            auditDetail.setRoleId(audit.getRoleId());
            applyDTO.setAuditDetail(auditDetail);
        }
    }

    private String getUsernameById(String userId) {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        User user = userMapper.selectByPrimaryKey(userId);
        if (user != null) {
            return user.getUsername();
        }
        return null;
    }
}
