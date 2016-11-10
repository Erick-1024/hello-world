package com.cana.account.service.transaction.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.cana.account.dao.mapper.AccountCustomMapper;
import com.cana.account.dao.mapper.AccountTableLockMapper;
import com.cana.account.dao.mapper.AccountTradeRecordCustomMapper;
import com.cana.account.dao.mapper.gen.AccountApplyMapper;
import com.cana.account.dao.mapper.gen.AccountAuditMapper;
import com.cana.account.dao.mapper.gen.AccountMapper;
import com.cana.account.dao.mapper.gen.AccountSupervisionMapper;
import com.cana.account.dao.mapper.gen.AccountTradeRecordMapper;
import com.cana.account.dao.po.Account;
import com.cana.account.dao.po.AccountApply;
import com.cana.account.dao.po.AccountAudit;
import com.cana.account.dao.po.AccountExample;
import com.cana.account.dao.po.AccountSupervision;
import com.cana.account.dao.po.AccountSupervisionExample;
import com.cana.account.dao.po.AccountTradeRecord;
import com.cana.account.dao.po.AccountTradeRecordExample;
import com.cana.account.dao.po.AccountTradeRecordPO;
import com.cana.account.service.IAccountApplyService;
import com.cana.account.service.IAccountTradeService;
import com.cana.account.service.converter.AccountConverter;
import com.cana.account.service.transaction.IAccountAuthorityTransactionService;
import com.cana.account.service.transaction.IAccountSupervisionTransactionService;
import com.cana.account.service.transaction.IAccountTransactionService;
import com.cana.account.service.transaction.ICustomerTransactionService;
import com.cana.account.service.utils.AccountIDGenerator;
import com.cana.account.service.utils.AccountQueryCriteriaUtil;
import com.cana.bankgate.api.BankgateApi;
import com.cana.member.dao.mapper.gen.AuditMapper;
import com.cana.member.dao.mapper.gen.UserMapper;
import com.cana.member.dao.po.Audit;
import com.cana.member.dao.po.User;
import com.cana.vbam.common.account.consts.AccountConsts;
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
import com.cana.vbam.common.account.enums.AccountAccumulationStatus;
import com.cana.vbam.common.account.enums.AccountApplyStatus;
import com.cana.vbam.common.account.enums.AccountStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.account.enums.AccountTradeType;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceDataDTO;
import com.cana.vbam.common.dto.CompanyInfoAuditDetail;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.enums.user.UserStatus;
import com.cana.vbam.common.member.enums.user.UserType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;

/**
 *
 * @since Nov 12, 20154:43:38 PM
 * @author dev1
 *
 */
@Service
public class AccountTransactionServiceImpl implements IAccountTransactionService {

	private static final Logger logger = LoggerFactory.getLogger(AccountTransactionServiceImpl.class);
	@Resource
	private UserMapper userMapper;
	@Resource
	private AuditMapper auditMapper;
	@Resource
	private IAccountApplyService accountApplyService;
	@Resource
	private AccountMapper accountMapper;
	@Resource
	private AccountApplyMapper accountApplyMapper;
	@Resource
	private AccountAuditMapper accountAuditMapper;
	@Resource
	private AccountSupervisionMapper accountSupervisionMapper;
	@Resource
	private AccountTableLockMapper accountTableLockMapper;
	@Resource
	private AccountTradeRecordMapper accountTradeRecordMapper;
	@Resource
	private AccountTradeRecordCustomMapper accountTradeRecordCustomMapper;
	@Resource(name = "bankgateApi")
	private BankgateApi bankgateApi;
	@Resource
	private AccountCustomMapper accountCustomMapper;
	@Resource
	private AccountConverter accountConverter;
	@Resource
	private ICustomerTransactionService customerTransactionService;
	@Resource
	private IAccountSupervisionTransactionService accountSupervisionTransactionService;
	@Resource
	private IAccountAuthorityTransactionService accountAuthorityTransactionService;
	@Resource
	private IAccountTradeService accountTradeService;
	
	@Override
	public List<Account> batchCreateAccountSelf(User user, List<Pair<String, String>> seqAndNos, List<String> buyerNames) {
		
		List<Account> accounts = Lists.newArrayList();
		int count = -1;
		for (Pair<String, String> seqAndNo : seqAndNos) {
			count++;
			String buyerName = CollectionUtils.isNotEmpty(buyerNames) ? buyerNames.get(count) : null;
			Account account = createAccount(user.getId(), seqAndNo.getKey(), seqAndNo.getValue(), user, buyerName, null);
			accounts.add(account);
		}
		return accounts;
	}

	private List<String> batchCreateAccountAgent(AccountApply apply, List<Pair<String, String>> seqAndNos) {
		List<String> buyerNames = Lists.newArrayList();
		if (StringUtils.isNotBlank(apply.getBuyerNames())) {
			buyerNames = Lists.newArrayList(apply.getBuyerNames().split(AccountConsts.SEMICOLON));
		}
		String applyId = apply.getId();
		User factor = userMapper.selectByPrimaryKey(apply.getAgentCompanyId());
		User finace = userMapper.selectByPrimaryKey(apply.getCompanyId());
		List<String> accountNos = Lists.newArrayList();
		List<Account> subAccounts = Lists.newArrayList();
		Account general = null;
		if (AccountType.GENERAL.name().equals(apply.getAccountType()) && CollectionUtils.isEmpty(buyerNames)) {
			general = createGeneralAccountAgent(seqAndNos.get(0), factor, finace, applyId);
			accountNos.add(general.getAccountNo());
		} else if (AccountType.SPECIAL.name().equals(apply.getAccountType())
				&& CollectionUtils.isNotEmpty(buyerNames)) {
			if (StringUtils.isNotBlank(apply.getSupervisionAccountId())) {
				general = accountTableLockMapper.lockAccountById(apply.getSupervisionAccountId());
			}
			int count = -1;
			for (Pair<String, String> seqAndNo : seqAndNos) {
				if (general == null) {// 如果没有指定监管帐号，创建一个监管帐号
					checkSupervisionCanBeCreatedWhenNeed(seqAndNos.size(), apply);
					User user = chooseUserByType(apply.getSupervisorType(), factor, finace);
					general = createGeneralAccountAgent(seqAndNo, factor, user, applyId);
					accountNos.add(general.getAccountNo());
				} else {
					count++;
					String seq = seqAndNo.getKey();
					String no = seqAndNo.getValue();
					Account account = createAccount(factor.getId(), seq, no, finace, buyerNames.get(count), applyId);
					accountNos.add(account.getAccountNo());
					subAccounts.add(account);
				}
			}
		} else {
			logger.error("参数不匹配，无法创建账户!");
			throw WebException.instance("参数不匹配，无法创建账户!");
		}
		accountSupervisionTransactionService.createSupervision(factor, finace, general, subAccounts);
		if (apply.getIsDefaultRepayment()) {
		    updateAccountIsDefaultRepayment(general);
		}
		return accountNos;
	}

	private Account createGeneralAccountAgent(Pair<String, String> seqAndNo, User factor, User owner,
			String applyId) {
		Account account = createAccount(factor.getId(), seqAndNo.getKey(), seqAndNo.getValue(), owner, null, applyId);
		return account;
	}
	
	private User chooseUserByType(String userType, User... users) {
		for (User user : users) {
			if (userType.equals(user.getUserType())) {
				return user;
			}
		}
		return null;
	}

	private void checkSupervisionCanBeCreatedWhenNeed(int accountSize, AccountApply apply) {
		if (isNeedNewSupervisionAccount(apply) && accountSize < 2) {
			logger.error("账号数异常，不能创建监管帐号！");
			throw WebException.instance("账号数异常，不能创建监管帐号！");
		}
	}

	private boolean isNeedNewSupervisionAccount(AccountApply apply) {
		if (AccountType.SPECIAL.name().equals(apply.getAccountType())) {
			return StringUtils.isNotBlank(apply.getBuyerNames())
					&& StringUtils.isBlank(apply.getSupervisionAccountId());
		}
		return false;
	}

	private Account createAccount(String operatorId, String bizSeq, String accountNo, User user, String buyerName,
			String accountApplyId) {
		String accountId = AccountIDGenerator.generateAccountId();
		Account account = new Account();
		account.setId(accountId);
		account.setAccountNo(accountNo);
		account.setUserType(user.getUserType());
		account.setAccountStatus(AccountStatus.NORMAL.name());
		if (StringUtils.isNotBlank(buyerName)) {
			account.setAccountType(AccountType.SPECIAL.name());
		} else {
			account.setAccountType(AccountType.GENERAL.name());
		}
		account.setBuyerName(buyerName);
		account.setCompanyId(user.getId());
		account.setCompanyName(user.getCompanyName());
		account.setSupervisionStatus(AccountSupervisionStatus.NO_SUPERVISION.name());
		account.setAccumulationStatus(AccountAccumulationStatus.NO_ACCUMULATION.name());
		account.setCreateTime(new Date());
		account.setUpdateTime(new Date());

		account.setAccountApplyId(accountApplyId);
		accountMapper.insertSelective(account);

		saveAccountTradeRecord(operatorId, account, bizSeq, AccountTradeStatus.TRADE_SUCCESS,
				AccountTradeType.CREATE_ACCOUNT, new Date());
		return account;
	}

	// 新建交易记录
	private void saveAccountTradeRecord(String operatorId,Account account, String bizSeq, AccountTradeStatus status,
			AccountTradeType type, Date endTime) {
		String recordId = AccountIDGenerator.generateAccountTradeRecordId();
		AccountTradeRecord record = new AccountTradeRecord();
		record.setId(recordId);
		record.setCompanyId(account.getCompanyId());
		record.setAccountType(account.getAccountType());
        record.setAccountName(account.getCompanyName());
		record.setAccountId(account.getId());
		record.setAccountNo(account.getAccountNo());
		record.setBusinessSeq(bizSeq);
		record.setStatus(status.name());
		record.setTradeStartTime(new Date());
		record.setTradeEndTime(endTime);
		record.setTradeType(type.name());
		record.setOperateUserId(operatorId);
		record.setUserType(account.getUserType());
		accountTradeRecordMapper.insertSelective(record);
	}

	@Override
	public String createAccountApplyByAgent(User agentCustomer, User accountUser,
			AccountAgentCreateDTO accountAgentCreateDTO) {
		AccountApply accountApply = new AccountApply();
		List<String> buyerNames = accountAgentCreateDTO.getBuyerNames();
		if (StringUtils.isNotBlank(accountAgentCreateDTO.getSupervisorAccountNo())) {
		    Account account = accountTableLockMapper.lockAccountByAccountNo(accountAgentCreateDTO.getSupervisorAccountNo());
			if (account == null)
				throw WebException.instance("银行帐号不存在.");
			accountApply.setSupervisionAccountId(account.getId());
			account.setAccountStatus(AccountStatus.HANDLING.name());
			accountMapper.updateByPrimaryKeySelective(account);
			
		}
		// 如果被代开户用户已经存在，则给企业ID和用户名称赋值
		if (accountUser != null && !UserStatus.PENDINGAUDIT.name().equals(accountUser.getUserStatus())) {
			accountApply.setCompanyId(accountUser.getId());
			accountApply.setUsername(accountUser.getUsername());
		}
		if (buyerNames != null)
			accountApply.setBuyerNames(StringUtils.join(buyerNames.toArray(), AccountConsts.SEMICOLON));
		accountApply.setId(AccountIDGenerator.generateAccountApplyId());
		accountApply.setApplyStatus(AccountApplyStatus.PENDINGAUDIT.name());
		accountApply.setUserType(UserType.FINACE.name());
		accountApply.setCompanyName(accountAgentCreateDTO.getCompanyName());
		accountApply.setAgentCompanyId(accountAgentCreateDTO.getAgentCompanyId());
		accountApply.setAgentCompanyName(agentCustomer.getCompanyName());
		accountApply.setAccountNumber(accountAgentCreateDTO.getAccountNumber());
		accountApply.setContactName(accountAgentCreateDTO.getContactName());
		accountApply.setContactJobTitle(accountAgentCreateDTO.getContactJobTitle());
		accountApply.setContactTel(accountAgentCreateDTO.getContactTel());
		accountApply.setContactMail(accountAgentCreateDTO.getContactMail());
		accountApply.setContactIdentityCardFrontMediaId(accountAgentCreateDTO.getContactIdentityCardFrontMediaId());
		accountApply.setContactIdentityCardBackMediaId(accountAgentCreateDTO.getContactIdentityCardBackMediaId());
		accountApply.setAuthorizationLetterId(accountAgentCreateDTO.getAuthorizationLetterId());
		accountApply.setOrganizationCode(accountAgentCreateDTO.getOrganizationCode());
		accountApply
				.setOrganizationCodeCertificateMediaId(accountAgentCreateDTO.getOrganizationCodeCertificateMediaId());
		accountApply.setBusinessLicenceCode(accountAgentCreateDTO.getBusinessLicenceCode());
		accountApply.setBusinessLicenceMediaId(accountAgentCreateDTO.getBusinessLicenceMediaId());
		accountApply
				.setLegalPersonIdentityCardFrontMediaId(accountAgentCreateDTO.getLegalPersonIdentityCardFrontMediaId());
		accountApply
				.setLegalPersonIdentityCardBackMediaId(accountAgentCreateDTO.getLegalPersonIdentityCardBackMediaId());
		accountApply.setTaxRegistrationCertificateCode(accountAgentCreateDTO.getTaxRegistrationCertificateCode());
		accountApply.setTaxRegistrationCertificateMediaId(accountAgentCreateDTO.getTaxRegistrationCertificateMediaId());
		accountApply.setCreateTime(new Date());
		accountApply.setUpdateTime(accountApply.getCreateTime());
		accountApply.setAccountType(accountAgentCreateDTO.getAccountType().name());
		if(accountAgentCreateDTO.getSupervisorType() == null){
			accountApply.setSupervisorType(UserType.FACTOR.name());
		}else{
			accountApply.setSupervisorType(accountAgentCreateDTO.getSupervisorType().name());
		}
		accountApply.setIsDefaultRepayment(accountAgentCreateDTO.getIsDefaultRepayment());
		accountApplyMapper.insertSelective(accountApply);
		return accountApply.getId();
	}

	private void coverUserInfoByAccountApply(User finaceUser, AccountApply accountApply) {
		finaceUser.setContactName(accountApply.getContactName());
		finaceUser.setContactMail(accountApply.getContactMail());
		finaceUser.setContactTel(accountApply.getContactTel());
		finaceUser.setContactIdentityCardFrontMediaId(accountApply.getContactIdentityCardFrontMediaId());
		finaceUser.setContactIdentityCardBackMediaId(accountApply.getContactIdentityCardBackMediaId());
		finaceUser.setOrganizationCode(accountApply.getOrganizationCode());
		finaceUser.setOrganizationCodeCertificateMediaId(accountApply.getOrganizationCodeCertificateMediaId());
		finaceUser.setBusinessLicenceCode(accountApply.getBusinessLicenceCode());
		finaceUser.setBusinessLicenceMediaId(accountApply.getBusinessLicenceMediaId());
		finaceUser.setLegalPersonIdentityCardFrontMediaId(accountApply.getLegalPersonIdentityCardFrontMediaId());
		finaceUser.setLegalPersonIdentityCardBackMediaId(accountApply.getLegalPersonIdentityCardBackMediaId());
		finaceUser.setTaxRegistrationCertificateCode(accountApply.getTaxRegistrationCertificateCode());
		finaceUser.setTaxRegistrationCertificateMediaId(accountApply.getTaxRegistrationCertificateMediaId());
		finaceUser.setUpateTime(new Date());
	}

	@Override
	public PageResult<AccountDTO> queryAccounts(String customerId, AccountQueryCriteria criteria) {
		User customer = customerTransactionService.checkCustomerIsValid(customerId);
		AccountQueryCriteria dbCriteria = AccountQueryCriteriaUtil.getValidCriteria(customer, criteria);
		int total = accountCustomMapper.count(dbCriteria);
		List<Account> accounts = accountCustomMapper.find(dbCriteria);
		List<AccountDTO> accountDTOs = accountConverter.convertForList(customer, accounts);
		return new PageResult<AccountDTO>(accountDTOs, total);
	}

	@Override
	public List<AccountDTO> queryAccountsByCustomerId(String customerId) {
		User customer = customerTransactionService.checkCustomerIsValid(customerId);
		AccountQueryCriteria dbCriteria = AccountQueryCriteriaUtil.getValidCriteria(customer);
		List<Account> accounts = accountCustomMapper.queryAccountsByCustomerId(dbCriteria);
		List<AccountDTO> accountDTOs = accountConverter.convertForList(customer, accounts);
		return accountDTOs;
	}
	
	@Override
	public AccountDTO getAccount(String customerId, String accountId) {
		User customer = customerTransactionService.checkCustomerIsValid(customerId);
		Account account = accountMapper.selectByPrimaryKey(accountId);
		checkAccessAuthority(customer, account);
		return accountConverter.convertForDetail(customer, account);
	}

	@Override
	public AccountDTO getOwnAccountByNo(String customerId, String accountNo) {
		User customer = customerTransactionService.checkCustomerIsValid(customerId);
		Account account = getAccountPOByNo(accountNo);
		if (account == null || !customer.getId().equals(account.getCompanyId()))
			return null;
		return accountConverter.convertForDetail(customer, account);
	}
	@Override
    public List<AccountDTO> getAccountByNos(String customerId,List<String> accountNos) {
		User customer = customerTransactionService.checkCustomerIsValid(customerId);
		AccountExample ex = new AccountExample();
		ex.createCriteria().andAccountNoIn(accountNos);
		List<Account> accounts = accountMapper.selectByExample(ex);
        return accountConverter.convertForList(customer, accounts);
    }

    @Override
    public List<AccountDTO> queryRepaymentAccounts(String factorId, String finaceName) {
        if (StringUtils.isAnyBlank(factorId, finaceName)) {
            return Lists.newArrayList();
        }
        AccountQueryCriteria criteria = new AccountQueryCriteria();
        criteria.setPageSize(Integer.MAX_VALUE);
        criteria.setFactorId(factorId);
        criteria.setFinaceName(finaceName);
        criteria.setAccountType(AccountType.GENERAL);
        criteria.setAccountStatus(AccountStatus.NORMAL);
        List<Account> accounts = accountCustomMapper.find(criteria);
        return accountConverter.convertForList(null, accounts);
    }
    @Override
    public boolean setFactorTransferInAccount(String factorId, String accountNo) {
        if (StringUtils.isAnyBlank(factorId, accountNo)) {
            throw WebException.instance("参数不能为空");
        }
        Account account = accountTableLockMapper.lockAccountByAccountNo(accountNo);
        if (account == null)
            throw WebException.instance("账户不存在");
        if (!account.getCompanyId().equals(factorId))
            throw WebException.instance("账户不属于当前客户");
        if (!UserType.FACTOR.name().equals(account.getUserType()))
            throw WebException.instance("账户不是保理商的账户");
        if (BooleanUtils.isTrue(account.getIsTransferInAccount()))
            return true;
        if (!AccountType.GENERAL.name().equals(account.getAccountType()))
            throw WebException.instance("账户不是一般账户");
        if (!AccountStatus.NORMAL.name().equals(account.getAccountStatus()))
            throw WebException.instance("账户状态不是正常状态");
        if (AccountSupervisionStatus.HAVE_SUPERVISION.name().equals(account.getSupervisionStatus()))
            throw WebException.instance("账户必须是未监管的账户");
        account.setIsTransferInAccount(true);
        accountMapper.updateByPrimaryKeySelective(account);
        return true;
    }
	
	@Override
	public PageResult<AccountTradeRecordDTO> getTradeRecord(String customerId, AccountTradeRecordCriteria accountTradeRecordCriteria) {
		User customer = customerTransactionService.checkCustomerIsValid(customerId);
		Account account = accountMapper.selectByPrimaryKey(accountTradeRecordCriteria.getAccountId());
		checkAccessAuthority(customer, account);
		AccountTradeRecordExample example = new AccountTradeRecordExample();
		example = countAddConditions(example, accountTradeRecordCriteria,customerId,account);
		int total = accountTradeRecordMapper.countByExample(example);
		example = queryAddConditions(example, accountTradeRecordCriteria);
		List<AccountTradeRecord> accountTradeRecords = accountTradeRecordMapper.selectByExample(example);
		List<AccountTradeRecordDTO> accountTradeRecordDTOs = accountConverter
				.convertAccountTradeRecordList(accountTradeRecords);
		return new PageResult<AccountTradeRecordDTO>(accountTradeRecordDTOs, total);
	}

	@Override
	public boolean accountFreeze(String userId, String customerId, String accountId) {
		User customer = customerTransactionService.checkCustomerIsValid(customerId);
		Account account = accountTableLockMapper.lockAccountById(accountId);
		checkAccessAuthority(customer, account);
		checkFreezeAuthority(customer, account);
		updateAccountAndInsertTradeRecord(userId, customerId, account,"freeze");
		return true;
	}

	@Override
	public boolean accountUnfreeze(String userId, String customerId, String accountId) {
		User customer = customerTransactionService.checkCustomerIsValid(customerId);
		Account account = accountTableLockMapper.lockAccountById(accountId);
		checkAccessAuthority(customer, account);
		checkUnFreezeAuthority(customerId, account);
		updateAccountAndInsertTradeRecord(userId, customerId, account,"unfreeze");
		return true;
	}
	
	@Override
	public boolean accountSetDefaultRepayment(String customerId, String accountId) {
		User customer = customerTransactionService.checkCustomerIsValid(customerId);
	    Account account = accountTableLockMapper.lockAccountById(accountId);
	    checkAccessAuthority(customer, account);
	    checkSetDefaultRepaymentAuthority(customer, account);
	    updateAccountIsDefaultRepayment(account);
		return true;
	}
	
	@Override
	public Account getAccountPOByNo(String accountNo) {
		AccountExample example = new AccountExample();
		example.createCriteria().andAccountNoEqualTo(accountNo);
		List<Account> accounts = accountMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(accounts))
			return null;
		else
			return accounts.get(0);
	}

    /**
     * 检查客户有没有访问账户的权限
     */
    private void checkAccessAuthority(User customer, Account account) {
        if (account == null)
            throw WebException.instance("账户不存在");
        if (UserType.CANA.name().equals(customer.getUserType()))
            return;
        if (!customer.getId().equals(account.getCompanyId())
                && !customer.getId().equals(account.getSupervisorId()))
            throw WebException.instance("对当前账户无访问权限");
    }

    /**
     * 对查询收支明细总数total增加搜索条件
     * @param example
     * @param page
     * @param pageSize
     * @return
     */
    private AccountTradeRecordExample countAddConditions(AccountTradeRecordExample example, AccountTradeRecordCriteria accountTradeRecordCriteria, String customerId, Account account){
    	List<String> tradeTypeValues = Lists.newArrayList();
    	
    	for(AccountTradeType accountTradeType : AccountTradeType.values()){
			if(accountTradeType == AccountTradeType.TRANSFER_FUND || accountTradeType == AccountTradeType.WITHDRAW_FUND)
				tradeTypeValues.add(accountTradeType.name());
		}
    	AccountTradeRecordExample.Criteria criteria = example.createCriteria();
    	criteria.andAccountIdEqualTo(accountTradeRecordCriteria.getAccountId()).andTradeTypeIn(tradeTypeValues);
		//账户状态为监管 并且 当前用户为监管账户的对方（不是监管账户的属于者）=> 只能看到监管时的收支明细
		if(StringUtils.isNotBlank(account.getAccountSupervisionId()) && customerId.equals(account.getSupervisorId())){
			criteria.andAccountSupervisionIdEqualTo(account.getAccountSupervisionId());
		}
		return example;
    }
    
    /**
     * 对查询收支明细增加搜索条件
     * @param example
     * @param page
     * @param pageSize
     * @return
     */
    private AccountTradeRecordExample queryAddConditions(AccountTradeRecordExample example ,AccountTradeRecordCriteria accountTradeRecordCriteria){
    	example.setOrderByClause("id desc");
	    int page = accountTradeRecordCriteria.getPage() > 0 ? accountTradeRecordCriteria.getPage() : 1;
	    int pageSize = accountTradeRecordCriteria.getPageSize() > 0 ? accountTradeRecordCriteria.getPageSize() :5;
		example.setLimitStart((page - 1) * pageSize);
		example.setLimitEnd(pageSize);
		return example;
    }
    
    /**
     * 检查当前用户是否可以冻结该账户
     * @param customer
     * @param account
     */
    private void checkFreezeAuthority(User customer, Account account){
    	if(!AccountStatus.NORMAL.equals(AccountStatus.valueOf(account.getAccountStatus())))
    		throw WebException.instance("该账户现在不能进行冻结操作");
    	UserType userType = UserType.valueOf(customer.getUserType());
    	AccountSupervisionStatus supervisionStatus = AccountSupervisionStatus.valueOf(account.getSupervisionStatus());
    	if(UserType.CANA.equals(userType))
    		return;
    	if ((UserType.FACTOR.equals(userType) && AccountSupervisionStatus.HAVE_SUPERVISION.equals(supervisionStatus) && (customer.getCompanyName().equals(account.getCompanyName()) || customer.getCompanyName().equals(account.getSupervisorName()))))
    		return;
    	throw WebException.instance("对该账户没有冻结权限");
    }
    
    /**
     * 检查当前用户是否可以解冻该账户
     * @param customer
     * @param account
     */
    private void checkUnFreezeAuthority(String customerId, Account account){
    	if(!AccountStatus.FROZEN.equals(AccountStatus.valueOf(account.getAccountStatus())))
    		throw WebException.instance("该账户现在不能进行解冻操作");

    	//原先进行冻结操作的customerId
    	String freezeCustomerId = account.getOperateCompanyId();
    	if(!freezeCustomerId.equals(customerId))
    		throw WebException.instance("对该账户没有解冻权限,谁冻结谁解冻！");
    }

    /**
     * 解冻冻结触发 --在accountTradeRecord表新增记录并且更新account表
     * @param userId
     * @param customerId
     * @param account
     */
    private void updateAccountAndInsertTradeRecord(String userId, String customerId, Account account, String operate){
		AccountTradeRecord record = new AccountTradeRecord();
		String tradeRecordId = AccountIDGenerator.generateAccountTradeApplyId();
		record.setId(tradeRecordId);
		record.setAccountId(account.getId());
		record.setAccountNo(account.getAccountNo());
		record.setCompanyId(account.getCompanyId());
        record.setAccountName(account.getCompanyName());
        record.setAccountType(account.getAccountType());
		record.setOperateUserId(userId);
		record.setTradeEndTime(new Date());
		record.setTradeStartTime(new Date());
		if(operate.equals("freeze")){
			record.setTradeType(AccountTradeType.FREEZE.name());
			account.setAccountStatus(AccountStatus.FROZEN.name());
		}
		else if(operate.equals("unfreeze")){
			record.setTradeType(AccountTradeType.UNFREEZE.name());
			account.setAccountStatus(AccountStatus.NORMAL.name());
		}
		record.setUserType(account.getUserType());
		accountTradeRecordMapper.insertSelective(record);
		account.setOperateCompanyId(customerId);
		accountMapper.updateByPrimaryKeySelective(account);
    }

    /**
     * 检查当前用户是否可以对该账户设置为默认账户
     * @param customer
     * @param account
     */
    private void checkSetDefaultRepaymentAuthority(User customer, Account account){
        if (!AccountType.GENERAL.name().equals(account.getAccountType())) {
            throw WebException.instance("当前账户不能进行设置默认账户操作");
        }
    	AccountSupervisionStatus supervisionStatus = AccountSupervisionStatus.valueOf(account.getSupervisionStatus());
    	if(AccountSupervisionStatus.NO_SUPERVISION.equals(supervisionStatus))
    		throw WebException.instance("该账户现在是未监管状态，不能进行设置默认账户操作");
		UserType userType = UserType.valueOf(customer.getUserType());
		if(!UserType.FACTOR.equals(userType))
			throw WebException.instance("对该账户没有设置默认账户权限");
    }
    
    @Override
    public void updateAccountIsDefaultRepayment(Account account) {
    	List<String> companyNames = Lists.newArrayList();
    	companyNames.add(account.getCompanyName());
    	companyNames.add(account.getSupervisorName());
	    Account updateAccount = new Account();
	    AccountExample example = new AccountExample(); 
	    updateAccount.setIsDefaultRepayment(false);
	    example.createCriteria().andCompanyNameIn(companyNames).andSupervisorNameIn(companyNames);
	    accountMapper.updateByExampleSelective(updateAccount, example);
	    
	    account.setIsDefaultRepayment(true);
	    accountMapper.updateByPrimaryKeySelective(account);
	}

	@Override
	public PageResult<AccountTradeRecordDTO> queryTradeRecords(String customerId, AccountTradeRecordCriteria criteria) {
		User customer = customerTransactionService.checkCustomerIsValid(customerId);
		criteria = getAccountTradeRecordCriteria(customer, criteria);
		int total = accountTradeRecordCustomMapper.count(criteria);
		List<AccountTradeRecordPO> accountTradeRecordPOs = accountTradeRecordCustomMapper.find(criteria);
		List<AccountTradeRecordDTO> accountTradeRecordDTOs = accountConverter.convertAccountTradeRecordDTOs(accountTradeRecordPOs);
		return new PageResult<AccountTradeRecordDTO>(accountTradeRecordDTOs,total);
	}
	
	/**
	 * 流水明细 查询条件转换
	 * @param customer
	 * @param criteria
	 * @return
	 */
	private AccountTradeRecordCriteria getAccountTradeRecordCriteria(User customer, AccountTradeRecordCriteria criteria){
		AccountTradeRecordCriteria criteriaDB = new AccountTradeRecordCriteria();
		UserType userType = UserType.valueOf(customer.getUserType());
		criteriaDB.setPage(criteria.getPage() < 1 ? 1 : criteria.getPage());
		criteriaDB.setPageSize(criteria.getPageSize() < 1 ? 10 : criteria.getPageSize());
		if (StringUtils.isNotBlank(criteria.getAccountName())) 
			criteriaDB.setAccountName("%" + criteria.getAccountName() + "%");
		if(StringUtils.isNotBlank(criteria.getAccountNo()))
			criteriaDB.setAccountNo(criteria.getAccountNo());
		criteriaDB.setTradeType(criteria.getTradeType());
		if(criteria.getTradeType() == null){
			List<AccountTradeType> accountTradeTypes = Lists.newArrayList();
	    	for(AccountTradeType accountTradeType : AccountTradeType.values()){
				if(accountTradeType == AccountTradeType.TRANSFER_FUND || accountTradeType == AccountTradeType.WITHDRAW_FUND)
					accountTradeTypes.add(accountTradeType);
			}
			criteriaDB.setTradeTypes(accountTradeTypes);
		}
		if (StringUtils.isNotBlank(criteria.getOppositeAccountName()))
			criteriaDB.setOppositeAccountName("%" + criteria.getOppositeAccountName() + "%");
		if(StringUtils.isNotBlank(criteria.getOppositeAccountNo()))
			criteriaDB.setOppositeAccountNo(criteria.getOppositeAccountNo());
		criteriaDB.setAccountType(criteria.getAccountType());
		if(StringUtils.isNotBlank(criteria.getStartTime()))
			criteriaDB.setStartTime(criteria.getStartTime());
		if(StringUtils.isNotBlank(criteria.getEndTime()))
			criteriaDB.setEndTime(stringPlusDay(criteria.getEndTime()));
		if(StringUtils.isNotBlank(criteria.getBusinessSeq()))
			criteriaDB.setBusinessSeq(criteria.getBusinessSeq());
		if (UserType.FACTOR.equals(userType)) 
			criteriaDB.setFactorId(customer.getId());
		if (UserType.FINACE.equals(userType)) 
			criteriaDB.setFinaceId(customer.getId());
		if (UserType.CORECOMPANY.equals(userType))
			criteriaDB.setCoreCompanyId(customer.getId());
		return criteriaDB;
	}

	private String stringPlusDay(String originDate){
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String newDate = sdf.format(new DateTime(originDate).plusDays(1).toDate());
		return newDate;
	}

	@Override
	public AccountDTO getOwnAccountByNameAndNo(String customerName,String accountNo) {
		AccountExample ex = new AccountExample();
		ex.createCriteria().andCompanyNameEqualTo(customerName).andAccountNoEqualTo(accountNo);
		List<Account> accounts = accountMapper.selectByExample(ex);
		if (CollectionUtils.isNotEmpty(accounts)) {
			User user = customerTransactionService.findCustomerByCompanyNameAndUserType(customerName,UserType.valueOf(accounts.get(0).getUserType()));
			AccountDTO dto = accountConverter.convertForDetail(user, accounts.get(0));
			return dto;
		} else {
			logger.error("所查账号不存在或不属于该客户!客户:{},账号:{}", customerName, accounts);
			throw WebException.instance("所查账号不存在或不属于该客户!");
		}
	}

    @Override
    public List<CustomerDetailDTO> querySupervisorsByFactorId(String factorId) {
        if (StringUtils.isBlank(factorId)) return Lists.newArrayList();
        AccountQueryCriteria criteria = new AccountQueryCriteria();
        criteria.setFactorId(factorId);
        criteria.setAccountType(AccountType.GENERAL);
        criteria.setSupervisoryStatus(AccountSupervisionStatus.HAVE_SUPERVISION);
        List<Account> accounts = accountCustomMapper.find(criteria);
        if (CollectionUtils.isNotEmpty(accounts)) {
            Map<String, String> finaceIdAndNames = Maps.newHashMap();
            for (Account acc : accounts) {
                if (UserType.FACTOR.name().equals(acc.getUserType())) {
                    finaceIdAndNames.put(acc.getSupervisorId(), acc.getSupervisorName());
                } else {
                    finaceIdAndNames.put(acc.getCompanyId(), acc.getCompanyName());
                }
            }
            List<CustomerDetailDTO> finaces = Lists.newArrayList();
            for (Entry<String, String> entry : finaceIdAndNames.entrySet()) {
                CustomerDetailDTO finace = new CustomerDetailDTO();
                finace.setId(entry.getKey());
                finace.setCompanyName(entry.getValue());
                finaces.add(finace);
            }
            return finaces;
        }
        return Lists.newArrayList();
    }

	@Override
	public boolean auditAccountApplyAndCreateAccount(User auditor, AccountApplyAuditDetail detail,
			List<Pair<String, String>> seqAndNos) {
		AccountApply apply = accountAuthorityTransactionService.checkAuditAgentApplyAuthority(detail);
		AccountApplyStatus status = detail.getEnumStatus();
		Boolean isNeedMail = auditAccountApply(auditor,apply,detail);
		if(AccountApplyStatus.ACCEPTED.equals(status) && StringUtils.isNotBlank(apply.getAgentCompanyId())){
			batchCreateAccountAgent(apply,seqAndNos);
		}
		return isNeedMail;

	}
	
	private boolean auditAccountApply(User auditor, AccountApply apply, AccountApplyAuditDetail detail) {
		Boolean isNeedActive = false;
		if (StringUtils.isNotBlank(apply.getSupervisionAccountId())) {
			Account account = accountTableLockMapper.lockAccountById(apply.getSupervisionAccountId());
			account.setAccountStatus(AccountStatus.NORMAL.name());
			accountMapper.updateByPrimaryKeySelective(account);
		}
		createAccountAudit(apply, detail);
		AccountApplyStatus status = detail.getEnumStatus();
		apply.setApplyStatus(status.name());
		apply.setUpdateTime(new Date());
		if (AccountApplyStatus.ACCEPTED.equals(status) && StringUtils.isNotBlank(apply.getAgentCompanyId())) {
			isNeedActive = createOrUpdateFinace(apply, detail);
		}
		accountApplyMapper.updateByPrimaryKeySelective(apply);
		return isNeedActive;
	}

	private void createAccountAudit(AccountApply apply, AccountApplyAuditDetail auditDetail){
		AccountAudit accountAudit = new AccountAudit();
		accountAudit.setId(AccountIDGenerator.generateAuditId());
		accountAudit.setAccountApplyId(apply.getId());
		accountAudit.setAuditStatus(auditDetail.getIntAccountAuditStatus());
		accountAudit.setAuditorId(auditDetail.getAuditorId());
		accountAudit.setAuditMessage(auditDetail.getAuditMessage());
		accountAudit.setRoleId(auditDetail.getRoleId());
		accountAudit.setCreateTime(new Date());
		accountAudit.setUpdateTime(new Date());
		accountAuditMapper.insertSelective(accountAudit);
	}
	
	private boolean createOrUpdateFinace(AccountApply apply, AccountApplyAuditDetail auditDetail) {
		// 如果是代办开户申请，此时应检查企业名称是否已注册，
		// 若已经注册，则应将融资商客户Id保存到开户申请表中
		boolean isNeedActive = false;
		User finaceUser = customerTransactionService.findCustomerByCompanyNameAndUserType(apply.getCompanyName(),UserType.FINACE);
		if (finaceUser == null) {
			// 若未注册，则注册一个
			finaceUser = new User();
			finaceUser.setId(AccountIDGenerator.generateUserId());
			finaceUser.setCompanyName(apply.getCompanyName());
			finaceUser.setUserType(UserType.FINACE.name());
			finaceUser.setUserStatus(UserStatus.PENDINGACTIVATE.name());
			if (StringUtils.isBlank(auditDetail.getRoleId()))
				throw WebException.instance("角色Id不能为空");
			finaceUser.setRoleId(auditDetail.getRoleId());
			finaceUser.setCreateTime(new Date());
			coverUserInfoByAccountApply(finaceUser, apply);
			finaceUser.setAgentCompany(apply.getAgentCompanyName());
			userMapper.insertSelective(finaceUser);
			insertCustomerAuditRecord(auditDetail, finaceUser);
			isNeedActive = true;
		} else {
			// 若已注册，则更新
//			UserType userType = UserType.valueOf(finaceUser.getUserType());
//			if (UserType.FINACE.equals(userType)) {
				coverUserInfoByAccountApply(finaceUser, apply);
				if (UserStatus.PENDINGAUDIT.name().equals(finaceUser.getUserStatus())) {
					if (StringUtils.isBlank(auditDetail.getRoleId()))
						throw WebException.instance("角色Id不能为空");
					finaceUser.setRoleId(auditDetail.getRoleId());
					finaceUser.setUserStatus(UserStatus.PENDINGACTIVATE.name());
					insertCustomerAuditRecord(auditDetail, finaceUser);
					isNeedActive = true;
				}
				userMapper.updateByPrimaryKeySelective(finaceUser);
//			}
		}
		apply.setCompanyId(finaceUser.getId());
		apply.setUsername(finaceUser.getUsername());
		return isNeedActive;
	}
	
	/**
	 * 代开户审核通过后，可能会注册或者通过一个客户记录，此时需要插入一条审核记录
	 */
	private void insertCustomerAuditRecord(CompanyInfoAuditDetail companyInfoAuditDetail, User customer) {
		Audit audit = new Audit();
		audit.setId(AccountIDGenerator.generateCustomerAuditId());
		audit.setAuditorId(companyInfoAuditDetail.getAuditorId());
		audit.setAuditMessage(companyInfoAuditDetail.getAuditMessage());
		audit.setAuditStatus(companyInfoAuditDetail.getIntUserAuditStatus());
		audit.setCustomerId(customer.getId());
		audit.setCreateTime(new Date());
		auditMapper.insertSelective(audit);
	}

	@Override
	public AccountBalancesAndNumberDTO getAccountBalancesAndNumber(String customerId) {
		User customer = customerTransactionService.checkCustomerIsValid(customerId);
		AccountQueryCriteria baseCriteria = getBaseCriteria(customer);
		List<Account> accounts = accountCustomMapper.findAll(baseCriteria);
		AccountBalancesAndNumberDTO accountBalancesAndNumberDTO = convert2AccountBalancesAndNumberDTO(customerId,accounts);
		return accountBalancesAndNumberDTO;
	}
	
	/**
	 * 转换查询账户的条件
	 * @param customer
	 * @return
	 */
	private AccountQueryCriteria getBaseCriteria(User customer){
		AccountQueryCriteria baseCriteria = new AccountQueryCriteria();
		UserType userType = UserType.valueOf(customer.getUserType());
		if (UserType.FACTOR.equals(userType)) {
            baseCriteria.setFactorId(customer.getId());
        } else if (UserType.FINACE.equals(userType)) {
            baseCriteria.setFinaceId(customer.getId());
        } else{
        	baseCriteria.setCompanyId(customer.getId());
        }
		return baseCriteria;
	}
	
	/**
	 * 分类汇总账户余额和数量
	 * @param accounts
	 * @return
	 */
	private AccountBalancesAndNumberDTO convert2AccountBalancesAndNumberDTO(String customerId,List<Account> accounts){
		List<Account> generalNoSupervisionAccounts = new ArrayList<Account>();
		List<Account> generalHavaSupervisionAccounts = new ArrayList<Account>();
		List<Account> specialNoSupervisionAccounts = new ArrayList<Account>();
		List<Account> specialHaveSupervisionAccounts = new ArrayList<Account>();
		
		AccountBalancesAndNumberDTO dto = new AccountBalancesAndNumberDTO();
		String allBalances = "";
		String generalNoSupervisionBalances = "";
		String generalHaveSupervisionBalances = "";
		String specialNoSupervisionBalances = "";
		String specialHaveSupervisionBalances = "";
		
		if(accounts != null && !accounts.isEmpty()){
			for(Account account : accounts){
				if(AccountType.GENERAL.name().equals(account.getAccountType()) && AccountSupervisionStatus.NO_SUPERVISION.name().equals(account.getSupervisionStatus()))
					generalNoSupervisionAccounts.add(account);
				if(AccountType.GENERAL.name().equals(account.getAccountType()) && AccountSupervisionStatus.HAVE_SUPERVISION.name().equals(account.getSupervisionStatus()))
					generalHavaSupervisionAccounts.add(account);
				if(AccountType.SPECIAL.name().equals(account.getAccountType()) && AccountSupervisionStatus.NO_SUPERVISION.name().equals(account.getSupervisionStatus()))
					specialNoSupervisionAccounts.add(account);
				if(AccountType.SPECIAL.name().equals(account.getAccountType()) && AccountSupervisionStatus.HAVE_SUPERVISION.name().equals(account.getSupervisionStatus()))
					specialHaveSupervisionAccounts.add(account);
			}
		}
		generalNoSupervisionBalances = getBalances(customerId,generalNoSupervisionAccounts);
		generalHaveSupervisionBalances = getBalances(customerId,generalHavaSupervisionAccounts);
		specialNoSupervisionBalances = getBalances(customerId,specialNoSupervisionAccounts);
		specialHaveSupervisionBalances = getBalances(customerId,specialHaveSupervisionAccounts);
		try{
			allBalances = MoneyUtil.cent2Yuan(MoneyUtil.yuan2Cent(generalNoSupervisionBalances) + MoneyUtil.yuan2Cent(generalHaveSupervisionBalances) + MoneyUtil.yuan2Cent(specialNoSupervisionBalances) + MoneyUtil.yuan2Cent(specialHaveSupervisionBalances));
		}catch(Exception e){
			allBalances = "获取失败";
		}
		dto.setAllBalances(allBalances);
		dto.setGeneralNoSupervisionBalances(generalNoSupervisionBalances);
		dto.setGeneralNoSupervisionNumber(generalNoSupervisionAccounts.size()+"");
		dto.setGeneralHaveSupervisionBalances(generalHaveSupervisionBalances);
		dto.setGeneralHaveSupervisionNumber(generalHavaSupervisionAccounts.size()+"");
		dto.setSpecialNoSupervisionBalances(specialNoSupervisionBalances);
		dto.setSpecialNoSupervisionNumber(specialNoSupervisionAccounts.size()+"");
		dto.setSpecialHaveSupervisionBalances(specialHaveSupervisionBalances);
		dto.setSpecialHaveSupervisionNumber(specialHaveSupervisionAccounts.size()+"");
		return dto;
	}
	
	/**
	 * 获取account列表的总余额
	 * @param customerId
	 * @param accounts
	 * @return
	 */
	private String getBalances(String customerId ,List<Account> accounts){
		Long balances = 0L;
	    for(Account account : accounts){
			try{
				List<BankAccountBalanceDataDTO> datas = accountTradeService.queryAccountBalance(customerId, account.getAccountNo());
				if(datas.get(0)==null)
					return "获取失败";
				balances += datas.get(0).getAvailableBalance();
			}catch(Exception e){
				logger.error(e.getMessage(), e);
				return "获取失败";
			}
	    }
	   return MoneyUtil.cent2Yuan(balances);
	}

	@Override
	public List<AccountGroupDTO> getOwnAccountGroups(String customerId) {
		List<Account> accounts = getAccountByCustomerId(customerId);
		return extractAccountGroups(accounts);
	}

	@Override
	public List<AccountGroupDTO> getOtherSupervisionAccountGroups(String customerId) {
		List<Account> accounts = getAccountBySupervisonCustomerId(customerId);
		return extractAccountGroups(accounts);
	}
	
	private List<Account> getAccountByCustomerId(String customerId) {
		AccountExample ex = new AccountExample();
		ex.createCriteria().andCompanyIdEqualTo(customerId);
		ex.setOrderByClause("id asc");
		List<Account> accounts = accountMapper.selectByExample(ex);
		return accounts;
	}
	
	private List<Account> getAccountBySupervisonCustomerId(String customerId) {
		AccountExample ex = new AccountExample();
		ex.createCriteria().andSupervisorIdEqualTo(customerId);
		ex.setOrderByClause("id asc");
		List<Account> accounts = accountMapper.selectByExample(ex);
		return accounts;
	}
	
	private List<AccountGroupDTO> extractAccountGroups(List<Account> accounts) {
		List<AccountGroupDTO> accountGroups = Lists.newArrayList();
		Date start = DateTime.now().minusDays(1).withTime(0, 0, 0, 0).toDate();
		Date end = DateTime.now().withTime(0, 0, 0, 0).toDate();
		if (CollectionUtils.isNotEmpty(accounts)) {
			for (Account account : accounts) {
				AccountGroupDTO group = new AccountGroupDTO();
				group.setCustomerId(account.getCompanyId());
				group.setMainAccountNo(null);// 平台主账号
				group.setBankUserName(null);// 对应平台主账号用户名
				group.setAccountNo(account.getAccountNo());

				AccountSupervisionExample ex = new AccountSupervisionExample();
				AccountSupervisionExample.Criteria criteria = ex.createCriteria();
				criteria.andAccountIdEqualTo(account.getId());
				criteria.andSupervisionStartTimeGreaterThanOrEqualTo(start);
				criteria.andSupervisionEndTimeLessThan(end);
				List<AccountSupervision> supervisions = accountSupervisionMapper.selectByExample(ex);
				List<Pair<Date, Date>> minMaxs = Lists.newArrayList();
				if (CollectionUtils.isNotEmpty(supervisions)) {
					for (AccountSupervision supervision : supervisions) {
						minMaxs.add(Pair.of(supervision.getSupervisionStartTime(), supervision.getSupervisionEndTime()));
					}
				}
				accountGroups.add(group);
			}
		}
		return accountGroups;
	}
	
	@Override
	public List<BankAccountGroupDTO> getBankAccountGroups() {
		List<BankAccountGroupDTO> groups = Lists.newArrayList();
		
		BankAccountGroupDTO group = new BankAccountGroupDTO();
		List<String> mainAccountNos = Lists.newArrayList();
		group.setBankUserName(null);
		group.setMainAccountNos(mainAccountNos);
		
		groups.add(group);
		return groups;
	}

	@Override
	public List<AccountTradeRecordDTO> getCustomerTradeRecord(AccountCustomerTradeRecordQueryDTO query) {
		AccountTradeRecordExample ex = new AccountTradeRecordExample();
		AccountTradeRecordExample.Criteria criteria = ex.createCriteria();
		criteria.andCompanyIdEqualTo(query.getCustomerId());
		if (query.getMinStartDate() != null) {
			criteria.andTradeStartTimeGreaterThanOrEqualTo(query.getMinStartDate());
		}
		if (query.getMaxStartDate() != null) {
			criteria.andTradeStartTimeLessThan(query.getMaxStartDate());
		}
		if (query.getMinEndDate() != null) {
			criteria.andTradeEndTimeGreaterThanOrEqualTo(query.getMinEndDate());
		}
		if (query.getMaxEndDate() != null) {
			criteria.andTradeEndTimeLessThan(query.getMaxEndDate());
		}
		if (CollectionUtils.isNotEmpty(query.getAccountTradeTypes())) {
			List<String> types = Lists.newArrayList();
			for (AccountTradeType type : query.getAccountTradeTypes()) {
				types.add(type.name());
			}
			criteria.andTradeTypeIn(types);
		}
		ex.setOrderByClause("id asc");
		ex.setLimitStart((query.getPage() - 1) * query.getPageSize());
		ex.setLimitEnd(query.getPageSize());
		List<AccountTradeRecord> records = accountTradeRecordMapper.selectByExample(ex);
		List<AccountTradeRecordDTO> recordDTOs = accountConverter.convertAccountTradeRecordList(records);
		return recordDTOs;
	}

}
