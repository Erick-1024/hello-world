package com.cana.repayment.server.validate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.cana.member.api.IUserApi;
import com.cana.repayment.dao.mapper.gen.RepaymentExpenseMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPlanMapper;
import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentExpenseExample;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentLoanInfoExample;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.dao.po.RepaymentPlanExample;
import com.cana.vbam.common.repayment.dto.LoanInfoRedisDTO;
import com.cana.vbam.common.repayment.dto.RepaymentExpenseRedisDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanInfoRedisIntegration;
import com.cana.vbam.common.repayment.dto.RepaymentPlanRedisDTO;
import com.cana.vbam.common.repayment.enums.Currency;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.EditAble;
import com.cana.vbam.common.repayment.enums.InputMethod;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.repayment.enums.VerifyStatus;
import com.cana.vbam.common.utils.MoneyArithUtil;

@Component
public class RepaymentValidator {
	
	@Resource
	private RepaymentLoanInfoMapper loanInfoMapper;
	
	@Resource
	private RepaymentPlanMapper repaymentPlanMapper;

	@Resource
	private RepaymentExpenseMapper repaymentExpenseMapper;
	
	@Resource
	private IUserApi userApi;
	
	/**
	 * 放款信息校验
	 * @param masterId
	 * @param loanInfoRedisDTO
	 * @return
	 */
	public boolean ValidateloanInfo(String masterId, LoanInfoRedisDTO loanInfoRedisDTO) {
		return basicValidateloanInfo(loanInfoRedisDTO) && advancedValidateLoanInfo(masterId,loanInfoRedisDTO);
	}
	
	/**
	 * 还款计划信息校验
	 * @param repaymentPlanRedisDTO
	 * @return
	 */
	public boolean validateForRepaymentPlan(RepaymentPlanRedisDTO repaymentPlanRedisDTO,String masterId,RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration) throws Exception{
		return basicValidateRepaymentPlan(repaymentPlanRedisDTO) && advancedValidateForRepaymentPlan(repaymentPlanRedisDTO,masterId,repaymentPlanInfoRedisIntegration);
	}
	
	/**
	 * 还款计划费用校验
	 * @param repaymentExpenseRedisDTO
	 * @return
	 */
	public boolean validateForRepaymentExpense(RepaymentExpenseRedisDTO repaymentExpenseRedisDTO,String masterId,List<RepaymentExpenseRedisDTO> repaymentExpenseCorrectList) throws Exception{
		return basicValidateRepaymentExpense(repaymentExpenseRedisDTO) && advancedValidateForRepaymentExpense(repaymentExpenseRedisDTO,masterId,repaymentExpenseCorrectList);
	}
	
	
	/**
	 * 放款信息基础校验（格式，空值）
	 * @param loanInfoRedisDTO
	 * @return
	 */
	private boolean basicValidateloanInfo(LoanInfoRedisDTO loanInfoRedisDTO){

		return notNullCheck(loanInfoRedisDTO)&&
				regexCheck(ValidateRules.DATE_REGEX, loanInfoRedisDTO.getDueDate(), LoanInfoBasicVerifyRuleDesc.DUE_DATE_REGEX_MESSAGE, loanInfoRedisDTO) &&
				 regexCheck(ValidateRules.DATE_REGEX, loanInfoRedisDTO.getLoanDate(), LoanInfoBasicVerifyRuleDesc.LOAN_DATE_REGEX_MESSAGE, loanInfoRedisDTO) &&
				  regexCheck(ValidateRules.AMOUNT_REGEX, loanInfoRedisDTO.getFinanceAmount(), LoanInfoBasicVerifyRuleDesc.FINANCE_AMOUNT_REGEX_MESSAGE, loanInfoRedisDTO) &&
				   regexCheck(ValidateRules.AMOUNT_REGEX, loanInfoRedisDTO.getFinanceBalance(), LoanInfoBasicVerifyRuleDesc.FINANCE_BALANCE_REGEX_MESSAGE, loanInfoRedisDTO) &&
				    regexCheck(ValidateRules.AMOUNT_REGEX, loanInfoRedisDTO.getReceivablesAmount(), LoanInfoBasicVerifyRuleDesc.RECEIVABLES_AMOUNT_REGEX_MESSAGE, loanInfoRedisDTO) &&
				     regexCheck(ValidateRules.AMOUNT_REGEX, loanInfoRedisDTO.getReceivablesBalance(), LoanInfoBasicVerifyRuleDesc.RECEIVABLES_BALANCE_REGEX_MESSAGE, loanInfoRedisDTO) &&
				      regexCheck(ValidateRules.DIGITAL_REGEX, loanInfoRedisDTO.getLoanPeriod(), LoanInfoBasicVerifyRuleDesc.LOAN_PERIOD_REGEX_MESSAGE, loanInfoRedisDTO) &&
				       regexCheck(ValidateRules.PERCENT_REGEX, loanInfoRedisDTO.getInterestRate(), LoanInfoBasicVerifyRuleDesc.INTEREST_RATE_REGEX_MESSAGE, loanInfoRedisDTO);
	}
	
	/**
	 * 放款信息空值校验
	 * @param loanInfoRedisDTO
	 * @return
	 */
	private boolean notNullCheck(LoanInfoRedisDTO loanInfoRedisDTO){
		if(StringUtils.isBlank(loanInfoRedisDTO.getLoanNo())){
			loanInfoRedisDTO.setVerifyFailReason(LoanInfoBasicVerifyRuleDesc.LOANNO_NOT_NULL);
			loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			return false;
		}
		if(StringUtils.isBlank(loanInfoRedisDTO.getBusinessContractNo())){
			loanInfoRedisDTO.setVerifyFailReason(LoanInfoBasicVerifyRuleDesc.BUSSINESS_CONTRACTNO_NOT_NULL);
			loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			return false;
		}
		if(StringUtils.isBlank(loanInfoRedisDTO.getFinanceCompany())){
			loanInfoRedisDTO.setVerifyFailReason(LoanInfoBasicVerifyRuleDesc.FINANCE_COMPANY_NOT_NULL);
			loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			return false;
		}
//		if(StringUtils.isBlank(loanInfoRedisDTO.getVoucherNo())){
//			loanInfoRedisDTO.setVerifyFailReason(LoanInfoBasicVerifyRuleDesc.VOUCHERNO_NOT_NULL);
//			loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
//			return false;
//		}
		if(StringUtils.isBlank(loanInfoRedisDTO.getBusinessProduct())){
			loanInfoRedisDTO.setVerifyFailReason(LoanInfoBasicVerifyRuleDesc.BUSSINESS_PRODUCT_NOT_NULL);
			loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			return false;
		}
		if(null == Currency.getValue(loanInfoRedisDTO.getCurrency())){
			loanInfoRedisDTO.setVerifyFailReason(LoanInfoBasicVerifyRuleDesc.CURRENCY_NOT_NULL);
			loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			return false;
		}
		if(null == DateUnit.getValue(loanInfoRedisDTO.getLoanPeriodUnit())){
			loanInfoRedisDTO.setVerifyFailReason(LoanInfoBasicVerifyRuleDesc.LOAN_PERIOD_UNIT_NOT_NULL);
			loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			return false;
		}
		if(null == RepaymentType.getValue(loanInfoRedisDTO.getRepaymentMethod())){
			loanInfoRedisDTO.setVerifyFailReason(LoanInfoBasicVerifyRuleDesc.REPAYMENT_TYPE_NOT_NULL);
			loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			return false;
		}
		if(null == InterestRateUnit.getValue(loanInfoRedisDTO.getInterestRateUnit())){
			loanInfoRedisDTO.setVerifyFailReason(LoanInfoBasicVerifyRuleDesc.INTEREST_RATE_UNIT_NOT_NULL);
			loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			return false;
		}
		return true;
	}
	
	/**
	 * 放款信息格式校验
	 * @param regex
	 * @param value
	 * @param field
	 * @param loanInfoRedisDTO
	 * @return
	 */
	private boolean regexCheck(String regex, String value, String message, LoanInfoRedisDTO loanInfoRedisDTO){
		if(!Pattern.matches(regex, value)){
			loanInfoRedisDTO.setVerifyFailReason(message);
			loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			return false;
		}
		return true;
	}
	
	/**
	 * 放款信息的高级校验（自定义规则）
	 * @param loanInfoRedisDTO
	 * @return
	 */
	private boolean advancedValidateLoanInfo(String masterId, LoanInfoRedisDTO loanInfoRedisDTO){
		RepaymentLoanInfoExample loanInfoExample = new RepaymentLoanInfoExample();
		loanInfoExample.createCriteria().andFactorIdEqualTo(masterId).andLoanNoEqualTo(loanInfoRedisDTO.getLoanNo());
		if(loanInfoMapper.countByExample(loanInfoExample) > 0){
			loanInfoRedisDTO.setVerifyFailReason(LoanInfoBizVerifyRuleDesc.LOANNO_VERIFY);
			loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			return false;
		}
		if(Currency.getValue(loanInfoRedisDTO.getCurrency()) != Currency.RMB){
			loanInfoRedisDTO.setVerifyFailReason(LoanInfoBizVerifyRuleDesc.CURRENCY_VERIFY);
			loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			return false;
		}
		if(Double.parseDouble(loanInfoRedisDTO.getReceivablesAmount()) < Double.parseDouble(loanInfoRedisDTO.getReceivablesBalance())){
			loanInfoRedisDTO.setVerifyFailReason(LoanInfoBizVerifyRuleDesc.RECEIVABLES_VERIFY);
			loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			return false;
		}
		if(Double.parseDouble(loanInfoRedisDTO.getFinanceAmount()) < Double.parseDouble(loanInfoRedisDTO.getFinanceBalance())){
			loanInfoRedisDTO.setVerifyFailReason(LoanInfoBizVerifyRuleDesc.FINANCE_VERIFY);
			loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			return false;
		}
		if(DateTime.parse(loanInfoRedisDTO.getDueDate()).compareTo(DateTime.parse(loanInfoRedisDTO.getLoanDate())) < 0){
			loanInfoRedisDTO.setVerifyFailReason(LoanInfoBizVerifyRuleDesc.DATE_VERIFY);
			loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			return false;
		}
		switch (DateUnit.getValue(loanInfoRedisDTO.getLoanPeriodUnit())) {
		case YEAR:
			if(!DateTime.parse(loanInfoRedisDTO.getLoanDate()).plusYears(Integer.parseInt(loanInfoRedisDTO.getLoanPeriod())).equals(DateTime.parse(loanInfoRedisDTO.getDueDate()))){
				loanInfoRedisDTO.setVerifyFailReason(LoanInfoBizVerifyRuleDesc.DUEDATE_VERIFY);
				loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
				return false;
			}
			break;

		case MONTH:
			if(!DateTime.parse(loanInfoRedisDTO.getLoanDate()).plusMonths(Integer.parseInt(loanInfoRedisDTO.getLoanPeriod())).equals(DateTime.parse(loanInfoRedisDTO.getDueDate()))){
				loanInfoRedisDTO.setVerifyFailReason(LoanInfoBizVerifyRuleDesc.DUEDATE_VERIFY);
				loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
				return false;
			}
			break;
		case DAY:
			if(!DateTime.parse(loanInfoRedisDTO.getLoanDate()).plusDays(Integer.parseInt(loanInfoRedisDTO.getLoanPeriod())).equals(DateTime.parse(loanInfoRedisDTO.getDueDate()))){
				loanInfoRedisDTO.setVerifyFailReason(LoanInfoBizVerifyRuleDesc.DUEDATE_VERIFY);
				loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
				return false;
			}
			break;
		}
		return true;
	}
	
	/**
	 * 还款计划基础格式校验
	 * @param repaymentPlanRedisDTO
	 * @return
	 */
	private boolean basicValidateRepaymentPlan(RepaymentPlanRedisDTO repaymentPlanRedisDTO){
		return basicCheck(ValidateRules.AMOUNT_REGEX,repaymentPlanRedisDTO.getFinanceAmount(),RepaymentPlanBasicVerifyRuleDesc.FINANCE_AMOUNT,repaymentPlanRedisDTO) &&
				basicCheck(ValidateRules.AMOUNT_REGEX,repaymentPlanRedisDTO.getFinanceBalance(),RepaymentPlanBasicVerifyRuleDesc.FINANCE_BALANCE,repaymentPlanRedisDTO) &&
				basicCheck(ValidateRules.DATE_REGEX,repaymentPlanRedisDTO.getLoanDate(),RepaymentPlanBasicVerifyRuleDesc.LOAN_DATE,repaymentPlanRedisDTO) &&
				basicCheck(ValidateRules.DATE_REGEX,repaymentPlanRedisDTO.getDueDate(),RepaymentPlanBasicVerifyRuleDesc.DUE_DATE,repaymentPlanRedisDTO) &&
				basicCheck(ValidateRules.DIGITAL_REGEX,repaymentPlanRedisDTO.getRepaymentPeriod(),RepaymentPlanBasicVerifyRuleDesc.REPAYMENT_PERIOD,repaymentPlanRedisDTO) &&
				basicCheck(ValidateRules.DATE_REGEX,repaymentPlanRedisDTO.getValueDate(),RepaymentPlanBasicVerifyRuleDesc.VALUE_DATE,repaymentPlanRedisDTO) &&
				basicCheck(ValidateRules.DATE_REGEX,repaymentPlanRedisDTO.getSettleInterestDate(),RepaymentPlanBasicVerifyRuleDesc.SETTLE_INTEREST_DATE,repaymentPlanRedisDTO) &&
				basicCheck(ValidateRules.DATE_REGEX,repaymentPlanRedisDTO.getRepaymentDate(),RepaymentPlanBasicVerifyRuleDesc.REPAYMENT_DATE,repaymentPlanRedisDTO) &&
				basicCheck(ValidateRules.AMOUNT_REGEX,repaymentPlanRedisDTO.getAccountRepaymentPrincipal(),RepaymentPlanBasicVerifyRuleDesc.ACCOUNT_REPAYMENT_PRINCIPAL,repaymentPlanRedisDTO) &&
				basicCheck(ValidateRules.AMOUNT_REGEX,repaymentPlanRedisDTO.getAccountRepaymentInterest(),RepaymentPlanBasicVerifyRuleDesc.ACCOUNT_REPAYMENT_INTEREST,repaymentPlanRedisDTO) &&
				basicCheck(ValidateRules.AMOUNT_REGEX,repaymentPlanRedisDTO.getAccountRepaymentServiceCharge(),RepaymentPlanBasicVerifyRuleDesc.ACCOUNT_REPAYMENT_SERVICE_CHARGE,repaymentPlanRedisDTO) &&
				basicCheck(ValidateRules.AMOUNT_REGEX,repaymentPlanRedisDTO.getAccountRepaymentTotal(),RepaymentPlanBasicVerifyRuleDesc.ACCOUNT_REPAYMENT_TOTAL,repaymentPlanRedisDTO);
	}
	
	/**
	 * 还款计划高级校验
	 * @param repaymentPlanRedisDTO
	 * @return
	 */
	private boolean advancedValidateForRepaymentPlan(RepaymentPlanRedisDTO repaymentPlanRedisDTO,String masterId,RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration) throws Exception{
		//高级校验
		//放款编号要在还款信息存在
		RepaymentLoanInfoExample repaymentLoanInfoExample = new RepaymentLoanInfoExample();
		repaymentLoanInfoExample.createCriteria().andLoanNoEqualTo(repaymentPlanRedisDTO.getLoanNo()).andFactorIdEqualTo(masterId);
		List<RepaymentLoanInfo> list = loanInfoMapper.selectByExample(repaymentLoanInfoExample);
		if( list == null || list.size() <= 0 ||list.size() > 1 ){
			repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.LOAN_INFO_VERIFY);
			return false;
		}
		RepaymentPlanExample repaymentPlanExample = new RepaymentPlanExample();
		repaymentPlanExample.createCriteria().andLoanNoEqualTo(repaymentPlanRedisDTO.getLoanNo()).andLoanInfoIdEqualTo(list.get(0).getId());
		List<RepaymentPlan> planList = repaymentPlanMapper.selectByExample(repaymentPlanExample);
		if(CollectionUtils.isNotEmpty(planList)){
			repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.REPAYMENT_PLAN_VERIFY);
			return false;
		}
		if(StringUtils.equals(repaymentPlanInfoRedisIntegration.getInputMethod(), InputMethod.EXCEL.name())){
			repaymentPlanRedisDTO.setLoanInfoId(list.get(0).getId());
		}
		if(StringUtils.isNotBlank(list.get(0).getCoreCompanyName())){
			repaymentPlanRedisDTO.setCoreCompanyId(list.get(0).getCoreCompanyId());
			repaymentPlanRedisDTO.setCoreCompanyName(list.get(0).getCoreCompanyName());
		}
		//同一保理商+放款编号的还款信息、还款计划放款日、到期日、融资金额、融资客户应相同
		if((new SimpleDateFormat("yyyy-MM-dd").parse(list.get(0).getLoanDate())).compareTo(new SimpleDateFormat("yyyy-MM-dd").parse(repaymentPlanRedisDTO.getLoanDate())) != 0){
			repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.LOAN_DATE_VERIFY);
			return false;
		}
		if((new SimpleDateFormat("yyyy-MM-dd").parse(list.get(0).getDueDate())).compareTo(new SimpleDateFormat("yyyy-MM-dd").parse(repaymentPlanRedisDTO.getDueDate())) != 0){
			repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.DUE_DATE_VERIFY);
			return false;
		}
		
		if(!(MoneyArithUtil.convertStringToMoney(repaymentPlanRedisDTO.getFinanceAmount()).longValue()==list.get(0).getFinanceAmount())){
			repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.FINANCE_AMOUNT_EQUAL_VERIFY);
			return false;
		}
		if(StringUtils.equals(repaymentPlanInfoRedisIntegration.getInputMethod(), InputMethod.EXCEL.name())){
			if( !StringUtils.equals(list.get(0).getFinanceCompany(), repaymentPlanRedisDTO.getFinanceCompany()) ){
				repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
				repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.FINANCE_COMPANY_VERIFY);
				return false;
			}
		}else{
			repaymentPlanRedisDTO.setFinanceCompany(list.get(0).getFinanceCompany());
		}
		if(MoneyArithUtil.convertStringToMoney(repaymentPlanRedisDTO.getFinanceAmount()).longValue() < MoneyArithUtil.convertStringToMoney(repaymentPlanRedisDTO.getFinanceBalance()).longValue()){
			repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.FINANCE_AMOUNT_VERIFY);
			return false;
		}
		if((repaymentPlanRedisDTO.getValueDate()).compareTo(repaymentPlanRedisDTO.getSettleInterestDate())>0){
			repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.VALUE_DATE_VERIFY);
			return false;
		}
		if((repaymentPlanRedisDTO.getRepaymentDate()).compareTo(repaymentPlanRedisDTO.getSettleInterestDate())<0){
			repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.REPAYMENT_DATE_VERIFY_2);
			return false;
		}
		if(StringUtils.equals(repaymentPlanRedisDTO.getSettleStatus(), SettleStatus.UNSETTLE.desc()) && (repaymentPlanRedisDTO.getRepaymentDate()).compareTo(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))<0){
			repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.REPAYMENT_DATE_VERIFY);
			return false;
		}
		if(!(MoneyArithUtil.convertStringToMoney(repaymentPlanRedisDTO.getAccountRepaymentPrincipal()).longValue()<=MoneyArithUtil.convertStringToMoney(repaymentPlanRedisDTO.getFinanceBalance()).longValue())){
			repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.ACCOUNT_REPAYMENT_PRINCIPAL_VERIFY);
			return false;
		}
		if(!(MoneyArithUtil.convertStringToMoney(repaymentPlanRedisDTO.getAccountRepaymentTotal()).longValue()==MoneyArithUtil.convertStringToMoney(repaymentPlanRedisDTO.getAccountRepaymentPrincipal())+MoneyArithUtil.convertStringToMoney(repaymentPlanRedisDTO.getAccountRepaymentInterest())+MoneyArithUtil.convertStringToMoney(repaymentPlanRedisDTO.getAccountRepaymentServiceCharge()).longValue())){
			repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.ACCOUNT_REPAYMENT_TOTAL_VERIFY);
			return false;
		}
		if(!StringUtils.equals(repaymentPlanRedisDTO.getSettleStatus(), SettleStatus.SETTLED.desc()) && !StringUtils.equals(repaymentPlanRedisDTO.getSettleStatus(), SettleStatus.UNSETTLE.desc())){
			repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.SETTLEMENT_STATE_VERIFY);
			return false;
		}
		// 最后一期的还款日 = 放款信息的到期日
		if(StringUtils.equals(repaymentPlanRedisDTO.getRepaymentPeriod(), list.get(0).getRepaymentPeriod().toString())){
			if(!StringUtils.equals(repaymentPlanRedisDTO.getRepaymentDate(), list.get(0).getDueDate())){
				repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
				repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.LOAN_DATE_VERIFY_2);
				return false;
			}
		}
		// 上一期的结息日+1天 = 后一期的起息日
		String lastSettleInterestDate = repaymentPlanInfoRedisIntegration.getLastSettleInterestDate(repaymentPlanRedisDTO);
		if(StringUtils.isNotBlank(lastSettleInterestDate)){
			if(! new DateTime(new SimpleDateFormat("yyyy-MM-dd").parse(lastSettleInterestDate).getTime()).plusDays(1).equals(new DateTime(new SimpleDateFormat("yyyy-MM-dd").parse(repaymentPlanRedisDTO.getValueDate()).getTime()))){
				repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
				repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.VALUE_DATE_VERIFY_3);
				return false;
			}
		}
		// 中间期数还款日不能大于到期日
		if(Integer.parseInt(repaymentPlanRedisDTO.getRepaymentPeriod()) > 1 && Integer.parseInt(repaymentPlanRedisDTO.getRepaymentPeriod()) < list.get(0).getRepaymentPeriod()){
			if((new SimpleDateFormat("yyyy-MM-dd").parse(list.get(0).getDueDate())).compareTo(new SimpleDateFormat("yyyy-MM-dd").parse(repaymentPlanRedisDTO.getDueDate())) < 0){
				repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
				repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.REPAYMENT_DATE_VERIFY_4);
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * 还款计划费用列表基础格式校验
	 * @param repaymentExpenseRedisDTO
	 * @return
	 */
	private boolean basicValidateRepaymentExpense(RepaymentExpenseRedisDTO repaymentExpenseRedisDTO){
		return basicCheck(ValidateRules.DATE_REGEX,repaymentExpenseRedisDTO.getRepaymentDate(),RepaymentExpenseBasicVerifyRuleDesc.REPAYMENT_DATE,repaymentExpenseRedisDTO) &&
				basicCheck(ValidateRules.AMOUNT_REGEX,repaymentExpenseRedisDTO.getRepaymentAmount(),RepaymentExpenseBasicVerifyRuleDesc.REPAYMENT_AMOUNT,repaymentExpenseRedisDTO);
	}
	
	/**
	 * 还款计划费用列表高级校验
	 * @param repaymentExpenseRedisDTO
	 * @return
	 */
	private boolean advancedValidateForRepaymentExpense(RepaymentExpenseRedisDTO repaymentExpenseRedisDTO,String masterId,List<RepaymentExpenseRedisDTO> repaymentExpenseCorrectList) throws Exception{
		//还款信息中不存在此还款编号
		RepaymentLoanInfoExample repaymentLoanInfoExample = new RepaymentLoanInfoExample();
		repaymentLoanInfoExample.createCriteria().andLoanNoEqualTo(repaymentExpenseRedisDTO.getLoanNo()).andFactorIdEqualTo(masterId);
		List<RepaymentLoanInfo> list = loanInfoMapper.selectByExample(repaymentLoanInfoExample);
		if( list == null || list.size() <= 0 ||list.size() > 1 ){
			repaymentExpenseRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			repaymentExpenseRedisDTO.setVerifyFailReason(RepaymentExpenseBizVerifyRuleDesc.LOAN_INFO_VERIFY);
			return false;
		}
		if(StringUtils.isNotBlank(list.get(0).getCoreCompanyName())){
			repaymentExpenseRedisDTO.setCoreCompanyId(list.get(0).getCoreCompanyId());
			repaymentExpenseRedisDTO.setCoreCompanyName(list.get(0).getCoreCompanyName());
		}
		RepaymentExpenseExample repaymentExpenseExample = new RepaymentExpenseExample();
		repaymentExpenseExample.createCriteria().andLoanNoEqualTo(repaymentExpenseRedisDTO.getLoanNo()).andExpenseSubjectEqualTo(repaymentExpenseRedisDTO.getExpenseSubject()).andRepaymentDateEqualTo(repaymentExpenseRedisDTO.getRepaymentDate());
		List<RepaymentExpense> repaymentExpenseList = repaymentExpenseMapper.selectByExample(repaymentExpenseExample);
		if(CollectionUtils.isNotEmpty(repaymentExpenseList)){
			repaymentExpenseRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			repaymentExpenseRedisDTO.setVerifyFailReason(repaymentExpenseRedisDTO.getExpenseSubject()+" "+repaymentExpenseRedisDTO.getRepaymentDate()+" "+RepaymentExpenseBizVerifyRuleDesc.LOAN_INFO_VERIFY_2);
			return false;
		}
		if(CollectionUtils.isNotEmpty(repaymentExpenseCorrectList)){
			for(RepaymentExpenseRedisDTO repaymentExpenseRedisDTOTemp:repaymentExpenseCorrectList){
				if(StringUtils.equals(repaymentExpenseRedisDTO.getExpenseSubject(), repaymentExpenseRedisDTOTemp.getExpenseSubject()) && StringUtils.equals(repaymentExpenseRedisDTO.getRepaymentDate(), repaymentExpenseRedisDTOTemp.getRepaymentDate())){
					repaymentExpenseRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
					repaymentExpenseRedisDTO.setVerifyFailReason(repaymentExpenseRedisDTO.getExpenseSubject()+" "+repaymentExpenseRedisDTO.getRepaymentDate()+" "+RepaymentExpenseBizVerifyRuleDesc.LOAN_INFO_VERIFY_2);
					return false;
				}
			}
		}
		repaymentExpenseRedisDTO.setLoanInfoId(list.get(0).getId());
		//6.融资客户与还款信息中不符
		if( !StringUtils.equals(list.get(0).getFinanceCompany(), repaymentExpenseRedisDTO.getFinanceCompany()) ){
			repaymentExpenseRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			repaymentExpenseRedisDTO.setVerifyFailReason(RepaymentExpenseBizVerifyRuleDesc.FINANCE_COMPANY_VERIFY);
			return false;
		}
		if(StringUtils.equals(repaymentExpenseRedisDTO.getSettleStatus(), SettleStatus.UNSETTLE.desc()) && (repaymentExpenseRedisDTO.getRepaymentDate()).compareTo(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))<0){
			repaymentExpenseRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			repaymentExpenseRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.REPAYMENT_DATE_VERIFY);
			return false;
		}
		if(!StringUtils.equals(repaymentExpenseRedisDTO.getSettleStatus(), SettleStatus.SETTLED.desc()) && !StringUtils.equals(repaymentExpenseRedisDTO.getSettleStatus(), SettleStatus.UNSETTLE.desc())){
			repaymentExpenseRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			repaymentExpenseRedisDTO.setVerifyFailReason(RepaymentExpenseBizVerifyRuleDesc.SETTLEMENT_STATE_VERIFY);
			return false;
		}
		return true;
	}
	
	private boolean basicCheck(String regex, String value, String field, RepaymentPlanRedisDTO repaymentPlanRedisDTO){
		if(!Pattern.matches(regex, value)){
			repaymentPlanRedisDTO.setVerifyFailReason(field + "格式不正确");
			repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			return false;
		}
		return true;
	}
	
	private boolean basicCheck(String regex, String value, String field, RepaymentExpenseRedisDTO repaymentExpenseRedisDTO){
		if(!Pattern.matches(regex, value)){
			repaymentExpenseRedisDTO.setVerifyFailReason(field + "格式不正确");
			repaymentExpenseRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
			return false;
		}
		return true;
	}
	
	/**
	 * 期数校验(Excel导入用)
	 * @param afterClassifyList
	 * @param repaymentPlanRedisDTOCorrectList
	 * @param repaymentPlanRedisDTOIncorrectList
	 * @param masterId
	 */
	public void repaymentPeriodValidateForExcel(List<List<RepaymentPlanRedisDTO>> afterClassifyList, List<RepaymentPlanRedisDTO> repaymentPlanRedisDTOCorrectList,List<RepaymentPlanRedisDTO> repaymentPlanRedisDTOIncorrectList,String masterId) throws Exception{
		int i = 0;
		for(List<RepaymentPlanRedisDTO> list:afterClassifyList){
			boolean flag = true;
			RepaymentLoanInfoExample repaymentLoanInfoExample = new RepaymentLoanInfoExample();
			repaymentLoanInfoExample.createCriteria().andLoanNoEqualTo(afterClassifyList.get(i).get(0).getLoanNo()).andFactorIdEqualTo(masterId);
			List<RepaymentLoanInfo> LoanInfoList = loanInfoMapper.selectByExample(repaymentLoanInfoExample);
			// 按照期数排序
			Collections.sort(list, new Comparator<RepaymentPlanRedisDTO>(){
				@Override
				public int compare(RepaymentPlanRedisDTO o1,RepaymentPlanRedisDTO o2) {
					if(StringUtils.equals(o1.getLoanNo(), o2.getLoanNo())){
						int repaymentPeriodO1 = Integer.parseInt(o1.getRepaymentPeriod());
						int repaymentPeriodO2 = Integer.parseInt(o2.getRepaymentPeriod());
						if( repaymentPeriodO1 > repaymentPeriodO2 )
							return 1;
						else if(repaymentPeriodO1 == repaymentPeriodO2)
							return 0;
						else
							return -1;
					}else{
						return o1.getLoanNo().compareTo(o2.getLoanNo());
					}
				}
			});
			int index = 0;
			int repaymentPeriod = Integer.parseInt(list.get(0).getRepaymentPeriod());
			for(RepaymentPlanRedisDTO incorrectRepaymentPlanRedisDTO:repaymentPlanRedisDTOIncorrectList){
				if(StringUtils.equals(afterClassifyList.get(i).get(0).getLoanNo(), incorrectRepaymentPlanRedisDTO.getLoanNo())){
					flag = false;
					break;
				}
			}
			for(RepaymentPlanRedisDTO repaymentPlanRedisDTO:list){
				if(!flag){
					repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.REPAYMENT_PERIOD_VERIFY_2);
					repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
					repaymentPlanRedisDTOIncorrectList.add(repaymentPlanRedisDTO);
				}else{
					//判断当期的还款日是否大于上期
					if(index>0){
						if( new SimpleDateFormat("yyyy-MM-dd").parse(repaymentPlanRedisDTO.getRepaymentDate()).compareTo(new SimpleDateFormat("yyyy-MM-dd").parse(list.get(index-1).getRepaymentDate())) <= 0 ){
							repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.REPAYMENT_DATE_VERIFY_3);
							repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
							if( list.size()==(index+1) || index==0 ){
								repaymentPlanRedisDTOIncorrectList.add(repaymentPlanRedisDTO);
								continue;
							}else{
								for(RepaymentPlanRedisDTO repaymentPlanRedisDTOTemp:list){
									repaymentPlanRedisDTOTemp.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.REPAYMENT_DATE_VERIFY_3);
									repaymentPlanRedisDTOTemp.setVerifyStatus(VerifyStatus.NEGATIVE.name());
									repaymentPlanRedisDTOIncorrectList.add(repaymentPlanRedisDTOTemp);
								}
								break;
							}
						}
					}
					//判断期数是否连续以及不大于还款信息中的期数
					if( repaymentPeriod !=Integer.parseInt(repaymentPlanRedisDTO.getRepaymentPeriod()) || repaymentPeriod > LoanInfoList.get(0).getRepaymentPeriod()){
						repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
						repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.REPAYMENT_PERIOD_VERIFY);
						if( list.size()==(index+1) || index==0 ){
							repaymentPlanRedisDTOIncorrectList.add(repaymentPlanRedisDTO);
							continue;
						}else{
							for(RepaymentPlanRedisDTO repaymentPlanRedisDTOTemp:list){
								repaymentPlanRedisDTOTemp.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.REPAYMENT_PERIOD_VERIFY);
								repaymentPlanRedisDTOTemp.setVerifyStatus(VerifyStatus.NEGATIVE.name());
								repaymentPlanRedisDTOIncorrectList.add(repaymentPlanRedisDTOTemp);
							}
							break;
						}
					}
					index++;
					repaymentPeriod++;
				}
			}
			i++;
		}
	}
	
	/**
	 * 期数校验(手工录入用)
	 * @param repaymentPlanRedisDTO
	 * @param repaymentPlanRedisDTOCorrectList
	 * @param id
	 * @throws Exception
	 */
	public void repaymentPeriodValidateForManual(RepaymentPlanRedisDTO repaymentPlanRedisDTO,List<RepaymentPlanRedisDTO> repaymentPlanRedisDTOCorrectList,String id) throws Exception{
		RepaymentPlanExample repaymentPlanExample = new RepaymentPlanExample();
		repaymentPlanExample.createCriteria().andLoanInfoIdEqualTo(id);
		RepaymentLoanInfo repaymentLoanInfo = loanInfoMapper.selectByPrimaryKey(id);
		List<RepaymentPlan> repaymentPlanList = repaymentPlanMapper.selectByExample(repaymentPlanExample);
		List<RepaymentPlanRedisDTO> repaymentPlanRedisDTOList = new ArrayList<RepaymentPlanRedisDTO>();
		if(CollectionUtils.isNotEmpty(repaymentPlanRedisDTOCorrectList)){
			for(RepaymentPlanRedisDTO repaymentPlanRedisDTOTemp:repaymentPlanRedisDTOCorrectList){
				if(!repaymentPlanRedisDTOTemp.equals(repaymentPlanRedisDTO)){
					repaymentPlanRedisDTOList.add(repaymentPlanRedisDTOTemp);
				}
			}
		}
		repaymentPlanRedisDTOList.add(repaymentPlanRedisDTO);
		//获取数据库中已保存的还款计划
		for(RepaymentPlan repaymentPlan:repaymentPlanList){
			RepaymentPlanRedisDTO repaymentPlanRedisDTOTemp = new RepaymentPlanRedisDTO(); 
			BeanUtils.copyProperties(repaymentPlan, repaymentPlanRedisDTOTemp);
			repaymentPlanRedisDTOTemp.setRepaymentPeriod(repaymentPlan.getRepaymentPeriod().toString());
			repaymentPlanRedisDTOTemp.setFinanceAmount(MoneyArithUtil.convertMoneyToString(repaymentPlan.getFinanceAmount()));
			repaymentPlanRedisDTOTemp.setFinanceBalance(MoneyArithUtil.convertMoneyToString(repaymentPlan.getFinanceBalance()));
			repaymentPlanRedisDTOTemp.setAccountRepaymentPrincipal(MoneyArithUtil.convertMoneyToString(repaymentPlan.getAccountPrincipal()));
			repaymentPlanRedisDTOTemp.setAccountRepaymentInterest(MoneyArithUtil.convertMoneyToString(repaymentPlan.getAccountInterest()));
			repaymentPlanRedisDTOTemp.setAccountRepaymentServiceCharge(MoneyArithUtil.convertMoneyToString(repaymentPlan.getAccountServiceCharge()));
			repaymentPlanRedisDTOTemp.setSettleStatus(SettleStatus.valueOf(repaymentPlan.getSettleStatus()).desc());
			repaymentPlanRedisDTOTemp.setEditAble(EditAble.EDITUNADLE.name());
			repaymentPlanRedisDTOList.add(repaymentPlanRedisDTOTemp);
		}
		//按照期数排序
		if(CollectionUtils.isNotEmpty(repaymentPlanRedisDTOList) && repaymentPlanRedisDTOList.size()>1){
			Collections.sort(repaymentPlanRedisDTOList, new Comparator<RepaymentPlanRedisDTO>(){
				@Override
				public int compare(RepaymentPlanRedisDTO o1,RepaymentPlanRedisDTO o2) {
					if(StringUtils.equals(o1.getLoanNo(), o2.getLoanNo())){
						int repaymentPeriodO1 = Integer.parseInt(o1.getRepaymentPeriod());
						int repaymentPeriodO2 = Integer.parseInt(o2.getRepaymentPeriod());
						return repaymentPeriodO1 - repaymentPeriodO2;
					}else{
						return o1.getLoanNo().compareTo(o2.getLoanNo());
					}
				}
			});
		}
		//期数校验
		String lastRepaymentDate = "0001-01-01";
		int i=1;
		for(RepaymentPlanRedisDTO repaymentPlanRedisDTOTemp:repaymentPlanRedisDTOList){
			//判断当期的还款日是否大于上期
			if(new SimpleDateFormat("yyyy-MM-dd").parse(repaymentPlanRedisDTOTemp.getRepaymentDate()).compareTo(new SimpleDateFormat("yyyy-MM-dd").parse(lastRepaymentDate)) <= 0){
				repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.REPAYMENT_DATE_VERIFY_3);
				repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
				return;
			}
			//判断期数是否连续以及不大于还款信息中的期数
			if(i!=Integer.parseInt(repaymentPlanRedisDTOTemp.getRepaymentPeriod()) || i > repaymentLoanInfo.getRepaymentPeriod()){
				repaymentPlanRedisDTO.setVerifyFailReason(RepaymentPlanBizVerifyRuleDesc.REPAYMENT_PERIOD_VERIFY);
				repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
				return;
			}
			i++;
			lastRepaymentDate = repaymentPlanRedisDTOTemp.getRepaymentDate();
		}
		repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.PASS.name());
	}
}
