package com.cana.asset.service.convertors;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.cana.asset.dao.mapper.gen.ExpenseMapper;
import com.cana.asset.dao.po.Credit;
import com.cana.asset.dao.po.CreditAudit;
import com.cana.asset.dao.po.Expense;
import com.cana.asset.dao.po.ExpenseExample;
import com.cana.asset.service.transaction.IAssetCreditTransactionService;
import com.cana.asset.service.transaction.IAssetLoanInfoTransactionService;
import com.cana.vbam.common.asset.dto.CreditAuditDTO;
import com.cana.vbam.common.asset.dto.CreditDTO;
import com.cana.vbam.common.asset.enums.CreditOperateType;
import com.cana.vbam.common.asset.enums.CreditStatus;
import com.cana.vbam.common.asset.enums.ExpenseType;
import com.cana.vbam.common.credit.enums.CreditMode;
import com.cana.vbam.common.repayment.enums.Currency;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;

/**
 * @author hu
 *
 */
@Component
public class CreditConvertor {

	@Resource
	private ExpenseMapper expenseMapper;
	
	@Resource
	private IAssetCreditTransactionService creditTransactionServiceImpl;
	
	@Resource
	private IAssetLoanInfoTransactionService loanInfoTransactionService;
	
	/**
	 * 授信dto转化
	 * @param credit
	 * @return
	 */
	public CreditDTO convertorCredit2DTO(Credit credit){
		if(null == credit){
			return null;
		}
		CreditDTO creditDTO = new CreditDTO();
		BeanUtils.copyProperties(credit, creditDTO);
		creditDTO.setCurrencyDesc(Currency.valueOf(creditDTO.getCurrency()).desc());
		creditDTO.setCreditModeDesc(StringUtils.isBlank(credit.getCreditMode()) ? null : CreditMode.valueOf(credit.getCreditMode()).desc());
		creditDTO.setStatusDesc(StringUtils.isBlank(credit.getStatus()) ? null : CreditStatus.valueOf(credit.getStatus()).desc());
		
		if(CreditStatus.NORMAL.name().equals(credit.getStatus()) && ((CreditMode.SINGLE.name().equals(credit.getCreditMode()) && 
				!loanInfoTransactionService.checkContractNoHasLoan(credit.getBusinessContractNo())) || CreditMode.SYNTHETICAL.name().equals(credit.getCreditMode())))
			creditDTO.setAvailableLimit(MoneyArithUtil.minusLong(credit.getTotalLimit(), credit.getUsedLimit()));
	
		creditDTO.setTotalLimitStr(MoneyArithUtil.convertMoneyToString(credit.getTotalLimit()));
		creditDTO.setUsedLimitStr(MoneyArithUtil.convertMoneyToString(credit.getUsedLimit()));
		creditDTO.setAvailableLimitStr(MoneyArithUtil.convertMoneyToString(creditDTO.getAvailableLimit()));
		Long totalExpense = new Long(0);
		ExpenseExample example = new ExpenseExample();
		example.createCriteria().andRefidEqualTo(credit.getId()).andReftypeEqualTo(ExpenseType.CREDIT.name());
		List<Expense> expenseList = expenseMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(expenseList)){
			for(Expense expense : expenseList){
				totalExpense = MoneyArithUtil.addLong(totalExpense, expense.getAmount());
			}
		}
		creditDTO.setTotalExpense(totalExpense);
		creditDTO.setTotalExpenseStr(MoneyArithUtil.convertMoneyToString(totalExpense));
		
		//用于按钮显示
		try{
			creditTransactionServiceImpl.checkCreditForModify(creditDTO.getId());
		}catch(WebException e){
			creditDTO.setAllowModify(false);
		}
		
		if(!CreditStatus.APPLY.name().equals(credit.getStatus()) && !CreditStatus.NORMAL.name().equals(credit.getStatus())){
			creditDTO.setAllowFreeze(false);
			//不能修改说明放过款
			if(!creditDTO.isAllowModify())
				creditDTO.setAllowRevoke(false);
			
		}
		
		if(!CreditStatus.FREEZE.name().equals(credit.getStatus())){
			creditDTO.setAllowUnFreeze(false);
		}
		
		if(!CreditStatus.NORMAL.name().equals(credit.getStatus())){
			creditDTO.setAllowCancel(false);
		}
		return creditDTO;
	}
	
	/**
	 * 授信列表dto转化
	 * @param creditList
	 * @return
	 */
	public List<CreditDTO> convertorCreditditList2DTO(List<Credit> creditList){
		if(CollectionUtils.isEmpty(creditList)){
			return Lists.newArrayList();
		}
		List<CreditDTO> creditDTOList = Lists.newArrayList();
		for(Credit credit : creditList){
			creditDTOList.add(convertorCredit2DTO(credit));
		}
		return creditDTOList;
	}
	
	/**
	 * 授信审计dto转化
	 * @param creditAudit
	 * @return
	 */
	public CreditAuditDTO convertorCreditAudit2DTO(CreditAudit creditAudit){
		if(null == creditAudit){
			return null;
		}
		CreditAuditDTO creditAuditDTO = new CreditAuditDTO();
		BeanUtils.copyProperties(creditAudit, creditAuditDTO);
		creditAuditDTO.setCreditModeDesc(StringUtils.isBlank(creditAudit.getCreditMode()) ? null : CreditMode.valueOf(creditAudit.getCreditMode()).desc());
		creditAuditDTO.setTypeDesc(StringUtils.isBlank(creditAudit.getType()) ? null : CreditOperateType.valueOf(creditAudit.getType()).desc());
		creditAuditDTO.setCreditStatusDesc(StringUtils.isBlank(creditAudit.getCreditStatus()) ? null : CreditStatus.valueOf(creditAudit.getCreditStatus()).desc());
		creditAuditDTO.setPreCreditStatusDesc(StringUtils.isBlank(creditAudit.getPreCreditStatus()) ? null : CreditStatus.valueOf(creditAudit.getPreCreditStatus()).desc());
		
		creditAuditDTO.setPreTotalLimitStr(MoneyArithUtil.convertMoneyToString(creditAudit.getPreTotalLimit()));
		creditAuditDTO.setPreUsedLimitStr(MoneyArithUtil.convertMoneyToString(creditAudit.getPreUsedLimit()));
		creditAuditDTO.setPreAvailableLimitStr(MoneyArithUtil.convertMoneyToString(creditAudit.getPreAvailableLimit()));
		creditAuditDTO.setTotalLimitStr(MoneyArithUtil.convertMoneyToString(creditAudit.getTotalLimit()));
		creditAuditDTO.setUsedLimitStr(MoneyArithUtil.convertMoneyToString(creditAudit.getUsedLimit()));
		creditAuditDTO.setAvailableLimitStr(MoneyArithUtil.convertMoneyToString(creditAudit.getAvailableLimit()));
		return creditAuditDTO;
	}
	
	/**
	 * 授信审计列表dto转化
	 * @param creditAuditList
	 * @return
	 */
	public List<CreditAuditDTO> convertorCreditAuditList2DTO(List<CreditAudit> creditAuditList){
		if(CollectionUtils.isEmpty(creditAuditList)){
			return Lists.newArrayList();
		}
		List<CreditAuditDTO> creditAuditDTOList = Lists.newArrayList();
		for(CreditAudit creditAudit : creditAuditList){
			creditAuditDTOList.add(convertorCreditAudit2DTO(creditAudit));
		}
		return creditAuditDTOList;
	}
}
