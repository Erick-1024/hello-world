/**
 * 
 */
package com.cana.yundaex.service.convertors;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.credit.limit.dao.po.CreditLimitAudit;
import com.cana.vbam.common.asset.dto.ContractInfoDTO;
import com.cana.vbam.common.asset.dto.FactorInfo;
import com.cana.vbam.common.asset.dto.ProjectInfo;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.repayment.enums.BusinessMode;
import com.cana.vbam.common.repayment.enums.Currency;
import com.cana.vbam.common.repayment.enums.InputMethod;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.yundaex.dto.loanApply.YundaexLoanApplyQueryDTO;
import com.cana.yundaex.dao.po.YundaexLoanInfoRecord;
import com.cana.yundaex.dao.utils.IDGenerator;

/**
 * @author guguanggong
 *
 */
public class YundaexLoanApplyConvert {
	public static YundaexLoanInfoRecord convertYundaexLoanApply2LoanInfoRecord(YundaexLoanApplyQueryDTO yundaexLoanApplyQueryDTO, ProjectInfo projectInfo, ContractInfoDTO contractInfoDTO, UserSessionDTO userSessionDTO, String businessSeq, String accountNo) {
		FactorInfo factorInfo = projectInfo.getFactors().get(0); // 资金方信息
		String id = IDGenerator.generateLoanInfoRecordId();
		String masterId = StringUtils.isBlank(userSessionDTO.getMasterId()) ? userSessionDTO.getId() : userSessionDTO.getMasterId();
		YundaexLoanInfoRecord record = new YundaexLoanInfoRecord();
		record.setId(id);
		record.setLoanNo(id); // 转入 放款信息表中更改
		record.setBusinessSeq(businessSeq);
		record.setBusinessMode(BusinessMode.FACTORANDFINACE.name()); // 可以不用 业务模式
		record.setInputMethod(InputMethod.AUTO.name()); // 可以不用 录入方式
		record.setFactorId(factorInfo.getCompanyId()); // 资金方ID
		record.setFactorCompany(factorInfo.getCompanyName()); // 资金方名称
		record.setFinanceId(masterId);
		record.setFinanceCompany(userSessionDTO.getCompanyName());
		record.setCoreCompanyId(projectInfo.getCoreCompanyId()); // 核心企业ID
		record.setCoreCompanyName(projectInfo.getCoreCompanyName()); // 核心企业名称
		record.setCurrency(Currency.RMB.name());
		record.setBusinessProductId(projectInfo.getId()); // 项目ID
		record.setBusinessProduct(projectInfo.getProjectName()); // 项目名称
		record.setReceivablesAmount(yundaexLoanApplyQueryDTO.getPaymentFee()); // 应收账款金额
		record.setReceivablesBalance(yundaexLoanApplyQueryDTO.getPaymentFee()); // 应收账款余额
		record.setFinanceAmount(yundaexLoanApplyQueryDTO.getPaymentFee());
		record.setFinanceBalance(yundaexLoanApplyQueryDTO.getPaymentFee());
		record.setInterestRateUnit(yundaexLoanApplyQueryDTO.getInterestRateUnit());
		record.setInterestRate(yundaexLoanApplyQueryDTO.getInterestRate());

		record.setAccountNo(contractInfoDTO.getAccountNo()); // 监管账户
		record.setAccountSupervisionId(contractInfoDTO.getAccountSupervisionId()); // 监管关系

		record.setLoanPeriodUnit(yundaexLoanApplyQueryDTO.getLoanPeriodUnit()); // 放款期限单位
		record.setLoanPeriod(yundaexLoanApplyQueryDTO.getLoanPeriod()); // 放款期限
		record.setLoanDate(yundaexLoanApplyQueryDTO.getLoanDate()); // 放款日
		record.setDueDate(yundaexLoanApplyQueryDTO.getDueDate()); // 到期日
		record.setRepaymentMethod(yundaexLoanApplyQueryDTO.getRepaymentMethod()); // 还款方式
		record.setSettleStatus(SettleStatus.UNSETTLE.name());
		record.setFactorTransferInAccountNo(factorInfo.getAccountNo()); // 保理商回款账号
		record.setDeductionTime(projectInfo.getDeductionTime()); // 保理商指定账扣时间
		record.setDeductionRule(projectInfo.getDeductionRule().name()); // 扣款规则
		record.setExtensionDays(projectInfo.getExtensionDays()); // 展期天数
		record.setExtensionRatio(projectInfo.getExtensionRatio()); // 展期上浮利率值 或者是 展期上浮比例
		record.setExtensionChargeMethod(projectInfo.getExtensionChargeMethod().name()); // 展期率计算方式
		record.setEarlyRepaymentChargeRatio(projectInfo.getEarlyRepaymentChargeRatio()); // 提前还款手续费率
		record.setPenaltyRate(projectInfo.getPenaltyRatio()); // 罚息率或者是 罚息上浮比例
		record.setChargeMethod(projectInfo.getPenaltyChargeMethod().name()); // 罚息计算方式
		record.setUseHolidayPolicy(projectInfo.isUseHolidayPolicy()); // 是否使用节假日
		record.setAccountNo(accountNo);
		
		record.setCreateTime(new Date());
		return record;
	}
	
	public static CreditLimitAudit convertCreditLimit2CreditLimitAudit(CreditLimit yundaexCreditLimit, Long newUsedLimit,
			YundaexLoanInfoRecord loanInfoRecord, String loanType) {
		CreditLimitAudit yundaexCreditLimitAudit = new CreditLimitAudit();
		yundaexCreditLimitAudit.setId(IDGenerator.generateCreditLimitAuditId());
		yundaexCreditLimitAudit.setLimitId(yundaexCreditLimit.getId());
		yundaexCreditLimitAudit.setType(loanType);
		yundaexCreditLimitAudit.setPrevTotalLimit(yundaexCreditLimit.getTotalLimit());
		yundaexCreditLimitAudit.setTotalLimit(yundaexCreditLimit.getTotalLimit());
		yundaexCreditLimitAudit.setPrevUsedLimit(yundaexCreditLimit.getUsedLimit() == null ? 0l : yundaexCreditLimit.getUsedLimit());
		yundaexCreditLimitAudit.setUsedLimit(newUsedLimit);
		yundaexCreditLimitAudit.setLoanId(loanInfoRecord.getId()); // 此时没有放款信息，先用放款记录ID
		yundaexCreditLimitAudit.setLoanNo(loanInfoRecord.getLoanNo());
		yundaexCreditLimitAudit.setCreateTime(new Date());
		return yundaexCreditLimitAudit;
	}
}
