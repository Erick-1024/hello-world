package com.cana.account.service.transaction.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.account.dao.mapper.AccountTableLockMapper;
import com.cana.account.dao.mapper.AccountTradeApplyCustomMapper;
import com.cana.account.dao.mapper.gen.AccountMapper;
import com.cana.account.dao.mapper.gen.AccountTradeApplyMapper;
import com.cana.account.dao.mapper.gen.AccountTradeRecordMapper;
import com.cana.account.dao.mapper.gen.BankBranchInfoMapper;
import com.cana.account.dao.po.Account;
import com.cana.account.dao.po.AccountTradeApply;
import com.cana.account.dao.po.AccountTradeRecord;
import com.cana.account.dao.po.AccountTradeRecordExample;
import com.cana.account.dao.po.BankBranchInfoExample;
import com.cana.account.service.converter.AccountTradeApplyConverter;
import com.cana.account.service.transaction.IAccountAuthorityTransactionService;
import com.cana.account.service.transaction.IAccountTradeTransactionService;
import com.cana.account.service.transaction.ICustomerTransactionService;
import com.cana.account.service.utils.AccountIDGenerator;
import com.cana.account.service.utils.AccountTradeApplyQueryCriteriaUtil;
import com.cana.member.dao.po.User;
import com.cana.vbam.common.account.dto.AccountTradeApplyDTO;
import com.cana.vbam.common.account.dto.AccountTradeApplyQueryCriteria;
import com.cana.vbam.common.account.dto.AccountTradeAuditResult;
import com.cana.vbam.common.account.dto.DeductFundRequestDTO;
import com.cana.vbam.common.account.dto.TransferFundForCreditRequestDTO;
import com.cana.vbam.common.account.dto.TransferFundRequestDTO;
import com.cana.vbam.common.account.dto.WithdrawFundRequestDTO;
import com.cana.vbam.common.account.enums.AccountStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountTradeApplyStatus;
import com.cana.vbam.common.account.enums.AccountTradeApplyType;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.account.enums.AccountTradeType;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.account.enums.TradeRuleResult;
import com.cana.vbam.common.bankgate.dto.response.BankAccountTradeFlowDataDTO;
import com.cana.vbam.common.bankgate.dto.response.TradeStatusResultDTO;
import com.cana.vbam.common.bankgate.enums.BankTranStatus;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.utils.FeeCalculateUtil;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;

@Service
public class AccountTradeTransactionServiceImpl implements IAccountTradeTransactionService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ICustomerTransactionService customerTransactionService;
    @Resource
    private IAccountAuthorityTransactionService accountAuthorityTransactionService;
    @Resource
    private AccountTableLockMapper accountTableLockMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AccountTradeApplyMapper accountTradeApplyMapper;
    @Resource
    private AccountTradeApplyCustomMapper accountTradeApplyCustomMapper;
    @Resource
    private AccountTradeRecordMapper accountTradeRecordMapper;
    @Resource
    private AccountTradeApplyConverter accountTradeApplyConverter;
    @Resource
    private BankBranchInfoMapper bankBranchInfoMapper;

    @Override
	public Pair<TradeRuleResult, String> transferFund(String bizSeq, TransferFundRequestDTO request) {
        if (StringUtils.isBlank(request.getAmount()))
            throw WebException.instance("转账金额不能为空");
        long amount = MoneyUtil.yuan2Cent(request.getAmount());
        String remark = request.getRemark();
        if (amount <= 0)
            throw WebException.instance("转账金额必需为正");
        String customerId = customerTransactionService.getCustomerIdByUserId(request.getUserId());
		User customer = customerTransactionService.checkCustomerIsValid(customerId);
		Account outAccount = accountTableLockMapper.lockAccountByAccountNo(request.getAccountNo());
		Account inAccount = accountTableLockMapper.lockAccountByAccountNo(request.getReceiveAccountNo());
		throwExceptionIfEmpty(outAccount, "转出账号不存在!");
		throwExceptionIfEmpty(inAccount, "收款账号不存在!");
		TradeRuleResult rule = accountAuthorityTransactionService.checkTransferAuthority(customer, outAccount, inAccount);
		String applyId = null;
		if(rule == TradeRuleResult.ACCEPT){
			createTradeRecord(bizSeq, request,outAccount,inAccount);
		}else if(rule == TradeRuleResult.NEED_AUDIT){
		    applyId = createTradeApplyForTransferFund(customer, outAccount, inAccount, amount,remark);
		}
		return Pair.of(rule, applyId);
	}
    
    private String createTradeApplyForTransferFund(User applyCustomer,
            Account account, Account receiveAccount, long amount,String remark) {

        AccountTradeApply apply = initTradeApplyNoReceive(applyCustomer, account);
        apply.setOppositeAccountId(receiveAccount.getId());
        apply.setOppositeAccountNo(receiveAccount.getAccountNo());
        apply.setOppositeAccountName(receiveAccount.getCompanyName());
        apply.setTradeType(AccountTradeApplyType.TRANSFER_FUND.name());
        apply.setAmount(amount);
        apply.setCharge(0l);
        apply.setRemark(remark);
        accountTradeApplyMapper.insert(apply);
        
        account.setAccountStatus(AccountStatus.HANDLING.name());
        accountMapper.updateByPrimaryKeySelective(account);

        return apply.getId();
    }

    private AccountTradeApply initTradeApplyNoReceive(User applyCustomer,Account account){
    	AccountTradeApply apply = new AccountTradeApply();
        apply.setId(AccountIDGenerator.generateAccountTradeApplyId());
        apply.setAccountId(account.getId());
        apply.setAccountName(account.getCompanyName());
        apply.setAccountNo(account.getAccountNo());
        apply.setStatus(AccountTradeApplyStatus.PENDINGAUDIT.name());
        apply.setCreateTime(new Date());
        apply.setApplyCompanyId(applyCustomer.getId());
        apply.setApplyCompanyName(applyCustomer.getCompanyName());
        if (account.getCompanyId().equals(applyCustomer.getId())) {
            apply.setAuditCompanyId(account.getSupervisorId());
        } else {
            apply.setAuditCompanyId(account.getCompanyId());
        }
        return apply;
    }

    private void createTradeRecord(String bizSeq, TransferFundRequestDTO transferFundRequest,Account account,Account receiveAccount) {
    	String userId = transferFundRequest.getUserId();
    	String remark = transferFundRequest.getRemark();
    	Long amount = MoneyUtil.yuan2Cent(transferFundRequest.getAmount());
    	createTradeRecord(userId,AccountTradeType.TRANSFER_FUND,bizSeq,account,receiveAccount,-amount, false,remark);
    	createTradeRecord(userId,AccountTradeType.TRANSFER_FUND,bizSeq,receiveAccount,account,+amount, false,remark);
    }

	private void createTradeRecord(String uerId,
	        AccountTradeType type,String bizSeq,
	        Account account, Account receiveAccount,
	        Long amount, boolean isDeductFund,String remark) {
		AccountTradeRecord record = new AccountTradeRecord();
		record.setId(AccountIDGenerator.generateAccountTradeRecordId());
		record.setCompanyId(account.getCompanyId());
		record.setAccountType(account.getAccountType());
		record.setAccountId(account.getId());
		record.setAccountNo(account.getAccountNo());
		record.setAccountName(account.getCompanyName());
		record.setAccountType(account.getAccountType());
		record.setOppositeAccountId(receiveAccount.getId());
		record.setOppositeAccountNo(receiveAccount.getAccountNo());
		record.setOppositeAccountName(receiveAccount.getCompanyName());
		record.setAccountSupervisionId(account.getAccountSupervisionId());
		record.setRemark(remark);
		record.setTradeType(type.name());
		if (isDeductFund) {
		    record.setOperateType(amount < 0 ? "自动转出" : "自动转入");
		} else {
            record.setOperateType(amount < 0 ? "手工转出" : "手工转入");
		}
		record.setAmount(amount);
		record.setStatus(AccountTradeStatus.BANKAPI_REQUEST.name());
		record.setBusinessSeq(bizSeq);
		record.setTradeStartTime(new Date());
		record.setOperateUserId(uerId);
		record.setUserType(account.getUserType());
		accountTradeRecordMapper.insert(record);
	}
	
    @Override
    public void updateTradeRecordStatus(String businessSeq, AccountTradeStatus tradeStatus) {
        List<AccountTradeRecord> records = accountTableLockMapper.lockTradeRecordBySeq(businessSeq);
        for (AccountTradeRecord record : records) {
            if ((AccountTradeStatus.BANKAPI_REQUEST.name().equals(record.getStatus())
                        || AccountTradeStatus.TRADE_HANDLING.name().equals(record.getStatus()))
                    && (AccountTradeStatus.TRADE_FAIL.equals(tradeStatus)
                            || AccountTradeStatus.TRADE_SUCCESS.equals(tradeStatus))) {
                record.setTradeEndTime(new Date());
            }
            record.setStatus(tradeStatus.name());
            accountTradeRecordMapper.updateByPrimaryKeySelective(record);
        }
    }

	@Override
	public TransferFundRequestDTO auditTransferFundApply(String bizSeq, AccountTradeAuditResult audit) {
		AccountTradeApply apply = accountTableLockMapper.lockAccountTradeApplyById(audit.getAccountTradeApplyId());
		String customerId = customerTransactionService.getCustomerIdByUserId(audit.getAuditUserId());
		Account account = accountTableLockMapper.lockAccountById(apply.getAccountId());
		Account receiveAccount = accountTableLockMapper.lockAccountById(apply.getOppositeAccountId());
		checkTransferAuditAuthority(customerId, apply);
		updateApplyStatusAndAccountStatus(apply, audit,account);
		if (AccountTradeApplyStatus.ACCEPTED.equals(audit.getAuditStatus())) {
    		TransferFundRequestDTO request = new TransferFundRequestDTO();
    		request.setAccountNo(apply.getAccountNo());
    		request.setAmount(MoneyUtil.cent2Yuan(apply.getAmount()));
    		request.setReceiveAccountNo(apply.getOppositeAccountNo());
    		request.setRemark(apply.getRemark());
    		createTradeRecord(bizSeq, request,account,receiveAccount);
    		return request;
		}
		return null;
	}

	private void checkTransferAuditAuthority(String customerId, AccountTradeApply apply) {
	    checkTradeApplyAuditAuthority(customerId, apply);
        if (!AccountTradeApplyType.TRANSFER_FUND.name().equals(apply.getTradeType())) {
            throw WebException.instance("申请类型错误");
        }
	}
	private void checkTradeApplyAuditAuthority(String customerId, AccountTradeApply apply) {
        if (apply == null) {
            throw WebException.instance("申请不存在");
        }
        if (!apply.getAuditCompanyId().equals(customerId)) {
            throw WebException.instance("无权审核该申请");
        }
        if (!AccountTradeApplyStatus.PENDINGAUDIT.name().equals(apply.getStatus())) {
            throw WebException.instance("重复提交");
        }
    }
	private void checkWithdrawAuditAuthority(String customerId, AccountTradeApply apply) {
	    checkTradeApplyAuditAuthority(customerId, apply);
        if (!AccountTradeApplyType.WITHDRAW_FUND.name().equals(apply.getTradeType())) {
            throw WebException.instance("申请类型错误");
        }
    }

	@Override
	public WithdrawFundRequestDTO auditWithdrawFundApply(String bizSeq, AccountTradeAuditResult audit) {
		AccountTradeApply apply = accountTableLockMapper.lockAccountTradeApplyById(audit.getAccountTradeApplyId());
		Account outAccount = accountTableLockMapper.lockAccountById(apply.getAccountId());
		String customerId = customerTransactionService.getCustomerIdByUserId(audit.getAuditUserId());
		checkWithdrawAuditAuthority(customerId, apply);
		updateApplyStatusAndAccountStatus(apply,audit,outAccount);
		if (AccountTradeApplyStatus.ACCEPTED.equals(audit.getAuditStatus())) {
    		WithdrawFundRequestDTO request = new WithdrawFundRequestDTO();
    		request.setAccountNo(apply.getAccountNo());
    		request.setAmount(MoneyUtil.cent2Yuan(apply.getAmount()));
    		request.setReceiveAccountNo(apply.getOppositeAccountNo());
    		request.setReceiveBankAddress(apply.getWithdrawBankAddress());
    		request.setLianHangNo(apply.getLianHangNo());
    		request.setReceiveBankName(apply.getWithdrawBank());
    		request.setRemark(apply.getRemark());
    		createTradeRecordForWithdrawFund(bizSeq, request);
    		return request;
		}
		return null;
	}
	
	/**
	 * 保存审核结果
	 */
	private void updateApplyStatusAndAccountStatus(AccountTradeApply apply,AccountTradeAuditResult audit,Account outAccount){
		outAccount.setAccountStatus(AccountStatus.NORMAL.name());
		accountMapper.updateByPrimaryKeySelective(outAccount);
		apply.setAuditTime(new Date());
		apply.setAuditUserId(audit.getAuditUserId());
		apply.setAuditMessage(audit.getAuditMessage());
		apply.setStatus(audit.getAuditStatus().name());
		accountTradeApplyMapper.updateByPrimaryKeySelective(apply);
	}
	
	@Override
	public Pair<TradeRuleResult, String> withdrawFund(String bizSeq, WithdrawFundRequestDTO request) {
        if (StringUtils.isBlank(request.getAmount()))
            throw WebException.instance("提现金额不能为空");
        if (MoneyUtil.yuan2Cent(request.getAmount()) <= 0)
            throw WebException.instance("提现金额必需为正");
	    String customerId = customerTransactionService.getCustomerIdByUserId(request.getUserId());
	    User customer = customerTransactionService.checkCustomerIsValid(customerId);
		Account account = accountTableLockMapper.lockAccountByAccountNo(request.getAccountNo());
		throwExceptionIfEmpty(account, "提现账号不存在!");
		TradeRuleResult rule = accountAuthorityTransactionService.checkWithdrawAuthority(customer, account);
		String applyId = null;
		if(rule == TradeRuleResult.ACCEPT){
			createTradeRecordForWithdrawFund(bizSeq, request);
		}else if(rule == TradeRuleResult.NEED_AUDIT){
		    applyId = createTradeApplyForWithdrawFund(customer, account, request);
		}
		return Pair.of(rule, applyId);
	}

	@Override
	public void withdrawFundForYundaEx(WithdrawFundRequestDTO request) {
		if (StringUtils.isBlank(request.getAmount()))
			throw WebException.instance("转账金额不能为空");
		if (MoneyUtil.yuan2Cent(request.getAmount()) <= 0)
			throw WebException.instance("提现金额必需为正");
		Account account = accountTableLockMapper.lockAccountByAccountNo(request.getAccountNo());
		throwExceptionIfEmpty(account, "提现账号不存在!");
		createTradeRecordForWithdrawFundEx(request.getBusinessSeq(), request);
	}
	
	private void createTradeRecordForWithdrawFund(String bizSeq, WithdrawFundRequestDTO request){
		Account account = accountTableLockMapper.lockAccountByAccountNo(request.getAccountNo());
		AccountTradeRecord record = new AccountTradeRecord();
		record.setId(AccountIDGenerator.generateAccountTradeRecordId());
		record.setAccountId(account.getId());
		record.setAccountNo(account.getAccountNo());
		record.setCompanyId(account.getCompanyId());
		record.setAccountName(account.getCompanyName());
        record.setAccountType(account.getAccountType());
		record.setOppositeAccountNo(request.getReceiveAccountNo());
		record.setOppositeAccountName(account.getCompanyName());
		record.setWithdrawBank(request.getReceiveBankName());
		record.setWithdrawBankAddress(request.getReceiveAccountName());
		record.setAccountSupervisionId(account.getAccountSupervisionId());
		record.setTradeType(AccountTradeType.WITHDRAW_FUND.name());
		record.setOperateType("手工转出");
		record.setAmount(-MoneyUtil.yuan2Cent(request.getAmount()));
		record.setStatus(AccountTradeStatus.BANKAPI_REQUEST.name());
		record.setBusinessSeq(bizSeq);
		record.setTradeStartTime(new Date());
		record.setRemark(request.getRemark());
		record.setUserType(account.getUserType());
		accountTradeRecordMapper.insert(record);
	}
	
	private void createTradeRecordForWithdrawFundEx(String bizSeq, WithdrawFundRequestDTO request){
		Account account = accountTableLockMapper.lockAccountByAccountNo(request.getAccountNo());
		AccountTradeRecord record = new AccountTradeRecord();
		record.setId(AccountIDGenerator.generateAccountTradeRecordId());
		record.setAccountId(account.getId());
		record.setAccountNo(account.getAccountNo());
		record.setCompanyId(account.getCompanyId());
		record.setAccountName(account.getCompanyName());
        record.setAccountType(account.getAccountType());
		record.setOppositeAccountNo(request.getReceiveAccountNo());
		record.setOppositeAccountName(request.getReceiveAccountName());
		record.setWithdrawBank(request.getReceiveBankName());
		record.setWithdrawBankAddress(request.getReceiveBankAddress());
		record.setAccountSupervisionId(account.getAccountSupervisionId());
		record.setTradeType(AccountTradeType.WITHDRAW_FUND.name());
		record.setOperateType("手工转出");
		record.setAmount(-MoneyUtil.yuan2Cent(request.getAmount()));
		record.setStatus(AccountTradeStatus.BANKAPI_REQUEST.name());
		record.setBusinessSeq(bizSeq);
		record.setTradeStartTime(new Date());
		record.setRemark(request.getRemark());
		record.setUserType(account.getUserType());
		accountTradeRecordMapper.insert(record);
	}
	
	private String createTradeApplyForWithdrawFund(User applyCustomer,
	        Account account, WithdrawFundRequestDTO request) {
		AccountTradeApply apply = initTradeApplyNoReceive(applyCustomer, account);
		apply.setOppositeAccountNo(request.getReceiveAccountNo());
		apply.setOppositeAccountName(applyCustomer.getCompanyName());//提现只能提到自己名下
		apply.setLianHangNo(request.getLianHangNo());
		apply.setWithdrawBank(request.getReceiveBankName());
		apply.setWithdrawBankAddress(request.getReceiveAccountName());
		apply.setTradeType(AccountTradeApplyType.WITHDRAW_FUND.name());
		apply.setAmount(MoneyUtil.yuan2Cent(request.getAmount()));
		String charge = FeeCalculateUtil.withdrawFee(request.getAmount());
		apply.setCharge(MoneyUtil.yuan2Cent(charge));
		apply.setRemark(request.getRemark());
		accountTradeApplyMapper.insert(apply);

		account.setAccountStatus(AccountStatus.HANDLING.name());
		accountMapper.updateByPrimaryKeySelective(account);

		return apply.getId();
	}

    @Override
    public PageResult<AccountTradeApplyDTO> queryTradeApplys(String userId, AccountTradeApplyQueryCriteria criteria) {
        String customerId = customerTransactionService.getCustomerIdByUserId(userId);
        User customer = customerTransactionService.checkCustomerIsValid(customerId);
        AccountTradeApplyQueryCriteria dbCriteria = AccountTradeApplyQueryCriteriaUtil.getValidCriteria(customer, criteria);
        int total = accountTradeApplyCustomMapper.count(dbCriteria);
        List<AccountTradeApply> applys = accountTradeApplyCustomMapper.find(dbCriteria);
        List<AccountTradeApplyDTO> applyDTOs = accountTradeApplyConverter.convertBaseInfos(customer, applys);
        return new PageResult<AccountTradeApplyDTO>(applyDTOs, total);
    }

	@Override
	public AccountTradeApplyDTO queryTradeApply(String userId, String applyId) {
		String customerId = customerTransactionService.getCustomerIdByUserId(userId);
		User customer = customerTransactionService.checkCustomerIsValid(customerId);
		AccountTradeApply apply = accountTradeApplyMapper.selectByPrimaryKey(applyId);
		return accountTradeApplyConverter.convertTradeApplyDetail(customer, apply);
	}

	public void throwExceptionIfEmpty(Object obj, String msg) {
		if (obj == null) {
			logger.error(msg);
			throw WebException.instance(msg);
		}
	}

	@Override
	public String deductFund(DeductFundRequestDTO request) {
	    checkDeductFundRequest(request);
        Account outAccount = accountTableLockMapper.lockAccountByAccountNo(request.getTransferOutAccountNo());
        Account inAccount = accountTableLockMapper.lockAccountByAccountNo(request.getTransferInAccountNo());
        checkAccountsForDeduct(outAccount, inAccount);
        createTradeRecord(null, AccountTradeType.TRANSFER_FUND, request.getBusinessSeq(), outAccount, inAccount, -request.getAmount(), true,null);
        createTradeRecord(null, AccountTradeType.TRANSFER_FUND, request.getBusinessSeq(), inAccount, outAccount, +request.getAmount(), true,null);
        return inAccount.getCompanyName();
	}

	@Override
	public String transferFundForCredit(TransferFundForCreditRequestDTO request) {
	    checkTransferFundForCreditRequest(request);
        Account outAccount = accountTableLockMapper.lockAccountByAccountNo(request.getTransferOutAccountNo());
        if (outAccount == null)
        	throw WebException.instance("转出账号[" + request.getTransferOutAccountNo() + "]不存在");
        Account inAccount = accountTableLockMapper.lockAccountByAccountNo(request.getTransferInAccountNo());
        if (inAccount == null)
        	throw WebException.instance("转入账号[" + request.getTransferInAccountNo() + "]不存在");
        createTradeRecord(null, AccountTradeType.TRANSFER_FUND, request.getBusinessSeq(), outAccount, inAccount, -request.getAmount(), true,null);
        createTradeRecord(null, AccountTradeType.TRANSFER_FUND, request.getBusinessSeq(), inAccount, outAccount, +request.getAmount(), true,null);
        return inAccount.getCompanyName();
	}

	private void checkAccountsForDeduct(Account outAccount, Account inAccount) {
	    if (outAccount == null)
            throw WebException.instance("转出账户不存在");
        if (inAccount == null)
            throw WebException.instance("转入账户不存在");
        if (!AccountType.GENERAL.name().equals(outAccount.getAccountType()))
            throw WebException.instance("转出账户不是一般账户");
        if (!AccountSupervisionStatus.HAVE_SUPERVISION.name().equals(outAccount.getSupervisionStatus()))
            throw WebException.instance("转出账户不是监管账户");
        if (!AccountType.GENERAL.name().equals(inAccount.getAccountType()))
            throw WebException.instance("转入账户不是一般账户");

        String factorId = null;
        if (UserType.FACTOR.name().equals(outAccount.getUserType())) {
            factorId = outAccount.getCompanyId();
        } else if (UserType.FINACE.name().equals(outAccount.getUserType())) {
            factorId = outAccount.getSupervisorId();
        } else {
            throw WebException.instance("转出账户必须是保理商或者融资客户的账户");
        }
        if (!factorId.equals(inAccount.getCompanyId()))
            throw WebException.instance("转入账户必须是转出账户的监管的保理商的账户");
	}

	private void checkDeductFundRequest(DeductFundRequestDTO request) {
	    if (request == null || StringUtils.isAnyBlank(request.getBusinessSeq(),
                request.getTransferInAccountNo(), request.getTransferOutAccountNo()))
            throw WebException.instance("请求参数不完整");
        if (request.getAmount() <= 0)
            throw WebException.instance("账扣金额必需大于0");
        AccountTradeRecordExample example = new AccountTradeRecordExample();
        example.createCriteria().andBusinessSeqEqualTo(request.getBusinessSeq());
        List<AccountTradeRecord> records = accountTradeRecordMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(records)) {
            throw WebException.instance("交易流水号已存在");
        }
	}

	private void checkTransferFundForCreditRequest(TransferFundForCreditRequestDTO request) {
        if (request == null || StringUtils.isAnyBlank(request.getBusinessSeq(),
                request.getTransferInAccountNo(), request.getTransferOutAccountNo()))
            throw WebException.instance("请求参数不完整");
        if (request.getAmount() <= 0)
            throw WebException.instance("账扣金额必需大于0");
        AccountTradeRecordExample example = new AccountTradeRecordExample();
        example.createCriteria().andBusinessSeqEqualTo(request.getBusinessSeq());
        List<AccountTradeRecord> records = accountTradeRecordMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(records)) {
            throw WebException.instance("交易流水号已存在");
        }
    }

	@Override
	public void insertWithdrawFeeRecord(AccountTradeRecord tradeRecord, BankAccountTradeFlowDataDTO tradeDTO) {
		AccountTradeRecord record = new AccountTradeRecord();
		record.setId(AccountIDGenerator.generateAccountTradeRecordId());
		record.setAccountId(tradeRecord.getAccountId());
		record.setCompanyId(tradeRecord.getCompanyId());
		record.setAccountNo(tradeRecord.getAccountNo());
		record.setAccountName(tradeRecord.getAccountName());
		record.setAccountType("GENERAL");
		record.setTradeType(AccountTradeApplyType.WITHDRAW_FUND.name());
		record.setOperateType("手续费");
        record.setAmount(-tradeDTO.getFee());
        record.setBusinessSeq(tradeRecord.getBusinessSeq());
        record.setTradeStartTime(tradeRecord.getTradeStartTime());
        record.setTradeEndTime(new Date());
        record.setStatus(AccountTradeStatus.TRADE_SUCCESS.name());
        record.setUserType(tradeRecord.getUserType());
        accountTradeRecordMapper.insertSelective(record);
	}

	@Override
	public WithdrawFundRequestDTO queryWithdrawInfoAndUpdateState(String tradeRecordId,String bizSeq, TradeStatusResultDTO queryTradeStatus) {
		WithdrawFundRequestDTO withdrawFundRequestDTO = new WithdrawFundRequestDTO();
		AccountTradeRecord accountTradeRecord = accountTradeRecordMapper.lockByPrimaryKey(tradeRecordId);
		if(!StringUtils.equals(accountTradeRecord.getStatus(), AccountTradeStatus.TRADE_FAIL.name()) || 
				!StringUtils.equals(queryTradeStatus.getTradeStatusDatas().get(0).getStatus().name(), BankTranStatus.fail.name())){
			throw WebException.instance("当前的交易状态不支持重新发起提现。");
		}
		withdrawFundRequestDTO.setAccountNo(accountTradeRecord.getAccountNo());
		withdrawFundRequestDTO.setAmount(MoneyArithUtil.convertMoneyToString(Math.abs(accountTradeRecord.getAmount())));
		if(StringUtils.isNotBlank(accountTradeRecord.getWithdrawBankAddress())){
			withdrawFundRequestDTO.setReceiveAccountName(accountTradeRecord.getWithdrawBankAddress());
		}else{
			withdrawFundRequestDTO.setReceiveAccountName(accountTradeRecord.getWithdrawBank());
		}
		withdrawFundRequestDTO.setReceiveAccountNo(accountTradeRecord.getOppositeAccountNo());
		withdrawFundRequestDTO.setReceiveBankName(accountTradeRecord.getWithdrawBank());
		withdrawFundRequestDTO.setRemark(accountTradeRecord.getRemark());
		BankBranchInfoExample example = new BankBranchInfoExample();
		example.createCriteria().andBranchNameEqualTo(accountTradeRecord.getWithdrawBankAddress());
		withdrawFundRequestDTO.setLianHangNo(bankBranchInfoMapper.selectByExample(example).get(0).getLianHangNo());
		accountTradeRecord.setBusinessSeq(bizSeq);
		accountTradeRecord.setTradeEndTime(null);
		accountTradeRecord.setStatus(AccountTradeStatus.BANKAPI_REQUEST.name());
		accountTradeRecordMapper.updateByPrimaryKey(accountTradeRecord);
		return withdrawFundRequestDTO;
	}
}
