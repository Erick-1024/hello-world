package com.cana.asset.service.transaction.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.gen.CreditAuditMapper;
import com.cana.asset.dao.mapper.gen.CreditMapper;
import com.cana.asset.dao.po.Credit;
import com.cana.asset.dao.po.CreditAudit;
import com.cana.asset.dao.po.CreditExample;
import com.cana.asset.dao.po.Expense;
import com.cana.asset.service.transaction.IAssetCreditTransactionService;
import com.cana.asset.service.transaction.IAssetExpenseTransactionService;
import com.cana.asset.service.transaction.IAssetLoanInfoTransactionService;
import com.cana.asset.service.transaction.util.CreditUtil;
import com.cana.asset.service.transaction.util.DataPermissionValidator;
import com.cana.asset.service.transaction.util.ValidateRules;
import com.cana.vbam.common.asset.dto.CreditCheckModifyResultDTO;
import com.cana.vbam.common.asset.dto.CreditRequestDTO;
import com.cana.vbam.common.asset.enums.CreditOperateType;
import com.cana.vbam.common.asset.enums.CreditStatus;
import com.cana.vbam.common.asset.enums.ExpenseType;
import com.cana.vbam.common.credit.enums.CreditMode;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;


/**
 * @author hu
 *
 */
@Service
public class AssetCreditTransactionServiceImpl implements IAssetCreditTransactionService{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private CreditMapper creditMapper;
	
	@Resource
	private CreditAuditMapper creditAuditMapper;
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private IVbamCommonService commonService;
	
	@Resource
	private IAssetLoanInfoTransactionService loanInfoTransactionService;
	
	@Resource
	private IAssetExpenseTransactionService expenseTransactionService;
	
	@Resource
	private DataPermissionValidator dataPermissionValidator;
	
	@Override
	public Credit lockByBussinessContractNo(String contractNo) {
		CreditExample example = new CreditExample();
		example.createCriteria().andBusinessContractNoEqualTo(contractNo);
		List<Credit> creditList = creditMapper.lockByExample(example);
		if(CollectionUtils.isEmpty(creditList)){
			return null;
		}
		return creditList.get(0);
	}

	@Override
	public void recoveryLimit(String creditId, long usedLimit, String operatorId) {
		if(StringUtils.isBlank(creditId))
			throw WebException.instance("参数不能为空");
		logger.info("用户[{}]执行恢复额度[{}]开始", operatorId, creditId);
		if(usedLimit <= 0)
			throw WebException.instance("需恢复的额度不大于０");
		Credit credit = creditMapper.lockByPrimaryKey(creditId);
		if(null == credit)
			throw WebException.instance("额度不存在");
		if(credit.getUsedLimit() < usedLimit){
			logger.info("额度[{}]大于当前使用额度[{}]",usedLimit,credit.getUsedLimit());
			throw WebException.instance("恢复额度参数错误!");
		}
		Credit oldCredit = copyBeans(credit);
		credit.setUsedLimit(credit.getUsedLimit() - usedLimit);
		creditMapper.updateByPrimaryKeySelective(credit);
		
		generatorCreditAudit(CreditOperateType.RECOVER, oldCredit, credit, operatorId);
		logger.info("用户[{}]执行恢复额度[{}]结束", operatorId, creditId);
	}

	@Override
	public boolean checkAvailableLimit(String creditId, long uselimit){
		if(StringUtils.isBlank(creditId))
			throw WebException.instance("参数不能为空");
		if(uselimit <= 0)
			throw WebException.instance("使用的额度不大于０");
		Credit credit = creditMapper.selectByPrimaryKey(creditId);
		if(null == credit)
			throw WebException.instance("额度不存在");
		if(CreditUtil.getAvailableLimit(credit) < uselimit)
			return false;
		return true;
	}
	
	@Override
	public void useLimit(String creditId, long usedLimit, String operatorId){
		if(StringUtils.isBlank(creditId))
			throw WebException.instance("参数不能为空");
		logger.info("用户[{}]执行占用额度[{}]开始", operatorId, creditId);
		if(usedLimit <= 0)
			throw WebException.instance("使用的额度不大于０");
		Credit credit = creditMapper.lockByPrimaryKey(creditId);
		if(null == credit)
			throw WebException.instance("额度不存在");
		if(!CreditStatus.NORMAL.name().equals(credit.getStatus())){
			logger.info("当前额度状态[{}]不为生效状态", credit.getStatus());
			throw WebException.instance("当前额度状态不为生效状态");
		}
		if(usedLimit > CreditUtil.getAvailableLimit(credit)){
			logger.info("额度[{}]+使用额度[{}]>总额度[{}]", usedLimit, credit.getUsedLimit(), credit.getTotalLimit());
			throw WebException.instance("恢复额度参数错误!");
		}
		Credit oldCredit = copyBeans(credit);
		credit.setUsedLimit(credit.getUsedLimit() + usedLimit);
		creditMapper.updateByPrimaryKeySelective(credit);
		
		generatorCreditAudit(CreditOperateType.USE, oldCredit, credit,operatorId);
		logger.info("用户[{}]执行恢复额度[{}]结束", operatorId, creditId);
	}
	
	@Override
	public void applyCredit(CreditRequestDTO request, UserVo userVo) {
		if(null == userVo || StringUtils.isBlank(request.getCustomerId()))
			throw WebException.instance("参数不能为空");
		if(!UserType.FACTOR.equals(userVo.getCustomer().getUserType()))
			throw WebException.instance("无操作权限");
		checkRequestParams(request);
		Credit credit = new Credit();
		credit.setId(generateCreditId());
		credit.setCustomerId(request.getCustomerId());
		credit.setFactorId(userVo.getCustomerId());
		credit.setUsedLimit(0L);
		fillCreditParams(credit, request, new CreditCheckModifyResultDTO(true));
		creditMapper.insertSelective(credit);
		if(StringUtils.isNotBlank(request.getExpense()) && MoneyArithUtil.convertStringToMoney(request.getExpense()) > 0)
			insertExpense(credit.getId(), MoneyArithUtil.convertStringToMoney(request.getExpense()));
		generatorCreditAudit(CreditOperateType.CREATE, null, credit, userVo.getUserId());
	}

	@Override
	public CreditCheckModifyResultDTO checkCreditForModify(String creditId){
		if(StringUtils.isBlank(creditId))
			throw WebException.instance("参数不能为空");
		Credit credit = creditMapper.lockByPrimaryKey(creditId);
		if(null == credit){
			throw WebException.instance("额度不存在");
		}
		if(!CreditStatus.APPLY.name().equals(credit.getStatus()) && !CreditStatus.NORMAL.name().equals(credit.getStatus())){
			logger.info("额度当前状态[{}],不能修改", credit.getStatus());
			throw WebException.instance("额度不为申请或生效状态,不能修改");
		}
		boolean hasLoan = loanInfoTransactionService.checkContractNoHasLoan(credit.getBusinessContractNo());
		if(hasLoan){
			if(CreditMode.SINGLE.name().equals(credit.getCreditMode())){
				logger.info("额度当前状态[{}]", credit.getStatus());
				throw WebException.instance("该单笔额度已放款,不能修改");
			}else if(CreditMode.SYNTHETICAL.name().equals(credit.getCreditMode())){
				CreditCheckModifyResultDTO resultDTO = new CreditCheckModifyResultDTO();
				resultDTO.setAllowModify(true);
				resultDTO.setAllowTotalLimit(true);
				resultDTO.setAllowExpense(true);
				resultDTO.setAllowDueDate(true);
				return resultDTO;
			}else{
				logger.info("额度当前状态[{}]", credit.getStatus());
				throw WebException.instance("额度状态有误");
			}
		}
		CreditCheckModifyResultDTO resultDTO = new CreditCheckModifyResultDTO(true);
		return resultDTO;
	}
	
	@Override
	public void modifyCredit(CreditRequestDTO request, UserVo userVo) {
		if(StringUtils.isBlank(request.getId()) || null == userVo)
			throw WebException.instance("参数不能为空");
		CreditCheckModifyResultDTO checkDTO = checkCreditForModify(request.getId());
		checkRequestParams(request);
		Credit credit = creditMapper.lockByPrimaryKey(request.getId());
		dataPermissionValidator.checkDataPermissions(userVo, credit.getCustomerId(), credit.getFactorId());
		if(MoneyArithUtil.convertStringToMoney(request.getTotalLimit()).compareTo(credit.getUsedLimit()) < 0){
			throw WebException.instance("修改申请额度不能小于已使用额度");
		}
		Credit oldCredit = copyBeans(credit);
		fillCreditParams(credit, request, checkDTO);
		credit.setUpateTime(new Date());
		creditMapper.updateByPrimaryKeySelective(credit);
		expenseTransactionService.deleteExpenseByRef(credit.getId(), ExpenseType.CREDIT);
		if(checkDTO.isAllowExpense() && StringUtils.isNotBlank(request.getExpense()) && MoneyArithUtil.convertStringToMoney(request.getExpense()) > 0)
			insertExpense(credit.getId(), MoneyArithUtil.convertStringToMoney(request.getExpense()));
		generatorCreditAudit(CreditOperateType.MODIFY, oldCredit, credit, userVo.getUserId());
	}

	private void checkRequestParams(CreditRequestDTO request){
		if(StringUtils.isBlank(request.getBusinessContractNo()))
			throw WebException.instance("业务合同号不能为空");
		if(StringUtils.isBlank(request.getTotalLimit()) || !ValidateRules.regexAmountCheck(request.getTotalLimit()))
			throw WebException.instance("申请金额有误");
		if(StringUtils.isNotBlank(request.getExpense()) && !ValidateRules.regexAmountCheck(request.getExpense()))
			throw WebException.instance("申请金额有误");
		if(StringUtils.isBlank(request.getEffectiveDate()) || !ValidateRules.regexDateCheck(request.getEffectiveDate()))
			throw WebException.instance("生效日期有误");
		if(StringUtils.isBlank(request.getDueDate()) || !ValidateRules.regexDateCheck(request.getDueDate()))
			throw WebException.instance("到期日期有误");
		if(commonService.getCurrentDate().compareTo(request.getDueDate()) > 0 ||
				request.getEffectiveDate().compareTo(request.getDueDate()) > 0)
			throw WebException.instance("日期选择有误");
		
		CreditExample example = new CreditExample();
		CreditExample.Criteria criteria = example.createCriteria();
		criteria.andBusinessContractNoEqualTo(request.getBusinessContractNo());
		if(StringUtils.isNotBlank(request.getId()))
			criteria.andIdNotEqualTo(request.getId());
		List<Credit> creditList = creditMapper.lockByExample(example);
		if(CollectionUtils.isNotEmpty(creditList))
			throw WebException.instance("业务合同号已存在");
	}
	
	private void fillCreditParams(Credit credit, CreditRequestDTO request, CreditCheckModifyResultDTO check){
		if(check.isAllowBusinessContractNo())
			credit.setBusinessContractNo(request.getBusinessContractNo());
		if(check.isAllowMode())
			credit.setCreditMode(request.getCreditMode());
		if(check.isAllowCurreny())	
			credit.setCurrency(request.getCurrency());
		if(check.isAllowTotalLimit())
			credit.setTotalLimit(MoneyArithUtil.convertStringToMoney(request.getTotalLimit()));
		if(check.isAllowEffectiveDate())
			credit.setEffectiveDate(request.getEffectiveDate());
		if(check.isAllowDueDate())
			credit.setDueDate(request.getDueDate());
		if(commonService.getCurrentDate().compareTo(request.getEffectiveDate()) < 0)
			credit.setStatus(CreditStatus.APPLY.name());
		else
			credit.setStatus(CreditStatus.NORMAL.name());
	}
	
	private void insertExpense(String creditId, Long amount){
		Expense expense = new Expense();
		expense.setAmount(amount);
		expense.setRefid(creditId);
		expense.setReftype(ExpenseType.CREDIT.name());
		expense.setSequence(1);
		expense.setExpenseSubject("申请额度费用");
		expenseTransactionService.savaExpense(expense);
	}
	@Override
	public void freezeCredit(String creditId, UserVo userVo) {
		if(StringUtils.isBlank(creditId) || null == userVo)
			throw WebException.instance("参数不能为空");
		logger.info("用户[{}]执行冻结额度[{}]开始", userVo.getUserId(), creditId);
		Credit credit = creditMapper.lockByPrimaryKey(creditId);
		if(null == credit)
			throw WebException.instance("额度不存在");
		dataPermissionValidator.checkDataPermissions(userVo, credit.getCustomerId(), credit.getFactorId());
		if(!CreditStatus.APPLY.name().equals(credit.getStatus()) && !CreditStatus.NORMAL.name().equals(credit.getStatus())){
			logger.info("额度当前状态[{}]", credit.getStatus());
			throw WebException.instance("额度不为申请或生效状态,不能冻结");
		}
		Credit oldCredit = copyBeans(credit);
		credit.setStatus(CreditStatus.FREEZE.name());
		creditMapper.updateByPrimaryKeySelective(credit);
		
		generatorCreditAudit(CreditOperateType.FREEZE, oldCredit, credit, userVo.getUserId());
		logger.info("用户[{}]执行冻结额度[{}]结束", userVo.getUserId(), creditId);
	}

	@Override
	public void unfreezeCredit(String creditId, UserVo userVo) {
		if(StringUtils.isBlank(creditId) || null == userVo)
			throw WebException.instance("参数不能为空");
		logger.info("用户[{}]执行解冻额度[{}]开始", userVo.getUserId(), creditId);
		Credit credit = creditMapper.lockByPrimaryKey(creditId);
		if(null == credit)
			throw WebException.instance("额度不存在");
		dataPermissionValidator.checkDataPermissions(userVo, credit.getCustomerId(), credit.getFactorId());
		if(!CreditStatus.FREEZE.name().equals(credit.getStatus())){
			logger.info("额度当前状态[{}]", credit.getStatus());
			throw WebException.instance("额度不为冻结状态,不能解冻");
		}
		Credit oldCredit = copyBeans(credit);
		
		String currentDate = commonService.getCurrentDate();
		if(currentDate.compareTo(credit.getEffectiveDate()) < 0)
			credit.setStatus(CreditStatus.APPLY.name());
		else if(currentDate.compareTo(credit.getDueDate()) > 0)
			credit.setStatus(CreditStatus.EXPIRE.name());
		else
			credit.setStatus(CreditStatus.NORMAL.name());
		creditMapper.updateByPrimaryKeySelective(credit);
		
		generatorCreditAudit(CreditOperateType.UNFREEZE, oldCredit, credit, userVo.getUserId());
		logger.info("用户[{}]执行解冻额度[{}]结束", userVo.getUserId(), creditId);
	}

	@Override
	public void revokeCredit(String creditId, UserVo userVo) {
		if(StringUtils.isBlank(creditId) || null == userVo)
			throw WebException.instance("参数不能为空");
		logger.info("用户[{}]执行撤销额度[{}]开始", userVo.getUserId(), creditId);
		Credit credit = creditMapper.lockByPrimaryKey(creditId);
		if(null == credit)
			throw WebException.instance("额度不存在");
		dataPermissionValidator.checkDataPermissions(userVo, credit.getCustomerId(), credit.getFactorId());
		if(!CreditStatus.APPLY.name().equals(credit.getStatus()) && !CreditStatus.NORMAL.name().equals(credit.getStatus())){
			logger.info("额度当前状态[{}]", credit.getStatus());
			throw WebException.instance("额度不为申请或生效状态,不能撤销");
		}
		if(loanInfoTransactionService.checkContractNoHasLoan(credit.getBusinessContractNo()))
			throw WebException.instance("额度已放过款不能进行撤销操作");
		
		Credit oldCredit = copyBeans(credit);
		credit.setStatus(CreditStatus.REVOKE.name());
		creditMapper.updateByPrimaryKeySelective(credit);
		
		generatorCreditAudit(CreditOperateType.REVOKE, oldCredit, credit, userVo.getUserId());
		logger.info("用户[{}]执行撤销额度[{}]结束", userVo.getUserId(), creditId);
		
	}

	@Override
	public void cancelCredit(String creditId, UserVo userVo) {
		if(StringUtils.isBlank(creditId) || null == userVo)
			throw WebException.instance("参数不能为空");
		logger.info("用户[{}]执行作废额度[{}]开始", userVo.getUserId(), creditId);
		Credit credit = creditMapper.lockByPrimaryKey(creditId);
		if(null == credit)
			throw WebException.instance("额度不存在");
		dataPermissionValidator.checkDataPermissions(userVo, credit.getCustomerId(), credit.getFactorId());
		if(!CreditStatus.NORMAL.name().equals(credit.getStatus())){
			logger.info("额度当前状态[{}]", credit.getStatus());
			throw WebException.instance("额度不为生效状态,不能作废");
		}
		Credit oldCredit = copyBeans(credit);
		credit.setStatus(CreditStatus.CANCEL.name());
		creditMapper.updateByPrimaryKeySelective(credit);
		
		generatorCreditAudit(CreditOperateType.CANCEL, oldCredit, credit, userVo.getUserId());
		logger.info("用户[{}]执行作废额度[{}]结束", userVo.getUserId(), creditId);
		
	}

	private void generatorCreditAudit(CreditOperateType type, Credit oldCredit, Credit credit, String operatorId){
		CreditAudit audit = new CreditAudit();
		audit.setId(generateCreditAuditId());
		audit.setType(type.name());
		audit.setBussinessContractNo(credit.getBusinessContractNo());
		audit.setCreditId(credit.getId());
		audit.setCreditMode(credit.getCreditMode());
		audit.setCurrency(credit.getCurrency());
		audit.setCustomerId(credit.getCustomerId());
		audit.setFactorId(credit.getFactorId());
		audit.setOperatorId(operatorId);
		//变化前
		if(null != oldCredit){
			audit.setPreTotalLimit(oldCredit.getTotalLimit());
			audit.setPreUsedLimit(oldCredit.getUsedLimit());
			audit.setPreAvailableLimit(CreditUtil.getAvailableLimit(credit));
			audit.setPreCreditStatus(oldCredit.getStatus());
			audit.setPreEffectiveDate(oldCredit.getEffectiveDate());
			audit.setPreDueDate(oldCredit.getDueDate());	
		}
		//变化后
		audit.setTotalLimit(credit.getTotalLimit());
		audit.setUsedLimit(credit.getUsedLimit());
		audit.setAvailableLimit(CreditUtil.getAvailableLimit(credit));
		audit.setCreditStatus(credit.getStatus());
		audit.setEffectiveDate(credit.getEffectiveDate());
		audit.setDueDate(credit.getDueDate());
		
		creditAuditMapper.insertSelective(audit);
	}
	
	private String generateCreditId(){
		return DateTimeUtil.datetime12()
				+ seqGen.getNextSeq(Constants.SEQUENCE_NAME_ASSET_CREDIT_ID, 4);
	}
	
	private String generateCreditAuditId(){
		return DateTimeUtil.datetime12()
				+ seqGen.getNextSeq(Constants.SEQUENCE_NAME_ASSET_CREDIT_AUDIT_ID, 4);
	}
	
	private Credit copyBeans(Credit credit){
		Credit oldCredit = new Credit();
		BeanUtils.copyProperties(credit, oldCredit);
		return oldCredit;
	}

	@Override
	public void updateExpireCreditState(String currentDate) {
		// 获取过期的额度信息
		CreditExample creditExample = new CreditExample();
		creditExample.createCriteria().andDueDateLessThan(currentDate).andStatusIn(CreditStatus.canAlterState());
		List<Credit> creditList = creditMapper.lockByExample(creditExample);
		for(Credit credit:creditList){
			credit.setStatus(CreditStatus.EXPIRE.name());
			creditMapper.updateByPrimaryKey(credit);
		}
	}

	@Override
	public void updateEffectiveCreditState(String currentDate) {
		// 获取申请状态的额度信息
		CreditExample creditExample = new CreditExample();
		creditExample.createCriteria().andEffectiveDateGreaterThanOrEqualTo(currentDate).andStatusIn(Lists.newArrayList(CreditStatus.APPLY.name())).andDueDateGreaterThanOrEqualTo(currentDate);
		List<Credit> creditList = creditMapper.lockByExample(creditExample);
		for(Credit credit:creditList){
			credit.setStatus(CreditStatus.NORMAL.name());
			creditMapper.updateByPrimaryKey(credit);
		}
	}
}
