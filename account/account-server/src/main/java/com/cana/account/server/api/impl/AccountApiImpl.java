package com.cana.account.server.api.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.cana.account.api.IAccountApi;
import com.cana.account.dao.mapper.gen.AccountApplyMapper;
import com.cana.account.dao.mapper.gen.AccountMapper;
import com.cana.account.dao.po.Account;
import com.cana.account.dao.po.AccountApply;
import com.cana.account.dao.po.AccountApplyExample;
import com.cana.account.dao.po.AccountExample;
import com.cana.account.server.consts.BankListAndCityComponent;
import com.cana.account.server.converter.AccountApplyConverter;
import com.cana.account.service.IAccountApplyService;
import com.cana.account.service.IAccountMessageService;
import com.cana.account.service.IAccountService;
import com.cana.account.service.IAccountTradeService;
import com.cana.account.service.converter.AccountConverter;
import com.cana.account.service.transaction.IAccountAuthorityTransactionService;
import com.cana.account.service.transaction.IAccountSupervisionTransactionService;
import com.cana.account.service.transaction.IAccountTradeTransactionService;
import com.cana.account.service.transaction.IAccountTransactionService;
import com.cana.account.service.transaction.ICustomerTransactionService;
import com.cana.account.service.utils.AccountIDGenerator;
import com.cana.account.service.utils.BuyerNameRuleUtil;
import com.cana.member.dao.mapper.gen.UserMapper;
import com.cana.member.dao.po.User;
import com.cana.vbam.common.account.consts.AccountConsts;
import com.cana.vbam.common.account.dto.AccountAgentCreateDTO;
import com.cana.vbam.common.account.dto.AccountApplyAuditDetail;
import com.cana.vbam.common.account.dto.AccountApplyDTO;
import com.cana.vbam.common.account.dto.AccountApplyQueryCriteria;
import com.cana.vbam.common.account.dto.AccountBalancesAndNumberDTO;
import com.cana.vbam.common.account.dto.AccountCustomerTradeRecordQueryDTO;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.dto.AccountGroupDTO;
import com.cana.vbam.common.account.dto.AccountPrintCodeDTO;
import com.cana.vbam.common.account.dto.AccountPrintCodeResultDTO;
import com.cana.vbam.common.account.dto.AccountQueryCriteria;
import com.cana.vbam.common.account.dto.AccountSelfCreateDTO;
import com.cana.vbam.common.account.dto.AccountSupervisionCreateDTO;
import com.cana.vbam.common.account.dto.AccountTradeApplyDTO;
import com.cana.vbam.common.account.dto.AccountTradeApplyQueryCriteria;
import com.cana.vbam.common.account.dto.AccountTradeAuditResult;
import com.cana.vbam.common.account.dto.AccountTradeRecordBasicInfo;
import com.cana.vbam.common.account.dto.AccountTradeRecordCriteria;
import com.cana.vbam.common.account.dto.AccountTradeRecordDTO;
import com.cana.vbam.common.account.dto.BankAccountGroupDTO;
import com.cana.vbam.common.account.dto.BankBranchInfoDTO;
import com.cana.vbam.common.account.dto.BranchNameQueryCriteria;
import com.cana.vbam.common.account.dto.DeductFundRequestDTO;
import com.cana.vbam.common.account.dto.TransferFundForCreditRequestDTO;
import com.cana.vbam.common.account.dto.TransferFundRequestDTO;
import com.cana.vbam.common.account.dto.WithdrawFundRequestDTO;
import com.cana.vbam.common.account.enums.AccountApplyStatus;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.account.enums.AccountTradeType;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceDataDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.enums.IllegalBuyerType;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;

public class AccountApiImpl implements IAccountApi {
	private static final Logger logger = LoggerFactory.getLogger(AccountApiImpl.class);

    @Resource
    private IAccountApplyService accountApplyService;

	@Resource
	private UserMapper userMapper;

	@Resource
	private AccountMapper accountMapper;

	@Resource
	private AccountApplyMapper accountApplyMapper;

	@Resource
	private AccountApplyConverter accountApplyConverter;

	@Resource
	private IAccountTransactionService accountTransactionServiceImpl;

	@Resource
	private IAccountService accountService;

	@Resource
	private IAccountSupervisionTransactionService accountSupervisionTransactionService;
	
	@Resource
	private ICustomerTransactionService customerTransactionService;

	@Resource
	private IAccountTradeService accountTradeService;
	
    @Resource
	private IAccountTradeTransactionService accountTradeTransactionService;

    @Resource
    private IAccountMessageService accountMessageService;
    
    @Resource
    private IAccountAuthorityTransactionService accountAuthorityTransactionService;
    
    @Resource
	private AccountConverter accountConverter;
    
	@Override
	public PageResult<AccountApplyDTO> queryAccountApplys(AccountApplyQueryCriteria criteria) {
		AccountApplyExample example = getAccountApplyExample(criteria);
		if (example == null) {
			return new PageResult<AccountApplyDTO>(null, 0);
		}
		int total = accountApplyMapper.countByExample(example);
		int page = criteria.getPage() > 0 ? criteria.getPage() : 1;
		int size = criteria.getPageSize() > 0 ? criteria.getPageSize() : 10;
		example.setOrderByClause("create_time desc");
		example.setLimitStart((page - 1) * size);
		example.setLimitEnd(size);
		List<AccountApply> accountApplys = accountApplyMapper.selectByExample(example);
		List<AccountApplyDTO> accountApplyDTOs = accountApplyConverter.convertForList(accountApplys);
		return new PageResult<AccountApplyDTO>(accountApplyDTOs, total);
	}

	@Override
	public AccountApplyDTO getAccountApply(String accountApplyId) {
		AccountApply accountApply = accountApplyMapper.selectByPrimaryKey(accountApplyId);
		return accountApplyConverter.convertForDetail(accountApply);
	}
	
	@Override
	public void checkAccountApplyStatus(String accountApplyId){
		AccountApply accountApply = accountApplyMapper.selectByPrimaryKey(accountApplyId);
		if(!AccountApplyStatus.PENDINGAUDIT.name().equals(accountApply.getApplyStatus())){
			throw WebException.instance("账户不能审核");
		}
		return;
	}

	@Override
	public Map<String, List<String>> checkInvalidBuyerNames(String finaceName, List<String> buyerNames) {
		Map<String, List<String>> illegalBuyers = new HashMap<>();
		List<String> buyers = BuyerNameRuleUtil.trimBuyerNames(buyerNames);
		if (CollectionUtils.isNotEmpty(buyers)) {
			List<String> sameBuyerNames = checkSameBuyerNames(buyers);
			if (sameBuyerNames != null)
				illegalBuyers.put(IllegalBuyerType.SAME.name(), sameBuyerNames);
//			if (StringUtils.isNotBlank(finaceName)) {
//				User user = accountApplyService.findUserByCompanyName(finaceName);
//				if (user != null) {
//					List<String> existedBuyerNames = judgeInvalidBuyerNames(user, buyers);
//					if (existedBuyerNames != null)
//						illegalBuyers.put(IllegalBuyerType.EXISTED.name(), existedBuyerNames);
//				}
//			}
		}
		return illegalBuyers;
	}

	@Override
	public List<String> createAccountBySelf(AccountSelfCreateDTO self) {
		String customerId = self.getCustomerId();
		User customer = getUser(customerId);
		List<String> buyerNames = BuyerNameRuleUtil.trimBuyerNames(self.getBuyerNames());
		Integer accountNumber = checkBuyerNames(customer, self.getAccountNumber(), buyerNames, false);
		List<Account> accounts = batchCreateAccountsSelf(customer, accountNumber, buyerNames);
		accountMessageService.sendMailForCreateAccountBySelf(customer, accounts);
		List<String> accountNos = Lists.newArrayList();
		for (Account account : accounts) {
			accountNos.add(account.getAccountNo());
		}
		return accountNos;
	}

	@Override
	public void createAccountByAgent(AccountAgentCreateDTO agentDTO) {
		verifyAllExcludeBuyer(agentDTO);
		User agentUser = getUser(agentDTO.getAgentCompanyId());
		if (!UserType.FACTOR.name().equals(agentUser.getUserType())) {
		    throw WebException.instance("当前客户不允许代开户");
		}
		User accountUser = customerTransactionService.findCustomerByCompanyNameAndUserType(agentDTO.getCompanyName(),UserType.FINACE);
		// 检查用户类型
		switch (UserType.valueOf(agentDTO.getUserType())) {
		case FINACE:
//			if (accountUser != null && !UserType.FINACE.name().equals(accountUser.getUserType()))
//				throw WebException.instance("企业已注册为其他类型，不能再注册为融资商企业。");
			break;
		default:
			throw WebException.instance("不存在的客户类型");
		}
		
		List<String> buyerNames = BuyerNameRuleUtil.trimBuyerNames(agentDTO.getBuyerNames());
		Integer accountNumber = checkBuyerNames(agentUser, agentDTO.getAccountNumber(), buyerNames, true);
		agentDTO.setAccountNumber(accountNumber);
		String applyId = accountTransactionServiceImpl.createAccountApplyByAgent(agentUser, accountUser, agentDTO);
		accountMessageService.sendNotificationForAccountApply(agentUser.getId(), applyId);
	}

    @Override
	public void auditAccountApply(AccountApplyAuditDetail detail) {
		if (StringUtils.isAnyBlank(detail.getAuditorId(), detail.getAccountApplyId()))
			throw WebException.instance("参数不可为空");
		User auditor = userMapper.selectByPrimaryKey(detail.getAuditorId());
		if (auditor == null)
			throw WebException.instance("客户不存在");
		AccountApply apply = accountAuthorityTransactionService.checkAuditAgentApplyAuthority(detail);
		List<Pair<String, String>> seqAndNos = Lists.newArrayList();
		if (AccountApplyStatus.ACCEPTED.equals(detail.getEnumStatus())
				&& StringUtils.isNotBlank(apply.getAgentCompanyId())) {
			seqAndNos = batchCreateAccountsAgent(apply);
		}
		Boolean isNeedMail = accountTransactionServiceImpl.auditAccountApplyAndCreateAccount(auditor, detail,
				seqAndNos);
		accountMessageService.sendMailForCreateAccountByAgent(detail.getAccountApplyId(), isNeedMail);
	}

    /**
     * 
     */
	private List<Pair<String, String>> batchCreateAccountsAgent(AccountApply apply) {
		List<String> buyerNames = Lists.newArrayList();
		int accountNum;
		if (StringUtils.isNotBlank(apply.getBuyerNames())) {
			buyerNames = Lists.newArrayList(apply.getBuyerNames().split(AccountConsts.SEMICOLON));
			accountNum = buyerNames.size();
		} else {
			accountNum = apply.getAccountNumber();
		}
		if (AccountType.SPECIAL.name().equals(apply.getAccountType())
		        && StringUtils.isBlank(apply.getSupervisionAccountId())) {
			accountNum++;
		}
		List<Pair<String, String>> seqAndNos = accountService.batchCreateBankAccount(accountNum, apply.getCompanyName());
		return seqAndNos;
	}
	
    /**
     * 返回创建成功的账户数
     */
    private List<Account> batchCreateAccountsSelf(User user, int accountNum, List<String> buyerNames) {
    	if(CollectionUtils.isNotEmpty(buyerNames)){
    		accountNum = buyerNames.size();
    	}
    	List<Pair<String, String>> seqAndNos = accountService.batchCreateBankAccount(accountNum, user.getCompanyName());
    	List<Account> accounts = accountTransactionServiceImpl.batchCreateAccountSelf(user, seqAndNos, buyerNames);
    	return accounts;
    }
    
    @Override
	public PageResult<AccountDTO> queryAccounts(String customerId,
	        AccountQueryCriteria accountQueryCriteria) {
		return accountTransactionServiceImpl.queryAccounts(customerId, accountQueryCriteria);
	}

	@Override
	public AccountDTO getAccount(String customerId, String accountId) {
	    return accountTransactionServiceImpl.getAccount(customerId, accountId);
	}
	@Override
	public AccountDTO getOwnAccountByNo(String customerId, String accountNo) {
	    return accountTransactionServiceImpl.getOwnAccountByNo(customerId, accountNo);
	}
	@Override
	public List<AccountDTO> getAccountByNos(String customerId,String... accountNos) {
		List<String> accountNoList = Lists.newArrayList(accountNos);
		if (CollectionUtils.isEmpty(accountNoList))
			return null;
        return accountTransactionServiceImpl.getAccountByNos(customerId, accountNoList);
	}

	@Override
	public PageResult<AccountTradeRecordDTO> getTradeRecord(String customerId, AccountTradeRecordCriteria accountTradeRecordCriteria) {
		return accountTransactionServiceImpl.getTradeRecord(customerId, accountTradeRecordCriteria);
	}
	
	@Override
	public boolean accountFreeze(String userId, String customerId, String accountId) {
		return accountTransactionServiceImpl.accountFreeze(userId, customerId,accountId);
	}

	@Override
	public boolean accountUnfreeze(String userId, String customerId, String accountId) {
		return accountTransactionServiceImpl.accountUnfreeze(userId, customerId,accountId);
	}
	
	@Override
	public boolean accountSetDefaultRepayment(String customerId, String accountId) {
		return accountTransactionServiceImpl.accountSetDefaultRepayment(customerId,accountId);
	}
	
	@Override
	public PageResult<AccountTradeRecordDTO> queryTradeRecords(String customerId,AccountTradeRecordCriteria criteria) {
		return accountTransactionServiceImpl.queryTradeRecords(customerId,criteria);
	}
	
	private AccountApplyExample getAccountApplyExample(AccountApplyQueryCriteria criteria) {
		AccountApplyExample example = new AccountApplyExample();
		AccountApplyExample.Criteria criteriaDb = example.createCriteria();

		if (StringUtils.isNotBlank(criteria.getUsername()))
			criteriaDb.andUsernameLike("%" + criteria.getUsername().trim() + "%");
		if (criteria.getUserType() != null)
			criteriaDb.andUserTypeEqualTo(criteria.getUserType().name());
		if (StringUtils.isNotBlank(criteria.getCompanyName()))
			criteriaDb.andCompanyNameLike("%" + criteria.getCompanyName().trim() + "%");

		if (StringUtils.isNotBlank(criteria.getAccountNo())) {
			AccountExample accountExample = new AccountExample();
			accountExample.createCriteria().andAccountNoEqualTo(criteria.getAccountNo());
			List<Account> accounts = accountMapper.selectByExample(accountExample);
			if (CollectionUtils.isEmpty(accounts) || StringUtils.isBlank(accounts.get(0).getAccountApplyId())) {
				return null;
			}
			criteriaDb.andIdEqualTo(accounts.get(0).getAccountApplyId());
		}
		if (StringUtils.isNotBlank(criteria.getBeginTime())) {
			DateTime date = DateTimeUtil.parseDate10(criteria.getBeginTime());
			criteriaDb.andCreateTimeGreaterThanOrEqualTo(date.toDate());
		}
		if (StringUtils.isNotBlank(criteria.getEndTime())) {
			DateTime date = DateTimeUtil.parseDate10(criteria.getEndTime());
			date = date.plusDays(1);
			criteriaDb.andCreateTimeLessThan(date.toDate());
		}

		if (criteria.getIsAuditPassed() != null) {
			if (criteria.getIsAuditPassed() == true)
				criteriaDb.andApplyStatusEqualTo(AccountApplyStatus.ACCEPTED.name());
			else
				criteriaDb.andApplyStatusEqualTo(AccountApplyStatus.REJECTED.name());
		} else {
			if (criteria.getIsAudited() != null) {
				if (criteria.getIsAudited() == true)
					criteriaDb.andApplyStatusIn(
							Lists.newArrayList(AccountApplyStatus.ACCEPTED.name(), AccountApplyStatus.REJECTED.name()));
				else
					criteriaDb.andApplyStatusEqualTo(AccountApplyStatus.PENDINGAUDIT.name());
			}
		}
		return example;
	}

	/**
	 * 检查开户数量，和买方数量是否匹配，开户名称是否合法（不能重名）
	 * 
	 * @param accountNumber
	 * @param buyers
	 * @param user
	 */
	private Integer checkBuyerNames(User customer, Integer accountNum, List<String> buyerNames, boolean isAgent) {
		if (CollectionUtils.isNotEmpty(buyerNames)) {
			if (isAgent) {
				List<String> sameBuyerNames = checkSameBuyerNames(buyerNames);
				if (CollectionUtils.isNotEmpty(sameBuyerNames)) {
					throw WebException.instance("存在买方企业重名" + StringUtils.join(sameBuyerNames, AccountConsts.SEMICOLON));
				}
			}
			return buyerNames.size();
		} else if (accountNum != null) {
			if (UserType.FACTOR.name().equals(customer.getUserType())) {// ?3:10
				if (isAgent && accountNum != 1) {
					throw WebException.instance("保理商代开户最多只能开1个一般账户!");
				} else if (accountNum < 1 || accountNum > 3) {
					throw WebException.instance("保理商最多只能开3个一般账户!");
				}
			} else {
				if (accountNum < 1 || accountNum > 10) {
					throw WebException.instance("融资商最多只能开10个一般账户!");
				}
			}
			return accountNum;
		} else {
			logger.error("买方名称为空或创建账户数量为空，数据非法!");
			throw WebException.instance("买方名称为空或创建账户数量为空，数据非法!");
		}
	}

	/**
	 * 根据用户ID来获取用户信息，不存在则抛出RuntimeException
	 * @param UserId 用户ID
	 * @return 返回用户信息
	 */
	private User getUser(String UserId) {
		if (StringUtils.isBlank(UserId))
			throw WebException.instance("客户ID不可为空");
		User user = userMapper.selectByPrimaryKey(UserId);
		if (user == null)
			throw WebException.instance("客户不存在");
		return user;
	}

	private List<String> checkSameBuyerNames(List<String> buyerNames) {
		List<String> sameBuyerNames = Lists.newArrayList();
		List<String> uniqueBuyerNames = Lists.newArrayList();
		for (String buyerName : buyerNames)
			if (uniqueBuyerNames.contains(buyerName)) {
				if (!sameBuyerNames.contains(buyerName))
					sameBuyerNames.add(buyerName);
			} else
				uniqueBuyerNames.add(buyerName);
		return sameBuyerNames.isEmpty() ? null : sameBuyerNames;
	}

	/**
	 * 检查代开户数据（除了开户数量和买家名称）
	 * @param accountAgentCreateDTO
	 */
	private void verifyAllExcludeBuyer(AccountAgentCreateDTO accountAgentCreateDTO) {
		if(StringUtils.isBlank(accountAgentCreateDTO.getUserType()))
			throw WebException.instance("客户类型不能为空");
		if (StringUtils.isBlank(accountAgentCreateDTO.getAuthorizationLetterId()))
			throw WebException.instance("授权书不能为空");
		if (StringUtils.isBlank(accountAgentCreateDTO.getCompanyName()))
			throw WebException.instance("企业名称不能为空");
		if (StringUtils.isBlank(accountAgentCreateDTO.getOrganizationCode()))
			throw WebException.instance("组织机构号码不能为空");
		if (StringUtils.isBlank(accountAgentCreateDTO.getOrganizationCodeCertificateMediaId()))
			throw WebException.instance("组织结构证不能为空");
		if (StringUtils.isBlank(accountAgentCreateDTO.getBusinessLicenceCode()))
			throw WebException.instance("营业执照号码不能为空");
		if (StringUtils.isBlank(accountAgentCreateDTO.getBusinessLicenceMediaId()))
			throw WebException.instance("营业执照不能为空");
		if (StringUtils.isBlank(accountAgentCreateDTO.getTaxRegistrationCertificateCode()))
			throw WebException.instance("税务登记号码不能为空");
		if (StringUtils.isBlank(accountAgentCreateDTO.getTaxRegistrationCertificateMediaId()))
			throw WebException.instance("税务登记证不能为空");
		if (StringUtils.isBlank(accountAgentCreateDTO.getLegalPersonIdentityCardFrontMediaId()))
			throw WebException.instance("法人代表身份证正面照");
		if (StringUtils.isBlank(accountAgentCreateDTO.getLegalPersonIdentityCardBackMediaId()))
			throw WebException.instance("法人代表身份证背面照");
		if (StringUtils.isBlank(accountAgentCreateDTO.getContactName()))
			throw WebException.instance("联系人姓名不能为空");
		if (StringUtils.isBlank(accountAgentCreateDTO.getContactIdentityCardFrontMediaId()))
			throw WebException.instance("联系人身份证正面照");
		if (StringUtils.isBlank(accountAgentCreateDTO.getContactIdentityCardBackMediaId()))
			throw WebException.instance("联系人身份证背面照");
		if (StringUtils.isBlank(accountAgentCreateDTO.getContactMail())
				&& Pattern.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$",
						accountAgentCreateDTO.getContactMail()))
			throw WebException.instance("联系人邮件格式不正确");
		if (StringUtils.isBlank(accountAgentCreateDTO.getContactTel())
				&& Pattern.matches("[1][0-9]{11}", accountAgentCreateDTO.getContactTel()))
			throw WebException.instance("联系人电话格式不正确");
	}

    @Override
    public String createSupervision(String userId,
            AccountSupervisionCreateDTO supervisionCreateDTO) {
        String tradeApplyId = accountSupervisionTransactionService.createSupervision(userId, supervisionCreateDTO);
        accountMessageService.sendNotificationForTradeApply(userId, tradeApplyId);
        return tradeApplyId;
        
    }

    @Override
    public String removeSupervision(String userId,
            List<String> accountIds) {
        String tradeApplyId = accountSupervisionTransactionService.removeSupervision(userId, accountIds);
        accountMessageService.sendNotificationForTradeApply(userId, tradeApplyId);
        return tradeApplyId;
    }

    @Override
    public boolean auditSupervision(String auditUserId,
            String supervisionApplyId, boolean isAgree, String message) {
        return accountSupervisionTransactionService.auditSupervision(auditUserId,
                supervisionApplyId, isAgree, message);
    }

    @Override
    public AccountTradeStatus transferFund(TransferFundRequestDTO transferFundRequest) {
    	return accountTradeService.transferFund(transferFundRequest);
    }

    @Override
    public AccountTradeStatus withdrawFund(WithdrawFundRequestDTO withdrawFundRequest) {
    	return accountTradeService.withdrawFund(withdrawFundRequest);
    }
    
    @Override
    public AccountTradeStatus withdrawFundForYundaEx(WithdrawFundRequestDTO withdrawFundRequest){
    	return accountTradeService.withdrawFundForYundaEx(withdrawFundRequest);
    }

    @Override
    public List<AccountDTO> queryRepaymentAccounts(String factorId,
            String finaceName) {
        return accountTransactionServiceImpl.queryRepaymentAccounts(factorId, finaceName);
    }

	@Override
	public void auditTransferFund(AccountTradeAuditResult auditResult) {
		accountTradeService.auditTransferFund(auditResult);
	}

	@Override
	public void auditWithdrawFund(AccountTradeAuditResult auditResult) {
		accountTradeService.auditWithdrawFund(auditResult);
	}

	@Override
	public  List<BankAccountBalanceDataDTO>  queryAccountBalance(String customerId,String... accountNos) {
		return accountTradeService.queryAccountBalance(customerId,accountNos);
	}

    @Override
    public PageResult<AccountTradeApplyDTO> queryTradeApplys(String userId,
            AccountTradeApplyQueryCriteria criteria) {
        return accountTradeTransactionService.queryTradeApplys(userId, criteria);
    }

    @Override
    public AccountTradeApplyDTO getSupervisionApply(String userId,
            String supervisionApplyId) {
        return accountSupervisionTransactionService.getSupervisionApply(userId, supervisionApplyId);
    }

	@Override
	public void checkAccountTradeAble(String customerId, String accountNo, AccountTradeType... types) {
		accountTradeService.checkAccountTradeAble(customerId, accountNo,types);
	}

	@Override
	public AccountTradeApplyDTO getTradeApply(String userId, String applyId) {
		return accountTradeTransactionService.queryTradeApply(userId, applyId);
	}

	@Override
	public AccountDTO getOwnAccountByNameAndNo(String customerName, String accountNo) {
		return accountTransactionServiceImpl.getOwnAccountByNameAndNo(customerName, accountNo);
	}

    @Override
    public AccountTradeStatus deductFund(DeductFundRequestDTO request) {
        return accountTradeService.deductFund(request);
    }

    @Override
    public AccountTradeStatus transferFundForCredit(TransferFundForCreditRequestDTO request) {
        return accountTradeService.transferFundForCredit(request);
    }

    @Override
    public List<CustomerDetailDTO> querySupervisorsByFactorId(String factorId) {
        return accountTransactionServiceImpl.querySupervisorsByFactorId(factorId);
    }

	@Override
	public AccountBalancesAndNumberDTO getAccountBalancesAndNumber(String customerId) {
		return accountTransactionServiceImpl.getAccountBalancesAndNumber(customerId);
	}

    @Override
    public boolean setFactorTransferInAccount(String factorId, String accountNo) {
        return accountTransactionServiceImpl.setFactorTransferInAccount(factorId, accountNo);
    }

	@Override
	public List<AccountGroupDTO> getOwnAccountGroups(String customerId) {
		return accountTransactionServiceImpl.getOwnAccountGroups(customerId);
	}

	@Override
	public List<AccountGroupDTO> getOtherSupervisionAccountGroups(String customerId) {
		return accountTransactionServiceImpl.getOtherSupervisionAccountGroups(customerId);
	}
	
	@Override
	public boolean createSupervisionWithoutAudit(String applyUserId, String accountNo, String supervisionCustomerId) {
	    return accountSupervisionTransactionService.createSupervisionWithoutAudit(applyUserId, accountNo, supervisionCustomerId);
	}

	@Override
	public List<BankAccountGroupDTO> getBankAccountGroups() {
		return accountTransactionServiceImpl.getBankAccountGroups();
	}

	@Override
	public List<AccountTradeRecordDTO> getCustomerTradeRecord(AccountCustomerTradeRecordQueryDTO query) {
		return accountTransactionServiceImpl.getCustomerTradeRecord(query);
	}

	@Override
	public Map<String, String> batchQueryTradeStatus(List<String> businessSeqs) {
		return accountTradeService.batchQueryTradeStatus(businessSeqs);
	}
	
	@Override
	public String queryTradeStatus(String business) {
		return accountTradeService.queryTradeStatus(business);
	}
	
	@Override
	public String generateBusinessSeq() {
		return AccountIDGenerator.generateBusinessSeq();
	}

	@Override
	public ListResult<BankBranchInfoDTO> queryBranchInfo(BranchNameQueryCriteria queryCriteria) {
		return ListResult.success(accountService.queryqueryBranchInfo(queryCriteria), 0);
	}

	@Override
	public List<String> getCitiesByProvince(String province) {
		List<String> cityList = BankListAndCityComponent.cityMap.get(province);
		if( null == cityList || cityList.size() == 0){
			cityList = Lists.newArrayList();
			cityList.add(province);
			return cityList;
		}else{
			return cityList;
		}
	}

	@Override
	public void relaunchingWithdrawOperate(String tradeRecordId) throws Exception {
		accountTradeService.relaunchingWithdrawOperate(tradeRecordId);
	}

	@Override
	public AccountTradeRecordBasicInfo queryTradeRecordBasicInfo(String business) {
		return accountTradeService.queryTradeRecordBasicInfo(business);
	}

	@Override
	public List<AccountPrintCodeResultDTO> queryAccountPrintCode(AccountPrintCodeDTO codeDTO) throws Exception {
		return accountTradeService.queryBankTradeDetailNonLogin(codeDTO);
	}

}
